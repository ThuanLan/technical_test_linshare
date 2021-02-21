package linShare.pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPages;
import linShare.pageUIs.HomePageUI;
import linShare.pageUIs.SharedSpacePageUI;

public class SharedSpacePageObject extends AbstractPages {
	WebDriver driver;

	public SharedSpacePageObject(WebDriver _driver) {
		driver = _driver;
	}

	public void inputWorkGroupIntoSearchTextBox() {
		waitToElementVisible(driver, SharedSpacePageUI.SEARCH_TEXTBOX);
		sendkeyToElement(driver,SharedSpacePageUI.SEARCH_TEXTBOX, MySpacePageObject.getWorkSpace);
	}

	public void clickToFoundWorkGroup() {
		waitToElementClickable(driver, SharedSpacePageUI.WORK_GROUP_FOLDER_NAME,MySpacePageObject.getWorkSpace);
		clickToElement(driver, SharedSpacePageUI.WORK_GROUP_FOLDER_NAME,MySpacePageObject.getWorkSpace);
	}

	public void isFileDisplayed() {
		waitToElementVisible(driver, SharedSpacePageUI.SEARCH_TEXTBOX);
		sendkeyToElement(driver,SharedSpacePageUI.SEARCH_TEXTBOX, MySpacePageObject.getWorkSpace);
		
		
	}
	
//	public boolean isValueOfAColumn(String columnName, String determineValue) {
//		String msgValid;
//		String valueOfColumn;
//		String columnNumber = String.valueOf(getIndexOfColumn(driver, CommonPageUI.TABLE_HEADER, columnName));
//		int totalRow = findElementsByXpath(driver, CommonPageUI.TABLE_TOTAL_ROW).size();
//		if (totalRow > 1) {
//			for (int i = 2; i <= totalRow; i++) {
//				valueOfColumn = getTextElement(driver, "//table[contains(@class,'table table-hover table-bordered')]//tr[" + i + "]//td[" + columnNumber + "]");
//				if ((valueOfColumn.equals(determineValue)) == true) {
//					return true;
//				}
//			}
//		}
//		return false;
//	}
	

}
