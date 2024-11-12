package com.example.theatrebookingsystem;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import com.thoughtworks.xstream.security.NoTypePermission;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import theatrebookingsystem.model.PerfomanceModel;
import theatrebookingsystem.model.ShowModel;
import utils.CustomList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class PerfomanceController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private ListView<String> perfomanceListView;

    @FXML
    private ChoiceBox<String> titleSlot;
    @FXML
    private DatePicker dateField;
    @FXML
    private ChoiceBox<String> matineeTimeSlot;

    @FXML
    private ChoiceBox<String> eveningTimeSlot;

    @FXML
    private Button addPerfomance;


    private CustomList<PerfomanceModel> perfomnceList = new CustomList<>();

    private ShowController showsList;
    @FXML
    public void initialize(){
        loadTitlesFromXML();
        for (int i = 12; i <=17; i++){
            matineeTimeSlot.getItems().add(i +":00");
        }
        for (int i = 18; i<= 23; i++){
            eveningTimeSlot.getItems().add(i +":00");
        }

    }

    public CustomList<ShowModel> showList = new CustomList<>();

    public ShowModel findShowTitle(String title) {
        for (int i = 0; i < showList.size(); i++) {
            ShowModel show = showList.get(i);
            if (show.getTitle().equalsIgnoreCase(title)) {
                return show;
            }
        }
        throw new IllegalArgumentException("show wasnt found");
    }



    public void addPerfomanceList(ActionEvent event){
        String title = titleSlot.getValue();
        LocalDate date = dateField.getValue();
        String matineeTime = matineeTimeSlot.getValue();
        String eveningTime = eveningTimeSlot.getValue();


        ShowModel show = findShowTitle(title);  // Find the ShowModel by title

        PerfomanceModel newPerfomance = new PerfomanceModel(date, matineeTime, eveningTime, show );

        perfomnceList.add(newPerfomance);
        perfomanceListView.getItems().add(newPerfomance.toString());

        titleSlot.setValue(null);
        dateField.setValue(null);
        matineeTimeSlot.setValue(null);
        eveningTimeSlot.setValue(null);

    }


    public void loadTitlesFromXML() {
        XStream xstream = new XStream();
        xstream.alias("Show", ShowModel.class);
        xstream.alias("Shows", CustomList.class);

        // Configure XStream security to allow specific classes
        xstream.addPermission(NoTypePermission.NONE);
        xstream.addPermission(AnyTypePermission.ANY);
        xstream.allowTypesByRegExp(new String[]{
                "utils\\.CustomList",
                "theatrebookingsystem\\.model\\.ShowModel"
        });

        File file = new File("C:\\Users\\Admin\\Desktop\\theateBookingSystem\\theatreBookingSystem\\src\\main\\resources\\com\\example\\theatrebookingsystem\\Shows.xml");

        try {
            // Load the XML content into showsList
            CustomList<ShowModel> showsList = (CustomList<ShowModel>) xstream.fromXML(file);
            System.out.println("Shows loaded from XML successfully.");

            // Clear existing items in the ChoiceBox
            titleSlot.getItems().clear();

            // Populate ChoiceBox with titles from the XML file
            for (int i = 0; i < showsList.size(); i++) {
                ShowModel show = showsList.get(i);
                titleSlot.getItems().add(show.getTitle());
                showList.add(show); // Add each ShowModel instance to showList
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error loading titles from XML: " + e.getMessage());
        }
    }
}





