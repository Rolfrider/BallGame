package App.Game;

import App.Config.SpriteLoader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Defines non movable obstacle
 */
public class Obstacle extends GameObject {

    protected Color fillColor = Color.RED;

    public Obstacle(){
        setSpriteID("src/Config/Resources/brick_wall.gif");
        setSprite(SpriteLoader.loadSprite(getSpriteID()));
    }

    /**
     * Obstacle creator
     * @param x x coordinates
     * @param y y coordinates
     * @param width width dimension
     * @param height height dimension
     */
    public Obstacle(double x, double y , double width, double height){
        super(x,y,width,height);

    }

    /**
     * Paints the obstacle
     * @param g graphics
     * @param d dimension
     */
    @Override
    public void paintObject(Graphics g, Dimension d) {
        Graphics2D graphics2D = (Graphics2D) g;
//        graphics2D.setColor(fillColor);
//        graphics2D.fillRect((int)(getX()*d.width), (int)(getY()*d.height),
//                (int)(getWidth()*d.width), (int)(getHeight()*d.height));
        g.drawImage(getSprite(),(int)(getX()*d.width), (int)(getY()*d.height),
                (int)(getWidth()*d.width), (int)(getHeight()*d.height),null);

    }

    /**
     * Returns shape of the obstacle
     * @return
     */
    @Override
    public Rectangle2D.Double getShape() {
        return new Rectangle2D.Double(getX(),getY(),getWidth(),getHeight());
    }

    //Getters and Setters
    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }
}
