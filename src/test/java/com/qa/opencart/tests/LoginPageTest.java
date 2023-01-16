package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.AppErrors;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Epic("Epic - 100: Design login page for open cart shopping application")
@Story("US - 101: Create login page dunctionality for open cart login page")
public class LoginPageTest extends BaseTest {
	
	
	@Description("login page title test")
	@Severity(SeverityLevel.TRIVIAL)
	@Test
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		System.out.println("Login Page Title is: " + actTitle);
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE , AppErrors.NO_TITLE_MATCH);
	}
	@Description("login page url test")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void logingPageURLTest() {
		String actURL = loginPage.getLoginPageUrl();
		System.out.println("Login Page URL is: " + actURL);
		Assert.assertTrue(actURL.contains(AppConstants.ACCOUNTS_PAGE_URL_FRACTION), AppErrors.NO_URL_MATCH);
	}
	@Description("forgot password link on login page test")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void forgotPwdLinkExistTest() {
		Assert.assertTrue(loginPage.isforgotPwdLinkExist());

	}
	@Description("login test")
	@Severity(SeverityLevel.BLOCKER)
	@Test
	public void loginTest() {
		accPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		Assert.assertTrue(accPage.isLogoutExist(),AppErrors.LOGIN_UNSUCCESSFUL);

	}

}
