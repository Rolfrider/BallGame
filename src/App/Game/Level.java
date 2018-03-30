package App.Game;

import java.awt.*;
import java.util.ArrayList;

public class Level {
    private Player player;
    private Goal goal;
    private ArrayList<MovingObstacle> movingObstacles = new ArrayList<>();
    private ArrayList<GameObject> gameObjects = new ArrayList<>();
    private int levelNumber;

    public Level(){}



    public void moveLevel(){
        movingObstacles.forEach(MovingObstacle::move);
    }


    public void paintLevel(Graphics g, Dimension d){
        gameObjects.forEach( gameObject -> gameObject.paintObject(g,d));
    }



    //Getters and Setters
    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList<MovingObstacle> getMovingObstacles() {
        return movingObstacles;
    }

    public void setMovingObstacles(ArrayList<MovingObstacle> movingObstacles) {
        this.movingObstacles = movingObstacles;
    }

    public ArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }

    public void setGameObjects(ArrayList<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }
}
