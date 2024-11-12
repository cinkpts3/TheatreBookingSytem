package com.example.theatrebookingsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.CustomList;

import java.io.IOException;

public class MainApplication extends Application {

    public void start(Stage stage) throws IOException {
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("mainview.fxml"));
        Parent root = mainLoader.load();
        MainController mainController = mainLoader.getController();

        // Ensure ShowController and PerfomanceController are linked here
        FXMLLoader showLoader = new FXMLLoader(getClass().getResource("showView.fxml"));
        showLoader.load();
        ShowController showController = showLoader.getController();

        FXMLLoader perfomanceLoader = new FXMLLoader(getClass().getResource("perfomanceView.fxml"));
        perfomanceLoader.load();
        PerfomanceController perfomanceController = perfomanceLoader.getController();

        mainController.setShowController(showController);
        mainController.setPerfomanceController(perfomanceController);

        Scene scene = new Scene(root);
        stage.setTitle("Booking System");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}

