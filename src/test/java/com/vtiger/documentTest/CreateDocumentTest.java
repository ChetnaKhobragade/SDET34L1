package com.vtiger.documentTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;

import com.sdet34l1.genericLibrary.BaseClass;
import com.sdet34l1.genericLibrary.JavaLibrary;
import com.sdet34l1.genericLibrary.WebDriverLibrary;
import com.vtiger.objectRepository.CreateNewDocumentPage;
import com.vtiger.objectRepository.DocumentInformationPage;
import com.vtiger.objectRepository.DocumentPage;

public class CreateDocumentTest extends BaseClass {
	@Test (groups = {"sanity","regression"})
	public void createDocumentTest() throws EncryptedDocumentException, IOException {		
		JavaLibrary javaLibrary = new JavaLibrary();

		String documentName = excelLibrary.getDataFromExcel("document", 2, 1) + randomNumber;
		String discription = excelLibrary.getDataFromExcel("document", 2, 2);

		DocumentPage documentpage= new DocumentPage(driver);
		DocumentInformationPage docinfopage = new DocumentInformationPage(driver);
		CreateNewDocumentPage createdocumentpage = new CreateNewDocumentPage(driver);
		 
		homepage.clickDocument();
		documentpage.clickDocumentPlusbtn(driver);
		createdocumentpage.enterDocumentTitleName(driver, documentName);
		webDriverLibrary.switchToFrame(driver, 0);
		createdocumentpage.enterDocumentDiscriptionAndSelectAll(driver, discription);
		webDriverLibrary.switchBackToFrame(driver);
		
		createdocumentpage.bold(driver);
		createdocumentpage.italics(driver);
		createdocumentpage.uploadDocument(driver);
		createdocumentpage.save(driver);
		
		javaLibrary.assertionThroughIfCondition(docinfopage.documentName().getText(), documentName, "Documents");
	}
}
