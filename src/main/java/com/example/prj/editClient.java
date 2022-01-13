package com.example.prj;

import SessionHandler.SessionHandler;
import User.Client;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class editClient implements Initializable {
    @FXML
    TextField username;
    @FXML
    TextField firstname;
    @FXML
    TextField lastname;
    @FXML
    TextField email;
    @FXML
    Button exit;
    @FXML
    Button edit;

    Client cl;


    public void exit(){
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream("Clients.BIN"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(true){
            try{
                Client client = (Client)in.readObject();
                if(client.getUserID() == Integer.parseInt(SessionHandler.currentEditingClient.getId())){
                    cl = client;
                    username.setText(client.getUserName());
                    firstname.setText(client.getFName());
                    lastname.setText(client.getLName());
                    email.setText(client.getEmail());
                }
            } catch (EOFException e) {
                try {
                    in.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onEditClicked() throws IOException {
        cl.setUserName(username.getText());
        cl.setEmail(email.getText());
        cl.setFName(firstname.getText());
        cl.setLName(lastname.getText());
        Client.editClient(cl);
        exit();
    }

    public void init(){

    }
}
