package desktop.pageObjects;

import desktop.functions.FunctionsDesktop;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.slf4j.LoggerFactory;

public class Cart extends FunctionsDesktop {

    // ------------------------------ FIELDS ------------------------------
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(FunctionsDesktop.class);

    // -------------------------- PUBLIC METHODS --------------------------
    public void validarProdutoCarrinho(String produtoTexto) {
        Assert.assertTrue("O produto " + produtoTexto + " não foi adicionado na sacola!", getText(By.cssSelector(".product-info")).contains(produtoTexto));
        log.info("O produto " + produtoTexto + " foi adionado na sacola com sucesso!");
    }
    // -------------------------- END OF METHODS --------------------------
}
