// Basic Java file IO
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.net.URI;

// Java classes for working with sets
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;


public class Map{
	// Counter for the two different words and sentiment value
	private int posCount = 0;
	private int negCount = 0;
	private double sentiValue = 0;

	// HashSets for filter terms
	private Set<String> patternToSkip = new HashSet<String>();
	private Set<String> goodWords = new HashSet<String>();
	private Set<String> badWords = new HashSet<String>();


	// Save the txt file to the string format
	private String content;
	private String[] rawWords;
	
	// Word boundary defined as whitespace-characters-word boundary-whitespace
	

	// Uris used for this task
	private URI goodWordsUri;
	private URI badWordsUri;
	private URI taskUri;
	private URI saveUri;

	// Constructor
	public Map(String goodWordsFile, String badWordsFile, String taskFile, String saveFile){
		goodWordsUri = URI.create(goodWordsFile);
		badWordsUri = URI.create(badWordsFile);
		taskUri = URI.create(taskFile);	
		saveUri = URI.create(saveFile);
	}

	// Create the good words set 
	private void parsePositive() {
		try {
			BufferedReader fis = new BufferedReader(new FileReader(
				new File(goodWordsUri.getPath())));
			String goodWord;
			while ((goodWord = fis.readLine()) != null) {
				goodWords.add(goodWord);
			} 
		} catch (IOException ioe) {
				System.err.println("Caught exception parsing cached file '"
				+ goodWordsUri + "'");
		}
		

	}

	// Create the bad words set
	private void parseNegative() {
		try {
			BufferedReader fis = new BufferedReader(new FileReader(
				new File(badWordsUri.getPath())));
			String badWord;
			while ((badWord = fis.readLine()) != null) {
				badWords.add(badWord);
			} 
		} catch (IOException ioe) {
				System.err.println("Caught exception parsing cached file '"
				+ badWordsUri + "'" );
		}
		

	}

	// Convert task's txt file to a string
	public String txtToString(){
		String res = "";
		try {
			BufferedReader fis = new BufferedReader(new FileReader(
				new File(taskUri.getPath())));
			String s = null;
			while((s = fis.readLine()) != null){
			res = res + s + '\n';
			}
		} catch (IOException ioe){
			System.err.println("Caught exception parsing cached file '"
			+ taskUri + "'");
		}
		return res;
	}

	// Extract words from text's string	
	public void splitWord(){
	// Delete symbols except "-"
	content = content.replace("--"," ").replace("|"," ").replace("("," ").replace(")"," ");
	content = content.replace("_"," ").replace(":"," ").replace(","," ").replace("'"," ");
	content = content.replace(";"," ").replace("."," ").replace("?"," ").replace("\""," ");
	content = content.replace("["," ").replace("]"," ").replace("!"," ");

	// Then we can split words by whitespace	
	rawWords = content.split("\\s+");
	}

	// Count the frequency and print the results
	public MapResult countFreq(){
	content = txtToString();
	splitWord();
	parseNegative();
	parsePositive();
	for(String word: rawWords){
	if (goodWords.contains(word.toLowerCase())) posCount ++;
	if (badWords.contains(word.toLowerCase())) negCount ++;
	}
	sentiValue = (posCount - negCount) / (double) (posCount + negCount);
	System.out.println(taskUri.getPath() + " is finished!");
	System.out.println("posCount = " + posCount);
	System.out.println("negCount = " + negCount);
	System.out.println("sentiment value = " + sentiValue);
	String[] parseTaskAdd = taskUri.getPath().split("/");
	String fileName = parseTaskAdd[parseTaskAdd.length - 2] + "/" +
	parseTaskAdd[parseTaskAdd.length - 1];  
	MapResult ret = new MapResult();
	ret.filename = fileName;
	ret.score = sentiValue;
	saveResult();
	return ret;
	}
	
	// Test function to debug the parsing rules
	private void saveToFile(String address){
	FileWriter fwriter = null;
	try {
	fwriter = new FileWriter(address);
	fwriter.write(content);
	} catch (IOException ioe) {
	System.err.println("Caught exception writing file '"
	+ address + "'" );
	} finally{
		try{
		fwriter.flush();
		fwriter.close();
		} catch (IOException ex) {
		ex.printStackTrace();
		}
	} 
	}
	
	// Save the result of a task to a txt file in a designated place
	public void saveResult(){
	FileWriter fwriter = null;
	String[] parseAddress = taskUri.getPath().split("/");
	String taskName = parseAddress[parseAddress.length - 1];
	String resAddress = saveUri.getPath() + "/" + taskName + "Result";
	try {
	fwriter = new FileWriter(resAddress);
	fwriter.write(Integer.toString(posCount));
	fwriter.write("\n");
	fwriter.write(Integer.toString(negCount));
	} catch (IOException ioe) {
	System.err.println("Caught exception writing file '"
	+ resAddress + "'" );
	} finally {
		try {
		fwriter.flush();
		fwriter.close();
		} catch (IOException ex) {
		ex.printStackTrace();
		}
	}
	}
	
	
	
	public static void main(String[] args){
	Map res = new Map("../data/positive.txt","../data/negative.txt","../data/example/poems","../data/output_dir");
	System.out.println(res.countFreq().filename);
	
	}
}
