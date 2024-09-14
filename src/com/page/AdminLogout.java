package com.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminLogout {

	@FindBy(xpath = "//li[@id='mi_logout']")
	WebElement ObjLogout;
	
	public void verify_Logout() {
		ObjLogout.click();
	}
	
}
