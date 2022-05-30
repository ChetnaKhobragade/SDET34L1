package com.vtiger.practise;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sdet34l1.genericLibrary.ExcelLibrary;

public class TestNGPractice2Test {

	@Test(dataProvider= "loginData")
	public void practiceTest(String username, String password ) {
	Reporter.log(username+"   ---->   "+password,true);
	}
	
//	@DataProvider
//	public Object[][] loginData(){
//		Object[][] data = new Object[5][2];
//		data[0][0]="username";
//		data[0][1]="password";
//		
//		data[1][0]="username1";
//		data[1][1]="password1";
//		
//		data[2][0]="username2";
//		data[2][1]="password2";
//		
//		data[3][0]="username3";
//		data[3][1]="password3";
//		
//		data[4][0]="username4";
//		data[4][1]="password4";
//		
//		return data;
//	}
	
	@DataProvider
	public Object[][] loginData() throws EncryptedDocumentException, IOException{
		ExcelLibrary el = new ExcelLibrary();
		//Object[][] data = ExcelLibrary.getMultipleDataFromExcel("loginData", 0, 0);
		//return data;
		
		//or
		
		return  el.getMultipleDataFromExcel("loginData", 0, 0);
	}
}
