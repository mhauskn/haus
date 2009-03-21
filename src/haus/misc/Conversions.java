package haus.misc;

import java.util.ArrayList;

/**
 * Contains some common static conversions of datatypes
 */
public class Conversions {
	/**
	 * Converts a ArrayList of strings into a string array
	 */
	public static String[] toStrArray (ArrayList<String> ar) {
		String[] out = new String[ar.size()];
		out = ar.toArray(out);
		return out;
	}
}
