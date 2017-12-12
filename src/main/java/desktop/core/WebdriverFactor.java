package desktop.core;

import desktop.functions.FunctionsDesktop;
import desktop.util.SystemProperties;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;

import java.io.File;

public class WebdriverFactor {
    // ------------------------------ FIELDS ------------------------------
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(FunctionsDesktop.class);
    protected static WebDriver driver;
    protected WebDriverWait wait;

    // -------------------------- PROTECTED METHODS --------------------------
    protected static void openBrowser(String browser, String site) {

        if ("Zattini".equals(site)) {
            SystemProperties.set("Site", "Zattini");
            SystemProperties.set("url", "https://www.zattini.com.br/");
        }

        if ("ShoeStock".equals(site)) {
            SystemProperties.set("Site", "ShoeStock");
            SystemProperties.set("url", "https://www.shoestock.com.br");
        }

        if ("NetShoesAr".equals(site)) {
            SystemProperties.set("Site", "NetShoesAr");
            SystemProperties.set("url", "https://www.netshoes.com.ar");
        }

        if (browser.equals("GoogleChrome"))
            chromeBrowser();

        if (browser.equals("Firefox"))
            firefoxBrowser();

        driver.manage().window().maximize();
        driver.get(SystemProperties.get("url"));
    }

    // -------------------------- PRIVATE METHODS --------------------------
    private static void chromeBrowser() {
        ChromeDriverManager.getInstance().forceCache().setup();
        driver = new ChromeDriver();
    }

    private static void firefoxBrowser() {
        ChromeDriverManager.getInstance().forceCache().setup();
        driver = new ChromeDriver();
    }

    protected void closeDriver() {
        screenShot();
        driver.quit();
    }

    private void screenShot() {
        Integer countScreenshot = 0;
        File file = null;
        try {
            while (countScreenshot++ <= 100) {
                file = new File("target/screenShot/" + SystemProperties.get("Site"), getClass().getSimpleName() + "_" + countScreenshot + "_" + ".png");
                if (!file.exists()) {
                    file = new File("target/screenShot/" + SystemProperties.get("Site"), getClass().getSimpleName() + "_" + countScreenshot + "_" + ".png");
                    break;
                }
            }
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(srcFile, file);
            log.info("ScreenShot: " + file);
        } catch (Exception e) {
            log.info("Something given wrong, when I tried get the screeshot");
        }
    }
    // -------------------------- END OF METHODS --------------------------
}
