package com.vtiger.practise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.sdet34l1.genericLibrary.ExcelLibrary;
import com.sdet34l1.genericLibrary.FileLibrary;
import com.sdet34l1.genericLibrary.IconstantPath;
import com.sdet34l1.genericLibrary.JavaLibrary;
import com.sdet34l1.genericLibrary.WebDriverLibrary;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateCampaignTest {

	public static void main(String[] args) throws Throwable {
		WebDriver driver=null;

		FileLibrary fileLibrary= new FileLibrary();
		ExcelLibrary excelLibrary= new ExcelLibrary();
		JavaLibrary javaLibrary= new JavaLibrary();
		WebDriverLibrary webDriverLibrary= new WebDriverLibrary();
		
		fileLibrary.openPropertyFile(com.sdet34l1.genericLibrary.IconstantPath.FILEPATH);
		excelLibrary.openExcel(IconstantPath.EXCELPATH);

		String url = fileLibrary.getDataFromPropertyFile("url");
		String username =  fileLibrary.getDataFromPropertyFile("username");
		String password =  fileLibrary.getDataFromPropertyFile("password");
		String timeout =  fileLibrary.getDataFromPropertyFile("timeout");
		String browser = fileLibrary.getDataFromPropertyFile("browser");

		long longTimeOut = javaLibrary.stringToLong(timeout);

		int randomNumber = javaLibrary.getRandomNumber(1000);

		String campaignName = excelLibrary.getDataFromExcel("Campaign", 2, 1)+ randomNumber;

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
			System.out.println("Specify a valid browser"); 
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();
			break;
		}

		webDriverLibrary.navigateApp(url, driver);
		webDriverLibrary.browserSetting(longTimeOut, driver);	
		webDriverLibrary.initializActions(driver);

		//--Step1 : Login to application : Home Page should display
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		if (driver.getTitle().contains("Home")) {
			excelLibrary.setDataInExcel("Campaign", 8, 4, "Home page diaplayed");
			excelLibrary.setDataInExcel("Campaign", 8, 5, "pass");
		}

		//--Step2 : Click on campaign : Campaign page should display
		WebElement MoreOptions = driver.findElement(By.xpath("//img[@src='themes/softed/images/menuDnArrow.gif']"));
		webDriverLibrary.mouseHoverOnElement(MoreOptions);
		driver.findElement(By.name("Campaigns")).click();
		String title = driver.findElement(By.linkText("Campaigns")).getText();
		if (title.contains("Campaigns")) {
			excelLibrary.setDataInExcel("Campaign", 9, 4, "Campaigns page diaplayed");
			excelLibrary.setDataInExcel("Campaign", 9, 5, "Pass");
		}

		//--Step3 : Click on Create campaign : Create campaign page should display
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		String title2 = driver.findElement(By.xpath("//span[text()='Creating New Campaign']")).getText();
		if (title2.contains("Creating New Campaign")) {
			excelLibrary.setDataInExcel("Campaign", 10, 4, "Create campaign page diaplayed");
			excelLibrary.setDataInExcel("Campaign", 10, 5, "Pass");
		}

		//--Step4 : Enter mandatory fields : Campaign information page should display
		driver.findElement(By.name("campaignname")).sendKeys(campaignName + randomNumber);
		driver.findElement(By.name("button")).click();

		//--Step 5: Validate the Campaign name : Data should be created as expected
		WebElement actualCampaignName = driver.findElement(By.id("dtlview_Campaign Name"));
		if(actualCampaignName.getText().contains(campaignName))
		{
			excelLibrary.setDataInExcel("Campaign", 11, 4, "Data is created as expected");
			excelLibrary.setDataInExcel("Campaign", 11, 5, "Pass");
			excelLibrary.setDataInExcel("Campaign", 2, 3, "Pass");
			javaLibrary.printStatement("Tc pass");
		}

		//--Step 6: Click on Sign-out : Login page should display
		WebElement logout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		webDriverLibrary.mouseHoverOnElement(logout);
		driver.findElement(By.linkText("Sign Out")).click();
	
		excelLibrary.setDataInExcel("Campaign", 13, 4, "Login page diaplayed");
			if(driver.getCurrentUrl().contains("Login"))
		{
				excelLibrary.setDataInExcel("Campaign", 13, 5, "pass");
		}

		//ExcelLibrary.flushDataExcelFile(com.sdet34l1.genericLibrary.IconstantPath.EXCELPATH);

		webDriverLibrary.quitBrowser(driver);
	}
}
