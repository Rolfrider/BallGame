package App;



import App.Config.Configuration;
import App.Windows.WelcomeWindow;

import javax.swing.*;
import java.awt.*;
import java.util.Properties;

public class WindowManager extends JFrame {

    private Configuration config = new Configuration();
    private Properties textProperties;
    private Properties settingsProperties;



    WindowManager(){
        super("Ball game");
        settingsProperties = config.getSettings();
        textProperties = config.getTextConfig(settingsProperties.getProperty("language"));
        JPanel panel = new WelcomeWindow(this);
        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(WindowManager::new);
    }

    public Properties getTextProperties() {
        return textProperties;
    }

    public Properties getSettingsProperties() {
        return settingsProperties;
    }

    public Configuration getConfig() {
        return config;
    }

    public void exit(){
        dispose();
        System.exit(0);
    }

    public void setWindow(JPanel panel){
        getContentPane().removeAll();
        add(panel);
        pack();
    }
}
