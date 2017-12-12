package mobileWeb.netShoesAr;


import desktop.util.SystemProperties;
import mobileWeb.functions.FunctionsMobile;
import mobileWeb.pageObjects.Busca;
import mobileWeb.pageObjects.Cart;
import mobileWeb.pageObjects.Home;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;

public class MobileWebBuscarProdutoNetShoesAr extends FunctionsMobile {
    // ------------------------------ FIELDS ------------------------------
    private static final Logger log = LoggerFactory.getLogger(MobileWebBuscarProdutoNetShoesAr.class);

    // -------------------------- OTHER METHODS --------------------------
    @Test
    public void testMobileWebBuscarProdutoNetShoesAr() {
        log.info("Buscar produto");
        pesquisarProduto();

        log.info("Incluir item na sacola");
        incluirItemSacola();

        log.info("Validar item na sacola");
        validarProdutoNaSacola();
    }

    // -------------------------- PRIVATE METHODS --------------------------
    private void pesquisarProduto() {
        Home home = new Home();
        home.preencherSearch();
    }

    private void incluirItemSacola() {
        Busca busca = new Busca();
        busca.clicarProdutoAleatorio();
        busca.gravarNomeProduto();
        busca.clicarTamanho();
        busca.clicarBotaoComprar();
    }

    private void validarProdutoNaSacola() {
        Cart cart = new Cart();
        cart.validarProdutoCarrinho(SystemProperties.get("ProdutoEscolhido"));
    }

    // -------------------------- END OF METHODS --------------------------
    @Before
    public void openBrowserMobileTest() throws MalformedURLException {
        SystemProperties.set("Site", "NetShoesAr");
        openBrowserMobile("Nexus 6 API 26", "Android", "8.0");
    }

    @After
    public void closeBrowserMobileTest() {
        closeBrowserMobile();
    }
}
