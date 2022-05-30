package com.vtiger.practise;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.sdet34l1.genericLibrary.ExcelLibrary;
import com.sdet34l1.genericLibrary.FileLibrary;
import com.sdet34l1.genericLibrary.JavaLibrary;
import com.sdet34l1.genericLibrary.WebDriverLibrary;
import com.vtiger.objectRepository.CreateNewProductPage;
import com.vtiger.objectRepository.HomePage;
import com.vtiger.objectRepository.LoginPage;
import com.vtiger.objectRepository.ProductInformationPage;
import com.vtiger.objectRepository.ProductPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateProductTestBeforeTestNG {

	public static void main(String[] args) throws Throwable {
		WebDriver driver= null;

		//FileLibraries.openPropertyFile(IconstantPath.PROPERTYFILEPATH);
		//ExcelLibraries.openExcel(IconstantPath.EXCELFILEPATH);
		
		String url = FileLibrary.getDataFromPropertyFile("url");
		String username =  FileLibrary.getDataFromPropertyFile("username");
		String password =  FileLibrary.getDataFromPropertyFile("password");
		String timeout =  FileLibrary.getDataFromPropertyFile("timeout");
		String browser = FileLibrary.getDataFromPropertyFile("browser");
		
		long longTimeOut = JavaLibrary.stringToLong(timeout);

		int randomNumber = JavaLibrary.getRandomNumber(1000);
		
		String productName = ExcelLibrary.getDataFromExcel("product", 2, 1) + randomNumber;
		
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
		
		WebDriverLibrary.navigateApp(url, driver);
		WebDriverLibrary.browserSetting(longTimeOut, driver);
		WebDriverLibrary.initializActions(driver);
		
		LoginPage lp = new LoginPage(driver);
		HomePage hp = new HomePage(driver);
		ProductPage productpage = new ProductPage(driver);
		CreateNewProductPage createproductpage = new CreateNewProductPage(driver);
		ProductInformationPage productinfopage = new ProductInformationPage(driver);
		
		lp.loginAction(username, password);
		hp.clickProduct();
		productpage.createNewProduct();
		createproductpage.enterProductNameandSave(productName);
		
		JavaLibrary.assertionThroughIfCondition(productinfopage.getProductName(), productName, "products");
	//	WebElement actualProductName = driver.findElement(By.id("dtlview_Product Name"));
	
		
	//	WebDriverLibraries.mouseHoverOnElement(hp.seeMoreOptions(driver));
		
		hp.signout(driver);
		
	//	ExcelLibraries.flushDataExcelFile(IconstantPath.EXCELFILEPATH);
	//	ExcelLibraries.closeExcel();
		WebDriverLibrary.quitBrowser(driver);	


		
//		
//		//--Step1 : Login to application : Home Page should display
//		//driver.findElement(By.name("user_name")).sendKeys(username);
//		//driver.findElement(By.name("user_password")).sendKeys(password);
//		//driver.findElement(By.id("submitButton")).click();
//		//if (driver.getTitle().contains("Home")) {
//		//	ExcelLibraries.writeDataOnExcelFile("product", 8, 4, "Home page diaplayed");
//		//	ExcelLibraries.writeDataOnExcelFile("product", 8, 5, "pass");
//		//}
//		
//		//--Step2 : Click on Products : Products page should display
//		driver.findElement(By.linkText("Products")).click();
//		String title = driver.findElement(By.linkText("Products")).getText();
//		if (title.contains("Products")) {
//			ExcelLibraries.writeDataOnExcelFile("product", 9, 4, "Products page diaplayed");
//			ExcelLibraries.writeDataOnExcelFile("product", 9, 5, "Pass");
//		}
//		
//		//--Step3 : Click on Create product : Create product page should display
//		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
//		String title2 = driver.findElement(By.xpath("//span[text()='Creating New Product']")).getText();
//		if (title2.contains("Creating New Product")) {
//			ExcelLibraries.writeDataOnExcelFile("product", 10, 4, "Create product page diaplayed");
//			ExcelLibraries.writeDataOnExcelFile("product", 10, 5, "Pass");
//		}
//
//		//--Step4 : Enter mandatory fields : Product information page should display
//		driver.findElement(By.name("productname")).sendKeys(productName);
//		driver.findElement(By.name("button")).click();
//		
//		//--Step 5: Validate the Product name : Data should be created as expected
//		WebElement actualProductName = driver.findElement(By.id("dtlview_Product Name"));
//		if(actualProductName.getText().contains(productName))
//		{
//			ExcelLibraries.writeDataOnExcelFile("product", 11, 4, "Data is created as expected");
//			ExcelLibraries.writeDataOnExcelFile("product", 11, 5, "Pass");
//			ExcelLibraries.writeDataOnExcelFile("product", 2, 3, "Pass");
//			JavaLibraries.printStatement("Tc pass");
//		}

//		//--Step 6: Click on Sign-out : Login page should display
//		WebElement administratorIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
//		WebDriverLibraries.mouseHoverOnElement(administratorIcon);	
//		driver.findElement(By.linkText("Sign Out")).click();
//		if(driver.getCurrentUrl().contains("Login"))
//		{
//			ExcelLibraries.writeDataOnExcelFile("product", 13, 4, "Login page diaplayed");
//			ExcelLibraries.writeDataOnExcelFile("product", 13, 5, "pass");
//		}

		}
}
