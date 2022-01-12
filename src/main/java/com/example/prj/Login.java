package com.example.prj;

import SessionHandler.SessionHandler;
import User.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Login {
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordPasswordField;
    @FXML
    private Button Login;
    @FXML
    private Button SignUp;
    @FXML
    private Button exitButton;
    @FXML
    private Label warningLabel1;
    @FXML
    private Label warningLabel2;

    public void changeWindowToSignUp(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Signup.fxml"));
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void LoginClient(ActionEvent e) throws IOException {
        int n = 4;
        if(usernameTextField.getText().length() >= n && passwordPasswordField.getText().length() >= n){
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("Clients.BIN"));
            while(true){
                try{
                    Client client = (Client)in.readObject();
                    if(client.getUserName().equals(usernameTextField.getText())){
                        warningLabel1.setText("");
                        if(client.getPassword().equals(passwordPasswordField.getText())){
                            warningLabel2.setText("");
                            SessionHandler.currentSignedInClient = client;
                            in.close();
                            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MainWindow.fxml"));
                            Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
                            Scene scene = new Scene(fxmlLoader.load());
                            stage.setScene(scene);
                            stage.centerOnScreen();
                            stage.show();
                            return;
                        }
                        else{
                            warningLabel2.setText("Incorrect Password");
                        }
                    }
                    else{
                        warningLabel1.setText("No user with this Username exits");
                    }
                } catch (EOFException | ClassNotFoundException ee) {
                    in.close();
                    break;

                }
            }
            in.close();
        }

    }

    public void exitWindow(ActionEvent e){
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}
