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
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import theatrebookingsystem.model.*;
import utils.CustomList;

import java.io.*;
import java.time.LocalDate;

public class BookingController {

//fxml objects
    @FXML
    private ChoiceBox perfomanceChoice;
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
    private void initialize(){
        loadBookings();
        loadCustomers();
        loadPerformance();
    }
    //regular objects
    private CustomList<BookingModel> bookingList = new CustomList<>();
    private CustomList<Seat> selectedSeats;
    private BookingModel bookingModel;


    private Stage stage;
    private Scene scene;
    private Parent root;

    private CustomList<PerfomanceModel> performanceList = new CustomList<>();
    public PerfomanceModel findPerformans(String performance) {
        for (int i = 0; i < performanceList.size(); i++) {
            PerfomanceModel perf = performanceList.get(i);
            return perf;
        }
        throw new IllegalArgumentException("show wasnt found");
    }
    public CustomList<CustomerModel> customerList = new CustomList<>();
    public CustomerModel findCustomer(String customer) {
        for (int i = 0; i < customerList.size(); i++) {
            CustomerModel c= customerList.get(i);
            return c;
        }
        throw new IllegalArgumentException("show wasnt found");
    }

    public void addBookingList(){
//        String performance = perfomanceChoice.getValue().toString();
//        String customer = customerChoice.getValue().toString();
//
//        PerfomanceModel perf= findPerformans(performance);
//        CustomerModel cust = findCustomer(customer);
//        // Создаем BookingModel с использованием selectedSeats
//        bookingModel = new BookingModel(perf, cust, selectedSeats);
//        bookingList.add(bookingModel);
//
//        // Обновляем ListView и очищаем текстовое поле выбранных мест
//        bookingListView.getItems().add(bookingModel.toString());
//        seatsChosenText.setText(""); // Очищаем текстовое поле
//        selectedSeats = new CustomList<>();

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
        seatsChosenText.setText(seatsText.toString());
    }

    public void switchToSeatsView(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("SeatsView.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
            CustomList<CustomerModel> customerList = (CustomList<CustomerModel>) in.readObject(); //load
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
            CustomList<PerfomanceModel> perfomanceList = (CustomList<PerfomanceModel>) in.readObject(); //load
            if (perfomanceList != null) {
                System.out.println("shows loaded!");
                perfomanceChoice.getItems().clear(); //clear existing titles (in case of removing show)
                for (int i = 0; i < perfomanceList.size(); i++) { //populate title slot with titles
                    PerfomanceModel perfomance = perfomanceList.get(i);
                    perfomanceChoice.getItems().add(perfomanceList.get(i).toString());

                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            System.err.println("error loading xml: " + e.getMessage());
        }

    }
}
