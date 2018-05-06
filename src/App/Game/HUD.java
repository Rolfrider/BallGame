package App.Game;

import javax.swing.*;
import java.awt.*;

public class HUD extends GameObject{
    private int score = 0;
    private int level;
    private int lives;
    private final double y = 0.02;
    private final double xScore = 0.475,
                        xLevel = 0.03,
                        xLives = 0.875;

    public HUD( int level, int lives) {
        this.level = level;
        this.lives = lives;
    }


    @Override
    public void paintObject(Graphics g, Dimension d) {
        g.setColor(Color.WHITE);
        g.setFont(new Font(Font.SERIF,Font.ITALIC,(d.getSize().height + d.getSize().width)/80));
        g.drawString("Level: " + level, (int)(xLevel*d.width), (int)(y*d.height) );
        g.drawString("" + score, (int)(xScore*d.width), (int)(y*d.height) );
        g.drawString("Lives: " + lives, (int)(xLives*d.width), (int)(y*d.height) );

    }

    @Override
    public Shape getShape() {
        return null;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
}
