package com.vtiger.practise;

import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestNGPracticeTest extends TestNGBasicConfigAnnotaionPracticeTest {
	@Parameters
	@Test
	public void practiceTest() {
		Reporter.log("Test1 ", true);
	}
	
	@Test
	public void practiceTest2() {
		Reporter.log("Test2 ", true);
	}
	
	@Test
	public void practiceTest3() {
		Reporter.log("Test3 ", true);
	}
	


}
