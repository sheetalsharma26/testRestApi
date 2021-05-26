package PageFactory;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @author Sheetal
 * BrowserFactory contains methods to handle browser
 */
public class BrowserFactory {
	WebDriver driver;

	/**
	 * method is called in TestBase class
	 * @param driver
	 * @param browsesrName
	 * @param appURL
	 * @return driver 
	 */
	public static WebDriver startApplication(WebDriver driver, String browsesrName, String srsApp) {
		if (browsesrName.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
			driver = new ChromeDriver();

		} else if (browsesrName.equalsIgnoreCase("Firefox")) {
			//System.setProperty("webdriver.gecko.driver", "./Driver/geckodriver.exe");
			//driver = new InternetExplorerDriver();

		} else if (browsesrName.equalsIgnoreCase("IE")) {
			//System.setProperty("webdriver.ie.driver", "./Driver/IEDriverServer.exe");
			//driver = new IEDriver();

		} else {
			System.out.println("Browser is not supported");
		}
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(srsApp);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	/**
	 * @param driver
	 * method to close browser
	 */
	public static void quitBrowsesr(WebDriver driver) {
		driver.quit();
				
	}
}
