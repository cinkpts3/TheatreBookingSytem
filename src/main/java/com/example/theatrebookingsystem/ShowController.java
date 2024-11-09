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
import theatrebookingsystem.model.ShowModel;
import utils.CustomList;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.io.IOException;

import java.io.*;
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
    private TextField stallsTicketPriceField;
    @FXML
    private TextField circleTicketPriceField;
    @FXML
    private TextField balconyTicketPriceField;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;
    // Custom list to store ShowModel objects
    private CustomList<ShowModel> showsList = new CustomList<>();

    private MainController mainController;

    private static final String FILE_NAME = "shows.xml";

    @FXML
    public void initialize() {
// Загрузите данные из файла при инициализации контроллера
        loadShowsFromFile();
        // Обновите ListView
        updateListView();
    }

    private void saveShowsToFile() {
        XStream xstream = new XStream(new DomDriver());
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            xstream.toXML(showsList, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadShowsFromFile() {
        XStream xstream = new XStream(new DomDriver());
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (FileReader reader = new FileReader(file)) {
                showsList = (CustomList<ShowModel>) xstream.fromXML(reader);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    // Method to populate ListView with existing shows
    private void updateListView() {
        showListView.getItems().clear(); // Clear existing items
        for (int i = 0; i < showsList.size(); i++) {
            showListView.getItems().add(showsList.get(i).toString()); // Populate ListView with shows
        }
    }

    public void addShowList(ActionEvent event){
        String title = titleField.getText();
        double runningTime = Double.parseDouble(runningTimeField.getText());
        LocalDate startDate =  startDateField.getValue();
        LocalDate endDate = endDateField.getValue();
        double stallsTicketPrice = Double.parseDouble(stallsTicketPriceField.getText());
        double circleTicketPrice = Double.parseDouble(circleTicketPriceField.getText());
        double balconyTicketPrice = Double.parseDouble(balconyTicketPriceField.getText());

        ShowModel newShow = new ShowModel(title, runningTime, startDate, endDate, stallsTicketPrice, circleTicketPrice, balconyTicketPrice);

        // Add the new show to the custom list
        showsList.add(newShow);

        // Optionally, update the ListView to display the added show
        showListView.getItems().add(newShow.toString());

        // Clear the input fields after adding the show
        titleField.clear();
        runningTimeField.clear();
        endDateField.setValue(null);
        startDateField.setValue(null);
        stallsTicketPriceField.clear();
        circleTicketPriceField.clear();
        balconyTicketPriceField.clear();

        saveShowsToFile();
    }

    public void deleteShow(ActionEvent e){
        int selectedIndex = showListView.getSelectionModel().getSelectedIndex();

        if (selectedIndex != 1){
            showsList.remove(selectedIndex); //remove from the lsit
            showListView.getItems().remove(selectedIndex);

        }
        saveShowsToFile();
    }

    public void testSaveShows() {
        ShowModel testShow = new ShowModel("Test Show", 120, LocalDate.now(), LocalDate.now().plusDays(10), 50, 40, 30);
        showsList.add(testShow);
        saveShowsToFile();
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
