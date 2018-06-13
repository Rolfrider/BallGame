package App.Game;

import javax.swing.*;
import java.awt.*;

/**
 * Object corresponding to heads-up display indicating score and important info
 */
public class HUD extends GameObject{
    private int score = 0;
    private int level;
    private int lives;
    private boolean paused = true;
    private final double y = 0.025;
    private final double xScore = 0.475,
                        xLevel = 0.03,
                        xLives = 0.875;

    public HUD( int level, int lives) {
        this.level = level;
        this.lives = lives;
    }

    /**
     * Paints HUD object
     * @param g graphics
     * @param d dimension
     */
    @Override
    public void paintObject(Graphics g, Dimension d) {
        g.setColor(Color.WHITE);
        g.setFont(new Font(Font.SERIF,Font.ITALIC,(d.getSize().height + d.getSize().width)/80));
        g.drawString("Level: " + level, (int)(xLevel*d.width), (int)(y*d.height) );
        g.drawString("" + score, (int)(xScore*d.width), (int)(y*d.height) );
        g.drawString("Lives: " + lives, (int)(xLives*d.width), (int)(y*d.height) );
        if(paused){
            g.drawString("Press SPACE to resume", (int)(d.width/2.8), (int)(d.height/1.8));
            g.setFont(new Font(Font.SERIF,Font.ITALIC,(d.getSize().height + d.getSize().width)/20));
            g.drawString("Paused",d.width/3, d.height/2);

        }

    }

    @Override
    public Shape getShape() {
        return null;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
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
