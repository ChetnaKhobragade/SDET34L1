package com.vtiger.practise;

import com.sdet34l1.genericLibrary.FileLibrary;
import com.sdet34l1.genericLibrary.IconstantPath;

public class FetchDataFromPropertyFileGeneric {

	public static void main(String[] args) throws Throwable {
		FileLibrary fileLibrary= new FileLibrary();
		fileLibrary.openPropertyFile(IconstantPath.PROPERTYFILEPATH);
		
		String url = fileLibrary.getDataFromPropertyFile("url");
		
		System.out.println(url);
		
	}

}
