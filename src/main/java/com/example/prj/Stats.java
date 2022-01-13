package com.example.prj;

import Movie.Movie;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Stats implements Initializable {
    @FXML
    private BarChart<String, Integer> BC;

    String[] movies = {" ", " ", " "};
    int[] values = {0, 0, 0};


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Movie");
        yAxis.setLabel("Visitation");

        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream("Movies.BIN"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        while(true){
            try{
                Movie movie = (Movie)in.readObject();
                if(movie.getNumberOfTimesVisited() > values[0]){
                    values[0] = movie.getNumberOfTimesVisited();
                    movies[0] = movie.getName();
                    continue;
                }
                if(movie.getNumberOfTimesVisited() > values[1]){
                    values[1] = movie.getNumberOfTimesVisited();
                    movies[1] = movie.getName();
                    continue;
                }
                if(movie.getNumberOfTimesVisited() > values[2]){
                    values[2] = movie.getNumberOfTimesVisited();
                    movies[2] = movie.getName();
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

        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();
        XYChart.Series series3 = new XYChart.Series();
        series1.setName(movies[0]);
        series2.setName(movies[1]);
        series3.setName(movies[2]);
        series1.getData().add(new XYChart.Data(movies[0], values[0]));
        series2.getData().add(new XYChart.Data(movies[1], values[1]));
        series3.getData().add(new XYChart.Data(movies[2], values[2]));
//        for(int i = 0; i < 3; i++){
//            series1.getData().add(new XYChart.Data(movies[i], values[i]));
//        }

        BC.getData().addAll(series1, series2, series3);
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Movie m1 =new Movie("Zeft",120,"Some inception happens", 4.2f,"Future, Gore","@/src/main/resources/imgs/Inception.png");
        //m1.writeMovie();
        m1.setScore(69);
        Movie.editMovie2(m1);
        Movie.readMovie();
    }
}
