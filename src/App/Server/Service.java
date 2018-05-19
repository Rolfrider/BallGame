package App.Server;

import App.Config.NetProtocol;
import App.Game.Level;

import java.beans.ExceptionListener;
import java.beans.XMLEncoder;
import java.io.*;
import java.net.Socket;
import java.util.StringTokenizer;

public class Service implements Runnable {

    private int id;

    private String username;

    private Socket clientSocket;

    private Server server;

    private BufferedReader input;

    private PrintWriter output;


    public Service(Socket clientSocket, Server server) {
        this.clientSocket = clientSocket;
        this.server = server;
    }

    void init() throws IOException {
        Reader reader = new InputStreamReader(clientSocket.getInputStream());
        input = new BufferedReader(reader);
        output = new PrintWriter(clientSocket.getOutputStream(), true);
    }

    public void close() {
        try {
            output.close();
            input.close();
            clientSocket.close();
        } catch (IOException e) {
            System.err.println("Error closing client (" + id + ").");
        } finally {
            output = null;
            input = null;
            clientSocket = null;
        }
    }

    @Override
    public void run() {
        while (true){
            String request = receive();
            System.out.println(request);
            StringTokenizer st = new StringTokenizer(request);
            String command = st.nextToken();
            if(command.equals(NetProtocol.LOGIN)){
                send(NetProtocol.LOGGEDIN + " " + (id = server.nextID()));
            } else if(command.equals(NetProtocol.GETSCORES)){
                send(NetProtocol.SCORES + " " +
                        server.getScores());
            }else if(command.equals(NetProtocol.GETLEVEL)){
                send(NetProtocol.LEVEL);
                sendLevel(server.getLevel(Integer.parseInt(st.nextToken())));
                break;
            }else if(command.equals(NetProtocol.LOGOUT)){
                send(NetProtocol.LOGGEDOUT);
                break;
            }else if(command.equals(NetProtocol.STOPPED)){
                break;
            } else if(command.equals(NetProtocol.STOPPED)){
                break;
            } else if(command.equals(NetProtocol.NULLCOMMAND)){
                break;
            }

        }

        server.removeClientService(this);
    }

    private String receive() {
        try {
            return input.readLine();
        } catch (IOException e) {
            System.err.println("Error reading client (" + id + ").");
            System.err.println(e.getMessage());
        }
        return NetProtocol.NULLCOMMAND;
    }

    void sendLevel(Level level){
        try {
            XMLEncoder encoder = new XMLEncoder(clientSocket.getOutputStream());
            encoder.setExceptionListener(new ExceptionListener() {
                @Override
                public void exceptionThrown(Exception e) {
                    System.out.println(e.getMessage());
                }
            });
            encoder.writeObject(level);
            encoder.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    void send(String command) {
        output.println(command);
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}
