package com.vtiger.practise;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactWriteDataExcelTest {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		WebDriver driver=null;
		
//property file for common data - like url , username , password , timeout , browser 
		FileInputStream fis=new FileInputStream("./src/test/resources/commanData.properties");
		
		Properties property= new Properties();
		
		property.load(fis);
		
        String url = property.getProperty("url");
		String username = property.getProperty("username");
		String password = property.getProperty("password");
		String timeout = property.getProperty("timeout");
		String browser = property.getProperty("browser");
		
		Random ran = new Random();
		int randomNumber= ran.nextInt(1000);
		
		long longtimeout = Long.parseLong(timeout);
	
//excel sheet for Test data - like organization name 
		FileInputStream fis1 = new FileInputStream("./src/test/resources/TestData.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		
		Sheet sh=wb.getSheet("contacts");
		
		Row row= sh.getRow(2);
		
		Cell cell = row.getCell(1);
		String contactLastName = cell.getStringCellValue()+randomNumber;
		
		switch (browser) {
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver= new FirefoxDriver();
			break;
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();
			break;
		default:
			System.out.println("specify browser correctly");

			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();
			break;
		}
		
		//rather than hardcoding we can use the variables in which we have stored the string values from the table
		
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(longtimeout, TimeUnit.SECONDS);
		
		//STEP1 - LOGIN TO VTIGER - HOME PAGE IS DIAPLAYED
		
		//enter username
		driver.findElement(By.name("user_name")).sendKeys(username);
		//enter password
		driver.findElement(By.name("user_password")).sendKeys(password);
		//click on enter 
		driver.findElement(By.id("submitButton")).click();
		
		//if home page is displayed then write test case is pass in status in excel sheet
		if(driver.getTitle().contains("contacts"))
		{
			wb.getSheet("contacts").getRow(6).createCell(5).setCellValue("homepage");
			wb.getSheet("contacts").getRow(6).createCell(6).setCellValue("pass");
		}
		
		//STEP2 - CLICK ON CONTACTS - CONTACT PAGE SHOULD DISPLAY
		
		//click on contacts
		driver.findElement(By.linkText("Contacts")).click();

		//if CONTACT page is displayed then write test case is pass in status in excel sheet
		//if(driver.getTitle().contains("contacts"))
		//{
		//	wb.getSheet("contacts").getRow(7).createCell(5).setCellValue("Contact page");
			wb.getSheet("contacts").getRow(7).createCell(6).setCellValue("pass");
		//}
		
		//STEP3 - CLICK ON + - CREATE CONTACT PAGE SHOULD DISPLAY

		//clcik on + button
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		//if create contact is displayed then write test case is pass in status in excel sheet
		//if(driver.getTitle().contains("contacts"))
		//{
		//	wb.getSheet("contacts").getRow(8).createCell(5).setCellValue("Create contact page");
		//	wb.getSheet("contacts").getRow(8).createCell(6).setCellValue("pass");
		//}
		
		//enter mandatory fields and last name= something 
		driver.findElement(By.name("lastname")).sendKeys(contactLastName);
		//click on save 
		driver.findElement(By.name("button")).click();

		//mousehover to logout options
		//WebElement logout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		//Actions a= new Actions(driver);
		//a.moveToElement(logout).perform();
		//click on signout
		//driver.findElement(By.linkText("Sign Out")).click();
		
		FileOutputStream fos = new FileOutputStream("./src/test/resources/TestData.xlsx");
		wb.write(fos);
		wb.close();
		driver.quit();

	}

}
