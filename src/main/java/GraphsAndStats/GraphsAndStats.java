package GraphsAndStats;

import Movie.Movie;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class GraphsAndStats {


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Double> BC;
        xAxis.setLabel("Movie");
        yAxis.setLabel("Visitation");





        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Frequency");
        series1.getData().add(new XYChart.Data("Movie1", 69.420));
    }
}
