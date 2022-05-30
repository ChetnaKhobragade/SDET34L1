package com.vtiger.objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sdet34l1.genericLibrary.WebDriverLibrary;

public class SearchOrganizationPage {

	@FindBy(xpath = "//input[@name='search_text']")
	private WebElement searchTxt;

	@FindBy(xpath = "//input[@class='crmbutton small create']")
	private WebElement searchBtn;
	
	public SearchOrganizationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void selectOranization(String organizationName, WebDriver driver) {
		WebDriverLibrary.switchToWindowBasedOnTitle(driver, "Organization");
		searchTxt.sendKeys(organizationName);
		searchBtn.click();
		driver.findElement(By.xpath("//a[text()='"+organizationName+"']")).click();
	}
}
