package utilities;

import java.lang.reflect.Method;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="getDataSuite1")
	public static Object[][] getDataSuite1(Method m) {
		// method m returns current method name 
		System.out.println(m.getName());
		
		// making dataprovider generic by not typing test name, path  etc here
		ExcelReader excel = new ExcelReader(Constants.SUITE1_XL_PATH);//System.getProperty("user.dir")+"\\src\\test\\resources\\data\\Suite1.xlsx");
		String testcase=m.getName();
		
		return TestUtils.getData(testcase,excel);
	
	}
	
	@DataProvider(name="getDataSuite2")
	public static Object[][] getDataSuite2(Method m) {
		// method m returns current method name 
				System.out.println(m.getName());
				
				// making dataprovider generic by not typing test name, path  etc here
				ExcelReader excel = new ExcelReader(Constants.SUITE2_XL_PATH);//System.getProperty("user.dir")+"\\src\\test\\resources\\data\\Suite2.xlsx");
				String testcase=m.getName();
				
				return TestUtils.getData(testcase,excel);
			
	}
	
	//*********BEGIN RUNMODES**********
	public static boolean isSuiteRunnable(String suiteName){ //String suiteName is worksheetname
	
		ExcelReader excel = new ExcelReader(Constants.TEST_SUITE_XL_PATH);//System.getProperty("user.dir")+"\\src\\test\\resources\\data\\TestSuite.xlsx");
		String SheetName=Constants.TEST_SUITE_SHEET;
		//get row count
		int rows=excel.getRowCount(SheetName);
		// create loop to read 
		for(int rowNum=2; rowNum<=rows; rowNum++) {
			
			// get cell data and store
			String testSuiteName = excel.getCellData(SheetName, Constants.TEST_SUITE_COL, rowNum);
			if(testSuiteName.equalsIgnoreCase(suiteName)) { 
				
				String runMode = excel.getCellData(SheetName, Constants.SUITE_RUNMODE_COL, rowNum);
				if(runMode.equalsIgnoreCase(Constants.RUNMODE_YES)) {
					return true;
							}else { 
								
								return false;
								}
			
			}
			
		}
		return false;
		
		
		
	}//*********END IS SUITE EXECUTABLE***********
	
	
	
	//*********BEGIN IS TEST EXECUTABLE***********

	public static boolean isTestRunnable(String testCaseName, ExcelReader excel) {
		String sheetName = Constants.TEST_CASE_SHEET;
		//get row count
		int rows=excel.getRowCount(sheetName);
		 // create loop to read 
		for(int rowNum=2; rowNum<=rows; rowNum++) {
					
		// get cell data and store
		String testCase = excel.getCellData(sheetName, Constants.TEST_CASE_COL, rowNum);
		if(testCase.equalsIgnoreCase(testCaseName)) { 
						
		String runMode = excel.getCellData(sheetName, Constants.TEST_RUNMODE_COL, rowNum);
		if(runMode.equalsIgnoreCase(Constants.RUNMODE_YES)) {
		return true;
		}else { 
						
		return false;
		}
			
	}
					
}
		return false;
		}
	
	//*********END  IS TEST EXECUTABLE***********
	
	//*********BEGIN CHECHK RUMMODE STATUS FOR SUITE, TEST AND ITTERATION***********
	
	//method should execept String testSuiteName,String testCaseName,String dataRunMode, ExcelReader excel
	public static void checkExecution(String testSuiteName,String testCaseName,String dataRunMode, ExcelReader excel) 
		{
		//design of common method
		
		if (!isSuiteRunnable(testSuiteName)) {
			throw new SkipException("Skipping the test : " +testCaseName+ "as the run mode of test suite " +testSuiteName+" is No");
			
		}
		if (!isTestRunnable(testCaseName,excel)) {
			throw new SkipException("Skipping the test : " +testCaseName+ "as the run mode of test case " +testCaseName+" is No");
			
		}
		
		if (dataRunMode.equalsIgnoreCase(Constants.RUNMODE_NO)) {
			throw new SkipException("Skipping the test : " +testCaseName+ "as the run mode of test data is Not Yes");
			
		}
		
		
		}
}
