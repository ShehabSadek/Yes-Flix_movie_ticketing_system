package com.example.prj;

import Configuration.Configuration;
import Halls.Halls;
import Halls.Seats;
import Movie.Movie;
import SessionHandler.SessionHandler;
import Ticket.Ticket;
import User.Client;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.FileNotFoundException;
import java.io.IOException;
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
    protected void initialize() throws IOException, ClassNotFoundException {
        selectedSeats = new ArrayList<>();
        grid.getChildren().clear();
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
                if(!Halls.checkSeatAvailability(date, time, hall, seats[i][j])){button.setDisable(true);
                    button.setStyle("-fx-background-color: #de0000; -fx-opacity: 1;");
                }
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
            for (int id:
                 selectedSeats) {
                Ticket ticket = new Ticket(hall, hall.getSeat(id), date, time);
                ticket.setMovie(SessionHandler.currentMovie);
                Client client = SessionHandler.currentSignedInClient;
                client.addTicket(ticket);
            }
            try {
                Client client = SessionHandler.currentSignedInClient;
                Client.editClient(client);
                toPayment();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        grid.add(button, hall.getSeatColumns(), hall.getSeatRows());
    }
    @FXML
    protected void onDatePickerAction() throws IOException, ClassNotFoundException {
        initialize();
    }
    @FXML
    protected void onTimePickerAction() throws IOException, ClassNotFoundException {
        initialize();
    }
    protected void toPayment() throws IOException {
        Pane secPane = SessionHandler.GPane;
        if(secPane.getChildren() != null){
            secPane.getChildren().clear();
        }
        Pane newLoadedPane =  FXMLLoader.load(getClass().getResource("Reservations.fxml"));

        newLoadedPane.setPrefSize(secPane.getPrefWidth(), secPane.getPrefHeight());
        secPane.getChildren().add(newLoadedPane);
    }
}
