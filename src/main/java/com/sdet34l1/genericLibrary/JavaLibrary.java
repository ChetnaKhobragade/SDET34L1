package com.sdet34l1.genericLibrary;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.openqa.selenium.WebElement;

/**
 * This class contains only java specific reusable methods 
 * @author Chetna K
 *
 */
public class JavaLibrary {

		/**
		 * This method is used to convert String value to long data type
		 * @param value
		 * @return
		 */
		public long stringToLong(String value) {
			return Long.parseLong(value);
		}
		
		/**
		 * This method is used to generate random number 
		 * @param limit
		 * @return
		 */
		public int getRandomNumber(int limit) {
			Random ran = new Random();
			int randomNumber = ran.nextInt(limit);
			return randomNumber;
		}
		
		
		/**
		 * This method is used to wait 
		 * @param element
		 * @param pollingTime
		 * @param duration
		 * @throws InterruptedException
		 */
		public void customeWait(WebElement element,long pollingTime , int duration) throws InterruptedException{
			int count = 0;
			while(count <= duration) {
				try {
				element.click();
				break;
				}
				catch(Exception e) {
					Thread.sleep(pollingTime);
					count++;
				}
			}
		}
		
		/**
		 * This method is used to print the message
		 * @param message
		 */
		public void printStatement(String message) {
			System.out.println(message);
		}
		
		/**
		 * This method is used to validate the test case by comaring two strings
		 * @param actualResult
		 * @param expectedResult
		 * @param testCaseName
		 */
		public void assertionThroughIfCondition(String actualResult,String expectedResult,String testCaseName) {
			if (actualResult.equalsIgnoreCase(expectedResult)) {
				System.out.println(testCaseName+ " created successfully");
				System.out.println("TC pass");
			}
		}
		
		/**
		 * This method is used to get the date in the format
		 * @return
		 */
		public String getDateTimeInFormat() {
			return  new SimpleDateFormat("yyyy_MM_dd_HH_mm_ssss").format(new Date());
		}

}
