package haus.misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import haus.io.Serializer;

public class Misc {
	/**
	 * Creates a new class given the arguments necessary to 
	 * build a new instance of this class
	 * @param className: The name of the class
	 * @param template: The classes of the constructor args
	 * @param args: The actual constructor args
	 * @return The new class
	 */
	@SuppressWarnings("unchecked")
	public static Object classForName (String className, Class[] template,
			Object[] args) {
		try {
			Class cl = Class.forName(className);
			java.lang.reflect.Constructor co = cl.getConstructor(template);
			return co.newInstance(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Creates a new object of the same type as passed. This is 
	 * the point of the reflection class.
	 */
	@SuppressWarnings("unchecked")
	public static Object classForName (Reflection original) {
		String name = original.getClass().getCanonicalName();
		Class[] classes = original.getTemplate();
		Object[] args = original.reflect();
		return classForName(name, classes, args);
	}
	
	/**
	 * Makes a deep copy of the given object by serializing and
	 * de-serializing it.
	 */
	public static Object copyObject (Object original) {
		Serializer.serialize(original, "test");
		return Serializer.deserialize("test");
	}
	
	/**
	 * Attempts to execute a command. Returns stdout and stderr
	 * concatenated into a single string.
	 */
	public static String exec (String command) {
		try {
			Process p = Runtime.getRuntime().exec(command);
			String out = "";
			BufferedReader stdInput = new BufferedReader(new 
					InputStreamReader(p.getInputStream()));
			
			BufferedReader stdError = new BufferedReader(new 
					InputStreamReader(p.getErrorStream()));
			String line;
			while ((line = stdInput.readLine()) != null)
			    out += line;
			while ((line = stdError.readLine()) != null)
			    out += line;
			return out;
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
}
