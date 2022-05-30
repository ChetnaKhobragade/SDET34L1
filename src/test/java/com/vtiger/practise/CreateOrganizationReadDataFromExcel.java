package com.vtiger.practise;

import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.sdet34l1.genericLibrary.FileLibrary;
import com.sdet34l1.genericLibrary.JavaLibrary;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganizationReadDataFromExcel {

	public static void main(String[] args) throws Throwable {
		WebDriver driver=null;
//property file for common data - like url , username , password , timeout , browser 
		//FileLibrary.openPropertyFile(IconstantPath.PROPERTYFILEPATH);
		
		String url = FileLibrary.getDataFromPropertyFile("url");
		String username =  FileLibrary.getDataFromPropertyFile("username");
		String password =  FileLibrary.getDataFromPropertyFile("password");
		String timeout =  FileLibrary.getDataFromPropertyFile("timeout");
		String browser = FileLibrary.getDataFromPropertyFile("browser");
		
		long longTimeOut = JavaLibrary.stringToLong(timeout);

		int randomNumber = JavaLibrary.getRandomNumber(1000);
	
//excel sheet for Test data - like organization name 
		FileInputStream fis1 = new FileInputStream("./src/test/resources/TestDataOrg.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		
		Sheet sh=wb.getSheet("organization");
		
		Row row= sh.getRow(2);
		
		Cell cell = row.getCell(1);
		String organizationName = cell.getStringCellValue()+randomNumber;
		
		wb.close();
		
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
		driver.manage().timeouts().implicitlyWait(longTimeOut, TimeUnit.SECONDS);

		//enter username
		driver.findElement(By.name("user_name")).sendKeys(username);
		//enter password
		driver.findElement(By.name("user_password")).sendKeys(password);
		//click on enter 
		driver.findElement(By.id("submitButton")).click();
		//click on organization
		driver.findElement(By.linkText("Organizations")).click();
		//click on + button
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		//enter mandatory fields and org name= something 
		driver.findElement(By.name("accountname")).sendKeys(organizationName);
		//click on save 
		driver.findElement(By.name("button")).click();
		driver.close();
	}
}
