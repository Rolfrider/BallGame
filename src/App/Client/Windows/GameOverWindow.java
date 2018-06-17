package App.Client.Windows;

import App.Client.Client;
import App.Client.WindowManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameOverWindow extends MenuWindow {
    private  JLabel scoreLabel ;
    private int score;

    public GameOverWindow(WindowManager windowParent, int score) {
        super();
        this.windowParent = windowParent;
        this.score = score;
        initButtons();
        buttonsLook();
        initLabel();
        placeComponents();
        if(Client.postScore(windowParent.usernameDialog(), score)){
            windowParent.dialog("Successfully added your score to the server scoreboard");
        }else {
            windowParent.dialog("Your score can't be added to the server scoreboard");
        }
        startRaining();
    }

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

        add(scoreLabel,bagConstraints);

        bagConstraints.gridy = 4;

        add(buttons.get(0), bagConstraints);


        bagConstraints.gridy = 5;

        add(buttons.get(1), bagConstraints);
    }

    protected void initButtons() {
        buttons.add(new JButton(windowParent.getTextProperties().getProperty("menu label")));
        buttons.add(new JButton(windowParent.getTextProperties().getProperty("exit label")));
        buttons.get(0).addActionListener( actionEvent -> {
            stopRaining();
            windowParent.setWindow(new WelcomeWindow(windowParent));});
        buttons.get(1).addActionListener( actionEvent -> {
            stopRaining();
            windowParent.exit();});


    }

    protected void initLabel() {
        titleLabel = new JLabel(windowParent.getTextProperties().getProperty("game over label"));
        titleLabel.setForeground(Color.WHITE);

        scoreLabel =  new JLabel(score + "");
        scoreLabel.setForeground(Color.RED);
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        // Resize the text on buttons
        int fontSize = (getSize().height + getSize().width)/40;
        scoreLabel.setFont(new Font(Font.SERIF, Font.BOLD, fontSize*2));

    }

}
