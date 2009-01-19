package haus.io;

/**
 * Generic output interface
 * @author epn
 *
 */
public interface Output<E> {
	/**
	 * Add an element to output
	 * @param Outputable: to be output
	 */
	public void add (E Outputable);
}
