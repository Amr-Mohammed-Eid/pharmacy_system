package com.pharmacy.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a prescription report in the pharmacy system.
 * Extends the base Report class with prescription-specific functionality.
 */
public class PrescriptionReport extends Report {
    private int totalPrescriptions;
    private int newPrescriptions;
    private int dispensedPrescriptions;
    private int expiredPrescriptions;
    private Map<Integer, Integer> doctorPrescriptions;
    private Map<String, Integer> medicationPrescriptions;

    /**
     * Default constructor
     */
    public PrescriptionReport() {
        super();
        this.setType("Prescription");
        this.totalPrescriptions = 0;
        this.newPrescriptions = 0;
        this.dispensedPrescriptions = 0;
        this.expiredPrescriptions = 0;
        this.doctorPrescriptions = new HashMap<>();
        this.medicationPrescriptions = new HashMap<>();
    }

    /**
     * Parameterized constructor
     * 
     * @param reportId Report ID
     * @param startDate Start date for report period
     * @param endDate End date for report period
     * @param generatedBy User ID of generator
     */
    public PrescriptionReport(int reportId, LocalDate startDate, LocalDate endDate, int generatedBy) {
        super(reportId, "Prescription", startDate, endDate, generatedBy);
        this.totalPrescriptions = 0;
        this.newPrescriptions = 0;
        this.dispensedPrescriptions = 0;
        this.expiredPrescriptions = 0;
        this.doctorPrescriptions = new HashMap<>();
        this.medicationPrescriptions = new HashMap<>();
    }

    @Override
    public boolean generate() {
        // In a real application, this would query the database for prescription data
        // For demonstration, we'll simulate some data
        
        // Simulate prescription statistics
        this.totalPrescriptions = 180;
        this.newPrescriptions = 45;
        this.dispensedPrescriptions = 120;
        this.expiredPrescriptions = 15;
        
        // Simulate doctor prescription data
        this.doctorPrescriptions.put(1, 35);
        this.doctorPrescriptions.put(2, 42);
        this.doctorPrescriptions.put(3, 28);
        this.doctorPrescriptions.put(4, 50);
        this.doctorPrescriptions.put(5, 25);
        
        // Simulate medication prescription data
        this.medicationPrescriptions.put("Amoxicillin", 30);
        this.medicationPrescriptions.put("Ibuprofen", 45);
        this.medicationPrescriptions.put("Lisinopril", 25);
        this.medicationPrescriptions.put("Metformin", 35);
        this.medicationPrescriptions.put("Atorvastatin", 20);
        
        return true;
    }

    /**
     * Analyze prescription trends over time
     * 
     * @return Map of dates to prescription counts
     */
    public Map<LocalDate, Integer> analyzePrescriptionTrends() {
        Map<LocalDate, Integer> prescriptionTrends = new HashMap<>();
        
        // In a real application, this would calculate prescription counts by date
        // For demonstration, we'll simulate some data
        LocalDate currentDate = getStartDate();
        while (!currentDate.isAfter(getEndDate())) {
            // Generate random prescription count between 1 and 10
            int count = 1 + (int)(Math.random() * 10);
            prescriptionTrends.put(currentDate, count);
            currentDate = currentDate.plusDays(1);
        }
        
        return prescriptionTrends;
    }

    /**
     * Identify top prescribing doctors
     * 
     * @param limit Number of top doctors to return
     * @return Map of doctor IDs to prescription counts, sorted by count
     */
    public Map<Integer, Integer> identifyTopPrescribers(int limit) {
        // In a real application, this would sort the doctor prescriptions and return the top ones
        // For demonstration, we'll return the existing map
        return this.doctorPrescriptions;
    }

    // Getters and Setters
    public int getTotalPrescriptions() {
        return totalPrescriptions;
    }

    public void setTotalPrescriptions(int totalPrescriptions) {
        this.totalPrescriptions = totalPrescriptions;
    }

    public int getNewPrescriptions() {
        return newPrescriptions;
    }

    public void setNewPrescriptions(int newPrescriptions) {
        this.newPrescriptions = newPrescriptions;
    }

    public int getDispensedPrescriptions() {
        return dispensedPrescriptions;
    }

    public void setDispensedPrescriptions(int dispensedPrescriptions) {
        this.dispensedPrescriptions = dispensedPrescriptions;
    }

    public int getExpiredPrescriptions() {
        return expiredPrescriptions;
    }

    public void setExpiredPrescriptions(int expiredPrescriptions) {
        this.expiredPrescriptions = expiredPrescriptions;
    }

    public Map<Integer, Integer> getDoctorPrescriptions() {
        return new HashMap<>(doctorPrescriptions);
    }

    public void setDoctorPrescriptions(Map<Integer, Integer> doctorPrescriptions) {
        this.doctorPrescriptions = new HashMap<>(doctorPrescriptions);
    }

    public Map<String, Integer> getMedicationPrescriptions() {
        return new HashMap<>(medicationPrescriptions);
    }

    public void setMedicationPrescriptions(Map<String, Integer> medicationPrescriptions) {
        this.medicationPrescriptions = new HashMap<>(medicationPrescriptions);
    }

    @Override
    public String toString() {
        return "PrescriptionReport{" +
                "reportId=" + getReportId() +
                ", totalPrescriptions=" + totalPrescriptions +
                ", newPrescriptions=" + newPrescriptions +
                ", dispensedPrescriptions=" + dispensedPrescriptions +
                ", expiredPrescriptions=" + expiredPrescriptions +
                '}';
    }
}
