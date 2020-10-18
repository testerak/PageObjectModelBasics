package rough;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class UPloadingAnImage {
	public static WebDriver driver;

	public static void main(String[] args) throws IOException, InterruptedException {
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.defalut_content_setting_values.notifications", 2);
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		options.addArguments("--disable-extensions");
		options.addArguments("--disable-infobars");
		options.addArguments("--disable-notifications");

		System.setProperty("webdriver.chrome.driver",
				"C:\\work\\Selenium\\PageObjectModel\\src\\test\\resources\\executables\\chromedriver.exe");
		driver = new ChromeDriver(options);
		driver.get("https://www.facebook.com");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		System.out.println("FACEBOOK LOADED AND PAGE MAXIMIZED");
		// LOGIN
		driver.findElement(By.xpath("//*[@id = 'email']")).sendKeys("mmeramail2@yahoo.co.uk");
		driver.findElement(By.xpath("//*[@id='pass']")).sendKeys("Rand0m11");
		driver.findElement(By.xpath("//*[@id='loginbutton']")).click();
		System.out.println("USER LOGGED IN");
		// GOING TO CLICK PROFILE NAME
		driver.findElement(By.xpath("//*[@class='l9j0dhe7 stjgntxs ni8dbmo4'][text()='Ajay Jindle']")).click();
		System.out.println("CLICKED PROFILE NAME");
		// GOING TO UPDATE PROFILE PICTUIRE PAGE
		driver.findElement(By.xpath("//*[@aria-label='Profile picture actions'][@role='img']")).click();
		driver.findElement(By.xpath(
				"//div[@class='oajrlxb2 g5ia77u1 qu0x051f esr5mh6w e9989ue4 r7d6kgcz rq0escxv nhd2j8a9 j83agx80 p7hjln8o kvgmc6g5 oi9244e8 oygrvhab h676nmdw cxgpxx05 dflh9lhu sj5x9vvc scb9dxdr i1ao9s8h esuyzwwr f1sip0of lzcic4wl l9j0dhe7 abiwlrkh p8dawk7l bp9cbjyn dwo3fsh8 btwxx1t3 pfnyh3mw du4w35lb']/div[2]"))
				.click();
		System.out.println("ON UPDATE PROFILE PICTURE PAGE");
		// GOING TO LOAD IMAGE
		driver.findElement(By.xpath("//*[@id='mount_0_0']/div/div/div[1]/div[4]/div/div/div[1]/div/div[2]/div/div/div/div[3]/div[1]/div[1]")).click();
		Thread.sleep(2000);
		Runtime.getRuntime().exec("C:\\Users\\user1\\FileUpload.exe");
		System.out.println("SELECTED PROFILE IMAGE IS NOW LOADED");
		// GOING TO SAVE IMAGE
		driver.findElement(By.xpath("//div[@class='kkf49tns']")).click();
		System.out.println("UPLOADED PROFILE IMAGE IS NOW SAVED");
	}


}
