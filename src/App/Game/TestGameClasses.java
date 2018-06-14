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
        TestGameClasses tgc = new TestGameClasses();
        tgc.encodingTest();


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
        Player player = new Player(0.07,0.17, 0.16, 0.16, 0.001);
        player.setSpriteID("src/Config/Resources/batman_");
        Goal goal = new Goal(0.87,0.77,0.16,0.16);
        goal.setSpriteID("src/Config/Resources/door.png");

        MovingObstacle mo = new MovingObstacle(0.48,0.53,0.03,0.03,-0.005, 0.005);
        MovingObstacle mo1 = new MovingObstacle(0.22,0.23,0.03,0.03,-0.005, 0.005);
        MovingObstacle mo2 = new MovingObstacle(0.28,0.83,0.03,0.03,0.005, -0.005);
        MovingObstacle mo3 = new MovingObstacle(0.68,0.73,0.03,0.03,0.005, -0.005);
        movingObstacles.add(mo);
        movingObstacles.add(mo1);
        movingObstacles.add(mo2);
        movingObstacles.add(mo3);

        player.setPlayerColor(Color.GRAY);
        gameObjects.add(mo);
        gameObjects.add(mo1);
        gameObjects.add(mo2);
        gameObjects.add(mo3);
        gameObjects.add(goal);
        gameObjects.add(player);

        gameObjects.add(new Obstacle(0,0,1, 0.03));
        gameObjects.add(new Obstacle(0,0,0.03, 1));
        gameObjects.add(new Obstacle(0.97,0,0.03, 1));
        gameObjects.add(new Obstacle(0,0.97,1, 0.03));

        gameObjects.forEach(gameObject -> {
            if(gameObject instanceof Obstacle){
                gameObject.setSpriteID("src/Config/Resources/brick_wall.gif");
            }
        });

        Level level = new Level();
        level.setMovingObstacles(movingObstacles);
        level.setGameObjects(gameObjects);
        level.setLevelNumber(4);// Set level number before saving
        level.setPlayer(player);
        level.setGoal(goal);


//        JFrame frame = new JFrame();
//        JPanel panel = new GameWindow();
//        ((GameWindow) panel).setLevel(level);
//        ((GameWindow) panel).getLevel().init();
//        frame.add(panel);
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        EventQueue.invokeLater(() -> {
//            frame.pack();
//            frame.setVisible(true);
//            panel.requestFocusInWindow();
//        });


        //Uncomment to save the lvl
        levelLoader.saveLevel(level);



    }

}
