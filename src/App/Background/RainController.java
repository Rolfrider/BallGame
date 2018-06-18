package App.Background;

import App.Client.Windows.GameWindow;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.BlockingQueue;


public class RainController implements Runnable{
    private JPanel windowParent;
    private BlockingQueue<RainDrop> rainDrops;
    private boolean running;
    private final int TARGET_FPS = 60;
    private final long ONE_NS = 1000000000;
    private final long OPTIMAL_TIME = ONE_NS/TARGET_FPS;
    private long lastFpsTime;
    private int fps;

    /**
     *
     * @param windowParent
     * @param rainDrops
     */
    public RainController(JPanel windowParent, BlockingQueue<RainDrop> rainDrops){
        this.rainDrops = rainDrops;
        this.windowParent = windowParent;
        running = true;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    private void loop() {

        long lastLoopTime = System.nanoTime();
        while (running) {

            // work out how long its been since the last update, this
            // will be used to calculate how far the entities should
            // move this loop
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;
            double delta = updateLength / ((double) OPTIMAL_TIME);

            // update the frame counter
            lastFpsTime += updateLength;
            fps++;

            // update our FPS counter if a second has passed since
            // we last recorded
            if (lastFpsTime >= 1000000000) {
                System.out.println("(FPS: " + fps + ")");
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
            if((lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000 > 0) {
                try {
                    Thread.sleep((lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Moves all rain drops and deletes rain drops off the screen
     */
    private void update() {
        rainDrops.forEach( rainDrop -> {
            rainDrop.move();
            if(rainDrop.getY() > windowParent.getSize().height)
                rainDrops.remove(rainDrop);
        } );
        rainDrops.add(new RainDrop());
    }

    @Override
    public void run() {
        loop();
    }
}
