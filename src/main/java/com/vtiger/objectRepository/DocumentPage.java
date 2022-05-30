package com.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DocumentPage {
	//Declaration of all the elements and specify the access specifier as private
	@FindBy( xpath = "//img[@title='Create Document...']")
	private WebElement createDocument;

	//initialize the driver address to all the elements through constructors and make it as public 
	public DocumentPage(WebDriver driver) {
		PageFactory.initElements(driver, this);	
	}

	//Business Library 
	public void clickDocumentPlusbtn(WebDriver driver) {
		createDocument.click();
	}
}
