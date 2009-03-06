package haus.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * The wrapped list is a data structure representing a compromise
 * between an array and a linked list. It is able to perform lookup,
 * addLast, and removeFirst operations in constant time.
 * 
 * The cost of this is the inability to add or remove in the middle
 * of the list. While maybe not necessary, this implementation 
 * also has limited size.
 *
 */
public class WrappedList<E> implements List<E> {
	Object[] ar;
	int sz;
	int start = 0;
	int end = 0;
	boolean empty = true;
	
	/**
	 * Creates a new WrappedList with a specified size
	 */
	public WrappedList (int size) {
		ar = new Object[size];
		sz = size;
	}

	/**
	 * Adds a new element to the back of our
	 * list
	 */
	public boolean add (E arg0) {
		if (start == end && !empty)
			return false;
		empty = false;
		ar[end] = arg0;
		end = (end + 1) % sz;
		return true;
	}
	
	/**
	 * Removes an elements from the front 
	 * our list
	 */
	@SuppressWarnings("unchecked")
	public E remove () {
		if (isEmpty())
			return null;
		empty = true;
		E out = (E) ar[start];
		ar[start] = null;
		start = (start + 1) % sz;
		return out;
	}
	
	/**
	 * Removes the object at the given index.
	 * This operation may take O(n) time. 
	 */
	public E remove (int arg0) {
		if (arg0 == -1 || arg0 >= size())
			return null;
		E out = get(arg0);
		for (int i = 0; i < (size() - arg0 -1); i = (i + 1) % sz) {
			int j = (start + arg0 + i) % sz;
			ar[j] = ar[(j+1)%sz];
		}
		end--;
		if (end < 0)
			end += sz;
		ar[end] = null;
		empty = true;
		return out;
	}
	
	public E removeLast (E arg0) {
		return remove(lastIndexOf(arg0));
	}

	/**
	 * Clears our wrapped list
	 */
	public void clear() {
		for (int i = 0; i < sz; i++)
			ar[i] = null;
		start = 0;
		end = 1;
		empty = false;
	}

	/**
	 * Accesses a list element
	 */
	@SuppressWarnings("unchecked")
	public E get(int arg0) {
		if (arg0 >= sz)
			return null;
		return (E) ar[((start + arg0) % sz)];
	}

	/**
	 * Checks if the list is empty
	 */
	public boolean isEmpty() {
		return (start == end && empty);
	}
	
	/**
	 * Returns the size of the list
	 */
	public int size() {
		if (end == start && empty)
			return 0;
		if (end == start && !empty)
			return sz;
		if (end > start)
			return end - start;
		return end + sz - start;
	}
	
	public int lastIndexOf (Object arg0) {
		int index = -1;
		int size = size();
		for (int i = size -1; i >= 0; i--) {
			if (get(i).equals(arg0)) {
				index = i;
				break;
			}
		}
		return index;
	}

	
	public String toString () {
		int size = size();
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < size; i++) {
			sb.append(get(i));
			if (i != size-1)
				sb.append(", ");
		}
		sb.append("]");
		return sb.toString();
	}
	
	public static void main (String[] args) {
		WrappedList<Integer> l = new WrappedList<Integer>(3);
		l.add(3);
		l.removeLast(3);
		System.out.println(l.size());
		l.add(2);
		l.add(1);
		l.remove();
		l.remove();
		l.add(3);
		l.add(2);
		l.remove(3);
		System.out.println(l.size() + " " + l.isEmpty());
		l.remove();
		System.out.println(l.size() + " " + l.isEmpty());
		l.add(5);
		System.out.println(l.size() + " " + l.isEmpty());
		l.remove();
		l.remove();
		l.remove();
		System.out.println(l.size() + " " + l.isEmpty());
	}

	public boolean contains(Object arg0) {return false;}
	public boolean containsAll(Collection<?> arg0) {return false;}
	public List<E> subList(int fromIndex, int toIndex) {return null;}
	public Object[] toArray() {return null;}
	public <T> T[] toArray(T[] a) {return null;}
	public int indexOf(Object arg0) {return 0;}
	public void add(int arg0, E arg1) {}
	public boolean addAll(Collection<? extends E> arg0) { return false; }
	public boolean addAll(int arg0, Collection<? extends E> arg1) {return false;}
	public Iterator<E> iterator() {	return null;}
	public ListIterator<E> listIterator() {return null;}
	public ListIterator<E> listIterator(int arg0) {return null;}
	public boolean remove (Object arg0) {return false;}	public boolean removeAll(Collection<?> arg0) {return false;}
	public boolean retainAll(Collection<?> arg0) {return false;}
	public E set(int arg0, E arg1) {return null;}
}
