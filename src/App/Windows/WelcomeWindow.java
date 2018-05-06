package App.Windows;

import App.WindowManager;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;

public class WelcomeWindow extends JPanel {
    private ArrayList<JButton> buttons = new ArrayList<>();
    private JLabel titleLabel ;
    private JLabel levelLabel ;
    private WindowManager windowParent;



    public WelcomeWindow(WindowManager windowParent){
        super();
        this.windowParent = windowParent;

        setBackground(Color.BLACK);
        setLayout(new GridBagLayout());

        initButtons();
        initLabel();
        placeComponents();

    }

    private void placeComponents() {
        GridBagConstraints bagConstraints = new GridBagConstraints();
        /* How to understand what is happening here :
         * https://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html*/

        //Title setup
        bagConstraints.gridx = 1;
        bagConstraints.gridy = 0;
        bagConstraints.gridheight = 2;
        bagConstraints.gridwidth = 3;
        bagConstraints.weighty = 1.0;
        bagConstraints.anchor = GridBagConstraints.PAGE_START;

        add(titleLabel, bagConstraints);

        //Other components setup
        bagConstraints.gridy = 3;
        bagConstraints.gridheight = 1;
        bagConstraints.anchor = GridBagConstraints.CENTER;

        add(buttons.get(0), bagConstraints);

        bagConstraints.gridy = 4;

        add(buttons.get(1), bagConstraints);

        bagConstraints.gridy = 5;

        add(buttons.get(2), bagConstraints);

        bagConstraints.gridy = 6;

        add(buttons.get(3), bagConstraints);

        bagConstraints.gridy = 7;

        add(levelLabel, bagConstraints);
    }

    private void initLabel() {
        titleLabel = new JLabel(windowParent.getTextProperties().getProperty("game title"));
        titleLabel.setForeground(Color.WHITE);

        String levelVal = "";

        switch (windowParent.getSettingsProperties().getProperty("level")){
            case "1":
                levelVal = windowParent.getTextProperties().getProperty("easy level");
                break;
            case "2":
                levelVal = windowParent.getTextProperties().getProperty("medium level");
                break;
            case "3":
                levelVal = windowParent.getTextProperties().getProperty("hard level");
                break;
        }

        levelLabel = new JLabel(windowParent.getTextProperties().getProperty("level label")
        + " : " + levelVal );



        levelLabel.setForeground(Color.GRAY);
    }

    private void initButtons(){
        buttons.add(new JButton(windowParent.getTextProperties().getProperty("play label")));
        buttons.add(new JButton(windowParent.getTextProperties().getProperty("scoreboard label")));
        buttons.add(new JButton(windowParent.getTextProperties().getProperty("option label")));
        buttons.add(new JButton(windowParent.getTextProperties().getProperty("exit label")));
        buttons.get(0).addActionListener( actionEvent -> windowParent.setWindow(new GameWindow(windowParent)));
        buttons.get(1).addActionListener( actionEvent -> windowParent.setWindow(new ScoreboardWindow(windowParent)));
        buttons.get(2).addActionListener( actionEvent -> windowParent.setWindow(new OptionWindow(windowParent)));
        buttons.get(3).addActionListener( actionEvent -> windowParent.exit());

        for (JButton b : buttons) {
            b.setBackground(Color.BLACK);
            b.setForeground(Color.RED);
            b.setOpaque(true);
            b.setBorderPainted(false);
        
        }

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(500,600);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        // Resize the text on buttons 
        int fontSize = (getSize().height + getSize().width)/40;
        for (JButton b : buttons) {
            b.setFont(new Font(Font.SERIF,Font.ITALIC, fontSize));
        }
        titleLabel.setFont(new Font(Font.SERIF, Font.BOLD, fontSize*3));
        levelLabel.setFont(new Font(Font.SERIF, Font.BOLD, fontSize));

    }
}
