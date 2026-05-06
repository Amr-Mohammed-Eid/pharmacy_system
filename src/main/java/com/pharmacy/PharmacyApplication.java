package com.pharmacy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main application class for the Pharmacy Management System.
 * This class serves as the entry point for the JavaFX application.
 */
public class PharmacyApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the main application FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
        Parent root = loader.load();
        
        // Set up the primary stage
        primaryStage.setTitle("Pharmacy Management System");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(600);
        primaryStage.show();
    }

    /**
     * Main method that launches the JavaFX application.
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
