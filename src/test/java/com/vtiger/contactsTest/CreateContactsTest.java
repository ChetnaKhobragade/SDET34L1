package com.vtiger.contactsTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;

import com.sdet34l1.genericLibrary.BaseClass;
import com.sdet34l1.genericLibrary.JavaLibrary;
import com.vtiger.objectRepository.ContactInformationPage;
import com.vtiger.objectRepository.ContactPage;
import com.vtiger.objectRepository.CreateNewContactPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

public class CreateContactsTest extends BaseClass {

	@Test(groups = "sanity", description = "testng:- CreateContactTest")
	@Description("Description:- CreateContactsTest ")
	@Epic("Epic:- e1 ")
	@Story("Story:- s1")
	@Step("Step:- s1")
	@Severity(SeverityLevel.BLOCKER)
	public void createContactsTest() throws EncryptedDocumentException, IOException {
		JavaLibrary javaLibrary = new JavaLibrary();
		String contactLastName = excelLibrary.getDataFromExcel("contact", 2, 1 ) + randomNumber;	
        
		ContactPage contactpage = new ContactPage(driver);
		CreateNewContactPage createcontactpage= new CreateNewContactPage(driver);
		ContactInformationPage contactinfopage = new ContactInformationPage(driver);

		homepage.clickContacts();
		contactpage.createNewContact();
		createcontactpage.enterLastName(contactLastName);
		createcontactpage.saveContact();

		javaLibrary.assertionThroughIfCondition(contactinfopage.getContactName(), contactLastName, "contacts");
	}
}
