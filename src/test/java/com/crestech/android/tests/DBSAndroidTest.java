package com.crestech.android.tests;

import java.util.logging.Logger;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.crestech.annotation.values.Author;
import com.crestech.base.UserBaseTest;
import com.crestech.common.utilities.Asserts;
import com.crestech.config.ContextManager;
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
	@Parameters({"userName", "password" })
	@Test(description = "Sample test for Android")
	@Description(value = "TestCases For Pcloudy Sample Project")
	@Author(name = "Shibu prasad panda")
	public void SampleAndroidTest(String userName,String password) throws InterruptedException {
		try {
			//logger.info("To Check Navigate to Log in page Functionality-------> ");
			//ContextManager.getExtentReportForPrecondition().info("Navigate to Home page");
		DBSAndroidPage dbspage = new DBSAndroidPage(driver);
		//dbspage.clickOnHomeButton();
		//dbspage.sendDataInUsername(userName);
		//dbspage.sendDataInPAssword(password);
		} catch (Exception e1) {
			e1.printStackTrace();
			System.out.println("This is checking for GIT branch");
		}
	}
}
