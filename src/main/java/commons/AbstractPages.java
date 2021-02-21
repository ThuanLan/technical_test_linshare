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

	// Open URL that input value
	public void openUrl(WebDriver driver, String urlValue) {
		driver.get(urlValue);
	}

	// Get page's title
	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	// Get current page's url
	public String getCurrentPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	// Back on browser
	public void back(WebDriver driver) {
		driver.navigate().back();
	}

	// Refresh page
	public void refreshPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	// Accep Alert
	public void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	// Cancel alert
	public void cancelAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	// Get text alert
	public String getTextAlert(WebDriver driver) {
		return driver.switchTo().alert().getText();
	}

	// Send key to alert
	public void senkeyToAlert(WebDriver driver, String value) {
		driver.switchTo().alert().sendKeys(value);

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

	// Find Dynamic locator
	public WebElement findElementByXpath(WebDriver driver, String locator, String... values) {
		locator = String.format(locator, (Object[]) values);
		return driver.findElement(byXpathLocator(locator));
	}

	// Find Dynamic locator
	public WebElement findElementByXpathAny(WebDriver driver, String locator, String... values) {
		locator = String.format(locator, (Object[]) values, (Object[]) values);
		return driver.findElement(byXpathLocator(locator));
	}

	// Find list dynamic elements
	public List<WebElement> findElementsByXpath(WebDriver driver, String locator, String... values) {
		highlightElement(driver, locator, values);
		locator = String.format(locator, (Object[]) values);
		return driver.findElements(byXpathLocator(locator));
	}

	// Find list elements
	public List<WebElement> findElementsByXpath(WebDriver driver, String locator) {
		highlightElement(driver, locator);
		return driver.findElements(byXpathLocator(locator));
	}

	// By Xpath
	public By byXpathLocator(String locator) {
		return By.xpath(locator);
	}

	// By Xpath for dynamic locator
	public By byXpathLocator(String locator, String... values) {
		locator = String.format(locator, (Object[]) values);
		return By.xpath(locator);
	}

	// Click on an Element
	public void clickToElement(WebDriver driver, String locator) {
		highlightElement(driver, locator);
		element = findElementByXpath(driver, locator);
		if (driver.toString().contains("internet explorer")) {
			clickToElementByJS(driver, locator);
			sleepInSecond(3);
		} else {
			element.click();
			sleepInSecond(1);
		}
	}

	public void clickToElement(WebDriver driver, String locator, String... values) {
		highlightElement(driver, locator, values);
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
		highlightElement(driver, locator);
		findElementByXpath(driver, locator).clear();
		findElementByXpath(driver, locator).sendKeys(value);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void sendkeyToElement(WebDriver driver, String locator, String valueToSendkey, String... values) {
		highlightElement(driver, locator, values);
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
		highlightElement(driver, locator);
		select = new Select(element);
		select.selectByVisibleText(valueItem);
	}

	public void selectAnItemInDropdown(WebDriver driver, String locator, String valueItem, String... values) {
		element = findElementByXpath(driver, locator, values);
		highlightElement(driver, locator, values);
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

	public void hoverMouseToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		highlightElement(driver, locator);
		element = findElementByXpath(driver, locator);
		action.moveToElement(element).perform();

	}

	public void hoverMouseToElement(WebDriver driver, String locator, String... values) {
		action = new Actions(driver);
		highlightElement(driver, locator, values);
		element = findElementByXpath(driver, locator, values);
		action.moveToElement(element).perform();

	}

	public String getAtribute(WebDriver driver, String locator, String atributeValue) {
		element = findElementByXpath(driver, locator);
		return element.getAttribute(atributeValue);
	}

	public String getAtribute(WebDriver driver, String locator, String atributeValue, String... values) {
		element = findElementByXpath(driver, locator, values);
		return element.getAttribute(atributeValue);
	}

	public void doubleClickAnElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		element = findElementByXpath(driver, locator);
		action.doubleClick(element).perform();
	}

	// Wait alert presence
	public void waitAlerPresence(WebDriver driver) {
		waitExplicit = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		waitExplicit.until(ExpectedConditions.alertIsPresent());
	}

	// Wait for element to display
	public void waitToElementVisible(WebDriver driver, String locator) {
		byXpath = byXpathLocator(locator);
		waitExplicit = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(byXpath));
	}

	// Wait for dynamic element to display
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
			// System.out.println("Start time for wai invisible = " + date.toString());
			waitExplicit.until(ExpectedConditions.elementToBeClickable(byLocator));
		} catch (org.openqa.selenium.TimeoutException ex) {
			ex.printStackTrace();
		}
		// System.out.println("End time for wai clickable = " + new Date().toString());
		overriderGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);

	}

	// Wait for dynamic element to be clickable
	public void waitToElementClickable(WebDriver driver, String locator, String... values) {
		date = new Date();
		By byLocator = byXpathLocator(locator, values);
		System.out.println("giá trị cần locator " +byLocator);
		waitExplicit = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		overriderGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
		try {
			// System.out.println("Start time for wai invisible = " + date.toString());
			waitExplicit.until(ExpectedConditions.elementToBeClickable(byLocator));
		} catch (org.openqa.selenium.TimeoutException ex) {
			ex.printStackTrace();
		}
		// System.out.println("End time for wai clickable = " + new Date().toString());
		overriderGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
	}

	// Wait for element to invisible
	public void waitToElementInVisible(WebDriver driver, String locator) {
		date = new Date();
		By byLocator = byXpathLocator(locator);
		waitExplicit = new WebDriverWait(driver, GlobalConstants.SHORT_TIMEOUT);
		overriderGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		try {
			System.out.println("Start time for wai invisible = " + date.toString());
			waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(byLocator));
		} catch (org.openqa.selenium.TimeoutException ex) {
			ex.printStackTrace();
		}
		System.out.println("End time for wai invisible = " + new Date().toString());
		overriderGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
	}

	public boolean isElementUndisplayed(WebDriver driver, String locator) {
		date = new Date();
		System.out.println("Start time = " + date.toString());
		overriderGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		List<WebElement> elements = driver.findElements(By.xpath(locator));

		if (elements.size() == 0) {
			System.out.println("Element not in DOM");
			System.out.println("End time = " + new Date().toString());
			overriderGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			return true;

		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			System.out.println("Element in DOM but not visible/ displayed");
			System.out.println("End time = " + new Date().toString());
			overriderGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			return true;
		} else {
			System.out.println("Element in DOM and visible");
			overriderGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			return false;
		}
	}

	private void overriderGlobalTimeout(WebDriver driver, long timeOut) {
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}

	public boolean isElementUndisplayed(WebDriver driver, String locator, String... values) {
		// date = new Date();
		// System.out.println("Start time = " + date.toString());
		overriderGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		locator = String.format(locator, (Object[]) values);
		List<WebElement> elements = driver.findElements(By.xpath(locator));
		if (elements.size() == 0) {
			// System.out.println("Element not in DOM");
			// System.out.println("End time = " + new Date().toString());
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			// System.out.println("Element in DOM but not visible/ displayed");
			// System.out.println("End time = " + new Date().toString());
			return true;
		} else {
			// System.out.println("Element in DOM and visible");
			return false;
		}
	}

	// Check selected Element
	public boolean isElementSelected(WebDriver driver, String locator) {
		try {
			highlightElement(driver, locator);
			element = findElementByXpath(driver, locator);
			return element.isSelected();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public boolean isElementSelected(WebDriver driver, String locator, String... values) {
		try {
			highlightElement(driver, locator, values);
			element = findElementByXpath(driver, locator, values);
			return element.isSelected();
		} catch (Exception ex) {
			return false;
		}
	}

	// Get random number
	public int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(100000);
	}

	// Get Text by JS function
	public String getTextByJS(WebDriver driver, String locator) {
		return (String) js.executeScript("return document.querySelector('" + locator + "').text");
	}

	// Hàm kiểm tra phần tử được chọn bằng javaScrip lấy content bên trong nó
	public String getTextByJSContent(WebDriver driver, String locator) {
		return (String) js.executeScript("return document.querySelector('" + locator + "').textContent");
	}

	// Verify text by JS function
	public boolean verifyTextInnerText(WebDriver driver, String textExpected) {
		String textActual = (String) js.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		System.out.println("Text Actual = " + textActual);
		return textActual.equals(textExpected);
	}

	// Switch to child windows (only 2 windows)
	public String switchToChildWindowByID(WebDriver driver, String parent) {
		Set<String> allWindows = driver.getWindowHandles();
		System.out.println("Count of windows: " + allWindows.size());
		for (String runWindows : allWindows) {
			if (!runWindows.equals(parent)) {
				driver.switchTo().window(runWindows);
				System.out.println(driver.getTitle());
			}

		}
		return driver.getTitle();
	}

	// Switch to windown by title
	public void switchToWindow(WebDriver driver, String windowTitle) {
		Set<String> windows = driver.getWindowHandles();
		for (String window : windows) {
			driver.switchTo().window(window);
			if (driver.getTitle().contains(windowTitle)) {
				return;
			}
		}
	}

	// Switch to child window (greater than 2 windows and title of the page are
	// unique)
	public void switchToWindowByTitle(WebDriver driver, String title) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			driver.switchTo().window(runWindows);
			String curentWin = driver.getTitle();
			if (curentWin.equals(title)) {
				break;
			}
		}
	}

	// Close all windows without parent window
	public boolean closeAllWindowsWithoutParent(WebDriver driver, String parentWindow) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			if (!runWindows.equals(parentWindow)) {
				driver.switchTo().window(runWindows);
				driver.close();
			}
		}
		driver.switchTo().window(parentWindow);
		if (driver.getWindowHandles().size() == 1)
			return true;
		else
			return false;
	}

	// Hàm cho những dropdown có thể input text vào dropdown list
	public void inputItemInDropdown(WebDriver driver, String parentXpath, String inputXpath, String expectedText) {
		// 1. Click vào thẻ chứa dropdown để nó xổ ra hết các items
		driver.findElement(By.xpath(parentXpath)).click();
		// 2. Input text vào textbox
		driver.findElement(By.xpath(inputXpath)).sendKeys(expectedText);
		// 3. Truyền phím Enter vào cho input text
		action.sendKeys(driver.findElement(By.xpath(inputXpath)), Keys.ENTER).perform();
	}

	// Hàm select Item trong dropdown list
	public void selectItemInCustomDropdown(WebDriver driver, String parentXpath, String allItemXpath, String expectedText) {

		// 1. Click vào dropdown để nó xổ hết các items trong xpath
		driver.findElement(By.xpath(parentXpath)).click();

		// 2. Khai báo một list các elements chứa all Elements bên trong
		List<WebElement> allItems = driver.findElements(By.xpath(allItemXpath));

		// 3. Wait cho tất cả các items được xuất hiện trong DOM
		waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));

		// 4. Get text từng item sau đó so sánh với item mình cần chọn
		for (WebElement item : allItems) {
			System.out.println("Text moi lan get Element =" + item.getText());
			if (item.getText().equals(expectedText)) {
				// Click vào item cần chọn
				item.click();
				break;
			}
		}
	}

	public void highlightElement(WebDriver driver, String locator) {
		js = (JavascriptExecutor) driver;
		element = findElementByXpath(driver, locator);
		String originalStyle = element.getAttribute("style");
		js.executeScript("arguments[0].setAttribute(arguments[1],arguments[2])", element, "style", "border: 2px solid #f20060; border-style: dashed;");
		sleepInSecond(1);
		js.executeScript("arguments[0].setAttribute(arguments[1],arguments[2])", element, "style", originalStyle);
	}

	public void highlightElement(WebDriver driver, String locator, String... values) {
		js = (JavascriptExecutor) driver;
		element = findElementByXpath(driver, locator, values);
		String originalStyle = element.getAttribute("style");
		js.executeScript("arguments[0].setAttribute(arguments[1],arguments[2])", element, "style", "border: 2px solid #f20060; border-style: dashed;");
		sleepInSecond(1);
		js.executeScript("arguments[0].setAttribute(arguments[1],arguments[2])", element, "style", originalStyle);
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

	public int getIndexOfColumn(WebDriver driver, String listHeaderXpath, String headerText) {
		List<WebElement> allItems = driver.findElements(By.xpath(listHeaderXpath));
		int columIndex = 0;
		int count = 0;
		for (WebElement item : allItems) {
			if (item.getText().contains(headerText) == true) {
				count = count + 1;
				columIndex = count;
				break;
			}
			count = count + 1;
		}
		return columIndex;
	}

	

	// get randomRowNumber on the table
	public int randomRowNumber(int rowNumber) {
		Random rand = new Random();
		return (2 + rand.nextInt(rowNumber - 1));
	}

	
}
