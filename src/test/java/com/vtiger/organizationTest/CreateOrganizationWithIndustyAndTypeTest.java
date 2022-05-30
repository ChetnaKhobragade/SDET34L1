package com.vtiger.organizationTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;

import com.sdet34l1.genericLibrary.BaseClass;
import com.sdet34l1.genericLibrary.JavaLibrary;
import com.vtiger.objectRepository.CreateNewOrganizationPage;
import com.vtiger.objectRepository.OrganizationInformationPage;
import com.vtiger.objectRepository.OrganizationPage;

public class CreateOrganizationWithIndustyAndTypeTest extends BaseClass{

	@Test(groups = "regression")
	public void createOrganizationWithIndustyAndTypeTest() throws EncryptedDocumentException, IOException {
		JavaLibrary javaLibrary = new JavaLibrary();

		String organizationName = excelLibrary.getDataFromExcel("organization", 2, 1) + randomNumber;

		OrganizationPage organizationPage = new OrganizationPage(driver);
		CreateNewOrganizationPage createorganizationpage = new CreateNewOrganizationPage(driver);
		OrganizationInformationPage orginfo = new OrganizationInformationPage(driver);
		
		homepage.clickOrganization();
		organizationPage.createNewOraganization();
		createorganizationpage.enterOranizationName(organizationName);
		createorganizationpage.selectIndustryDropdown("Education",webDriverLibrary);
		createorganizationpage.selectTypeDropdown("Press",webDriverLibrary);
		createorganizationpage.save();
		
		javaLibrary.assertionThroughIfCondition(orginfo.getOraganizationName(), organizationName, "organization");
	}
}
