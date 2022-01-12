package ChatSystem;

import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class chatClient {
    Socket socket;
    int port;
    String ip;
    String id;

    public chatClient(String ip, int port, String id) throws IOException {
        this.port = port;
        this.id = id;
        this.ip = ip;
        this.socket = new Socket("localhost", port);
    }

    public Socket getSocket() {
        return socket;
    }

    public int getPort() {
        return port;
    }

    public String getIp() {
        return ip;
    }

    public String getId() {
        return id;
    }

    public void sendMessage(String message) throws IOException {
        PrintWriter pr = new PrintWriter(socket.getOutputStream(), true);
        pr.println(id + " " + message);
    }

    public String receiveMessage() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String mssg = bf.readLine();
        bf.close();
        return mssg;
    }


}
