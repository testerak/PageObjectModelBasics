package base; //or testcore

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

//import com.mysql.cj.util.TestUtils;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import listeners.ExtentManager;
//import testcase.TestConfig;
import utilities.DbManager;
import utilities.ExcelReader;
import utilities.MonitoringMail;
import utilities.TestUtils;
/*COVERS LECTURES IN SECTION 30 LIVE PROJECTS
 * excel - done Initialised
 * Logs - done log4j 1.2.17 jar log files application and selenium and log4j.properties requiredrequired Initialised
 * Properties - done Initialised
 * TestNG - done
 * JavaMail - done Implement
 * ReportNG -done
 * DataBase Initialised TODO
 * WebDriver - done Initialised
 * Explicit and Implicit Wait -done
 * KeyWords -done
 * Screenshots -done
 * Maven as Build Tool
 * Jenkins -TODO
 * //use control+plus to increase font size CTRL+MINUS
 * ZIP
 * LISTENERS - SOFT ASSERTIONS, TEST FAILURES
 * MAVEN,GRADLE
 * EXTENT REPORTS Initialised  we get instance of extentmanager 
 * RUNMODES
 * EXTENT REPORTS
 * */
public class TestBaseReplacedbyPagesREFONLY {
//THIS IS OUR SUPER TEST CLASS AND EVERYTHING WE DO IS INITALISED IN THIS CLASS  use ctrl+space to import

	// INITIALISE all that we can from above list others will get added later
	public static WebDriver driver;
	public static Properties OR = new Properties();
	public static Properties Config = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger("devpinoyLogger");// devpinoyLogger is standard name for logger
																	// Logger.getLogger(BaseTest.class);
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "\\src\\test\\resources\\data\\testdata.xlsx");
	public static MonitoringMail mail = new MonitoringMail();
	
	public static WebDriverWait wait;
	public static WebElement dropdown;
	public ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;
	public static String browser;
	public static TopMenu menu;
	// setup every thing in this method that we want to
	// run before all test cases are run hence @Beforesuite

	@BeforeSuite
	public void setUp() {
		 //PropertyConfigurator.configure(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\log4j.xml");

		// 1 LOAD THE Config.PROPERTIES AND OR.PROPERTIES FILES WITHIN TRY CATCH
		if (driver == null) {
			// NOTE: if using log4j.xml then DOM configurator is required otherwise it is
			// PropertyConfigurator
			DOMConfigurator.configure(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\log4j.xml");

			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				Config.load(fis);
				log.debug("Config file loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//
			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				OR.load(fis);
				log.info("OR file loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 2 IF BROWSER IS TO BE SELECTED BY SPECIFIED ENVIRONMENT EXAMPLE JENKINS
			// FILTER RATHER THAN FROM CONFIG.PROPERTIES FILE THAN code below is used
			if (System.getenv("browser") != null && !System.getenv("browser").isEmpty()) {

				browser = System.getenv("browser") ;
				
				
			}else {
				
				browser=Config.getProperty("browser");
			}
			Config.setProperty("browser", browser);

			// 2a SELECT AND CONFIGURE WHICH BROWSER IS OPENED FOR TESTS

			if (Config.getProperty("browser").equals("firefox")) {
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\geckodriver.exe");
				driver = new FirefoxDriver();
				log.info("Firefox browser launched");
			}

			else if (Config.getProperty("browser").equals("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("profile.default_content_setting_values.notifications", 2);
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("prefs", prefs);
				options.addArguments("--disable-extensions");

				options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
				driver = new ChromeDriver(options);
				log.info("Chrome browser launched");
			} else if (Config.getProperty("browser").equals("ie")) {
				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\IEDriverServer.exe");

				DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
				capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
				driver = new InternetExplorerDriver(capabilities);
				log.info("IE browser launched");
			} else if (Config.getProperty("browser").equals("edge")) {
				System.setProperty("webdriver.edge.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\msedgedriver.exe");
				driver = new EdgeDriver();
				log.info("Edge browser launched");
			}
			
			
			// 3 NAVIGATE TO URL
			driver.get(Config.getProperty("testurl"));
			//driver.get("https://www.zoho.com");
			
			log.info("Navigated to :" + Config.getProperty("testurl"));
			driver.manage().window().maximize();

			// NEED TO CONVERT TO INTEGER AS IMPLICIT WAIT IS EXPECTING A NUMERICAL VALUE
			// HENCE ADDING Integer.parseInt
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(Config.getProperty("implicit.wait")),
					TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, Integer.parseInt(Config.getProperty("explicit.wait")));
			
			// create object here for top menu
			menu = new TopMenu(driver);
			
			
			
			
			
			// 4 SET UP DB CONNECTION
			try {
				DbManager.setMysqlDbConnection();
				log.info("Connected to MySQL DataBase");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//may not need this wait = new WebDriverWait(driver, Integer.parseInt(Config.getProperty("explicit.wait")));
		}

	}

	// 3 KEYWORD METHODS ADDED BELOW
	//NOTE CODE if (key.endsWith("_XPATH")) MEANS IF LOCATOR ENDS WITH ETC.

	// 1KEYWORD CLICK METHOD
	public static void click(String key) {

		if (key.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(key))).click();
			
		
		} else if (key.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(key))).click();
				
			
		
		} else if (key.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(key))).click();
			
		}

		test.log(LogStatus.INFO, "Clicking on:  "+key);

	}

	// 2 KEYWORD TYPE METHOD
	public static void type(String key, String value) {

		if (key.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(key))).sendKeys(value);
			
		} else if (key.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(key))).sendKeys(value);
			
		} else if (key.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(key))).sendKeys(value);
			
		}

		test.log(LogStatus.INFO, "Typing in:  "+key +" Entering Value as : "+value);
		
		
	}

	// 3 KEYWORD SELECT METHOD ALSO NEEDS PUBLIC STATIC WEBELEMENT DROPDOWN
	public static void select(String key, String value) {

		if (key.endsWith("_XPATH")) {
			dropdown = driver.findElement(By.xpath(OR.getProperty(key)));

		} else if (key.endsWith("_CSS")) {
			dropdown = driver.findElement(By.cssSelector(OR.getProperty(key)));

		} else if (key.endsWith("_ID")) {
			dropdown = driver.findElement(By.id(OR.getProperty(key)));

		}

		Select select = new Select(dropdown);
		select.selectByVisibleText(value);
		log.info("Clicking on an element : " + key + " and entered the value as : " + value);
		test.log(LogStatus.INFO, "Selecting from dropdown : " + key + " and the from excel reader as : " + value);
	}

	// ELEMENT PRESENT
	public static Boolean isElementPresent(String key) {

		try {

			if (key.endsWith("_XPATH")) {
				driver.findElement(By.xpath(OR.getProperty(key))).click();

			} else if (key.endsWith("_CSS")) {
				driver.findElement(By.cssSelector(OR.getProperty(key))).click();

			} else if (key.endsWith("_ID")) {
				driver.findElement(By.id(OR.getProperty(key))).click();

			}

			log.info("Element Found : " + key);
			return true;
		} catch (Throwable t) {

			log.info("Error when finding element  : " + key + "; Exception Message is : " + t.getMessage());
			return false;
		}
	}

// METHOD TO ENSURE EXTENT REPORT TESTNG LOGS FAILURES AND TEST CONTIUNED
	public static void verifyEquals(String actual, String expected) throws IOException {
		try {
			Assert.assertEquals(actual, expected);

		} catch (Throwable t) {

			// capture screenshot
			TestUtils.captureScreenShot();

			// attach to extent and testng uses reporter.log report
			// testng reporting
			Reporter.log("<br>" + "Verification failure" + t.getMessage() + "<br>");
			Reporter.log("<br>");
			Reporter.log("<a target= \"_blank\"href=" + TestUtils.filename + "><img src=" + TestUtils.filename
					+ " height=200,width=200></a>");
			Reporter.log("<br>");
			Reporter.log("<br>");
			// extent reporting USES TEST.LOG
			// log test status as pass
			test.log(LogStatus.FAIL, "Verification failed with exception : " + t.getMessage());
			// map screenshot to same report
			test.log(LogStatus.FAIL, test.addScreenCapture(TestUtils.filename));

		}
	}

	public boolean isElementPresent1(By by) {
		
		try {
			
		driver.findElement(by);	
		return true;
		}
		
		catch(NoSuchElementException  e) {
			
			return false;
		}
		
		
		
		
		
	}
	
	
	@AfterSuite
	public void tearDown() {
		// Close all process like browser, db every thing in this method we want to
		// run this after all test cases are run hence @Aftersuite

		driver.quit();
		log.debug("Test Execution Completed");
	}

}
