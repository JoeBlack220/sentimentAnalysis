import org.apache.thrift.TException;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TTransportFactory;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TSSLTransportFactory.TSSLTransportParameters;
import java.util.HashMap;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
// Generated code
public class Node {
    public static MapServiceHandler handler;
    public static MapService.Processor processor;

    public static void main(String [] args) {
	try{
		handler = new MapServiceHandler();
		processor = new MapService.Processor(handler);

		Runnable simple = new Runnable() {
		public void run() {
		simple(processor);
		}
	};

		new Thread(simple).start();
	} catch (Exception x) {
	x.printStackTrace();
	}
    }

    public static void simple(MapService.Processor processor) {
        try {
            BufferedReader fis = null;
            int nodePort = 9996;
            try {
                    fis = new BufferedReader(new FileReader(new File("./configure_nodeport.txt")));
                    nodePort = Integer.parseInt(fis.readLine());
			System.out.println("portnumber is "+nodePort);
            } catch(Exception e) {
                        System.err.println("Something wrong with the configuration file of ndoeport");
            }
            //Create Thrift server socket
            TServerTransport serverTransport = new TServerSocket(nodePort);
            TTransportFactory factory = new TFramedTransport.Factory();

            //Create service request handler
            MapServiceHandler handler = new MapServiceHandler();
            processor = new MapService.Processor(handler);

            //Set server arguments
            TSimpleServer.Args args = new TSimpleServer.Args(serverTransport);
            args.processor(processor);  //Set handler
            args.transportFactory(factory);  //Set FramedTransport (for performance)

            //Run server as a  multithread
	     TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));
	     System.out.println("A Server has started.");
	     server.serve();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

