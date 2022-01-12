package ChatSystem;

import com.example.prj.ChatWindow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class chatServer extends Thread {
    ServerSocket serverSocket;
    ArrayList<Socket> clientSockets;
    int port;
    boolean running;

    public chatServer(int port) throws IOException {
        this.port = port;
        serverSocket = new ServerSocket(port);
        clientSockets = new ArrayList<Socket>();
    }

    @Override
    public void run(){
        while(true){
            try {
                Socket socket = serverSocket.accept();
                PrintWriter pr = new PrintWriter(socket.getOutputStream(), true);
                pr.println("yes");
                clientSockets.add(socket);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void spawnServerThread(Socket socket){
        new Thread(() -> {
            try {
                PrintWriter pr = new PrintWriter(socket.getOutputStream());
                BufferedReader bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));


            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void broadcast(){

    }

    public static void main(String[] args) throws IOException {
        chatServer cs = new chatServer(6968);
        cs.start();
    }

}
