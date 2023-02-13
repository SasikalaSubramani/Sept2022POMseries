package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	
	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	private EdgeOptions eo;
	
	public OptionsManager(Properties prop) {
		this.prop = prop;
	}
	
	public ChromeOptions getChromeOptions() {
		co = new ChromeOptions();
		
		if(Boolean.parseBoolean(prop.getProperty("remote"))) {
			co.setCapability("enableVNC", true);
			co.setPlatformName("linux");	
		}
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
		
		if(Boolean.parseBoolean(prop.getProperty("remote"))) {
			fo.setCapability("enableVNC", true);
			fo.setPlatformName("linux");	
		}
		
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

	public EdgeOptions getEdgeOptions() {
		eo = new EdgeOptions();
		
		if(Boolean.parseBoolean(prop.getProperty("remote"))) {
			eo.setCapability("enableVNC", true);
			eo.setPlatformName("linux");	
		}
		
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			System.out.println("......Running the tests in headless mode");
			eo.setHeadless(true);
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			System.out.println("......Running the tests in incognito mode");
			eo.addArguments("--incognito");
		}
		return eo;
		
	}
}
