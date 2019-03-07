import org.apache.thrift.TException;
import org.apache.thrift.transport.TTransportFactory;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSSLTransportFactory.TSSLTransportParameters;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.HashMap;
public class Client {
    public static void main(String [] args) {
	 try {
		BufferedReader fis = null;
		int serverPort = 9998;
                try {
                        fis = new BufferedReader(new FileReader(new File("./configure_serverport.txt")));
                        serverPort = Integer.parseInt(fis.readLine());

                } catch(Exception e) {
                        System.err.println("Something wrong with the configuration file, using the default probability (all 0.8).");
                }
		// To run server and client in different machine, please modify the locahost to IP address of the server machine
		TTransport  transport = new TSocket("localhost", serverPort);
		TProtocol protocol = new TBinaryProtocol(transport);
		AssignService.Client client = new AssignService.Client(protocol);
		// default input dir
		String inputDir = "../data/input_dir";
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
			}
		}
		// accept parameters as the input dir
		if(args.length > 1) {
			File f = new File(args[1]);
			if(f.isDirectory()) inputDir = args[1]; 
		}
		System.out.println("Starting a job in directory " + inputDir + "!");
		// Send input directory address and mode to server
		ClientResult finalResult = client.assign(inputDir, mode);
		// Log final file score ranking and elapsed time
		System.out.println(finalResult.fileOrder + "\nTime taken is: " + finalResult.time);
		// Notice
		System.out.println("finished job!");
	} catch(TException e) {
	
        }

    }
}

