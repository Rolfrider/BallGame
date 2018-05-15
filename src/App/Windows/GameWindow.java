package App.Windows;



import App.Config.LevelLoader;
import App.Game.GameLoop;
import App.Game.HUD;
import App.Game.KeyInput;
import App.Game.Level;
import App.WindowManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameWindow extends JPanel {

    private Level level;
    private HUD hud;
    private GameLoop gameLoop;
    private LevelLoader levelLoader = new LevelLoader();
    private int currentLevel = 1;
    private WindowManager windowParent; // A JFrame


    public GameWindow(){
        super();
        setBackground(Color.BLACK);
        gameLoop = new GameLoop(this);
        level = levelLoader.loadLevel(currentLevel);
        setFocusable(true);
        addKeyListener(new KeyInput(this));
        start();
    }



    public GameWindow(WindowManager windowParent){
        super();
        this.windowParent = windowParent;
        setBackground(Color.BLACK);
        gameLoop = new GameLoop(this);
        level = levelLoader.loadLevel(currentLevel);
        setFocusable(true);
        addKeyListener(new KeyInput(this));
        hud = new HUD(currentLevel,
                Integer.parseInt((String) windowParent.getSettingsProperties().get("lives-"
                        + windowParent.getSettingsProperties().get("difficulty"))));
        updateScore();
        start();
    }


    public void pauseOrResume(){
        if(gameLoop.isPause())
            gameLoop.resume();
        else
            gameLoop.pause();
    }

    public void updateScore(){
        hud.setScore(Integer.parseInt((String) windowParent.getSettingsProperties().get("difficulty"))*currentLevel*100
                        + hud.getLives()*100);
    }

    public void levelUp(){
        currentLevel++;
        if(currentLevel > Integer.parseInt((String) windowParent.getSettingsProperties().get("max level"))){
            currentLevel--;
            gameOver();
        }

        level = levelLoader.loadLevel(currentLevel);
        hud.setLevel(currentLevel);
        updateScore();
        gameLoop = new GameLoop(this);
        start();
    }

    public void die(){
        hud.setLives(hud.getLives()-1);
        if(hud.getLives() < 0 )
            gameOver();
        level = levelLoader.loadLevel(currentLevel);
        updateScore();
        gameLoop = new GameLoop(this);
        start();
    }

    public void gameOver(){
        updateScore();
        windowParent.setWindow(new GameOverWindow(windowParent, hud.getScore()));
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
        hud.paintObject(g,getSize());
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}
