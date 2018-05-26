package App.Client;

import App.Config.Configuration;
import App.Config.NetProtocol;
import App.Game.Level;

import java.beans.XMLDecoder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Properties;
import java.util.StringTokenizer;

public class Client {

    private static int port;

    private static String host;

    private static int timeout;


    /**
     * Initialize values: host, port and timeout
     */
    private static void init(){
        Properties properties = new Configuration().getClientConfig();
        port = Integer.parseInt((String) properties.get("port"));
        host = (String) properties.get("host");
        timeout = Integer.parseInt((String) properties.get("timeout"));
    }


    /**
     * Connects to a server, sends command, picks up an answer
     * @param command command send to a server
     * @return Server answer as a one line String
     * @throws Exception
     */
    public static String connect(String command) throws Exception {
        init();
        String answer;
        Socket socket = new Socket(host, port);
        socket.setSoTimeout(timeout);
        BufferedReader input = new BufferedReader(new InputStreamReader(socket
                .getInputStream()));
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

        output.println(command);

        answer = input.readLine();

        input.close();
        output.close();
        socket.close();

        return answer;
    }

    /**
     * Connects to server and asks for a level data
     * @param num number of asked level
     * @return Level object, null if connection was unsuccessful
     */
    public static Level getLevel(int num){
        init();
        Level level = null;
        try {
            Socket socket = new Socket(host, port);
            socket.setSoTimeout(timeout);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket
                    .getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            output.println(NetProtocol.GETLEVEL + " " + num);

            if(input.readLine().equals(NetProtocol.LEVEL)) {
                XMLDecoder decoder = new XMLDecoder(socket.getInputStream());
                level = (Level) decoder.readObject();
                decoder.close();
            }

            input.close();
            output.close();
            socket.close();

        }catch (IOException ex){
            System.err.println(ex.getMessage());
        }

        return level;
    }

    /**
     * Creates get_score command and parses server answer to Properties
     * @return Properties containing scores
     */
    public static Properties getScores(){
        Properties properties = new Properties();
        String answer;
        try {
            answer = connect(NetProtocol.GETSCORES);
            StringTokenizer st = new StringTokenizer(answer);
            if(st.nextToken().equals(NetProtocol.SCORES)){
                int num = Integer.parseInt(st.nextToken());
                for (int i = 0; i < num; i++) {
                    properties.put(st.nextToken(), st.nextToken());
                }
            }
        }catch (Exception ex){
            System.err.println(ex.getMessage());
            return null;
        }

        return properties;
    }

    /**
     * Creates post command and parses server response
     * @param username
     * @param score
     * @return true if score has been added to scoreboard
     */
    public static boolean postScore(String username, int score){
        String answer;
        boolean updated = false;
        try {
            answer = connect(NetProtocol.POSTSCORE + " " +
            username + " " + score);
            StringTokenizer st = new StringTokenizer(answer);
            if(st.nextToken().equals(NetProtocol.SCOREUPDATED)){
                updated = Boolean.parseBoolean(st.nextToken());
            }
        }catch (Exception ex){
            System.err.println(ex.getMessage());
            return false;
        }
        return updated;
    }

    /**
     * Creates get_config command and parses server response to Properties
     * @return Properties containing configuration
     */
    public static Properties getConfig(){
        Properties properties = new Properties();
        String answer;
        try {
            answer = connect(NetProtocol.GETCONFIG);
            StringTokenizer st = new StringTokenizer(answer);
            if(st.nextToken().equals(NetProtocol.CONFIG)){
                int num = Integer.parseInt(st.nextToken());
                for (int i = 0; i < num; i++) {
                    properties.put(st.nextToken(), st.nextToken());
                }
            }
        }catch (Exception ex){
            System.err.println(ex.getMessage());
            return null;
        }

        return properties;
    }

}
