package mobileWeb.functions;

import app.core.AndroidDriverFactory;
import app.functions.FuntionsAndroidDriver;
import desktop.util.SystemProperties;
import mobileWeb.core.DriverMobileFactor;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FunctionsMobile extends DriverMobileFactor {

    // -------------------------- PROTECTED METHODS --------------------------


    protected void clicar(By locator) {
        checkReadyState();
        wait(locator);
        WebElement element = getElement(locator);
        highlight(element);
        element.click();
    }

    protected void clicarRandom(By locator) {
        checkReadyState();
        wait(locator);
        List<WebElement> optionClick = getElements(locator);
        WebElement optionSelected = optionClick.get(createRandomNumber(0, 2));
        highlight(optionSelected);
        optionSelected.click();
    }

    protected void preencher(By locator, String texto, Keys keys) {
        checkReadyState();
        wait(locator);
        WebElement element = getElement(locator);
        highlight(element);
        element.clear();
        element.sendKeys(texto);
        element.sendKeys(keys);
    }

    protected String listText(String... textos) {
        List<String> listTexto = new ArrayList<String>();
        for (String lista : textos)
            listTexto.add(lista);

        return listTexto.get(createRandomNumber(0, listTexto.size() - 1));
    }

    protected boolean existElement(By locator) {
        return getElements(locator).size() != 0;
    }

    protected String getText(By locator) {
        checkReadyState();
        wait(locator);
        return getElement(locator).getText();
    }

    protected void scrollDown(String px) {
        ((JavascriptExecutor) driverMobile).executeScript("window.scrollBy(0," + px + ")");
    }

    protected List<WebElement> getElements(By locator) {
        return driverMobile.findElements(locator);
    }

    // -------------------------- PRIVATE METHODS --------------------------
    private WebElement getElement(By locator) {
        return driverMobile.findElement(locator);
    }


    private void highlight(WebElement element) {
        if (!SystemProperties.get("Site").equals("NetShoesAr"))
            ((JavascriptExecutor) driverMobile).executeScript("arguments[0].style.border='4px solid red'", element);
    }

    private void checkReadyState() {
        if (!SystemProperties.get("Site").equals("NetShoesAr")) {
            Integer cont = 0;
            while (!((JavascriptExecutor) driverMobile).executeScript("return document.readyState").toString().equals("complete") && cont != 60) {
                esperar(500);
                cont++;
            }
        }
    }

    private void wait(By locator) {
        wait = new WebDriverWait(driverMobile, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private Integer createRandomNumber(Integer numeroInicial, Integer numeroFinal) {
        Random random = new Random();
        return random.nextInt(numeroFinal) + numeroInicial;
    }

    private static void esperar(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            System.out.println("Something given wrong, when I tried wait");
        }
    }

    // -------------------------- END OF METHODS --------------------------
}
