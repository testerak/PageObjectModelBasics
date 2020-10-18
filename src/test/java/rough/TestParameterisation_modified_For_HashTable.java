package rough;

import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utilities.ExcelReader;

public class TestParameterisation_modified_For_HashTable {

	@Test (dataProvider="getData")
	public void testData(Hashtable<String,String> data) {
	// fourth Now in test data we add the paramenter values to match columns in our data sheet

System.out.println(data.get("Iteration")+"------"+ data.get("TestData")+"------"+  data.get("Browser")+"------"+ data.get("RunMode"));
}

	@DataProvider
public static Object[][] getData(){
	
	ExcelReader excel = new ExcelReader("C:\\work\\Selenium\\HybridFramewor_Section30\\src\\test\\resources\\excel\\testdatahybrid.xlsx");
	String sheetName="TestData";
	String testCase="LoginTest";
//so we need to workout in which row a new test case starts
	
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
		
			// now print data from excel data sheet
			
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
}
