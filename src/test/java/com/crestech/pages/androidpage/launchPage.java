package com.crestech.pages.androidpage;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import com.crestech.annotation.values.ElementDescription;
import com.crestech.appium.utils.CommonAppiumTest;
import com.crestech.common.utilities.CommonTestData;
import com.crestech.common.utilities.HandleException;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;

/**
 * @author Divya
 *
 */
public class launchPage extends CommonAppiumTest{
	
	public AppiumDriver<RemoteWebElement> driver = null;
	HandleException obj_handleexception = null;
	
	
	public launchPage(AppiumDriver<RemoteWebElement> driver) throws Exception {
		super(driver);
		try {
			this.driver = driver;
			obj_handleexception = new HandleException(null, null);
			PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(5)), this);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	
	//object
	@ElementDescription(value = "Quit Button.")
	@AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.Button")
	private MobileElement quitBtn;
	
	@ElementDescription(value = "PreLogin Button")
	@AndroidFindBy(xpath = "//android.widget.Button[@text='PRE LOGIN']")
	private MobileElement PreLoginBtn;
	
	@ElementDescription(value = "More Button")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='More']")
	private MobileElement MoreButton;
	
	@ElementDescription(value = "LOG IN Button")
	@AndroidFindBy(xpath = "//android.widget.Button[@text='LOG IN']")
	private MobileElement loginButton;

	@ElementDescription(value = "User ID EditTexT")
	@AndroidFindBy(xpath = "//android.widget.EditText[contains(@resource-id,'id/edit_user_id')]")
	private MobileElement userIdEditText;
	
	public MobileElement quitBtn() { 
		return quitBtn;
	}
	
	@Step("Handling Of QUIT Button.")
	public void handlingQuitButton() throws Exception {
		try {
			driver.manage().timeouts().implicitlyWait(75, TimeUnit.SECONDS);
			if (isElementVisible2(quitBtn)) {
				driver.closeApp();
				relaunchingDBS();
				wait.fluentWaitForElement(loginButton); 
			}
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		} catch (HandleException e) {
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Handle Quit Button ", e);
		} catch (Exception e) {
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Handle Quit Button ", e);
		}
	}
	

	@Step("Relaunching POSB application")
	public void relaunchingPOSB() throws Exception {
		try {
			relanchApplication(CommonTestData.POSB_APP_PACKAGE.getEnumValue(),
					CommonTestData.DBS_APPS_ACTIVITY.getEnumValue());
		} catch (HandleException e) {
			obj_handleexception.throwHandleException("RELAUNCHING_POSB_EXCEPTION",
					" Failed to Relaunching POSB Application  ", e);

		} catch (Exception e) {

			obj_handleexception.throwException("RELAUNCHING_POSB_EXCEPTION",
					" Failed to Relaunching POSB Application  ", e);
		}
	}

	@Step("Relaunching iWealth application")
	public void relaunchingIwealth() throws Exception {
		try {
			relanchApplication(CommonTestData.IWEALTH_APP_PACKAGE.getEnumValue(),
					CommonTestData.DBS_APPS_ACTIVITY.getEnumValue());
		} catch (HandleException e) {
			obj_handleexception.throwHandleException("RELAUNCHING_iWEALTH_EXCEPTION",
					" Failed to Relaunching iWealth Application  ", e);

		} catch (Exception e) {

			obj_handleexception.throwException("RELAUNCHING_iWEALTH_EXCEPTION",
					" Failed to Relaunching iWealth Application  ", e);
		}
	}
	
	@Step("Relaunching DBS application")
	public void relaunchingDBS() throws Exception {
		try {
			relanchApplication(CommonTestData.DBS_APP_PACKAGE.getEnumValue(),
					CommonTestData.DBS_APPS_ACTIVITY.getEnumValue());
		} catch (HandleException e) {
			obj_handleexception.throwHandleException("RELAUNCHING_DBS_EXCEPTION",
					" Failed to Relaunching DBS Application  ", e);
		} catch (Exception e) {
			obj_handleexception.throwException("RELAUNCHING_DBS_EXCEPTION", " Failed to Relaunching DBS Application  ",
					e);
		}
	}
	
	@Step("Clicked on Pre-Login button")
	public void ClickOnPreloginButton() throws Exception {
		try {
			int count = 0;
			do {
				clickOnElement(PreLoginBtn);
				count++;
			} while (!isElementVisible2(MoreButton) && count < 3);
		} catch (HandleException e) {
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On Prelogin Button ", e);
		} catch (Exception e) {
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click On Prelogin Button ", e);
		}
	}
	
	@Step("Clicked on Login button")
	public void clickOnLoginButton() throws Exception {
		try {
			int count = 0;
			do {
				clickOnElement(loginButton);
				count++;
			} while (!isElementVisible2(userIdEditText) && count < 3);
		} catch (HandleException e) {
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On Login Button ", e);
		} catch (Exception e) {
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click On Login Button ", e);
		}
	}

}