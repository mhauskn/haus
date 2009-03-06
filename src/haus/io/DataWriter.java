package haus.io;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

/**
 * Designed to write data to a standard file
 */
public class DataWriter implements Output<String>
{
	String file;
	FileWriter fstream;
	BufferedWriter out;
	String writeDir;
	String extension;
	
	/**
	 * Create a new data writer to write to file fileName
	 */
	public DataWriter (String fileName) {
		file = fileName;		
		try {
			fstream = new FileWriter (file);
			out = new BufferedWriter(fstream);
		} catch(Exception e) {
			System.err.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Tells the data writer to write files into the writeDir
	 */
	public void setWriteDir (String _writeDir) {
		writeDir = _writeDir;
		File wd = new File(writeDir);
		if (!wd.exists()) {
			System.out.println("Attempting to make dir " + writeDir);
			if (!new File(writeDir).mkdir()) {
				System.out.println("Problem making dir " + writeDir);
				System.exit(1);
			}
		}
	}
	
	public String getWriteDir () {
		return writeDir;
	}
	
	public void writeln (String toWrite) {
		write(toWrite + "\n");
	}
	
	public void write (String toWrite)
	{
		try {
			out.write(toWrite);
		} catch (IOException e) {
			System.err.println("Error while writing.");
			e.printStackTrace();
		}
	}
	
	public void changeFile (String newFile) {
		close();
		
		file = "";
		
		if (writeDir != null)
			file += writeDir;
		
		file += newFile;
		
		if (extension != null)
			file += extension;

		try
		{
			fstream = new FileWriter (file);
			out = new BufferedWriter(fstream);
		}
		catch(Exception e)
		{
			System.err.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
		System.out.println("Now writing to file: " + file);
	}
	
	/**
	 * Adds an extension which will be appended to every written file
	 */
	public void setExtension (String _extension) {
		extension = _extension;
	}
	
	/**
	 * Closes the current file being written to
	 */
	public void close () {
		try {
			System.out.println("Data Written to File: " + file);
			out.close();
		} catch (IOException e) {
			System.err.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Writes our storable string to output
	 */
	public void add (String storable) {
		writeln(storable);		
	}
	
	/**
	 * Simple way to write a file
	 */
	public static void writeFile (String fileName, List<String> data) {
		DataWriter writer = new DataWriter(fileName);
		for (String answer: data)
			writer.write(answer);
		writer.close();
	}
	
	/**
	 * Writes a list of objects to a file, calling the toString 
	 * method of each object
	 */
	@SuppressWarnings("unchecked")
	public static void writeObjects (String fileName, List data) {
		DataWriter writer = new DataWriter(fileName);
		for (int i = 0; i < data.size(); i++)
			writer.writeln(data.get(i).toString());
		writer.close();
	}
}
