package com.vtiger.practise;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactReadDataFromPropertyfile {

	public static void main(String[] args) throws IOException {
		WebDriver driver=null;
		FileInputStream fis = new FileInputStream("./src/test/resources/commanData.properties");
		Properties property= new Properties();
		property.load(fis);
		String url = property.getProperty("url");
		String username = property.getProperty("username");
		String password = property.getProperty("password");
		String timeouts = property.getProperty("timeouts");
		String browser = property.getProperty("browser");

		long longTimeOut = Long.parseLong(timeouts);

		switch (browser) {
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver= new FirefoxDriver();
			break;

		default:
			break;
		}
		
		driver.get(url);
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(longTimeOut, TimeUnit.SECONDS);
		
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
		WebElement MoreOptions = driver.findElement(By.xpath("//img[@src='themes/softed/images/menuDnArrow.gif']"));
		Actions a = new Actions(driver);
		a.moveToElement(MoreOptions).perform();
		
		driver.findElement(By.name("Campaigns")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		driver.findElement(By.name("campaignname")).sendKeys("ll0ll");
		driver.findElement(By.name("button")).click();
	
		driver.close();
			
	}
}
