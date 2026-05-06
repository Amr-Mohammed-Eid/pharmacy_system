package com.pharmacy.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.pharmacy.model.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Controller for the dashboard view.
 */
public class DashboardController implements Initializable {

    @FXML
    private BorderPane mainPane;
    
    @FXML
    private Label userLabel;
    
    @FXML
    private Button inventoryButton;
    
    @FXML
    private Button prescriptionsButton;
    
    @FXML
    private Button salesButton;
    
    @FXML
    private Button patientsButton;
    
    @FXML
    private Button suppliersButton;
    
    @FXML
    private Button reportsButton;
    
    @FXML
    private Button usersButton;
    
    @FXML
    private Button logoutButton;
    
    private User currentUser;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize controller
    }
    
    /**
     * Set the current user
     * 
     * @param user User object
     */
    public void setUser(User user) {
        this.currentUser = user;
        userLabel.setText("Welcome, " + user.getFullName() + " (" + user.getRole() + ")");
        
        // Show/hide buttons based on user role
        if (!user.getRole().equals("Admin")) {
            usersButton.setVisible(false);
        }
    }
    
    /**
     * Handle inventory button click
     * 
     * @param event Action event
     */
    @FXML
    private void handleInventory(ActionEvent event) {
        loadModule("/fxml/Inventory.fxml");
    }
    
    /**
     * Handle prescriptions button click
     * 
     * @param event Action event
     */
    @FXML
    private void handlePrescriptions(ActionEvent event) {
        loadModule("/fxml/Prescriptions.fxml");
    }
    
    /**
     * Handle sales button click
     * 
     * @param event Action event
     */
    @FXML
    private void handleSales(ActionEvent event) {
        loadModule("/fxml/Sales.fxml");
    }
    
    /**
     * Handle patients button click
     * 
     * @param event Action event
     */
    @FXML
    private void handlePatients(ActionEvent event) {
        loadModule("/fxml/Patients.fxml");
    }
    
    /**
     * Handle suppliers button click
     * 
     * @param event Action event
     */
    @FXML
    private void handleSuppliers(ActionEvent event) {
        loadModule("/fxml/Suppliers.fxml");
    }
    
    /**
     * Handle reports button click
     * 
     * @param event Action event
     */
    @FXML
    private void handleReports(ActionEvent event) {
        loadModule("/fxml/Reports.fxml");
    }
    
    /**
     * Handle users button click
     * 
     * @param event Action event
     */
    @FXML
    private void handleUsers(ActionEvent event) {
        loadModule("/fxml/Users.fxml");
    }
    
    /**
     * Handle logout button click
     * 
     * @param event Action event
     */
    @FXML
    private void handleLogout(ActionEvent event) {
        try {
            // Load the login view
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
            
            // Show the login view
            Stage stage = (Stage) logoutButton.getScene().getWindow();
            stage.setScene(new Scene(root, 800, 600));
            stage.setTitle("Pharmacy Management System - Login");
            stage.setMaximized(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Load a module into the main pane
     * 
     * @param fxmlPath FXML file path
     */
    private void loadModule(String fxmlPath) {
        try {
            // Load the module view
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent moduleView = loader.load();
            
            // Set the module view in the main pane
            mainPane.setCenter(moduleView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
