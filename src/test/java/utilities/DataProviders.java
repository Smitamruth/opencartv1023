package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

// Data Provider 1.
	
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException{
		String path = ".\\testData\\login.xlsx"; 	//taking xl file from testdata folder.
		
		ExcelUtility xlUtil = new ExcelUtility(path);
		
		int totalRows = xlUtil.getRowCount("Sheet1");
		int totalCols = xlUtil.getColCount("Sheet1", 1);
		
		String loginData[][] = new String[totalRows][totalCols];		//created for two dimension array which can be stored.
		
		for(int i=1; i<=totalRows; i++) {	// read data from xl and store it in 2D array.
			for(int j=0; j<totalCols; j++) {	// is is rows ad j is cols.
				loginData[i-1][j] = xlUtil.getCellData("Sheet1", i, j); 	// i value is 1 initially but i is taken as 0 here, coz array index starts from zero.
			}
		}
			return loginData; 	//returning 2D array.	
	}
	
// Data Provider 2.
	
	
// Data Provider 3.
	
	
// Data Provider 4.
	
}
