package App.Game;

import App.Config.SpriteLoader;

/**
 * Object corresponding to moving game obstacles
 */
public class MovingObstacle extends Obstacle implements Movable {

    private double velX, velY;

    public MovingObstacle(){ }

    /**
     * Moving obstacle creator
     * @param x x coorinates
     * @param y y coordinates
     * @param width width dimensions
     * @param height height dimensions
     * @param velX x axis velocity
     * @param velY y axis velocity
     */
    public MovingObstacle(double x, double y , double width, double height, double velX, double velY){
        super(x, y , width, height);
        this.velX = velX;
        this.velY = velY;
    }

    /**
     * Moves the obstacle
     */
    @Override
    public void move() {
        setX(getX() + velX);
        setY(getY() + velY);
    }

    /**
     * Reverses the direction of movement
     */
    public void reverseDirection(){
        velY *= -1;
        velX *= -1;
    }

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
}
