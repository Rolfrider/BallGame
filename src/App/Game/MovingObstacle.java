package App.Game;

public class MovingObstacle extends Obstacle implements Movable {

    float velX, velY;

    public MovingObstacle(){};

    public MovingObstacle(double x, double y , double width, double height, float velX, float velY){
        super(x, y , width, height);
        this.velX = velX;
        this.velY = velY;
    }

    @Override
    public void move() {

    }


    public float getVelX() {
        return velX;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }
}
