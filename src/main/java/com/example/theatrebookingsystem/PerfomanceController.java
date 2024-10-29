package com.example.theatrebookingsystem;

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

    private CustomList<ShowModel> showList = new CustomList<>(); //for show list storage
    private CustomList<PerfomanceModel> perfomnceList = new CustomList<>();

    @FXML
    public void initialize(){
        for (int i= 0; i < showList.size(); i++){
            ShowModel show = showList.get(i);
            titleSlot.getItems().add(show.getTitle());
        }
        for (int i = 12; i <=17; i++){
            matineeTimeSlot.getItems().add(i +":00");
        }
        for (int i = 18; i<= 23; i++){
            eveningTimeSlot.getItems().add(i +":00");
        }
    }
    public ShowModel findShowTitle(String title){
        for (int i = 0; i<showList.size(); i++){
            ShowModel show = showList.get(i);
            if (show.getTitle().equalsIgnoreCase(title)){
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


    //switchers

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
