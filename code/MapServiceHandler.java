import org.apache.thrift.TException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;
public class MapServiceHandler implements MapService.Iface
{      
	private String posAddress = "../data/positive.txt";
	private String negAddress = "../data/negative.txt";
	private String outDir = "../data/output_dir";
	private Random rand = new Random();
	private HashMap<Integer, Double> nodeMap = new HashMap<Integer,Double>() {{
        	put(0, 0.2);
        	put(1, 0.2);
		put(2, 0.2);
		put(3, 0.2);
    	}};
	
	@Override
	public String sort(List<MapResult> unsorted) throws TException{
		ArrayList<MapResult> temp = new ArrayList<>(unsorted.size());
		temp.addAll(unsorted);
		Sort sortObj = new Sort(temp);
		return sortObj.getResult();
	}	
	@Override
        public boolean accept(int nodeID) throws TException {
		System.out.println("I got accept()");
		int tmp = rand.nextInt(100);
		if (tmp > nodeMap.get(nodeID) * 100) return true;
		return false; 	
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

