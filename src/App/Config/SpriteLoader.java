package App.Config;


import javax.swing.*;
import java.awt.*;

/**
 * Loads images
 */
public class SpriteLoader {

    public static Image loadSprite(String id){
        ImageIcon image = new ImageIcon(id);
        return image.getImage();
    }
}
