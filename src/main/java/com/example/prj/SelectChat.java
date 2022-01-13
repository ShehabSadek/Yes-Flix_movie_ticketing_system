package com.example.prj;

import SessionHandler.SessionHandler;
import User.Client;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class SelectChat implements Initializable {
    @FXML
    private Button search;
    @FXML
    private TextField textField;
    @FXML
    private ListView<String> listView;
    @FXML
    private Button customerService;
    @FXML
    private Button exitButton;

    public void exit(){
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    public void searchClient() throws IOException, ClassNotFoundException {
        listView.getItems().clear();
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("Clients.BIN"));
        while(true){
            try{
                Client client = (Client)in.readObject();
                if(client.getUserName().contains(textField.getText()) || client.getUserName().equalsIgnoreCase(textField.getText())){
                    listView.getItems().add(client.getUserName());
                }
            } catch (EOFException e) {
                in.close();
                break;
            }
        }
        in.close();
    }

    public void onSelectClient(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ChatWindow.fxml"));
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                SessionHandler.currentChatWithClient = listView.getSelectionModel().getSelectedItem();
                //listView.getSelectionModel().clearSelection();
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ChatWindow.fxml"));
                ActionEvent e = new ActionEvent();
                Stage stage = new Stage();
                Bounds boundsInScene = customerService.localToScene(customerService.getBoundsInLocal());
                stage.setX(boundsInScene.getCenterX() + 1027);
                stage.setY(boundsInScene.getCenterY() - 20);
                stage.setResizable(false);
                stage.initStyle(StageStyle.UNDECORATED);
                Scene scene = null;
                try {
                    scene = new Scene(fxmlLoader.load());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                stage.setScene(scene);
                stage.show();
            }
        });

        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream("Clients.BIN"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(true){
            try{
                Client client = (Client)in.readObject();
                if(client.getUserName().equals(SessionHandler.currentSignedInClient.getUserName()))
                    continue;
                listView.getItems().add(client.getUserName());
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
    }
}
