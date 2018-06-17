package App.Client.Windows;

import App.Background.RainController;
import App.Background.RainDrop;
import App.Client.WindowManager;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class WelcomeWindow extends MenuWindow {
    private JLabel levelLabel ;




    public WelcomeWindow(WindowManager windowParent){
        super(windowParent);
    }


    @Override
    protected void placeComponents() {
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

    protected void initLabel() {
        titleLabel = new JLabel(windowParent.getTextProperties().getProperty("game title"));
        titleLabel.setForeground(Color.WHITE);

        String levelVal = "";

        switch (windowParent.getSettingsProperties().getProperty("difficulty")){
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

    protected void initButtons(){
        buttons.add(new JButton(windowParent.getTextProperties().getProperty("play label")));
        buttons.add(new JButton(windowParent.getTextProperties().getProperty("scoreboard label")));
        buttons.add(new JButton(windowParent.getTextProperties().getProperty("options label")));
        buttons.add(new JButton(windowParent.getTextProperties().getProperty("exit label")));
        buttons.get(0).addActionListener( actionEvent -> {
            stopRaining();
            windowParent.setWindow(new GameWindow(windowParent));});
        buttons.get(1).addActionListener( actionEvent -> {
            stopRaining();
            windowParent.setWindow(new ScoreboardWindow(windowParent));});
        buttons.get(2).addActionListener( actionEvent -> {
            stopRaining();
            windowParent.setWindow(new OptionsWindow(windowParent));});
        buttons.get(3).addActionListener( actionEvent -> {
            stopRaining();
            windowParent.exit();});


    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        int fontSize = (getSize().height + getSize().width)/40;
        levelLabel.setFont(new Font(Font.SERIF, Font.BOLD, fontSize));
    }
}
