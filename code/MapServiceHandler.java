import org.apache.thrift.TException;

public class MapServiceHandler implements MapService.Iface
{      
	private String posAddress = "../data/positive.txt";
	private String negAddress = "../data/negative.txt";
	private String outDir = "../data/output_dir";
	@Override
        public boolean accept() throws TException {
		System.out.println("I got accept()");
		return true;
		}

        @Override
        public MapResult mapping(String fileUri) throws TException {
		MapResult res = null;		
		System.out.println("I got put\n");
		Map task = new Map(posAddress, negAddress, fileUri, outDir);
		res = task.countFreq();
                return res;
        }
        

}

