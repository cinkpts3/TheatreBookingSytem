package com.example.theatrebookingsystem;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import theatrebookingsystem.model.*;
import utils.CustomList;

import java.io.*;

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
    @FXML
    private ChoiceBox<String> performanceSlot;

    private PerfomanceModel selectedPerformance;

    private Seat seat;

    public CustomList<Seat> seats = new CustomList<>();
    private CustomList<Seat> selectedSeats = new CustomList<>();
    @FXML
    private Button switchToBooking;

    private BookingController bookingController;

    @FXML
    public void initialize(){
        initBalconySeats();
        initCircleSeats();
        initStallSeats();
        loadPerfomance();
    }


    public void initBalconySeats() {
        int rows = 3;
        int column = 8;

        for (int row = 0; row < rows; row++) {
            int seatNumberStart = 17 - row * column;
            for (int col = 0; col < column; col++) { //nested loop
                String seatNumber = "B" + (seatNumberStart + col);
                Seat seat = new Seat(seatNumber, selectedPerformance);  //creates new seat object
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
                Seat seat = new Seat(seatNumber, selectedPerformance);
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
                Seat seat = new Seat(seatNumber, selectedPerformance);
                seats.add(seat);

                Button seatButton = new Button(seatNumber);
                seatButton.setStyle("-fx-background-color: LIGHTPINK;");

                seatButton.setOnAction(event -> toggleSeatSelection(seat, seatButton));
                stallsGrid.add(seatButton, col, startingRow + row);
            }
        }
    }


    private void toggleSeatSelection(Seat seat, Button seatButton) {
        if (!seat.isSelected()) { //if user press on button
            seat.setSelected(true);
            seatButton.setStyle("-fx-background-color: red;");
        } else {
            seat.setSelected(false);
            if (seat.getSeatNumber().startsWith("B")) {
                seatButton.setStyle("-fx-background-color: LIGHTGREEN;");
            } else if (seat.getSeatNumber().startsWith("C")) {
                seatButton.setStyle("-fx-background-color: LIGHTBLUE;");
            } else if (seat.getSeatNumber().startsWith("S")) {
                seatButton.setStyle("-fx-background-color: LIGHTPINK;");
            }
        }
    }



    private CustomList<PerfomanceModel> performanceList = new CustomList<>();

    public PerfomanceModel findPerformance(String performance) {
        for (int i = 0; i < performanceList.size(); i++) {
            PerfomanceModel perf = performanceList.get(i);
            System.out.println("Checking: " + perf.toString());
            if (perf.toString().equalsIgnoreCase(performance)) {
                return perf;
            }
        }
        throw new IllegalArgumentException("performance wasnt found");
    }

    public void addSeats(ActionEvent event) throws Exception{
        String performance = performanceSlot.getValue();
        PerfomanceModel perf = findPerformance(performance);
        for (int i = 0; i < seats.size(); i++) {
            Seat seat = seats.get(i);
            if (seat.isSelected()) {
                Seat newSeat = new Seat(seat.getSeatNumber(), perf);
                selectedSeats.add(newSeat);
            }
        }
        saveToSeatXML();

        System.out.println("seats are added to the xml file");

        if (bookingController != null) {
            bookingController.setSelectedSeats(selectedSeats);
        }
        // Finalize seat selection
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();  // Closes the window after selection
    }
    private MainController mainController;  // Reference to MainController
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }


    public void switchToBookingView(ActionEvent event ) throws IOException {


        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainview.fxml"));
        Parent root = loader.load();
        bookingController = loader.getController();  // Set bookingController here
        bookingController.setSelectedSeats(selectedSeats);
        mainController.switchToBookingTab();


        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    private File fileSeat = new File("C:\\Users\\Admin\\Desktop\\theateBookingSystem\\theatreBookingSystem\\src\\main\\resources\\com\\example\\theatrebookingsystem\\xmlFiles\\Seats.xml");
    public void saveToSeatXML() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        xstream.allowTypeHierarchy(ShowModel.class);
        xstream.allowTypeHierarchy(CustomList.class);
        ObjectOutputStream os = xstream.createObjectOutputStream(new FileWriter(fileSeat));
        os.writeObject(selectedSeats);
        System.out.println ("performance saved to xml:)");
        os.close();

    }


    //load and save
    private void loadPerfomance(){
        File file = new File("C:\\Users\\Admin\\Desktop\\theateBookingSystem\\theatreBookingSystem\\src\\main\\resources\\com\\example\\theatrebookingsystem\\xmlFiles\\Perfomances.xml");

        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        //allow type hierarchy instead of list of classes for serialisation
        xstream.allowTypeHierarchy(PerfomanceModel.class);
        xstream.allowTypeHierarchy(CustomList.class);
        try {
            ObjectInputStream in = xstream.createObjectInputStream(new FileReader(file));
            performanceList = (CustomList<PerfomanceModel>) in.readObject(); //load
            if (performanceList != null) {
                System.out.println("performances are loaded!");
                performanceSlot.getItems().clear(); //clear existing titles (in case of removing show)
                for (int i = 0; i < performanceList.size(); i++) { //populate title slot with titles
                    PerfomanceModel performance = performanceList.get(i);
                    performanceSlot.getItems().add(performance.toString());

                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            System.err.println("error loading xml: " + e.getMessage());
        }

    }

    public void setBookingController(BookingController bookingController) {
        this.bookingController = bookingController;
    }


}
