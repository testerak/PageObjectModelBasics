package pages;

import org.openqa.selenium.By;
import base.Page;
import base.TestBaseReplacedbyPagesREFONLY;
import crm.CRMHomePage;

public class ZohoAppPage extends Page {
	
	
	public void goToBooks() {
		//._logo-books._logo-x96.zod-app-logo
		driver.findElement(By.cssSelector("._logo-books._logo-x96.zod-app-logo")).click();
	}
	public void goToCalendar() {
		//._logo-calendar._logo-x96.zod-app-logo
		driver.findElement(By.cssSelector("._logo-calendar._logo-x96.zod-app-logo")).click();
	}
	public CRMHomePage goToCRM() {
		//._logo-crm._logo-x96.zod-app-logo
		//driver.findElement(By.cssSelector("._logo-crm._logo-x96.zod-app-logo")).click();
		click("crmlink_CSS");
		return new CRMHomePage();
		
	}
	public void goToCliq() {
		
		//._logo-chat._logo-x96.zod-app-logo
		driver.findElement(By.cssSelector("._logo-chat._logo-x96.zod-app-logo")).click();
	}
	public void goToCampaigns() {
		//._logo-campaigns._logo-x96.zod-app-logo
		driver.findElement(By.cssSelector("._logo-campaigns._logo-x96.zod-app-logo")).click();
	}
	public void goToConnect() {
		//._logo-connect._logo-x96.zod-app-logo
		driver.findElement(By.cssSelector("._logo-connect._logo-x96.zod-app-logo")).click();
	}
	public void goToDesk() {
		//._logo-support._logo-x96.zod-app-logo
		driver.findElement(By.cssSelector("._logo-support._logo-x96.zod-app-logo")).click();	
	}
	public void goToInvoice() {
		//._logo-invoice._logo-x96.zod-app-logo
		driver.findElement(By.cssSelector("._logo-invoice._logo-x96.zod-app-logo")).click();
	}
	
}
