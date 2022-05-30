package com.vtiger.contactsTest;

import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;

import com.sdet34l1.genericLibrary.BaseClass;
import com.sdet34l1.genericLibrary.JavaLibrary;
import com.sdet34l1.genericLibrary.WebDriverLibrary;
import com.vtiger.objectRepository.ContactInformationPage;
import com.vtiger.objectRepository.ContactPage;
import com.vtiger.objectRepository.CreateNewContactPage;
import com.vtiger.objectRepository.CreateNewOrganizationPage;
import com.vtiger.objectRepository.OrganizationInformationPage;
import com.vtiger.objectRepository.OrganizationPage;
import com.vtiger.objectRepository.SearchOrganizationPage;

public class CreateContactWithOrganizationTest extends BaseClass {

	@Test(groups = "regression")

	public void createContactWithOrganizationTest() throws EncryptedDocumentException, IOException {
		JavaLibrary javaLibrary = new JavaLibrary();
		String organizationName =excelLibrary.getDataFromExcel("contact", 4, 1) + randomNumber;
		String contactLastName =excelLibrary.getDataFromExcel("contact", 4, 2) + randomNumber;

		OrganizationPage op = new OrganizationPage(driver);
		CreateNewOrganizationPage createoragatizationpage = new CreateNewOrganizationPage(driver);
		ContactPage contactpage = new ContactPage(driver);
		CreateNewContactPage createcontactpage = new CreateNewContactPage(driver);
		ContactInformationPage contactinfopage = new ContactInformationPage(driver);
		SearchOrganizationPage searchOrganizationPage = new SearchOrganizationPage(driver);
		OrganizationInformationPage organizationInformationPage= new OrganizationInformationPage(driver);
		
		homepage.clickOrganization();
		op.createNewOraganization();
		createoragatizationpage.enterOranizationName(organizationName);
		createoragatizationpage.save();
		organizationInformationPage.waitUntilVisible(driver,webDriverLibrary);
		
		homepage.clickContacts();
		contactpage.createNewContact();
		createcontactpage.enterLastName(contactLastName);	
		createcontactpage.addOrganizationOption();
		
		searchOrganizationPage.selectOranization(organizationName, driver);
		WebDriverLibrary.switchToWindowBasedOnTitle(driver, "Contact");
		
		createcontactpage.saveContact();
		
		javaLibrary.assertionThroughIfCondition(contactinfopage.getContactName(), contactLastName, "contacts");
	}
}
