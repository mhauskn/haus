package haus.io;

import haus.misc.Map;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Interactively reads from user input
 */
public class InteractiveReader {
	InputStreamReader converter = null;
	BufferedReader iReader = null;
	String line = "";
	String end_dialogue = "quit";
	Map<String> user;
	
	/**
	 * Constructs a new interactive reader
	 * @param caller The class needing the use of this interactive
	 * reader. This class needs to implement the map function 
	 * to deal with the user's input
	 */
	public InteractiveReader (Map<String> caller) {
		user = caller;
		converter = new InputStreamReader(System.in);
		iReader = new BufferedReader(converter);
	}
	
	/**
	 * Starts the interactive reader running, reading input, and 
	 * processing it
	 */
	public void run () {
		System.out.println("Type 'quit' to quit.");
		String line;
		while ((line = getInput()) != null) {
			user.map(line);
		}
	}
	
	/**
	 * Gets a single line of input or returns null if 
	 * the user has decided to quit
	 */
	String getInput () {
		System.out.print(" > ");
		try {
			line = iReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		if (line.equals(end_dialogue))
			return null;
		return line;
	}
}
