package com.ecom.pageobject;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InventoryPage {
	
	WebDriver driver;
	
	public InventoryPage(WebDriver rdriver) {
		PageFactory.initElements(rdriver, this);
	}
	
	@FindBy(css = "div.header_secondary_container span")
	WebElement InventoryTitle;
	
	@FindBy(css = "div.bm-burger-button button#react-burger-menu-btn")
	WebElement menuButton;
	
	@FindBy(css = "div.bm-menu a#logout_sidebar_link")
	WebElement logoutLink;
	
	//cart locator 
	@FindBy(css = "a.shopping_cart_link")
	WebElement cartLink;
	
	//cart badge
	@FindBy(css = "a.shopping_cart_link span.shopping_cart_badge")
	WebElement cartBadge;
	
	//find inventory items list 
	@FindBy(css = "div#inventory_container div.inventory_list div.inventory_item")
	List<WebElement> inventoryItems;
	
	//TODO filter objects
	//TODO filter functionsa
	
	
	public String getInventoryTitle() {
		String text = InventoryTitle.getText();
		return text;
	}
	
	public void clickMenuButton() {
		menuButton.click();
	}
	
	public void clickLogOut() {
		menuButton.click();
		logoutLink.click();
	}
	
	public List<WebElement> getInventoryItems() {
		return inventoryItems;
	}
	
	public int getInventoryItemSize() {
		return inventoryItems.size();
	}
	
	public List<String> getInventoryItemNames() {
		List<String> items = new ArrayList<String>();
		for (WebElement item: inventoryItems) {
			items.add(item.findElement(By.cssSelector("div.inventory_item_name")).getText());
		}
		
		return items;
	}
	
	public WebElement getAnInventoryItem(int index) {
		return inventoryItems.get(index);
	}
	
	//get item name
	public String getItemName(WebElement item) {
		return item.findElement(By.cssSelector("div.inventory_item_name")).getText();
	}
	
	public String getItemPrice(WebElement item) {
		return item.findElement(By.cssSelector("div.inventory_item_price")).getText();	
	}
	
	//add item to cart
	public void addProduct(WebElement item) {
		item.findElement(By.cssSelector("button.btn_inventory")).click();
	}
	
	public WebElement getCartBadgeElement() {
		return cartBadge;
	}
	public String getCartBadge() {
		return cartBadge.getText();
	}
	
	public void clickCart() {
		cartLink.click();
	}
}
