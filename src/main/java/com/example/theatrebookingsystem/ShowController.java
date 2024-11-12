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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
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

    private CustomList<String> titles = new CustomList<>();

    @FXML
    public void initialize() {
        load();
    }




    public void setMainController(MainController mainController) {
        this.mainController = mainController;
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


    public void deleteShow(ActionEvent e){
        int selectedIndex = showListView.getSelectionModel().getSelectedIndex();

        if (selectedIndex != 1){
            showsList.remove(selectedIndex); //remove from the lsit
            showListView.getItems().remove(selectedIndex);

        }

    }



    public String getTitles() {
        CustomList<String> extractedTitles = new CustomList<>();
        for (int i = 0; i < showsList.size(); i++) {
            extractedTitles.add(showsList.get(i).getTitle());
        }
        System.out.println("Titles extracted from ShowController: " + extractedTitles.size());
        return extractedTitles.toString();
    }
    public void save() throws Exception {
        var xstream = new XStream(new DomDriver());
        xstream.alias("Show", ShowModel.class);
        xstream.alias("Shows", CustomList.class);

        FileWriter writer = new FileWriter("C:\\Users\\Admin\\Desktop\\theateBookingSystem\\theatreBookingSystem\\src\\main\\resources\\com\\example\\theatrebookingsystem\\Shows.xml");
        ObjectOutputStream os = xstream.createObjectOutputStream(writer);
        os.writeObject(showsList);
        System.out.println ("shows saved to xml:)");
        os.close();


    }


    public void load() {
        Class<?>[] classes = new Class[] {ShowModel.class, CustomList.class};

        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);

        xstream.alias("Show", ShowModel.class);
        xstream.alias("Shows", CustomList.class);


        File file = new File("C:\\Users\\Admin\\Desktop\\theateBookingSystem\\theatreBookingSystem\\src\\main\\resources\\com\\example\\theatrebookingsystem\\Shows.xml");

        try {
            //load the xml data into showsList
            showsList = (CustomList<ShowModel>) xstream.fromXML(file);
            System.out.println("Shows loaded from XML successfully.");
            for (int i = 0; i < showsList.size(); i++) { //populating listview with loaded shows
                showListView.getItems().add(showsList.get(i).toString());
            }


        } catch (Exception error) {
            error.printStackTrace();
            System.err.println("Error loading shows from XML: " + error.getMessage());
        }
    }


}
