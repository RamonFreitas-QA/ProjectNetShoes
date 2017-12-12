package desktop.pageObjects;

import desktop.functions.FunctionsDesktop;
import desktop.util.SystemProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Busca extends FunctionsDesktop {

    // -------------------------- PUBLIC METHODS --------------------------
    public void clicarProdutoAleatorio() {
        clicarRandom(By.className("i"));
    }

    public void clicarCor() {
        if (existElement(By.cssSelector("#product-showcase-buy > div:nth-child(2) > ul > li.active")))
            clicarClickOptionNaoBloqueada(By.cssSelector(".product-item "));
    }

    public void clicarTamanho() {
        if (existElement(By.cssSelector(".product-item ")))
            clicarClickOptionNaoBloqueada(By.cssSelector(".product-item "));
    }

    public void clicarBotaoComprar() {
        clicar(By.cssSelector("#buy-button-now"));
    }

    public void gravarNomeProduto() {
        if (!SystemProperties.get("Site").equals("ShoeStock"))
            SystemProperties.set("ProdutoEscolhido", getText(By.cssSelector("div.description-box > div > h1")).substring(0, getText(By.cssSelector("div.description-box > div > h1")).indexOf("-") - 1));
        else
            SystemProperties.set("ProdutoEscolhido", getText(By.cssSelector("div.product-showcase-description > h1")).substring(0, getText(By.cssSelector("div.product-showcase-description > h1")).indexOf("-") - 1));
    }

    // -------------------------- PRIVATE METHODS --------------------------
    private void clicarClickOptionNaoBloqueada(By locator) {
        for (WebElement options : getElements((locator))) {
            if (!options.getAttribute("class").contains("unavailable ns-icon ns-icon-unavailable")) {
                options.click();
                break;
            }
        }
    }
    // -------------------------- END OF METHODS --------------------------

}