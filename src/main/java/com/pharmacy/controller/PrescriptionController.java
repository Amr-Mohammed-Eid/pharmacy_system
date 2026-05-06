package com.pharmacy.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.pharmacy.model.Patient;
import com.pharmacy.model.Doctor;
import com.pharmacy.model.Prescription;
import com.pharmacy.model.PrescriptionItem;

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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Controller for the prescriptions view.
 */
public class PrescriptionController implements Initializable {

    @FXML
    private TableView<Prescription> prescriptionTable;
    
    @FXML
    private TableColumn<Prescription, Integer> idColumn;
    
    @FXML
    private TableColumn<Prescription, Integer> patientIdColumn;
    
    @FXML
    private TableColumn<Prescription, Integer> doctorIdColumn;
    
    @FXML
    private TableColumn<Prescription, LocalDate> issueDateColumn;
    
    @FXML
    private TableColumn<Prescription, String> statusColumn;
    
    @FXML
    private ComboBox<Patient> patientComboBox;
    
    @FXML
    private ComboBox<Doctor> doctorComboBox;
    
    @FXML
    private DatePicker issueDatePicker;
    
    @FXML
    private DatePicker expiryDatePicker;
    
    @FXML
    private ComboBox<String> statusComboBox;
    
    @FXML
    private TextArea notesTextArea;
    
    @FXML
    private TableView<PrescriptionItem> itemsTable;
    
    @FXML
    private TableColumn<PrescriptionItem, Integer> itemIdColumn;
    
    @FXML
    private TableColumn<PrescriptionItem, Integer> medicationIdColumn;
    
    @FXML
    private TableColumn<PrescriptionItem, String> dosageColumn;
    
    @FXML
    private TableColumn<PrescriptionItem, Integer> quantityColumn;
    
    @FXML
    private Button addButton;
    
    @FXML
    private Button updateButton;
    
    @FXML
    private Button deleteButton;
    
    @FXML
    private Button clearButton;
    
    @FXML
    private Button verifyButton;
    
    @FXML
    private Button dispenseButton;
    
    private ObservableList<Prescription> prescriptionList = FXCollections.observableArrayList();
    private ObservableList<PrescriptionItem> itemsList = FXCollections.observableArrayList();
    private ObservableList<Patient> patientList = FXCollections.observableArrayList();
    private ObservableList<Doctor> doctorList = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize table columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("prescriptionId"));
        patientIdColumn.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        doctorIdColumn.setCellValueFactory(new PropertyValueFactory<>("doctorId"));
        issueDateColumn.setCellValueFactory(new PropertyValueFactory<>("issueDate"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        itemIdColumn.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        medicationIdColumn.setCellValueFactory(new PropertyValueFactory<>("medicationId"));
        dosageColumn.setCellValueFactory(new PropertyValueFactory<>("dosage"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        
        // Initialize combo boxes
        statusComboBox.setItems(FXCollections.observableArrayList(
            "New", "Verified", "Dispensed", "Expired"
        ));
        
        // Add sample data
        addSampleData();
        
        // Set table data
        prescriptionTable.setItems(prescriptionList);
        itemsTable.setItems(itemsList);
        patientComboBox.setItems(patientList);
        doctorComboBox.setItems(doctorList);
        
        // Add listener for table selection
        prescriptionTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                showPrescriptionDetails(newSelection);
            }
        });
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
        
        // Add sample doctors
        doctorList.add(new Doctor(1, "Dr. Sarah Wilson", "General Practitioner", "MD12345", 
                                 "555-2468", "sarah@example.com", "Medical Center"));
        doctorList.add(new Doctor(2, "Dr. Michael Brown", "Cardiologist", "MD67890", 
                                 "555-1357", "michael@example.com", "Heart Clinic"));
        doctorList.add(new Doctor(3, "Dr. Emily Davis", "Pediatrician", "MD24680", 
                                 "555-3691", "emily@example.com", "Children's Hospital"));
        
        // Add sample prescriptions
        Prescription prescription1 = new Prescription(1, 1, 1, LocalDate.now(), 
                                                    LocalDate.now().plusMonths(6), "Verified", 
                                                    "Take with food", 1);
        Prescription prescription2 = new Prescription(2, 2, 2, LocalDate.now().minusDays(7), 
                                                    LocalDate.now().plusMonths(3), "New", 
                                                    "Refill allowed once", 1);
        Prescription prescription3 = new Prescription(3, 3, 3, LocalDate.now().minusDays(14), 
                                                    LocalDate.now().plusMonths(1), "Dispensed", 
                                                    "No alcohol", 1);
        
        prescriptionList.add(prescription1);
        prescriptionList.add(prescription2);
        prescriptionList.add(prescription3);
        
        // Add sample prescription items
        PrescriptionItem item1 = new PrescriptionItem(1, 1, 1, "500mg", "Twice daily", "7 days", 
                                                    "Take with water", 14, 0, 15.99);
        PrescriptionItem item2 = new PrescriptionItem(2, 1, 2, "200mg", "Once daily", "30 days", 
                                                    "Take after meal", 30, 1, 8.50);
        PrescriptionItem item3 = new PrescriptionItem(3, 2, 3, "50mg", "Three times daily", "10 days", 
                                                    "Avoid sunlight", 30, 0, 12.75);
        
        itemsList.add(item1);
        itemsList.add(item2);
        itemsList.add(item3);
    }
    
    /**
     * Show prescription details in the form
     * 
     * @param prescription Prescription to display
     */
    private void showPrescriptionDetails(Prescription prescription) {
        // Find and select the patient
        for (Patient patient : patientList) {
            if (patient.getPatientId() == prescription.getPatientId()) {
                patientComboBox.setValue(patient);
                break;
            }
        }
        
        // Find and select the doctor
        for (Doctor doctor : doctorList) {
            if (doctor.getDoctorId() == prescription.getDoctorId()) {
                doctorComboBox.setValue(doctor);
                break;
            }
        }
        
        issueDatePicker.setValue(prescription.getIssueDate());
        expiryDatePicker.setValue(prescription.getExpiryDate());
        statusComboBox.setValue(prescription.getStatus());
        notesTextArea.setText(prescription.getNotes());
        
        // Update items table to show only items for this prescription
        ObservableList<PrescriptionItem> prescriptionItems = FXCollections.observableArrayList();
        for (PrescriptionItem item : itemsList) {
            if (item.getPrescriptionId() == prescription.getPrescriptionId()) {
                prescriptionItems.add(item);
            }
        }
        itemsTable.setItems(prescriptionItems);
        
        // Enable/disable buttons based on prescription status
        boolean isNew = "New".equals(prescription.getStatus());
        boolean isVerified = "Verified".equals(prescription.getStatus());
        
        verifyButton.setDisable(!isNew);
        dispenseButton.setDisable(!isVerified);
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
            if (patientComboBox.getValue() == null || doctorComboBox.getValue() == null || 
                issueDatePicker.getValue() == null || expiryDatePicker.getValue() == null || 
                statusComboBox.getValue() == null) {
                showAlert(AlertType.ERROR, "Input Error", "Please fill in all required fields.");
                return;
            }
            
            // Parse input
            Patient patient = patientComboBox.getValue();
            Doctor doctor = doctorComboBox.getValue();
            LocalDate issueDate = issueDatePicker.getValue();
            LocalDate expiryDate = expiryDatePicker.getValue();
            String status = statusComboBox.getValue();
            String notes = notesTextArea.getText();
            
            // Generate prescription ID
            int prescriptionId = prescriptionList.size() + 1;
            
            // Create prescription
            Prescription prescription = new Prescription(prescriptionId, patient.getPatientId(), 
                                                       doctor.getDoctorId(), issueDate, expiryDate, 
                                                       status, notes, 1);
            
            // Add to list
            prescriptionList.add(prescription);
            
            // Clear form
            clearForm();
            
            showAlert(AlertType.INFORMATION, "Success", "Prescription added successfully.");
        } catch (Exception e) {
            showAlert(AlertType.ERROR, "Input Error", "Please check your input and try again.");
        }
    }
    
    /**
     * Handle update button click
     * 
     * @param event Action event
     */
    @FXML
    private void handleUpdate(ActionEvent event) {
        Prescription selectedPrescription = prescriptionTable.getSelectionModel().getSelectedItem();
        
        if (selectedPrescription == null) {
            showAlert(AlertType.ERROR, "Selection Error", "Please select a prescription to update.");
            return;
        }
        
        try {
            // Validate input
            if (patientComboBox.getValue() == null || doctorComboBox.getValue() == null || 
                issueDatePicker.getValue() == null || expiryDatePicker.getValue() == null || 
                statusComboBox.getValue() == null) {
                showAlert(AlertType.ERROR, "Input Error", "Please fill in all required fields.");
                return;
            }
            
            // Parse input
            Patient patient = patientComboBox.getValue();
            Doctor doctor = doctorComboBox.getValue();
            LocalDate issueDate = issueDatePicker.getValue();
            LocalDate expiryDate = expiryDatePicker.getValue();
            String status = statusComboBox.getValue();
            String notes = notesTextArea.getText();
            
            // Update prescription
            selectedPrescription.setPatientId(patient.getPatientId());
            selectedPrescription.setDoctorId(doctor.getDoctorId());
            selectedPrescription.setIssueDate(issueDate);
            selectedPrescription.setExpiryDate(expiryDate);
            selectedPrescription.setStatus(status);
            selectedPrescription.setNotes(notes);
            
            // Refresh table
            prescriptionTable.refresh();
            
            showAlert(AlertType.INFORMATION, "Success", "Prescription updated successfully.");
        } catch (Exception e) {
            showAlert(AlertType.ERROR, "Input Error", "Please check your input and try again.");
        }
    }
    
    /**
     * Handle delete button click
     * 
     * @param event Action event
     */
    @FXML
    private void handleDelete(ActionEvent event) {
        Prescription selectedPrescription = prescriptionTable.getSelectionModel().getSelectedItem();
        
        if (selectedPrescription == null) {
            showAlert(AlertType.ERROR, "Selection Error", "Please select a prescription to delete.");
            return;
        }
        
        prescriptionList.remove(selectedPrescription);
        clearForm();
        
        showAlert(AlertType.INFORMATION, "Success", "Prescription deleted successfully.");
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
     * Handle verify button click
     * 
     * @param event Action event
     */
    @FXML
    private void handleVerify(ActionEvent event) {
        Prescription selectedPrescription = prescriptionTable.getSelectionModel().getSelectedItem();
        
        if (selectedPrescription == null) {
            showAlert(AlertType.ERROR, "Selection Error", "Please select a prescription to verify.");
            return;
        }
        
        if (!"New".equals(selectedPrescription.getStatus())) {
            showAlert(AlertType.ERROR, "Status Error", "Only prescriptions with 'New' status can be verified.");
            return;
        }
        
        selectedPrescription.setStatus("Verified");
        prescriptionTable.refresh();
        
        // Update form
        statusComboBox.setValue("Verified");
        verifyButton.setDisable(true);
        dispenseButton.setDisable(false);
        
        showAlert(AlertType.INFORMATION, "Success", "Prescription verified successfully.");
    }
    
    /**
     * Handle dispense button click
     * 
     * @param event Action event
     */
    @FXML
    private void handleDispense(ActionEvent event) {
        Prescription selectedPrescription = prescriptionTable.getSelectionModel().getSelectedItem();
        
        if (selectedPrescription == null) {
            showAlert(AlertType.ERROR, "Selection Error", "Please select a prescription to dispense.");
            return;
        }
        
        if (!"Verified".equals(selectedPrescription.getStatus())) {
            showAlert(AlertType.ERROR, "Status Error", "Only prescriptions with 'Verified' status can be dispensed.");
            return;
        }
        
        selectedPrescription.setStatus("Dispensed");
        prescriptionTable.refresh();
        
        // Update form
        statusComboBox.setValue("Dispensed");
        verifyButton.setDisable(true);
        dispenseButton.setDisable(true);
        
        showAlert(AlertType.INFORMATION, "Success", "Prescription dispensed successfully.");
    }
    
    /**
     * Clear the form fields
     */
    private void clearForm() {
        patientComboBox.setValue(null);
        doctorComboBox.setValue(null);
        issueDatePicker.setValue(null);
        expiryDatePicker.setValue(null);
        statusComboBox.setValue(null);
        notesTextArea.clear();
        
        prescriptionTable.getSelectionModel().clearSelection();
        itemsTable.setItems(FXCollections.observableArrayList());
        
        verifyButton.setDisable(true);
        dispenseButton.setDisable(true);
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
