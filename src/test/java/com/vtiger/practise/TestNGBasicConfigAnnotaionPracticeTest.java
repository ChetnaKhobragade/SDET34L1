package com.vtiger.practise;

import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class TestNGBasicConfigAnnotaionPracticeTest {
	//TestNGPracticeTest
	
	@BeforeSuite(groups = "baseclass" )
	public void beforeSuiteTest() {
		Reporter.log("beforesuite 1", true);
	}
	
	@AfterSuite(groups = "baseclass" )
	public void afterSuiteTest() {
		Reporter.log("aftersuite 1", true);
	}
	@Parameters("browser")
	@BeforeClass(groups = "baseclass" )
	public void beforeClassTest(String browser) {
		Reporter.log("beforeclass 1", true);
	}
	@AfterClass(groups = "baseclass" )
	public void afterClassTest() {
		Reporter.log("afterclass 1", true);
	}
	
	@BeforeTest(groups = "baseclass" )
	public void beforeTest() {
		Reporter.log("beforetest 1", true);
	}
	@AfterTest(groups = "baseclass" )
	public void afterTest() {
		Reporter.log("after test", true);
	}
	@BeforeMethod(groups = "baseclass" )
	public void beforeMethod2() {
		Reporter.log("beforeMethod 2", true);
	}
	@BeforeMethod(groups = "baseclass" )
	public void beforeMethod1() {
		Reporter.log("beforeMethod 1", true);
	}
	@AfterMethod(groups = "baseclass" )
	public void afterMethod() {
		Reporter.log("afterMethod 1", true);
	}
	
}
