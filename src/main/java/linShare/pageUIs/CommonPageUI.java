package linShare.pageUIs;

public class CommonPageUI {
	
	// Menu link
	public static final String DYNAMIC_MENU_LINK = "//span[contains(text(),'%s')]";

	// Child menu link
	public static final String DYNAMIC_CHILD_MENU_LINK = "//ul[@class='nav child_menu' and @style='display: block;']//a[contains(text(),'%s')]";

	// Textbox
	public static final String DYNAMIC_TEXTBOX = "//input[@name ='%s']";

	// Text Area
	public static final String DYNAMIC_TEXTAREA = "//textarea[@name ='%s']";

	// Checkbox
	public static final String DYNAMIC_CHECKBOX = "//input[@name ='%s']";

	// Radio Button
	public static final String DYNAMIC_RADIO = "//input[@value ='%s']";

	// Dropdown list
	public static final String DYNAMIC_DROPDOWN = "//select[@id ='%s']";

	// Button
	public static final String DYNAMIC_BUTTON = "//button[text() ='%s']";
	public static final String DYNAMIC_INPUT_BUTTON = "//input[@name ='%s']";
	public static final String DYNAMIC_BUTTON_SCREEN = "//div//a[contains(.,'%s')]";

	// Heading Text
	public static final String HEADING_TEXT = "//h3[contains(text(),'%s')]";

	// Row Name
	public static final String DYNAMIC_ROW_NAME = "//td[text()='%s']/following-sibling::td";

	// Common UI Expand menu
	public static final String MENU_TOGGLE = "//a[@id='menu_toggle']";
	public static final String MENU_TOGGLE_DISABLE = "//body[@class='nav-sm']";
	public static final String TABLE_TOTAL_ROW = "//table//tbody//tr";
}
