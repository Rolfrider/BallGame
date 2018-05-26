package App.Game;

import App.Config.Configuration;
import App.Config.LevelLoader;
import App.Client.Windows.GameWindow;

import javax.swing.*;
import java.awt.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Tool to create new game levels
 */
public class TestGameClasses {
    LevelLoader levelLoader = new LevelLoader();

    public static void main(String[] args) {
//        TestGameClasses tgc = new TestGameClasses();
//        tgc.decodingTest();
//        System.out.println(getPropertyAsString(new Configuration().getScores("scoreboard")));
        Configuration config = new Configuration();
        Properties properties = new Properties();
        System.out.println(properties.toString());
        config.save(properties, "plik.xml");


    }




//    public static String getPropertyAsString(Properties prop) {
//        StringWriter writer = new StringWriter();
//        prop.list(new PrintWriter(writer));
//        String massage = writer.getBuffer().toString();
//        massage = massage.replace(System.getProperty("line.separator"), " ");
//        massage = massage.replaceFirst("-- listing properties --", "");
//        massage = massage.replace("=", " ");
//        return massage;
//    }

    //Tutorial en/decoding : https://howtodoinjava.com/core-java/serialization/xmlencoder-and-xmldecoder-example/

    void decodingTest(){
        Level level = levelLoader.loadLevel(1);

        System.out.println("Player :");
        System.out.println(level.getPlayer().getPlayerColor());
        System.out.println(level.getPlayer().getX());

        System.out.println(level.getGameObjects().get(0).getHeight());
        System.out.println(level.getGameObjects().get(2).getX());


    }


    /**
     * Used to create, preview and save levels
     */
    void encodingTest(){
        ArrayList<GameObject> gameObjects = new ArrayList<>();
        ArrayList<MovingObstacle> movingObstacles = new ArrayList<>();
        Player player = new Player(0.07,0.17, 0.08, 0.08, 0.001);
        Goal goal = new Goal(0.87,0.77,0.08,0.08);

        MovingObstacle mo = new MovingObstacle(0.48,0.53,0.03,0.1,0, 0.005);
        movingObstacles.add(mo);

        player.setPlayerColor(Color.GRAY);
        gameObjects.add(mo);
        gameObjects.add(goal);
        gameObjects.add(player);

        gameObjects.add(new Obstacle(0,0,1, 0.03));
        gameObjects.add(new Obstacle(0,0,0.03, 1));
        gameObjects.add(new Obstacle(0.97,0,0.03, 1));
        gameObjects.add(new Obstacle(0,0.97,1, 0.03));

        Level level = new Level();
        level.setMovingObstacles(movingObstacles);
        level.setGameObjects(gameObjects);
        level.setLevelNumber(2);// Set level number before saving
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
            panel.requestFocusInWindow();
        });


        //Uncomment to save the lvl
        //levelLoader.saveLevel(level);



    }

}
