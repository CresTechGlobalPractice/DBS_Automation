package com.crestech.pages;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;


import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.apache.xpath.axes.WalkingIteratorSorted;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crestech.appium.utils.CommonAppiumTest;
import com.crestech.common.utilities.AndroidAlert;
import com.crestech.common.utilities.Asserts;
import com.crestech.common.utilities.CommonAlertElements;
import com.crestech.common.utilities.CommonTestData;
import com.crestech.common.utilities.GestureUtils;
import com.crestech.common.utilities.HandleException;
import com.crestech.common.utilities.WaitUtils;
import com.crestech.pageobjects.DBSAndroidObject;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.functions.ExpectedCondition;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;

/**
 * @author Divya Devi, Shafkat Ali
 *
 */
public class DBSAndroidPage extends CommonAppiumTest {

	static Logger log = Logger.getLogger(DBSAndroidPage.class.getName());
	public DBSAndroidObject DBSappObject = new DBSAndroidObject();
	public AppiumDriver<RemoteWebElement> driver = null;
	AndroidAlert androidAlert = null;
	WaitUtils wait = null;
	GestureUtils gestUtils = null;
	Asserts Assert = null;
	HandleException obj_handleexception=null;

	public DBSAndroidPage(AppiumDriver<RemoteWebElement> driver) throws Exception {
		super(driver);
		try {
			this.driver = driver;
			gestUtils = new GestureUtils(driver);
			androidAlert = new AndroidAlert(driver);
			wait = new WaitUtils(driver);
			gestUtils = new GestureUtils(driver);
			Assert = new Asserts();
			obj_handleexception =new HandleException(null, null);
			PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(5)), DBSappObject);
		} 
		catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
//		//
//		catch (Exception e) {			
//			//System.out.println("Inside Appply debit card catch");
//			obj_handleexception.throwException("WebDriver Execption", " Unable to perform Action  ",e);
//		}
	}

	@Step("Relaunching DBS application")
	public void relaunchingDBS() throws Exception {
		try {
			relanchApplication(CommonTestData.DBS_APP_PACKAGE.getEnumValue(),
					CommonTestData.DBS_APPS_ACTIVITY.getEnumValue());
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("RELAUNCHING_DBS_EXCEPTION", " Failed to Relaunching DBS Application  ",e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("RELAUNCHING_DBS_EXCEPTION", " Failed to Relaunching DBS Application  ",e);
		}
	}

	@Step("Relaunching POSB application")
	public void relaunchingPOSB() throws Exception {
		try {
			relanchApplication(CommonTestData.POSB_APP_PACKAGE.getEnumValue(),
					CommonTestData.DBS_APPS_ACTIVITY.getEnumValue());
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("RELAUNCHING_POSB_EXCEPTION", " Failed to Relaunching POSB Application  ",e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("RELAUNCHING_POSB_EXCEPTION", " Failed to Relaunching POSB Application  ",e);
		}
	}

	@Step("Relaunching iWealth application")
	public void relaunchingIwealth() throws Exception {
		try {
			relanchApplication(CommonTestData.IWEALTH_APP_PACKAGE.getEnumValue(),
					CommonTestData.DBS_APPS_ACTIVITY.getEnumValue());
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("RELAUNCHING_iWEALTH_EXCEPTION", " Failed to Relaunching iWealth Application  ",e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("RELAUNCHING_iWEALTH_EXCEPTION", " Failed to Relaunching iWealth Application  ",e);
		}
	}

	@Step("Log In the Application")
	public void logInApplication(String userName, String password, String appName) throws Exception {
		try {
			CommonAlertElements btnElements = new CommonAlertElements(driver);
			Thread.sleep(40000);
			String quitButtonXpath = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.Button";
			List<RemoteWebElement> list = driver.findElements(By.xpath(quitButtonXpath));
			if (list.size() > 0) {
				driver.closeApp();
				if (appName.contains("DBS"))
					relaunchingDBS();
				else if (appName.contains("POSB"))
					relaunchingPOSB();
				else if (appName.contains("iWEALTH"))
					relaunchingIwealth();
				wait.waitForElementToBeClickable(DBSappObject.loginButton());
				Thread.sleep(5000);
			}
			UpdateUATN4Server();
			clickOnLoginButton();
			sendDataInUserId(userName);
			sendDataInUserPin(password);
			clickOnLoginButton();
			digitalTokenSetUp();
			AndroidAlert androidAlert = new AndroidAlert(driver);
			Thread.sleep(2000);
			String getStartedXpath = null;
			
			if (appName.contains("POSB"))
				getStartedXpath = "//android.widget.Button[@resource-id='com.dbs.sit1.posbmbanking:id/btn_get_started']";
			else if (appName.contains("DBS"))
				getStartedXpath = "//android.widget.Button[@resource-id='com.dbs.sit1.dbsmbanking:id/btn_get_started']";
			else if (appName.contains("iWEALTH"))
				getStartedXpath = "//android.widget.Button[@resource-id='com.dbs.sg.uat.dbsiwealth:id/btn_get_started']";

			List<RemoteWebElement> getStartedlist = driver.findElements(By.xpath(getStartedXpath));
			if (getStartedlist.size() > 0)
				clickOnElement((MobileElement) getStartedlist.get(0));

			String errorAlertOKButton = "//android.widget.Button[@resource-id='android:id/button1']";
			List<RemoteWebElement> errorAlertOKButtonlist = driver.findElements(By.xpath(errorAlertOKButton));

			if (errorAlertOKButtonlist.size() > 0)
				clickOnElement((MobileElement) errorAlertOKButtonlist.get(0));

			androidAlert.fingerprintAlertHandlingWithButtonMessage(btnElements.closeButton(),
					CommonTestData.FINGERPRINT_MESSAGE.getEnumValue());
			androidAlert.recordingAlertHandlingWithButtonMessage(btnElements.closeButton(),
					CommonTestData.RECORDERSECTION_MESSAGE.getEnumValue());
			Asserts.assertEquals(getTexOfElement(DBSappObject.WelcomeToText()).trim(),
					CommonTestData.WELCOME.getEnumValue(),
					CommonTestData.WELCOME.getEnumValue() + " text is not found");

			TakeScreenshot(DBSappObject.DigibankText());
			if (DBSappObject.DigibankText().getText().equalsIgnoreCase(CommonTestData.DIGIBANK.getEnumValue())) {
				Asserts.assertEquals(getTexOfElement(DBSappObject.DigibankText()).trim(),
						CommonTestData.DIGIBANK.getEnumValue(),
						CommonTestData.DIGIBANK.getEnumValue() + " text is not found");
			} else if (DBSappObject.DigibankText().getText()
					.equalsIgnoreCase(CommonTestData.DBS_DIGIBANK.getEnumValue())) {
				Asserts.assertEquals(getTexOfElement(DBSappObject.DigibankText()).trim(),
						CommonTestData.DBS_DIGIBANK.getEnumValue(),
						CommonTestData.DBS_DIGIBANK.getEnumValue() + " text is not found");
			}

		}catch (HandleException e) {	
			obj_handleexception.throwHandleException("TESTCASE_EXCEPTION", " Failed to Exceute Log In Application " ,e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("TESTCASE_EXCEPTION", " Failed to Exceute Log In Application ",e);
		}
	}

	@Step("Select And Save UAT N4 Server")
	public void UpdateUATN4Server() throws Exception {
		try {
			TakeScreenshot(DBSappObject.PreLoginBtn());
			clickOnElement(DBSappObject.PreLoginBtn());
			TakeScreenshot(DBSappObject.MoreButton());
			clickOnElement(DBSappObject.MoreButton());
			TakeScreenshot(DBSappObject.ChangeServerBtn());
			clickOnElement(DBSappObject.ChangeServerBtn());
			Thread.sleep(2000);
			gestUtils.scrollUPtoObject("text", "UAT N4", DBSappObject.SelectUATN4_Server());
			TakeScreenshot(DBSappObject.SelectUATN4_Server());
			clickOnElement(DBSappObject.SelectUATN4_Server());
			TakeScreenshot(DBSappObject.ChangeServerSaveBtn());
			clickOnElement(DBSappObject.ChangeServerSaveBtn());
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("SELECTUATSERVER_EXCEPTION", " Failed to Update UAT Server " ,e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("SELECTUATSERVER_EXCEPTION", " Failed to Update UAT Server ",e);
		}
	}
	

	@Step("Clicked on Login button")
	public void clickOnLoginButton() throws Exception {
		try {
			//System.out.println(driver.getCapabilities().getCapability("pCloudy_DeviceFullName"));
			TakeScreenshot(DBSappObject.loginButton());
			clickOnElement(DBSappObject.loginButton());
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On Login Button " ,e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click On Login Button ",e);
		}
	}

	@Step("Clicked on Sign Up For Digibank button")
	public void clickOnSignUpForDigibankButton() throws Exception {
		try {
			clickOnElement(DBSappObject.signUpForDigibankButton());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Step("Clicked on Pre Login button")
	public void preLoginButton() throws Exception {
		try {
			clickOnElement(DBSappObject.loginButton());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Step("Enter data in User EditBox")
	public void sendDataInUserId(String text) throws Exception {
		try {
			if (isElementEnable(DBSappObject.userIdEditText()))
				enterTextInTextbox(DBSappObject.userIdEditText(), text);

			Asserts.assertTrue(isElementEnable(DBSappObject.userIdEditText()), "EditField is not enable");
			TakeScreenshot(DBSappObject.userIdEditText());
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Send USER ID In Input Box " ,e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Send USER ID In Input Box ",e);
		}
	}

	@Step("Enter data in Pin EditBox")
	public void sendDataInUserPin(String text) throws Exception {
		try {
			if (isElementEnable(DBSappObject.userPinEditText()))
				enterTextInTextbox(DBSappObject.userPinEditText(), text);

			Asserts.assertTrue(isElementEnable(DBSappObject.userPinEditText()), "EditField is not enable");
			TakeScreenshot(DBSappObject.userPinEditText());
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Send USER PIN In Input Box " ,e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Send USER PIN In Input Box ",e);
		}
	}

	@Step("Application Logout & Verifies the 'Tap on the stars to rate' field Message.")
	public void clickOnLogoutAndVerify(String logoutTextMsg, String Ratingmsg) throws Exception {
		try {
			TakeScreenshot(DBSappObject.logoutButton());
			androidAlert.AlertHandlingWithButtonMessage(DBSappObject.logoutButton(), logoutTextMsg,
					DBSappObject.logoutButton());
			Asserts.assertEquals(getTexOfElement(DBSappObject.postLogoutAlertMessage()), Ratingmsg,
					"'Tap on the stars to rate' Text is not found");
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("TESTCASE_EXCEPTION", " Failed to Execute Log Out  ",e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("TESTCASE_EXCEPTION", " Failed to Execute Log Out  ",e);
		}
	}

	@Step("Application Logout & Verifies the 'Tap on the stars to rate' field Message.")
	public void clickOnLogoutAndVerifyInFundTransfer() throws Exception {
		try {
			gestUtils.scrollDOWNtoObject("text", "Log Out", DBSappObject.logOutPaylahButton());
			TakeScreenshot(DBSappObject.logOutPaylahButton());
			androidAlert.AlertHandlingWithButtonMessage(DBSappObject.logOutPaylahButton(), "Log Out",
					DBSappObject.logOutPaylahButton());
			Asserts.assertEquals(getTexOfElement(DBSappObject.postLogoutAlertMessage()),
					CommonTestData.RATE_MESSAGE.getEnumValue(), "'Tap on the stars to rate' Text is not found");
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to logout and verify tap on the stars message  ",e);
		}
		catch (Exception e) {		
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to logout and verify tap on the stars message  ",e);
		}
	}

	@Step("Verifying Page and clicking on 'SET UP NOW' button ")
	public void verifyPageAndClickOnSetUpNowButton(String expectecMessage) throws Exception {
		try {
			androidAlert.AlertHandlingWithButtonMessage(DBSappObject.setUpNowButton(), expectecMessage,
					DBSappObject.tokenSetupMessage());
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On Set Up Now Button " ,e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click On Set Up Now Button ",e);
		}
	}

	@Step("Verifying Email/SMS OTP Page and Send OTP on TextField button ")
	public void verifyPageAndSendOtpToEditBox(String OTP, String expectecMessage) throws Exception {
		try {
			String actualMessage = getTexOfElement(DBSappObject.emailSmsOtpMessage());
			if (actualMessage.equalsIgnoreCase(expectecMessage))
				if (isElementEnable(DBSappObject.emailSmsOtpEditBox()))
					enterTextInTextbox(DBSappObject.emailSmsOtpEditBox(), OTP);
			Asserts.assertEquals(actualMessage, expectecMessage, "Title Message Not matching");
		}  catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to send Email/SMS OTP " ,e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", "  Failed to send Email/SMS OTP ",e);
		}
	}

	@Step("Verifying Page after Digital Token setup after clicking on 'Done' button")
	public void digitalTokenSetUp() throws Exception {
		try {
			Thread.sleep(5000);
			String digitalTokenSetUpXpath = "//android.widget.TextView[contains(@resource-id,'id/status_message')]";
			Thread.sleep(5000);
			List<RemoteWebElement> digitalTokenSetUpList = driver.findElements(By.xpath(digitalTokenSetUpXpath));
			if (digitalTokenSetUpList.size() > 0) {
				Thread.sleep(5000);
				verifyPageAndClickOnSetUpNowButton(CommonTestData.DIGITAL_TOKEN_SETUP_MESSAGE.getEnumValue());
				String alertMsg = "//android.widget.TextView[@text='Please note you can only have one digital token registered to your profile. Any digital token on an alternative device will therefore be automatically deregistered.']";
				List<RemoteWebElement> elements = driver.findElements(By.xpath(alertMsg));
				if (elements.size() > 0) {
					String continueButtonXpath = "//android.widget.Button[@text='CONTINUE']";
					List<RemoteWebElement> continueButtons = driver.findElements(By.xpath(continueButtonXpath));
					continueButtons.get(0).click();
				}
				verifyPageAndSendOtpToEditBox(CommonTestData.OTP.getEnumValue(),
						CommonTestData.EMAIL_OTP_MESSAGE.getEnumValue());
				verifyPageAndSendOtpToEditBox(CommonTestData.OTP.getEnumValue(),
						CommonTestData.SMS_OTP_MESSAGE.getEnumValue());
				Thread.sleep(4000);
				gestUtils.scrollUPtoObject("text", "DONE", DBSappObject.doneButton());
				String xpath = "//android.widget.Button[@text='DONE']";
				List<RemoteWebElement> list = driver.findElements(By.xpath(xpath));
				if (list.size() > 0) {
					TakeScreenshot(DBSappObject.doneButton());
					androidAlert.AlertHandlingWithButtonMessage(DBSappObject.doneButton(),
							CommonTestData.DIGITAL_TOKEN_MESSAGE_AFTER_STEPUP.getEnumValue(),
							DBSappObject.tokenGetSetupMessage());
				}
			}
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to execute Digital Taken Setup " ,e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to execute Digital Taken Setup ",e);
		}
	}

	@Step("Verifies Remittance Corridor")
	public void VerifyRemittanceCorridor() throws Exception {
		try {
			ClickOnPayAndTransferBtn();
			EnterPasscodeAndDone();
			SelectingPayeeAfterSelectingOverseas(CommonTestData.PAYEE_NAME_CORRIDOR.getEnumValue());
			selectFundSourceAndSelectAccountForCorredor(CommonTestData.SOURCE_ACCOUNT_NAME_CORRIDOR.getEnumValue(),
					CommonTestData.SOURCE_ACCOUNT_CORRIDOR.getEnumValue());
			pressEnterKeyAfterEnteringAmount(CommonTestData.CORRIDOR_AMOUNT.getEnumValue());
			gestUtils.scrollUPtoObject("text", "NEXT", DBSappObject.NextBtn());
			selectPurposeAccountTypeMobileNumberIfAvaliable("Savings","Personal Gifts","9999999990");
			ClickOnNextBtnAndVerifiesReviewTransferPage();
			ClickOnTransferNowBtnAndVerifiesTransferSubmittedMsg(CommonTestData.TRANSFER_SUBMITTED_MSG.getEnumValue(),
					DBSappObject.TransferSubmittedImage(), DBSappObject.TransferSubmittedMsg());
			ClickOnImageExpandBtnAndVerifiesReferenceNumberText();
			ClickOnShareTransferDetailsBtnAndVerifiesReferenceNumberText();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Step("Verifies Remittance eOTT")
	public void VerifyRemittanceEOTT() throws Exception {
		try {
			ClickOnPayAndTransferBtn();
			EnterPasscodeAndDone();
			SelectAllTAB();
			clickOnElement(DBSappObject.editSearchField());
			String ExpectedEottName = CommonTestData.EOTTREMITTANCE_NAME.getEnumValue();
			enterTextInTextbox(DBSappObject.editSearchField(), ExpectedEottName);
			pressKey(driver, Keys.ENTER);
			String xpath = "//android.widget.TextView[@text='" + ExpectedEottName + "']";
			MobileElement ExpectedEottEle = (MobileElement) driver.findElement(By.xpath(xpath));
			isElementVisible(ExpectedEottEle);
			clickOnElement(ExpectedEottEle);
			//Asserts.assertEquals(getTexOfElement(DBSappObject.overseasTransferHeader()),
			//		CommonTestData.OVERSEAS_TRANSFER_PAGEHEADER.getEnumValue(),
				//	CommonTestData.OVERSEAS_TRANSFER_PAGEHEADER.getEnumValue() + " Text is not found");
			selectFundSourceAndSelectAccountForCorredor(CommonTestData.SOURCE_ACCOUNT_NAME_CORRIDOR.getEnumValue(), null);
			//clickOnElement(DBSappObject.SelectFundSourcePage());
			//clickOnElement(DBSappObject.SourceFundList().get(0));

			pressEnterKeyAfterEnteringAmount(CommonTestData.eOTT_AMOUNT.getEnumValue());
			gestUtils.scrollUPtoObject("resource-id", "id/btn_remitnext", DBSappObject.NextBtn());
			clickOnElement(DBSappObject.SelectPurposeOfTransfer());
			clickOnElement(DBSappObject.FundTransferPurposeOption());
			Asserts.assertEquals(getTexOfElement(DBSappObject.TextViewPurpose()),
					CommonTestData.PURPOSE_OF_TRANSFER_TEXT.getEnumValue(),
					CommonTestData.PURPOSE_OF_TRANSFER_TEXT.getEnumValue() + " Text is not found");
			gestUtils.scrollUPtoObject("text", "Next", null);
			//clickOnElement(DBSappObject.SelectPurposeOfTransfer());
			//clickOnElement(DBSappObject.FundTransferPurposeOption());
			selectPurposeAccountTypeMobileNumberIfAvaliable("Savings","Personal Gifts","9999999990");
			//Asserts.assertEquals(getTexOfElement(DBSappObject.TextViewPurpose()),
				//	CommonTestData.PURPOSE_OF_TRANSFER_TEXT.getEnumValue(),
				//	CommonTestData.PURPOSE_OF_TRANSFER_TEXT.getEnumValue() + " Text is not found");
			gestUtils.scrollUPtoObject("text", "NEXT", null);
			ClickOnNextBtnAndVerifiesReviewTransferPage();
			ClickOnTransferNowBtnAndVerifiesTransferSubmittedMsg(CommonTestData.TRANSFER_SUBMITTED_MSG.getEnumValue(),
					DBSappObject.TransferSubmittedImage(), DBSappObject.TransferSubmittedMsg());
			ClickOnImageExpandBtnAndVerifiesReferenceNumberText();
			ClickOnShareTransferDetailsBtnAndVerifiesReferenceNumberText();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Step("Verifies Add payee DBSorPOSB.")
	public void VerifyAddPayeeDBSorPOSB() throws Exception {
		try {
			ClickOnPayAndTransferBtn();
			EnterPasscodeAndDone();
			clickOnAddLocalRecipientBtnAndVerifyLocalTransferPayNowPageHeader();
			
			String ExpectedRecipientName = CommonTestData.PAYEEADD_DBSPOSB_RECIPIENT_NAME.getEnumValue();
			EnterRecipientDetailsAfterSelectingBankAccountOption(ExpectedRecipientName,
					CommonTestData.PAYEEADD_DBSPOSB_BANK_NAME.getEnumValue(),
					CommonTestData.PAYEEADD_DBSPOSB_ACCOUNT_NUMBER.getEnumValue());
			ClickOnNextBtnAndReviewRecipientDetails();
			ClickOnAddRecipientNowBtn();
			VerifyYouHaveAddedRecipientMsgAfterEnterSecurePIN();
			verifyValidationForPayeeAdd(ExpectedRecipientName, CommonTestData.PAYEEADD_DBSPOSB_BANK_NAME.getEnumValue(),
					CommonTestData.PAYEEADD_DBSPOSB_ACCOUNT_NUMBER.getEnumValue());
			
			//Leaving on Home Page After adding payee to DBS/POSB for next run.
			ClickOnBackIcon();
			ClickOnHomeButton();

		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("TESTCASE_EXCEPTION", " Failed to Execute Add payee DBSorPOSB  ",e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("TESTCASE_EXCEPTION", " Failed to Execute Add payee DBSorPOSB  ",e);
		}
	}

	@Step("Verify 'You Have Added Recipient Msg' After Entering Secure PIN.")
	public void VerifyYouHaveAddedRecipientMsgAfterEnterSecurePIN() throws Exception {
		try {
			EnterPasscodeAndDone();
			Thread.sleep(4000); 
			if (DBSappObject.SuccessTickImageView().isDisplayed()) {
			TakeScreenshot(DBSappObject.SuccessTickImageView());
				if(getTexOfElement(DBSappObject.PageHeaderForOpenAccount()).toLowerCase().equalsIgnoreCase(CommonTestData.YOU_HAVE_ADDED_RECIPIENT_MSG.getEnumValue()))
					Asserts.assertEquals(getTexOfElement(DBSappObject.PageHeaderForOpenAccount()),
							CommonTestData.YOU_HAVE_ADDED_RECIPIENT_MSG.getEnumValue(),
							CommonTestData.YOU_HAVE_ADDED_RECIPIENT_MSG.getEnumValue() + " Text is not matching");
				
				else if(getTexOfElement(DBSappObject.PageHeaderForOpenAccount()).toLowerCase().equalsIgnoreCase(CommonTestData.YOU_HAVE_ADDED_RECIPIENT_MSG2.getEnumValue())) {
					Asserts.assertEquals(getTexOfElement(DBSappObject.PageHeaderForOpenAccount()),
							CommonTestData.YOU_HAVE_ADDED_RECIPIENT_MSG2.getEnumValue(),
							CommonTestData.YOU_HAVE_ADDED_RECIPIENT_MSG2.getEnumValue() + " Text is not matching");
				}	
			} else {
				if(androidAlert.isAlertPresent()) {
					System.out.println("Alert title :: "+this.driver.switchTo().alert().getText()); 
					Asserts.assertFail(this.driver.switchTo().alert().getText());
				}	
				Asserts.assertFail("You Have added a Recipient Page not displaying");
			}		
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FILEDVERIFICATION_EXCEPTION", " Failed to verify you've added recipient msg  ",e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("FILEDVERIFICATION_EXCEPTION", " Failed to verify you've added recipient msg  ",e);
		}
	}

	@Step("Verify Local Transfer Pay Now Page Header After Clicking On Add Local Recipient Page Header.")
	public void clickOnAddLocalRecipientBtnAndVerifyLocalTransferPayNowPageHeader() throws Exception {
		try {
			String xpath = "//android.widget.Button[@text='ADD RECIPIENT NOW']";
			List<RemoteWebElement> list = driver.findElements(By.xpath(xpath));
			if (list.size() > 0) {
				TakeScreenshot(DBSappObject.AddRecipientNowBtn());
				clickOnElement(DBSappObject.AddRecipientNowBtn());
			} else {
				TakeScreenshot(DBSappObject.AddLocalRecipient());
				clickOnElement(DBSappObject.AddLocalRecipient());
			}
			wait.waitForElementVisibility(DBSappObject.PageHeader());
			Asserts.assertEquals(getTexOfElement(DBSappObject.PageHeader()),
					CommonTestData.LOCAL_TRANSFER_PayNow.getEnumValue(),
					CommonTestData.LOCAL_TRANSFER_PayNow.getEnumValue() + " Page Header is not matching");
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On Add Local Recipient Button and verify page header ",e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click On Add Local Recipient Button and verify page header ",e);
		}
	}

	@Step("Enter Recipient Details Into Bank Account Section.")
	public void EnterRecipientDetailsAfterSelectingBankAccountOption(String ExpectedRecipientName, String BankName,
			String AccountNumber) throws Exception {
		try {
			TakeScreenshot(DBSappObject.SelectBankAccount());
			clickOnElement(DBSappObject.SelectBankAccount());
			wait.waitForElementToBeClickable(DBSappObject.EditFields().get(0));
			Asserts.assertEquals(getTexOfElement(DBSappObject.PageHeader()),
					CommonTestData.ENTER_RECIPIENT_DETAILS.getEnumValue(),
					CommonTestData.ENTER_RECIPIENT_DETAILS.getEnumValue() + " Text is not found");
			enterTextInTextbox(DBSappObject.EditFields().get(0), ExpectedRecipientName);
			clickOnElement(DBSappObject.EditFields().get(1));
			clickOnElement(DBSappObject.SearchField());
			enterTextInTextbox(DBSappObject.SearchField(), BankName);
			backButton();
			gestUtils.scrollUPtoObject("text", "BANK OF INDIA", DBSappObject.SelectBankOFIndia());
			String xpath = "//android.widget.RelativeLayout//android.widget.TextView[@text='" + BankName + "']";
			MobileElement Selectbank = (MobileElement) driver.findElement(By.xpath(xpath));
			clickOnElement(Selectbank);
			enterTextInTextbox(DBSappObject.EditFields().get(2), AccountNumber);
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Enter Details   ",e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Enter Details  ",e);
		}
	}

	public String GenerateRandomRecipientName() {
		// create a string of all characters
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		// create random string builder
		StringBuilder sb = new StringBuilder();

		// create an object of Random class
		Random random = new Random();

		// specify length of random string
		int length = 2;

		for (int i = 0; i < length; i++) {

			// generate random index number
			int index = random.nextInt(alphabet.length());

			// get character specified by index
			// from the string
			char randomChar = alphabet.charAt(index);

			// append the character to string builder
			sb.append(randomChar);
		}

		String randomString = sb.toString();
		System.out.println("Random String is: " + randomString);
		return randomString;
	}

	public int GenerateRandomInt() {

		// create instance of Random class
		Random rand = new Random();

		// Generate random integers in range 0 to 999
		int rand_int1 = rand.nextInt(100);

		return rand_int1;
	}

	@Step("Enter Passcode(123456) and click on Done button for Secure Pin Authentication.")
	public void EnterPasscodeAndDone() throws Exception {
		try {
			String xpath = "//android.widget.EditText[@text='••••••']";
			List<RemoteWebElement> list = driver.findElements(By.xpath(xpath));
			if (list.size() > 0) {
				TakeScreenshot(DBSappObject.PasscodeField());
				enterTextInTextbox(DBSappObject.PasscodeField(), CommonTestData.OTP.getEnumValue());
				String doneButtonxpath = "//android.widget.Button[@text='Done']";
				List<RemoteWebElement> doneButtonList = driver.findElements(By.xpath(doneButtonxpath));
				if (doneButtonList.size() > 0)
					clickOnElement(DBSappObject.DoneButtonForPasscode());
			}
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("ENTER_PASSCODE_EXCEPTION", " Failed to enter passcode  ",e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("ENTER_PASSCODE_EXCEPTION", " Failed to enter passcode  ",e);
		}
	}

	@Step("Verifies Overseas Transfer Page Header on the top & Select Payee and fund source from Overseas Transfer page.")
	public void SelectingPayeeAfterSelectingOverseas(String valueSelectedFromList) throws Exception {
		try {
			overseasVerifyClick(CommonTestData.OVERSEAS_ICON.getEnumValue());
			TakeScreenshot(DBSappObject.OverseasTransferPage());
			Asserts.assertEquals(getTexOfElement(DBSappObject.OverseasTransferPage()).toLowerCase(),
					CommonTestData.OVERSEAS_TRANSFER_PAGEHEADER.getEnumValue().toLowerCase(),
					CommonTestData.OVERSEAS_TRANSFER_PAGEHEADER.getEnumValue() + " Text is not found");

			TakeScreenshot(DBSappObject.OverseasTransferPage());
			List<MobileElement> Elementlist = DBSappObject.overseaRecipientList();
			int l = Elementlist.size();
			int index = 0;
			String OverseaRecipientList = null;
			for (int i = 0; i < l; i++) {
				OverseaRecipientList = Elementlist.get(i).getText();
				if (OverseaRecipientList.contains(valueSelectedFromList)) {
					index++;
					clickOnElement(Elementlist.get(i));
					break;
				}
			}
			Asserts.assertTrue(index>0, "No element found in the list of corresponding value");

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Step("Press enter key after Entering Amount.")
	public void pressEnterKeyAfterEnteringAmount(String Amt) throws Exception {
		try {
			clickOnElement(DBSappObject.AmountTextFields().get(0));
			enterTextInTextbox(DBSappObject.AmountTextFields().get(0), Amt);
			pressKey(driver, Keys.ENTER);
			wait.waitForElementVisibility(DBSappObject.ExchangeRateText());
			backButton();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Step("Verifies Review Transfer Page Header after clicking on Next Button.")
	public void ClickOnNextBtnAndVerifiesReviewTransferPage() throws Exception {
		try {
			gestUtils.scrollUPtoObject("text", "NEXT", DBSappObject.NextBtn());
			clickOnElement(DBSappObject.NextBtn());
			Asserts.assertEquals(getTexOfElement(DBSappObject.ReviewTransferPageHeader()),
					CommonTestData.REVIEW_TRANSFER.getEnumValue(),
					CommonTestData.REVIEW_TRANSFER.getEnumValue() + " Text is not matching");
			TakeScreenshot(DBSappObject.ReviewTransferPageHeader());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Step("Verifies Transfer Submitted Message after clicking on Transfer Now Button.")
	public void ClickOnTransferNowBtnAndVerifiesTransferSubmittedMsg(String SuccessMsg, MobileElement successImage,
			MobileElement transfferdSubmitMsgEle) throws Exception {
		try {
			gestUtils.scrollUPtoObject(null, null, null);
			clickOnElement(DBSappObject.TransferNowBtn());
			TakeScreenshot(successImage);
			if (isElementVisible(successImage))
				Asserts.assertEquals(getTexOfElement(transfferdSubmitMsgEle), SuccessMsg,
						SuccessMsg + " Text is not matching");
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to CLick on transfer now and Verify success message ",e);
		}
		catch (Exception e) {		
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to CLick on transfer now and Verify success message  ",e);
		}
	}

	@Step("Verifies Reference Number Text after clicking on Image Expand Button.")
	public void ClickOnImageExpandBtnAndVerifiesReferenceNumberText() throws Exception {
		try {
			clickOnElement(DBSappObject.ImageExpand());
			gestUtils.scrollUPtoObject(null, null, null);
			Asserts.assertEquals(getTexOfElement(DBSappObject.ReferenceNumberText()),
					CommonTestData.REFERENCE_NUMBER_TEXT.getEnumValue(),
					CommonTestData.REFERENCE_NUMBER_TEXT.getEnumValue() + " Text is not found");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Step("Verifies Overseas transfer Message after clicking on Share Transfer Details Button.")
	public void ClickOnShareTransferDetailsBtnAndVerifiesReferenceNumberText() throws Exception {
		try {
			gestUtils.scrollUPtoObject("text", "SHARE TRANSFER DETAILS", DBSappObject.ShareTransferDetailsBtn());
			clickOnElement(DBSappObject.ShareTransferDetailsBtn());
			//Asserts.assertEquals(getTexOfElement(DBSappObject.OverseasTransferMsg()),
			//		CommonTestData.OVERSEAS_TRANSFER_TEXT.getEnumValue(),
			//		CommonTestData.OVERSEAS_TRANSFER_TEXT.getEnumValue() + " Text is not found");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Step("Click On Pay & Transfer Button.")
	public void ClickOnPayAndTransferBtn() throws Exception {
		try {
			TakeScreenshot(DBSappObject.PayAndTransferBtn());
			if (isElementVisible(DBSappObject.PayAndTransferBtn()))
				clickOnElement(DBSappObject.PayAndTransferBtn());
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On Pay and Transfer Button  ",e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click On On Pay and Transfer Button  ",e);
		}
	}

	@Step("Verifying Overseas  icon and click")
	public void overseasVerifyClick(String expectecText) throws Exception {
		try {

			String actualText = getTexOfElement(DBSappObject.overseasLabel());
			Asserts.assertEquals(actualText, expectecText, "Label Not matching");

			if (actualText.equalsIgnoreCase(expectecText))
				clickOnElement(DBSappObject.Btnlist().get(3));

		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click on Overseas Button  ",e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click on Overseas Button ",e);
		}
	}

	@Step("Verifying Add Overseas Recipient  Label and click")
	public void addOverseasRecipientVerifyClick(String expectecText) throws Exception {
		try {

			String actualText = getTexOfElement(DBSappObject.addRecipientNowButton());

			if (actualText.equalsIgnoreCase(expectecText))
				clickOnElement(DBSappObject.addRecipientNowButton());

			Asserts.assertEquals(actualText, expectecText, "Label Not matching");

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	@Step("Enter the text in search and select the corresponding value in the dropdown")
	public void sendDataInSearchBoxAndSelectFromDropDown(String searchBoxData, String valueSelectedFromList,
			MobileElement searchField) throws Exception {
		try {
			if (isElementEnable(searchField))
				enterTextInTextbox(searchField, searchBoxData);
			List<MobileElement> Elementlist = DBSappObject.countryList();
			if (Elementlist.size() > 0) {
				TakeScreenshot(Elementlist.get(0));
				List<MobileElement> ElementlistClickable = DBSappObject.dropDowmList();
				int l = Elementlist.size();
				int index = 0;
				String countryFromList = null;
				for (int i = 0; i < l; i++) {
					countryFromList = Elementlist.get(i).getText();
					if (countryFromList.equalsIgnoreCase(valueSelectedFromList)) {
						index++;
						clickOnElement(ElementlistClickable.get(i));
						break;
					}
				}

				Asserts.assertTrue(index > 0, "No element found in the lis of corresponding value");
			} else {
				if (androidAlert.isAlertPresent()) {
					System.out.println("Alert title :: " + this.driver.switchTo().alert().getText());
				}
				Asserts.assertFail(valueSelectedFromList + " not found in the list as list size is 0");
			}
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Send Data In Search Box ",e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to to Send Data In Search Box ",e);
		}
	}

	@Step("Select CurrencyType From the List")
	public void CurrencyTypeVerifyClick(String expectecCurrency) throws Exception {
		try {
			if (DBSappObject.currencyOptionList().size() > 0) {
				TakeScreenshot(DBSappObject.currencyOptionList().get(0));
				int index = 0;
				String currencyFromList = null;
				for (int i = 0; i < DBSappObject.currencyOptionList().size(); i++) {
					currencyFromList = DBSappObject.currencyOptionList().get(i).getText();
					if (currencyFromList.equalsIgnoreCase(expectecCurrency)) {
						index++;
						clickOnElement(DBSappObject.currencyOptionList().get(i));
						break;
					}
				}

				Asserts.assertTrue(index > 0, "No currency found in the list of corresponding value");
			} else {
				if (androidAlert.isAlertPresent()) {
					System.out.println("Alert title :: " + this.driver.switchTo().alert().getText());
				}
				Asserts.assertFail(expectecCurrency + " not found in the list as list size is 0");
			}
		}catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Select Currency Type from list. ",e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Select Currency Type from list.  ",e);
		}
	}

	@Step("Click On Next Button.")
	public void ClickOnNextButton1() throws Exception {
		try {
			gestUtils.scrollUPtoObject("text", "NEXT", DBSappObject.nextButton());
			String nextButtonXpath = "//android.widget.Button[@text='NEXT']";
			List<RemoteWebElement> nextButton = driver.findElements(By.xpath(nextButtonXpath));
			if (nextButton.size() > 0) {
				TakeScreenshot(DBSappObject.nextButton());
				if (getTexOfElement(DBSappObject.nextButton()).equalsIgnoreCase("NEXT"))
					clickOnElement(DBSappObject.nextButton());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	@Step("Click On Next Button.")
	public void ClickOnNextButton() throws Exception {
		try {
			String confirmButtonXpath = "//android.widget.Button[@text='CONFIRM']";
			List<RemoteWebElement> confirmButton = driver.findElements(By.xpath(confirmButtonXpath));
			String nextButtonXpath = "//android.widget.Button[@text='NEXT']";
			List<RemoteWebElement> nextButton = driver.findElements(By.xpath(nextButtonXpath));
			if(confirmButton.size()>0) {
				gestUtils.scrollUPtoObject("text", "CONFIRM", DBSappObject.confirmButton());
				TakeScreenshot(DBSappObject.confirmButton());
			
			}
			else if(nextButton.size()>0) {
				gestUtils.scrollUPtoObject("text", "NEXT", DBSappObject.nextButton());
				TakeScreenshot(DBSappObject.nextButton());
				if (getTexOfElement(DBSappObject.nextButton()).equalsIgnoreCase("NEXT"))
					clickOnElement(DBSappObject.nextButton());
			}
			
		
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On Next Button  ",e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click On Next Button  ",e);
		}
	}

	@Step("Enter data in Bank Code EditBox")
	public void sendBankCode(String text) throws Exception {
		try {

			if (isElementEnable(DBSappObject.enterBankcodeTextField()))
				enterTextInTextbox(DBSappObject.enterBankcodeTextField(), text);

			Asserts.assertTrue(isElementEnable(DBSappObject.enterBankcodeTextField()), "EditField is not enable");
			TakeScreenshot(DBSappObject.enterBankcodeTextField());

		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Send Bank Code in EditBox ",e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Send Bank Code in EditBox  ",e);
		}
	}

	@Step("Enter Account No EditBox")
	public void sendAccountNo(String text) throws Exception {
		try {
			if (isElementEnable(DBSappObject.recipientAccountNoEditBox()))
				enterTextInTextbox(DBSappObject.recipientAccountNoEditBox(), text);
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Send Account Number in EditBox ",e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Send Account Number in EditBox  ",e);
		}
	}

	@Step("Enter Full name EditBox")
	public void sendFullName(String text) throws Exception {
		try {
			if (isElementEnable(DBSappObject.recipientNameEditBox()))
				enterTextInTextbox(DBSappObject.recipientNameEditBox(), text);
			TakeScreenshot(DBSappObject.recipientAddressEditBox());
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Send Full Name in EditBox ",e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Send Full Name in EditBox  ",e);
		}
	}

	@Step("Enter Address EditBox")
	public void sendAddress(String text) throws Exception {
		try {
			if (isElementEnable(DBSappObject.recipientAddressEditBox()))
				enterTextInTextbox(DBSappObject.recipientAddressEditBox(), text);

			// Asserts.assertTrue(isElementEnable(DBSappObject.recipientAddressEditBox()),
			// "EditField is not enable");
		}catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Send Address in EditBox ",e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Send Address in EditBox  ",e);
		}
	}

	@Step("Enter city EditBox")
	public void sendcity(String text) throws Exception {
		try {
			String xpath = "//android.widget.EditText[@text='In the City of']";
			List<RemoteWebElement> CityList = driver.findElements(By.xpath(xpath));
			if (CityList.size() > 0)
				enterTextInTextbox(DBSappObject.recipientCityEditBox(), text);

		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Send City in EditBox ",e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Send City in EditBox  ",e);
		}
	}

	@Step("Verify 'REVIEW RECIPIENT'S DETAILS label' field")
	public void verifyRecipientReviewDetailLabel(String expectedText) throws Exception {
		try {
			String actualText = getTexOfElement(DBSappObject.recipientReviewDetailLabel());
			TakeScreenshot(DBSappObject.recipientReviewDetailLabel());
			Asserts.assertEquals(actualText.toLowerCase(), expectedText.toLowerCase(),
					"'REVIEW RECIPIENT'S DETAILS label' Text is not found");

		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Verify Review Recipient Details label ",e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Verify Review Recipient Details label  ",e);
		}
	}

	@Step("clicking On 'ADD RECIPIENT NOW' button")
	public void ClickOnAddRecipientNowBtn() throws Exception {
		try {
			gestUtils.scrollUPtoObject("text", "ADD RECIPIENT NOW", DBSappObject.AddRecipientNowBtn());
			TakeScreenshot(DBSappObject.AddRecipientNowBtn());
			String actualText = getTexOfElement(DBSappObject.AddRecipientNowBtn());
			if (actualText.equalsIgnoreCase(CommonTestData.ADD_RECIPIENT_LABEL.getEnumValue()))
				clickOnElement(DBSappObject.AddRecipientNowBtn());
			Thread.sleep(4000);
			Asserts.assertEquals(actualText, CommonTestData.ADD_RECIPIENT_LABEL.getEnumValue(), "Button not matching");
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On Add Recipient Now Button  ",e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click On Add Recipient Now Button  ",e);
		}

	}
	
	@Step("clicking On 'ADD RECIPIENT NOW' button")
	public void ClickOnAddRecipientNowBtnForAddPayeeRemittance() throws Exception {
		try {
			String xpath = "//android.widget.Button[@text='ADD RECIPIENT NOW']";
			List<RemoteWebElement> list = driver.findElements(By.xpath(xpath));
			if (list.size() > 0) {
				TakeScreenshot(DBSappObject.AddRecipientNowBtn());
				clickOnElement(DBSappObject.AddRecipientNowBtn());
			} else {
				TakeScreenshot(DBSappObject.AddOverseasRecipient());
				clickOnElement(DBSappObject.AddOverseasRecipient());
			}
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On Add Recipient Now Button  ",e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click On On Add Recipient Now Button  ",e);
		}
	}

	@Step("Verify 'Reference No. Field and its value' field and Verify 'MAKE A TRANSFER' Button After Expanding & Scrolling to the Page.")
	public void verifyReferenceFieldAndItsValue(String expectedText) throws Exception {
		try {
			clickOnElement(DBSappObject.expandButton());
			gestUtils.scrollUPtoObject("text", "Reference No.", DBSappObject.ReferenceNumberText());
			TakeScreenshot(DBSappObject.makeTransferButton());
			Asserts.assertEquals(getTexOfElement(DBSappObject.makeTransferButton()),
					CommonTestData.MAKE_TRANSFER.getEnumValue(), "'MAKE A TRANSFER' Text is not found");
			Asserts.assertEquals(getTexOfElement(DBSappObject.ReferenceNumberText()), expectedText,
					"'Reference no Field' is not found");
			//boolean i = DBSappObject.referenceNoValue().getText().isEmpty();
			//Asserts.assertTrue(i == false, "Reference Number not Found");
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Verify Reference Field Value ",e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Verify Reference Field Value  ",e);
		}
	}

	@Step("Verifying TopUp Label and click")
	public void topUpVerifyClick(String expectecText) throws Exception {
		try {

			String actualText = getTexOfElement(DBSappObject.topUpLabel());

			if (actualText.equalsIgnoreCase(expectecText))
				clickOnElement(DBSappObject.topUpButton());

			Asserts.assertEquals(actualText, expectecText, "Top up Label Not matching");

		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to verify TopUp lable and click  ",e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to verify TopUp lable and click  ",e);
		}

	}

	@Step("Verifying TopUp  Label and click and then verify Top Up Paylah label")
	public void payLahVerifyClick(String expectecText) throws Exception {
		try {

			String actualText = getTexOfElement(DBSappObject.paylahLabel());

			if (actualText.equalsIgnoreCase(expectecText))
				clickOnElement(DBSappObject.paylahLabel());

			Asserts.assertEquals(actualText, expectecText, "PayLah Label Not matching");
			Asserts.assertEquals(getTexOfElement(DBSappObject.topUpPaylahLabel()),
					CommonTestData.TOPUP_PAYLAH_LABEL.getEnumValue(), "'Top Up Paylah' Text is not found");
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to verify Paylah label and click  ",e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to verify Paylah label and click  ",e);
		}
	}

	@Step("Enter currency in EditBox")
	public void sendCurrencyInTextField(String text) throws Exception {
		try {
			enterTextInTextbox(DBSappObject.currencyTextBox(), text);
			TakeScreenshot(DBSappObject.currencyTextBox());
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to enter currency   ",e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to enter currency  ",e);
		}
	}

	@Step("Verify 'Top Up Paylah Label' field And Verify 'Enter Amount' field")
	public void verifyReviewTopUpLabel(String expectedText) throws Exception {
		try {
			wait.waitForElementVisibility(DBSappObject.reviewTopUpLabel());
			Asserts.assertEquals(getTexOfElement(DBSappObject.reviewTopUpLabel()), expectedText,
					"'Top Up Paylah' Text is not found");
			Asserts.assertEquals(getTexOfElement(DBSappObject.displayAmount()),
					CommonTestData.AMOUNT_PAYLAH.getEnumValue(), "'Display Amount' is incorrect");
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FIELDVERIFICATION_EXCEPTION", " Failed to verify fileds:TopUp Label and Enter Amount  ",e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("FIELDVERIFICATION_EXCEPTION", " Failed to verify fileds:TopUp Label and Enter Amount  ",e);
		}
	}

	@Step("Verifying TOP UP NOW  Label and click and then Verify 'Top-up Done' field and Display Amount")
	public void topUpNowVerifyClick(String expectecText) throws Exception {
		try {

			String actualText = getTexOfElement(DBSappObject.topUpNowButton());

			if (actualText.equalsIgnoreCase(expectecText))
				clickOnElement(DBSappObject.topUpNowButton());
			Asserts.assertEquals(actualText, expectecText, "TOP UP NOW button Not exist");
			wait.waitForElementVisibility(DBSappObject.topUpDoneLabel());
			Asserts.assertEquals(getTexOfElement(DBSappObject.topUpDoneLabel()),
					CommonTestData.TOPUP_UP_DONE_LABEL.getEnumValue(), "'Top-up Done' Text is not found");
			Asserts.assertEquals(getTexOfElement(DBSappObject.displayAmount()),
					CommonTestData.AMOUNT_PAYLAH.getEnumValue(), "'Display Amount' is incorrect");
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to verify click on TopUp Now Button and Top-up Done field and Display amount  ",e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to verify click on TopUp Now Button and Top-up Done field and Display amount  ",e);
		}

	}

	@Step("Verifying Logout Label and click")
	public void logOutTopUpVerifyClick(String expectecText) throws Exception {
		try {
			String actualText = getTexOfElement(DBSappObject.logOutPaylahButton());
			if (actualText.equalsIgnoreCase(expectecText))
				clickOnElement(DBSappObject.logOutPaylahButton());
			Asserts.assertEquals(actualText, expectecText, "LogOut button Not exist");
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to click on LogOut Button  ",e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to click on LogOut Button  ",e);
		}
	}

	@Step("Verifies the Applying Debit Card and Verify the completion page details.")
	public void ApplyDebitCard() throws Exception {
		try {
			ClickOnMoreButton();
			EnterPasscodeAndDone();
			SelectDebitCardOptionFromCardsSectionAndAuthenticationOfSecurePIN();
			FillingDetailsToApplyingDebitCard();
			ClickOnNextButton();
			MobileElement element = null;
			element = verifyElementExistInTheList(DBSappObject.PageHeaderList(),
					CommonTestData.REVIEW_APPLICATION.getEnumValue());
			if (element != null) {
				Asserts.assertEquals(getTexOfElement(element), CommonTestData.REVIEW_APPLICATION.getEnumValue(),
						CommonTestData.REVIEW_APPLICATION.getEnumValue() + " Text is not found");
			}

			gestUtils.scrollUPtoObject("text", "APPLY NOW", DBSappObject.ApplyNowButton());
			clickOnElement(DBSappObject.ApplyNowButton());
			ClickOnSubmitButtonAfterSettingCardPIN();
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("TESTCASE_EXCEPTION", " Failed to Execute Apply Debit card  ",e);
			System.out.println("Inside Appply debit card catch"+e.getCode());		
		}
		catch (Exception e) {			
			System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("TESTCASE_EXCEPTION", " Failed to Execute Apply Debit card  ",e);
		}
	}

	@Step("Verifies the Set Card Pin Page Header and then Submit While Entering Confirm and Create New Pin & Verifies the 'Application Submitted' Message.")
	public void ClickOnSubmitButtonAfterSettingCardPIN() throws Exception {
		try {
			MobileElement element = null;
			element = verifyElementExistInTheList(DBSappObject.PageHeaderList(),
					CommonTestData.SET_CARD_PIN.getEnumValue());
			if (element != null) {
				Asserts.assertEquals(getTexOfElement(element), CommonTestData.SET_CARD_PIN.getEnumValue(),
						CommonTestData.SET_CARD_PIN.getEnumValue() + " Text is not found");
			}
			clickOnElement(DBSappObject.CreateYourPINField());
			enterTextInTextbox(DBSappObject.CreateYourPINField(), CommonTestData.CREATE_PIN.getEnumValue());
			clickOnElement(DBSappObject.ConfirmNewPINField());
			enterTextInTextbox(DBSappObject.ConfirmNewPINField(), CommonTestData.CONFIRM_PIN.getEnumValue());
			driver.hideKeyboard();
			clickOnElement(DBSappObject.submitButton());

			element = verifyElementExistInTheList(DBSappObject.PageHeaderList(),
					CommonTestData.APPLICATION_SUBMITTED.getEnumValue());
			if (element != null) {
				Asserts.assertEquals(getTexOfElement(element), CommonTestData.APPLICATION_SUBMITTED.getEnumValue(),
						CommonTestData.APPLICATION_SUBMITTED.getEnumValue() + " Text is not Matching");
				System.out.println("Inside : " + CommonTestData.APPLICATION_SUBMITTED.getEnumValue());
			}

		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXEPTION", " Failed to Set card PIN And Click On Submit Button  ",e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("FUNCTIONAL_EXEPTION", " Failed to Click On Submit Button  ",e);
		}
	}

	@Step("Filling Required Details to applying Debit Card like as 'AccountToBeLinkedToTheCardField',"
			+ " 'Title', 'EnterNameToAppearOnTheCardField', 'Race', 'Marital Status','Residential Type',"
			+ "'Education','Economic Status','Annual Income', 'And Select Checkbox SendMeDBSPrmotionViaMail'.")
	public void FillingDetailsToApplyingDebitCard() throws Exception {
		try {
			// To filling Debit Card Details for applying Debit card.
			clickOnElement(DBSappObject.AccountToBeLinkedToTheCardField());
			selectElementFromTheGivenList(DBSappObject.DebitCardDetailsDropdownList(),
					CommonTestData.ACCOUNT_LINKED_WITH_DEBIT_CARD.getEnumValue());

			clickOnElement(DBSappObject.TitleField());
			selectElementFromTheGivenList(DBSappObject.DebitCardDetailsDropdownList(),
					CommonTestData.TITLE.getEnumValue());

			clickOnElement(DBSappObject.EnterNameToAppearOnTheCardField());
			enterTextInTextbox(DBSappObject.EnterNameToAppearOnTheCardField(),
					CommonTestData.NAMETO_APPEAR_ON_DEBITCARD.getEnumValue());
			driver.hideKeyboard();
			gestUtils.scrollUPtoObject("text", "Education", DBSappObject.EducationField());
			clickOnElement(DBSappObject.RaceField());
			selectElementFromTheGivenList(DBSappObject.DebitCardDetailsDropdownList(),
					CommonTestData.RACE.getEnumValue());

			clickOnElement(DBSappObject.MaritalStatusField());
			selectElementFromTheGivenList(DBSappObject.DebitCardDetailsDropdownList(),
					CommonTestData.MARITAL_STATUS.getEnumValue());

			clickOnElement(DBSappObject.ResidentialTypeField());
			selectElementFromTheGivenList(DBSappObject.DebitCardDetailsDropdownList(),
					CommonTestData.RESIDENCE_TYPE.getEnumValue());

			clickOnElement(DBSappObject.EducationField());
			selectElementFromTheGivenList(DBSappObject.DebitCardDetailsDropdownList(),
					CommonTestData.EDUCATION.getEnumValue());

			clickOnElement(DBSappObject.EconomicStatusField());
			selectElementFromTheGivenList(DBSappObject.DebitCardDetailsDropdownList(),
					CommonTestData.ECONOMIC_STATUS.getEnumValue());

			clickOnElement(DBSappObject.AnnualIncomeField());
			selectElementFromTheGivenList(DBSappObject.DebitCardDetailsDropdownList(),
					CommonTestData.ANNUAL_INCOME.getEnumValue());

			gestUtils.scrollUPtoObject("text", "NEXT", DBSappObject.nextButton());
			clickOnElement(DBSappObject.SendMeDBSPrmotionViaMail());

		}  catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to enter debit card details  ",e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to enter debit card details  ",e);
		}
	}

	@Step("Select Debit Card Option After Clicking on Cards Section and then 2FA Authentication Done.")
	public void SelectDebitCardOptionFromCardsSectionAndAuthenticationOfSecurePIN() throws Exception {
		try {
			gestUtils.scrollUPtoObject("text", "Cards", DBSappObject.CardsButton());
			if (isElementVisible(DBSappObject.CardsButton()))
				clickOnElement(DBSappObject.CardsButton());
			TakeScreenshot(DBSappObject.SelectDebitCard());
			clickOnElement(DBSappObject.SelectDebitCard());
			EnterPasscodeAndDone();
			selectDebitCardType(CommonTestData.DEBIT_CARD_NAME.getEnumValue());
			TakeScreenshot(DBSappObject.AccountToBeLinkedToTheCardField());
		} catch (HandleException e) {
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Select Debit card and authenticate ",e);
		}
		catch (Exception e) {
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Select Debit card and authenticate ",e);
		}
	}
	@Step("Verifies the Payee Add Bill Payment and after completion payment verifies the transfering amount.")
	public void PayeeAddBillPayment() throws Exception {
		try {
			ClickOnPayAndTransferBtn();
			EnterPasscodeAndDone();
			ClickOnBillsModule();
			ClickOnAddBillingOrganisation();
			EnterBillingOrganisationDetails(CommonTestData.PAYEEADD_BILLPAYMENT_ACCOUNTNAME.getEnumValue(),
					CommonTestData.PAYEEADD_BILLPAYMENT_REFERENCENUMBER.getEnumValue());
			ClickOnNextButton();
			Asserts.assertEquals(getTexOfElement(DBSappObject.PageHeader()),
					CommonTestData.REVIEW_RECIPIENT_DETAILS.getEnumValue(),
					CommonTestData.REVIEW_RECIPIENT_DETAILS.getEnumValue() + " Text is not matching.");
			VerifyBillingOrganisationAndBillReferenceNumber(
					CommonTestData.PAYEEADD_BILLPAYMENT_ACCOUNTNAME.getEnumValue(),
					CommonTestData.PAYEEADD_BILLPAYMENT_REFERENCENUMBER.getEnumValue());
			ClickOnAddRecipientNowBtn();
			VerifyYouHaveAddedRecipientMsgAfterEnterSecurePIN();
			VerifyBillingOrganisationAndBillReferenceNumber(
					CommonTestData.PAYEEADD_BILLPAYMENT_ACCOUNTNAME.getEnumValue(),
					CommonTestData.PAYEEADD_BILLPAYMENT_REFERENCENUMBER.getEnumValue());
			ClickOnMakeAPaymentAndEnterAmountInAmountEditField();
			EnterAmount(DBSappObject.AmountEditableField(), CommonTestData.AMOUNTTO_TRANSFERFUND.getEnumValue());
			ClickOnNextButton();
			Thread.sleep(3000);
			TakeScreenshot(DBSappObject.ReviewRequestHeader()); 
//			for(int i=0; i< DBSappObject.PageHeaderList2().size();i++) {
//				System.out.println(DBSappObject.PageHeaderList2().get(i).getText());
//			}
			Asserts.assertEquals(getTexOfElement(DBSappObject.PageHeaderList2().get(13)).toLowerCase(),
					CommonTestData.REVIEW_PAYMENT_PAGEHEADER.getEnumValue().toLowerCase(),
					CommonTestData.REVIEW_PAYMENT_PAGEHEADER.getEnumValue() + " Text is not matching");
			ClickOnPayNowButton();
			VerifyPaymentSubmittedMsg();
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("TESTCASE_EXCEPTION", " Failed to Execute Payee Add To Bill Payment ",e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("TESTCASE_EXCEPTION", " Failed to Execute Payee Add to Bill Payment  ",e);
		}
	}

	@Step("Enter Billing Organisation Details.")
	public void EnterBillingOrganisationDetails(String AccountName, String ReferenceNo) throws Exception {
		try {
			TakeScreenshot(DBSappObject.SelectBillingOrganisation());
			clickOnElement(DBSappObject.SelectBillingOrganisation());
			clickOnElement(DBSappObject.SearchForBillingOrganisationField());
			enterTextInTextbox(DBSappObject.SearchForBillingOrganisationField(), AccountName);
			clickOnElement(DBSappObject.SelectSearchedOption());
			clickOnElement(DBSappObject.EnterReferenceNoEditField());
			enterTextInTextbox(DBSappObject.EnterReferenceNoEditField(), ReferenceNo);
			backButton();
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Enter Billing Organisation Details ",e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Enter Billing Organisation Details ",e);
		}
	}

	@Step("Verify Enter Recipient Page Header After Click On Add Billing Organisation.")
	public void ClickOnAddBillingOrganisation() throws Exception {
		try {
			String xpath = "//android.widget.Button[@text='ADD RECIPIENT NOW']";
			List<RemoteWebElement> list = driver.findElements(By.xpath(xpath));
			if (list.size() > 0) {
				TakeScreenshot(DBSappObject.AddRecipientNowBtn());
				clickOnElement(DBSappObject.AddRecipientNowBtn());
			} else {
				TakeScreenshot(DBSappObject.AddBillingOrganisation());
				clickOnElement(DBSappObject.AddBillingOrganisation());
			}
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On Add Billing Organisation Button ",e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click On Add Billing Organisation Button ",e);
		}
	}
	
	@Step("Click On Bills Module.")
	public void ClickOnBillsModule() throws Exception {
		try {
			TakeScreenshot(DBSappObject.BillsButton());
			clickOnElement(DBSappObject.BillsButton());
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On Bill Module ",e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click On Bill Module ",e);
		}
	}

	@Step("Click On Make A Payment Button.")
	public void ClickOnMakeAPaymentAndEnterAmountInAmountEditField() throws Exception {
		try {
			TakeScreenshot(DBSappObject.MakeAPaymentButton());
			clickOnElement(DBSappObject.MakeAPaymentButton());
			TakeScreenshot(DBSappObject.PageHeader2());
			Asserts.assertEquals(getTexOfElement(DBSappObject.PageHeader2()),
						CommonTestData.PAY_TO_BILLER_PAGE_HEADER.getEnumValue(),
						CommonTestData.PAY_TO_BILLER_PAGE_HEADER.getEnumValue() + " Text is not matching");	
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On Make A Payment Button ",e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click on Make A Payment Button ",e);
		}
	}

	@Step("Click On Pay Now Button And Verify Payment Submitted Message & Verifies the 'Log out', "
			+ "'Make Another Transfer' Button, 'Share Payment Details' Button and 'Transferred Amount Value' "
			+ "after transferring the fund")
	public void VerifyPaymentSubmittedMsg() throws Exception {
		try {
			// verifies the payment completion with expected amount.
			if (isElementVisible(DBSappObject.ImageForPaymentSuccess())) {
				Asserts.assertEquals(getTexOfElement(DBSappObject.PageHeaderList2().get(0)),
						CommonTestData.PAYMENT_SUBMITTED.getEnumValue(),
						CommonTestData.PAYMENT_SUBMITTED.getEnumValue() + " Text is not matching");

				Asserts.assertEquals(getTexOfElement(DBSappObject.AmountEditableField()),
						CommonTestData.AMOUNTTO_TRANSFERFUND.getEnumValue() + ".00",
						CommonTestData.AMOUNTTO_TRANSFERFUND.getEnumValue() + " Text is not matching.");
			}

			Asserts.assertTrue(DBSappObject.LOGOUTButton().isDisplayed(), "Log Out Button not found.");

			gestUtils.scrollUPtoObject("text", "MAKE ANOTHER TRANSFER", null);
			Asserts.assertTrue(DBSappObject.MakeAnotherTransferBtn().isDisplayed(),
					"Make Another Transfer Button not found.");
			Asserts.assertTrue(DBSappObject.SharePaymentDetailsButton().isDisplayed(),
					"'Share Payment Details' Button not found.");
			Asserts.assertTrue(DBSappObject.BackIcon().isDisplayed(), "'Back' Button not found.");
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Verify Details After Submit Payment ",e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Verify Details After Submit Payment ",e);
		}
	}
	
	@Step("Click On Pay Now Button.")
	public void ClickOnPayNowButton() throws Exception { 
		try {
			TakeScreenshot(DBSappObject.PayNowButton());
			clickOnElement(DBSappObject.PayNowButton());
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On Pay Now Button ",e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click On Pay Now Button ",e);
		}
	}

	@Step("Verifies Billing Organisation And Bill Reference Number.")
	public void VerifyBillingOrganisationAndBillReferenceNumber(String AccountName, String ReferenceNum)
			throws Exception {
		try {
			if (isElementVisible(DBSappObject.BillingOrganisation())
					&& isElementVisible(DBSappObject.BillReferenceNo())) {
				
				String ActualAccountNameXpath = "//android.widget.TextView[@text='"+AccountName+"']";
				MobileElement ActualAccountNameEle = (MobileElement) driver.findElement(By.xpath(ActualAccountNameXpath));
				Asserts.assertEquals(getTexOfElement(ActualAccountNameEle), AccountName,
						AccountName + " Text is not matching");
				
				String ActualReferenceNumberXpath = "//android.widget.TextView[@text='"+ReferenceNum+"']";
				MobileElement ActualReferenceNumberEle = (MobileElement) driver.findElement(By.xpath(ActualReferenceNumberXpath));
				Asserts.assertEquals(getTexOfElement(ActualReferenceNumberEle), ReferenceNum,
						ReferenceNum + " Text is not matching");
			}
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Verify Billing Organisation Details After Adding Payee ",e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Verify Billing Organisation Details After Adding Payee ",e);
		}
	}

	@Step("Verifies the Open Account.")
	public void OpenAccount() throws Exception {
		try {
			ClickOnMoreButton();
			EnterPasscodeAndDone();
			gestUtils.scrollUPtoObject("text", "Deposit Accounts", DBSappObject.DepositAccountsModule());
			ClickOnDepositAccountsAnd2FAAuthenticationDone();
			SelectOpenAccountOptionAndVerifyAccountBenifitsPageHeader();
			ClickOnopenAccountInStepButton();
			EnterMonthlySavingsAmtAndSelectSourceOfFundsForSavings();
			ClickOnNextButton1();
			VerifyWarningMessageAndImportantNotes();
			gestUtils.scrollUPtoObject("text", "I ACKNOWLEDGE", DBSappObject.IACKNOWLEDGEButton());
			ClickOnIAcknowledgeButtonAndReviewOpenAccountApplication();
			gestUtils.scrollUPtoObject("text", "OPEN ACCOUNT NOW", DBSappObject.OpenAccountNowButton());
			ClickOnOpenAccountNowButton();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Step("Select Open Account Option By Clicking And Verify Account Benifits Page Header.")
	public void SelectOpenAccountOptionAndVerifyAccountBenifitsPageHeader() throws Exception {
		try {
			selectElementFromTheGivenList(DBSappObject.SelectOpenAccountOptionList(),
					CommonTestData.OPEN_ACCOUNT_OPTION.getEnumValue());
			// clickOnElement(DBSappObject.SelectOpenAccountOption());
			Asserts.assertEquals(getTexOfElement(DBSappObject.PageHeaderForOpenAccount()),
					CommonTestData.ACCOUNT_BENIFITS.getEnumValue(),
					CommonTestData.ACCOUNT_BENIFITS.getEnumValue() + " Page Header Text is not matching");
		} catch (Exception e) {


			e.printStackTrace();

			throw e;
		}
	}

	@Step("Enter Monthly Savings Amount And Select Source Of Funds For Savings.")
	public void EnterMonthlySavingsAmtAndSelectSourceOfFundsForSavings() throws Exception {
		try {
			clickOnElement(DBSappObject.EnterMonthlySavingsAmtEditField());
			enterTextInTextbox(DBSappObject.EnterMonthlySavingsAmtEditField(),
					CommonTestData.MONTHLY_SAVING_AMT_BALANCE.getEnumValue());

			driver.hideKeyboard();
			clickOnElement(DBSappObject.SelectSourceOfFundsForSavingsDropdown());
			selectElementFromTheGivenList(DBSappObject.SelectSourceOfFundsForSavingsDropdownList(),
					CommonTestData.SELECT_ACCOUNT.getEnumValue());

		} catch (Exception e) {


			e.printStackTrace();

			throw e;
		}
	}

	@Step("Click On Open Account In 2/3 Step Button.")
	public void ClickOnopenAccountInStepButton() throws Exception {
		try {
			clickOnElement(DBSappObject.StepOpenAccountButton());
			Asserts.assertEquals(getTexOfElement(DBSappObject.PageHeader()), CommonTestData.OPEN_ACCOUNT.getEnumValue(),
					CommonTestData.OPEN_ACCOUNT.getEnumValue() + " Page Header Text is not matching");
		} catch (Exception e) {


			e.printStackTrace();

			throw e;
		}
	}

	@Step("Click On Deposit Accounts Module And 2FA Authentication Done And Verifies the Open Account Page Header.")
	public void ClickOnDepositAccountsAnd2FAAuthenticationDone() throws Exception {
		try {
			clickOnElement(DBSappObject.DepositAccountsModule());
			EnterPasscodeAndDone();
			Asserts.assertEquals(getTexOfElement(DBSappObject.PageHeaderForOpenAccount()),
					CommonTestData.OPEN_ACCOUNT.getEnumValue(),
					CommonTestData.OPEN_ACCOUNT.getEnumValue() + " Page Header Text is not matching");
		} catch (Exception e) {


			e.printStackTrace();

			throw e;
		}
	}

	@Step("Verify Warning Message And Important Notes.")
	public void VerifyWarningMessageAndImportantNotes() throws Exception {
		try {
			Asserts.assertEquals(getTexOfElement(DBSappObject.PageHeader()),
					CommonTestData.IMPORTANT_NOTES.getEnumValue(),
					CommonTestData.IMPORTANT_NOTES.getEnumValue() + " Text is not matching.");

			Asserts.assertEquals(getTexOfElement(DBSappObject.WarningHeading()),
					CommonTestData.WARNING_HEADING_TEXT.getEnumValue(),
					CommonTestData.WARNING_HEADING_TEXT.getEnumValue() + " Message is not matching");
		} catch (Exception e) {


			e.printStackTrace();

			throw e;
		}
	}

	@Step("Click On Open Account Now Button And Got 'Your account is open and ready to use!' Message.")
	public void ClickOnOpenAccountNowButton() throws Exception {
		try {
			clickOnElement(DBSappObject.OpenAccountNowButton());
			if (isElementVisible(DBSappObject.OpenAcconuntSuccessImageIcon()))
				Asserts.assertEquals(getTexOfElement(DBSappObject.AccountStatusMessage()),
						CommonTestData.YOUR_ACCOUNT_OPEN_READYTOUSE_MESSAGE.getEnumValue(),
						CommonTestData.YOUR_ACCOUNT_OPEN_READYTOUSE_MESSAGE.getEnumValue()
								+ " Message is not matching.");
		} catch (Exception e) {


			e.printStackTrace();

			throw e;
		}
	}

//	@Step("Click On IAcknowledge Button And Review Open Account Application.")
//	public void ClickOnIAcknowledgeButtonAndReviewOpenAccountApplication() throws Exception {
//		try {
//			clickOnElement(DBSappObject.IACKNOWLEDGEButton());
//			Asserts.assertEquals(getTexOfElement(DBSappObject.PageHeader()),
//					CommonTestData.REVIEW_ACCOUNT_APPLICATION.getEnumValue(),
//					CommonTestData.REVIEW_ACCOUNT_APPLICATION.getEnumValue() + " Text is not matched.");
//			if (isElementVisible(DBSappObject.YouAreOpeningText())) {
//				MobileElement element = verifyElementExistInTheList(DBSappObject.AccountList(),
//						CommonTestData.OPEN_ACCOUNT_OPTION.getEnumValue());
//				Asserts.assertEquals(getTexOfElement(element), CommonTestData.OPEN_ACCOUNT_OPTION.getEnumValue(),
//						" Given account is not matched or found.");
//			}
//
//		} catch (Exception e) {
//
//
//			e.printStackTrace();
//
//			throw e;
//		}
//	}
	@Step("Click On IAcknowledge Button And Review Open Account Application.")
	public void ClickOnIAcknowledgeButtonAndReviewOpenAccountApplication() throws Exception {
		try {
			gestUtils.scrollUPtoObject("text", "I ACKNOWLEDGE", DBSappObject.IACKNOWLEDGEButton());
			clickOnElement(DBSappObject.IACKNOWLEDGEButton());
			Asserts.assertEquals(getTexOfElement(DBSappObject.PageHeader2()),
					CommonTestData.REVIEW_ACCOUNT_APPLICATION.getEnumValue(),
					CommonTestData.REVIEW_ACCOUNT_APPLICATION.getEnumValue() + " Text is not matched.");
			if (isElementVisible(DBSappObject.YouAreOpeningText())) {
				Asserts.assertTrue(DBSappObject.YouAreOpeningText().isDisplayed(),
						CommonTestData.OPEN_ACCOUNT_OPTION.getEnumValue() + "text not displaying.");
				String a = getTexOfElement(DBSappObject.AccountNameList().get(0));
				System.out.println("0" + a);
				Asserts.assertEquals(getTexOfElement(DBSappObject.AccountNameList().get(0)),
						CommonTestData.OPEN_ACCOUNT_OPTION.getEnumValue(), " Given account is not matched or found.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}


	@Step("Click on 'Account type' From List under Local fund Limit page'")
	public void selectDebitCardType(String debitCardToBeSelected) throws Exception {
		try {
			if(DBSappObject.DebitCardDetailsDropdownList().size()>0) {
				wait.waitForElementVisibility(DBSappObject.DebitCardDetailsDropdownList().get(0));
				List<MobileElement> Elementlist = DBSappObject.DebitCardDetailsDropdownList();
				int l = Elementlist.size();
				int index = 0;
				String accountFromList = null;
				for (int i = 0; i <= l; i++) {
					accountFromList = Elementlist.get(i).getText();
					if (accountFromList.contains(debitCardToBeSelected)) {
						index++;
						clickOnElement(Elementlist.get(i));
						break;
					}
				}
				Asserts.assertTrue(index > 0,
						"No " + debitCardToBeSelected + " found in the list of corresponding value");
			} else {
				if(androidAlert.isAlertPresent()) {
					System.out.println("Alert title :: "+this.driver.switchTo().alert().getText()); 
				}	
				Asserts.assertFail(debitCardToBeSelected + " not found in the list as list size is 0");
			}
		} catch (HandleException e) {
			//System.out.println("Inside select debit card type catch" + e.getCode());
			obj_handleexception.throwHandleException("ELEMENTNOTINLIST_EXCEPTION",
					" Failed to Select Debit card type:: " + debitCardToBeSelected, e);
		} catch (Exception e) {
			//System.out.println("Inside select debit card type catch 3");
			obj_handleexception.throwException("ELEMENTNOTINLIST_EXCEPTION",
					" Failed to Select Debit card type " + debitCardToBeSelected, e);
		}
	}

	@Step("Select element from the given lists")
	public void selectElementFromTheGivenList(List<MobileElement> elementList, String elementToBeSelected)
			throws Exception {
		try {
			if (elementList.size() > 0) {
				wait.waitForElementVisibility(elementList.get(1));
				List<MobileElement> Elementlist = elementList;
				int l = Elementlist.size();
				int index = 0;
				String accountFromList = null;
				for (int i = 0; i < l; i++) {
					accountFromList = Elementlist.get(i).getText();
					if (accountFromList.contains(elementToBeSelected)) {
						index++;
						clickOnElement(Elementlist.get(i));
						break;
					}
				}

				Asserts.assertTrue(index > 0,
						"No " + elementToBeSelected + " found in the list of corresponding value");
			}
			else {
				Asserts.assertFail(elementToBeSelected + " not found in the list as list size is 0");
			}
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("ELEMENTNOTINLIST_EXCEPTION", " Failed to select element from the list  ",e);
			//System.out.println("Inside Appply debit card catch"+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("ELEMENTNOTINLIST_EXCEPTION", " Failed to select element from the list  ",e);
		}
	}

	@Step("Click on 'Account type' From List under Local fund Limit page'")
	public MobileElement verifyElementExistInTheList(List<MobileElement> elementList, String elementTextToBeVerified)
			throws Exception {
		MobileElement element = null;
		try {
			if(elementList.size() > 0) {
				wait.waitForElementVisibility(elementList.get(1));
				int l = elementList.size();

				String accountFromList = null;
				for (int i = 0; i < l; i++) {
					accountFromList = elementList.get(i).getText();
					System.out.println(accountFromList + " : " + i);
					if (accountFromList.contains(elementTextToBeVerified)) {
						element = elementList.get(i);
						break;
					}
				}
				return element;
			}
			else {
				Asserts.assertFail(elementTextToBeVerified + " not found in the list as list size is 0");
			}
			
		} catch (HandleException e) {
			//System.out.println("Inside select debit card type catch" + e.getCode());
			obj_handleexception.throwHandleException("ELEMENTNOTINLIST_EXCEPTION",
					" Failed to Select Debit card type:: " + elementTextToBeVerified, e);
		} catch (Exception e) {
			//System.out.println("Inside select debit card type catch 3");
			obj_handleexception.throwException("ELEMENTNOTINLIST_EXCEPTION",
					" Failed to Select Debit card type " + elementTextToBeVerified, e);
		}
		return element;
	}

	@Step("Verifies the Payee Add Local OtherBank and verifies 'YOU HAVE ADDED RECIPIENT MSG' .")
	public void PayeeAddLocalOtherBank() throws Exception {
		try {
			ClickOnPayAndTransferBtn();
			EnterPasscodeAndDone();
			clickOnLocalButton();
			clickOnAddLocalRecipientBtnAndVerifyLocalTransferPayNowPageHeader();

			String ExpectedRecipientName = CommonTestData.LOCAL_RECIPIENT_NAME.getEnumValue();
			System.out.println("ExpectedRecipientName is: " + ExpectedRecipientName);

//			int randomInt = GenerateRandomInt();
//			String s = String.valueOf(randomInt);
//			System.out.println("random number:" + s);
			String ExpectedAccountNumber = CommonTestData.LOCAL_RECIPIENT_ACCOUNT_NUMBER.getEnumValue();
			System.out.println("ExpectedAccountNumber is: " + ExpectedAccountNumber);

			EnterRecipientDetailsAfterSelectingBankAccountOption(ExpectedRecipientName,
					CommonTestData.LOCAL_RECIPIENT_BANK_NAME.getEnumValue(), ExpectedAccountNumber);
			ClickOnNextBtnAndReviewRecipientDetails();
			ClickOnAddRecipientNowBtn();
			VerifyYouHaveAddedRecipientMsgAfterEnterSecurePIN();
			verifyValidationForPayeeAdd(ExpectedRecipientName, CommonTestData.LOCAL_RECIPIENT_BANK_NAME.getEnumValue(),
					ExpectedAccountNumber);
			
			//Leaving on Home Page After adding payee Local To Other Bank for next run.
			ClickOnBackIcon();
			ClickOnHomeButton();
			
		}catch (HandleException e) {	
			obj_handleexception.throwHandleException("TESTCASE_EXCEPTION", " Failed to Execute PayeeAddLocalOtherBank ",e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("TESTCASE_EXCEPTION", " Failed to Execute PayeeAddLocalOtherBank  ",e);
		}
	}

	@Step("Click On Local Button.")
	public void clickOnLocalButton() throws Exception {
		try {
			TakeScreenshot(DBSappObject.LocalButton());
			clickOnElement(DBSappObject.LocalButton());
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On Local Button  ",e);
				}
		catch (Exception e) {			
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click On Local Button  ",e);
		}
	}

	@Step("Verifies Visibilty of 'logout' and 'make a transfer' button and Verifies the recipient name, account number, bank name.")
	public void verifyValidationForPayeeAdd(String ExpectedRecipientName, String BankName, String AccountNumber)
			throws Exception {
		try {
			wait.waitForElementVisibility(DBSappObject.LogoutBtn());
			Asserts.assertTrue(DBSappObject.LogoutBtn().isDisplayed(), "Log Out Button not found.");
			Asserts.assertTrue(DBSappObject.makeTransferButton().isDisplayed(), "Make A Transfer Button not found.");
			String[] ExpTitleList = new String[] { "Recipient's Name", "Country", "Recipient's Bank",
					"Recipient's Account No.", "Reference No." };

			for (int i = 0; i < DBSappObject.PayeeTitleList().size(); i++) {
				Asserts.assertEquals(getTexOfElement(DBSappObject.PayeeTitleList().get(i)), ExpTitleList[i],
						ExpTitleList[i] + "Titles is not matching after adding payee");
			}

			Asserts.assertEquals(getTexOfElement(DBSappObject.PayeeValueList().get(0)), ExpectedRecipientName,
					ExpectedRecipientName + " is not matching after adding payee");
			Asserts.assertEquals(getTexOfElement(DBSappObject.PayeeValueList().get(2)), BankName,
					BankName + " is not matching after adding payee");
			Asserts.assertEquals(getTexOfElement(DBSappObject.PayeeValueList().get(3)), AccountNumber,
					AccountNumber + " is not matching after adding payee");
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FILEDVERIFICATION_EXCEPTION", " Failed to verify LogOut, Make Transfer Button etc  ",e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("FILEDVERIFICATION_EXCEPTION", " Failed to verify LogOut, Make Transfer Button etc  ",e);
		}
	}
	
	@Step("Delete Payee To DBS/POSB.")
	public void DeletePayeeDBSPOSB(String ExpectedRecipientName) throws Exception {
		try {
			ClickOnPayAndTransferBtn();
			EnterPasscodeAndDone();
			ClickOnLocalModule();
			DeletePayee(ExpectedRecipientName);
			ClickOnCloseButton();
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("DELETEPAYEE_EXCEPTION", " Failed to Execute Delete Payee  For DBS/POSB.",e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("DELETEPAYEE_EXCEPTION", " Failed to Execute Delete Payee For DBS/POSB. ",e);
		}
	}
	
	
	@Step("Delete Payee To Local To Other Bank.")
	public void DeletePayeeLocalToOtherBank(String ExpectedRecipientName) throws Exception {
		try {
			ClickOnPayAndTransferBtn();
			EnterPasscodeAndDone();
			ClickOnLocalModule();
			DeletePayee(ExpectedRecipientName);
			ClickOnCloseButton();
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("DELETEPAYEE_EXCEPTION", " Failed to Execute Delete Payee  For Local To Other Bank",e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("DELETEPAYEE_EXCEPTION", " Failed to Execute Delete Payee Local To Other Bank ",e);
		}
	}
	
	@Step("Delete Payee To Remittance.")
	public void DeletePayeeRemittance(String ExpectedRecipientName) throws Exception {
		try {
			ClickOnPayAndTransferBtn();
			EnterPasscodeAndDone();
			overseasVerifyClick(CommonTestData.OVERSEAS_ICON.getEnumValue());
			DeletePayee(ExpectedRecipientName);
			ClickOnOverseasScreenClosingButton();
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("DELETEPAYEE_EXCEPTION", " Failed to Execute Delete Payee Remittance ",e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("DELETEPAYEE_EXCEPTION", " Failed to Execute Delete Payee Remittance ",e);
		}
	}
	
	@Step("Click On Close Button to closing Overseas Screen.")
	public void ClickOnOverseasScreenClosingButton() throws Exception {
		try {
			TakeScreenshot(DBSappObject.CloseBtn_OverseasScreen()); 
			clickOnElement(DBSappObject.CloseBtn_OverseasScreen()); 
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click on Close Button  ",e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click on Close Button ",e);
		}
	}
	
	
	
	@Step("Delete Payee To Bill Payment.")
	public void DeletePayeeToBillPayment(String ExpectedRecipientName) throws Exception {
		try {
			ClickOnPayAndTransferBtn();
			EnterPasscodeAndDone();
			ClickOnBillsModule();
			DeletePayee(ExpectedRecipientName);
			ClickOnCloseButton();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Step("Click On Close Button.")
	public void ClickOnCloseButton() throws Exception {
		try {
			TakeScreenshot(DBSappObject.CloseButton()); 
			clickOnElement(DBSappObject.CloseButton());
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click on Close Button  ",e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click on Close Button ",e);
		}
	}

	@Step("Delete Payee.")
	public void DeletePayee(String ExpectedRecipientName) throws Exception {
		try {
			// "This service isn't available right now. You can try again soon, or call 1800
			// 111 1111 for assistance."
			
			String xpath = "//android.widget.ImageView[contains(@resource-id,':id/tv_expandable_item_selected')]";
			List<RemoteWebElement> Payeelist = driver.findElements(By.xpath(xpath));
			int ExpectedTotalPayeeSize = Payeelist.size();
			if(ExpectedTotalPayeeSize>0) {
				for (int i = 0; i < ExpectedTotalPayeeSize; i++) {
					ClickOnDeletePayeeToIcon(i);
					TakeScreenshot(DBSappObject.payee_details_title_name()); 
					Asserts.assertEquals(getTexOfElement(DBSappObject.payee_details_title_name()),
							CommonTestData.RECIPIENT_DETAILS_PAGEHEADER.getEnumValue(),
							CommonTestData.RECIPIENT_DETAILS_PAGEHEADER.getEnumValue() + " is not matching after adding payee");
					String RecipientNameXpath =	"//android.widget.TextView[@text='" + ExpectedRecipientName + "']";
					List<RemoteWebElement> RecipientNameElementList = driver.findElements(By.xpath(RecipientNameXpath));
					if (RecipientNameElementList.size() > 0) {
						ClickOnMoreOptionBtnAndDeletePayeeBtn();
						ClickOnYesBtn();

						for (int innerLoop = 0; innerLoop < 2; innerLoop++) {
							//Sometimes this alert with message (You may be facing some delays and
							//we are trying to sort it out now. Sorry for the inconvenience.
							// Do check back later.) coming. So this Thread.sleep(); added here.
							
							Thread.sleep(4000); 
							String ErrorissueXpath = 	"//android.widget.TextView[@resource-id='android:id/message']";
							List<RemoteWebElement> list = driver.findElements(By.xpath(ErrorissueXpath));
							if (list.size() > 0) {
								if(getTexOfElement(DBSappObject.ErrorMessgeElement()).contains("You may be facing some delays")) {
									TakeScreenshot(DBSappObject.OKButtonDeletePayee());
									clickOnElement(DBSappObject.OKButtonDeletePayee());
									ClickOnDeletePayeeToIcon(i);
									TakeScreenshot(DBSappObject.payee_details_title_name()); 
									Asserts.assertEquals(getTexOfElement(DBSappObject.payee_details_title_name()),
											CommonTestData.RECIPIENT_DETAILS_PAGEHEADER.getEnumValue(),
											CommonTestData.RECIPIENT_DETAILS_PAGEHEADER.getEnumValue() + " is not matching after adding payee");
									ClickOnMoreOptionBtnAndDeletePayeeBtn();
									ClickOnYesBtn();
								} 
							}
						}
						
						ClickOnOkButtonAfterVerifyingPayeeDeletedMsg(ExpectedRecipientName,ExpectedTotalPayeeSize);
						break;
					} else {
						clickOnElement(DBSappObject.BackBtnImageView());
					}
				}
			}	
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("DELETEPAYEE_EXCEPTION", " Failed to Execute Delete Payee  ",e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("DELETEPAYEE_EXCEPTION", " Failed to Execute Delete Payee  ",e);
		}
	}
	

	@Step("Click On OK Button after verifying 'Payee Name deleted' message.")
	public void ClickOnOkButtonAfterVerifyingPayeeDeletedMsg(String ExpectedRecipientName, int ExpectedTotalPayeeSize) throws Exception {
		try {
			String payeeName = ExpectedRecipientName + " deleted.";
			System.out.println(payeeName);
			String PayeeNameXpath = "//android.widget.TextView[@text='" + payeeName + "']";
			MobileElement DeletePayeeMessageElement = (MobileElement) driver
					.findElement(By.xpath(PayeeNameXpath));
			if (isElementVisible(DeletePayeeMessageElement)) {
				TakeScreenshot(DBSappObject.OKButtonDeletePayee());
				clickOnElement(DBSappObject.OKButtonDeletePayee());
				Thread.sleep(1000);
			}
			
			String xpath = "//android.widget.ImageView[contains(@resource-id,':id/tv_expandable_item_selected')]";
			List<RemoteWebElement> Payeelist = driver.findElements(By.xpath(xpath));
			
			int ActualTotalPayeeSize = Payeelist.size();
			int ExpectedTotalSizeAfterDeletingPayee = ExpectedTotalPayeeSize - 1; 
			//Asserts.assertEquals(String.valueOf(ExpectedTotalSizeAfterDeletingPayee),
				//	String.valueOf(ActualTotalPayeeSize), " Payee is not deleting after adding payee.");
			
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On OK Button and delete msg  ",e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click On OK Button and delete msg  ",e);
		}
	}

	@Step("Click On Yes Button after verifying 'Are you sure to delete payee' message.")
	public void ClickOnYesBtn() throws Exception {
		try {
			if (isElementVisible(DBSappObject.AreYouSureToDeleteThisPayeeMessage()))
				clickOnElement(DBSappObject.YesBtn());
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On Yes Button  ",e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click On Yes Button   ",e);
		}
	}

	@Step("Click On Local Module.")
	public void ClickOnLocalModule() throws Exception {
		try {
			TakeScreenshot(DBSappObject.LocalButton()); 
			clickOnElement(DBSappObject.LocalButton());
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On Local Module  ",e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click On Local Module  ",e);
		}
	}
	
	
	@Step("Click On Delete payee I Icon from the list.")
	public void ClickOnDeletePayeeToIcon(int index) throws Exception {
		try {
			TakeScreenshot(DBSappObject.PayeeAddedExpandableIconList().get(index)); 
			clickOnElement(DBSappObject.PayeeAddedExpandableIconList().get(index));
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On Delete Payee Icon(i)  ",e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click On Delete Payee Icon(i)  ",e);
		}
	}

	@Step("Click On 'More Options' Button And 'Delete payee' Button.")
	public void ClickOnMoreOptionBtnAndDeletePayeeBtn() throws Exception {
		try {
			clickOnElement(DBSappObject.MoreOptionBtn());
			TakeScreenshot(DBSappObject.DeletePayeeBtn()); 
			clickOnElement(DBSappObject.DeletePayeeBtn());
			TakeScreenshot(DBSappObject.AreYouSureToDeleteThisPayeeMessage());
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On More and Delete Payee Button  ",e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click On More and Delete Payee Button  ",e);
		}
	}

	@Step("Click On Next Btn And Review Recipient Details.")
	public void ClickOnNextBtnAndReviewRecipientDetails() throws Exception {
		try {
			TakeScreenshot(DBSappObject.NextButtonToAddedLocalRecipient());
			clickOnElement(DBSappObject.NextButtonToAddedLocalRecipient());
			wait.waitForElementVisibility(DBSappObject.AddRecipientNowBtn());
			Asserts.assertEquals(getTexOfElement(DBSappObject.PageHeader()),
					CommonTestData.REVIEW_RECIPIENT_DETAILS.getEnumValue(),
					CommonTestData.REVIEW_RECIPIENT_DETAILS.getEnumValue() + " Text is not matching.");
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On Next Button and verify Add Recipient Now  ",e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click On Next Button and verify Add Recipient Now  ",e);
		}
	}


	@Step("verify balance on peek balance popup in prelogin page should be same as current account balance in dashboard with current date and time.")
	public void VerifyPeekBalance() throws Exception {
		try {
			ClickOnMoreButton();
			EnterPasscodeAndDone();
			Asserts.assertTrue(isElementVisible(DBSappObject.DepositsAccountType()),
					"Deposits Account Type is not displayed on home page after login.");
			clickOnElement(DBSappObject.DepositsAccountName().get(0));
			EnterPasscodeAndDone();

			for (int i = 0; i < DBSappObject.AccountTitleList().size(); i++) {
				String j = DBSappObject.AccountTitleList().get(i).getText();
				System.out.println(i + ":   " + j);

				String x = DBSappObject.AccountValueList().get(i).getText();
				System.out.println(i + ":   " + x);
			}

			Asserts.assertEquals(getTexOfElement(DBSappObject.AccountTitleList().get(1)),
					CommonTestData.TOTAL_BALANCE_TITLE.getEnumValue(),
					CommonTestData.TOTAL_BALANCE_TITLE.getEnumValue() + " Text is not matching.");

			String ExpectedTotalBalanceValue = DBSappObject.AccountValueList().get(1).getText();
			System.out.println("ExpectedTotalBalanceValue:   " + ExpectedTotalBalanceValue);

			String ExpectedUserAccountName = DBSappObject.UserAccountName().getText();
			System.out.println("ExpectedUserAccountName:   " + ExpectedUserAccountName);

			Asserts.assertEquals(getTexOfElement(DBSappObject.UserAccountName()),
					CommonTestData.USER_ACCOUNT_NAME.getEnumValue(),
					CommonTestData.USER_ACCOUNT_NAME.getEnumValue() + " Text is not matching.");

			String ExpectedUserAccountNumber = DBSappObject.UserAccountNumber().getText();
			System.out.println("ExpectedUserAccountNumber:   " + ExpectedUserAccountNumber);

			clickOnElement(DBSappObject.ToolbarBackIcon());

			ClickOnMoreButton();
			EnterPasscodeAndDone();
clickOnElement(DBSappObject.ContactSearchfield());
			clickOnElement(DBSappObject.EditTextSearchBox());
			enterTextInTextbox(DBSappObject.EditTextSearchBox(), CommonTestData.PEEK_BALANCE.getEnumValue());
			TakeScreenshot(DBSappObject.SelectPeekBalance());
			clickOnElement(DBSappObject.SelectPeekBalance());

			for (int i = 0; i < DBSappObject.AccountTitleList().size(); i++) {
				String j = DBSappObject.AccountTitleList().get(i).getText();
				System.out.println(i + "App Settings:   " + j);
			}

			Asserts.assertEquals(getTexOfElement(DBSappObject.AccountTitleList().get(1)),
					CommonTestData.APP_SETTINGS.getEnumValue(),
					CommonTestData.APP_SETTINGS.getEnumValue() + " Text is not matching.");

			clickOnElement(DBSappObject.PeekBalanceToggle());
			clickOnElement(DBSappObject.AccountForPeekBalanceDropdown());

			String xpath = "//android.widget.TextView[@text='" + ExpectedUserAccountName + "']";
			MobileElement selectAccount = (MobileElement) driver.findElement(By.xpath(xpath));
			clickOnElement(selectAccount);

			String SelectedAccountNameWithAccountNumber = ExpectedUserAccountName + " " + ExpectedUserAccountNumber;
			System.out.println("SelectedAccountNameWithAccountNumber+: " + SelectedAccountNameWithAccountNumber);
			Asserts.assertEquals(getTexOfElement(DBSappObject.SelectedAccountForPeekBalance()),
					SelectedAccountNameWithAccountNumber,
					SelectedAccountNameWithAccountNumber + " Text is not matching.");
			clickOnElement(DBSappObject.SaveBtn());
			if (isElementVisible(DBSappObject.PersonalizeYourDevicePopup())) {
				TakeScreenshot(DBSappObject.PersonalizeYourDevicePopup());
				clickOnElement(DBSappObject.OKBtn_PersonalizeYourDevicePopup());
			}

			String ErrorMsg = getTexOfElement(DBSappObject.ErrorMessgeElement());

			if (CommonTestData.PEEK_BALANCE_DIGIALERT_MSG.getEnumValue().equals(ErrorMsg)) {
				TakeScreenshot(DBSappObject.OKButton());
				clickOnElement(DBSappObject.OKButton());
			}

			clickOnElement(DBSappObject.BackBtnImageView());
			clickOnLogoutAndVerify(CommonTestData.LOGOUT.getEnumValue(), CommonTestData.RATE_MESSAGE.getEnumValue());
			clickOnElement(DBSappObject.CloseBtnToClosingTapToStarPage());
			Asserts.assertEquals(getTexOfElement(DBSappObject.PeekBalanceSubtitle()),
					CommonTestData.PEEK_BALANCE_SUBTITLE.getEnumValue(),
					CommonTestData.PEEK_BALANCE_SUBTITLE.getEnumValue() + " Text is not matching.");

			// TODO: Code Add for tap and hold on above element and get total amount balance

			// DeRegister Process to removing peek balance from login page for next run.
			clickOnElement(DBSappObject.LogInButton());
			TakeScreenshot(DBSappObject.NotYouLink());
			clickOnElement(DBSappObject.NotYouLink());
			String PeekBalance_DeregisterAlertMsg = getTexOfElement(DBSappObject.ErrorMessgeElement());

			if (CommonTestData.PEEK_BALANCE_DEREGISTER_MESSAGE.getEnumValue().equals(PeekBalance_DeregisterAlertMsg)) {
				TakeScreenshot(DBSappObject.PeekbalanceDeregisterButton());
				clickOnElement(DBSappObject.PeekbalanceDeregisterButton());
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}



	@Step("Click On More Button and then 2FA Authentication Done.")
	public void ClickOnMoreButton() throws Exception {
		try {
			TakeScreenshot(DBSappObject.MoreBtn());
			if (isElementVisible(DBSappObject.MoreBtn()))
				clickOnElement(DBSappObject.MoreBtn());

			String xpath = "//android.widget.ImageView[@content-desc='CLOSE']";
			List<RemoteWebElement> list = driver.findElements(By.xpath(xpath));
			if (list.size() > 0) {
				TakeScreenshot(DBSappObject.CloseButton());
				clickOnElement(DBSappObject.CloseButton());
			}
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On More Button  ",e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click On More Button  ",e);
		}
	}

	@Step("Verify 'You have added a Recipient ' after excecuting Payee Add Remittance Case.")
	public void PayeeAddRemittance() throws Exception {
		try {
			ClickOnPayAndTransferBtn();
			EnterPasscodeAndDone();
			overseasVerifyClick(CommonTestData.OVERSEAS_ICON.getEnumValue());
			ClickOnAddRecipientNowBtnForAddPayeeRemittance();
			sendDataInSearchBoxAndSelectFromDropDown(CommonTestData.COUNTRY_AUS.getEnumValue(),
					CommonTestData.COUNTRY_AUS.getEnumValue(), DBSappObject.locationAutocompleteSearchBox());
			CurrencyTypeVerifyClick(CommonTestData.CURRENCY_AUS.getEnumValue());
			ClickOnNextButton();
			sendBankCode(CommonTestData.BANK_BCD_CODE.getEnumValue());
			ClickOnNextButton();
			ClickOnNextButton();
			sendAccountNo(CommonTestData.ACCOUNT_NO.getEnumValue());
			sendFullName(CommonTestData.FULL_NAME.getEnumValue());
			gestUtils.scrollUPtoObject("text", "NEXT", DBSappObject.nextButton());
			sendAddress(CommonTestData.ADDRESS.getEnumValue());
			sendcity(CommonTestData.CITY.getEnumValue());
			ClickOnNextButton();
			verifyRecipientReviewDetailLabel(CommonTestData.REVIEW_RECIPIENT_LABEL.getEnumValue());
			ClickOnAddRecipientNowBtn();
			EnterPasscodeAndDone();
			wait.waitForElementVisibility(DBSappObject.MainHeaderOrSuccessMsgElement());
			if(getTexOfElement(DBSappObject.MainHeaderOrSuccessMsgElement()).toLowerCase().equalsIgnoreCase(CommonTestData.YOU_HAVE_ADDED_RECIPIENT_MSG.getEnumValue()))
				Asserts.assertEquals(getTexOfElement(DBSappObject.MainHeaderOrSuccessMsgElement()).toLowerCase(),
					CommonTestData.YOU_HAVE_ADDED_RECIPIENT_MSG.getEnumValue().toLowerCase(),
					"'You’ve added a recipient label' Text is not matching");
			else if(getTexOfElement(DBSappObject.MainHeaderOrSuccessMsgElement()).toLowerCase().equalsIgnoreCase(CommonTestData.YOU_HAVE_ADDED_RECIPIENT_MSG2.getEnumValue())) {
				Asserts.assertEquals(getTexOfElement(DBSappObject.MainHeaderOrSuccessMsgElement()).toLowerCase(),
						CommonTestData.YOU_HAVE_ADDED_RECIPIENT_MSG2.getEnumValue().toLowerCase(),
						"You've added a recipient' Text is not matching");
			}
			verifyReferenceFieldAndItsValue(CommonTestData.REFERENCE_NUMBER.getEnumValue());
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("TESTCASE_EXCEPTION", " Failed to Execute Add payee Remittance  ",e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("TESTCASE_EXCEPTION", " Failed to Execute Add payee Remittance  ",e);
		}
	}

	@Step("Verify topup Paylah Case and logout topup Paylah.")
	public void TopupPaylah() throws Exception {
		try {
			ClickOnPayAndTransferBtn();
			EnterPasscodeAndDone();
			topUpVerifyClick(CommonTestData.TOPUP_LABEL.getEnumValue());
			payLahVerifyClick(CommonTestData.PAYLAH_LABEL.getEnumValue());
			sendCurrencyInTextField(CommonTestData.AMOUNT_PAYLAH.getEnumValue());
			ClickOnNextButton();
			verifyReviewTopUpLabel(CommonTestData.TOPUP_REVIEW_LABEL.getEnumValue());
			topUpNowVerifyClick(CommonTestData.TOPUP_NOW_BUTTOM_LABEL.getEnumValue());
			logOutTopUpVerifyClick(CommonTestData.LOGOUT_PAYLAH.getEnumValue());
		}  catch (HandleException e) {	
			obj_handleexception.throwHandleException("TESTCASE_EXCEPTION", " Failed to Execute TopUp Paylah  ",e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("TESTCASE_EXCEPTION", " Failed to Execute TopUp Paylah  ",e);
		}
	}

	@Step("Enter the text in search and select the corresponding value in the dropdown")
	public void sendDataInCommonSearchBoxAndSelectFromDropDown(String searchBoxData, String valueSelectedFromList,
			String ExpecetedText, MobileElement PageHeader) throws Exception {
		try {
			clickOnElementOnEnable(DBSappObject.searchIcon());
			if (isElementEnable(DBSappObject.searchBox()))
				enterTextInTextbox(DBSappObject.searchBox(), searchBoxData);

			TakeScreenshot(DBSappObject.searchTextElement().get(0));
			List<MobileElement> Elementlist = DBSappObject.searchTextElement();
			List<MobileElement> ElementlistClickable = DBSappObject.searchClickableElement();
			int l = Elementlist.size();
			int index = 0;
			String elementFromList = null;
			for (int i = 0; i < l; i++) {
				elementFromList = Elementlist.get(i).getText();
				if (elementFromList.equalsIgnoreCase(valueSelectedFromList)) {
					index++;
					clickOnElement(ElementlistClickable.get(i));
					break;
				}
			}
			Asserts.assertTrue(index > 0, "No element found in the list of corresponding value");
			EnterPasscodeAndDone();
			verifyPageHeader(ExpecetedText, PageHeader);
			
		} catch (Exception e) {


			e.printStackTrace();

			throw e;
		}
	}

	@Step("Verifying page header")
	public void verifyPageHeader(String expectedText, MobileElement ele) throws Exception {
		try {
			if (ele != null)
				TakeScreenshot(ele);
				Asserts.assertEquals(getTexOfElement(ele).toLowerCase(), expectedText.toLowerCase(),
						"'Header Title' is not Matching");
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("VERIFYHEADER_EXCEPTION", " Failed to verify page header ",e);
		}
		catch (Exception e) {		
			obj_handleexception.throwException("VERIFYHEADER_EXCEPTION", " Failed to verify page header ",e);
		}
	}

	public void VerifyButtonLabelAndClick(MobileElement Button, String expectecText) throws Exception {
		try {
			String actualText = getTexOfElement(Button);
			TakeScreenshot(Button);
			Asserts.assertEquals(actualText, expectecText, "button Not exist");
			if (actualText.equalsIgnoreCase(expectecText))
				clickOnElement(Button);

		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On " + Button + "Button" ,e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click On " + Button + "Button",e);
		}
	}

	@Step("Click on 'To Other Banks Limit' Button and then Verifying page header 'Transfer to Other Banks'")
	public void ClickOnToOtherBankLimit() throws Exception {
		try {
			selectAccountTypeInLocalFundTransfer(CommonTestData.TO_OTHERBANK_LABEL.getEnumValue());
			EnterPasscodeAndDone();
			verifyPageHeader(CommonTestData.TRANSFER_TO_OTHERBANK_LABEL.getEnumValue(),
					DBSappObject.PageHeaderList2().get(0));
			//TakeScreenshot(DBSappObject.PageHeaderList2().get(0));
			

		} catch (Exception e) {


			e.printStackTrace();

			throw e;
		}
	}

	@Step("Click on 'Account type' From List under Local fund Limit page'")
	public void selectAccountTypeInLocalFundTransfer(String AccountToBeSelected) throws Exception {
		try {

			wait.waitForElementVisibility(DBSappObject.localTransferLimitAccountList().get(0));
			List<MobileElement> Elementlist = DBSappObject.localTransferLimitAccountList();
			int l = Elementlist.size();
			int index = 0;
			String accountFromList = null;
			for (int i = 0; i < l; i++) {
				accountFromList = Elementlist.get(i).getText();
				if (accountFromList.equalsIgnoreCase(AccountToBeSelected)) {
					index++;
					clickOnElement(Elementlist.get(i));
					break;
				}
			}

			Asserts.assertTrue(index > 0, "No " + AccountToBeSelected + " found in the list of corresponding value");

		} catch (Exception e) {


			e.printStackTrace();

			throw e;
		}
	}

	@Step("click On 'set current Limit' and verify page header. ")
	public void verifyClickSetCurrentLimit() throws Exception {
		try {
			clickOnElementOnEnable(DBSappObject.currentLimitTextButton());
			TakeScreenshot(DBSappObject.PageHeaderList2().get(4));
			verifyPageHeader(CommonTestData.SET_DAILY_LIMIT_TITLE.getEnumValue(),
					DBSappObject.PageHeaderList2().get(4));
		} catch (Exception e) {


			e.printStackTrace();

			throw e;
		}
	}

	public String handlingSetCurrentLimit(String AmountToBeselected) throws Exception {
		try {
			TakeScreenshot(DBSappObject.currentLimitTextButton());
			String currentText = getTexOfElement(DBSappObject.currentLimitTextButton());
			String[] arrOfStr = currentText.split(" ");
			String flag = null;
			verifyClickSetCurrentLimit();
			String selectedValue = null;
			if (arrOfStr[1].equalsIgnoreCase(CommonTestData.SELECTED_LIMIT_0.getEnumValue())) {
				selectedValue = selectAmountFromSetCurrentLimitList(AmountToBeselected);
			} else {
				gestUtils.scrollDOWNtoObject("text", CommonTestData.SELECTED_LIMIT_0.getEnumValue(),
						DBSappObject.Amount_0_inLocaltransferlimitList());
				flag = selectAmountFromSetCurrentLimitList(CommonTestData.SELECTED_LIMIT_0.getEnumValue());
				currentText = getTexOfElement(DBSappObject.currentLimitTextButton());
				verifyClickSetCurrentLimit();
				arrOfStr = currentText.split(" ");
				if (arrOfStr[1].equalsIgnoreCase(CommonTestData.SELECTED_LIMIT_0.getEnumValue()))
					selectedValue = selectAmountFromSetCurrentLimitList(AmountToBeselected);
			}
			return selectedValue;
		} catch (Exception e) {


			e.printStackTrace();

			throw e;
		}
	}

	@Step("Select amount from the List of 'set current Limit' ")
	public String selectAmountFromSetCurrentLimitList(String amount) throws Exception {
		try {
			wait.waitForElementVisibility(DBSappObject.currentLimitAmountValue().get(3));
			List<MobileElement> Elementlist = DBSappObject.currentLimitAmountValue();
			int l = Elementlist.size();
			int index = 0;
			String elementFromList = null;
			String selectedAmount = null;
			for (int i = 0; i < l; i++) {
				elementFromList = Elementlist.get(i).getText();
				if (elementFromList.equalsIgnoreCase(amount)) {
					selectedAmount = elementFromList;
					index++;
					clickOnElement(Elementlist.get(i));
					break;
				}
			}
			Asserts.assertTrue(index > 0, "No" + amount + "found in the list of corresponding value");
			return selectedAmount;
		} catch (Exception e) {


			e.printStackTrace();

			throw e;
		}
	}

	@Step("Click On 'CHANGE DAILY LIMIT NOW' BUTTON from Review Daily limit page and Verify 'Local Transfer Limit Changed!' Title  ")
	public void verifyClickChangeDailyLimitNowButton() throws Exception {
		try {
			verifyPageHeader(CommonTestData.REVIEW_DAILY_LIMIT_TITLE.getEnumValue(),
					DBSappObject.PageHeaderList2().get(4));
			TakeScreenshot(DBSappObject.changeDailyLimitButton());
			clickOnElementOnEnable(DBSappObject.changeDailyLimitButton());
			EnterPasscodeAndDone();
			Asserts.assertEquals(getTexOfElement(DBSappObject.successTitleLabel()),
					CommonTestData.LOCAL_TRANSFER_CAHNGE_TITLE.getEnumValue(), "'Header Title' is not Matching");
		} catch (Exception e) {


			e.printStackTrace();

			throw e;
		}
	}

	@Step("Verifying and click 'BACK TO MORE' BUTTON ")
	public void verifyClickBackToMoreButton() throws Exception {
		try {
			TakeScreenshot(DBSappObject.successTitleLabel());
			clickOnElementOnEnable(DBSappObject.backToMoreButton());
		} catch (Exception e) {


			e.printStackTrace();

			throw e;
		}
	}

	@Step("Verify Amount display After Limit get Changed")
	public void verifyDisplayAmountLocalTransferLimitChange(String expectedText) throws Exception {
		try {
			TakeScreenshot(DBSappObject.currentLimitTextButton());
			String currentText = getTexOfElement(DBSappObject.currentLimitTextButton());
			String[] arrOfStr = currentText.split(" ");
			String acutalText = arrOfStr[1];
			Asserts.assertEquals(acutalText, expectedText, "'Amount display' After Limit get Changed is Wrong");
		} catch (Exception e) {


			e.printStackTrace();

			throw e;
		}
	}

	@Step("Change local fund transfer limit verification")
	public void ChangeLocalFundsTransferLimit() throws Exception {
		try {
			ClickOnMoreButton();
			EnterPasscodeAndDone();
			sendDataInCommonSearchBoxAndSelectFromDropDown(CommonTestData.LOCAL_TRANSFER_LIMIT_LABEL.getEnumValue(),
					CommonTestData.LOCAL_TRANSFER_LIMIT_LABEL.getEnumValue(),
					CommonTestData.LOCAL_TRANSFER_LIMIT_LABEL.getEnumValue(), DBSappObject.PageHeader2());
			ClickOnToOtherBankLimit();
			String amountSlected = handlingSetCurrentLimit(CommonTestData.SELECTED_LIMIT_50000.getEnumValue());
			ClickOnNextButton();
			verifyClickChangeDailyLimitNowButton();
			verifyClickBackToMoreButton();
			sendDataInCommonSearchBoxAndSelectFromDropDown(CommonTestData.LOCAL_TRANSFER_LIMIT_LABEL.getEnumValue(),
					CommonTestData.LOCAL_TRANSFER_LIMIT_LABEL.getEnumValue(),
					CommonTestData.LOCAL_TRANSFER_LIMIT_LABEL.getEnumValue(), DBSappObject.PageHeader2());
			ClickOnToOtherBankLimit();
			verifyDisplayAmountLocalTransferLimitChange(amountSlected);
		} catch (Exception e) {


			e.printStackTrace();

			throw e;
		}
	}

	@Step("Click on 'All Tab' section")
	public void clickAndVerifyOnAllTab() throws Exception {
		try {
			VerifyButtonLabelAndClick(DBSappObject.AllTab(), CommonTestData.ALL_SECTION.getEnumValue());
			TakeScreenshot(DBSappObject.AllTab());
		} catch (Exception e) {


			e.printStackTrace();

			throw e;
		}

	}

	@Step("Click on Account after selecting 'Local Recipients' and verify pageHeader")
	public void clickingOnAccountTypeInLocalRecipient_1(String valueSelectedFromList) throws Exception {
		try {
			
			int o = 0;
			for (int i = 0; i < DBSappObject.AllTabOptionsList().size(); i++) {
				String tabText = DBSappObject.AllTabOptionsList().get(i).getText();
				o++;
				if (tabText.contains(CommonTestData.LOCAL_RECIPIENT_FROMLIST.getEnumValue())) {
					break;
				}
			}

			gestUtils.DragAndDropElementToElement(DBSappObject.AllTabOptionsList().get(o), DBSappObject.AllTab());
			if(DBSappObject.SubTitleTextList().size()>0)
			{
			TakeScreenshot(DBSappObject.SubTitleTextList().get(0));
			List<MobileElement> Elementlist = DBSappObject.SubTitleTextList();
			List<MobileElement> ElementlistClickable = DBSappObject.ListElementToClickable();
			int l = Elementlist.size();
			int index = 0;
			String LocalRecipientList = null;
			for (int i = 0; i < l; i++) {
				LocalRecipientList = Elementlist.get(i).getText();
				if (LocalRecipientList.equalsIgnoreCase(valueSelectedFromList)) {
					index++;
					clickOnElement(ElementlistClickable.get(i));
					break;
				}
			}
			
			Asserts.assertTrue(index > 0, "Local Recipient " +valueSelectedFromList+" not found in the list to initiate the fund transfer");
			}
			else
			{
				Asserts.assertFail("No  receipient Found in the Local recipient list");
			}
			Thread.sleep(2000);
			String xpath = "//android.widget.TextView[@text='Primary source of fund']";
			List<RemoteWebElement> list = driver.findElements(By.xpath(xpath));
			if (list.size() > 0) {
				TakeScreenshot(DBSappObject.PrimarySourceOfFund());
				clickOnElement(DBSappObject.Alert_OKButton());
			}
			verifyPageHeader(CommonTestData.TRANSFER_DBS_POSB.getEnumValue(), DBSappObject.PageHeader());
			TakeScreenshot(DBSappObject.PageHeader());
		}catch (HandleException e) {	
				obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to select account from local recipient and verify header  ",e);
			}
			catch (Exception e) {		
				obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to select account from local recipient and verify header  ",e);
			}

	}

	@Step("Click on 'Select Fund Source' and Select Account")
	public void selectFundSourceAndSelectAccount(String SelectedAccountName, String SelectedAccountNumber)
			throws Exception {
		try {
			String xpath = "//android.widget.TextView[@text='Select Fund Source']";
			List<RemoteWebElement> list = driver.findElements(By.xpath(xpath));
			if (list.size() > 0) {
				VerifyButtonLabelAndClick(DBSappObject.selectFundSourceTextButton(),
						CommonTestData.SELECT_SOURCE_FUND.getEnumValue());
				TakeScreenshot(DBSappObject.selectFundSourceListAccountName().get(0));
				List<MobileElement> AccountName = DBSappObject.selectFundSourceListAccountName();
				List<MobileElement> AccountNumber = DBSappObject.selectFundSourceListAccountNumber();
				int l = AccountName.size();
				int index = 0;
				String AccountNameList = null;
				String AccountNumberList = null;
				for (int i = 0; i < l; i++) {
					AccountNameList = AccountName.get(i).getText();
					AccountNumberList = AccountNumber.get(i).getText();
					if (AccountNameList.equalsIgnoreCase(SelectedAccountName)
							&& AccountNumberList.equalsIgnoreCase(SelectedAccountNumber)) {
						index++;
						clickOnElement(AccountName.get(i));
						break;
					}
				}
				Thread.sleep(2000);
				String xpath1 = "//android.widget.TextView[contains(@resource-id,'id/tv_primary_account_bottom_sheet_title')]";
				List<RemoteWebElement> list1 = driver.findElements(By.xpath(xpath1));
				if (list1.size() > 0) {
					androidAlert.AlertHandlingWithButtonMessage(DBSappObject.Alert_OKButton(),
							CommonTestData.PRIMARY_SOURCE_ALERT_TITLE.getEnumValue(),
							DBSappObject.primarysourceAlertTitle());

				}
			}
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to select fund source and account ",e);
		}
		catch (Exception e) {		
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to select fund source and account  ",e);
		}

	}

	@Step("Click on 'DBS CURRENT ACCOUNT' after selecting 'Local Recipients' and verify 'Transfer to DBS/POSB' Title")
	public void selectLocalRecientAndClickingOnDbsCurrentAccount() throws Exception {
		try {
			VerifyButtonLabelAndClick(DBSappObject.AllTabOptionsList().get(2),
					CommonTestData.LOCAL_RECIPIENT_FROMLIST.getEnumValue());
			gestUtils.scrollUPtoObject("text", CommonTestData.DBS_CURRENT_ACCOUNT_TEXT.getEnumValue(),
					DBSappObject.dbsCurrentAccountOption());
			VerifyButtonLabelAndClick(DBSappObject.dbsCurrentAccountOption(),
					CommonTestData.DBS_CURRENT_ACCOUNT_TEXT.getEnumValue());
			verifyPageHeader(CommonTestData.TRANSFER_DBS_POSB.getEnumValue(), DBSappObject.PageHeader());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;

		}
	}

	@Step("Click on 'Select Fund Source' and Select Account")
	public void selectFundSourceAndSelectAccountForCorredor(String SelectedAccountName, String SelectedAccountNumber)
			throws Exception {
		try {
			Thread.sleep(10000);	
			TakeScreenshot(DBSappObject.overseasTransferHeader());
			//wait.waitForElementVisibility(DBSappObject.AmountTextFields().get(0));
			String xpath = "//android.widget.TextView[@text='Select Fund Source']";
			List<RemoteWebElement> list = driver.findElements(By.xpath(xpath));
			if (list.size() > 0) {
				VerifyButtonLabelAndClick(DBSappObject.selectFundSourceTextButton(),
						CommonTestData.SELECT_SOURCE_FUND.getEnumValue());
				TakeScreenshot(DBSappObject.selectFundSourceListAccountName2().get(0));
				List<MobileElement> AccountName = DBSappObject.selectFundSourceListAccountName2();
				// List<MobileElement> AccountNumber =
				// DBSappObject.selectFundSourceListAccountNumber2();
				int l = AccountName.size();
				int index = 0;
				String AccountNameList = null;
				// String AccountNumberList = null;
				for (int i = 0; i < l; i++) {
					AccountNameList = AccountName.get(i).getText();
					// AccountNumberList = AccountNumber.get(i).getText();
					if (AccountNameList.equalsIgnoreCase(SelectedAccountName)) {// &&
																				// AccountNumberList.equalsIgnoreCase(SelectedAccountNumber)
						index++;
						clickOnElement(AccountName.get(i));
						break;
					}
				}
				String xpath1 = "//android.widget.TextView[contains(@resource-id,'id/tv_primary_account_bottom_sheet_title')]";
				List<RemoteWebElement> list1 = driver.findElements(By.xpath(xpath1));
				if (list1.size() > 0) {
					androidAlert.AlertHandlingWithButtonMessage(DBSappObject.OKButton(),
							CommonTestData.PRIMARY_SOURCE_ALERT_TITLE.getEnumValue(),
							DBSappObject.primarysourceAlertTitle());
					verifyPageHeader(CommonTestData.TRANSFER_DBS_POSB.getEnumValue(), DBSappObject.PageHeader());
				}
			}
			String xpath1 = "//android.widget.TextView[contains(@resource-id,'id/tv_primary_account_bottom_sheet_title')]";
			List<RemoteWebElement> list1 = driver.findElements(By.xpath(xpath1));
			if (list1.size() > 0) {
				androidAlert.AlertHandlingWithButtonMessage(DBSappObject.OKButton(),CommonTestData.PRIMARY_SOURCE_ALERT_TITLE.getEnumValue(),
					DBSappObject.primarysourceAlertTitle());
			verifyPageHeader(CommonTestData.OVERSEA_HEADER.getEnumValue(), DBSappObject.overseasTransferHeader());
			}
			

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	@Step("Verify 'SGD Currency Field' and Enter Amount '11'")
	public void enterAmountAndVerifySgdCurrency(String Amount) throws Exception {
		try {
			Asserts.assertEquals(getTexOfElement(DBSappObject.sgdFieldText()),
					CommonTestData.SGD_CURRENCY_LABEL.getEnumValue(), "'Currency' is not Matching");
			enterTextInTextbox(DBSappObject.amountTransferTextBox(), Amount);
			TakeScreenshot(DBSappObject.amountTransferTextBox());
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to verify SGD Currency field and enter amount",e);
		}
		catch (Exception e) {		
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to verify SGD Currency field and enter amount  ",e);
		}

	}

	@Step(" Verifying page header 'Review Transfer' And Click on 'TRANSFER NOW' Button")
	public void verifyReviewTransferAndClickTransferNowButton() throws Exception {
		try {
			wait.waitForElementVisibility(DBSappObject.PageHeader());
			verifyPageHeader(CommonTestData.REVIEW_TRANSFER_LABEL.getEnumValue(), DBSappObject.PageHeader());
			TakeScreenshot(DBSappObject.TransferNowBtn());
			VerifyButtonLabelAndClick(DBSappObject.TransferNowBtn(), CommonTestData.TRANSFER_NOW_BUTTON.getEnumValue());
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to verify review transfer and click on Transfer now button ",e);
		}
		catch (Exception e) {		
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", "  Failed to verify review transfer and click on Transfer now button   ",e);
		}

	}

	@Step(" Verifying page header 'Transferred' And Generated Reference Number")
	public void verifyTransferredAndReferenceNumberField() throws Exception {
		try {
			Thread.sleep(5000);
			verifyPageHeader(CommonTestData.TRANSFER_TITLE.getEnumValue(), DBSappObject.PageHeaderList().get(0));
			clickOnElement(DBSappObject.expandButton2());
			gestUtils.scrollUPtoObject("text", "Reference No.", DBSappObject.ReferenceNumberText());

			TakeScreenshot( DBSappObject.ReferenceNumberText());
			Asserts.assertEquals(getTexOfElement(DBSappObject.ReferenceNumberText()), CommonTestData.REFERENCE_NUMBER.getEnumValue(),
					"'Reference no Field' is not found");
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to verify page header and generated reference number ",e);
		}
		catch (Exception e) {		
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to verify page header and generated reference number   ",e);
		}
	}

	@Step(" Verifying page header 'Transferred' And Click on 'Logout' Button")
	public void verifyTransferredTitleAndClickOnLogout() throws Exception {
		try {
			verifyPageHeader(CommonTestData.TRANSFER_TITLE.getEnumValue(), DBSappObject.PageHeader());
			logOutTopUpVerifyClick("Log Out");
			VerifyButtonLabelAndClick(DBSappObject.TransferNowBtn(), CommonTestData.TRANSFER_NOW_BUTTON.getEnumValue());
			Asserts.assertEquals(getTexOfElement(DBSappObject.postLogoutAlertMessage()),
					CommonTestData.RATE_MESSAGE.getEnumValue(), "'Tap on the stars to rate' Text is not found");
		} catch (Exception e) {


			e.printStackTrace();

			throw e;
		}

	}

	@Step("Verifies FundTransfer Other DBS/POSB")
	public void FundTransferOtherAccountSameBank() throws Exception {
		try {
			ClickOnPayAndTransferBtn();
			EnterPasscodeAndDone();
			clickAndVerifyOnAllTab();
			selectOptionFromAllTabSection(CommonTestData.LOCAL_RECIPIENT_FROMLIST.getEnumValue());
			clickingOnAccountTypeInLocalRecipient_1(
					CommonTestData.LOCAL_RECIPIENT_LIST_SELECTED_ACCOUNTNAME.getEnumValue());
			selectFundSourceAndSelectAccount(CommonTestData.SOURCE_ACCOUNT_NAME.getEnumValue(),
					CommonTestData.SOURCE_ACCOUNT_NUMBER.getEnumValue());
			enterAmountAndVerifySgdCurrency("11");
			ClickOnNextButton();
			verifyReviewTransferAndClickTransferNowButton();
			verifyTransferredAndReferenceNumberField();
			clickOnLogoutAndVerifyInFundTransfer();
		}catch (HandleException e) {	
			obj_handleexception.throwHandleException("TESTCASE_EXCEPTION", " Failed to verify fund transfer other DBS_POSB  ",e);
		}
		catch (Exception e) {		
			obj_handleexception.throwException("TESTCASE_EXCEPTION", " Failed to verify fund transfer other DBS_POSB  ",e);
		}
	}

	@Step("Verify Fund Transfer For Own Account.")
	public void VerifyFundTransfer_OwnAccount() throws Exception {
		try {
			ClickOnPayAndTransferBtn();
			EnterPasscodeAndDone();
			SelectAllTAB();
			SelectOWNAccountAndAnyAccountOption(CommonTestData.FUNDTRANSFER_TO_OWN_ACCOUNT_NUMBER.getEnumValue());
			String ExpectedFromAccountName = CommonTestData.FUNDTRANSFER_FROM_OWN_ACCOUNT_NAME.getEnumValue();
			SelectFundSourceAccount(ExpectedFromAccountName);
			EnterAmount(DBSappObject.AmountEditableField(), CommonTestData.AMOUNTTO_TRANSFERFUND.getEnumValue());
			ClickOnNextButton();
			Asserts.assertEquals(getTexOfElement(DBSappObject.PageHeader()),
					CommonTestData.REVIEW_TRANSFER.getEnumValue(),
					CommonTestData.REVIEW_TRANSFER.getEnumValue() + " Text is not matching");

			ClickOnTransferNowBtnAndVerifiesTransferSubmittedMsg(CommonTestData.TRANSFERRED.getEnumValue(),
					DBSappObject.ImageForPaymentSuccess(), DBSappObject.PageHeader());
			String ExpectedToAccountNumber = CommonTestData.FUNDTRANSFER_TO_OWN_ACCOUNT_NUMBER.getEnumValue()
					+ "	SGD";
			String ExpectedToAccountName = CommonTestData.FUNDTRANSFER_TO_OWN_ACCOUNT_NAME.getEnumValue();
			String ExpectedFromAccountNumber = CommonTestData.FUNDTRANSFER_FROM_OWN_ACCOUNT_NUMBER.getEnumValue()
					+ "	SGD";
			VerifyAccountDetailsAfterFundTransferToOwnAccount(ExpectedToAccountNumber, ExpectedToAccountName,
					ExpectedFromAccountNumber, ExpectedFromAccountName);
		}catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to verify Fund transfer to own account  ",e);
		}
		catch (Exception e) {		
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to verify Fund transfer to own account ",e);
		}
	}

	@Step("Verify Account Details After Fund Transfer To Own Account.")
	public void VerifyAccountDetailsAfterFundTransferToOwnAccount(String ExpectedToAccountNumber,
			String ExpectedToAccountName, String ExpectedFromAccountNumber, String ExpectedFromAccountName)
			throws Exception {
		try {
			Asserts.assertTrue(DBSappObject.LOGOUTButton().isDisplayed(), "Log Out Button not matching.");
			Asserts.assertTrue(DBSappObject.BACKToHOME().isDisplayed(), "BACK To HOME Button not found.");
			Asserts.assertTrue(DBSappObject.MakeAnotherTransferBtn().isDisplayed(),
					"MAKE ANOTHER TRANSFER Button not found.");

			String[] ExpTitleList = new String[] { "From", "To", "When", "Source Account Balance",
					"Destination Account Balance", "Reference No." };

			for (int i = 0; i < DBSappObject.FundTransferDetailslabel1List().size(); i++) {
				Asserts.assertEquals(getTexOfElement(DBSappObject.FundTransferDetailslabel1List().get(i)),
						ExpTitleList[i], ExpTitleList[i] + "Titles is not matching after adding payee");
			}

			Asserts.assertEquals(getTexOfElement(DBSappObject.FundTransferDetailslabel2List().get(0)),
					ExpectedFromAccountName,
					ExpectedFromAccountName + " is not matching after Fund Transfer to Own Account");

			Asserts.assertEquals(getTexOfElement(DBSappObject.FundTransferDetailslabel2List().get(1)),
					ExpectedToAccountName,
					ExpectedToAccountName + " is not matching after Fund Transfer to Own Account");

			Asserts.assertEquals(getTexOfElement(DBSappObject.FundTransferDetailslabel3List().get(0)),
					ExpectedFromAccountNumber,
					ExpectedFromAccountNumber + " is not matching after Fund Transfer to Own Account.");

			Asserts.assertEquals(getTexOfElement(DBSappObject.FundTransferDetailslabel3List().get(1)),
					ExpectedToAccountNumber,
					ExpectedToAccountNumber + " is not matching after fund transfer to own account");
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to verify account details after fund transfer  ",e);
		}
		catch (Exception e) {		
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to verify account details after fund transfer  ",e);
		}
	}

	@Step("Select All TAB.")
	public void SelectAllTAB() throws Exception {
		try {
			TakeScreenshot(DBSappObject.AllTab());
			clickOnElement(DBSappObject.AllTab());
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to select All Tab  ",e);
		}
		catch (Exception e) {		
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to select All Tab   ",e);
		}
	}


	@Step("Select 'Your DBS/POSB Accounts' and then verify 'Transfer to Your Account' Page header after selecting any own account option.")
	public void SelectOWNAccountAndAnyAccountOption(String valueSelectedFromList) throws Exception {
		try {
			int o = 0;
			for (int i = 0; i < DBSappObject.AllTabOptionsList().size(); i++) {
				String tabText = DBSappObject.AllTabOptionsList().get(i).getText();
				o++;
				if (tabText.contains(CommonTestData.YOUR_DBSPOSB_ACCOUNTS.getEnumValue())) {
					clickOnElement(DBSappObject.AllTabOptionsList().get(i));
					break;
				}
			}
			gestUtils.DragAndDropElementToElement(DBSappObject.AllTabOptionsList().get(o), DBSappObject.AllTab());
			if(DBSappObject.SubTitleTextList().size()>0) {
			TakeScreenshot(DBSappObject.SubTitleTextList().get(0));
			List<MobileElement> Elementlist = DBSappObject.SubTitleTextList();
			List<MobileElement> ElementlistClickable = DBSappObject.ListElementToClickable();
			int l = Elementlist.size();
			int index = 0;
			String ToOwnAccountList = null;
			for (int i = 0; i < l; i++) {
				ToOwnAccountList = Elementlist.get(i).getText();
				if (ToOwnAccountList.equalsIgnoreCase(valueSelectedFromList)) {
					index++;
					clickOnElement(ElementlistClickable.get(i));
					break;
				}
			}
			Asserts.assertTrue(index > 0, "No element found in the list of corresponding value");

			TakeScreenshot(DBSappObject.PageHeader());
			verifyPageHeader(CommonTestData.TRANSFER_TO_YOUR_ACCOUNT.getEnumValue(), DBSappObject.PageHeader());
			}else
			{
				Asserts.assertFail("No element found in the list as list size is 0");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Step("Select Any Fund Source Account After clicking on add sign for select fund source.")
	public void SelectFundSourceAccount(String fromOwnAccount) throws Exception {
		try {
			String xpath = "//android.widget.TextView[@text='Select Fund Source']";
			List<RemoteWebElement> list = driver.findElements(By.xpath(xpath));
			if (list.size() > 0) {
				gestUtils.scrollDOWNtoObject("text", "Select Fund Source", DBSappObject.SelectFundSourcePage());
				TakeScreenshot(DBSappObject.SelectFundSourcePage());
				clickOnElement(DBSappObject.SelectFundSourcePage());
				TakeScreenshot(DBSappObject.SelectLocalRecipientToAccount().get(0));

				for (int i = 0; i < DBSappObject.SelectLocalRecipientToAccount().size(); i++) {
					String actualfromOwnAccount = DBSappObject.SelectLocalRecipientToAccount().get(i).getText();
					if (actualfromOwnAccount.contains(fromOwnAccount)) {
						clickOnElement(DBSappObject.SelectLocalRecipientToAccount().get(i));
						break;
					}
				}

				String xpath1 = "//android.widget.TextView[@text='Primary source of fund']";
				List<RemoteWebElement> list1 = driver.findElements(By.xpath(xpath1));
				if (list1.size() > 0) {
					TakeScreenshot(DBSappObject.PrimarySourceOfFund());
					if (isElementVisible(DBSappObject.PrimarySourceOfFund()))
						clickOnElement(DBSappObject.OKButton());
				}
			}
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to select any fund source account  ",e);
		}
		catch (Exception e) {		
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", "Failed to select any fund source account  ",e);
		}
	}

	@Step("Enter Amount In Editable field to transfer fund.")
	public void EnterAmount(MobileElement editField, String textToEnter) throws Exception {
		try {
			TakeScreenshot(editField);
			clickOnElement(editField);
			enterTextInTextbox(editField, textToEnter);
			backButton();
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Enter Amount ",e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Enter Amount ",e);
		}
	}

	@Step("Verify Fund Transfer For Other Bank Non Fast transfer when future date selected.")
	public void FundsTransfer_OtherBank_NonFASTFuture(String appname) throws Exception {
		try {
			ClickOnPayAndTransferBtn();
			EnterPasscodeAndDone();
			SelectAllTAB();
			String ExpectedToBankNameWithAccountNo = CommonTestData.FUNDTRANSFER_NONFAST_TO_ACCOUNTNUMBER_WITHBANK
					.getEnumValue();
			clickingOnAccountTypeInLocalRecipient(ExpectedToBankNameWithAccountNo);

			DisableToTransferViaFastToggle();

			TakeScreenshot(DBSappObject.EditFields().get(0));
			clickOnElement(DBSappObject.EditFields().get(0));
			enterTextInTextbox(DBSappObject.EditFields().get(0), "Non Fast");

			String ExpectedFromBankName = CommonTestData.FUNDTRANSFER_NONFAST_FROM_ACCOUNT_NAME.getEnumValue();
			SelectFundSourceAccount(ExpectedFromBankName);

			SelectFutureDateThroughCalendar();

			EnterAmount(DBSappObject.AmountEditableField(), CommonTestData.AMOUNTTO_TRANSFERFUND.getEnumValue());
			ClickOnNextButton();
			VerifyReviewTransferPageAndNonFastServiceInReview();
			ClickOnTransferNowBtnAndVerifiesTransferSubmittedMsg(CommonTestData.TRANSFER_SUBMITTED_MSG.getEnumValue(),
					DBSappObject.ImageForPaymentSuccess(), DBSappObject.TransferSuccessMsgElement());

			String ExpectedToRecipientName = CommonTestData.FUNDTRANSFER_NONFAST_TO_RECIPIENT_NAME.getEnumValue();
			VerifyVisibiltyOfSomeElements(ExpectedFromBankName, ExpectedToBankNameWithAccountNo,
					ExpectedToRecipientName);

			ClickOnExpandbtnAndBackBtn();
			ClickOnMoreButton();
			EnterPasscodeAndDone();
			ClickOnTransactionHistory(appname);
			SelectTimeAndAccountTypeForStatement(appname, ExpectedFromBankName);
			ClickOnShowButtonAndVerifyHeader(ExpectedFromBankName);
			ValadateTransactionHistoryListInThreeMonth();
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("TESTCASE_EXCEPTION", " Failed to verify fund transfer other bank NON-FAST FUTURE  ",e);
		}
		catch (Exception e) {		
			obj_handleexception.throwException("TESTCASE_EXCEPTION", "Failed to verify fund transfer other bank NON-FAST FUTURE   ",e);
		}

	}

	@Step("Verify Fund Transfer For Other Bank Fast transfer when future date selected.")
	public void FundsTransfer_OtherBank_FASTFuture(String appname) throws Exception {
		try {
			ClickOnPayAndTransferBtn();
			EnterPasscodeAndDone();
			SelectAllTAB();
			String ExpectedToBankNameWithAccountNo = CommonTestData.FUNDTRANSFER_NONFAST_TO_ACCOUNTNUMBER_WITHBANK
					.getEnumValue();
			clickingOnAccountTypeInLocalRecipient(ExpectedToBankNameWithAccountNo);

			TakeScreenshot(DBSappObject.EditFields().get(0));
			clickOnElement(DBSappObject.EditFields().get(0));
			enterTextInTextbox(DBSappObject.EditFields().get(0), "Fast");

			String ExpectedFromBankName = CommonTestData.FUNDTRANSFER_NONFAST_FROM_ACCOUNT_NAME.getEnumValue();
			SelectFundSourceAccount(ExpectedFromBankName);

			SelectFutureDateThroughCalendar();
			EnterAmount(DBSappObject.AmountEditableField(), CommonTestData.AMOUNTTO_TRANSFERFUND.getEnumValue());
			ClickOnNextButton();
			VerifyReviewTransferPageAndFastServiceInReview();
			ClickOnTransferNowBtnAndVerifiesTransferSubmittedMsg(CommonTestData.TRANSFER_SUBMITTED_MSG.getEnumValue(),
					DBSappObject.ImageForPaymentSuccess(), DBSappObject.TransferSuccessMsgElement());

//			String ExpectedFromAccountNumber = CommonTestData.FUNDTRANSFER_NONFAST_FROM_ACCOUNT_NUMBER.getEnumValue()
//					+ "	SGD";
			String ExpectedToRecipientName = CommonTestData.FUNDTRANSFER_NONFAST_TO_RECIPIENT_NAME.getEnumValue();
			VerifyVisibiltyOfSomeElements(ExpectedFromBankName, ExpectedToBankNameWithAccountNo,
					ExpectedToRecipientName);

			ClickOnExpandbtnAndBackBtn();
			ClickOnMoreButton();
			EnterPasscodeAndDone();
			ClickOnTransactionHistory(appname);
			SelectTimeAndAccountTypeForStatement(appname, ExpectedFromBankName);
			ClickOnShowButtonAndVerifyHeader(ExpectedFromBankName);
			ValadateTransactionHistoryListInThreeMonth();
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("TESTCASE_EXCEPTION", " Failed to verify fund transfer other bank FAST FUTURE  ",e);
		}
		catch (Exception e) {		
			obj_handleexception.throwException("TESTCASE_EXCEPTION", "Failed to verify fund transfer other bank FAST FUTURE   ",e);
		}

	}

	@Step("Verify Fund Transfer For Other Bank Non Fast transfer.")
	public void FundsTransfer_OtherBank_NonFAST(String appname) throws Exception {
		try {
			ClickOnPayAndTransferBtn();
			EnterPasscodeAndDone();
			SelectAllTAB();
			String ExpectedToBankNameWithAccountNo = CommonTestData.FUNDTRANSFER_NONFAST_TO_ACCOUNTNUMBER_WITHBANK
					.getEnumValue();
			clickingOnAccountTypeInLocalRecipient(ExpectedToBankNameWithAccountNo);
			DisableToTransferViaFastToggle();

			TakeScreenshot(DBSappObject.EditFields().get(0));
			clickOnElement(DBSappObject.EditFields().get(0));
			enterTextInTextbox(DBSappObject.EditFields().get(0), "Non Fast");

			String ExpectedFromBankName = CommonTestData.FUNDTRANSFER_NONFAST_FROM_ACCOUNT_NAME.getEnumValue();
			SelectFundSourceAccount(ExpectedFromBankName);

			String ExpectedSelectedDate = getTexOfElement(DBSappObject.TransferDateTextElement());
			Asserts.assertEquals("Immediate", ExpectedSelectedDate, "Selected Date is not Matching");
			EnterAmount(DBSappObject.AmountEditableField(), CommonTestData.AMOUNTTO_TRANSFERFUND.getEnumValue());
			ClickOnNextButton();
			VerifyReviewTransferPageAndNonFastServiceInReview();
			ClickOnTransferNowBtnAndVerifiesTransferSubmittedMsg(CommonTestData.TRANSFER_SUBMITTED_MSG.getEnumValue(),
					DBSappObject.ImageForPaymentSuccess(), DBSappObject.TransferSuccessMsgElement());

			String ExpectedToRecipientName = CommonTestData.FUNDTRANSFER_NONFAST_TO_RECIPIENT_NAME.getEnumValue();
			VerifyVisibiltyOfSomeElements(ExpectedFromBankName, ExpectedToBankNameWithAccountNo,
					ExpectedToRecipientName);

			ClickOnExpandbtnAndBackBtn();
			ClickOnMoreButton();
			EnterPasscodeAndDone();
			ClickOnTransactionHistory(appname);
			SelectTimeAndAccountTypeForStatement(appname, ExpectedFromBankName);
			ClickOnShowButtonAndVerifyHeader(ExpectedFromBankName);
			ValadateTransactionHistoryListInThreeMonth();
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("TESTCASE_EXCEPTION", " Failed to verify fund transfer other bank NON-FAST  ",e);
		}
		catch (Exception e) {		
			obj_handleexception.throwException("TESTCASE_EXCEPTION", "Failed to verify fund transfer other bank NON-FAST   ",e);
		}
	}

	@Step("Verify Fund Transfer For Other Bank Fast transfer.")
	public void FundsTransfer_OtherBank_FAST(String appname) throws Exception {
		try {
			ClickOnPayAndTransferBtn();
			EnterPasscodeAndDone();
			SelectAllTAB();
			String ExpectedToBankNameWithAccountNo = CommonTestData.FUNDTRANSFER_NONFAST_TO_ACCOUNTNUMBER_WITHBANK
					.getEnumValue();
			clickingOnAccountTypeInLocalRecipient(ExpectedToBankNameWithAccountNo);

			TakeScreenshot(DBSappObject.EditFields().get(0));
			clickOnElement(DBSappObject.EditFields().get(0));
			enterTextInTextbox(DBSappObject.EditFields().get(0), "Fast");

			String ExpectedFromBankName = CommonTestData.FUNDTRANSFER_NONFAST_FROM_ACCOUNT_NAME.getEnumValue();
			SelectFundSourceAccount(ExpectedFromBankName);

			String ExpectedSelectedDate = getTexOfElement(DBSappObject.TransferDateTextElement());
			Asserts.assertEquals("Immediate", ExpectedSelectedDate, "Selected Date is not Matching");
			EnterAmount(DBSappObject.AmountEditableField(), CommonTestData.AMOUNTTO_TRANSFERFUND.getEnumValue());
			ClickOnNextButton();
			VerifyReviewTransferPageAndFastServiceInReview();
			ClickOnTransferNowBtnAndVerifiesTransferSubmittedMsg(CommonTestData.TRANSFER_SUBMITTED_MSG.getEnumValue(),
					DBSappObject.ImageForPaymentSuccess(), DBSappObject.TransferSuccessMsgElement());

//			String ExpectedFromAccountNumber = CommonTestData.FUNDTRANSFER_NONFAST_FROM_ACCOUNT_NUMBER.getEnumValue()
//					+ "	SGD";
			String ExpectedToRecipientName = CommonTestData.FUNDTRANSFER_NONFAST_TO_RECIPIENT_NAME.getEnumValue();
			VerifyVisibiltyOfSomeElements(ExpectedFromBankName, ExpectedToBankNameWithAccountNo,
					ExpectedToRecipientName);

			ClickOnExpandbtnAndBackBtn();
			ClickOnMoreButton();
			EnterPasscodeAndDone();
			ClickOnTransactionHistory(appname);
			SelectTimeAndAccountTypeForStatement(appname, ExpectedFromBankName);
			ClickOnShowButtonAndVerifyHeader(ExpectedFromBankName);
			ValadateTransactionHistoryListInThreeMonth();
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("TESTCASE_EXCEPTION", " Failed to verify fund transfer other bank FAST  ",e);
		}
		catch (Exception e) {		
			obj_handleexception.throwException("TESTCASE_EXCEPTION", "Failed to verify fund transfer other bank FAST   ",e);
		}
	}

	@Step("Click On 'Expandable btn' And scroll Down to 'Reference No.' text then Click On Back Button.")
	public void ClickOnExpandbtnAndBackBtn() throws Exception {
		try {
			clickOnElement(DBSappObject.FooterExpandableBtn());
			gestUtils.scrollUPtoObject("text", "Reference No.", DBSappObject.ReferenceNumberText());

			TakeScreenshot(DBSappObject.ReferenceNumberText());
			clickOnElement(DBSappObject.BackIcon());
			
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to click on expandable button and scroll down to reference# and click back button ",e);
		}
		catch (Exception e) {		
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to click on expandable button and scroll down to reference# and click back button ",e);
		}
	}

	@Step("Verifies the 'Log out', 'Make Another Transfer' Button and 'Transferred Amount Value' after transferring the fund.")
	public void VerifyVisibiltyOfSomeElements(String ExpectedFromBankName, String ExpectedToBankName,
			String ExpectedToRecipientName) throws Exception {
		try {
			Asserts.assertTrue(DBSappObject.LOGOUTButton().isDisplayed(), "Log Out Button not found.");
			gestUtils.scrollUPtoObject("text", "MAKE ANOTHER TRANSFER", DBSappObject.MakeAnotherTransferBtn());
			Asserts.assertTrue(DBSappObject.MakeAnotherTransferBtn().isDisplayed(),
					"Make Another Transfer Button not found.");
			Asserts.assertEquals(getTexOfElement(DBSappObject.SendingAmountElement()),
					CommonTestData.AMOUNTTO_TRANSFERFUND.getEnumValue() + ".00", "Amount is not matching");

			Asserts.assertEquals(getTexOfElement(DBSappObject.AccountNameList().get(0)), ExpectedFromBankName,
					ExpectedFromBankName + " is not matching");

			Asserts.assertEquals(getTexOfElement(DBSappObject.AccountNameList().get(1)), ExpectedToRecipientName,
					ExpectedToRecipientName + " is not matching");

//			Asserts.assertEquals(getTexOfElement(DBSappObject.AccountNumberList().get(0)), ExpectedFromAccountNumber,
//					ExpectedFromAccountNumber + " is not matching");

			Asserts.assertEquals(getTexOfElement(DBSappObject.AccountNumberList().get(1)).trim(), ExpectedToBankName,
					ExpectedToBankName + " is not matching");

		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", "failed to verify logout, make another transfer and transferred ammount value after transfer ",e);
		}
		catch (Exception e) {		
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " failed to verify logout, make another transfer and transferred ammount value after transfer  ",e);
		}
	}

	@Step("Verify 'Review Transfer' Page And 'Non-Fast' Service In Review")
	public void VerifyReviewTransferPageAndNonFastServiceInReview() throws Exception {
		try {
			Asserts.assertEquals(getTexOfElement(DBSappObject.PageHeaderList().get(0)),
					CommonTestData.REVIEW_TRANSFER.getEnumValue(),
					CommonTestData.REVIEW_TRANSFER.getEnumValue() + " Text is not matching");

			gestUtils.scrollUPtoObject("text", "TRANSFER NOW", DBSappObject.TransferNowBtn());
			TakeScreenshot(DBSappObject.NonFastTransactionService());
			Asserts.assertTrue(DBSappObject.NonFastTransactionService().isDisplayed(),
					"Non-Fast Service not available in review.");
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to verify review transfer page and non fast service  ",e);
		}
		catch (Exception e) {		
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to verify review transfer page and non fast service ",e);
		}

	}

	@Step("Verify 'Review Transfer' Page And 'Fast' Service In Review")
	public void VerifyReviewTransferPageAndFastServiceInReview() throws Exception {
		try {
			Asserts.assertEquals(getTexOfElement(DBSappObject.PageHeaderList().get(0)),
					CommonTestData.REVIEW_TRANSFER.getEnumValue(),
					CommonTestData.REVIEW_TRANSFER.getEnumValue() + " Text is not matching");

			gestUtils.scrollUPtoObject("text", "TRANSFER NOW", DBSappObject.TransferNowBtn());
			TakeScreenshot(DBSappObject.FastTransactionService());
			Asserts.assertTrue(DBSappObject.FastTransactionService().isDisplayed(),
					"Fast Service not available in review.");
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to verify review transfer page and  fast service  ",e);
		}
		catch (Exception e) {		
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to verify review transfer page and  fast service ",e);
		}
	}

	@Step("Verify 'Transfer To Other Bank' Page Header and Click on 'TransferViaFast Toggle' to disable fast service.")
	public void DisableToTransferViaFastToggle() throws Exception {
		try {

			verifyPageHeader(CommonTestData.TRANSFER_TO_OTHERBANK_LABEL_LABEL.getEnumValue(),
					DBSappObject.PageHeaderList().get(0));
			gestUtils.scrollUPtoObject(null, null, DBSappObject.TransferViaFastTransferToggle());

			TakeScreenshot(DBSappObject.TransferViaFastTransferToggle());
			clickOnElement(DBSappObject.TransferViaFastTransferToggle());
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", "Failed to verify page header and click on fast toggle to disable it",e);
		}
		catch (Exception e) {		
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to verify page header and click on fast toggle to disable it  ",e);
		}
	}

	@Step("Click on 'Transaction History' Button and then Verifying page header 'Transaction History'")
	public void ClickOnTransactionHistory(String appName) throws Exception {
		try {
			VerifyButtonLabelAndClick(DBSappObject.transactionHistoryLabelAndButton(),
					CommonTestData.TRANSCETION_HISTORY_LABEL.getEnumValue());
			EnterPasscodeAndDone();
			if (appName.contains("DBS")) {
				verifyPageHeader(CommonTestData.TRANSCETION_HISTORY_LABEL.getEnumValue(),
						DBSappObject.TransactionHistoryHeaderForDBS());
				TakeScreenshot(DBSappObject.TransactionHistoryHeaderForDBS());
			} else if (appName.contains("POSB")) {
				verifyPageHeader(CommonTestData.TRANSCETION_HISTORY_LABEL.getEnumValue(),
						DBSappObject.TransactionHistoryHeaderForPOSB());
				TakeScreenshot(DBSappObject.TransactionHistoryHeaderForPOSB());
			} else if (appName.contains("iWEALTH")) {
				verifyPageHeader(CommonTestData.TRANSCETION_HISTORY_LABEL.getEnumValue(),
						DBSappObject.TransactionHistoryHeaderForiWEALTH());
				TakeScreenshot(DBSappObject.TransactionHistoryHeaderForiWEALTH());
			}
		} catch (HandleException e) {	
				obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to click transaction history and verify page header  ",e);
			}
			catch (Exception e) {		
				obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to click transaction history and verify page header  ",e);
			}

	}

	@Step("Select option From All Tab section")
	public void selectOptionFromAllTabSection(String TabValue) throws Exception {
		try {
			TakeScreenshot(DBSappObject.AllTabOptionsList().get(0));
			for (int i = 0; i < DBSappObject.AllTabOptionsList().size(); i++) {
				String tabText = DBSappObject.AllTabOptionsList().get(i).getText();
				if (tabText.contains(TabValue)) {
					clickOnElement(DBSappObject.AllTabOptionsList().get(i));
					break;
				}
			}

		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to select "+TabValue+ " from All Tab section  ",e);
		}
		catch (Exception e) {		
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION"," Failed to select "+TabValue+ " from All Tab section  ",e);
		}
	}

	public void TakeScreenshot(MobileElement Element) throws Exception {
		try {
			wait.waitForElementVisibility(Element);
			com.crestech.listeners.TestListener.saveScreenshotPNG(driver);
		}catch (HandleException e) {
			obj_handleexception.throwHandleException("SCREENSHOT", " Failed to capture the screenshot ",e);
		}
		catch (Exception e) {
			obj_handleexception.throwException("SCREENSHOT", " Failed to capture the screenshot ",e);
		}
	}

	@Step("Select'3 Months Transaction History' And 'From Account' from 'Deposit Account' section")
	public void SelectTimeAndAccountTypeForStatement(String appName, String AccountName) throws Exception {
		try {
			clickOnElement(DBSappObject.threeMonthLabel());
			if (appName.contains("DBS"))
				clickOnElement(DBSappObject.DepositAccountButtonDBS());

			else if (appName.contains("POSB"))
				clickOnElement(DBSappObject.DepositAccountButtonPOSB());

			else if (appName.contains("iWEALTH"))
				clickOnElement(DBSappObject.DepositAccountButtoniWEALTH());

			selectAccountTypeInTransactionHistory(AccountName, appName);

			if (appName.contains("DBS")) {
				verifyPageHeader(CommonTestData.TRANSCETION_HISTORY_LABEL.getEnumValue(),
						DBSappObject.TransactionHistoryHeaderForDBS());
				TakeScreenshot(DBSappObject.TransactionHistoryHeaderForDBS());
			} else if (appName.contains("POSB")) {
				verifyPageHeader(CommonTestData.TRANSCETION_HISTORY_LABEL.getEnumValue(),
						DBSappObject.TransactionHistoryHeaderForPOSB());
				TakeScreenshot(DBSappObject.TransactionHistoryHeaderForPOSB());
			} else if (appName.contains("iWEALTH")) {
				verifyPageHeader(CommonTestData.TRANSCETION_HISTORY_LABEL.getEnumValue(),
						DBSappObject.TransactionHistoryHeaderForiWEALTH());
				TakeScreenshot(DBSappObject.TransactionHistoryHeaderForiWEALTH());
			}
		}catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to select '3 Months Transaction History' And 'From Account' from 'Deposit Account' section ",e);
		}
		catch (Exception e) {		
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION",  " Failed to select '3 Months Transaction History' And 'From Account' from 'Deposit Account' section ",e);
		}
	}

	@Step("Click on 'Account type' From List under Local fund Limit page'")
	public void selectAccountTypeInTransactionHistory(String AccountToBeSelected, String appName) throws Exception {
		try {
			List<MobileElement> Elementlist = null;
			if (appName.contains("DBS")) {
				wait.waitForElementVisibility(DBSappObject.AccountNameListInTransactionHistoryForDBS().get(1));
				Elementlist = DBSappObject.AccountNameListInTransactionHistoryForDBS();
			} else if (appName.contains("POSB")) {
				wait.waitForElementVisibility(DBSappObject.AccountNameListInTransactionHistoryForPOSB().get(1));
				Elementlist = DBSappObject.AccountNameListInTransactionHistoryForPOSB();
			} else if (appName.contains("iWEALTH")) {
				wait.waitForElementVisibility(DBSappObject.AccountNameListInTransactionHistoryForiWEALTH().get(1));
				Elementlist = DBSappObject.AccountNameListInTransactionHistoryForiWEALTH();
			}
			int l = Elementlist.size();
			int index = 0;
			String accountFromList = null;
			for (int i = 1; i <= l; i++) {
				accountFromList = Elementlist.get(i).getText();
				if (accountFromList.contains(AccountToBeSelected)) {
					index++;
					clickOnElement(Elementlist.get(i));
					break;
				}
			}

			Asserts.assertTrue(index > 0, "No " + AccountToBeSelected + " found in the list of corresponding value");

		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to select Account type under local fund limit page  ",e);
		}
		catch (Exception e) {		
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", "Failed to select Account type under local fund limit page  ",e);
		}
	}

	@Step("Back to Home page from Transaction History statement")
	public void BackToHomeFromTransactionHistory(String appName) throws Exception {
		try {
			ClickOnBackButton();
			if (appName.contains("DBS"))
				verifyPageHeader(CommonTestData.TRANSCETION_HISTORY_LABEL.getEnumValue(),
						DBSappObject.TransactionHistoryHeaderForDBS());
			else if (appName.contains("POSB"))
				verifyPageHeader(CommonTestData.TRANSCETION_HISTORY_LABEL.getEnumValue(),
						DBSappObject.TransactionHistoryHeaderForPOSB());
			else if (appName.contains("POSB"))
				verifyPageHeader(CommonTestData.TRANSCETION_HISTORY_LABEL.getEnumValue(),
						DBSappObject.TransactionHistoryHeaderForiWEALTH());
			
			//Leaving On Home Page for next run.
			ClickOnBackButton();
			ClickOnHomeButton();
		} catch (Exception e) {


			e.printStackTrace();

			throw e;
		}
	}
	
	@Step("Click On Home Button.")
	public void ClickOnHomeButton() throws Exception {
		try {
			TakeScreenshot(DBSappObject.homeButton());
			clickOnElement(DBSappObject.homeButton()); 
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On Home Button  ",e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click On Home Button  ",e);
		}
	}
	
	@Step("Click On Back Button.")
	public void ClickOnBackButton() throws Exception {
		try {
			TakeScreenshot(DBSappObject.backButton());
			clickOnElement(DBSappObject.backButton()); 
		} catch (Exception e) {
			e.printStackTrace();
			throw e; 
		}
	}
	
	@Step("Click On Back Button.")
	public void ClickOnBackIcon() throws Exception {
		try {
			TakeScreenshot(DBSappObject.BackIcon());
			clickOnElement(DBSappObject.BackIcon());
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On Back Button  ",e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click On Back Button  ",e);
		}
	}
	

	@Step("Click on 'Show' Button and then Verifying From Account.")
	public void ClickOnShowButtonAndVerifyHeader(String ExpectedAccountName) throws Exception {
		try {
			VerifyButtonLabelAndClick(DBSappObject.showButton(), CommonTestData.SHOW_BUTTON.getEnumValue());
			EnterPasscodeAndDone();
			Asserts.assertEquals(getTexOfElement(DBSappObject.AccountNameToCheckTransactionHistory()),
					ExpectedAccountName, ExpectedAccountName + " is not matching.");
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to click show button and verify amount  ",e);
		}
		catch (Exception e) {		
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to click show button and verify amount",e);
		}

	}

	@Step("Validating From Account Transaction History List.")
	public void ValadateTransactionHistoryListInThreeMonth() throws Exception {
		try {
			List<MobileElement> Elementlist = DBSappObject.dropDowmList();
			int l = Elementlist.size();
			Asserts.assertTrue(l > 0, "No Transaction History is Display");
			com.crestech.listeners.TestListener.saveScreenshotPNG(driver);
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to verify from account transaction history list  ",e);
		}
		catch (Exception e) {		
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to verify from account transaction history list ",e);
		}

	}

	public void transactionHistoryVerify(String appName) throws Exception {
		try {
			ClickOnMoreButton();
			EnterPasscodeAndDone();
			ClickOnTransactionHistory(appName);
			SelectTimeAndAccountTypeForStatement(appName, CommonTestData.ACCOUNT_NAME.getEnumValue());
			ClickOnShowButtonAndVerifyHeader(CommonTestData.STATEMENT_TITLE.getEnumValue());
			ValadateTransactionHistoryListInThreeMonth();
			BackToHomeFromTransactionHistory(appName);
		} catch (Exception e) {


			e.printStackTrace();

			throw e;
		}
	}

	@Step("Select Future Date Through Calendar and verifies the selected 'future date'.")
	public void SelectFutureDateThroughCalendar() throws Exception {
		try {
			clickOnElement(DBSappObject.TransferDateTextElement());
			TakeScreenshot(DBSappObject.OKButton());
//			Calendar calendar = Calendar.getInstance();
//			Date today = calendar.getTime();
//
//			calendar.add(Calendar.DAY_OF_YEAR, 1);
//			Date tomorrow = calendar.getTime();
//
//			DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
//
//			String todayAsString = dateFormat.format(today);
//			String tomorrowAsString = dateFormat.format(tomorrow);
//			String ExpectedDate = tomorrowAsString.replaceAll("-", " ");
//			System.out.println(todayAsString);
//			System.out.println(tomorrowAsString);
//			System.out.println(ExpectedDate);
//			String[] sDate = tomorrowAsString.split("-");
//			System.out.println(sDate[0]);
//			String CalendardateXpath = "//android.view.View[@text='" + sDate[0] + "']";
//         if(sDate[0]<10) sDate[0]+10;
//			MobileElement Calendardate = (MobileElement) driver.findElement(By.xpath(CalendardateXpath));
//			if (Calendardate.isEnabled())
//				clickOnElement(Calendardate);
//
//			
//			String ActualSelectedDate = getTexOfElement(DBSappObject.TransferDateTextElement());
//			System.out.println(ActualSelectedDate);
//			Asserts.assertEquals(ActualSelectedDate, ExpectedDate, "Selected Date is not Matching");

			String CalendardateXpath = "//android.view.View[@text='20']";
			MobileElement Calendardate = (MobileElement) driver.findElement(By.xpath(CalendardateXpath));
			clickOnElement(Calendardate);
			TakeScreenshot(DBSappObject.OKButton());
			clickOnElement(DBSappObject.OKButton());
			String ActualSelectedDate = getTexOfElement(DBSappObject.TransferDateTextElement());
			TakeScreenshot(DBSappObject.TransferDateTextElement());
			Asserts.assertEquals(ActualSelectedDate.split(" ")[0], "20", "Selected Date is not Matching");

		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to select future date and verification  ",e);
		}
		catch (Exception e) {		
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to select future date and verification ",e);
		}
	}

	@Step("Enter amount , duration and purposed for creditLimit Increase")
	public void setAmountDurationPurposeForLimitIncrease(String Amount, String purpos) throws Exception {
		try {
			if (isElementEnable(DBSappObject.amountCreditLimt()))
				enterTextInTextbox(DBSappObject.amountCreditLimt(), Amount);
			TakeScreenshot(DBSappObject.PageHeader());
			clickOnElement(DBSappObject.purposeOption());
			TakeScreenshot(DBSappObject.PurposeList().get(1));
			List<MobileElement> Elementlist = DBSappObject.PurposeList();
			int l = Elementlist.size();
			int index = 0;
			String purposedFromList = null;
			for (int i = 0; i < l; i++) {
				purposedFromList = Elementlist.get(i).getText();
				if (purposedFromList.equalsIgnoreCase(purpos)) {
					index++;
					clickOnElement(Elementlist.get(i));
					break;
				}
			}
			TakeScreenshot(DBSappObject.PageHeader());
			
			//String cureentDate = getTexOfElement(DBSappObject.durationOption());
			clickOnElement(DBSappObject.durationOption());
			TakeScreenshot(DBSappObject.calenderHeaderCreditLimit());
			clickOnElement(DBSappObject.selectDateOctEleven());
			DBSappObject.OKButton().click();
			//clickOnElement(DBSappObject.OKButton());
			TakeScreenshot(DBSappObject.PageHeader());
			clickOnElement(DBSappObject.durationOption());
			TakeScreenshot(DBSappObject.calenderHeaderCreditLimit());
			clickOnElement(DBSappObject.selectDateOctEleven());
			DBSappObject.OKButton().click();
			//clickOnElement(DBSappObject.OKButton());
			TakeScreenshot(DBSappObject.PageHeader());
//			String[] arrOfStr = cureentDate.split(" ");
//			String year = arrOfStr[2];
//			String Month = arrOfStr[1];
//			String date = arrOfStr[0];
//			Asserts.assertEquals(getTexOfElement(DBSappObject.yearHeader()), year, "year is not matching.");
//
//			List<MobileElement> monthlist = DBSappObject.monthList();
//			int m = monthlist.size();
//			int ind = 1;
//
//			for (int i = 1; i <= m; i++) {
//				String monthFromList = monthlist.get(i).getText();
//				if (monthFromList.equalsIgnoreCase(month)) {
//					ind++;
//					clickOnElement(Elementlist.get(i));
//					break;
//				}
//			}
//			String xapth = "//android.widget.ListView[contains(@resource-id,'id/calendar_view')]/android.widget.LinearLayout["
//					+ ind + "]/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout";
//			List<RemoteWebElement> list = driver.findElements(By.xpath(xapth));
//			for (int i = 0; i <= 42; i++) {
//				String dateFromList = list.get(i).getText();
//				if (dateFromList.equalsIgnoreCase(Date) && Integer.parseInt(Date) > Integer.parseInt(date)) {
//					clickOnElement(Elementlist.get(i));
//					break;
//				}
//			}
//
//			clickOnElement(DBSappObject.OKButton());

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Step("Click on Account after selecting 'Local Recipients' and verify pageHeader")
	public void clickingOnAccountTypeInLocalRecipient(String valueSelectedFromList) throws Exception {
		try {
			int o = 0;
			for (int i = 0; i < DBSappObject.AllTabOptionsList().size(); i++) {
				String tabText = DBSappObject.AllTabOptionsList().get(i).getText();
				o++;
				if (tabText.contains(CommonTestData.LOCAL_RECIPIENT_FROMLIST.getEnumValue())) {
					clickOnElement(DBSappObject.AllTabOptionsList().get(i));
					break;
				}
			}
			gestUtils.DragAndDropElementToElement(DBSappObject.AllTabOptionsList().get(o), DBSappObject.AllTab());
			TakeScreenshot(DBSappObject.SubTitleTextList().get(0));
			List<MobileElement> Elementlist = DBSappObject.SubTitleTextList();
			List<MobileElement> ElementlistClickable = DBSappObject.ListElementToClickable();
			int l = Elementlist.size();
			int index = 0;
			String LocalRecipientList = null;
			for (int i = 0; i < l; i++) {
				LocalRecipientList = Elementlist.get(i).getText();
				if (LocalRecipientList.equalsIgnoreCase(valueSelectedFromList)) {
					index++;
					clickOnElement(ElementlistClickable.get(i));
					break;
				}
			}
			Asserts.assertTrue(index > 0, "No element found in the list of corresponding value");
			String ErrorMsg = getTexOfElement(DBSappObject.ErrorMessgeElement());

			if (CommonTestData.ERROR_MSG.getEnumValue().equals(ErrorMsg)) {
				TakeScreenshot(DBSappObject.OKButton());
				clickOnElement(DBSappObject.OKButton());
			}

			String xpath1 = "//android.widget.TextView[@text='Primary source of fund']";
			List<RemoteWebElement> list1 = driver.findElements(By.xpath(xpath1));
			if (list1.size() > 0) {
				TakeScreenshot(DBSappObject.PrimarySourceOfFund());
				if (isElementVisible(DBSappObject.PrimarySourceOfFund()))
					clickOnElement(DBSappObject.OKButton());
			}
		}catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to select account from local recipient and verify header  ",e);
		}
		catch (Exception e) {		
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to select account from local recipient and verify header  ",e);
		}
	}

	@Step("Update Personal Details")
	public void UpdatePersonalDetails(String appName) throws Exception {
		try {
			ClickOnMoreButton();
			EnterPasscodeAndDone();
			SelectUpdateContactDetails();
			SelectPersonalDetailsTabAndVerifyPersonalDetailsPage(appName);
			ClickOnCheckboxes();
			ClickOnNextButton();
			ClickOnConfirmButton();
			ClickOnBackToMoreServicesBtn();
			SelectUpdateContactDetails();
			SelectPersonalDetailsTabAndVerifyPersonalDetailsPage(appName);
			VerifyLastUpdatedDateOfCheckboxes();

			// Leave On Home Page for next case run.
			ClickOnBackButton();
			ClickOnBackButton();
			ClickOnHomeButton();

		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("TESTCASE_EXCEPTION", " Failed to update personal details  ",e);
		}
		catch (Exception e) {		
			obj_handleexception.throwException("TESTCASE_EXCEPTION", "  Failed to update personal details  ",e);
		}
	}

	@Step("Click On 'Back to More Services' Button.")
	public void ClickOnBackToMoreServicesBtn() throws Exception {
		try {
			clickOnElement(DBSappObject.BACKTOMoreServicesBtn());
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to click on Back toMore button  ",e);
		}
		catch (Exception e) {		
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to click on Back toMore button  ",e);
		}
	}

	@Step("Verify Last Update Date Of Checkboxes.")
	public void VerifyLastUpdatedDateOfCheckboxes() throws Exception {
		try {
			Calendar calendar = Calendar.getInstance();
			Date today = calendar.getTime();
			DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
			String todayAsString = dateFormat.format(today);
			String ExpectedDate = todayAsString.replaceAll("-", " ");
			String ExpectedLastUpdatedDateValue = "Last updated on " + ExpectedDate;

			for (int i = 0; i < DBSappObject.ContactDetailsValuesList().size(); i++) {
				Asserts.assertEquals(getTexOfElement(DBSappObject.ContactDetailsValuesList().get(i)),
						ExpectedLastUpdatedDateValue,
						ExpectedLastUpdatedDateValue + " Dates is not matching after Updating Personal Details.");
			}
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to verify last update on checkbox ",e);
		}
		catch (Exception e) {		
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to verify last update on checkbox  ",e);
		}
	}

	@Step("Click On Confirm Button and Verify 'Successfully Updated' Message, 'Back Button', 'logout Button', 'Update More Details' button & 'Back to More Services' Button.")
	public void ClickOnConfirmButton() throws Exception {
		try {
			gestUtils.scrollUPtoObject("text", "CONFIRM", DBSappObject.ConfirmBtn());
			TakeScreenshot(DBSappObject.ConfirmBtn());
			clickOnElement(DBSappObject.ConfirmBtn());
			EnterPasscodeAndDone();
			if (isElementVisible(DBSappObject.CompletionStatusImage())) {
				TakeScreenshot(DBSappObject.SuccessfullyUpdatedMessageEle());
				Asserts.assertEquals(getTexOfElement(DBSappObject.SuccessfullyUpdatedMessageEle()),
						CommonTestData.SUCCESSFULLY_UPDATED_MESSAGE.getEnumValue(),
						CommonTestData.SUCCESSFULLY_UPDATED_MESSAGE.getEnumValue() + " Text is not matching");

				Asserts.assertTrue(isElementVisible(DBSappObject.LogoutBtn()), "Log Out is not displayed.");
				Asserts.assertTrue(isElementVisible(DBSappObject.BackBtnImageView()),
						"Back Btn Image View is not displayed.");
				gestUtils.scrollUPtoObject("text", "UPDATE MORE DETAILS", DBSappObject.UpdateMoreDetailsBtn());
				TakeScreenshot(DBSappObject.UpdateMoreDetailsBtn());
				Asserts.assertTrue(isElementVisible(DBSappObject.BACKTOMoreServicesBtn()),
						"BACK TO More Services Btn is not displayed.");
				Asserts.assertTrue(isElementVisible(DBSappObject.UpdateMoreDetailsBtn()),
						"Update More Details Btn is not displayed.");
			}

		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to click on confirm button and verify success message  and all buttons  ",e);
		}
		catch (Exception e) {		
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to click on confirm button and verify success message  and all buttons ",e);
		}
	}

	@Step("Click on 'Call Me','SMS Me','Email Me','Fax Me','Mail Me'")
	public void ClickOnCheckboxes() throws Exception {
		try {
			String[] ExpectedTitles = new String[] {"Call Me","SMS Me","Email Me","Fax Me","Mail Me"};
			int j = 0;
			for (int i = 0; i < DBSappObject.ContactDetailsTitlesList().size()&&j<5; i++) {
				String actualTitles = getTexOfElement(DBSappObject.ContactDetailsTitlesList().get(i));
				if(actualTitles.equalsIgnoreCase(ExpectedTitles[j])) {
					Asserts.assertEquals(getTexOfElement(DBSappObject.ContactDetailsTitlesList().get(i)), ExpectedTitles[j],
							ExpectedTitles[i] + " Titles is not matching in Personal Perticulars Section.");
					clickOnElement(DBSappObject.ContactDetailsTitlesList().get(i)); 
					j++;
					i = 0;
				}
			}
			TakeScreenshot(DBSappObject.TermsAndConditionsMsg());
			Asserts.assertEquals(getTexOfElement(DBSappObject.TermsAndConditionsMsg()),
					CommonTestData.TERMS_AND_CONDITIOINS_MESSAGE.getEnumValue(),
					CommonTestData.TERMS_AND_CONDITIOINS_MESSAGE.getEnumValue() + " Text is not matching");
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to select All checkboxes  ",e);
		}
		catch (Exception e) {		
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to select All checkboxes  ",e);
		}
		
	}

	@Step("Select 'Personal & Contact Details' Tab And Verify visibility of 'Personal & Contact Details' Page Header, 'Contact Details' &  'Personal Perticulars' Section.")
	public void SelectPersonalDetailsTabAndVerifyPersonalDetailsPage(String appName) throws Exception {
		try {
			clickOnElement(DBSappObject.PersonalAndContactDetailsTab());
			EnterPasscodeAndDone();

			
			TakeScreenshot(DBSappObject.ContactDetailsTitle()); 
			if(getTexOfElement(DBSappObject.UpdateContactDetailsPageHeader()).equalsIgnoreCase(CommonTestData.PERSONAL_AND_CONTACTDETAILS_PAGEHEADER.getEnumValue())) {
				Asserts.assertEquals(getTexOfElement(DBSappObject.UpdateContactDetailsPageHeader()),
						CommonTestData.PERSONAL_AND_CONTACTDETAILS_PAGEHEADER.getEnumValue(),
						CommonTestData.PERSONAL_AND_CONTACTDETAILS_PAGEHEADER.getEnumValue() + " Text is not matching");
			}
			else if(getTexOfElement(DBSappObject.UpdateContactDetailsPageHeader()).equalsIgnoreCase(CommonTestData.CONTACT_DETAILS_PAGEHEADER.getEnumValue())) {
				Asserts.assertEquals(getTexOfElement(DBSappObject.UpdateContactDetailsPageHeader()),
						CommonTestData.CONTACT_DETAILS_PAGEHEADER.getEnumValue(),
						CommonTestData.CONTACT_DETAILS_PAGEHEADER.getEnumValue() + " Text is not matching");
			}


			TakeScreenshot(DBSappObject.ContactDetailsTitle());
			Asserts.assertEquals(getTexOfElement(DBSappObject.UpdateContactDetailsPageHeader()),
					CommonTestData.PERSONAL_AND_CONTACTDETAILS_PAGEHEADER.getEnumValue(),
					CommonTestData.PERSONAL_AND_CONTACTDETAILS_PAGEHEADER.getEnumValue() + " Text is not matching");


			Asserts.assertEquals(getTexOfElement(DBSappObject.ContactDetailsTitle()),
					CommonTestData.CONTACT_DETAILS_TITLE.getEnumValue(),
					CommonTestData.CONTACT_DETAILS_TITLE.getEnumValue() + " Text is not matching");

			Asserts.assertEquals(getTexOfElement(DBSappObject.EmailNotes()), CommonTestData.EMAIL_NOTES.getEnumValue(),
					CommonTestData.EMAIL_NOTES.getEnumValue() + " Text is not matching");


			gestUtils.scrollUPtoObject("text", "PERSONAL PARTICULARS", DBSappObject.PersonalPerticularSectionTitle()); 

			gestUtils.scrollUPtoObject("text", "PERSONAL PARTICULARS",
					DBSappObject.PersonalPerticularSectionTitle());

			Thread.sleep(1500);
			TakeScreenshot(DBSappObject.PersonalPerticularSectionTitle());
			Asserts.assertEquals(getTexOfElement(DBSappObject.ContactDetailsChangeBtn()),
					CommonTestData.CHANGE_BUTTON.getEnumValue(),
					CommonTestData.CHANGE_BUTTON.getEnumValue() + " Text is not matching");
			Asserts.assertEquals(getTexOfElement(DBSappObject.PersonalPerticularSectionTitle()),
					CommonTestData.PERSONAL_PARTICULARS.getEnumValue(),
					CommonTestData.PERSONAL_PARTICULARS.getEnumValue() + " Text is not matching");

			
			gestUtils.scrollUPtoObject("text", "MARKETING MESSAGES", DBSappObject.MarketingMessageTitle()); 


			gestUtils.scrollUPtoObject("text", "MARKETING MESSAGES", DBSappObject.MarketingMessageTitle());

			Thread.sleep(1500);
			TakeScreenshot(DBSappObject.MarketingMessageTitle());
			Asserts.assertEquals(getTexOfElement(DBSappObject.PersonalPerticularChangeBtn()),
					CommonTestData.CHANGE_BUTTON.getEnumValue(),
					CommonTestData.CHANGE_BUTTON.getEnumValue() + " Text is not matching");

			Asserts.assertEquals(getTexOfElement(DBSappObject.MarketingMessageTitle()),
					CommonTestData.MARKETING_MESSAGE_TITLES.getEnumValue(),
					CommonTestData.MARKETING_MESSAGE_TITLES.getEnumValue() + " Text is not matching");

			Asserts.assertEquals(getTexOfElement(DBSappObject.MarketingMessageNotes()),
					CommonTestData.MARKETING_MESSAGE_NOTES.getEnumValue(),
					CommonTestData.MARKETING_MESSAGE_NOTES.getEnumValue() + " Text is not matching");

			if(appName.equalsIgnoreCase("DBS")) {
				gestUtils.scrollUPtoObject("text", "NEXT", DBSappObject.nextButton()); 
				TakeScreenshot(DBSappObject.nextButton()); 
			}
			else if(appName.equalsIgnoreCase("iWEALTH")) {
				gestUtils.scrollUPtoObject("text", "CONFIRM", DBSappObject.confirmButton()); 
				TakeScreenshot(DBSappObject.confirmButton()); 
			}
			Asserts.assertEquals(getTexOfElement(DBSappObject.UPPSectionLabel()),
					CommonTestData.IWOULD_LIKE_THEBANK_TO_MESSAGE.getEnumValue(),
					CommonTestData.IWOULD_LIKE_THEBANK_TO_MESSAGE.getEnumValue() + " Text is not matching");
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to select Personal And contact details and verify all fields  ",e);
		}
		catch (Exception e) {		
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to select Personal And contact details and verify all fields ",e);
		}
	}
	@Step("Verify Account Type , Account Name, Currency display and displayed Amount under Account Section")
	public void verifyAccountTypeNameCurrencyAmount(String AccountType, String AccountName, String currency)
			throws Exception {
		try {
			VerifyButtonLabelAndClick(DBSappObject.accountSectionHomePage(), CommonTestData.ACCOUNT_SECTION.getEnumValue());
			gestUtils.scrollUPtoObject("text", "digiPortfolio", null);
			TakeScreenshot(DBSappObject.depositeHomePage());
			Asserts.assertEquals(getTexOfElement(DBSappObject.depositeHomePage()), AccountType,
					AccountType + " is not present");
			Asserts.assertEquals(getTexOfElement(DBSappObject.accountNameHomePage()), AccountName,
					AccountName + " is not present");
			Asserts.assertEquals(getTexOfElement(DBSappObject.currencyHomePage()), currency,
					currency + " is not present");
			//boolean i = DBSappObject.amountValueHomePage().getText().isEmpty();
			//Asserts.assertTrue(i == false, "Amount not Found");

		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("TESTCASE_EXCEPTION", " Failed to Execute Account Details CASA ",e);
			//System.out.println("Inside Appply debit card catch "+e.getCode());		
		}
		catch (Exception e) {			
			//System.out.println("Inside Appply debit card catch");
			obj_handleexception.throwException("TESTCASE_EXCEPTION", " Failed to Execute Account Details CASA ",e);
		}
	}

	@Step("Select Update Contact Details And Verify visibility of 'Back Button', 'Personal & Contact Details' Tab, 'Mailing Address' Tab and 'Update Contact Details' Page Header.")
	public void SelectUpdateContactDetails() throws Exception {
		try {
			clickOnElement(DBSappObject.ContactSearchfield());
			clickOnElement(DBSappObject.EditTextSearchBox());
			enterTextInTextbox(DBSappObject.EditTextSearchBox(),
					CommonTestData.UPDATE_CONTACT_DETAILS_PAGEHEADER.getEnumValue());
			TakeScreenshot(DBSappObject.UpdateContactDetails());
			clickOnElement(DBSappObject.UpdateContactDetails());
			TakeScreenshot(DBSappObject.BackBtnImageView());
			Asserts.assertEquals(getTexOfElement(DBSappObject.PageHeaderForOpenAccount()),
					CommonTestData.UPDATE_CONTACT_DETAILS_PAGEHEADER.getEnumValue(),
					CommonTestData.UPDATE_CONTACT_DETAILS_PAGEHEADER.getEnumValue() + " Text is not matching");
			Asserts.assertTrue(isElementVisible(DBSappObject.BackBtnImageView()),
					"Back Btn Image View is not displayed.");
			Asserts.assertTrue(isElementVisible(DBSappObject.PersonalAndContactDetailsTab()),
					"Personal And Contact Details Tab is not displayed.");
			Asserts.assertTrue(isElementVisible(DBSappObject.MailingAddressTab()),
					"Mailing Address Tab is not displayed.");
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to select update contact details and verify visibility all fields  ",e);
		}
		catch (Exception e) {		
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to select update contact details and verify visibility all fields  ",e);
		} 
	}
		@Step("Verify CreditCard Temperary Limit Increase")
		public void CreditCardTempLimitIncrease() throws Exception {
			try {
				ClickOnMoreButton();
				EnterPasscodeAndDone();
				sendDataInCommonSearchBoxAndSelectFromDropDown(CommonTestData.TEMP_LIMIT_INCREASE.getEnumValue(),
						CommonTestData.TEMP_LIMIT_INCREASE.getEnumValue(),
						CommonTestData.TEMP_LIMIT_INCREASE_TITLE.getEnumValue(), DBSappObject.PageHeader());
				setAmountDurationPurposeForLimitIncrease("100", "wedding");
				gestUtils.scrollUPtoObject("text", "NEXT", null);
//				sendDataInCommonSearchBoxAndSelectFromDropDown(CommonTestData.TEMP_LIMIT_INCREASE.getEnumValue(),
//						CommonTestData.TEMP_LIMIT_INCREASE.getEnumValue(),
//						CommonTestData.TEMP_LIMIT_INCREASE_TITLE.getEnumValue(), DBSappObject.PageHeader());
//				setAmountDurationPurposeForLimitIncrease("100", "wedding");
//
//				gestUtils.scrollUPtoObject("text", "NEXT", DBSappObject.NextBtn());
				ClickOnNextButton1();
				Thread.sleep(3000);
				for(int i=0; i<DBSappObject.PageHeaderList2().size();i++ ) {
					System.out.println(DBSappObject.PageHeaderList2().get(i).getText());
				}
				verifyPageHeader(CommonTestData.REVIEW_APPLICATION_CREDITLIMIT_TITLE.getEnumValue(),
						DBSappObject.PageHeaderList2().get(2));

			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}

	public void selectPurposeAccountTypeMobileNumberIfAvaliable(String AccountType,String purpose,String MobileNo) throws Exception//"Savings""Personal Gifts""9999999990"
	{
		try {
		String xpath_account = "//android.widget.TextView[contains(@text,'account type')]";
		List<RemoteWebElement> list = driver.findElements(By.xpath(xpath_account));
		if (list.size() > 0) {
			selectAccountType(AccountType);
		}
		String xpath_purpose = "//android.widget.TextView[contains(@text,'purpose')]";
		List<RemoteWebElement> list1 = driver.findElements(By.xpath(xpath_purpose));
		if (list1.size() > 0) {
			selectPurpose(purpose);
		}
		String xpath = "//android.widget.EditText[contains(@text,'mobile number')]";
		List<RemoteWebElement> list2 = driver.findElements(By.xpath(xpath));
		if (list2.size() > 0) {
			enterMobileNo(MobileNo);
		}
		
	}
	 catch (Exception e) {
		 e.printStackTrace();
			throw e;
	}
	
}
	@Step("Select AccountType")
	public void selectAccountType(String AccountType) throws Exception//"Savings"
	{
		try {
			clickOnElement(DBSappObject.accountTypeInOversea());
			TakeScreenshot(DBSappObject.commonList().get(0));
			List<MobileElement> Elementlist = DBSappObject.commonList();
			int l = Elementlist.size();
			int index = 0;
			String LocalRecipientList = null;
			for (int i = 0; i < l; i++) {
				LocalRecipientList = Elementlist.get(i).getText();
				if (LocalRecipientList.contains(AccountType)) {
					index++;
					clickOnElement(Elementlist.get(i));
					Thread.sleep(3000);
				}
				Asserts.assertTrue(index > 0, "No element found in the list of corresponding value");
				wait.waitForElementVisibility(DBSappObject.PageHeaderList2().get(0));
			}
		}
		 catch (Exception e) {
			 e.printStackTrace();
				throw e;
		}
		}

	@Step("Verify Fund Transfer Pay Credit Card.")
	public void FundsTransfer_PayCreditCard() throws Exception {
		try {
			ClickOnPayAndTransferBtn();
			EnterPasscodeAndDone();
			SelectAllTAB();
			String ExpectedToBankNameWithAccountNo = CommonTestData.FUNDTRANSFER_CREDITCARD_TO_ACCOUNTNUMBER_WITHBANK
					.getEnumValue();
			clickingOnAccountTypeInCreditCard(ExpectedToBankNameWithAccountNo);

			EnterAmount(DBSappObject.AmountEditableField(), CommonTestData.AMOUNTTO_TRANSFERFUND.getEnumValue());

			String ExpectedFromBankName = CommonTestData.FUNDTRANSFER_CREDITCARD_FROM_ACCOUNT_NAME.getEnumValue();
			SelectFundSourceAccount(ExpectedFromBankName);

			String ExpectedSelectedDate = getTexOfElement(DBSappObject.TransferDateTextElement());
			Asserts.assertEquals("Immediate", ExpectedSelectedDate, "Selected Date is not Matching");

			ClickOnNextButton();
			Asserts.assertEquals(getTexOfElement(DBSappObject.CreditCard_PageHeader()),
					CommonTestData.REVIEW_PAYMENT_PAGEHEADER.getEnumValue(),
					CommonTestData.REVIEW_PAYMENT_PAGEHEADER.getEnumValue() + " Text is not matching");
			ClickOnPayNowButton();
			VerifyPaymentSubmittedMsg();

			String ExpectedFromAccountNumber = CommonTestData.FUNDTRANSFER_CREDITCARD_FROM_ACCOUNT_NUMBER
					.getEnumValue();
			String ExpectedToAccountNumber = CommonTestData.FUNDTRANSFER_CREDITCARD_TO_ACCOUNTNUMBER.getEnumValue();
			String ExpectedToCreditCardName = CommonTestData.FUNDTRANSFER_TOCREDITCARD_NAME.getEnumValue();
			VerifyVisibiltyOfSomeElements_FundTransferCreditCard(ExpectedFromBankName, ExpectedFromAccountNumber,
					ExpectedToAccountNumber, ExpectedToCreditCardName);

			// Leaving On Home Page for Next case Run.
			clickOnElement(DBSappObject.BackIcon());
			clickOnElement(DBSappObject.homeButton());
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("TESTCASE_EXCEPTION", " Failed to fund transfer pay credit card  ",e);
		}
		catch (Exception e) {		
			obj_handleexception.throwException("TESTCASE_EXCEPTION", " Failed to fund transfer pay credit card    ",e);
		}
	}

	@Step("Verify Fund Transfer Bill Payment.")
	public void FundsTransfer_BillPayment() throws Exception {
		try {
			ClickOnPayAndTransferBtn();
			EnterPasscodeAndDone();
			SelectAllTAB();
			String ExpectedToBankNameWithAccountNo = CommonTestData.FUNDTRANSFER_BillPayment_TO_ACCOUNTNUMBER_WITHBANK
					.getEnumValue();
			clickingOnAccountTypeInBillingOrganisations(ExpectedToBankNameWithAccountNo);
			EnterAmount(DBSappObject.AmountEditableField(), CommonTestData.AMOUNTTO_TRANSFERFUND.getEnumValue());
			String ExpectedSelectedDate = getTexOfElement(DBSappObject.TransferDateTextElement());
			Asserts.assertEquals("Immediate", ExpectedSelectedDate, "Selected Date is not Matching");

			ClickOnNextButton();
			Asserts.assertEquals(getTexOfElement(DBSappObject.PageHeader()),
					CommonTestData.REVIEW_PAYMENT_PAGEHEADER.getEnumValue(),
					CommonTestData.REVIEW_PAYMENT_PAGEHEADER.getEnumValue() + " Text is not matching");
			ClickOnPayNowButton();
			VerifyPaymentSubmittedMsg();

			// Leaving On Home Page for Next case Run.
			clickOnElement(DBSappObject.BackIcon());
			clickOnElement(DBSappObject.homeButton());
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("TESTCASE_EXCEPTION", " Failed to verify Fund transfer bill payment  ",e);
		}
		catch (Exception e) {		
			obj_handleexception.throwException("TESTCASE_EXCEPTION", " Failed to verify Fund transfer bill payment  ",e);
		}
	}

	@Step("Verifies the Credit Card Name and Account Number After transferring the fund.")
	public void VerifyVisibiltyOfSomeElements_FundTransferCreditCard(String ExpectedFromBankName,
			String ExpectedFromAccountNumber, String ExpectedToAccountNumber, String ExpectedToCreditCardName)
			throws Exception {
		try {
			clickOnElement(DBSappObject.FooterExpandableBtn());
			gestUtils.scrollUPtoObject("text", "Reference No.", DBSappObject.ReferenceNumberText());
			TakeScreenshot(DBSappObject.ReferenceNumberText());

			String[] ExpTitleList = new String[] { "From", "To", "When", "Source Account Balance",
					"Destination Account Balance", "Reference No." };

			for (int i = 0; i < DBSappObject.FundTransferDetailslabel1List().size(); i++) {
				Asserts.assertEquals(getTexOfElement(DBSappObject.FundTransferDetailslabel1List().get(i)),
						ExpTitleList[i],
						ExpTitleList[i] + "Titles is not matching after transfer fund through credit card.");
			}

			Asserts.assertEquals(getTexOfElement(DBSappObject.FundTransferDetailslabel2List().get(0)),
					ExpectedFromBankName, ExpectedFromBankName + " is not matching after Fund Transfer Credit Cards.");

			Asserts.assertEquals(getTexOfElement(DBSappObject.FundTransferDetailslabel2List().get(1)),
					ExpectedToCreditCardName,
					ExpectedToCreditCardName + " is not matching after Fund Transfer Credit Cards.");

			Asserts.assertEquals(getTexOfElement(DBSappObject.FundTransferDetailslabel3List().get(0)),
					ExpectedFromAccountNumber,
					ExpectedFromAccountNumber + " is not matching after Fund Transfer Credit Cards.");

			Asserts.assertEquals(getTexOfElement(DBSappObject.FundTransferDetailslabel3List().get(1)),
					ExpectedToAccountNumber,
					ExpectedToAccountNumber + " is not matching after Fund Transfer Credit Cards.");

		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to verify credit card name and account number  ",e);
		}
		catch (Exception e) {		
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to verify credit card name and account number  ",e);
		}
	}

	@Step("Click on 'To Account Credit Card' after selecting 'Credit Cards' and verify pageHeader")
	public void clickingOnAccountTypeInCreditCard(String valueSelectedFromList) throws Exception {
		try {
			int o = 0;
			for (int i = 0; i < DBSappObject.AllTabOptionsList().size(); i++) {
				String tabText = DBSappObject.AllTabOptionsList().get(i).getText();
				o++;
				if (tabText.contains(CommonTestData.CREDIT_CARDS_TAB.getEnumValue())) {
					clickOnElement(DBSappObject.AllTabOptionsList().get(i));
					break;
				}
			}

			gestUtils.DragAndDropElementToElement(DBSappObject.AllTabOptionsList().get(o), DBSappObject.AllTab());
			TakeScreenshot(DBSappObject.SubTitleTextList().get(0));
			List<MobileElement> Elementlist = DBSappObject.SubTitleTextList();
			List<MobileElement> ElementlistClickable = DBSappObject.ListElementToClickable();
			int l = Elementlist.size();
			int index = 0;
			String ToAccountOptionInSelectedTab = null;
			for (int i = 0; i < l; i++) {
				ToAccountOptionInSelectedTab = Elementlist.get(i).getText();
				if (ToAccountOptionInSelectedTab.equalsIgnoreCase(valueSelectedFromList)) {
					index++;
					clickOnElement(ElementlistClickable.get(i));
					break;
				}
			}
			Asserts.assertTrue(index > 0, "No element found in the list of corresponding value");

			Asserts.assertEquals(getTexOfElement(DBSappObject.CreditCard_PageHeader()),
					CommonTestData.CREDIT_CARD_PAGEHEADER.getEnumValue(),
					CommonTestData.CREDIT_CARD_PAGEHEADER.getEnumValue() + " Text is not matching");

		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to select credit card and account type in credit card  ",e);
		}
		catch (Exception e) {		
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to select credit card and account type in credit card ",e);
		}
	}
		@Step("Select purpose")
		public void selectPurpose(String purpose) throws Exception
		{
			try {
				clickOnElement(DBSappObject.purposTypeInOversea());
				TakeScreenshot(DBSappObject.commonList().get(0));
				List<MobileElement> Elementlist = DBSappObject.commonList();
				int l = Elementlist.size();
				int index = 0;
				String LocalRecipientList = null;
				for (int i = 0; i < l; i++) {
					LocalRecipientList = Elementlist.get(i).getText();
					if (LocalRecipientList.contains(purpose)) {
						index++;
						clickOnElement(Elementlist.get(i));
						break;
					}
				}
				Asserts.assertTrue(index > 0, "No element found in the list of corresponding value");
				wait.waitForElementVisibility(DBSappObject.PageHeaderList2().get(0));
			}
		 catch (Exception e) {
			 e.printStackTrace();
				throw e;
		}
	
}
		@Step("enter mobile No.")
		public void enterMobileNo(String MobileNo) throws Exception
		{
			try {
				TakeScreenshot(DBSappObject.mobileNoInOversea());
				enterTextInTextbox(DBSappObject.mobileNoInOversea(), MobileNo);
				wait.waitForElementVisibility(DBSappObject.PageHeaderList2().get(0));
			}
		 catch (Exception e) {
			 e.printStackTrace();
				throw e;
		}
	
}


	@Step("Click on 'To Account Bill' after selecting 'Billing organisation' and verify Page Header")
	public void clickingOnAccountTypeInBillingOrganisations(String valueSelectedFromList) throws Exception {
		try {
			int o = 0;
			for (int i = 0; i < DBSappObject.AllTabOptionsList().size(); i++) {
				String tabText = DBSappObject.AllTabOptionsList().get(i).getText();
				o++;
				if (tabText.contains(CommonTestData.BILLING_ORGANISATIONS_TAB.getEnumValue())) {
					clickOnElement(DBSappObject.AllTabOptionsList().get(i));
					break;
				}
			}

			gestUtils.DragAndDropElementToElement(DBSappObject.AllTabOptionsList().get(o), DBSappObject.AllTab());
			TakeScreenshot(DBSappObject.SubTitleTextList().get(0));
			List<MobileElement> Elementlist = DBSappObject.SubTitleTextList();
			List<MobileElement> ElementlistClickable = DBSappObject.ListElementToClickable();
			int l = Elementlist.size();
			int index = 0;
			String ToAccountOptionInSelectedTab = null;
			for (int i = 0; i < l; i++) {
				ToAccountOptionInSelectedTab = Elementlist.get(i).getText();
				if (ToAccountOptionInSelectedTab.equalsIgnoreCase(valueSelectedFromList)) {
					index++;
					clickOnElement(ElementlistClickable.get(i));
					break;
				}
			}
			Asserts.assertTrue(index > 0, "No element found in the list of corresponding value");

			Asserts.assertEquals(getTexOfElement(DBSappObject.PageHeader2()),
					CommonTestData.PAY_TO_BILLER_PAGE_HEADER.getEnumValue(),
					CommonTestData.PAY_TO_BILLER_PAGE_HEADER.getEnumValue() + " Text is not matching");

		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to select billing organiation , to account and verify page header  ",e);
		}
		catch (Exception e) {		
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to select billing organiation , to account and verify page header  ",e);
		}
	}

}
