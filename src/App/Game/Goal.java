package App.Game;

import App.Config.SpriteLoader;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Object corresponding to level destination
 */
public class Goal extends GameObject {

    public Goal(){}

    @Override
    public void init() {
        setSprite(SpriteLoader.loadSprite(getSpriteID()));
    }

    /**
     * Goal constructor
     * @param x x coordinates
     * @param y y coordinates
     * @param width width of the object
     * @param height height of the object
     */
    public Goal(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    /**
     * Returns shape of the goal
     * @return
     */
    @Override
    public Rectangle2D.Double getShape() {
        double verticalFrame = getHeight()*0.25;
        double horizontalFrame = getWidth()*0.5;
        return new Rectangle2D.Double(getX()+ horizontalFrame/2,getY()+ verticalFrame/2
                ,getWidth()- horizontalFrame,getHeight()- verticalFrame);
    }

    @Override
    public String toString() {
        String result = getClass().getName() + " " + super.toString();
        return result;
    }

    /**
     * Paints goal object
     * @param g graphics
     * @param d dimensions
     */
    @Override
    public void paintObject(Graphics g, Dimension d) {
        Graphics2D graphics2D = (Graphics2D) g;
        g.drawImage(getSprite(),(int)(getX()*d.width), (int)(getY()*d.height),
                (int)(getWidth()*d.width), (int)(getHeight()*d.height),null);
    }
}
