package com.vtiger.practise;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.sdet34l1.genericLibrary.ExcelLibrary;
import com.sdet34l1.genericLibrary.FileLibrary;
import com.sdet34l1.genericLibrary.JavaLibrary;
import com.sdet34l1.genericLibrary.WebDriverLibrary;
import com.vtiger.objectRepository.CreateNewOrganizationPage;
import com.vtiger.objectRepository.HomePage;
import com.vtiger.objectRepository.LoginPage;
import com.vtiger.objectRepository.OrganizationInformationPage;
import com.vtiger.objectRepository.OrganizationPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganizationTestBeforeTestNG {

	public static void main(String[] args) throws Throwable {
		WebDriver driver=null;
		
		//FileLibrary.openPropertyFile(IconstantPath.PROPERTYFILEPATH);
		//ExcelLibrary.openExcel(IconstantPath.EXCELFILEPATH);
		
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
		
		LoginPage lp = new LoginPage(driver);
		HomePage hp = new HomePage(driver);
		OrganizationPage op = new OrganizationPage(driver);
		CreateNewOrganizationPage createorganizationpage = new CreateNewOrganizationPage(driver);
		OrganizationInformationPage orginfopage = new OrganizationInformationPage(driver);
		
		lp.loginAction(username, password);
		hp.clickOrganization();
		op.createNewOraganization();
		createorganizationpage.enterOranizationName(organizationName);
		createorganizationpage.save();
		
		
		JavaLibrary.assertionThroughIfCondition(orginfopage.getOraganizationName(), organizationName, "organizations");
		
		hp.signout(driver);
	
		//ExcelLibraries.flushDataExcelFile(com.sdet34l1.genericLibrary.IconstantPath);
		//ExcelLibraries.closeExcel();
		
		//Close the application
		Thread.sleep(20);
		WebDriverLibrary.quitBrowser(driver);
		}
}