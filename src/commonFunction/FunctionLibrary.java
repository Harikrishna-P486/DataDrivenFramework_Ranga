package commonFunction;

import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.Reporter;

import config.AppUtil;

public class FunctionLibrary extends AppUtil{
	
	public static boolean adminLogin(String Username , String Password)throws Throwable {
		driver.get(conpro.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath(conpro.getProperty("ObjReset"))).click();
		driver.findElement(By.xpath(conpro.getProperty("ObjUser"))).sendKeys(Username);
		driver.findElement(By.xpath(conpro.getProperty("ObjPass"))).sendKeys(Password);
		driver.findElement(By.xpath(conpro.getProperty("ObjLogin"))).click();
		Thread.sleep(3000);
		String Expected = "dashboard";
		String Actual = driver.getCurrentUrl();
		
		if(Actual.contains(Expected)) {
			Reporter.log("Valid Username & Password:: "+Expected+"  "+Actual,true);
			//click logout link
			driver.findElement(By.xpath(conpro.getProperty("ObjLogout"))).click();
			return true;
		}
		else {
			//capture error message
		String message =driver.findElement(By.xpath(conpro.getProperty("ObjError"))).getText();
		Thread.sleep(3000);
		driver.findElement(By.xpath(conpro.getProperty("ObjOk"))).click();
		Reporter.log(message+"   "+Expected+"    "+Actual,true);
			
		}
		return false;
		
	}

}
