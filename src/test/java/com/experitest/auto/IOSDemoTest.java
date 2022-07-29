package com.experitest.auto;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

public class IOSDemoTest extends BaseTest {
	protected IOSDriver<IOSElement> driver = null;

	@BeforeMethod
	@Parameters("deviceQuery")
	public void setUp(@Optional("@os='ios'") String deviceQuery) throws Exception {
		init(deviceQuery);
		// Init application / device capabilities
		dc.setCapability(MobileCapabilityType.APP, "cloud:com.experitest.ExperiBank");
		dc.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "com.experitest.ExperiBank");
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
		dc.setCapability("platformName", MobilePlatform.IOS);
		dc.setCapability("appiumVersion", "1.22.2");
		dc.setCapability("deviceName", "auto");
		dc.setCapability("testName", "iOS Tests from CI-CD");
		driver = new IOSDriver<>(new URL(CloudUrl + "/wd/hub"), dc);
	}

	@Test
	public void test() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id("usernameTextField")).sendKeys("company");
		driver.findElement(By.id("passwordTextField")).sendKeys("company");
		driver.findElement(By.id("loginButton")).click();
		driver.findElement(By.xpath("//*[@name='makePaymentButton']")).click();
		driver.findElement(By.xpath("//*[@name='phoneTextField']")).sendKeys("0501234567");
		driver.findElement(By.xpath("//*[@name='nameTextField']")).sendKeys("John Snow");
		driver.findElement(By.xpath("//*[@name='amountTextField']")).sendKeys("50");
		driver.findElement(By.xpath("//*[@name='countryButton']")).click();
		driver.findElement(By.xpath("//*[@name='Switzerland']")).click();
		driver.findElement(By.xpath("//*[@name='sendPaymentButton']")).click();
		driver.findElement(By.xpath("//*[@name='Yes']")).click();

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
