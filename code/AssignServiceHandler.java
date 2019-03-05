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
		private boolean injectMode;
       		public Task(String s, boolean m) {
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

	@Override
	public ClientResult assign(String folderAddress, boolean injectMode) throws TException {
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
				Task tmp = new Task(fileAddress, injectMode);
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
				}catch (InterruptedException e) {
				}
			}
			// finished mapping, calling the first node to do the sorting job
			System.out.println("Start sorting.");
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
	
	private void callMap(String address, boolean mode){
		// this flag tests whether a node accept a task
		boolean flag = false;
		// when start calling map, first assign the task to the first node, if it rejects, then assign it to the next node
		int acceptNodeId = 0;
		// this flag tests whether we should do injection
		boolean injectMode = mode;
		// when we use injection, assign a task to a random node, if it says delay, then delay few seconds then send it;
		if (injectMode) {
			Random rand = new Random();
			int tmp = rand.nextInt(4);
			acceptNodeId = tmp % 4;
		}
		while(!flag){
		        try {		
				TTransport  transport = new TSocket(nodeIp[acceptNodeId], 9996);
				TProtocol protocol = new TBinaryProtocol(transport);
				MapService.Client client = new MapService.Client(protocol);

				//Try to connect
				transport.open();

				//What you need to do
				flag = client.accept(acceptNodeId);
				if(flag) {
					// print whether a node accepts
					System.out.println("Node " + acceptNodeId + "accept task " + address + "!");
					saveResult.addElement(client.mapping(address));
					System.out.println("Task '"+address+"' finished.");
				} else if (!flag && injectMode) {
					System.out.println("In Inject Mode.");
					try{	
						// if a node doesn't accept and it is in injection mode, delay few seconds	
    						Thread.sleep(3000);
					}catch(InterruptedException ex){
   						Thread.currentThread().interrupt();
					}
					System.out.println("Node " + acceptNodeId + " accept the task after delay in injection mode.");
					saveResult.addElement(client.mapping(address));
					System.out.println("Task finished.");
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
                	return client.sort(unsortedArray);
       		 } catch(TException e) {
           		 return "";
       		 }
	}
}
