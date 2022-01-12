package com.example.prj;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ChatWindow {
    @FXML
    private Button exitButton;
    @FXML
    private Button sendButton;
    @FXML
    private TextArea textArea;
    @FXML
    private TextField textField;

    public void exit(){
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}
