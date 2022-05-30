package com.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductInformationPage {
	 //driver.findElement(By.id("dtlview_Product Name"));
	@FindBy (id = "dtlview_Product Name" )
	private WebElement productNameText;
	
	public ProductInformationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public String getProductName() {
		return productNameText.getText();
	}
}
