package App;



import App.Config.Configuration;
import App.Windows.WelcomeWindow;

import javax.swing.*;
import java.awt.*;
import java.util.Properties;

public class WindowManager extends JFrame {

    private Properties textProperties = Configuration.getTextConfig();


    WindowManager(){
        super("Ball game");
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


    public void setWindow(JPanel panel){
        getContentPane().removeAll();
        add(panel);
        pack();
    }
}
