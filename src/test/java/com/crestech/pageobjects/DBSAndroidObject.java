package com.crestech.pageobjects;

import java.util.List;

import com.crestech.annotation.values.ElementDescription;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;;

/**
 *
 * @author Divya, Shafkat This class is used to store the objects of Android
 *         Page.
 *
 */

public class DBSAndroidObject {

	@ElementDescription(value = "Application logo")
	@AndroidFindBy(xpath = "//android.widget.ImageView[contains(@resource-id,'id/bank_logo')]")
	private MobileElement Logo;

	@ElementDescription(value = "DBS text")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='digibank']")
	private MobileElement dbsText;

	@ElementDescription(value = "PRE LOGIN Button")
	@AndroidFindBy(xpath = "//android.widget.Button[@text='PRE LOGIN']")
	private MobileElement preLoginButton;

	@ElementDescription(value = "SIGN UP FOR DIGIBANK Button")
	@AndroidFindBy(xpath = "//android.widget.Button[@text='SIGN UP FOR DIGIBANK']")
	private MobileElement signUpForDigibankButton;

	@ElementDescription(value = "LOG IN Button")
	@AndroidFindBy(xpath = "//android.widget.Button[@text='LOG IN']")
	private MobileElement loginButton;

	@ElementDescription(value = "More Button")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='More']")
	private MobileElement MoreButton;

	@ElementDescription(value = "User ID EditTexT")
	@AndroidFindBy(xpath = "//android.widget.EditText[contains(@resource-id,'id/edit_user_id')]")
	private MobileElement userIdEditText;

	@ElementDescription(value = "User PIN EditTexT")
	@AndroidFindBy(xpath = "//android.widget.EditText[contains(@resource-id,'id/edit_user_pin')]")
	private MobileElement userPinEditText;

	@ElementDescription(value = "Welcome to")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'id/banner_text_1')]")
	private MobileElement welcomeTo_Element;

	@ElementDescription(value = "digbank")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'id/banner_text_2')]")
	private MobileElement digibank_Element;

	@ElementDescription(value = "Logout Button")
	@AndroidFindBy(xpath = "//android.widget.Button[@text='LOG OUT']")
	private MobileElement logoutButton;

	@ElementDescription(value = "Alert Title")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'id/tv_title')]")
	private MobileElement postLogoutAlertTitle;

	@ElementDescription(value = "Alert Message")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'id/tv_star')]")
	private MobileElement postLogoutAlertMessage;

	@ElementDescription(value = "Submit Button")
	@AndroidFindBy(xpath = "//android.widget.Button[@content-desc='APPLY']")
	private MobileElement submitButton;

	@ElementDescription(value = "Close Button")
	@AndroidFindBy(xpath = "//android.widget.ImageView[contains(@resource-id,'id/btn_close')]")
	private MobileElement postLogoutcloseButton;

	@ElementDescription(value = "set up your digital token")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'id/status_message')]")
	private MobileElement tokenSetupMessage;

	@ElementDescription(value = "SET UP NOW Button")
	@AndroidFindBy(xpath = "//android.widget.Button[@text='SET UP NOW']")
	private MobileElement setUpNowButton;

	@ElementDescription(value = "Email/SMS OTP Message")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'id/txtv_kht_regcode_header')]")
	private MobileElement emailSmsOtpMessage;

	@ElementDescription(value = "Email/SMS OTP Editbox")
	@AndroidFindBy(xpath = "//android.widget.EditText[contains(@resource-id,'id/kht_otp_box')]")
	private MobileElement emailSmsOtpEditBox;

	@ElementDescription(value = "You've set up your digital token")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'id/digital_token_msg')]")
	private MobileElement tokenGetSetupMessage;

	@ElementDescription(value = "Done Button")
	@AndroidFindBy(xpath = "//android.widget.Button[@text='DONE']")
	private MobileElement doneButton;

	@ElementDescription(value = "PreLogin Button")
	@AndroidFindBy(xpath = "//android.widget.Button[@text='PRE LOGIN']")
	private MobileElement PreLoginBtn;

	@ElementDescription(value = "ChangeServer Button")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Change Server']")
	private MobileElement ChangeServerBtn;

	@ElementDescription(value = "SelectUATN4_Server Button")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='UAT N4']")
	private MobileElement SelectUATN4_Server;

	@ElementDescription(value = "Save Btn to Change Server")
	@AndroidFindBy(xpath = "//android.widget.Button[contains(@resource-id,':id/btn_save_now')]")
	private MobileElement ChangeServerSaveBtn;

	public MobileElement ChangeServerSaveBtn() {
		return ChangeServerSaveBtn;
	}

	public MobileElement SelectUATN4_Server() {
		return SelectUATN4_Server;
	}

	public MobileElement ChangeServerBtn() {
		return ChangeServerBtn;
	}

	public MobileElement Logo() {
		return Logo;
	}

	public MobileElement PreLoginBtn() {
		return PreLoginBtn;
	}

	public MobileElement MoreButton() {
		return MoreButton;
	}

	public String getDbsText() {
		return dbsText.getText();
	}

	public MobileElement preLoginButton() {
		return preLoginButton;
	}

	public MobileElement signUpForDigibankButton() {
		return signUpForDigibankButton;
	}

	public MobileElement loginButton() {
		return loginButton;
	}

	public MobileElement userIdEditText() {
		return userIdEditText;
	}

	public MobileElement userPinEditText() {
		return userPinEditText;
	}

	public MobileElement WelcomeToText() {
		return welcomeTo_Element;
	}

	public MobileElement DigibankText() {
		return digibank_Element;
	}

	public MobileElement logoutButton() {
		return logoutButton;
	}

	public MobileElement postLogoutAlertTitle() {
		return postLogoutAlertTitle;
	}

	public MobileElement postLogoutAlertMessage() {
		return postLogoutAlertMessage;
	}

	public MobileElement submitButton() {
		return submitButton;
	}

	public MobileElement postLogoutcloseButton() {
		return postLogoutcloseButton;
	}

	public MobileElement tokenSetupMessage() {
		return tokenSetupMessage;
	}

	public MobileElement setUpNowButton() {
		return setUpNowButton;
	}

	public MobileElement emailSmsOtpMessage() {
		return emailSmsOtpMessage;
	}

	public MobileElement emailSmsOtpEditBox() {
		return emailSmsOtpEditBox;
	}

	public MobileElement tokenGetSetupMessage() {
		return tokenGetSetupMessage;
	}

	public MobileElement doneButton() {
		return doneButton;
	}

	/***************** Corridor Remittance ******************/
	@ElementDescription(value = "Pay & Transfer Button")
	@AndroidFindBy(xpath = "//android.widget.FrameLayout[@content-desc='Pay & Transfer']")
	private MobileElement PayAndTransferBtn;

	@ElementDescription(value = "2FA Secure Pin Message")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='An SMS containing the 6-digit SMS Secure PIN has been sent to your mobile. Please key in below']")
	private MobileElement ToFASecurePINMsg;

	@ElementDescription(value = "overseas Label")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Overseas']")
	private MobileElement overseasLabel;

	public MobileElement overseasLabel() {
		return overseasLabel;
	}

	@ElementDescription(value = "Top up Label")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Top up']")
	private MobileElement topUpLabel;

	@ElementDescription(value = "Top up button")
	@AndroidFindBy(xpath = "(//android.widget.LinearLayout[contains(@resource-id,'id/cvActionItem')])[8]")
	private MobileElement topUpButton;

	public MobileElement topUpLabel() {
		return topUpLabel;
	}

	public MobileElement topUpButton() {
		return topUpButton;
	}

	@ElementDescription(value = "Overseas Transfer Label")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Overseas Transfer']")
	private MobileElement overseasTransferLabel;

	@ElementDescription(value = "Add Recipient Now button")
	@AndroidFindBy(xpath = "//android.widget.Button[contains(@resource-id,'add_recipient')]")
	private MobileElement addRecipientNowButton;

	public MobileElement addRecipientNowButton() {
		return addRecipientNowButton;
	}

	public MobileElement overseasTransferLabel() {
		return overseasTransferLabel;
	}

	@ElementDescription(value = "Select Location Label")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Select Location']")
	private MobileElement selectLocationLabel;

	@ElementDescription(value = "Search for a Location")
	@AndroidFindBy(xpath = "//android.widget.EditText[contains(@resource-id,'id/editText_search')]")
	private MobileElement countryAutocompleteSearchBox;

	@ElementDescription(value = "List of element of country")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'single_list_item')]")
	private List<MobileElement> countryList;

	// @ElementDescription(value = "clickable element corresponding to list of
	// country")
	// @AndroidFindBy(xpath =
	// "//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup")
	// private List<MobileElement> countryclickableList;

	public List<MobileElement> countryList() {
		return countryList;
	}

//	public List<MobileElement> countryclickableList() {
//		return countryclickableList;
//	}

	public MobileElement locationAutocompleteSearchBox() {
		return countryAutocompleteSearchBox;
	}

	public MobileElement selectLocationLabel() {
		return selectLocationLabel;
	}

	@ElementDescription(value = "currency Label")
	@AndroidFindBy(xpath = "//android.widget.RadioButton[contains(@resource-id,'iid/rb_inr')]") //
	private MobileElement currencyLabel;

	@ElementDescription(value = "currency option list")
	@AndroidFindBy(xpath = "//android.widget.RadioGroup[contains(@resource-id,'id/rg_currency')]/android.widget.RadioButton") //
	private List<MobileElement> currencyOptionList;

	@ElementDescription(value = "Next button")
	@AndroidFindBy(xpath = "//android.widget.Button[@text='NEXT']")
	private MobileElement nextButton;
	
	@ElementDescription(value = "Next button")
	@AndroidFindBy(xpath = "//android.widget.Button[@text='CONFIRM']")
	private MobileElement confirmButton;

	public MobileElement currencyLabel() {
		return currencyLabel;
	}

	public List<MobileElement> currencyOptionList() {
		return currencyOptionList;
	}

	public MobileElement nextButton() {
		return nextButton;
	}
	
	public MobileElement confirmButton() {
		return confirmButton;
	}

	@ElementDescription(value = "Enter Bank Details")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Enter Bank Details']")
	private MobileElement enterDetailsLabel;

	@ElementDescription(value = "Enter Bank code TextField")
	@AndroidFindBy(xpath = "//android.widget.EditText[contains(@resource-id,'id/textInputEditText')]")
	private MobileElement enterBankcodeTextField;

	public MobileElement enterDetailsLabel() {
		return enterDetailsLabel;
	}

	public MobileElement enterBankcodeTextField() {
		return enterBankcodeTextField;
	}

	@ElementDescription(value = "RECIPIENT'S ACCOUNT DETAILS label")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='RECIPIENT'S ACCOUNT DETAILS']")
	private MobileElement recipientAccountDetailLabel;

	@ElementDescription(value = "Enter RECIPIENT'S ACCOUNT DETAILS TextBox")
	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Enter account no.']")
	private MobileElement recipientAccountNoEditBox;

	@ElementDescription(value = "Enter recipient's full name TextBox")
	@AndroidFindBy(xpath = "//android.widget.EditText[contains(@text,'full name')]")
	private MobileElement recipientNameEditBox;

	@ElementDescription(value = "Enter Full Address TextBox")
	@AndroidFindBy(xpath = "//android.widget.EditText[contains(@text,'ddress')]")
	
	private MobileElement recipientAddressEditBox;
	
	@ElementDescription(value = "Enter City TextBox")
	@AndroidFindBy(xpath = "//android.widget.EditText[@text='In the City of']")
	private MobileElement recipientCityEditBox;

	public MobileElement recipientAccountDetailLabel() {
		return recipientAccountDetailLabel;
	}

	public MobileElement recipientAccountNoEditBox() {
		return recipientAccountNoEditBox;
	}

	public MobileElement recipientNameEditBox() {
		return recipientNameEditBox;
	}

	public MobileElement recipientAddressEditBox() {
		return recipientAddressEditBox;
	}

	public MobileElement recipientCityEditBox() {
		return recipientCityEditBox;
	}

	/**
	 * 
	 */
	/**
	 *
	 * @author Shafqat DBS Android APPLICATION screen- recipient review details
	 *
	 */
	@ElementDescription(value = "RECIPIENT'S ACCOUNT DETAILS label")
	@AndroidFindBy(xpath = "(//android.widget.TextView[contains(@resource-id,'id/tv_title')])[1]")
	private MobileElement recipientReviewDetailLabel;

	@ElementDescription(value = "List of Title")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'id/tv_title')]")
	private List<MobileElement> reviewTitleList;

	@ElementDescription(value = "List of Title Field")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'id/tv_value')]")
	private List<MobileElement> reviewTitleFieldList;

	public List<MobileElement> reviewTitleList() {
		return reviewTitleList;
	}

	public List<MobileElement> reviewTitleFieldList() {
		return reviewTitleFieldList;
	}

	public MobileElement recipientReviewDetailLabel() {
		return recipientReviewDetailLabel;
	}

	@ElementDescription(value = "MAKE A TRANSFER")
	@AndroidFindBy(xpath = "//android.widget.Button[@text='MAKE A TRANSFER']")
	private MobileElement makeTransferButton;

	@ElementDescription(value = "expand button")
	@AndroidFindBy(xpath = "//android.widget.ImageView[contains(@resource-id,'id/image_expand')]")
	private MobileElement expandButton;

	@ElementDescription(value = "Reference No. value")
	@AndroidFindBy(xpath = "(//android.widget.TextView[contains(@resource-id,'id/tv_value')])[8]")
	private MobileElement referenceNoValue;

	@ElementDescription(value = "Payee Titles")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,':id/payeeTitle')]")
	private List<MobileElement> PayeeTitleList;

	@ElementDescription(value = "Payee Values")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,':id/payeeValue')]")
	private List<MobileElement> PayeeValueList;

	public List<MobileElement> PayeeValueList() {
		return PayeeValueList;
	}

	public List<MobileElement> PayeeTitleList() {
		return PayeeTitleList;
	}

	public MobileElement makeTransferButton() {
		return makeTransferButton;
	}

	public MobileElement expandButton() {
		return expandButton;
	}

	public MobileElement referenceNoValue() {
		return referenceNoValue;
	}

	@ElementDescription(value = "Paylah! label")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Paylah!']")
	private MobileElement paylahLabel;

	public MobileElement paylahLabel() {
		return paylahLabel;
	}

	@ElementDescription(value = "FundTransferDetailsList")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,':id/tv_lable1')]")
	private List<MobileElement> FundTransferDetailslabel1List;

	@ElementDescription(value = "FundTransferDetailsList")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,':id/tv_lable2')]")
	private List<MobileElement> FundTransferDetailslabel2List;

	@ElementDescription(value = "FundTransferDetailsList")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,':id/tv_lable3')]")
	private List<MobileElement> FundTransferDetailslabel3List;

	public List<MobileElement> FundTransferDetailslabel2List() {
		return FundTransferDetailslabel2List;
	}

	public List<MobileElement> FundTransferDetailslabel3List() {
		return FundTransferDetailslabel3List;
	}

	public List<MobileElement> FundTransferDetailslabel1List() {
		return FundTransferDetailslabel1List;
	}

	@ElementDescription(value = "Top Up Paylah! label")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Top Up Paylah!']")
	private MobileElement topUpPaylahLabel;

	@ElementDescription(value = "Currency edittext box")
	@AndroidFindBy(xpath = "//android.widget.EditText[contains(@resource-id,'id/currencyText')]")
	private MobileElement currencyTextBox;

	// use next button from above
	@ElementDescription(value = "Review Top up label")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Review Top-Up']")
	private MobileElement reviewTopUpLabel;

	@ElementDescription(value = "Amount display for Topup")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'id/card_amount')]")
	private MobileElement displayAmount;

	@ElementDescription(value = "TOP UP NOW button")
	@AndroidFindBy(xpath = "//android.widget.Button[@text='TOP UP NOW']")
	private MobileElement topUpNowButton;

	@ElementDescription(value = "Top up done label")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Top-up Done']")
	private MobileElement topUpDoneLabel;

	@ElementDescription(value = "Logout button paylah page")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,':id/logout_icon')]")
	private MobileElement logOutPaylahButton;

	public MobileElement topUpPaylahLabel() {
		return topUpPaylahLabel;
	}

	public MobileElement currencyTextBox() {
		return currencyTextBox;
	}

	public MobileElement reviewTopUpLabel() {
		return reviewTopUpLabel;
	}

	public MobileElement displayAmount() {
		return displayAmount;
	}

	public MobileElement topUpNowButton() {
		return topUpNowButton;
	}

	public MobileElement topUpDoneLabel() {
		return topUpDoneLabel;
	}

	public MobileElement logOutPaylahButton() {
		return logOutPaylahButton;
	}

	@ElementDescription(value = "Passcode Field")
	@AndroidFindBy(xpath = "//android.widget.EditText[@text='••••••']")
	private MobileElement PasscodeField;

	@ElementDescription(value = "Success Tick Image View")
	@AndroidFindBy(xpath = "//android.widget.ImageView[contains(@resource-id,':id/imgView_ic_success_tick')]")
	private MobileElement SuccessTickImageView;

	@ElementDescription(value = "DoneButtonForPasscode")
	@AndroidFindBy(xpath = "//android.widget.Button[@text='Done']")
	private MobileElement DoneButtonForPasscode;

	@ElementDescription(value = "Add Local Recipient")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Add Local Recipient']")
	private MobileElement AddLocalRecipient;

	@ElementDescription(value = "Local Button")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Local']")
	private MobileElement LocalButton;

	@ElementDescription(value = "Overseas Button Text")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Overseas']")
	private MobileElement OverseasBtnText;

	@ElementDescription(value = "Overseas Button,Topup Button")
	@AndroidFindBy(xpath = "//android.widget.LinearLayout[contains(@resource-id,'id/cvActionItem')]")
	private List<MobileElement> Btnlist; // index=3

	@ElementDescription(value = "Select any one payee from list")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'id/tv_expandable_item_head_title')]")
	private List<MobileElement> PayeeList; // index=2

	@ElementDescription(value = "Select Fund Source Page")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Select Fund Source']")
	private MobileElement SelectFundSourcePage;

	@ElementDescription(value = "Select Bank Account")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Bank Account']")
	private MobileElement SelectBankAccount;

	@ElementDescription(value = "Search field")
	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Search']")
	private MobileElement SearchField;

	@ElementDescription(value = "Select Bank OF India")
	@AndroidFindBy(xpath = "//android.widget.RelativeLayout//android.widget.TextView[@text='BANK OF INDIA']")
	private MobileElement SelectBankOFIndia;

	@ElementDescription(value = "EditFields List")
	@AndroidFindBy(xpath = "//android.widget.EditText[contains(@resource-id,':id/edit_user_id')]")
	private List<MobileElement> EditFields;

	@ElementDescription(value = "Select any one Fund Source from list")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,':id/account_name')]")
	private List<MobileElement> SourceFundList;

	@ElementDescription(value = "Overseas Transfer Page")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,':id/tv_overseas_transfer')]")
	private MobileElement OverseasTransferPage;

	@ElementDescription(value = "Enter Name to appear on the Card Field")
	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Name to appear on the Card']")
	private MobileElement EnterNameToAppearOnTheCardField;

	@ElementDescription(value = "Page Header")
	@AndroidFindBy(xpath = "//android.widget.RelativeLayout//android.widget.TextView[contains(@resource-id,':id/text')]")
	private MobileElement PageHeader;

	@ElementDescription(value = "Page Header List")
	@AndroidFindBy(xpath = " //android.widget.RelativeLayout//android.widget.TextView[contains(@resource-id,':id/text')]")
	private List<MobileElement> PageHeaderList;

	@ElementDescription(value = "Page Header List")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'id/text')]")
	private List<MobileElement> PageHeaderList2;

	@ElementDescription(value = "Page Header")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'id/text')]")
	private MobileElement PageHeader2;
	//
	@ElementDescription(value = "Edit Amount Text Fields")
	@AndroidFindBy(xpath = "//android.widget.EditText[contains(@resource-id,':id/amountEdit')]")
	private List<MobileElement> AmountTextFields;

	@ElementDescription(value = "Next Button")
	@AndroidFindBy(xpath = "//android.widget.Button[contains(@resource-id,':id/btn_remitnext')]")
	private MobileElement NextBtn;

	@ElementDescription(value = "Review Transfer Page Header")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,':id/text_title_mfe')]")
	private MobileElement ReviewTransferPageHeader;

	@ElementDescription(value = "Add Recipient Now Button")
	@AndroidFindBy(xpath = "//android.widget.Button[@text='ADD RECIPIENT NOW']")
	private MobileElement AddRecipientNowBtn;

	@ElementDescription(value = "Add Recipient Now Button")
	@AndroidFindBy(xpath = "//android.widget.Button[@text='Add Overseas Recipient']")
	private MobileElement AddOverseasRecipientNowBtn;
	
	@ElementDescription(value = "Transfer Now Button")
	@AndroidFindBy(xpath = "//android.widget.Button[@text='TRANSFER NOW']")
	private MobileElement TransferNowBtn;

	@ElementDescription(value = "Transfer Submitted Message")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,':id/textViewInfo')]")
	private MobileElement TransferSubmittedMsg;

	@ElementDescription(value = "Main Header Or Success Msg Element")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'id/tv_added_recipient')]")
	private MobileElement MainHeaderOrSuccessMsgElement;

	@ElementDescription(value = "Image Expand")
	@AndroidFindBy(xpath = "//android.widget.ImageView[contains(@resource-id,':id/image_expand')]")
	private MobileElement ImageExpand;

	@ElementDescription(value = "Reference Number Text")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Reference No.']")
	private MobileElement ReferenceNumberText;

	@ElementDescription(value = "Share Transfer Details button Or Track This Transfer Button")
	@AndroidFindBy(xpath = "//android.widget.Button[@text='SHARE TRANSFER DETAILS']")
	private MobileElement ShareTransferDetailsBtn;

	@ElementDescription(value = "Overseas Transfer Message")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,':id/lable_non_remit_tranfer')]")
	private MobileElement OverseasTransferMsg;

	@ElementDescription(value = "Exchange Rate Text")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,':id/exchangerate_view_lbl')]")
	private MobileElement ExchangeRateText;

	@ElementDescription(value = "TransferSubmittedImage")
	@AndroidFindBy(xpath = "//android.widget.ImageView[contains(@resource-id,':id/appCompatImageView')]")
	private MobileElement TransferSubmittedImage;

	@ElementDescription(value = "iB Secure PIN, key in the numbers below.")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='iB Secure PIN, key in the numbers below.']")
	private MobileElement iBSecurePin;

	public MobileElement iBSecurePin() {
		return iBSecurePin;
	}

	public MobileElement LocalButton() {
		return LocalButton;
	}

	public MobileElement TransferSubmittedImage() {
		return TransferSubmittedImage;
	}

	public MobileElement ExchangeRateText() {
		return ExchangeRateText;
	}

	public MobileElement PayAndTransferBtn() {
		return PayAndTransferBtn;
	}

	public MobileElement ToFASecurePINMsg() {
		return ToFASecurePINMsg;
	}

	public MobileElement PasscodeField() {
		return PasscodeField;
	}

	public MobileElement SuccessTickImageView() {
		return SuccessTickImageView;
	}

	public MobileElement DoneButtonForPasscode() {
		return DoneButtonForPasscode;
	}

	public MobileElement AddLocalRecipient() {
		return AddLocalRecipient;
	}

	public MobileElement OverseasBtnText() {
		return OverseasBtnText;
	}

	public List<MobileElement> Btnlist() {
		return Btnlist;
	}

	public List<MobileElement> PayeeList() {
		return PayeeList;
	}

	public MobileElement SelectFundSourcePage() {
		return SelectFundSourcePage;
	}

	public MobileElement SelectBankAccount() {
		return SelectBankAccount;
	}

	public List<MobileElement> SourceFundList() {
		return SourceFundList;
	}

	public List<MobileElement> EditFields() {
		return EditFields;
	}

	public MobileElement OverseasTransferPage() {
		return OverseasTransferPage;
	}

	public MobileElement SearchField() {
		return SearchField;
	}

	public MobileElement SelectBankOFIndia() {
		return SelectBankOFIndia;
	}

	public MobileElement PageHeader() {
		return PageHeader;
	}

	public MobileElement PageHeader2() {
		return PageHeader2;
	}

	public List<MobileElement> PageHeaderList() {
		return PageHeaderList;
	}

	public List<MobileElement> PageHeaderList2() {
		return PageHeaderList2;
	}

	public List<MobileElement> AmountTextFields() {
		return AmountTextFields;
	}

	public MobileElement NextBtn() {
		return NextBtn;
	}

	public MobileElement ReviewTransferPageHeader() {
		return ReviewTransferPageHeader;
	}

	public MobileElement TransferNowBtn() {
		return TransferNowBtn;
	}

	public MobileElement AddRecipientNowBtn() {
		return AddRecipientNowBtn;
	}
	
	public MobileElement AddOverseasRecipientNowBtn() {
		return AddOverseasRecipientNowBtn;
	}

	public MobileElement TransferSubmittedMsg() {
		return TransferSubmittedMsg;
	}

	public MobileElement MainHeaderOrSuccessMsgElement() {
		return MainHeaderOrSuccessMsgElement;
	}

	public MobileElement ImageExpand() {
		return ImageExpand;
	}

	public MobileElement ReferenceNumberText() {
		return ReferenceNumberText;
	}

	public MobileElement ShareTransferDetailsBtn() {
		return ShareTransferDetailsBtn;
	}

	public MobileElement OverseasTransferMsg() {
		return OverseasTransferMsg;
	}

	/***************** End Corridor Remittance ******************/
	/***************** Start Corridor eOTT ******************/
	@ElementDescription(value = "ALL TAB")
	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@content-desc='ALL']/android.widget.TextView")
	private MobileElement AllTab;

	@ElementDescription(value = "Edit text Search Field")
	@AndroidFindBy(xpath = "//android.widget.EditText[contains(@resource-id,':id/editText_search')]")
	private MobileElement editSearchField;

	@ElementDescription(value = "BHDeOTT Payee")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='BHDeott']")
	private MobileElement BHDeott_Payee;

	@ElementDescription(value = "Exchange Rate ICON")
	@AndroidFindBy(xpath = "//android.widget.ImageView[contains(@resource-id,'id/ic_exchange')]")
	private MobileElement ExchangeRateICON;

	@ElementDescription(value = "Select Purpose Of Transfer")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,':id/text_view_hint_big')]")
	private MobileElement SelectPurposeOfTransfer;

	@ElementDescription(value = "Fund Transfer Purpose Option")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Family support')]")
	private MobileElement FundTransferPurposeOption;

	@ElementDescription(value = "Next Button To Added Local Recipient")
	@AndroidFindBy(xpath = "//android.widget.Button[contains(@resource-id,':id/btn_submit')]")
	private MobileElement NextButtonToAddedLocalRecipient;

	@ElementDescription(value = "Text View Purpose")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,':id/text_view_purpose')]")
	private MobileElement TextViewPurpose;

	@ElementDescription(value = "Billing Organisation")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Billing Organisation']")
	private MobileElement BillingOrganisation;

	@ElementDescription(value = "Bill Reference No")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Bill Reference No.']")
	private MobileElement BillReferenceNo;

//	@ElementDescription(value = "DBS CASHLINE Value")
//	@AndroidFindBy(xpath = "//android.widget.TextView[@text='DBS CASHLINE']")
//	private MobileElement DBSCASHLINE;
//
//	@ElementDescription(value = "Reference Number Value")
//	@AndroidFindBy(xpath = "//android.widget.TextView[@text='0824867822']")
//	private MobileElement ReferenceNumberValue;

	@ElementDescription(value = "MAKE A PAYMENT BUTTON")
	@AndroidFindBy(xpath = "//android.widget.Button[@text='MAKE A PAYMENT']")
	private MobileElement MakeAPaymentButton;

	@ElementDescription(value = "Amount Field For Bill Org")
	@AndroidFindBy(xpath = "//android.widget.EditText[contains(@resource-id,':id/appCompatEditText')]")
	private MobileElement AmountEditableField;

	@ElementDescription(value = "Log Out Button")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Log Out']")
	private MobileElement LOGOUTButton;

	@ElementDescription(value = "Log Out Button")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='LOG OUT']")
	private MobileElement LogoutBtn;

	@ElementDescription(value = "POSB SAYE Account SelectOpenAccountOption")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='POSB SAYE Account']")
	private MobileElement SelectOpenAccountOption;

	@ElementDescription(value = "open account in 3 step button")
	@AndroidFindBy(xpath = "//android.widget.Button[contains(@resource-id,':id/sgmb_open_account')]")
	private MobileElement StepOpenAccountButton;

	@ElementDescription(value = "Enter monthly savings amount")
	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Enter monthly savings amount']")
	private MobileElement EnterMonthlySavingsAmtEditField;

	@ElementDescription(value = "Select source of funds for savings")
	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Select source of funds for savings']")
	private MobileElement SelectSourceOfFundsForSavingsDropdown;

	@ElementDescription(value = "Select source of funds for savings dropdown list")
	@AndroidFindBy(xpath = "//android.widget.LinearLayout[contains(@resource-id,':id/layout_acc_details')]")
	private List<MobileElement> SelectSourceOfFundsForSavingsDropdownList;

	@ElementDescription(value = "Select source of funds for savings dropdown list")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,':id/text_toolbar_title'])")
	private List<MobileElement> SelectSourceAccountFromList;
	
	@ElementDescription(value = "Transaction History Label and Button")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Transaction History']")
	private MobileElement transactionHistoryLabel;

	public List<MobileElement> SelectSourceOfFundsForSavingsDropdownList() {
		return SelectSourceOfFundsForSavingsDropdownList;
	}

	public List<MobileElement> SelectSourceAccountFromList() {
		return SelectSourceAccountFromList;
	}
	
	public MobileElement SelectSourceOfFundsForSavingsDropdown() {
		return SelectSourceOfFundsForSavingsDropdown;
	}

	public MobileElement EnterMonthlySavingsAmtEditField() {
		return EnterMonthlySavingsAmtEditField;
	}

	public MobileElement StepOpenAccountButton() {
		return StepOpenAccountButton;
	}

	public MobileElement SelectOpenAccountOption() {
		return SelectOpenAccountOption;
	}

	public MobileElement LOGOUTButton() {
		return LOGOUTButton;
	}

	public MobileElement LogoutBtn() {
		return LogoutBtn;
	}

	public MobileElement AmountEditableField() {
		return AmountEditableField;
	}

	public MobileElement MakeAPaymentButton() {
		return MakeAPaymentButton;
	}

//	public MobileElement ReferenceNumberValue() {
//		return ReferenceNumberValue;
//	}
//
//	public MobileElement DBSCASHLINE() {
//		return DBSCASHLINE;
//	}

	public MobileElement BillReferenceNo() {
		return BillReferenceNo;
	}

	public MobileElement BillingOrganisation() {
		return BillingOrganisation;
	}

	public MobileElement TextViewPurpose() {
		return TextViewPurpose;
	}

	public MobileElement FundTransferPurposeOption() {
		return FundTransferPurposeOption;
	}

	public MobileElement NextButtonToAddedLocalRecipient() {
		return NextButtonToAddedLocalRecipient;
	}

	public MobileElement SelectPurposeOfTransfer() {
		return SelectPurposeOfTransfer;
	}

	public MobileElement ExchangeRateICON() {
		return ExchangeRateICON;
	}

	public MobileElement AllTab() {
		return AllTab;
	}

	public MobileElement editSearchField() {
		return editSearchField;
	}

	public MobileElement BHDeott_Payee() {
		return BHDeott_Payee;
	}

	/***************** End Corridor eOTT ******************/
	/***************** Start Apply Debit Card ******************/
	@ElementDescription(value = "More Button")
	@AndroidFindBy(xpath = "//android.widget.FrameLayout[@content-desc='More']")
	private MobileElement MoreBtn;

	@ElementDescription(value = "Cards Button")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Cards']")
	private MobileElement CardsButton;

	@ElementDescription(value = "Select Debit Card")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Debit Cards']")
	private MobileElement SelectDebitCard;

	@ElementDescription(value = "Debit card Option for selection")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='PASSION POSB MASTERCARD DEBIT CARD']")
	private MobileElement DebitCardOption;

	@ElementDescription(value = "Account to be linked to the card")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Account to be linked to the card']")
	private MobileElement AccountToBeLinkedToTheCardField;

	@ElementDescription(value = "Title Field")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Title']")
	private MobileElement TitleField;

	@ElementDescription(value = "Debit Card Details Dropdown List")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,':id/tv_view_id')]")
	private List<MobileElement> DebitCardDetailsDropdownList;

	@ElementDescription(value = "Race Field")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Race']")
	private MobileElement RaceField;

	@ElementDescription(value = "Marital Status field")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Marital Status']")
	private MobileElement MaritalStatusField;

	@ElementDescription(value = "Residential Type field")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Residential Type']")
	private MobileElement ResidentialTypeField;

	@ElementDescription(value = "Education field")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Education']")
	private MobileElement EducationField;

	@ElementDescription(value = "Economic Status field")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Economic Status']")
	private MobileElement EconomicStatusField;

	@ElementDescription(value = "Annual Income Field")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Annual Income']")
	private MobileElement AnnualIncomeField;

	@ElementDescription(value = "Send Me DBS Prmotion Via Mail")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Email']")
	private MobileElement SendMeDBSPrmotionViaMail;

	@ElementDescription(value = "Apply Now Button")
	@AndroidFindBy(xpath = "//android.widget.Button[@text='APPLY NOW']")
	private MobileElement ApplyNowButton;

	@ElementDescription(value = "Send Me DBS Prmotion Via Mail")
	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Confirm New PIN']")
	private MobileElement ConfirmNewPINField;

	@ElementDescription(value = "Apply Now Button")
	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Create Your PIN']")
	private MobileElement CreateYourPINField;

	@ElementDescription(value = "Bills Button")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Bills']")
	private MobileElement BillsButton;

	@ElementDescription(value = "Add Billing Organisation")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Add Billing Organisation']")
	private MobileElement AddBillingOrganisation;
	
	@ElementDescription(value = "Add Oversea Recipient")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Add Overseas Recipient']")
	private MobileElement AddOverseasRecipient;
	
	@ElementDescription(value = "Select Billing Organisation")
	@AndroidFindBy(xpath = "//android.widget.EditText[contains(@resource-id,':id/edit_user_id')]")
	private MobileElement SelectBillingOrganisation;

	@ElementDescription(value = "Search For Billing Organisation Field")
	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Search for a billing organisation']")
	private MobileElement SearchForBillingOrganisationField;

	@ElementDescription(value = "Select Searched Option For Billing Organisation Field")
	@AndroidFindBy(xpath = "//android.widget.LinearLayout[contains(@resource-id,':id/layout_acc_details')]")
	private MobileElement SelectSearchedOption;

	@ElementDescription(value = "Enter reference no. Edit Field")
	@AndroidFindBy(xpath = "//android.widget.EditText[contains(@resource-id,':id/editTextId')]")
	private MobileElement EnterReferenceNoEditField;

	@ElementDescription(value = "Pay Now Button")
	@AndroidFindBy(xpath = "//android.widget.Button[@text='PAY NOW']")
	private MobileElement PayNowButton;

	@ElementDescription(value = "Image ICON For Payment Success")
	@AndroidFindBy(xpath = "//android.widget.ImageView[contains(@resource-id,':id/image_result')]")
	private MobileElement ImageForPaymentSuccess;

	@ElementDescription(value = "Transfer Success Message")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,':id/text_transfer_result')]")
	private MobileElement TransferSuccessMsgElement;

	@ElementDescription(value = "Deposit Account Modules")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Deposit Accounts']")
	private MobileElement DepositAccountsModule;

	@ElementDescription(value = "Warning Heading")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,':id/warning_heading')]")
	private MobileElement WarningHeading;
	
	@ElementDescription(value = "POSB SAYE Account")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='POSB SAYE Account']")
	private MobileElement POSBSayeAccount;

	@ElementDescription(value = "I ACKNOWLEDGE BUTTON")
	@AndroidFindBy(xpath = "//android.widget.Button[@text='I ACKNOWLEDGE']")
	private MobileElement IACKNOWLEDGEButton;

	@ElementDescription(value = "You Ar Opening Text")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='You Are Opening']")
	private MobileElement YouAreOpeningText;

	@ElementDescription(value = "Open Account Now Button")
	@AndroidFindBy(xpath = "//android.widget.Button[@text='OPEN ACCOUNT NOW']")
	private MobileElement OpenAcconuntNowBtn;

	@ElementDescription(value = "Open Acconunt Success Image Icon")
	@AndroidFindBy(xpath = "//android.widget.ImageView[contains(@resource-id,':id/image_view_ic')]")
	private MobileElement OpenAcconuntSuccessImageIcon;
	
	@ElementDescription(value = "Account Status Message")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,':id/account_status')]")
	private MobileElement AccountStatusMessage;
	
	@ElementDescription(value = "BACK To HOME Button")
	@AndroidFindBy(xpath = "//android.widget.Button[@text='BACK TO HOME']")
	private MobileElement BACKToHOME;

	@ElementDescription(value = "Select Own Account Number")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='420-86386-1']")
	private MobileElement SelectOwnAccountNumber;

	@ElementDescription(value = "Select Local Recipients To Account List")
	@AndroidFindBy(xpath = "//android.widget.LinearLayout[contains(@resource-id,':id/tv_expandable_item_sub_title')]")
	private List<MobileElement> SelectLocalRecipientsToAccountList;

	@ElementDescription(value = "Non Fast Transaction Service Message")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='NON-FAST']")
	private MobileElement NonFastTransactionService;

	public MobileElement NonFastTransactionService() {
		return NonFastTransactionService;
	}
	
	public MobileElement AddOverseasRecipient() {
		return AddOverseasRecipient;
	}
	
	@ElementDescription(value = "Fast Transaction Service Message")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='FAST']")
	private MobileElement FastTransactionService;

	public MobileElement FastTransactionService() {
		return FastTransactionService;
	}

	public MobileElement TransferSuccessMsgElement() {
		return TransferSuccessMsgElement;
	}

	public MobileElement SelectOwnAccountNumber() {
		return SelectOwnAccountNumber;
	}

	public List<MobileElement> SelectLocalRecipientsToAccountList() {
		return SelectLocalRecipientsToAccountList;
	}

	public MobileElement BACKToHOME() {
		return BACKToHOME;
	}

	public MobileElement AccountStatusMessage() {
		return AccountStatusMessage;
	}

	public MobileElement OpenAcconuntSuccessImageIcon() {
		return OpenAcconuntSuccessImageIcon;
	}

	public MobileElement OpenAccountNowButton() {
		return OpenAcconuntNowBtn;
	}

	public MobileElement YouAreOpeningText() {
		return YouAreOpeningText;
	}

	public MobileElement POSBSayeAccount() {
		return POSBSayeAccount;
	}

	public MobileElement IACKNOWLEDGEButton() {
		return IACKNOWLEDGEButton;
	}

	public MobileElement WarningHeading() {
		return WarningHeading;
	}

	public MobileElement DepositAccountsModule() {
		return DepositAccountsModule;
	}

	public MobileElement ImageForPaymentSuccess() {
		return ImageForPaymentSuccess;
	}

	public MobileElement PayNowButton() {
		return PayNowButton;
	}

	public MobileElement EnterReferenceNoEditField() {
		return EnterReferenceNoEditField;
	}

	public MobileElement SelectSearchedOption() {
		return SelectSearchedOption;
	}

	public MobileElement SearchForBillingOrganisationField() {
		return SearchForBillingOrganisationField;
	}

	public MobileElement SelectBillingOrganisation() {
		return SelectBillingOrganisation;
	}

	public MobileElement AddBillingOrganisation() {
		return AddBillingOrganisation;
	}

	public MobileElement BillsButton() {
		return BillsButton;
	}

	public MobileElement CreateYourPINField() {
		return CreateYourPINField;
	}

	public MobileElement ConfirmNewPINField() {
		return ConfirmNewPINField;
	}

	public MobileElement ApplyNowButton() {
		return ApplyNowButton;
	}

	public MobileElement EducationField() {
		return EducationField;
	}

	public MobileElement EconomicStatusField() {
		return EconomicStatusField;
	}

	public MobileElement AnnualIncomeField() {
		return AnnualIncomeField;
	}

	public MobileElement ResidentialTypeField() {
		return ResidentialTypeField;
	}

	public MobileElement MaritalStatusField() {
		return MaritalStatusField;
	}

	public MobileElement RaceField() {
		return RaceField;
	}

	public MobileElement MoreBtn() {
		return MoreBtn;
	}

	public MobileElement CardsButton() {
		return CardsButton;
	}

	public MobileElement SelectDebitCard() {
		return SelectDebitCard;
	}

	public MobileElement DebitCardOption() {
		return DebitCardOption;
	}

	public MobileElement AccountToBeLinkedToTheCardField() {
		return AccountToBeLinkedToTheCardField;
	}

	public MobileElement TitleField() {
		return TitleField;
	}

	public List<MobileElement> DebitCardDetailsDropdownList() {
		return DebitCardDetailsDropdownList;
	}

	public MobileElement EnterNameToAppearOnTheCardField() {
		return EnterNameToAppearOnTheCardField;
	}

	public MobileElement SendMeDBSPrmotionViaMail() {
		return SendMeDBSPrmotionViaMail;
	}

	/***************** End Apply Debit Card ******************/

	@ElementDescription(value = "search icon")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'id/contact_search')]")
	private MobileElement searchIcon;

	@ElementDescription(value = "search editbox")
	@AndroidFindBy(xpath = "//android.widget.EditText[contains(@resource-id,'id/search_text_et')]")
	private MobileElement searchBox;

	@ElementDescription(value = "'To other bank' label and button")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='To Other Banks']")
	private MobileElement toOtherBankTextButton;

	@ElementDescription(value = "'Current Limit' label and button")
	@AndroidFindBy(xpath = "//android.widget.EditText[contains(@resource-id,'id/edit_user_id')]")
	private MobileElement currentLimitTextButton;

	@ElementDescription(value = "'Current Limit' Value")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'id/text_src_acc_name')]")
	private List<MobileElement> currentLimitAmountValue;

	@ElementDescription(value = "search Text Element list")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'id/textView')]")
	private List<MobileElement> searchTextElement;

	@ElementDescription(value = "search Clickable Element list")
	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@clickable='true']")
	private List<MobileElement> searchClickableElement;

	@ElementDescription(value = "'CHANGE DAILY LIMIT NOW' button")
	@AndroidFindBy(xpath = "//android.widget.Button[@text='CHANGE DAILY LIMIT NOW']")
	private MobileElement changeDailyLimitButton;

	@ElementDescription(value = "'successful Title' label")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'id/text_top_nav_title')]")
	private MobileElement successTitleLabel;

	@ElementDescription(value = "'Back To more' button")
	@AndroidFindBy(xpath = "//android.widget.Button[@text='BACK TO MORE']")
	private MobileElement backToMoreButton;

	@ElementDescription(value = "Select fund Source Account")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='028-976191-5']")
	private MobileElement SelectFundSourceAccount;

	@ElementDescription(value = "Primary source of fund")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Primary source of fund']")
	private MobileElement PrimarySourceOfFund;

	@ElementDescription(value = "Error Messge Element")
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='android:id/message']")
	private MobileElement ErrorMessgeElement;

	@ElementDescription(value = "OK Button")
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.dbs.sit1.dbsmbanking:id/tv_ok_id']")
	private MobileElement OKButton;
	
	@ElementDescription(value = "Close Button to Closing The Overseas screen.")
	@AndroidFindBy(xpath = "//android.widget.ImageButton[contains(@resource-id,':id/img_scan')]")
	private MobileElement CloseBtn_OverseasScreen;
	
	
	@ElementDescription(value = "OK Button")
	@AndroidFindBy(xpath = "//android.widget.Button[@text='OK']")
	private MobileElement OKButtonDeletePayee;
	
	
	@ElementDescription(value = "Transfer Via Fast Transfer Toggle")
	@AndroidFindBy(xpath = "//android.widget.Switch[contains(@resource-id,':id/switch_transfer_type')]")
	private MobileElement TransferViaFastTransferToggle;

	@ElementDescription(value = "Select Local Recipient To Account")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,':id/text_src_acc_name')]")
	private List<MobileElement> SelectLocalRecipientToAccount;

	@ElementDescription(value = "Transfer Date Text Element")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,':id/text_transfer_date')]")
	private MobileElement TransferDateTextElement;

	@ElementDescription(value = "MAKE ANOTHER TRANSFER BUTTON")
	@AndroidFindBy(xpath = "//android.widget.Button[@text='MAKE ANOTHER TRANSFER']")
	private MobileElement MakeAnotherTransferBtn;

	@ElementDescription(value = "MAKE ANOTHER PAYMENT BUTTON")
	@AndroidFindBy(xpath = "//android.widget.Button[@text='MAKE ANOTHER PAYMENT']")
	private MobileElement MakeAnotherPaymentBtn;
	
	@ElementDescription(value = "Sending Amount Element")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,':id/card_amount')]")
	private MobileElement SendingAmountElement;

	@ElementDescription(value = "Account name list:- Contains From Account and To Account")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,':id/tv_lable2')]")
	private List<MobileElement> AccountNameList;

	@ElementDescription(value = "Account Number list:- Contains From Account and To Account")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,':id/tv_lable3')]")
	private List<MobileElement> AccountNumberList;

	@ElementDescription(value = "Footer Expandable Button")
	@AndroidFindBy(xpath = "//android.widget.ImageView[contains(@resource-id,':id/ic_logo_footer_iv')]")
	private MobileElement FooterExpandableBtn;

	@ElementDescription(value = "Back Icon")
	@AndroidFindBy(xpath = "//android.widget.ImageView[contains(@resource-id,':id/back_icon')]")
	private MobileElement BackIcon;
	
	@ElementDescription(value = "Close Button")
	@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc='CLOSE']")
	private MobileElement CloseButton;

	public MobileElement CloseBtn_OverseasScreen() {
		return CloseBtn_OverseasScreen;
	}
	
	
	public MobileElement MakeAnotherPaymentBtn() {
		return MakeAnotherPaymentBtn;
	}
	
	public MobileElement CloseButton() {
		return CloseButton;
	}

	public MobileElement BackIcon() {
		return BackIcon;
	}

	public MobileElement ErrorMessgeElement() {
		return ErrorMessgeElement;
	}

	public MobileElement FooterExpandableBtn() {
		return FooterExpandableBtn;
	}

	public List<MobileElement> AccountNumberList() {
		return AccountNumberList;
	}

	public List<MobileElement> AccountNameList() {
		return AccountNameList;
	}

	public MobileElement SendingAmountElement() {
		return SendingAmountElement;
	}

	public MobileElement MakeAnotherTransferBtn() {
		return MakeAnotherTransferBtn;
	}

	public MobileElement TransferDateTextElement() {
		return TransferDateTextElement;
	}

	public List<MobileElement> SelectLocalRecipientToAccount() {
		return SelectLocalRecipientToAccount;
	}

	public MobileElement TransferViaFastTransferToggle() {
		return TransferViaFastTransferToggle;
	}

	public MobileElement OKButton() {
		return OKButton;
	}
	
	public MobileElement OKButtonDeletePayee() {
		return OKButtonDeletePayee;
	}

	public MobileElement PrimarySourceOfFund() {
		return PrimarySourceOfFund;
	}

	public MobileElement SelectFundSourceAccount() {
		return SelectFundSourceAccount;
	}

	public MobileElement searchBox() {
		return searchBox;
	}

	public MobileElement searchIcon() {
		return searchIcon;
	}

	public MobileElement toOtherBankTextButton() {
		return toOtherBankTextButton;
	}

	public MobileElement currentLimitTextButton() {
		return currentLimitTextButton;
	}

	public MobileElement changeDailyLimitButton() {
		return changeDailyLimitButton;
	}

	public MobileElement successTitleLabel() {
		return successTitleLabel;
	}

	public MobileElement backToMoreButton() {
		return backToMoreButton;
	}

	public List<MobileElement> currentLimitAmountValue() {
		return currentLimitAmountValue;
	}

	public List<MobileElement> searchTextElement() {
		return searchTextElement;
	}

	public List<MobileElement> searchClickableElement() {
		return searchClickableElement;
	}

	//////// fund transfer to other bank

	@ElementDescription(value = "Local Recipient options list, Credit Card Options list")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,':id/tv_expandable_item_head_title')]")
	private List<MobileElement> AllTabOptionsList;

	@ElementDescription(value = "DBS CURRENT ACCOUNT")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='DBS CURRENT ACCOUNT']")
	private MobileElement dbsCurrentAccountOption;

	@ElementDescription(value = "Contact Search Field")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,':id/contact_search')]")
	private MobileElement ContactSearchfield;
	
	@ElementDescription(value = "Edit Text Search Box")
	@AndroidFindBy(xpath = "//android.widget.EditText[contains(@resource-id,':id/search_text_et')]")
	private MobileElement EditTextSearchBox;
	
	@ElementDescription(value = "Update Contact Details")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Update Contact Details']")
	private MobileElement UpdateContactDetails;
	
	public MobileElement UpdateContactDetails() {
		return UpdateContactDetails;
	}
	
	public MobileElement EditTextSearchBox() {
		return EditTextSearchBox;
	}
	
	public MobileElement ContactSearchfield() {
		return ContactSearchfield;
	}
	
	public MobileElement dbsCurrentAccountOption() {
		return dbsCurrentAccountOption;
	}

	public List<MobileElement> AllTabOptionsList() {
		return AllTabOptionsList;
	}

	@ElementDescription(value = "Select Fund Source")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Select Fund Source']")
	private MobileElement selectFundSourceTextButton;

	public MobileElement selectFundSourceTextButton() {
		return selectFundSourceTextButton;
	}

	@ElementDescription(value = "DBS Multiplier Account")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='DBS Multiplier Account']")
	private MobileElement dbsMultiplierAccountTextButton;

	public MobileElement dbsMultiplierAccountTextButton() {
		return dbsMultiplierAccountTextButton;
	}

	// alert
	@ElementDescription(value = "Primary source of fund title text")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'id/tv_primary_account_bottom_sheet_title')]")
	private MobileElement primarysourceAlertTitle;

	public MobileElement primarysourceAlertTitle() {
		return primarysourceAlertTitle;
	}

	@ElementDescription(value = "Amount Transfer edittext box")
	@AndroidFindBy(xpath = "//android.widget.EditText[contains(@resource-id,'id/appCompatEditText')]")
	private MobileElement amountTransferTextBox;

	@ElementDescription(value = "SGD")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='SGD']")
	private MobileElement sgdFieldText;

	public MobileElement amountTransferTextBox() {
		return amountTransferTextBox;
	}

	public MobileElement sgdFieldText() {
		return sgdFieldText;
	}

	@ElementDescription(value = "Review Transfer")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Review Transfer']")
	private MobileElement reviewTransferTitle;

	public MobileElement reviewTransferTitle() {
		return reviewTransferTitle;
	}

	public MobileElement transactionHistoryLabelAndButton() {
		return transactionHistoryLabel;
	}

	@ElementDescription(value = "Deposit Account Button")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Deposit Account']")
	private MobileElement DepositAccountButton;

	public MobileElement DepositAccountButton() {
		return DepositAccountButton;
	}

	@ElementDescription(value = "POSB STATEMENT SAVINGS  Label and Button")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'POSB STATEMENT SAVINGS')]")
	private MobileElement posbStatementSavingLabel;

	public MobileElement posbStatementSavingLabel() {
		return posbStatementSavingLabel;
	}

	@ElementDescription(value = "3 three month  Label")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='3 Months']")
	private MobileElement threeMonthLabel;

	public MobileElement threeMonthLabel() {
		return threeMonthLabel;
	}

	@ElementDescription(value = "Show button")
	@AndroidFindBy(xpath = "//android.widget.Button[@text='SHOW']")
	private MobileElement showButton;

	public MobileElement showButton() {
		return showButton;
	}

	@ElementDescription(value = "DropDown List")
	@AndroidFindBy(xpath = "//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup")
	private List<MobileElement> dropDowmList;

	public List<MobileElement> dropDowmList() {
		return dropDowmList;
	}

	@ElementDescription(value = "back button")
	@AndroidFindBy(xpath = "//android.widget.ImageView[contains(@resource-id,'id/back_btn')]")
	private MobileElement backButton;

	public MobileElement backButton() {
		return backButton;
	}

	@ElementDescription(value = "Toolbar Icon List")
	@AndroidFindBy(xpath = "//android.widget.ImageView[@resource-id='com.dbs.sit1.dbsmbanking:id/icon']")
	private List<MobileElement> toolbarIconList;

	public List<MobileElement> ToolbarIconList() {
		return toolbarIconList;
	}

	@ElementDescription(value = "Home button")
	@AndroidFindBy(xpath = "//android.widget.FrameLayout[@content-desc='Home']")
	private MobileElement homeButton;

	public MobileElement homeButton() {
		return homeButton;
	}

	@ElementDescription(value = "Transaction History Header")
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.dbs.sit1.dbsmbanking:id/text']")
	private MobileElement TransactionHistoryHeaderDBS;

	@ElementDescription(value = "Transaction History Header")
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.dbs.sit1.posbmbanking:id/text']")
	private MobileElement TransactionHistoryHeaderPOSB;

	@ElementDescription(value = "Transaction History Header")
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.dbs.sg.uat.dbsiwealth:id/text']")
	private MobileElement TransactionHistoryHeaderForiWEALTH;

	public MobileElement TransactionHistoryHeaderForDBS() {
		return TransactionHistoryHeaderDBS;
	}

	public MobileElement TransactionHistoryHeaderForPOSB() {
		return TransactionHistoryHeaderPOSB;
	}

	public MobileElement TransactionHistoryHeaderForiWEALTH() {
		return TransactionHistoryHeaderForiWEALTH;
	}

	@ElementDescription(value = "POSB STATEMENT SAVINGS Button")
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.dbs.sit1.dbsmbanking:id/tv_expandable_item_head_title']")
	private List<MobileElement> AccountNameListInTransactionHistoryForDBS;

	public List<MobileElement> AccountNameListInTransactionHistoryForDBS() {
		return AccountNameListInTransactionHistoryForDBS;
	}
	// <-------end--------->

	@ElementDescription(value = "POSB STATEMENT SAVINGS Button")
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.dbs.sit1.posbmbanking:id/tv_expandable_item_head_title']")
	private List<MobileElement> AccountNameListInTransactionHistoryForPOSB;

	public List<MobileElement> AccountNameListInTransactionHistoryForPOSB() {
		return AccountNameListInTransactionHistoryForPOSB;
	}

	@ElementDescription(value = "POSB STATEMENT SAVINGS Button")
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.dbs.sg.uat.dbsiwealth:id/tv_expandable_item_head_title']")
	private List<MobileElement> AccountNameListInTransactionHistoryForiWEALTH;

	public List<MobileElement> AccountNameListInTransactionHistoryForiWEALTH() {
		return AccountNameListInTransactionHistoryForiWEALTH;
	}

	@ElementDescription(value = "Selected bank Account From Local Recipient List")
	@AndroidFindBy(xpath = "(//android.widget.LinearLayout[contains(@resource-id,':id/ll_maincontent_view_id')])[1]")
	private MobileElement localRecipientListSelectedAccount;

	public MobileElement localRecipientListSelectedAccount() {
		return localRecipientListSelectedAccount;
	}

	@ElementDescription(value = "Billing Organisations label And Button")
	@AndroidFindBy(xpath = "//android.widget.ImageView[@text='Billing Organisations']")
	private MobileElement billingOrganisations;

	public MobileElement billingOrganisations() {
		return billingOrganisations;
	}

	@ElementDescription(value = "0.00 amount in local transfer limit list")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='0.00']")
	private MobileElement Amount_0_inLocaltransferlimitList;

	public MobileElement Amount_0_inLocaltransferlimitList() {
		return Amount_0_inLocaltransferlimitList;
	}

	@ElementDescription(value = "50000.00 amount in local transfer limit list")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='50,000.00']")
	private MobileElement Amount_50000_inLocaltransferlimitList;

	public MobileElement Amount_50000_inLocaltransferlimitList() {
		return Amount_50000_inLocaltransferlimitList;
	}

	@ElementDescription(value = "Selected bank Account From Select Fund Source")
	@AndroidFindBy(xpath = "(//android.widget.TextView[contains(@resource-id,':id/text_src_acc_name')])[0]")
	private MobileElement FundSourceListSelectedAccount;

	@ElementDescription(value = "Account name to checking transaction history.")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,':id/tv_accountName')]")
	private MobileElement AccountNameToCheckTransactionHistory;

	@ElementDescription(value = "Deposit Account List")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,':id/tv_expandable_item_head_title')]")
	private List<MobileElement> DepositAccountList;

	@ElementDescription(value = "Payee Added Expandable Icon List")
	@AndroidFindBy(xpath = "//android.widget.ImageView[contains(@resource-id,':id/tv_expandable_item_selected')]")
	private List<MobileElement> PayeeAddedExpandableIconList;
	
	@ElementDescription(value = "payee_details_title_name.")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,':id/payee_details_title_name')]")
	private MobileElement payee_details_title_name;
	
	@ElementDescription(value = "More Option Button.")
	@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc='More options']")
	private MobileElement MoreOptionBtn;

	@ElementDescription(value = "Delete Payee Button.")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Delete Payee']")
	private MobileElement DeletePayeeBtn;

	@ElementDescription(value = "YES Button.")
	@AndroidFindBy(xpath = "//android.widget.Button[@text='YES']")
	private MobileElement YesBtn;

	@ElementDescription(value = "Are you sure you want to delete this payee? Message Element.")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Are you sure you want to delete this payee?']")
	private MobileElement AreYouSureToDeleteThisPayeeMessage;

	public MobileElement AreYouSureToDeleteThisPayeeMessage() {
		return AreYouSureToDeleteThisPayeeMessage;
	}

	public MobileElement YesBtn() {
		return YesBtn;
	}

	public MobileElement DeletePayeeBtn() {
		return DeletePayeeBtn;
	}

	public MobileElement MoreOptionBtn() {
		return MoreOptionBtn;
	}

	public MobileElement payee_details_title_name() {
		return payee_details_title_name;
	}

	public List<MobileElement> PayeeAddedExpandableIconList() {
		return PayeeAddedExpandableIconList;
	}

	public List<MobileElement> DepositAccountList() {
		return DepositAccountList;
	}

	public MobileElement FundSourceListSelectedAccount() {
		return FundSourceListSelectedAccount;
	}

	public MobileElement AccountNameToCheckTransactionHistory() {
		return AccountNameToCheckTransactionHistory;
	}

	@ElementDescription(value = "duration option")
	@AndroidFindBy(xpath = "(//android.widget.EditText[contains(@resource-id,'id/edit_user_id')])[1]")
	private MobileElement durationOption;

	public MobileElement durationOption() {
		return durationOption;
	}

	@ElementDescription(value = "Purpose option")
	@AndroidFindBy(xpath = "(//android.widget.EditText[contains(@resource-id,'id/edit_user_id')])[2]")
	private MobileElement purposeOption;

	public MobileElement purposeOption() {
		return purposeOption;
	}

	@ElementDescription(value = "Amount Enter credilimt EditBox")
	@AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='Please enter an amount']")
	private MobileElement amountCreditLimt;

	public MobileElement amountCreditLimt() {
		return amountCreditLimt;
	}

	@ElementDescription(value = "Purpose List")
	@AndroidFindBy(xpath = "//androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.TextView")
	private List<MobileElement> PurposeList;

	public List<MobileElement> PurposeList() {
		return PurposeList;
	}

	@ElementDescription(value = "credit limit calender year header")
	@AndroidFindBy(xpath = "//android.widget.ListView[contains(@resource-id,'id/tv_displaying_year_header')]")
	private MobileElement yearHeader;

	public MobileElement yearHeader() {
		return yearHeader;
	}

	@ElementDescription(value = "month list credit limit header")
	@AndroidFindBy(xpath = "//android.widget.ListView[contains(@resource-id,'id/calendar_view')]/android.widget.LinearLayout/android.widget.TextView")
	private List<MobileElement> monthList;

	public List<MobileElement> monthList() {
		return monthList;
	}

	@ElementDescription(value = "date list credit limit header")
	@AndroidFindBy(xpath = "//android.widget.ListView[contains(@resource-id,'id/calendar_view')]/android.widget.LinearLayout[1]/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout")
	private List<MobileElement> dateList;

	public List<MobileElement> dateList() {
		return dateList;
	}

	@ElementDescription(value = "AccountList in Local fund transfer Limit")
	@AndroidFindBy(xpath = "//android.widget.RelativeLayout//android.widget.TextView[contains(@resource-id,':id/text')]")
	private List<MobileElement> localTransferLimitAccountList;

	public List<MobileElement> localTransferLimitAccountList() {
		return localTransferLimitAccountList;
	}

	@ElementDescription(value = "Deposit Account Button")
	@AndroidFindBy(xpath = "//android.widget.ImageView[@resource-id='com.dbs.sit1.dbsmbanking:id/iv_expandable_item_head_expand']")
	private MobileElement DepositAccountButtonDBS;

	public MobileElement DepositAccountButtonDBS() {
		return DepositAccountButtonDBS;
	}

	@ElementDescription(value = "Deposit Account Button")
	@AndroidFindBy(xpath = "//android.widget.ImageView[@resource-id='com.dbs.sit1.posbmbanking:id/iv_expandable_item_head_expand']")
	private MobileElement DepositAccountButtonPOSB;

	public MobileElement DepositAccountButtonPOSB() {
		return DepositAccountButtonPOSB;
	}

	@ElementDescription(value = "Deposit Account Button")
	@AndroidFindBy(xpath = "//android.widget.ImageView[@resource-id='com.dbs.sg.uat.dbsiwealth:id/iv_expandable_item_head_expand']")
	private MobileElement DepositAccountButtoniWealth;

	public MobileElement DepositAccountButtoniWEALTH() {
		return DepositAccountButtoniWealth;
	}

	@ElementDescription(value = "Local recipient List Text")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'id/tv_expandable_item_sub_title')]")
	private List<MobileElement> SubTitleTextList;

	
	public List<MobileElement> SubTitleTextList() {
		return SubTitleTextList;                           
	}
	@ElementDescription(value = "Local recipient List clickable")
	@AndroidFindBy(xpath = "//android.widget.LinearLayout[contains(@resource-id,':id/root_ll')]")
	private List<MobileElement> ListElementToClickable;
	
	public List<MobileElement> ListElementToClickable() {
		return ListElementToClickable;
	}
	
	@ElementDescription(value = "select fund source list on basis of account name")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'id/text_src_acc_name')]")
	private List<MobileElement> selectFundSourceListAccountName;
	
	public List<MobileElement> selectFundSourceListAccountName() {
		return selectFundSourceListAccountName;
	}
	
	@ElementDescription(value = "select fund source list on basis of account number")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'id/text_src_acc_no')]")
	private List<MobileElement> selectFundSourceListAccountNumber;
	
	public List<MobileElement> selectFundSourceListAccountNumber() {
		return selectFundSourceListAccountNumber;
	}
	@ElementDescription(value = "select fund source list on basis of account name")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'id/account_name')]")
	private List<MobileElement> selectFundSourceListAccountName2;
	
	public List<MobileElement> selectFundSourceListAccountName2() {
		return selectFundSourceListAccountName2;
	}
	
	@ElementDescription(value = "select fund source list on basis of account number")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'id/account_number')]")
	private List<MobileElement> selectFundSourceListAccountNumber2;
	
	public List<MobileElement> selectFundSourceListAccountNumber2() {
		return selectFundSourceListAccountNumber2;
	}
	
	@ElementDescription(value = "expand button 2")
	@AndroidFindBy(xpath = "//android.widget.ImageView[contains(@resource-id,':id/ic_logo_footer_iv')]")
	private MobileElement expandButton2;

	public MobileElement expandButton2() {
		return expandButton2;
	}
	@ElementDescription(value = "Reference Number value")
	@AndroidFindBy(xpath = "(//android.widget.TextView[contains(@resource-id,'id/tv')])[11]")
	private MobileElement referenceNumberValue2;

	public MobileElement referenceNumberValue2() {
		return referenceNumberValue2;
	}
	
	@ElementDescription(value = "Deposite Home page")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Deposits')]")
	private MobileElement depositeHomePage;

	public MobileElement depositeHomePage() {
		return depositeHomePage;
	}
	@ElementDescription(value = "Account section Home page")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'ACCOUNTS')]")
	private MobileElement accountSectionHomePage;

	public MobileElement  accountSectionHomePage() {
		return  accountSectionHomePage;
	}
	@ElementDescription(value = "Account Name Home page")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'id/text_src_acc_name')]")
	private MobileElement accountNameHomePage;

	public MobileElement  accountNameHomePage() {
		return  accountNameHomePage;
	}
	@ElementDescription(value = "currency Home page")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'id/tv_currency_label')]")
	private MobileElement currencyHomePage;

	public MobileElement  currencyHomePage() {
		return  currencyHomePage;
	}
	@ElementDescription(value = "Amount value Home page")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'id/tv_balance_amount')]")
	private MobileElement amountValueHomePage;

	public MobileElement  amountValueHomePage() {
		return  amountValueHomePage;
	}
	
	@ElementDescription(value = "Oversea reciepent List")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'id/tv_expandable_item_head_title')]")
	private List<MobileElement> overseaRecipientList;
	
	public List<MobileElement> overseaRecipientList() {
		return overseaRecipientList;
	}


	@ElementDescription(value = "Page Header")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,':id/text')]")
	private MobileElement PageHeaderForOpenAccount;

	public MobileElement PageHeaderForOpenAccount() {
		return PageHeaderForOpenAccount;
	}
	
	@ElementDescription(value = "Back Btn Image View")
	@AndroidFindBy(xpath = "//android.widget.ImageView[contains(@resource-id,':id/back_btn')]")
	private MobileElement BackBtnImageView;
	
	@ElementDescription(value = "Personal & Contact Details")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Personal & Contact Details']")
	private MobileElement PersonalAndContactDetailsTab;
	
	@ElementDescription(value = "Mailing Address")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Mailing Address']")
	private MobileElement MailingAddressTab;
	
	@ElementDescription(value = "Update Contact Details Page Header.")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,':id/text_toolbar_title')]")
	private MobileElement UpdateContactDetailsPageHeader;
	
	@ElementDescription(value = "Contact Details Title.")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,':id/tv_upp_cd_section_title_id')]")
	private MobileElement ContactDetailsTitle;
	
	@ElementDescription(value = "Terms And Conditions Msg.")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,':id/tv_upp_ters_condns_label__id')]")
	private MobileElement TermsAndConditionsMsg;
	
	@ElementDescription(value = "Review Request Header.")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,':id/text_toolbar_title')]")
	private MobileElement ReviewRequestHeader;
	
	@ElementDescription(value = "Email Notes.")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,':id/tv_upp_email_notes_id')]")
	private MobileElement EmailNotes;
	
	@ElementDescription(value = "Contact Details Change Button.")
	@AndroidFindBy(xpath = "//android.widget.Button[contains(@resource-id,':id/tv_upp_change_contact_details_btn_id')]")
	private MobileElement ContactDetailsChangeBtn;
	
	@ElementDescription(value = "Personal Perticular Change Button.")
	@AndroidFindBy(xpath = "//android.widget.Button[contains(@resource-id,':id/tv_upp_change_personal_perticulars_btn_id')]")
	private MobileElement PersonalPerticularChangeBtn;
	
	@ElementDescription(value = "Personal Perticular Section Title.")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,':id/tv_upp_pp_section_title_id')]")
	private MobileElement PersonalPerticularSectionTitle;
	
	
	@ElementDescription(value = "Marketing Message Title.")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,':id/tv_upp_mm_section_title_i')]")
	private MobileElement MarketingMessageTitle;
	
	@ElementDescription(value = "Marketing Message Notes.")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,':id/tv_upp_marketing_messages_notes_id')]")
	private MobileElement MarketingMessageNotes;
	
	@ElementDescription(value = "Upp Section Label.")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,':id/tv_upp_section_label__id')]")
	private MobileElement UPPSectionLabel;
	
	@ElementDescription(value = "Confirm Button")
	@AndroidFindBy(xpath = "//android.widget.Button[@text='CONFIRM']")
	private MobileElement ConfirmBtn;
	
	@ElementDescription(value = "Completion Status Image")
	@AndroidFindBy(xpath = "//android.widget.ImageView[@resource-id='com.dbs.sit1.dbsmbanking:id/iv_status_completion']")
	private MobileElement CompletionStatusImage;
	
	@ElementDescription(value = "Successfully Updated Message")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,':id/text_transfer_result')]")
	private MobileElement SuccessfullyUpdatedMessageEle;
	
	@ElementDescription(value = "UPDATE MORE DETAILS Button.")
	@AndroidFindBy(xpath = "//android.widget.Button[@text='UPDATE MORE DETAILS']")
	private MobileElement UpdateMoreDetailsBtn;
	
	@ElementDescription(value = "BACK TO MORE SERVICES Button")
	@AndroidFindBy(xpath = "//android.widget.Button[@text='BACK TO MORE SERVICES']")
	private MobileElement BACKTOMoreServicesBtn;


	public MobileElement UpdateMoreDetailsBtn() { 
		return UpdateMoreDetailsBtn;
	}
	
	public MobileElement BACKTOMoreServicesBtn() { 
		return BACKTOMoreServicesBtn;
	}
	
	public MobileElement SuccessfullyUpdatedMessageEle() { 
		return SuccessfullyUpdatedMessageEle;
	}

	public MobileElement CompletionStatusImage() { 
		return CompletionStatusImage;
	}
	
	public MobileElement ConfirmBtn() { 
		return ConfirmBtn;
	}
	
	public MobileElement UPPSectionLabel() { 
		return UPPSectionLabel;
	}
	
	public MobileElement ReviewRequestHeader() { 
		return ReviewRequestHeader;
	}
	
	public MobileElement MarketingMessageNotes() { 
		return MarketingMessageNotes;
	}
	
	public MobileElement TermsAndConditionsMsg() { 
		return TermsAndConditionsMsg;
	}

	public MobileElement MarketingMessageTitle() { 
		return MarketingMessageTitle;
	}
	
	public MobileElement PersonalPerticularChangeBtn() { 
		return PersonalPerticularChangeBtn;
	}
	
	public MobileElement PersonalPerticularSectionTitle() { 
		return PersonalPerticularSectionTitle;
	}
	
	
	public MobileElement ContactDetailsChangeBtn() { 
		return ContactDetailsChangeBtn;
	}
	
	public MobileElement EmailNotes() { 
		return EmailNotes;
	}
	
	@ElementDescription(value = "Contact Details Titles List.")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,':id/tv_title_id')]")
	private List<MobileElement> ContactDetailsTitlesList;
	
	@ElementDescription(value = "Contact Details Values List.")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,':id/tv_value_id')]")
	private List<MobileElement> ContactDetailsValuesList;
	
	public List<MobileElement> ContactDetailsValuesList() { 
		return ContactDetailsValuesList;
	}
	
	public List<MobileElement> ContactDetailsTitlesList() { 
		return ContactDetailsTitlesList;
	}
	
	public MobileElement ContactDetailsTitle() {
		return ContactDetailsTitle;
	}
	
	public MobileElement UpdateContactDetailsPageHeader() {
		return UpdateContactDetailsPageHeader;
	}
	
	public MobileElement BackBtnImageView() {
		return BackBtnImageView;
	}
	
	public MobileElement PersonalAndContactDetailsTab() {
		return PersonalAndContactDetailsTab;
	}
	
	public MobileElement MailingAddressTab() {
		return MailingAddressTab;
	}
	
	

	@ElementDescription(value = "POSB SAYE Account SelectOpenAccountOption")
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.dbs.sit1.dbsmbanking:id/tv_lable1']")
	private List<MobileElement> SelectOpenAccountOptionList;

	public List<MobileElement> SelectOpenAccountOptionList() {
		return SelectOpenAccountOptionList;
	}

	@ElementDescription(value = "Amount type in oversea")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'account type')]")
	private MobileElement accountTypeInOversea;

	public MobileElement  accountTypeInOversea() {
		return  accountTypeInOversea;
	}
	@ElementDescription(value = "purpos in oversea")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'purpose')]")
	private MobileElement purposTypeInOversea;

	public MobileElement  purposTypeInOversea() {
		return  purposTypeInOversea;
	}
	@ElementDescription(value = "mobile no. in oversea")
	@AndroidFindBy(xpath = "//android.widget.EditText[contains(@text,'mobile number')]")
	private MobileElement mobileNoInOversea;

	public MobileElement  mobileNoInOversea() {
		return  mobileNoInOversea;
	}
	@ElementDescription(value = "common List.")
	@AndroidFindBy(xpath = "//android.widget.LinearLayout/android.widget.TextView")
	private List<MobileElement> commonList;
	
	public List<MobileElement> commonList() { 
		return commonList;
	}
	@ElementDescription(value = "overseas transfer header 2")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'id/text_title_mfe')]")
	private MobileElement overseasTransferHeader;

	public MobileElement  overseasTransferHeader() {
		return  overseasTransferHeader;
	}
	@ElementDescription(value = "credit card limit calender header")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'id/tv_displaying_range_header')]")
	private MobileElement calenderHeaderCreditLimit;

	public MobileElement  calenderHeaderCreditLimit() {
		return  calenderHeaderCreditLimit;
	}
	@ElementDescription(value = "select date oct 11")
	@AndroidFindBy(xpath = "(//android.widget.TextView[@text='11'])[1]")
	private MobileElement selectDateOctEleven;

	public MobileElement  selectDateOctEleven() {
		return  selectDateOctEleven;
	}
	

	@ElementDescription(value = "Deposits Account Type")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Deposits']")
	private MobileElement DepositsAccountType;

	@ElementDescription(value = "Deposits Account Name")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,':id/text_src_acc_name')]")
	private List<MobileElement> DepositsAccountName;

	@ElementDescription(value = "Deposits Account Type")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,':id/tv_value')]")
	private List<MobileElement> AccountValueList;

	@ElementDescription(value = "Deposits Account Name")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,':id/tv_title')]")
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
	
	@ElementDescription(value = "Select Peek balance")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Peek Balance']")
	private MobileElement SelectPeekBalance;
	
	@ElementDescription(value = "Peek balance Toggle")
	@AndroidFindBy(xpath = "//android.widget.Switch[contains(@resource-id,':id/btn_switch')]")
	private MobileElement PeekBalanceToggle;
	
	@ElementDescription(value = "Account for Peek Balance Dropdown.")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Account for Peek Balance']")
	private MobileElement AccountForPeekBalanceDropdown;
	
	@ElementDescription(value = "Selected Account For Peek Balance.")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,':id/dropdown_pb_acc')]")
	private MobileElement SelectedAccountForPeekBalance;
	
	@ElementDescription(value = "Save Button to Save Peek Balance on login page.")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Save']")
	private MobileElement SaveBtn;
	
	@ElementDescription(value = "Personalize Your Device Pop Up.")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Personalize Your Device']")
	private MobileElement PersonalizeYourDevicePopup;
	
	@ElementDescription(value = "OKBtn Personalize Your Device Pop Up.")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='OK']")
	private MobileElement OKBtn_PersonalizeYourDevicePopup;
	
	@ElementDescription(value = "Close Btn To Closing Tap To Star Page After Log out.")
	@AndroidFindBy(xpath = "//android.widget.ImageView[contains(@resource-id,':id/btn_close')]")
	private MobileElement CloseBtnToClosingTapToStarPage;
	
	@ElementDescription(value = "Peek balance Sub title On login page.")
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.dbs.sit1.dbsmbanking:id/tv_peek_balance_sub_title']")
	private MobileElement PeekBalanceSubtitle;
	
	@ElementDescription(value = "Log In Button")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Log In']")
	private MobileElement LogInButton;
	
	@ElementDescription(value = "NotYou Link")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Not you?']")
	private MobileElement NotYouLink;
	
	@ElementDescription(value = "Peek Balance Deregister Button")
	@AndroidFindBy(xpath = "//android.widget.Button[@text='DEREGISTER']")
	private MobileElement PeekbalanceDeregisterButton;
	
	public MobileElement PeekbalanceDeregisterButton() {
		return PeekbalanceDeregisterButton;
	}
	
	public MobileElement NotYouLink() {
		return NotYouLink;
	}
	
	public MobileElement LogInButton() {
		return LogInButton;
	}
	
	public MobileElement PeekBalanceSubtitle() {
		return PeekBalanceSubtitle;
	}
	
	public MobileElement CloseBtnToClosingTapToStarPage() {
		return CloseBtnToClosingTapToStarPage;
	}
	
	public MobileElement OKBtn_PersonalizeYourDevicePopup() {
		return OKBtn_PersonalizeYourDevicePopup;
	}
	
	public MobileElement PersonalizeYourDevicePopup() {
		return PersonalizeYourDevicePopup;
	}
	
	public MobileElement SaveBtn() {
		return SaveBtn;
	}
	
	public MobileElement SelectedAccountForPeekBalance() {
		return SelectedAccountForPeekBalance;
	}
	
	
	public MobileElement AccountForPeekBalanceDropdown() {
		return AccountForPeekBalanceDropdown;
	}
	
	public MobileElement PeekBalanceToggle() {
		return PeekBalanceToggle;
	}
	
	public MobileElement SelectPeekBalance() {
		return SelectPeekBalance;
	}
	
	public MobileElement ToolbarBackIcon() {
		return ToolbarBackIcon;
	}
	
	public MobileElement UserAccountNumber() {
		return UserAccountNumber;
	}
	
	public MobileElement UserAccountName() {
		return UserAccountName;
	}
	
	public List<MobileElement> AccountTitleList() {
		return AccountTitleList;
	}
	
	public List<MobileElement> AccountValueList() {
		return AccountValueList; 
	}
	
	public List<MobileElement> DepositsAccountName() {
		return DepositsAccountName;
	}
	
	public MobileElement DepositsAccountType() {
		return DepositsAccountType;
	}
	
	@ElementDescription(value = "Credit Card Page Header.")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,':id/text_toolbar_title')]")
	private MobileElement CreditCard_PageHeader;

	@ElementDescription(value = "SHARE PAYMENT DETAILS Button.")
	@AndroidFindBy(xpath = "//android.widget.Button[@text='SHARE PAYMENT DETAILS']")
	private MobileElement SharePaymentDetailsButton;
	
	public MobileElement SharePaymentDetailsButton() {
		return SharePaymentDetailsButton;
	}
	
	public MobileElement CreditCard_PageHeader() {
		return CreditCard_PageHeader;
	}
	@ElementDescription(value = "OK Button")
	@AndroidFindBy(xpath = "//android.widget.Button[@text='OK']")
	private MobileElement Alert_OKButton;
	
	public MobileElement Alert_OKButton() {
		return Alert_OKButton;
	}

}
