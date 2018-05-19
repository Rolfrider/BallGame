package App.Game;


import App.Client.Windows.GameWindow;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Object responsible for processing key input
 */
public class KeyInput extends KeyAdapter {

    private GameWindow windowParent;

    private final double keySensitivity = 0.001;

    /**
     * Assigns window parent
     * @param windowParent
     */
    public KeyInput(GameWindow windowParent){
        this.windowParent = windowParent;
    }

    /**
     * Changes key input into ball movement
     * @param e key pressed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        Player player = windowParent.getLevel().getPlayer();
        if(key == KeyEvent.VK_UP)
            player.setVelY(player.getVelY() - keySensitivity);
        if(key == KeyEvent.VK_DOWN)
            player.setVelY(player.getVelY() + keySensitivity);
        if(key == KeyEvent.VK_RIGHT)
            player.setVelX(player.getVelX() + keySensitivity);
        if(key == KeyEvent.VK_LEFT)
            player.setVelX(player.getVelX() - keySensitivity);

    }

    /**
     * Pauses or resumes game based on key release
     * @param e key released
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_SPACE)
            windowParent.pauseOrResume();
    }
}
