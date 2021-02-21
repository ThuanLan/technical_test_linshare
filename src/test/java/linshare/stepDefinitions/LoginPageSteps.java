package linshare.stepDefinitions;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import commons.DataHelper;
import commons.VerifyHelper;
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

	

	@When("^Click to view More Actions for a file$")
	public void clickToViewMoreActionsForAFile()  {
	    
	    
	}

	@When("^Select \"([^\"]*)\" option$")
	public void selectOption(String arg1)  {
	    
	    
	}

	@When("^Select a workgroup from the list$")
	public void selectAWorkgroupFromTheList()  {
	    
	    
	}

	@When("^Click Copy Here button$")
	public void clickCopyHereButton()  {
	    
	    
	}

	@When("^Click on Shared Space on the left menu$")
	public void clickOnSharedSpaceOnTheLeftMenu()  {
	    
	    
	}

	@When("^Wait until the page loads successfully$")
	public void waitUntilThePageLoadsSuccessfully()  {
	    
	    
	}

	@When("^Open the workgroup that you selected in step (\\d+)$")
	public void openTheWorkgroupThatYouSelectedInStep(int arg1)  {
	    
	    
	}

	@Then("^Verify if the file is copied to workgroup successfully$")
	public void verifyIfTheFileIsCopiedToWorkgroupSuccessfully()  {
	    
	    
	}

}
