package App.Client;


// TODO : wczytywanie z pliku jak serwer zwróci null, sortowanie przed wyswietleniem


import App.Config.Configuration;
import App.Client.Windows.WelcomeWindow;

import javax.swing.*;
import java.awt.*;
import java.util.Properties;

public class WindowManager extends JFrame {

    private Configuration config = new Configuration();
    private Properties textProperties;
    private Properties settingsProperties;
    private String username;
    private Client client;

    /**
     * Manages windows and their funcionalities
     */
    public WindowManager(){
        super("Ball game");
        settingsProperties = config.getSettings();
        textProperties = config.getTextConfig(settingsProperties.getProperty("language"));
        JPanel panel = new WelcomeWindow(this);
        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        username = usernameDialog();
    }

    public String usernameDialog(){
        return JOptionPane.showInputDialog(
                this,
                textProperties.getProperty("dialog question"),
                textProperties.getProperty("dialog title"),
                JOptionPane.PLAIN_MESSAGE
        );
    }

    public void dialog(String massage){
        JOptionPane.showMessageDialog(this, massage);
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

    /**
     * Closes window and close the application
     */
    public void exit(){
        dispose();
        System.exit(0);
    }

    public Client getClient() {
        return client;
    }

    public void setWindow(JPanel panel){
        getContentPane().removeAll();
        add(panel);
        panel.requestFocusInWindow();
        pack();
    }
}
