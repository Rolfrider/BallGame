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

        output.println(NetProtocol.LOGIN);

        StringTokenizer st = new StringTokenizer(input.readLine());

        String massage = st.nextToken();

        if (massage.equals(NetProtocol.LOGGEDIN)) {
            System.out.println("Successfully logged in with " + st.nextToken() + " id.");
            output.println(command);
            answer = input.readLine();
            output.println(NetProtocol.LOGOUT);

            input.close();
            output.close();
            socket.close();

            return answer;
        } else {

            input.close();
            output.close();
            socket.close();

            throw new Exception("Unexpected error while connecting to the server");
        }
    }

    public static Level getLevel(int num){
        Level level = null;
        try {
            Socket socket = new Socket(HOST, port);
            socket.setSoTimeout(timeout);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket
                    .getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            output.println(NetProtocol.LOGIN);

            StringTokenizer st = new StringTokenizer(input.readLine());

            String massage = st.nextToken();

            if (massage.equals(NetProtocol.LOGGEDIN)) {
                System.out.println("Successfully logged in with " + st.nextToken() + " id.");
                output.println(NetProtocol.GETLEVEL + " " + num);
                if(input.readLine().equals(NetProtocol.LEVEL)) {
                    XMLDecoder decoder = new XMLDecoder(socket.getInputStream());
                    level = (Level) decoder.readObject();
                    decoder.close();
                }
                input.close();
                output.close();
                socket.close();

            } else {
                input.close();
                output.close();
                socket.close();
            }
        }catch (IOException ex){
            System.out.println(ex.getMessage());
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
            System.out.println(ex.getMessage());
            return null;
        }

        return properties;
    }

}
