package PageFactory;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class Base {
	
	WebDriver driver;
	
	public Base(WebDriver ldriver) {
		this.driver = ldriver;
		PageFactory.initElements(driver, this);
	}

	public ExtentReports report;
	public ExtentTest test;

	public void test() {

		Reporter.log("set up of suite", true);
		ExtentHtmlReporter extent = new ExtentHtmlReporter(
				new File("./Reports/" + Utility.Helper.getCurrentDateTime() + ".html"));
		report = new ExtentReports();
		report.attachReporter(extent);
		extent.config().setDocumentTitle("Shell Remote Sense");
		extent.config().setReportName(" Test Report");
		extent.config().setTheme(Theme.DARK);
	}

}
