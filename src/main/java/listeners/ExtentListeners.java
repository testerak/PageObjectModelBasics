package listeners;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;

import com.relevantcodes.extentreports.LogStatus;

import base.Page;
import base.TestBaseReplacedbyPagesREFONLY;
import utilities.MonitoringMail;
import utilities.TestConfig;
import utilities.TestUtils;

public class ExtentListeners extends Page implements ITestListener,ISuiteListener {
	public String messageBody;
	//listeners.ExtentListeners
	//below two from IsuiteListeners interface
	@Override
	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ISuite suite) {
		
		MonitoringMail mail = new MonitoringMail();
		
		try {
			messageBody = "http://" + InetAddress.getLocalHost().getHostAddress()
					+ ":8080/job/DataDrivenFramework20Aug2020/ExtentReports/";
		} catch (UnknownHostException e1) {
			
			e1.printStackTrace();
		}
		

		try {
			mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, messageBody,
					TestConfig.attachmentPath, TestConfig.attachmentName);
		} catch (AddressException e) {
			
			e.printStackTrace();
		} catch (MessagingException e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public void onTestStart(ITestResult result) {
// before we can run test in maven we need to tell on teststart get name of test and start selected test
		 test = rep.startTest(result.getName().toUpperCase());

		// RUNMODE - y OR n
		 if (!TestUtils.isTestRunnable(result.getName(), excel)) {

			 throw new SkipException("Skipping the test : " + result.getName().toUpperCase() + " as runmode is not Yes");
		}
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		// log test status as pass
		test.log(LogStatus.PASS, result.getName().toUpperCase() + "PASS");
		// now we need to end this test
		rep.endTest(test);
		rep.flush();

	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		// CAPTURE SCREENSHOT ON FAILURE
		try {
			TestUtils.captureScreenShot();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// log test status as pass
		test.log(LogStatus.FAIL, result.getName().toUpperCase() + "Failed with exception : " + result.getThrowable());
		// map screenshot to same report
		test.log(LogStatus.FAIL, test.addScreenCapture(TestUtils.filename));

		Reporter.log("Click to see screenshot");
		Reporter.log("<a target= \"_blank\"href=" + TestUtils.filename + ">SCREENSHOT LINK</a>");
		Reporter.log("<br>");
		Reporter.log("<br>");
		Reporter.log("<a target= \"_blank\"href=" + TestUtils.filename + "><img src=" + TestUtils.filename
				+ " height=200,width=200></a>");

		// now we need to end this test
		rep.endTest(test);
		rep.flush();

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// log test status as pass
				test.log(LogStatus.SKIP, "Skipped "+result.getName().toUpperCase() + " test as runmode is not yes");
				// now we need to end this test
				rep.endTest(test);
				rep.flush();
	
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onFinish(ITestContext context) {

		/*
		 * if (extent != null) {
		 * 
		 * extent.flush(); }
		 */

	}

}
