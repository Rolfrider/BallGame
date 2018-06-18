package App.Background;

import App.Game.Movable;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

public class RainDrop implements Movable {

    private double x;
    private int y = 0;
    private double vel = 1;

    /**
     * Sets x to random value
     */
    public RainDrop(){
        Random rand = new Random();
        this.x = rand.nextDouble();
    }

    public int getY() {
        return y;
    }

    @Override
    public void move() {
        vel += 0.05;
        y += vel;
    }

    public void paintObject(Graphics g, Dimension d) {
        Graphics2D graphics2D = (Graphics2D) g;
        g.setColor(Color.GREEN);
        int size = Math.max(1, Math.max(d.height,d.width)/100);
        int xValue = (int)(x*d.width);
        //g.fillRect(xValue,y,size,size);
        GradientPaint gp = new GradientPaint(xValue, y + size, Color.BLACK,
                xValue,y + 10*size, Color.GREEN);
        ((Graphics2D) g).setPaint(gp);
        g.fillRect(xValue,y,size,size*6);
    }

}
