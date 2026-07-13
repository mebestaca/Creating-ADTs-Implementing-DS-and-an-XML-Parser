package appDomain;

import parser.XMLParser;

public class AppDriver {
	public static void main( String[] args )
	{
		
		if (args.length != 1) {
	        System.out.println("Usage: java -jar Parser.jar <xml-file>");
	        return;
	    }
		
		new XMLParser(args[0]);
	}
}
