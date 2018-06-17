package App.Client.Windows;



import App.Client.Client;
import App.Config.LevelLoader;
import App.Game.GameLoop;
import App.Game.HUD;
import App.Game.KeyInput;
import App.Game.Level;
import App.Client.WindowManager;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends CustomWindow {

    private Level level;
    private HUD hud;
    private GameLoop gameLoop;
    private LevelLoader levelLoader = new LevelLoader();
    private int currentLevel = 1;



    public GameWindow(WindowManager windowParent){
        super(windowParent);
        gameLoop = new GameLoop(this);
        loadLevel();
        level.init();
        setFocusable(true);
        addKeyListener(new KeyInput(this));
        hud = new HUD(currentLevel,
                Integer.parseInt((String) windowParent.getSettingsProperties().get("lives-"
                        + windowParent.getSettingsProperties().get("difficulty"))));
        updateScore();
        start();
        startRaining();
    }


    public void pauseOrResume(){
        if(gameLoop.isPause()) {
            gameLoop.resume();
            hud.setPaused(false);
        }else {
            gameLoop.pause();
            hud.setPaused(true);
            repaint();
        }
    }

    public void updateScore(){
        hud.setScore(Integer.parseInt((String) windowParent.getSettingsProperties().get("difficulty"))*currentLevel*100
                        + hud.getLives()*100);
    }

    public void levelUp(){
        hud.setPaused(true);
        stopRaining();
        currentLevel++;
        if(currentLevel > Integer.parseInt((String) windowParent.getSettingsProperties().get("max_level"))){
            currentLevel--;
            gameOver();
            return;
        }
        loadLevel();
        level.init();
        hud.setLevel(currentLevel);
        updateScore();
        gameLoop = new GameLoop(this);
        startRaining();
        start();
    }

    public void die(){
        hud.setPaused(true);
        stopRaining();
        hud.setLives(hud.getLives()-1);
        if(hud.getLives() < 0 ) {
            gameOver();
            return;
        }
        level = Client.getLevel(currentLevel);
        loadLevel();
        level.init();
        updateScore();
        gameLoop = new GameLoop(this);
        startRaining();
        start();
    }

    public void gameOver(){
        updateScore();
        stopRaining();
        windowParent.setWindow(new GameOverWindow(windowParent, hud.getScore()));
    }

    private  void start(){
        Thread thread = new Thread(gameLoop);
        thread.start();
    }

    public void loadLevel(){
        if(windowParent.getSettingsProperties().getProperty("online").equals("1")){
            level = Client.getLevel(currentLevel);
            if (level == null){
                windowParent.dialog("Could not load level from server.");
                level = levelLoader.loadLevel(currentLevel);
            }
        }else{
            level = levelLoader.loadLevel(currentLevel);
        }
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
