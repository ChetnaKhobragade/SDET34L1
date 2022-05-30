package com.vtiger.campaignTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;

import com.sdet34l1.genericLibrary.BaseClass;
import com.sdet34l1.genericLibrary.JavaLibrary;
import com.sdet34l1.genericLibrary.WebDriverLibrary;
import com.vtiger.objectRepository.CampaignInformationPage;
import com.vtiger.objectRepository.CampaignPage;
import com.vtiger.objectRepository.CreateNewCampaignPage;
import com.vtiger.objectRepository.CreateNewProductPage;
import com.vtiger.objectRepository.ProductPage;
import com.vtiger.objectRepository.SearchProductsPage;

public class CreateCampaignWithProductTest extends BaseClass { 

	@Test(groups = "regression")
	public void createCampaignWithProductTest() throws EncryptedDocumentException, IOException {
		JavaLibrary javaLibrary = new JavaLibrary();

		String campaignName = excelLibrary.getDataFromExcel("Campaign", 4, 1) + randomNumber;	
		String productName= excelLibrary.getDataFromExcel("Campaign", 4, 2) + randomNumber;	

		ProductPage productpage= new ProductPage(driver);
		CampaignPage campaignpage = new CampaignPage(driver);
		CreateNewProductPage createnewproduct = new CreateNewProductPage(driver);
		CreateNewCampaignPage createnewcampaign = new CreateNewCampaignPage(driver);
		SearchProductsPage searchproductpage= new SearchProductsPage(driver);
		CampaignInformationPage campaigninfopage = new CampaignInformationPage(driver);

		homepage.clickProduct();
		productpage.createNewProduct();
		createnewproduct.enterProductNameandSave(productName);

		homepage.clickCampaign(webDriverLibrary);
		campaignpage.clickCampaignPlusbtn();
		createnewcampaign.enterCampaignName( campaignName);
		createnewcampaign.addProductOption();
		searchproductpage.selectProduct(productName, driver);
		WebDriverLibrary.switchToWindowBasedOnTitle(driver, "Campaign");
		createnewcampaign.save();

		javaLibrary.assertionThroughIfCondition(campaigninfopage.getCampaignNameText(), campaignName, "campaign");
	}
}
