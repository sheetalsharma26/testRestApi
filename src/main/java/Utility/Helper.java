package Utility;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * @author Sheetal
 * This files contains all the generic methods (eg handle Screenshot, frames, multiple windows, sync issue etc)
 */

public class Helper {
	static final Logger LOGGER = LoggerFactory.getLogger(Helper.class);
	static WebDriverWait wait;
	
	/**
	 * @param driver
	 * @return Path of Screenshot
	 */
	public static String captureScreenshot(WebDriver driver) {
		LOGGER.info("**** capturing Screenshot ");
		String screenshotPath = System.getProperty("user.dir") + "./Screenshots/" + getCurrentDateTime() + ".png";
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			org.openqa.selenium.io.FileHandler.copy(src, new File(screenshotPath));
		} catch (IOException e) {
			Reporter.log("Screenshot not saved " + e.getMessage());
		}
		return screenshotPath;
	}
	
	public static void printLog (String message, Object className){
		LOGGER.info("**** ");
		
	}
	
	public static boolean retryingFindClick(By by, WebDriver driver) {
		LOGGER.info("**** retrying clicking on element " + by);
	    boolean result = false;
	    int attempts = 0;
	    while(attempts < 2) {
	        try {
	            driver.findElement(by).click();
	            result = true;
	            break;
	        } catch(Exception e) {
	        }
	        attempts++;
	    }
	    return result;
	}
	
	public static void clickOn(By locator, WebDriver driver, int timeout)
	
	{
	    final WebDriverWait wait = new WebDriverWait(driver, timeout);
	    wait.until(ExpectedConditions.refreshed(
	        ExpectedConditions.elementToBeClickable(locator)));
	    driver.findElement(locator).click();
	}

	/**
	 * @param driver
	 * @return boolean value to indicate if alert present or not
	 */
	public static boolean isAlertPresent(WebDriver driver) {
		LOGGER.info("**** handling alret present");
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException ex) {
			return false;
		}
	}

	/**
	 * @return current Time-stamp
	 */
	public static String getCurrentDateTime() {
		LOGGER.info("**** retreiving CurrrentDateTime ");
		DateFormat customDateFormat = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
		Date currentDate = new Date();
		return customDateFormat.format(currentDate);
	}

	public static String getCurrentDate() {
		LOGGER.info("**** retreiving CurrrentDateTime");
		DateFormat customDateFormat = new SimpleDateFormat("MM_dd_yyyy_HH_mm");
		Date currentDate = new Date();
		return customDateFormat.format(currentDate);
	}
	/**
	 * @param driver
	 * This function is used to handle frames
	 */
	public static void handleFrame(WebDriver driver) {
		LOGGER.info("**** handling Frame");
		String urlFAQ = "";
		String winHandleBefore = driver.getWindowHandle();
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
		driver.close();
		driver.switchTo().window(winHandleBefore);
	}
	
	public static void waitUntil(WebDriver driver, WebElement x) {
		LOGGER.info("**** waiting for element to be visible " +x);
		wait= new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(x));
	}
	

	
}