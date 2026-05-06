package com.pharmacy.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a prescription in the pharmacy system.
 */
public class Prescription {
    private int prescriptionId;
    private int patientId;
    private int doctorId;
    private LocalDate issueDate;
    private LocalDate expiryDate;
    private String status; // "New", "Verified", "Dispensed", "Expired"
    private String notes;
    private int createdBy;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private List<PrescriptionItem> items;

    /**
     * Default constructor
     */
    public Prescription() {
        this.issueDate = LocalDate.now();
        this.expiryDate = LocalDate.now().plusMonths(6); // Default 6 months validity
        this.status = "New";
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
        this.items = new ArrayList<>();
    }

    /**
     * Parameterized constructor
     * 
     * @param prescriptionId Prescription ID
     * @param patientId Patient ID
     * @param doctorId Doctor ID
     * @param issueDate Issue date
     * @param expiryDate Expiry date
     * @param status Status
     * @param notes Notes
     * @param createdBy User ID of creator
     */
    public Prescription(int prescriptionId, int patientId, int doctorId, 
                       LocalDate issueDate, LocalDate expiryDate, String status, 
                       String notes, int createdBy) {
        this.prescriptionId = prescriptionId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.issueDate = issueDate;
        this.expiryDate = expiryDate;
        this.status = status;
        this.notes = notes;
        this.createdBy = createdBy;
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
        this.items = new ArrayList<>();
    }

    /**
     * Add an item to the prescription
     * 
     * @param item Prescription item to add
     */
    public void addItem(PrescriptionItem item) {
        items.add(item);
        this.updatedAt = LocalDate.now();
    }

    /**
     * Remove an item from the prescription
     * 
     * @param itemId ID of the item to remove
     * @return true if item was removed, false if not found
     */
    public boolean removeItem(int itemId) {
        boolean removed = items.removeIf(item -> item.getItemId() == itemId);
        if (removed) {
            this.updatedAt = LocalDate.now();
        }
        return removed;
    }

    /**
     * Verify the prescription
     * 
     * @param pharmacistId ID of the pharmacist verifying the prescription
     * @return true if verification was successful
     */
    public boolean verify(int pharmacistId) {
        if (!"New".equals(this.status)) {
            return false;
        }
        
        this.status = "Verified";
        this.updatedAt = LocalDate.now();
        return true;
    }

    /**
     * Mark prescription as dispensed
     * 
     * @param pharmacistId ID of the pharmacist dispensing the prescription
     * @return true if dispensing was successful
     */
    public boolean dispense(int pharmacistId) {
        if (!"Verified".equals(this.status)) {
            return false;
        }
        
        this.status = "Dispensed";
        this.updatedAt = LocalDate.now();
        return true;
    }

    /**
     * Check if prescription is valid (not expired and not already dispensed)
     * 
     * @return true if prescription is valid
     */
    public boolean isValid() {
        return !isExpired() && !"Dispensed".equals(this.status);
    }

    /**
     * Check if prescription has expired
     * 
     * @return true if expired
     */
    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }

    /**
     * Calculate total cost of all items in the prescription
     * 
     * @return Total cost
     */
    public double calculateTotal() {
        return items.stream()
                .mapToDouble(PrescriptionItem::calculatePrice)
                .sum();
    }

    // Getters and Setters
    public int getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<PrescriptionItem> getItems() {
        return new ArrayList<>(items);
    }

    public void setItems(List<PrescriptionItem> items) {
        this.items = new ArrayList<>(items);
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "prescriptionId=" + prescriptionId +
                ", patientId=" + patientId +
                ", doctorId=" + doctorId +
                ", issueDate=" + issueDate +
                ", status='" + status + '\'' +
                ", items=" + items.size() +
                '}';
    }
}
