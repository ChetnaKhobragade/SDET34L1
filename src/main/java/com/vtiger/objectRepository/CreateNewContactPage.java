package com.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewContactPage {
	//Declaration of all the elements and specify the access specifier as private
	@FindBy (name ="lastname")
	private WebElement contactLastNameTxt;

	@FindBy (name ="button")
	private WebElement saveBtn;
	

	@FindBy (xpath ="//img[@src='themes/softed/images/select.gif']")
	private WebElement addOrganizationPlus;
	
	//Initialize
	public CreateNewContactPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//business library
	public void enterLastName(String lastName) {
		contactLastNameTxt.sendKeys(lastName);
	}	
	public void addOrganizationOption() {
		addOrganizationPlus.click();
	}
	public void saveContact() {
		saveBtn.click();
	}	
}
