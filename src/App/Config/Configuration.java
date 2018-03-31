package App.Config;

import java.io.*;
import java.util.Properties;

/**
 *
 */
public class Configuration {
    private final String TEXT_PATH = "src/Config/Text/";
    private final String SETTINGS_PATH = "src/Config/settings-config.xml";

    public  Properties getTextConfig(String language){
        Properties properties = new Properties();
        try{
            InputStream iStream = new FileInputStream(TEXT_PATH + language +".xml");
            properties.loadFromXML(iStream);
            iStream.close();
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return properties;
    }

    public Properties getSettings(){
        Properties properties = new Properties();
        try{
            InputStream iStream = new FileInputStream(SETTINGS_PATH);
            properties.loadFromXML(iStream);
            iStream.close();
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return properties;
    }

    public void saveSettings(Properties properties){
        try{
            OutputStream oStream = new FileOutputStream(SETTINGS_PATH);
            properties.storeToXML(oStream, "settings config");
            oStream.close();
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * @param properties
     */
    public  void saveTextConfig(Properties properties){
        try{
            OutputStream oStream = new FileOutputStream(TEXT_PATH + "english.xml");
            properties.storeToXML(oStream, "text config");
            oStream.close();
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
