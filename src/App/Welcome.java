package App;

import javax.swing.*;
import java.awt.*;

public class Welcome extends JPanel {
    private int rows = 5, columns = 5;
    private JPanel [][] panels = new JPanel[rows][columns];


    Welcome(){
        setBackground(Color.BLACK);
        setLayout(new GridLayout(rows, columns, 5, 5));
        JButton button = new JButton("Play");
        button.setFont(new Font(null,2, 30));
        button.setBackground(Color.BLACK);
        JButton button1 = new JButton("dlay");
        for(int r = 0; r < rows; r++){
            for (int c = 0; c < columns; c++){
                panels[r][c] = new JPanel();
                panels[r][c].setBackground(Color.BLACK);
                add(panels[r][c]);
            }
        }

        panels[1][2].add(button);
        panels[3][2].add(button1);
    }
}
