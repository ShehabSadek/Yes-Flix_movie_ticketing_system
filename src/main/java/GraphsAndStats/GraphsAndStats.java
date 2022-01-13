package GraphsAndStats;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class GraphsAndStats {

    public static void main(String[] args) {
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
