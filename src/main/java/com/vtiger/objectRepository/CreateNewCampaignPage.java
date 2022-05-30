package com.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewCampaignPage {
	//Declaration of all the elements and specify the access specifier as private
	@FindBy( name = "campaignname")
	private WebElement campaignNameTxt;

	@FindBy( xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;


	@FindBy (xpath ="//img[@src='themes/softed/images/select.gif']")
	private WebElement addProductPlus;
	
	//initialize the driver address to all the elements through constructors and make it as public 
	public CreateNewCampaignPage(WebDriver driver) {
		PageFactory.initElements(driver, this);	
	}

	//Business Library 
	public void enterCampaignName(String campaignName) {
		campaignNameTxt.sendKeys(campaignName);
	}

	public void addProductOption() {
		addProductPlus.click();
	}
	
	public void save() {
		saveBtn.click();
	}

}
