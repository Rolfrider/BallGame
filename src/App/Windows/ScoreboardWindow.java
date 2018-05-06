package App.Windows;

import App.WindowManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ScoreboardWindow extends JPanel {
    private WindowManager windowParent;
    private ArrayList<JButton> buttons = new ArrayList<>();
    private JLabel optionsLabel ;
    private ArrayList<JLabel> scoreLabel = new ArrayList<>();
    private ArrayList<Integer> scores = new ArrayList<>();
    private ArrayList<String> names = new ArrayList<>();

    public ScoreboardWindow(WindowManager windowParent){
        super();
        this.windowParent = windowParent;

        setBackground(Color.BLACK);
        setLayout(new GridBagLayout());

        Arrays.asList("jbutton,lhamilton,pmaldonado,fmassa,nrosberg".split(","))
                .forEach(a -> names.add(a));
        Arrays.asList("2000,1800,1600,1400,1000".split(","))
                .forEach(a -> scores.add(Integer.parseInt(a)));

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


        bagConstraints.gridy = k++;

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
        optionsLabel = new JLabel(windowParent.getTextProperties().getProperty("scoreboard label"));
        optionsLabel.setForeground(Color.WHITE);

        for (int i=0; i < names.size(); i++) {
            scoreLabel.add(new JLabel(names.get(i) + " - " + scores.get(i)));
            scoreLabel.get(i).setForeground(Color.RED);
        }
        /*scoreLabel =  new JLabel(score + "");
        scoreLabel.setForeground(Color.RED);*/
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
        for (JLabel label: scoreLabel) {
            label.setFont(new Font(Font.SERIF, Font.BOLD, fontSize));
        }
        //scoreLabel.setFont(new Font(Font.SERIF, Font.BOLD, fontSize*2));

    }
}
