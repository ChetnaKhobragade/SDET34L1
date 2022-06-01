package com.sdet34l1.genericLibrary;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.vtiger.objectRepository.HomePage;
import com.vtiger.objectRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * This class contains implementation of all basic configuration annotation
@author CHETNA K and priya kumari
 *

 * @author CHETNA K and yo yo honey singh
 * this are the changes check 1233...
 * chnages done by chtena 
 */
public class BaseClass {
	public WebDriver driver;
	public long longTimeOut;
	public LoginPage loginpage;
	public HomePage homepage;
	public String url;
	public String password;
	public String username;
	public String browser;
	public int randomNumber;
	public ExcelLibrary excelLibrary ;
	public FileLibrary fileLibrary ;
	public WebDriverLibrary webDriverLibrary;
	static WebDriver staticdriver;

	/**
	 * In this annotation we open the database
	 * open the property file and 
	 * open the excel
	 * @throws IOException 
	 * @throws EncryptedDocumentException 
	 */
	@BeforeSuite(groups = "baseclass")
	public void beforeSuiteTest() throws EncryptedDocumentException, IOException {
		//code to open the excel
		
	}

	/**
	 * In this annotation we fetch the common data from the property file , 
	 * create the instance for all the generic utility,
	 * launch browser ,
	 * do the browser settings , 
	 * create the instance for common object 
	 * @throws Throwable
	 */
	//@Parameters("browser") //and pass String browser in beforeClassTest() as argument
	@BeforeClass(groups = "baseclass")
	public void beforeClassTest() throws Throwable {
		//create instance for all generic  
		fileLibrary=new FileLibrary();
		excelLibrary = new ExcelLibrary(); 
		fileLibrary.openPropertyFile(IconstantPath.PROPERTYFILEPATH);
		excelLibrary.openExcel(IconstantPath.EXCELPATH);
		System.out.println("Before Suite = open excel and property file");
		JavaLibrary javaLibrary= new JavaLibrary();

	//	String url = fileLibrary.getDataFromPropertyFile("url");
	//	username= fileLibrary.getDataFromPropertyFile("username");
	//	password= fileLibrary.getDataFromPropertyFile("password");
	//  String browser = fileLibrary.getDataFromPropertyFile("browser");

		username=System.getProperty("USERNAME");
		password=System.getProperty("PASSWORD");
		url=System.getProperty("URL");
		browser=System.getProperty("BROWSER");

		String timeout = fileLibrary.getDataFromPropertyFile("timeout");

		randomNumber= javaLibrary.getRandomNumber(1000);
		longTimeOut= Long.parseLong(timeout);

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
		staticdriver=driver;
		webDriverLibrary= new WebDriverLibrary();

		//Browser setting(maximize browser , implicit wait , action class initialization , explicit wait instance initialization)
		webDriverLibrary.browserSetting(10, driver);	
		webDriverLibrary.initializActions(driver);
		webDriverLibrary.explicitlyWait(driver, longTimeOut);

		//Create instance for all common object repository class
		loginpage = new LoginPage(driver);
		homepage = new HomePage(driver);

		//Navigate to the application
		webDriverLibrary.navigateApp(url, driver);
	}

	/**
	 *  In this annotation we will do login action
	 */
	@BeforeMethod(groups = "baseclass")
	public void beforeMethod1Test() {
		loginpage.loginAction(username, password);
	}

	/**
	 *  In this annotation we will do logout action 
	 */
	@AfterMethod(groups = "baseclass")
	public void afterMethod() {
		homepage.signout(driver, webDriverLibrary);
	}

	/**
	 *  In this annotation we will close the browser instance 
	 */
	@AfterClass(groups = "baseclass")
	public void afterClassTest() {
		webDriverLibrary.quitBrowser(driver);
	}

	/**
	 *  In this annotation close the database
	 *  close the excel sheet
	 * @throws IOException 
	 */
	@AfterSuite(groups = "baseclass")
	public void afterSuiteTest() throws IOException {
		//close the data base connection code
		//close the excel sheet code
		excelLibrary = new ExcelLibrary(); 
		excelLibrary.closeExcel();
		System.out.println("after suit: close excel statements");
	}
}
