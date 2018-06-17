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
        bagConstraints.anchor = GridBagConstraints.CENTER;

        bagConstraints.gridy = 4;

        add(buttons.get(0), bagConstraints);


        bagConstraints.gridy = 5;

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

    protected void initLabel() {
        titleLabel = new JLabel(windowParent.getTextProperties().getProperty("options label"));
        titleLabel.setForeground(Color.WHITE);
    }

}
