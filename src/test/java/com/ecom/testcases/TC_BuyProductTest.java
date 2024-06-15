package com.ecom.testcases;

import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ecom.pageobject.CartPage;
import com.ecom.pageobject.CheckOutCompletePage;
import com.ecom.pageobject.CheckOutOnePage;
import com.ecom.pageobject.CheckOutTwoPage;
import com.ecom.pageobject.InventoryPage;
import com.ecom.pageobject.LoginPage;

public class TC_BuyProductTest extends BaseClass {
	
	@Test
	public void verifyBuyProduct() throws IOException {
		LoginPage lp = new LoginPage(driver);
		InventoryPage ip = new InventoryPage(driver);
		CartPage cp = new CartPage(driver);
		CheckOutOnePage checkoutOnePg = new CheckOutOnePage(driver);
		CheckOutTwoPage checkoutTwoPg = new CheckOutTwoPage(driver);
		CheckOutCompletePage checkoutFinishPg = new CheckOutCompletePage(driver);
		
		logger.info("logging into Swag app..");
		lp.enterUsername("standard_user");
		lp.enterPassword("secret_sauce");
		lp.clickLoginBtn();
		logger.info("logged in successfully");
			
		
		logger.info("On Inventory page...");
		int size = ip.getInventoryItemSize();
		
		logger.info("selecting randomly a product from Inventory items");
		Random rnd = new Random();
		WebElement item = ip.getAnInventoryItem(rnd.nextInt(size));
		logger.info("selected random item - " + ip.getItemName(item));
		
		String name = ip.getItemName(item);
		logger.info(ip.getItemPrice(item));
		float price = Float.parseFloat(ip.getItemPrice(item).replace("$", ""));
		
		logger.info("adding product " + name + " to cart");
		ip.addProduct(item);
				
		logger.info("moving to cart page...");
		ip.clickCart();
		
		int cartItemsSize = cp.getCartItemsSize();
		
		logger.info("clicking on checkout button, moving to checkout page 1");
		cp.clickCheckoutBtn();
		
		Assert.assertEquals(checkoutOnePg.getPageTitle(), "Checkout: Your Information");
		logger.info("successfully landed on Checkout page 1");
		
		checkoutOnePg.enterFirstName("abc");
		checkoutOnePg.enterLastName("xyz");
		checkoutOnePg.enterZipCode("987654");
		logger.info("entered user information, firstname, lastname and zipcode");
		
		logger.info("moving to checkout page 2");
		checkoutOnePg.clickContinue();
		
		Assert.assertEquals(checkoutTwoPg.getPageTitle(), "Checkout: Overview");
		
		int checkOutItemsSize = checkoutTwoPg.getCheckOutItemsSize();
		
		if (checkOutItemsSize==cartItemsSize) {
			Assert.assertTrue(true);
			logger.info("Number of items in CheckOut verified " + checkOutItemsSize);
		}
		else {
			Assert.assertTrue(false);
			captureScreenshot(driver, "verifyBuyProduct");
		}
		
		float totalCost = checkoutTwoPg.getItemTotaCost();
		
		if (totalCost==price) {
			Assert.assertTrue(true);
			logger.info("total cost verified " + totalCost);
		}
		else {
			Assert.assertTrue(false);
			captureScreenshot(driver, "verifyBuyProduct");
		}
		
		float tax = checkoutTwoPg.getTaxCost();
		float finalCost = checkoutTwoPg.getFinalCost();
		logger.info("tax amount " + tax + " and final cost is " + finalCost );
		
		logger.info("moving to finish checkout page...");
		checkoutTwoPg.clickFinishBtn();
		
		Assert.assertEquals(checkoutFinishPg.getPageTitle(), "Checkout: Complete!");
		logger.info("landed successfuly on finish checkout page...");
		
		if (checkoutFinishPg.getThankYouText().contains("Thank you for your order!") && 
				checkoutFinishPg.getOrderDispatchText().contains("")) {
			Assert.assertTrue(true);
			logger.info("order successfully placed message " + checkoutFinishPg.getOrderDispatchText());
		}
		else {
			Assert.assertTrue(false);
			captureScreenshot(driver, "verifyBuyProduct");
		}
		
		checkoutFinishPg.clickBackHomeBtn();
		Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"));
		
		ip.clickLogOut();
	}

}
