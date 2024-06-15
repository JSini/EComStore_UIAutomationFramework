package com.ecom.utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	
	Properties properties;
	
	String path = "C:\\Jp\\youtube\\selenium_projects\\EcommerceFramework\\EComStore\\configuration\\config.properties";
	
	
	//create constructor
	public ReadConfig() {
		
		try {
			properties = new Properties();
			FileInputStream fis = new FileInputStream(path);
			properties.load(fis);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String getBaseUrl() {
		String value = properties.getProperty("baseURL");
		
		if(value!=null)
			return value;
		else
			throw new RuntimeException("Url not specified in config file.");
	}
	
	
	public String getBrowser() {
		String value = properties.getProperty("browser");
		
		if (value!=null)
			return value;
		else
			throw new RuntimeException("browser info not specified in config file.");
	}

}
