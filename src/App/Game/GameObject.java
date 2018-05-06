package App.Game;
import java.awt.*;

/**
 * Abstract object defining basic game object properties
 */
public abstract class GameObject {
    private double x, y;
    private double width, height;

    public GameObject(){}

    /**
     * Game object constructor
     * @param x x coordinates
     * @param y y coordinates
     * @param width width of the object
     * @param height height of the object
     */
    public GameObject(double x, double y , double width, double height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Paints object
     * @param g graphics
     * @param d dimension
     */
    public abstract void paintObject(Graphics g, Dimension d);

    //Getters and Setters
    public abstract Shape getShape();

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
