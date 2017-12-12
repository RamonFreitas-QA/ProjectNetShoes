package app;

import app.functions.FuntionsAndroidDriver;
import desktop.util.SystemProperties;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.util.List;

public class AplicativoNetShoes extends FuntionsAndroidDriver {

    // ------------------------------ FIELDS ------------------------------
    private static final Logger log = LoggerFactory.getLogger(AplicativoNetShoes.class);

    // -------------------------- OTHER METHODS --------------------------
    @Test
    public void testAplicativoNetShoes() {
        log.info("Limpando fila de app's abertos");
        clearOpenApps();

        log.info("Open NetShoes");
        openNetShoes();

        log.info("Limpando carrinho");
        clearCart();

        log.info("Buscando Camisa da Alemanha");
        findingGermanyShirt();

        log.info("Clicar produto aleatório");
        clickRandomProduct();

        log.info("Gravando nome produto carrinho");
        getProductName();

        log.info("Incluindo produto e acessando carrinho");
        insertProductAndAcessCart();

        log.info("Validando produto no carrinho");
        checkIfProductInCart();
    }

    // -------------------------- PRIVATE METHODS --------------------------
    private void clearOpenApps() {
        androidQuadrate();
        if (existElement(By.id("com.android.systemui:id/dismiss_task"), 3)) {
            while (existElement(By.id("com.android.systemui:id/dismiss_task"), 3))
                click(By.id("com.android.systemui:id/dismiss_task"));
        } else androidQuadrate();
    }

    private void openNetShoes() {
        click(By.className("android.widget.TextView"), "Netshoes");
        if (existElement(By.id("br.com.netshoes.app:id/generic_dialog_positive"), 3))
            click(By.id("br.com.netshoes.app:id/generic_dialog_positive"));
    }

    private void clearCart() {
        click(By.id("br.com.netshoes.app:string/cart_title"));
        waiting(2000);
        retornarElementos();

        if (existElemets(By.id("br.com.netshoes.app:id/ctvCartRemove"))) {
            List<WebElement> listRemove = getElements(By.id("br.com.netshoes.app:id/ctvCartRemove"));
            for (WebElement remove : listRemove) {
                remove.click();
                click(By.id("br.com.netshoes.app:id/material_dialog_positive"));
                retornarElementos();
            }
        }
        waiting(2000);
        androidBack();
        waiting(2000);
    }

    private void findingGermanyShirt() {
        click(By.id("br.com.netshoes.app:id/search_button"));
        fill(By.id("br.com.netshoes.app:id/search_src_text"), "Camiseta Alemanha");
        androidEnter();

        if (existElement(By.id("br.com.netshoes.app:id/search_src_text"), 3))
            click(By.id("br.com.netshoes.app:id/search_src_text"));
    }

    private void clickRandomProduct() {
        clickRandom(By.id("br.com.netshoes.app:id/product_name"), 0, 4);
        waiting(2000);
        touch("swipe", 5, 1200, 5, 10, 1);
        clicarTamanho();
    }

    private void getProductName() {
        SystemProperties.set("ProdutoNome", getText(By.id("br.com.netshoes.app:id/product_name")));
    }

    private void insertProductAndAcessCart() {
        click(By.id("br.com.netshoes.app:id/progress_button_text"));
        click(By.id("br.com.netshoes.app:id/dialog_stamps_cart"));
    }

    private void checkIfProductInCart() {
        retornarElementos();
        Assert.assertTrue("O produto " + SystemProperties.get("ProdutoNome") + " não foi adiciona ao carrinho", getText(By.id("br.com.netshoes.app:id/tvProductTitle")).contains(SystemProperties.get("ProdutoNome")));
        screenShotAndroid();
        waiting(5000);
    }

    // -------------------------- END OF METHODS --------------------------
    @Before
    public void openCelphoneMobileTester() throws MalformedURLException {
        SystemProperties.set("AppName", "ApplicativoNetShoes");
        openMobileDriver("Nexus 6 API 26", "emulator-5554", "Android", "8.0", "com.google.android.apps.nexuslauncher", "com.google.android.apps.nexuslauncher.NexusLauncherActivity");
    }

    @After()
    public void closeMobileDriverTest() {
        closeMobileDriver();
    }
}
