package driverFactory;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunction.FunctionLibrary;
import utilities.ExcelFileUtil;

public class DriverScript extends FunctionLibrary {

	String inputpath = "./FileInput/Login.xlsx";
	String outputpath ="./FileOutput/DataDrivenResults.xlsx";
	ExtentReports reports;
	ExtentTest logger;
	@Test
	public void startTest() throws Throwable {
		//define path of html
		reports= new ExtentReports("./Reports/Login.html");
		
		//create object for excelfileutil
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		//counting no of rows in logindata sheet
		int rc = xl.rowCount("LoginData");
		Reporter.log("No.of rows are :: "+rc,true);
		//iterate all rows in a sheet
		for(int i=1 ;i<=rc;i++) {
			logger = reports.startTest("Validate Login");
			//call user name and password cell
			String username = xl.getCellData("LoginData", i, 0);
			String password = xl.getCellData("LoginData", i, 1);
			//calling login method from function lobrary class
			logger.log(LogStatus.INFO,username+"    "+ password);
			boolean res = FunctionLibrary.adminLogin(username, password);
			if(res) {
				//if res is true write as valid username and password into result cell
				xl.setCellData("LoginData", i, 2, "Valid username and password", outputpath);
				//write as pass into status cell
				xl.setCellData("LoginData", i, 3, "Pass", outputpath);
				logger.log(LogStatus.PASS, "Login Success");
			}else {
				//if res is false write as valid username and password into result cell
				xl.setCellData("LoginData", i, 2, "Invalid username and password", outputpath);
				//write as pass into status cell
				xl.setCellData("LoginData", i, 3, "Fail", outputpath);
				logger.log(LogStatus.FAIL, "Fail");
			}
			reports.endTest(logger);
			reports.flush();
		}
	}
	
}