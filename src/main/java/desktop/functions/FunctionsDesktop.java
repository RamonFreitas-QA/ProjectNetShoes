package desktop.functions;

import desktop.core.WebdriverFactor;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FunctionsDesktop extends WebdriverFactor {

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

    protected void mouseMove(By locator) {
        Actions action = new Actions(driver);
        action.moveToElement(getElement(locator)).build().perform();
    }

    protected List<WebElement> getElements(By locator) {
        return driver.findElements(locator);
    }

    // -------------------------- PRIVATE METHODS --------------------------
    private WebElement getElement(By locator) {
        return driver.findElement(locator);
    }

    private void highlight(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='4px solid red'", element);
    }

    private void checkReadyState() {
        Integer cont = 0;
        while (!((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete") && cont != 60) {
            esperar(500);
            cont++;
        }
    }

    private void wait(By locator) {
        wait = new WebDriverWait(driver, 30);
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
