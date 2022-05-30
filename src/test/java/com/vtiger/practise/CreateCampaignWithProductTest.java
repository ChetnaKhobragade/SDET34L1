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
import com.vtiger.objectRepository.HomePage;
import com.vtiger.objectRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateCampaignWithProductTest {

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

		String campaignName = excelLibrary.getDataFromExcel("product", 4, 1) + randomNumber;	
		String productName = excelLibrary.getDataFromExcel("product", 4, 2) + randomNumber;	

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
			javaLibrary.printStatement("specify browser correctly");
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();
			break;
		}

		webDriverLibrary.navigateApp(url, driver);
		webDriverLibrary.browserSetting(longTimeOut, driver);	
		webDriverLibrary.explicitlyWait(driver, longTimeOut);
		webDriverLibrary.initializActions(driver);
		LoginPage lp = new LoginPage(driver);
		HomePage hp = new HomePage(driver);

		//--Step1 : Login to application : Home Page should display
		
		lp.loginAction(username, password);
		hp.clickCampaign(webDriverLibrary);
		
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		if (driver.getTitle().contains("Home")) {
			excelLibrary.setDataInExcel("campaign", 8, 4, "Home page diaplayed");
			excelLibrary.setDataInExcel("campaign", 8, 5, "pass");
		}

		//--Step2 : Click on Products : Products page should display
		driver.findElement(By.linkText("Products")).click();
		String title = driver.findElement(By.linkText("Products")).getText();
		if (title.contains("Products")) {
			excelLibrary.setDataInExcel("campaign", 9, 4, "Products page diaplayed");
			excelLibrary.setDataInExcel("campaign", 9, 5, "pass");
		}		
		
		//--Step3 : Click on Create campaign : Create campaign page should display
		driver.findElement(By.xpath("//img[@title='Create Product...']")).click();
		
		driver.findElement(By.name("productname")).sendKeys(productName);
		driver.findElement(By.name("button")).click();
		
		webDriverLibrary.waitUntillElementClickable(driver.findElement(By.linkText("More")));
		WebElement MoreOptions = driver.findElement(By.linkText("More"));
		webDriverLibrary.mouseHoverOnElement(MoreOptions);

		driver.findElement(By.name("Campaigns")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		
		driver.findElement(By.name("campaignname")).sendKeys(campaignName);

		//adding created product
		driver.findElement(By.xpath("//img[@src='themes/softed/images/select.gif']")).click();
		
		driver.findElement(By.xpath("//td[contains(text(),'Product') and @class = 'dvtCellLabel']/following-sibling::td/img")).click();

		WebDriverLibrary.switchToWindowBasedOnTitle(driver, "Products");
		
		driver.findElement(By.xpath("//input[@name='search_text']")).sendKeys(productName);
		driver.findElement(By.xpath("//input[@class='crmbutton small create']")).sendKeys(productName);
		
		
		//driver.findElement(By.xpath("//a[text()= '+"productName"+']"))

		WebDriverLibrary.switchToWindowBasedOnTitle(driver, "Campaigns");

		driver.findElement(By.name("search")).click();
	
		//save campaign
		driver.findElement(By.name("button")).click();
		
		//validation actualCampaignName and  CampaignName
		
		//logout
		WebElement logout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		webDriverLibrary.mouseHoverOnElement(logout);
		driver.findElement(By.linkText("Sign Out")).click();

		//ExcelLibraries.flushDataExcelFile(IconstantPath.EXCELFILEPATH);
		//WebDriverLibraries.quitBrowser(driver);	
		}
}
