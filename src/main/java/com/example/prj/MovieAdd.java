package com.example.prj;

import Movie.Movie;
import SessionHandler.SessionHandler;
import User.Client;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class MovieAdd {
@FXML
    Button Confirm;
@FXML
    Button Browse;
@FXML
    ImageView choosenImg;
@FXML
    TextField title;
@FXML
    TextField duration;
@FXML
    TextField score;
@FXML
    TextField genres;
@FXML
    TextArea description;
@FXML
    Label warningLabel;
@FXML
    Label warningLabel2;
@FXML
    Label warningLabel3;
@FXML
    Label warningLabel4;
@FXML
    Label warningLabel5;
@FXML
    AnchorPane pane;
File file;
public void Confirm() throws IOException, ClassNotFoundException {
    int n=2;
    if(title.getText().length() < n){
        warningLabel.setText("X");
    }
    else{
        warningLabel.setText("");
    }
    if(duration.getText().length() < n){
        warningLabel2.setText("X");
    }
    else{
        warningLabel2.setText("");
    }
    if(score.getText().length() < n){
        warningLabel3.setText("X");
    }
    else{
        warningLabel3.setText("");
    }
    if(genres.getText().length() < n){
        warningLabel4.setText("X");
    }
    else{
        warningLabel4.setText("");
    }
    if(description.getText().length() < n){
        warningLabel5.setText("X");
    }
    else{
        warningLabel5.setText("");
    }
    if(title.getText().length() >= n && duration.getText().length() >=n && score.getText().length() >= n && genres.getText().length() >= n && description.getText().length() >= n){
        float scor = Float.parseFloat(score.getText());
        int dur = Integer.parseInt(duration.getText());
        Movie movie = new Movie(title.getText(),dur,description.getText(),scor,genres.getText(), file.getPath()); //TODO FIle chooser crashes
        movie.writeMovie();
    }
}
public void passPath(){
    FileChooser fs = new FileChooser();
    fs.setTitle("Pick image for movie banner");
    Stage stage = (Stage) pane.getScene().getWindow();
    file = fs.showOpenDialog(stage);
}
}
