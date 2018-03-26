package App;



import javax.swing.*;
import java.awt.*;

public class WindowManager extends JFrame {
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
