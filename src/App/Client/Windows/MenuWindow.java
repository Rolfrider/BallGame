package App.Client.Windows;

import App.Client.WindowManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public abstract class MenuWindow extends CustomWindow {

    protected JLabel titleLabel;
    protected ArrayList<JButton> buttons = new ArrayList<>();

    public MenuWindow(){
        setLayout(new GridBagLayout());
    }

    public MenuWindow(WindowManager windowParent) {
        super(windowParent);
        setLayout(new GridBagLayout());
        initButtons();
        buttonsLook();
        initLabel();
        placeComponents();
        startRaining();
    }

    protected void buttonsLook(){
        for (JButton b : buttons) {
            b.setForeground(Color.BLUE);
            b.setOpaque(false);
            b.setContentAreaFilled(false);
            b.setBorderPainted(false);
        }
    }

    protected abstract void placeComponents();

    protected abstract void initButtons();

    protected abstract void initLabel();

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(500,600);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        // Resize the text on buttons
        int fontSize = (getSize().height + getSize().width)/40;
        for (JButton b : buttons) {
            b.setFont(new Font(Font.SERIF,Font.ITALIC, fontSize));
        }
        titleLabel.setFont(new Font(Font.SERIF, Font.BOLD, fontSize*3));
    }
}
