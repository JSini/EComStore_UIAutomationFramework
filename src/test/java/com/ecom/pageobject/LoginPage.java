package com.ecom.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	//create object  of webdriver
	WebDriver driver;
	
	public LoginPage(WebDriver rdriver) {
		
		PageFactory.initElements(rdriver, this);
		
	}
	
	//identify web elements
	@FindBy(id = "user-name")
	WebElement username;
	
	@FindBy(id = "password")
	WebElement password;
	
	@FindBy(id = "login-button")
	WebElement logBtn;
	
	
	public void enterUsername(String name) {
		username.sendKeys(name);
	}
	
	public void enterPassword(String pwd) {
		password.sendKeys(pwd);
	}
	
	public void clickLoginBtn() {
		logBtn.click();
	}
	
	

}
