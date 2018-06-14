package App.Client.Windows;

import App.Background.RainController;
import App.Background.RainDrop;
import App.Client.WindowManager;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class CustomWindow extends JPanel {
    protected WindowManager windowParent;
    private BlockingQueue<RainDrop> rainDrops = new LinkedBlockingQueue<>();
    private RainController rainController;


    public CustomWindow(WindowManager windowParent){
        super();
        this.windowParent = windowParent;

        setBackground(Color.BLACK);
        setLayout(new GridBagLayout());

    }

    protected abstract void placeComponents() ;

    public void startRaining(){
        rainDrops.add(new RainDrop());
        rainController = new RainController(this, rainDrops);
        Thread thread = new Thread(rainController);
        thread.start();
    }

    public void stopRaining(){
        rainController.setRunning(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        rainDrops.forEach(rainDrop -> rainDrop.paintObject(g, getSize()));
    }
}
