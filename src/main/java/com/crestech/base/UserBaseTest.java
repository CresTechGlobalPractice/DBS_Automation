package com.crestech.base;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.aspectj.lang.annotation.After;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.log4testng.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.crestech.appium.utils.ConfigurationManager;
import com.crestech.common.utilities.ExcelUtils;
import com.crestech.common.utilities.ScreenshotUtils;
import com.crestech.config.ContextManager;
import com.crestech.listeners.TestListener;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.AllureResultsWriter;
import io.qameta.allure.Attachment;
import io.qameta.allure.model.Allure2ModelJackson;
import io.qameta.allure.testng.AllureTestNg;

/**
 *
 * @author Shibu Prasad Panda
 *
 */

public class UserBaseTest extends TestListenerAdapter implements ITestListener {

	public AppiumDriver<RemoteWebElement> driver = null;
	public ConfigurationManager prop;
	protected boolean dontStopAppOnReset = false;
	public String device_udid;
	private AppiumDriverLocalService service;
	private AppiumServiceBuilder builder;
	public ExtentReports report;
	public ExtentTest extentLogs;
	public AllureLifecycle allureLifeCycle;

	Logger logger = Logger.getLogger(UserBaseTest.class);

	public UserBaseTest() 	{
		try {
			prop = ConfigurationManager.getInstance();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author Shibu Prasad Panda
	 * @param name- device name/udid
	 * @throws Exception
	 */
	@Parameters({ "device", "version", "os" })
	@BeforeMethod(alwaysRun = true)
	public void startApp(String device, String version, Method method, String os) throws Exception {
		List<String> s1 = new ArrayList<String>();
		s1 = ExcelUtils.readExcel(System.getProperty("user.dir") + "//TestData//TestData.xlsx", os, "Capabilities");
		if (prop.getProperty("ReportType").trim().equalsIgnoreCase("Extent"))
			extentLogs = report.createTest(method.getName() + " " + device);
		DesiredCapabilities androidCaps = androidNative(s1, device, version, os);
		System.out.println(androidCaps);
		Thread.sleep(2000);
		try {
			this.driver = startingServerInstance(androidCaps, os);
			ContextManager.setAndroidDriver(this.driver);
		} catch (Exception e) {
			if (prop.getProperty("ReportType").trim().equalsIgnoreCase("Extent")) {
				extentLogs.skip(MarkupHelper.createLabel("Test Case is SKIPPED", ExtentColor.YELLOW));
				extentLogs.log(Status.SKIP, e.getMessage());
			}
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			throw e;
		}

	}

	@BeforeSuite(alwaysRun = true)
	public void beforeSuite() throws Exception {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		if (prop.getProperty("ReportType").trim().equalsIgnoreCase("Extent")) {
			@SuppressWarnings("deprecation")
			ExtentHtmlReporter extent = new ExtentHtmlReporter(
					new File(System.getProperty("user.dir") + "/ExtentReport/" + dateFormat.format(date) + ".html"));
			report = new ExtentReports();
			report.attachReporter(extent);
			extent.config().setCSS(".r-img { width: 40%;}");
			extent.config().setDocumentTitle("AutomationTesting On Crestechglobal");
			extent.config().setReportName("Automation Report");
			extent.config().setTheme(Theme.DARK);
		} else {
			try {
				File fileClean = new File(System.getProperty("user.dir") + "/allure-results");
				FileUtils.deleteDirectory(fileClean);
				allureLifeCycle=Allure.getLifecycle();
			} catch (Exception e) {
				System.out.println("Dir doesno exist");
			}
		}
		
	}
	
	/*********************************
	 * FOR Extent Report Implementation
	 ***********************************************/

	@Parameters({ "device", "os" })
	@AfterMethod
	public void getResult(ITestResult result, String device, String os) {
		if (prop.getProperty("ReportType").trim().equalsIgnoreCase("Extent")) {
			String screenshotProperty = prop.getProperty("ScreenshotForExtent").trim();
			if (result.getStatus() == ITestResult.FAILURE) {
				extentLogs.fail(MarkupHelper.createLabel("Test Case is FAILED", ExtentColor.RED));
				if (screenshotProperty.equalsIgnoreCase("None")) {
					extentLogs.info("TestCase Failed");
				} else {
					try {
						extentLogs.log(Status.FAIL, "Snapshot below:", MediaEntityBuilder
								.createScreenCaptureFromPath(ScreenshotUtils.getScreenshot(driver)).build());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				extentLogs.fail(result.getThrowable());
			} else if (result.getStatus() == ITestResult.SUCCESS) {
				extentLogs.pass(MarkupHelper.createLabel("Test Case is PASSED", ExtentColor.GREEN));
				if (screenshotProperty.equalsIgnoreCase("pass")) {
					try {
						extentLogs.log(Status.PASS, "Snapshot below:", MediaEntityBuilder
								.createScreenCaptureFromPath(ScreenshotUtils.getScreenshot(driver)).build());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} else {
				extentLogs.skip(MarkupHelper.createLabel("Test Case is SKIPPED", ExtentColor.YELLOW));
				extentLogs.skip(result.getThrowable());
			}
		}

		System.out.println("stopApp");
		if (driver != null) {
			this.driver.quit();
		}
		if (os.equalsIgnoreCase("Android"))
			service.stop();

	}

	@AfterSuite(alwaysRun = true)
	public void flushReport() {
		System.out.println("My Report Flushed Start.....");
		if (prop.getProperty("ReportType").trim().equalsIgnoreCase("Extent")) {
			File src= new File(System.getProperty("user.dir") + "\\AllureReport");
			try {
				FileUtils.deleteDirectory(src);
			} catch (IOException e) {
				e.printStackTrace();
			}
			report.flush();
		}
		else {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
			Date date = new Date();
			File srcDir = new File(System.getProperty("user.dir") + "\\allure-results");
			File destDir = new File(System.getProperty("user.dir") + "\\AllureReport\\allure-results_"
					+ dateFormat.format(date).replace(" ", "_").replace("-", ""));
			try {
				FileUtils.forceMkdir(destDir);
				FileUtils.copyDirectory(srcDir, destDir);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("My Report Flushed End.....");
	}

	/**
	 * This will return the Desired Capabilities instance
	 *
	 * @param device Id
	 * @return capabilities instance
	 * @throws IOException
	 */

	public synchronized DesiredCapabilities androidNative(List<String> s, String device_udid, String version, String os)
			throws IOException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		switch (os) {
		case "Android":
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device_udid);
			capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, version);
			capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, s.get(3));
			capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, s.get(4));
			capabilities.setCapability(MobileCapabilityType.APP,
					System.getProperty("user.dir") + "\\App\\" + s.get(16));
			capabilities.setCapability(MobileCapabilityType.UDID, s.get(8));
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
			if (checkDeviceVersion(version)) {
				capabilities.setCapability("automationName", s.get(6));
			} else {
				capabilities.setCapability("automationName", "UiAutomator1");
			}
			if (dontStopAppOnReset == true) {
				capabilities.setCapability(AndroidMobileCapabilityType.DONT_STOP_APP_ON_RESET, true);
			} else {
				capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
			}
			break;

		case "iOS":
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device_udid);
			capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, version);
			capabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "/App/" + s.get(5));
			capabilities.setCapability(MobileCapabilityType.UDID, s.get(8));
			capabilities.setCapability("automationName", s.get(6));
			capabilities.setCapability("bundleId", s.get(9));
			if (dontStopAppOnReset == true) {
				capabilities.setCapability(AndroidMobileCapabilityType.DONT_STOP_APP_ON_RESET, true);
			} else {
				capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
			}
			capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 600);
			break;

		case "Android_Chrome":
			capabilities.setCapability("automationName", s.get(6));
			capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, s.get(2));
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device_udid);
			capabilities.setCapability(MobileCapabilityType.UDID, s.get(8));
			capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, s.get(7));
			capabilities.setCapability("chromedriverExecutable", prop.getProperty("browserLocation"));
			if (checkDeviceVersion(version)) {
				capabilities.setCapability("automationName", s.get(6));
			} else {
				capabilities.setCapability("automationName", "UiAutomator1");
			}
			if (dontStopAppOnReset == true) {
				capabilities.setCapability(AndroidMobileCapabilityType.DONT_STOP_APP_ON_RESET, true);
			} else {
				capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
			}
			break;

		case "pCloudyAndroid":

			System.out.println(device_udid + "," + version);
			capabilities.setCapability("pCloudy_Username", s.get(14));
			capabilities.setCapability("pCloudy_ApiKey", s.get(15));
			capabilities.setCapability("pCloudy_DurationInMinutes", s.get(17));
			capabilities.setCapability("newCommandTimeout", 600);
			capabilities.setCapability("launchTimeout", 90000);
			capabilities.setCapability("pCloudy_DeviceFullName", device_udid);
			capabilities.setCapability("platformVersion", version);
			capabilities.setCapability("platformName", "Android");
			capabilities.setCapability("pCloudy_ApplicationName", s.get(16));
			capabilities.setCapability("appPackage", s.get(4));
			capabilities.setCapability("appActivity", s.get(3));
			capabilities.setCapability("pCloudy_WildNet", "false");

			if (checkDeviceVersion(version)) {
				capabilities.setCapability("automationName", "UiAutomator2");
				capabilities.setCapability("uiautomator2ServerLaunchTimeout", 90000);
				capabilities.setCapability("noSign", true);
			} else {
				capabilities.setCapability("automationName", "UiAutomator1");
			}

			break;

		case "pCloudyIOS":
			capabilities.setCapability("pCloudy_Username", s.get(14));
			capabilities.setCapability("pCloudy_ApiKey", s.get(15));
			capabilities.setCapability("pCloudy_ApplicationName", s.get(16));
			capabilities.setCapability("pCloudy_DurationInMinutes", s.get(17));
			capabilities.setCapability("pCloudy_DeviceFullName", device_udid);
			capabilities.setCapability("platformVersion", version);
			capabilities.setCapability("newCommandTimeout", 600);
			capabilities.setCapability("launchTimeout", 90000);
			capabilities.setCapability("bundleId", s.get(9));
			capabilities.setCapability("acceptAlerts", true);
			capabilities.setCapability("automationName", s.get(6));
			capabilities.setCapability("pCloudy_WildNet", "false");
			capabilities.setCapability("platformName", "ios");

			break;

		default:
			System.out.println("Please Select pCloudyAndroid OR pCloudyIOS in properties File");
			capabilities = null;
		}

		return capabilities;
	}

	/**
	 * This will Start the Server
	 *
	 * @param Capabilities
	 * @return Driver Instance
	 * @throws Exception
	 */
	public AppiumDriver<RemoteWebElement> startingServerInstance(DesiredCapabilities androidCaps, String os)
			throws Exception {

		if (os.equalsIgnoreCase("Android") || os.equalsIgnoreCase("Android_Chrome")) {
			// if simple appium installed
			// driver = new AndroidDriver<RemoteWebElement>(new
			// URL("http://127.0.0.1:4723/wd/hub"), androidCaps);

			// install nodejs in your system ->through nodejs install appium
			// Build the Appium service
			builder = new AppiumServiceBuilder();
			builder.withIPAddress(prop.getProperty("server_address"));
			builder.usingPort(Integer.parseInt(prop.getProperty("port")));
			builder.withCapabilities(androidCaps);
			builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);

			// Start the server with the builder
			service = AppiumDriverLocalService.buildService(builder);
			try {
				service.start();
			} finally {
				service.stop();
			}
			driver = new AndroidDriver<RemoteWebElement>(androidCaps);
		} else if (os.equalsIgnoreCase("pCloudyAndroid")) {
			driver = new AndroidDriver<RemoteWebElement>(
					new URL(prop.getProperty("pCloudy_Endpoint") + "/appiumcloud/wd/hub"), androidCaps);
		} else {
			driver = new IOSDriver<RemoteWebElement>(
					new URL(prop.getProperty("pCloudy_Endpoint") + "/appiumcloud/wd/hub"), androidCaps);
		}
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
		return driver;
	}

	/**
	 * This method is to check Memory is deducted or not
	 *
	 * @param availableMemoryBeforeDownload
	 * @param availableMemoryAfterDownload
	 * @return
	 */
	public boolean isMemoryDeducted(double availableMemoryBeforeDownload, double availableMemoryAfterDownload) {

		if (availableMemoryBeforeDownload > availableMemoryAfterDownload)
			return true;
		else
			return false;
	}

	/**
	 * This method is to check Memory is reverted or not
	 *
	 * @param availableMemoryBeforeCancel -Memory Space At start
	 * @param availableMemoryAfterCancel  -Memory Space After Cancellation
	 * @return-return the boolean value
	 */
	public boolean isMemoryReverted(double availableMemoryAfterCancel, double availableMemoryBeforeCancel) {

		if (availableMemoryAfterCancel > availableMemoryBeforeCancel)
			return true;
		else
			return false;
	}

	public boolean isEpisodeFitOnDeviceQueue(int startEpisode, int lastEpisode, Set<Integer> episodeList) {
		for (Integer episode : episodeList) {
			if (episode >= startEpisode && episode <= lastEpisode) {
				return true;
			} else {
				break;
			}
		}
		return false;
	}

	/**
	 * To check whether the device version is >=6
	 *
	 * @param deviceVersion
	 * @return true/false
	 */
	public boolean checkDeviceVersion(String deviceVersion) {
		try {
			String str = deviceVersion.replace(".", ",").split(",")[0];
			int version = Integer.parseInt(str);
			if (version >= 6) {
				return true;
			}
			return false;
		} catch (Exception e) {
			System.out.println("version not given. Required to set automationName:UiAutomator2");
		}

		return false;
	}

	/**
	 * For using assert on testcase level
	 * 
	 * @param value
	 * @param detail
	 * @param StepDetail
	 */

	/*
	 * public void log(boolean value, String detail, String StepDetail) { if(value)
	 * { ExtentTestManager.getTest().log(LogStatus.PASS, ); } else {
	 * ExtentTestManager.getTest().log(LogStatus.FAIL, ); } }
	 */
	
	public void addAttachment() {
		allureLifeCycle.addAttachment(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MMM-yy_hh:mm:ss")), "image/png", "png", ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
	}
	
	public void addAttachment(RemoteWebDriver driver) {
		allureLifeCycle=Allure.getLifecycle();
		allureLifeCycle.addAttachment(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MMM-yy_hh:mm:ss")), "image/png", "png", ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
	}

}
