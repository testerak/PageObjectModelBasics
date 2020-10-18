package pages;

import org.openqa.selenium.By;


import base.Page;
import base.TestBaseReplacedbyPagesREFONLY;


public class HomePage extends Page{

	
	
	// add methods say like for all functions on homepage example zoho.com has 
		public void goToSignUp() {
		//ajayjindle@myself.com pwd randomzoh1
		driver.findElement(By.cssSelector(".zh-signup")).click();
	}
		
	public LoginPage goToLogin() {
		//driver.findElement(By.cssSelector(".zh-login")).click();
		click("loginlink_CSS");
		return new LoginPage();
		
		
	}
	
	public void goToSupport() {
		//nth-child(2) means second child for first 
		//we could right first-child()
		driver.findElement(By.cssSelector(".siging>a:nth-child(2)")).click();
		
	}
	
	public void goToCustomers() {}
	
	public void goToContactSales() {}
	
	public void goToLearnMore() {}
	
	public void validateFooterLinks() {}
	
	
}
