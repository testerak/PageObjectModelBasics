package base;
import org.openqa.selenium.By;
/*Accounts page has a top menu
 * homepage has atop menu
 * so we can say where ever there is a hasa relationship encapsulation takes place
 * REMEMBER ISA RELATIONSHIP INHERTANCE IS IMPLEMENTED
 * 
 * NOW AS TOP MENU IS NOT A PAGE WE CAN NOT EXTEND PAGE CLASS HERE 
 * TO CALL SAME DRIVER REFERENCE WHAT WE DO IS EITHER 
 * USE PAGE.DRIVER.FINDELEMENT -- THIS WILL WORK OR 
 * SIMPLE DECLARE WEBDRIVER INSTANCE AND CREATE CONSTRUCTOR OF TOPMENU 
 * AS BELOW BOTH WILL WORK
 * */
import org.openqa.selenium.WebDriver;

import crmAccount.CRMAccountsLandingPage;



public class TopMenu {
	
	WebDriver driver;  //THIS IS GLOBAL DRIVER FROM PAGE CLASS
	
	public TopMenu(WebDriver driver) {
		this.driver=driver;
		
	}
	public void goToHome() {
		//ajayjindle@myself.com pwd randomzoh1
		driver.findElement(By.cssSelector("#mainMenuTabDiv > crm-menu > div.crmMenuLeft.fL > crm-tab > div.lyteMenuItems > div:nth-child(1) > a")).click();
	
	}
	
	public void goToLeads() {
		driver.findElement(By.cssSelector("#mainMenuTabDiv > crm-menu > div.crmMenuLeft.fL > crm-tab > div.lyteMenuItems > div:nth-child(2) > a")).click();
		//#mainMenuTabDiv > crm-menu > div.crmMenuLeft.fL > crm-tab > div.lyteMenuItems > div:nth-child(2) > a
	}
	
	public void goToContacts() {
		driver.findElement(By.cssSelector("#mainMenuTabDiv > crm-menu > div.crmMenuLeft.fL > crm-tab > div.lyteMenuItems > div:nth-child(3) > a")).click();
		//#mainMenuTabDiv > crm-menu > div.crmMenuLeft.fL > crm-tab > div.lyteMenuItems > div:nth-child(3) > a
	}
	public CRMAccountsLandingPage goToAccounts() {
		//ajayjindle@myself.com pwd randomzoh1
		//.lyteItem.lyteMenuActive>a
		driver.findElement(By.cssSelector("#mainMenuTabDiv > crm-menu > div.crmMenuLeft.fL > crm-tab > div.lyteMenuItems > div:nth-child(4) > a")).click();
		return new CRMAccountsLandingPage();
	}
	public void goToDeals() {
		//ajayjindle@myself.com pwd randomzoh1
		driver.findElement(By.cssSelector("#mainMenuTabDiv > crm-menu > div.crmMenuLeft.fL > crm-tab > div.lyteMenuItems > div:nth-child(5) > a")).click();
		//#mainMenuTabDiv > crm-menu > div.crmMenuLeft.fL > crm-tab > div.lyteMenuItems > div:nth-child(5) > a
	
	}
	
	public void signOut() {
		//ajayjindle@myself.com pwd randomzoh1
		
	}

}
