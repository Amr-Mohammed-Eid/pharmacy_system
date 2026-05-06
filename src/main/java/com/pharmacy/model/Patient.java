package com.pharmacy.model;

import java.time.LocalDate;

/**
 * Represents a patient in the pharmacy system.
 */
public class Patient {
    private int patientId;
    private String name;
    private LocalDate dateOfBirth;
    private String gender;
    private String address;
    private String phone;
    private String email;
    private String insuranceProvider;
    private String insuranceNumber;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    /**
     * Default constructor
     */
    public Patient() {
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    /**
     * Parameterized constructor
     * 
     * @param patientId Patient ID
     * @param name Full name
     * @param dateOfBirth Date of birth
     * @param gender Gender
     * @param address Address
     * @param phone Phone number
     * @param email Email address
     * @param insuranceProvider Insurance provider
     * @param insuranceNumber Insurance number
     */
    public Patient(int patientId, String name, LocalDate dateOfBirth, String gender,
                  String address, String phone, String email, 
                  String insuranceProvider, String insuranceNumber) {
        this.patientId = patientId;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.insuranceProvider = insuranceProvider;
        this.insuranceNumber = insuranceNumber;
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    /**
     * Calculate patient's age based on date of birth
     * 
     * @return Age in years
     */
    public int calculateAge() {
        return LocalDate.now().getYear() - dateOfBirth.getYear();
    }

    /**
     * Update patient information
     * 
     * @param name Full name
     * @param address Address
     * @param phone Phone number
     * @param email Email address
     * @param insuranceProvider Insurance provider
     * @param insuranceNumber Insurance number
     */
    public void updateInfo(String name, String address, String phone, String email,
                          String insuranceProvider, String insuranceNumber) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.insuranceProvider = insuranceProvider;
        this.insuranceNumber = insuranceNumber;
        this.updatedAt = LocalDate.now();
    }

    // Getters and Setters
    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInsuranceProvider() {
        return insuranceProvider;
    }

    public void setInsuranceProvider(String insuranceProvider) {
        this.insuranceProvider = insuranceProvider;
    }

    public String getInsuranceNumber() {
        return insuranceNumber;
    }

    public void setInsuranceNumber(String insuranceNumber) {
        this.insuranceNumber = insuranceNumber;
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

    @Override
    public String toString() {
        return "Patient{" +
                "patientId=" + patientId +
                ", name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", insuranceProvider='" + insuranceProvider + '\'' +
                '}';
    }
}
