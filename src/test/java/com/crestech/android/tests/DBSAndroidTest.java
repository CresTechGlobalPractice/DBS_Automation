package com.crestech.android.tests;

import java.util.logging.Logger;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.crestech.annotation.values.Author;
import com.crestech.base.UserBaseTest;
import com.crestech.common.utilities.Asserts;
import com.crestech.common.utilities.CommonTestData;
import com.crestech.common.utilities.HandleException;
import com.crestech.listeners.TestListener;
import com.crestech.pages.DBSAndroidPage;
import io.qameta.allure.Description;

@Listeners(TestListener.class)
public class DBSAndroidTest extends UserBaseTest {
	
	Asserts Assert = null;
	DBSAndroidPage dbspage1 = null;
	public DBSAndroidTest() throws Exception {
		super();
		Assert = new Asserts();
	}
	

	Logger logger = Logger.getLogger(DBSAndroidTest.class.getName());
	

	
	/******************Start Test Script For DBS App************************************/
//	@Parameters({ "userName", "password" })
//	@Test(priority = 1, enabled = true, description = "AccountDetails-CASA-ONEAPP-14400")
//	@Description(value = "Execution of this testcase:: Verify the account detail on dashboard page. ")
//	@Author(name = "Shafqat Ali")
//	public void AccountDetails_CASA(String userName, String password) throws Exception {
//		try {
//			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
//			dbspage.logInApplication(userName, password, "DBS");
//			dbspage.verifyAccountTypeNameCurrencyAmount(CommonTestData.ACCOUNT_TYPE.getEnumValue() ,CommonTestData.ACCOUNT_NAME_HOME.getEnumValue(),CommonTestData.CURRENCY.getEnumValue() );
//		} catch (HandleException e) {
//			Asserts.assertFail(e.getCode()+"--> "+ e.getMessage()+ e.getCause());
//		}
//		catch (Exception e) {
//			Asserts.assertFail( "Unable to execute Account Details CASA "+e.getMessage());
//		}
//	}
//	
//	@Parameters({ "userName", "password" })
//	@Test(priority = 2, enabled = true, description = "Logout-ONEAPP-9392")
//	@Description(value = "Execution of this testcase:: Verify the Logout functionality for Applications.")
//	@Author(name = "Shafqat Ali")
//	public void Logout_ONEAPP(String userName, String password) throws Exception {
//		try {
//			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
//			dbspage.logInApplication(userName, password, "DBS");
//			dbspage.clickOnLogoutAndVerify(CommonTestData.LOGOUT.getEnumValue(),
//					CommonTestData.RATE_MESSAGE.getEnumValue());
//		} catch (HandleException e) {
//			Asserts.assertFail(e.getCode()+"--> "+ e.getMessage()+ e.getCause());
//		}
//		catch (Exception e) {
//			Asserts.assertFail( "Unable to execute Log Out "+e.getMessage());
//		}
//	}
//	
//	@Parameters({ "userName", "password" })
//	@Test(priority = 3, enabled = true, description = "Remittance-Corridor-ONEAPP-13407")
//	@Description(value = "Execution of this testcase:: Verifies the Remittance Corridor")
//	@Author(name = "Divya Devi")
//	public void Remittance_Corridor(String userName, String password) throws Exception {
//		try {
//			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
//			dbspage.logInApplication(userName, password, "DBS");
//			dbspage.VerifyRemittanceCorridor();
//		} catch (HandleException e) {
//			Asserts.assertFail(e.getCode()+"--> "+ e.getMessage()+ e.getCause());
//		}
//		catch (Exception e) {
//			Asserts.assertFail( "Unable to execute Remittance Corridor "+e.getMessage());
//		}
//	}
//
//	@Parameters({ "userName", "password" })
//	@Test(priority = 4, enabled = true, description = "Remittance-eOTT-ONEAPP-14008")
//	@Description(value = "Execution of this testcase:: Verifies the Remittance eOTT.")
//	@Author(name = "Divya Devi")
//	public void Remittance_eOTT(String userName, String password) throws Exception {
//		try {
//			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
//			dbspage.logInApplication(userName, password, "DBS");
//			dbspage.VerifyRemittanceEOTT();
//		} catch (HandleException e) {
//			Asserts.assertFail(e.getCode()+"--> "+ e.getMessage()+ e.getCause());
//		}
//		catch (Exception e) {
//			Asserts.assertFail( "Unable to execute Remittance EOTT "+e.getMessage());
//		}
//	}
//
//	@Parameters({ "userName", "password" })
//	@Test(priority = 5, enabled = true, description = "Payee-Add-DBSorPOSB-ONEAPP-14675")
//	@Description(value = "Execution of this testcase:: Verifies the Payee add DSB or POSB.")
//	@Author(name = "Divya Devi")
//	public void Payee_Add_ToOwnAccount(String userName, String password) throws Exception {
//		try {
//			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
//			dbspage.logInApplication(userName, password, "DBS");
//			dbspage.DeletePayeeDBSPOSB(CommonTestData.PAYEEADD_DBSPOSB_RECIPIENT_NAME.getEnumValue());
//			dbspage.VerifyAddPayeeDBSorPOSB();
//		} catch (HandleException e) {
//			Asserts.assertFail(e.getCode()+"--> "+ e.getMessage()+ e.getCause());
//		}
//		catch (Exception e) {
//			Asserts.assertFail( "Unable to execute Payee Add To DBS/POSB "+e.getMessage());
//		}
//	}
//
//	@Parameters({ "userName", "password" })
//	@Test(priority = 6, enabled = true, description = "TopUp-PayLah-ONEAPP-13915")
//	@Description(value = "Execution of this testcase:: Verify the Topup Paylah in Applications")
//	@Author(name = "Shafqat Ali")
//	public void Topup_Paylah(String userName, String password) throws Exception {
//		try {
//			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
//			dbspage.logInApplication(userName, password, "DBS");
//			dbspage.TopupPaylah();
//		} catch (HandleException e) {
//			Asserts.assertFail(e.getCode()+"--> "+ e.getMessage()+ e.getCause());
//		}
//		catch (Exception e) {
//			Asserts.assertFail( "Unable to execute TopUp Paylah "+e.getMessage());
//		}
//	}
//		
//	@Parameters({ "userName", "password" })
//	@Test(priority = 7, enabled = true, description = "Payee-Add-Remittance-ONEAPP-13679")
//	@Description(value = "Execution of this testcase:: Verify the Add Remittance payee")
//	@Author(name = "Shafqat Ali")
//	public void Payee_Add_Remittance(String userName, String password) throws Exception {
//		try {
//			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
//            dbspage.logInApplication(userName, password, "DBS");
//            dbspage.DeletePayeeRemittance(CommonTestData.FULL_NAME.getEnumValue());
//			dbspage.PayeeAddRemittance();
//		} catch (HandleException e) {
//			Asserts.assertFail(e.getCode()+"--> "+ e.getMessage()+ e.getCause());
//		}
//		catch (Exception e) {
//			Asserts.assertFail( "Unable to execute Payee Add To Remittance "+e.getMessage());
//		}
//	}
//
//	@Parameters({ "userName", "password" })
//	@Test(priority = 8, enabled = true, description = "ApplyDebitCard-ONEAPP-15861")
//	@Description(value = "Execution of this testcase:: Verifies the Apply Debit Card.")
//	@Author(name = "Divya Devi")
//	public void ApplyDebitCard(String userName, String password) throws Exception {
//		try {
//			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
//			dbspage.logInApplication(userName, password, "DBS");
//			dbspage.ApplyDebitCard();
//		} catch (HandleException e) {
//			Asserts.assertFail(e.getCode()+"--> "+ e.getMessage()+ e.getCause());
//		}
//		catch (Exception e) {
//			Asserts.assertFail( "Unable to execute Apply Debit card"+e.getMessage());
//		}
//	}
//
//	@Parameters({ "userName", "password" })
//	@Test(priority = 9, enabled = true, description = "Payee-Add-Local-OtherBank-ONEAPP-15677")
//	@Description(value = "Execution of this testcase:: Verifies the Payee Add Local Other Bank.")
//	@Author(name = "Divya Devi")
//	public void Payee_Add_Local_OtherBank(String userName, String password) throws Exception {
//		try {
//			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
//			dbspage.logInApplication(userName, password, "DBS");
//			dbspage.DeletePayeeLocalToOtherBank(CommonTestData.LOCAL_RECIPIENT_NAME.getEnumValue());
//			dbspage.PayeeAddLocalOtherBank();
//		} catch (HandleException e) {
//			Asserts.assertFail(e.getCode()+"--> "+ e.getMessage()+ e.getCause());
//		}
//		catch (Exception e) {
//			Asserts.assertFail( "Unable to execute Payee Add Local OtherBank "+e.getMessage());
//		}
//	}
//
//	@Parameters({ "userName", "password" })
//	@Test(priority = 10, enabled = true, description = "Payee-Add-BillPayment-ONEAPP-15938")
//	@Description(value = "Execution of this testcase:: Verifies the Payee Add Bill Payment.")
//	@Author(name = "Divya Devi")
//	public void Payee_Add_BillPayment(String userName, String password) throws Exception {
//		try {
//			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
//			dbspage.logInApplication(userName, password, "DBS");
//			dbspage.DeletePayeeToBillPayment(CommonTestData.PAYEEADD_BILLPAYMENT_ACCOUNTNAME.getEnumValue());
//			dbspage.PayeeAddBillPayment();
//		} catch (HandleException e) {
//			Asserts.assertFail(e.getCode()+"--> "+ e.getMessage()+ e.getCause());
//		}
//		catch (Exception e) {
//			Asserts.assertFail( "Unable to execute Payee Add Bill Payment "+e.getMessage());
//		}
//	}
	

//	@Parameters({ "userName", "password" })
//	@Test(priority = 11, enabled = true, description = "OpenAccount-ONEAPP-14028")
//	@Description(value = "Execution of this testcase:: Verifies the Open Account.")
//	@Author(name = "Divya Devi")
//	public void OpenAccount(String userName, String password) throws Exception {
//		try {
//			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
//			dbspage.logInApplication(userName, password, "DBS");
//			dbspage.OpenAccount();
//		} catch (HandleException e) {
//			Asserts.assertFail(e.getCode()+"--> "+ e.getMessage()+ e.getCause());
//		}
//		catch (Exception e) {
//			Asserts.assertFail( "Unable to execute Open Account "+e.getMessage());
//		}
//	}
//
//	@Parameters({ "userName", "password" })
//	@Test(priority = 12, enabled = true, description = "ChangeLocalFundsTransferLimit-Increase-ONEAPP-7847")
//	@Description(value = "Execution of this testcase:: Successful Change Limit for Transfers to Other Banks Accounts to Increase the Current limit.")
//	@Author(name = "Shafqat Ali")
//	public void ChangeLocalFundsTransferLimit(String userName, String password) throws Exception {
//		try {
//			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
//			dbspage.logInApplication(userName, password, "DBS");
//			dbspage.ChangeLocalFundsTransferLimit();
//		} catch (HandleException e) {
//			Asserts.assertFail(e.getCode()+"--> "+ e.getMessage()+ e.getCause());
//		}
//		catch (Exception e) {
//			Asserts.assertFail( "Unable to execute Change Local Funds Transfer Limit "+e.getMessage());
//		}
//	}
//
//	@Parameters({ "userName", "password" })
//	@Test(priority = 13, enabled = true, description = "PeekBalance-MCA-ONEAPP-13982")
//	@Description(value = "Execution of this testcase:: Verifies the Peek Balance.")
//	@Author(name = "Divya Devi")
//	public void PeekBalance_MCA(String userName, String password) throws Exception {
//		try {
//			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
//			dbspage.logInApplication(userName, password, "DBS");
//			dbspage.VerifyPeekBalance();
//		}  catch (HandleException e) {
//			Asserts.assertFail(e.getCode()+"--> "+ e.getMessage()+ e.getCause());
//		}
//		catch (Exception e) {
//			Asserts.assertFail( "Unable to execute Peek Balance "+e.getMessage());
//		}
//	}
//
//	
//	@Parameters({ "userName", "password" })
//	@Test(priority = 14, enabled = true, description = "FundsTransfer-OwnAccount-ONEAPP-16730")
//	@Description(value = "Execution of this testcase:: Verifies the Fund Transfer Own Account.")
//	@Author(name = "Divya Devi")
//	public void FundsTransferOwnAccount(String userName, String password) throws Exception {
//		try {
//			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
//			dbspage.logInApplication(userName, password, "DBS");
//			dbspage.VerifyFundTransfer_OwnAccount();
//		} catch (HandleException e) {
//			Asserts.assertFail(e.getCode()+"--> "+ e.getMessage()+ e.getCause());
//		}
//		catch (Exception e) {
//			Asserts.assertFail( "Unable to execute Fund Transfer Own Account "+e.getMessage());
//		}
//	}
//
//	@Parameters({ "userName", "password" })
//	@Test(priority = 15, enabled = true, description = "FundsTransfer-OtherBank-NonFAST-Future-THANOS-5244")
//	@Description(value = "Execution of this testcase:: Verifies the Fund Transfer Other Bank Non Fast Future Transfer Date.")
//	@Author(name = "Divya Devi")
//	public void FundsTransfer_OtherBank_NonFAST_Future(String userName, String password) throws Exception {
//		try {
//			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
//			dbspage.logInApplication(userName, password, "DBS");
//			dbspage.FundsTransfer_OtherBank_NonFASTFuture("DBS");
//		} catch (HandleException e) {
//			Asserts.assertFail(e.getCode()+"--> "+ e.getMessage()+ e.getCause());
//		}
//		catch (Exception e) {
//			Asserts.assertFail( "Unable to execute Fund Transfer Other Bank Non Fast Future "+e.getMessage());
//		}
//	}
//
//	@Parameters({ "userName", "password" })
//	@Test(priority = 16, enabled = true, description = "FundsTransfer-OtherBank-NonFAST-ONEAPP-16728")
//	@Description(value = "Execution of this testcase:: Verifies the Fund Transfer Other Bank Non Immediate Transfer Date.")
//	@Author(name = "Divya Devi")
//	public void FundsTransfer_OtherBank_NonFAST(String userName, String password) throws Exception {
//		try {
//			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
//			dbspage.logInApplication(userName, password, "DBS");
//			dbspage.FundsTransfer_OtherBank_NonFAST("DBS");
//		} catch (HandleException e) {
//			Asserts.assertFail(e.getCode()+"--> "+ e.getMessage()+ e.getCause());
//		}
//		catch (Exception e) {
//			Asserts.assertFail( "Unable to execute Fund Transfer Other Bank Non Fast "+e.getMessage());
//		}
//	}
//	

	@Parameters({"userName", "password" })
	@Test(priority=17, enabled=true, description = "FundsTransfer-OtherPOSBDBS-ONEAPP-16723")
	@Description(value = "Execution of this testcase:: Verifies FundTransfer Other DBS/POSB")
	@Author(name = "shafqat")
	public void FundTransferOtherDBSPOSB(String userName,String password) throws Exception {
		try {
			 DBSAndroidPage dbspage = new DBSAndroidPage(driver);
			 dbspage.logInApplication(userName, password, "DBS");
			 dbspage.FundTransferDBSPOSB();
		} catch (HandleException e) {
			Asserts.assertFail(e.getCode()+"--> "+ e.getMessage()+ e.getCause());
		}
		catch (Exception e) {
			Asserts.assertFail( "Unable to execute Fund Transfer Other DBS/POSB "+e.getMessage());
		}
	}
//	
//	@Parameters({"userName", "password" })
//	@Test(priority=18, enabled=true, description = "TransactionHistory-ONEAPP-14312")
//	@Description(value = "Execution of this testcase:: Verifies Transaction History")
//	@Author(name = "shafqat")
//	public void TransactionHistory_DBS(String userName,String password) throws Exception {
//		try {
//			 DBSAndroidPage dbspage = new DBSAndroidPage(driver);
//			 dbspage.logInApplication(userName, password, "DBS");
//			 dbspage.transactionHistoryVerify("DBS");
//			 dbspage.clickOnLogoutAndVerify(CommonTestData.LOGOUT.getEnumValue(), CommonTestData.RATE_MESSAGE.getEnumValue());
//		} catch (HandleException e) {
//			Asserts.assertFail(e.getCode()+"--> "+ e.getMessage()+ e.getCause());
//		}
//		catch (Exception e) {
//			Asserts.assertFail( "Unable to execute Transaction History "+e.getMessage());
//		}
//	}
//	
//	@Parameters({"userName", "password" })
//	@Test(priority=19, enabled=true, description = "Verifies Credit Card Temperory Limit Increase")
//	@Description(value = "Execution of this testcase:: CreditCardTempLimitIncrease-ONEAPP-16669")
//	@Author(name = "shafqat")
//	public void CreditCardTempLimitIncrease(String userName,String password) throws Exception {
//		try {
//			 DBSAndroidPage dbspage = new DBSAndroidPage(driver);
//			 dbspage.logInApplication(userName, password,"DBS");
//			 dbspage.CreditCardTempLimitIncrease();
//		} catch (HandleException e) {
//			Asserts.assertFail(e.getCode()+"--> "+ e.getMessage()+ e.getCause());
//		}
//		catch (Exception e) {
//			Asserts.assertFail( "Unable to execute Credit Card Temp Limit Increase "+e.getMessage());
//		}
//	}
//
	@Parameters({ "userName", "password" })
	@Test(priority = 20, enabled = true, description = "UpdatePersonalParticular-ONEAPP-13921")
	@Description(value = "Execution of this testcase:: Verifies the Update Personal Particular Details.")
	@Author(name = "Divya Devi")
	public void UpdatePersonalParticular(String userName, String password) throws Exception {
			try {
				DBSAndroidPage dbspage = new DBSAndroidPage(driver);
				dbspage1 = dbspage;
				dbspage1.logInApplication(userName, password, "DBS");
				dbspage1.UpdatePersonalDetails("DBS");
			} catch (HandleException e) {
				dbspage1.verifyDigibankAlert();
				Asserts.assertFail(e.getCode()+"--> "+ e.getMessage()+ e.getCause());
			}
			catch (Exception e) {
				dbspage1.verifyDigibankAlert();
				Asserts.assertFail( "Unable to execute update Personal Particular "+e.getMessage());
			}
	}
	
//	@Parameters({ "userName", "password" })
//	@Test(priority = 21, enabled = true, description = "FundsTransfer-OtherBank-FAST-FUTURE-THANOS-5245")
//	@Description(value = "Execution of this testcase:: Verifies the Fund Transfer Other Bank Fast Future Transfer Date.")
//	@Author(name = "Divya Devi")
//	public void FundsTransfer_OtherBank_FAST_FUTURE(String userName, String password) throws Exception {
//		try {
//			
//			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
//			dbspage1 = dbspage;
//			dbspage1.logInApplication(userName, password, "DBS");
//			dbspage1.FundsTransfer_OtherBank_FASTFuture("DBS");
//		} catch (HandleException e) {
//			dbspage1.verifyDigibankAlert();
//			Asserts.assertFail(e.getCode()+"--> "+ e.getMessage()+ e.getCause());
//		}
//		catch (Exception e) {
//			dbspage1.verifyDigibankAlert();
//			Asserts.assertFail( "Unable to execute Fund Transfer Other bank Fast Future "+e.getMessage());
//		}
//	}

//	@Parameters({ "userName", "password" })
//	@Test(priority = 22, enabled = true, description = "FundsTransfer-OtherBank-FAST-ONEAPP-16725")
//	@Description(value = "Execution of this testcase:: Verifies the Fund Transfer Other Bank Immediate Transfer Date.")
//	@Author(name = "Divya Devi")
//	public void FundsTransfer_OtherBank_FAST_ONEAPP(String userName, String password) throws Exception {
//		try {
//			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
//			dbspage.logInApplication(userName, password, "DBS");
//			dbspage.FundsTransfer_OtherBank_FAST("DBS");
//		} catch (HandleException e) {
	//dbspage1.verifyDigibankAlert();
//			Asserts.assertFail(e.getCode()+"--> "+ e.getMessage()+ e.getCause());
//		}
//		catch (Exception e) {
//			Asserts.assertFail( "Unable to execute Fund Transfer Other bank Fast One App "+e.getMessage());
//		}
//	}
//	
	@Parameters({ "userName", "password" })
	@Test(priority = 23, enabled = true, description = "FundsTransfer-PayCreditCard-ONEAPP-14024")
	@Description(value = "Execution of this testcase:: Verifies the Fund Transfer Pay Credit Card.")
	@Author(name = "Divya Devi")
	public void FundsTransfer_PayCreditCard(String userName, String password) throws Exception {
		try {
			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
			dbspage1 = dbspage;
			dbspage1.logInApplication(userName, password, "DBS");
			dbspage1.FundsTransfer_PayCreditCard();
		} catch (HandleException e) {
			dbspage1.verifyDigibankAlert();
			Asserts.assertFail(e.getCode()+"--> "+ e.getMessage()+ e.getCause());
		}
		catch (Exception e) {
			dbspage1.verifyDigibankAlert();
			Asserts.assertFail( "Unable to execute Fund Transfer Pay Credit Card "+e.getMessage());
		}
	}
	
	
	@Parameters({ "userName", "password" })
	@Test(priority = 24, enabled = true, description = "FundsTransfer-BillPayment-ONEAPP-14040")
	@Description(value = "Execution of this testcase:: Verifies the Fund Transfer Bill Payment.")
	@Author(name = "Divya Devi")
	public void FundsTransfer_BillPayment(String userName, String password) throws Exception {
		try {
			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
			dbspage1 = dbspage;
			dbspage1.logInApplication(userName, password, "DBS");
			dbspage1.FundsTransfer_BillPayment();
		} catch (HandleException e) {
			dbspage1.verifyDigibankAlert();
			Asserts.assertFail(e.getCode()+"--> "+ e.getMessage()+ e.getCause());
		}
		catch (Exception e) {
			dbspage1.verifyDigibankAlert();
			Asserts.assertFail( "Unable to execute Fund Transfer Bill Payment "+e.getMessage());
		}
	}
	
	/******************End Test Script For DBS App************************************/
/******************Start Test Script For POSB App************************************/
//	@Parameters({ "userName", "password" })
//	@Test(priority = 25, enabled = true, description = "TopUp-PayLah-ONEAPP-13915")
//	@Description(value = "Execution of this testcase:: Verify the Topup Paylah in Applications")
//	@Author(name = "Shafqat Ali")
//	public void Topup_Paylah_POSB(String userName, String password) throws Exception {
//		try {
//			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
//			dbspage.logInApplication(userName, password, "POSB");
//			dbspage.TopupPaylah();
//		} catch (HandleException e) {
//			Asserts.assertFail(e.getCode()+"--> "+ e.getMessage()+ e.getCause());
//		}
//		catch (Exception e) {
//			Asserts.assertFail( "Unable to execute Topup Paylah_POSB "+e.getMessage());
//		}
//	}
//	
//	@Parameters({"userName", "password" })
//	@Test(priority=26, enabled=true, description = "TransactionHistory-ONEAPP-14312")
//	@Description(value = "Execution of this testcase:: Verifies Transaction History")
//	@Author(name = "shafqat")
//	public void TransactionHistory_POSB(String userName,String password) throws Exception {
//		try {
//			 DBSAndroidPage dbspage = new DBSAndroidPage(driver);
//			 dbspage.logInApplication(userName, password,"POSB");
//			 dbspage.transactionHistoryVerify("POSB");
//			 dbspage.clickOnLogoutAndVerify(CommonTestData.LOGOUT.getEnumValue(), CommonTestData.RATE_MESSAGE.getEnumValue());
//		} catch (HandleException e) {
//			Asserts.assertFail(e.getCode()+"--> "+ e.getMessage()+ e.getCause());
//		}
//		catch (Exception e) {
//			Asserts.assertFail( "Unable to execute Transaction History_POSB "+e.getMessage());
//		}
//	}
//	
//	@Parameters({ "userName", "password" })
//	@Test(priority = 27, enabled = true, description = "ApplyDebitCard-ONEAPP-15861")
//	@Description(value = "Execution of this testcase:: Verifies the Apply Debit Card.")
//	@Author(name = "Divya Devi")
//	public void ApplyDebitCard_POSB(String userName, String password) throws Exception {
//		try {
//			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
//			dbspage.logInApplication(userName, password, "POSB");
//			dbspage.ApplyDebitCard();
//		} catch (HandleException e) {
//			Asserts.assertFail(e.getCode()+"--> "+ e.getMessage()+ e.getCause());
//		}
//		catch (Exception e) {
//			Asserts.assertFail( "Unable to execute Apply Debit Card_POSB "+e.getMessage());
//		}
//	}
	
	/******************END Test Script For POSB App************************************/
	/******************Start Test Script For iWEALTH App************************************/
//	@Parameters({ "userName", "password" })
//	@Test(priority = 28, enabled = true, description = "TopUp-PayLah-ONEAPP-13915")
//	@Description(value = "Execution of this testcase:: Verify the Topup Paylah in Applications")
//	@Author(name = "Shafqat Ali")
//	public void Topup_Paylah_iWEALTH(String userName, String password) throws Exception {
//		try {
//			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
//			dbspage.logInApplication(userName, password, "iWEALTH");
//			dbspage.TopupPaylah();
//		} catch (HandleException e) {
//			Asserts.assertFail(e.getCode()+"--> "+ e.getMessage()+ e.getCause());
//		}
//		catch (Exception e) {
//			Asserts.assertFail( "Unable to execute Topup Paylah iWEALTH "+e.getMessage());
//		}
//	}
//	
//	@Parameters({"userName", "password" })
//	@Test(priority=29, enabled=true, description = "TransactionHistory-ONEAPP-14312")
//	@Description(value = "Execution of this testcase:: Verifies Transaction History")
//	@Author(name = "shafqat")
//	public void TransactionHistory_iWEALTH(String userName,String password) throws Exception {
//		try {
//			 DBSAndroidPage dbspage = new DBSAndroidPage(driver);
//			 dbspage.logInApplication(userName, password, "iWEALTH");
//			 dbspage.transactionHistoryVerify("iWEALTH");
//			 dbspage.clickOnLogoutAndVerify(CommonTestData.LOGOUT.getEnumValue(), CommonTestData.RATE_MESSAGE.getEnumValue());
//		} catch (HandleException e) {
//			Asserts.assertFail(e.getCode()+"--> "+ e.getMessage()+ e.getCause());
//		}
//		catch (Exception e) {
//			Asserts.assertFail( "Unable to execute Transaction History_iWEALTH "+e.getMessage());
//		}
//	}
//	
//	@Parameters({ "userName", "password" })
//	@Test(priority = 30, enabled = true, description = "ApplyDebitCard-ONEAPP-15861")
//	@Description(value = "Execution of this testcase:: Verifies the Apply Debit Card.")
//	@Author(name = "Divya Devi")
//	public void ApplyDebitCard_iWEALTH(String userName, String password) throws Exception {
//		try {
//			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
//			dbspage.logInApplication(userName, password, "iWEALTH");
//			dbspage.ApplyDebitCard();
//		} catch (HandleException e) {
//			Asserts.assertFail(e.getCode()+"--> "+ e.getMessage()+ e.getCause());
//		}
//		catch (Exception e) {
//			Asserts.assertFail( "Unable to execute Apply Debit Card_iWEALTH "+e.getMessage());
//		}
//	}
	/******************END Test Script For iWEALTH App************************************/
	
}
