package haus.rsch.evolution;

import java.util.ArrayList;

/**
 * Implements a sort and replacement policy for those 
 * organisms which perform poorly on the tests
 * @author Administrator
 *
 */
public interface Replacer {
	/**
	 * Replaces a set number of organisms which were deemed unfit.
	 * @param num The number or organisms to replace
	 */
	@SuppressWarnings("unchecked")
	public void replace (int num, ArrayList<Organism> orgs);
}
