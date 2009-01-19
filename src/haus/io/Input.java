package haus.io;

/**
 * Generic input interface
 * @author Administrator
 *
 * @param <E>
 */
public interface Input<E> {
	/**
	 * Gets the next element from input
	 * @param Inputable: to be input
	 * @return
	 */
	public E get ();
}
