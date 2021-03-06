package com.example.prj;

import SessionHandler.SessionHandler;
import User.Client;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.SimpleStyleableIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewClients implements Initializable {
    @FXML
    private TableColumn<ProtUser, String> firstnameColumn;

    @FXML
    private TableColumn<ProtUser, String> idColumn;

    @FXML
    private TableColumn<ProtUser, String> isadminColumn;

    @FXML
    private TableColumn<ProtUser, String> lastnameColumn;

    @FXML
    private TableView<ProtUser> tableView;

    @FXML
    private TableColumn<ProtUser, String> usernameColumn;

    @FXML
    private Button editClient;

    @FXML
    private Button refresh;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        init();
    }

    public void onEditClient() throws IOException {
        ProtUser user = tableView.getSelectionModel().getSelectedItem();
        SessionHandler.currentEditingClient = user;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("editClient.fxml"));
        Parent root1 = (Parent)fxmlLoader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root1);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        Bounds boundsInScene = editClient.localToScene(editClient.getBoundsInLocal());
        stage.setX(boundsInScene.getCenterX());
        stage.setY(boundsInScene.getCenterY());
        stage.show();
    }

    public void onRefreshClicked(){
        init();
    }

    public void init(){
        firstnameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFirstname()));
        lastnameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getLastname()));
        usernameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUsername()));
        idColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getId()));
        isadminColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getIsAdmin()));
        ObservableList<ProtUser> list = FXCollections.observableArrayList();
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream("Clients.BIN"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(true){
            try{
                Client client = (Client)in.readObject();
                ProtUser user = new ProtUser(client.getUserName(), client.getFName(), client.getLName(), client.getEmail(), Integer.toString(client.getUserID()), "False");
                list.add(user);
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

        tableView.setItems(list);
    }
}
