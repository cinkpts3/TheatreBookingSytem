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

    private File file;
    public ShowController() {
        this.showsList = new CustomList<>(); // Initialize your custom list
    }

    // This constructor can still be used when necessary, but you won't use it in FXML loading
    public ShowController(File file) {
        this.file = file;
        this.showsList = new CustomList<>();
    }

    public String fileName(){
        return file.getName();
    }

    public void load() throws Exception{
        XStream xStream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xStream);
        xStream.allowTypes(new Class[]{ShowModel.class});

        ObjectInputStream in = xStream.createObjectInputStream(new FileReader(fileName()));
        showsList = (CustomList<ShowModel>) in.readObject();
        in.close();
    }

    public void save() throws  Exception{
        XStream xStream = new XStream(new DomDriver());
        ObjectOutputStream out = xStream.createObjectOutputStream(new FileWriter("src/main/resources/com/example/theatrebookingsystem/shows.xml"));
        out.writeObject(showsList);
        out.close();
    }
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

    @FXML
    public void initialize(){
        try {
            this.file = new File("src/main/resources/com/example/theatrebookingsystem/shows.xml");
            load();
        } catch (Exception e){
            e.printStackTrace();
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



}
