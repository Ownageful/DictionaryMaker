import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * Uses the Jackson library and the google dictionary API
 * to create a flat-file dictionary given a list of words.
 * 
 * @author Shehryar Farooq
 *
 */
public class DataFinder {
	
	private static ObjectMapper OBJECT_MAPPER = new ObjectMapper(); 
	private static final String REQUEST = "http://www.google.com/dictionary/json?callback=dict_api.callbacks.id100&q=";
	private static final String REQUEST_END = "&sl=en&tl=en&restrict=pr%2Cde&client=te";
	
	public static void main(String[] args) throws IOException {
		
		/*
		 * Check that arguments were entered.
		 */
		if(args.length != 3){
			System.out.println("Invalid runtime arguments");
			return;
		}
		
		/*
		 * The URL object that will be built and called to access the API
		 */
		URL url;
		/*
		 * The JSON response from the google API
		 */
		String jsonRepresentation = "";
		/*
		 * Reader to receive the input from the Input stream reader.
		 */
		BufferedReader in;
		
		/*
		 * Create file objects for all three files (word list, definitions, and words not found)
		 */
		File wordsList = new File(args[0]), definitions = new File(args[1]), wordsNotFound = new File(args[2]);
		
		/*
		 * If a definitions/wordsNotFound file doesn't already exist, create it.
		 */
		if(!definitions.exists())
			definitions.createNewFile();
		if(!wordsNotFound.exists())
			wordsNotFound.createNewFile();
		
		int counter = 0;
		/*
		 * Writers to write to the files.
		 */
		BufferedWriter definitionWriter = new BufferedWriter(new FileWriter(definitions));
		BufferedWriter notFoundWriter = new BufferedWriter(new FileWriter(wordsNotFound));
		
		/*
		 * Scanner to read the words list
		 */
		Scanner scanner = new Scanner(wordsList);
		
		String word;
		while((word = scanner.nextLine()) != null){
			
			url = new URL(REQUEST + word + REQUEST_END);
			in = new BufferedReader(new InputStreamReader(url.openStream()));
			/*
			 * The following replacements remove pjson specific characters from the definitions.
			 */
			jsonRepresentation = in.readLine();
			jsonRepresentation = jsonRepresentation.replace("dict_api.callbacks.id100(", "");
			jsonRepresentation = jsonRepresentation.substring(0, jsonRepresentation.length()-1);
			jsonRepresentation = jsonRepresentation.replace("\\x27","'");
			jsonRepresentation = jsonRepresentation.replace("\\x3cem","");
			jsonRepresentation = jsonRepresentation.replace("\\x3e","");
			jsonRepresentation = jsonRepresentation.replace("\\x3c","");
			jsonRepresentation = jsonRepresentation.replace("/em","");
			jsonRepresentation = jsonRepresentation.replace("\\x26quot;","'");
			jsonRepresentation = jsonRepresentation.replace("\\x3ca","");
			jsonRepresentation = jsonRepresentation.replace("\\x3d","");
			jsonRepresentation = jsonRepresentation.replace("\\x22","");
			jsonRepresentation = jsonRepresentation.replace("\\x","");
			
			/*
			 * Parse the JSON response using jackson.
			 */
			try{
				Root root = OBJECT_MAPPER.readValue(jsonRepresentation, Root.class);
				for(Entry e: root.getPrimaries()[0].getEntries()){
					for(Term t: e.getTerms()){
						if(root.getQuery() != t.getText()){
							counter++;
							definitionWriter.write(root.getQuery() + "=" + t.getText());
							definitionWriter.newLine();
							System.out.println(counter + ": " + "Definition received for " + word);
						}
					}
				}
			}catch(Exception e){
				// Word could not be saved to the dictionary, add it to not found list.
				System.out.println("Definition not found for " + word);
				notFoundWriter.write(word);
				notFoundWriter.newLine();
				continue;
			}
		}
		definitionWriter.close();
		notFoundWriter.close();
		scanner.close();
	}

}
