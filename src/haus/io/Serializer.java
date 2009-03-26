package haus.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Serializes and deserializes an object of type T
 */
public class Serializer {
	/**
	 * Deserializes a given object of type T from a specified file
	 */
	public static Object deserialize (String fileName) {
		try {
			System.out.print("Deserializing " + fileName + " ... ");
			long start = System.currentTimeMillis();
			FileInputStream fis = new FileInputStream(fileName);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Object myDeserializedObject = ois.readObject();
			ois.close();
			System.out.println("[" + (System.currentTimeMillis() - start) + "ms]");
			return myDeserializedObject;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Serializes the given object to the given fileName
	 */
	public static void serialize (Object toSer, String fileName) {
		try {
			System.out.print("Serializing " + fileName + " ... ");
			long start = System.currentTimeMillis();
			FileOutputStream fos = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(toSer);
			oos.flush();
			oos.close();
			System.out.println("[" + (System.currentTimeMillis() - start) + "ms]");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
