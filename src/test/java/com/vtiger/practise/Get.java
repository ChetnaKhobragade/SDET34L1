package com.vtiger.practise;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Get {

	public static void main(String[] args) throws Throwable 
	{
		FileInputStream fis= new FileInputStream("./src/test/resources/TestData.xlsx");
		
						Workbook wb = WorkbookFactory.create(fis);
						
						String data = wb.getSheet("organization").getRow(1).getCell(1).getStringCellValue();
						System.out.println(data);

	}

}
