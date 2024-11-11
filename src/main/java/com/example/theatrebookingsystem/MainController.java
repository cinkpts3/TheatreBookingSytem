package com.example.theatrebookingsystem;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import theatrebookingsystem.model.ShowModel;
import utils.CustomList;

public class MainController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToMainView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("mainview.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToShowView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("showView.fxml"));
        Parent root = loader.load();



        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }
    public void switchToPerfomanceView(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("perfomanceView.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToCustomerView(ActionEvent event ) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("customerView.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToBookingView(ActionEvent event ) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("booking.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}