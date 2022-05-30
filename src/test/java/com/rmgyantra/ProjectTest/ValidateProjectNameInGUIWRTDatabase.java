package com.rmgyantra.ProjectTest;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.sdet34l1.genericLibrary.DatabaseLibrary;
import com.sdet34l1.genericLibrary.ExcelLibrary;
import com.sdet34l1.genericLibrary.FileLibrary;
import com.sdet34l1.genericLibrary.IconstantPath;
import com.sdet34l1.genericLibrary.JavaLibrary;
import com.sdet34l1.genericLibrary.WebDriverLibrary;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ValidateProjectNameInGUIWRTDatabase {

	public static void main(String[] args) throws Throwable {
		//FileLibrary.openPropertyFile(IconstantPath.RMGYANTRAPROPERTYFILEPATH);
		
		ExcelLibrary excelLibrary= new ExcelLibrary();
		FileLibrary fileLibrary= new FileLibrary();
		JavaLibrary javaLibrary= new JavaLibrary();
		WebDriverLibrary webDriverLibrary = new WebDriverLibrary();
		
		excelLibrary.openExcel(IconstantPath.RMGYANTRA_EXCELPATH);
		int randomNumber = javaLibrary.getRandomNumber(1000);

		String projectName = excelLibrary.getDataFromExcel("projects", 1, 1) +randomNumber;

		javaLibrary.printStatement(projectName);

		DatabaseLibrary.openDBConnection(com.sdet34l1.genericLibrary.IconstantPath.DATABASEPATH+fileLibrary.getDataFromPropertyFile("dBName"),fileLibrary.getDataFromPropertyFile("dbUserName"),fileLibrary.getDataFromPropertyFile("dbPassWord"));
		DatabaseLibrary.setDataInDataBase("insert into project values('TY_PRO_"+javaLibrary.getRandomNumber(1000)+"','chetna','20/04/2022','"+projectName+"','On Going',13);");

		WebDriverManager.firefoxdriver().setup();
		WebDriver driver= new FirefoxDriver();

		webDriverLibrary.navigateApp("http://localhost:8084/", driver);
		webDriverLibrary.browserSetting(10, driver);
		
		//enter user-name,password and click sign-in
		driver.findElement(By.id("usernmae")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();
		//enter all fields
		driver.findElement(By.linkText("Projects")).click();

		List<WebElement> list = driver.findElements(By.xpath("//table/tbody/tr/td[2]"));

		for(WebElement project:list)
		{
			if(project.getText().equalsIgnoreCase(projectName))
			{
				System.out.println("pass");
			}

		}
		
		DatabaseLibrary.closeDBConnection();
		webDriverLibrary.quitBrowser(driver);
}
}
