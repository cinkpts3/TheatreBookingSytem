package com.example.theatrebookingsystem;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import theatrebookingsystem.model.*;
import utils.CustomList;

import java.io.*;
import java.time.LocalDate;

public class BookingController {

//fxml objects
    @FXML
    private ChoiceBox performanceChoice;
    @FXML
    private ChoiceBox customerChoice;
   ;
    @FXML
    private Button seatPage;
    @FXML
    private Button addBooking;
    @FXML
    private Button deleteBooking;

    @FXML
    private Text seatsChosenText;
    @FXML
    private ListView<String> bookingListView;
    @FXML
    private Tab mainTab;
    @FXML
    private Tab showTab;
    @FXML
    private Tab performanceTab;
    @FXML
    private Tab customerTab;
    @FXML
    private Tab bookingTab;

    @FXML
    private void initialize(){
        loadBookings();
        loadCustomers();
        loadPerformance();
//        loadTabContent(mainTab, "mainview.fxml");
//        loadTabContent(showTab, "showView.fxml");
//        loadTabContent(performanceTab, "perfomanceView.fxml");
//        loadTabContent(customerTab, "customerView.fxml");
//        loadTabContent(bookingTab, "booking.fxml");

    }
    private void loadTabContent(Tab tab, String fxmlFile) {
        try {
            AnchorPane content = FXMLLoader.load(getClass().getResource(fxmlFile));
            tab.setContent(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //regular objects
    private CustomList<BookingModel> bookingList = new CustomList<>();
    private CustomList<Seat> selectedSeats = new CustomList<>();
    private BookingModel bookingModel;


    private Stage stage;
    private Scene scene;
    private Parent root;

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
    public CustomList<CustomerModel> customerList = new CustomList<>();
    public CustomerModel findCustomer(String customer) {
        for (int i = 0; i < customerList.size(); i++) {
            CustomerModel c = customerList.get(i);
            if (c.toString().equals(customer)) {
                System.out.println ("customer found: " + c);
                return c;
            }
        }
        throw new IllegalArgumentException("customer wasnt found");
    }

    public void addBookingList(){
        String performance = performanceChoice.getValue().toString();
        String customer = customerChoice.getValue().toString();

        PerfomanceModel perf= findPerformance(performance);
        CustomerModel cust = findCustomer(customer);
        // Создаем BookingModel с использованием selectedSeats
        bookingModel = new BookingModel(perf, cust, selectedSeats );//, selectedS);
        bookingList.add(bookingModel);

        // Обновляем ListView и очищаем текстовое поле выбранных мест
        bookingListView.getItems().add(bookingModel.toString());
        seatsChosenText.setText(""); // Очищаем текстовое поле
        selectedSeats = new CustomList<>();

    }

    public void deleteBookingList(){

    }
    public void setSelectedSeats(CustomList<Seat> selectedSeats) {
        this.selectedSeats = selectedSeats;

        // Display selected seats in the `seatsChosenText` Text component
        StringBuilder seatsText = new StringBuilder();
        for (int i = 0; i < selectedSeats.size(); i++) {
            seatsText.append(selectedSeats.get(i).getSeatNumber()).append(" ");
        }
        seatsChosenText.setText(seatsText.toString());  // Update the Text with selected seats
    }


    public void switchToSeatsView(ActionEvent event) throws IOException{
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/theatrebookingsystem/SeatsView.fxml"));
            Parent seatsRoot = loader.load();
            SeatsController seatsController = loader.getController();
            seatsController.setBookingController(this);  // Pass BookingController reference to SeatsController

            Stage seatsStage = new Stage();
            seatsStage.setTitle("Select Seats");
            seatsStage.setScene(new Scene(seatsRoot));
            seatsStage.showAndWait();  // Pauses until the seat selection window is closed

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File file = new File("C:\\Users\\Admin\\Desktop\\theateBookingSystem\\theatreBookingSystem\\src\\main\\resources\\com\\example\\theatrebookingsystem\\xmlFiles\\Bookings.xml");

    public void saveBooking() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        xstream.allowTypeHierarchy(BookingModel.class);
        xstream.allowTypeHierarchy(CustomList.class);
        ObjectOutputStream os = xstream.createObjectOutputStream(new FileWriter(file));
        os.writeObject(bookingList);
        System.out.println ("booking saved to xml:)");
        os.close();

    }


    public void loadBookings() {
        //security
        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        //list of classes for  serialisation
        xstream.allowTypeHierarchy(BookingModel.class);
        xstream.allowTypeHierarchy(CustomList.class);
        try {
            ObjectInputStream in = xstream.createObjectInputStream(new FileReader(file));
            //load the xml data into showsList
            bookingList = (CustomList<BookingModel>) in.readObject();
            System.out.println("bookings are loaded.");//debug
            for (int i = 0; i < bookingList.size(); i++) { //populating listview with loaded shows
                bookingListView.getItems().add(bookingList.get(i).toString());
            }
            in.close();
        } catch (Exception error) {
            error.printStackTrace();
            System.err.println("error loading from xml: " + error.getMessage()); //debug
        }

    }


    public void loadCustomers(){
        File file = new File("C:\\Users\\Admin\\Desktop\\theateBookingSystem\\theatreBookingSystem\\src\\main\\resources\\com\\example\\theatrebookingsystem\\xmlFiles\\Customers.xml");

        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        //list of classes for serialisation
        xstream.allowTypeHierarchy(CustomerModel.class);
        xstream.allowTypeHierarchy(CustomList.class);
        try {
            ObjectInputStream in = xstream.createObjectInputStream(new FileReader(file));
            customerList = (CustomList<CustomerModel>) in.readObject(); //load
            if(customerList != null ){
                System.out.println("customers are loaded!");
                for (int i = 0; i < customerList.size(); i++) { //populate customer slot with customers
                    CustomerModel customer = customerList.get(i);
                    customerChoice.getItems().add(customer.toString()); //adding each customer instance to the choice box
                    customerList.add(customer);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("error loading xml: " + e.getMessage());
        }

    }
    public void loadPerformance(){
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
                performanceChoice.getItems().clear(); //clear existing titles (in case of removing show)
                for (int i = 0; i < performanceList.size(); i++) { //populate title slot with titles
                    PerfomanceModel performance = performanceList.get(i);
                    performanceChoice.getItems().add(performance.toString());

                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            System.err.println("error loading xml: " + e.getMessage());
        }

    }


    public void loadChosenSeats() {
        File file = new File("C:\\Users\\Admin\\Desktop\\theateBookingSystem\\theatreBookingSystem\\src\\main\\resources\\com\\example\\theatrebookingsystem\\xmlFiles\\Bookings.xml");

        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        //allow type hierarchy instead of list of classes for serialisation
        xstream.allowTypeHierarchy(Seat.class);
        xstream.allowTypeHierarchy(CustomList.class);
        try {
            ObjectInputStream in = xstream.createObjectInputStream(new FileReader(file));
            selectedSeats = (CustomList<Seat>) in.readObject(); //load
            if (selectedSeats != null) {
                System.out.println("seats are loaded!");
                performanceChoice.getItems().clear(); //clear existing titles (in case of removing show)
                for (int i = 0; i < selectedSeats.size(); i++) { //populate title slot with titles
                    Seat seat = selectedSeats.get(i);
                    performanceChoice.getItems().add(seat.toString());

                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            System.err.println("error loading xml: " + e.getMessage());
        }
    }
}
