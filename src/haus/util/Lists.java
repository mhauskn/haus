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
		
		while (l1ptr <= l1sz && l2ptr <= l2sz) {
			if (l1min.compareTo(l2min) == 0) {
				out.add(l1min);
				if (l1ptr < l1sz) l1min = l1.get(l1ptr);
				if (l2ptr < l2sz) l2min = l2.get(l2ptr);
				l1ptr++; l2ptr++;
				continue;
			}
			if (l1min.compareTo(l2min) < 0) {
				if (l1ptr < l1sz) l1min = l1.get(l1ptr);
				l1ptr++;
			} else {
				if (l2ptr < l2sz) l2min = l2.get(l2ptr);
				l2ptr++;
			}
		}
		
		return out;
	}
	
	/**
	 * Merges a list of lists into a single list.
	 */
	@SuppressWarnings("unchecked")
	public static List<Comparable> mergeSortedLists (
			List<List<Comparable>> masterList) {
		ArrayList out = new ArrayList();
		
		if (masterList.size() == 0) return out;
		
		int[] ptrs = new int[masterList.size()];
		
		int[] sizes = new int[masterList.size()];
		
		Comparable[] mins = new Comparable[masterList.size()];
		
		for (int i = 0; i < masterList.size(); i++) {
			List<Comparable> l = masterList.get(i);
			if (l.isEmpty())
				return out;
			sizes[i] = l.size();
			mins[i] = l.get(ptrs[i]++);
		}
		
		while (insideLists(ptrs, sizes)) {
			Comparable min = sameMins(mins);
			if (min == null) {
				out.add(mins[0]);
				for (int i = 0; i < mins.length; i++) {
					if (ptrs[i] < sizes[i])
						mins[i] = masterList.get(i).get(ptrs[i]);
					ptrs[i]++;
				}
			} else {
				for (int i = 0; i < mins.length; i++)
					if (min.compareTo(mins[i]) == 0) {
						if (ptrs[i] < sizes[i])
							mins[i] = masterList.get(i).get(ptrs[i]);
						ptrs[i]++;
					}
			}	
		}
		
		return out;
	}
	
	/**
	 * Checks to ensure that the list pointer is inside of the list's size
	 */
	static boolean insideLists (int[] ptrs, int[] sizes) {
		for (int i = 0; i < ptrs.length; i++) 
			if (ptrs[i] > sizes[i])
				return false;
		return true;
	}
	
	/**
	 * Checks if all lists have same minimum element. If so, returns null.
	 * Otherwise returns the minimum element.
	 */
	@SuppressWarnings("unchecked")
	static Comparable sameMins (Comparable[] mins) {
		Comparable min = mins[0];
		boolean same = true;
		for (int i = 1; i < mins.length; i++) {
			int comparison = min.compareTo(mins[i]);
			if (comparison != 0)
				same = false;
			if (comparison > 0)
				min = mins[i];
		}
		if (same)
			return null;
		return min;
	}
}
