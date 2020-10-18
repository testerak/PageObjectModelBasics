package rough;

import org.openqa.selenium.By;

import base.Page;
import crm.CRMHomePage;
import crmAccount.CRMAccountsLandingPage;
import crmAccount.CreateAccountPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ZohoAppPage;

public class LoginTest {


		
/*To  avoid creating object of class in testcases for every page 
 * being navigated to as we have been doing using 

*HomePage home = new HomePage();
*LoginPage login = new LoginPage(); 
*ZohoAppPage zp = new ZohoAppPage();
*CRMAccountsLandingPage accLandPage = new CRMAccountsLandingPage();
*CreateAccountPage caPage = new CreateAccountPage();
*WE HAVE MADE METHOD CALLING THE NEXT PAGE RESPONISBLE FOR CREATING 
*OBJECT IN THERE EXAMPLE 
*HOMEPAGE HAS METHOD goToLogin which CALLS LoginPage 
*SO WE PUT LoginPage AS RETURN METHOD IN goToLogin method as 
*EXAMPLE public LoginPage goToLogin() {
		driver.findElement(By.cssSelector(".zh-login")).click();
		return new LoginPage();
}
*NOW WE ONLY NEED TO CREATE OBJECT OF HOMEPAGE AND ASSIGN 
*
*LoginPage login = home.goToLogin(); AND FOLLOW EXECUTION FLOW see below testcase
*
*/	
		public static void main(String[] args) throws InterruptedException {
		HomePage home = new HomePage();
		LoginPage login=home.goToLogin();
		ZohoAppPage zp =login.doLogin("ajayjindle@myself.com", "randomzoh1");
		CRMHomePage cp= zp.goToCRM();
		//to use in test case example as its declared as static
		CRMAccountsLandingPage accLandPage =Page.menu.goToAccounts();
		//Page.menu.signOut();
		CreateAccountPage caPage = accLandPage.goToCreateAccountsPage();
		caPage.CreateAccount("Ajay Jindle");
		System.out.println("All Done Without failures !!!!");
	}

}
