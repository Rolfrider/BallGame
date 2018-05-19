package App.Config;

import java.io.*;
import java.util.Properties;

/** Stores configuration information
 */
public class Configuration {
    private final String TEXT_PATH = "src/Config/Text/";
    private final String SETTINGS_PATH = "src/Config/settings-config.xml";
    private final String SCORES_PATH = "src/Config/Scores/";


    /**
     * Loads configuration from xml file
     */
    public Properties load(String fileName){
        Properties properties = new Properties();
        try{
            InputStream iStream = new FileInputStream(fileName);
            properties.loadFromXML(iStream);
            iStream.close();
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return properties;
    }


    /**
     * Loads configuration from xml file
     * @param language
     * @return object containing properties
     */
    public Properties getTextConfig(String language){

        return load(TEXT_PATH + language +".xml");
    }

    /**
     * Loads scoreboard from xml file
     * @return object containing properties
     */
    public Properties getScores(String fileName){
        return load(SCORES_PATH + fileName + ".xml");
    }


    /**
     * Loads settings from xml file
     * @return object containing properties
     */
    public Properties getSettings(){

        return load(SETTINGS_PATH);
    }

    /**
     * Saves settings to xml file
     * @param properties
     */
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
     * Saves settings to xml file
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
