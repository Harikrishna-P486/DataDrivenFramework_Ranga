package driverFactory;

import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.page.CustomerPage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import config.ERP_Apputil;
import utilities.ExcelFileUtil;

public class TestScript extends ERP_Apputil{

	String inputpath ="./FileInput/CustomerData.xlsx ";
	String outputpath="./FileOutput";
	ExtentReports reports;
	ExtentTest logger;
	String TCSheet = "Customer";
	@Test
	public void startTest() throws Throwable 
	{
		reports = new ExtentReports("./Reports/Customer.html");
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		//count no. of rows in excel sheet
		int rc = xl.rowCount(TCSheet);
		Reporter.log("No of rows are :: "+rc,true);
		for(int i=1;i<=rc;i++) 
		{
			logger = reports.startTest("Add Customer");
			String cname = xl.getCellData(TCSheet, i, 0);
			String Address =xl.getCellData(TCSheet, i, 1);
			String city = xl.getCellData(TCSheet, rc, 2);
			String country = xl.getCellData(TCSheet, rc, 3);
			String cperson = xl.getCellData(TCSheet, rc, 4);
			String pnumber = xl.getCellData(TCSheet, rc, 5);
			String email = xl.getCellData(TCSheet, rc, 6);
			String mnumber = xl.getCellData(TCSheet, rc, 7);
			String notes = xl.getCellData(TCSheet, rc, 8);

			CustomerPage cus = PageFactory.initElements(driver, CustomerPage.class);

			boolean result = cus.addcustomer(cname, Address, city, country, cperson, pnumber, email, mnumber, notes);
			logger.log(LogStatus.INFO,cname+" "+Address+" "+city+" "+country+" "+cperson+" "+pnumber+" "+email+" "+mnumber+" "+notes);
			if(result) {
				xl.setCellData(TCSheet, i, 9, "Pass", outputpath);
				logger.log(LogStatus.PASS, "Customer Added Successfully");
			}else {
				xl.setCellData(TCSheet, i, 9, "Fail", outputpath);
				logger.log(LogStatus.FAIL, "Customer Add Failed");
			}
			reports.endTest(logger);
			reports.flush();

		}

	}


}
