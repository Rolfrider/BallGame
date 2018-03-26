package App;



import javax.swing.*;
import java.awt.*;

public class Game extends JPanel {

    GameObject[] gameObject = new GameObject[5];

    Game(){
        super();
        gameObject[0] = new Obstacle(0,0,1, 0.05);
        gameObject[1] = new Obstacle(0,0,0.05, 1);
        gameObject[2] = new Obstacle(0.95,0,0.06, 1);
        gameObject[3] = new Obstacle(0,0.95,1, 0.06);
        gameObject[4] = new Obstacle(0.5,0,0.05,0.5);
        setBackground(Color.BLACK);

    }


    @Override
    public Dimension getPreferredSize() {
        return new Dimension(300, 300);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (GameObject game : gameObject)
            game.paintObject(g,getSize());
    }
}
