package testcases;

import java.util.Hashtable;

import org.testng.annotations.Test;

import base.Page;
import crm.CRMHomePage;
import crmAccount.CRMAccountsLandingPage;
import crmAccount.CreateAccountPage;
import pages.ZohoAppPage;
import utilities.TestUtils;

public class CreateAccountTest {

	@Test(dataProviderClass=TestUtils.class,dataProvider="dp1")
	public void createAccountTest(Hashtable<String,String>data) throws InterruptedException {
		ZohoAppPage zp =new ZohoAppPage();
		CRMHomePage cp= zp.goToCRM();
		//to use in test case example as its declared as static
		CRMAccountsLandingPage accLandPage =Page.menu.goToAccounts();
		//Page.menu.signOut();
		CreateAccountPage caPage = accLandPage.goToCreateAccountsPage();
		caPage.CreateAccount(data.get("accountname"));
		System.out.println("All Done !!!!");
	}
}
