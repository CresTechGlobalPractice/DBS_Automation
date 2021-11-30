package com.crestech.common.utilities;

public enum CommonTestData {
	
	//Payee Add Local To Other Bank
	LOCAL_RECIPIENT_NAME("Payee OtherBank "),
	LOCAL_RECIPIENT_ACCOUNT_NUMBER("12322354"),
	LOCAL_RECIPIENT_BANK_NAME("BANK OF INDIA"),
	
	//Payee Add DBS POSB
	PAYEEADD_DBSPOSB_RECIPIENT_NAME("Payee DBSPOSB "),
	PAYEEADD_DBSPOSB_BANK_NAME("DBS/POSB"),
	PAYEEADD_DBSPOSB_ACCOUNT_NUMBER("0289488348"),
	
	//Payee Add to BillPayment
	PAYEEADD_BILLPAYMENT_ACCOUNTNAME("DBS CASHLINE"),
	PAYEEADD_BILLPAYMENT_REFERENCENUMBER("0824867822"),
	
	//Payee-Add-Remittance
	COUNTRY_AUS("Australia"),
	CURRENCY_AUS("AUD"),
	BANK_BCD_CODE("610104"),
	ACCOUNT_NO("900132012904"),
	FULL_NAME("AUTOAUS4"),
	ADDRESS("Cherry Blossom Flower Drive 90 Canbera 123334"),
	CITY("Canbera"),
	
	//FundTransfer NonFast
	LOCAL_RECIPIENTS_TAB_NAME("Local Recipients"),
	ERROR_MSG("We are unable to process your request. Please contact our Customer Service Hotline at 1800-111 1111 or +65-6327 2265(if you are calling from abroad)."),
	FUNDTRANSFER_NONFAST_TO_ACCOUNTNUMBER_WITHBANK("INDIAN OVERSEAS BANK 582177606001"),
	FUNDTRANSFER_NONFAST_FROM_ACCOUNT_NAME("DBS SAVINGS PLUS"),
	FUNDTRANSFER_NONFAST_FROM_ACCOUNT_NUMBER("028-9-333111"),
	FUNDTRANSFER_NONFAST_TO_RECIPIENT_NAME("Sakshi"),
	FUNDTRANSFER_NONFAST_TO_BANKNAME("INDIAN OVERSEAS BANK"),
	FUNDTRANSFER_NONFAST_TO_ACCOUNTNUMBER("582177606001"),
	
	//FundTransferTo OwnAccount
	FUNDTRANSFER_TO_OWN_ACCOUNT_NUMBER("420-86386-1"),
	FUNDTRANSFER_TO_OWN_ACCOUNT_NAME("POSB STATEMENT SAVINGS"),
	FUNDTRANSFER_FROM_OWN_ACCOUNT_NUMBER("028-976191-5"),
	FUNDTRANSFER_FROM_OWN_ACCOUNT_NAME("DBS Multiplier Account"),
	
	
	
	//Page Headers in Application
	RECIPIENT_DETAILS_PAGEHEADER("Recipient's Details"),
	REVIEW_RECIPIENT_LABEL("REVIEW RECIPIENT'S DETAILS"),
	OVERSEAS_TRANSFER_PAGEHEADER("Overseas Transfer"),
	SET_CARD_PIN("Set Card PIN"),
	
	
	//App Package And Activity
	DBS_APPS_ACTIVITY("com.dbs.sg.digibank.ui.demo.SplashActivity"),
	DBS_APP_PACKAGE("com.dbs.sit1.dbsmbanking"),
	POSB_APP_PACKAGE("com.dbs.sit1.posbmbanking"),
	IWEALTH_APP_PACKAGE("com.dbs.sg.uat.dbsiwealth"),
	DBS_APP_APK("DBS_Android.apk"),
	POSB_APP_APK("POSB_Android.apk"),
	iWEALTH_APP_APK("iWealth_Android.apk"),
	DBS_DIGIBANK("DBS digibank"),
	ACCOUNT_NAME("POSB STATEMENT SAVINGS"),
	
	
	//ApplyDebitCard
	DEBIT_CARD_NAME("PASSION POSB MASTERCARD DEBIT CARD"),
	ACCOUNT_LINKED_WITH_DEBIT_CARD("POSB eSAVINGS"),
	TITLE("Ms"),
	RACE("Chinese"),
	MARITAL_STATUS("Single"),
	RESIDENCE_TYPE("HDB"),
	EDUCATION("PSLE"),
	ECONOMIC_STATUS("Professional"),
	ANNUAL_INCOME("S$60,001 - S$90,000"),
		
	// OpenAccount
	OPEN_ACCOUNT_OPTION("POSB SAYE Account"), 
	SELECT_ACCOUNT("DBS SAVINGS PLUS"),
	
	
	//Relanching alert message
	RELAUNCH_MESSAGE("Your application's security profile has been updated. Please re-launch the application to continue."),
	
	//post login alert message
	FINGERPRINT_MESSAGE("Turn on a few nifty features, such as Fingerprint Login and Peek balance, to improve your experience."),
	RECORDERSECTION_MESSAGE("You can reorder sections on your home page"),
	
	//ScreenName-OneAppPostLogin
	WELCOME("Welcome to"),
	DIGIBANK("digibank"),
	LOGOUT("LOG OUT"),
	
	//ScreenName-Tap on the stars to rate
	RATE_MESSAGE("Tap on the stars to rate"),
	DIGITAL_TOKEN_SETUP_MESSAGE("Set up your Digital Token"),
	EMAIL_OTP_MESSAGE("Step 1: Email OTP"),
	SMS_OTP_MESSAGE("Step 2: SMS OTP"),
	DIGITAL_TOKEN_MESSAGE_AFTER_STEPUP("You've set up your digital token"),
	OTP("123456"),
	
	
	PAY_TRANSFER_ICON("Pay & Transfer"),
	OVERSEAS_ICON("Overseas"),


	

	
    //paylah
	TOPUP_LABEL("Top up"),
	PAYLAH_LABEL("Paylah!"),
	TOPUP_PAYLAH_LABEL("Top Up Paylah!"),
	TOPUP_REVIEW_LABEL("Review Top-Up"),
	TOPUP_NOW_BUTTOM_LABEL("TOP UP NOW"),
	TOPUP_UP_DONE_LABEL("Top-up Done"),
	LOGOUT_PAYLAH("Log Out"),
	AMOUNT_PAYLAH("5.00"),
	CORRIDOR_AMOUNT("34"),
	eOTT_AMOUNT("11.65"),
	TRANSFER_SUBMITTED_MSG("Transfer Submitted"),
	REVIEW_TRANSFER("Review Transfer"),
	REFERENCE_NUMBER_TEXT("Reference No."),
	
	OVERSEAS_TRANSFER_TEXT("OVERSEAS TRANSFER"),
	EOTTREMITTANCE_NAME("BHDeott"),
	
	LOCAL_TRANSFER_PayNow("Local Transfer & PayNow"),
	PURPOSE_OF_TRANSFER_TEXT("Family support (Workers' remittances)"),
	REVIEW_RECIPIENT_DETAILS("Review Recipient's Details"),

	SECURE_PIN_PAGE_HEADER("Secure PIN"),
	

	YOU_HAVE_ADDED_RECIPIENT_MSG("You’ve added a recipient"),

	NAMETO_APPEAR_ON_DEBITCARD("DBS Passion"),
	REVIEW_APPLICATION("Review Application"),
	CREATE_PIN("123456"),
	CONFIRM_PIN("123456"),
	ENTER_RECIPIENT_DETAILS("Enter Recipient's Details"),
	PAY_TO_BILLER_PAGE_HEADER("Pay to Biller"),
	AMOUNTTO_TRANSFERFUND("10"),
	REVIEW_PAYMENT_PAGEHEADER("Review Payment"),
	PAYMENT_SUBMITTED("Payment Submitted"),
	OPEN_ACCOUNT("Open Account"),
	ACCOUNT_BENIFITS("Account Benefits"),
	MONTHLY_SAVING_AMT_BALANCE("50"),
	IMPORTANT_NOTES("Important Notes"),
	WARNING_HEADING_TEXT("Warning Advisory from the Singapore Police Force"),
	REVIEW_ACCOUNT_APPLICATION("Review Account Application"),
	YOUR_ACCOUNT_OPEN_READYTOUSE_MESSAGE("Your account is open and ready to use!"),
	APPLICATION_SUBMITTED("Application Submitted"),
	UPGRADE_EXPERIENCE_MESSAGE("Upgrade your banking experience with us"), 
	SETUP("Let's Set Up"),
	MOTION_FITNESS_MESSAGE("“DBS digibank” Would Like to Access Your Motion & Fitness Activity"),
	// ScreenName-OneAppPostLogin
	LOGOUT_ISO("logout"), 
	SGD_CURRENCY_LABEL("SGD"), 
	REVIEW_TOPUP_HEADER_IOS("Review Top-Up"),
	LOGOT_PAYLAH_IOS("Log Out"), 
	TITLE_2FA("Secure PIN"), 
	DONE_2FA_IOS("Done"),
	PIN_2FA("123456"),
	// local transfer limit increase
	MORE_LABEL("More"),
	LOCAL_FUND_Transfer_LABEL("Local Fund Transfer"),
	LOCAL_TRANSFER_LIMIT_LABEL("Local Transfer Limit"),
	LOCAL_TRANSFER_LIMIT_SEARCHBOX_IOS("local"),
	TO_OTHERBANK_LABEL("To Other Banks"), 
	TRANSFER_TO_OTHERBANK_LABEL_LABEL("Transfer to Other Banks"),
	SELECTED_LIMIT_1("40,000.00"), 
	SELECTED_LIMIT_50000("50,000.00"), 
	SELECTED_LIMIT_0("0.00"),
	LOCAL_TRANSFER_LABEL("Local Transfer Limit"), 
	TRANSFER_TO_OTHERBANK_LABEL("Transfer to Other Banks"),
	CHANGE_DAILY_LIMIT_BUTTON("CHANGE DAILY LIMIT NOW"), 
	BACK_TOMORE_BUTTON("BACK TO MORE"),
	LOCAL_TRANSFER_CAHNGE_TITLE("Local Transfer Limit Changed!"), 
	SET_DAILY_LIMIT_TITLE("Set Daily Limit"),
	REVIEW_DAILY_LIMIT_TITLE("Review Daily Limit"),
	TOPUP_LABEL_IOS("Top Up"),

	
	
	////fund transfer posbdbs
	ALL_SECTION("ALL"),
	LOCAL_RECIPIENT_FROMLIST("Local Recipients"),
	DBS_CURRENT_ACCOUNT_TEXT("DBS CURRENT ACCOUNT"),
	TRANSFER_DBS_POSB("Transfer to DBS/POSB"),
	SELECT_SOURCE_FUND("Select Fund Source"),
	DBS_MULTIPLIER_ACCOUNT_TEXT("DBS Multiplier Account"),
	PRIMARY_SOURCE_ALERT_TITLE("Primary source of fund"),
	OK_BUTTON("OK"),
	REVIEW_TRANSFER_LABEL("Review Transfer"),
	TRANSFER_NOW_BUTTON("TRANSFER NOW"),
	TRANSFER_TITLE("Transferred"),
	
	TRANSCETION_HISTORY_LABEL("Transaction History"),
	ACCOUNT_STATEMENT_NAME("POSB STATEMENT SAVINGS 420-86201-6"),
	HOME_BUTTON("Home"),
	BILLING_ORGANISATION_LABEL("Billing Organisations"),
	TRANSFER_OTHERBANK("Transfer to Other Banks"),
	COMMENT_LOCAL_RECIPIENT("Non Fast transfer"),
	IMMEDIATE_TEXT("Immediate"),
	AMOUNT_FUNDTRANSFER("11"),
	AMOUNT_FUNDTRANSFER_VERIFY("11.00"),

	TRANSFER_TO_YOUR_ACCOUNT("Transfer to Your Account"),
	TRANSFERRED("Transferred"),
    STATEMENT_TITLE("POSB STATEMENT SAVINGS"),
	SHOW_BUTTON("SHOW"),
	


	TEMP_LIMIT_INCREASE("Temporary Credit Limit Increase"),
	TEMP_LIMIT_INCREASE_TITLE("Temporary Limit Increase"),
	REVIEW_APPLICATION_CREDITLIMIT_TITLE("Review Application"),
	ADD_RECIPIENT_LABEL("ADD RECIPIENT NOW"),
	ADD_NOW_RECIPIENT_LABEL("ADD RECIPIENT NOW"),
	MAKE_TRANSFER("MAKE A TRANSFER"),
	REFERENCE_NUMBER("Reference No."),
	
	;
	
	private final String message;

	private CommonTestData(String message) {
		this.message = message;
	}

	public String getEnumValue() throws Exception {
		try {
			if (this.message == null)
				throw new Exception("The Message isn't provided");
			return this.message;
		} catch (Exception e) {
			throw new Exception("CommonTestData::EnumValue() Exception : " + e.getLocalizedMessage());
		}
	}

}
