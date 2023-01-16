package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

import io.qameta.allure.Step;

public class LoginPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	// 1.private By locators
	
	private By emailID = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");
	
	// 2. page constructor 
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	//3. page actions
	@Step ("getting login page title")
	public String getLoginPageTitle() {
		return eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, TimeUtil.DEFAULT_TIME_OUT);
	}
	@Step ("getting login page title")
	public String getLoginPageUrl() {
		return eleUtil.waitForURLContains(AppConstants.ACCOUNTS_PAGE_URL_FRACTION, TimeUtil.DEFAULT_TIME_OUT);
	}
	@Step ("checking forgot password link existance")
	public boolean isforgotPwdLinkExist() {
		return eleUtil.doIsDisplayed(forgotPwdLink);
	}
	@Step ("login with username:{0} and password: {1}")
	public AccountstPage doLogin(String un, String pwd) {
		System.out.println("Creds are : " + un + " : " + pwd);
		 eleUtil.waitForElementVisible(emailID, TimeUtil.DEFAULT_TIME_OUT).sendKeys(un);
		 eleUtil.waitForElementVisible(password, TimeUtil.DEFAULT_TIME_OUT).sendKeys(pwd);
		 eleUtil.doClick(loginBtn);
		 return new AccountstPage(driver);
	}
	@Step("Navigation to Register page")
	public RegPage navigateToRegisterPage() {
		eleUtil.doClick(registerLink);
		return new RegPage(driver);
	}
	
	

}
