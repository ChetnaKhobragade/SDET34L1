package com.vtiger.practise;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.sdet34l1.genericLibrary.ExcelLibrary;
import com.sdet34l1.genericLibrary.FileLibrary;
import com.sdet34l1.genericLibrary.IconstantPath;
import com.sdet34l1.genericLibrary.JavaLibrary;
import com.sdet34l1.genericLibrary.WebDriverLibrary;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateDocumentTest {

	public static void main(String[] args) throws Throwable {
		WebDriver driver= null;

		//FileLibraries.openPropertyFile(IconstantPath.PROPERTYFILEPATH);
		//ExcelLibraries.openExcel(IconstantPath.EXCELFILEPATH);
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
		
		String documentName = excelLibrary.getDataFromExcel("document", 2, 1) + randomNumber;
		String discription = excelLibrary.getDataFromExcel("document", 2, 2);

		switch (browser) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver= new FirefoxDriver();
			break;

		default:
			System.out.println("Specify a valid browser"); 

			WebDriverManager.firefoxdriver().setup();
			driver= new FirefoxDriver();
			break;
		}
		
		WebDriverLibrary.navigateApp(url, driver);
		WebDriverLibrary.browserSetting(longTimeOut, driver);
		WebDriverLibrary.initializActions(driver);

		//--Step1 : Login to app : Home Page should display
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		if (driver.getTitle().contains("Home")) {
			ExcelLibrary.setDataInExcel("document", 8, 4, "Home page diaplayed");
			ExcelLibrary.setDataInExcel("document", 8, 5, "pass");
		}

		//--Step2 : Click on Document : Documents page should display
		driver.findElement(By.linkText("Documents")).click();
		String title = driver.findElement(By.linkText("Documents")).getText();
		if (title.contains("Documents")) {
			ExcelLibrary.setDataInExcel("document", 9, 4, "Organizations page diaplayed");
			ExcelLibrary.setDataInExcel("document", 9, 5, "Pass");
		}

		//--Step3 : Click on Create organization : Create organization page should display
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		String title2 = driver.findElement(By.xpath("//span[text()='Creating New Document']")).getText();
		if (title2.contains("Creating New Document")) {
			ExcelLibrary.setDataInExcel("document", 10, 4, "Create document page diaplayed");
			ExcelLibrary.setDataInExcel("document", 10, 5, "Pass");
		}
		
		//--Step4 : Enter mandatory fields : Campaign information page should display
		driver.findElement(By.name("notes_title")).sendKeys(documentName);

		driver.switchTo().frame(0);
		
		WebElement noteTextBox = driver.findElement(By.xpath("//body[@class='cke_show_borders']"));
		noteTextBox.sendKeys(discription);
		noteTextBox.sendKeys(Keys.CONTROL+"a");

		driver.switchTo().defaultContent();
		
		driver.findElement(By.xpath("(//span[@class='cke_icon'])[1]")).click();
		driver.findElement(By.xpath("(//span[@class='cke_icon'])[2]")).click();

		driver.findElement(By.id("filename_I__")).sendKeys("F:\\Q Spiders\\Syllabus fees rec\\ADVANCE AUTOMAION PACKAGE.docx.pdf");
		driver.findElement(By.name("button")).click();
		
		//--Step 5: Validate the Campaign name : Data should be created as expected
	    WebElement actualDocumentName = driver.findElement(By.id("dtlview_Title"));
		if (actualDocumentName.getText().contains(documentName))
		{
			ExcelLibrary.setDataInExcel("document", 1, 4, "pass");
			ExcelLibrary.setDataInExcel("document", 11, 4, "Data is created as expected");
			ExcelLibrary.setDataInExcel("document", 11, 5, "pass");
			JavaLibrary.printStatement("The document is created.");
			JavaLibrary.printStatement("Tc pass");
		}
	

		WebElement administratorIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions a = new Actions(driver);
		a.moveToElement(administratorIcon).perform();
		
		//--Step 6: Click on Sign-out : Login page should display
		driver.findElement(By.linkText("Sign Out")).click();
		if(driver.getCurrentUrl().contains("Login"))
		{
			ExcelLibrary.setDataInExcel("document", 13, 4, "Login page diaplayed");
			ExcelLibrary.setDataInExcel("document", 13, 5, "pass");
		}


		//ExcelLibraries.flushDataExcelFile(IconstantPath.EXCELFILEPATH);
		//ExcelLibraries.closeExcel();
		WebDriverLibrary.quitBrowser(driver);
	}
}
