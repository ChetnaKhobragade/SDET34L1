package com.sdet34l1.genericLibrary;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This method is used to perform the methods from WebDriver interface 
 * @author Chetna k
 *
 */
public class WebDriverLibrary {

	static WebDriver driver;
	static WebDriverWait wait;
	public Actions action;
	static Select select ;
	static JavascriptExecutor js;
	
	/**
	 * This method is used to navigate to the application
	 * @param url
	 * @param driver
	 */
	public void navigateApp(String url,WebDriver driver) {
		driver.get(url);
	}

	/**
	 * This method is used to maximize the browser and implicitly wait 
	 * @param longTimeOut
	 * @param driver
	 */
	public void  browserSetting(long longTimeOut,WebDriver driver) {
		maximizeBrower(driver);
		waitTillPageLoad(longTimeOut,driver);
	}

	/**
	 * This method is used to maximize the browser window
	 * @param driver
	 */
	public void maximizeBrower(WebDriver driver) {
		driver.manage().window().maximize();
	}

	/**
	 * This method is used to implicitly wait until the page is loaded 
	 * @param longTimeOut
	 * @param driver
	 */
	public void waitTillPageLoad(long longTimeOut,WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(longTimeOut, TimeUnit.SECONDS);
	}

	/**
	 * This method is used to initialize the actions class
	 * @param driver
	 */
	public void initializActions(WebDriver driver) {
		action = new Actions(driver);

	}

	/**
	 * This method is used to mouse hover on the element
	 * @param moreLink
	 */
	public void mouseHoverOnElement(WebElement moreLink){
		action.moveToElement(moreLink).perform();
	}

	/**
	 * This method is used to double click on the WebElement
	 * @param moreLink
	 * @param driver
	 */
	public void doubleClick(WebElement moreLink,WebDriver driver){
		action.doubleClick(moreLink).perform();
	}

	/**
	 * This method is used to double click on the element
	 */
	public void doubleClick(){
		action.doubleClick().perform();
	}

	/**
	 * This method is used to right click on the element
	 * @param moreLink
	 * @param driver
	 */
	public void rightClickOnElement(WebElement moreLink,WebDriver driver){
		action.contextClick(moreLink).perform();
	}

	/**
	 * This method is used to drag and drop a element
	 * @param moreLink
	 * @param driver
	 */
	public void dragAndDropElement(WebElement position1,WebElement position2,WebDriver driver){
		action.dragAndDrop(position1,position2).perform();
	}

	/**
	 * This method is used to select a option the drop down
	 * @param dropDown
	 * @param value
	 * @param index
	 * @param visibleText
	 */
	public void SelectdropDown(WebElement dropDown,String value ){
		select = new Select(dropDown);
		select.selectByValue(value);
	}

	/**
	 * This method is used to select a option the drop down
	 * @param dropDown
	 * @param value
	 * @param index
	 * @param visibleText
	 */
	public void SelectdropDown(WebElement dropDown,int index ){
		select = new Select(dropDown);
		select.selectByIndex(index);
	}

	/**
	 * This method is used to select a option the drop down
	 * @param dropDown
	 * @param value
	 * @param index
	 * @param visibleText
	 */
	public void SelectdropDown(String value, WebElement dropDown ){
		select = new Select(dropDown);
		select.selectByVisibleText(value);
	}


	/**
	 * This method is used to switch to frame based on the index
	 * @param driver
	 * @param index
	 */
	public void switchToFrame(WebDriver driver,int index){
		driver.switchTo().frame(index);
	}
	/**
	 * This method is used to switch to frame based on name or id
	 * @param driver
	 * @param nameOrId
	 */
	public void switchToFrame(WebDriver driver,String nameOrId){
		driver.switchTo().frame(nameOrId);
	}
	
	/**
	 * This method is used to switch to frame based on element
	 * @param driver
	 * @param element
	 */
	public void switchToFrame(WebDriver driver,WebElement element){
		driver.switchTo().frame(element);
	}
	
	/**
	 * This method is used to switch back to default content
	 * @param driver
	 */
	public void switchBackToFrame(WebDriver driver){
		driver.switchTo().defaultContent();
	}

	/**
	 * This method is used to close all the open windows
	 * @param driver
	 */
	public void quitBrowser(WebDriver driver) {
		driver.quit();
	}

	/**
	 *  This method is used to wait the control till the particular element is visible
	 * @param element
	 */
	public void waitUntillElementClickable(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * This method is used to initialize wait instance
	 * @param driver
	 * @param timeOut
	 */
	public void explicitlyWait(WebDriver driver, long timeOut) {
		wait = new WebDriverWait(driver,timeOut);
	}

	/**
	 * This method is used to switch the window based on title
	 * @param driver
	 * @param particularText
	 */
	public static void switchToWindowBasedOnTitle(WebDriver driver,String particularText) {
		Set<String> sessionIDs= driver.getWindowHandles();
		for (String id : sessionIDs) {
			driver.switchTo().window(id);
			if (driver.getTitle().contains(particularText)) {
				break;
			}
		}
	}

	/**
	 * This method is used to switch the window to child window
	 * @param driver
	 */
	public static void switchToWindow(WebDriver driver) {
		Set<String> child= driver.getWindowHandles();
		for (String b : child) {
			driver.switchTo().window(b);
		}
	}

	/**
	 * This method is used to switch to parent window
	 * @param driver
	 * @param element
	 */
	public static void switchToParentWindow(WebDriver driver,String element) {
		driver.switchTo().window(element);
	}

	/**
	 * This method is used to initialize the javaScriptExecutor
	 * @param driver
	 */
	public static void initiallizeJs(WebDriver driver) {
		js=(JavascriptExecutor) driver;
	}

	/**
	 * This method is used to enter the data in text box through javascriptExecutor
	 * @param element
	 * @param data
	 */
	public static void enterDataThroughJs(WebElement element, String data) {
		js.executeScript("argument[0].value=argument[1]",element, data);	
	}

	/**
	 * This method is used to click on the element using JavaScriptExecutor
	 * @param element
	 */
	public static void clickThroughJs(WebElement element) {
		js.executeAsyncScript("argument[0].click()",element);
	}

	/**
	 * This method is used to  navigate to all the application using JavascriptExecutor
	 * @param url
	 */
	public static void navigatToAllAppThroughJs(String url) {
		js.executeScript("window.location=argument[0]",url);
	}

	/**
	 * This method is used to scroll to the certain height on the page 
	 * @param height
	 */
	public static void scrollToSpecificHeight(String height) {
		js.executeScript("window.scrollBy(0,"+height+")");
	}

	/**
	 * This method is used to scroll to the bottom of the page 
	 */
	public static void scrollToBottom() {
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	/**
	 * This method is used to scroll till a specified element
	 * @param element
	 */
	public static void scrollTillElement(WebElement element) {
		js.executeScript("argument[0].scrollIntoView()",element);
	}

	/**
	 * This method is used to take screenshot 
	 * @param filePath
	 * @param driver
	 * @return 
	 * @throws IOException
	 */
	public String takeScreenshot(String fileName,WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dst = new File("./screenshot/"+fileName+"_.png");
		FileUtils.copyFile(src, dst);
        return dst.getAbsolutePath();
		
	}

	/**
	 * This method is used to click on ok for alert pop up 
	 * @param driver
	 */
	public static void alertAccept(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	/**
	 * This method is used to click on cancel button for alert pop up
	 * @param driver
	 */
	public void alertDismiss(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	/**
	 *  This is 
	 * @param driver
	 * @param data
	 */
	public void alertDismiss(WebDriver driver,String data) {
		driver.switchTo().alert().sendKeys(data);
	}

	/**
	 * This method is used to read the text from the alert pop up
	 * @param driver
	 * @return
	 */
	public String alertSendData(WebDriver driver) {
		return driver.switchTo().alert().getText();
	}
}
