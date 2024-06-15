package com.ecom.rough;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Rough {
	
	@FindBy(css = "div#inventory_container div.inventory_list")
	static
	List<WebElement> InventoryItems;
	
	
	static void print(List<WebElement> InventoryItems) {
		System.out.println(InventoryItems);
	}
	
	public static void main(String[] args) {
		
		print(InventoryItems);
		
		
	}
	
	
	
}
