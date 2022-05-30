package com.vtiger.practise;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class JavaScriptExecutor {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver= new ChromeDriver();
		JavascriptExecutor js =(JavascriptExecutor) driver;
		js.executeScript("window.location='http://localhost:8888/'");
		driver.manage().window().maximize();
		js.executeScript("arguments[0].value='admin'",driver.findElement(By.name("user_name")));
		js.executeScript("arguments[0].value='admin'",driver.findElement(By.name("user_password")));
		js.executeScript("arguments[0].click()",driver.findElement(By.name("submitButton")));
		//js.executeScript("document.getElementByName('user_name').value='admin'");
		//js.executeScript("document.getElementByName('password').value='admin'");
		//js.executeScript("document.getElementById('submitButton').click()");
		js.executeScript("window.scrollBy(0,500)");
	}

}
