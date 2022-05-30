package com.vtiger.practise;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.sdet34l1.genericLibrary.FileLibrary;
import com.sdet34l1.genericLibrary.JavaLibrary;
import com.sdet34l1.genericLibrary.WebDriverLibrary;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TC_46 {

	public static void main(String[] args) throws Throwable {
		//FileLibrary.openPropertyFile(IconstantPath.PROPERTYFILEPATH);
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
		
		driver.findElement(By.xpath("//img[@id='show_Contacts_Quotes']")).click();
		
		driver.findElement(By.name("button")).click();
		//driver.findElement(By.linkText("More")).click();
		
	    //WebDriverLibraries.explicitlyWait(driver, 10);
		//WebDriverLibraries.waitUntillElementClickable(driver.findElement(By.linkText("More")));
		//WebElement element = driver.findElement(By.linkText("More"));
		//WebDriverLibraries.mouseHoverOnElement(element);
		
		//driver.findElement(By.xpath("//a[@id='more' and @name='Quotes']")).click();
		
		//driver.findElement(By.xpath("//img[@title='Create Quote...']")).click();
		//driver.findElement(By.name("subject")).sendKeys("");

		//WebElement quoteStageDropdown = driver.findElement(By.name("quotestage"));
		//WebDriverLibraries.SelectdropDown("Reviewed", quoteStageDropdown);
		
		driver.findElement(By.name("button")).click();
		
		Thread.sleep(5000);
		Alert a= driver.switchTo().alert();
		a. accept();
		
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
		//ExcelLibraries.flushDataExcelFile(IconstantPath.EXCELPATH_TC);
		//ExcelLibraries.closeExcel();
		WebDriverLibrary.quitBrowser(driver);		
	}
}
