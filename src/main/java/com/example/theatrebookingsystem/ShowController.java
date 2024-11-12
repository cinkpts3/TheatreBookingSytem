package com.example.theatrebookingsystem;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;
import com.thoughtworks.xstream.security.NoTypePermission;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import theatrebookingsystem.model.ShowModel;
import utils.CustomList;

//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.io.IOException;

import java.io.*;
import java.time.LocalDate;

public class ShowController {

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
    public void initialize() {
        load();
    }


    public void addShowList(ActionEvent event) throws Exception {
        String title = titleField.getText();
        double runningTime = Double.parseDouble(runningTimeField.getText());
        LocalDate startDate = startDateField.getValue();
        LocalDate endDate = endDateField.getValue();
        double stallsTicketPrice = Double.parseDouble(stallsTicketPriceField.getText());
        double circleTicketPrice = Double.parseDouble(circleTicketPriceField.getText());
        double balconyTicketPrice = Double.parseDouble(balconyTicketPriceField.getText());

        ShowModel newShow = new ShowModel(title, runningTime, startDate, endDate, stallsTicketPrice, circleTicketPrice, balconyTicketPrice);


        showsList.add(newShow);
        showListView.getItems().add(newShow.toString());
        save();

        // Clear the input fields after adding the show
        titleField.clear();
        runningTimeField.clear();
        endDateField.setValue(null);
        startDateField.setValue(null);
        stallsTicketPriceField.clear();
        circleTicketPriceField.clear();
        balconyTicketPriceField.clear();

    }


    public void deleteShow(ActionEvent e) throws Exception{
        int selectedIndex = showListView.getSelectionModel().getSelectedIndex();

        if (selectedIndex != 1){
            showsList.remove(selectedIndex); //remove from the lsit
            showListView.getItems().remove(selectedIndex);

        }
        save();
    }

   //extracting titles for the performance controller
    public String getTitles() {
        CustomList<String> extractedTitles = new CustomList<>();
        for (int i = 0; i < showsList.size(); i++) {
            extractedTitles.add(showsList.get(i).getTitle());
        }
        System.out.println("Titles extracted from ShowController: " + extractedTitles.size());
        return extractedTitles.toString();
    }
    private File file = new File("C:\\Users\\Admin\\Desktop\\theateBookingSystem\\theatreBookingSystem\\src\\main\\resources\\com\\example\\theatrebookingsystem\\xmlFiles\\Shows.xml");

    public void save() throws Exception {
        XStream  xstream = new XStream(new DomDriver());
        xstream.allowTypeHierarchy(ShowModel.class);
        xstream.allowTypeHierarchy(CustomList.class);
        ObjectOutputStream os = xstream.createObjectOutputStream(new FileWriter(file));
        os.writeObject(showsList);
        System.out.println ("shows saved to xml:)");
        os.close();

    }


    public void load() {
        //security
        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        //list of classes for  serialisation
        xstream.allowTypeHierarchy(ShowModel.class);
        xstream.allowTypeHierarchy(CustomList.class);
        try {

            ObjectInputStream in = xstream.createObjectInputStream(new FileReader(file));
            //load the xml data into showsList
            showsList = (CustomList<ShowModel>) in.readObject();
            System.out.println("shows loaded.");//debug
            for (int i = 0; i < showsList.size(); i++) { //populating listview with loaded shows
                showListView.getItems().add(showsList.get(i).toString());
            }
            in.close();
        } catch (Exception error) {
            error.printStackTrace();
            System.err.println("error loading from xml: " + error.getMessage()); //debug
        }

    }


}
