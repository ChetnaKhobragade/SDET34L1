package com.vtiger.campaignTest;

import org.testng.annotations.Test;

import com.sdet34l1.genericLibrary.BaseClass;
import com.sdet34l1.genericLibrary.JavaLibrary;
import com.vtiger.objectRepository.CampaignInformationPage;
import com.vtiger.objectRepository.CampaignPage;
import com.vtiger.objectRepository.CreateNewCampaignPage;

public class CreateCampaignTest extends BaseClass {
	
	@Test(groups = "sanity")
	public void createCampaignTest() throws Throwable {
		JavaLibrary javaLibrary = new JavaLibrary();
		String campaignName = excelLibrary.getDataFromExcel("Campaign", 2, 1) + randomNumber;
		
		CampaignPage campaignpage = new CampaignPage(driver);
		CreateNewCampaignPage createNewCampaignPage = new CreateNewCampaignPage(driver);
		CampaignInformationPage campaignInformationPage = new CampaignInformationPage(driver);

		homepage.clickCampaign(webDriverLibrary);
		campaignpage.clickCampaignPlusbtn();
		createNewCampaignPage.enterCampaignName(campaignName);
		createNewCampaignPage.save();
						
		javaLibrary.assertionThroughIfCondition(campaignInformationPage.getCampaignNameText(), campaignName, "campaign");
		}
}
