package App.Game;

import App.Config.SpriteLoader;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

/**
 * Player controlled object
 */
public class Player extends GameObject implements Movable{

    private Color playerColor;
    private double velX, velY;
    private double gravityY;
    private final double maxSpeed = 0.005;
    private transient Image leftRun;
    private transient Image rightRun;

    public Player(){}

    public void init(){
        leftRun = SpriteLoader.loadSprite(getSpriteID() + "left.gif");
        rightRun = SpriteLoader.loadSprite(getSpriteID() + "right.gif");
        setSprite(rightRun);
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
     * Method describing mechanics of player movement
     */
    @Override
    public void move() {
        setSprite(SpriteLoader.loadSprite(getSpriteID()));

        if(Math.abs(velY) > maxSpeed)
            velY = Math.signum(velY)*maxSpeed;
        if(Math.abs(velX) > maxSpeed)
            velX = Math.signum(velX)*maxSpeed;
        setX(getX() + velX);
        setY(getY() + velY + gravityY);
    }

    /**
     * Returns shape of the player controlled object
     * @return
     */
    @Override
    public Rectangle2D.Double getShape() {

        double verticalFrame = getHeight()*0.5;
        double horizontalFrame = getWidth()*0.5;
        return new Rectangle2D.Double(getX()+ horizontalFrame,getY()+ verticalFrame
                ,getWidth()- horizontalFrame,getHeight()- verticalFrame);
    }

    /**
     * Paints the player object
     * @param g graphics
     * @param d dimension
     */
    @Override
    public void paintObject(Graphics g, Dimension d) {
        Graphics2D graphics2D = (Graphics2D) g;

        if(velX >= 0){
            setSprite(rightRun);
        }else
            setSprite(leftRun);

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
