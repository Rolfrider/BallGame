package App.Windows;



import App.Config.LevelLoader;
import App.Game.GameLoop;
import App.Game.GameObject;
import App.Game.Level;
import App.Game.Obstacle;
import App.WindowManager;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JPanel {

    private Level level;
    private LevelLoader levelLoader = new LevelLoader();
    private WindowManager windowManager;


    public GameWindow(){
        super();
        setBackground(Color.BLACK);
        level = levelLoader.loadLevel(1);
        start();
    }

    public GameWindow(WindowManager windowManager){
        super();
        this.windowManager = windowManager;
        setBackground(Color.BLACK);
        level = levelLoader.loadLevel(1);
        start();
    }


    private void start(){
        Thread thread = new Thread(new GameLoop(this));
        thread.start();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(600, 600);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        level.paintLevel(g,getSize());
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}
