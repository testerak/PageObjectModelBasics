package testcases;

import java.util.Hashtable;

import org.testng.annotations.Test;

import crm.CRMHomePage;
import pages.HomePage;
import pages.LoginPage;
import pages.ZohoAppPage;
import utilities.TestUtils;

public class LoginTest extends BaseTest{

	@Test(dataProviderClass=TestUtils.class,dataProvider="dp1")
	public void loginTest(Hashtable<String,String>data) throws InterruptedException {
		HomePage home = new HomePage();
		
		LoginPage login=home.goToLogin();
		
		login.doLogin(data.get("username"), data.get("password"));
		System.out.println("Hopefully going to next testAll Done !!!!");
	}
}
