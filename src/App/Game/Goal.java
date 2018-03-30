package App.Game;

import java.awt.*;

public class Goal extends GameObject {


    public Goal(){}

    public Goal(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    @Override
    public void paintObject(Graphics g, Dimension d) {
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setColor(Color.CYAN);
        graphics2D.fillRect((int)(getX()*d.width), (int)(getY()*d.height),
                (int)(getWidth()*d.width), (int)(getHeight()*d.height));
    }
}
