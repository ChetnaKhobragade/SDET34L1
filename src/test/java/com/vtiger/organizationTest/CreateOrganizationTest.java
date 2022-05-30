package com.vtiger.organizationTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;

import com.sdet34l1.genericLibrary.BaseClass;
import com.sdet34l1.genericLibrary.JavaLibrary;
import com.vtiger.objectRepository.CreateNewOrganizationPage;
import com.vtiger.objectRepository.OrganizationInformationPage;
import com.vtiger.objectRepository.OrganizationPage;

public class CreateOrganizationTest extends BaseClass {

	@Test(groups = "sanity")
	public void createOrganizationTest() throws EncryptedDocumentException, IOException {
		JavaLibrary javaLibrary = new JavaLibrary();

		String organizationName = excelLibrary.getDataFromExcel("organization", 2, 1) + randomNumber;

		OrganizationPage op = new OrganizationPage(driver);
		CreateNewOrganizationPage createorganizationpage = new CreateNewOrganizationPage(driver);
		OrganizationInformationPage orginfopage = new OrganizationInformationPage(driver);
		
		homepage.clickOrganization();
		op.createNewOraganization();
		createorganizationpage.enterOranizationName(organizationName);
		createorganizationpage.save();
		
		javaLibrary.assertionThroughIfCondition(orginfopage.getOraganizationName(), organizationName, "organizations");
		}
}