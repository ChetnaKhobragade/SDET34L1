package com.vtiger.practise;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class TestNgAssertTest {
	@Test //(retryAnalyzer = com.sdet34l1.genericLibrary.RetryAnalyzerImplementation.class)
	public void practice1Test() {
		Reporter.log("a-practice",true);

		Reporter.log("b-practice", true);

		Reporter.log("c-practice", true);

		Reporter.log("d-practice", true);

		Reporter.log("e-practice", true);
	}
	
	@Test
	public void practice2Test() {
		Reporter.log("a-practice",true);

		Reporter.log("b-practice", true);

		Assert.fail();
		Reporter.log("c-practice", true);

		Reporter.log("d-practice", true);

		Reporter.log("e-practice", true);
	}
	
	@Test
	public void practice3Test() {
		Reporter.log("a-practice",true);

		Reporter.log("b-practice", true);

		Reporter.log("c-practice", true);

		Reporter.log("d-practice", true);

		Reporter.log("e-practice", true);
	}
	
	@Test
	public void practice4Test() {
		Reporter.log("a-practice",true);

		Reporter.log("b-practice", true);

		Reporter.log("c-practice", true);

		Reporter.log("d-practice", true);

		Reporter.log("e-practice", true);
	}
}
