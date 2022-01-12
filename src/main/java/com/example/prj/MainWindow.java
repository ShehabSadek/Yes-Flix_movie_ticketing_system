package com.example.prj;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindow implements Initializable {
    @FXML
    private Button home;
    @FXML
    private Button Movies;
    @FXML
    private Button Reservations;
    @FXML
    private Button Account;
    @FXML
    private Button Logout;
    @FXML
    private Button Exit;
    @FXML
    private ChoiceBox<String> dropDownMenu;
    @FXML Button chat;
    @FXML
    Pane secPane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        String[] items = {"Dashboard", "Chat","Request Admin Access", "LogOut", "Exit"};
//        dropDownMenu.getItems().addAll(items);
    }

    public void openChatWindow(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ChatWindow.fxml"));
        Parent root1 = (Parent)fxmlLoader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root1);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        Bounds boundsInScene = chat.localToScene(chat.getBoundsInLocal());
        stage.setX(boundsInScene.getCenterX() - 85);
        stage.setY(boundsInScene.getCenterY() - 300);
        stage.show();
    }
    public void exitMainWindow(ActionEvent e){
        Stage stage = (Stage) Exit.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void onButton1() throws IOException {
        if(secPane.getChildren() != null){
            secPane.getChildren().clear();
        }
        SplitPane newLoadedPane =  FXMLLoader.load(getClass().getResource("hall-view.fxml"));

        newLoadedPane.setPrefSize(secPane.getPrefWidth(), secPane.getPrefHeight());
        secPane.getChildren().add(newLoadedPane);
    }
    public void onButton2() throws IOException {
        if(secPane.getChildren() != null){
            secPane.getChildren().clear();
        }
        Pane newLoadedPane =  FXMLLoader.load(getClass().getResource("ChatWindow.fxml"));
        secPane.getChildren().add(newLoadedPane);
    }

}
