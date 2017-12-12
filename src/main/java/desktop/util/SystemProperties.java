package desktop.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

public class SystemProperties {
    private static final Logger log = LoggerFactory.getLogger(SystemProperties.class);
    private static Properties properties = java.lang.System.getProperties();

    // -------------------------- OTHER METHODS --------------------------
    public static void set(String nomeProperties, String valorProperties) {
        clear(nomeProperties);
        java.lang.System.setProperty(nomeProperties, valorProperties);
        log.info("Gravando... " + nomeProperties + " = " + valorProperties);
    }

    public static String get(String nomeProperties) {
        String valor = properties.getProperty(nomeProperties);
        log.debug("Pegando valor... " + nomeProperties + " = " + valor);
        return valor;
    }

    public static void getResourcesProperties(String file) {
        try {
            InputStream resource = System.class.getClassLoader().getResourceAsStream(file);
            properties.load(resource);

        } catch (Exception e) {
            log.debug("Falha ao tentar ler as propriedades", e);
        }
    }

    public static void clear(String nomeProperites) {
        properties.remove(nomeProperites);
    }

}
