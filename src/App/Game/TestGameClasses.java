package App.Game;

import App.Config.LevelLoader;
import App.WindowManager;
import App.Windows.GameWindow;

import javax.swing.*;
import java.awt.*;

import java.util.ArrayList;

public class TestGameClasses {
    LevelLoader levelLoader = new LevelLoader();

    public static void main(String[] args) {
        TestGameClasses tgc = new TestGameClasses();
        tgc.encodingTest();

    }


    //Tutorial en/decoding : https://howtodoinjava.com/core-java/serialization/xmlencoder-and-xmldecoder-example/

    void decodingTest(){
        Level level = levelLoader.loadLevel(1);

        System.out.println("Player :");
        System.out.println(level.getPlayer().getPlayerColor());
        System.out.println(level.getPlayer().getX());

        System.out.println(level.getGameObjects().get(0).getHeight());
        System.out.println(level.getGameObjects().get(2).getX());
        System.out.println(level.getMovingObstacles().get(0).getWidth());

    }



    void encodingTest(){
        ArrayList<GameObject> gameObjects = new ArrayList<>();
        ArrayList<MovingObstacle> movingObstacles = new ArrayList<>();
        Player player = new Player(0.87,0.27, 0.08, 0.08, 0.01);
        Goal goal = new Goal(0.07,0.57,0.08,0.08);

        MovingObstacle mo = new MovingObstacle(0.4,0.53,0.03,0.03,0, 0.005);
        movingObstacles.add(mo);

        player.setPlayerColor(Color.GRAY);
        gameObjects.add(mo);
        gameObjects.add(goal);
        gameObjects.add(player);

        gameObjects.add(new Obstacle(0,0,1, 0.03));
        gameObjects.add(new Obstacle(0,0,0.03, 1));
        gameObjects.add(new Obstacle(0.97,0,0.03, 1));
        gameObjects.add(new Obstacle(0,0.97,1, 0.03));
        gameObjects.add(new Obstacle(0.03,0.48,0.30, 0.03));
        gameObjects.add(new Obstacle(0.67,0.48,0.30, 0.03));

        Level level = new Level();
        level.setMovingObstacles(movingObstacles);
        level.setGameObjects(gameObjects);
        level.setLevelNumber(3);// Set level number before saving
        level.setPlayer(player);
        level.setGoal(goal);


        JFrame frame = new JFrame();
        JPanel panel = new GameWindow();
        ((GameWindow) panel).setLevel(level);
        frame.add(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        EventQueue.invokeLater(() -> {
            frame.pack();
            frame.setVisible(true);
        });


        //Uncomment to save the lvl
        //levelLoader.saveLevel(level);



    }

}
