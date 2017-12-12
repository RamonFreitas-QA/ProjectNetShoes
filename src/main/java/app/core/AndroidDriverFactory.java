package app.core;

import app.functions.FuntionsAndroidDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

public class AndroidDriverFactory {
    // ------------------------------ FIELDS ------------------------------
    protected static final Logger log = LoggerFactory.getLogger(FuntionsAndroidDriver.class);
    protected static AndroidDriver driver;

    // -------------------------- PROTECTED METHODS -------------------------
    protected void openMobileDriver(String deviceName, String udid, String platformName, String platformVersion, String appPackage, String appActivity) throws MalformedURLException {
        mobileBrowserOrApp(deviceName, udid, platformName, platformVersion, appPackage, appActivity);
    }

    protected void closeMobileDriver() {
        driver.quit();
    }

    // -------------------------- PRIVATE METHODS -------------------------
    private void mobileBrowserOrApp(String deviceName, String udid, String platformName, String
            platformVersion, String appPackage, String appActivity) throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", deviceName);
        caps.setCapability("udid", udid);
        caps.setCapability("platformName", platformName);
        caps.setCapability("platformVersion", platformVersion);
        caps.setCapability("appPackage", appPackage);
        caps.setCapability("appActivity", appActivity);
        caps.setCapability("noReset", "true");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
    }
    // -------------------------- END OF METHODS -------------------------
}
