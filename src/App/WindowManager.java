package App;



import javax.swing.*;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class WindowManager extends JFrame {

    private Properties textProperties = new Properties();


    WindowManager(){
        super("Ball game");
        JPanel gamePanel = new Welcome();
        add(gamePanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }


    public static void main(String[] args) {
        EventQueue.invokeLater(WindowManager::new);
    }
}
