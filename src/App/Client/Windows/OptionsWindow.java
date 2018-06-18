package App.Client.Windows;

import App.Client.WindowManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class OptionsWindow extends MenuWindow {

    public OptionsWindow(WindowManager windowParent){
        super(windowParent);
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
        bagConstraints.gridwidth = 1;
        bagConstraints.anchor = GridBagConstraints.CENTER;

        bagConstraints.gridy = 4;
        bagConstraints.gridx = 1;
        add(buttons.get(0), bagConstraints);
        bagConstraints.gridx = 2;
        add(buttons.get(1), bagConstraints);
        bagConstraints.gridx = 3;
        add(buttons.get(2), bagConstraints);

        bagConstraints.gridy = 5;
        bagConstraints.gridx = 1;
        add(buttons.get(3), bagConstraints);
        bagConstraints.gridx = 3;
        add(buttons.get(4), bagConstraints);

        bagConstraints.gridy = 6;
        bagConstraints.gridx = 1;
        add(buttons.get(5), bagConstraints);
        bagConstraints.gridx = 3;
        add(buttons.get(6), bagConstraints);

        bagConstraints.gridy = 7;
        bagConstraints.gridx = 2;
        add(buttons.get(7), bagConstraints);

        bagConstraints.gridy = 8;
        add(buttons.get(8), bagConstraints);
    }

    protected void initButtons() {
        buttons.add(new JButton(windowParent.getTextProperties().getProperty("easy level")));
        buttons.add(new JButton(windowParent.getTextProperties().getProperty("medium level")));
        buttons.add(new JButton(windowParent.getTextProperties().getProperty("hard level")));
        buttons.add(new JButton(windowParent.getTextProperties().getProperty("polish")));
        buttons.add(new JButton(windowParent.getTextProperties().getProperty("english")));
        buttons.add(new JButton(windowParent.getTextProperties().getProperty("online")));
        buttons.add(new JButton(windowParent.getTextProperties().getProperty("offline")));
        buttons.add(new JButton(windowParent.getTextProperties().getProperty("back label")));
        buttons.add(new JButton(windowParent.getTextProperties().getProperty("exit label")));
        buttons.get(0).addActionListener( actionEvent -> {
            //stopRaining();
            windowParent.getSettingsProperties().replace("difficulty","1");
            windowParent.setWindow(new WelcomeWindow(windowParent));});
        buttons.get(1).addActionListener( actionEvent -> {
            //stopRaining();
            windowParent.getSettingsProperties().replace("difficulty","2");
            windowParent.setWindow(new WelcomeWindow(windowParent));});
        buttons.get(2).addActionListener( actionEvent -> {
            //stopRaining();
            windowParent.getSettingsProperties().replace("difficulty","3");
            windowParent.setWindow(new WelcomeWindow(windowParent));});
        buttons.get(3).addActionListener( actionEvent -> {
            //stopRaining();
            windowParent.getSettingsProperties().replace("language","polish");
            windowParent.updateTextProperties();
            windowParent.setWindow(new WelcomeWindow(windowParent));});
        buttons.get(4).addActionListener( actionEvent -> {
            //stopRaining();
            windowParent.getSettingsProperties().replace("language","english");
            windowParent.updateTextProperties();
            windowParent.setWindow(new WelcomeWindow(windowParent));});
        buttons.get(5).addActionListener( actionEvent -> {
            //stopRaining();
            windowParent.getSettingsProperties().replace("online","1");
            windowParent.setWindow(new WelcomeWindow(windowParent));});
        buttons.get(6).addActionListener( actionEvent -> {
            //stopRaining();
            windowParent.getSettingsProperties().replace("online","0");
            windowParent.setWindow(new WelcomeWindow(windowParent));});
        buttons.get(7).addActionListener( actionEvent -> {
            stopRaining();
            windowParent.setWindow(new WelcomeWindow(windowParent));});
        buttons.get(8).addActionListener( actionEvent -> {
            stopRaining();
            windowParent.exit();});

    }

    protected void initLabel() {
        titleLabel = new JLabel(windowParent.getTextProperties().getProperty("options label"));
        titleLabel.setForeground(Color.WHITE);
    }

}
