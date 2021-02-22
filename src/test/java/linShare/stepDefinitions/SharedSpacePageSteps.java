package linShare.stepDefinitions;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumberOption.Hooks;
import linShare.pageObjects.PageGeneratorManager;
import linShare.pageObjects.SharedSpacePageObject;

public class SharedSpacePageSteps {
	WebDriver driver;
	SharedSpacePageObject sharedSpacePage;

	public SharedSpacePageSteps() {
		driver = Hooks.getAndCloseBrowser();
		sharedSpacePage = PageGeneratorManager.getSharedSpacePage(driver);
	}

	@When("^Open the workgroup that you selected earlier$")
	public void openTheWorkgroupThatYouSelectedEarlier() {
		sharedSpacePage.inputWorkGroupIntoSearchTextBox();
		sharedSpacePage.clickToFoundWorkGroup();
	}

	@Then("^Verify if the file is copied to workgroup successfully$")
	public void verifyIfTheFileIsCopiedToWorkgroupSuccessfully() {
		Assert.assertTrue(sharedSpacePage.isFileDisplayed());
	}

}
