package App.Game;


import App.Windows.GameWindow;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private GameWindow windowParent;

    private double keySensitivity = 0.001;



    public KeyInput(GameWindow windowParent){
        this.windowParent = windowParent;

    }

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

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_SPACE)
            windowParent.pauseOrResume();
    }
}
