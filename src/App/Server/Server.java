package App.Server;

import App.Config.Configuration;
import App.Config.LevelLoader;
import App.Game.Level;

import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

/**
 * Game server
 */
public class Server extends JFrame implements Runnable{

    private ServerSocket serverSocket;

    private Configuration configuration = new Configuration();

    private static final int port = 40000;// temporary in future move to property

    public Server(ServerSocket serverSocket) {
        super("Server");
        this.serverSocket = serverSocket;
        JButton b = new JButton("stop and exit");
        b.addActionListener( actionEvent -> System.exit(0));
        add(b);
        pack();
        setVisible(true);
        new Thread(this).start();
    }

    public static void main(String[] args) {

        try {
            Server server = new Server(new ServerSocket(port));
        } catch (IOException e) {
            System.err.println("Unexpected error while starting the server." +
                    "Error message: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        while (true){
            try{
                Socket clientSocket = serverSocket.accept();
                Service clientService = new Service(clientSocket, this);
                addClientService(clientService);
            }catch (IOException ex){
                System.err.println("Unexpected error while accepting " +
                        "connection. Client cannot be served. Error massage :"
                        + ex.getMessage());
            }
        }
    }


    public String getScores(){
        Properties properties = configuration.getScores("scoreboard");
        String message = "" + properties.size();
        message += getPropertyAsString(properties);
        return message;
    }


    public String getConfig() {
        Properties properties = configuration.getSettings();
        String massage = "" + properties.size();
        massage += getPropertyAsString(properties);
        return massage;
    }

    public Level getLevel(int num){
        return new LevelLoader().loadLevel(num);
    }


    public String getPropertyAsString(Properties prop) {
        StringWriter writer = new StringWriter();
        prop.list(new PrintWriter(writer));
        String massage = writer.getBuffer().toString();
        massage = massage.replace(System.getProperty("line.separator"), " ");
        massage = massage.replaceFirst("-- listing properties --", "");
        massage = massage.replace("=", " ");
        System.err.println(massage);
        return massage;
    }


    /**
     * Creates new thread to service client
     * @param clientService
     * @throws IOException
     */
    private synchronized void addClientService(Service clientService)
            throws IOException{
        clientService.init();
        new Thread(clientService).start();
        System.out.println("Add new client");
    }


    /**
     * Saves score if it is good enough
     * @param username
     * @param score
     * @return
     */
    public synchronized boolean updateScores(String username, int score){
        Properties scores = configuration.getScores("scoreboard");
        if (scores.size() < 6){
            scores.put(username, score + "");
            configuration.saveScores(scores, "scoreboard");
            return true;
        }else {
            int minValue = Integer.MAX_VALUE;
            String minKey = "";
            for (String key : scores.stringPropertyNames()) {
                if (minValue > Integer.parseInt((String) scores.get(key))) {
                    minValue = Integer.parseInt((String) scores.get(key));
                    minKey = key;
                }
            }
            if (score > Integer.parseInt((String) scores.get(minKey))) {
                scores.remove(minKey);
                scores.put(username, score + "");
                configuration.saveScores(scores, "scoreboard");
                return true;
            }
        }
        return false;
    }


    public synchronized  void removeClientService(Service clientService){

        clientService.close();
        System.out.println("Removed client");
    }

}
