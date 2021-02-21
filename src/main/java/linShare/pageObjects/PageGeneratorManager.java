package linShare.pageObjects;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {

	public static HomePageObject getHomePage(WebDriver driver) {
		return new HomePageObject(driver);
	}

	public static LoginPageObject getLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}

	public static CommonPageObject getCommonPage(WebDriver driver) {
		return new CommonPageObject(driver);
	}
	
	public static MySpacePageObject getMySpacePage(WebDriver driver) {
		return new MySpacePageObject(driver);
	}
	public static SharedSpacePageObject getSharedSpacePage(WebDriver driver) {
		return new SharedSpacePageObject(driver);
	}

}
