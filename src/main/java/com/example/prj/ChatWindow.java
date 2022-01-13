package com.example.prj;

import ChatSystem.chatClient;
import ChatSystem.chatServer;
import SessionHandler.SessionHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChatWindow implements Initializable {
    @FXML
    private Button exitButton;
    @FXML
    private Button sendButton;
    @FXML
    private TextArea textArea;
    @FXML
    private TextField textField;
    @FXML
    private Label chatLabel;
    @FXML
    private Label warningLabel;
    chatClient chatCl = new chatClient("127.0. 0.1", 6969, "root");

    private boolean running;

    public ChatWindow() throws IOException {
    }

    public void exit(){
        running = false;
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    public void send() throws IOException {
        PrintWriter pr = new PrintWriter(chatCl.getSocket().getOutputStream(), true);
        pr.println(SessionHandler.currentChatWithClient + " " + textField.getText());
        textArea.appendText(textField.getText() + "  ::Sent\n");
        textField.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chatLabel.setText("Chatting with " + SessionHandler.currentChatWithClient);
        running = true;
        new Thread(() -> {
            while(true){
                try {
                    while(running){
                        BufferedReader bf = new BufferedReader(new InputStreamReader(chatCl.getSocket().getInputStream()));
                        String msg = bf.readLine();
                        if(msg.substring(0, msg.indexOf(' ')).equals(SessionHandler.currentSignedInClient.getUserName())){
                            textArea.appendText(msg.substring(msg.indexOf(' '), msg.length()) + " :: Received\n");
                        }
                        //textArea.appendText(msg + " :: Received\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
