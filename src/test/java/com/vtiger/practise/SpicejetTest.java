package com.vtiger.practise;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SpicejetTest {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.firefoxdriver().setup();
		
				//To handle notification popup
				FirefoxOptions options=new FirefoxOptions();
				
				options.addArguments("--disable-notifications");
				
				WebDriver driver=new FirefoxDriver(options);
				JavascriptExecutor js=(JavascriptExecutor)driver;
				driver.manage().window().maximize();
				driver.get("https://www.spicejet.com/");
				
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.findElement(By.xpath("//div[text()='round trip']")).click();
				driver.findElement(By.xpath("//input[@autocapitalize='sentences']")).click();
				driver.findElement(By.xpath("//div[text()='Bengaluru']")).click();
				driver.findElement(By.xpath("//div[text()='Delhi']")).click();
				Thread.sleep(1000);
				driver.findElement(By.xpath("//div[text()='20']")).click();
				driver.findElement(By.xpath("//div[text()='21']")).click();
				Thread.sleep(1000);
				driver.findElement(By.xpath("//div[text()='Passengers']")).click();
				driver.findElement(By.xpath("//div[@data-testid='Children-testID-plus-one-cta']")).click();
				
				driver.findElement(By.xpath("//div[@data-testid='home-page-travellers-done-cta']")).click();
				
				driver.findElement(By.xpath("//div[@data-testid='home-page-flight-cta']")).click();
				
				driver.findElement(By.xpath("//div[@data-testid='spiceflex-flight-select-radio-button-2']")).click();
				
				js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
				js.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.xpath("//div[text()='SG 191']")));
				
				driver.findElement(By.xpath("//div[@data-testid='spicemax-flight-select-radio-button-3']")).click();
				
				driver.findElement(By.xpath("//div[@data-testid='continue-search-page-cta']")).click();
				
				//Enter first name
				driver.findElement(By.xpath("//input[@maxlength='32']")).sendKeys("Chetna");
				
				//Enter last name
				driver.findElement(By.xpath("(//input[@maxlength='32'])[2]")).sendKeys("Khobragade");
				
				//Enter contact number
				driver.findElement(By.xpath("//input[@maxlength='10']")).sendKeys("7777777777");
				
				//Enter email
				driver.findElement(By.xpath("//input[@maxlength='266']")).sendKeys("chetna.khobragade11@gmail.com");
				
				//Enter town or city
				driver.findElement(By.xpath("(//input[@maxlength='32'])[3]")).sendKeys("Nagpur");
				
				//Enter country
				//driver.findElement(By.xpath("(//div[@class='css-1dbjc4n r-1awozwy r-18u37iz r-1wtj0ep'])[3]")).click();
				
				js.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.xpath("//div[text()='Passenger 1']")));
				
				driver.findElement(By.xpath("//div[text()='I am flying as the primary passenger']")).click();
				
				driver.findElement(By.xpath("//div[text()='Next']")).click();
				
				//Enter passenger 2 details
				driver.findElement(By.xpath("//div[text()='Select']")).click();
				driver.findElement(By.xpath("//div[text()='Master']")).click();
				
				//Enter child first name
				driver.findElement(By.xpath("(//input[@type='text'])[6]")).sendKeys("baby");
				
				//Enter child last name
				driver.findElement(By.xpath("(//input[@type='text'])[7]")).sendKeys("k");
				
				driver.findElement(By.xpath("//div[@data-testid='traveller-info-continue-cta']")).click();	
	}
}
