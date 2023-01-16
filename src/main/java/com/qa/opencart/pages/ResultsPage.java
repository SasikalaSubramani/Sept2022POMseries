package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class ResultsPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By searchProducts = By.cssSelector("div.product-layout");

	public ResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public String getsearchPageTitle(String productName) {
		return eleUtil.waitForTitleContains(productName, TimeUtil.DEFAULT_TIME_OUT);
	}
	
	
	public int serachProductCount() {
		int productCount = eleUtil.waitForElementsVisible(searchProducts, TimeUtil.DEFAULT_TIME_OUT).size();
		return productCount;
	}
	
	public ProductInfoPage selectProduct(String mainProductName) {
		System.out.println("The main product name is "+ mainProductName);
		eleUtil.doClick(By.linkText(mainProductName));
		return new ProductInfoPage(driver);
	}
	
	

}
