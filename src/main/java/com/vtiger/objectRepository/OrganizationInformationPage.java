package com.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sdet34l1.genericLibrary.WebDriverLibrary;

public class OrganizationInformationPage {
	
	@FindBy( id = "dtlview_Organization Name")
	private WebElement organizationNameText;

	//initialize the driver address to all the elements through constructors and make it as public 
	public OrganizationInformationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);	
	}

	//Business Library 
	public String getOraganizationName() {
		return organizationNameText.getText();
	}
	public WebElement oraganizationNameElement() {
		return organizationNameText;
	}
	public void waitUntilVisible(WebDriver driver,WebDriverLibrary webDriverLibrary) {
		webDriverLibrary.explicitlyWait(driver, 10);
		webDriverLibrary.waitUntillElementClickable(organizationNameText);
	}
}
