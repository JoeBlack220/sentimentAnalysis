import org.apache.thrift.TException;
import org.apache.thrift.transport.TTransportFactory;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSSLTransportFactory.TSSLTransportParameters;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.concurrent.ThreadPoolExecutor;
import java.time.Instant;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ExecutorService; 
import java.util.concurrent.Executors;	
import java.util.Random;
public class AssignServiceHandler implements AssignService.Iface
{	
	// Runnable class for threads to call mapping service
	public class Task implements Runnable
	{
       		private String address;
		private int injectMode;
       		public Task(String s, int m) {
               		address = s;
			injectMode = m;
        	}
        	public void run() {
                	callMap(address, injectMode);
        	}
	}
	// PLEASE MODIFY HERE TO CHANGE NODE IP ADDRESSES TO YOURS
	private String[] nodeIp = {"128.101.35.181","128.101.35.195","128.101.35.178","128.101.35.163"};
	// sotre final mapping results in this arraylist
	private ArrayList<MapResult> unsortedArray = new ArrayList<MapResult>();
	// use vector as intermediate container because it's thread-safe
	// data in vector will be added to the arraylist at the end
	private Vector saveResult = new Vector(3,2);
	private String folderAddress = null;
	@Override
	public ClientResult assign(String folderAddress, int mode) throws TException {
		this.folderAddress = folderAddress;
		// Start counting time
		Instant start = Instant.now();
		// empty vector
		saveResult.clear();
		ClientResult ret = new ClientResult();
		try{
			// read the input_dir
			File file = new File(folderAddress);
			// save every task's name to a fileList
			String[] fileList = file.list();
			// create tasks list
			ArrayList<Task> tasks = new ArrayList<>();
			for(int i = 0; i < fileList.length; i++){
				// make complete file address
				String fileAddress = folderAddress + "/" + fileList[i];
				// create a new task
				Task tmp = new Task(fileAddress, mode);
				tasks.add(tmp);
			}
			// define the size of the threadpool in the server
			int threadNum = 30;
			// define thread pool
			ExecutorService pool = Executors.newFixedThreadPool(threadNum);
			for (int i = 0; i < tasks.size(); i ++) {
				pool.execute(tasks.get(i));
			}
			// wait for all the files to be finished
			while(saveResult.size() != fileList.length){
				try {
					// wait 0.5 seconds then count the finished task number
      					Thread.sleep(500);
					System.out.println("Now we have finished " + saveResult.size() + " tasks.");
					System.out.println("Currently " +((ThreadPoolExecutor)pool).getActiveCount()+ " tasks are running."); 
				}catch (InterruptedException e) {
				}
			}
			// finished mapping, calling the first node to do the sorting job
			System.out.println("Start assign sorting task to the node 0.");
			ret.fileOrder = callSort() ;
			Instant end = Instant.now();
			// record the time a job takes
			int time = (int) Duration.between(start, end).getSeconds();
			ret.time = time;

			
		} catch (Exception e) {
			System.err.println("Directory '" + folderAddress + "' not found.");
		}
		// return result
		return ret;		
			
	}
	
	private void callMap(String address, int mode){
		// this flag tests whether a node accept a task
		boolean flag = false;
		// when start calling map, first assign the task to the first node, if it rejects, then assign it to the next node
		int acceptNodeId = 0;
		// this flag indicates which mode we are using 0-load balancing 1-random mode 2-inject mode
		// when we use injection or random mode, assign a task to a random node
		if (mode == 1 || mode == 2) {
			Random rand = new Random();
			acceptNodeId = rand.nextInt(4);
		}
		while(!flag){
		        try {		
				TTransport  transport = new TSocket(nodeIp[acceptNodeId], 9996);
				TProtocol protocol = new TBinaryProtocol(transport);
				MapService.Client client = new MapService.Client(protocol);

				// Try to connect
				transport.open();

				// What you need to do
				// if we are in random mode, we don't have to call accept function, just assgin the task to a random node
				if(mode == 1) flag = true;
				else flag = client.accept(acceptNodeId);
				if(flag) {
					// print whether a node accepts
					if(mode == 0) System.out.println("In load balancing load.");
					else if(mode == 1) System.out.println("In random mode.");
					else System.out.println("In inject mode");
					System.out.println("Node " + acceptNodeId + " accept task " + address + "!");
					saveResult.addElement(client.mapping(address));
					System.out.println("Task '"+address+"' finished by node " + acceptNodeId +".");
				// in inject mode, the reject just means some delay, the assigned node still have to do the computing
				} else if (!flag && mode==2) {
					System.out.println("In Inject Mode.");
					try{	
						// if a node doesn't accept and it is in injection mode, delay few seconds	
    						Thread.sleep(3000);
					}catch(InterruptedException ex){
   						Thread.currentThread().interrupt();
					}
					System.out.println("Node " + acceptNodeId + " accept the task after delay in injection mode.");
					saveResult.addElement(client.mapping(address));
					System.out.println("Task '" + address + "' is finished by node " + acceptNodeId +".");
					// set the flag to true to ensure the loop goes on
					flag = true;
				} else{
					// if it's in normal mode and doesn't accept, assign the task to the next node
					acceptNodeId = (acceptNodeId + 1) % 4;
				}
			} catch(TException e) {

			}
		}

	}

	private String callSort(){
		try {
           		 TTransport  transport = new TSocket(nodeIp[0], 9996);
               		 TProtocol protocol = new TBinaryProtocol(transport);
               		 MapService.Client client = new MapService.Client(protocol);
               		 //Try to connect
			transport.open();
               		 //What you need to do
			unsortedArray.addAll(saveResult);
                	return client.sort(unsortedArray,folderAddress);
       		 } catch(TException e) {
           		 return "";
       		 }
	}
}
