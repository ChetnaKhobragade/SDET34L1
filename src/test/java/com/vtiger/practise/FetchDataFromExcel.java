package com.vtiger.practise;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FetchDataFromExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream("./src/test/resources/TestDataOrg.xlsx");
		
		Workbook wb = WorkbookFactory.create(fis);
		
		Sheet sh = wb.getSheet("organization");
		
		Row r = sh.getRow(8);
		
		Cell c = r.getCell(1);
		
		//as we cant fetch numeric value from the excel as we can fetch only string values ...so we can use data formatter class
		DataFormatter format = new DataFormatter();
		String data = format.formatCellValue(c);
		System.out.println(data);
		
		//or
		String value = c.toString();
		System.out.println(value);
		
		//or -- not recommened as for utilization we have to convert it to string from (as above shown)
		System.out.println(c);
		wb.close();
		}
}
