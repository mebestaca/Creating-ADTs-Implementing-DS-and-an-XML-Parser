package appDomain;

import parser.XMLParser;

/**
 * @author Marc Edison Estaca, Robert Macklin
 * 
 * This class in the main driver of the program. It takes in the file name from the provided arguments and passes it to the XMLParser.
 * 
 */
public class AppDriver {
	/**
	 * This is the main function that is triggered when the program is run. It gets the file name from the args and passes it to the XMLParser.
	 * 
	 * @param args The arguments given to the program when it runs.
	 */
	public static void main( String[] args )
	{
		
		if (args.length != 1) {
	        System.out.println("Usage: java -jar Parser.jar <xml-file>");
	        return;
	    }
		
		new XMLParser(args[0]);
	}
}
