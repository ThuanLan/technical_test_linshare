package linShare.pageUIs;

public class CommonPageUI {
	
	// Menu link
	public static final String DYNAMIC_MENU_LINK = "//span[contains(text(),'%s')]";

	// Textbox
	public static final String DYNAMIC_TEXTBOX = "//input[@name ='%s']";

	// Button
	public static final String DYNAMIC_BUTTON = "//button[text() ='%s']";
	
	public static final String TABLE_TOTAL_ROW = "//table//tbody//tr";
	public static final String CURRENT_LANGUAGE_ICON = "//span[@class='disp-language ng-binding']";
	public static final String LANGUAGE_DROPDOWN = "//span[@class='lang-short ng-binding']";
	public static final String US_LANGUAGE_DROPDOWN = "//span[@class='disp-language ng-binding' and contains(text(),'US')]";
}
