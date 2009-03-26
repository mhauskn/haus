package haus.io;

/**
 * A closable object is one which needs to be closed at the
 * end of use. 
 */
public interface Closable {
	/**
	 * Close the closable object. This is usually preformed 
	 * at the end of the program.
	 */
	public void close ();
}
