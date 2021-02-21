package linShare.pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPages;
import linShare.pageUIs.HomePageUI;

public class HomePageObject extends AbstractPages {
	WebDriver driver;

	public HomePageObject(WebDriver _driver) {
		driver = _driver;
	}

	public boolean isWelcomeMessageDisplayed() {
		waitToElementVisible(driver, HomePageUI.WELCOME_MSG);
		return isElementDisplayed(driver, HomePageUI.WELCOME_MSG);
	}

}
