package com.ecom.pageobject;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

	
	WebDriver driver;
	
	public CartPage(WebDriver rdriver) {
		PageFactory.initElements(rdriver, this);
		
	}
	
	
	//title of the page
	@FindBy(css = "div.header_container div.header_secondary_container")
	WebElement cartTitle;
	
	//cartitems
	@FindBy(css = "div.cart_list div.cart_item")
	List<WebElement> cartItems;
	
	
	//continueshopping button
	@FindBy(name = "continue-shopping")
	WebElement continueShopBtn;
	
	//checkout button
	@FindBy(name = "checkout")
	WebElement checkoutBtn;
	
	public String getTitle() {
		return cartTitle.getText(); 
	}
	
	public int getCartItemsSize() {
		return cartItems.size();
	}
	
	
	public List<String> getCartItemNames(){
		List<String> myCartItems = new ArrayList<String>();
		for (WebElement el: cartItems) {
			myCartItems.add(el.findElement(By.cssSelector("div.inventory_item_name")).getText());
		}
		return myCartItems;
	}
	
	public void removeItemsFromCart() {
		for (WebElement el: cartItems) {
			el.findElement(By.cssSelector("button.cart_button")).click();
		}
	}
	
	
	public void clickContinueShoppingBtn() {
		continueShopBtn.click();
	}
	
	public void clickCheckoutBtn() {
		checkoutBtn.click();
	}

}
