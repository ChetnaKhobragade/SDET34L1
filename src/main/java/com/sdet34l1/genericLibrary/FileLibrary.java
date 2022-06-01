package com.sdet34l1.genericLibrary;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * This file contains only property file specific common methods
 * @author Chetna k
 *
 */
public class FileLibrary {
	public FileInputStream fis ;
	public Properties property;

	/**
	 * This method will open the property file
	 * @param filePath
	 * @throws IOException 
	 * chnages in branch 222
	 */
	public void openPropertyFile(String filePath) throws IOException {
		fis = new FileInputStream(filePath);
		property = new Properties();
		property.load(fis);
	}

	/**
	 * This method is used to open and get the data from the property file
	 * @param filePath
	 * @throws IOException 
	 */
	public String getDataFromPropertyFile(String key) throws Throwable {
		String value =property.getProperty(key);
		return value;
	}
}
