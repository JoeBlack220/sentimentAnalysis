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
	private String posAddress = "../data/positve.txt";
	private String negAddress = "../data/negative.txt";
	private String outDir = "../data/output_dir";
	private Random rand = new Random();
	// use nodeMap to store the load probabilities of each node
	private HashMap<Integer, Double> nodeMap;
			
	@Override
	public String sort(List<MapResult> unsorted, String inputDir) throws TException{
		BufferedReader fis = null;
		try {
			fis = new BufferedReader(new FileReader(new File("./configure_output.txt")));
                        outDir = fis.readLine();

		} catch(Exception e) {
			System.out.println("something wrong in sort read output configuration file");
		}
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
        public boolean accept(int nodeID, int mode) throws TException {
		// accept function is to tell server whether this node is willing to acept a new task
		// we generate a random number tmp, if it's larger than the load probability of this node(in percentage), return true
		// otherwise return false
		// mode 1 is random, mode 2 is load balancing
		BufferedReader fis = null;
//		try {
//			fis = new BufferedReader(new FileReader(new File("./configure_probability.txt")));
//			for (int i = 0; i < 4; i++){
//				nodeMap.put(i,Double.valueOf(fis.readLine()));
//			}
//		} catch(Exception e) {
//			System.err.println("Something wrong with the configuration file, using the default probability .");
			for (int i = 0; i < 4; i++){
				nodeMap.put(i,0.8);	
		}
//		}
		int tmp = rand.nextInt(100);
		System.out.println("generated a random number");
		if (tmp > nodeMap.get(nodeID) * 100 || mode == 1) {
			tmp = rand.nextInt(100);
			if (tmp > nodeMap.get(nodeID) * 100) {
				System.out.println("I accept the task.");
				return true;
			} else {
				System.out.println("I delay the task");
				try{
                               	        // if a node doesn't accept and it is in injection mode, delay few seconds
                                        Thread.sleep(3000);
                                    }catch(InterruptedException ex){
                                           Thread.currentThread().interrupt();
                                    }
				return true;
			}				
		} else {
			System.out.println("I reject the task.");
			return false;
		}
	}

        @Override
        public MapResult mapping(String fileUri) throws TException {
		BufferedReader fis = null;
		try {
                          fis = new BufferedReader(new FileReader(new File("./configure_probability.txt")));
                          double lp;
                          String tmp;
                          int i = 0;
                          while ((tmp = fis.readLine()) != null){
                                  nodeMap.put(i, Double.valueOf(tmp));
                                  i ++;
                          }
                          fis = new BufferedReader(new FileReader(new File("./configure_negpos.txt")));
                          posAddress = fis.readLine();
                          negAddress = fis.readLine();
                          fis = new BufferedReader(new FileReader(new File("./configure_output.txt")));
                          outDir = fis.readLine();
                  } catch(Exception e) {
                          System.err.println("Something wrong with the configuration file, using the default one.");
                 }

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

