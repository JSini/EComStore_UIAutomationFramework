package com.ecom.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ecom.pageobject.InventoryPage;
import com.ecom.pageobject.LoginPage;
import com.ecom.utilities.ReadExcel;

public class TC_LoginTest_DataDriven extends BaseClass{
	
	
	@Test(dataProvider="LoginDataProvider")
	public void verifyLogin(String username, String password, String expectedText) throws IOException {
		
		
		
		LoginPage pg = new LoginPage(driver);
		
		pg.enterUsername(username);
		pg.enterPassword(password);
		logger.info("username:"+ username);
		
		pg.clickLoginBtn();
		logger.info("Clicked on Login!");
		
		
		
		InventoryPage inPg = new InventoryPage(driver);
		
		String pageHeading = inPg.getInventoryTitle();
		
		if (pageHeading.equals(expectedText)) {
			logger.info("Verify Login - Passed");
			Assert.assertTrue(true);
			inPg.clickMenuButton();
			inPg.clickLogOut();
			
		}
		else {
			logger.info("Verify Login - Passed");
			captureScreenshot(driver, "verifyLogin");
			Assert.assertTrue(false);
		}
		
		
	
	}
	
	
	@DataProvider(name="LoginDataProvider")
	public String[][] LoginDataProvider(){
		
		String filename = System.getProperty("user.dir") + "\\testdata\\testcases.xlsx";
		
		int totalrows = ReadExcel.getRowCount(filename, "LoginTestData");
		int totalcols = ReadExcel.getCellCount(filename, "LoginTestData");
		
		System.out.println(totalrows);
		System.out.println(totalcols);
		
		String data[][] = new String[totalrows-1][totalcols];
		
		for (int i=1; i<totalrows; i++) {
			for (int j=0; j<totalcols; j++) {
				data[i-1][j] = ReadExcel.getCellValue(filename, "LoginTestData", i, j);
			}
		}
		return data;
		
	}

}
