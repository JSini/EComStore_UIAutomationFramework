package com.ecom.pageobject;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutTwoPage {
	
	WebDriver driver;
	
	public CheckOutTwoPage(WebDriver rdriver) {
		PageFactory.initElements(rdriver, this);
	}
	
	//Page title
	@FindBy(css = "div.header_container div.header_secondary_container")
	WebElement pageTitle;
	
	@FindBy(css = "div.cart_list div.cart_item")
	List<WebElement> checkOutItems;
	
	@FindBy(css = "div.summary_info div.summary_value_label")
	WebElement paymentInfoValue;
	
	@FindBy(css = "div.summary_info div.shipping-info-value")
	WebElement shippingInfoValue;
	
	@FindBy(css = "div.summary_subtotal_label")
	WebElement itemTotalCost;
	
	@FindBy(css = "div.summary_tax_label")
	WebElement tax;
	
	@FindBy(css = "div.summary_total_label")
	WebElement finalTotalCost;
	
	@FindBy(css = "button#finish")
	WebElement finishBtn;
	
	@FindBy(css = "button#cancel")
	WebElement cancelBtn;
	
	
	
	public String getPageTitle() {
		return pageTitle.getText();
	}
	
	public int getCheckOutItemsSize() {
		return checkOutItems.size();
	}
	
	public List<String> getCheckOutItems(){
		List<String> myCheckoutItems = new ArrayList<String>();
		for (WebElement el:checkOutItems) {
			myCheckoutItems.add(el.findElement(By.cssSelector("div.inventory_item_name")).getText());
		}
		return myCheckoutItems;
		
	}
	
	
	public String getPaymentInfo() {
		return paymentInfoValue.getText();
	}
	
	public float getItemTotaCost() {
		float totalCost = Float.parseFloat(itemTotalCost.getText().replace("Item total: $", ""));
		return totalCost;
	}
	
	public String getShippingValue() {
		return shippingInfoValue.getText();
	}
	
	public float getTaxCost() {
		float taxCost = Float.parseFloat(tax.getText().replace("Tax: $", ""));
		return taxCost;
	}
	
	public float getFinalCost() {
		float finalCost = Float.parseFloat(finalTotalCost.getText().replace("Total: $", ""));
		return finalCost;
	}
	
	public void clickFinishBtn() {
		finishBtn.click();
	}
	
	public void clickCancelBtn() {
		cancelBtn.click();
	}
	
}
