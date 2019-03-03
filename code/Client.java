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
        //Create client connect.
        try {
            TTransport  transport = new TSocket("localhost", 9097);
            TProtocol protocol = new TBinaryProtocol(transport);
            MapService.Client client = new MapService.Client(protocol);

            //Try to connect
            transport.open();
	    System.out.println("Connected to the server.");

            //What you need to do.
	    client.accept();
            MapResult ret1 = client.mapping("../data/example/comedies");
            System.out.printf("Successfully call mapping in the node\n");
	    System.out.println("The file is "+ ret1.filename + " The score is "+ ret1.score);
          //  System.out.printf("Successfully get %s from the server\n", ret2);
        } catch(TException e) {

        }

    }
}
