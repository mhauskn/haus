package haus.io;

import java.util.ArrayList;

/**
 * Abstract class representing any program which 
 * depends on input/output.
 * @author Administrator
 *
 */
public abstract class IO <In,Out> implements Runnable {
	/**
	 * Generic input mechanism
	 */
	public Pipe<In> in = null;
	
	/**
	 * Generic output mechanism
	 */
	public Pipe<Out> out = null;
	
	/**
	 * Default constructor
	 */
	public IO () {}
	
	/**
	 * Generic Constructor
	 * @param input: The input mechanism
	 * @param output: The output mechanism
	 */
	public IO (Pipe<In> input, Pipe<Out> output) {
		in = input;
		out = output;
	}
	
	/**
	 * Changes the input
	 */
	public void setInput (Pipe<In> newInput) {
		in = newInput;
	}
	
	/**
	 * Changes the output
	 * @param newOutput
	 */
	public void setOutput (Pipe<Out> newOutput) {
		out = newOutput;
	}
	
	/**
	 * Most IO classes will wish to be run by having
	 * their input mapped to them. Those who do not 
	 * agree can over-ride this.
	 */
	public void run () {
		mapInput();
	}
	
	/**
	 * Applies a map function over our input. Keeps on 
	 * reading and mapping input until we get null.
	 */
	public void mapInput () {
		In i;
		while ((i = in.get()) != null)
			mapInput(i);
	}
	
	/**
	 * Forces the program to provide a way of handling 
	 * a single input unit.
	 */
	public abstract void mapInput (In _in);
	
	/**
	 * Connects another IO's Input to the Output of this IO
	 */
	public void connect (IO<Out,?> other) {
		other.setInput(this.out);
	}
	
	/**
	 * Connects to specified pipe between this IO's output
	 * and another IO's input.
	 */
	public void connect (IO<Out,?> other, Pipe<Out> p) {
		setOutput(p);
		connect(other);
	}
	
	/**
	 * Chains together a string of pipes in the order
	 * that they are given connected by pipes in the
	 * order that they are given.
	 */
	@SuppressWarnings("unchecked")
	public static void cascadeInput (ArrayList<IO> nodes, ArrayList<Pipe> pipes) {
		int nodeSz = nodes.size(), pipeSz = pipes.size();
		if (nodeSz + 1 != pipeSz)
			return;
		
		nodes.get(0).setInput(pipes.get(0));
		for (int i = 0; i < nodeSz -1; i++)
			nodes.get(i).connect(nodes.get(i+1), pipes.get(i+1));
		nodes.get(nodeSz-1).setOutput(pipes.get(pipeSz-1));
	}
}
