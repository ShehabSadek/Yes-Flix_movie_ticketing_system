package com.example.prj;

import Halls.Halls;
import Halls.Seats;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.FileNotFoundException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;

public class HallsController {
    @FXML
    private GridPane grid = new GridPane();
    @FXML
    AnchorPane root = new AnchorPane();
    @FXML
    DatePicker datePicker = new DatePicker();
    @FXML
    ComboBox comboBox = new ComboBox();
    ArrayList<Integer> selectedSeats = null;
    @FXML
    protected void initialize() throws FileNotFoundException {
        selectedSeats = new ArrayList<>();
        String[] arr = {"12:00", "2:00", "4:00", "6:00", "8:00", "10:00"};
        if(datePicker.getValue() == null){
            datePicker.setValue(LocalDate.now());
        }
        if(comboBox.getValue() == null){
            comboBox.getItems().addAll(arr);
            comboBox.setValue(arr[0]);
        }
        java.sql.Date date = java.sql.Date.valueOf(datePicker.getValue());
        Time time = Time.valueOf((String) comboBox.getValue() + ":00");
        Halls hall = new Halls(5, 5);
        grid.prefWidthProperty().bind(grid.widthProperty());
        grid.prefHeightProperty().bind(grid.heightProperty());
        grid.setPadding(new Insets(5));
        grid.setHgap(5);
        grid.setVgap(5);
        Seats[][] seats = hall.getHallSeats();
        int seatID = 0;
        for(int i = 0; i< hall.getSeatRows(); i++){
            for(int j = 0; j<hall.getSeatColumns(); j++){
                Button button = new Button();
                int seatNum = seats[i][j].getSeatNumber();
                button.setText(String.valueOf(seatNum));
                button.setId(String.valueOf(seatNum));
                button.setMinSize(100, 100);
                button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
//                if(!Halls.checkSeatAvailability(date, time, hall, seats[i][j])){button.setDisable(true);
//                    button.setStyle("-fx-background-color: #de0000; -fx-opacity: 1;");
//                }
                button.setOnAction(actionEvent -> {
                    if(button.getStyle() != "-fx-background-color: #2ddc2d;"){
                        button.setStyle("-fx-background-color: #2ddc2d;");
                        selectedSeats.add(Integer.parseInt(button.getId()));
                    }
                    else{
                        button.setStyle("");
                        selectedSeats.remove(Integer.valueOf(Integer.parseInt(button.getId())));
                    }
                });

                grid.add(button, j, i);
            }
        }
        Button button = new Button("Next");
        button.setOnAction(actionEvent -> {
            System.out.println(selectedSeats);
            for (int id:
                 selectedSeats) {
//                Ticket ticket = new Ticket(hall, hall.getSeat(id), date, time);
            }
//            Ticket ticket = new Ticket(ha)
        });
        grid.add(button, hall.getSeatColumns(), hall.getSeatRows());
    }
    @FXML
    protected void onDatePickerAction() throws FileNotFoundException {
        System.out.println(datePicker.getValue());
        initialize();
    }
    @FXML
    protected void onTimePickerAction() throws FileNotFoundException {
        System.out.println(comboBox.getValue());
        initialize();
    }

}
