package App.Windows;



import App.Config.LevelLoader;
import App.Game.GameLoop;
import App.Game.KeyInput;
import App.Game.Level;
import App.WindowManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameWindow extends JPanel {

    private Level level;
    private GameLoop gameLoop;
    private LevelLoader levelLoader = new LevelLoader();
    private WindowManager windowParent; // A JFrame


    public GameWindow(){
        super();
        setBackground(Color.BLACK);
        gameLoop = new GameLoop(this);
        level = levelLoader.loadLevel(1);
        setFocusable(true);
        addKeyListener(new KeyInput(this));
        start();
    }



    public GameWindow(WindowManager windowParent){
        super();
        this.windowParent = windowParent;
        setBackground(Color.BLACK);
        gameLoop = new GameLoop(this);
        level = levelLoader.loadLevel(1);
        setFocusable(true);
        addKeyListener(new KeyInput(this));
        start();
    }


    public void pauseOrResume(){
        if(gameLoop.isPause())
            gameLoop.resume();
        else
            gameLoop.pause();
    }

    public void gameOver(){
        windowParent.setWindow(new GameOverWindow(windowParent, 2));
    }

    private  void start(){
        Thread thread = new Thread(gameLoop);
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
