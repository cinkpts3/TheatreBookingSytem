package com.example.theatrebookingsystem;

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

import java.io.IOException;
import java.time.LocalDate;

public class CustomerController {
    private Stage stage;
    private Scene scene;
    private Parent root;

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


    public void addCustomerList(ActionEvent event){
        String name = nameField.getText();
        String email = emailField.getText();
        String phone = phoneNumberField.getText();

        CustomerModel newCustomer = new CustomerModel(name, email, phone);

        customerList.add(newCustomer);
        customerListView.getItems().add(newCustomer.toString());

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
}
