package haus.io;

public interface Pipe<E> {
	/**
	 * Gets the next element from the pipe
	 * @param Inputable: to be input
	 * @return
	 */
	public E get ();
	
	/**
	 * Add an element to output
	 * @param Outputable: to be output
	 */
	public boolean add (E Outputable);
}
