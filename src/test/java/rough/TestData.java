package rough;

import utilities.ExcelReader;

public class TestData {

	public static void main(String[] args) {
		
		ExcelReader excel = new ExcelReader("C:\\work\\Selenium\\HybridFramewor_Section30\\src\\test\\resources\\data\\testdatahybrid.xlsx");
		
		String sheetName="TestData";
		String testCase="SignUpTest";//LoginTest,UserRegTest,SignUpTest
// in this data sheet i have all my test cases in single worksheet called testdata
// so we need to workout in which row a new test case starts
		
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
			//so this while loop check till the time blank rows is found
				// and this is why we need both variables where data begins and increment row
				//dataStartRowNum+rows in the while loop
				
				System.out.println("Total number of rows are "+rows);
				System.out.println("*********");
	// now print data from excel data sheet
			for (int row=dataStartRowNum; row<dataStartRowNum+rows;row++) {
				for (int col=0; col<cols;col++) {
					
					System.out.println(excel.getCellData(sheetName, col, row));
				
				}
				
			}	
				
				
	}	
}
	
