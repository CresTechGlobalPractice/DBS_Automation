package com.crestech.pages;

import static org.testng.Assert.assertFalse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import com.aventstack.extentreports.reporter.TestListener;
import com.crestech.appium.utils.CommonAppiumTest;
import com.crestech.common.utilities.AndroidAlert;
import com.crestech.common.utilities.Asserts;
import com.crestech.common.utilities.CommonAlertElements;
import com.crestech.common.utilities.CommonTestData;
import com.crestech.common.utilities.GestureUtils;
import com.crestech.common.utilities.WaitUtils;
import com.crestech.pageobjects.DBSAndroidObject;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;


/**
 * @author Divya Devi, Shafkat Ali
 *
 */
public class DBSAndroidPage extends CommonAppiumTest {

	static Logger log = Logger.getLogger(DBSAndroidPage.class.getName());
	public DBSAndroidObject DBSappObject = new DBSAndroidObject();

	public DBSAndroidPage(AppiumDriver<RemoteWebElement> driver) throws Exception {
		super(driver);
		try {
			PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(5)), DBSappObject);
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Relaunching DBS application")
	public void relaunchingDBS() throws Exception {
		try {
			relanchApplication(CommonTestData.DBS_APP_PACKAGE.getEnumValue(),
					CommonTestData.DBS_APPS_ACTIVITY.getEnumValue());
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Relaunching POSB application")
	public void relaunchingPOSB() throws Exception {
		try {
			relanchApplication(CommonTestData.POSB_APP_PACKAGE.getEnumValue(),
					CommonTestData.DBS_APPS_ACTIVITY.getEnumValue());
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Relaunching iWealth application")
	public void relaunchingIwealth() throws Exception {
		try {
			relanchApplication(CommonTestData.IWEALTH_APP_PACKAGE.getEnumValue(),
					CommonTestData.DBS_APPS_ACTIVITY.getEnumValue());
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Log In the Application")
	public void logInApplication(String userName, String password) throws Exception {
		try {
			WaitUtils wait = new WaitUtils(driver);
			CommonAlertElements btnElements = new CommonAlertElements(driver);
			Thread.sleep(30000);
			String quitButtonXpath = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.Button";
			List<RemoteWebElement> list = driver.findElements(By.xpath(quitButtonXpath));
			if (list.size() > 0) {
				driver.closeApp();
				relaunchingDBS();
				wait.waitForElementToBeClickable(DBSappObject.loginButton());
				Thread.sleep(5000);
			}
			clickOnLoginButton();
			sendDataInUserId(userName);
			sendDataInUserPin(password);
			clickOnLoginButton();
			digitalTokenSetUp();
			AndroidAlert androidAlert = new AndroidAlert(driver);
			androidAlert.fingerprintAlertHandlingWithButtonMessage(btnElements.closeButton(),
					CommonTestData.FINGERPRINT_MESSAGE.getEnumValue());
			androidAlert.recordingAlertHandlingWithButtonMessage(btnElements.closeButton(),
					CommonTestData.RECORDERSECTION_MESSAGE.getEnumValue());
			Asserts.assertEquals(getTexOfElement(DBSappObject.WelcomeToText()).trim(),
					CommonTestData.WELCOME.getEnumValue(),
					CommonTestData.WELCOME.getEnumValue() + " text is not found");
			Asserts.assertEquals(getTexOfElement(DBSappObject.DigibankText()).trim(),
					CommonTestData.DIGIBANK.getEnumValue(),
					CommonTestData.DIGIBANK.getEnumValue() + " text is not found");
		} catch (Exception e) {
			assertFalse(true);
			throw new Exception(getExceptionMessage(e));

		}
	}

	@Step("Clicked on Login button")
	public void clickOnLoginButton() throws Exception {
		try {
			clickOnElement(DBSappObject.loginButton());
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Clicked on Sign Up For Digibank button")
	public void clickOnSignUpForDigibankButton() throws Exception {
		try {
			clickOnElement(DBSappObject.signUpForDigibankButton());
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Clicked on Pre Login button")
	public void preLoginButton() throws Exception {
		try {
			clickOnElement(DBSappObject.loginButton());
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Enter data in User EditBox")
	public void sendDataInUserId(String text) throws Exception {
		try {
			if (isElementEnable(DBSappObject.userIdEditText()))
				enterTextInTextbox(DBSappObject.userIdEditText(), text);

			Asserts.assertTrue(isElementEnable(DBSappObject.userIdEditText()), "EditField is not enable");
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Enter data in Pin EditBox")
	public void sendDataInUserPin(String text) throws Exception {
		try {
			if (isElementEnable(DBSappObject.userPinEditText()))
				enterTextInTextbox(DBSappObject.userPinEditText(), text);

			Asserts.assertTrue(isElementEnable(DBSappObject.userPinEditText()), "EditField is not enable");
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Application Logout & Verifies the 'Tap on the stars to rate' field Message.")
	public void clickOnLogoutAndVerify(String logoutTextMsg, String Ratingmsg) throws Exception {
		try {
			wait.waitForElementVisibility(DBSappObject.logoutButton()); 
			com.crestech.listeners.TestListener.saveScreenshotPNG(driver);
			AndroidAlert.AlertHandlingWithButtonMessage(DBSappObject.logoutButton(), logoutTextMsg,
					DBSappObject.logoutButton());
			Asserts.assertEquals(getTexOfElement(DBSappObject.postLogoutAlertMessage()), Ratingmsg,
					"'Tap on the stars to rate' Text is not found");
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Verifying Page and clicking on 'SET UP NOW' button ")
	public void verifyPageAndClickOnSetUpNowButton(String expectecMessage) throws Exception {
		try {
			AndroidAlert.AlertHandlingWithButtonMessage(DBSappObject.setUpNowButton(), expectecMessage,
					DBSappObject.tokenSetupMessage());
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
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
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Verifying Page after Digital Token setup after clicking on 'Done' button")
	public void digitalTokenSetUp() throws Exception {
		try {
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
				GestureUtils.scrollUPtoObject("text", "DONE", DBSappObject.doneButton()); 
				String xpath = "//android.widget.Button[@text='DONE']";
				List<RemoteWebElement> list = driver.findElements(By.xpath(xpath));
				if (list.size() > 0) {
					AndroidAlert.AlertHandlingWithButtonMessage(DBSappObject.doneButton(),
							CommonTestData.DIGITAL_TOKEN_MESSAGE_AFTER_STEPUP.getEnumValue(),
							DBSappObject.tokenGetSetupMessage());
				}
			}
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Verifies Remittance Corridor")
	public void VerifyRemittanceCorridor() throws Exception {
		try {
			ClickOnPayAndTransferBtn();
			EnterPasscodeAndDone();
			SelectingPayeeAndFundSourceAfterSelectingOverseas();
			pressEnterKeyAfterEnteringAmount(CommonTestData.CORRIDOR_AMOUNT.getEnumValue());
			ClickOnNextBtnAndVerifiesReviewTransferPage();
			ClickOnTransferNowBtnAndVerifiesTransferSubmittedMsg(CommonTestData.TRANSFER_SUBMITTED_MSG.getEnumValue(),
					DBSappObject.TransferSubmittedImage(), DBSappObject.TransferSubmittedMsg());
			ClickOnImageExpandBtnAndVerifiesReferenceNumberText();
			ClickOnShareTransferDetailsBtnAndVerifiesReferenceNumberText();
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
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
			enterTextInTextbox(DBSappObject.editSearchField(), ExpectedEottName );
			pressKey(driver, Keys.ENTER);
			String xpath ="//android.widget.TextView[@text='"+ExpectedEottName+"']";
			MobileElement ExpectedEottEle = (MobileElement) driver.findElement(By.xpath(xpath));
			isElementVisible(ExpectedEottEle);
			clickOnElement(ExpectedEottEle);
			Asserts.assertEquals(getTexOfElement(DBSappObject.OverseasTransferPage()),
					CommonTestData.OVERSEAS_TRANSFER_PAGEHEADER.getEnumValue(),
					CommonTestData.OVERSEAS_TRANSFER_PAGEHEADER.getEnumValue() + " Text is not found");
			clickOnElement(DBSappObject.SelectFundSourcePage());
			clickOnElement(DBSappObject.SourceFundList().get(0));

			pressEnterKeyAfterEnteringAmount(CommonTestData.eOTT_AMOUNT.getEnumValue());
			GestureUtils.scrollUPtoObject("resource-id", "id/btn_remitnext", DBSappObject.NextBtn());
			clickOnElement(DBSappObject.SelectPurposeOfTransfer());
			clickOnElement(DBSappObject.FundTransferPurposeOption());
			Asserts.assertEquals(getTexOfElement(DBSappObject.TextViewPurpose()),
					CommonTestData.PURPOSE_OF_TRANSFER_TEXT.getEnumValue(),
					CommonTestData.PURPOSE_OF_TRANSFER_TEXT.getEnumValue() + " Text is not found");

			ClickOnNextBtnAndVerifiesReviewTransferPage();
			ClickOnTransferNowBtnAndVerifiesTransferSubmittedMsg(CommonTestData.TRANSFER_SUBMITTED_MSG.getEnumValue(),
					DBSappObject.TransferSubmittedImage(), DBSappObject.TransferSubmittedMsg());
			ClickOnImageExpandBtnAndVerifiesReferenceNumberText();
			ClickOnShareTransferDetailsBtnAndVerifiesReferenceNumberText();
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Verifies Add payee DBSorPOSB.")
	public void VerifyAddPayeeDBSorPOSB() throws Exception {
		try {
			ClickOnPayAndTransferBtn();
			EnterPasscodeAndDone();
			clickOnAddLocalRecipientBtnAndVerifyLocalTransferPayNowPageHeader();
			String randomString = GenerateRandomRecipientName();
			 String ExpectedRecipientName = CommonTestData.PAYEEADD_DBSPOSB_RECIPIENT_NAME.getEnumValue().concat(randomString);
			EnterRecipientDetailsAfterSelectingBankAccountOption(ExpectedRecipientName,CommonTestData.PAYEEADD_DBSPOSB_BANK_NAME.getEnumValue(),
					CommonTestData.PAYEEADD_DBSPOSB_ACCOUNT_NUMBER.getEnumValue());
			ClickOnNextBtnAndReviewRecipientDetails();
			ClickOnAddRecipientNowBtn();
			VerifyYouHaveAddedRecipientMsgAfterEnterSecurePIN();
			verifyValidationForPayeeAdd(ExpectedRecipientName,CommonTestData.PAYEEADD_DBSPOSB_BANK_NAME.getEnumValue(),
					CommonTestData.PAYEEADD_DBSPOSB_ACCOUNT_NUMBER.getEnumValue());
//			clickOnElement(DBSappObject.BackIcon());
			//com.crestech.listeners.TestListener.saveScreenshotPNG(driver);
//			DeletePayee(ExpectedRecipientName);
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Verify 'You Have Added Recipient Msg' After Entering Secure PIN.")
	public void VerifyYouHaveAddedRecipientMsgAfterEnterSecurePIN() throws Exception {
		try {
			EnterPasscodeAndDone();
			Thread.sleep(5000); 
			wait.waitForElementVisibility(DBSappObject.SuccessTickImageView()); 
			com.crestech.listeners.TestListener.saveScreenshotPNG(driver);
			if (isElementVisible(DBSappObject.SuccessTickImageView()))
				Asserts.assertEquals(getTexOfElement(DBSappObject.MainHeaderOrSuccessMsgElement()),
						CommonTestData.YOU_HAVE_ADDED_RECIPIENT_MSG.getEnumValue(),
						CommonTestData.YOU_HAVE_ADDED_RECIPIENT_MSG.getEnumValue() + " Text is not matching");
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Verify Local Transfer Pay Now Page Header After Clicking On Add Local Recipient Page Header.")
	public void clickOnAddLocalRecipientBtnAndVerifyLocalTransferPayNowPageHeader() throws Exception {
		try {
			String xpath = "//android.widget.Button[@text='ADD RECIPIENT NOW']";
			List<RemoteWebElement> list = driver.findElements(By.xpath(xpath));
			if (list.size() > 0) {
				wait.waitForElementVisibility(DBSappObject.AddRecipientNowBtn());
				com.crestech.listeners.TestListener.saveScreenshotPNG(driver);
					clickOnElement(DBSappObject.AddRecipientNowBtn());
			}else {
				wait.waitForElementVisibility(DBSappObject.AddLocalRecipient());
				com.crestech.listeners.TestListener.saveScreenshotPNG(driver);
				clickOnElement(DBSappObject.AddLocalRecipient());
			}
			Asserts.assertEquals(getTexOfElement(DBSappObject.PageHeader()),
					CommonTestData.LOCAL_TRANSFER_PayNow.getEnumValue(),
					CommonTestData.LOCAL_TRANSFER_PayNow.getEnumValue() + " Page Header is not matching");
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Enter Recipient Details Into Bank Account Section.")
	public void EnterRecipientDetailsAfterSelectingBankAccountOption(String ExpectedRecipientName , String BankName, String AccountNumber) throws Exception {
		try {
			wait.waitForElementVisibility(DBSappObject.SelectBankAccount()); 
			com.crestech.listeners.TestListener.saveScreenshotPNG(driver);
			clickOnElement(DBSappObject.SelectBankAccount());
			Asserts.assertEquals(getTexOfElement(DBSappObject.PageHeader()),
					CommonTestData.ENTER_RECIPIENT_DETAILS.getEnumValue(),
					CommonTestData.ENTER_RECIPIENT_DETAILS.getEnumValue() + " Text is not found");
			enterTextInTextbox(DBSappObject.EditFields().get(0), ExpectedRecipientName);
			clickOnElement(DBSappObject.EditFields().get(1));
			clickOnElement(DBSappObject.SearchField());
			enterTextInTextbox(DBSappObject.SearchField(), BankName);
			backButton();
			GestureUtils.scrollUPtoObject("text", "BANK OF INDIA", DBSappObject.SelectBankOFIndia());
			String xpath = "//android.widget.RelativeLayout//android.widget.TextView[@text='"+BankName+"']";
			MobileElement Selectbank = (MobileElement) driver.findElement(By.xpath(xpath));
			clickOnElement(Selectbank);
			enterTextInTextbox(DBSappObject.EditFields().get(2), AccountNumber);
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
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

	    for(int i = 0; i < length; i++) {

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
				wait.waitForElementVisibility(DBSappObject.PasscodeField()); 
				com.crestech.listeners.TestListener.saveScreenshotPNG(driver);
				enterTextInTextbox(DBSappObject.PasscodeField(), CommonTestData.OTP.getEnumValue());
				String doneButtonxpath = "//android.widget.Button[@text='Done']";
				List<RemoteWebElement> doneButtonList = driver.findElements(By.xpath(doneButtonxpath));
				if (doneButtonList.size() > 0)
					clickOnElement(DBSappObject.DoneButtonForPasscode());
			}
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Verifies Overseas Transfer Page Header on the top & Select Payee and fund source from Overseas Transfer page.")
	public void SelectingPayeeAndFundSourceAfterSelectingOverseas() throws Exception {
		try {
			overseasVerifyClick(CommonTestData.OVERSEAS_ICON.getEnumValue());
			Asserts.assertEquals(getTexOfElement(DBSappObject.OverseasTransferPage()),
					CommonTestData.OVERSEAS_TRANSFER_PAGEHEADER.getEnumValue(),
					CommonTestData.OVERSEAS_TRANSFER_PAGEHEADER.getEnumValue() + " Text is not found");
			clickOnElement(DBSappObject.PayeeList().get(2));
			clickOnElement(DBSappObject.SelectFundSourcePage());
			clickOnElement(DBSappObject.SourceFundList().get(2));
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
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
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Verifies Review Transfer Page Header after clicking on Next Button.")
	public void ClickOnNextBtnAndVerifiesReviewTransferPage() throws Exception {
		try {
			GestureUtils.scrollUPtoObject("resource-id", "id/btn_remitnext", DBSappObject.NextBtn());
			clickOnElement(DBSappObject.NextBtn());
			Asserts.assertEquals(getTexOfElement(DBSappObject.ReviewTransferPageHeader()),
					CommonTestData.REVIEW_TRANSFER.getEnumValue(),
					CommonTestData.REVIEW_TRANSFER.getEnumValue() + " Text is not matching");
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Verifies Transfer Submitted Message after clicking on Transfer Now Button.")
	public void ClickOnTransferNowBtnAndVerifiesTransferSubmittedMsg(String SuccessMsg, MobileElement successImage,
			MobileElement transfferdSubmitMsgEle) throws Exception {
		try {
			clickOnElement(DBSappObject.TransferNowBtn());
			if (isElementVisible(successImage))
				Asserts.assertEquals(getTexOfElement(transfferdSubmitMsgEle), SuccessMsg,
						SuccessMsg + " Text is not matching");
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Verifies Reference Number Text after clicking on Image Expand Button.")
	public void ClickOnImageExpandBtnAndVerifiesReferenceNumberText() throws Exception {
		try {
			clickOnElement(DBSappObject.ImageExpand());
			Asserts.assertEquals(getTexOfElement(DBSappObject.ReferenceNumberText()),
					CommonTestData.REFERENCE_NUMBER_TEXT.getEnumValue(),
					CommonTestData.REFERENCE_NUMBER_TEXT.getEnumValue() + " Text is not found");
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Verifies Overseas transfer Message after clicking on Share Transfer Details Button.")
	public void ClickOnShareTransferDetailsBtnAndVerifiesReferenceNumberText() throws Exception {
		try {
			GestureUtils.scrollUPtoObject("text", "SHARE TRANSFER DETAILS", DBSappObject.ShareTransferDetailsBtn());
			clickOnElement(DBSappObject.ShareTransferDetailsBtn());
			Asserts.assertEquals(getTexOfElement(DBSappObject.OverseasTransferMsg()),
					CommonTestData.OVERSEAS_TRANSFER_TEXT.getEnumValue(),
					CommonTestData.OVERSEAS_TRANSFER_TEXT.getEnumValue() + " Text is not found");
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Click On Pay & Transfer Button.")
	public void ClickOnPayAndTransferBtn() throws Exception {
		try {
			wait.waitForElementVisibility(DBSappObject.PayAndTransferBtn()); 
			com.crestech.listeners.TestListener.saveScreenshotPNG(driver);
			if (isElementVisible(DBSappObject.PayAndTransferBtn()))
				clickOnElement(DBSappObject.PayAndTransferBtn());
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Verifying Overseas  icon and click")
	public void overseasVerifyClick(String expectecText) throws Exception {
		try {

			String actualText = getTexOfElement(DBSappObject.overseasLabel());
			Asserts.assertEquals(actualText, expectecText, "Label Not matching");

			if (actualText.equalsIgnoreCase(expectecText))
				clickOnElement(DBSappObject.Btnlist().get(3));

		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
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
			throw new Exception(getExceptionMessage(e));
		}

	}

	@Step("Enter the text in search and select the corresponding value in the dropdown")
	public void sendDataInSearchBoxAndSelectFromDropDown(String searchBoxData, String valueSelectedFromList, MobileElement searchField)
			throws Exception {
		try {
			
			if (isElementEnable(searchField))
				enterTextInTextbox(searchField, searchBoxData);
			wait.waitForElementVisibility(DBSappObject.countryList().get(0));
			List<MobileElement> Elementlist = DBSappObject.countryList();
			List<MobileElement> ElementlistClickable = DBSappObject.dropDowmList();
			int l = Elementlist.size();
			int index = 0;
			String countryFromList = null;
			for (int i = 0; i < l; i++) {
				countryFromList = Elementlist.get(i).getText();
				if (countryFromList.equalsIgnoreCase(valueSelectedFromList)) {
					index++;
					clickOnElement(ElementlistClickable.get(i));

				}
			}
			Asserts.assertTrue(isElementEnable(DBSappObject.locationAutocompleteSearchBox()),
					"SearchField is not enable");
			Asserts.assertTrue(index > 0, "No element found in the lis of corresponding value");

		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Verifying AUD CurrencyType Label and click")
	public void CurrencyTypeVerifyClick(String expectecText) throws Exception {
		try {

			String actualText = getTexOfElement(DBSappObject.currencyLabel());

			if (actualText.equalsIgnoreCase(expectecText))
				clickOnElement(DBSappObject.currencyLabel());

			Asserts.assertEquals(actualText, expectecText, "Currency is Not matching");

		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}

	}

	@Step("Click On Next Button.")
	public void ClickOnNextButton() throws Exception {
		try {
			GestureUtils.scrollUPtoObject("text", "NEXT", DBSappObject.nextButton());
			wait.waitForElementVisibility(DBSappObject.nextButton());
			com.crestech.listeners.TestListener.saveScreenshotPNG(driver);
			if (getTexOfElement(DBSappObject.nextButton()).equalsIgnoreCase("NEXT"))
				clickOnElement(DBSappObject.nextButton());
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}

	}

	@Step("Enter data in Bank Code EditBox")
	public void sendBankCode(String text) throws Exception {
		try {
			if (isElementEnable(DBSappObject.enterBankcodeTextField()))
				enterTextInTextbox(DBSappObject.enterBankcodeTextField(), text);

			Asserts.assertTrue(isElementEnable(DBSappObject.enterBankcodeTextField()), "EditField is not enable");
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Enter Account No EditBox")
	public void sendAccountNo(String text) throws Exception {
		try {
			if (isElementEnable(DBSappObject.recipientAccountNoEditBox()))
				enterTextInTextbox(DBSappObject.recipientAccountNoEditBox(), text);

			Asserts.assertTrue(isElementEnable(DBSappObject.recipientAccountNoEditBox()), "EditField is not enable");
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Enter Full name EditBox")
	public void sendFullName(String text) throws Exception {
		try {
			if (isElementEnable(DBSappObject.recipientNameEditBox()))
				enterTextInTextbox(DBSappObject.recipientNameEditBox(), text);

			Asserts.assertTrue(isElementEnable(DBSappObject.recipientNameEditBox()), "EditField is not enable");
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Enter Address EditBox")
	public void sendAddress(String text) throws Exception {
		try {
			if (isElementEnable(DBSappObject.recipientAddressEditBox()))
				enterTextInTextbox(DBSappObject.recipientAddressEditBox(), text);

			Asserts.assertTrue(isElementEnable(DBSappObject.recipientAddressEditBox()), "EditField is not enable");
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Enter city EditBox")
	public void sendcity(String text) throws Exception {
		try {
			if (isElementEnable(DBSappObject.recipientCityEditBox()))
				enterTextInTextbox(DBSappObject.recipientCityEditBox(), text);

			Asserts.assertTrue(isElementEnable(DBSappObject.recipientCityEditBox()), "EditField is not enable");
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Verify 'REVIEW RECIPIENT'S DETAILS label' field")
	public void verifyRecipientReviewDetailLabel(String expectedText) throws Exception {
		try {
			String actualText = getTexOfElement(DBSappObject.recipientReviewDetailLabel());

			Asserts.assertEquals(actualText, expectedText, "'REVIEW RECIPIENT'S DETAILS label' Text is not found");

		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("clicking On 'ADD RECIPIENT NOW' button")
	public void ClickOnAddRecipientNowBtn() throws Exception {
		try {
			GestureUtils.scrollUPtoObject("text", "ADD RECIPIENT NOW", DBSappObject.AddRecipientNowBtn());
			wait.waitForElementVisibility(DBSappObject.AddRecipientNowBtn()); 
			com.crestech.listeners.TestListener.saveScreenshotPNG(driver);
			String actualText = getTexOfElement(DBSappObject.AddRecipientNowBtn());
			if (actualText.equalsIgnoreCase(CommonTestData.ADD_RECIPIENT_LABEL.getEnumValue()))
				clickOnElement(DBSappObject.AddRecipientNowBtn());
			Thread.sleep(4000); 
			Asserts.assertEquals(actualText, CommonTestData.ADD_RECIPIENT_LABEL.getEnumValue(), "Button not matching");
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}

	}

	@Step("Verify 'Reference No. Field and its value' field and Verify 'MAKE A TRANSFER' Button After Expanding & Scrolling to the Page.")
	public void verifyReferenceFieldAndItsValue(String expectedText) throws Exception {
		try {
			clickOnElement(DBSappObject.expandButton());
			GestureUtils.scrollUPtoObject("text", "Reference No.", DBSappObject.ReferenceNumberText());
			Asserts.assertEquals(getTexOfElement(DBSappObject.makeTransferButton()),
					CommonTestData.MAKE_TRANSFER.getEnumValue(), "'MAKE A TRANSFER' Text is not found");
			Asserts.assertEquals(getTexOfElement(DBSappObject.ReferenceNumberText()), expectedText,
					"'Reference no Field' is not found");
			Asserts.assertTrue(isElementVisible(DBSappObject.referenceNoValue()), "Reference Number not Found");
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Verifying TopUp Label and click")
	public void topUpVerifyClick(String expectecText) throws Exception {
		try {

			String actualText = getTexOfElement(DBSappObject.topUpLabel());

			if (actualText.equalsIgnoreCase(expectecText))
				clickOnElement(DBSappObject.topUpButton());

			Asserts.assertEquals(actualText, expectecText, "Top up Label Not matching");

		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
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
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Enter currency in EditBox")
	public void sendCurrencyInTextField(String text) throws Exception {
		try {
				enterTextInTextbox(DBSappObject.currencyTextBox(), text);

		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Verify 'Top Up Paylah Label' field And Verify 'Enter Amount' field")
	public void verifyReviewTopUpLabel(String expectedText) throws Exception {
		try {
			Asserts.assertEquals(getTexOfElement(DBSappObject.reviewTopUpLabel()), expectedText,
					"'Top Up Paylah' Text is not found");
			Asserts.assertEquals(getTexOfElement(DBSappObject.displayAmount()),
					CommonTestData.AMOUNT_PAYLAH.getEnumValue(), "'Display Amount' is incorrect");
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Verifying TOP UP NOW  Label and click and then Verify 'Top-up Done' field and Display Amount")
	public void topUpNowVerifyClick(String expectecText) throws Exception {
		try {

			String actualText = getTexOfElement(DBSappObject.topUpNowButton());

			if (actualText.equalsIgnoreCase(expectecText))
				clickOnElement(DBSappObject.topUpNowButton());

			Asserts.assertEquals(actualText, expectecText, "TOP UP NOW button Not exist");
			Asserts.assertEquals(getTexOfElement(DBSappObject.topUpDoneLabel()),
					CommonTestData.TOPUP_UP_DONE_LABEL.getEnumValue(), "'Top-up Done' Text is not found");
			Asserts.assertEquals(getTexOfElement(DBSappObject.displayAmount()),
					CommonTestData.AMOUNT_PAYLAH.getEnumValue(), "'Display Amount' is incorrect");
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}

	}

	@Step("Verifying Logout Label and click")
	public void logOutTopUpVerifyClick(String expectecText) throws Exception {
		try {
			String actualText = getTexOfElement(DBSappObject.logOutPaylahButton());
			if (actualText.equalsIgnoreCase(expectecText))
				clickOnElement(DBSappObject.logOutPaylahButton());
			Asserts.assertEquals(actualText, expectecText, "LogOut button Not exist");
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
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
			Asserts.assertEquals(getTexOfElement(DBSappObject.PageHeaderList().get(4)),
					CommonTestData.REVIEW_APPLICATION.getEnumValue(),
					CommonTestData.REVIEW_APPLICATION.getEnumValue() + " Text is not found");
			GestureUtils.scrollUPtoObject("text", "APPLY NOW", DBSappObject.ApplyNowButton());
			clickOnElement(DBSappObject.ApplyNowButton());
			ClickOnSubmitButtonAfterSettingCardPIN();
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	
	@Step("Verifies the Set Card Pin Page Header and then Submit While Entering Confirm and Create New Pin & Verifies the 'Application Submitted' Message.")
	public void ClickOnSubmitButtonAfterSettingCardPIN() throws Exception {
		try {
			Asserts.assertEquals(getTexOfElement(DBSappObject.PageHeaderList().get(5)),
					CommonTestData.SET_CARD_PIN.getEnumValue(),
					CommonTestData.SET_CARD_PIN.getEnumValue() + " Text is not found");
			clickOnElement(DBSappObject.CreateYourPINField());
			enterTextInTextbox(DBSappObject.CreateYourPINField(), CommonTestData.CREATE_PIN.getEnumValue());
			clickOnElement(DBSappObject.ConfirmNewPINField());
			enterTextInTextbox(DBSappObject.ConfirmNewPINField(), CommonTestData.CONFIRM_PIN.getEnumValue());
			driver.hideKeyboard();
			clickOnElement(DBSappObject.submitButton());
			Asserts.assertEquals(getTexOfElement(DBSappObject.PageHeaderList().get(8)),
					CommonTestData.APPLICATION_SUBMITTED.getEnumValue(),
					CommonTestData.APPLICATION_SUBMITTED.getEnumValue() + " Text is not Matching");
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Filling Required Details to applying Debit Card like as 'AccountToBeLinkedToTheCardField',"
			+ " 'Title', 'EnterNameToAppearOnTheCardField', 'Race', 'Marital Status','Residential Type',"
			+ "'Education','Economic Status','Annual Income', 'And Select Checkbox SendMeDBSPrmotionViaMail'.")
	public void FillingDetailsToApplyingDebitCard() throws Exception {
		try {
			// To filling Debit Card Details for applying Debit card.
			clickOnElement(DBSappObject.AccountToBeLinkedToTheCardField());
			clickOnElement(DBSappObject.DebitCardDetailsDropdownList().get(3));
			clickOnElement(DBSappObject.TitleField());
			clickOnElement(DBSappObject.DebitCardDetailsDropdownList().get(2));
			clickOnElement(DBSappObject.EnterNameToAppearOnTheCardField());
			enterTextInTextbox(DBSappObject.EnterNameToAppearOnTheCardField(),
					CommonTestData.NAMETO_APPEAR_ON_DEBITCARD.getEnumValue());
			driver.hideKeyboard();
			GestureUtils.scrollUPtoObject("text", "Education", DBSappObject.EducationField());
			clickOnElement(DBSappObject.RaceField());
			clickOnElement(DBSappObject.DebitCardDetailsDropdownList().get(3));
			clickOnElement(DBSappObject.MaritalStatusField());
			clickOnElement(DBSappObject.DebitCardDetailsDropdownList().get(0));
			clickOnElement(DBSappObject.ResidentialTypeField());
			clickOnElement(DBSappObject.DebitCardDetailsDropdownList().get(0));
			clickOnElement(DBSappObject.EducationField());
			clickOnElement(DBSappObject.DebitCardDetailsDropdownList().get(1));
			clickOnElement(DBSappObject.EconomicStatusField());
			clickOnElement(DBSappObject.DebitCardDetailsDropdownList().get(2));
			clickOnElement(DBSappObject.AnnualIncomeField());
			clickOnElement(DBSappObject.DebitCardDetailsDropdownList().get(2));
			GestureUtils.scrollUPtoObject("text", "NEXT", DBSappObject.nextButton());
			clickOnElement(DBSappObject.SendMeDBSPrmotionViaMail());
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Select Debit Card Option After Clicking on Cards Section and then 2FA Authentication Done.")
	public void SelectDebitCardOptionFromCardsSectionAndAuthenticationOfSecurePIN() throws Exception {
		try {
			GestureUtils.scrollUPtoObject("text", "Cards", DBSappObject.CardsButton());
			if (isElementVisible(DBSappObject.CardsButton()))
				clickOnElement(DBSappObject.CardsButton());
			wait.waitForElementVisibility(DBSappObject.SelectDebitCard());
			clickOnElement(DBSappObject.SelectDebitCard());
			EnterPasscodeAndDone();
			clickOnElement(DBSappObject.DebitCardOption());
			wait.waitForElementVisibility(DBSappObject.AccountToBeLinkedToTheCardField());
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Verifies the Payee Add Bill Payment and after completion payment verifies the transfering amount.")
	public void PayeeAddBillPayment() throws Exception {
		try {
			ClickOnPayAndTransferBtn();
			EnterPasscodeAndDone();
			ClickOnBillModuleAndClickOnAddBillingOrganisation();
			EnterBillingOrganisationDetails(CommonTestData.PAYEEADD_BILLPAYMENT_ACCOUNTNAME.getEnumValue(),CommonTestData.PAYEEADD_BILLPAYMENT_REFERENCENUMBER.getEnumValue());
			ClickOnNextButton();
			Asserts.assertEquals(getTexOfElement(DBSappObject.PageHeader()),
					CommonTestData.REVIEW_RECIPIENT_DETAILS.getEnumValue(),
					CommonTestData.REVIEW_RECIPIENT_DETAILS.getEnumValue() + " Text is not matching.");
			VerifyBillingOrganisationAndBillReferenceNumber(CommonTestData.PAYEEADD_BILLPAYMENT_ACCOUNTNAME.getEnumValue(),CommonTestData.PAYEEADD_BILLPAYMENT_REFERENCENUMBER.getEnumValue());
			ClickOnAddRecipientNowBtn();
			VerifyYouHaveAddedRecipientMsgAfterEnterSecurePIN();
			VerifyBillingOrganisationAndBillReferenceNumber(CommonTestData.PAYEEADD_BILLPAYMENT_ACCOUNTNAME.getEnumValue(),CommonTestData.PAYEEADD_BILLPAYMENT_REFERENCENUMBER.getEnumValue());
			ClickOnMakeAPaymentAndEnterAmountInAmountEditField();
			ClickOnNextButton();
			Asserts.assertEquals(getTexOfElement(DBSappObject.PageHeader()),
					CommonTestData.REVIEW_PAYMENT_PAGEHEADER.getEnumValue(),
					CommonTestData.REVIEW_PAYMENT_PAGEHEADER.getEnumValue() + " Text is not matching");
			ClickOnPayNowBtnAndVerifyPaymentSubmittedMsg();
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Enter Billing Organisation Details.")
	public void EnterBillingOrganisationDetails(String AccountName, String ReferenceNo) throws Exception {
		try {
			Thread.sleep(1000); 
			com.crestech.listeners.TestListener.saveScreenshotPNG(driver);
			clickOnElement(DBSappObject.SelectBillingOrganisation());
			clickOnElement(DBSappObject.SearchForBillingOrganisationField());
			enterTextInTextbox(DBSappObject.SearchForBillingOrganisationField(),AccountName);
			clickOnElement(DBSappObject.SelectSearchedOption());
			clickOnElement(DBSappObject.EnterReferenceNoEditField());
			enterTextInTextbox(DBSappObject.EnterReferenceNoEditField(),ReferenceNo);
			backButton();
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Click On Bill Module & Verify Enter Recipient Page Header After Click On Add Billing Organisation.")
	public void ClickOnBillModuleAndClickOnAddBillingOrganisation() throws Exception {
		try {
			wait.waitForElementVisibility(DBSappObject.BillsButton());
			com.crestech.listeners.TestListener.saveScreenshotPNG(driver);
			clickOnElement(DBSappObject.BillsButton());
			
			String xpath = "//android.widget.Button[@text='ADD RECIPIENT NOW']";
			List<RemoteWebElement> list = driver.findElements(By.xpath(xpath));
			if (list.size() > 0) {
				wait.waitForElementVisibility(DBSappObject.AddRecipientNowBtn());
				com.crestech.listeners.TestListener.saveScreenshotPNG(driver);
					clickOnElement(DBSappObject.AddRecipientNowBtn());
			}else {
				wait.waitForElementVisibility(DBSappObject.AddBillingOrganisation());
				com.crestech.listeners.TestListener.saveScreenshotPNG(driver);
				clickOnElement(DBSappObject.AddBillingOrganisation());
			}
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Click On Make A Payment Button And Enter Amount In Amount Edit Field.")
	public void ClickOnMakeAPaymentAndEnterAmountInAmountEditField() throws Exception {
		try {
			wait.waitForElementVisibility(DBSappObject.MakeAPaymentButton()); 
			com.crestech.listeners.TestListener.saveScreenshotPNG(driver);
			clickOnElement(DBSappObject.MakeAPaymentButton());
			Asserts.assertEquals(getTexOfElement(DBSappObject.MainHeaderOrSuccessMsgElement()),
					CommonTestData.PAY_TO_BILLER_PAGE_HEADER.getEnumValue(),
					CommonTestData.PAY_TO_BILLER_PAGE_HEADER.getEnumValue() + " Text is not matching");
			EnterAmount(DBSappObject.AmountEditableField(), CommonTestData.AMOUNTTO_TRANSFERFUND.getEnumValue());
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Click On Pay Now Button And Verify Payment Submitted Message.")
	public void ClickOnPayNowBtnAndVerifyPaymentSubmittedMsg() throws Exception {
		try {
			wait.waitForElementVisibility(DBSappObject.PayNowButton());
			com.crestech.listeners.TestListener.saveScreenshotPNG(driver);
			clickOnElement(DBSappObject.PayNowButton());
			// verifies the payment completion with expected amount.
			if (isElementVisible(DBSappObject.ImageForPaymentSuccess())) {
				Asserts.assertEquals(getTexOfElement(DBSappObject.PageHeader()),
						CommonTestData.PAYMENT_SUBMITTED.getEnumValue(),
						CommonTestData.PAYMENT_SUBMITTED.getEnumValue() + " Text is not matching");

				Asserts.assertEquals(getTexOfElement(DBSappObject.AmountEditableField()),
						CommonTestData.AMOUNTTO_TRANSFERFUND.getEnumValue() + ".00",
						CommonTestData.AMOUNTTO_TRANSFERFUND.getEnumValue() + " Text is not matching.");
			}
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Verifies Billing Organisation And Bill Reference Number.")
	public void VerifyBillingOrganisationAndBillReferenceNumber(String AccountName , String ReferenceNum) throws Exception {
		try {
			if (isElementVisible(DBSappObject.BillingOrganisation())
					&& isElementVisible(DBSappObject.BillReferenceNo())) {
				Asserts.assertEquals(getTexOfElement(DBSappObject.DBSCASHLINE()),
						AccountName,AccountName + " Text is not matching");
				Asserts.assertEquals(getTexOfElement(DBSappObject.ReferenceNumberValue()),
						ReferenceNum, ReferenceNum + " Text is not matching");
			}
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Verifies the Open Account.")
	public void OpenAccount() throws Exception {
		try {
			ClickOnMoreButton();
			EnterPasscodeAndDone();
			// add scroll if required.
			ClickOnDepositAccountsAnd2FAAuthenticationDone();

			SelectOpenAccountOptionAndVerifyAccountBenifitsPageHeader();

			ClickOnopenAccountInStepButton();
			EnterMonthlySavingsAmtAndSelectSourceOfFundsForSavings();

			ClickOnNextButton();
			VerifyWarningMessageAndImportantNotes();
			// add scroll
			ClickOnIAcknowledgeButtonAndReviewOpenAccountApplication();
			// add scroll
			ClickOnOpenAccountNowButton();
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Select Open Account Option By Clicking And Verify Account Benifits Page Header.")
	public void SelectOpenAccountOptionAndVerifyAccountBenifitsPageHeader() throws Exception {
		try {
			clickOnElement(DBSappObject.SelectOpenAccountOption());
			Asserts.assertEquals(getTexOfElement(DBSappObject.PageHeader()),
					CommonTestData.ACCOUNT_BENIFITS.getEnumValue(),
					CommonTestData.ACCOUNT_BENIFITS.getEnumValue() + " Page Header Text is not matching");
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Enter Monthly Savings Amount And Select Source Of Funds For Savings.")
	public void EnterMonthlySavingsAmtAndSelectSourceOfFundsForSavings() throws Exception {
		try {
			clickOnElement(DBSappObject.EnterMonthlySavingsAmtEditField());
			enterTextInTextbox(DBSappObject.EnterMonthlySavingsAmtEditField(),
					CommonTestData.MONTHLY_SAVING_AMT_BALANCE.getEnumValue());
			backButton();
			clickOnElement(DBSappObject.SelectSourceOfFundsForSavingsDropdown());
			clickOnElement(DBSappObject.SelectSourceOfFundsForSavingsDropdownList().get(0));
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Click On Open Account In 2/3 Step Button.")
	public void ClickOnopenAccountInStepButton() throws Exception {
		try {
			clickOnElement(DBSappObject.StepOpenAccountButton());
			Asserts.assertEquals(getTexOfElement(DBSappObject.PageHeader()), CommonTestData.OPEN_ACCOUNT.getEnumValue(),
					CommonTestData.OPEN_ACCOUNT.getEnumValue() + " Page Header Text is not matching");
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Click On Deposit Accounts Module And 2FA Authentication Done And Verifies the Open Account Page Header.")
	public void ClickOnDepositAccountsAnd2FAAuthenticationDone() throws Exception {
		try {
			clickOnElement(DBSappObject.DepositAccountsModule());
			EnterPasscodeAndDone();
			Asserts.assertEquals(getTexOfElement(DBSappObject.PageHeader()), CommonTestData.OPEN_ACCOUNT.getEnumValue(),
					CommonTestData.OPEN_ACCOUNT.getEnumValue() + " Page Header Text is not matching");
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
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
			throw new Exception(getExceptionMessage(e));
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
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Click On IAcknowledge Button And Review Open Account Application.")
	public void ClickOnIAcknowledgeButtonAndReviewOpenAccountApplication() throws Exception {
		try {
			clickOnElement(DBSappObject.IACKNOWLEDGEButton());
			Asserts.assertEquals(getTexOfElement(DBSappObject.PageHeader()),
					CommonTestData.REVIEW_ACCOUNT_APPLICATION.getEnumValue(),
					CommonTestData.REVIEW_ACCOUNT_APPLICATION.getEnumValue() + " Text is not matched.");
			if (isElementVisible(DBSappObject.YouAreOpeningText()))
				Asserts.assertEquals(getTexOfElement(DBSappObject.POSBSayeAccount()), "POSB SAYE Account",
						" 'POSB SAYE Account' is not matched or found.");
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Verifies the Payee Add Local OtherBank and verifies 'YOU HAVE ADDED RECIPIENT MSG' .")
	public void PayeeAddLocalOtherBank() throws Exception {
		try {
			ClickOnPayAndTransferBtn();
			EnterPasscodeAndDone();
			clickOnLocalButton();
			clickOnAddLocalRecipientBtnAndVerifyLocalTransferPayNowPageHeader();
			
			 String randomString = GenerateRandomRecipientName();
			 String ExpectedRecipientName = CommonTestData.LOCAL_RECIPIENT_NAME.getEnumValue().concat(randomString);
			 System.out.println("ExpectedRecipientName is: " + ExpectedRecipientName);
			
			 int randomInt = GenerateRandomInt();
			 String s=String.valueOf(randomInt);		  
			    System.out.println("random number:"+s);
			    String ExpectedAccountNumber = CommonTestData.LOCAL_RECIPIENT_ACCOUNT_NUMBER.getEnumValue().concat(s);
				 System.out.println("ExpectedAccountNumber is: " + ExpectedAccountNumber);
			 
			EnterRecipientDetailsAfterSelectingBankAccountOption(ExpectedRecipientName,
					CommonTestData.LOCAL_RECIPIENT_BANK_NAME.getEnumValue(), ExpectedAccountNumber);
			ClickOnNextBtnAndReviewRecipientDetails();
			ClickOnAddRecipientNowBtn();
			VerifyYouHaveAddedRecipientMsgAfterEnterSecurePIN();
			verifyValidationForPayeeAdd(ExpectedRecipientName,
					CommonTestData.LOCAL_RECIPIENT_BANK_NAME.getEnumValue(), ExpectedAccountNumber);
//			clickOnElement(DBSappObject.BackIcon());
//			com.crestech.listeners.TestListener.saveScreenshotPNG(driver);
//			DeletePayee(ExpectedRecipientName);
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}
	@Step("Click On Local Button.")
	public void clickOnLocalButton() throws Exception {
		try {
			wait.waitForElementVisibility(DBSappObject.LocalButton());
			clickOnElement(DBSappObject.LocalButton());
			com.crestech.listeners.TestListener.saveScreenshotPNG(driver);
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}
	
	
	@Step("Verifies Visibilty of 'logout' and 'make a transfer' button and Verifies the recipient name, account number, bank name.")
	public void verifyValidationForPayeeAdd(String ExpectedRecipientName , String BankName, String AccountNumber) throws Exception { 
		try {
			Asserts.assertTrue(DBSappObject.LogoutBtn().isDisplayed(), "Log Out Button not found.");
			Asserts.assertTrue(DBSappObject.makeTransferButton().isDisplayed(), "Make A Transfer Button not found.");
			String[] ExpTitleList = new String[] {  "Recipient's Name", "Country",  "Recipient's Bank", "Recipient's Account No.", "Reference No." };
			
				for(int i = 0; i< DBSappObject.PayeeTitleList().size(); i++){
				Asserts.assertEquals(getTexOfElement(DBSappObject.PayeeTitleList().get(i)),
						ExpTitleList[i], ExpTitleList[i] + "Titles is not matching after adding payee"); 
			      }
				
				Asserts.assertEquals(getTexOfElement(DBSappObject.PayeeValueList().get(0)),
						ExpectedRecipientName, ExpectedRecipientName + " is not matching after adding payee"); 
				Asserts.assertEquals(getTexOfElement(DBSappObject.PayeeValueList().get(2)),
						BankName,BankName + " is not matching after adding payee"); 
				Asserts.assertEquals(getTexOfElement(DBSappObject.PayeeValueList().get(3)),
						AccountNumber, AccountNumber + " is not matching after adding payee");
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		} 
	}
	
	@Step("Delete Payee.")
	public void DeletePayee(String ExpectedRecipientName) throws Exception { 
		try {
			ClickOnLocalAndDeletePayeeToIcon();
			ClickOnMoreOptionBtnAndDeletePayeeBtn();
			ClickOnYesBtn();
			
		String ErrorissueXpath ="//android.widget.TextView[@text='You may be facing some delays and we are trying to sort it out now. Sorry for the inconvenience. Do check back later.']";
		List<RemoteWebElement> list = driver.findElements(By.xpath(ErrorissueXpath));
		
	//	"This service isn't available right now. You can try again soon, or call 1800 111 1111 for assistance."
		
			if (list.size() > 0) {
				clickOnElement(DBSappObject.OKButton());
				wait.waitForElementVisibility(DBSappObject.PayeeAddedExpandableIconList().get(0)); 
				com.crestech.listeners.TestListener.saveScreenshotPNG(driver);
				clickOnElement(DBSappObject.PayeeAddedExpandableIconList().get(0));
				wait.waitForElementVisibility(DBSappObject.payee_details_title_name());
				com.crestech.listeners.TestListener.saveScreenshotPNG(driver);
				Asserts.assertEquals(getTexOfElement(DBSappObject.payee_details_title_name()),
						CommonTestData.RECIPIENT_DETAILS_PAGEHEADER.getEnumValue(),
						CommonTestData.RECIPIENT_DETAILS_PAGEHEADER.getEnumValue()
								+ " is not matching after adding payee");
				ClickOnMoreOptionBtnAndDeletePayeeBtn();
				ClickOnYesBtn();
			}
			ClickOnOkButtonAfterVerifyingPayeeDeletedMsg(ExpectedRecipientName);
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}
	
	@Step("Click On OK Button after verifying 'Payee Name deleted' message.")
	public void ClickOnOkButtonAfterVerifyingPayeeDeletedMsg(String ExpectedRecipientName) throws Exception {
		try {
			String payeeName = ExpectedRecipientName + " deleted";
			System.out.println(payeeName);
			String xpath = "//android.widget.TextView[@text='" + payeeName + "']";
			MobileElement payeeNames = (MobileElement) driver.findElement(By.xpath(xpath));
			if (isElementVisible(payeeNames))
				clickOnElement(DBSappObject.OKButton());
			Thread.sleep(1000);
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}
	
	@Step("Click On Yes Button after verifying 'Are you sure to delete payee' message.")
	public void ClickOnYesBtn() throws Exception {
		try {
			if (isElementVisible(DBSappObject.AreYouSureToDeleteThisPayeeMessage()))
				clickOnElement(DBSappObject.YesBtn());
			wait.waitForElementVisibility(DBSappObject.OKButton()); 
			com.crestech.listeners.TestListener.saveScreenshotPNG(driver);
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}
	
	@Step("Click On Local And 'PayeeAdded Recipient Details Showing Icon' Button.")
	public void ClickOnLocalAndDeletePayeeToIcon() throws Exception { 
		try {
			wait.waitForElementVisibility(DBSappObject.LocalButton());
			clickOnElement(DBSappObject.LocalButton());
			wait.waitForElementVisibility(DBSappObject.PayeeAddedExpandableIconList().get(0));
			com.crestech.listeners.TestListener.saveScreenshotPNG(driver);
			clickOnElement(DBSappObject.PayeeAddedExpandableIconList().get(0));
			wait.waitForElementVisibility(DBSappObject.payee_details_title_name()); 
			com.crestech.listeners.TestListener.saveScreenshotPNG(driver);
			Asserts.assertEquals(getTexOfElement(DBSappObject.payee_details_title_name()),
					CommonTestData.RECIPIENT_DETAILS_PAGEHEADER.getEnumValue(),
					CommonTestData.RECIPIENT_DETAILS_PAGEHEADER.getEnumValue()
							+ " is not matching after adding payee");
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}
	
	@Step("Click On 'More Options' Button And 'Delete payee' Button.")
	public void ClickOnMoreOptionBtnAndDeletePayeeBtn() throws Exception {
		try {
			clickOnElement(DBSappObject.MoreOptionBtn());
			wait.waitForElementVisibility(DBSappObject.DeletePayeeBtn()); 
			com.crestech.listeners.TestListener.saveScreenshotPNG(driver);
			clickOnElement(DBSappObject.DeletePayeeBtn());
			wait.waitForElementVisibility(DBSappObject.AreYouSureToDeleteThisPayeeMessage()); 
			com.crestech.listeners.TestListener.saveScreenshotPNG(driver);
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e)); 
		}
	}

	@Step("Click On Next Btn And Review Recipient Details.")
	public void ClickOnNextBtnAndReviewRecipientDetails() throws Exception {
		try {
			wait.waitForElementVisibility(DBSappObject.NextButtonToAddedLocalRecipient()); 
			com.crestech.listeners.TestListener.saveScreenshotPNG(driver);
			clickOnElement(DBSappObject.NextButtonToAddedLocalRecipient());
			Asserts.assertEquals(getTexOfElement(DBSappObject.PageHeader()),
					CommonTestData.REVIEW_RECIPIENT_DETAILS.getEnumValue(),
					CommonTestData.REVIEW_RECIPIENT_DETAILS.getEnumValue() + " Text is not found");
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("verify balance on peek balance popup in prelogin page should be same as current account balance in dashboard with current date and time.")
	public void VerifyPeekBalance() throws Exception {
		try {
			ClickOnMoreButton();
			EnterPasscodeAndDone();
			// ToDo
			// validations pending due to some error getting on provided credentials.
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Click On More Button and then 2FA Authentication Done.")
	public void ClickOnMoreButton() throws Exception {
		try {
			if (isElementVisible(DBSappObject.MoreBtn()))
				clickOnElement(DBSappObject.MoreBtn());

			String xpath = "//android.widget.ImageView[@content-desc='CLOSE']";
			List<RemoteWebElement> list = driver.findElements(By.xpath(xpath));
			if (list.size() > 0)
				clickOnElement(DBSappObject.CloseButton());
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Verify 'You have added a Recipient ' after excecuting Payee Add Remittance Case.")
	public void PayeeAddRemittance() throws Exception {
		try {
			ClickOnPayAndTransferBtn();
			EnterPasscodeAndDone();
			overseasVerifyClick(CommonTestData.OVERSEAS_ICON.getEnumValue());
			ClickOnAddRecipientNowBtn();
			sendDataInSearchBoxAndSelectFromDropDown(CommonTestData.COUNTRY_AUS.getEnumValue(),
					CommonTestData.COUNTRY_AUS.getEnumValue(),DBSappObject.locationAutocompleteSearchBox());
			CurrencyTypeVerifyClick(CommonTestData.CURRENCY_AUS.getEnumValue());
			ClickOnNextButton();
			sendBankCode(CommonTestData.BANK_BCD_CODE.getEnumValue());
			ClickOnNextButton();
			sendAccountNo(CommonTestData.ACCOUNT_NO.getEnumValue());
			sendFullName(CommonTestData.FULL_NAME.getEnumValue());
			GestureUtils.scrollUPtoObject("text", "NEXT", DBSappObject.nextButton());
			sendAddress(CommonTestData.ADDRESS.getEnumValue());
			sendcity(CommonTestData.CITY.getEnumValue());
			ClickOnNextButton();
			verifyRecipientReviewDetailLabel(CommonTestData.REVIEW_RECIPIENT_LABEL.getEnumValue());
			ClickOnAddRecipientNowBtn();
			Asserts.assertEquals(getTexOfElement(DBSappObject.MainHeaderOrSuccessMsgElement()),
					CommonTestData.YOU_HAVE_ADDED_RECIPIENT_MSG.getEnumValue(),
					"'You've added a recipient label' Text is not matching");
			verifyReferenceFieldAndItsValue(CommonTestData.REFERENCE_NUMBER.getEnumValue());
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
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
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Enter the text in search and select the corresponding value in the dropdown")
	public void sendDataInCommonSearchBoxAndSelectFromDropDown(String searchBoxData, String valueSelectedFromList,
			String ExpecetedText, MobileElement PageHeader) throws Exception {
		try {
			clickOnElementOnEnable(DBSappObject.searchIcon());
			if (isElementEnable(DBSappObject.searchBox()))
				enterTextInTextbox(DBSappObject.searchBox(), searchBoxData);
			wait.waitForElementVisibility(DBSappObject.searchTextElement().get(0));
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
			verifyPageHeader(ExpecetedText, PageHeader);
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Verifying page header")
	public void verifyPageHeader(String expectedText, MobileElement ele) throws Exception {
		try {
			if (ele != null)
				Asserts.assertEquals(getTexOfElement(ele), expectedText, "'Header Title' is not Matching");
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	public void VerifyButtonLabelAndClick(MobileElement Button, String expectecText) throws Exception {
		try {
			String actualText = getTexOfElement(Button);
			Asserts.assertEquals(actualText, expectecText, "button Not exist");
			if (actualText.equalsIgnoreCase(expectecText))
				clickOnElement(Button);
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Click on 'To Other Banks Limit' Button and then Verifying page header 'Transfer to Other Banks'")
	public void ClickOnToOtherBankLimit() throws Exception {
		try {
			VerifyButtonLabelAndClick(DBSappObject.toOtherBankTextButton(),
					CommonTestData.TO_OTHERBANK_LABEL.getEnumValue());
			verifyPageHeader(CommonTestData.TRANSFER_TO_OTHERBANK_LABEL.getEnumValue(), DBSappObject.PageHeader());
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("click On 'set current Limit' and verify page header. ")
	public void verifyClickSetCurrentLimit() throws Exception {
		try {
			clickOnElementOnEnable(DBSappObject.currentLimitTextButton());
			verifyPageHeader(CommonTestData.SET_DAILY_LIMIT_TITLE.getEnumValue(), DBSappObject.PageHeader());
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	public String handlingSetCurrentLimit() throws Exception {
		try {
			String currentText = getTexOfElement(DBSappObject.currentLimitTextButton());
			String[] arrOfStr = currentText.split(" ");
			verifyClickSetCurrentLimit();
			String selectedValue = null;
			if (arrOfStr[1].equalsIgnoreCase(CommonTestData.SELECTED_LIMIT_3.getEnumValue())) {
				selectAmountFromSetCurrentLimitList(CommonTestData.SELECTED_LIMIT_2.getEnumValue());
				selectedValue = CommonTestData.SELECTED_LIMIT_2.getEnumValue();
			} else if (arrOfStr[1].equalsIgnoreCase(CommonTestData.SELECTED_LIMIT_2.getEnumValue())) {
				selectAmountFromSetCurrentLimitList(CommonTestData.SELECTED_LIMIT_1.getEnumValue());
				selectedValue = CommonTestData.SELECTED_LIMIT_1.getEnumValue();
			} else if (arrOfStr[1].equalsIgnoreCase(CommonTestData.SELECTED_LIMIT_1.getEnumValue())) {
				selectAmountFromSetCurrentLimitList(CommonTestData.SELECTED_LIMIT_2.getEnumValue());
				selectedValue = CommonTestData.SELECTED_LIMIT_2.getEnumValue();
			}
			return selectedValue;
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Select amount from the List of 'set current Limit' ")
	public void selectAmountFromSetCurrentLimitList(String amount) throws Exception {
		try {
			List<MobileElement> Elementlist = DBSappObject.currentLimitAmountValue();
			int l = Elementlist.size();
			int index = 0;
			String elementFromList = null;
			for (int i = 0; i < l; i++) {
				elementFromList = Elementlist.get(i).getText();
				if (elementFromList.equalsIgnoreCase(amount)) {
					index++;
					clickOnElement(Elementlist.get(i));
				}
			}
			Asserts.assertTrue(index > 0, "No element found in the list of corresponding value");
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Click On 'CHANGE DAILY LIMIT NOW' BUTTON from Review Daily limit page and Verify 'Local Transfer Limit Changed!' Title  ")
	public void verifyClickChangeDailyLimitNowButton() throws Exception {
		try {
			verifyPageHeader(CommonTestData.REVIEW_DAILY_LIMIT_TITLE.getEnumValue(), DBSappObject.PageHeader());
			clickOnElementOnEnable(DBSappObject.changeDailyLimitButton());
			EnterPasscodeAndDone();
			Asserts.assertEquals(getTexOfElement(DBSappObject.successTitleLabel()),
					CommonTestData.LOCAL_TRANSFER_CAHNGE_TITLE.getEnumValue(), "'Header Title' is not Matching");
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Verifying and click 'BACK TO MORE' BUTTON ")
	public void verifyClickBackToMoreButton() throws Exception {
		try {
			clickOnElementOnEnable(DBSappObject.backToMoreButton());
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Verify Amount display After Limit get Changed")
	public void verifyDisplayAmountLocalTransferLimitChange(String expectedText) throws Exception {
		try {
			String currentText = getTexOfElement(DBSappObject.currentLimitTextButton());
			String[] arrOfStr = currentText.split(" ");
			String acutalText = arrOfStr[1];
			Asserts.assertEquals(acutalText, expectedText, "'Amount display' After Limit get Changed is Wrong");
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Change local fund transfer limit verification")
	public void ChangeLocalFundsTransferLimit() throws Exception {
		try {
			ClickOnMoreButton();
			EnterPasscodeAndDone();
			sendDataInCommonSearchBoxAndSelectFromDropDown(CommonTestData.LOCAL_TRANSFER_LIMIT_LABEL.getEnumValue(),
					CommonTestData.LOCAL_TRANSFER_LIMIT_LABEL.getEnumValue(),
					CommonTestData.LOCAL_TRANSFER_LIMIT_LABEL.getEnumValue(), DBSappObject.PageHeader());
			ClickOnToOtherBankLimit();
			String amountSlected = handlingSetCurrentLimit();
			ClickOnNextButton();
			verifyClickChangeDailyLimitNowButton();
			verifyClickBackToMoreButton();
			sendDataInCommonSearchBoxAndSelectFromDropDown(CommonTestData.LOCAL_TRANSFER_LIMIT_LABEL.getEnumValue(),
					CommonTestData.LOCAL_TRANSFER_LIMIT_LABEL.getEnumValue(),
					CommonTestData.LOCAL_TRANSFER_LIMIT_LABEL.getEnumValue(), DBSappObject.PageHeader());
			ClickOnToOtherBankLimit();
			verifyDisplayAmountLocalTransferLimitChange(amountSlected);
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}
	@Step("Click on 'All Tab' section")
	public void clickAndVerifyOnAllTab() throws Exception {
		try {
			VerifyButtonLabelAndClick(DBSappObject.AllTab(),CommonTestData.ALL_SECTION.getEnumValue());	
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}

	}
	@Step("Click on 'DBS CURRENT ACCOUNT' after selecting 'Local Recipients' and verify 'Transfer to DBS/POSB' Title")
	public void selectLocalRecientAndClickingOnDbsCurrentAccount() throws Exception {
		try {
			VerifyButtonLabelAndClick(DBSappObject.localRecipientsTextButton(),CommonTestData.LOCAL_RECIPIENT_FROMLIST.getEnumValue());
			GestureUtils.scrollUPtoObject("text", CommonTestData.DBS_CURRENT_ACCOUNT_TEXT.getEnumValue(), DBSappObject.dbsCurrentAccountOption() );
			VerifyButtonLabelAndClick(DBSappObject.dbsCurrentAccountOption(),CommonTestData.DBS_CURRENT_ACCOUNT_TEXT.getEnumValue());
			verifyPageHeader(CommonTestData.TRANSFER_DBS_POSB.getEnumValue(), DBSappObject.PageHeader());
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}

	}
	@Step("Click on 'Select Fund Source' and Select DBS Multiplier Account")
	public void selectFundSourceAndSelectDBSMultiplierAccount() throws Exception {
		try {
			String xpath = "//android.widget.TextView[@text='Select Fund Source']";
			List<RemoteWebElement> list = driver.findElements(By.xpath(xpath));
			if (list.size() > 0) {
			VerifyButtonLabelAndClick(DBSappObject.selectFundSourceTextButton(),CommonTestData.SELECT_SOURCE_FUND.getEnumValue());
			VerifyButtonLabelAndClick(DBSappObject.dbsMultiplierAccountTextButton(),CommonTestData.DBS_MULTIPLIER_ACCOUNT_TEXT.getEnumValue());
			AndroidAlert.AlertHandlingWithButtonMessage(DBSappObject.OKButton(),CommonTestData.PRIMARY_SOURCE_ALERT_TITLE.getEnumValue(),
					DBSappObject.primarysourceAlertTitle());
			verifyPageHeader(CommonTestData.TRANSFER_DBS_POSB.getEnumValue(), DBSappObject.PageHeader());
			}
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}

	}
	@Step("Verify 'SGD Currency Field' and Enter Amount '11'")
	public void enterAmountAndVerifySgdCurrency(String Amount) throws Exception {
		try {
			Asserts.assertEquals(getTexOfElement(DBSappObject.sgdFieldText()), CommonTestData.SGD_CURRENCY_LABEL.getEnumValue(), "'Currency' is not Matching");
			enterTextInTextbox(DBSappObject.amountTransferTextBox(), Amount);
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}

	}
	@Step(" Verifying page header 'Review Transfer' And Click on 'TRANSFER NOW' Button")
	public void verifyReviewTransferAndClickTransferNowButton() throws Exception {
		try {
			verifyPageHeader( CommonTestData.REVIEW_TRANSFER_LABEL.getEnumValue(), DBSappObject.PageHeader());
			VerifyButtonLabelAndClick(DBSappObject.TransferNowBtn(),CommonTestData.TRANSFER_NOW_BUTTON.getEnumValue());
			
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}

	}
	@Step(" Verifying page header 'Transferred' And Click on 'Logout' Button")
	public void verifyTransferredTitleAndClickOnLogout() throws Exception {
		try {
			verifyPageHeader( CommonTestData.TRANSFER_TITLE.getEnumValue(), DBSappObject.PageHeader());
			logOutTopUpVerifyClick("Log Out");
			VerifyButtonLabelAndClick(DBSappObject.TransferNowBtn(),CommonTestData.TRANSFER_NOW_BUTTON.getEnumValue());
			Asserts.assertEquals(getTexOfElement(DBSappObject.postLogoutAlertMessage()), CommonTestData.RATE_MESSAGE.getEnumValue(),
					"'Tap on the stars to rate' Text is not found");
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}

	}
	@Step("Verifies FundTransfer Other DBS/POSB")
	public void FundTransferOtherBank() throws Exception {
		try {
			 
			ClickOnPayAndTransferBtn();
			EnterPasscodeAndDone();
			clickAndVerifyOnAllTab();
			selectLocalRecientAndClickingOnDbsCurrentAccount();
			selectFundSourceAndSelectDBSMultiplierAccount();
            enterAmountAndVerifySgdCurrency("11");
            ClickOnNextButton();
            verifyReviewTransferAndClickTransferNowButton();
            verifyTransferredTitleAndClickOnLogout();
             
		} catch (Exception e) {
			throw new Exception(CommonAppiumTest.getExceptionMessage(e)); 
		}
	}
	

	

	@Step("Verify Fund Transfer For Own Account.")
	public void VerifyFundTransfer_OwnAccount() throws Exception {
		try {
			ClickOnPayAndTransferBtn();
			EnterPasscodeAndDone();
			SelectAllTAB();
			SelectOWNAccountAndAnyAccountOption();
			SelectFundSourceAccount(1);
			EnterAmount(DBSappObject.AmountEditableField(), CommonTestData.AMOUNTTO_TRANSFERFUND.getEnumValue());
			ClickOnNextButton();
			Asserts.assertEquals(getTexOfElement(DBSappObject.MainHeaderOrSuccessMsgElement()),
					CommonTestData.REVIEW_TRANSFER.getEnumValue(),
					CommonTestData.REVIEW_TRANSFER.getEnumValue() + " Text is not matching");
			ClickOnTransferNowBtnAndVerifiesTransferSubmittedMsg(CommonTestData.TRANSFERRED.getEnumValue(),
					DBSappObject.ImageForPaymentSuccess(), DBSappObject.PageHeader());
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Select All TAB.")
	public void SelectAllTAB() throws Exception {
		try {
			clickOnElement(DBSappObject.AllTab());
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Select 'Your DBS/POSB Accounts' and then verify 'Transfer to Your Account' Page header after selecting any own account option.")
	public void SelectOWNAccountAndAnyAccountOption() throws Exception {
		try {
			clickOnElement(DBSappObject.SelectOwnAccount());
			clickOnElement(DBSappObject.SelectOwnAccountNumber());
			verifyPageHeader(CommonTestData.TRANSFER_TO_YOUR_ACCOUNT.getEnumValue(), DBSappObject.PageHeader());
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Select Any Fund Source Account After clicking on add sign for select fund source.")
	public void SelectFundSourceAccount(int index) throws Exception {
		try {

			String xpath = "//android.widget.TextView[@text='Select Fund Source']";
			List<RemoteWebElement> list = driver.findElements(By.xpath(xpath));
			if (list.size() > 0) {
				clickOnElement(DBSappObject.SelectFundSourcePage());
				clickOnElement(DBSappObject.SelectLocalRecipientToAccount().get(index));
				if (isElementVisible(DBSappObject.PrimarySourceOfFund()))
					clickOnElement(DBSappObject.OKButton());
			}
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Enter Amount In Editable field to transfer fund.")
	public void EnterAmount(MobileElement editField, String textToEnter) throws Exception {
		try {
			wait.waitForElementVisibility(editField); 
			com.crestech.listeners.TestListener.saveScreenshotPNG(driver);
			clickOnElement(editField);
			enterTextInTextbox(editField, textToEnter);
			backButton();
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Verify Fund Transfer For Other Bank Non Fast transfer when future date selected.")
	public void FundsTransfer_OtherBank_NonFASTFuture() throws Exception {
		try {
			ClickOnPayAndTransferBtn();
			EnterPasscodeAndDone();
			SelectAllTAB();
			SelectLocalRecipientsAccount();
			DisableToTransferViaFastToggle();
			EnterAmount(DBSappObject.EditFields().get(0), "Non Fast");
			GestureUtils.scrollDOWNtoObject("text", "Immediate", DBSappObject.TransferDateTextElement());
			SelectFundSourceAccount(0);
			SelectFutureDateThroughCalendar();
			EnterAmount(DBSappObject.AmountEditableField(), CommonTestData.AMOUNTTO_TRANSFERFUND.getEnumValue());
			ClickOnNextButton();
			VerifyReviewTransferPageAndNonFastServiceInReview();
			ClickOnTransferNowBtnAndVerifiesTransferSubmittedMsg(CommonTestData.TRANSFER_SUBMITTED_MSG.getEnumValue(),
					DBSappObject.ImageForPaymentSuccess(), DBSappObject.TransferSuccessMsgElement());
			VerifyVisibiltyOfSomeElements();
			String FromAccountname = getTexOfElement(DBSappObject.AccountNameList().get(0));
			String FromAccountNo = getTexOfElement(DBSappObject.AccountNumberList().get(0));
			System.out.println(FromAccountname);
			System.out.println(FromAccountNo);
			ClickOnExpandbtnAndBackBtn();
			ClickOnMoreButton();
			EnterPasscodeAndDone();
			ClickOnTransactionHistory();
			selectTimeAndAccountTypeForStatement(DBSappObject.DepositAccountList().get(1));
			ClickOnShowButtonAndVerifyHeader(FromAccountname);
			ValadateTransactionHistoryListInThreeMonth();
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Verify Fund Transfer For Other Bank Non Fast transfer.")
	public void FundsTransfer_OtherBank_NonFAST() throws Exception {
		try {
			ClickOnPayAndTransferBtn();
			EnterPasscodeAndDone();
			SelectAllTAB();
			SelectLocalRecipientsAccount();
			DisableToTransferViaFastToggle();
			EnterAmount(DBSappObject.EditFields().get(0), "Non Fast");
			GestureUtils.scrollDOWNtoObject("text", "Immediate", DBSappObject.TransferDateTextElement());
			SelectFundSourceAccount(0);
			String ExpectedSelectedDate = getTexOfElement(DBSappObject.TransferDateTextElement());
			Asserts.assertEquals("Immediate", ExpectedSelectedDate, "Selected Date is not Matching");
			EnterAmount(DBSappObject.AmountEditableField(), CommonTestData.AMOUNTTO_TRANSFERFUND.getEnumValue());
			ClickOnNextButton();
			VerifyReviewTransferPageAndNonFastServiceInReview();
			ClickOnTransferNowBtnAndVerifiesTransferSubmittedMsg(CommonTestData.TRANSFER_SUBMITTED_MSG.getEnumValue(),
					DBSappObject.ImageForPaymentSuccess(), DBSappObject.TransferSuccessMsgElement());
			VerifyVisibiltyOfSomeElements();
			String FromAccountname = getTexOfElement(DBSappObject.AccountNameList().get(0));
			String FromAccountNo = getTexOfElement(DBSappObject.AccountNumberList().get(0));
			System.out.println(FromAccountname);
			System.out.println(FromAccountNo);
			ClickOnExpandbtnAndBackBtn();
			ClickOnMoreButton();
			EnterPasscodeAndDone();
			ClickOnTransactionHistory();
			selectTimeAndAccountTypeForStatement(DBSappObject.DepositAccountList().get(1));
			ClickOnShowButtonAndVerifyHeader(FromAccountname);
			ValadateTransactionHistoryListInThreeMonth();
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}
	
	@Step("Click On 'Expandable btn' And scroll Down to 'Reference No.' text then Click On Back Button.")
	public void ClickOnExpandbtnAndBackBtn() throws Exception {
		try {
			clickOnElement(DBSappObject.FooterExpandableBtn());
			GestureUtils.scrollUPtoObject("text", "Reference No.", DBSappObject.ReferenceNumberText());
			clickOnElement(DBSappObject.BackIcon());
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}
	
	@Step("Verifies the 'Log out', 'Make Another Transfer' Button and 'Transferred Amount Value' after transferring the fund.")
	public void VerifyVisibiltyOfSomeElements() throws Exception {
		try {
			Asserts.assertTrue(DBSappObject.LOGOUTButton().isDisplayed(), "Log Out Button not found.");
			Asserts.assertTrue(DBSappObject.MakeAnotherTransferBtn().isDisplayed(), "Make Another Transfer Button not found.");
			Asserts.assertEquals(getTexOfElement(DBSappObject.SendingAmountElement()),
					CommonTestData.AMOUNTTO_TRANSFERFUND.getEnumValue() + ".00 ", "Amount is not matching");
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e)); 
		}
	}
	
	@Step("Verify 'Review Transfer' Page And 'Non-Fast' Service In Review")
	public void VerifyReviewTransferPageAndNonFastServiceInReview() throws Exception {
		try {
			Asserts.assertEquals(getTexOfElement(DBSappObject.PageHeaderList().get(0)),
					CommonTestData.REVIEW_TRANSFER.getEnumValue(),
					CommonTestData.REVIEW_TRANSFER.getEnumValue() + " Text is not matching");

			GestureUtils.scrollUPtoObject("text", "TRANSFER NOW", DBSappObject.TransferNowBtn());
			Asserts.assertTrue(DBSappObject.NonFastTransactionService().isDisplayed(),
					"Non-Fast Service not available in review.");
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));  
		}
	}
	
	@Step("Verify 'Transfer To Other Bank' Page Header and Click on 'TransferViaFast Toggle' to disable fast service.")
	public void DisableToTransferViaFastToggle() throws Exception {
		try {
			verifyPageHeader(CommonTestData.TRANSFER_TO_OTHERBANK_LABEL_LABEL.getEnumValue(),
					DBSappObject.PageHeaderList().get(0));
			GestureUtils.scrollUPtoObject(null, null, DBSappObject.TransferViaFastTransferToggle());
			clickOnElement(DBSappObject.TransferViaFastTransferToggle());
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e)); 
		}
	}

	@Step("Click on 'Transaction History' Button and then Verifying page header 'Transaction History'")
	public void ClickOnTransactionHistory() throws Exception {
		try {
			VerifyButtonLabelAndClick(DBSappObject.transactionHistoryLabelAndButton(),
					CommonTestData.TRANSCETION_HISTORY_LABEL.getEnumValue());
			EnterPasscodeAndDone();
			verifyPageHeader(CommonTestData.TRANSCETION_HISTORY_LABEL.getEnumValue(),
					DBSappObject.PageHeaderList().get(0));
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}

	}

	@Step("Select Any Local Recipient From Account After clicking on Local Recipient and Verify visibility of Primary source of Fund Dialog then click on Ok Button.")
	public void SelectLocalRecipientsAccount() throws Exception {
		try {
			clickOnElement(DBSappObject.SelectLocalRecipients());
			clickOnElement(DBSappObject.SelectLocalRecipientsToAccountList().get(1));
			if (isElementVisible(DBSappObject.PrimarySourceOfFund()))
				clickOnElement(DBSappObject.OKButton());
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Select'3 Months Transaction History' And 'From Account' from 'Deposit Account' section")
	public void selectTimeAndAccountTypeForStatement(MobileElement depositAccountName) throws Exception {
		try {
			clickOnElement(DBSappObject.threeMonthLabel()); 
			clickOnElement(DBSappObject.DepositAccountButton()); 
			clickOnElement(depositAccountName);
			verifyPageHeader(CommonTestData.TRANSCETION_HISTORY_LABEL.getEnumValue(),DBSappObject.PageHeaderList().get(0));
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}

	}

	@Step("Back to Home page from Transaction History statement")
	public void BackToHomeFromTransactionHistory() throws Exception {
		try {
			clickOnElementOnEnable(DBSappObject.backButton());
			verifyPageHeader(CommonTestData.TRANSCETION_HISTORY_LABEL.getEnumValue(), DBSappObject.PageHeaderList().get(0));
			clickOnElementOnEnable(DBSappObject.backButton());
			VerifyButtonLabelAndClick(DBSappObject.showButton(),CommonTestData.SHOW_BUTTON.getEnumValue());
			VerifyButtonLabelAndClick(DBSappObject.homeButton(),CommonTestData.HOME_BUTTON.getEnumValue());
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Click on 'Show' Button and then Verifying From Account.")
	public void ClickOnShowButtonAndVerifyHeader(String ExpectedAccountName) throws Exception {
		try {
			VerifyButtonLabelAndClick(DBSappObject.showButton(), CommonTestData.SHOW_BUTTON.getEnumValue());
			EnterPasscodeAndDone();
			Asserts.assertEquals(getTexOfElement(DBSappObject.AccountNameToCheckTransactionHistory()),
					ExpectedAccountName, ExpectedAccountName + " is not matching.");
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}

	@Step("Validating From Account Transaction History List.")
	public void ValadateTransactionHistoryListInThreeMonth() throws Exception {
		try {
			List<MobileElement> Elementlist = DBSappObject.dropDowmList();
			int l = Elementlist.size();
			Asserts.assertTrue(l == 0, "No Transaction History is Display");
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}
	
	public void transactionHistoryVerify() throws Exception {
		try {
			 ClickOnMoreButton();
			 EnterPasscodeAndDone();
			 ClickOnTransactionHistory();
			 selectTimeAndAccountTypeForStatement(DBSappObject.posbStatementSavingLabel());
			 ClickOnShowButtonAndVerifyHeader(CommonTestData.STATEMENT_TITLE.getEnumValue());
			 ValadateTransactionHistoryListInThreeMonth();
			 BackToHomeFromTransactionHistory();
		} catch (Exception e) {
			throw new Exception(CommonAppiumTest.getExceptionMessage(e)); 
		}
	}
	
	@Step("Select Future Date Through Calendar and verifies the selected 'future date'.")
	public void SelectFutureDateThroughCalendar() throws Exception {
		try {
			clickOnElement(DBSappObject.TransferDateTextElement());
			Calendar calendar = Calendar.getInstance();
			Date today = calendar.getTime();

			calendar.add(Calendar.DAY_OF_YEAR, 1);
			Date tomorrow = calendar.getTime();

			DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

			String todayAsString = dateFormat.format(today);
			String tomorrowAsString = dateFormat.format(tomorrow);
			String ExpectedDate = tomorrowAsString.replaceAll("-", " ");
			System.out.println(todayAsString);
			System.out.println(tomorrowAsString);
			System.out.println(ExpectedDate);
			String[] sDate = tomorrowAsString.split("-");
			System.out.println(sDate[0]);
			String CalendardateXpath = "//android.view.View[@text='" + sDate[0] + "']";

			MobileElement Calendardate = (MobileElement) driver.findElement(By.xpath(CalendardateXpath));
			if (Calendardate.isEnabled())
				clickOnElement(Calendardate);

			
			String ActualSelectedDate = getTexOfElement(DBSappObject.TransferDateTextElement());
			System.out.println(ActualSelectedDate);
			Asserts.assertEquals(ActualSelectedDate, ExpectedDate, "Selected Date is not Matching");
		
		} catch (Exception e) {
			throw new Exception(getExceptionMessage(e));
		}
	}
}
