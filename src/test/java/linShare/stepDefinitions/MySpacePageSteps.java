package linShare.stepDefinitions;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import commons.VerifyHelper;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumberOption.Hooks;
import linShare.pageObjects.LoginPageObject;
import linShare.pageObjects.MySpacePageObject;
import linShare.pageObjects.PageGeneratorManager;
import linShare.pageUIs.LoginPageUI;

public class MySpacePageSteps {
	WebDriver driver;
	MySpacePageObject mySpacePage;

	public MySpacePageSteps() {
		driver = Hooks.getAndCloseBrowser();
		mySpacePage = PageGeneratorManager.getMySpacePage(driver);
	}

	@When("^Click to view More Actions for a file$")
	public void clickToViewMoreActionsForAFile() {
		mySpacePage.clicktoViewMoreActions();
		
	}

	@When("^Select \"([^\"]*)\" option$")
	public void selectOption(String option) {
		mySpacePage.selectAnOptionOnChildMenu(option);
	}

	@When("^Select a workgroup from the list$")
	public void selectAWorkgroupFromTheList() {
		mySpacePage.selectAWorkGroup();
	}

	@When("^Click Copy Here button$")
	public void clickCopyHereButton() {
		mySpacePage.clickOnCopyHereButton();
	}

}
