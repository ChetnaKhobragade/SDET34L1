package com.vtiger.practise;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.sdet34l1.genericLibrary.FileLibrary;
import com.sdet34l1.genericLibrary.JavaLibrary;
import com.sdet34l1.genericLibrary.WebDriverLibrary;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TC_47 {

	public static void main(String[] args) throws Throwable {
		//FileLibraries.openPropertyFile(IconstantPath.PROPERTYFILEPATH);
		//ExcelLibraries.openExcel(IconstantPath.EXCELPATH_TC);

		String url = FileLibrary.getDataFromPropertyFile("url");
		String username = FileLibrary.getDataFromPropertyFile("username");
		String password = FileLibrary.getDataFromPropertyFile("password");

		WebDriverManager.firefoxdriver().setup();
		WebDriver driver= new FirefoxDriver();

		WebDriverLibrary.navigateApp(url, driver);
		WebDriverLibrary.browserSetting(10, driver);
		WebDriverLibrary.initializActions(driver);

		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();

		driver.findElement(By.linkText("Contacts")).click();

		driver.findElement(By.xpath("//a[@title='Contacts']/../ancestor::tr[@id='row_9']//descendant::td/input[@id='9']")).click();

		driver.findElement(By.xpath("//a[@title='Contacts']/../ancestor::tr[@id='row_9']//descendant::td/a[text()='ooo']")).click();

		driver.findElement(By.linkText("More Information")).click();
		
		driver.findElement(By.id("show_Contacts_SalesOrder")).click();
		driver.findElement(By.name("button")).click();

		driver.findElement(By.name("subject")).sendKeys("Mobile Bill");
		
		driver.findElement(By.xpath("(//img[@src='themes/softed/images/select.gif' and @title='Select'])[4]")).click();

		WebDriverLibrary.switchToWindowBasedOnTitle(driver, "Accounts&action");
		//String parent = driver.getWindowHandle();
		//Set<String> child= driver.getWindowHandles();
		//for(String b : child) {
		//	driver.switchTo().window(b);
		//}
		driver.findElement(By.xpath("//input[@id='search_txt']")).sendKeys("org");
		driver.findElement(By.xpath("//input[@name='search']")).click();

		//driver.switchTo().window(parent);
		WebDriverLibrary.explicitlyWait(driver, 10);
		WebDriverLibrary.waitUntillElementClickable(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")));
		WebElement administratorIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		WebDriverLibrary.mouseHoverOnElement(administratorIcon);
		
		driver.findElement(By.linkText("Sign Out")).click();
		
		WebElement usrElement = driver.findElement(By.name("user_name"));
		WebDriverLibrary.waitUntillElementClickable(usrElement);
		
		if(driver.getCurrentUrl().contains("Login")) {
			//ExcelLibraries.writeDataOnExcelFile("contact", 22, 5, "pass");
			JavaLibrary.printStatement("--login page is displayed--");
			
		}
		WebDriverLibrary.quitBrowser(driver);
		
	}

}
