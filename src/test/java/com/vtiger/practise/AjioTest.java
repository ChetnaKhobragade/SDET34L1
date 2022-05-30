package com.vtiger.practise;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AjioTest {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.firefoxdriver().setup();
		WebDriver driver = new FirefoxDriver();

		driver.get("https://www.ajio.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.xpath("//div[@class='ic-close-quickview']")).click();
		driver.findElement(By.xpath("//a[@title='KIDS']/parent::li")).click();

		// WebElement element = driver.findElement(By.xpath("//a[@href='/s/starting-at-rs-199-4663-75761']"));
		// JavascriptExecutor js=(JavascriptExecutor) driver;
		// js.executeScript("argument[0].scrollIntoView()",element);
		// js.executeAsyncScript("argument[0].click()",element);


		driver.findElement(By.xpath("//a[@href='/s/starting-at-rs-199-4663-75761']")).click();
		
		///following-sibling::span
		WebElement sortByDropdown = driver.findElement(By.xpath("//select[@class='']"));
		Select select = new Select(sortByDropdown);
		select.selectByValue("prce-desc");
		

		Thread.sleep(20);
	//	driver.close();
	}

}
