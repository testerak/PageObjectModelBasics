package rough;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

// TEST CALLING PROPERTIES FILE TO 
// SHOW TO CALL AND USE - SEE IMPLEMENTION IN BASETEST CLASS
public class TestProperties {

	public static void main(String[] args) throws IOException {
		System.out.println(System.getProperty("user.dir"));
		Properties config = new Properties();// java.util
		Properties OR = new Properties();// java.util
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
		config.load(fis);
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
		OR.load(fis);
		System.out.println(config.getProperty("browser"));
		System.out.println(OR.getProperty("bmlBtn_CSS"));
		
		// now on run time WE can type to get and use property element when using webdriver
		//driver.findElement(By.cssSelector(OR.getProperty("bmlBtn_CSS"))).click();
	}

}
