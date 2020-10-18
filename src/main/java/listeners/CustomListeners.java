package listeners;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.testng.ISuite;
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
//add to implements ISuite after ITestListeners when email to send
public class CustomListeners extends Page implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		test = rep.startTest(result.getName().toUpperCase());// where result.getname gets test name to run
		// check runmode
		
		
		if (!TestUtils.isTestRunnable(result.getName(), excel)) {

			throw new SkipException("Skipping test : " + result.getName().toUpperCase() + " A test runmode is No");

		}
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TO log status of test in extent report with testname
		// WHENEVER YOU GENERATE AN EXTENT REPORT YOU NEED TO END THIS REPORT AS WELL
		test.log(LogStatus.PASS, result.getName().toUpperCase() + " Pass");
		// TO END
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
		// log test status as fail
		test.log(LogStatus.FAIL, result.getName().toUpperCase() + " Fail with exception  " + result.getThrowable());

		// map screenshot to same report
		test.log(LogStatus.FAIL, test.addScreenCapture(TestUtils.filename));

		Reporter.log("Capturing screenshot");
		Reporter.log("Click to see screenshot");
		Reporter.log("<a target= \"_blank\"href=" + TestUtils.filename + ">SCREENSHOT LINK</a>");
		// Reporter.log("<a target= \"_blank\"
		// href=\"C:\\Users\\user1\\Pictures\\Screenpresso\\2020-10-05_17h10_04.png
		// \">SCREENSHOT LINK</a>");

		Reporter.log("<br>");
		Reporter.log("<br>");
		Reporter.log("<a target= \"_blank\"href=" + TestUtils.filename + "><img src=" + TestUtils.filename
				+ " height=200,width=200></a>");
		// Reporter.log("<a target=
		// \"_blank\"href=\"C:\\Users\\user1\\Pictures\\Screenpresso\\2020-10-05_17h10_04.png
		// \"><img
		// src=\"C:\\Users\\user1\\Pictures\\Screenpresso\\2020-10-05_17h10_04.png\"
		// height=200,width=200></a>");

		// now we need to end this test
		rep.endTest(test);
		rep.flush();

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.log(LogStatus.SKIP, result.getName().toUpperCase() + "Skipped test as test mode is No");
		
		//now we need to end this test
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
	
	
	/*
	 * @Override public void onStart(ISuite suite) { // TODO Auto-generated method
	 * stub
	 * 
	 * }
	 * 
	 * @Override public void onFinish(ISuite suite) {
	 * 
	 * MonitoringMail mail = new MonitoringMail();
	 * 
	 * try { messageBody = "http://" + InetAddress.getLocalHost().getHostAddress() +
	 * ":8080/job/DataDrivenFramework20Aug2020/ExtentReports/"; } catch
	 * (UnknownHostException e1) {
	 * 
	 * e1.printStackTrace(); }
	 * 
	 * 
	 * try { mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to,
	 * TestConfig.subject, messageBody, TestConfig.attachmentPath,
	 * TestConfig.attachmentName); } catch (AddressException e) {
	 * 
	 * e.printStackTrace(); } catch (MessagingException e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * }
	 */
}
