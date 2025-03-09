package LambdaTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class UserCertificates {

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
		AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"),cap);
		
		// Navigate to the certificates section in emulator
		// Navigate to Security settings
        WebElement securityOption = driver.findElement(By.xpath("//android.widget.TextView[@text='Security']"));
        securityOption.click();

        // Navigate to Credential Storage settings
        WebElement credentialStorageOption = driver.findElement(By.xpath("//android.widget.TextView[@text='Credential Storage']"));
        credentialStorageOption.click();

        // Navigate to Trusted Certificates settings
        WebElement trustedCertificatesOption = driver.findElement(By.xpath("//android.widget.TextView[@text='Trusted certificates']"));
        trustedCertificatesOption.click();

        // Navigate to User Certificates
        WebElement userCertificatesOption = driver.findElement(By.xpath("//android.widget.TextView[@text='User certificates']"));
        userCertificatesOption.click();

        List<WebElement> certificateElements = driver.findElements(By.id("userCertificates"));
        
       // Only 1 user certificate is installed for demo purpose

        // Extract certificate information
        for (WebElement certificate : certificateElements) {
            String certificateName = certificate.getText();
            System.out.println("Certificate Name: " + certificateName);
        }
        driver.quit();
	}

}
