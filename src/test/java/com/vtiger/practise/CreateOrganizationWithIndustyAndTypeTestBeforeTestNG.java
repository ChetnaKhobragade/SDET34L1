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

public class CreateOrganizationWithIndustyAndTypeTestBeforeTestNG {

	public static void main(String[] args) throws Throwable{
		WebDriver driver=null;

		//FileLibraries.openPropertyFile(IconstantPath.PROPERTYFILEPATH);
		//ExcelLibraries.openExcel(IconstantPath.EXCELFILEPATH);

		String url = FileLibrary.getDataFromPropertyFile("url");
		String username =  FileLibrary.getDataFromPropertyFile("username");
		String password =  FileLibrary.getDataFromPropertyFile("password");
		String timeout =  FileLibrary.getDataFromPropertyFile("timeout");
		String browser = FileLibrary.getDataFromPropertyFile("browser");

		long longTimeOut = JavaLibrary.stringToLong(timeout);

		int randomNumber = JavaLibrary.getRandomNumber(1000);

		switch(browser){
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver= new FirefoxDriver();
			break;
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();
			break;
		default:
			System.out.println("Please specify a valid browser");
			WebDriverManager.firefoxdriver().setup();
			driver= new FirefoxDriver();
			break;
		}

		String organizationName = ExcelLibrary.getDataFromExcel("organization", 2, 1) + randomNumber;

		WebDriverLibrary.navigateApp(url, driver);
		WebDriverLibrary.browserSetting(longTimeOut, driver);
		WebDriverLibrary.initializActions(driver);
		
		LoginPage lp = new LoginPage(driver);
		HomePage hp = new HomePage(driver);
		OrganizationPage op = new OrganizationPage(driver);
		CreateNewOrganizationPage createorganizationpage = new CreateNewOrganizationPage(driver);
		OrganizationInformationPage orginfo = new OrganizationInformationPage(driver);
		
		lp.loginAction(username, password);
		hp.clickOrganization();
		op.createNewOraganization();
		createorganizationpage.enterOranizationName(organizationName);
	//	WebDriverLibrary.SelectdropDown(createorganizationpage.industryDropdown(), "Education");
		//WebDriverLibrary.SelectdropDown(createorganizationpage.typeDropdown(), "Press");
		createorganizationpage.save();
		
		JavaLibrary.assertionThroughIfCondition(orginfo.getOraganizationName(), organizationName, "organization");
		
		hp.signout(driver);
		//ExcelLibraries.flushDataExcelFile(IconstantPath.EXCELFILEPATH);
		//ExcelLibraries.closeExcel();
		WebDriverLibrary.quitBrowser(driver);	
	}
}
