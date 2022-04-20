package com.crestech.android.tests;


import java.util.logging.Logger;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.crestech.base.UserBaseTest;
import com.crestech.common.utilities.WaitUtils;
import com.crestech.listeners.TestListener;
import com.crestech.pages.ChromeHomePage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

@Listeners(TestListener.class)
@Epic("Chrome Test")
@Feature("My first report")
public class chromeTest extends UserBaseTest {

	Logger logger = Logger.getLogger(chromeTest.class.getName());

	@Test(description = "Sample test for Android")
	//@Test(retryAnalyzer = RetryAnalyzer.class)
	@Description(value = "TestCases For Pcloudy Sample Project")
	@Parameters({"userName", "password" })
	public void SampleAndroidTest(String userName, String password) throws Exception {
		//WaitUtils wait = new WaitUtils(driver);
		//wait.ImplicitlyWait();
			
		/*****************************
		 * API DEMOS APPLICATION_ STARTS
		 *********************************************/
		logger.info("To Check Login Functionality-------> ");
		ChromeHomePage chromeHomePage = new ChromeHomePage(driver);
		chromeHomePage.sendDataInUsername(userName);
		chromeHomePage.sendDataInPAssword(password);
		System.out.println("Checking Conflicts11");
		System.out.println("Checking Conflicts22");
		System.out.println("Checking Conflicts33");
	}
}