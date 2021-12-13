package com.crestech.common.utilities;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.crestech.appium.utils.CommandPrompt;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class WaitUtils extends CommandPrompt {

	public AppiumDriver<RemoteWebElement> driver = null;
	private long WAIT_TIME = 90;
	HandleException obj_handleexception= new HandleException(null,null);
	public WaitUtils(AppiumDriver<RemoteWebElement> driver2) {
		try {
			this.driver = driver2;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * This method will Wait For Page Load
	 * @throws Exception 
	 */
	public void waitForPageLoad() throws Exception {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("return document.readyState").toString().equals("complete");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * This method will wait for element to be InVisible
	 *
	 * @param element
	 * @throws Exception 
	 */
	public void waitForElementInvisibility(MobileElement element) throws Exception {
		try {
			WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME);
			wait.until(ExpectedConditions.invisibilityOf(element));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * This method will wait element visibility in seconds
	 *
	 * @param element
	 * @throws Exception 
	 */
	public void waitForElementVisibility(MobileElement element) throws Exception {
		try {
			WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME);
			wait.until(ExpectedConditions.visibilityOf(element));
	} 
			catch (Exception e) {
			//e.printStackTrace();
			System.out.println("Inside take ele visi catch" );
			throw new HandleException ("WAITELEMENTVISIBLE_EXCEPTION", "Element not visible on the screen ::",e);
		
		}
//		catch (HandleException e) {
//			obj_handleexception.throwHandleException("SCREENSHOT", " Failed to capture the screenshot ",e);
//		}
//		catch (Exception e) {
//			obj_handleexception.throwException("SCREENSHOT", " Failed to capture the screenshot ",e);
//		}
	}

	/**
	 * This method will wait until element to be clickable
	 *
	 * @param element
	 * @throws Exception 
	 */
	public void waitForElementToBeClickable(MobileElement element) throws Exception {
		try {
			//System.out.println(driver.getCapabilities().getCapability(MobileCapabilityType.DEVICE_NAME));
			WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME);
			wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			//System.out.println("Inside take ele visi catch" );
			throw new HandleException ("WAITELEMENTCLICKABLE_EXCEPTION", "Element not clickable on the screen ::",e);
		}
	}

	/**
	 * This method will wait for page load Timeout
	 *
	 * @param Seconds
	 * @throws Exception 
	 */
	public void waitForPageLoadTimeout() throws Exception {
		try {
			driver.manage().timeouts().pageLoadTimeout(WAIT_TIME, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * This method will wait for Element visibility on whole page.
	 * @throws Exception
	 */
	public void ImplicitlyWait() throws Exception {
		try {
			driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			//System.out.println("Inside take ele visi catch" );
			throw new HandleException ("IMPLICIT_WAIT_EXCEPTION", "Element not Visible on the screen ::",e);
		}
	}
	
	/**
	 * This method will wait for page load with time
	 * 
	 * @param timeInSeconds
	 */
	public void waitForPageLoadedWithTime(int timeInSeconds) {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};
		try {
			Thread.sleep(1000);
			WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
			wait.until(expectation);
		} catch (Throwable error) {
			Assert.fail("Timeout waiting for Page Load Request to complete.");
		}
	}

	/**
	 * This method will wait until the given text is present on the given element
	 *
	 * @param element
	 * @throws Exception 
	 */
	public void waitForTextToBePresent(MobileElement element, String text) throws Exception {
		try {
			WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME);
			wait.until(ExpectedConditions.textToBePresentInElement(element, text));;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	

	/**
	 * This method will wait until the given title is present
	 * @param title
	 * @throws Exception 
	 */
	public void waitForGivenTitle(String title) throws Exception {
		try {
			WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME);
			wait.until(ExpectedConditions.titleContains(title));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	
}
