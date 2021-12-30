package com.crestech.pages.androidpage;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import com.crestech.annotation.values.ElementDescription;
import com.crestech.appium.utils.CommonAppiumTest;
import com.crestech.common.utilities.AndroidAlert;
import com.crestech.common.utilities.Asserts;
import com.crestech.common.utilities.CommonTestData;
import com.crestech.common.utilities.GestureUtils;
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
public class homePage extends CommonAppiumTest {

	
	public AppiumDriver<RemoteWebElement> driver = null;
	HandleException obj_handleexception = null;
	GestureUtils gestUtils = null;
	Asserts Assert = null;
	AndroidAlert androidAlert = null;
	
	public homePage(AppiumDriver<RemoteWebElement> driver) throws Exception {
		super(driver);
		try {
			this.driver = driver;
			obj_handleexception = new HandleException(null, null);
			gestUtils = new GestureUtils(driver);
			Assert = new Asserts();
			androidAlert = new AndroidAlert(driver);
			PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(5)), this);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	
	//objects
	@ElementDescription(value = "currency Home page")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'id/tv_currency_label')]")
	private MobileElement currencyHomePage;
	
	@ElementDescription(value = "Account Name Home page")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'id/text_src_acc_name')]")
	private MobileElement accountNameHomePage;
	
	@ElementDescription(value = "Deposite Home page")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Deposits')]")
	private MobileElement depositeHomePage;
	
	@ElementDescription(value = "Deposite Home page")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'CPFIA/SRS Account')]")
	private MobileElement SRSACCOUNT;
	
	@ElementDescription(value = "Account section Home page")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'ACCOUNTS')]")
	private MobileElement accountSectionHomePage;
	
    @ElementDescription(value = "Alert Recording Message")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'id/title')]")
	private MobileElement headerRecordingMessage;
    
    @ElementDescription(value = "Alert Fingerprint Message")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'id/desc')]")
	private MobileElement headerFingerprintMessage;
    
    @ElementDescription(value = "Email/SMS OTP Message")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'id/txtv_kht_regcode_header')]")
	private MobileElement emailSmsOtpMessage;

	@ElementDescription(value = "Email/SMS OTP Editbox")
	@AndroidFindBy(xpath = "//android.widget.EditText[contains(@resource-id,'id/kht_otp_box')]")
	private MobileElement emailSmsOtpEditBox;
	
	@ElementDescription(value = "set up your digital token")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'id/status_message')]")
	private MobileElement tokenSetupMessage;

	@ElementDescription(value = "SET UP NOW Button")
	@AndroidFindBy(xpath = "//android.widget.Button[@text='SET UP NOW']")
	private MobileElement setUpNowButton;
	
	@ElementDescription(value = "You've set up your digital token")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'id/digital_token_msg')]")
	private MobileElement tokenGetSetupMessage;
	
	@ElementDescription(value = "Done Button")
	@AndroidFindBy(xpath = "//android.widget.Button[@text='DONE']")
	private MobileElement doneButton;
	
	@ElementDescription(value = "Welcome to")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'id/banner_text_1')]")
	private MobileElement WelcomeToText;

	@ElementDescription(value = "digbank")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'id/banner_text_2')]")
	private MobileElement DigibankText;
	
	@ElementDescription(value = "Logout Button")
	@AndroidFindBy(xpath = "//android.widget.Button[@text='LOG OUT']")
	private MobileElement logoutButton;
	
	@ElementDescription(value = "Close Button")
	@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc='CLOSE']")
	private MobileElement CloseButton;
	
	@ElementDescription(value = "Pay & Transfer Button")
	@AndroidFindBy(xpath = "//android.widget.FrameLayout[@content-desc='Pay & Transfer']")
	private MobileElement PayAndTransferBtn;
	
	@ElementDescription(value = "More Button")
	@AndroidFindBy(xpath = "//android.widget.FrameLayout[@content-desc='More']")
	private MobileElement MoreBtn;
	
	@ElementDescription(value = "Deposits Account Name")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,':id/text_src_acc_name')]")
	private MobileElement DepositsAccountName;

	@ElementDescription(value = "Deposits Account Type")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,':id/value')]")
	private List<MobileElement> AccountValueList;
	
	@ElementDescription(value = "Deposits Account Name")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,':id/title')]")
	private List<MobileElement> AccountTitleList;
	
	@ElementDescription(value = "User Account Name")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,':id/tv_account_name')]")
	private MobileElement UserAccountName;
	
	@ElementDescription(value = "User Account Number")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,':id/tv_account_number')]")
	private MobileElement UserAccountNumber;
	
	@ElementDescription(value = "Toolbar Back Icon")
	@AndroidFindBy(xpath = "//android.widget.ImageView[contains(@resource-id,':id/toolbar_back_icon')]")
	private MobileElement ToolbarBackIcon;
	
	@ElementDescription(value = "Deposits Account Type")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Deposits']")
	private MobileElement DepositsAccountType;
	
	@ElementDescription(value = "Next button")
	@AndroidFindBy(xpath = "//android.widget.Button[@text='NEXT']")
	private MobileElement nextButton;
	
	@ElementDescription(value = "Confirm button")
	@AndroidFindBy(xpath = "//android.widget.Button[@text='CONFIRM']")
	private MobileElement confirmButton;
	
	@ElementDescription(value = "Get started button")
	@AndroidFindBy(xpath = "//android.widget.Button[contains(@resource-id,':id/btn_get_started')]")
	private MobileElement getstartedBtn;
	
	@ElementDescription(value = "Handling error alert")
	@AndroidFindBy(xpath = "//android.widget.Button[@resource-id='android:id/button1']")
	private MobileElement errorAlertOKButton;
	
	@ElementDescription(value = "Continue Button")
	@AndroidFindBy(xpath = "//android.widget.Button[@text='CONTINUE']")
	private MobileElement continueButton;
	
	@ElementDescription(value = "Digital Token SetUp")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'id/status_message')]")
	private MobileElement digitalTokenSetUpMessage;
	
	@Step("Verify Deposit Account Type On Dashboard Page")
	public void VerifyDepositAccountTypeOnDashboardPage() throws Exception {
		try {
			if (isElementVisible2(DepositsAccountType)) {
				gestUtils.scrollUPtoObject("text", "Deposits", DepositsAccountType);
				Asserts.assertTrue(isElementVisible(DepositsAccountType),
						"Deposits Account Type is not displayed on home page after login.");
			}
		} catch (HandleException e) {
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION",
					" Failed to Verify Deposit Account Type On Dashboard Page ", e);
		} catch (Exception e) {
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION",
					" Failed to Verify Deposit Account Type On Dashboard Page ", e);
		}
	}
	
	@Step("Click On Toolbar Back Icon.")
	public void ClickOnToolBarBackIcon() throws Exception {
		try {
			clickOnElement(ToolbarBackIcon);
		} catch (HandleException e) {
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On Toolbar Back Icon ",
					e);
		} catch (Exception e) {
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click On Toolbar Back Icon ", e);
		}
	}


	@Step("Click On More Button.")
	public void ClickOnMoreButton() throws Exception {
		try {
			wait.waitForElementToBeClickable(MoreBtn); 
			clickOnElement(MoreBtn);
            ClickOnCloseButton();
		} catch (HandleException e) {
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On More Button  ", e);
		} catch (Exception e) {
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click On More Button  ", e);
		}
	}
	
	@Step("Click On Pay & Transfer Button.")
	public void ClickOnPayAndTransferBtn() throws Exception {
		try {
			wait.waitForElementToBeClickable(PayAndTransferBtn); 
			clickOnElement(PayAndTransferBtn);
		} catch (HandleException e) {
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION"," Failed to Click On Pay and Transfer Button  ", e);
		} catch (Exception e) {
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION"," Failed to Click On On Pay and Transfer Button  ", e);
		}
	}
	
	@Step("Digital Token Set Up")
	public void digitalTokenSetUp() throws Exception {
		try {
			if (isElementVisible2(digitalTokenSetUpMessage)) { 
				ClickOnSetUpNowButton();
				ClickOnContinueButton();
				ClickOnNextButton();
				
				EnterEmailOrSMSOTP(CommonTestData.OTP.getEnumValue(),
						CommonTestData.EMAIL_OTP_MESSAGE.getEnumValue());
				EnterEmailOrSMSOTP(CommonTestData.OTP.getEnumValue(),
						CommonTestData.SMS_OTP_MESSAGE.getEnumValue());

                wait.waitForElementVisibility(tokenGetSetupMessage); 
                
				if(isElementVisible2(doneButton)) {
					verifyDigitalTokenMessageAfterSetUp(CommonTestData.DIGITAL_TOKEN_MESSAGE_AFTER_STEPUP.getEnumValue());
					ClickOnDoneButton();
				} else {
					gestUtils.scrollUPtoObject(null, null, null);
					verifyDigitalTokenMessageAfterSetUp(CommonTestData.DIGITAL_TOKEN_MESSAGE_AFTER_STEPUP.getEnumValue());
					ClickOnDoneButton();
				}
			}
		} catch (HandleException e) {
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to execute Digital Token Setup ",
					e);
		} catch (Exception e) {
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to execute Digital Token Setup ", e);
		}
	}
	
	@Step("verify Digital Token Message After SetUp.")
	public void verifyDigitalTokenMessageAfterSetUp(String expectecMessage)throws Exception{
		try {
			String actualMessage = getTexOfElement(tokenGetSetupMessage);
			Asserts.assertEquals(actualMessage, expectecMessage, "Message Not matching"); 
		}catch (HandleException e) {
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to verify Digital Token Message After SetUp ",
					e);
		} catch (Exception e) {
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to verify Digital Token Message After SetUp ", e);
		}
	}
	
	@Step("Click On Done Button.")
	public void ClickOnDoneButton()throws Exception{
		try {
			clickOnElement(doneButton); 
		}catch (HandleException e) {
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On Done Button ",
					e);
		} catch (Exception e) {
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click On Done Button ", e);
		}
	}
	
	@Step("Click On Continue Button.")
	public void ClickOnContinueButton()throws Exception{
		try {
		if (isElementVisible2(continueButton)) 
			clickOnElement(continueButton); 
		}catch (HandleException e) {
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On Continue Button ",
					e);
		} catch (Exception e) {
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click On Continue Button ", e);
		}
	}

	@Step("Click On Next Button.")
	public void ClickOnNextButton() throws Exception {
		try {
			String confirmButtonXpath = "//android.widget.Button[@text='CONFIRM']";
			List<RemoteWebElement> confirmButtonList = driver.findElements(By.xpath(confirmButtonXpath));
			String nextButtonXpath = "//android.widget.Button[@text='NEXT']";
			List<RemoteWebElement> nextButtonlist = driver.findElements(By.xpath(nextButtonXpath));
			if (confirmButtonList.size() > 0) {
				gestUtils.scrollUPtoObject("text", "CONFIRM", confirmButton);
				clickOnElement(confirmButton);
			} else if (nextButtonlist.size() > 0) {
				gestUtils.scrollUPtoObject("text", "NEXT", nextButton);
				if (getTexOfElement(nextButton).equalsIgnoreCase("NEXT"))
					clickOnElement(nextButton);
			}
		} catch (HandleException e) {
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On Next Button  ", e);
		} catch (Exception e) {
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click On Next Button  ", e);
		}
	}
	
	@Step("Click on 'SET UP NOW' button ")
	public void ClickOnSetUpNowButton() throws Exception {
		try {
			clickOnElement(setUpNowButton);
		} catch (HandleException e) {
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On Set Up Now Button ",
					e);
		} catch (Exception e) {
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click On Set Up Now Button ", e);
		}
	}

	public MobileElement DepositsAccountName() { 
		return DepositsAccountName;
	}
	
	@Step("Enter OTP for Email/SMS Verification")
	public void EnterEmailOrSMSOTP(String OTP, String expectecMessage) throws Exception {
		try {
			wait.waitForElementToBeClickable(emailSmsOtpEditBox);
			String actualMessage = getTexOfElement(emailSmsOtpMessage);
			if (actualMessage.equalsIgnoreCase(expectecMessage))
				enterTextInTextbox(emailSmsOtpEditBox, OTP);
		} catch (HandleException e) {
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to enter Email/SMS OTP ", e);
		} catch (Exception e) {
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", "  Failed to enter Email/SMS OTP ", e);
		}
	}
	
	@Step("Handling Error Alert.")
	public void handlingErrorAlert() throws Exception {
		try {
			if(isElementVisible2(errorAlertOKButton))
				clickOnElement(errorAlertOKButton);
		} catch (HandleException e) {
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Handle Error Alert ", e);
		} catch (Exception e) {
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Handle Error Alert ", e);
		}
	}

	@Step("Handling Of 'Get Started' Popup.")
	public void handlingGetStartedPopup() throws Exception {
		try {
			if(isElementVisible2(getstartedBtn))
				clickOnElement(getstartedBtn);	
		} catch (HandleException e) {
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION",
					" Failed to Handle Of 'Get Started' Popup ", e);
		} catch (Exception e) {
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Handle Of 'Get Started' Popup ", e);
		}
	}
	
	
	@Step("Handling Finger Print Alert")
	public void handlingFingerPrintAlert(String expectecMessage) throws Exception   
	{
		try {
			if(isElementVisible2(headerFingerprintMessage)) {
				String actualMessage = getTexOfElement(headerFingerprintMessage);
				Asserts.assertEquals(actualMessage, expectecMessage, "Finger Print Message Not matching");
				clickOnElement(CloseButton);
			}
		} catch (HandleException e) {	
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Handling Finger Print Alert  ",e);		
		}
		catch (Exception e) {			
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Handling Finger Print Alert  ",e);
		}
	}
	
	@Step("Handle Recording Alert")
	public void handleRecordingAlert(String expectecMessage) throws Exception
	{
		try {
			if(isElementVisible2(headerRecordingMessage)) {
				String actualMessage = getTexOfElement(headerRecordingMessage);
				Asserts.assertEquals(actualMessage, expectecMessage, "Recording alert Message Not matching");
				clickOnElement(CloseButton);
			}
		} catch (HandleException e) {
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Handle Recording Alert ",
					e);
		} catch (Exception e) {
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Handle Recording Alert  ", e);
		}
	}
	
	@Step("Verify Account Type , Account Name, Currency display and displayed Amount under Account Section")
	public void verifyAccountTypeNameCurrencyAmount(String AccountType, String AccountName, String currency)
			throws Exception {
		try {
			clickOnElement(accountSectionHomePage);
			gestUtils.scrollDOWNtoObject("text", "Deposits", null);
			Asserts.assertEquals(getTexOfElement(depositeHomePage), AccountType,AccountType + " is not present");
			
			if (isElementVisible2(accountNameHomePage)) {
				Asserts.assertEquals(getTexOfElement(accountNameHomePage), AccountName,
						AccountName + " is not present");
			} else
				Asserts.assertFail(AccountName + " Not Found on the Dashboard Page."); 
			
			gestUtils.scrollUPtoObject("text", "digiPortfolio", null);
			Asserts.assertEquals(getTexOfElement(currencyHomePage), currency,currency + " is not present");
		} catch (HandleException e) {
			obj_handleexception.throwHandleException("TESTCASE_EXCEPTION", " Failed to Execute Account Details CASA ",
					e);
		} catch (Exception e) {
			obj_handleexception.throwException("TESTCASE_EXCEPTION", " Failed to Execute Account Details CASA ", e);
		}
	}
	
	@Step("Verify Account Type , Account Name, Currency display and displayed Amount under Account Section")
	public void verifyAccountTypeNameCurrencyAmount_iWEAlLTH(String AccountType, String AccountName, String currency)
			throws Exception {
		try {
			clickOnElement(accountSectionHomePage);
			verifyAccountType(AccountType);
			verifyAccountName(AccountName);
			verifyCurrency(currency);
		} catch (HandleException e) {
			obj_handleexception.throwHandleException("TESTCASE_EXCEPTION", " Failed to Execute Account Details CASA ",
					e);
		} catch (Exception e) {
			obj_handleexception.throwException("TESTCASE_EXCEPTION", " Failed to Execute Account Details CASA ", e);
		}
	}
	
	@Step("Verify 'Currency' on dashboard Page.")
	public void verifyCurrency(String currency) throws Exception{
		try {
			if(isElementVisible2(currencyHomePage)) 
			Asserts.assertEquals(getTexOfElement(currencyHomePage), currency,currency + " is not present");
			else
				Asserts.assertFail(currency + " Not Found on the Dashboard Page."); 
		}catch (HandleException e) {
			obj_handleexception.throwHandleException("FUNTIONAL_EXCEPTION", " Failed to Verify 'Currency' on dashboard Page ",
					e);
		} catch (Exception e) {
			obj_handleexception.throwException("FUNTIONAL_EXCEPTION", " Failed to Verify 'Currency' on dashboard Page ", e);
		}
	}
	
	@Step("Verify 'Account Name' on dashboard Page.")
	public void verifyAccountName(String AccountName) throws Exception{
		try {
			if(isElementVisible2(accountNameHomePage)) 
			Asserts.assertEquals(getTexOfElement(accountNameHomePage), AccountName,AccountName + " is not present");
			else
				Asserts.assertFail(AccountName + " Not Found on the Dashboard Page."); 
			
			
			gestUtils.scrollUPtoObject("text", "SGD", null);
		}catch (HandleException e) {
			obj_handleexception.throwHandleException("FUNTIONAL_EXCEPTION", " Failed to Verify 'Account Name' on dashboard Page ",
					e);
		} catch (Exception e) {
			obj_handleexception.throwException("FUNTIONAL_EXCEPTION", " Failed to Verify 'Account Name' on dashboard Page ", e);
		}
	}
	
	@Step("Verify 'Account Type' on dashboard Page.")
	public void verifyAccountType(String AccountType) throws Exception{
		try {
			gestUtils.scrollDOWNtoObject("text", "CPFIA/SRS Account", null);
			Asserts.assertEquals(getTexOfElement(SRSACCOUNT), AccountType, AccountType + " is not present");
		}catch (HandleException e) {
			obj_handleexception.throwHandleException("FUNTIONAL_EXCEPTION", " Failed to Verify 'Account Type' on dashboard Page ",
					e);
		} catch (Exception e) {
			obj_handleexception.throwException("FUNTIONAL_EXCEPTION", " Failed to Verify 'Account Type' on dashboard Page ", e);
		}
	}
	
	@Step("Verify 'Welcome to DigiBank' Messages on dashboard Page.")
	public void VerifyWelcomeMessagesOnDashboardPage(String welcome, String DigiBank, String DBSDigibank)
			throws Exception {
		try {
			Asserts.assertEquals(getTexOfElement(WelcomeToText).trim(), welcome, welcome + " text is not matching.");

			if (DigibankText.getText().equalsIgnoreCase(DigiBank)) 
				Asserts.assertEquals(getTexOfElement(DigibankText).trim(), DigiBank, DigiBank + " text is not matching.");
			 else if (DigibankText.getText().equalsIgnoreCase(DBSDigibank)) 
				Asserts.assertEquals(getTexOfElement(DigibankText).trim(), DBSDigibank, DBSDigibank + " text is not matching.");
			
		} catch (HandleException e) {
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to verify welcome messages ", e);
		} catch (Exception e) {
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to verify welcome messages ", e);
		}
	}
	
	@Step("Click On logout Button")
	public void ClickOnLogOutButton() throws Exception {
		try {
			clickOnElement(logoutButton);
		} catch (HandleException e) {
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click On logout Button  ", e);
		} catch (Exception e) {
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click On logout Button  ", e);
		}
	}
	
	
	@Step("Click On Close Button.")
	public void ClickOnCloseButton() throws Exception {
		try {
			if(isElementVisible2(CloseButton)) 
			clickOnElement(CloseButton);
		} catch (HandleException e) {
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to Click on Close Button  ", e);
		} catch (Exception e) {
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to Click on Close Button ", e);
		}
	}
	
	@Step("Get And Click On Deposite Account Name From Dashboard.")
	public String getAndClickOnDepositeAccountNameFromDashboard() throws Exception {
		try {
			String DepositeAccountNameOnDashboard = DepositsAccountName.getText();
			clickOnElement(DepositsAccountName);
			return DepositeAccountNameOnDashboard;
		} catch (HandleException e) {
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION",
					" Failed to get And Click On Deposite Account Name From Dashboard ", e);
		} catch (Exception e) {
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION",
					" Failed to get And Click On Deposite Account Name From Dashboard ", e);
		}
		return null;
	}
	
	@Step("Get User Account Number")
	public String GetUserAccountNumber() throws Exception {
		try {
			String ExpectedUserAccountNumber = UserAccountNumber.getText();
			return ExpectedUserAccountNumber;
		} catch (Exception e) {
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to get User Account Number ", e);
		}
		return null;
	}

	@Step("Get User Account name")
	public String GetUserAccountName(String DepositeAccountNameOnDashboard) throws Exception {
		try {
			String ExpectedUserAccountName = UserAccountName.getText();
			Asserts.assertEquals(getTexOfElement(UserAccountName), DepositeAccountNameOnDashboard,
					DepositeAccountNameOnDashboard + " Text is not matching.");
			return ExpectedUserAccountName;
		} catch (HandleException e) {
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to get User Account Name ", e);
		} catch (Exception e) {
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to get User Account Name ", e);
		}
		return null;
	}

	@Step("Get Total Balance")
	public String getTotalBalance(String TotalBalanceTitle) throws Exception {
		try {
			Asserts.assertEquals(getTexOfElement(AccountTitleList.get(1)), TotalBalanceTitle,
					TotalBalanceTitle + " Text is not matching.");

			String ExpectedTotalBalanceValue = AccountValueList.get(1).getText();
			return ExpectedTotalBalanceValue;
		} catch (HandleException e) {
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to get Total Balance ", e);
		} catch (Exception e) {
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to get Total Balance  ", e);
		}
		return null;
	}

	@Step("Get Available Balance")
	public String getAvailableBalance(String AvailableBalanceTitle) throws Exception {
		try {
			Asserts.assertEquals(getTexOfElement(AccountTitleList.get(0)), AvailableBalanceTitle,
					AvailableBalanceTitle + " Text is not matching.");
			String ExpectedAvailableBalanceValue = AccountValueList.get(0).getText();
			return ExpectedAvailableBalanceValue;
		} catch (HandleException e) {
			obj_handleexception.throwHandleException("FUNCTIONAL_EXCEPTION", " Failed to get Available Balance ", e);
		} catch (Exception e) {
			obj_handleexception.throwException("FUNCTIONAL_EXCEPTION", " Failed to get Available Balance  ", e);
		}
		return null;
	}

}