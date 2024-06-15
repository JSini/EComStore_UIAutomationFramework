package com.ecom.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutCompletePage {
	
	WebDriver driver;
	
	public CheckOutCompletePage(WebDriver rdriver) {
		PageFactory.initElements(rdriver, this);
	}
	
	@FindBy(css="div.header_container div.header_secondary_container")
	WebElement pageTitle;
	
	@FindBy(css="div.checkout_complete_container h2 ")
	WebElement thankYouText;
	
	@FindBy(css="div.checkout_complete_container div.complete-text ")
	WebElement orderDispatchText;
	
	@FindBy(css = "button#back-to-products")
	WebElement backHomeBtn;
	
	
	public String getPageTitle() {
		return pageTitle.getText();
	}
	
	public String getThankYouText() {
		return thankYouText.getText();
	}
	
	public String getOrderDispatchText() {
		return orderDispatchText.getText();
	}
	
	public void clickBackHomeBtn() {
		backHomeBtn.click();
	}

}
