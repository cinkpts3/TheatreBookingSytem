package com.example.theatrebookingsystem;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
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

import java.io.*;
import java.time.LocalDate;
import java.util.Random;

public class PerfomanceController {

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

    private PerfomanceModel perfomanceModel;
    private CustomList<PerfomanceModel> perfomnceList = new CustomList<>();


    @FXML
    public void initialize(){
        loadTitlesFromXML();
        loadFromPerformanceXML();
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

    public int generateRandomId() {
        Random random = new Random();
        int id;
        boolean idExists;

        do {
             id = random.nextInt(10000000) ;  // Generates a random ID between 1 and 1,000,000
            idExists = false;

            // Check if the ID already exists
            for (int i = 0; i < perfomnceList.size(); i++) {
                if (perfomnceList.get(i).getId() == id) {
                    idExists = true;
                    break;
                }
            }
        } while (idExists); // Repeat if the ID is already taken

        return id;
    }


    public void addPerfomanceList(ActionEvent event) throws Exception {
        String title = titleSlot.getValue();
        LocalDate date = dateField.getValue();
        String matineeTime = matineeTimeSlot.getValue();
        String eveningTime = eveningTimeSlot.getValue();
        ShowModel show = findShowTitle(title);  //find the showmodel titles
        //only matinée or evening can be chosen
        if ((matineeTime != null && eveningTime != null) || (matineeTime == null && eveningTime == null)) {
            System.out.println("Please select only one time slot: either matinee or evening.");
            matineeTimeSlot.setValue(null);
            eveningTimeSlot.setValue(null);
            return;//prevents both values from being saved
        }
        if ( matineeTime != null && eveningTime == null){
            eveningTime = "ONLY MATINEE";
        }
        else if (matineeTime == null && eveningTime != null){
            matineeTime = "ONLY EVENING";
        }
        //checks for existing performance with the same date and time
        for (int i = 0; i < perfomnceList.size(); i++){
            PerfomanceModel existingP = perfomnceList.get(i);
            //same date and same matinee time
            if (existingP.getDate().equals(date)) {
                if (matineeTime != null && matineeTime.equals(existingP.getMatineeTime())) {
                    System.out.println("such performance already exists");
                    return;
                }
                if (eveningTime != null && eveningTime.equals(existingP.getEveningTime())) {
                    System.out.println("such performance already exists");
                    return;
                }
            }
            //no more than two performances in one day

            int performancesCountForDate = 0;
            for (int k = 0; k < perfomnceList.size(); k++) {
                PerfomanceModel existingPerformance = perfomnceList.get(k);

                if (existingPerformance.getDate().equals(date)) {
                    performancesCountForDate++;
                }
            }
            //if there are already two performances
            if (performancesCountForDate >= 2) {
                System.out.println("a max of two performances are allowed per day");
                return;
            }
        }

        int id = generateRandomId();

        PerfomanceModel newPerfomance = new PerfomanceModel(id, date, matineeTime, eveningTime, show );

        perfomnceList.add(newPerfomance);
        perfomanceListView.getItems().add(newPerfomance.toString());

        saveToPerfomanceXML();

        titleSlot.setValue(null);
        dateField.setValue(null);
        matineeTimeSlot.setValue(null);
        eveningTimeSlot.setValue(null);

    }

    public void deletePerformance(ActionEvent e) throws Exception{
        int selectedIndex = perfomanceListView.getSelectionModel().getSelectedIndex();

        if (selectedIndex != 1){
            perfomnceList.remove(selectedIndex); //remove from the lsit
            perfomanceListView.getItems().remove(selectedIndex);

        }
        saveToPerfomanceXML();
    }

    private  File filePerfomance = new File("C:\\Users\\Admin\\Desktop\\theateBookingSystem\\theatreBookingSystem\\src\\main\\resources\\com\\example\\theatrebookingsystem\\xmlFiles\\Perfomances.xml");

    public void saveToPerfomanceXML() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        xstream.allowTypeHierarchy(ShowModel.class);
        xstream.allowTypeHierarchy(CustomList.class);
        ObjectOutputStream os = xstream.createObjectOutputStream(new FileWriter(filePerfomance));
        os.writeObject(perfomnceList);
        System.out.println ("performance saved to xml:)");
        os.close();

    }
    public void loadFromPerformanceXML() {
        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypeHierarchy(PerfomanceModel.class);
        xstream.allowTypeHierarchy(CustomList.class);
        try {
            ObjectInputStream in = xstream.createObjectInputStream(new FileReader(filePerfomance));
            perfomnceList = (CustomList<PerfomanceModel>) in.readObject();//load the xml data into performanceList
                System.out.println("shows loaded.");//debbug
                for (int i = 0; i < perfomnceList.size(); i++) { //populating listview with loaded shows
                    perfomanceListView.getItems().add(perfomnceList.get(i).toString());
                }
            in.close();
        } catch (Exception error) {
            error.printStackTrace();
            System.err.println("error loading from xml: " + error.getMessage()); //debug
        }

    }


    public void loadTitlesFromXML() {
        File file = new File("C:\\Users\\Admin\\Desktop\\theateBookingSystem\\theatreBookingSystem\\src\\main\\resources\\com\\example\\theatrebookingsystem\\xmlFiles\\Shows.xml");

        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        //list of classes for  serialisation
        xstream.allowTypeHierarchy(ShowModel.class);
        xstream.allowTypeHierarchy(CustomList.class);
        try {
            ObjectInputStream in = xstream.createObjectInputStream(new FileReader(file));
            CustomList<ShowModel> showsList = (CustomList<ShowModel>) in.readObject(); //load
            if(showsList != null ){
                System.out.println("shows loaded!");
                titleSlot.getItems().clear(); //clear existing titles (in case of removing show)
                for (int i = 0; i < showsList.size(); i++) { //populate title slot with titles
                    ShowModel show = showsList.get(i);
                    titleSlot.getItems().add(show.getTitle());
                    showList.add(show); //adding each show instance to the choice box
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("error loading xml: " + e.getMessage());
        }

    }
}





