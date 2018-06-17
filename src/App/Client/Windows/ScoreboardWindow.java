package App.Client.Windows;

import App.Client.Client;
import App.Client.WindowManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.SortedMap;

public class ScoreboardWindow extends MenuWindow {

    private ArrayList<JLabel> scoreLabel = new ArrayList<>();
    private ArrayList<String> scores = new ArrayList<>();
    private ArrayList<String> names = new ArrayList<>();

    public ScoreboardWindow(WindowManager windowParent){
        super();
        this.windowParent = windowParent;

        if(windowParent.getSettingsProperties().getProperty("online").equals("1")){
            Properties properties = Client.getScores();
            if(properties != null) {
                loadScores(properties);
            }else {
                windowParent.dialog("Could not get scores form the server.");
                loadScores(windowParent.getScoreboard());
            }
        }else {
            loadScores(windowParent.getScoreboard());
        }

        initButtons();
        buttonsLook();
        initLabel();
        placeComponents();
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

        int k = 3;
        //Other components setup
        bagConstraints.gridy = k++;
        bagConstraints.gridheight = 1;
        bagConstraints.anchor = GridBagConstraints.CENTER;

        for (JLabel label: scoreLabel) {
            bagConstraints.gridy = k++;
            add(label,bagConstraints);
        }
        //add(scoreLabel.get(0),bagConstraints);

        bagConstraints.gridy = k++;

        add(buttons.get(0), bagConstraints);


        bagConstraints.gridy = k;

        add(buttons.get(1), bagConstraints);
    }

    protected void initButtons() {
        buttons.add(new JButton(windowParent.getTextProperties().getProperty("back label")));
        buttons.add(new JButton(windowParent.getTextProperties().getProperty("exit label")));
        buttons.get(0).addActionListener( actionEvent -> {
            stopRaining();
            windowParent.setWindow(new WelcomeWindow(windowParent));});
        buttons.get(1).addActionListener( actionEvent -> {
            stopRaining();
            windowParent.exit();});

    }

    private void loadScores(Properties properties) {
        properties.forEach((key, value) -> {
            int newScore = Integer.parseInt((String) value);
            if (scores.size() > 0) {
                for (int i = 0; i < scores.size(); i++) {
                    if (newScore > Integer.parseInt(scores.get(i))) {
                        names.add(i, (String) key);
                        scores.add(i, (String) value);
                        break;
                    } else if (i + 1 == scores.size()) {
                        names.add((String) key);
                        scores.add((String) value);
                        break;
                    }
                }
            } else {
                names.add((String) key);
                scores.add((String) value);
            }
        });
    }

    protected void initLabel() {
        titleLabel = new JLabel(windowParent.getTextProperties().getProperty("scoreboard label"));
        titleLabel.setForeground(Color.WHITE);

        for (int i=0; i < names.size(); i++) {
            scoreLabel.add(new JLabel(names.get(i) + " - " + scores.get(i)));
            scoreLabel.get(i).setForeground(Color.RED);
        }
        /*scoreLabel =  new JLabel(score + "");
        scoreLabel.setForeground(Color.RED);*/
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        // Resize the text on buttons
        int fontSize = (getSize().height + getSize().width)/40;
        for (JLabel label: scoreLabel) {
            label.setFont(new Font(Font.SERIF, Font.BOLD, fontSize));
        }
        //scoreLabel.setFont(new Font(Font.SERIF, Font.BOLD, fontSize*2));

    }
}
