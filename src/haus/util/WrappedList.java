package haus.util;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

public class WrappedList<E> extends AbstractList<E> 
		implements List<E>, RandomAccess, Serializable {
	private static final long serialVersionUID = -8902633399525640835L;
	
	/**
	 * The array holding the actual elements
	 */
	private E[] elementData;
	
	/**
	 * The number of elements in the list
	 */
	private int size = 0;

	/**
	 * The start index of this list
	 */
	private int start = 0;
	
	/**
	 * The end index of this list
	 */
	private int end = 0;
	
	/**
	 * Creates a new WrappedList with a specified size
	 */
	@SuppressWarnings("unchecked")
	public WrappedList (int initialCapacity) {
		super();
		if (initialCapacity < 0)
			throw new IllegalArgumentException("Illegal Capacity: " + 
					initialCapacity);
		this.elementData = (E[]) new Object[initialCapacity];
	}
	
	/**
	 * Constructs an empty list with an initial capacity of ten.
	 */
	public WrappedList () {
		this(10);
	}
	
	/**
	 * Increases the capacity of this list if necessary to ensure
	 * that it can hold at least the number of elements specified by 
	 * the minimum capacity argument.
	 */
	@SuppressWarnings("unchecked")
	public void ensureCapacity (int minCapacity) {
		int oldCapacity = elementData.length;
		if (minCapacity > oldCapacity) {
			E[] oldData = elementData;
			int newCapacity = (oldCapacity * 3)/2 + 1;
			if (newCapacity < minCapacity)
				newCapacity = minCapacity;
			elementData = (E[]) new Object[newCapacity];
			for (int i = 0; i < size; i++)
				elementData[i] = oldData[(start + i) % oldData.length];
			start = 0;
			end = size;
		}
	}
	
	/**
	 * Returns the number of elements in this list.
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Tests if this list has no elements.
	 * 
	 * @return <tt>true</tt> if this list has no elements;
	 * 		   <tt>false</tt> otherwise.
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Adds a new element to the back of our
	 * list
	 */
	public boolean add (E elem) {
		ensureCapacity(size + 1);
		
		elementData[end] = elem;
		end = (end + 1) % elementData.length;
		size++;
		return true;
	}
	
	/**
	 * Removes an elements from the front of the list.
	 * This is a constant time operation.
	 */
	public E remove () {
		if (isEmpty())
			return null;
		E out = (E) elementData[start];
		elementData[start] = null;
		start = (start + 1) % elementData.length;
		size--;
		return out;
	}
	
	public E removeLast () {
		if (isEmpty())
			return null;
		if (end == 0)
			end = elementData.length-1;
		else
			end = (end - 1) % elementData.length;
		E out = (E) elementData[end];
		elementData[end] = null;
		size--;
		return out;
	}

	/**
	 * Clears our wrapped list
	 */
	public void clear() {
		for (int i = 0; i < size; i++)
			elementData[(start + i) % elementData.length] = null;
		start = 0;
		end = 0;
		size = 0;
	}

	/**
	 * Accesses a list element
	 */
	public E get (int index) {
		RangeCheck(index);
		
		return (E) elementData[((start + index) % elementData.length)];
	}

	public E set(int index, E element) {
		RangeCheck(index);
		
		E oldValue = get(index);
		elementData[(start + index) % elementData.length] = element;
		return oldValue;
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
	
	/**
	 * Linear time operation to find the specified element
	 */
	public int indexOf (Object elem) {
		if (elem == null) {
			for (int i = 0; i < size; i++)
				if (elementData[(start + i) % elementData.length] == null)
					return i;
		} else {
			for (int i = 0; i < size; i++)
				if (elem.equals(elementData[(start + i) % elementData.length]))
					return i;
		}
		return -1;
	}
	
	/**
	 * Linear time operation to perform the element shifting
	 */
	public E remove (int index) {
		RangeCheck(index);
		E out = elementData[(start + index) % elementData.length];
		
		for (int i = index; i < size-1; i++)
			elementData[(start + i) % elementData.length] = elementData[(start + i + 1) % elementData.length];
		removeLast();
		
		return out;
	}
	
	/**
	 * Linear time operation
	 */
	public boolean remove (Object elem) {
		int index = indexOf(elem);
		if (index < 0)
			return false;
		
		if (remove(index) == null)
			return false;
		return true;
	}
	
	private void RangeCheck (int index) {
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException(
					"Index: "+index+", Size: "+size);
	}
	
	private boolean isEqual (List<Integer> other) {
		if (size != other.size())
			return false;
		for (int i = 0; i < size; i++)
			if (!get(i).equals(other.get(i)))
				return false;
		return true;
	}
	
	public static void main (String[] args) {
		WrappedList<Integer> w = new WrappedList<Integer>();
		ArrayList<Integer> a = new ArrayList<Integer>();
		
		// Test ADD
		for (int i = 0; i < 500; i++) {
			int ran = (int) (Math.random() * 1000);
			w.add(ran);
			a.add(ran);
			
			if (!w.isEqual(a))
				System.out.println("Failure: ADD");
		}
		
		// Test SET
		for (int i = 0; i < 1000; i++) {
			int index = (int) (Math.random() * w.size);
			int val = (int) (Math.random() * 1000);
			
			w.set(index, val);
			a.set(index, val);
			
			if (!w.isEqual(a))
				System.out.println("Failure: SET");
		}
		

		// Test remove(Index)
		for (int i = 0; i < 500; i++) {
			int index = (int) (Math.random() * w.size);
			
			if (!w.remove(index).equals(a.remove(index)))
				System.out.println("Failure: REMOVE(index)");
			
			if (!w.isEqual(a))
				System.out.println("Failure: REMOVE(index)");
		}
		
		// Test ADD
		for (int i = 0; i < 500; i++) {
			int ran = (int) (Math.random() * 1000);
			w.add(ran);
			a.add(ran);
			
			if (!w.isEqual(a))
				System.out.println("Failure: ADD");
		}
		
		// Test INDEXOF
		for (int i = 0; i < 1000; i++) {
			Integer val = (int) (Math.random() * 10000);
			
			if (w.indexOf(val) != a.indexOf(val))
				System.out.println("Failure: indexOf");
		}
		
		// Test Remove(object)
		for (int i = 0; i < 500; i++) {
			Integer val = (int) (Math.random() * 1000);
			
			w.remove(val);
			a.remove(val);
			
			if (!w.isEqual(a))
				System.out.println("Failure: REMOVE(obj)");
		}
		
		System.out.println("Done");
	}

	public int lastIndexOf (Object arg0) {throw new UnsupportedOperationException();}
	public boolean contains(Object elem) {throw new UnsupportedOperationException();}
	public boolean containsAll(Collection<?> arg0) {throw new UnsupportedOperationException();}
	public List<E> subList(int fromIndex, int toIndex) {throw new UnsupportedOperationException();}
	public Object[] toArray() {throw new UnsupportedOperationException();}
	public <T> T[] toArray(T[] a) {throw new UnsupportedOperationException();}
	public void add(int arg0, E arg1) {throw new UnsupportedOperationException();}
	public boolean addAll(Collection<? extends E> arg0) { throw new UnsupportedOperationException(); }
	public boolean addAll(int arg0, Collection<? extends E> arg1) {throw new UnsupportedOperationException();}
	public Iterator<E> iterator() {	throw new UnsupportedOperationException();}
	public ListIterator<E> listIterator() {throw new UnsupportedOperationException();}
	public ListIterator<E> listIterator(int arg0) {throw new UnsupportedOperationException();}	
	public boolean removeAll(Collection<?> arg0) {throw new UnsupportedOperationException();}
	public boolean retainAll(Collection<?> arg0) {throw new UnsupportedOperationException();}
}