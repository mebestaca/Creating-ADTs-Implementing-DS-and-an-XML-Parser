package parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import implementations.MyQueue;
import implementations.MyStack;

public class XMLParser {
	private MyStack<String> stack;
    private MyQueue<String> errorQ;


    public XMLParser(String filename) {

        stack = new MyStack<>();
        errorQ = new MyQueue<>();

        parse(filename);
    }



    private void parse(String filename) {

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(filename)
                    );


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



            if(errorQ.isEmpty()) {

                System.out.println(
                        "XML document is constructed correctly."
                );

            }
            else {

                while(!errorQ.isEmpty()) {

                    System.out.println(
                            "Error: <"
                            + errorQ.dequeue()
                            + "> is not closed."
                    );

                }

            }


        }
        catch(IOException e) {

            System.out.println(
                    "Cannot read file."
            );

        }

    }




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


            String tag =
                    line.substring(start, end + 1);


            processTag(tag, lineNumber);


            index = end + 1;

        }

    }




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


            String name =
                    tag.substring(
                            1,
                            tag.length() - 1
                    );


            // Remove attributes
            if(name.contains(" ")) {

                name =
                    name.substring(
                            0,
                            name.indexOf(" ")
                    );

            }


            stack.push(name);

        }



        // Closing tag
        else {


            String name =
                    tag.substring(
                            2,
                            tag.length() - 1
                    );


            closeTag(name);

        }

    }




    private void closeTag(String tag) {


        if(!stack.isEmpty()
                &&
           stack.peek().equals(tag)) {


            stack.pop();

        }


        else if(!errorQ.isEmpty()
                &&
                errorQ.peek().equals(tag)) {


            errorQ.dequeue();

        }


        else if(stack.isEmpty()) {


            errorQ.enqueue(tag);

        }


        else {


            searchStack(tag);

        }

    }





    private void searchStack(String tag) {


        MyStack<String> temp = new MyStack<>();

        boolean found = false;



        while(!stack.isEmpty()) {


            String current = stack.pop();


            if(current.equals(tag)) {

                found = true;
                break;

            }


            temp.push(current);

        }




        // Everything above matching tag is an error
        while(!temp.isEmpty()) {

            errorQ.enqueue(temp.pop());

        }



        if(!found) {

            errorQ.enqueue(tag);

        }

    }





    private boolean validTag(String tag) {


        if(!tag.startsWith("<")
                ||
           !tag.endsWith(">")) {

            return false;

        }



        if(tag.startsWith("</")) {

            return tag.matches(
                    "</[A-Za-z0-9_]+>"
            );

        }



        if(tag.endsWith("/>")) {

            return tag.matches(
                    "<[A-Za-z0-9_]+(\\s+.*)?/>"
            );

        }



        return tag.matches(
                "<[A-Za-z0-9_]+(\\s+.*)?>"
        );

    }





    private void printError(int line, String tag) {

        System.out.println(
                "Error at line "
                + line
                + ": "
                + tag
                + " is not constructed correctly."
        );

    }

}
