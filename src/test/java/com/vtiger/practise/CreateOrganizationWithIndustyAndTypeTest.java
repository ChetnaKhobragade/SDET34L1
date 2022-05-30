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

public class CreateOrganizationWithIndustyAndTypeTest {

	public static void main(String[] args) throws Throwable {
		WebDriver driver=null;

	//	FileLibrary.openPropertyFile(IconstantPath.PROPERTYFILEPATH);
	//	ExcelLibraries.openExcel(IconstantPath.EXCELFILEPATH);

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
		
		//--Step1 : Login to app : Home Page should display
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		if (driver.getTitle().contains("Home")) {
			ExcelLibrary.setDataInExcel("organization", 17, 4, "Home page diaplayed");
			ExcelLibrary.setDataInExcel("organization", 17, 5, "pass");
		}

		//--Step2 : Click on organization : Organizations page should display
		driver.findElement(By.linkText("Organizations")).click();
		String title = driver.findElement(By.linkText("Organizations")).getText();
		if (title.contains("Organizations")) {
			ExcelLibrary.setDataInExcel("organization", 18, 4, "Organizations page diaplayed");
			ExcelLibrary.setDataInExcel("organization", 18, 5, "pass");
		}

		//--Step3 : Click on Create organization : Create organizations page should display
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		String title2 = driver.findElement(By.xpath("//span[text()='Creating New Organization']")).getText();
		if (title2.contains("Creating New Organization")) {
			ExcelLibrary.setDataInExcel("organization", 19, 4, "Create organizations page diaplayed");
			ExcelLibrary.setDataInExcel("organization", 19, 5, "pass");
		}

		//--Step4 : Enter mandatory fields :Organization information page should display 
		driver.findElement(By.name("accountname")).sendKeys(organizationName);

		WebElement industryDropdown = driver.findElement(By.name("industry"));
		WebDriverLibrary.SelectdropDown(industryDropdown, "Education");		
		WebElement typeDropdown = driver.findElement(By.name("accounttype"));
		WebDriverLibrary.SelectdropDown(typeDropdown, "Press");

		//Click on save button
		driver.findElement(By.name("button")).click();

		//--Step 5: Validate the organization name : Organization name should be displayed in newly created organization page
		WebElement actualOrganizationName = driver.findElement(By.id("dtlview_Organization Name"));
		JavaLibrary.assertionThroughIfCondition(actualOrganizationName.getText(), organizationName, "organization");

		//--Step 6: Click on Sign-out : login page should display
		WebElement administratorIcon =driver.findElement(By.xpath("(//img[@style='padding: 0px;padding-left:5px'])[1]"));
		WebDriverLibrary.mouseHoverOnElement(administratorIcon);
		driver.findElement(By.linkText("Sign Out")).click();
		if(driver.getCurrentUrl().contains("Login"))
		{
			ExcelLibrary.setDataInExcel("organization", 22, 4, "Login page diaplayed");
			ExcelLibrary.setDataInExcel("organization", 22, 5, "pass");
		}

		//ExcelLibraries.flushDataExcelFile(IconstantPath.EXCELFILEPATH);
		//ExcelLibraries.closeExcel();
		WebDriverLibrary.quitBrowser(driver);	
	}
}
