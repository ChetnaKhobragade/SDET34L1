package com.vtiger.objectRepository;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewDocumentPage {	 

	//Declaration of all the elements and specify the access specifier as private
	@FindBy( name = "notes_title")
	private WebElement noteTxt;
	
	@FindBy( name = "button")
	private WebElement saveBtn;

	@FindBy( xpath = "//body[@class='cke_show_borders']")
	private WebElement noteTxtBox;
	
	@FindBy( xpath = "(//span[@class='cke_icon'])[1]")
	private WebElement boldBtn;

	@FindBy( xpath = "(//span[@class='cke_icon'])[2]")
	private WebElement italicsBtn;

	@FindBy( id = "filename_I__")
	private WebElement fileUpload;
	
	//initialize the driver address to all the elements through constructors and make it as public 
	public CreateNewDocumentPage(WebDriver driver) {
		PageFactory.initElements(driver, this);	
	}

	//Business Library 
	public void enterDocumentTitleName(WebDriver driver,String documentName) {
		noteTxt.sendKeys(documentName);
	}
	
	public void enterDocumentDiscriptionAndSelectAll(WebDriver driver,String discription) {
		noteTxtBox.sendKeys(discription);
		noteTxtBox.sendKeys(Keys.CONTROL+"a");
	}

	public void save(WebDriver driver) {
		saveBtn.click();
	}
	
	public void bold(WebDriver driver) {
		boldBtn.click();
	}
	public void italics(WebDriver driver) {
		italicsBtn.click();
	}
	
	public void uploadDocument(WebDriver driver) {
		fileUpload.sendKeys("F:\\Q Spiders\\Syllabus fees rec\\ADVANCE AUTOMAION PACKAGE.docx.pdf");
	}
}
