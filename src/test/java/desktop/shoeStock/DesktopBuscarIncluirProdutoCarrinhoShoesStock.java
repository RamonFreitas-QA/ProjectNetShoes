package desktop.shoeStock;

import desktop.functions.FunctionsDesktop;
import desktop.util.SystemProperties;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import desktop.pageObjects.Busca;
import desktop.pageObjects.Cart;
import desktop.pageObjects.Home;

public class DesktopBuscarIncluirProdutoCarrinhoShoesStock extends FunctionsDesktop {

    // ------------------------------ FIELDS ------------------------------
    private static final Logger log = LoggerFactory.getLogger(DesktopBuscarIncluirProdutoCarrinhoShoesStock.class);

    // -------------------------- OTHER METHODS --------------------------
    @Test
    public void testDesktopBuscarIncluirProdutoCarrinhoShoesStock() {
        log.info("Buscar produto");
        pesquisarProduto();

        log.info("Clicar produto aleatório");
        clicarProdutoAleatorio();

        log.info("Validar produto na sacola");
        validarProdutoNaSacola();
    }

    // -------------------------- PRIVATE METHODS --------------------------
    private void pesquisarProduto() {
        Home home = new Home();
        home.verificarItensSacola();
        home.preencherSearch();
    }

    private void clicarProdutoAleatorio() {
        Busca busca = new Busca();
        busca.clicarProdutoAleatorio();
        busca.gravarNomeProduto();
        busca.clicarCor();
        busca.clicarTamanho();
        busca.clicarBotaoComprar();
    }

    private void validarProdutoNaSacola() {
        Cart cart = new Cart();
        cart.validarProdutoCarrinho(SystemProperties.get("ProdutoEscolhido"));
    }

    // -------------------------- END OF OTHER METHODS --------------------------
    @Before
    public void openBrowserAcessarZattini() {
        log.info("Abrir Chrome e acessar ShoeStock");
        openBrowser("GoogleChrome", "ShoeStock");
    }

    @After
    public void closeBrowser() {
        closeDriver();
    }
}
