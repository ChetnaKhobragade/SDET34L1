package com.vtiger.practise;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.sdet34l1.genericLibrary.ExcelLibrary;
import com.sdet34l1.genericLibrary.FileLibrary;
import com.sdet34l1.genericLibrary.IconstantPath;
import com.sdet34l1.genericLibrary.JavaLibrary;
import com.sdet34l1.genericLibrary.WebDriverLibrary;
import com.vtiger.objectRepository.CampaignInformationPage;
import com.vtiger.objectRepository.CampaignPage;
import com.vtiger.objectRepository.CreateNewCampaignPage;
import com.vtiger.objectRepository.CreateNewProductPage;
import com.vtiger.objectRepository.HomePage;
import com.vtiger.objectRepository.LoginPage;
import com.vtiger.objectRepository.ProductPage;
import com.vtiger.objectRepository.SearchProductsPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateCampaignWithProductTestBeforeTestNG {

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


		String campaignName = excelLibrary.getDataFromExcel("Campaign", 4, 1) + randomNumber;	
		String productName = excelLibrary.getDataFromExcel("Campaign", 4, 2) + randomNumber;	

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
		ProductPage pp= new ProductPage(driver);
		CampaignPage campaignpage = new CampaignPage(driver);
		CreateNewProductPage createnewproduct = new CreateNewProductPage(driver);
		CreateNewCampaignPage createnewcampaign = new CreateNewCampaignPage(driver);
		SearchProductsPage searchproduct= new SearchProductsPage(driver);
		CampaignInformationPage campaigninfo = new CampaignInformationPage(driver);
		
		//--Step1 : Login to application : Home Page should display
		lp.loginAction(username, password);
		
		//--Step2 : Click on product : Product page should display
		hp.clickProduct();
		
		//--Step3 : Click on Create product : Create product page should display
		pp.createNewProduct();
		
		//--Step4 : Enter mandatory fields : Product information page should display
		createnewproduct.enterProductNameandSave(productName);
		
        //Mouse hover to More option
		WebDriverLibrary.waitUntillElementClickable(hp.seeMoreOptions(driver));
		WebDriverLibrary.mouseHoverOnElement(hp.seeMoreOptions(driver));
		
		//--Step5 : Click on campaign : Campaign page should display
		hp.clickCampaign();
		
		//--Step6 : Click on Create campaign : Create campaign page should display
		campaignpage.clickCampaignPlusbtn();
		
		//--Step4 : Enter mandatory fields : Campaign information page should display 
		createnewcampaign.enterCampaignName( campaignName);
		
		//Add product, that we created before
		createnewproduct.addProductOption(productName);
		WebDriverLibrary.switchToWindowBasedOnTitle(driver, "Products");
		
		searchproduct.selectProduct(productName, driver);

		WebDriverLibrary.switchToWindowBasedOnTitle(driver, "Campaigns");
		
		//save campaign
		createnewcampaign.save();
		//driver.findElement(By.name("button")).click();
		
		//validation CampaignName
		JavaLibrary.assertionThroughIfCondition(campaigninfo.getCampaignName(), campaignName, "campaign");

		//logout
		hp.signout(driver);
		
		//ExcelLibrary.flushDataExcelFile(IconstantPath.EXCELFILEPATH);
		//close browser
		WebDriverLibrary.quitBrowser(driver);	
		}
}
