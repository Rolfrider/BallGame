package App.Game;

import App.Client.Windows.GameWindow;

/**
 * Main game loop
 */
public class GameLoop implements Runnable{

    private GameWindow windowParent;
    private boolean running, pause;
    private final int TARGET_FPS = 60;
    private final long ONE_NS = 1000000000;
    private final long OPTIMAL_TIME = ONE_NS/TARGET_FPS;
    private long lastFpsTime;
    private int fps;

    /**
     * Sets active windowParent
     * @param windowParent
     */
    public GameLoop(GameWindow windowParent){
        this.windowParent = windowParent;
        running = true;
        pause = true;
    }

    /**
     * Checks if the loop is paused
     * @return Boolean indicating pause
     */
    public boolean isPause() {
        return pause;
    }

    /**
     * Pauses loop
     */
    public synchronized void pause(){
        pause = true;
    }

    /**
     * Resumes loop
     */
    public synchronized void resume(){
        pause = false;
        notify();
    }

    /**
     * Waiting for loop to resume
     */
    private synchronized void Paused(){
        try{
            if (pause)
                wait();
        }catch (InterruptedException ex){
            ex.getMessage();
        }
    }

    /**
     * Runs loop
     */
    @Override
    public void run() {
        loop();
    }

    /**
     * Loop main body
     */
    // More info : http://www.java-gaming.org/index.php?topic=24220.0
    private void loop(){

        long lastLoopTime = System.nanoTime();
        while (running){

            Paused();

            // work out how long its been since the last update, this
            // will be used to calculate how far the entities should
            // move this loop
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;
            double delta = updateLength / ((double)OPTIMAL_TIME);

            // update the frame counter
            lastFpsTime += updateLength;
            fps++;

            // update our FPS counter if a second has passed since
            // we last recorded
            if (lastFpsTime >= 1000000000)
            {
                System.out.println("(FPS: "+fps+")");
                lastFpsTime = 0;
                fps = 0;
            }

            // update the game logic
            update();

            // draw everything
            windowParent.repaint();

            // we want each frame to take 10 milliseconds, to do this
            // we've recorded when we started the frame. We add 10 milliseconds
            // to this and then factor in the current time to give
            // us our final value to wait for
            // remember this is in ms, whereas our lastLoopTime etc. vars are in ns.
            try {
                Thread.sleep( (lastLoopTime-System.nanoTime() + OPTIMAL_TIME)/1000000 );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Updates game status
     */
    public void update(){
        if(windowParent.getLevel().reachedGoal()) {
            System.out.println("reached goal");
            running = false;
            windowParent.levelUp();
        }
        if(windowParent.getLevel().playerHitObstacle()) {
            System.out.println("hit obstacle");
            running = false;
            windowParent.die();
        }
        windowParent.getLevel().obstacleHitObstacle();
        windowParent.getLevel().moveLevel();
    }


}
