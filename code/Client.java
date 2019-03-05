import org.apache.thrift.TException;
import org.apache.thrift.transport.TTransportFactory;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSSLTransportFactory.TSSLTransportParameters;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;

public class Client {
    public static void main(String [] args) {
	 try {
		// To run server and client in different machine, please modify the locahost to IP address of the server machine
		TTransport  transport = new TSocket("localhost", 9998);
		TProtocol protocol = new TBinaryProtocol(transport);
		AssignService.Client client = new AssignService.Client(protocol);
		
		// Try to connect
		transport.open();

		// Check which mode is in use
		// The default mode is load balancing
		int mode = 0;
		if(args.length!= 0){
			if(args[0].equals("balance")) {
				mode = 0;
			} else if(args[0].equals("random")){
				mode = 1;
			} else if(args[0].equals("inject")){
				mode = 2;	
			}
		}
		// Send input directory address and mode to server
		ClientResult finalResult = client.assign("../data/input_dir", mode);
		// Log final file score ranking and elapsed time
		System.out.println(finalResult.fileOrder + "\nTime taken is: " + finalResult.time);
		// Notice
		System.out.println("finished job!");
	} catch(TException e) {
	
        }

    }
}

