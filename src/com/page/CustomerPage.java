package com.page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactoryFinder;
import org.testng.Reporter;

public class CustomerPage {
WebDriver driver;
public CustomerPage(WebDriver driver) {
	this.driver = driver;
} 
	@FindBy(xpath = "//li[@id='mi_a_customers']")
	WebElement clickCutomerLink;
	@FindBy(xpath = "(//span[@class='glyphicon glyphicon-plus ewIcon'])[1]")
    WebElement clickAddIconButton;
	@FindBy(id = "x_Customer_Number")
	WebElement enterCustNumber;
	@FindBy(name = "x_Customer_Name")
	WebElement customerName;
	@FindBy(css ="#x_Address" )
	WebElement enterAddress;
	@FindBy(xpath = "//input[@placeholder='City']")
	WebElement enterCity;
	@FindBy(name = "x_Country")
	WebElement enterCountry;
	@FindBy(css =  "#x_Contact_Person")
	WebElement enterContactPerson;
	@FindBy(css = "#x_Phone_Number")
	WebElement enterPhoneNumber;
	@FindBy(xpath = "//input[@id='x__Email']")
	WebElement enterEmail;
	@FindBy(id = "x_Mobile_Number")
	WebElement enterMobileNumber;
	@FindBy(xpath = "//input[@id='x_Notes']")
	WebElement enterNotes;
	@FindBy(css = "#btnAction")
	WebElement clickAddButton;
	@FindBy(xpath = "//button[@id='btnCancel']")
	WebElement cancelButton;
	@FindBy(xpath = "//button[normalize-space()='OK!']")
	WebElement confirmOkButton;
	@FindBy(xpath = "(//button[contains(text(),'OK')])[6]")
    WebElement clickAlertOk;
	@FindBy(xpath = "//button[@data-form='fa_customerslistsrch']")
	WebElement clickSearchPannel;
	@FindBy(xpath = "//input[@id='psearch']")
	WebElement enterSearchTextBox;
	@FindBy(css = "#btnsubmit")
	WebElement clickSearchButton;
	@FindBy(xpath = "//table[@class='table ewTable']/tbody/tr[1]/td[5]/div/span/span")
	WebElement webTable;
	//method for add customer
	public boolean addcustomer(String cname, String address,String city,String country,String cperson,String pnumber,
			String email, String mnumber,String notes) throws Throwable
	{
		Actions ac = new Actions(driver);
	    ac.moveToElement(clickCutomerLink).click().perform();
	    Thread.sleep(1000);
	    ac.moveToElement(clickAddIconButton).click().perform();
	    Thread.sleep(1000);
	    //capture customer number
	    String exp_data = this.enterCustNumber.getAttribute("value");
	    customerName.sendKeys(cname);
	    enterAddress.sendKeys(address);
	    enterCity.sendKeys(city);
	    enterCountry.sendKeys(country);
	    enterContactPerson.sendKeys(cperson);
	    enterPhoneNumber.sendKeys(pnumber);
	    enterEmail.sendKeys(email);
	    enterMobileNumber.sendKeys(mnumber);
	    enterNotes.sendKeys(notes);
	    clickAddButton.sendKeys(Keys.ENTER);
	    Thread.sleep(1000);
	    ac.moveToElement(confirmOkButton).click().perform();
	    ac.pause(3000);
	    ac.moveToElement(clickAlertOk).click().build().perform();
	    //if search text box text is already displayed dont click on search pannel button
	    if(!enterSearchTextBox.isDisplayed())
	    	clickSearchPannel.click();
	    enterSearchTextBox.clear();
	    enterSearchTextBox.sendKeys(exp_data);
	    clickSearchButton.click();
	    String Act_data = webTable.getText();
	    if(Act_data.equals(exp_data)) {
	    	Reporter.log(Act_data+"  "+exp_data);
	    	return true;
	    }else {
	    	Reporter.log(Act_data+"  "+exp_data);
	    	return false;
	    }
	    
	    
	}
	
	
    
	
	
	
}
