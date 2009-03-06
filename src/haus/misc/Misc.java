package haus.misc;

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
	@SuppressWarnings("unchecked")
	public static Object copyObject (Object original) {
		Serializer s = new Serializer();
		s.serialize(original, "test");
		return s.deserialize("test");
	}
}
