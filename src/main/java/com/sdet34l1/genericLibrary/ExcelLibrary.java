package com.sdet34l1.genericLibrary;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * This class is used to maintain all the excel specific common methods
 * @author Chetna k
 *
 */
public class ExcelLibrary {

	public FileInputStream fis ;
	public Workbook wb ;

	/**
	 * This method is used to open the excel sheet 
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public void openExcel(String filepath) throws EncryptedDocumentException, IOException {
		fis  = new FileInputStream(filepath);
		wb = WorkbookFactory.create(fis);
	}

	/**
	 *  This method is used to get the data from the excel sheet 
	 * @param sheetName
	 * @param rowNumber
	 * @param cellNumber
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public String getDataFromExcel(String sheetName,int rowNumber, int cellNumber ) throws EncryptedDocumentException, IOException {
		Sheet sh = wb.getSheet(sheetName);
		Row row = sh.getRow(rowNumber);
		Cell cell =row. getCell(cellNumber);
		String cellValue = cell.getStringCellValue();
		return cellValue;
	}

	/**
	 * This method is used to get the multiple data from the excel sheet 
	 * @param sheetName
	 * @param rowNumber
	 * @param cellNumber
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public Object[][] getMultipleDataFromExcel(String sheetName, int rowNumber, int cellNumber) throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream(IconstantPath.EXCELPATH_PRACTICE);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);

		Object[][] arr= new Object[sh.getLastRowNum()][sh.getRow(0).getLastCellNum()];
		for (int i = 0; i < sh.getLastRowNum(); i++) 
		{
			for (int j = 0; j < sh.getRow(i).getLastCellNum(); j++) 
			{
				arr[i][j]= sh.getRow(i+1).getCell(j).getStringCellValue();
			}
		}
		return arr;
	}	

	/**
	 *  This method is used to set the data into the excel sheet 
	 * @param sheetName
	 * @param rowNumber
	 * @param cellNumber
	 * @param value
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public void setDataInExcel(String sheetName,int rowNumber, int cellNumber, String value) throws EncryptedDocumentException, IOException {
		Sheet sh = wb.getSheet(sheetName);
		Row row = sh.getRow(rowNumber);
		row. createCell(cellNumber).setCellValue(value);
	}

	/**
	 * This method is used to flush all the data inside the excel
	 * @param filePath
	 * @throws IOException
	 */
	public void flushDataInExcel(String filePath) throws IOException{
		FileOutputStream fos = new FileOutputStream(filePath);
		wb.write(fos);
	}

	/**
	 * This method is used to close the excel sheet 
	 */
	public void closeExcel() throws IOException {
		wb.close();
	}
}
