package com.ecom.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutOnePage {
	
	
	WebDriver driver;
	
	public CheckOutOnePage(WebDriver rdriver) {
		PageFactory.initElements(rdriver, this);
	}
	
	
	//Page title
	@FindBy(css = "div.header_container div.header_secondary_container")
	WebElement pageTitle;
	
	@FindBy(css= "input#first-name")
	WebElement firstName;
	
	@FindBy(css= "input#last-name")
	WebElement lastName;
	
	@FindBy(css= "input#postal-code")
	WebElement zipCode;
	
	@FindBy(css= "button#cancel")
	WebElement cancelBtn;
	
	@FindBy(css= "input#continue")
	WebElement continueBtn;
	
	
	public String getPageTitle() {
		return pageTitle.getText();
	}
	
	
	public void enterFirstName(String firstname) {
		firstName.sendKeys(firstname);
	}
	
	public void enterLastName(String lastname) {
		lastName.sendKeys(lastname);
	}
	
	public void enterZipCode(String zipcode) {
		zipCode.sendKeys(zipcode);
	}
	
	public void clickContinue() {
		continueBtn.click();
	}
	
	public void clickCancel() {
		cancelBtn.click();
	}
}
