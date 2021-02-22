package linShare.stepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Sleeper;
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
	CommonPageObject commonPage;

	public CommonPageSteps() {
		driver = Hooks.getAndCloseBrowser();
		verify = VerifyHelper.getVerify();
		commonPage = PageGeneratorManager.getCommonPage(driver);
	}

	
	@When("^Click on a \"([^\"]*)\" menu on the left menu$")
	public void clickOnAMenuOnTheLeftMenu(String leftMenuName)  {
		commonPage.selectUSLanguage();
		commonPage.clickOnTheLeftMenu(leftMenuName);
	}

}
