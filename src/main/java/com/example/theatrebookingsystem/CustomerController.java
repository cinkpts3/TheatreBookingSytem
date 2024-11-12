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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import theatrebookingsystem.model.CustomerModel;
import theatrebookingsystem.model.ShowModel;
import utils.CustomList;

import java.io.*;
import java.time.LocalDate;

public class CustomerController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private Button addCustomerButton;

    @FXML
    private Button deleteCustomerButton;

    @FXML
    private ListView<String> customerListView;
    private CustomList<CustomerModel> customerList = new CustomList<>();
    @FXML
    public void initialize(){
        if (customerList != null) {
            loadCustomers();
        }
    }


    public void addCustomerList(ActionEvent event) throws Exception {
        String name = nameField.getText();
        String email = emailField.getText();
        String phone = phoneNumberField.getText();

        CustomerModel newCustomer = new CustomerModel(name, email, phone);

        customerList.add(newCustomer);
        customerListView.getItems().add(newCustomer.toString());

        saveCustomers();
        nameField.clear();
        emailField.clear();
        phoneNumberField.clear();


    }
    public void removeCustomer(ActionEvent event){
        int selectedIndex = customerListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex != 1){
            customerList.remove(selectedIndex);
            customerListView.getItems().remove(selectedIndex);
        }
    }

    private File file = new File("C:\\Users\\Admin\\Desktop\\theateBookingSystem\\theatreBookingSystem\\src\\main\\resources\\com\\example\\theatrebookingsystem\\xmlFiles\\Customers.xml");

    public void saveCustomers() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        xstream.allowTypeHierarchy(CustomerModel.class);
        xstream.allowTypeHierarchy(CustomList.class);
        ObjectOutputStream os = xstream.createObjectOutputStream(new FileWriter(file));
        os.writeObject(customerList);
        System.out.println ("customers saved to xml:)");
        os.close();

    }


    public void loadCustomers() {
        //security
        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        //list of classes for  serialisation
        xstream.allowTypeHierarchy(CustomerModel.class);
        xstream.allowTypeHierarchy(CustomList.class);

        try {
            ObjectInputStream in = xstream.createObjectInputStream(new FileReader(file));
            //load the xml data into showsList
            customerList = (CustomList<CustomerModel>) in.readObject();
            System.out.println("customers are loaded.");//debug
            for (int i = 0; i < customerList.size(); i++) { //populating listview with loaded shows
                customerListView.getItems().add(customerList.get(i).toString());
            }
            in.close();
        } catch (Exception error) {
            error.printStackTrace();
            System.err.println("error loading from xml: " + error.getMessage()); //debug
        }

    }

}
