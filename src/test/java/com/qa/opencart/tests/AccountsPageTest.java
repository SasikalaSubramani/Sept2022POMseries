package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.AppErrors;

public class AccountsPageTest extends BaseTest {
	
	@BeforeClass
	public void setUp() {
		accPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	@Test
	public void accPageTitleTeset() {
		String actTitle = accPage.getAccPageTitle();
		System.out.println("acc page title : " + actTitle);
		Assert.assertEquals(actTitle, AppConstants.ACCOUNTS_PAGE_TITLE );
	}
	
	@Test
	public void accPageURLTeset() {
		String actURL = accPage.getAccPageURL();
		System.out.println("acc page title : " + actURL);
		Assert.assertTrue(actURL.contains(AppConstants.ACCOUNTS_PAGE_URL_FRACTION)  , AppErrors.NO_URL_MATCH);
	}
	@Test
	public void SerachExist() {
		Assert.assertTrue(accPage.isSearchExist());
	}
	@Test
	public void isLogoutExist() {
		Assert.assertTrue(accPage.isLogoutExist());		
	}
	
	@Test
	public void accPageHeadersTest() {
		List<String> accHeaderList = accPage.getAccountPageSectionHeaders(); 
		Assert.assertEquals(accHeaderList, AppConstants.EXPECTED_ACC_HEADERS_LIST);
	}
	@DataProvider
	public Object[][] getProductName() {
		return new Object [][] {
			{"Macbook"},
			{"iMac"},
			{"Samsung"}
		};
		
	}
	//TDD
	@Test(dataProvider = "getProductName")
	public void productSearchTest(String productName ) {
		resultsPage = accPage.performSearch(productName);
		String actTitle = resultsPage.getsearchPageTitle(productName);
		softAssert.assertEquals(actTitle, AppConstants.SEARCH_PAGE_TITLE + " "+productName);
		Assert.assertTrue(resultsPage.serachProductCount()>0);
	}
	

}
