package haus.misc;

/**
 * The condition interface is a conditional statement which can 
 * be passed to a method to act as a general conditional.
 * @author Administrator
 *
 */
public interface Condition<e> {
	/**
	 * Check if this conditional is satisfied
	 * @param arg0
	 * @return true if satisfied, false otherwise
	 */
	boolean satisfied (e arg0);
}
