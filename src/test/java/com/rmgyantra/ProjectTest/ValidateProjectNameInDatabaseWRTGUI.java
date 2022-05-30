package com.rmgyantra.ProjectTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.sdet34l1.genericLibrary.DatabaseLibrary;
import com.sdet34l1.genericLibrary.ExcelLibrary;
import com.sdet34l1.genericLibrary.FileLibrary;
import com.sdet34l1.genericLibrary.IconstantPath;
import com.sdet34l1.genericLibrary.JavaLibrary;
import com.sdet34l1.genericLibrary.WebDriverLibrary;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ValidateProjectNameInDatabaseWRTGUI {

	public static void main(String[] args) throws Throwable {		
		ExcelLibrary excelLibrary= new ExcelLibrary();
		FileLibrary fileLibrary= new FileLibrary();
		JavaLibrary javaLibrary= new JavaLibrary();
		WebDriverLibrary webDriverLibrary = new WebDriverLibrary();
		
		excelLibrary.openExcel(IconstantPath.RMGYANTRA_EXCELPATH);
		
		String timeOut = fileLibrary.getDataFromPropertyFile("timeout");
		
		long longTimeOut= javaLibrary.stringToLong(timeOut);
		
		String projectName = excelLibrary.getDataFromExcel("projects", 1, 1) + javaLibrary.getRandomNumber(1000);
		
		javaLibrary.printStatement(projectName);
				
		WebDriverManager.firefoxdriver().setup();
		WebDriver driver= new FirefoxDriver();

		webDriverLibrary.navigateApp("http://localhost:8084/", driver);
		webDriverLibrary.browserSetting(longTimeOut, driver);
		
		//Enter username,password and click Sign-in
		driver.findElement(By.id("usernmae")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();
		
		//Enter data in all fields
		driver.findElement(By.linkText("Projects")).click();
		driver.findElement(By.xpath("//span[text()='Create Project']")).click();
		driver.findElement(By.name("projectName")).sendKeys(projectName);

		//disabled text box team size
		//	JavascriptExecutor js=(JavascriptExecutor) driver;
		//	js.executeScript("");

		driver.findElement(By.name("createdBy")).sendKeys("lead1");
		
		//Select from dropdowm
		WebElement dropdown = driver.findElement(By.xpath("(//select[@name='status'])[2]"));
		webDriverLibrary.SelectdropDown("Created", dropdown);
		
		//Click on add project 
		driver.findElement(By.xpath("//input[@type='submit']")).click();

		//DataBaseLibraries.openDBConnection(IconstantPath.DATABASEPATH+FileLibraries.getDataFromPropertyfile("dBName"), 
			//	FileLibraries.getDataFromPropertyfile("dBUserName"), FileLibraries.getDataFromPropertyfile("dBPassWord") );
		
		//+FileLibraries.getDataFromPropertyfile("dBName")
		DatabaseLibrary.openDBConnection(com.sdet34l1.genericLibrary.IconstantPath.DATABASEPATH, 
				fileLibrary.getDataFromPropertyFile("dBUsername") ,fileLibrary.getDataFromPropertyFile("dBPassWord"));
		
		DatabaseLibrary.setDataInDataBase("insert into project values('TY_PRO_"+javaLibrary.getRandomNumber(1000)+"','Chetna', '25/04/2022','"+projectName+"','On Going',12);");
		
		boolean status = DatabaseLibrary.validateTheDataInDatabase("select project_name from project;", "project_name", projectName);
		
		if (status== true) {
			System.out.println("Tc pass");
			
		}
		else {
			System.out.println("TC fail");
		}
		
		DatabaseLibrary.closeDBConnection();
		
		//ExcelLibrary.closeExcel();
		webDriverLibrary.quitBrowser(driver);
	}
}
