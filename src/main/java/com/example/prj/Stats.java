package com.example.prj;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ResourceBundle;

public class Stats implements Initializable {
    @FXML
    private BarChart<String, Integer> BC;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Movie");
        yAxis.setLabel("Visitation");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Frequency");
        series1.getData().add(new XYChart.Data("Movie1", 69.420));

        BC.getData().addAll(series1);
    }
}
