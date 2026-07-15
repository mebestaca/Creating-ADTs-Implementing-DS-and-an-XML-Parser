package parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import implementations.MyQueue;
import implementations.MyStack;

/**
 * @author Marc Edison Estaca, Robert Macklin
 * 
 * This class is the main XMLParser. It takes in a file name and then parses through it to ensure that the file's tags are formatted correctly.
 */
public class XMLParser {
	private MyStack<String> stack;
    private MyQueue<String> errorQ;
    private MyQueue<String> extrasQ;
    
    /**
     * The constructor of the XMLParser. It takes in the filename to be read and parses through it, checking for errors in the format.
     * 
     * @param filename The filename of the XML code to be parsed through.
     */
    public XMLParser(String filename) {

        stack = new MyStack<>();
        errorQ = new MyQueue<>();
        extrasQ = new MyQueue<>();
        
        parse(filename);
    }

    /**
     * <p>This function hands the parsing of the given file. It reads the file line by line before handing them to the parseLine function.
     * It then handles the errors of any tags that were not closed.</p>
     * 
     * @param filename The file name of the XML code to be parsed through.
     */
    private void parse(String filename) {

        try {

            BufferedReader reader = new BufferedReader(new FileReader(filename));

            String line;
            int lineNumber = 0;


            while((line = reader.readLine()) != null) {
                lineNumber++;
                processLine(line, lineNumber);
            }

            reader.close();

            // Anything left in stack was never closed
            while(!stack.isEmpty()) {
                errorQ.enqueue(stack.pop());
            }

            if (errorQ.isEmpty() && extrasQ.isEmpty()) {

                System.out.println("XML document is constructed correctly.");

            }
            else {
                while (!errorQ.isEmpty() && !extrasQ.isEmpty()) {
                    if (errorQ.peek().equals(extrasQ.peek())) {
                        errorQ.dequeue();
                        extrasQ.dequeue();

                    }
                    else {
                        System.out.println("Error: <" + errorQ.dequeue() + "> is not closed.");
                    }
                }

                while (!errorQ.isEmpty()) {
                    System.out.println("Error: <" + errorQ.dequeue() + "> is not closed.");
                }

                while (!extrasQ.isEmpty()) {
                    System.out.println("Error: </" + extrasQ.dequeue() + "> has no matching opening tag.");
                }
            }
        }
        catch(IOException e) {
            System.out.println( "Cannot read file.");
        }
    }

    /**
     * <p>This function takes in a line of code and breaks it apart to find each tag. It prints an error if the tag has a "<" but not a ">".
     * Each found tag is then passed to the processTag function.</p>
     * 
     * @param line The line of code to be parsed.
     * @param lineNumber The line number to be passed to the printError function should there be an error.
     */
    private void processLine(String line, int lineNumber) {
        int index = 0;
        while(index < line.length()) {
            int start = line.indexOf("<", index);
            if(start == -1)
                break;
            
            int end = line.indexOf(">", start);

            if(end == -1) {
                printError(lineNumber, line.substring(start));
                break;

            }

            String tag = line.substring(start, end + 1);

            processTag(tag, lineNumber);
            index = end + 1;
        }
    }

    /**
     * <p>This function processes the tags. It first checks if the tag is formated correctly. 
     * It then ignores the XML declaration and self closing tags.
     * With the tags that remain, it splits them between opening tags and closing tags.
     * Opening tags are added to the tags stack while closing tags are passed to the closeTag function.</p>
     * 
     * @param tag The tag to be parsed.
     * @param lineNumber The line number that is used by the printError function should a error occur.
     */
    private void processTag(String tag, int lineNumber) {

    	// Ignore XML declaration
        if(tag.startsWith("<?xml")) {
            return;
        }
    	

        if(!validTag(tag)) {
            printError(lineNumber, tag);
            return;
        }

        // Ignore self closing tags
        if(tag.endsWith("/>")) {
            return;
        }
        // Opening tag
        if(!tag.startsWith("</")) {

            String name = tag.substring(1,tag.length() - 1);

            // Remove attributes
            if(name.contains(" ")) {
                name = name.substring(0, name.indexOf(" "));
            }
            stack.push(name);

        }
        else {
            String name = tag.substring(2, tag.length() - 1);
            closeTag(name);
        }
    }

    /**
     * <p>This function handles closing tags. It takes the closing tag in and checks if the tag matches the one at the top of the stack.
     * If so, it removes it from the top of the stack. If not, it hands it over to the searchStack function.</p>
     * 
     * @param tag The closing tag to be compared to the tags stack.
     */
    private void closeTag(String tag) {
    	if (!stack.isEmpty() && stack.peek().equals(tag)) {
            stack.pop();
        }
        else if (stack.isEmpty()) {
            extrasQ.enqueue(tag);
        }
        else {
            searchStack(tag);
        }
    }

    /**
     * <p>This function is used to search the stack after a error has occurred where the closing tag doesn't match the tag at the top of the stack.
     * Every tag above the matching tag is placed into the error queue. If the tag isn't found, it's added to the error queue as well.</p>
     * 
     * @param tag The tag to be searched for in the stack.
     */
    private void searchStack(String tag) {
    	MyStack<String> temp = new MyStack<>();
        boolean found = false;

        while (!stack.isEmpty()) {
            String current = stack.pop();
            if (current.equals(tag)) {
                found = true;
                break;
            }
            temp.push(current);
        }

        if (found) {
            // everything popped above the match really was unclosed
            while (!temp.isEmpty()) {
                errorQ.enqueue(temp.pop());
            }
        } else {
            // no match anywhere — restore the stack untouched
            while (!temp.isEmpty()) {
                stack.push(temp.pop());
            }
            extrasQ.enqueue(tag);
        }

    }

    /**
     * <p>This function takes in a tag and tests to see if it follows a valid structure. 
     * If it does, it returns true. Otherwise, it returns false.</p>
     * 
     * @param tag The tag to be tested.
     * @return Returns true if the tag is formatted correctly, false otherwise.
     */
    private boolean validTag(String tag) {
        if(!tag.startsWith("<") || !tag.endsWith(">")) {
            return false;
        }

        if(tag.startsWith("</")) {
            return tag.matches("</[A-Za-z0-9_]+>");
        }

        if(tag.endsWith("/>")) {
            return tag.matches("<[A-Za-z0-9_]+(\\s+.*)?/>");
        }

        return tag.matches("<[A-Za-z0-9_]+(\\s+.*)?>");
    }

    /**
     * <p>This function takes in the tag responsible for the error and the line it is on.
     * It then displays the error to the terminal.</p>
     * 
     * @param line The line number that the error occurred on.
     * @param tag The tag that caused the error.
     */
    private void printError(int line, String tag) {
        System.out.println("Error at line " + line + ": " + tag + " is not constructed correctly.");
    }

}