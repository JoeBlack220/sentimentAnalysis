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
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
public class Server {
    public static AssignServiceHandler handler;
    public static AssignService.Processor processor;

    public static void main(String [] args) {
        try {
            handler = new AssignServiceHandler();
            processor = new AssignService.Processor(handler);

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

    public static void simple(AssignService.Processor processor) {
        try {
            BufferedReader fis = null;
            int serverPort = 9998;
            try {
                    fis = new BufferedReader(new FileReader(new File("./configure_serverport.txt")));
                    serverPort = Integer.parseInt(fis.readLine());
            } catch(Exception e) {
                        System.err.println("Something wrong with the configuration file, using the default probability (all 0.8).");
            }
            //Create Thrift server socket
            TServerTransport serverTransport = new TServerSocket(serverPort);
            TTransportFactory factory = new TFramedTransport.Factory();

            //Create service request handler
            AssignServiceHandler handler = new AssignServiceHandler();
            processor = new AssignService.Processor(handler);

            //Set server arguments
            TServer.Args args = new TServer.Args(serverTransport);
            args.processor(processor);  //Set handler
            args.transportFactory(factory);  //Set FramedTransport (for performance)

            //Run server as a multithread server
            TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));
            server.serve();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
