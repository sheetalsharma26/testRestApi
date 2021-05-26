package PageFactory;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


/**
 * @author Sheetal BaseClass contians Driver, DataProviders, Report
 *         Initialization
 */
public class TestBase {
	private final Logger LOGGER = LoggerFactory.getLogger(TestBase.class);
	public WebDriver driver;

	public ExtentReports report;
	public ExtentTest test;

	/**
	 * Initialises Reports, DataProviders, Properties files etc required to run test
	 * cases
	 */
	@BeforeSuite
	public void setUpSuite() {
		LOGGER.info("**** Set up suite ");
		
//	if (report == null) {
			Reporter.log("set up of suite", true);

			report = new ExtentReports();
			ExtentHtmlReporter extent = new ExtentHtmlReporter(
					new File("./Reports/" + Utility.Helper.getCurrentDateTime() + ".html"));
			report = new ExtentReports();
			report.attachReporter(extent);
			extent.config().setDocumentTitle("Shell Remote Sense");
			extent.config().setReportName(" Automation Test Report ");
			extent.config().setTheme(Theme.STANDARD);
//	//	}
//		//return report;
//		
	}

	/**
	 */
	@BeforeClass
	public void setup() {
		LOGGER.info("**** setting up TestNG Suite");
		//driver = BrowserFactory.startApplication(driver, config.getBrowser(), config.getURL());
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	/**
	 * to close browser
	 */
	@AfterClass
	public void tearDown() {
		LOGGER.info("**** tear down");
		// BrowserFactory.quitBrowsesr(driver);
	}

	/**
	 * @param res
	 * @throws IOException
	 */
	@AfterMethod
	public void tearDownMethod(ITestResult res) throws IOException {
		LOGGER.info("**** Test Case tear down");
		if (res.getStatus() == ITestResult.FAILURE) {
			// Helper.captureScreenshot(driver);
//			test.fail("Test failed",
//					MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
//			// }

		} else if (res.getStatus() == ITestResult.SUCCESS) {
//			test.pass("Test pass",
//					MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		} else if (res.getStatus() == ITestResult.SKIP) {
//			test.skip("TESt Skipped",
//					MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		}
		report.flush();

	}

}
