package LambdaTest;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class OnePlusAccount {

	public static void main(String[] args) throws MalformedURLException {
		DesiredCapabilities cap = new DesiredCapabilities();
		
		
		// The 'cap' object stores all the desired capabilities to be invoked into the Android driver
		
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("Test_Emulator");
		cap.setCapability("platformName", "Android");
		cap.setCapability("automationName", "UiAutomator2");
		cap.setCapability("deviceName", "Test_Emulator");
		
		// the below two capabilities are for access to the Setting App of the device
		cap.setCapability("appPackage", "com.android.settings");
        cap.setCapability("appActivity", ".Settings");
        
        // Initialize Appium Driver
		AndroidDriver driver = new AndroidDriver(new URI("http://127.0.0.1:4723/wd/hub").toURL(),cap);
		
		// scrolling till element Users & accounts is in view
		driver.findElementByAndroidUiAutomator2("new UiScrollable(new UiSelector().scrollable(true).instance(0))"
        		+ ".scrollIntoView(new UiSelector().textContains(\"Users & accounts\").instance(0))");
		
		WebElement usersAndAccounts = driver.findElement(By.xpath("//android.widget.TextView[@text='Users & accounts']"));
        usersAndAccounts.click();

        // Check for presence of OnePlus account (example)
        WebElement onePlusAccount = driver.findElement(By.xpath("//android.widget.TextView[@text='OnePlus Account']"));
        Assert.assertFalse(onePlusAccount.isDisplayed());

        // Quit the driver
        driver.quit();

	}

}
