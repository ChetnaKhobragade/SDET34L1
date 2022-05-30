package com.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CampaignPage {

	//Declaration of all the elements and specify the access specifier as private
	@FindBy( xpath = "//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement createCampaign;

	//initialize the driver address to all the elements through constructors and make it as public 
	public CampaignPage(WebDriver driver) {
		PageFactory.initElements(driver, this);	
	}

	//Business Library 
	public void clickCampaignPlusbtn() {
		createCampaign.click();
	}
}
