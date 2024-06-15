package com.ecom.testcases;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ecom.pageobject.CartPage;
import com.ecom.pageobject.InventoryPage;
import com.ecom.pageobject.LoginPage;


public class TC_AddRemoveProductTest extends BaseClass {
	
	
	@Test(enabled=true, priority=2)
	public void verifyAddRemoveProduct() throws IOException, InterruptedException {
		LoginPage lp = new LoginPage(driver);
		logger.info("logging into Swag app..");
		lp.enterUsername("standard_user");
		lp.enterPassword("secret_sauce");
		lp.clickLoginBtn();
		logger.info("logged in successfully");
		
		logger.info("On Inventory page...");
		InventoryPage ip = new InventoryPage(driver);
		int size = ip.getInventoryItemSize();
		
		logger.info("selecting randomly a product from Inventory items");
		Random rnd = new Random();
		WebElement item = ip.getAnInventoryItem(rnd.nextInt(size));
		logger.info("Selected random item " + item.getText());
		
		//getting item name
		String name = ip.getItemName(item);
		
		logger.info("adding product " + name + " to cart");
		ip.addProduct(item);
				
		logger.info("moving to cart page...");
		ip.clickCart();
		
		CartPage cp = new CartPage(driver);
		
		Assert.assertEquals(cp.getTitle(), "Your Cart");
		logger.info("successfuly landed on cart page...");
		
		List<String> myCartItems = cp.getCartItemNames();
		if (myCartItems.contains(name)) {
			Assert.assertTrue(true);
			logger.info("Items successfully added to the cart \n" + myCartItems);
		}
		else {
			Assert.assertTrue(false);
			captureScreenshot(driver, "verifyAddRemoveProduct");
		}
		
		logger.info("removing items from cart...");
		cp.removeItemsFromCart();
		try {
			if(ip.getCartBadgeElement().isDisplayed()) {
				Assert.assertTrue(false);
				captureScreenshot(driver, "verifyAddRemoveProduct");
			}
			
		}
		catch(NoSuchElementException e) {
			Assert.assertTrue(true);
			logger.info("all items removed, cart empty!");
			
		}
		
		//logout
		ip.clickLogOut();
	}
	
	@Test(priority=1)
	public void verifyContinueShoppingBtn() throws IOException, InterruptedException {
		LoginPage lp = new LoginPage(driver);
		InventoryPage ip = new InventoryPage(driver);
		CartPage cp = new CartPage(driver);
		
		logger.info("logging into Swag app..");
		lp.enterUsername("standard_user");
		lp.enterPassword("secret_sauce");
		lp.clickLoginBtn();
		logger.info("logged in successfully");
			
		
		logger.info("moving to cart page...");
		ip.clickCart();
		
		logger.info("clicking on ContinueShopping button");
		cp.clickContinueShoppingBtn();
		String pageUrl = driver.getCurrentUrl();
		
		if(pageUrl.contains("inventory.html")){
			Assert.assertTrue(true);
			logger.info("back to inventory page: " + pageUrl);
		}
		else {
			Assert.assertTrue(false);
			captureScreenshot(driver, "verifyContinueShopping");
		}
		
		//logout
		ip.clickLogOut();
		
	}

}
