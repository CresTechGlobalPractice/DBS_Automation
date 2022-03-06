package com.crestech.base;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.crestech.appium.utils.ConfigurationManager;
import com.crestech.common.utilities.CommonTestData;
import com.crestech.common.utilities.ExcelUtils;
import com.crestech.common.utilities.ScreenshotUtils;
import com.crestech.common.utilities.WaitUtils;
import com.crestech.config.ContextManager;
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

/**
 *  *  * @author Divya, Shafkat,Shubham  *  
 */

public class UserBaseTest extends TestListenerAdapter implements ITestListener {

	public AppiumDriver<RemoteWebElement> driver = null;
	public static ConfigurationManager prop;
	protected boolean dontStopAppOnReset = false; 
	public String device_udid;
	private AppiumDriverLocalService service;
	private AppiumServiceBuilder builder;
	private static final AllureLifecycle ALLURE_LIFECYCLE = Allure.getLifecycle();
	public List<String> excelDataList;
	public ScreenshotUtils scrShotUtils = null;
	private static Object syncObj = new Object();
	WaitUtils waitut=null;

	Logger logger = Logger.getLogger(UserBaseTest.class);

	public UserBaseTest() 	{
		try {
			prop = ConfigurationManager.getInstance();
			//scrShotUtils = new ScreenshotUtils();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author Shibu Prasad Panda
	 * @param name- device name/udid
	 * @throws Exception
	 */
	@Parameters({ "device", "version", "os", "manafacturer", "min_Ver", "max_Ver", "individual_ID", "app_Name"})
	@BeforeMethod(alwaysRun = true)
	public void startApp(String device, String version, String os, String manafacturer, String min_Ver, String max_Ver, String individual_ID, Method method, String app_Name) throws Exception {
		logger.info("Inside Before Method");
		if (prop.getProperty("ReportType").trim().equalsIgnoreCase("Extent")) {
			ContextManager.createNode(method.getName() + " " + device);
		}
		synchronized (syncObj) {
		excelDataList = ExcelUtils.readExcel(System.getProperty("user.dir") + File.separator+"TestData"+File.separator+"TestData.xlsx", os, "Capabilities");
		}
		DesiredCapabilities androidCaps = androidNative(excelDataList, device, version, os, manafacturer, min_Ver, max_Ver, individual_ID);
		
		try {
			this.driver = startingServerInstance(androidCaps, os, excelDataList);
			//	PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(5)), this);
			ContextManager.setDriver(this.driver); 
			waitut =new WaitUtils(this.driver);
			waitut.ImplicitlyWait(30);
			
			
		} catch (Exception e) {
			if (prop.getProperty("ReportType").trim().equalsIgnoreCase("Extent")) {
				ContextManager.getExtentReportForPrecondition().skip(MarkupHelper.createLabel("Test Case is SKIPPED", ExtentColor.YELLOW));
				ContextManager.getExtentReportForPrecondition().log(Status.SKIP, e.getMessage());
			}
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			throw e;
		}

	}

	@BeforeSuite(alwaysRun = true)
	public void beforeSuite() throws Exception {
//		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
//	        public void run() {
//	        	logger.info("In the middle of a shutdown");
//	            flushReport();
//	        }
//	        }));
		try {
			logger.info("Inside before suite");
			if (prop.getProperty("ReportType").trim().equalsIgnoreCase("Extent")) {
				ContextManager.startReport();
			} else {
				try {
					File fileClean = new File(System.getProperty("user.dir") + "/allure-results");
					FileUtils.deleteDirectory(fileClean);
				} catch (Exception e) {
					System.out.println("Dir doesno exist");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*********************************
	 * FOR Extent Report Implementation
	 * @throws Exception 
	 ***********************************************/

	@Parameters({ "device", "os" })
	@AfterMethod
	public void getResult(ITestResult result, String device, String os) throws Exception {  
		if (prop.getProperty("ReportType").trim().equalsIgnoreCase("Extent")) {
			String screenshotProperty = prop.getProperty("ScreenshotForReport").trim();
			if (result.getStatus() == ITestResult.FAILURE) {
				ContextManager.getExtentReportForPrecondition().fail(MarkupHelper.createLabel("Test Case is FAILED", ExtentColor.RED));
				if (screenshotProperty.equalsIgnoreCase("None")) {
					ContextManager.getExtentReportForPrecondition().info("TestCase Failed");
				} else {
					try {
						ContextManager.getExtentReportForPrecondition().log(Status.FAIL, "Snapshot below:", MediaEntityBuilder
								.createScreenCaptureFromPath(ScreenshotUtils.getScreenshot(driver)).build());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				ContextManager.getExtentReportForPrecondition().fail(result.getThrowable());
			} else if (result.getStatus() == ITestResult.SUCCESS) {
				ContextManager.getExtentReportForPrecondition().pass(MarkupHelper.createLabel("Test Case is PASSED", ExtentColor.GREEN));
				if (screenshotProperty.equalsIgnoreCase("pass")) {
					try {
						ContextManager.getExtentReportForPrecondition().log(Status.PASS, "Snapshot below:", MediaEntityBuilder
								.createScreenCaptureFromPath(ScreenshotUtils.getScreenshot(driver)).build());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} else {
				ContextManager.getExtentReportForPrecondition().skip(MarkupHelper.createLabel("Test Case is SKIPPED", ExtentColor.YELLOW));
				ContextManager.getExtentReportForPrecondition().skip(result.getThrowable());
			}
		}

				System.out.println("CloseApp");
				if (ContextManager.getDriver() != null) {
					ContextManager.getDriver().quit();
				}
				if (os.equalsIgnoreCase("Android"))
					service.stop();
	}



	@AfterSuite(alwaysRun = true)
	public void flushReport() {
		System.out.println("My Report Flushed Start.....");
		try {
			if (prop.getProperty("ReportType").trim().equalsIgnoreCase("Extent")) {
				File src= new File(System.getProperty("user.dir") + File.separator+ "AllureReport");
				try {
					FileUtils.deleteDirectory(src);
				} catch (IOException e) {
					e.printStackTrace();
				}
				ContextManager.endReport();
			}
			else {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
				Date date = new Date();
				File srcDir = new File(System.getProperty("user.dir") + File.separator+ "allure-results");
				File destDir = new File(System.getProperty("user.dir") + File.separator+ "AllureReport" + File.separator+ "Allure_"
						+ dateFormat.format(date).replace(" ", "_").replace("-", "") + File.separator+ "allure-results");
				
				try {
					File categoriesJsonFile = new File(System.getProperty("user.dir") + File.separator+ "src"+ File.separator+ 
							"test"+ File.separator+ "resources"+ File.separator+ "categories.json");
					
			        FileUtils.copyFileToDirectory(categoriesJsonFile, srcDir);
					FileUtils.forceMkdir(destDir);
					FileUtils.copyDirectory(srcDir, destDir);
					Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"allure generate "+destDir+" -o "+System.getProperty("user.dir") + File.separator+ "AllureReport"+ File.separator+"Allure_" + 
							dateFormat.format(date).replace(" ", "_").replace("-", "")+ File.separator+"allure-report");
					Runtime.getRuntime().exec("taskkill /f /im cmd.exe") ;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
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

	public synchronized DesiredCapabilities androidNative(List<String> s, String device_udid, String version, String os, String manafacturer, String min_Ver, String max_Ver, String individual_ID)
			throws IOException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		switch (os) {
		case "Android":
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device_udid);
			capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, version);
			capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, s.get(1));
			capabilities.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY,  s.get(1));
			capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,s.get(2));
			capabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "\\App\\" + s.get(14));
			capabilities.setCapability(MobileCapabilityType.UDID, s.get(6));
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");

			if (checkDeviceVersion(version))
				capabilities.setCapability("automationName", s.get(4));
			else
				capabilities.setCapability("automationName", "UiAutomator1");

			if (dontStopAppOnReset == true)
				capabilities.setCapability(AndroidMobileCapabilityType.DONT_STOP_APP_ON_RESET, true);
			else
				capabilities.setCapability(MobileCapabilityType.NO_RESET, true);

			capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 600);
			break;

		case "iOS":
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device_udid);
			capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, version);
			capabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "/App/" + s.get(3));
			capabilities.setCapability(MobileCapabilityType.UDID, s.get(6));
			capabilities.setCapability("automationName", s.get(4));
			capabilities.setCapability("bundleId", s.get(7));
			if (dontStopAppOnReset == true) {
				capabilities.setCapability(AndroidMobileCapabilityType.DONT_STOP_APP_ON_RESET, true);
			} else {
				capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
			}
			capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 600);
			break;

		case "Android_Chrome":
			capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
			//capabilities.setCapability("automationName", s.get(4));
			capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, version);
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device_udid);
			//capabilities.setCapability(MobileCapabilityType.UDID, s.get(6));
			capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, s.get(5));
			//capabilities.setCapability("chromedriverExecutable", prop.getProperty("browserLocation"));
			//			if (checkDeviceVersion(version)) {
			//				capabilities.setCapability("automationName", s.get(4));
			//			} else {
			//				capabilities.setCapability("automationName", "UiAutomator1");
			//			}
			if (dontStopAppOnReset == true) {
				capabilities.setCapability(AndroidMobileCapabilityType.DONT_STOP_APP_ON_RESET, true);
			} else {
				capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
			}
			break;

		case "pCloudyAndroid":

			//System.out.println(device_udid + "," + version);
			capabilities.setCapability("pCloudy_Username", s.get(12));
			capabilities.setCapability("pCloudy_ApiKey", s.get(13));
			capabilities.setCapability("pCloudy_DurationInMinutes", s.get(15));
			capabilities.setCapability("newCommandTimeout", 20000);
			capabilities.setCapability("launchTimeout", 90000);
			capabilities.setCapability("pCloudy_DeviceFullName", device_udid);
			capabilities.setCapability("platformVersion", version);
			capabilities.setCapability("platformName", "Android");
			capabilities.setCapability("pCloudy_ApplicationName", s.get(14));
			capabilities.setCapability("appPackage", s.get(2));
			capabilities.setCapability("appActivity", s.get(1));
			capabilities.setCapability("pCloudy_WildNet", "false");
			capabilities.setCapability("autoGrantPermissions", "true");
			capabilities.setCapability("pCloudy_EnableVideo", "true");

//			capabilities.setCapability("pCloudy_DeviceManafacturer", manafacturer);
//			capabilities.setCapability("pCloudy_MinVersion",min_Ver); 
//			capabilities.setCapability("pCloudy_MaxVersion",max_Ver); 
//			capabilities.setCapability("pCloudy_Individual",individual_ID);
			capabilities.setCapability(MobileCapabilityType.NO_RESET, true);

			if (checkDeviceVersion(version)) {
				capabilities.setCapability("automationName", "UiAutomator2");
				capabilities.setCapability("uiautomator2ServerLaunchTimeout", 200000);
				capabilities.setCapability("noSign", true);
			} else {
				capabilities.setCapability("automationName", "UiAutomator1");
			}

			break;

		case "pCloudyIOS":
			capabilities.setCapability("pCloudy_Username", s.get(12));
			capabilities.setCapability("pCloudy_ApiKey", s.get(13));
			//capabilities.setCapability("pCloudy_ApplicationName", s.get(14));
			capabilities.setCapability("pCloudy_DurationInMinutes", s.get(15));
	        capabilities.setCapability("pCloudy_DeviceFullName", device_udid);
		    capabilities.setCapability("platformVersion", version);
			capabilities.setCapability("newCommandTimeout", 20000);
			capabilities.setCapability("launchTimeout", 90000);
			capabilities.setCapability("bundleId", s.get(7));
			capabilities.setCapability("automationName", s.get(4));
			capabilities.setCapability("pCloudy_WildNet", "false");
			capabilities.setCapability("platformName", "ios");
			capabilities.setCapability("noReset", true);
			capabilities.setCapability("fullReset", false);
		    capabilities.setCapability("pCloudy_EnableVideo", "true");
		    capabilities.setCapability("autoGrantPermissions", "true");
		    capabilities.setCapability("acceptAlerts", true);
		    capabilities.setCapability("idleTimeout",180);
		    //capabilities.setCapability("autoAcceptAlerts", "true"); 
		    //capabilities.setCapability("autoDissmissAlerts", "true");
		    capabilities.setCapability("locationServicesAuthorized", "true");
		    capabilities.setCapability("clearSystemFiles", true);
		    
//		    capabilities.setCapability("project", "DBSAutomation");
//			capabilities.setCapability("build", "DBSBuild1");
//			capabilities.setCapability("name", method.getName());//method.getName()
//			capabilities.setCapability("tag", "Pr_Testing");
//			capabilities.setCapability("pCloudy_DeviceManafacturer", manafacturer);
//			capabilities.setCapability("pCloudy_MinVersion",min_Ver); 
//			capabilities.setCapability("pCloudy_MaxVersion",max_Ver); 
//			capabilities.setCapability("pCloudy_Individual",individual_ID);

			break;

		case "pCloudyAndroidChrome":
			capabilities.setCapability("pCloudy_Username", s.get(12));
			capabilities.setCapability("pCloudy_ApiKey", s.get(13));
			capabilities.setCapability("pCloudy_DurationInMinutes", s.get(15));
			capabilities.setCapability("newCommandTimeout", 600);
			capabilities.setCapability("launchTimeout", 90000);
			capabilities.setCapability("pCloudy_DeviceFullName", device_udid);
			capabilities.setCapability("platformVersion", version);
			capabilities.setCapability("platformName", "Android");
			capabilities.setBrowserName(s.get(5));
			capabilities.setCapability("pCloudy_WildNet", "false");

			if (checkDeviceVersion(version)) {
				capabilities.setCapability("automationName", "UiAutomator2");
				capabilities.setCapability("uiautomator2ServerLaunchTimeout", 90000);
				capabilities.setCapability("noSign", true);
			} else {
				capabilities.setCapability("automationName", "UiAutomator1");
			}
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
	public AppiumDriver<RemoteWebElement> startingServerInstance(DesiredCapabilities androidCaps, String os, List<String> s)
			throws Exception {

		if (os.equalsIgnoreCase("Android") || os.equalsIgnoreCase("Android_Chrome")) {
			// if simple appium installed
			/*
			 * driver = new AndroidDriver<RemoteWebElement>(new
			 * URL("http://127.0.0.1:4723/wd/hub"), androidCaps);
			 */


			//			  //install nodejs in your system ->through nodejs install appium 
			//			  // Build the Appium service
			builder = new AppiumServiceBuilder();
			builder.withIPAddress(prop.getProperty("server_address"));
			builder.usingPort(Integer.parseInt(prop.getProperty("port")));
			builder.withCapabilities(androidCaps);
			builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
			builder.withLogFile(new File("C:\\Users\\Public\\Desktop\\Appium.text"));


			// Start the server with the builder 
			service =  AppiumDriverLocalService.buildService(builder);

			//System.out.println(service.getUrl().toString()); 
			try {
				service.start();
			} finally {
				service.stop();
			}

			//			Process p = Runtime.getRuntime().exec("cmd.exe /c start appium");
			//			Thread.sleep(5000);

			//	 driver = new AndroidDriver<RemoteWebElement>(service.getUrl(), androidCaps);
			//This time out is set because test can be run on slow Android SDK emulator
			// PageFactory.initElements(new AppiumFieldDecorator(driver, ofSeconds(5)), this);


			driver = new AndroidDriver<RemoteWebElement>(androidCaps);
		} else if (os.equalsIgnoreCase("pCloudyAndroid") || os.equalsIgnoreCase("pCloudyAndroidChrome")) {
//			driver = new AndroidDriver<RemoteWebElement>(
//					new URL(prop.getProperty("pCloudy_Endpoint") + "/appiumcloud/wd/hub"), androidCaps);
			driver = new AndroidDriver<RemoteWebElement>(
					new URL(s.get(3) + "/appiumcloud/wd/hub"), androidCaps);
		}
		else {
//			driver = new IOSDriver<RemoteWebElement>(
//					new URL(prop.getProperty("pCloudy_Endpoint") + "/appiumcloud/wd/hub"), androidCaps);
			driver = new IOSDriver<RemoteWebElement>(
					new URL(s.get(3) + "/appiumcloud/wd/hub"), androidCaps);
//			driver = new IOSDriver<RemoteWebElement>(
//					new URL(s.get(3) + "/progressive/wd/hub"), androidCaps);
		}
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
	 * @param availableMemoryAfterCancel   -Memory Space After Cancellation
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

	public static void addAttachment() {
		ALLURE_LIFECYCLE.addAttachment(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MMM-yy_hh:mm:ss")), "image/png", "png", ((TakesScreenshot) ContextManager.getDriver()).getScreenshotAs(OutputType.BYTES));
	}

	public void addAttachment(RemoteWebDriver driver) {
		ALLURE_LIFECYCLE.addAttachment(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MMM-yy_hh:mm:ss")), "image/png", "png", ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
	}
}
