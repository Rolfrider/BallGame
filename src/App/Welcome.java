package App;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

import java.awt.*;
import java.util.ArrayList;

public class Welcome extends JPanel {
    private int rows = 7, columns = 5;
    private JPanel [][] panels = new JPanel[rows][columns];
    private ArrayList<JButton> buttons = new ArrayList<>();
    private JLabel titleLabel = new JLabel("Ball Game");
    private JLabel levelLabel = new JLabel("Level: easy");


    Welcome(){
        setBackground(Color.BLACK);
        setLayout(new GridLayout(rows, columns, 5, 5));
        initButtons();
        for(int r = 0; r < rows; r++){
            for (int c = 0; c < columns; c++){
                panels[r][c] = new JPanel();
                panels[r][c].setBackground(Color.BLACK);
                add(panels[r][c]);
            }
        }
        panels[1][2].add(titleLabel);
        panels[3][2].add(buttons.get(0));
        panels[4][2].add(buttons.get(1));
        panels[5][2].add(buttons.get(2));
        panels[6][2].add(levelLabel);

    }

    private void initButtons(){
        buttons.add(new JButton("Play"));
        buttons.add(new JButton("Option"));
        buttons.add(new JButton("Exit"));
        for (JButton b : buttons) {
            b.setBackground(Color.BLACK);
            b.setForeground(Color.CYAN);
            b.setOpaque(true);
            b.setBorderPainted(false);
        
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Resize the text on buttons 
        int fontSize = (getSize().height + getSize().width)/40;
        for (JButton b : buttons) {
            b.setFont(new Font(Font.SERIF,Font.ITALIC, fontSize));
        }
        titleLabel.setFont(new Font(Font.SERIF, Font.BOLD, fontSize*2));
        levelLabel.setFont(new Font(Font.SERIF, Font.BOLD, fontSize));

    }
}
