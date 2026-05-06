package com.pharmacy.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.pharmacy.model.Product;
import com.pharmacy.model.Medication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Controller for the inventory view.
 */
public class InventoryController implements Initializable {

    @FXML
    private TableView<Product> productTable;
    
    @FXML
    private TableColumn<Product, Integer> idColumn;
    
    @FXML
    private TableColumn<Product, String> nameColumn;
    
    @FXML
    private TableColumn<Product, String> categoryColumn;
    
    @FXML
    private TableColumn<Product, String> manufacturerColumn;
    
    @FXML
    private TableColumn<Product, Double> priceColumn;
    
    @FXML
    private TableColumn<Product, Integer> stockColumn;
    
    @FXML
    private TextField nameField;
    
    @FXML
    private TextField descriptionField;
    
    @FXML
    private ComboBox<String> categoryComboBox;
    
    @FXML
    private TextField manufacturerField;
    
    @FXML
    private TextField priceField;
    
    @FXML
    private TextField stockField;
    
    @FXML
    private TextField reorderLevelField;
    
    @FXML
    private ComboBox<String> prescriptionRequiredComboBox;
    
    @FXML
    private TextField dosageField;
    
    @FXML
    private TextField activeIngredientsField;
    
    @FXML
    private DatePicker expiryDatePicker;
    
    @FXML
    private Button addButton;
    
    @FXML
    private Button updateButton;
    
    @FXML
    private Button deleteButton;
    
    @FXML
    private Button clearButton;
    
    private ObservableList<Product> productList = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize table columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("productId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        manufacturerColumn.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stockLevel"));
        
        // Initialize combo boxes
        categoryComboBox.setItems(FXCollections.observableArrayList(
            "Antibiotics", "Pain Relief", "Vitamins", "First Aid", "Skin Care", "Other"
        ));
        
        prescriptionRequiredComboBox.setItems(FXCollections.observableArrayList(
            "Yes", "No"
        ));
        
        // Add sample data
        addSampleData();
        
        // Set table data
        productTable.setItems(productList);
        
        // Add listener for table selection
        productTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                showProductDetails(newSelection);
            }
        });
    }
    
    /**
     * Add sample data to the product list
     */
    private void addSampleData() {
        productList.add(new Product(1, "Amoxicillin", "Antibiotic medication", "Antibiotics", 
                                   "PharmaCorp", 15.99, 120, 20, "P00000001", true));
        productList.add(new Product(2, "Ibuprofen", "Pain relief medication", "Pain Relief", 
                                   "MediPharm", 8.50, 200, 30, "P00000002", false));
        productList.add(new Product(3, "Vitamin C", "Immune support supplement", "Vitamins", 
                                   "NutriHealth", 12.75, 85, 15, "P00000003", false));
        productList.add(new Product(4, "Bandages", "Adhesive bandages for wounds", "First Aid", 
                                   "MediCare", 5.25, 150, 25, "P00000004", false));
        productList.add(new Product(5, "Hydrocortisone Cream", "Anti-itch cream", "Skin Care", 
                                   "DermaCare", 9.99, 75, 10, "P00000005", false));
    }
    
    /**
     * Show product details in the form
     * 
     * @param product Product to display
     */
    private void showProductDetails(Product product) {
        nameField.setText(product.getName());
        descriptionField.setText(product.getDescription());
        categoryComboBox.setValue(product.getCategory());
        manufacturerField.setText(product.getManufacturer());
        priceField.setText(String.valueOf(product.getUnitPrice()));
        stockField.setText(String.valueOf(product.getStockLevel()));
        reorderLevelField.setText(String.valueOf(product.getReorderLevel()));
        prescriptionRequiredComboBox.setValue(product.isPrescriptionRequired() ? "Yes" : "No");
        
        if (product instanceof Medication) {
            Medication medication = (Medication) product;
            dosageField.setText(medication.getDosage());
            activeIngredientsField.setText(medication.getActiveIngredients());
            if (medication.getExpiryDate() != null) {
                expiryDatePicker.setValue(medication.getExpiryDate());
            }
        } else {
            dosageField.clear();
            activeIngredientsField.clear();
            expiryDatePicker.setValue(null);
        }
    }
    
    /**
     * Handle add button click
     * 
     * @param event Action event
     */
    @FXML
    private void handleAdd(ActionEvent event) {
        try {
            // Validate input
            if (nameField.getText().isEmpty() || priceField.getText().isEmpty() || stockField.getText().isEmpty()) {
                showAlert(AlertType.ERROR, "Input Error", "Please fill in all required fields.");
                return;
            }
            
            // Parse input
            String name = nameField.getText();
            String description = descriptionField.getText();
            String category = categoryComboBox.getValue();
            String manufacturer = manufacturerField.getText();
            double unitPrice = Double.parseDouble(priceField.getText());
            int stockLevel = Integer.parseInt(stockField.getText());
            int reorderLevel = Integer.parseInt(reorderLevelField.getText());
            boolean prescriptionRequired = "Yes".equals(prescriptionRequiredComboBox.getValue());
            
            // Generate product ID and barcode
            int productId = productList.size() + 1;
            String barcode = "P" + String.format("%08d", productId);
            
            // Create product
            Product product = new Product(productId, name, description, category, manufacturer, 
                                         unitPrice, stockLevel, reorderLevel, barcode, prescriptionRequired);
            
            // Add to list
            productList.add(product);
            
            // Clear form
            clearForm();
            
            showAlert(AlertType.INFORMATION, "Success", "Product added successfully.");
        } catch (NumberFormatException e) {
            showAlert(AlertType.ERROR, "Input Error", "Please enter valid numbers for price, stock, and reorder level.");
        }
    }
    
    /**
     * Handle update button click
     * 
     * @param event Action event
     */
    @FXML
    private void handleUpdate(ActionEvent event) {
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
        
        if (selectedProduct == null) {
            showAlert(AlertType.ERROR, "Selection Error", "Please select a product to update.");
            return;
        }
        
        try {
            // Validate input
            if (nameField.getText().isEmpty() || priceField.getText().isEmpty() || stockField.getText().isEmpty()) {
                showAlert(AlertType.ERROR, "Input Error", "Please fill in all required fields.");
                return;
            }
            
            // Parse input
            String name = nameField.getText();
            String description = descriptionField.getText();
            String category = categoryComboBox.getValue();
            String manufacturer = manufacturerField.getText();
            double unitPrice = Double.parseDouble(priceField.getText());
            int stockLevel = Integer.parseInt(stockField.getText());
            int reorderLevel = Integer.parseInt(reorderLevelField.getText());
            boolean prescriptionRequired = "Yes".equals(prescriptionRequiredComboBox.getValue());
            
            // Update product
            selectedProduct.setName(name);
            selectedProduct.setDescription(description);
            selectedProduct.setCategory(category);
            selectedProduct.setManufacturer(manufacturer);
            selectedProduct.setUnitPrice(unitPrice);
            selectedProduct.setStockLevel(stockLevel);
            selectedProduct.setReorderLevel(reorderLevel);
            selectedProduct.setPrescriptionRequired(prescriptionRequired);
            
            // Refresh table
            productTable.refresh();
            
            showAlert(AlertType.INFORMATION, "Success", "Product updated successfully.");
        } catch (NumberFormatException e) {
            showAlert(AlertType.ERROR, "Input Error", "Please enter valid numbers for price, stock, and reorder level.");
        }
    }
    
    /**
     * Handle delete button click
     * 
     * @param event Action event
     */
    @FXML
    private void handleDelete(ActionEvent event) {
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
        
        if (selectedProduct == null) {
            showAlert(AlertType.ERROR, "Selection Error", "Please select a product to delete.");
            return;
        }
        
        productList.remove(selectedProduct);
        clearForm();
        
        showAlert(AlertType.INFORMATION, "Success", "Product deleted successfully.");
    }
    
    /**
     * Handle clear button click
     * 
     * @param event Action event
     */
    @FXML
    private void handleClear(ActionEvent event) {
        clearForm();
    }
    
    /**
     * Clear the form fields
     */
    private void clearForm() {
        nameField.clear();
        descriptionField.clear();
        categoryComboBox.setValue(null);
        manufacturerField.clear();
        priceField.clear();
        stockField.clear();
        reorderLevelField.clear();
        prescriptionRequiredComboBox.setValue(null);
        dosageField.clear();
        activeIngredientsField.clear();
        expiryDatePicker.setValue(null);
        
        productTable.getSelectionModel().clearSelection();
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
