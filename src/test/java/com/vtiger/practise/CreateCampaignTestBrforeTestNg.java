package com.vtiger.practise;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.sdet34l1.genericLibrary.ExcelLibrary;
import com.sdet34l1.genericLibrary.FileLibrary;
import com.sdet34l1.genericLibrary.IconstantPath;
import com.sdet34l1.genericLibrary.JavaLibrary;
import com.sdet34l1.genericLibrary.WebDriverLibrary;
import com.vtiger.objectRepository.CampaignInformationPage;
import com.vtiger.objectRepository.CampaignPage;
import com.vtiger.objectRepository.CreateNewCampaignPage;
import com.vtiger.objectRepository.HomePage;
import com.vtiger.objectRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateCampaignTestBrforeTestNg {

	@Test
	public void createCampaignTest() throws Throwable {
	WebDriver driver=null;
	
	FileLibrary fileLibrary= new FileLibrary();
	ExcelLibrary excelLibrary= new ExcelLibrary();
	JavaLibrary javaLibrary= new JavaLibrary();
	WebDriverLibrary webDriverLibrary= new WebDriverLibrary();
	
	fileLibrary.openPropertyFile(com.sdet34l1.genericLibrary.IconstantPath.FILEPATH);
	excelLibrary.openExcel(IconstantPath.EXCELPATH);
		
		String url = fileLibrary.getDataFromPropertyFile("url");
		String username = fileLibrary.getDataFromPropertyFile("username");
		String password = fileLibrary.getDataFromPropertyFile("password");
		String timeout =  fileLibrary.getDataFromPropertyFile("timeout");
		String browser = fileLibrary.getDataFromPropertyFile("browser");


		int randomNumber = javaLibrary.getRandomNumber(1000);

		String campaignName = excelLibrary.getDataFromExcel("Campaign", 2, 1) + randomNumber;

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
		webDriverLibrary.browserSetting(10, driver);	
		webDriverLibrary.initializActions(driver);
		LoginPage lp = new LoginPage(driver);
		HomePage hp = new HomePage(driver);
		CampaignPage cp = new CampaignPage(driver);
		CreateNewCampaignPage cncp = new CreateNewCampaignPage(driver);
		CampaignInformationPage cip = new CampaignInformationPage(driver);

		//--Step1 : Login to application : Home Page should display
		lp.loginAction(username, password);
		hp.clickCampaign(webDriverLibrary);
		cp.clickCampaignPlusbtn();
		cncp.enterCampaignName( campaignName);
		cncp.save();
						
		javaLibrary.assertionThroughIfCondition(cip.getCampaignNameText(), campaignName, "campaign");
		
		hp.signout(driver, webDriverLibrary);

		webDriverLibrary.quitBrowser(driver);
	}
}
