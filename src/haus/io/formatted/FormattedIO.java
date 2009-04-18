package haus.io.formatted;

import java.util.ArrayList;

import haus.io.IO;

/**
 * Formatted IO is a structured type of IO in which the 
 * input process has certain expectations of how to interpret 
 * the incoming data and also agrees to provide methods 
 * by which its outgoing data may be interpreted.
 */
public abstract class FormattedIO <In,Out,inputInterface,outputInterface> extends IO<In,Out> {
	public inputInterface context = null;
	
	/**
	 * Acquires input interface in a very direct manner.
	 */
	public void setInputFormat (inputInterface inputIFace) {
		context = inputIFace;
	}
	
	/**
	 * Acquire input interface from another matching 
	 * FormattedIO.
	 */
	public void setInputFormat (FormattedIO<?,In,?,inputInterface> other) {
		this.context = other.provideOutputFormat();
	}
	
	/**
	 * Provide the necessary output interface for 
	 * others to use.
	 */
	public abstract outputInterface provideOutputFormat ();
	
	/**
	 * Connects our output to the other Formatted IO's
	 * input.
	 */
	public void connect (FormattedIO<Out,?,outputInterface,?> other) {
		other.setInputFormat(this);
		super.connect(other);
	}
	
	/**
	 * Chains together a string of pipes in the order
	 * that they are given connected by pipes in the
	 * order that they are given.
	 */
	@SuppressWarnings("unchecked")
	public static void cascadeInput (ArrayList<FormattedIO> nodes) {
		int nodeSz = nodes.size();
		
		for (int i = 0; i < nodeSz -1; i++)
			nodes.get(i).connect(nodes.get(i+1));
	}
}
