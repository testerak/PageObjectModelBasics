package listeners;

//for screenshot
import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {

	private static ExtentReports extent;
	
	
	

	    public static ExtentReports getInstance() {
	        
	    	if (extent==null) {
	    		
	    		// extent report folder containing extent.html
	    		extent=new ExtentReports(System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\extent.html", true, DisplayOrder.OLDEST_FIRST);
	    		extent.loadConfig(new File(System.getProperty("user.dir")+"\\src\\test\\resources\\extentconfig\\ReportsConfig.xml"));
	    	}
	        
	        
	        return extent;
	    }

	    // Only needed when working with web or Appium for Api 
	    // As we are not generating any screens dont need it
	    public static String screenshotPath;
		public static String screenshotName;
		public static WebDriver driver;
		public static void captureScreenshot() {
			System.setProperty("webdriver.chrome.driver", "C:\\work\\executables\\chromedriver.exe");
			driver=new ChromeDriver();
			//File scrFile = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			Date d = new Date();
			screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";

			try {
				FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "\\reports\\" + screenshotName));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		
		}
	

	}
