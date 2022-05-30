package com.sdet34l1.genericLibrary;

import org.openqa.selenium.WebDriver;
import com.vtiger.objectRepository.HomePage;
import com.vtiger.objectRepository.LoginPage;

public class GettersAndSettersForListener {
	public WebDriver driver;
	public long longTimeOut;
	public LoginPage loginpage;
	public HomePage homepage;
	public String password;
	public String username;
	public String browser;
	public int randomNumber;
	public ExcelLibrary excelLibrary ;
	public FileLibrary fileLibrary ;
	public JavaLibrary javalibrary;
	public WebDriverLibrary webDriverLibrary;
	
	public WebDriver getDriver() {
		return driver;
	}
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	public long getLongTimeOut() {
		return longTimeOut;
	}
	public void setLongTimeOut(long longTimeOut) {
		this.longTimeOut = longTimeOut;
	}
	public LoginPage getLoginpage() {
		return loginpage;
	}
	public void setLoginpage(LoginPage loginpage) {
		this.loginpage = loginpage;
	}
	public HomePage getHomepage() {
		return homepage;
	}
	public void setHomepage(HomePage homepage) {
		this.homepage = homepage;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public int getRandomNumber() {
		return randomNumber;
	}
	public void setRandomNumber(int randomNumber) {
		this.randomNumber = randomNumber;
	}
	public ExcelLibrary getExcelLibrary() {
		return excelLibrary;
	}
	public void setExcelLibrary(ExcelLibrary excelLibrary) {
		this.excelLibrary = excelLibrary;
	}
	public FileLibrary getFileLibrary() {
		return fileLibrary;
	}
	public void setFileLibrary(FileLibrary fileLibrary) {
		this.fileLibrary = fileLibrary;
	}
	public JavaLibrary getJavalibrary() {
		return javalibrary;
	}
	public void setJavalibrary(JavaLibrary javalibrary) {
		this.javalibrary = javalibrary;
	}
	public WebDriverLibrary getWebDriverLibrary() {
		return webDriverLibrary;
	}
	public void setWebDriverLibrary(WebDriverLibrary webDriverLibrary) {
		this.webDriverLibrary = webDriverLibrary;
	}
	}
