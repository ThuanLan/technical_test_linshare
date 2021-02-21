package linshare.stepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Sleeper;

import commons.DataHelper;
import commons.VerifyHelper;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumberOption.Hooks;
import linShare.pageObjects.CommonPageObject;
import linShare.pageObjects.PageGeneratorManager;
import linShare.pageUIs.CommonPageUI;

public class CommonPageSteps {

	WebDriver driver;
	VerifyHelper verify;
	DataHelper data;
	CommonPageObject commonPage;

	public CommonPageSteps() {
		driver = Hooks.getAndCloseBrowser();
		verify = VerifyHelper.getVerify();
		data = DataHelper.getData();
		commonPage = PageGeneratorManager.getCommonPage(driver);
	}

	// Go to screen
	@Given("^Go to \"([^\"]*)\" screen from menu \"([^\"]*)\"$")
	public void goToScreenFromMenu(String childMenuName, String parentMenuName) {
		commonPage.goToChildPageFromMenu(driver, childMenuName, parentMenuName);
	}

	
	
	@When("^Click on My Space on the left menu$")
	public void clickOnMySpaceOnTheLeftMenu()  {
		commonPage.clickOnTheLeftMenu();
	    
	}

}
