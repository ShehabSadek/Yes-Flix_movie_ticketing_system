package com.example.prj;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import Movie.Movie;

import java.io.IOException;

public class MainWindowAdmin {
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
    @FXML Button viewClients;
    @FXML
    Pane pane;
    @FXML
    Button stats;

    public void openChatWindow(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SelectChat.fxml"));
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

    public void onViewClientPushed() throws IOException {
        Pane pane2 = FXMLLoader.load(getClass().getResource("ViewClients.fxml"));
        pane.getChildren().add(pane2);

    }
    public void onMoviesPushed() throws IOException {
        Pane pane2 = FXMLLoader.load(getClass().getResource("AddMovie.fxml"));
        pane.getChildren().add(pane2);

    }

    public void onStatsClicked() throws IOException, ClassNotFoundException {
        Movie.readMovie(234);
        Pane pane2 = FXMLLoader.load(getClass().getResource("Stats.fxml"));
        pane.getChildren().add(pane2);
    }
}
