import org.apache.thrift.TException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;
public class MapServiceHandler implements MapService.Iface
{      
	private String posAddress = "../data/positive.txt";
	private String negAddress = "../data/negative.txt";
	private String outDir = "../data/intermediate_dir";
	private Random rand = new Random();
	// use nodeMap to store the load probabilities of each node
	private HashMap<Integer, Double> nodeMap = new HashMap<Integer,Double>() {{
        	put(0, 0.8);
        	put(1, 0.6);
		put(2, 0.4);
		put(3, 0.2);
    	}};
	
	@Override
	public String sort(List<MapResult> unsorted) throws TException{
		// sort function is to sort the arraylist of MapResult from the server
		// MapResult are {filename, score}
		ArrayList<MapResult> temp = new ArrayList<>(unsorted.size());
		temp.addAll(unsorted);
		// sort class is the actual sort service provider
		// use sortObj to sort unsort MapResults
		Sort sortObj = new Sort(temp);
		return sortObj.getResult();
	}
	
	@Override
        public boolean accept(int nodeID) throws TException {
		// accept function is to tell server whether this node is willing to acept a new task
		// we generate a random number tmp, if it's larger than the load probability of this node(in percentage), return true
		// otherwise return false
		int tmp = rand.nextInt(100);
		if (tmp > nodeMap.get(nodeID) * 100) {
			System.out.println("I accept the task.");
			return true;
		} else {
			System.out.println("I reject/delay the task.");
			return false;
		}
	}

        @Override
        public MapResult mapping(String fileUri) throws TException {
		// mapping function is to compute the score of file whose address is this  fileUri
		MapResult res = null;		
		System.out.println("Start mapping " + fileUri + ".\n");
		// Map class is the actual map service provideer
		// use task to compute the score of this file
		// modify here is you want to change address of positive.txt/negative.txt/output_dir/intermediate_dir 
		Map task = new Map(posAddress, negAddress, fileUri, outDir);
		res = task.countFreq();
                return res;
        }
        

}

