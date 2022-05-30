package com.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DocumentInformationPage {

	//driver.findElement(By.id("dtlview_Title"));
	@FindBy ( id = "dtlview_Title")
	private WebElement documentNameText;
	
	public DocumentInformationPage(	WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public WebElement documentName() {
		return documentNameText;
	}
}
