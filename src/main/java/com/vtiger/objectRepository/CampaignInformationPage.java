package com.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CampaignInformationPage {
	//Declaration of all the elements and specify the access specifier as private
	@FindBy( id = "dtlview_Campaign Name")
	private WebElement campaignNameText;

	//initialize the driver address to all the elements through constructors and make it as public 
	public CampaignInformationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);	
	}

	//Business Library 
	public String getCampaignNameText() {
		return campaignNameText.getText();
	}
	
	
}
