package com.example.prj;

import Movie.Movie;
import SessionHandler.SessionHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MovieWin implements Initializable {
    @FXML
    ImageView v1;
    @FXML
    ImageView v2;
    @FXML
    ImageView v3;
    @FXML
    ImageView v4;
    @FXML
    ImageView v5;
    @FXML
    ImageView v6;
    @FXML
    Button b1; //Left
    @FXML
    Button b2;  //Right
    @FXML
    Pane secPane;
    @FXML
            AnchorPane ap;
    int moviesAt;
    ArrayList<Movie> movies= new ArrayList<>();
    public void ButtonLeft(){
        for (int i = moviesAt-1;i>-1;i--){
            if (i%6==0) {
                addImgView(i,v1);
                moviesAt--;
            }
            if (i%6==1) {
                addImgView(i,v2);
                moviesAt--;
            }
            if (i%6==2) {
                addImgView(i,v3);
                moviesAt--;
            }
            if (i%6==3) {
                addImgView(i,v4);
                moviesAt--;
            }
            if (i%6==4) {
                addImgView(i,v5);
                moviesAt--;
            }
            if (i%6==5) {
                addImgView(i,v6);
                moviesAt--;
            }
        }
    }
    public void ButtonRight(){
        for (int i = moviesAt;i<movies.size();i++){
            if (i%6==0) {
                addImgView(i,v1);
                moviesAt++;
            }
            if (i%6==1) {
                addImgView(i,v2);
                moviesAt++;
            }
            if (i%6==2) {
                addImgView(i,v3);
                moviesAt++;
            }
            if (i%6==3) {
                addImgView(i,v4);
                moviesAt++;
            }
            if (i%6==4) {
                addImgView(i,v5);
                moviesAt++;
            }
            if (i%6==5) {
                addImgView(i,v6);
                moviesAt++;
            }
        }
        if (moviesAt==movies.size()){
            for (int i=(moviesAt)%6;i<6;i++){
                if (i%6==0) {
                    clearImg(i%6,v1);
                }
                if (i%6==1) {
                    clearImg(i%6,v2);
                }
                if (i%6==2) {
                    clearImg(i%6,v3);
                }
                if (i%6==3) {
                    clearImg(i%6,v4);
                }
                if (i%6==4) {
                    clearImg(i%6,v5);
                }
                if (i%6==5) {
                    clearImg(i%6,v6);
                }
            }
        }
    }
    public void clearImg(int i,ImageView v){
        v.setImage(null);
        v.setFitHeight(200);
        v.setFitWidth(200);
        v.setPreserveRatio(true);
    }
    public void addImgView(int i,ImageView v){
            File file = new File(movies.get(i).getTrailer());;
            String path = file.toURI().toString();
            StringBuilder sb = new StringBuilder(path);
            int tmp = path.indexOf('@');
            sb.deleteCharAt(tmp);
            sb.deleteCharAt(tmp);
            Image image = new Image(sb.toString());
            v.setImage(image);
            v.setFitHeight(200);
            v.setFitWidth(200);
            v.setPreserveRatio(true);
            v.setPickOnBounds(true); // allows click on transparent areas
            v.setOnMouseClicked((MouseEvent e) -> {
                movies.get(i).numberOfTimesVisited++;
                try {
                    Movie.editMovie2(movies.get(i));
                    Pane secPane = SessionHandler.GPane;
                    if(secPane.getChildren() != null){
                        secPane.getChildren().clear();
                    }
                    SessionHandler.currentMovie=movies.get(i);
                    SplitPane newLoadedPane =  FXMLLoader.load(getClass().getResource("hall-view.fxml")); //TODO fix link between Movie & reservation
                    newLoadedPane.setPrefSize(secPane.getPrefWidth(), secPane.getPrefHeight());
                    secPane.getChildren().add(newLoadedPane);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

               });

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        moviesAt=0;
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream("Movies.BIN"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(true){
            try{
                Movie movie = (Movie)in.readObject();
                movies.add(movie);
            } catch (IOException | ClassNotFoundException e) {
                try {
                    in.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
            }
        }
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i=0;i<movies.size();i++){
            if (i==0) {
                addImgView(i,v1);
                moviesAt++;
            }
            if (i==1) {
                addImgView(i,v2);
                moviesAt++;
            }
            if (i==2) {
                addImgView(i,v3);
                moviesAt++;
            }
            if (i==3) {
                addImgView(i,v4);
                moviesAt++;
            }
            if (i==4) {
                addImgView(i,v5);
                moviesAt++;
            }
            if (i==5) {
                addImgView(i,v6);
                moviesAt++;
            }
        }

    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Movie m1 =new Movie("Inception",120,"Some inception happens", 4.2f,"Future, Gore","@/src/main/resources/imgs/Inception.png");
        Movie m2 =new Movie("The Matrix",120,"Computer guy gets to be the one", 4.2f,"Fantasy, Sci-Fi","@/src/main/resources/imgs/TheMatrix.png");
        Movie m3 =new Movie("Transformers",120,"Cars become robots", 4.2f,"Lols","@/src/main/resources/imgs/Transformers.png");
        Movie m4 =new Movie("blfobl",120,"Some descttnbjiha", 4.2f,"Lols","@/src/main/resources/imgs/Transformers.png");
        Movie m5 =new Movie("blfsfobl",120,"Some descttnbjiha", 4.2f,"Lols","@/src/main/resources/imgs/Inception.png");
        //Movie m6 =new Movie("ay 7aga",120,"Some descttnbjiha", 4.2f,"Lols","@/src/main/resources/imgs/Inception.png");
        //Movie m7 =new Movie("ay 7aga",120,"Some descttnbjiha", 4.2f,"Lols","@/src/main/resources/imgs/Transformers.png");
        //Movie m8 =new Movie("ay 7aga",120,"Some descttnbjiha", 4.2f,"Lols","@/src/main/resources/imgs/Transformers.png");

        m1.writeMovie();
        m2.writeMovie();
        m3.writeMovie();
        m4.writeMovie();
        m5.writeMovie();
        //m6.writeMovie();
        //m7.writeMovie();
        //m8.writeMovie();
        Movie.readMovie();
    }



}
