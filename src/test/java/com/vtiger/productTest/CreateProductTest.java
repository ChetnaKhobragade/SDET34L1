package com.vtiger.productTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;

import com.sdet34l1.genericLibrary.BaseClass;
import com.sdet34l1.genericLibrary.JavaLibrary;
import com.vtiger.objectRepository.CreateNewProductPage;
import com.vtiger.objectRepository.ProductInformationPage;
import com.vtiger.objectRepository.ProductPage;

public class CreateProductTest extends BaseClass {

	@Test (groups = "sanity")
	public void createProductTest() throws EncryptedDocumentException, IOException {
		JavaLibrary javaLibrary = new JavaLibrary();

		String productName = excelLibrary.getDataFromExcel("product", 2, 1) + randomNumber;

		ProductPage productpage = new ProductPage(driver);
		CreateNewProductPage createproductpage = new CreateNewProductPage(driver);
		ProductInformationPage productinfopage = new ProductInformationPage(driver);
		
		homepage.clickProduct();
		productpage.createNewProduct();
		createproductpage.enterProductNameandSave(productName);
		
		javaLibrary.assertionThroughIfCondition(productinfopage.getProductName(), productName, "products");
	}
}
