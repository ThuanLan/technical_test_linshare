package linShare.pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPages;
import commons.GlobalConstants;
import linShare.pageUIs.CommonPageUI;
import linShare.pageUIs.LoginPageUI;
import net.bytebuddy.agent.builder.AgentBuilder.CircularityLock.Global;

public class LoginPageObject extends AbstractPages {
	WebDriver driver;

	public LoginPageObject(WebDriver _driver) {
		driver = _driver;
	}

	public String getLoginPageUrl() {
		getCurrentPageUrl(driver);
		return getCurrentPageUrl(driver);
	}

	public void inputToUserIDTextbox(String userID) {
		waitToElementVisible(driver, LoginPageUI.USER_NAME_TXT);
		sendkeyToElement(driver, LoginPageUI.USER_NAME_TXT, userID);

	}

	public void inputToPasswordTextbox(String password) {
		waitToElementVisible(driver, LoginPageUI.PASSWORD_TXT);
		sendkeyToElement(driver, LoginPageUI.PASSWORD_TXT, password);

	}

	public HomePageObject clickToLoginButton() {
		waitToElementClickable(driver, LoginPageUI.LOGIN_BTN);
		clickToElement(driver, LoginPageUI.LOGIN_BTN);
		return PageGeneratorManager.getHomePage(driver);
	}

	public HomePageObject loginAsUser(String userID, String password) {
		inputToUserIDTextbox(userID);
		inputToPasswordTextbox(password);
		return clickToLoginButton();
	}

	public void goToTheWebsite() {
		waitToElementVisible(driver, LoginPageUI.USER_NAME_TXT);
		sendkeyToElement(driver, LoginPageUI.USER_NAME_TXT, GlobalConstants.USERNAME);
		waitToElementVisible(driver, LoginPageUI.PASSWORD_TXT);
		sendkeyToElement(driver, LoginPageUI.PASSWORD_TXT, GlobalConstants.PASSWORD);
		waitToElementClickable(driver, LoginPageUI.LOGIN_BTN);
		clickToElement(driver, LoginPageUI.LOGIN_BTN);
	}

}
