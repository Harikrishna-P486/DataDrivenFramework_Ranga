package com.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class AdminLogin {
	 WebDriver driver;
	public AdminLogin(WebDriver driver) {
		this.driver=driver;
	}
	
	//define Repository for Login 
	@FindBy(xpath = "//button[@id='btnreset']")
	WebElement ObjReset;
	@FindBy(id ="username")
	WebElement ObjUser;
	@FindBy(xpath="//input[@id='password']")
	WebElement ObjPass;
	@FindBy(name ="btnsubmit")
	WebElement ObjLogin;
	//Method for Login
	public void verify_Login(String ObjUser , String pass) {
		ObjReset.click();
		this.ObjUser.sendKeys(ObjUser);
		ObjPass.sendKeys(pass);
		ObjLogin.click();
		String Expected = "dashboard";
		String Actual = driver.getCurrentUrl();
		try {
		Assert.assertTrue(Actual.contains(Expected), "Invalid username and password");
		
		}catch (AssertionError er) {
			System.out.println(er.getMessage());
		}
		
	}

}
