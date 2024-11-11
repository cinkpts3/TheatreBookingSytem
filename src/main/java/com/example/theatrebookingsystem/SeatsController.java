package com.example.theatrebookingsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import theatrebookingsystem.model.*;
import utils.CustomList;

import java.io.IOException;

public class SeatsController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private GridPane balconyGrid;
    @FXML
    private GridPane circleGrid;
    @FXML
    private GridPane stallsGrid;



    public CustomList<Seat> seats = new CustomList<>();

    @FXML
    public Button switchToBooking;


    @FXML
    public void initialize(){
        initBalconySeats();
        initCircleSeats();
        initStallSeats();
    }


    public void initBalconySeats() {
        int rows = 3;
        int column = 8;

        for (int row = 0; row < rows; row++) {
            int seatNumberStart = 17 - row * column;
            for (int col = 0; col < column; col++) { //nested loop
                String seatNumber = "B" + (seatNumberStart + col);
                Seat seat = new Seat(seatNumber);  //creates new seat object
                seats.add(seat); //adds objects to the custom list

                Button seatButton = new Button(seatNumber);
                seatButton.setStyle("-fx-background-color: LIGHTGREEN;");

                seatButton.setOnAction(event -> toggleSeatSelection(seat, seatButton)); //adds an action to the button
                balconyGrid.add(seatButton, col, row);
            }
        }
    }
    public void initCircleSeats() {
        int rows = 3;
        int column = 10;
        int startingRow = 3; // specifies starting rows in grid pane

        for (int row = 0; row < rows; row++) {

            int seatNumberStart = 21 - row * column;
            for (int col = 0; col < column; col++) {
                String seatNumber = "C" + (seatNumberStart + col);
                Seat seat = new Seat(seatNumber);
                seats.add(seat);

                Button seatButton = new Button(seatNumber);
                seatButton.setStyle("-fx-background-color: LIGHTBLUE;");

                seatButton.setOnAction(event -> toggleSeatSelection(seat, seatButton));
                circleGrid.add(seatButton, col, startingRow + row); // Circle starts at row 3
            }
        }
    }
    public void initStallSeats() {
        int rows = 4;
        int column = 10;
        int startingRow = 6;
        for (int row = 0; row<rows; row++) {
            int seatNumberStart = 31 - row * column;
            for (int col = 0; col < column; col++) {
                String seatNumber = "S" + ( seatNumberStart + col);
                Seat seat = new Seat(seatNumber);
                seats.add(seat);

                Button seatButton = new Button(seatNumber);
                seatButton.setStyle("-fx-background-color: LIGHTPINK;");

                seatButton.setOnAction(event -> toggleSeatSelection(seat, seatButton));
                stallsGrid.add(seatButton, col, startingRow + row);
            }
        }
    }


    private void toggleSeatSelection(Seat seat, Button seatButton) {
        if (!seat.isBooked()) {
            seat.setBooked(true);
            seatButton.setStyle("-fx-background-color: red;");
        } else {
            seat.setBooked(false);
            if (seat.getSeatNumber().startsWith("B")) {
                seatButton.setStyle("-fx-background-color: LIGHTGREEN;");
            } else if (seat.getSeatNumber().startsWith("C")) {
                seatButton.setStyle("-fx-background-color: LIGHTBLUE;");
            } else if (seat.getSeatNumber().startsWith("S")) {
                seatButton.setStyle("-fx-background-color: LIGHTPINK;");
            }
        }
    }

    public CustomList<Seat> getSelectedSeats() {
        CustomList<Seat> selectedSeats = new CustomList<>();
        for (int i = 0; i < seats.size(); i++) {
            Seat seat = seats.get(i);
            if (seat.isBooked()) {
                selectedSeats.add(seat);
            }
        }
        return selectedSeats;
    }

    public void switchToBookingView(ActionEvent event ) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("booking.fxml"));
        Parent root = loader.load();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}
