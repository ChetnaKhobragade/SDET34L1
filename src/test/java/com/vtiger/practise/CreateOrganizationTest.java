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

public class CreateOrganizationTest {

	public static void main(String[] args) throws Throwable {
		WebDriver driver=null;

		//FileLibraries.openPropertyFile(IconstantPath.PROPERTYFILEPATH);
		//ExcelLibraries.openExcel(IconstantPath.EXCELFILEPATH);

		String url = FileLibrary.getDataFromPropertyFile("url");
		String username =  FileLibrary.getDataFromPropertyFile("username");
		String password =  FileLibrary.getDataFromPropertyFile("password");
		String timeout =  FileLibrary.getDataFromPropertyFile("timeout");
		String browser = FileLibrary.getDataFromPropertyFile("browser");

		long longTimeOut = JavaLibrary.stringToLong(timeout);

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
			JavaLibrary.printStatement("Specify a valid browser");
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();
			break;
		}


		int randomNumber=JavaLibrary.getRandomNumber(1000);
		String organizationName = ExcelLibrary.getDataFromExcel("organization", 2, 1) + randomNumber;

		WebDriverLibrary.navigateApp(url, driver);
		WebDriverLibrary.browserSetting(longTimeOut, driver);	
		WebDriverLibrary.initializActions(driver);


		//--Step1 : Login to app : Home Page should display
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		if (driver.getTitle().contains("Home")) {
			ExcelLibrary.setDataInExcel("organization", 8, 4, "Home page diaplayed");
			ExcelLibrary.setDataInExcel("organization", 8, 5, "pass");
		}

		//--Step2 : Click on organization : organization page should display
		driver.findElement(By.linkText("Organizations")).click();
		String title = driver.findElement(By.linkText("Organizations")).getText();
		if (title.contains("Organizations")) {
			ExcelLibrary.setDataInExcel("organization", 9, 4, "Organizations page diaplayed");
			ExcelLibrary.setDataInExcel("organization", 9, 5, "Pass");
		}

		//--Step3 : Click on Create organization : Create organization page should display
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		String title2 = driver.findElement(By.xpath("//span[text()='Creating New Organization']")).getText();
		if (title2.contains("Creating New Organization")) {
			ExcelLibrary.setDataInExcel("organization", 10, 4, "Create organization page diaplayed");
			ExcelLibrary.setDataInExcel("organization", 10, 5, "Pass");
		}

		//--Step4 : Enter mandatory fields : Campaign information page should display
		organizationName= organizationName + randomNumber;
		driver.findElement(By.name("accountname")).sendKeys(organizationName + randomNumber );
		driver.findElement(By.name("button")).click();

		//--Step 5: Validate the Campaign name : Data should be created as expected
		WebElement actualOrganizationName = driver.findElement(By.id("dtlview_Organization Name"));
		if (actualOrganizationName.getText().contains(organizationName))
		{
			ExcelLibrary.setDataInExcel("organization", 1, 4, "pass");
			ExcelLibrary.setDataInExcel("organization", 11, 4, "Data is created as expected");
			ExcelLibrary.setDataInExcel("organization", 11, 5, "pass");
			JavaLibrary.printStatement("The oraganization is created with oraganization ");
			JavaLibrary.printStatement("Tc pass");
		}

		//--Step 6: Click on Sign-out : Login page should display
		WebElement logout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		WebDriverLibrary.mouseHoverOnElement(logout);
		Thread.sleep(10);
		driver.findElement(By.linkText("Sign Out")).click();
		if(driver.getCurrentUrl().contains("Login"))
		{
			ExcelLibrary.setDataInExcel("organization", 13, 4, "Login page diaplayed");
			ExcelLibrary.setDataInExcel("organization", 13, 5, "pass");
		}

		//ExcelLibraries.flushDataExcelFile(IconstantPath.EXCELFILEPATH);
		//ExcelLibraries.closeExcel();
		//Close the application
		Thread.sleep(20);
		WebDriverLibrary.quitBrowser(driver);
	}
}