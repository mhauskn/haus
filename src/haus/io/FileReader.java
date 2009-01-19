package haus.io;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Reads files
 */
public class FileReader
{	
	File rootDir;
		
	FileInputStream fStream;
	DataInputStream dStream;
	InputStreamReader iStream;
	BufferedReader bReader;
	
	boolean doRecursiveRead;
	boolean gatheredFiles;
	
	ArrayList<File> toRead;
	Hashtable<String, Boolean> ignoreExtensions = null;
	Hashtable<String, Boolean> ignoreFiles = null;
	
	String filterExtension = null;
	
	/**
	 * Creates a test writer to traverse readable files
	 * @param fileName Name of the file or directory to read
	 */
	public FileReader (String fileName)
	{
		toRead = new ArrayList<File>();
		ignoreExtensions = new Hashtable<String, Boolean>();
		ignoreFiles = new Hashtable<String, Boolean>();
		gatheredFiles = false;
		doRecursiveRead = false;
		rootDir = new File(fileName);
	}
	
	/**
	 * Changes the file to be read
	 */
	public void setFile (String fileName) {
		rootDir = new File(fileName);
		gatheredFiles = false;
		toRead.clear();
	}
	
	/**
	 * If Enabled will read all sub directories of the given directory
	 * as well as their subfolders.
	 */
	public void setRecursive (boolean recursive) { 
		doRecursiveRead = recursive;
	}
	
	/**
	 * Gathers the list of files to traverse- This is done auto-
	 * mattically when needed.
	 */
	void gatherFiles ()
	{
		if (gatheredFiles)
			return;
		if (!rootDir.exists()) {
			System.err.println("File not found " + rootDir);
			System.exit(1);
		}
		if (!rootDir.canRead()) {
			System.err.println("Cannot read " + rootDir);
			System.exit(1);
		}
		if (rootDir.isFile())
			initializeReader(rootDir);
		else 
		{
			gatherFiles(rootDir);
			if (toRead.size() == 0)
			{
				System.err.println("No readable files gathered from " + rootDir.toString());
				System.exit(1);
			}
			initializeReader(toRead.remove(0));
		}
		gatheredFiles = true;
	}
	
	/**
	 * Adds a file filter extension. Only parses files ending in this extension.
	 * @param fileExtension
	 */
	public void setFilterExtension (String fileExtension) {
		filterExtension = fileExtension;
	}
	
	/**
	 * Adds a file extension to ignore. If files end with the specified extension
	 * they will be ignored.
	 * @param ignoreExtension
	 */
	public void addIgnoreExtensions (String fileExtension) {
		ignoreExtensions.put(fileExtension, true);
	}
	
	/**
	 * Adds a file name to the list of files to ignore.
	 */
	public void addIgnoreFiles (String fileName) {
		ignoreFiles.put(fileName, true);
	}
	
	/**
	 * Returns the next line in the file or null if there was an error.
	 * @return
	 */
	public String getNextLine () {
		String line = null;
		if (!gatheredFiles)
			gatherFiles();
		
		try {
			line = bReader.readLine();
		} catch (IOException e) {
			System.err.println("Error while reading.");
			e.printStackTrace();
		}
		
		if (line == null) {
			if (toRead.size() == 0)
				return null;
			else {
				initializeReader(toRead.remove(0));
				return getNextLine();
			}
		}
		return line;
	}
	
//------------------PRIVATE METHODS----------------------//
	
	/**
	 * Gather all readable files in dir and recursively
	 * enters directories if specified
	 * @param dir
	 */
	private void gatherFiles (File dir) {
		File[] files = dir.listFiles();
		
		for (File f : files)
		{
			String fileName = f.toString();
			String extension = getFileExtension(fileName);
			
			if (filterExtension != null && !extension.equals(filterExtension))
				continue;
			
			if (ignoreFiles.containsKey(fileName) || ignoreExtensions.containsKey(extension))
				continue;
			
			if (f.isFile() && f.canRead())
				toRead.add(f);
			
			if (f.isDirectory() && f.canRead() && doRecursiveRead)
				gatherFiles(f);
		}
	}
	
	/**
	 * Grabs the extension of a file name
	 */
	private String getFileExtension (String filename) {
		int dotIndex = filename.lastIndexOf('.');
		if(dotIndex < 0 || dotIndex >= filename.length()-1)
			return "";
		return filename.substring(dotIndex+1);
	}
	
	/**
	 * Initializes our reader on whatever files 
	 */
	private void initializeReader (File f)
	{
		System.out.println("Initialized Reader to file " + f);
		try 
		{
			fStream = new FileInputStream(f);
		}
		catch (IOException e)
		{
			System.err.println("Invalid file name specified.");
			e.printStackTrace();
		}
		dStream = new DataInputStream(fStream);
		iStream = new InputStreamReader(dStream);
		bReader = new BufferedReader(iStream);
	}
}
