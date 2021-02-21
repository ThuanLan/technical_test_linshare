package linshare.stepDefinitions;

import org.openqa.selenium.WebDriver;

import commons.VerifyHelper;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumberOption.Hooks;
import linShare.pageObjects.HomePageObject;
import linShare.pageObjects.PageGeneratorManager;
import linShare.pageUIs.HomePageUI;

public class HomePageSteps {
	WebDriver driver;
	HomePageObject homePage;
	VerifyHelper verify;

	public HomePageSteps() {
		driver = Hooks.getAndCloseBrowser();
		homePage = PageGeneratorManager.getHomePage(driver);
		verify = VerifyHelper.getVerify();
		System.out.println("Driver at Home Page = " + driver.toString());
	}

	
}
