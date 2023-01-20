package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {

	public Properties prop;
	public OptionsManager optionsManager;
	
	public static String highlight;
	
	public static ThreadLocal <WebDriver> tlDriver = new ThreadLocal <WebDriver>();

	public WebDriver initDriver(Properties prop) {
		
		String browserName = prop.getProperty("browser").trim();
		System.out.println("Launching browser : " + browserName);
		
		highlight = prop.getProperty("highlight");
		
		optionsManager = new OptionsManager(prop);

		switch (browserName.toLowerCase()) {
		case "chrome":
//			driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			break;
			
		case "firefox":
//			driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			break;
			
		case "safari":
//			driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
			break;
			
		case "edge":
//			driver = new EdgeDriver();
			tlDriver.set(new EdgeDriver());
			break;

		default:
			System.out.println("plz pass the right browser...." + browserName);
			break;
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		
		return getDriver();
	}
	// get the local thread copy of the driver
	public synchronized static WebDriver getDriver() {
		return tlDriver.get();
	}
	
	public Properties initProp() {
		 prop= new Properties();
		 FileInputStream ip = null;
		 
		 //mvn clean install -Denv="qa"
		 
		 String envName = System.getProperty("env");
		 System.out.println("--------->Running test cases on environment------>" +envName);
		 
		 if(envName == null) {
			 System.out.println("No env is given......hence running the test cases on QA env......");
			 try {
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		 }
		 else
			 try {
		 
		switch (envName.toLowerCase()) {
		case "qa":			
			ip = new FileInputStream("./src/test/resources/config/qa.config.properties");			
			break;
		case "stage":			
			ip = new FileInputStream("./src/test/resources/config/stage.config.properties");			
			break;
		case "prod":			
			ip = new FileInputStream("./src/test/resources/config/config.properties");			
			break;

		default:
			System.out.println("Please pass the right env name........." + envName);
			break;
		}
			 } catch (FileNotFoundException e) {
					e.printStackTrace();
				}
		 
		 try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
		 return prop;
		
	}
	/**
	 * take screenshot
	 */
	public static String getScreenshot() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileHandler.copy(srcFile, destination);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;

	}

}
