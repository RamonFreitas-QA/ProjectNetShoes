package mobileWeb.pageObjects;

import desktop.util.SystemProperties;
import mobileWeb.functions.FunctionsMobile;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Busca extends FunctionsMobile {

    // -------------------------- PUBLIC METHODS --------------------------
    public void clicarProdutoAleatorio() {
        clicarRandom(By.className("i"));
    }

    public void clicarTamanho() {
        if (existElement(By.name("sku-select-size"))) {
            if (existElement(By.cssSelector("#product-showcase > div.product-buy-box > div:nth-child(4) > label"))) {
                clicar(By.cssSelector("#product-showcase > div.product-buy-box > div:nth-child(4) > label"));

                if (SystemProperties.get("Site").equals("Zattini"))
                    clicarClickOptionNaoBloqueada(By.cssSelector("body > div.ns-slide-push > div > ul > li.ns-checkbox"), "Indisponível");

                if (SystemProperties.get("Site").equals("NetShoesAr"))
                    clicarClickOptionNaoBloqueada(By.cssSelector("body > div.ns-slide-push > div > ul > li.ns-checkbox"), "No disponible");

                if (existElement(By.cssSelector("body > div.ns-slide-push > div > ul > li > label")) && SystemProperties.get("Site").equals("ShoeStock"))
                    clicar(By.cssSelector("body > div.ns-slide-push > div > ul > li > label"));
            }
        }

        if (existElement(By.name("sku-select-size")) && !SystemProperties.get("Site").equals("Zattini"))
            if (existElement(By.cssSelector("#product-showcase > div.product-buy-box > div:nth-child(3) > label.ns-sku-selector.setted")))
                if (getText(By.cssSelector("#product-showcase > div.product-buy-box > div:nth-child(3) > label.ns-sku-selector.setted")).contains("Único")) {
                    clicar(By.cssSelector("#product-showcase > div.product-buy-box > div:nth-child(3) > label.ns-sku-selector.setted"));
                    if (SystemProperties.get("Site").equals("ShoeStock"))
                        getElements(By.className("android.view.View"));
                    else
                        clicar(By.cssSelector(".ns-checkbox default checked"));
                }

        if (existElement(By.name("sku-select-size")) && !SystemProperties.get("Site").equals("Zattini")) {
            if (existElement(By.name("sku-select-size"))) {
                clicar(By.cssSelector("#product-showcase > div.product-buy-box > div:nth-child(3) > label.ns-sku-selector"));
                if (SystemProperties.get("Site").equals("NetShoesAr"))
                    clicarClickOptionNaoBloqueada(By.cssSelector(".ns-checkbox"), "No disponible");
                else
                    clicarClickOptionNaoBloqueada(By.cssSelector(".ns-checkbox"), "Indisponível");

            }
        }
    }

    public void clicarBotaoComprar() {
        if (SystemProperties.get("Site").equals("NetShoesAr")) {
            Integer contador = 0;
            while (existElement(By.id("buy-button-now")) && contador++ <= 3)
                scrollDown("500");
        }

        clicar(By.id("buy-button-now"));
    }

    public void gravarNomeProduto() {
        if (!SystemProperties.get("Site").equals("ShoeStock"))
            SystemProperties.set("ProdutoEscolhido", getText(By.cssSelector("div.product-buy-box > div:nth-child(1) > div.product-showcase-title-stamps > h1")).substring(0, getText(By.cssSelector("div.product-buy-box > div:nth-child(1) > div.product-showcase-title-stamps > h1")).indexOf("-") - 1));
        else
            SystemProperties.set("ProdutoEscolhido", getText(By.cssSelector("div.product-showcase-description > h1")).substring(0, getText(By.cssSelector("div.product-showcase-description > h1")).indexOf("-") - 1));
    }

    // -------------------------- PRIVATE METHODS --------------------------
    private void clicarClickOptionNaoBloqueada(By locator, String opcao) {
        for (WebElement options : getElements((locator))) {
            if (options.isDisplayed())
                if (!options.getText().contains(opcao)) {
                    options.click();
                    break;
                }
        }
    }
    // -------------------------- END OF METHODS --------------------------
}