package haus.util;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains static methods which operate on or return lists.
 */
public class Lists {
	/**
	 * Merges two sorted lists. The final list may have duplicate 
	 * elements if the given lists have duplicates.
	 * 
	 * Overall runtime is worst case O(l1 + l2) but can be shorter depending 
	 * on the numbers in l1 and l2.
	 */
	@SuppressWarnings("unchecked")
	public static List<? extends Comparable> mergeSortedLists (List<? extends Comparable> l1, 
			List< ? extends Comparable> l2) {
		List out = new ArrayList();
		if (l1.isEmpty() || l2.isEmpty())
			return out;
		
		int l1ptr = 0;
		int l2ptr = 0;
		int l1sz = l1.size();
		int l2sz = l2.size();
		Comparable l1min = l1.get(l1ptr++);
		Comparable l2min = l2.get(l2ptr++);
		
		while (l1ptr < l1sz && l2ptr < l2sz) {
			if (l1min.compareTo(l2min) == 0) {
				out.add(l1min);
				l1min = l1.get(l1ptr++);
				l2min = l2.get(l2ptr++);
				continue;
			}
			if (l1min.compareTo(l2min) < 0)
				l1min = l1.get(l1ptr++);
			else
				l2min = l2.get(l2ptr++);
		}
		
		return out;
	}
}
