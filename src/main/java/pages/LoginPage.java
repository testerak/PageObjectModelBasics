package pages;

import org.openqa.selenium.By;


import base.Page;
import base.TestBaseReplacedbyPagesREFONLY;

public class LoginPage extends Page {

	
	public ZohoAppPage doLogin(String username, String password) throws InterruptedException {
				//ajayjindle@myself.com pwd randomzoh1
		
		driver.findElement(By.cssSelector("#login_id")).sendKeys(username);;		
		//type("email_CSS",username);
		Thread.sleep(3000);	
		//*[@id='nextbtn'] #nextbtn
		//driver.findElement(By.xpath("//*[@id='nextbtn']")).click();
				click("nexbtn_XPATH");
				//Thread.sleep(3000);
		driver.findElement(By.cssSelector("#password")).sendKeys(password);;		
		
				//type("password_CSS",password);
				driver.findElement(By.cssSelector("#nextbtn > span")).click();
				//click("signinbtn_CSS"); #nextbtn > span;//*[@id="nextbtn"]/span
				return new ZohoAppPage();
		
	}
}
