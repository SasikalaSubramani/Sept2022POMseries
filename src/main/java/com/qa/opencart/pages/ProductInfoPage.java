package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class ProductInfoPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By productHeader = By.cssSelector("div#content h1");
	private By productImages = By.cssSelector("a.thumbnail");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPricingData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	
	private Map<String, String> productMap;
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		
	}
	public String getProductHeader() {
		return eleUtil.doGetElementText(productHeader);
	}
	public int getProductImagesCount() {
		int imagesCount = eleUtil.waitForElementsVisible(productImages, TimeUtil.DEFAULT_TIME_OUT).size();
		System.out.println("Images Count ---->" + imagesCount);
		return imagesCount;
	}
	//HashMap --> maintains no order
	//Linked HashMap--> maintains insertion order
	//TreeMap ---->Maintains alphabetical order
	
	
	public Map<String, String> getProductInformation() {
		productMap = new HashMap<String,String>();
//		productMap = new LinkedHashMap<String,String>();
//		productMap = new TreeMap<String,String>();
		
		getProductMetaData();
		getProductPricingData();
		
		System.out.println(productMap);
		
		return productMap;
		
	}
	
//	Brand: Apple
//	Product Code: Product 18
//	Reward Points: 800
//	Availability: In Stock
	private void getProductMetaData() {
		List<WebElement> metaDataList = eleUtil.getElements(productMetaData);	
		System.out.println("product meta data count -->"+metaDataList.size());
		
		for(WebElement e : metaDataList) {
		String meta = e.getText();
		String metaData[] = meta.split(":");
		String metaKey = metaData[0].trim();
		String metaValue = metaData[1].trim();
		productMap.put(metaKey, metaValue);
		
		}
	}
	
	//$2,000.00
//	Ex Tax: $2,000.00
	
	private void getProductPricingData() {
		List<WebElement> metaPriceList = eleUtil.getElements(productPricingData);	
		System.out.println("product price count -->"+metaPriceList.size());
		String price = metaPriceList.get(0).getText().trim();
		String ExTaxprice = metaPriceList.get(1).getText().trim();
		
		productMap.put("actualPrice", price); 
		productMap.put("actualTaxPrice", ExTaxprice); 
	}
	
	
	

}
