import org.apache.thrift.TException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
public class MapServiceHandler implements MapService.Iface
{      
	private String posAddress = "../data/positive.txt";
	private String negAddress = "../data/negative.txt";
	private String outDir = "../data/intermediate_dir";
	private Random rand = new Random();
	// use nodeMap to store the load probabilities of each node
	private HashMap<Integer, Double> nodeMap = new HashMap<Integer,Double>() {{
        	put(0, 0.8);
        	put(1, 0.8);
		put(2, 0.8);
		put(3, 0.8);
    	}};
	
	@Override
	public String sort(List<MapResult> unsorted, String inputDir) throws TException{
		// sort function is to sort the arraylist of MapResult from the server
		// MapResult are {filename, score}
		ArrayList<MapResult> temp = new ArrayList<>(unsorted.size());
		temp.addAll(unsorted);
		// sort class is the actual sort service provider
		// use sortObj to sort unsort MapResults
		Sort sortObj = new Sort(temp,inputDir);
		return sortObj.getResult();
	}
	
	@Override
        public boolean accept(int nodeID) throws TException {
		// accept function is to tell server whether this node is willing to acept a new task
		// we generate a random number tmp, if it's larger than the load probability of this node(in percentage), return true
		// otherwise return false
		BufferedReader fis = null;
		try {
			fis = new BufferedReader(new FileReader(new File("./configure_probability.txt")));
			for (int i = 0; i < 4; i++){
				nodeMap.put(i,Double.valueOf(fis.readLine()));
			}
		} catch(Exception e) {
			System.err.println("Something wrong with the configuration file, using the default probability (all 0.8).");
		}
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

