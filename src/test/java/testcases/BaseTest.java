package testcases;

import org.testng.annotations.AfterSuite;

import base.Page;

public class BaseTest extends Page{

	@AfterSuite
	public void tearDown() {
		// Close all process like browser, db every thing in this method we want to
		// run this after all test cases are run hence @Aftersuite

		Page.quit();
		
	}
}
