package utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import base.Page;
import base.TestBaseReplacedbyPagesREFONLY; // to get driver ref from there

public class TestUtils extends Page {
	
	//********CAPTURE SCREENSHOT BEGINS ************ 
	public static String filename;  //screenshot name
	
	//static variable filename above added outside of method
	//because we can directly call it using classname.methodname
	public static void captureScreenShot() throws IOException {

		Date d = new Date();
		filename = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";

		// capture and store screenshot, we use TakesScreenshot interface which is
		// typecast using driver reference and add timestamp
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		// below for capture of screenshot when running testng.xml
		// FileUtils.copyFile(screenshot, new File(System.getProperty("user.dir") +
		// "\\test-output\\html\\" + filename));

		// new code 21/08/2020 below for capture of screenshot when running as maven
		FileUtils.copyFile(screenshot,
				new File(System.getProperty("user.dir") + "\\target\\surefire-reports\\html\\" + filename));

	}

	// ****END CAPTURESCREENSHOT****

	
	 
	// *****BEGIN DATA PROVIDER NEW FOR HASHTABLE*****
	@DataProvider(name="dp1")
	public Object[][] getData1(Method m) {

		String sheetName = m.getName(); //instead of String sheetName = "AddCustomerTest"-  m.getname gets the name from calling testcase
		
		// get row and column count
		int rowNum = excel.getRowCount(sheetName);
		int colNum = excel.getColumnCount(sheetName);
		
		// create object of data class
		Object[][] data = new Object[rowNum - 1][1];
		
		Hashtable<String,String> table = null;
		
		// now to read we do a forloop
		for (int rows = 2; rows <= rowNum; rows++) {
		
			table = new Hashtable <String,String>(); // Creates new table for each row  
		
			for (int cols = 0; cols < colNum; cols++) {
				
				table.put(excel.getCellData(sheetName, cols, 1),excel.getCellData(sheetName, cols, rows));
				data[rows - 2][0] = table;
			}
		}
		return data;

	}

	// *****END DATA PROVIDER NEW FOR HASHTABLE*****

	
	
	//*****BEGIN CHECKING RUNMODE STATUS IN TESTDATA.XLS*****
	public static boolean isTestRunnable(String testName, ExcelReader excel) {
		String sheetName = "test_suite";
		int rows = excel.getRowCount(sheetName);

		for (int rNum = 2; rNum <= rows; rNum++) {
			String testCase = excel.getCellData(sheetName, "TCID", rNum);
			if (testCase.equalsIgnoreCase(testName)) {
				String runmode = excel.getCellData(sheetName, "Runmode", rNum);
				if (runmode.equalsIgnoreCase("Y")) 
						return true;
					else
						return false;
					
				
			}
		}

		return false;
	}
	//*****END CHECKING RUNMODE STATUS IN TESTDATA.XLS*****
	
	
	
	// *****STAND ALONE DATA PROVIDER USING METHOD M FROM JAVA LANG REFLECT*****
		@DataProvider(name="dp")
		public Object[][] getData(Method m) {

			String sheetName = m.getName(); //instead of String sheetName = "AddCustomerTest"-  m.getname gets the name from calling testcase
			
			// get row and column count
			int rowNum = excel.getRowCount(sheetName);
			int colNum = excel.getColumnCount(sheetName);
			
			// create object of data class
			Object[][] data = new Object[rowNum - 1][colNum];
			
			// now to read we do a forloop
			for (int rows = 2; rows <= rowNum; rows++) {
				for (int cols = 0; cols < colNum; cols++) {
					data[rows - 2][cols] = excel.getCellData(sheetName, cols, rows);
				}
			}
			return data;

		}

		
		// *****STAND ALONE DATA PROVIDER ENDS *****		
	
		
		
		
		// *****BEGIN DATA PROVIDER ANOTHER HASHTABLE*****
		
		public static Object[][] getData(String testcase,ExcelReader excel) { 
			
			String sheetName="TestData";
			String testCase=testcase; //TestA1 originally
		
			// test case start from row example row 1
			int testCaseRowNum=1; // for first test variable
			
			while(!excel.getCellData(sheetName, 0, testCaseRowNum).equalsIgnoreCase(testCase)) {
			//so while loop check till the time testCase name is not equal to name specified increment testCaseRowNum
			
			testCaseRowNum++;
			}

			System.out.println("Test begins at row num"+testCaseRowNum);	
			// next we check how many columns we have and how many rows are used by each testcase to find out cols start from and testdata starts from
			int colsStartRowNum=testCaseRowNum+1 ;// column name start in row number for selected test
			int dataStartRowNum=colsStartRowNum+1 ;// data begins at row number for selected test
			
			// lets find total number of columns in test
			int cols=0;
			while(!excel.getCellData(sheetName, cols, colsStartRowNum).trim().equals("")) {
				
				cols++;
			}
			System.out.println("Total number of columns are "+cols);	
			
			// lets find total number of rows in test
					int rows=0;
					while(!excel.getCellData(sheetName, 0, dataStartRowNum+rows).trim().equals("")) {
						
						rows++;
					}
				//PRINT TEST DATA
					
					Object[][] data = new Object[rows][1];
					int i=0;
				for (int row=dataStartRowNum; row<dataStartRowNum+rows;row++) {
					Hashtable<String,String> table = new Hashtable<String,String>();
					for (int col=0; col<cols;col++) {
						
						String Testdata = excel.getCellData(sheetName, col, row);
						String colName = excel.getCellData(sheetName, col, colsStartRowNum);
						table.put(colName, Testdata);
					}
					data[i][0]=table;
					i++;
				}	
				return data;
		}

		// *****END DATA PROVIDER ANOTHER HASHTABLE *****

		
		
		
		// *****BEGIN  IS ALERT PRESENT TEST  *****
		public static boolean isAlertPresent() {

		    boolean presentFlag = false;

		    try {

		        // Check the presence of alert
		        Alert alert = driver.switchTo().alert();
		        // Alert present; set the flag
		        presentFlag = true;
		        // if present consume the alert
		        alert.accept();
		        //( Now, click on ok or cancel button )

		    } catch (NoAlertPresentException ex) {
		        // Alert not present
		        ex.printStackTrace();
		    }

		    return presentFlag;
		}
		
		// *****END  IS ALERT PRESENT TEST  *****
		
		
		
}
