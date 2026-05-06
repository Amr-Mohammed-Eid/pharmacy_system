package com.pharmacy.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a medication in the pharmacy inventory.
 * Extends the base Product class with medication-specific attributes.
 */
public class Medication extends Product {
    private int medicationId;
    private String dosage;
    private String activeIngredients;
    private String sideEffects;
    private String storageInstructions;
    private LocalDate expiryDate;
    private List<String> interactions;

    /**
     * Default constructor
     */
    public Medication() {
        super();
        this.interactions = new ArrayList<>();
    }

    /**
     * Parameterized constructor
     * 
     * @param productId Product ID
     * @param name Product name
     * @param description Product description
     * @param category Product category
     * @param manufacturer Product manufacturer
     * @param unitPrice Unit price
     * @param stockLevel Current stock level
     * @param reorderLevel Reorder threshold level
     * @param barcode Product barcode
     * @param prescriptionRequired Whether prescription is required
     * @param medicationId Medication ID
     * @param dosage Medication dosage
     * @param activeIngredients Active ingredients
     * @param sideEffects Side effects
     * @param storageInstructions Storage instructions
     * @param expiryDate Expiry date
     */
    public Medication(int productId, String name, String description, String category, 
                     String manufacturer, double unitPrice, int stockLevel, 
                     int reorderLevel, String barcode, boolean prescriptionRequired,
                     int medicationId, String dosage, String activeIngredients, 
                     String sideEffects, String storageInstructions, LocalDate expiryDate) {
        super(productId, name, description, category, manufacturer, unitPrice, 
             stockLevel, reorderLevel, barcode, prescriptionRequired);
        this.medicationId = medicationId;
        this.dosage = dosage;
        this.activeIngredients = activeIngredients;
        this.sideEffects = sideEffects;
        this.storageInstructions = storageInstructions;
        this.expiryDate = expiryDate;
        this.interactions = new ArrayList<>();
    }

    /**
     * Check if medication has expired
     * 
     * @return true if expired, false otherwise
     */
    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }

    /**
     * Check if medication will expire soon (within 90 days)
     * 
     * @return true if expiring soon, false otherwise
     */
    public boolean isExpiringSoon() {
        LocalDate warningDate = LocalDate.now().plusDays(90);
        return !isExpired() && expiryDate.isBefore(warningDate);
    }

    /**
     * Add a medication that interacts with this medication
     * 
     * @param medicationName Name of interacting medication
     */
    public void addInteraction(String medicationName) {
        if (!interactions.contains(medicationName)) {
            interactions.add(medicationName);
        }
    }

    /**
     * Check if this medication interacts with another medication
     * 
     * @param medicationName Name of medication to check
     * @return true if interaction exists, false otherwise
     */
    public boolean checkInteraction(String medicationName) {
        return interactions.contains(medicationName);
    }

    /**
     * Get all interactions for this medication
     * 
     * @return List of interacting medications
     */
    public List<String> getInteractions() {
        return new ArrayList<>(interactions);
    }

    /**
     * Verify if the dosage is within safe limits
     * In a real application, this would check against medical guidelines
     * 
     * @param patientAge Patient age
     * @param patientWeight Patient weight in kg
     * @return true if dosage is safe, false otherwise
     */
    public boolean verifyDosage(int patientAge, double patientWeight) {
        // Simplified dosage verification for demonstration
        // In a real application, this would involve complex medical rules
        return true;
    }

    // Getters and Setters
    public int getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(int medicationId) {
        this.medicationId = medicationId;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getActiveIngredients() {
        return activeIngredients;
    }

    public void setActiveIngredients(String activeIngredients) {
        this.activeIngredients = activeIngredients;
    }

    public String getSideEffects() {
        return sideEffects;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

    public String getStorageInstructions() {
        return storageInstructions;
    }

    public void setStorageInstructions(String storageInstructions) {
        this.storageInstructions = storageInstructions;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public String toString() {
        return "Medication{" +
                "productId=" + getProductId() +
                ", name='" + getName() + '\'' +
                ", dosage='" + dosage + '\'' +
                ", expiryDate=" + expiryDate +
                ", stockLevel=" + getStockLevel() +
                ", prescriptionRequired=" + isPrescriptionRequired() +
                '}';
    }
}
