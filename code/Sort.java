// Basic Java classes used for IO
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.net.URI;

// Useful built-in datastructures
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Sort{
	// The directory of outputs and inputs
	private String intDir = "../data/intermediate_dir";
	private String inputDir = "../data/input_dir";
	private String outputDir = "../data/output_dir";
	private String directName = "input_dir/";
	// file writer to write the final output file
	private FileWriter fwriter = null;
	private String result = "";
	// Array that stores key value pairs (filename,sentimentvalue)
	private PriorityQueue<MapResult> files = 
        new PriorityQueue<MapResult>(new Comparator<MapResult>() {
        	public int compare(MapResult a, MapResult b) {
        	if(b.score >= a.score) return 1;
		else return -1; 
                }
	});

	// Constructor
	public Sort(ArrayList<MapResult> unsorted){
	sort(unsorted);
	}
	//return the fileorder and score
	public String getResult(){
	return result;
	}
		
	// Sort the (filename, sentiment value) pairs and save them
	public void sort(ArrayList<MapResult> unsorted){
		for(int i = 0; i < unsorted.size(); i++){
		files.add(unsorted.get(i));
		}		
		writeOutput(outputDir+"/output.txt");
	}
	// Write the final output to a txt file
	public void writeOutput(String address){
	MapResult current = null;
	try{
	fwriter = new FileWriter(address);
	writeHelper(fwriter);
	fwriter.write("\n");
	fwriter.write("-- output for the " + directName + " directory --\n");
	while(files.size()!=0){
		current = files.poll();
		result = result + current.filename+" "+current.score+"\n";

		}
	fwriter.write(result);
	} catch (IOException ioe) {
	System.err.println("Caught exception writing file '" + 
	address + "'");
	} finally{
		try{
		fwriter.flush();
		fwriter.close();
		} catch (IOException ex) {
		ex.printStackTrace();
		}
	}
	}

	public void writeHelper(FileWriter fwriter) {

		File file = new File(intDir);
		String[] fileList = file.list();
		for(String fileName: fileList){
			try{
			BufferedReader intOut = new BufferedReader(
			new FileReader(new File(intDir + "/" + fileName)));
			int pos = Integer.parseInt(intOut.readLine());
			int neg = Integer.parseInt(intOut.readLine());
			double score = (pos - neg)/(double)(pos+neg);
			fwriter.write(directName + fileName.substring(0,fileName.length()-6) + "\n");
			fwriter.write("Positive word: " + pos + " Negative word: " + neg + "\n");
			fwriter.write("Sentiment score: " + score +"\n\n");
			} catch (IOException ioe){
			System.err.println("Caught exception converting intermediate results file '" + fileName + "'");
			}

		}

	}

	public static void main(String[] args){
	ArrayList<MapResult> test = new ArrayList<MapResult>();
	MapResult p1 = new MapResult();
	p1.score = -0.2;
	p1.filename = "comedies";
	MapResult p2 = new MapResult();
	p1.score = -0.1;
	p1.filename = "tragedies";
	MapResult p3 = new MapResult();
	p1.score = 0.2;
	p1.filename = "poems";
	MapResult p4 = new MapResult();
	p1.score = 1;
	p1.filename = "histories";


	test.add(p1);
	test.add(p2);
	test.add(p3);
	test.add(p4);
	Sort S1 = new Sort(test);
	S1.writeOutput(S1.outputDir+"/output.txt");


	}
	


}
