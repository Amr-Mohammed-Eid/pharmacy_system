package com.pharmacy.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.pharmacy.model.User;
import com.pharmacy.util.ValidationUtils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * Controller for the login view.
 */
public class LoginController implements Initializable {

    @FXML
    private TextField usernameField;
    
    @FXML
    private PasswordField passwordField;
    
    @FXML
    private Button loginButton;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize controller
    }
    
    /**
     * Handle login button click
     * 
     * @param event Action event
     */
    @FXML
    private void handleLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        
        // Validate input
        if (username.isEmpty() || password.isEmpty()) {
            showAlert(AlertType.ERROR, "Login Error", "Please enter both username and password.");
            return;
        }
        
        // In a real application, this would check against the database
        // For demonstration, we'll use a hardcoded admin user
        if (username.equals("admin") && password.equals("admin123")) {
            try {
                // Create a user object
                User user = new User();
                user.setUserId(1);
                user.setUsername("admin");
                user.setFullName("System Administrator");
                user.setRole("Admin");
                
                // Load the dashboard view
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Dashboard.fxml"));
                Parent root = loader.load();
                
                // Pass the user to the dashboard controller
                DashboardController dashboardController = loader.getController();
                dashboardController.setUser(user);
                
                // Show the dashboard view
                Stage stage = (Stage) loginButton.getScene().getWindow();
                stage.setScene(new Scene(root, 1024, 768));
                stage.setTitle("Pharmacy Management System - Dashboard");
                stage.setMaximized(true);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
                showAlert(AlertType.ERROR, "Error", "An error occurred while loading the dashboard.");
            }
        } else {
            showAlert(AlertType.ERROR, "Login Error", "Invalid username or password.");
        }
    }
    
    /**
     * Show an alert dialog
     * 
     * @param type Alert type
     * @param title Alert title
     * @param message Alert message
     */
    private void showAlert(AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
