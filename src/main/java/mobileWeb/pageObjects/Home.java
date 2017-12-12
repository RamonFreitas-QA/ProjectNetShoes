package mobileWeb.pageObjects;

import desktop.util.SystemProperties;
import io.appium.java_client.android.AndroidKeyCode;
import mobileWeb.functions.FunctionsMobile;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.security.Key;

public class Home extends FunctionsMobile {

    // -------------------------- PUBLIC METHODS --------------------------
    public void preencherSearch() {
        preencher(By.cssSelector("#search-input"), opcoesBusca(), Keys.ENTER);
    }

    // -------------------------- PRIVATE METHODS --------------------------
    private String opcoesBusca() {
        String opcao = "";
        if (SystemProperties.get("Site").equals("Zattini"))
            opcao = "Calças";

        if (SystemProperties.get("Site").equals("ShoeStock"))
            opcao ="Palmilha";

        if (SystemProperties.get("Site").equals("NetShoesAr"))
            opcao = "Camiseta Argentina";

        return opcao;
    }
    // -------------------------- END OF METHODS --------------------------

}
