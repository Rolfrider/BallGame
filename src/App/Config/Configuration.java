package App.Config;

import java.io.*;
import java.util.Properties;

/** Stores configuration information
 */
public class Configuration {
    private final String TEXT_PATH = "src/Config/Text/";
    private final String SETTINGS_PATH = "src/Config/";
    private final String SCORES_PATH = "src/Config/Scores/";


    /**
     * Loads configuration from xml file
     */
    public Properties load(String filename){
        Properties properties = new Properties();
        try{
            InputStream iStream = new FileInputStream(filename);
            properties.loadFromXML(iStream);
            iStream.close();
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return properties;
    }

    public void save(Properties properties,String filename){
        try{
            OutputStream oStream = new FileOutputStream(filename);
            properties.storeToXML(oStream, "game data");
            oStream.close();
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
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
    public Properties getScores(String filename){
        return load(SCORES_PATH + filename + ".xml");
    }


    /**
     * Loads settings from xml file
     * @return object containing properties
     */
    public Properties getSettings(){

        return load(SETTINGS_PATH + "settings-config.xml");
    }

    /**
     * Saves settings to xml file
     * @param properties
     */
    public void saveSettings(Properties properties){
        save(properties,SETTINGS_PATH + "settings-config.xml");
    }


    /**
     * Saves settings to xml file
     * @param properties
     */
    public  void saveTextConfig(Properties properties){
        save(properties, TEXT_PATH + "english.xml");
    }


    public void saveScores(Properties scores, String filename) {
        save(scores, SCORES_PATH + filename + ".xml");
    }
}
