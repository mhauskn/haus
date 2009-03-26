package haus.io;

import java.util.ArrayList;

/**
 * The closer class represents a convenient way to close 
 * multiple closable objects at the end of a program.
 */
public class Closer {
	ArrayList<Closable> closables = new ArrayList<Closable>();
	
	/**
	 * Registers a new closable object to be closed at the end
	 * of the session.
	 * @param c The closable
	 */
	public void registerClosable (Closable c) {
		closables.add(c);
	}
	
	/**
	 * Closes all of our closable objects
	 */
	public void close () {
		for (Closable c : closables)
			c.close();
	}
}
