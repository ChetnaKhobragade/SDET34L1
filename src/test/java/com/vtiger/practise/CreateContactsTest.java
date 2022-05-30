package com.vtiger.practise;

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

public class CreateContactsTest {

	public static void main(String[] args) throws Throwable {
		WebDriver driver=null;

		//FileLibrary.openPropertyFile("./src/test/resources/commanData.properties");
		//ExcelLibraries.openExcel("./src/test/resources/TestData.xlsx");
		
		String url = FileLibrary.getDataFromPropertyFile("url");
		String username =  FileLibrary.getDataFromPropertyFile("username");
		String password =  FileLibrary.getDataFromPropertyFile("password");
		String timeout =  FileLibrary.getDataFromPropertyFile("timeout");
		String browser = FileLibrary.getDataFromPropertyFile("browser");

		long longTimeOut = JavaLibrary.stringToLong(timeout);

		int randomNumber = JavaLibrary.getRandomNumber(1000);

		//excel sheet for Test data - like organization name 
		String contactLastName = ExcelLibrary.getDataFromExcel("contacts", 2, 1 );	
		
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
		
		WebDriverLibrary.navigateApp(url, driver);
		WebDriverLibrary.browserSetting(longTimeOut, driver);	
		WebDriverLibrary.initializActions(driver);
		
		//enter username
		driver.findElement(By.name("user_name")).sendKeys(username);
		//enter password
		driver.findElement(By.name("user_password")).sendKeys(password);
		//click on enter 
		driver.findElement(By.id("submitButton")).click();
		//click on contacts
		driver.findElement(By.linkText("Contacts")).click();
		//Click on + button
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		//enter mandatory fields and last name= something 

		driver.findElement(By.name("lastname")).sendKeys(contactLastName + randomNumber);
		//click on save 
		driver.findElement(By.name("button")).click();
		//validate last name field
		WebElement actualContactName = driver.findElement(By.id("dtlview_Last Name"));
		JavaLibrary.assertionThroughIfCondition(actualContactName.getText(), contactLastName, "contacts");

		//mouse hover to logout options
		WebElement logout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		WebDriverLibrary.mouseHoverOnElement(logout);

		Thread.sleep(10);
		//click on signout
		driver.findElement(By.linkText("Sign Out")).click();

		Thread.sleep(10);
		WebDriverLibrary.quitBrowser(driver);	}
}
