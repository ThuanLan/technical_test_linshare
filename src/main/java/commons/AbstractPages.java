package commons;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import linShare.pageUIs.CommonPageUI;

public class AbstractPages {

	private JavascriptExecutor js;
	private By byXpath;
	private Actions action;
	private WebElement element;
	private WebDriverWait waitExplicit;
	private Select select;
	private Date date;

	public void openUrl(WebDriver driver, String urlValue) {
		driver.get(urlValue);
	}

	public String getTextElement(WebDriver driver, String locator) {
		return findElementByXpath(driver, locator).getText();
	}

	public String getTextElement(WebDriver driver, String locator, String... values) {
		return findElementByXpath(driver, locator, values).getText();
	}

	// Find Element
	public WebElement findElementByXpath(WebDriver driver, String locator) {
		return driver.findElement(byXpathLocator(locator));
	}

	public WebElement findElementByXpath(WebDriver driver, String locator, String... values) {
		locator = String.format(locator, (Object[]) values);
		return driver.findElement(byXpathLocator(locator));
	}

	public List<WebElement> findElementsByXpath(WebDriver driver, String locator, String... values) {
		locator = String.format(locator, (Object[]) values);
		return driver.findElements(byXpathLocator(locator));
	}

	public List<WebElement> findElementsByXpath(WebDriver driver, String locator) {
		return driver.findElements(byXpathLocator(locator));
	}

	public By byXpathLocator(String locator) {
		return By.xpath(locator);
	}

	public By byXpathLocator(String locator, String... values) {
		locator = String.format(locator, (Object[]) values);
		return By.xpath(locator);
	}

	public void clickToElement(WebDriver driver, String locator) {
		element = findElementByXpath(driver, locator);
		if (driver.toString().contains("internet explorer")) {
			clickToElementByJS(driver, locator);
			sleepInSecond(3);
		} else {
			element.click();
		}
	}

	public void clickToElement(WebDriver driver, String locator, String... values) {
		element = findElementByXpath(driver, locator, values);
		System.out.println(element);
		if (driver.toString().contains("internet explorer")) {
			clickToElementByJS(driver, locator, values);
			sleepInSecond(3);
		} else {
			element.click();
		}

	}

	public void clickToElementByJS(WebDriver driver, String locator) {
		js = (JavascriptExecutor) driver;
		element = findElementByXpath(driver, locator);
		js.executeScript("arguments[0].click();", element);
	}

	public void clickToElementByJS(WebDriver driver, String locator, String... values) {
		js = (JavascriptExecutor) driver;
		element = findElementByXpath(driver, locator, values);
		js.executeScript("arguments[0].click();", element);
	}

	public void sendkeyToElementByJS(WebElement element, String value) {
		js.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
	}

	public void sendkeyToElement(WebDriver driver, String locator, String value) {
		findElementByXpath(driver, locator).clear();
		findElementByXpath(driver, locator).sendKeys(value);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void sendkeyToElement(WebDriver driver, String locator, String valueToSendkey, String... values) {
		findElementByXpath(driver, locator, values).clear();
		findElementByXpath(driver, locator, values).sendKeys(valueToSendkey);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void selectAnItemInDropdown(WebDriver driver, String locator, String valueItem) {
		element = findElementByXpath(driver, locator);
		select = new Select(element);
		select.selectByVisibleText(valueItem);
	}

	public void selectAnItemInDropdown(WebDriver driver, String locator, String valueItem, String... values) {
		element = findElementByXpath(driver, locator, values);
		select = new Select(element);
		select.selectByVisibleText(valueItem);
	}

	public int countElementNumber(WebDriver driver, String locator) {
		return findElementsByXpath(driver, locator).size();
	}

	public boolean isElementDisplayed(WebDriver driver, String locator) {
		overriderGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		try {
			element = findElementByXpath(driver, locator);
			overriderGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
			return element.isDisplayed();
		} catch (Exception ex) {
			overriderGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
			return false;
		}
	}

	public boolean isElementDisplayed(WebDriver driver, String locator, String... values) {
		try {
			element = findElementByXpath(driver, locator, values);
			System.out.println(element);
			return element.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isElementEnabled(WebDriver driver, String locator) {
		try {
			element = findElementByXpath(driver, locator);
			return element.isEnabled();
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isElementEnabled(WebDriver driver, String locator, String... values) {
		try {
			element = findElementByXpath(driver, locator, values);
			return element.isEnabled();
		} catch (Exception ex) {
			return false;
		}
	}

	public void waitToElementVisible(WebDriver driver, String locator) {
		byXpath = byXpathLocator(locator);
		waitExplicit = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(byXpath));
	}

	public void waitToElementVisible(WebDriver driver, String locator, String... values) {
		byXpath = byXpathLocator(locator, values);
		waitExplicit = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(byXpath));
	}

	public void waitToElementClickable(WebDriver driver, String locator) {
		date = new Date();
		By byLocator = byXpathLocator(locator);
		waitExplicit = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		overriderGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
		try {
			waitExplicit.until(ExpectedConditions.elementToBeClickable(byLocator));
		} catch (org.openqa.selenium.TimeoutException ex) {
			ex.printStackTrace();
		}
		overriderGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);

	}

	// Wait for dynamic element to be clickable
	public void waitToElementClickable(WebDriver driver, String locator, String... values) {
		date = new Date();
		By byLocator = byXpathLocator(locator, values);
		waitExplicit = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		overriderGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
		try {
			waitExplicit.until(ExpectedConditions.elementToBeClickable(byLocator));
		} catch (org.openqa.selenium.TimeoutException ex) {
			ex.printStackTrace();
		}
		overriderGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
	}

	public void waitToElementInVisible(WebDriver driver, String locator) {
		date = new Date();
		By byLocator = byXpathLocator(locator);
		waitExplicit = new WebDriverWait(driver, GlobalConstants.SHORT_TIMEOUT);
		overriderGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		try {
			waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(byLocator));
		} catch (org.openqa.selenium.TimeoutException ex) {
			ex.printStackTrace();
		}
		overriderGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
	}

	public boolean isElementUndisplayed(WebDriver driver, String locator) {
		date = new Date();
		overriderGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		List<WebElement> elements = driver.findElements(By.xpath(locator));

		if (elements.size() == 0) {
			overriderGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			return true;

		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			overriderGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			return true;
		} else {
			overriderGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			return false;
		}
	}

	private void overriderGlobalTimeout(WebDriver driver, long timeOut) {
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}

	public boolean isElementUndisplayed(WebDriver driver, String locator, String... values) {
		overriderGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		locator = String.format(locator, (Object[]) values);
		List<WebElement> elements = driver.findElements(By.xpath(locator));
		if (elements.size() == 0) {
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isElementSelected(WebDriver driver, String locator) {
		try {
			element = findElementByXpath(driver, locator);
			return element.isSelected();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public boolean isElementSelected(WebDriver driver, String locator, String... values) {
		try {
			element = findElementByXpath(driver, locator, values);
			return element.isSelected();
		} catch (Exception ex) {
			return false;
		}
	}

	public int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(100000);
	}

	public void selectItemInCustomDropdown(WebDriver driver, String parentXpath, String allItemXpath, String expectedText) {

		driver.findElement(By.xpath(parentXpath)).click();
		List<WebElement> allItems = driver.findElements(By.xpath(allItemXpath));
		waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));
		for (WebElement item : allItems) {
			if (item.getText().contains(expectedText)) {
				item.click();
				break;
			}
		}
	}

	public void removeAtributeInDOM(WebDriver driver, String locator, String atributeRemove) {
		js = (JavascriptExecutor) driver;
		element = findElementByXpath(driver, locator);
		js.executeScript("arguments[0].removeAttribute('" + atributeRemove + "');", element);
		sleepInSecond(1);
	}

	public void removeAtributeInDOM(WebDriver driver, String locator, String atributeRemove, String... values) {
		js = (JavascriptExecutor) driver;
		element = findElementByXpath(driver, locator, values);
		js.executeScript("arguments[0].removeAttribute('" + atributeRemove + "');", element);
		sleepInSecond(1);
	}

	public int randomRowNumber(int rowNumber) {
		Random rand = new Random();
		return (2 + rand.nextInt(rowNumber - 1));
	}
}
