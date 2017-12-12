package mobileWeb.core;

import desktop.util.SystemProperties;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverMobileFactor {
    // ------------------------------ FIELDS ------------------------------
    protected static WebDriver driverMobile;
    protected WebDriverWait wait;

    // -------------------------- PROTECTED METHODS -------------------------
    protected static void openBrowserMobile(String deviceName, String platformName, String platformVersion) throws MalformedURLException {
        createBrowserMobileTest(deviceName, platformName, platformVersion);
        String url = null;
        if (SystemProperties.get("Site").equals("Zattini"))
            url = "https://www.zattini.com.br";

        if (SystemProperties.get("Site").equals("ShoeStock"))
            url = "https://www.shoestock.com.br/";

        if (SystemProperties.get("Site").equals("NetShoesAr"))
            url = "https://www.netshoes.com.ar/";

        driverMobile.get(url);
    }

    // -------------------------- PRIVATE METHODS -------------------------
    private static void createBrowserMobileTest(String deviceName, String platformName, String platformVersion) throws MalformedURLException {
        DesiredCapabilities capabilities = DesiredCapabilities.android();
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, BrowserType.CHROME);
        capabilities.setCapability(MobileCapabilityType.PLATFORM, Platform.ANDROID);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
        capabilities.setCapability(MobileCapabilityType.VERSION, platformVersion);
        driverMobile = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    protected void closeBrowserMobile() {
        driverMobile.quit();
    }

    protected void acessarUrl(String url) {
        driverMobile.get(url);
    }
    // -------------------------- END OF METHODS -------------------------
}