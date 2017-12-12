package mobileWeb.pageObjects;

import desktop.functions.FunctionsDesktop;
import desktop.util.SystemProperties;
import mobileWeb.functions.FunctionsMobile;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.slf4j.LoggerFactory;

public class Cart extends FunctionsMobile {

    // ------------------------------ FIELDS ------------------------------
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(FunctionsDesktop.class);

    // -------------------------- PUBLIC METHODS --------------------------
    public void validarProdutoCarrinho(String produtoTexto) {
        if (SystemProperties.get("Site").equals("Zattini"))
            Assert.assertTrue("O produto " + produtoTexto + " não foi adicionado na sacola!", getText(By.cssSelector("#cart-mobileWeb-content > ul > li > div.product-holder > div.content-holder > div.product-details > strong")).contains(produtoTexto));

        if (SystemProperties.get("Site").equals("NetShoesAr"))
            Assert.assertTrue("O produto " + produtoTexto + " não foi adicionado na sacola!", getText(By.cssSelector("#cart-mobile-content > ul > li > div.product-holder > div.content-holder > div.product-details > strong")).contains(produtoTexto));

        if (SystemProperties.get("Site").equals("ShoeStock"))
            if (existElement(By.cssSelector("#cart-mobile-content > ul > li > div.product-holder > div.product-details > strong")))
                Assert.assertTrue("O produto " + produtoTexto + " não foi adicionado na sacola!", getText(By.cssSelector("#cart-mobile-content > ul > li > div.product-holder > div.product-details > strong")).contains(produtoTexto));
            else
                Assert.assertTrue("O produto " + produtoTexto + " não foi adicionado na sacola!", getText(By.cssSelector("#cart-mobileWeb-content > ul > li > div.product-holder > div.product-details > strong")).contains(produtoTexto));


        log.info("O produto " + produtoTexto + " foi adionado na sacola com sucesso!");
    }
    // -------------------------- END OF METHODS --------------------------
}
