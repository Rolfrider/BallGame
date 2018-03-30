package App.Config;

import java.io.*;
import java.util.Properties;

/**
 *
 */
public class Configuration {
    static String path = "src/Config/";

    public static Properties getTextConfig(){
        Properties properties = new Properties();
        try{
            InputStream iStream = new FileInputStream(path + "text-config.xml");
            properties.loadFromXML(iStream);
            iStream.close();
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return properties;
    }

    /**
     * @param properties
     */
    public static void saveTextConfig(Properties properties){
        try{
            OutputStream oStream = new FileOutputStream(path + "text-config.xml");
            properties.storeToXML(oStream, "text config");
            oStream.close();
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
