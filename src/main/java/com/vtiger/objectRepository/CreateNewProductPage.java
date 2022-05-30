package com.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewProductPage {
	
	@FindBy (name ="productname")
	private WebElement productNameTxt;

	@FindBy (name ="button")
	private WebElement saveBtn;
	
	//Initialize
	public CreateNewProductPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//business library
	public void enterProductNameandSave(String productName) {
		productNameTxt.sendKeys(productName);
		saveBtn.click();
	}
}
