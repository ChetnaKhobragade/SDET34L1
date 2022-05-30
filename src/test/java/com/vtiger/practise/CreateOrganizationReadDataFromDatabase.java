package com.vtiger.practise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganizationReadDataFromDatabase {

	public static void main(String[] args) throws SQLException {
		//declaring all the variables global because if declared in while block it will be local to that block
		String url=null,username=null,password=null,organization=null,timeouts=null,browser=null;

		//declaring Webdriver global 
		WebDriver driver=null;

		//connection with database and read the data from the table all 7 steps 

		Driver d= new Driver();
		DriverManager.registerDriver(d);
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sdet34l1", "root", "root");	
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("select * from vtiger");
		while(result.next()) {
			//url,username,password,organization,browser= String and timeouts is having long return type
			url = result.getString("url");
			username = result.getString("username");
			password = result.getString("password");
			organization = result.getString("organization");
			timeouts=result.getString("timeouts");
			browser=result.getString("browser");

		}

		//converting the string to long type
		long longTimeOut = Long.parseLong(timeouts);

		//connection.close();
		/*	if(browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver= new FirefoxDriver();
		}
		else if(browser.equals("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();
		}
		 */

		//either if else or switch case for browser --- if else is slower and switch is bit faaster
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
		driver.findElement(By.name("accountname")).sendKeys(organization);
		//click on save 
		driver.findElement(By.name("button")).click();
		driver.close();
	}
}