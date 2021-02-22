package linShare.pageObjects;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

	public void clickOnTheLeftMenu(String leftMenuName) {
		waitToElementClickable(driver, CommonPageUI.DYNAMIC_MENU_LINK, leftMenuName);
		clickToElement(driver, CommonPageUI.DYNAMIC_MENU_LINK, leftMenuName);
		sleepInSecond(1);
	}

	public void selectUSLanguage() {
		if (isElementUndisplayed(driver, CommonPageUI.US_LANGUAGE_DROPDOWN)) {
			selectItemInCustomDropdown(driver,CommonPageUI.CURRENT_LANGUAGE_ICON,CommonPageUI.LANGUAGE_DROPDOWN,"US");
			sleepInSecond(5);
		}
	}

}
