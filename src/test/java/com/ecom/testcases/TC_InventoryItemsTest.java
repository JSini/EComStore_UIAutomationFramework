package com.ecom.testcases;

import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ecom.pageobject.InventoryPage;
import com.ecom.pageobject.LoginPage;

public class TC_InventoryItemsTest extends BaseClass{
	
	
	@Test(enabled=true)
	public void verifyInventoryItems() throws IOException {
		
		LoginPage lp = new LoginPage(driver);
		
		logger.info("logging into Swag app..");
		lp.enterUsername("standard_user");
		lp.enterPassword("secret_sauce");
		lp.clickLoginBtn();
		logger.info("logged in successfully");
		
		
		logger.info("moving to Inventory page...");
		InventoryPage ip = new InventoryPage(driver);
		int size = ip.getInventoryItemSize();
		
		//int size = 0; //intentionally failing test
		
		logger.info("checking Inventory page items number");
		if (size!=0) {
			logger.info("verifyInventoryItems - Passed");
			Assert.assertTrue(true);
			System.out.println("Number of items in Inventory " + size);
			System.out.println("Items in Inventory: " + "\n" + ip.getInventoryItemNames());
		}
		else {
			logger.info("verifyInventoryItems - Failed");
			logger.info("There seems a problem - no items in inventory!!");
			captureScreenshot(driver, "verifyInventoryItems");
			Assert.assertTrue(false);
		}
		
		//logout
		ip.clickLogOut();
		logger.info(driver.getCurrentUrl());
	}
	
	@Test(enabled=true)
	public void verifySingleInventoryItem() throws IOException {
		
		LoginPage lp = new LoginPage(driver);
		logger.info("logging into Swag app..");
		lp.enterUsername("standard_user");
		lp.enterPassword("secret_sauce");
		lp.clickLoginBtn();
		logger.info("logged in successfully");
		
		logger.info("moving to Inventory page...");
		InventoryPage ip = new InventoryPage(driver);
		int size = ip.getInventoryItemSize();
		
		logger.info("selecting randomly a product from Inventory items");
		Random rnd = new Random();
		int index = rnd.nextInt(size);
		logger.info(index);
		
		//getting the random item from items by index
		WebElement item = ip.getAnInventoryItem(index);
		logger.info("Selected random item " + item.getText());
		
		//getting item name
		String name = ip.getItemName(item);

		if (name != null) {
			Assert.assertTrue(true);
			logger.info("product selected is : " + name);
			
		}
		else {
			Assert.assertTrue(false);
			captureScreenshot(driver, "verifySingleInventoryItems");
		}
		
		String price = ip.getItemPrice(item);
		
		if (price != null) {
			Assert.assertTrue(true);
			logger.info("product price is : " + price);
			
		}
		else {
			Assert.assertTrue(false);
			captureScreenshot(driver, "verifySingleInventoryItems");
		}
		
		logger.info("adding product " + name + " to cart");
		ip.addProduct(item);
		
		String badge = ip.getCartBadge();
		logger.info("#items in Cart " + badge);
		ip.clickCart();
			
		
		logger.info("Product added to cart");
		
		//logout
		ip.clickLogOut();
		
	}
	
	public void verifyFilter() {
		
		//verify sort
	}
}
