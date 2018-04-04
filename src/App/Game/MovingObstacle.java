package App.Game;

public class MovingObstacle extends Obstacle implements Movable {

    private double velX, velY;

    public MovingObstacle(){}

    public MovingObstacle(double x, double y , double width, double height, double velX, double velY){
        super(x, y , width, height);
        this.velX = velX;
        this.velY = velY;
    }

    @Override
    public void move() {
        setX(getX() + velX);
        setY(getY() + velY);
    }

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
