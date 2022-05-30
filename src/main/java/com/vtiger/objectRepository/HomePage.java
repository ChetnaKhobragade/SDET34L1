package com.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sdet34l1.genericLibrary.WebDriverLibrary;


public class HomePage {

	//Declaration of all the elements and specify the access specifier as private
	@FindBy( linkText = "More")
	private WebElement moreDropDown;
	
	@FindBy( linkText = "Organizations")
	private WebElement organizationsTab;
	
	//@FindBy( xpath = "//td[@class='tabUnSelected']/../td/descendant::a[4]")
	//private WebElement contactsTab;

	@FindBy( linkText = "Contacts" )
	private WebElement contactsTab;
	
	@FindBy( name = "Campaigns")
	private WebElement campaignsTab;
	
	@FindBy( linkText = "Products")
	private WebElement productsTab;
	
	@FindBy( linkText = "Documents")
	private WebElement documentsTab;

	@FindBy( xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement administratorIcon;
	
	@FindBy( linkText = "Sign Out")
	private WebElement signOutLink;

	@FindBy( linkText = "More")
	private WebElement moreDropDownR;
	
	//initialize the driver address to all the elements through constructors and make it as public 
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);	
	}
	
	//Business Library 
	public void clickContacts() {
		
		contactsTab.click();
	}
	public WebElement elementContacts() {
		return contactsTab;
	}

	public void clickOrganization() {
		organizationsTab.click();
	}
	
	public void clickCampaign(WebDriverLibrary webDriverLibrary) {
		webDriverLibrary.waitUntillElementClickable(moreDropDown);
		webDriverLibrary.mouseHoverOnElement(moreDropDown);
		campaignsTab.click();
	}
	
	public void clickProduct() {
		productsTab.click();
	}																																				
	public void clickDocument() {
		documentsTab.click();
	}
	
	public void signout(WebDriver driver,WebDriverLibrary webDriverLibrary) {
		webDriverLibrary.mouseHoverOnElement(administratorIcon);
		signOutLink.click();
	}
	
	public WebElement seeMoreOptions(WebDriver driver,WebDriverLibrary webDriverLibrary) {
		webDriverLibrary.mouseHoverOnElement(administratorIcon);
		return moreDropDownR;
	}	
}
