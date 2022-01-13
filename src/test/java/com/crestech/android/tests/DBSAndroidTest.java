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
import com.crestech.listeners.RetryAnalyzer;
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
	
	@Parameters({ "userName", "password", "app_Name" })
	@Test(priority = 1, enabled = true, description = "Pre-requisite Script", retryAnalyzer = RetryAnalyzer.class)
	@Description(value = "Execution of this testcase:: Handling Quit button and UAT server selection ")
	@Author(name = "Shafqat Ali")
	public void PreRequisiteScript(String userName, String password , String app_Name) throws Exception {
		try {
			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
			dbspage1=dbspage;
			dbspage1.preRequisiteScript(userName, password, app_Name , CommonTestData.N4_SERVER.getEnumValue());
		} catch (HandleException e) {
			dbspage1.verifyDigibankAlert();
			Asserts.assertFail(e.getCode()+"--> "+ e.getMessage());
		}
		catch (Exception e) {
			dbspage1.verifyDigibankAlert();
			Asserts.assertFail( "Unable to execute Pre-Requisite Script "+e.getMessage());
		}
	}

//	@Parameters({ "userName", "password", "app_Name" })
//	@Test(priority = 2, enabled = true, description = "AccountDetails-CASA-ONEAPP-14400", dependsOnMethods={"PreRequisiteScript"})
//	@Description(value = "Execution of this testcase:: Verify the account detail on dashboard page. ")
//	@Author(name = "Shafqat Ali")
//	public void AccountDetails_CASA(String userName, String password , String app_Name) throws Exception {
//		try {
//			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
//			dbspage1=dbspage;
//			dbspage1.LogInApplication(userName, password);
//			dbspage1.verifyAccountDetailsOnHomePage(app_Name);
//		} catch (HandleException e) {
//			dbspage1.verifyDigibankAlert();
//			Asserts.assertFail(e.getCode()+"--> "+ e.getMessage());
//		}
//		catch (Exception e) {
//			dbspage1.verifyDigibankAlert();
//			Asserts.assertFail( "Unable to execute Account Details CASA "+e.getMessage());
//		}
//	}
//	
//	@Parameters({ "userName", "password", "app_Name" })
//	@Test(priority = 3, enabled = true, description = "Remittance-Corridor-ONEAPP-13407", dependsOnMethods={"PreRequisiteScript"})
//	@Description(value = "Execution of this testcase:: Verifies the Remittance Corridor")
//	@Author(name = "Divya Devi")
//	public void Remittance_Corridor(String userName, String password , String app_Name) throws Exception {
//		try {
//			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
//			dbspage1=dbspage;
//			dbspage1.LogInApplication(userName, password);
//			dbspage1.VerifyRemittanceCorridor(app_Name);
//		} catch (HandleException e) {
//			dbspage1.verifyDigibankAlert();
//			Asserts.assertFail(e.getCode()+"--> "+ e.getMessage());
//		}
//		catch (Exception e) {
//			dbspage1.verifyDigibankAlert();
//			Asserts.assertFail( "Unable to execute Remittance Corridor "+e.getMessage());
//		}
//	}
//
//	@Parameters({ "userName", "password", "app_Name" })
//	@Test(priority = 4, enabled = true, description = "Remittance-eOTT-ONEAPP-14008", dependsOnMethods={"PreRequisiteScript"})
//	@Description(value = "Execution of this testcase:: Verifies the Remittance eOTT.")
//	@Author(name = "Divya Devi")
//	public void Remittance_eOTT(String userName, String password , String app_Name) throws Exception {
//		try {
//			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
//			dbspage1=dbspage;
//			dbspage1.LogInApplication(userName, password);
//			dbspage1.VerifyRemittanceEOTT(app_Name);
//		} catch (HandleException e) {
//			dbspage1.verifyDigibankAlert();
//			Asserts.assertFail(e.getCode()+"--> "+ e.getMessage());
//		}
//		catch (Exception e) {
//			dbspage1.verifyDigibankAlert();
//			Asserts.assertFail( "Unable to execute Remittance EOTT "+e.getMessage());
//		}
//	}
//
//	@Parameters({ "userName", "password", "app_Name" })
//	@Test(priority = 5, enabled = true, description = "Payee-Add-DBSorPOSB-ONEAPP-14675", dependsOnMethods={"PreRequisiteScript"})
//	@Description(value = "Execution of this testcase:: Verifies the Payee add DSB or POSB.")
//	@Author(name = "Divya Devi")
//	public void Payee_Add_ToOwnAccount(String userName, String password , String app_Name) throws Exception {
//		try {
//			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
//			dbspage1=dbspage;
//			dbspage1.LogInApplication(userName, password);
//			dbspage1.DeletePayeeDBSPOSB(CommonTestData.PAYEEADD_DBSPOSB_RECIPIENT_NAME.getEnumValue());
//			dbspage1.VerifyAddPayeeDBSorPOSB();
//		} catch (HandleException e) {
//			dbspage1.verifyDigibankAlert();
//			Asserts.assertFail(e.getCode()+"--> "+ e.getMessage());
//		}
//		catch (Exception e) {
//			dbspage1.verifyDigibankAlert();
//			Asserts.assertFail( "Unable to execute Payee Add To DBS/POSB "+e.getMessage());
//		}
//	}
//
//	@Parameters({ "userName", "password", "app_Name" })
//	@Test(priority = 6, enabled = true, description = "TopUp-PayLah-ONEAPP-13915", dependsOnMethods={"PreRequisiteScript"})
//	@Description(value = "Execution of this testcase:: Verify the Topup Paylah in Applications")
//	@Author(name = "Shafqat Ali")
//	public void Topup_Paylah(String userName, String password , String app_Name) throws Exception {
//		try {
//			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
//			dbspage1=dbspage;
//			dbspage1.LogInApplication(userName, password);
//			dbspage1.VerifyTopupPaylah();
//		} catch (HandleException e) {
//			dbspage1.verifyDigibankAlert();
//			Asserts.assertFail(e.getCode()+"--> "+ e.getMessage());
//		}
//		catch (Exception e) {
//			dbspage1.verifyDigibankAlert();
//			Asserts.assertFail( "Unable to execute TopUp Paylah "+e.getMessage());
//		}
//	}
//		
//	@Parameters({ "userName", "password", "app_Name" })
//	@Test(priority = 7, enabled = true, description = "Payee-Add-Remittance-ONEAPP-13679", dependsOnMethods={"PreRequisiteScript"})
//	@Description(value = "Execution of this testcase:: Verify the Add Remittance payee")
//	@Author(name = "Shafqat Ali")
//	public void Payee_Add_Remittance(String userName, String password , String app_Name) throws Exception {
//		try {
//			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
//			dbspage1 = dbspage;
//			dbspage1.LogInApplication(userName, password);
//			dbspage1.DeletePayeeRemittance(CommonTestData.FULL_NAME.getEnumValue());
//			dbspage1.VerifyPayeeAddRemittance();
//		} catch (HandleException e) {
//			dbspage1.verifyDigibankAlert();
//			Asserts.assertFail(e.getCode()+"--> "+ e.getMessage());
//		}
//		catch (Exception e) {
//			dbspage1.verifyDigibankAlert();
//			Asserts.assertFail( "Unable to execute Payee Add To Remittance "+e.getMessage());
//		}
//	}
//
//	@Parameters({ "userName", "password", "app_Name" })
//	@Test(priority = 8, enabled = true, description = "ApplyDebitCard-ONEAPP-15861", dependsOnMethods={"PreRequisiteScript"})
//	@Description(value = "Execution of this testcase:: Verifies the Apply Debit Card.")
//	@Author(name = "Divya Devi")
//	public void ApplyDebitCard(String userName, String password , String app_Name) throws Exception {
//		try {
//			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
//			dbspage1 = dbspage;
//			dbspage1.LogInApplication(userName, password);
//			dbspage1.ApplyDebitCard(app_Name);
//		} catch (HandleException e) {
//			dbspage1.verifyDigibankAlert();
//			Asserts.assertFail(e.getCode()+"--> "+ e.getMessage());
//		}
//		catch (Exception e) {
//			dbspage1.verifyDigibankAlert();
//			Asserts.assertFail( "Unable to execute Apply Debit card"+e.getMessage());
//		}
//	}
//
//	@Parameters({ "userName", "password", "app_Name" })
//	@Test(priority = 9, enabled = true, description = "Payee-Add-Local-OtherBank-ONEAPP-15677", dependsOnMethods={"PreRequisiteScript"})
//	@Description(value = "Execution of this testcase:: Verifies the Payee Add Local Other Bank.")
//	@Author(name = "Divya Devi")
//	public void Payee_Add_Local_OtherBank(String userName, String password , String app_Name) throws Exception {
//		try {
//			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
//			dbspage1 = dbspage;
//			dbspage1.LogInApplication(userName, password);
//			dbspage1.DeletePayeeLocalToOtherBank(CommonTestData.LOCAL_RECIPIENT_NAME.getEnumValue());
//			dbspage1.PayeeAddLocalOtherBank();
//		} catch (HandleException e) {
//			dbspage1.verifyDigibankAlert();
//			Asserts.assertFail(e.getCode()+"--> "+ e.getMessage());
//		}
//		catch (Exception e) {
//			dbspage1.verifyDigibankAlert();
//			Asserts.assertFail( "Unable to execute Payee Add Local OtherBank "+e.getMessage());
//		}
//	}
//
//	@Parameters({ "userName", "password", "app_Name" })
//	@Test(priority = 10, enabled = true, description = "Payee-Add-BillPayment-ONEAPP-15938", dependsOnMethods={"PreRequisiteScript"})
//	@Description(value = "Execution of this testcase:: Verifies the Payee Add Bill Payment.")
//	@Author(name = "Divya Devi")
//	public void Payee_Add_BillPayment(String userName, String password , String app_Name) throws Exception {
//		try {
//			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
//			dbspage1 = dbspage;
//			dbspage1.LogInApplication(userName, password);
//			dbspage1.DeletePayeeToBillPayment(CommonTestData.PAYEEADD_BILLPAYMENT_ACCOUNTNAME.getEnumValue());
//			dbspage1.PayeeAddBillPayment();
//		} catch (HandleException e) {
//			dbspage1.verifyDigibankAlert();
//			Asserts.assertFail(e.getCode()+"--> "+ e.getMessage());
//		}
//		catch (Exception e) {
//			dbspage1.verifyDigibankAlert();
//			Asserts.assertFail( "Unable to execute Payee Add Bill Payment "+e.getMessage());
//		}
//	}
//	
//
//	@Parameters({ "userName", "password", "app_Name" })
//	@Test(priority = 11, enabled = true, description = "OpenAccount-ONEAPP-14028" , dependsOnMethods={"PreRequisiteScript"})
//	@Description(value = "Execution of this testcase:: Verifies the Open Account.")
//	@Author(name = "Divya Devi")
//	public void OpenAccount(String userName, String password , String app_Name) throws Exception {
//		try {
//			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
//			dbspage1=dbspage;
//			dbspage1.LogInApplication(userName, password);
//			dbspage1.OpenAccount(app_Name);
//		} catch (HandleException e) {
//			dbspage1.verifyDigibankAlert();
//			Asserts.assertFail(e.getCode()+"--> "+ e.getMessage());
//		}
//		catch (Exception e) {
//			dbspage1.verifyDigibankAlert();
//			Asserts.assertFail( "Unable to execute Open Account "+e.getMessage());
//		}
//	}
//
//	@Parameters({ "userName", "password", "app_Name" })
//	@Test(priority = 12, enabled = true, description = "ChangeLocalFundsTransferLimit-Increase-ONEAPP-7847", dependsOnMethods={"PreRequisiteScript"})
//	@Description(value = "Execution of this testcase:: Successful Change Limit for Transfers to Other Banks Accounts to Increase the Current limit.")
//	@Author(name = "Shafqat Ali")
//	public void ChangeLocalFundsTransferLimit(String userName, String password , String app_Name) throws Exception {
//		try {
//			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
//			dbspage1=dbspage;
//			dbspage1.LogInApplication(userName, password);
//			dbspage1.ChangeLocalFundsTransferLimit();
//		} catch (HandleException e) {
//			dbspage1.verifyDigibankAlert();
//			Asserts.assertFail(e.getCode()+"--> "+ e.getMessage());
//		}
//		catch (Exception e) {
//			dbspage1.verifyDigibankAlert();
//			Asserts.assertFail( "Unable to execute Change Local Funds Transfer Limit "+e.getMessage());
//		}
//	}
//	
//	@Parameters({ "userName", "password", "app_Name" })
//	@Test(priority = 13, enabled = true, description = "Logout-ONEAPP-9392", dependsOnMethods={"PreRequisiteScript"})
//	@Description(value = "Execution of this testcase:: Verify the Logout functionality for Applications.")
//	@Author(name = "Shafqat Ali")
//	public void Logout_ONEAPP(String userName, String password , String app_Name) throws Exception {
//		try {
//			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
//			dbspage1=dbspage;
//			dbspage1.LogInApplication(userName, password);
//			dbspage1.VerifyLogout(CommonTestData.RATE_MESSAGE.getEnumValue());
//		} catch (HandleException e) {
//			dbspage1.verifyDigibankAlert();
//			Asserts.assertFail(e.getCode()+"--> "+ e.getMessage());
//		}
//		catch (Exception e) {
//			dbspage1.verifyDigibankAlert();
//			Asserts.assertFail( "Unable to execute Log Out "+e.getMessage());
//		}
//	}
//
//	@Parameters({ "userName", "password", "app_Name" })
//	@Test(priority = 14, enabled = true, description = "FundsTransfer-OwnAccount-ONEAPP-16730", dependsOnMethods={"PreRequisiteScript"})
//	@Description(value = "Execution of this testcase:: Verifies the Fund Transfer Own Account.")
//	@Author(name = "Divya Devi")
//	public void FundsTransferOwnAccount(String userName, String password , String app_Name) throws Exception {
//		try {
//			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
//			dbspage1 = dbspage;
//			dbspage1.LogInApplication(userName, password);
//			dbspage1.VerifyFundTransfer_OwnAccount();
//		} catch (HandleException e) {
//			dbspage1.verifyDigibankAlert();
//			Asserts.assertFail(e.getCode()+"--> "+ e.getMessage());
//		}
//		catch (Exception e) {
//			dbspage1.verifyDigibankAlert();
//			Asserts.assertFail( "Unable to execute Fund Transfer Own Account "+e.getMessage());
//		}
//	}
//
//	@Parameters({ "userName", "password", "app_Name" })
//	@Test(priority = 15, enabled = true, description = "FundsTransfer-OtherBank-NonFAST-Future-THANOS-5244", dependsOnMethods={"PreRequisiteScript"})
//	@Description(value = "Execution of this testcase:: Verifies the Fund Transfer Other Bank Non Fast Future Transfer Date.")
//	@Author(name = "Divya Devi")
//	public void FundsTransfer_OtherBank_NonFAST_Future(String userName, String password , String app_Name) throws Exception {
//		try {
//			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
//			dbspage1 = dbspage;
//			dbspage1.LogInApplication(userName, password);
//			dbspage1.FundsTransfer_OtherBank_NonFASTFuture(app_Name);
//		} catch (HandleException e) {
//			dbspage1.verifyDigibankAlert();
//			Asserts.assertFail(e.getCode()+"--> "+ e.getMessage());
//		}
//		catch (Exception e) {
//			dbspage1.verifyDigibankAlert();
//			Asserts.assertFail( "Unable to execute Fund Transfer Other Bank Non Fast Future "+e.getMessage());
//		}
//	}
//
//	@Parameters({ "userName", "password", "app_Name" })
//	@Test(priority = 16, enabled = true, description = "FundsTransfer-OtherBank-NonFAST-ONEAPP-16728", dependsOnMethods={"PreRequisiteScript"})
//	@Description(value = "Execution of this testcase:: Verifies the Fund Transfer Other Bank Non Immediate Transfer Date.")
//	@Author(name = "Divya Devi")
//	public void FundsTransfer_OtherBank_NonFAST(String userName, String password , String app_Name) throws Exception {
//		try {
//			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
//			dbspage1 = dbspage;
//			dbspage1.LogInApplication(userName, password);
//			dbspage1.FundsTransfer_OtherBank_NonFAST(app_Name);
//		} catch (HandleException e) {
//			dbspage1.verifyDigibankAlert();
//			Asserts.assertFail(e.getCode()+"--> "+ e.getMessage());
//		}
//		catch (Exception e) {
//			dbspage1.verifyDigibankAlert();
//			Asserts.assertFail( "Unable to execute Fund Transfer Other Bank Non Fast "+e.getMessage());
//		}
//	}
//	
//
//	@Parameters({"userName", "password", "app_Name" })
//	@Test(priority=17, enabled=true, description = "FundsTransfer-OtherPOSBDBS-ONEAPP-16723", dependsOnMethods={"PreRequisiteScript"})
//	@Description(value = "Execution of this testcase:: Verifies FundTransfer Other DBS/POSB")
//	@Author(name = "shafqat")
//	public void FundTransferOtherDBSPOSB(String userName,String password, String app_Name) throws Exception {
//		try {
//			 DBSAndroidPage dbspage = new DBSAndroidPage(driver);
//			 dbspage1 = dbspage;
//			 dbspage1.LogInApplication(userName, password);
//			 dbspage1.FundTransferDBSPOSB(app_Name);
//		} catch (HandleException e) {
//			dbspage1.verifyDigibankAlert();
//			Asserts.assertFail(e.getCode()+"--> "+ e.getMessage());
//		}
//		catch (Exception e) {
//			dbspage1.verifyDigibankAlert();
//			Asserts.assertFail( "Unable to execute Fund Transfer Other DBS/POSB "+e.getMessage());
//		}
//	}
//	
//	@Parameters({"userName", "password", "app_Name" })
//	@Test(priority=18, enabled=true, description = "TransactionHistory-ONEAPP-14312", dependsOnMethods={"PreRequisiteScript"})
//	@Description(value = "Execution of this testcase:: Verifies Transaction History")
//	@Author(name = "shafqat")
//	public void TransactionHistory_DBS(String userName,String password, String app_Name) throws Exception {
//		try {
//			 DBSAndroidPage dbspage = new DBSAndroidPage(driver);
//			 dbspage1 = dbspage;
//			 dbspage1.LogInApplication(userName, password);
//			 dbspage1.verifyTransactionHistory(app_Name);
//			 dbspage1.VerifyLogout(CommonTestData.RATE_MESSAGE.getEnumValue());
//		} catch (HandleException e) {
//			dbspage1.verifyDigibankAlert();
//			Asserts.assertFail(e.getCode()+"--> "+ e.getMessage());
//		}
//		catch (Exception e) {
//			dbspage1.verifyDigibankAlert();
//			Asserts.assertFail( "Unable to execute Transaction History "+e.getMessage());
//		}
//	}
//	
//	@Parameters({"userName", "password","app_Name" })
//	@Test(priority=19, enabled=true, description = "CreditCardTempLimitIncrease-ONEAPP-16669", dependsOnMethods={"PreRequisiteScript"})
//	@Description(value = "Execution of this testcase:: Verifies Credit Card Temperory Limit Increase")
//	@Author(name = "shafqat")
//	public void CreditCardTempLimitIncrease(String userName,String password, String app_Name) throws Exception {
//		try {
//			 DBSAndroidPage dbspage = new DBSAndroidPage(driver);
//			 dbspage1 = dbspage;
//			 dbspage1.LogInApplication(userName, password);
//			 dbspage1.CreditCardTempLimitIncrease();
//		} catch (HandleException e) {
//			 dbspage1.verifyDigibankAlert();
//			Asserts.assertFail(e.getCode()+"--> "+ e.getMessage());
//		}
//		catch (Exception e) {
//			 dbspage1.verifyDigibankAlert();
//			Asserts.assertFail( "Unable to execute Credit Card Temp Limit Increase "+e.getMessage());
//		}
//	}
//
//	@Parameters({ "userName", "password", "app_Name" })
//	@Test(priority = 20, enabled = true, description = "UpdatePersonalParticular-ONEAPP-13921", dependsOnMethods={"PreRequisiteScript"})
//	@Description(value = "Execution of this testcase:: Verifies the Update Personal Particular Details.")
//	@Author(name = "Divya Devi")
//	public void UpdatePersonalParticular(String userName, String password , String app_Name) throws Exception {
//			try {
//				DBSAndroidPage dbspage = new DBSAndroidPage(driver);
//				dbspage1 = dbspage;
//				dbspage1.LogInApplication(userName, password);
//				dbspage1.UpdatePersonalDetails(app_Name);
//			} catch (HandleException e) {
//				dbspage1.verifyDigibankAlert();
//				Asserts.assertFail(e.getCode()+"--> "+ e.getMessage());
//			}
//			catch (Exception e) {
//				dbspage1.verifyDigibankAlert();
//				Asserts.assertFail( "Unable to execute update Personal Particular "+e.getMessage());
//			}
//	}
//	
//	@Parameters({ "userName", "password", "app_Name" })
//	@Test(priority = 21, enabled = true, description = "FundsTransfer-OtherBank-FAST-FUTURE-THANOS-5245", dependsOnMethods={"PreRequisiteScript"})
//	@Description(value = "Execution of this testcase:: Verifies the Fund Transfer Other Bank Fast Future Transfer Date.")
//	@Author(name = "Divya Devi")
//	public void FundsTransfer_OtherBank_FAST_FUTURE(String userName, String password , String app_Name) throws Exception {
//		try {
//			
//			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
//			dbspage1 = dbspage;
//			dbspage1.LogInApplication(userName, password);
//			dbspage1.FundsTransfer_OtherBank_FASTFuture(app_Name);
//		} catch (HandleException e) {
//			dbspage1.verifyDigibankAlert();
//			Asserts.assertFail(e.getCode()+"--> "+ e.getMessage());
//		}
//		catch (Exception e) {
//			dbspage1.verifyDigibankAlert();
//			Asserts.assertFail( "Unable to execute Fund Transfer Other bank Fast Future "+e.getMessage());
//		}
//	}
//
//	@Parameters({ "userName", "password", "app_Name" })
//	@Test(priority = 22, enabled = true, description = "FundsTransfer-OtherBank-FAST-ONEAPP-16725", dependsOnMethods={"PreRequisiteScript"})
//	@Description(value = "Execution of this testcase:: Verifies the Fund Transfer Other Bank Immediate Transfer Date.")
//	@Author(name = "Divya Devi")
//	public void FundsTransfer_OtherBank_FAST_ONEAPP(String userName, String password , String app_Name) throws Exception {
//		try {
//			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
//			dbspage1 = dbspage;
//			dbspage1.LogInApplication(userName, password);
//			dbspage1.FundsTransfer_OtherBank_FAST(app_Name);
//		} catch (HandleException e) {
//	            dbspage1.verifyDigibankAlert();
//			Asserts.assertFail(e.getCode()+"--> "+ e.getMessage());
//		}
//		catch (Exception e) {
//			 dbspage1.verifyDigibankAlert();
//			Asserts.assertFail( "Unable to execute Fund Transfer Other bank Fast One App "+e.getMessage());
//		}
//	}
//	
//	@Parameters({ "userName", "password", "app_Name" })
//	@Test(priority = 23, enabled = true, description = "FundsTransfer-PayCreditCard-ONEAPP-14024", dependsOnMethods={"PreRequisiteScript"})
//	@Description(value = "Execution of this testcase:: Verifies the Fund Transfer Pay Credit Card.")
//	@Author(name = "Divya Devi")
//	public void FundsTransfer_PayCreditCard(String userName, String password , String app_Name) throws Exception {
//		try {
//			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
//			dbspage1 = dbspage;
//			dbspage1.LogInApplication(userName, password);
//			dbspage1.FundsTransfer_PayCreditCard();
//		} catch (HandleException e) {
//			dbspage1.verifyDigibankAlert();
//			Asserts.assertFail(e.getCode()+"--> "+ e.getMessage());
//		}
//		catch (Exception e) {
//			dbspage1.verifyDigibankAlert();
//			Asserts.assertFail( "Unable to execute Fund Transfer Pay Credit Card "+e.getMessage());
//		}
//	}
//	
//	
//	@Parameters({ "userName", "password", "app_Name" })
//	@Test(priority = 24, enabled = true, description = "FundsTransfer-BillPayment-ONEAPP-14040", dependsOnMethods={"PreRequisiteScript"})
//	@Description(value = "Execution of this testcase:: Verifies the Fund Transfer Bill Payment.")
//	@Author(name = "Divya Devi")
//	public void FundsTransfer_BillPayment(String userName, String password , String app_Name) throws Exception {
//		try {
//			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
//			dbspage1 = dbspage;
//			dbspage1.LogInApplication(userName, password);
//			dbspage1.FundsTransfer_BillPayment();
//		} catch (HandleException e) {
//			dbspage1.verifyDigibankAlert();
//			Asserts.assertFail(e.getCode()+"--> "+ e.getMessage());
//		}
//		catch (Exception e) {
//			dbspage1.verifyDigibankAlert();
//			Asserts.assertFail( "Unable to execute Fund Transfer Bill Payment "+e.getMessage());
//		}
//	}
//	
//	@Parameters({ "userName", "password", "app_Name" })
//	@Test(priority = 25, enabled = true, description = "PayNow-NRIC-ONEAPP-13966", dependsOnMethods={"PreRequisiteScript"})
//	@Description(value = "Execution of this testcase:: Verifies the Pay Now NRIC.")
//	@Author(name = "Divya Devi")
//	public void PayNow_NRIC_ONEAPP(String userName, String password , String app_Name) throws Exception {
//		try {
//			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
//			dbspage1 = dbspage;
//			dbspage1.LogInApplication(userName, password);
//			dbspage1.PayNowNRIC();
//		} catch (HandleException e) {
//			dbspage1.verifyDigibankAlert();
//			Asserts.assertFail(e.getCode()+"--> "+ e.getMessage());
//		}
//		catch (Exception e) {
//			dbspage1.verifyDigibankAlert();
//			Asserts.assertFail( "Unable to execute Pay Now NRIC ONEAPP "+e.getMessage());
//		}
//	}
//	
//	@Parameters({ "userName", "password", "app_Name" })
//	@Test(priority = 26, enabled = true, description = "PayNow-UEN-ONEAPP-13969", dependsOnMethods={"PreRequisiteScript"})
//	@Description(value = "Execution of this testcase:: Verifies the Pay Now UEN.")
//	@Author(name = "Divya Devi")
//	public void PayNow_UEN_ONEAPP(String userName, String password , String app_Name) throws Exception {
//		try {
//			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
//			dbspage1 = dbspage;
//			dbspage1.LogInApplication(userName, password);
//			dbspage1.PayNowUEN();
//		} catch (HandleException e) {
//			dbspage1.verifyDigibankAlert();
//			Asserts.assertFail(e.getCode()+"--> "+ e.getMessage());
//		}
//		catch (Exception e) {
//			dbspage1.verifyDigibankAlert();
//			Asserts.assertFail( "Unable to execute Pay Now UEN ONEAPP "+e.getMessage());
//		}
//	}
//	
//	@Parameters({ "userName", "password", "app_Name" })
//	@Test(priority = 27, enabled = true, description = "PayNow-VPA-ONEAPP-13991", dependsOnMethods={"PreRequisiteScript"})
//	@Description(value = "Execution of this testcase:: Verifies the Pay Now VPA.")
//	@Author(name = "Divya Devi")
//	public void PayNow_VPA_ONEAPP(String userName, String password , String app_Name) throws Exception {
//		try {
//			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
//			dbspage1 = dbspage;
//			dbspage1.LogInApplication(userName, password);
//			dbspage1.PayNowVPA();
//		} catch (HandleException e) {
//			dbspage1.verifyDigibankAlert();
//			Asserts.assertFail(e.getCode()+"--> "+ e.getMessage());
//		}
//		catch (Exception e) {
//			dbspage1.verifyDigibankAlert();
//			Asserts.assertFail( "Unable to execute Pay Now VPA ONEAPP "+e.getMessage());
//		}
//	}
//	
//
//	@Parameters({ "userName", "password", "app_Name" })
//	@Test(priority = 28, enabled = true, description = "PeekBalance-MCA-ONEAPP-13982", dependsOnMethods={"PreRequisiteScript"})
//	@Description(value = "Execution of this testcase:: Verifies the Peek Balance.")
//	@Author(name = "Divya Devi")
//	public void PeekBalance_MCA(String userName, String password , String app_Name) throws Exception {
//		try {
//			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
//			dbspage1 = dbspage;
//			dbspage1.LogInApplication(userName, password);
//			dbspage1.VerifyPeekBalance(app_Name);
//		}  catch (HandleException e) {
//			dbspage1.verifyDigibankAlert();
//			Asserts.assertFail(e.getCode()+"--> "+ e.getMessage());
//		}
//		catch (Exception e) {
//			dbspage1.verifyDigibankAlert();
//			Asserts.assertFail( "Unable to execute Peek Balance "+e.getMessage());
//		}
//	}
	
	/******************End Test Script For DBS App************************************/
}
