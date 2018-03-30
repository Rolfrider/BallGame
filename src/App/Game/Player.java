package App.Game;

import java.awt.*;

public class Player extends GameObject implements Movable{

    private Color playerColor;

    public Player(){}

    public Player(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    @Override
    public void move() {

    }

    @Override
    public void paintObject(Graphics g, Dimension d) {
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect((int)(getX()*d.width), (int)(getY()*d.height),
                (int)(getWidth()*d.width), (int)(getHeight()*d.height));
    }

    //Getters and Setters
    public Color getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(Color playerColor) {
        this.playerColor = playerColor;
    }
}
