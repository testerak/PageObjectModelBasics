package crmAccount;

import org.openqa.selenium.By;

import base.Page;
import base.TestBaseReplacedbyPagesREFONLY;

public class CRMAccountsLandingPage extends Page {
	

	public void verifyTextCRMAccountsLandingPage() {
		
	}

	public CreateAccountPage goToCreateAccountsPage() {
		// create account -css -- #submenu_Accounts > button
				//driver.findElement(By.cssSelector("#submenu_Accounts > button")).click();
				click("accountstab_CSS");
				return new CreateAccountPage();
				
	}

	public void goToImportAccountsPage() {

	}

	public void goToDisableModulePage() {

	}
}
