package com.crestech.android.tests;

import java.util.logging.Logger;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.crestech.annotation.values.Author;
import com.crestech.appium.utils.CommonAppiumTest;
import com.crestech.base.UserBaseTest;
import com.crestech.common.utilities.Asserts;
import com.crestech.common.utilities.CommonTestData;
import com.crestech.listeners.TestListener;
import com.crestech.pages.DBSAndroidPage;
import io.qameta.allure.Description;

@Listeners(TestListener.class)
public class DBSAndroidTest extends UserBaseTest {

	public DBSAndroidTest() throws Exception {
		super();
	}
	

	Logger logger = Logger.getLogger(DBSAndroidTest.class.getName());
	Asserts Assert = new Asserts();

	@Parameters({ "userName", "password" })
	@Test(priority = 1, enabled = true, description = "AccountDetails-CASA-ONEAPP-14400")
	@Description(value = "Execution of this testcase:: Verify the account detail on dashboard page. ")
	@Author(name = "Shafqat Ali")
	public void accountDetails_CASA(String userName, String password) throws Exception {
		try {
			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
			dbspage.logInApplication(userName, password, "DBS");

		} catch (Exception e) {
			throw new Exception(CommonAppiumTest.getExceptionMessage(e));
		}
	}
	
	@Parameters({ "userName", "password" })
	@Test(priority = 2, enabled = true, description = "Logout-ONEAPP-9392")
	@Description(value = "Execution of this testcase:: Verify the Logout functionality for Applications.")
	@Author(name = "Shafqat Ali")
	public void Logout_ONEAPP(String userName, String password) throws Exception {
		try {
			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
			dbspage.logInApplication(userName, password, "DBS");
			dbspage.clickOnLogoutAndVerify(CommonTestData.LOGOUT.getEnumValue(),
					CommonTestData.RATE_MESSAGE.getEnumValue());
		} catch (Exception e) {
			throw new Exception(CommonAppiumTest.getExceptionMessage(e));
		}
	}
	@Parameters({ "userName", "password" })
	@Test(priority = 3, enabled = true, description = "Remittance-Corridor-ONEAPP-13407")
	@Description(value = "Execution of this testcase:: Verifies the Remittance Corridor")
	@Author(name = "Divya Devi")
	public void Remittance_Corridor(String userName, String password) throws Exception {
		try {
			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
			dbspage.logInApplication(userName, password, "DBS");
			dbspage.VerifyRemittanceCorridor();
		} catch (Exception e) {
			throw new Exception(CommonAppiumTest.getExceptionMessage(e));
		}
	}

	@Parameters({ "userName", "password" })
	@Test(priority = 4, enabled = true, description = "Remittance-eOTT-ONEAPP-14008")
	@Description(value = "Execution of this testcase:: Verifies the Remittance eOTT.")
	@Author(name = "Divya Devi")
	public void Remittance_eOTT(String userName, String password) throws Exception {
		try {
			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
			dbspage.logInApplication(userName, password, "DBS");
			dbspage.VerifyRemittanceEOTT();
		} catch (Exception e) {
			throw new Exception(CommonAppiumTest.getExceptionMessage(e));
		}
	}

	@Parameters({ "userName", "password" })
	@Test(priority = 5, enabled = true, description = "Payee-Add-DBSorPOSB-ONEAPP-14675")
	@Description(value = "Execution of this testcase:: Verifies the Payee add DSB or POSB.")
	@Author(name = "Divya Devi")
	public void Payee_Add_DBSorPOSB(String userName, String password) throws Exception {
		try {
			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
			dbspage.logInApplication(userName, password, "DBS");
			dbspage.VerifyAddPayeeDBSorPOSB();
		} catch (Exception e) {
			throw new Exception(CommonAppiumTest.getExceptionMessage(e));
		}
	}

	@Parameters({ "userName", "password" })
	@Test(priority = 6, enabled = true, description = "TopUp-PayLah-ONEAPP-13915")
	@Description(value = "Execution of this testcase:: Verify the Topup Paylah in Applications")
	@Author(name = "Shafqat Ali")
	public void Topup_Paylah(String userName, String password) throws Exception {
		try {
			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
			dbspage.logInApplication(userName, password, "DBS");
			dbspage.TopupPaylah();
		} catch (Exception e) {
			throw new Exception(CommonAppiumTest.getExceptionMessage(e));
		}
	}

	@Parameters({ "userName", "password" })
	@Test(priority = 7, enabled = true, description = "Payee-Add-Remittance-ONEAPP-13679")
	@Description(value = "Execution of this testcase:: Verify the Add Remittance payee")
	@Author(name = "Shafqat Ali")
	public void Payee_Add_Remittance(String userName, String password) throws Exception {
		try {
			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
			dbspage.logInApplication(userName, password, "DBS");
			dbspage.PayeeAddRemittance();
		} catch (Exception e) {
			throw new Exception(CommonAppiumTest.getExceptionMessage(e));
		}
	}

	@Parameters({ "userName", "password" })
	@Test(priority = 8, enabled = true, description = "ApplyDebitCard-ONEAPP-15861")
	@Description(value = "Execution of this testcase:: Verifies the Apply Debit Card.")
	@Author(name = "Divya Devi")
	public void ApplyDebitCard(String userName, String password) throws Exception {
		try {
			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
			dbspage.logInApplication(userName, password, "DBS");
			dbspage.ApplyDebitCard();
		} catch (Exception e) {
			throw new Exception(CommonAppiumTest.getExceptionMessage(e));
		}
	}

	@Parameters({ "userName", "password" })
	@Test(priority = 9, enabled = true, description = "Payee-Add-Local-OtherBank-ONEAPP-15677")
	@Description(value = "Execution of this testcase:: Verifies the Payee Add Local Other Bank.")
	@Author(name = "Divya Devi")
	public void Payee_Add_Local_OtherBank(String userName, String password) throws Exception {
		try {
			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
			dbspage.logInApplication(userName, password, "DBS");
			dbspage.PayeeAddLocalOtherBank();
		} catch (Exception e) {
			throw new Exception(CommonAppiumTest.getExceptionMessage(e));
		}
	}

	@Parameters({ "userName", "password" })
	@Test(priority = 10, enabled = true, description = "Payee-Add-BillPayment-ONEAPP-15938")
	@Description(value = "Execution of this testcase:: Verifies the Payee Add Bill Payment.")
	@Author(name = "Divya Devi")
	public void Payee_Add_BillPayment(String userName, String password) throws Exception {
		try {
			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
			dbspage.logInApplication(userName, password, "DBS");
			dbspage.PayeeAddBillPayment();
		} catch (Exception e) {
			throw new Exception(CommonAppiumTest.getExceptionMessage(e));
		}
	}
	

	@Parameters({ "userName", "password" })
	@Test(priority = 11, enabled = true, description = "OpenAccount-ONEAPP-14028")
	@Description(value = "Execution of this testcase:: Verifies the Open Account.")
	@Author(name = "Divya Devi")
	public void OpenAccount(String userName, String password) throws Exception {
		try {
			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
			dbspage.logInApplication(userName, password, "DBS");
			dbspage.OpenAccount();
		} catch (Exception e) {
			throw new Exception(CommonAppiumTest.getExceptionMessage(e));
		}
	}

	@Parameters({ "userName", "password" })
	@Test(priority = 12, enabled = true, description = "ChangeLocalFundsTransferLimit-Increase-ONEAPP-7847")
	@Description(value = "Execution of this testcase:: Successful Change Limit for Transfers to Other Banks Accounts to Increase the Current limit.")
	@Author(name = "Shafqat Ali")
	public void ChangeLocalFundsTransferLimit(String userName, String password) throws Exception {
		try {
			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
			dbspage.logInApplication(userName, password, "DBS");
			dbspage.ChangeLocalFundsTransferLimit();
		} catch (Exception e) {
			throw new Exception(CommonAppiumTest.getExceptionMessage(e));
		}
	}

	@Parameters({ "userName", "password" })
	@Test(priority = 13, enabled = true, description = "PeekBalance-MCA-ONEAPP-13982")
	@Description(value = "Execution of this testcase:: Verifies the Peek Balance.")
	@Author(name = "Divya Devi")
	public void PeekBalance_MCA(String userName, String password) throws Exception {
		try {
			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
			dbspage.logInApplication(userName, password, "DBS");
			dbspage.VerifyPeekBalance();
		} catch (Exception e) {
			throw new Exception(CommonAppiumTest.getExceptionMessage(e));
		}
	}

	@Parameters({ "userName", "password" })
	@Test(priority = 14, enabled = true, description = "FundsTransfer-OwnAccount-ONEAPP-16730")
	@Description(value = "Execution of this testcase:: Verifies the Fund Transfer Own Account.")
	@Author(name = "Divya Devi")
	public void FundsTransferOwnAccount(String userName, String password) throws Exception {
		try {
			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
			dbspage.logInApplication(userName, password, "DBS");
			dbspage.VerifyFundTransfer_OwnAccount();
		} catch (Exception e) {
			throw new Exception(CommonAppiumTest.getExceptionMessage(e));
		}
	}

	@Parameters({ "userName", "password" })
	@Test(priority = 15, enabled = true, description = "FundsTransfer-OtherBank-NonFAST-Future-THANOS-5244")
	@Description(value = "Execution of this testcase:: Verifies the Fund Transfer Other Bank Non Fast Future Transfer Date.")
	@Author(name = "Divya Devi")
	public void FundsTransfer_OtherBank_NonFAST_Future(String userName, String password) throws Exception {
		try {
			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
			dbspage.logInApplication(userName, password, "DBS");
			dbspage.FundsTransfer_OtherBank_NonFASTFuture("DBS");
		} catch (Exception e) {
			throw new Exception(CommonAppiumTest.getExceptionMessage(e));
		}
	}

	@Parameters({ "userName", "password" })
	@Test(priority = 16, enabled = true, description = "FundsTransfer-OtherBank-NonFAST-ONEAPP-16728")
	@Description(value = "Execution of this testcase:: Verifies the Fund Transfer Other Bank Non Immediate Transfer Date.")
	@Author(name = "Divya Devi")
	public void FundsTransfer_OtherBank_NonFAST(String userName, String password) throws Exception {
		try {
			DBSAndroidPage dbspage = new DBSAndroidPage(driver);
			dbspage.logInApplication(userName, password, "DBS");
			dbspage.FundsTransfer_OtherBank_NonFAST("DBS");
		} catch (Exception e) {
			throw new Exception(CommonAppiumTest.getExceptionMessage(e));
		}
	}

	@Parameters({"userName", "password" })
	@Test(priority=17, enabled=true, description = "FundsTransfer-OtherPOSBDBS-ONEAPP-16723")
	@Description(value = "Execution of this testcase:: Verifies FundTransfer Other DBS/POSB")
	@Author(name = "shafqat")
	public void FundTransferOtherDBSPOSB(String userName,String password) throws Exception {
		try {
			 DBSAndroidPage dbspage = new DBSAndroidPage(driver);
			 dbspage.logInApplication(userName, password, "DBS");
			 dbspage.FundTransferOtherBank();
             
		} catch (Exception e) {
			throw new Exception(CommonAppiumTest.getExceptionMessage(e)); 
		}
	}
	@Parameters({"userName", "password" })
	@Test(priority=18, enabled=true, description = "TransactionHistory-ONEAPP-14312")
	@Description(value = "Execution of this testcase:: Verifies Transaction History")
	@Author(name = "shafqat")
	public void transactionHistory(String userName,String password) throws Exception {
		try {
			 DBSAndroidPage dbspage = new DBSAndroidPage(driver);
			 dbspage.logInApplication(userName, password, "DBS");
			 dbspage.transactionHistoryVerify("DBS");
			 dbspage.clickOnLogoutAndVerify(CommonTestData.LOGOUT.getEnumValue(), CommonTestData.RATE_MESSAGE.getEnumValue());
		} catch (Exception e) {
			throw new Exception(CommonAppiumTest.getExceptionMessage(e)); 
		}
	}
}
