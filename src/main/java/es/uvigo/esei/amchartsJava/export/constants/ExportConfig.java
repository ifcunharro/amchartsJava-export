package es.uvigo.esei.amchartsJava.export.constants;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.Paths;
import java.util.Properties;

import es.uvigo.esei.amchartsJava.core.constants.lang.I18n;

/**
 * 
 * This class load properties with params config.
 * @author Iago Fernández Cuñarro
 *
 */
public final class ExportConfig {

    private static final Properties EXPORTCONFIG = new Properties();
    
    private ExportConfig(){
    	
    }

    static {
    		
			try {
				//load url properties independent mode debug/release
				final String OS = System.getProperty("os.name").toLowerCase();
				URL localURL;
				if(OS.indexOf("win")>=0){
					localURL = new URL(URLDecoder.decode("file:/"+Paths.get(".").toAbsolutePath().normalize().toString() + "\\resources\\export.properties","UTF-8" ));
				}
				else{
					localURL = new URL(URLDecoder.decode("file:"+Paths.get(".").toAbsolutePath().normalize().toString() + "/resources/export.properties" ,"UTF-8"));
					
				}
				try {
					EXPORTCONFIG.load(localURL.openStream());
				} catch (IOException e) {
					throw new RuntimeException(
							I18n.get("propertiesException"));
				}
			} catch (MalformedURLException | UnsupportedEncodingException URLException) {
				throw new RuntimeException(
						I18n.get("propertiesException"));
			}
		
	}

    /**
     * Get parameter of configuration.
     * @param key Name of parameter of configuration.
     * @return String Value asocciated to parameter of configuration.
     */
    public static String getString(final String key) {
        if (EXPORTCONFIG.containsKey(key)){
            return EXPORTCONFIG.getProperty(key);
        }

        throw new RuntimeException(
            "'" + key + "' "+I18n.get("ConfigKeyException")
        );
    }
    
}