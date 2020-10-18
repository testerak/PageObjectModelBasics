package rough;

import utilities.ExcelReader;

public class TestData2 {

	public static void main(String[] args) {
ExcelReader excel = new ExcelReader("C:\\work\\Selenium\\HybridFramewor_Section30\\src\\test\\resources\\data\\testdatahybrid.xlsx");
		
		String sheetName="TestData";
		String testCase="SignUpTest";

		int testCaseRowNum=1; 
		
		while(!excel.getCellData(sheetName, 0, testCaseRowNum).equalsIgnoreCase(testCase)) {
		
		
		testCaseRowNum++;
		}
	
		System.out.println("Test begins at row num"+testCaseRowNum);

	}

}
