package App;


import java.awt.*;

public class Obstacle extends GameObject {

    protected Color fillColor = Color.RED;


    public Obstacle(double x, double y , double width, double height){
        super(x,y,width,height);
    }

    @Override
    public void paintObject(Graphics g, Dimension d) {
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setColor(fillColor);
        graphics2D.fillRect((int)(getX()*d.width), (int)(getY()*d.height),
                (int)(getWidth()*d.width), (int)(getHeight()*d.height));

    }
}
