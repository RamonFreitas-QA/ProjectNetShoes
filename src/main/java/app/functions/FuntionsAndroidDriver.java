package app.functions;

import app.core.AndroidDriverFactory;
import desktop.util.SystemProperties;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidKeyCode;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FuntionsAndroidDriver extends AndroidDriverFactory {
    // ------------------------------ FIELDS ------------------------------
    private static final Logger log = LoggerFactory.getLogger(FuntionsAndroidDriver.class);
    protected static TouchAction touchAction;

    protected FuntionsAndroidDriver() {
        SystemProperties.getResourcesProperties("Config/Devices.properties");
    }

    // -------------------------- PROTECTED METHODS -------------------------
    protected void click(By locator, String opcao) {
        setWait(locator);
        List<WebElement> element = driver.findElements(locator);

        for (int i = 0; i < element.size(); i++) {
            if (element.get(i).getText().contains(opcao)) {
                element.get(i).click();
                break;
            }
            if (element.size() == i + 1) element.get(0).click();
        }
    }

    protected void clickRandom(By locator, Integer numInitial, Integer numFinal) {
        setWait(locator);
        List<WebElement> element = driver.findElements(locator);
        element.get(createRandomNumber(numInitial, numFinal)).click();
    }

    protected void waiting(Integer milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void click(By locator) {
        setWait(locator);
        WebElement element = getElement(locator);
        element.click();
    }

    protected void fill(By locator, String texto) {
        setWait(locator);
        WebElement element = getElement(locator);
        element.sendKeys(texto);
    }

    protected void touch(String typeMovement, Integer x, Integer y, Integer endx, Integer endy, Integer quantity) {
        touchAction = new TouchAction(driver);

        if ("swipe".equals(typeMovement)) {
            for (int i = 1; i <= quantity; i++) {
                if (x != null) touchAction.longPress(x, y).moveTo(endx, endy).release().perform();
                waiting(500);
            }

        } else if ("tap".equals(typeMovement)) {
            for (int i = 1; i <= quantity; i++) {
                touchAction.tap(x, y).release().perform();
                waiting(500);
            }
        }
    }

    protected void androidBack() {
        driver.pressKeyCode(AndroidKeyCode.BACK);
    }

    protected void androidEnter() {
        driver.pressKeyCode(AndroidKeyCode.ENTER);
    }

    protected void androidQuadrate() {
        driver.pressKeyCode(AndroidKeyCode.KEYCODE_APP_SWITCH);
    }

    protected List<WebElement> getElements(By locator) {
        return driver.findElements(locator);
    }

    protected boolean existElement(By locador, Integer timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(locador)) != null;
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean existElemets(By locator) {
        return driver.findElements(locator).size() != 0;
    }

    protected String getText(By locator) {
        setWait(locator);
        return driver.findElement(locator).getText();
    }

    protected void clicarTamanho() {
        retornarElementos();
        List<WebElement> listaOpcoes = driver.findElements(By.id("br.com.netshoes.app:id/attribute_text"));

        for (WebElement element : listaOpcoes) {
            if (!existElemets(By.id("br.com.netshoes.app:id/attribute_text"))) {
                touch("swipe", 5, 1200, 5, 10, 1);
            }
            for (String tamanhos : listText("EGG", "GG", "G", "M", "P", "14A", "12A", "10A", "8A")) {
                if (element.getText().contains(tamanhos)) {
                    element.click();
                    retornarElementos();
                    break;
                }
            }
            if (getText(By.id("br.com.netshoes.app:id/progress_button_text")).contains("COMPRAR"))
                break;
        }
    }

    protected void retornarElementos() {
        waiting(1000);
        androidQuadrate();
        waiting(2000);
        androidQuadrate();
    }

    protected void screenShotAndroid() {
        Integer countScreenshot = 0;
        File file = null;
        try {
            while (countScreenshot++ <= 100) {
                file = new File("target/" + SystemProperties.get("AppName"), SystemProperties.get("AppName") + "_" + countScreenshot + "_" + ".png");
                if (!file.exists()) {
                    file = new File("target/" + SystemProperties.get("AppName"), SystemProperties.get("AppName") + "_" + countScreenshot + "_" + ".png");
                    break;
                }
            }
            File srcFile = driver.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(srcFile, file);
        } catch (Exception e) {
            log.info("Something given wrong, when I try get the screeshot");
        }
    }

    // -------------------------- PRIVATE METHODS -------------------------
    private Integer createRandomNumber(Integer numeroInicial, Integer numeroFinal) {
        Random random = new Random();
        return random.nextInt(numeroFinal) + numeroInicial;
    }

    private void setWait(By locador) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locador));
    }

    private List<String> listText(String... textos) {
        List<String> listTexto = new ArrayList<String>();
        for (String lista : textos) {
            listTexto.add(lista);
        }
        return listTexto;
    }

    private WebElement getElement(By locator) {
        return driver.findElement(locator);
    }
    // -------------------------- END OF METHODS -------------------------
}
