package haus.io;

import haus.misc.Map;

/**
 * Abstract class representing any program which 
 * depends on input/output.
 * @author Administrator
 *
 */
public abstract class IO <In,Out> implements Map<In>{
	/**
	 * Generic input mechanism
	 */
	public Input<In> in = null;
	
	/**
	 * Generic output mechanism
	 */
	public Output<Out> out = null;
	
	/**
	 * Default constructor
	 */
	public IO () {}
	
	/**
	 * Generic Constructor
	 * @param input: The input mechanism
	 * @param output: The output mechanism
	 */
	public IO (Input<In> input, Output<Out> output) {
		in = input;
		out = output;
	}
	
	/**
	 * Changes the input
	 */
	public void setInput (Input<In> newInput) {
		in = newInput;
	}
	
	/**
	 * Changes the output
	 * @param newOutput
	 */
	public void setOutput (Output<Out> newOutput) {
		out = newOutput;
	}
	
	/**
	 * Applies a map function over our input. Keeps on 
	 * reading and mapping input until we get null.
	 */
	public void mapInput () {
		In i;
		while ((i = in.get()) != null)
			map(i);
	}
}
