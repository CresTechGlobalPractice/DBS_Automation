package com.crestech.pages;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofSeconds;
import org.openqa.selenium.Dimension;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
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
import io.appium.java_client.TouchAction;
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
	public  TouchAction touch =null;
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
			touch = new TouchAction(this.driver);
			PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(5)), DBSappObject);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Step("Relaunching DBS application")
	public void relaunchingDBS() throws Exception {
		try {
			relanchApplication(CommonTestData.DBS_APP_PACKAGE.getEnumValue(),
					CommonTestData.DBS_APPS_ACTIVITY.getEnumValue());
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("RELAUNCHING_DBS_EXCEPTION", " Failed to Relaunching DBS Application  ",e);
					
		}
		catch (Exception e) {			
			
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
					
		}
		catch (Exception e) {			
			
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
					
		}
		catch (Exception e) {			
			
			obj_handleexception.throwException("RELAUNCHING_iWEALTH_EXCEPTION", " Failed to Relaunching iWealth Application  ",e);
		}
	}

	@Step("Log In the Application")
	public void logInApplication(String userName, String password, String appName, String serverName) throws Exception {
		try {
			CommonAlertElements btnElements = new CommonAlertElements(driver);
			Thread.sleep(40000);
			String quitButtonXpath = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.Button";
			List<RemoteWebElement> list = driver.findElements(By.xpath(quitButtonXpath));
			if (list.size() > 0) {
				driver.closeApp();
				relaunchingDBS();
				wait.waitForElementToBeClickable(DBSappObject.loginButton());
				Thread.sleep(5000);
				System.out.println("Relaunch Done");
			}
			SelectUATServer(serverName);
			System.out.println("UAT server selected");
			clickOnLoginButton();
			System.out.println("clickOnLoginButton");
			sendDataInUserId(userName);
			System.out.println("sendDataInUserId");
			sendDataInUserPin(password);
			System.out.println("sendDataInUserPin");
			clickOnLoginButton2();
			System.out.println("clickOnLoginButton");
			digitalTokenSetUp();
			AndroidAlert androidAlert = new AndroidAlert(driver);
			Thread.sleep(4000);
			String getStartedXpath = null;
//			
//			if (appName.contains("POSB"))
//				getStartedXpath = "//android.widget.Button[@resource-id='com.dbs.sit1.posbmbanking:id/btn_get_started']";
//			else if (appName.contains("DBS"))
				getStartedXpath = "//android.widget.Button[@resource-id='com.dbs.sit1.dbsmbanking:id/btn_get_started']";
//			else if (appName.contains("iWEALTH"))
//				getStartedXpath = "//android.widget.Button[@resource-id='com.dbs.sg.uat.dbsiwealth:id/btn_get_started']";
			List<RemoteWebElement> getStartedlist = driver.findElements(By.xpath(getStartedXpath));
			if (getStartedlist.size() > 0) {
				clickOnElement((MobileElement) getStartedlist.get(0));
				System.out.println("Click on Get Started");
			}
			String errorAlertOKButton = "//android.widget.Button[@resource-id='android:id/button1']";
			List<RemoteWebElement> errorAlertOKButtonlist = driver.findElements(By.xpath(errorAlertOKButton));

			if (errorAlertOKButtonlist.size() > 0)
				clickOnElement((MobileElement) errorAlertOKButtonlist.get(0));

			androidAlert.fingerprintAlertHandlingWithButtonMessage(btnElements.closeButton(),
					CommonTestData.FINGERPRINT_MESSAGE.getEnumValue());
			androidAlert.recordingAlertHandlingWithButtonMessage(btnElements.closeButton(),
					CommonTestData.RECORDERSECTION_MESSAGE.getEnumValue());
			TakeScreenshot(DBSappObject.PayAndTransferBtn()); 

		}catch (HandleException e) {	
			obj_handleexception.throwHandleException("TESTCASE_EXCEPTION", " Failed to Exceute Log In Application " ,e);		
		}
		catch (Exception e) {				
			obj_handleexception.throwException("TESTCASE_EXCEPTION", " Failed to Exceute Log In Application ",e);
		}
	}

	@Step("Select UAT Server.")
	public void SelectUATServer(String serverName) throws Exception {
		try {
			ClickOnPreloginButton();
			ClickOnMoreModuleOnLoginPage();
			ClickOnChangeServerButton();
			gestUtils.scrollUPtoObject("text", serverName, null);
			String serverNameXpath = "//android.widget.TextView[@text='"+serverName+"']";
			MobileElement serverNameElement = (MobileElement) driver.findElement(By.xpath(serverNameXpath));
			TakeScreenshot(serverNameElement);
			clickOnElement(serverNameElement);
			ClickOnChangeServerSaveButton();
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("SELECTUATSERVER_EXCEPTION", " Failed to Select UAT Server " ,e);		
		}
		catch (Exception e) {			
			obj_handleexception.throwException("SELECTUATSERVER_EXCEPTION", " Failed to Select UAT Server ",e);
		}
	}
	
	
	@Step("Clicked on Save button to Change Server")
	public void ClickOnChangeServerSaveButton() throws Exception {
		try {
				TakeScreenshot(DBSappObject.ChangeServerSaveBtn());
				clickOnElement(DBSappObject.ChangeServerSaveBtn());
				Thread.sleep(2000); 
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On Save Button " ,e);		
		}
		catch (Exception e) {			
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click On Save Button ",e);
		}
	}
	
	@Step("Clicked on Change Server button")
	public void ClickOnChangeServerButton() throws Exception {
		try {
				TakeScreenshot(DBSappObject.ChangeServerBtn());
				clickOnElement(DBSappObject.ChangeServerBtn());
				Thread.sleep(2000); 
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On Change Server Button " ,e);		
		}
		catch (Exception e) {			
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click On Change Server Button ",e);
		}
	}
	
	@Step("Clicked on Pre-Login button")
	public void ClickOnPreloginButton() throws Exception {
		try {
			int count = 0;
			TakeScreenshot(DBSappObject.PreLoginBtn());
			do {
				clickOnElement(DBSappObject.PreLoginBtn());
				Thread.sleep(5000);
				count++;
			} while (isElementVisible2(DBSappObject.PreLoginBtn()) && count < 3);
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On Prelogin Button " ,e);		
		}
		catch (Exception e) {			
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click On Prelogin Button ",e);
		}
	}

	
	@Step("Click On More Module On Login Page")
	public void ClickOnMoreModuleOnLoginPage() throws Exception{
		try {
			int count = 0;
			TakeScreenshot(DBSappObject.MoreButton());
			do {
				clickOnElement(DBSappObject.MoreButton());
				Thread.sleep(3000);
				count++;
			}while(isElementVisible2(DBSappObject.MoreButton()) && count < 3);
				
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On More Button " ,e);		
		}
		catch (Exception e) {			
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click On More Button ",e);
		}
	}

	@Step("Clicked on Login button")
	public void clickOnLoginButton() throws Exception {
		try {
			int count =0;
			TakeScreenshot(DBSappObject.loginButton());
			do {
				clickOnElement(DBSappObject.loginButton());
				Thread.sleep(5000);
				count++;
			} while (!isElementVisible2(DBSappObject.userIdEditText()) && count < 3);
		} catch (HandleException e) {
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On Login Button ", e);
		} catch (Exception e) {
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click On Login Button ", e);
		}
	}
	
	@Step("Clicked on Login button after Enter User Pin")
	public void clickOnLoginButton2() throws Exception {
		try {
			int count =0;
			TakeScreenshot(DBSappObject.loginButton());
			do {
				clickOnElement(DBSappObject.loginButton());
				Thread.sleep(5000);
				count++;
			} while (isElementVisible2(DBSappObject.loginButton()) && count < 3);
		} catch (HandleException e) {
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On Login Button ", e);
		} catch (Exception e) {
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click On Login Button ", e);
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
					
		}
		catch (Exception e) {			
			
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
					
		}
		catch (Exception e) {			
			
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Send USER PIN In Input Box ",e);
		}
	}

	@Step("Application Logout & Verifies the 'Tap on the stars to rate' field Message.")
	public void clickOnLogoutAndVerify(String logoutTextMsg, String Ratingmsg) throws Exception {
		try {
			VerifyWelcomeMessagesOnDashboardPage(CommonTestData.WELCOME.getEnumValue(), CommonTestData.DIGIBANK.getEnumValue(), CommonTestData.DBS_DIGIBANK.getEnumValue());
			TakeScreenshot(DBSappObject.logoutButton());
			androidAlert.AlertHandlingWithButtonMessage(DBSappObject.logoutButton(), logoutTextMsg,
					DBSappObject.logoutButton());
			Asserts.assertEquals(getTexOfElement(DBSappObject.postLogoutAlertMessage()), Ratingmsg,
					"'Tap on the stars to rate' Text is not found");
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("TESTCASE_EXCEPTION", " Failed to Execute Log Out  ",e);
					
		}
		catch (Exception e) {			
			
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
					
		}
		catch (Exception e) {			
			
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
					
		}
		catch (Exception e) {			
			
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
					
		}
		catch (Exception e) {			
			
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to execute Digital Taken Setup ",e);
		}
	}

	@Step("Verifies Remittance Corridor")
	public void VerifyRemittanceCorridor() throws Exception {
		try {
			ClickOnPayAndTransferBtn();
			EnterPasscodeAndDone();
			ClickOnOverseasModule(CommonTestData.OVERSEAS_ICON.getEnumValue());
			SelectOverseaPayee(CommonTestData.PAYEE_NAME_CORRIDOR.getEnumValue());
			selectFundSourceAndSelectAccountForCorredor(CommonTestData.SOURCE_ACCOUNT_NAME_CORRIDOR.getEnumValue(),
					CommonTestData.SOURCE_ACCOUNT_CORRIDOR.getEnumValue());
			pressEnterKeyAfterEnteringAmount(CommonTestData.CORRIDOR_AMOUNT.getEnumValue());
			gestUtils.scrollUPtoObject("text", "NEXT", DBSappObject.NextBtn());
			selectPurposeAccountTypeMobileNumberIfAvaliable(CommonTestData.EOTT_ACCOUNT_TYPE.getEnumValue(),
					CommonTestData.PURPOSE.getEnumValue(),CommonTestData.MOBILE_NUMBER.getEnumValue());
			ClickOnNextBtnAndVerifiesReviewTransferPage();
			ClickOnTransferNowBtnAndVerifiesTransferSubmittedMsg(CommonTestData.TRANSFER_SUBMITTED_MSG.getEnumValue(),
					DBSappObject.TransferSubmittedImage(), DBSappObject.TransferSubmittedMsg());
			ClickOnImageExpandBtnAndVerifiesReferenceNumberText();
			ClickOnShareTransferDetailsBtnAndVerifiesReferenceNumberText();
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("TESTCASE_EXCEPTION", " Failed to Execute Remittance Corridor  ",e);			
		}
		catch (Exception e) {			
			obj_handleexception.throwException("TESTCASE_EXCEPTION", " Failed to Execute Remittance Corridor  ",e);
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
			selectFundSourceAndSelectAccountForCorredor(CommonTestData.SOURCE_ACCOUNT_NAME_CORRIDOR.getEnumValue(), null);
			pressEnterKeyAfterEnteringAmount(CommonTestData.eOTT_AMOUNT.getEnumValue());
//			gestUtils.scrollUPtoObject("resource-id", "id/btn_remitnext", DBSappObject.NextBtn());
//			clickOnElement(DBSappObject.SelectPurposeOfTransfer());
//			clickOnElement(DBSappObject.FundTransferPurposeOption());
//			Asserts.assertEquals(getTexOfElement(DBSappObject.TextViewPurpose()),
//					CommonTestData.PURPOSE_OF_TRANSFER_TEXT.getEnumValue(),
//					CommonTestData.PURPOSE_OF_TRANSFER_TEXT.getEnumValue() + " Text is not found");
			gestUtils.scrollUPtoObject("text", "Next", null);
			selectPurposeAccountTypeMobileNumberIfAvaliable(CommonTestData.EOTT_ACCOUNT_TYPE.getEnumValue(),
					CommonTestData.PURPOSE.getEnumValue(),CommonTestData.MOBILE_NUMBER.getEnumValue());
			gestUtils.scrollUPtoObject("text", "NEXT", null);
			ClickOnNextBtnAndVerifiesReviewTransferPage();
			ClickOnTransferNowBtnAndVerifiesTransferSubmittedMsg(CommonTestData.TRANSFER_SUBMITTED_MSG.getEnumValue(),
					DBSappObject.TransferSubmittedImage(), DBSappObject.TransferSubmittedMsg());
			ClickOnImageExpandBtnAndVerifiesReferenceNumberText();
			ClickOnShareTransferDetailsBtnAndVerifiesReferenceNumberText();
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("TESTCASE_EXCEPTION", " Failed to Execute Remittance EOTT  ",e);			
		}
		catch (Exception e) {			
			obj_handleexception.throwException("TESTCASE_EXCEPTION", " Failed to Execute Remittance EOTT  ",e);
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
					
		}
		catch (Exception e) {			
			
			obj_handleexception.throwException("TESTCASE_EXCEPTION", " Failed to Execute Add payee DBSorPOSB  ",e);
		}
	}

	@Step("Verify 'You Have Added Recipient Msg' After Entering Secure PIN.")
	public void VerifyYouHaveAddedRecipientMsgAfterEnterSecurePIN() throws Exception {
		try {
			EnterPasscodeAndDone();
			Thread.sleep(10000); 
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
				Asserts.assertFail("You Have added a Recipient Page not displaying");
			}		
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FILEDVERIFICATION_EXCEPTION", " Failed to verify you've added recipient msg  ",e);
					
		}
		catch (Exception e) {			
			
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
					
		}
		catch (Exception e) {			
			
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
					
		}
		catch (Exception e) {			
			
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
			Thread.sleep(2000); 
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("ENTER_PASSCODE_EXCEPTION", " Failed to enter passcode  ",e);		
		}
		catch (Exception e) {			
			obj_handleexception.throwException("ENTER_PASSCODE_EXCEPTION", " Failed to enter passcode  ",e);
		}
	}

	@Step("Select Oversea Payee.")
	public void SelectOverseaPayee(String valueSelectedFromList) throws Exception {
		try {
			if(DBSappObject.overseaRecipientList().size() >0) {
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
				Asserts.assertTrue(index > 0, "Overseas Payee " +valueSelectedFromList+" not found in the list to initiate the fund transfer");
			}
			else
			{
				Asserts.assertFail("No Receipient found in the Oversea Payee list");
			}
		}  catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Selecting Overseas Payee ",e);		
		}
		catch (Exception e) {			
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Selecting Overseas Payee ",e);
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
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Pressing Enter Key After Entering Amount ",e);		
		}
		catch (Exception e) {			
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Pressing Enter Key After Entering Amount ",e);
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
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On Next Button ",e);		
		}
		catch (Exception e) {			
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click On Next Button ",e);
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
					CommonTestData.REFERENCE_NUMBER_TEXT.getEnumValue() + " Text is not Matching.");
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On Image Expand Button ",e);			
		}
		catch (Exception e) {			
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click On Image Expand Button ",e);
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
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On Share Transfer Details Button ",e);			
		}
		catch (Exception e) {			
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click On Share Transfer Details Button ",e);
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
		}
		catch (Exception e) {				
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click On On Pay and Transfer Button  ",e);
		}
	}

	@Step("Verifying Overseas Module and click")
	public void ClickOnOverseasModule(String expectecText) throws Exception {
		try {
			String actualText = getTexOfElement(DBSappObject.overseasLabel());
			Asserts.assertEquals(actualText, expectecText, "Label Not matching");

			if (actualText.equalsIgnoreCase(expectecText))
				clickOnElement(DBSappObject.Btnlist().get(3));
			
			TakeScreenshot(DBSappObject.OverseasTransferPage());
			Asserts.assertEquals(getTexOfElement(DBSappObject.OverseasTransferPage()).toLowerCase(),
					CommonTestData.OVERSEAS_TRANSFER_PAGEHEADER.getEnumValue().toLowerCase(),
					CommonTestData.OVERSEAS_TRANSFER_PAGEHEADER.getEnumValue() + " Text is not found");

		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click on Overseas Button  ",e);
					
		}
		catch (Exception e) {			
			
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
					
		}
		catch (Exception e) {			
			
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
					
		}
		catch (Exception e) {			
			
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
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click on Next Button. ",e);			
		}
		catch (Exception e) {			
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", "  Failed to Click on Next Button. ",e);
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
				clickOnElement(DBSappObject.confirmButton());
			}
			else if(nextButton.size()>0) {
				gestUtils.scrollUPtoObject("text", "NEXT", DBSappObject.nextButton());
				TakeScreenshot(DBSappObject.nextButton());
				if (getTexOfElement(DBSappObject.nextButton()).equalsIgnoreCase("NEXT"))
					clickOnElement(DBSappObject.nextButton());
			}
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On Next Button  ",e);
					
		}
		catch (Exception e) {			
			
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
					
		}
		catch (Exception e) {			
			
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
					
		}
		catch (Exception e) {			
			
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
					
		}
		catch (Exception e) {			
			
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
					
		}
		catch (Exception e) {			
			
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
					
		}
		catch (Exception e) {			
			
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
					
		}
		catch (Exception e) {			
			
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
					
		}
		catch (Exception e) {			
			
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
					
		}
		catch (Exception e) {			
			
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
					
		}
		catch (Exception e) {			
			
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
					
		}
		catch (Exception e) {			
			
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
					
		}
		catch (Exception e) {			
			
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
					
		}
		catch (Exception e) {			
			
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
					
		}
		catch (Exception e) {			
			
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
					
		}
		catch (Exception e) {			
			
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
					
		}
		catch (Exception e) {			
			
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
					
		}
		catch (Exception e) {			
			
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
					
		}
		catch (Exception e) {			
			
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
			ClickOnMakeAPayment(); 
			verifyPageHeader(CommonTestData.PAY_TO_BILLER_PAGE_HEADER.getEnumValue(), DBSappObject.PageHeader2());
			EnterAmount(DBSappObject.AmountEditableField(), CommonTestData.AMOUNTTO_TRANSFERFUND.getEnumValue());
			ClickOnNextButton();
			TakeScreenshot(DBSappObject.PageHeaderList2().get(0));
			MobileElement element = null;
			element = verifyElementExistInTheList(DBSappObject.PageHeaderList2(),
					CommonTestData.REVIEW_PAYMENT_PAGEHEADER.getEnumValue());
			if (element != null) 
				verifyPageHeader(CommonTestData.REVIEW_PAYMENT_PAGEHEADER.getEnumValue(), element);
			
			ClickOnPayNowButton();
			VerifyPaymentSubmittedMsg();
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("TESTCASE_EXCEPTION", " Failed to Execute Payee Add To Bill Payment ",e);
					
		}
		catch (Exception e) {			
			
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
					
		}
		catch (Exception e) {			
			
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
					
		}
		catch (Exception e) {			
			
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
					
		}
		catch (Exception e) {			
			
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click On Bill Module ",e);
		}
	}

	@Step("Click On Make A Payment Button.")
	public void ClickOnMakeAPayment() throws Exception {
		try {
			TakeScreenshot(DBSappObject.MakeAPaymentButton());
			clickOnElement(DBSappObject.MakeAPaymentButton());
			Thread.sleep(2000); 
			TakeScreenshot(DBSappObject.AmountEditableField());
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On Make A Payment Button ",e);
					
		}
		catch (Exception e) {			
			
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click on Make A Payment Button ",e);
		}
	}

	@Step("Verify Payment Submitted Message & Verifies the 'Log out', "
			+ "'Make Another Transfer' Button, 'Share Payment Details' Button and 'Transferred Amount Value' "
			+ "after transferring the fund")
	public void VerifyPaymentSubmittedMsg() throws Exception {
		try {
			Thread.sleep(2000); 
				MobileElement element = null;
				element = verifyElementExistInTheList(DBSappObject.PageHeaderList2(),
						CommonTestData.PAYMENT_SUBMITTED.getEnumValue());
				if (element != null) 
					verifyPageHeader(CommonTestData.PAYMENT_SUBMITTED.getEnumValue(), element);

				TakeScreenshot(DBSappObject.AmountEditableField());
				Asserts.assertEquals(getTexOfElement(DBSappObject.AmountEditableField()),
						CommonTestData.AMOUNTTO_TRANSFERFUND.getEnumValue() + ".00",
						CommonTestData.AMOUNTTO_TRANSFERFUND.getEnumValue() + " Text is not matching.");

			Asserts.assertTrue(DBSappObject.LOGOUTButton().isDisplayed(), "Log Out Button not found.");

			gestUtils.scrollUPtoObject("text", "MAKE ANOTHER PAYMENT", DBSappObject.MakeAnotherPaymentBtn());
			Asserts.assertTrue(DBSappObject.MakeAnotherPaymentBtn().isDisplayed(),
					"Make Another Transfer Button not found.");
			Asserts.assertTrue(DBSappObject.SharePaymentDetailsButton().isDisplayed(),
					"'Share Payment Details' Button not found.");
			Asserts.assertTrue(DBSappObject.BackIcon().isDisplayed(), "'Back' Button not found.");
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Verify Details After Submit Payment ",e);		
		}
		catch (Exception e) {			
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Verify Details After Submit Payment ",e);
		}
	}
	
	@Step("Click On Pay Now Button.")
	public void ClickOnPayNowButton() throws Exception { 
		try {
			TakeScreenshot(DBSappObject.PayNowButton());
			clickOnElement(DBSappObject.PayNowButton());
			Thread.sleep(2000); 
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On Pay Now Button ",e);			
		}
		catch (Exception e) {			
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
		}
		catch (Exception e) {			
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Verify Billing Organisation Details After Adding Payee ",e);
		}
	}

	@Step("Verifies the Open Account.")
	public void OpenAccount() throws Exception {
		try {
			ClickOnMoreButton();
			EnterPasscodeAndDone();
			ClickOnDepositAccountsAnd2FAAuthenticationDone();
			SelectOpenAccountOption();
			verifyPageHeader(CommonTestData.ACCOUNT_BENIFITS.getEnumValue(), DBSappObject.PageHeaderForOpenAccount());
			ClickOnopenAccountInStepButton();
			EnterMonthlySavingAmount();
			SelectSourceOfFundsForSavings();
			ClickOnNextButton1();
			VerifyWarningMessageAndImportantNotes();
			ClickOnIAcknowledgeButton();
			ReviewOpenAccountApplication();
			ClickOnOpenAccountNowButton();
			VerifyDetailsAfterOpenAccount();
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("TESTCASE_EXCEPTION", " Failed to Exceute Open Account " ,e);			
		}
		catch (Exception e) {			
			obj_handleexception.throwException("TESTCASE_EXCEPTION", " Failed to Exceute Open Account ",e);
		}
	}

	@Step("Select Open Account Option By Clicking And Verify Account Benifits Page Header.")
	public void SelectOpenAccountOption() throws Exception {
		try {
			selectElementFromTheGivenList(DBSappObject.SelectOpenAccountOptionList(),
					CommonTestData.OPEN_ACCOUNT_OPTION.getEnumValue());
			TakeScreenshot(DBSappObject.PageHeaderForOpenAccount()); 
		}  catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Select Open Account Option  ",e);			
		}
		catch (Exception e) {			
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", "  Failed to Select Open Account Option  ",e);
		}
	}

	@Step("Select Source Of Funds For Savings.")
	public void SelectSourceOfFundsForSavings() throws Exception {
		try {
			clickOnElement(DBSappObject.SelectSourceOfFundsForSavingsDropdown());
			selectElementFromTheGivenList(DBSappObject.DepositsAccountName(),
					CommonTestData.SELECT_ACCOUNT.getEnumValue());
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to select source of fund account for savings ",e);			
		}
		catch (Exception e) {			
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", "  Failed to select source of fund account for savings ",e);
		}
	}
	
	@Step("Enter Monthly Savings Amount")
	public void EnterMonthlySavingAmount() throws Exception { 
		try {
			clickOnElement(DBSappObject.EnterMonthlySavingsAmtEditField());
			enterTextInTextbox(DBSappObject.EnterMonthlySavingsAmtEditField(),
					CommonTestData.MONTHLY_SAVING_AMT_BALANCE.getEnumValue());
			driver.hideKeyboard();
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Enter Monthly Savings Amount ",e);			
		}
		catch (Exception e) {			
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", "  Failed to Enter Monthly Savings Amount  ",e);
		}
	}

	@Step("Click On Open Account In 2/3 Step Button.")
	public void ClickOnopenAccountInStepButton() throws Exception {
		try {
			clickOnElement(DBSappObject.StepOpenAccountButton());
			Asserts.assertEquals(getTexOfElement(DBSappObject.PageHeader()), CommonTestData.OPEN_ACCOUNT.getEnumValue(),
					CommonTestData.OPEN_ACCOUNT.getEnumValue() + " Page Header Text is not matching");
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On Open Account In Step 2/3 Button  ",e);			
		}
		catch (Exception e) {			
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", "  Failed to Click On Open Account In Step 2/3 Button  ",e);
		}
	}

	@Step("Click On Deposit Accounts Module And 2FA Authentication Done And Verifies the Open Account Page Header.")
	public void ClickOnDepositAccountsAnd2FAAuthenticationDone() throws Exception {
		try {
			gestUtils.scrollUPtoObject("text", "Deposit Accounts", DBSappObject.DepositAccountsModule());
			clickOnElement(DBSappObject.DepositAccountsModule());
			EnterPasscodeAndDone();
			Asserts.assertEquals(getTexOfElement(DBSappObject.PageHeaderForOpenAccount()),
					CommonTestData.OPEN_ACCOUNT.getEnumValue(),
					CommonTestData.OPEN_ACCOUNT.getEnumValue() + " Page Header Text is not matching");
		}  catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On Deposit Account Module  ",e);			
		}
		catch (Exception e) {			
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click On Deposit Account Module  ",e);
		}
	}

	@Step("Verify Warning Message And Important Notes.")
	public void VerifyWarningMessageAndImportantNotes() throws Exception {
		try {
			Asserts.assertEquals(getTexOfElement(DBSappObject.PageHeader2()),
					CommonTestData.IMPORTANT_NOTES.getEnumValue(),
					CommonTestData.IMPORTANT_NOTES.getEnumValue() + " Text is not matching.");
		}catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Verify Warning Messages ",e);			
		}
		catch (Exception e) {			
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", "  Failed to Verify Warning Messages ",e);
		}
	}

	@Step("Click On Open Account Now Button And Got 'Your account is open and ready to use!' Message.")
	public void ClickOnOpenAccountNowButton() throws Exception {
		try {
			gestUtils.scrollUPtoObject("text", "OPEN ACCOUNT NOW", DBSappObject.OpenAccountNowButton());
			clickOnElement(DBSappObject.OpenAccountNowButton());
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On Open Account Now Button ",e);			
		}
		catch (Exception e) {			
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", "  Failed to Click On Open Account Now Button ",e);
		}
	}
	
	@Step("Verify 'Your account is open and ready to use!' Message After Open Account.")
	public void VerifyDetailsAfterOpenAccount() throws Exception {
		try {
			Thread.sleep(5000); 
				TakeScreenshot(DBSappObject.OpenAcconuntSuccessImageIcon()); 
				Asserts.assertEquals(getTexOfElement(DBSappObject.AccountStatusMessage()),
						CommonTestData.YOUR_ACCOUNT_OPEN_READYTOUSE_MESSAGE.getEnumValue(),
						CommonTestData.YOUR_ACCOUNT_OPEN_READYTOUSE_MESSAGE.getEnumValue()
								+ " Message is not matching.");
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Verify Details After Open Account ",e);			
		}
		catch (Exception e) {			
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", "  Failed to Verify Details After Open Account ",e);
		}
	}

	@Step("Review Open Account Application.")
	public void ReviewOpenAccountApplication() throws Exception {
		try {
			Asserts.assertEquals(getTexOfElement(DBSappObject.PageHeader2()),
					CommonTestData.REVIEW_ACCOUNT_APPLICATION.getEnumValue(),
					CommonTestData.REVIEW_ACCOUNT_APPLICATION.getEnumValue() + " Text is not matched.");
			if (isElementVisible(DBSappObject.YouAreOpeningText())) {
			Asserts.assertTrue(DBSappObject.YouAreOpeningText().isDisplayed(),
					"You Are Opening" + " text not displaying.");
			String a = getTexOfElement(DBSappObject.AccountNameList().get(0));
			System.out.println("0" + a);
			Asserts.assertEquals(getTexOfElement(DBSappObject.AccountNameList().get(0)),
					CommonTestData.OPEN_ACCOUNT_OPTION.getEnumValue(), " Given account is not matched or found.");
			}
			
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Review Open Account Application. ",e);			
		}
		catch (Exception e) {			
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Review Open Account Application. ",e);
		}
	}

	@Step("Click On IAcknowledge Button")
	public void ClickOnIAcknowledgeButton() throws Exception {
		try {
			gestUtils.scrollUPtoObject("text", "I ACKNOWLEDGE", DBSappObject.IACKNOWLEDGEButton());
			clickOnElement(DBSappObject.IACKNOWLEDGEButton());
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On I Acknowledge Button ",e);			
		}
		catch (Exception e) {			
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", "  Failed to Click On I Acknowledge Button ",e);
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
				Asserts.assertFail(debitCardToBeSelected + " not found in the list as list size is 0");
			}
		} catch (HandleException e) {
			obj_handleexception.throwHandleException("ELEMENTNOTINLIST_EXCEPTION",
					" Failed to Select Debit card type:: " + debitCardToBeSelected, e);
		} catch (Exception e) {
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
						Thread.sleep(2000);
						break;
					}
				}
				Asserts.assertTrue(index > 0,
						"No " + elementToBeSelected + " found in the list.");
			}
			else {
				if(androidAlert.isAlertPresent()) {
					System.out.println("Alert title :: "+this.driver.switchTo().alert().getText()); 
					Asserts.assertFail(this.driver.switchTo().alert().getText());
				}	
				Asserts.assertFail(elementToBeSelected + " not found in the list as list size is 0");
			}
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("ELEMENTNOTINLIST_EXCEPTION", " Failed to select element from the list  ",e);	
		}
		catch (Exception e) {			
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
					
		}
		catch (Exception e) {			
			
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
					
		}
		catch (Exception e) {			
			
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
					
		}
		catch (Exception e) {			
			
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
					
		}
		catch (Exception e) {			
			
			obj_handleexception.throwException("DELETEPAYEE_EXCEPTION", " Failed to Execute Delete Payee Local To Other Bank ",e);
		}
	}
	
	@Step("Delete Payee To Remittance.")
	public void DeletePayeeRemittance(String ExpectedRecipientName) throws Exception {
		try {
			ClickOnPayAndTransferBtn();
			EnterPasscodeAndDone();
			ClickOnOverseasModule(CommonTestData.OVERSEAS_ICON.getEnumValue());
			DeletePayee(ExpectedRecipientName);
			ClickOnOverseasScreenClosingButton();
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("DELETEPAYEE_EXCEPTION", " Failed to Execute Delete Payee Remittance ",e);
					
		}
		catch (Exception e) {			
			
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
					
		}
		catch (Exception e) {			
			
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
					
		}
		catch (Exception e) {			
			
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click on Close Button ",e);
		}
	}

	@Step("Delete Payee.")
	public void DeletePayee(String ExpectedRecipientName) throws Exception {
		try {
//			String xpath = "//android.widget.ImageView[contains(@resource-id,':id/tv_expandable_item_selected')]";
//			List<RemoteWebElement> Payeelist = driver.findElements(By.xpath(xpath));
//			int ExpectedTotalPayeeSize = Payeelist.size();
//			if(ExpectedTotalPayeeSize>0) {
//				for (int i = 0; i < ExpectedTotalPayeeSize; i++) {
//					ClickOnDeletePayeeToIcon(i);
//					TakeScreenshot(DBSappObject.payee_details_title_name()); 
//					Asserts.assertEquals(getTexOfElement(DBSappObject.payee_details_title_name()),
//							CommonTestData.RECIPIENT_DETAILS_PAGEHEADER.getEnumValue(),
//							CommonTestData.RECIPIENT_DETAILS_PAGEHEADER.getEnumValue() + " is not matching after adding payee");
//					String RecipientNameXpath =	"//android.widget.TextView[@text='" + ExpectedRecipientName + "']";
//					List<RemoteWebElement> RecipientNameElementList = driver.findElements(By.xpath(RecipientNameXpath));
//					if (RecipientNameElementList.size() > 0) {
//						ClickOnMoreOptionBtnAndDeletePayeeBtn();
//						ClickOnYesBtn();
//
//						for (int innerLoop = 0; innerLoop < 2; innerLoop++) {
//							//Sometimes this alert with message (You may be facing some delays and
//							//we are trying to sort it out now. Sorry for the inconvenience.
//							// Do check back later.) coming. So this Thread.sleep(); added here.
//							
//							Thread.sleep(4000); 
//							String ErrorissueXpath = 	"//android.widget.TextView[@resource-id='android:id/message']";
//							List<RemoteWebElement> list = driver.findElements(By.xpath(ErrorissueXpath));
//							if (list.size() > 0) {
//								if(getTexOfElement(DBSappObject.ErrorMessgeElement()).contains("You may be facing some delays")) {
//									ClickOnOKButton_Alert();
//									ClickOnDeletePayeeToIcon(i);
//									TakeScreenshot(DBSappObject.payee_details_title_name()); 
//									Asserts.assertEquals(getTexOfElement(DBSappObject.payee_details_title_name()),
//											CommonTestData.RECIPIENT_DETAILS_PAGEHEADER.getEnumValue(),
//											CommonTestData.RECIPIENT_DETAILS_PAGEHEADER.getEnumValue() + " is not matching after adding payee");
//									ClickOnMoreOptionBtnAndDeletePayeeBtn();
//									ClickOnYesBtn();
//								} 
//							}
//						}
//						
//						ClickOnOkButtonAfterVerifyingPayeeDeletedMsg(ExpectedRecipientName);
//						//VerifyPayeeSizeAfterDeletePayee(ExpectedTotalPayeeSize);
//						break;
//					} else {
//						ClickOnBackButtonImageView();
//					}
//				}
//			}	
//			
			Dimension windowSize = driver.manage().window().getSize();
			int h = windowSize.getHeight();
			int y1 = (int) (h * 0.2);
			int y2 = (int) (h - y1);
			int x = (int) ((windowSize.getWidth()) / 2);
			String s1 = driver.getPageSource();
			int count = 0;
			int deletePayee =0;
			WaitUtils wait = new WaitUtils(driver);
			wait.ImplicitlyWait();
			
			if (DBSappObject.AllTabOptionsList().size() > 0) {
				while (count == 0 && deletePayee == 0) {
					List<MobileElement> Elementlist = DBSappObject.AllTabOptionsList();
					int length = Elementlist.size();
					String DeleteRecipientList = null;
					
					if (length < 4) {
						for (int i = 0; i < length; i++) {
							DeleteRecipientList = Elementlist.get(i).getText();
							
							if (DeleteRecipientList.contains(ExpectedRecipientName)) {
								ClickOnDeletePayeeToIcon(i);
								verifyPageHeader(CommonTestData.RECIPIENT_DETAILS_PAGEHEADER.getEnumValue(), DBSappObject.payee_details_title_name());
								String RecipientNameXpath =	"//android.widget.TextView[@text='" + ExpectedRecipientName + "']";
								List<RemoteWebElement> RecipientNameElementList = driver.findElements(By.xpath(RecipientNameXpath));
								if (RecipientNameElementList.size() > 0) {
									ClickOnMoreOptionBtnAndDeletePayeeBtn();
									ClickOnYesBtn();
									HandlingErrorPopupInDeletePayee(i);
									ClickOnOkButtonAfterVerifyingPayeeDeletedMsg(ExpectedRecipientName);
									deletePayee++;
									//VerifyPayeeSizeAfterDeletePayee(ExpectedTotalPayeeSize);
									break;	
								} 
								else 
									ClickOnBackButtonImageView();
							}	
						}
						if (deletePayee == 0 && count == 0)
							break;
					}else {
						for (int i = 0; i < length; i++) {
							DeleteRecipientList = Elementlist.get(i).getText();
							if (DeleteRecipientList.contains(ExpectedRecipientName)) {
								ClickOnDeletePayeeToIcon(i);
								verifyPageHeader(CommonTestData.RECIPIENT_DETAILS_PAGEHEADER.getEnumValue(), DBSappObject.payee_details_title_name());
								String RecipientNameXpath =	"//android.widget.TextView[@text='" + ExpectedRecipientName + "']";
								List<RemoteWebElement> RecipientNameElementList = driver.findElements(By.xpath(RecipientNameXpath));
								if (RecipientNameElementList.size() > 0) {
									ClickOnMoreOptionBtnAndDeletePayeeBtn();
									ClickOnYesBtn();
									HandlingErrorPopupInDeletePayee(i);
									ClickOnOkButtonAfterVerifyingPayeeDeletedMsg(ExpectedRecipientName);
									deletePayee++;
									// VerifyPayeeSizeAfterDeletePayee(ExpectedTotalPayeeSize);
									break;	
								}else 
									ClickOnBackButtonImageView();
							}
						}
						
						if (deletePayee == 0) {
							touch.longPress(longPressOptions().withPosition(point(x, y2)).withDuration(ofSeconds(2)))
									.moveTo(element(DBSappObject.RECIPIENTS_TAB())).release().perform();

							String s2 = driver.getPageSource();
							if (s1.equals(s2) != true)
								s1 = s2;
							else
								count = 1;
						} else
							break;	
						
						if (deletePayee == 0 && count==1)
							break;
					}
				}
			}
			}catch (HandleException e) {	
			obj_handleexception.throwHandleException("DELETEPAYEE_EXCEPTION", " Failed to Execute Delete Payee  ",e);		
		}
		catch (Exception e) {			
			obj_handleexception.throwException("DELETEPAYEE_EXCEPTION", " Failed to Execute Delete Payee  ",e);
		}
	}
	
	public void HandlingErrorPopupInDeletePayee(int index) throws Exception{
		try {
			for (int innerLoop = 0; innerLoop < 2; innerLoop++) {
				//Sometimes this alert with message (You may be facing some delays and
				//we are trying to sort it out now. Sorry for the inconvenience.
				// Do check back later.) coming. So this Thread.sleep(); added here.
				
				Thread.sleep(4000); 
				String ErrorissueXpath = 	"//android.widget.TextView[@resource-id='android:id/message']";
				List<RemoteWebElement> list = driver.findElements(By.xpath(ErrorissueXpath));
				if (list.size() > 0) {
					if(getTexOfElement(DBSappObject.ErrorMessgeElement()).contains("You may be facing some delays")) {
						ClickOnOKButton_Alert();
						ClickOnDeletePayeeToIcon(index);
						verifyPageHeader(CommonTestData.RECIPIENT_DETAILS_PAGEHEADER.getEnumValue(), DBSappObject.payee_details_title_name());
						ClickOnMoreOptionBtnAndDeletePayeeBtn();
						ClickOnYesBtn();
					} 
				}
			}
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Handle Error Popup in delete payee.  ",e);		
		}
		catch (Exception e) {			
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Handle Error Popup in delete payee.  ",e);
		}
	}
	

	@Step("Click On OK Button after verifying 'Payee Name deleted' message.")
	public void ClickOnOkButtonAfterVerifyingPayeeDeletedMsg(String ExpectedRecipientName) throws Exception {
		try {
			String payeeName = ExpectedRecipientName + " deleted.";
			System.out.println(payeeName);
			String PayeeNameXpath = "//android.widget.TextView[@text='" + payeeName + "']";
			MobileElement DeletePayeeMessageElement = (MobileElement) driver
					.findElement(By.xpath(PayeeNameXpath));
			if (isElementVisible(DeletePayeeMessageElement)) {
				ClickOnOKButton_Alert();
			}
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On OK Button and delete msg  ",e);		
		}
		catch (Exception e) {			
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click On OK Button and delete msg  ",e);
		}
	}

	@Step("Click On Yes Button after verifying 'Are you sure to delete payee' message.")
	public void ClickOnYesBtn() throws Exception {
		try {
			if (isElementVisible(DBSappObject.AreYouSureToDeleteThisPayeeMessage()))
				clickOnElement(DBSappObject.YesBtn());
			    Thread.sleep(2000); 
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On Yes Button  ",e);		
		}
		catch (Exception e) {			
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
					
		}
		catch (Exception e) {			
			
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
					
		}
		catch (Exception e) {			
			
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
					
		}
		catch (Exception e) {			
			
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
					
		}
		catch (Exception e) {			
			
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click On Next Button and verify Add Recipient Now  ",e);
		}
	}


	@Step("Verify Peek Balance.")
	public void VerifyPeekBalance() throws Exception {
		try {
			Asserts.assertTrue(isElementVisible(DBSappObject.DepositsAccountType()),
					"Deposits Account Type is not displayed on home page after login.");
			if(DBSappObject.DepositsAccountName().size() > 0) {
				String DepositeAccountNameOnDashboard = getAndClickOnDepositeAccountNameFromDashboard();
				EnterPasscodeAndDone();
				String ExpectedAvailableBalanceValue = getAvailableBalance(CommonTestData.AVAILABLE_BALANCE_TITLE.getEnumValue());
				String ExpectedTotalBalanceValue =  getTotalBalance(CommonTestData.TOTAL_BALANCE_TITLE.getEnumValue());
				String ExpectedUserAccountName = GetUserAccountName(DepositeAccountNameOnDashboard);
				String ExpectedUserAccountNumber = GetUserAccountNumber();
				ClickOnToolBarBackIcon();

				ClickOnMoreButton();
				EnterPasscodeAndDone();
				SelectPeekBalanceModule();
				EnablePeekBalanceToggle();
				SelectAccountToEnablePeekBalance(ExpectedUserAccountName);
				
				String SelectedAccountNameWithAccountNumber = ExpectedUserAccountName + " " + ExpectedUserAccountNumber;
				verifySelectedAccountForPeekBalance(CommonTestData.ACCOUNT_FOR_PEEK_BALANCE.getEnumValue(), SelectedAccountNameWithAccountNumber);
				
				ClickOnSaveButton();
				ClickOnOkButtonInPersonalizeYourDevicePopup();
				AcceptDigiBankAlert(CommonTestData.PEEK_BALANCE_DIGIALERT_MSG.getEnumValue());
				
				ClickOnBackButtonImageView();
				clickOnLogoutAndVerify(CommonTestData.LOGOUT.getEnumValue(), CommonTestData.RATE_MESSAGE.getEnumValue());
				ClickOnCloseBtnToClosingTapToStarPage();
				VerifyPeekBalanceEnabilityOnLogInPage(CommonTestData.PEEK_BALANCE_SUBTITLE.getEnumValue());

				// TODO: Code Add for tap and hold on above element and get total amount balance
				TapAndHoldPeekBalance();
				
				// DeRegister/Disable Process to removing peek balance from login page for next run.
				ClickOnLoginButtonAfterEnablePeekBalance();
				ClickOnNOTYouLink();
				ClickOnDeregisterButtonInDigiAlertPopup(CommonTestData.PEEK_BALANCE_DEREGISTER_MESSAGE.getEnumValue());
			}
				else 	
					Asserts.assertFail("Deposite Account Name not showing on the Dashboard Page.");
				
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("TESTCASE_EXCEPTION", " Failed to Peek balance ",e);			
		}
		catch (Exception e) {			
			obj_handleexception.throwException("TESTCASE_EXCEPTION", " Failed to Peek balance  ",e);
		}
	}
	
	@Step("Get And Click On Deposite Account Name From Dashboard.")
	public String getAndClickOnDepositeAccountNameFromDashboard() throws Exception{ 
		try {
			String DepositeAccountNameOnDashboard = DBSappObject.DepositsAccountName().get(0).getText();
			clickOnElement(DBSappObject.DepositsAccountName().get(0));
			return DepositeAccountNameOnDashboard;
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to get And Click On Deposite Account Name From Dashboard ",e);			
		}
		catch (Exception e) {			
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to get And Click On Deposite Account Name From Dashboard ",e);
		}
		return null; 
	}
	
	@Step("Click On Toolbar Back Icon.")
	public void ClickOnToolBarBackIcon() throws Exception {
		try {
			clickOnElement(DBSappObject.ToolbarBackIcon());
		} catch (HandleException e) {
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On Toolbar Back Icon ", e);
		} catch (Exception e) {
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click On Toolbar Back Icon ", e);
		}
	}
	
	@Step("Get User Account Number")
	public String GetUserAccountNumber() throws Exception{
		try {
			String ExpectedUserAccountNumber = DBSappObject.UserAccountNumber().getText();
			return ExpectedUserAccountNumber;
		} catch (Exception e) {			
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to get User Account Number ",e);
		}
		return null; 
	}
	
	@Step("Get User Account name")
	public String GetUserAccountName(String DepositeAccountNameOnDashboard) throws Exception{
		try {
			String ExpectedUserAccountName = DBSappObject.UserAccountName().getText();
			Asserts.assertEquals(getTexOfElement(DBSappObject.UserAccountName()),
					DepositeAccountNameOnDashboard, DepositeAccountNameOnDashboard + " Text is not matching.");
			return ExpectedUserAccountName;
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to get User Account Name ",e);			
		}
		catch (Exception e) {			
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to get User Account Name ",e);
		}
		return null; 
	}
	
	@Step("Get Total Balance")
	public String getTotalBalance(String TotalBalanceTitle) throws Exception{
		try {
			Asserts.assertEquals(getTexOfElement(DBSappObject.AccountTitleList().get(1)),
					TotalBalanceTitle, TotalBalanceTitle + " Text is not matching.");

			String ExpectedTotalBalanceValue = DBSappObject.AccountValueList().get(1).getText();
			return ExpectedTotalBalanceValue;
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to get Total Balance ",e);			
		}
		catch (Exception e) {			
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to get Total Balance  ",e);
		}
		return null; 
	}
	
	@Step("Get Available Balance")
	public String getAvailableBalance(String AvailableBalanceTitle) throws Exception{
		try {
			TakeScreenshot(DBSappObject.UserAccountName());
			Asserts.assertEquals(getTexOfElement(DBSappObject.AccountTitleList().get(0)),
					AvailableBalanceTitle,
					AvailableBalanceTitle + " Text is not matching.");
			String ExpectedAvailableBalanceValue = DBSappObject.AccountValueList().get(0).getText();
			return ExpectedAvailableBalanceValue;
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to get Available Balance ",e);			
		}
		catch (Exception e) {			
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to get Available Balance  ",e);
		}
		return null; 
	}
	
	@Step("Select Peek Balance Module After Search.")
	public void SelectPeekBalanceModule() throws Exception{
		try {
			TakeScreenshot(DBSappObject.ContactSearchfield());
			clickOnElement(DBSappObject.ContactSearchfield());
			TakeScreenshot(DBSappObject.EditTextSearchBox());
			clickOnElement(DBSappObject.EditTextSearchBox());
			enterTextInTextbox(DBSappObject.EditTextSearchBox(), CommonTestData.PEEK_BALANCE.getEnumValue());
			TakeScreenshot(DBSappObject.SelectPeekBalance());
			clickOnElement(DBSappObject.SelectPeekBalance());
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Select Peek balance Module. ",e);			
		}
		catch (Exception e) {			
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Select Peek balance Module.  ",e);
		}
	}
	
	@Step("Enable Peek balance Toggle.")
	public void EnablePeekBalanceToggle() throws Exception{
		try {
			TakeScreenshot(DBSappObject.PeekBalanceToggle());
			clickOnElement(DBSappObject.PeekBalanceToggle());
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Enable Peek balance Toggle. ",e);			
		}
		catch (Exception e) {			
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Enable Peek balance Toggle.  ",e);
		}
	}
	
	@Step("Select Account To Enable Peek Balance.")
	public void SelectAccountToEnablePeekBalance(String ExpectedUserAccountName) throws Exception {
		try {
			TakeScreenshot(DBSappObject.AccountForPeekBalanceDropdown());
			clickOnElement(DBSappObject.AccountForPeekBalanceDropdown());
			String xpath = "//android.widget.TextView[@text='" + ExpectedUserAccountName + "']";
			MobileElement selectAccount = (MobileElement) driver.findElement(By.xpath(xpath));
			TakeScreenshot(selectAccount);
			clickOnElement(selectAccount); 
		} catch (HandleException e) {
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Select Account To Enable Peek Balance ", e);
		} catch (Exception e) {
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Select Account To Enable Peek Balance  ", e);
		}
	}
	
	
	@Step("verify Selected Account For Peek Balance")
	public void verifySelectedAccountForPeekBalance(String AccountTitle, String SelectedAccountNameWithAccountNumber) throws Exception{
		try {
			TakeScreenshot(DBSappObject.SelectedAccountForPeekBalance().get(0));
			Asserts.assertEquals(getTexOfElement(DBSappObject.SelectedAccountForPeekBalance().get(0)),
					AccountTitle, AccountTitle + " Text is not matching.");
			Asserts.assertEquals(getTexOfElement(DBSappObject.SelectedAccountForPeekBalance().get(1)),
					SelectedAccountNameWithAccountNumber, SelectedAccountNameWithAccountNumber + " Text is not matching."); 
		}  catch (HandleException e) {
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to verify Selected Account For Peek Balance ", e);
		} catch (Exception e) {
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to verify Selected Account For Peek Balance ", e);
		}
	}
	
	@Step("Click On Save Button.")
	public void ClickOnSaveButton() throws Exception {
		try {
			TakeScreenshot(DBSappObject.SaveBtn());
			clickOnElement(DBSappObject.SaveBtn());
		} catch (HandleException e) {
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On Save Button ", e);
		} catch (Exception e) {
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click On Save Button  ", e);
		}
	}
	
	@Step("Click On Ok Button After Displaying Personalize Your Device Popup.")
	public void ClickOnOkButtonInPersonalizeYourDevicePopup() throws Exception {
		try {
			if (isElementVisible(DBSappObject.PersonalizeYourDevicePopup())) {
				TakeScreenshot(DBSappObject.PersonalizeYourDevicePopup());
				clickOnElement(DBSappObject.OKBtn_PersonalizeYourDevicePopup());
			}
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On Ok Button After Displaying PersonalizeYourDevicePopup ",e);			
		}
		catch (Exception e) {			
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click On Ok Button After Displaying PersonalizeYourDevicePopup ",e);
		}
	}
	
	@Step("Accept Digi Bank Alert.")
	public void AcceptDigiBankAlert(String alertText) throws Exception{
		try {
			String ErrorMsg = getTexOfElement(DBSappObject.ErrorMessgeElement());
			if (ErrorMsg.contains(alertText)) 
				ClickOnOKButton_Alert();
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("TESTCASE_EXCEPTION", " Failed to Accept Digi Bank Alert ",e);			
		}
		catch (Exception e) {			
			obj_handleexception.throwException("TESTCASE_EXCEPTION", " Failed to Accept Digi Bank Alert  ",e);
		}
	}
	
	@Step("Verify Visibility of Peek Balance on Login Page.")
	public void VerifyPeekBalanceEnabilityOnLogInPage(String PeekBalanceSubtitle) throws Exception {
		try {
			Thread.sleep(10000); 
			TakeScreenshot(DBSappObject.PeekBalanceSubtitle()); 
			Asserts.assertEquals(getTexOfElement(DBSappObject.PeekBalanceSubtitle()),
					PeekBalanceSubtitle, PeekBalanceSubtitle + " Text is not matching.");
		}  catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Verify visibility of Peek Balance On Login page. ",e);			
		}
		catch (Exception e) {			
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Verify visibility of Peek Balance On Login page. ",e);
		}
	}
	
	
	@Step("Tap And Hold to Peek Balance On the Login Page.")
	public void TapAndHoldPeekBalance() throws Exception  {
		try {
			gestUtils.longPressOnAndroidElement(DBSappObject.PeekBalanceSubtitle());
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Tap And Hold On Peek Balance. ",e);			
		}
		catch (Exception e) {			
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Tap And Hold On Peek Balance. ",e);
		}
	}
	
	@Step("Click On Deregister Button.")
	public void ClickOnDeregisterButtonInDigiAlertPopup(String peekBalanceDeregisterMsg) throws Exception {
		try {
			TakeScreenshot(DBSappObject.ErrorMessgeElement());
			String PeekBalance_DeregisterAlertMsg = getTexOfElement(DBSappObject.ErrorMessgeElement());
			System.out.println("PeekBalance_DeregisterAlertMsg:: " + PeekBalance_DeregisterAlertMsg);
			if (peekBalanceDeregisterMsg.contains(PeekBalance_DeregisterAlertMsg)) {
				TakeScreenshot(DBSappObject.PeekbalanceDeregisterButton());
				clickOnElement(DBSappObject.PeekbalanceDeregisterButton());
				Thread.sleep(1000);
			}
		} catch (HandleException e) {
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On Deregister Button ", e);
		} catch (Exception e) {
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click On Deregister Button  ", e);
		}
	}

	@Step("Click On Close Button To Closing Tap To Star Page after logout.")
	public void ClickOnCloseBtnToClosingTapToStarPage() throws Exception {
		try {
			TakeScreenshot(DBSappObject.CloseBtnToClosingTapToStarPage());
			clickOnElement(DBSappObject.CloseBtnToClosingTapToStarPage());
		} catch (HandleException e) {
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On Close Button ", e);
		} catch (Exception e) {
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click On Close Button  ", e);
		}
	}
	
	@Step("Click On LogIn Button.")
	public void ClickOnLoginButtonAfterEnablePeekBalance() throws Exception {
		try {
			TakeScreenshot(DBSappObject.LogInButton());
			clickOnElement(DBSappObject.LogInButton());
		} catch (HandleException e) {
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On LogIn Button ", e);
		} catch (Exception e) {
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click On LogIn Button  ", e);
		}
	}
	
	@Step("Click On Not You Link Button.")
	public void ClickOnNOTYouLink() throws Exception {
		try {
			TakeScreenshot(DBSappObject.NotYouLink());
			clickOnElement(DBSappObject.NotYouLink());
		} catch (HandleException e) {
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On Not You Button ", e);
		} catch (Exception e) {
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click On Not You Button  ", e);
		}
	}

	@Step("Click On More Button.")
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
		}
		catch (Exception e) {			
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click On More Button  ",e);
		}
	}

	@Step("Verify 'You have added a Recipient ' after excecuting Payee Add Remittance Case.")
	public void PayeeAddRemittance() throws Exception {
		try {
			ClickOnPayAndTransferBtn();
			EnterPasscodeAndDone();
			ClickOnOverseasModule(CommonTestData.OVERSEAS_ICON.getEnumValue());
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
					
		}
		catch (Exception e) {			
			
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
					
		}
		catch (Exception e) {			
			
			obj_handleexception.throwException("TESTCASE_EXCEPTION", " Failed to Execute TopUp Paylah  ",e);
		}
	}

	@Step("Enter the text in search and select the corresponding value in the dropdown")
	public void sendDataInCommonSearchBoxAndSelectFromDropDown(String searchBoxData, String valueSelectedFromList) throws Exception {
		try {
			clickOnElementOnEnable(DBSappObject.searchIcon());
			if (isElementEnable(DBSappObject.searchBox()))
				enterTextInTextbox(DBSappObject.searchBox(), searchBoxData);

			if(DBSappObject.searchTextElement().size() > 0) {
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
			}else {
				if(androidAlert.isAlertPresent()) {
					System.out.println("Alert title :: "+this.driver.switchTo().alert().getText()); 
					Asserts.assertFail(this.driver.switchTo().alert().getText());
				}	
				Asserts.assertFail(valueSelectedFromList + " not found in the list as list size is 0");
			}
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Select Data From Dropdown.  ",e);			
		}
		catch (Exception e) {			
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Select Data From Dropdown.  ",e);
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
					
		}
		catch (Exception e) {			
			
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
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On Other Bank Limit " ,e);	
		}
		catch (Exception e) {			
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click On Other Bank Limit ",e);
		}
	}

	@Step("Click on 'Account type' From List under Local fund Limit page'")
	public void selectAccountTypeInLocalFundTransfer(String AccountToBeSelected) throws Exception {
		try {
			if (DBSappObject.localTransferLimitAccountList().size() > 0) {
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

				Asserts.assertTrue(index > 0,
						"No " + AccountToBeSelected + " found in the list of corresponding value");

			}else {
				if(androidAlert.isAlertPresent()) {
					System.out.println("Alert title :: "+this.driver.switchTo().alert().getText()); 
					Asserts.assertFail(this.driver.switchTo().alert().getText());
				}	
				Asserts.assertFail(AccountToBeSelected + " not found in the list as list size is 0");
			}
			
		}catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Select Account Type " ,e);	
		}
		catch (Exception e) {			
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Select Account Type ",e);
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
			} else if(arrOfStr[1].equalsIgnoreCase(CommonTestData.SELECTED_LIMIT_50000.getEnumValue())){
				gestUtils.scrollDOWNtoObject("text", "500.00",null);
				ChangeLocalFundsTransferLimitReset();
				currentText = getTexOfElement(DBSappObject.currentLimitTextButton());
				verifyClickSetCurrentLimit();
				arrOfStr = currentText.split(" ");
				if (arrOfStr[1].equalsIgnoreCase(CommonTestData.SELECTED_LIMIT_500.getEnumValue()))
					selectedValue = selectAmountFromSetCurrentLimitList(AmountToBeselected);
			}
			else
				selectedValue = selectAmountFromSetCurrentLimitList(AmountToBeselected);
			return selectedValue;
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Set Current Limit " ,e);	
		}
		catch (Exception e) {			
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Set Current Limit ",e);
		}
		return null; 
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
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Set Amount from Current Limit " ,e);	
		}
		catch (Exception e) {			
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Set Amount from Current Limit ",e);
		}
		return null; 
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
		}catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On Change Daily Limit Now Button  ",e);		
		}
		catch (Exception e) {				
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click On Change Daily Limit Now Button  ",e);
		}
	}

	@Step("Verifying and click 'BACK TO MORE' BUTTON ")
	public void ClickOnBackToMoreButton() throws Exception {
		try {
			TakeScreenshot(DBSappObject.successTitleLabel());
			clickOnElementOnEnable(DBSappObject.backToMoreButton());
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On Back To More Button  ",e);		
		}
		catch (Exception e) {				
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click On Back To More Button  ",e);
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
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Verify Display Amount Local Transfer Limit Change ",e);		
		}
		catch (Exception e) {				
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Verify Display Amount Local Transfer Limit Change ",e);
		}
	}

	@Step("Change local fund transfer limit verification")
	public void ChangeLocalFundsTransferLimit() throws Exception {
		try {
			ClickOnMoreButton();
			EnterPasscodeAndDone();
			sendDataInCommonSearchBoxAndSelectFromDropDown(CommonTestData.LOCAL_TRANSFER_LIMIT_LABEL.getEnumValue(),
					CommonTestData.LOCAL_TRANSFER_LIMIT_LABEL.getEnumValue());
			EnterPasscodeAndDone();
			verifyPageHeader(CommonTestData.LOCAL_TRANSFER_LIMIT_LABEL.getEnumValue(), DBSappObject.PageHeader2());
			ClickOnToOtherBankLimit();
			String amountSlected = handlingSetCurrentLimit(CommonTestData.SELECTED_LIMIT_50000.getEnumValue());
			ClickOnNextButton();
			verifyClickChangeDailyLimitNowButton();
			ClickOnBackToMoreButton();
			sendDataInCommonSearchBoxAndSelectFromDropDown(CommonTestData.LOCAL_TRANSFER_LIMIT_LABEL.getEnumValue(),
					CommonTestData.LOCAL_TRANSFER_LIMIT_LABEL.getEnumValue());
			EnterPasscodeAndDone();
			verifyPageHeader(CommonTestData.LOCAL_TRANSFER_LIMIT_LABEL.getEnumValue(), DBSappObject.PageHeader2());
			ClickOnToOtherBankLimit();
			verifyDisplayAmountLocalTransferLimitChange(amountSlected);
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("TESTCASE_EXCEPTION", " Failed to Exceute Change Local Funds Transfer Limit " ,e);			
		}
		catch (Exception e) {			
			obj_handleexception.throwException("TESTCASE_EXCEPTION", " Failed to Exceute Change Local Funds Transfer Limit ",e);
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
		
		    Dimension windowSize = driver.manage().window().getSize();
			System.out.println("getSessionId :"+driver.getSessionId());
			
			int h = windowSize.getHeight();
			int y1 = (int) (h * 0.2);
			int y2 = (int) (h - y1);
			int x = (int) ((windowSize.getWidth()) / 2);
			
			String s1 = driver.getPageSource();
			int count = 0;
			int index = 0;
			WaitUtils wait = new WaitUtils(driver);
			wait.ImplicitlyWait();
			while (count == 0 && index == 0) {
				if (DBSappObject.SubTitleTextList().size() > 0) {
					TakeScreenshot(DBSappObject.SubTitleTextList().get(0));
					List<MobileElement> Elementlist = DBSappObject.SubTitleTextList();
					List<MobileElement> ElementlistClickable = DBSappObject.ListElementToClickable();
					int length = Elementlist.size();
					String LocalRecipientList = null;
					if (length < 2) {
						for (int i = 0; i < length; i++) {
							LocalRecipientList = Elementlist.get(i).getText();
							if (LocalRecipientList.equalsIgnoreCase(valueSelectedFromList)) {
								index++;
								clickOnElement(ElementlistClickable.get(i));
								break;
							}
						}
						// Exception Handling without scrolling case and no expected element found in
						// the list then index ==0
						if (index == 0 && count == 0)
							Asserts.assertFail("Local Recipient " + valueSelectedFromList
									+ " not found in the list to initiate the fund transfer");
						else
							break;
					} else

						// Code will work :: When Need to scroll
						for (int i = 0; i < length; i++) {
							LocalRecipientList = Elementlist.get(i).getText();
							if (LocalRecipientList.equalsIgnoreCase(valueSelectedFromList)) {
								index++;
								clickOnElement(ElementlistClickable.get(i));
								break;
							}
						}
					if (index == 0) {
						touch.longPress(longPressOptions().withPosition(point(x, y2)).withDuration(ofSeconds(2)))
								.moveTo(element(DBSappObject.AllTab())).release().perform();

						String s2 = driver.getPageSource();
						if (s1.equals(s2) != true)
							s1 = s2;
						else
							count = 1;
					} else
						break;

					// Exception Handling in scrolling case and no expected element found in the
					// list then index ==0, count ==1
					if (count == 1 && index == 0)
						Asserts.assertFail("Local Recipient " + valueSelectedFromList
								+ " not found in the list to initiate the fund transfer");

				} else
					Asserts.assertFail("No receipient Found in the Local recipient list");
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
			String xpath = "//android.widget.TextView[@text='Select Fund Source']";
			List<RemoteWebElement> list = driver.findElements(By.xpath(xpath));
			if (list.size() > 0) {
				VerifyButtonLabelAndClick(DBSappObject.selectFundSourceTextButton(),
						CommonTestData.SELECT_SOURCE_FUND.getEnumValue());
				TakeScreenshot(DBSappObject.selectFundSourceListAccountName2().get(0));
				List<MobileElement> AccountName = DBSappObject.selectFundSourceListAccountName2();
				
				int l = AccountName.size();
				int index = 0;
				String AccountNameList = null;
				for (int i = 0; i < l; i++) {
					AccountNameList = AccountName.get(i).getText();
					if (AccountNameList.equalsIgnoreCase(SelectedAccountName)) {
						index++;
						clickOnElement(AccountName.get(i));
						break;
					}
				}
			}

			String xpath1 = "//android.widget.TextView[contains(@resource-id,'id/tv_primary_account_bottom_sheet_title')]";
			List<RemoteWebElement> list1 = driver.findElements(By.xpath(xpath1));
			if (list1.size() > 0) {
				androidAlert.AlertHandlingWithButtonMessage(DBSappObject.Alert_OKButton(),
						CommonTestData.PRIMARY_SOURCE_ALERT_TITLE.getEnumValue(),
						DBSappObject.primarysourceAlertTitle());
				verifyPageHeader(CommonTestData.OVERSEA_HEADER.getEnumValue(), DBSappObject.overseasTransferHeader());
			}
			
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Selecting Overseas Payee ",e);		
		}
		catch (Exception e) {			
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Selecting Overseas Payee ",e);
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
			Thread.sleep(4000); 
			TakeScreenshot(DBSappObject.TransferNowBtn());
			verifyPageHeader(CommonTestData.REVIEW_TRANSFER_LABEL.getEnumValue(), DBSappObject.PageHeader());
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
			Thread.sleep(4000); 
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
	public void FundTransferDBSPOSB() throws Exception {
		try {
			ClickOnPayAndTransferBtn();
			EnterPasscodeAndDone();
			SelectAllTAB();
			selectOptionFromAllTabSection(CommonTestData.LOCAL_RECIPIENT_FROMLIST.getEnumValue());
			clickingOnAccountTypeInLocalRecipient_1(
					CommonTestData.LOCAL_RECIPIENT_LIST_SELECTED_ACCOUNTNAME.getEnumValue());
			selectFundSourceAndSelectAccount(CommonTestData.SOURCE_ACCOUNT_NAME.getEnumValue(),
					CommonTestData.SOURCE_ACCOUNT_NUMBER.getEnumValue());
			enterAmountAndVerifySgdCurrency(CommonTestData.AMOUNT_FUNDTRANSFER.getEnumValue());
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
			gestUtils.scrollUPtoObject("text", "MAKE ANOTHER TRANSFER", DBSappObject.MakeAnotherTransferBtn()); 
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
			
			Dimension windowSize = driver.manage().window().getSize();
			int h = windowSize.getHeight();
			int y1 = (int) (h * 0.2);
			int y2 = (int) (h - y1);
			int x = (int) ((windowSize.getWidth()) / 2);
			String s1 = driver.getPageSource();
			int count = 0;
			int index = 0;
			WaitUtils wait = new WaitUtils(driver);
			wait.ImplicitlyWait();
			while (count == 0 && index == 0) {
				if (DBSappObject.SubTitleTextList().size() > 0) {
					TakeScreenshot(DBSappObject.SubTitleTextList().get(0));
					List<MobileElement> Elementlist = DBSappObject.SubTitleTextList();
					List<MobileElement> ElementlistClickable = DBSappObject.ListElementToClickable();
					int length = Elementlist.size();
					String LocalRecipientList = null;
					if (length < 2) {
						for (int i = 0; i < length; i++) {
							LocalRecipientList = Elementlist.get(i).getText();
							if (LocalRecipientList.equalsIgnoreCase(valueSelectedFromList)) {
								index++;
								clickOnElement(ElementlistClickable.get(i));
								break;
							}
						}
						// Exception Handling without scrolling case and no expected element found in
						// the list then index ==0
						if (index == 0 && count == 0)
							Asserts.assertFail("Your DBS/POSB Accounts " +valueSelectedFromList+" not found in the list to initiate the fund transfer");
						else
							break;
					} else

						// Code will work :: When Need to scroll
						for (int i = 0; i < length; i++) {
							LocalRecipientList = Elementlist.get(i).getText();
							if (LocalRecipientList.equalsIgnoreCase(valueSelectedFromList)) {
								index++;
								clickOnElement(ElementlistClickable.get(i));
								break;
							}
						}
					if (index == 0) {
						touch.longPress(longPressOptions().withPosition(point(x, y2)).withDuration(ofSeconds(2)))
								.moveTo(element(DBSappObject.AllTab())).release().perform();

						String s2 = driver.getPageSource();
						if (s1.equals(s2) != true)
							s1 = s2;
						else
							count = 1;
					} else
						break;

					// Exception Handling in scrolling case and no expected element found in the
					// list then index ==0, count ==1
					if (count == 1 && index == 0)
						Asserts.assertFail("Your DBS/POSB Accounts " +valueSelectedFromList+" not found in the list to initiate the fund transfer");

				} else
					Asserts.assertFail("No receipient found in the Your DBS/POSB Own Account List.");
			}
			Thread.sleep(2000);
			TakeScreenshot(DBSappObject.PageHeader());
			verifyPageHeader(CommonTestData.TRANSFER_TO_YOUR_ACCOUNT.getEnumValue(), DBSappObject.PageHeader());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Step("Select Any Fund Source Account After clicking on add sign for select fund source.")
	public void SelectFundSourceAccount(String fromAccount) throws Exception {
		try {
			String xpath = "//android.widget.TextView[@text='Select Fund Source']";
			List<RemoteWebElement> list = driver.findElements(By.xpath(xpath));
			if (list.size() > 0) {
				gestUtils.scrollDOWNtoObject("text", "Select Fund Source", DBSappObject.SelectFundSourcePage());
				TakeScreenshot(DBSappObject.SelectFundSourcePage());
				clickOnElement(DBSappObject.SelectFundSourcePage());
				TakeScreenshot(DBSappObject.SelectLocalRecipientToAccount().get(0));
                int selectedAccount =0;
				for (int i = 0; i < DBSappObject.SelectLocalRecipientToAccount().size(); i++) {
					String actualfromOwnAccount = DBSappObject.SelectLocalRecipientToAccount().get(i).getText();
					if (actualfromOwnAccount.contains(fromAccount)) {
						selectedAccount++;
						clickOnElement(DBSappObject.SelectLocalRecipientToAccount().get(i));
						break;
					}
				}
				
				if(selectedAccount == 0)
				Asserts.assertFail("Select Fund Source " +fromAccount+" not found in the list to initiate the fund transfer");
				
				String xpath1 = "//android.widget.TextView[@text='Primary source of fund']";
				List<RemoteWebElement> list1 = driver.findElements(By.xpath(xpath1));
				if (list1.size() > 0) {
					TakeScreenshot(DBSappObject.PrimarySourceOfFund());
					if (isElementVisible(DBSappObject.PrimarySourceOfFund()))
						clickOnElement(DBSappObject.Alert_OKButton());
				}
			}else
				Asserts.assertFail("No Account found in the Select Fund Source Account List.");
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
					
		}
		catch (Exception e) {			
			
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
			EnterCommentForRecipientInEditField(CommonTestData.COMMENT_NONFAST_TRANSFER.getEnumValue(),  DBSappObject.EditFields().get(0));
            //Add Scroll to select fund source on the top of the page after disabling the fast toggle.
			gestUtils.scrollDOWNtoObject(null, null, null);
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
			
			String ExpectedFromBankName = CommonTestData.FUNDTRANSFER_NONFAST_FROM_ACCOUNT_NAME.getEnumValue();
			SelectFundSourceAccount(ExpectedFromBankName);

			SelectFutureDateThroughCalendar();
			EnterAmount(DBSappObject.AmountEditableField(), CommonTestData.AMOUNTTO_TRANSFERFUND.getEnumValue());
			EnterCommentForRecipientInEditField(CommonTestData.COMMENT_FAST_TRANSFER.getEnumValue(),  DBSappObject.EditFields().get(0));
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
			EnterCommentForRecipientInEditField(CommonTestData.COMMENT_NONFAST_TRANSFER.getEnumValue(),  DBSappObject.EditFields().get(0));
            //Add Scroll to select fund source on the top of the page after disabling the fast toggle.
			gestUtils.scrollDOWNtoObject(null, null, null);
			String ExpectedFromBankName = CommonTestData.FUNDTRANSFER_NONFAST_FROM_ACCOUNT_NAME.getEnumValue();
			SelectFundSourceAccount(ExpectedFromBankName);
			VerifyImmediateText(CommonTestData.IMMEDIATE_TEXT.getEnumValue());
		
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
	
	@Step("Verify Immediate text")
	public void VerifyImmediateText(String ActualSelectedDate)throws Exception {
		try {
			String ExpectedSelectedDate = getTexOfElement(DBSappObject.TransferDateTextElement());
			Asserts.assertEquals(ActualSelectedDate, ExpectedSelectedDate, "Immediate text is not Matching in date section.");
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("TESTCASE_EXCEPTION", " Failed to Verify Immediate Text ",e);
		}
		catch (Exception e) {		
			obj_handleexception.throwException("TESTCASE_EXCEPTION", "Failed to Verify Immediate Text. ",e);
		}
	}
	
	@Step("Enter Comment for Recipient In Edit Field")
	public void EnterCommentForRecipientInEditField(String Comment, MobileElement EditFieldElement)throws Exception {
		try {
			TakeScreenshot(EditFieldElement);
			clickOnElement(EditFieldElement);
			enterTextInTextbox(EditFieldElement, Comment);
			driver.hideKeyboard();
			Thread.sleep(2000); 
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("TESTCASE_EXCEPTION", " Failed to Enter comments in edit field. ",e);
		}
		catch (Exception e) {		
			obj_handleexception.throwException("TESTCASE_EXCEPTION", "Failed to Enter comments in edit field. ",e);
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
			
			String ExpectedFromBankName = CommonTestData.FUNDTRANSFER_NONFAST_FROM_ACCOUNT_NAME.getEnumValue();
			SelectFundSourceAccount(ExpectedFromBankName);
			VerifyImmediateText(CommonTestData.IMMEDIATE_TEXT.getEnumValue());
	
			EnterAmount(DBSappObject.AmountEditableField(), CommonTestData.AMOUNTTO_TRANSFERFUND.getEnumValue());
			EnterCommentForRecipientInEditField(CommonTestData.COMMENT_FAST_TRANSFER.getEnumValue(),  DBSappObject.EditFields().get(0));
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
			//if (appName.contains("DBS")) {
				verifyPageHeader(CommonTestData.TRANSCETION_HISTORY_LABEL.getEnumValue(),
						DBSappObject.TransactionHistoryHeaderForDBS());
				TakeScreenshot(DBSappObject.TransactionHistoryHeaderForDBS());
//			} else if (appName.contains("POSB")) {
//				verifyPageHeader(CommonTestData.TRANSCETION_HISTORY_LABEL.getEnumValue(),
//						DBSappObject.TransactionHistoryHeaderForPOSB());
//				TakeScreenshot(DBSappObject.TransactionHistoryHeaderForPOSB());
//			} else if (appName.contains("iWEALTH")) {
//				verifyPageHeader(CommonTestData.TRANSCETION_HISTORY_LABEL.getEnumValue(),
//						DBSappObject.TransactionHistoryHeaderForiWEALTH());
//				TakeScreenshot(DBSappObject.TransactionHistoryHeaderForiWEALTH());
//			}
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
			//if (appName.contains("DBS"))
				clickOnElement(DBSappObject.DepositAccountButtonDBS());

//			else if (appName.contains("POSB"))
//				clickOnElement(DBSappObject.DepositAccountButtonPOSB());
//
//			else if (appName.contains("iWEALTH"))
//				clickOnElement(DBSappObject.DepositAccountButtoniWEALTH());

			selectAccountTypeInTransactionHistory(AccountName, appName);

			//if (appName.contains("DBS")) {
				verifyPageHeader(CommonTestData.TRANSCETION_HISTORY_LABEL.getEnumValue(),
						DBSappObject.TransactionHistoryHeaderForDBS());
				TakeScreenshot(DBSappObject.TransactionHistoryHeaderForDBS());
//			} else if (appName.contains("POSB")) {
//				verifyPageHeader(CommonTestData.TRANSCETION_HISTORY_LABEL.getEnumValue(),
//						DBSappObject.TransactionHistoryHeaderForPOSB());
//				TakeScreenshot(DBSappObject.TransactionHistoryHeaderForPOSB());
//			} else if (appName.contains("iWEALTH")) {
//				verifyPageHeader(CommonTestData.TRANSCETION_HISTORY_LABEL.getEnumValue(),
//						DBSappObject.TransactionHistoryHeaderForiWEALTH());
//				TakeScreenshot(DBSappObject.TransactionHistoryHeaderForiWEALTH());
//			}
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
			List<MobileElement> Elementlist = new ArrayList<MobileElement>();
			//if (appName.contains("DBS")) {
			if(DBSappObject.AccountNameListInTransactionHistoryForDBS().size() > 0) {
				//wait.waitForElementVisibility(DBSappObject.AccountNameListInTransactionHistoryForDBS().get(0));
				Elementlist = DBSappObject.AccountNameListInTransactionHistoryForDBS();
//			} else if (appName.contains("POSB")) {
//				wait.waitForElementVisibility(DBSappObject.AccountNameListInTransactionHistoryForPOSB().get(1));
//				Elementlist = DBSappObject.AccountNameListInTransactionHistoryForPOSB();
//			} else if (appName.contains("iWEALTH")) {
//				wait.waitForElementVisibility(DBSappObject.AccountNameListInTransactionHistoryForiWEALTH().get(1));
//				Elementlist = DBSappObject.AccountNameListInTransactionHistoryForiWEALTH();
//			}
			int l = Elementlist.size();
			int index = 0;
			String accountFromList = null;
			for (int i = 0; i < l; i++) {
				accountFromList = Elementlist.get(i).getText();
				if (accountFromList.contains(AccountToBeSelected)) {
					index++;
					clickOnElement(Elementlist.get(i));
					break;
				}
			}

			Asserts.assertTrue(index > 0, "No " + AccountToBeSelected + " found in the list of corresponding value");
			}
				
			else {
				Asserts.assertFail("No Account Type found as the list size is zero");
			}
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
			//if (appName.contains("DBS"))
				verifyPageHeader(CommonTestData.TRANSCETION_HISTORY_LABEL.getEnumValue(),
						DBSappObject.TransactionHistoryHeaderForDBS());
//			else if (appName.contains("POSB"))
//				verifyPageHeader(CommonTestData.TRANSCETION_HISTORY_LABEL.getEnumValue(),
//						DBSappObject.TransactionHistoryHeaderForPOSB());
//			else if (appName.contains("POSB"))
//				verifyPageHeader(CommonTestData.TRANSCETION_HISTORY_LABEL.getEnumValue(),
//						DBSappObject.TransactionHistoryHeaderForiWEALTH());
			
			//Leaving On Home Page for next run.
			ClickOnBackButton();
			ClickOnHomeButton();
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On Back To Home Button  ",e);			
		}
		catch (Exception e) {			
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click On Back To Home Button  ",e);
		}
	}
	
	@Step("Click On Home Button.")
	public void ClickOnHomeButton() throws Exception {
		try {
			TakeScreenshot(DBSappObject.homeButton());
			clickOnElement(DBSappObject.homeButton()); 
			TakeScreenshot(DBSappObject.WelcomeToText()); 
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On Home Button  ",e);		
		}
		catch (Exception e) {			
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click On Home Button  ",e);
		}
	}
	
	@Step("Click On Back Button.")
	public void ClickOnBackButton() throws Exception {
		try {
			TakeScreenshot(DBSappObject.backButton());
			clickOnElement(DBSappObject.backButton()); 
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On Back Button  ",e);			
		}
		catch (Exception e) {			
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click On Back Button  ",e);
		}
	}
	
	@Step("Click On Back Button.")
	public void ClickOnBackIcon() throws Exception {
		try {
			TakeScreenshot(DBSappObject.BackIcon());
			clickOnElement(DBSappObject.BackIcon());
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On Back Icon  ",e);		
		}
		catch (Exception e) {			
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click On Back Icon  ",e);
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

	@Step("Verify Transaction History.")
	public void transactionHistoryVerify(String appName) throws Exception {
		try {
			ClickOnMoreButton();
			EnterPasscodeAndDone();
			ClickOnTransactionHistory(appName);
			SelectTimeAndAccountTypeForStatement(appName, CommonTestData.ACCOUNT_NAME.getEnumValue());
			ClickOnShowButtonAndVerifyHeader(CommonTestData.STATEMENT_TITLE.getEnumValue());
			ValadateTransactionHistoryListInThreeMonth();
			BackToHomeFromTransactionHistory(appName);
		}catch (HandleException e) {	
			obj_handleexception.throwHandleException("TESTCASE_EXCEPTION", " Failed to verify transaction history ",e);
		}
		catch (Exception e) {		
			obj_handleexception.throwException("TESTCASE_EXCEPTION", " Failed to verify transaction history ",e);
		}
	}

	@Step("Select Future Date Through Calendar and verifies the selected 'future date'.")
	public void SelectFutureDateThroughCalendar() throws Exception {
		try {
			clickOnElement(DBSappObject.TransferDateTextElement());
			TakeScreenshot(DBSappObject.Alert_OKButton());
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
			ClickOnOKButton_Alert();
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
			clickOnElement(DBSappObject.OKButton());
			TakeScreenshot(DBSappObject.PageHeader());
			clickOnElement(DBSappObject.durationOption());
			TakeScreenshot(DBSappObject.calenderHeaderCreditLimit());
			clickOnElement(DBSappObject.selectDateOctEleven());
			clickOnElement(DBSappObject.OKButton());
			TakeScreenshot(DBSappObject.PageHeader());
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Set Amount Duration Purpose For Limit Increase  ",e);
		}
		catch (Exception e) {		
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Set Amount Duration Purpose For Limit Increase ",e);
		}
	}
	
	
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
			Dimension windowSize = driver.manage().window().getSize();

			int h = windowSize.getHeight();
			int y1 = (int) (h * 0.2);
			int y2 = (int) (h - y1);
			int x = (int) ((windowSize.getWidth()) / 2);

			String s1 = driver.getPageSource();
			int count = 0;
			int index = 0;
			WaitUtils wait = new WaitUtils(driver);
			wait.ImplicitlyWait();
			while (count == 0 && index == 0) {
				if (DBSappObject.SubTitleTextList().size() > 0) {
					TakeScreenshot(DBSappObject.SubTitleTextList().get(0));
					List<MobileElement> Elementlist = DBSappObject.SubTitleTextList();
					List<MobileElement> ElementlistClickable = DBSappObject.ListElementToClickable();
					int length = Elementlist.size();
					String LocalRecipientList = null;
					if (length < 2) {
						for (int i = 0; i < length; i++) {
							LocalRecipientList = Elementlist.get(i).getText();
							if (LocalRecipientList.equalsIgnoreCase(valueSelectedFromList)) {
								index++;
								clickOnElement(ElementlistClickable.get(i));
								break;
							}
						}
						// Exception Handling without scrolling case and no expected element found in
						// the list then index ==0
						if (index == 0 && count == 0)
							Asserts.assertFail("Local Recipient " + valueSelectedFromList
									+ " not found in the list to initiate the fund transfer");
						else
							break;
					} else

						// Code will work :: When Need to scroll
						for (int i = 0; i < length; i++) {
							LocalRecipientList = Elementlist.get(i).getText();
							if (LocalRecipientList.equalsIgnoreCase(valueSelectedFromList)) {
								index++;
								clickOnElement(ElementlistClickable.get(i));
								break;
							}
						}
					if (index == 0) {
						touch.longPress(longPressOptions().withPosition(point(x, y2)).withDuration(ofSeconds(2)))
								.moveTo(element(DBSappObject.AllTab())).release().perform();

						String s2 = driver.getPageSource();
						if (s1.equals(s2) != true)
							s1 = s2;
						else
							count = 1;
					} else
						break;

					// Exception Handling in scrolling case and no expected element found in the
					// list then index ==0, count ==1
					if (count == 1 && index == 0)
						Asserts.assertFail("Local Recipient " + valueSelectedFromList
								+ " not found in the list to initiate the fund transfer");

				} else
					Asserts.assertFail("No receipient Found in the Local Recipient list");
			}

			Thread.sleep(2000);
			String xpath1 = "//android.widget.TextView[@text='Primary source of fund']";
			List<RemoteWebElement> list1 = driver.findElements(By.xpath(xpath1));
			if (list1.size() > 0) {
				TakeScreenshot(DBSappObject.PrimarySourceOfFund());
				if (isElementVisible(DBSappObject.PrimarySourceOfFund()))
					clickOnElement(DBSappObject.Alert_OKButton());
			}
		} catch (HandleException e) {
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION",
					" Failed to select account from local recipient and verify header  ", e);
		} catch (Exception e) {
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION",
					" Failed to select account from local recipient and verify header  ", e);
		}
	}

	@Step("Update Personal Details")
	public void UpdatePersonalDetails(String appName) throws Exception {
		try {
			ClickOnMoreButton();
			EnterPasscodeAndDone();
			SelectUpdateContactDetails();
			TakeScreenshot(DBSappObject.BackBtnImageView());
			verifyPageHeader(CommonTestData.UPDATE_CONTACT_DETAILS_PAGEHEADER.getEnumValue(), DBSappObject.PageHeaderForOpenAccount());
			VerifySomeTabVisibiltyOnUpdateContactDetailsPage();
			SelectPersonalContactDetailsTab();
			EnterPasscodeAndDone();
			VerifyPersonalDetailsPage(appName);
			ClickOnCheckboxes();
			ClickOnNextButton();
			ClickOnConfirmButton(appName);
			
			//Verify Final Result after go through on Personal Details Page.
			ClickOnBackToMoreServicesBtn();
			SelectUpdateContactDetails();
			TakeScreenshot(DBSappObject.BackBtnImageView());
			verifyPageHeader(CommonTestData.UPDATE_CONTACT_DETAILS_PAGEHEADER.getEnumValue(), DBSappObject.PageHeaderForOpenAccount());
			VerifySomeTabVisibiltyOnUpdateContactDetailsPage();
			SelectPersonalContactDetailsTab();
			EnterPasscodeAndDone();
			VerifyPersonalDetailsPage(appName);
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
			TakeScreenshot(DBSappObject.BACKTOMoreServicesBtn()); 
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
	public void ClickOnConfirmButton(String appName) throws Exception {
		try {
			gestUtils.scrollUPtoObject(null, null, null);
			TakeScreenshot(DBSappObject.ConfirmBtn());
			clickOnElement(DBSappObject.ConfirmBtn());
			EnterPasscodeAndDone();
			if (isElementVisible(DBSappObject.CompletionStatusImage())) {
				TakeScreenshot(DBSappObject.SuccessfullyUpdatedMessageEle());
				if(appName.equalsIgnoreCase("DBS")) {
					Asserts.assertEquals(getTexOfElement(DBSappObject.SuccessfullyUpdatedMessageEle()),
							CommonTestData.SUCCESSFULLY_UPDATED_MESSAGE.getEnumValue(),
							CommonTestData.SUCCESSFULLY_UPDATED_MESSAGE.getEnumValue() + " Text is not matching");
					gestUtils.scrollUPtoObject(null, null, null);
					TakeScreenshot(DBSappObject.UpdateMoreDetailsBtn());
					Asserts.assertTrue(isElementVisible(DBSappObject.UpdateMoreDetailsBtn()),
							"Update More Details Btn is not displayed.");
				}
				else if(appName.equalsIgnoreCase("iWEALTH")) {
					Asserts.assertEquals(getTexOfElement(DBSappObject.SuccessfullyUpdatedMessageEle()),
							CommonTestData.REQUESTS_SUBMITTED_MESSAGE.getEnumValue(),
							CommonTestData.REQUESTS_SUBMITTED_MESSAGE.getEnumValue() + " Text is not matching");
					gestUtils.scrollUPtoObject(null, null, null);
					TakeScreenshot(DBSappObject.UpdateMailingAddressBtn());
					Asserts.assertTrue(isElementVisible(DBSappObject.UpdateMailingAddressBtn()),
							"Update Mailing Address Btn is not displayed.");
				}
				Asserts.assertTrue(isElementVisible(DBSappObject.BACKTOMoreServicesBtn()),
						"BACK TO More Services Btn is not displayed.");
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

	@Step("Verify visibility of 'Personal & Contact Details' Page Header, 'Contact Details' &  'Personal Perticulars' Section.")
	public void VerifyPersonalDetailsPage(String appName) throws Exception {
		try {
			Thread.sleep(4000); 
			TakeScreenshot(DBSappObject.ContactDetailsTitle()); 
			System.out.println("text on screen:: "+ getTexOfElement(DBSappObject.UpdateContactDetailsPageHeader()) + " text from test data");
			
			if(getTexOfElement(DBSappObject.UpdateContactDetailsPageHeader()).equals(CommonTestData.PERSONAL_AND_CONTACTDETAILS_PAGEHEADER.getEnumValue())) {
				Asserts.assertEquals(getTexOfElement(DBSappObject.UpdateContactDetailsPageHeader()),
						CommonTestData.PERSONAL_AND_CONTACTDETAILS_PAGEHEADER.getEnumValue(),
						CommonTestData.PERSONAL_AND_CONTACTDETAILS_PAGEHEADER.getEnumValue() + " Text is not matching");
			}
			else if(getTexOfElement(DBSappObject.UpdateContactDetailsPageHeader()).equals(CommonTestData.CONTACT_DETAILS_PAGEHEADER.getEnumValue())) {
				Asserts.assertEquals(getTexOfElement(DBSappObject.UpdateContactDetailsPageHeader()),
						CommonTestData.CONTACT_DETAILS_PAGEHEADER.getEnumValue(),
						CommonTestData.CONTACT_DETAILS_PAGEHEADER.getEnumValue() + " Text is not matching");
			}

			Asserts.assertEquals(getTexOfElement(DBSappObject.ContactDetailsTitle()),
					CommonTestData.CONTACT_DETAILS_TITLE.getEnumValue(),
					CommonTestData.CONTACT_DETAILS_TITLE.getEnumValue() + " Text is not matching");

			Asserts.assertEquals(getTexOfElement(DBSappObject.EmailNotes()), CommonTestData.EMAIL_NOTES.getEnumValue(),
					CommonTestData.EMAIL_NOTES.getEnumValue() + " Text is not matching");


			gestUtils.scrollUPtoObject("text", "PERSONAL PARTICULARS", DBSappObject.PersonalPerticularSectionTitle()); 

			Thread.sleep(1500);
			TakeScreenshot(DBSappObject.PersonalPerticularSectionTitle());
			Asserts.assertEquals(getTexOfElement(DBSappObject.ContactDetailsChangeBtn()),
					CommonTestData.CHANGE_BUTTON.getEnumValue(),
					CommonTestData.CHANGE_BUTTON.getEnumValue() + " Text is not matching");
			Asserts.assertEquals(getTexOfElement(DBSappObject.PersonalPerticularSectionTitle()),
					CommonTestData.PERSONAL_PARTICULARS.getEnumValue(),
					CommonTestData.PERSONAL_PARTICULARS.getEnumValue() + " Text is not matching");

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
	
	@Step("Click On Personal & Contact Details Tab.")
	public void SelectPersonalContactDetailsTab() throws Exception {
		try {
			clickOnElement(DBSappObject.PersonalAndContactDetailsTab());
			Thread.sleep(2000); 
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On Personal And contact details TAB",e);
		}
		catch (Exception e) {		
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click On Personal And contact details TAB ",e);
		}
	}
	
	@Step("Verify Account Type , Account Name, Currency display and displayed Amount under Account Section")
	public void verifyAccountTypeNameCurrencyAmount(String AccountType, String AccountName, String currency)
			throws Exception {
		try {
			VerifyButtonLabelAndClick(DBSappObject.accountSectionHomePage(), CommonTestData.ACCOUNT_SECTION.getEnumValue());
			gestUtils.scrollDOWNtoObject("text", "Deposits", null);
			TakeScreenshot(DBSappObject.depositeHomePage());
			Asserts.assertEquals(getTexOfElement(DBSappObject.depositeHomePage()), AccountType,
					AccountType + " is not present");
			Asserts.assertEquals(getTexOfElement(DBSappObject.accountNameHomePage()), AccountName,
					AccountName + " is not present");
			gestUtils.scrollUPtoObject("text", "digiPortfolio", null);
			Asserts.assertEquals(getTexOfElement(DBSappObject.currencyHomePage()), currency,
					currency + " is not present");
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("TESTCASE_EXCEPTION", " Failed to Execute Account Details CASA ",e);		
		}
		catch (Exception e) {			
			obj_handleexception.throwException("TESTCASE_EXCEPTION", " Failed to Execute Account Details CASA ",e);
		}
	}

	@Step("Select Update Contact Details And Verify visibility of 'Back Button', 'Personal & Contact Details' Tab, 'Mailing Address' Tab and 'Update Contact Details' Page Header.")
	public void SelectUpdateContactDetails() throws Exception {
		try {
			TakeScreenshot(DBSappObject.ContactSearchfield()); 
			clickOnElement(DBSappObject.ContactSearchfield());
			TakeScreenshot(DBSappObject.EditTextSearchBox()); 
			clickOnElement(DBSappObject.EditTextSearchBox());
			enterTextInTextbox(DBSappObject.EditTextSearchBox(),
					CommonTestData.UPDATE_CONTACT_DETAILS_PAGEHEADER.getEnumValue());
			TakeScreenshot(DBSappObject.UpdateContactDetails());
			clickOnElement(DBSappObject.UpdateContactDetails());
			Thread.sleep(2000); 
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to select update contact details. ",e);
		}
		catch (Exception e) {		
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to select update contact details. ",e);
		} 
	}
	
	@Step("Verify 'Back Button', 'Mailing Address Tab', 'Personal & Contact Details Tab' on update Contact details Page. ")
	public void VerifySomeTabVisibiltyOnUpdateContactDetailsPage() throws Exception {
		try {
			Asserts.assertTrue(isElementVisible(DBSappObject.BackBtnImageView()),
					"Back Btn Image View is not displayed.");
			Asserts.assertTrue(isElementVisible(DBSappObject.PersonalAndContactDetailsTab()),
					"Personal And Contact Details Tab is not displayed.");
			Asserts.assertTrue(isElementVisible(DBSappObject.MailingAddressTab()),
					"Mailing Address Tab is not displayed.");
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to verify visibility all fields  ",e);
		}
		catch (Exception e) {		
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to verify visibility all fields  ",e);
		} 
	}
	
		@Step("Verify CreditCard Temperary Limit Increase")
		public void CreditCardTempLimitIncrease() throws Exception {
			try {
				ClickOnMoreButton();
				EnterPasscodeAndDone();
				sendDataInCommonSearchBoxAndSelectFromDropDown(CommonTestData.TEMP_LIMIT_INCREASE.getEnumValue(),
						CommonTestData.TEMP_LIMIT_INCREASE.getEnumValue());
				EnterPasscodeAndDone();
				verifyPageHeader(CommonTestData.TEMP_LIMIT_INCREASE_TITLE.getEnumValue(), DBSappObject.PageHeader());
				setAmountDurationPurposeForLimitIncrease(CommonTestData.CREDITCARD_LIMITINCREASE_AMOUNT.getEnumValue()
						, CommonTestData.CREDITCARD_LIMITINCREASE_PURPOSE.getEnumValue());
				
				gestUtils.scrollUPtoObject("text", "NEXT", null);
				
				ClickOnNextButton1();
				Thread.sleep(3000);
//				for(int i=0; i<DBSappObject.PageHeaderList2().size();i++ ) {
//					System.out.println(DBSappObject.PageHeaderList2().get(i).getText());
//				}
				
				MobileElement element = null;
				element = verifyElementExistInTheList(DBSappObject.PageHeaderList2(),
						CommonTestData.REVIEW_APPLICATION_CREDITLIMIT_TITLE.getEnumValue());
				if (element != null) {
					Asserts.assertEquals(getTexOfElement(element),
							CommonTestData.REVIEW_APPLICATION_CREDITLIMIT_TITLE.getEnumValue(),
							CommonTestData.REVIEW_APPLICATION_CREDITLIMIT_TITLE.getEnumValue() + " Text is not matching");
				}

			}catch (HandleException e) {	
				obj_handleexception.throwHandleException("TESTCASE_EXCEPTION", " Failed to Execute Credit Card Temp Limit Increase ",e);
			}
			catch (Exception e) {		
				obj_handleexception.throwException("TESTCASE_EXCEPTION", " Failed to Execute Credit Card Temp Limit Increase ",e);
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
		catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Selecting Purpose Account Type, Mobile Number ",e);
					
		}
		catch (Exception e) {			
			
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Selecting Purpose Account Type, Mobile Number ",e);
		}
}
	@Step("Select AccountType")
	public void selectAccountType(String AccountType) throws Exception//"Savings"
	{
		try {
			clickOnElement(DBSappObject.accountTypeInOversea());
			if(DBSappObject.commonList().size() > 0) {
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
						break;
					}
				}
				Asserts.assertTrue(index > 0,"Account Type "+AccountType + " Not found in the list.");
				wait.waitForElementVisibility(DBSappObject.PageHeaderList2().get(0));
			}else {
				Asserts.assertFail("Account type " + AccountType + " Not found in the list as list size is 0");
			}
		}
		catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Select Account Type ",e);			
		}
		catch (Exception e) {			
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Select Account Type ",e);
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
			Thread.sleep(2000); 
			TakeScreenshot(DBSappObject.PageHeader2()); 
			
//			for(int i=0; i < DBSappObject.PageHeaderList2().size(); i++) {
//				System.out.println(DBSappObject.PageHeaderList2().get(i).getText());
//			}
			MobileElement element = null;
			element = verifyElementExistInTheList(DBSappObject.PageHeaderList2(),
					CommonTestData.REVIEW_PAYMENT_PAGEHEADER.getEnumValue());
			if (element != null) {
				Asserts.assertEquals(getTexOfElement(element),
						CommonTestData.REVIEW_PAYMENT_PAGEHEADER.getEnumValue(),
						CommonTestData.REVIEW_PAYMENT_PAGEHEADER.getEnumValue() + " Text is not matching");
			}
			
			ClickOnPayNowButton();
			VerifyPaymentSubmittedMsg();

			String ExpectedFromAccountNumber = CommonTestData.FUNDTRANSFER_CREDITCARD_FROM_ACCOUNT_NUMBER
					.getEnumValue();
			String ExpectedToAccountNumber = CommonTestData.FUNDTRANSFER_CREDITCARD_TO_ACCOUNTNUMBER.getEnumValue();
			String ExpectedToCreditCardName = CommonTestData.FUNDTRANSFER_TOCREDITCARD_NAME.getEnumValue();
			VerifyVisibiltyOfSomeElements_FundTransferCreditCard(ExpectedFromBankName, ExpectedFromAccountNumber,
					ExpectedToAccountNumber, ExpectedToCreditCardName);

			// Leaving On Home Page for Next case Run.
			ClickOnBackIcon();
			ClickOnHomeButton();
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
			Thread.sleep(2000); 
			TakeScreenshot(DBSappObject.PageHeader2()); 
			MobileElement element = null;
			element = verifyElementExistInTheList(DBSappObject.PageHeaderList2(),
					CommonTestData.REVIEW_PAYMENT_PAGEHEADER.getEnumValue());
			if (element != null) {
				Asserts.assertEquals(getTexOfElement(element),
						CommonTestData.REVIEW_PAYMENT_PAGEHEADER.getEnumValue(),
						CommonTestData.REVIEW_PAYMENT_PAGEHEADER.getEnumValue() + " Text is not matching");
			}
			
			ClickOnPayNowButton();
			VerifyPaymentSubmittedMsg();

			// Leaving On Home Page for Next case Run.
			ClickOnBackIcon();
			ClickOnHomeButton();
			
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

			String[] ExpTitleList = new String[] { "From", "To", "When", "Latest Balance", "Reference No." };

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

			Dimension windowSize = driver.manage().window().getSize();
			System.out.println("getSessionId :" + driver.getSessionId());

			int h = windowSize.getHeight();
			int y1 = (int) (h * 0.2);
			int y2 = (int) (h - y1);
			int x = (int) ((windowSize.getWidth()) / 2);

			String s1 = driver.getPageSource();
			int count = 0;
			int index = 0;
			WaitUtils wait = new WaitUtils(driver);
			wait.ImplicitlyWait();
			while (count == 0 && index == 0) {
				if (DBSappObject.SubTitleTextList().size() > 0) {
					TakeScreenshot(DBSappObject.SubTitleTextList().get(0));
					List<MobileElement> Elementlist = DBSappObject.SubTitleTextList();
					List<MobileElement> ElementlistClickable = DBSappObject.ListElementToClickable();
					int length = Elementlist.size();
					String CreditCardList = null;
					if (length < 2) {
						for (int i = 0; i < length; i++) {
							CreditCardList = Elementlist.get(i).getText();
							if (CreditCardList.equalsIgnoreCase(valueSelectedFromList)) {
								index++;
								clickOnElement(ElementlistClickable.get(i));
								break;
							}
						}
						// Exception Handling without scrolling case and no expected element found in
						// the list then index ==0
						if (index == 0 && count == 0)
							Asserts.assertFail("Credit Cards " + valueSelectedFromList
									+ " not found in the list to initiate the fund transfer.");
						else
							break;
					} else

						// Code will work :: When Need to scroll
						for (int i = 0; i < length; i++) {
							CreditCardList = Elementlist.get(i).getText();
							if (CreditCardList.equalsIgnoreCase(valueSelectedFromList)) {
								index++;
								clickOnElement(ElementlistClickable.get(i));
								break;
							}
						}
					if (index == 0) {
						touch.longPress(longPressOptions().withPosition(point(x, y2)).withDuration(ofSeconds(2)))
								.moveTo(element(DBSappObject.AllTab())).release().perform();

						String s2 = driver.getPageSource();
						if (s1.equals(s2) != true)
							s1 = s2;
						else
							count = 1;
					} else
						break;

					// Exception Handling in scrolling case and no expected element found in the
					// list then index ==0, count ==1
					if (count == 1 && index == 0)
						Asserts.assertFail("Credit Cards " + valueSelectedFromList
								+ " not found in the list to initiate the fund transfer.");

				} else
					Asserts.assertFail("No receipient found in the Credit Cards list");
			}

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
				
				if(DBSappObject.commonList().size() > 0) {
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
							Thread.sleep(2000); 
							break;
						}
					}
					Asserts.assertTrue(index > 0, "Purpose "+ purpose + " Not found in the list.");
					wait.waitForElementVisibility(DBSappObject.PageHeaderList2().get(0));
				}else {	
					Asserts.assertFail("Purpose "+ purpose + " not found in the list as list size is 0");
				}	
			}
			catch (HandleException e) {	
				obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Select Purpose ",e);		
			}
			catch (Exception e) {			
				obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Select Purpose ",e);
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
			catch (HandleException e) {	
				obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Enter Mobile Number ",e);		
			}
			catch (Exception e) {			
				obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Enter Mobile Number ",e);
			}
}


	@Step("Click on 'To Account Bill' after selecting 'Billing organisation' and verify Page Header")
	public void clickingOnAccountTypeInBillingOrganisations(String valueSelectedFromList) throws Exception {
		try {
			gestUtils.DragAndDropElementToElement(DBSappObject.AllTabOptionsList().get( DBSappObject.AllTabOptionsList().size()-1), DBSappObject.AllTab());
			
			int o = 0;
			for (int i = 0; i < DBSappObject.AllTabOptionsList().size(); i++) {
				String tabText = DBSappObject.AllTabOptionsList().get(i).getText();
				o++;
				if (tabText.contains(CommonTestData.BILLING_ORGANISATIONS_TAB.getEnumValue())) {
					clickOnElement(DBSappObject.AllTabOptionsList().get(i));
					break;
				}
			}

		//	gestUtils.DragAndDropElementToElement(DBSappObject.AllTabOptionsList().get(o), DBSappObject.AllTab());
			
			Dimension windowSize = driver.manage().window().getSize();
			System.out.println("getSessionId :" + driver.getSessionId());

			int h = windowSize.getHeight();
			int y1 = (int) (h * 0.2);
			int y2 = (int) (h - y1);
			int x = (int) ((windowSize.getWidth()) / 2);

			String s1 = driver.getPageSource();
			int count = 0;
			int index = 0;
			WaitUtils wait = new WaitUtils(driver);
			wait.ImplicitlyWait();
			while (count == 0 && index == 0) {
				if (DBSappObject.SubTitleTextList().size() > 0) {
					TakeScreenshot(DBSappObject.SubTitleTextList().get(0));
					List<MobileElement> Elementlist = DBSappObject.SubTitleTextList();
					List<MobileElement> ElementlistClickable = DBSappObject.ListElementToClickable();
					int length = Elementlist.size();
					String BillingOrganisationlist = null;
					if (length < 2) {
						for (int i = 0; i < length; i++) {
							BillingOrganisationlist = Elementlist.get(i).getText();
							if (BillingOrganisationlist.equalsIgnoreCase(valueSelectedFromList)) {
								index++;
								clickOnElement(ElementlistClickable.get(i));
								break;
							}
						}
						// Exception Handling without scrolling case and no expected element found in
						// the list then index ==0
						if (index == 0 && count == 0)
							Asserts.assertFail("Billing Organisation " +valueSelectedFromList+" not found in the list to initiate the fund transfer.");
						else
							break;
					} else

						// Code will work :: When Need to scroll
						for (int i = 0; i < length; i++) {
							BillingOrganisationlist = Elementlist.get(i).getText();
							if (BillingOrganisationlist.equalsIgnoreCase(valueSelectedFromList)) {
								index++;
								clickOnElement(ElementlistClickable.get(i));
								break;
							}
						}
					if (index == 0) {
						touch.longPress(longPressOptions().withPosition(point(x, y2)).withDuration(ofSeconds(2)))
								.moveTo(element(DBSappObject.AllTab())).release().perform();

						String s2 = driver.getPageSource();
						if (s1.equals(s2) != true)
							s1 = s2;
						else
							count = 1;
					} else
						break;

					// Exception Handling in scrolling case and no expected element found in the
					// list then index ==0, count ==1
					if (count == 1 && index == 0)
						Asserts.assertFail("Billing Organisation " +valueSelectedFromList+" not found in the list to initiate the fund transfer.");

				} else
					Asserts.assertFail("No receipient found in the Billing Organisation list");
			}
			

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
	
	public void verifyDigibankAlert() throws Exception
	{
		String alertMessage=null;
		try {
		if(androidAlert.isAlertPresent()) {
			System.out.println("Alert title :: "+ this.driver.switchTo().alert().getText()); 
			
			alertMessage=this.driver.switchTo().alert().getText();
				Asserts.assertFail(alertMessage);
		}
			}
			catch (Exception e) {		
				obj_handleexception.throwException("DIGIBANK_ALERT", " Failed to proceed because of DIGI BANK ALERT "+ alertMessage,e);
			}
	}
	
	@Step("Click On OK Button.")
	public void ClickOnOKButton_Alert() throws Exception{
		try {
			TakeScreenshot(DBSappObject.Alert_OKButton());
			clickOnElement(DBSappObject.Alert_OKButton());
			Thread.sleep(1000);
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On OK Button  ",e);		
		}
		catch (Exception e) {			
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click On OK Button   ",e);
		}
	}
	
	@Step("Verify Payee Size After Delete Payee.")
	public void VerifyPayeeSizeAfterDeletePayee(int ExpectedTotalPayeeSize) throws Exception{
		try {
			String xpath = "//android.widget.ImageView[contains(@resource-id,':id/tv_expandable_item_selected')]";
			List<RemoteWebElement> Payeelist = driver.findElements(By.xpath(xpath));
			
			int ActualTotalPayeeSize = Payeelist.size();
			int ExpectedTotalSizeAfterDeletingPayee = ExpectedTotalPayeeSize - 1;  
			Asserts.assertEquals(String.valueOf(ExpectedTotalSizeAfterDeletingPayee),
					String.valueOf(ActualTotalPayeeSize), " Payee size is not matching after performing delete operation.");
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Verify Payee Size after Delete Payee.  ",e);		
		}
		catch (Exception e) {			
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Verify Payee Size after Delete Payee.  ",e);
		}
	}
	
	@Step("Click On Back Button")
	public void ClickOnBackButtonImageView() throws Exception{
		try {
			TakeScreenshot(DBSappObject.BackBtnImageView());
			clickOnElement(DBSappObject.BackBtnImageView());
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to click on Back Button. ",e);		
		}
		catch (Exception e) {			
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to click on Back Button. ",e);
		}
	}

	@Step("Change reset local fund transfer limit")
	public void ChangeLocalFundsTransferLimitReset() throws Exception {
		try {
			String amountSlected = handlingSetCurrentLimit(CommonTestData.SELECTED_LIMIT_500.getEnumValue());
			ClickOnNextButton();
			verifyClickChangeDailyLimitNowButton();
			ClickOnBackToMoreButton();
			sendDataInCommonSearchBoxAndSelectFromDropDown(CommonTestData.LOCAL_TRANSFER_LIMIT_LABEL.getEnumValue(),
					CommonTestData.LOCAL_TRANSFER_LIMIT_LABEL.getEnumValue());
			EnterPasscodeAndDone();
			verifyPageHeader(CommonTestData.LOCAL_TRANSFER_LIMIT_LABEL.getEnumValue(), DBSappObject.PageHeader2());
			ClickOnToOtherBankLimit();
			verifyClickSetCurrentLimit();
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("TESTCASE_EXCEPTION", " Failed to Exceute Change Local Funds Transfer Limit " ,e);			
		}
		catch (Exception e) {			
			obj_handleexception.throwException("TESTCASE_EXCEPTION", " Failed to Exceute Change Local Funds Transfer Limit ",e);
		}
	}
	
	@Step("Verify 'Welcome to DigiBank' Messages on dashboard Page.")
	public void VerifyWelcomeMessagesOnDashboardPage(String welcome, String DigiBank, String DBSDigibank) throws Exception {
		try {
			Asserts.assertEquals(getTexOfElement(DBSappObject.WelcomeToText()).trim(),
					welcome, welcome + " text is not matching.");

			TakeScreenshot(DBSappObject.DigibankText());
			if (DBSappObject.DigibankText().getText().equalsIgnoreCase(DigiBank)) {
				Asserts.assertEquals(getTexOfElement(DBSappObject.DigibankText()).trim(),
						DigiBank, DigiBank + " text is not matching.");
			} else if (DBSappObject.DigibankText().getText()
					.equalsIgnoreCase(DBSDigibank)) {
				Asserts.assertEquals(getTexOfElement(DBSappObject.DigibankText()).trim(),
						DBSDigibank,DBSDigibank + " text is not matching.");
			}
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to verify welcome messages " ,e);			
		}
		catch (Exception e) {			
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to verify welcome messages ",e);
		}
	}
}
