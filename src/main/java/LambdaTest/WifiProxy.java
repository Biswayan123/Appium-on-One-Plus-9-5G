package LambdaTest;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class WifiProxy {

	public static void main(String[] args) throws MalformedURLException {
		DesiredCapabilities cap = new DesiredCapabilities();
		// The 'cap' object stores all the desired capabilities to be invoked into the Android driver
		cap.setCapability("platformName", "Android");
		cap.setCapability("automationName", "UiAutomator2");
		cap.setCapability("deviceName", "Test_Emulator");
		
		// the below two capabilities are for access to the Setting App of the device
		cap.setCapability("appPackage", "com.android.settings");
        cap.setCapability("appActivity", ".Settings");
        
        // Initialize Appium Driver
		AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),cap);
		
		// Open Wifi settings
        driver.findElement(By.id("com.android.settings:id/search")).click();
        driver.findElement(By.id("android:id/search_src_text")).sendKeys("WiFi");
        driver.pressKey(new KeyEvent().withKey(AndroidKey.ENTER));

        // Find the element representing the connected SSID
        WebElement currentNetwork = driver.findElement(By.xpath("//*[text()='Home_5G']"));
        currentNetwork.click();
        
        //scroll down to the element
        driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0))"
        		+ ".scrollIntoView(new UiSelector().textContains(\"Proxy\").instance(0))"));

        WebElement noneOption = driver.findElement(By.xpath("//*[@id='Proxy']/span"));
        String proxySetting = noneOption.getText();
        System.out.println("Proxy Settings : " +proxySetting);
        Assert.assertEquals(proxySetting, "None");

        // Quit the driver
        driver.quit();
	}

}
