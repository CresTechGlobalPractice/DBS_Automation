package com.crestech.common.utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.RemoteWebElement;

import com.crestech.appium.utils.CommonAppiumTest;
import com.crestech.base.UserBaseTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;

public class AndroidAlert extends UserBaseTest{
	
	
	

	public static AppiumDriver<RemoteWebElement> driver;
	public AndroidAlert(AppiumDriver<RemoteWebElement> driver2) {
		this.driver = driver2;
	}
	
	public String ToastMessage() throws Exception    //android.widget.Toast[1]
	{
		try {
			CommonAlertElements obj =new CommonAlertElements(driver);
			return obj.getToastMessageElement().getAttribute("name");
		} catch (Exception e) {
			throw new Exception(CommonAppiumTest.getExceptionMessage(e));
		}
	}
	
	public List<String> MultiToastMessage() throws Exception    //android.widget.Toast[1]
	{
		try {
			CommonAlertElements obj =new CommonAlertElements(driver);
			List<MobileElement> Elementlist = obj.getToastMessageElementList();
		
			int l = Elementlist.size();
			List<String> ToastMessagelist = new ArrayList<String>();
			String str;
			for (int i = 0; i < l; i++) {
				str = Elementlist.get(i).getAttribute("name");
				ToastMessagelist.add(i, str);
			}
			return ToastMessagelist;
		} catch (Exception e) {
			throw new Exception(CommonAppiumTest.getExceptionMessage(e));
		}
	}
	
	public void PermissionAlertToggleList(int index) throws Exception    
	{
		
		try {
			CommonAlertElements obj =new CommonAlertElements(driver);
			List<MobileElement> Elementlist = obj.toggleList();
			
			CommonAppiumTest.clickOnElement(Elementlist.get(index));
			
		} catch (Exception e) {
			throw new Exception(CommonAppiumTest.getExceptionMessage(e));
		}
	}
	
	public void AlertHandlingWithButtonTiltleMessage(MobileElement Button, String expectecMessage, String expectecTitle) throws Exception    //android.widget.Toast[1]
	{
		try {
			CommonAlertElements obj =new CommonAlertElements(driver);
			String actualTitle = CommonAppiumTest.getTexOfElement(obj.getalertTitle());
			String actualMessage = CommonAppiumTest.getTexOfElement(obj.getalertMessage());
			
				if( actualTitle.equalsIgnoreCase(expectecTitle) && actualMessage.equalsIgnoreCase(expectecMessage))
					CommonAppiumTest.clickOnElement(Button);
				   
				Asserts.assertEquals(actualTitle, expectecTitle, "Title Not matching");
				Asserts.assertEquals(actualMessage, expectecMessage, "Alert Message Not matching");
					
			
		} catch (Exception e) {
			throw new Exception(CommonAppiumTest.getExceptionMessage(e));
		}
	
	}
	
	@Step("Accepting Alert Message")
	public void AlertHandlingWithButtonMessage(MobileElement Button, String expectecMessage) throws Exception    //android.widget.Toast[1]
	{
		try {

			CommonAlertElements obj =new CommonAlertElements(driver);
			String actualMessage = CommonAppiumTest.getTexOfElement(obj.getalertMessage());
			
				if(actualMessage.equalsIgnoreCase(expectecMessage))
					CommonAppiumTest.clickOnElement(Button);
				   
				
				Asserts.assertEquals(actualMessage, expectecMessage, "Alert Message Not matching");
					
			
		} catch (Exception e) {
			throw new Exception(CommonAppiumTest.getExceptionMessage(e));
		}
	
	}
	/**
	 *handling of fingerprint alert appear after login
	 */
	@Step("Accepting Alert Message")
	public void fingerprintAlertHandlingWithButtonMessage(MobileElement Button, String expectecMessage) throws Exception    //android.widget.Toast[1]
	{
		try {

			CommonAlertElements obj =new CommonAlertElements(driver);
			String actualMessage = CommonAppiumTest.getTexOfElement(obj.headerFingerprintMessage());
			
				if(actualMessage.equalsIgnoreCase(expectecMessage))
					CommonAppiumTest.clickOnElement(Button);
				   
				
				Asserts.assertEquals(actualMessage, expectecMessage, "Alert Message Not matching");
					
			
		} catch (Exception e) {
			throw new Exception(CommonAppiumTest.getExceptionMessage(e));
		}
	
	}

/**
 *handling of recording alert appear after login
 */
@Step("Accepting Alert Message")
public void recordingAlertHandlingWithButtonMessage(MobileElement Button, String expectecMessage) throws Exception    //android.widget.Toast[1]
{
	try {

		CommonAlertElements obj =new CommonAlertElements(driver);
		String actualMessage = CommonAppiumTest.getTexOfElement(obj.headerRecordingMessage());
		
			if(actualMessage.equalsIgnoreCase(expectecMessage))
				CommonAppiumTest.clickOnElement(Button);
			   
			
			Asserts.assertEquals(actualMessage, expectecMessage, "Alert Message Not matching");
				
		
	} catch (Exception e) {
		throw new Exception(CommonAppiumTest.getExceptionMessage(e));
	}

}
	/**
	 *Relaunch application alert
	 */
	@Step("Quiting the application for relanching")
	public void relanchAlertWithButtonTiltleMessage(MobileElement Button, String expectecMessage, String expectecTitle) throws Exception    //android.widget.Toast[1]
	{
		try {CommonAlertElements obj =new CommonAlertElements(driver);
			String actualTitle = CommonAppiumTest.getTexOfElement(obj.getTitle());
			String actualMessage = CommonAppiumTest.getTexOfElement(obj.getMessage());
			
				if( actualTitle.equalsIgnoreCase(expectecTitle) && actualMessage.equalsIgnoreCase(expectecMessage))
					CommonAppiumTest.clickOnElement(Button);
				   
				Asserts.assertEquals(actualTitle, expectecTitle, "Title Not matching");
				Asserts.assertEquals(actualMessage, expectecMessage, "Alert Message Not matching");
					
			
		} catch (Exception e) {
			throw new Exception(CommonAppiumTest.getExceptionMessage(e));
		}
	
	}


	
	@Step("Accepting Alert Message")
	public void AlertHandlingWithButtonMessageContainsCase(MobileElement Button, String expectecMessage) throws Exception    //android.widget.Toast[1]
	{
		try {

			CommonAlertElements obj =new CommonAlertElements(driver);
			String actualMessage = CommonAppiumTest.getTexOfElement(obj.getalertMessage());
			
				if(actualMessage.toLowerCase().contains(expectecMessage.toLowerCase()))
					CommonAppiumTest.clickOnElement(Button);
				   
				
				Asserts.assertTrue(actualMessage.toLowerCase().contains(expectecMessage.toLowerCase()), "Alert Message Does not contain expected String");
					
			
		} catch (Exception e) {
			throw new Exception(CommonAppiumTest.getExceptionMessage(e));
		}
	
	}
	
	public void AlertHandlingWithButtonTiltle(MobileElement Button, String expectecTitle) throws Exception    //android.widget.Toast[1]
	{
		try {
			CommonAlertElements obj =new CommonAlertElements(driver);
			String actualTitle = CommonAppiumTest.getTexOfElement(obj.getalertTitle());
			
			
				if( actualTitle.equalsIgnoreCase(expectecTitle))
					CommonAppiumTest.clickOnElement(Button);
				   
				Asserts.assertEquals(actualTitle, expectecTitle, "Title Not matching");
				
					
			
		} catch (Exception e) {
			throw new Exception(CommonAppiumTest.getExceptionMessage(e));
		}
	
	}
	public void notRespondingAlertWithButtonTiltle(MobileElement Button, String expectecTitle) throws Exception    //android.widget.Toast[1]
	{
		try {
			CommonAlertElements obj =new CommonAlertElements(driver);
			String actualTitle = CommonAppiumTest.getTexOfElement(obj.getNotRespondingTitle());
			
			
				if( actualTitle.equalsIgnoreCase(expectecTitle))
					CommonAppiumTest.clickOnElement(Button);
				   
				Asserts.assertEquals(actualTitle, expectecTitle, "Title Not matching");
				
					
			
		} catch (Exception e) {
			throw new Exception(CommonAppiumTest.getExceptionMessage(e));
		}
	
	}
	public void permissionAlertWithButtonMessage(MobileElement Button, String expectecMessage) throws Exception    //android.widget.Toast[1]
	{
		try {

			CommonAlertElements obj =new CommonAlertElements(driver);
			String actualMessage = CommonAppiumTest.getTexOfElement(obj.getPermissionMessage());
			
				if(actualMessage.equalsIgnoreCase(expectecMessage))
					CommonAppiumTest.clickOnElement(Button);
				   
				
				Asserts.assertEquals(actualMessage, expectecMessage, "Alert Message Not matching");
					
			
		} catch (Exception e) {
			throw new Exception(CommonAppiumTest.getExceptionMessage(e));
		}
	
	}
	
	public String getAlertTitle(MobileElement title) throws Exception    //android.widget.Toast[1]
	{
		try {	
			return title.getText();
		} catch (Exception e) {
			throw new Exception(CommonAppiumTest.getExceptionMessage(e));
		}
	
	}
	public String getAlertMessage(MobileElement message) throws Exception    
	{
		try {
			return message.getText();
		} catch (Exception e) {
			throw new Exception(CommonAppiumTest.getExceptionMessage(e));
		}
		
	}
	
	public void ClickOnButtonAlert(MobileElement Button) throws Exception    
	{
		try {
		
		CommonAppiumTest.clickOnElement(Button);
		
	} catch (Exception e) {
		throw new Exception(CommonAppiumTest.getExceptionMessage(e));
	}
	}
	
	
	public void getContextWebORNative() throws Exception    //android.widget.Toast[1]
	{
		try {
			Set<String> contextNames = driver.getContextHandles();
		for (String contextName : contextNames) {
		    System.out.println(contextName); //prints out something like NATIVE_APP \n WEBVIEW_1
		}
	
		} catch (Exception e) {
			throw new Exception(CommonAppiumTest.getExceptionMessage(e));
		}}
	public void switchContext(String AppType) throws Exception    //android.widget.Toast[1]
	{
		try {
			
		driver.context(AppType);
		} catch (Exception e) {
			throw new Exception(CommonAppiumTest.getExceptionMessage(e));
		}
		
	}

	/**
	 * This is applications 1st alert handling After login 
	 */
	@Step
	public void handlingFirstPopupAfterLogin(String expectecMessage) throws Exception    
	{
		
		try {
			CommonAlertElements obj =new CommonAlertElements(driver);
			String actualMessage = CommonAppiumTest.getTexOfElement(obj.headerMessage());
			if(actualMessage.equalsIgnoreCase(expectecMessage)) {
				Dimension windowSize1 = driver.manage().window().getSize();
				int y =(int)((windowSize1.getHeight())-10);
				int x =(int)((windowSize1.getWidth())/2);
				GestureUtils.swipeElementtoCoordinate(obj.swipeButton(),  x,  y);
			}
			
			Asserts.assertEquals(actualMessage, expectecMessage, "Alert Message Not matching");
			
		} catch (Exception e) {
			throw new Exception(CommonAppiumTest.getExceptionMessage(e));
		}
	}

}
