package haus.misc;

/**
 * This ability allows an object to reflect upon itself 
 * and create a new instance of it own class.
 *
 */
public interface Reflection {
	/**
	 * Creates the template which specifies which constructor to 
	 * call upon.
	 */
	@SuppressWarnings("unchecked")
	public Class[] getTemplate();
	
	/**
	 * Create the object array which serves as the parameters 
	 * to construct another object of this type.
	 * @return
	 */
	public Object[] reflect();
}
