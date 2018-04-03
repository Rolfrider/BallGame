package App.Game;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Player extends GameObject implements Movable{

    private Color playerColor;
    private double velX, velY;
    private double gravityY;

    public Player(){}

    public Player(double x, double y, double width, double height, double gravityY) {
        super(x, y, width, height);
        this.gravityY = gravityY;
    }

    @Override
    public void move() {
//        velY = 0;
//        if(getY() > 1 - getHeight()|| getY() < 0 )
//            gravityY *= -1;
//        velY += gravityY;
//        setX(getX() + velX);
//        setY(getY() + velY);
    }

    @Override
    public Ellipse2D.Double getShape() {
        return new Ellipse2D.Double(getX(),getY(),getWidth(),getHeight());
    }

    @Override
    public void paintObject(Graphics g, Dimension d) {
        Graphics2D graphics2D = (Graphics2D) g;

        graphics2D.setColor(Color.WHITE);
        graphics2D.fillOval((int)(getX()*d.width), (int)(getY()*d.height),
                (int)(getWidth()*d.width), (int)(getHeight()*d.height));
    }

    //Getters and Setters


    public double getVelX() {
        return velX;
    }

    public void setVelX(double velX) {
        this.velX = velX;
    }

    public double getVelY() {
        return velY;
    }

    public void setVelY(double velY) {
        this.velY = velY;
    }

    public double getGravityY() {
        return gravityY;
    }

    public void setGravityY(double gravityY) {
        this.gravityY = gravityY;
    }

    public Color getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(Color playerColor) {
        this.playerColor = playerColor;
    }
}
