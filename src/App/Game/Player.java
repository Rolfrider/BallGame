package App.Game;

import App.Config.SpriteLoader;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Player controlled object
 */
public class Player extends GameObject implements Movable{

    private Color playerColor;
    private double velX, velY;
    private double gravityY;
    private final double maxSpeed = 0.005;

    public Player(){
        setSpriteID("src/Config/Resources/batman_right.gif");
        setSprite(SpriteLoader.loadSprite(getSpriteID()));
    }

    /**
     * Player object constructor
     * @param x x coordinates
     * @param y y coordinates
     * @param width width dimension
     * @param height height dimension
     * @param gravityY gravity acceleration
     */
    public Player(double x, double y, double width, double height, double gravityY) {
        super(x, y, width, height);

        this.gravityY = gravityY;
    }

    /**
     * Moves the player object
     */
    @Override
    public void move() {
        velY = Math.abs(velY) > maxSpeed ? maxSpeed : velY;
        velX = Math.abs(velX) > maxSpeed ? maxSpeed : velX;
        setX(getX() + velX);
        setY(getY() + velY + gravityY);
    }

    /**
     * Returns shape of the player controlled object
     * @return
     */
    @Override
    public Ellipse2D.Double getShape() {
        return new Ellipse2D.Double(getX(),getY(),getWidth(),getHeight());
    }

    /**
     * Paints the player object
     * @param g graphics
     * @param d dimension
     */
    @Override
    public void paintObject(Graphics g, Dimension d) {
        Graphics2D graphics2D = (Graphics2D) g;

        g.drawImage(getSprite(),(int)(getX()*d.width), (int)(getY()*d.height),
                (int)(getWidth()*d.width), (int)(getHeight()*d.height),null);
//        graphics2D.setColor(Color.WHITE);
//        graphics2D.fillOval((int)(getX()*d.width), (int)(getY()*d.height),
//                (int)(getWidth()*d.width), (int)(getHeight()*d.height));
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
