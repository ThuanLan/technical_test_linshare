package linShare.pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPages;
import linShare.pageUIs.CommonPageUI;
import linShare.pageUIs.SharedSpacePageUI;

public class SharedSpacePageObject extends AbstractPages {
	WebDriver driver;

	public SharedSpacePageObject(WebDriver _driver) {
		driver = _driver;
	}

	public void inputWorkGroupIntoSearchTextBox() {
		waitToElementVisible(driver, SharedSpacePageUI.SEARCH_TEXTBOX);
		sendkeyToElement(driver, SharedSpacePageUI.SEARCH_TEXTBOX, MySpacePageObject.getWorkSpace);
		sleepInSecond(5);
	}

	public void clickToFoundWorkGroup() {
		waitToElementClickable(driver, SharedSpacePageUI.WORK_GROUP_FOLDER_NAME, MySpacePageObject.getWorkSpace);
		clickToElement(driver, SharedSpacePageUI.WORK_GROUP_FOLDER_NAME, MySpacePageObject.getWorkSpace);
		sleepInSecond(5);
	}

	public boolean isFileDisplayed() {
		waitToElementVisible(driver, SharedSpacePageUI.SEARCH_TEXTBOX);
		sendkeyToElement(driver, SharedSpacePageUI.SEARCH_TEXTBOX, MySpacePageObject.getFileName);
		return isValueOfAColumn(MySpacePageObject.getFileName);
	}

	public boolean isValueOfAColumn(String determineValue) {
		String valueOfColumn;
		waitToElementInVisible(driver, CommonPageUI.TABLE_TOTAL_ROW);
		int totalRow = findElementsByXpath(driver, CommonPageUI.TABLE_TOTAL_ROW).size();
		if (totalRow >= 1) {
			for (int i = 1; i <= totalRow; i++) {
				waitToElementInVisible(driver, "//table//tbody//tr[" + i + "]//span[@class='file-name-disp single-line ng-binding']");
				valueOfColumn = getTextElement(driver, "//table//tbody//tr[" + i + "]//span[@class='file-name-disp single-line ng-binding']");
				if ((valueOfColumn.equals(determineValue)) == true) {
					return true;
				}
			}
		}
		return false;
	}

}
