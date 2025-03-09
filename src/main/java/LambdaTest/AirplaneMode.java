package LambdaTest;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class AirplaneMode {
	
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		
		DesiredCapabilities cap = new DesiredCapabilities();
		// the cap object stores all the desired capabilities to be invoked into the Android driver
		cap.setCapability("platformName", "Android");
		cap.setCapability("automationName", "UiAutomator2");
		cap.setCapability("deviceName", "Test_Emulator");
		
		// the below two capabilities are for access to the Setting App of the device
		cap.setCapability("appPackage", "com.android.settings");
        cap.setCapability("appActivity", ".Settings");
//        Path path = Paths.get("http://127.0.0.1:4723/wd/hub") ;
//        URI uri = path.toUri () ;
        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"),cap);
		
		// Open Airplane Mode settings
        driver.findElement(By.id("com.android.settings:id/search_action_bar")).click();
        Thread.sleep(15000);
        driver.findElement(By.className("android.widget.EditText")).sendKeys("Ai");
        Thread.sleep(15000);
        driver.pressKey(new KeyEvent().withKey(AndroidKey.ENTER));

        // Verify Airplane Mode is turned off
        WebElement airplaneModeSwitch = driver.findElement(By.id("com.android.settings:id/switch_widget"));
        Assert.assertEquals(airplaneModeSwitch.getAttribute("checked"), "false", "Airplane Mode is currently ON, please turn it OFF");
        
        //Close mobile settings
        driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
        driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
        
        driver.quit();
		
	}

}
