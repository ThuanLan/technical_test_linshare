package linShare.stepDefinitions;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumberOption.Hooks;
import linShare.pageObjects.LoginPageObject;
import linShare.pageObjects.PageGeneratorManager;
import linShare.pageUIs.LoginPageUI;

public class LoginPageSteps {
	WebDriver driver;
	LoginPageObject loginPage;

	public LoginPageSteps()  {
		driver = Hooks.getAndCloseBrowser();
		loginPage = PageGeneratorManager.getLoginPage(driver);
	}

	@Given("^Login to the system$")
	public void loginToTheSystem()  {
	    loginPage.goToTheWebsite();
	}

}
