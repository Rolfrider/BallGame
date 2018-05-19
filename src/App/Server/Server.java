package App.Server;

import App.Config.Configuration;
import App.Config.LevelLoader;
import App.Game.Level;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Properties;

public class Server implements Runnable{

    private ServerSocket serverSocket;

    private int lastID = -1;

    private Configuration configuration = new Configuration();

    private ArrayList<Service> clients = new ArrayList<>();

    private static final int port = 40000;// temporary in future move to property

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
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

    public synchronized int nextID(){
        return ++lastID;
    }

    public String getScores(){
        Properties properties = configuration.getScores("scoreboard");
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
        return massage;
    }


    private synchronized void addClientService(Service clientService)
            throws IOException{
        clientService.init();
        clients.add(clientService);
        new Thread(clientService).start();
        System.out.println("Add Client " + (lastID + 1) );
    }

    public synchronized  void removeClientService(Service clientService){
        clients.remove(clientService);
        clientService.close();
        System.out.println("Removed Client " + lastID-- );
    }
}
