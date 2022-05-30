package com.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sdet34l1.genericLibrary.WebDriverLibrary;

public class CreateNewOrganizationPage {

	@FindBy (name ="accountname")
	private WebElement organizationNameTxt;
	
	@FindBy (name ="industry")
	private WebElement industryDropdown;
	
	@FindBy (name ="accounttype")
	private WebElement typeDropdownElement;
	
	@FindBy (name ="button")
	private WebElement saveBtn;
	
	public CreateNewOrganizationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//business library
	public void enterOranizationName(String organizationName) {
		organizationNameTxt.sendKeys(organizationName);
	}
	
	public void selectIndustryDropdown(String value,WebDriverLibrary webDriverLibrary) {
		webDriverLibrary.SelectdropDown(industryDropdown, value);
	}
	
	public void selectTypeDropdown(String value,WebDriverLibrary webDriverLibrary) {
		webDriverLibrary.SelectdropDown(typeDropdownElement, value);
	}
	
	public void save() {
		saveBtn.click();
	}
}
