package com.vtiger.practise;

import java.io.IOException;
import java.util.Set;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.sdet34l1.genericLibrary.ExcelLibrary;
import com.sdet34l1.genericLibrary.FileLibrary;
import com.sdet34l1.genericLibrary.JavaLibrary;
import com.sdet34l1.genericLibrary.WebDriverLibrary;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactWithOrganizationTest {

	public static void main(String[] args) throws Throwable {
		WebDriver driver=null;
		
		//property file for common data - like url , username , password , timeout , browser 
		//FileLibrary.openPropertyFile(com.sdet34l1.genericLibrary.IconstantPath.PROPERTYFILEPATH);
        //ExcelLibrary.openExcel("./src/test/resources/TestData.xlsx");

		String url = FileLibrary.getDataFromPropertyFile("url");
		String username =  FileLibrary.getDataFromPropertyFile("username");
		String password =  FileLibrary.getDataFromPropertyFile("password");
		String timeout =  FileLibrary.getDataFromPropertyFile("timeout");
		String browser = FileLibrary.getDataFromPropertyFile("browser");

		long longTimeOut = JavaLibrary.stringToLong(timeout);

		int randomNumber = JavaLibrary.getRandomNumber(1000);

		//excel sheet for Test data - like organization name 
        
    	String organizationName =ExcelLibrary.getDataFromExcel("contact", 4, 1);
    	String contactLastName =ExcelLibrary.getDataFromExcel("contact", 4, 2);

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

		//Rather than hardcoding we can use the variables in which we have stored the string values from the table
		WebDriverLibrary.navigateApp(url, driver);
		WebDriverLibrary.browserSetting(longTimeOut, driver);
		WebDriverLibrary.initializActions(driver);
		WebDriverLibrary.explicitlyWait(driver, longTimeOut);
		
		//Enter username
		driver.findElement(By.name("user_name")).sendKeys(username);
		//Enter password
		driver.findElement(By.name("user_password")).sendKeys(password);
		//Click on enter 
		driver.findElement(By.id("submitButton")).click();

		//Create organization
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

		driver.findElement(By.name("accountname")).sendKeys(organizationName + randomNumber);
		driver.findElement(By.name("button")).click();

		//switch to contacts
		//driver.findElement(By.linkText("Contacts")).click();
		//waiting statement - explicit 
		//WebDriverWait wait = new WebDriverWait(driver, longtimeout);
		//wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Contacts"))).click();
		
		WebDriverLibrary.waitUntillElementClickable(driver.findElement(By.linkText("Contacts")));
		driver.findElement(By.linkText("Contacts")).click();
		//driver.findElement(By.xpath("//a[@href='index.php?module=Contacts&action=index']")).click();
		//WebElement ele = driver.findElement(By.linkText("Contacts"));
		//WebDriverLibraries.mouseHoverOnElement(ele);
		
		//Click on + button
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		//Enter mandatory fields and last name= something 
		driver.findElement(By.name("lastname")).sendKeys(contactLastName);

		//Adding organization
		driver.findElement(By.xpath("//img[@src='themes/softed/images/select.gif\']")).click();
		WebDriverLibrary.switchToWindowBasedOnTitle(driver, "Accounts&action");
		
		driver.findElement(By.id("search_txt")).sendKeys("og");
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[@id=\"1\"]")).click();
		
		//close child window
		WebDriverLibrary.switchToWindowBasedOnTitle(driver, "Contacts&action");

		//click on save 
		driver.findElement(By.name("button")).click();

		WebElement actualLastName = driver.findElement(By.id("dtlview_Last Name"));
		JavaLibrary.assertionThroughIfCondition(actualLastName.getText(), contactLastName, "contacts");

		//mousehover to logout options
		WebElement logout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		WebDriverLibrary.mouseHoverOnElement(logout);
		
		//click on signout
		driver.findElement(By.linkText("Sign Out")).click();
		WebDriverLibrary.quitBrowser(driver);	
		}
}
