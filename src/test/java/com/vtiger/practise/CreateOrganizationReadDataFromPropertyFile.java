package com.vtiger.practise;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.sdet34l1.genericLibrary.FileLibrary;
import com.sdet34l1.genericLibrary.JavaLibrary;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganizationReadDataFromPropertyFile {

	public static void main(String[] args) throws Throwable  {
		WebDriver driver=null;

	//	FileLibrary.openPropertyFile(IconstantPath.PROPERTYFILEPATH);
		
		String url = FileLibrary.getDataFromPropertyFile("url");
		String username =  FileLibrary.getDataFromPropertyFile("username");
		String password =  FileLibrary.getDataFromPropertyFile("password");
		String timeout =  FileLibrary.getDataFromPropertyFile("timeout");
		String browser = FileLibrary.getDataFromPropertyFile("browser");
		
		long longTimeOut = JavaLibrary.stringToLong(timeout);

		int randomNumber = JavaLibrary.getRandomNumber(1000);
		
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
			
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();
			break;
		}
		
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(longTimeOut, TimeUnit.SECONDS);
		
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
		driver.findElement(By.linkText("Organizations")).click();
		
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

		
		driver.findElement(By.name("accountname")).sendKeys("ordi"+randomNumber);
		driver.findElement(By.name("button")).click();
		
		Thread.sleep(10);
		WebElement logoutIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions a= new Actions(driver);
		a.moveToElement(logoutIcon).perform();
		
		//mousehover to logout options
			//WebElement logout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
			//Actions a= new Actions(driver);
			//a.moveToElement(logout).perform();
			
		
		driver.findElement(By.linkText("Sign Out")).click();
		driver.close();
	}

}
