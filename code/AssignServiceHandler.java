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
import java.time.Instant;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
public class AssignServiceHandler implements AssignService.Iface
{	
	private String[] nodeIp = {"localhost","localhost","localhost","localhost"};
	private ArrayList<MapResult> unsortedArray = new ArrayList<MapResult>();
	@Override
	public ClientResult assign(String folderAddress) throws TException {
		Instant start = Instant.now();
		ClientResult ret = new ClientResult();
		try{
			File file = new File(folderAddress);
			String[] fileList = file.list();
			for(int i = 0; i < fileList.length; i++){
				String fileAddress = folderAddress + "/" + fileList[i];
				Runnable callMap = new Runnable() {
					public void run() { 
						callMap(fileAddress);
					}	
				};
				Thread thread = new Thread(callMap);
				thread.start();	
			}
			while(unsortedArray.size() < fileList.length){
			}
			ret.fileOrder = callSort() ;
			Instant end = Instant.now();
			int time = (int) Duration.between(start, end).getSeconds();
			ret.time = time;

			
		} catch (Exception e) {
			System.err.println("Directory '" + folderAddress + "' not found.");
		}
		return ret;		
			
	}
	
	private void callMap(String address){
		boolean flag = false;
		int acceptNodeId = 0;
		while(!flag){
		        try {		
				TTransport  transport = new TSocket(nodeIp[acceptNodeId], 9090);
				TProtocol protocol = new TBinaryProtocol(transport);
				MapService.Client client = new MapService.Client(protocol);

				//Try to connect
				transport.open();

				//What you need to do
				flag = client.accept(acceptNodeId);
				if(flag) {
					System.out.println("Node " + acceptNodeId + "accept task " + address + "!");
					unsortedArray.add(client.mapping(address));
					System.out.println("Task '"+address+"' finished");
				} else{
					acceptNodeId = (acceptNodeId + 1) % 4;
				}
			} catch(TException e) {

			}
		}

	}

	private String callSort(){
		try {
           		 TTransport  transport = new TSocket(nodeIp[0], 9097);
               		 TProtocol protocol = new TBinaryProtocol(transport);
               		 MapService.Client client = new MapService.Client(protocol);
               		 //Try to connect
                	transport.open();
               		 //What you need to do
                	return client.sort(unsortedArray);
       		 } catch(TException e) {
           		 return "";
       		 }
	}
}