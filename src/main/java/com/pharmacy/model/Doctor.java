package com.pharmacy.model;

import java.time.LocalDate;

/**
 * Represents a doctor in the pharmacy system.
 */
public class Doctor {
    private int doctorId;
    private String name;
    private String specialization;
    private String licenseNumber;
    private String phone;
    private String email;
    private String address;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    /**
     * Default constructor
     */
    public Doctor() {
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    /**
     * Parameterized constructor
     * 
     * @param doctorId Doctor ID
     * @param name Full name
     * @param specialization Medical specialization
     * @param licenseNumber License number
     * @param phone Phone number
     * @param email Email address
     * @param address Address
     */
    public Doctor(int doctorId, String name, String specialization, String licenseNumber,
                 String phone, String email, String address) {
        this.doctorId = doctorId;
        this.name = name;
        this.specialization = specialization;
        this.licenseNumber = licenseNumber;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    /**
     * Update doctor information
     * 
     * @param name Full name
     * @param specialization Medical specialization
     * @param phone Phone number
     * @param email Email address
     * @param address Address
     */
    public void updateInfo(String name, String specialization, String phone, String email, String address) {
        this.name = name;
        this.specialization = specialization;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.updatedAt = LocalDate.now();
    }

    // Getters and Setters
    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
        return "Doctor{" +
                "doctorId=" + doctorId +
                ", name='" + name + '\'' +
                ", specialization='" + specialization + '\'' +
                ", licenseNumber='" + licenseNumber + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
