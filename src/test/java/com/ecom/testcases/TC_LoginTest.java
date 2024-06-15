package com.ecom.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.ecom.pageobject.InventoryPage;
import com.ecom.pageobject.LoginPage;

public class TC_LoginTest extends BaseClass{
	
	
	@Test
	public void verifyLogin() throws IOException {
		
		
		
		LoginPage lp = new LoginPage(driver);
		
		lp.enterUsername("standard_user");
		lp.enterPassword("secret_sauce");
		logger.info("added username and password");
		
		lp.clickLoginBtn();
		logger.info("clicked on Login!");
		
		
		
		InventoryPage ip = new InventoryPage(driver);
		
		String pageHeading = ip.getInventoryTitle();
		
		if (pageHeading.equals("Products")) {
			logger.info("verify Login - Passed");
			Assert.assertTrue(true);
		}
		else {
			logger.info("verify Login - Failed");
			captureScreenshot(driver, "verifyLogin");
			Assert.assertTrue(false);
		}
		
		ip.clickLogOut();
	
	}
	
}
