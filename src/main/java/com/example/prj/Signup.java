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
import javafx.stage.StageStyle;

import java.io.IOException;

public class Signup {
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordPasswordField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField FirstNameTextField;
    @FXML
    private TextField LastNameTextField;
    @FXML
    private Button signUpButton;
    @FXML
    private Button signInButton;
    @FXML
    private Label warningLabel1;
    @FXML
    private Label warningLabel2;
    @FXML
    private Label warningLabel3;
    @FXML
    private Label warningLabel4;
    @FXML
    private Label warningLabel5;



    public void changeToSignInWindow(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Login.fxml"));
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void SignUp(ActionEvent e) throws IOException, ClassNotFoundException {
        int n = 4;
        if(usernameTextField.getText().length() < n){
            warningLabel1.setText("X");
        }
        else{
            warningLabel1.setText("");
        }
        if(FirstNameTextField.getText().length() < n){
            warningLabel2.setText("X");
        }
        else{
            warningLabel2.setText("");
        }
        if(LastNameTextField.getText().length() < n){
            warningLabel3.setText("X");
        }
        else{
            warningLabel3.setText("");
        }
        if(emailTextField.getText().length() < n){
            warningLabel4.setText("X");
        }
        else{
            warningLabel4.setText("");
        }
        if(passwordPasswordField.getText().length() < n){
            warningLabel5.setText("X");
        }
        else{
            warningLabel5.setText("");
        }

        if(usernameTextField.getText().length() >= n && FirstNameTextField.getText().length() >= n && LastNameTextField.getText().length() >= n && emailTextField.getText().length() >= n && passwordPasswordField.getText().length() >= n){
            Client client = new Client(usernameTextField.getText(), FirstNameTextField.getText(), LastNameTextField.getText(), passwordPasswordField.getText(), emailTextField.getText());
            client.writeClient();
            SessionHandler.currentSignedInClient = client;
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MainWindow.fxml"));
            Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        }


    }
}
