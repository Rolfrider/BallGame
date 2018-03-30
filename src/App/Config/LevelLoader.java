package App.Config;

import App.Game.Level;

import java.beans.ExceptionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class LevelLoader {

    private final String PATH = "src/Config/Levels/level";

    public Level loadLevel(int levelNumber){
        Level level = new Level();

        try {
            FileInputStream fis = new FileInputStream(PATH + levelNumber +".xml");
            XMLDecoder decoder = new XMLDecoder(fis);
            level = (Level) decoder.readObject();
            decoder.close();
            fis.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return level;
    }

    public void saveLevel(Level level){
        try {
            FileOutputStream fos = new FileOutputStream(PATH + level.getLevelNumber() + ".xml");
            XMLEncoder encoder = new XMLEncoder(fos);
            encoder.setExceptionListener(new ExceptionListener() {
                @Override
                public void exceptionThrown(Exception e) {
                    System.out.println(e.getMessage());
                }
            });
            encoder.writeObject(level);
            encoder.close();
            fos.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
