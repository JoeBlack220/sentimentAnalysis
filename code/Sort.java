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
import javafx.util.Pair;
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
	// Array that stores key value pairs (filename,sentimentvalue)
	private PriorityQueue<Pair<String, Double>> files = 
        new PriorityQueue<Pair<String, Double>>(new Comparator<Pair<String,Double>>() {
        	public int compare(Pair<String, Double> a, Pair<String, Double> b) {
        	if(b.getValue() >= a.getValue()) return 1;
		else return -1; 
                }
	});

	// Constructor
	public Sort(ArrayList<Pair<String, Double>> unsorted){
	sort(unsorted);
	}

	// Sort the (filename, sentiment value) pairs and save them
	public void sort(ArrayList<Pair<String, Double>> unsorted){
		for(int i = 0; i < unsorted.size(); i++){
		files.add(unsorted.get(i));
		}		
	}
	// Write the final output to a txt file
	public void writeOutput(String address){
	Pair<String, Double> current = null;
	try{
	fwriter = new FileWriter(address);
	writeHelper(fwriter);
	fwriter.write("\n");
	fwriter.write("-- output for the " + directName + " directory --\n");
	while(files.size()!=0){
		current = files.poll();
		fwriter.write(current.getKey()+" "+current.getValue()+"\n");

		}
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
	ArrayList<Pair<String,Double>> test = new ArrayList<Pair<String, Double>>();
	Pair<String, Double> p1 = new Pair<String, Double>("example/histories",-0.0507148754943);
	Pair<String, Double> p2 = new Pair<String, Double>("example/comedies",0.0722801788376);
	Pair<String, Double> p3 = new Pair<String, Double>("example/poems",-0.120983606557);
	Pair<String, Double> p4 = new Pair<String, Double>("example/tragedies",-0.0408327891522);
	test.add(p1);
	test.add(p2);
	test.add(p3);
	test.add(p4);
	Sort S1 = new Sort(test);
	S1.writeOutput(S1.outputDir+"/output.txt");


	}
	


}
