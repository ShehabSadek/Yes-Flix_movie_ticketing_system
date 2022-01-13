package com.example.prj;

import SessionHandler.SessionHandler;
import Ticket.Ticket;
import User.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.util.ArrayList;

public class Reservations {
    @FXML
    ListView listView = new ListView();
    public void initialize(){
        Client current = SessionHandler.currentSignedInClient;
        ArrayList<String> arrayList = new ArrayList<>();
        System.out.println(current.tickets);
        for (Ticket ticket:
             current.tickets) {
            arrayList.add(ticket.toString());
        }
        listView.setItems(FXCollections.observableArrayList(arrayList));
    }
    @FXML
    public void onDeleteClicked() throws IOException {
        Client current = SessionHandler.currentSignedInClient;
        if(listView.getSelectionModel() != null){
        int index = listView.getSelectionModel().getSelectedIndex();
        if(index != -1){
        current.tickets.remove(index);
        Client.editClient(current);
        initialize();}
        }
    }
}
