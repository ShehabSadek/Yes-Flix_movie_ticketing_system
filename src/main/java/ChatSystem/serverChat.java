package ChatSystem;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class serverChat extends Thread{
    ArrayList<serverThread> serverThreads = new ArrayList<>();
    ServerSocket serverSocket;
    int port;

    public serverChat(int port) throws IOException {
        this.port = port;
        serverSocket = new ServerSocket(port);
    }

    @Override
    public void run(){
        while(true){
            try {
                Socket socket = serverSocket.accept();
                serverThread SThread = new serverThread(socket, serverThreads);
                serverThreads.add(SThread);
                SThread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        serverChat S = new serverChat(6969);
        S.start();
    }
}
