package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	
	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	
	public OptionsManager(Properties prop) {
		this.prop = prop;
	}
	
	public ChromeOptions getChromeOptions() {
		co = new ChromeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			System.out.println("......Running the tests in headless mode");
			co.setHeadless(true);
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			System.out.println("......Running the tests in incognito mode");
			co.addArguments("--incognito");
		}
		return co;
		
	}
	public FirefoxOptions getFirefoxOptions() {
		fo = new FirefoxOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			System.out.println("......Running the tests in headless mode");
			fo.setHeadless(true);
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			System.out.println("......Running the tests in incognito mode");
			fo.addArguments("--incognito");
		}
		return fo;
		
	}

}
