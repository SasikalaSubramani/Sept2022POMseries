package com.qa.opencart.utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtil {

	private WebDriver driver;
	private JavaScriptUtil jsUtil;
	

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		jsUtil = new JavaScriptUtil(driver);
	}

	public WebElement getElement(By locator) {
		WebElement ele = driver.findElement(locator);;
		if(Boolean.parseBoolean(DriverFactory.highlight)) {
			jsUtil.flash(ele);			
		}
		return ele;
	}

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	public void doSendKeys(By locator, String value) {
		WebElement ele =getElement(locator);
		ele.clear();
		ele.sendKeys(value);
	}

	public void doClick(By locator) {
		getElement(locator).click();

	}

	public String doGetElementText(By locator) {
		return getElement(locator).getText();
	}

	public String doGetAttribute(By locator, String attrName) {
		return getElement(locator).getAttribute(attrName);
	}

	public void doClickOnElement(By locator, String text) {
		List<WebElement> list = getElements(locator);

		for (WebElement e : list) {
			String textvalue = e.getText();
			System.out.println(textvalue);
			if (textvalue.equals(text)) {
				e.click();
				break;
			}

		}

	}

	public boolean doIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

	/**
	 * isDiplayed logic using findElements method
	 * 
	 * @param locator
	 * @return
	 */
	public boolean isSingleElementExist(By locator) {
		int actCount = getElements(locator).size();
		if (actCount == 1) {
			return true;
		}
		return false;
	}

	public boolean isTwoElementExist(By locator) {
		int actCount = getElements(locator).size();
		if (actCount == 2) {
			return true;
		}
		return false;
	}

	public boolean isMultipleElementExist(By locator) {
		int actCount = getElements(locator).size();
		if (actCount > 1) {
			return true;
		}
		return false;
	}

	public boolean isMultipleElementExist(By locator, int expElementCount) {
		int actCount = getElements(locator).size();
		if (actCount == expElementCount) {
			return true;
		}
		return false;
	}

	// ************* Drop Down selection methods ********************
	public void doSelectDropDownByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}

	public void doSelectDropDownByVisibleText(By locator, String text) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(text);
	}

	public void doSelectDropDownByValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}

	public void doSelectDropDownValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		List<WebElement> optionsList = select.getOptions();

		for (WebElement e : optionsList) {
			String text = e.getText();
			System.out.println(text);

			if (text.equals(value)) {
				e.click();
				break;
			}
		}

	}

	// *******************************Wait Util ************************
	/**
	 * An expectation for checking that an element is present on the DOM of a page.
	 * This does notnecessarily mean that the element is visible
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public WebElement waitForElementPresence(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}

	/**
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible. Visibility means that the element is not only displayed but also
	 * has a height and width that is greater than 0.
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public WebElement waitForElementVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}

	/**
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible. Visibility means that the element is not only displayed but also
	 * has a height and width that is greater than 0.
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public WebElement waitForElementVisible(By locator, int timeOut, int intervalTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofMillis(intervalTime));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}

	/**
	 * An expectation for checking that all elements present on the web page that
	 * match the locatorare visible. Visibility means that the elements are not only
	 * displayed but also have a heightand width that is greater than 0.
	 * 
	 * @param locator
	 * @param timeout
	 * @return
	 */
	public List<WebElement> waitForElementsVisible(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	/**
	 * An expectation for checking that there is at least one element present on a
	 * web page.
	 * 
	 * @param locator
	 * @param timeout
	 * @return
	 */

	public List<WebElement> waitForElementsPresence(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}

	// **************************Actions Class *********************************

	public void selectSubMenu(String parentHtmlTag, String childHtmlTag, String parentMenu, String childMenu)
			throws InterruptedException {
		By parentMenuLocator = By.xpath("//" + parentHtmlTag + "[(text()='" + parentMenu + "')]");
		By childtMenuLocator = By.xpath("//" + childHtmlTag + "[(text()='" + childMenu + "')]");

		WebElement parentMenuElement = getElement(parentMenuLocator);

		Actions act = new Actions(driver);

		act.moveToElement(parentMenuElement).build().perform();
		getElement(parentMenuLocator).click();
		Thread.sleep(3000);

		getElement(childtMenuLocator).click();

	}

	public void doActionsSendKeys(By locator, String value) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value).build().perform();
	}

	public void doActionsClick(By locator) {
		Actions act = new Actions(driver);
		act.click(getElement(locator)).build().perform();
	}

	// ******************wait Utils for non web
	// elements*******************************
	public String waitForTitleContains(String titleFractionValue, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		if (wait.until(ExpectedConditions.titleContains(titleFractionValue))) {
			return driver.getTitle();

		} else {
			System.out.println("Expected title is not visible");
			return null;
		}

	}

	public String waitForTitleIs(String titleValue, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		if (wait.until(ExpectedConditions.titleIs(titleValue))) {
			return driver.getTitle();

		} else {
			System.out.println("Expected title is not visible");
			return null;
		}

	}

	public String waitForURLContains(String urlFractionVal, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		if (wait.until(ExpectedConditions.urlContains(urlFractionVal))) {
			return driver.getCurrentUrl();

		} else {
			System.out.println("Expected URL is not visible");
			return null;
		}

	}

	public String waitForURL(String urlValue, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		if (wait.until(ExpectedConditions.urlToBe(urlValue))) {
			return driver.getCurrentUrl();

		} else {
			System.out.println("Expected URL is not visible");
			return null;
		}

	}

	public Alert waitForAlertPresentAndSwitch(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.alertIsPresent());

	}

	public String getAlertText(int timeout) {
		return waitForAlertPresentAndSwitch(timeout).getText();
	}

	public void acceptAlert(int timeout) {
		waitForAlertPresentAndSwitch(timeout).accept();
		;
	}

	public void dismissAlert(int timeout) {
		waitForAlertPresentAndSwitch(timeout).dismiss();

	}

	public void alertSendKeys(int timeout, String value) {
		waitForAlertPresentAndSwitch(timeout).sendKeys(value);
	}

	public void waitForFramePresenceAndSwitch(int frameIndex, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
	}

	public void waitForFramePresenceAndSwitch(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
	}

	public void waitForFramePresenceAndSwitch(WebElement frameElemeent, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElemeent));
	}

	public void waitForFramePresenceAndSwitch(String nameOrID, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(nameOrID));
	}

	/**
	 * An expectation for checking an element is visible and enabled such that you
	 * can click it.
	 * 
	 * @param locator
	 * @param timeout
	 */
	public void clickElementWhenready(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	/**
	 * An expectation for checking an element is visible and enabled such that you
	 * can click it.
	 * 
	 * @param locator
	 * @param timeout
	 * @param intervalTime
	 */
	public void clickElementWhenready(By locator, int timeout, int intervalTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout), Duration.ofSeconds(intervalTime));
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	// Implementation is same--> Functional wise both the methods are same

	public WebElement waitForElementToBeVisibleWithFluentWiat(By locator, int timeOut, int intervalTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(intervalTime)).ignoring(ElementNotInteractableException.class)
				.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class)
				.withMessage("Element not found on the page....");
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}

	public WebElement waitForElementToBeVisible(By locator, int timeOut, int intervalTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofSeconds(intervalTime));
		wait.ignoring(NoSuchElementException.class).ignoring(ElementNotInteractableException.class)
				.withMessage("Element not found on the page....");

		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}

	// *************************CUSTOM WAIT *********************

	public WebElement retryingElement(By locator, int timeOut) {
		WebElement element = null;
		int attempts = 0;

		while (attempts < timeOut) {
			try {
				element = getElement(locator);
				System.out.println("element found in attempts: " + attempts);
				break;
			} catch (NoSuchElementException e) {
				System.out.println("element is not found in attempt: " + attempts + " for " + locator);
				TimeUtil.applyWait(500);
			}
			attempts++;
		}
		if (element == null) {
			System.out.println("element is not found.......tried for: " + timeOut + " sec "
					+ " with the interval of 500 milli sec");

//			throw new FrameworkException("TimeOutException");

		}
		return element;
	}

	public WebElement retryingElement(By locator, int timeOut, int intervalTime) {
		WebElement element = null;
		int attempts = 0;

		while (attempts < timeOut) {
			try {
				element = getElement(locator);
				System.out.println("element found in attempts: " + attempts);
				break;
			} catch (NoSuchElementException e) {
				System.out.println("element is not found in attempt: " + attempts + " for " + locator);
				TimeUtil.applyWait(intervalTime);
			}
		}
		attempts++;

		if (element == null) {
			System.out.println("element is not found.......tried for: " + timeOut + " sec " + " with the interval of "
					+ intervalTime + " sec");

//			throw new FrameworkException("TimeOutException");
		}
		return element;
	}

	public void WaitForPageLoad(int timeOut) {

		long endTime = System.currentTimeMillis() + timeOut;

		while (System.currentTimeMillis() < endTime) {

			JavascriptExecutor js = (JavascriptExecutor) driver;
			String pageState = js.executeScript("return document.readyState").toString();
			if (pageState.equals("complete")) {
				System.out.println("page DOM is fully laoded now...");
				break;
			}
		}

	}

}
