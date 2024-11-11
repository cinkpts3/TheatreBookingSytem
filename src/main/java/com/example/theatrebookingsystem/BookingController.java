package com.example.theatrebookingsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import theatrebookingsystem.model.*;
import utils.CustomList;

import java.io.IOException;

public class BookingController {
    private Stage stage;
    private Scene scene;
    private Parent root;

//fxml objects
    @FXML
    private ChoiceBox perfomanceChoice;
    @FXML
    private ChoiceBox CustomerChoice;
    @FXML
    private ChoiceBox TimeChoice;
    @FXML
    private Button seatPage;
    @FXML
    private Button addBooking;
    @FXML
    private Button deleteBooking;

    @FXML
    private Text seatsChosenText;
    @FXML
    private ListView<String> BookingListView;

    //regular objects
    private CustomList<CustomerModel> BookingList = new CustomList<>();
    private CustomList<Seat> selectedSeats;
    private BookingModel bookingModel;





    public void addBookingList(){

    }

    public void deleteBookingList(){

    }





    //switch scene statements
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

    public void switchToSeatsView(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("SeatsView.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
