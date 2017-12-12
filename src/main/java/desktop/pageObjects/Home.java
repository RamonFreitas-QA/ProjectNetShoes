package desktop.pageObjects;

import desktop.functions.FunctionsDesktop;
import desktop.util.SystemProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class Home extends FunctionsDesktop {

    // -------------------------- PUBLIC METHODS --------------------------
    public void preencherSearch() {
        preencher(By.cssSelector("#search-input"), opcoesBusca(), Keys.ENTER);
    }

    public void verificarItensSacola() {
        mouseMove(By.cssSelector("#minicart"));
        if (existElement(By.cssSelector(".btn-buy")))
            clicar(By.cssSelector("#minicart"));
    }
    // -------------------------- PRIVATE METHODS --------------------------
    private String opcoesBusca() {
        String opcao = "";
        if (SystemProperties.get("Site").equals("Zattini"))
            opcao = listText("Adidas", "Camisas", "Vestidos", "Sapatos", "Calças");

        if (SystemProperties.get("Site").equals("ShoeStock"))
            opcao = listText("Botas", "Sapato social", "Acessorios", "Bolsas", "Tenis");

        if (SystemProperties.get("Site").equals("NetShoesAr"))
            opcao = listText("Remeras", "Camiseta Argentina", "Vestidos", "Calzados", "pelotas");

        return opcao;
    }
    // -------------------------- END OF METHODS --------------------------
}
