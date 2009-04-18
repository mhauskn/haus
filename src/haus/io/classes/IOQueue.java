package haus.io.classes;

import haus.io.Pipe;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A ArrayList which can be used for generic IO
 */
public class IOQueue<E> implements Pipe<E> {
	public Queue<E> queue = new LinkedList<E>();
	
	public E get() {
		if (queue.isEmpty())
			return null;
		return queue.remove();
	}
	
	public boolean add (E outputtable) {
		return queue.add(outputtable);
	}
}
