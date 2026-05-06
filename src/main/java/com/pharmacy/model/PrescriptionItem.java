package com.pharmacy.model;

/**
 * Represents an individual item in a prescription.
 */
public class PrescriptionItem {
    private int itemId;
    private int prescriptionId;
    private int medicationId;
    private String dosage;
    private String frequency;
    private String duration;
    private String instructions;
    private int quantity;
    private int refillsAllowed;
    private int refillsUsed;
    private double unitPrice;

    /**
     * Default constructor
     */
    public PrescriptionItem() {
        this.refillsUsed = 0;
    }

    /**
     * Parameterized constructor
     * 
     * @param itemId Item ID
     * @param prescriptionId Prescription ID
     * @param medicationId Medication ID
     * @param dosage Dosage instructions
     * @param frequency Frequency of use
     * @param duration Duration of treatment
     * @param instructions Special instructions
     * @param quantity Quantity prescribed
     * @param refillsAllowed Number of refills allowed
     * @param unitPrice Unit price of medication
     */
    public PrescriptionItem(int itemId, int prescriptionId, int medicationId, 
                           String dosage, String frequency, String duration, 
                           String instructions, int quantity, int refillsAllowed,
                           double unitPrice) {
        this.itemId = itemId;
        this.prescriptionId = prescriptionId;
        this.medicationId = medicationId;
        this.dosage = dosage;
        this.frequency = frequency;
        this.duration = duration;
        this.instructions = instructions;
        this.quantity = quantity;
        this.refillsAllowed = refillsAllowed;
        this.refillsUsed = 0;
        this.unitPrice = unitPrice;
    }

    /**
     * Update the quantity of medication
     * 
     * @param newQuantity New quantity
     */
    public void updateQuantity(int newQuantity) {
        if (newQuantity > 0) {
            this.quantity = newQuantity;
        }
    }

    /**
     * Process a refill of this prescription item
     * 
     * @return true if refill was processed successfully, false if no refills remaining
     */
    public boolean processRefill() {
        if (refillsUsed < refillsAllowed) {
            refillsUsed++;
            return true;
        }
        return false;
    }

    /**
     * Check if refills are available
     * 
     * @return true if refills are available
     */
    public boolean hasRefillsAvailable() {
        return refillsUsed < refillsAllowed;
    }

    /**
     * Calculate the total price for this prescription item
     * 
     * @return Total price
     */
    public double calculatePrice() {
        return quantity * unitPrice;
    }

    // Getters and Setters
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

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

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getRefillsAllowed() {
        return refillsAllowed;
    }

    public void setRefillsAllowed(int refillsAllowed) {
        this.refillsAllowed = refillsAllowed;
    }

    public int getRefillsUsed() {
        return refillsUsed;
    }

    public void setRefillsUsed(int refillsUsed) {
        this.refillsUsed = refillsUsed;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "PrescriptionItem{" +
                "itemId=" + itemId +
                ", medicationId=" + medicationId +
                ", dosage='" + dosage + '\'' +
                ", quantity=" + quantity +
                ", refillsAllowed=" + refillsAllowed +
                ", refillsUsed=" + refillsUsed +
                '}';
    }
}
