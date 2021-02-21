package cucumberOption;

import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import commons.GlobalConstants;
import cucumber.api.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Hooks {
	// Run for many thread
	private static WebDriver driver;
	private static final Logger log = Logger.getLogger(Hooks.class.getName());

	@Before // synchronized = handle đồng bộ
	public synchronized static WebDriver getAndCloseBrowser() {
		// Run by Maven command line
		String browser = System.getProperty("BROWSER");

		// Check driver đã được khởi tạo hay chưa?
		if (driver == null) {

			// Happy path case
			try {
				// Kiem tra BROWSER = null -> gan = chrome/ firefox (browser default for
				// project)
				if (browser == null) {
					// Get browser name from Environment Variable in OS
					browser = System.getenv("BROWSER");
					if (browser == null) {
						// Set default browser
						browser = "chrome";
					}
				}

				switch (browser) {
				case "chrome":
					WebDriverManager.chromedriver().setup();
					driver = new ChromeDriver();
					break;
				case "hchrome":
					WebDriverManager.chromedriver().setup();
					ChromeOptions chromeOptions = new ChromeOptions();
					chromeOptions.addArguments("headless");
					chromeOptions.addArguments("window-size=1920x1080");
					driver = new ChromeDriver(chromeOptions);
					break;
				case "firefox":
					WebDriverManager.firefoxdriver().setup();
					System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
					System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
					driver = new FirefoxDriver();
					break;
				case "hfirefox":
					WebDriverManager.firefoxdriver().setup();
					System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
					System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
					FirefoxOptions firefoxOptions = new FirefoxOptions();
					firefoxOptions.setHeadless(true);
					driver = new FirefoxDriver(firefoxOptions);
					break;
				default:
					throw new RuntimeException("Please choose your browser!");
				}
			}
			finally {
				Runtime.getRuntime().addShutdownHook(new Thread(new BrowserCleanup()));
			}

			driver.get(GlobalConstants.DEV_URL);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
			log.info("------------- Started the browser -------------");
		}
		return driver;
	}

	
	public static void closeBrowserAndDriver() {

		try {
			String osName = System.getProperty("os.name").toLowerCase();
			log.info("OS name = " + osName);

			String cmd = "";
			if (driver != null) {
				driver.manage().deleteAllCookies();
				driver.quit();
			}

			if (driver.toString().toLowerCase().contains("chrome")) {
				if (osName.toLowerCase().contains("mac")) {
					cmd = "pkill chromedriver";
				} else if (osName.toLowerCase().contains("windows")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
				}
			} else if (driver.toString().toLowerCase().contains("internetexplorer")) {
				if (osName.toLowerCase().contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
				}
			} else if (driver.toString().toLowerCase().contains("firefox")) {
				if (osName.toLowerCase().contains("mac")) {
					cmd = "pkill geckodriver";
				} else if (osName.toLowerCase().contains("windows")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
				}
			}

			driver = null;
			Process process = Runtime.getRuntime().exec(cmd);
			process.waitFor();

		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}

	private static class BrowserCleanup implements Runnable {
		@Override
		public void run() {
			closeBrowserAndDriver();
		}
	}

}