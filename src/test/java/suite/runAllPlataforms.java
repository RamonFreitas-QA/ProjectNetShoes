package suite;

import app.AplicativoNetShoes;
import mobileWeb.netShoesAr.MobileWebBuscarProdutoNetShoesAr;
import mobileWeb.shoeStock.MobileWebBuscarProdutoShoeStock;
import mobileWeb.zattini.MobileWebBuscarProdutoZattini;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import desktop.netShoesAr.DesktopBuscarIncluirProdutoCarrinhoNetShoesAr;
import desktop.shoeStock.DesktopBuscarIncluirProdutoCarrinhoShoesStock;
import desktop.zattini.DesktopBuscarIncluirProdutoCarrinhoZattini;

@RunWith(Suite.class)
@Suite.SuiteClasses(value = {

        //app NetShoes:
        AplicativoNetShoes.class,

        //mobileWeb Web:
        MobileWebBuscarProdutoNetShoesAr.class,
        MobileWebBuscarProdutoShoeStock.class,
        MobileWebBuscarProdutoZattini.class,

        //desktop:
        DesktopBuscarIncluirProdutoCarrinhoZattini.class,
        DesktopBuscarIncluirProdutoCarrinhoShoesStock.class,
        DesktopBuscarIncluirProdutoCarrinhoNetShoesAr.class
})

public class runAllPlataforms {
}
