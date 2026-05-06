package com.pharmacy.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.pharmacy.model.Sale;
import com.pharmacy.model.SaleItem;
import com.pharmacy.model.Patient;
import com.pharmacy.model.Product;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Controller for the sales view.
 */
public class SalesController implements Initializable {

    @FXML
    private TableView<Sale> saleTable;
    
    @FXML
    private TableColumn<Sale, Integer> idColumn;
    
    @FXML
    private TableColumn<Sale, Integer> patientIdColumn;
    
    @FXML
    private TableColumn<Sale, Integer> cashierIdColumn;
    
    @FXML
    private TableColumn<Sale, LocalDate> saleDateColumn;
    
    @FXML
    private TableColumn<Sale, Double> totalColumn;
    
    @FXML
    private TableColumn<Sale, String> statusColumn;
    
    @FXML
    private TableView<SaleItem> itemsTable;
    
    @FXML
    private TableColumn<SaleItem, Integer> itemIdColumn;
    
    @FXML
    private TableColumn<SaleItem, String> productNameColumn;
    
    @FXML
    private TableColumn<SaleItem, Integer> quantityColumn;
    
    @FXML
    private TableColumn<SaleItem, Double> unitPriceColumn;
    
    @FXML
    private TableColumn<SaleItem, Double> subtotalColumn;
    
    @FXML
    private ComboBox<Patient> patientComboBox;
    
    @FXML
    private ComboBox<Product> productComboBox;
    
    @FXML
    private TextField quantityField;
    
    @FXML
    private Button addItemButton;
    
    @FXML
    private Button removeItemButton;
    
    @FXML
    private Label subtotalLabel;
    
    @FXML
    private Label taxLabel;
    
    @FXML
    private Label discountLabel;
    
    @FXML
    private Label totalLabel;
    
    @FXML
    private TextField discountField;
    
    @FXML
    private ComboBox<String> discountTypeComboBox;
    
    @FXML
    private Button applyDiscountButton;
    
    @FXML
    private ComboBox<String> paymentMethodComboBox;
    
    @FXML
    private Button processPaymentButton;
    
    @FXML
    private Button newSaleButton;
    
    @FXML
    private Button printReceiptButton;
    
    private ObservableList<Sale> saleList = FXCollections.observableArrayList();
    private ObservableList<SaleItem> currentSaleItems = FXCollections.observableArrayList();
    private ObservableList<Patient> patientList = FXCollections.observableArrayList();
    private ObservableList<Product> productList = FXCollections.observableArrayList();
    
    private Sale currentSale;
    private int nextSaleId = 1;
    private int nextItemId = 1;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize table columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("saleId"));
        patientIdColumn.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        cashierIdColumn.setCellValueFactory(new PropertyValueFactory<>("cashierId"));
        saleDateColumn.setCellValueFactory(new PropertyValueFactory<>("saleDate"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("paymentStatus"));
        
        itemIdColumn.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        unitPriceColumn.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        subtotalColumn.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
        
        // Initialize combo boxes
        discountTypeComboBox.setItems(FXCollections.observableArrayList(
            "Amount", "Percentage"
        ));
        
        paymentMethodComboBox.setItems(FXCollections.observableArrayList(
            "Cash", "Credit Card", "Debit Card", "Insurance"
        ));
        
        // Add sample data
        addSampleData();
        
        // Set table data
        saleTable.setItems(saleList);
        itemsTable.setItems(currentSaleItems);
        patientComboBox.setItems(patientList);
        productComboBox.setItems(productList);
        
        // Add listener for table selection
        saleTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                showSaleDetails(newSelection);
            }
        });
        
        // Create a new sale initially
        handleNewSale(null);
    }
    
    /**
     * Add sample data to the lists
     */
    private void addSampleData() {
        // Add sample patients
        patientList.add(new Patient(1, "John Smith", LocalDate.of(1980, 5, 15), "Male", 
                                   "123 Main St", "555-1234", "john@example.com", "BlueCross", "BC12345"));
        patientList.add(new Patient(2, "Jane Doe", LocalDate.of(1975, 8, 22), "Female", 
                                   "456 Oak Ave", "555-5678", "jane@example.com", "Aetna", "AE67890"));
        patientList.add(new Patient(3, "Bob Johnson", LocalDate.of(1990, 3, 10), "Male", 
                                   "789 Pine Rd", "555-9012", "bob@example.com", "Cigna", "CI34567"));
        
        // Add sample products
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
        
        // Add sample sales
        Sale sale1 = new Sale(nextSaleId++, 1, 1, LocalDate.now().minusDays(2), "Cash");
        sale1.setSubtotal(24.49);
        sale1.setTax(2.45);
        sale1.setTotal(26.94);
        sale1.setPaymentStatus("Completed");
        
        Sale sale2 = new Sale(nextSaleId++, 2, 1, LocalDate.now().minusDays(1), "Credit Card");
        sale2.setSubtotal(18.25);
        sale2.setTax(1.83);
        sale2.setDiscount(2.00);
        sale2.setTotal(18.08);
        sale2.setPaymentStatus("Completed");
        
        saleList.add(sale1);
        saleList.add(sale2);
    }
    
    /**
     * Show sale details in the form
     * 
     * @param sale Sale to display
     */
    private void showSaleDetails(Sale sale) {
        currentSale = sale;
        
        // Find and select the patient if available
        if (sale.getPatientId() != null) {
            for (Patient patient : patientList) {
                if (patient.getPatientId() == sale.getPatientId()) {
                    patientComboBox.setValue(patient);
                    break;
                }
            }
        } else {
            patientComboBox.setValue(null);
        }
        
        // Update totals
        subtotalLabel.setText(String.format("$%.2f", sale.getSubtotal()));
        taxLabel.setText(String.format("$%.2f", sale.getTax()));
        discountLabel.setText(String.format("$%.2f", sale.getDiscount()));
        totalLabel.setText(String.format("$%.2f", sale.getTotal()));
        
        // Update payment method
        paymentMethodComboBox.setValue(sale.getPaymentMethod());
        
        // Update items table
        ObservableList<SaleItem> saleItems = FXCollections.observableArrayList();
        for (SaleItem item : sale.getItems()) {
            saleItems.add(item);
        }
        currentSaleItems.clear();
        currentSaleItems.addAll(saleItems);
        
        // Enable/disable buttons based on sale status
        boolean isCompleted = "Completed".equals(sale.getPaymentStatus());
        
        patientComboBox.setDisable(isCompleted);
        productComboBox.setDisable(isCompleted);
        quantityField.setDisable(isCompleted);
        addItemButton.setDisable(isCompleted);
        removeItemButton.setDisable(isCompleted);
        discountField.setDisable(isCompleted);
        discountTypeComboBox.setDisable(isCompleted);
        applyDiscountButton.setDisable(isCompleted);
        paymentMethodComboBox.setDisable(isCompleted);
        processPaymentButton.setDisable(isCompleted);
        printReceiptButton.setDisable(!isCompleted);
    }
    
    /**
     * Handle new sale button click
     * 
     * @param event Action event
     */
    @FXML
    private void handleNewSale(ActionEvent event) {
        // Create a new sale
        currentSale = new Sale(nextSaleId++, null, 1, LocalDate.now(), null);
        
        // Clear form
        patientComboBox.setValue(null);
        productComboBox.setValue(null);
        quantityField.clear();
        discountField.clear();
        discountTypeComboBox.setValue(null);
        paymentMethodComboBox.setValue(null);
        
        // Clear items
        currentSaleItems.clear();
        
        // Reset totals
        subtotalLabel.setText("$0.00");
        taxLabel.setText("$0.00");
        discountLabel.setText("$0.00");
        totalLabel.setText("$0.00");
        
        // Enable controls
        patientComboBox.setDisable(false);
        productComboBox.setDisable(false);
        quantityField.setDisable(false);
        addItemButton.setDisable(false);
        removeItemButton.setDisable(false);
        discountField.setDisable(false);
        discountTypeComboBox.setDisable(false);
        applyDiscountButton.setDisable(false);
        paymentMethodComboBox.setDisable(false);
        processPaymentButton.setDisable(false);
        printReceiptButton.setDisable(true);
    }
    
    /**
     * Handle add item button click
     * 
     * @param event Action event
     */
    @FXML
    private void handleAddItem(ActionEvent event) {
        try {
            // Validate input
            if (productComboBox.getValue() == null || quantityField.getText().isEmpty()) {
                showAlert(AlertType.ERROR, "Input Error", "Please select a product and enter quantity.");
                return;
            }
            
            // Parse input
            Product product = productComboBox.getValue();
            int quantity = Integer.parseInt(quantityField.getText());
            
            if (quantity <= 0) {
                showAlert(AlertType.ERROR, "Input Error", "Quantity must be greater than zero.");
                return;
            }
            
            if (quantity > product.getStockLevel()) {
                showAlert(AlertType.ERROR, "Stock Error", "Not enough stock available.");
                return;
            }
            
            // Create sale item
            SaleItem item = new SaleItem(nextItemId++, currentSale.getSaleId(), product.getProductId(), 
                                        product.getName(), quantity, product.getUnitPrice(), null);
            
            // Add to current sale
            currentSaleItems.add(item);
            
            // Update totals
            updateTotals();
            
            // Clear product selection
            productComboBox.setValue(null);
            quantityField.clear();
        } catch (NumberFormatException e) {
            showAlert(AlertType.ERROR, "Input Error", "Please enter a valid quantity.");
        }
    }
    
    /**
     * Handle remove item button click
     * 
     * @param event Action event
     */
    @FXML
    private void handleRemoveItem(ActionEvent event) {
        SaleItem selectedItem = itemsTable.getSelectionModel().getSelectedItem();
        
        if (selectedItem == null) {
            showAlert(AlertType.ERROR, "Selection Error", "Please select an item to remove.");
            return;
        }
        
        currentSaleItems.remove(selectedItem);
        
        // Update totals
        updateTotals();
    }
    
    /**
     * Handle apply discount button click
     * 
     * @param event Action event
     */
    @FXML
    private void handleApplyDiscount(ActionEvent event) {
        try {
            // Validate input
            if (discountField.getText().isEmpty() || discountTypeComboBox.getValue() == null) {
                showAlert(AlertType.ERROR, "Input Error", "Please enter discount amount and select type.");
                return;
            }
            
            // Parse input
            double discountAmount = Double.parseDouble(discountField.getText());
            String discountType = discountTypeComboBox.getValue();
            
            if (discountAmount < 0) {
                showAlert(AlertType.ERROR, "Input Error", "Discount amount must be non-negative.");
                return;
            }
            
            // Apply discount
            currentSale.applyDiscount(discountAmount, discountType);
            
            // Update totals
            updateTotals();
        } catch (NumberFormatException e) {
            showAlert(AlertType.ERROR, "Input Error", "Please enter a valid discount amount.");
        }
    }
    
    /**
     * Handle process payment button click
     * 
     * @param event Action event
     */
    @FXML
    private void handleProcessPayment(ActionEvent event) {
        // Validate input
        if (currentSaleItems.isEmpty()) {
            showAlert(AlertType.ERROR, "Input Error", "Cannot process payment for empty sale.");
            return;
        }
        
        if (paymentMethodComboBox.getValue() == null) {
            showAlert(AlertType.ERROR, "Input Error", "Please select a payment method.");
            return;
        }
        
        // Set patient ID if selected
        if (patientComboBox.getValue() != null) {
            currentSale.setPatientId(patientComboBox.getValue().getPatientId());
        }
        
        // Set payment method
        currentSale.setPaymentMethod(paymentMethodComboBox.getValue());
        
        // Process payment
        boolean success = currentSale.processPayment(paymentMethodComboBox.getValue(), currentSale.getTotal());
        
        if (success) {
            // Add items to sale
            for (SaleItem item : currentSaleItems) {
                currentSale.addItem(item);
            }
            
            // Add to sale list if not already there
            if (!saleList.contains(currentSale)) {
                saleList.add(currentSale);
            }
            
            // Disable controls
            patientComboBox.setDisable(true);
            productComboBox.setDisable(true);
            quantityField.setDisable(true);
            addItemButton.setDisable(true);
            removeItemButton.setDisable(true);
            discountField.setDisable(true);
            discountTypeComboBox.setDisable(true);
            applyDiscountButton.setDisable(true);
            paymentMethodComboBox.setDisable(true);
            processPaymentButton.setDisable(true);
            printReceiptButton.setDisable(false);
            
            showAlert(AlertType.INFORMATION, "Success", "Payment processed successfully.");
        } else {
            showAlert(AlertType.ERROR, "Payment Error", "Failed to process payment.");
        }
    }
    
    /**
     * Handle print receipt button click
     * 
     * @param event Action event
     */
    @FXML
    private void handlePrintReceipt(ActionEvent event) {
        if (currentSale == null || !"Completed".equals(currentSale.getPaymentStatus())) {
            showAlert(AlertType.ERROR, "Error", "No completed sale to print receipt for.");
            return;
        }
        
        String receipt = currentSale.generateReceipt();
        
        // In a real application, this would print the receipt
        // For demonstration, we'll show it in an alert
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Receipt");
        alert.setHeaderText("Sale #" + currentSale.getSaleId() + " Receipt");
        alert.setContentText(receipt);
        alert.showAndWait();
    }
    
    /**
     * Update the total labels based on current sale
     */
    private void updateTotals() {
        double subtotal = 0.0;
        
        for (SaleItem item : currentSaleItems) {
            subtotal += item.getSubtotal();
        }
        
        // Set subtotal
        currentSale.setSubtotal(subtotal);
        
        // Calculate tax (10% for demonstration)
        double tax = subtotal * 0.1;
        currentSale.setTax(tax);
        
        // Calculate total
        double total = subtotal + tax - currentSale.getDiscount();
        currentSale.setTotal(total);
        
        // Update labels
        subtotalLabel.setText(String.format("$%.2f", subtotal));
        taxLabel.setText(String.format("$%.2f", tax));
        discountLabel.setText(String.format("$%.2f", currentSale.getDiscount()));
        totalLabel.setText(String.format("$%.2f", total));
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
