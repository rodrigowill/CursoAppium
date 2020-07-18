package br.ce.rodrigo.appium;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class CalculadoraTesteDispositivo {
	
	@Test
	public void deveSomarDoisValores() throws MalformedURLException {
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
	    desiredCapabilities.setCapability("platformName", "Android");
	    desiredCapabilities.setCapability("deviceName", "emulator-5554");
	    desiredCapabilities.setCapability("automationName", "uiautomator2");
	    desiredCapabilities.setCapability("appPackage", "com.google.android.calculator");
	    desiredCapabilities.setCapability("appActivity", "com.android.calculator2.Calculator");
	    
	    AndroidDriver<MobileElement> driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
	    
	    MobileElement el5 = (MobileElement) driver.findElementById("com.google.android.calculator:id/digit_2");
	    el5.click();
	    MobileElement el6 = (MobileElement) driver.findElementByAccessibilityId("mais");
	    el6.click();
	    MobileElement el7 = (MobileElement) driver.findElementById("com.google.android.calculator:id/digit_2");
	    el7.click();
	    MobileElement el8 = (MobileElement) driver.findElementById("com.google.android.calculator:id/result");
	    el8.click();
	    
	    Assert.assertEquals("4", el8.getText());
	    
	    driver.quit();
	    
	}
	

}
