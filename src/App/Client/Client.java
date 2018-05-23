package App.Client;

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

    private static final int port = 40000;

    private static final String HOST = "192.168.1.13";

    private static final int timeout = 10000;


    public static String connect(String command) throws Exception {
        String answer;
        Socket socket = new Socket(HOST, port);
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

    public static Level getLevel(int num){
        Level level = null;
        try {
            Socket socket = new Socket(HOST, port);
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
