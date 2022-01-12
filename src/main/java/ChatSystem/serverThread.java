package ChatSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.ArrayList;

public class serverThread extends Thread {
    private Socket socket;
    private ArrayList<serverThread> serverThreads;
    public PrintWriter output;
    private BufferedReader input;

    public serverThread(Socket socket, ArrayList<serverThread> serverThreads) {
        this.socket = socket;
        this.serverThreads = serverThreads;
    }

    @Override
    public void run(){
        try {
            output = new PrintWriter(socket.getOutputStream(), true);

            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while(true){
                String msg = input.readLine();
                broadcastMessage(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void broadcastMessage(String msg){
        for(serverThread S : serverThreads){
            S.output.println(msg);
        }
    }
}
