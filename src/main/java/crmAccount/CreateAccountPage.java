package crmAccount;

import org.openqa.selenium.By;

import base.Page;
import base.TestBaseReplacedbyPagesREFONLY;

public class CreateAccountPage extends Page {
	// create account
	public void CreateAccount(String accountname) throws InterruptedException {
	//driver.findElement(By.cssSelector("#Crm_Accounts_ACCOUNTNAME")).sendKeys(accountname);
	type("createaccountbtn_CSS",accountname);
	Thread.sleep(3000);
	
	//driver.findElement(By.cssSelector("#cancelAccountsBtn")).click();
	click("cancelacctbtn_CSS");
	
	//#cancelAccountsBtn
	//#saveAccountsBtn
	//#saveAndNewAccountsBtn
	}
	
	//import
	//#accountscontainer > div > div > div:nth-child(1) > div > div > link-to:nth-child(2) > lyte-button > button
	//disable
	//#accountscontainer > div > div > div:nth-child(2) > div > link-to > a > lyte-button > button
	
}
