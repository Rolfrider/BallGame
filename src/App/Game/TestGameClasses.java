package App.Game;

import App.Config.LevelLoader;
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
        Player player = new Player(0.17,0.17, 0.08, 0.08);
        Goal goal = new Goal(0.67,0.17,0.08,0.08);
        player.setPlayerColor(Color.GRAY);
        gameObjects.add(goal);
        gameObjects.add(player);
        gameObjects.add(new Obstacle(0,0,1, 0.03));
        gameObjects.add(new Obstacle(0,0,0.03, 1));
        gameObjects.add(new Obstacle(0.97,0,0.03, 1));
        gameObjects.add(new Obstacle(0,0.97,1, 0.03));
        gameObjects.add(new Obstacle(0.48,0.03,0.03, 0.47));

        Level level = new Level();
        level.setGameObjects(gameObjects);
        level.setLevelNumber(1);
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

        levelLoader.saveLevel(level);



    }

}
