package linShare.pageObjects;

import java.util.Random;

import org.openqa.selenium.WebDriver;

import commons.AbstractPages;
import commons.GlobalConstants;
import linShare.pageUIs.CommonPageUI;
import linShare.pageUIs.LoginPageUI;
import linShare.pageUIs.MySpacePageUI;

public class MySpacePageObject extends AbstractPages {
	WebDriver driver;
	public static String getFileName;
	public static String getWorkSpace;

	public MySpacePageObject(WebDriver _driver) {
		driver = _driver;
	}

	public void clicktoViewMoreActions() {
		clickOnRandomFile();
	}

	public String clickOnRandomFile() {
		int totalRowTable = findElementsByXpath(driver, CommonPageUI.TABLE_TOTAL_ROW).size();
		if (totalRowTable >= 1) {
			String rowNumber = String.valueOf(randomRowNumber(totalRowTable));
			System.out.println("Hang ngau nhien: " + rowNumber);
			waitToElementClickable(driver, MySpacePageUI.TABLE_VIEW_MORE_ICON, rowNumber);
			getFileName = getTextElement(driver, MySpacePageUI.TABLE_FILE_NAME, rowNumber);
			clickToElement(driver, MySpacePageUI.TABLE_VIEW_MORE_ICON, rowNumber);
			// sleepInSecond(5);
		} else {
			System.out.println("No data found!");
		}
		return getFileName;
	}

	public String clickOnRandomWorkGroup() {
		int totalGroups = findElementsByXpath(driver, MySpacePageUI.TOTAL_WORK_GROUP).size();
		if (totalGroups >= 1) {
			String rowNumber = String.valueOf(randomRowNumber(totalGroups));
			System.out.println("Group ngau nhien: " + rowNumber);
			waitToElementClickable(driver, MySpacePageUI.WORK_GROUP, rowNumber);
			getWorkSpace = getTextElement(driver, MySpacePageUI.WORK_GROUP, rowNumber);
			clickToElement(driver, MySpacePageUI.WORK_GROUP, rowNumber);
			sleepInSecond(5);
		} else {
			System.out.println("No data found!");
		}
		return getWorkSpace;
	}

	public int randomRowNumber(int rowNumber) {
		Random rand = new Random();
		return (1 + rand.nextInt(rowNumber));
	}

	public void selectAnOptionOnChildMenu(String option) {
		waitToElementClickable(driver, MySpacePageUI.VIEW_MORE_DIALOG, option);
		clickToElement(driver, MySpacePageUI.VIEW_MORE_DIALOG, option);
	}

	public void selectAWorkGroup() {
		clickOnRandomWorkGroup();
	}

	public void clickOnCopyHereButton() {
		waitToElementClickable(driver, MySpacePageUI.COPY_HERE_BUTTON);
		clickToElement(driver, MySpacePageUI.COPY_HERE_BUTTON);
	}

}
