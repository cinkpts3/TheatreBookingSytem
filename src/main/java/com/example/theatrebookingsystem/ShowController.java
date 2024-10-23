package com.example.theatrebookingsystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.CustomList;
import theatrebookingsystem.model.ShowModel;

import java.io.IOException;
import java.time.LocalDate;

public class ShowController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ListView<String> showListView; // ListView to display the shows
    @FXML
    private TextField titleField;
    @FXML
    private TextField runningTimeField;
    @FXML
    private DatePicker startDateField;
    @FXML
    private DatePicker endDateField;
    @FXML
    private TextField ticketPriceField;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;
    // Custom list to store ShowModel objects
    private CustomList<ShowModel> showsList = new CustomList<>();


    public void addShowList(ActionEvent event){
        String title = titleField.getText();
        double runningTime = Double.parseDouble(runningTimeField.getText());
        LocalDate startDate =  startDateField.getValue();
        LocalDate endDate = endDateField.getValue();
        double ticketPrice = Double.parseDouble(ticketPriceField.getText());

        ShowModel newShow = new ShowModel(title, runningTime, startDate, endDate, ticketPrice);

        // Add the new show to the custom list
        showsList.add(newShow);

        // Optionally, update the ListView to display the added show
        showListView.getItems().add(newShow.toString());

        // Clear the input fields after adding the show
        titleField.clear();
        runningTimeField.clear();
        endDateField.setValue(null);
        startDateField.setValue(null);
        ticketPriceField.clear();

    }

    public void deleteShow(ActionEvent e){
        int selectedIndex = showListView.getSelectionModel().getSelectedIndex();

        if (selectedIndex != 1){
            showsList.remove(selectedIndex); //remove from the lsit
            showListView.getItems().remove(selectedIndex);

        }
    }
    //switch
    public void switchToMainView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("mainview.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToShowView(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("showView.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
