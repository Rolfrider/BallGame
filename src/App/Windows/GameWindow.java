package App.Windows;



import App.Config.LevelLoader;
import App.Game.GameObject;
import App.Game.Level;
import App.Game.Obstacle;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JPanel {

    Level level;
    LevelLoader levelLoader = new LevelLoader();

    public GameWindow(){
        super();
        setBackground(Color.BLACK);
        level = levelLoader.loadLevel(1);

    }


    @Override
    public Dimension getPreferredSize() {
        return new Dimension(300, 300);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        level.paintLevel(g,getSize());
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}
