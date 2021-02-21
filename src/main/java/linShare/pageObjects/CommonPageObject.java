package linShare.pageObjects;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import commons.VerifyHelper;
import linShare.pageUIs.CommonPageUI;
import linShare.pageUIs.MySpacePageUI;
import commons.AbstractPages;

public class CommonPageObject extends AbstractPages {
	WebDriver driver;
	public static String getTextCompare;
	public static String getTextRandom;
	public static String getNavigateUrl;
	public static String getValue;

	public CommonPageObject(WebDriver _driver) {
		driver = _driver;
	}

	public void goToChildPageFromMenu(WebDriver driver, String childMenuName, String parentMenuName) {
		// check toggle is expanding.
		if (isElementUndisplayed(driver, CommonPageUI.MENU_TOGGLE_DISABLE)) {
			clickToElement(driver, CommonPageUI.DYNAMIC_MENU_LINK, parentMenuName);
			clickToElementByJS(driver, CommonPageUI.DYNAMIC_CHILD_MENU_LINK, childMenuName);
			sleepInSecond(1);
		} else {
			clickToElement(driver, CommonPageUI.MENU_TOGGLE);
			clickToElement(driver, CommonPageUI.DYNAMIC_MENU_LINK, parentMenuName);
			clickToElement(driver, CommonPageUI.DYNAMIC_CHILD_MENU_LINK, childMenuName);
			sleepInSecond(1);
		}
	}

	public void clickOnTheLeftMenu(String leftMenuName) {
		waitToElementClickable(driver, CommonPageUI.DYNAMIC_MENU_LINK, leftMenuName);
		clickToElement(driver, CommonPageUI.DYNAMIC_MENU_LINK, leftMenuName);
		sleepInSecond(5);
		
	}


}
