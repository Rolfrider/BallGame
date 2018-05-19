package App.Client.Windows;

import App.Client.WindowManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class OptionsWindow extends JPanel {
    private WindowManager windowParent;
    private ArrayList<JButton> buttons = new ArrayList<>();
    private JLabel optionsLabel;

    public OptionsWindow(WindowManager windowParent){
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

        add(optionsLabel, bagConstraints);

        //Other components setup
        bagConstraints.gridy = 3;
        bagConstraints.gridheight = 1;
        bagConstraints.anchor = GridBagConstraints.CENTER;

        bagConstraints.gridy = 4;

        add(buttons.get(0), bagConstraints);


        bagConstraints.gridy = 5;

        add(buttons.get(1), bagConstraints);
    }

    private void initButtons() {
        buttons.add(new JButton(windowParent.getTextProperties().getProperty("back label")));
        buttons.add(new JButton(windowParent.getTextProperties().getProperty("exit label")));
        buttons.get(0).addActionListener( actionEvent -> windowParent.setWindow(new WelcomeWindow(windowParent)));
        buttons.get(1).addActionListener( actionEvent -> windowParent.exit());

        for (JButton b : buttons) {
            b.setBackground(Color.BLACK);
            b.setForeground(Color.BLUE);
            b.setOpaque(true);
            b.setBorderPainted(false);
        }
    }

    private void initLabel() {
        optionsLabel = new JLabel(windowParent.getTextProperties().getProperty("options label"));
        optionsLabel.setForeground(Color.WHITE);

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
        optionsLabel.setFont(new Font(Font.SERIF, Font.BOLD, fontSize*3));
    }
}