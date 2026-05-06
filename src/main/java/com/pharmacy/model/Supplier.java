package com.pharmacy.model;

import java.time.LocalDate;

/**
 * Represents a supplier in the pharmacy system.
 */
public class Supplier {
    private int supplierId;
    private String name;
    private String contactPerson;
    private String phone;
    private String email;
    private String address;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private boolean active;

    /**
     * Default constructor
     */
    public Supplier() {
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
        this.active = true;
    }

    /**
     * Parameterized constructor
     * 
     * @param supplierId Supplier ID
     * @param name Company name
     * @param contactPerson Contact person name
     * @param phone Phone number
     * @param email Email address
     * @param address Physical address
     */
    public Supplier(int supplierId, String name, String contactPerson, String phone, 
                   String email, String address) {
        this.supplierId = supplierId;
        this.name = name;
        this.contactPerson = contactPerson;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
        this.active = true;
    }

    /**
     * Update supplier information
     * 
     * @param name Company name
     * @param contactPerson Contact person name
     * @param phone Phone number
     * @param email Email address
     * @param address Physical address
     */
    public void updateInfo(String name, String contactPerson, String phone, String email, String address) {
        this.name = name;
        this.contactPerson = contactPerson;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.updatedAt = LocalDate.now();
    }

    /**
     * Place an order with this supplier
     * In a real application, this would communicate with the supplier
     * 
     * @param orderId Order ID
     * @param products List of products to order
     * @param quantities List of quantities for each product
     * @return true if order was placed successfully
     */
    public boolean placeOrder(int orderId, int[] products, int[] quantities) {
        // Simplified order placement for demonstration
        // In a real application, this would involve communication with the supplier
        return true;
    }

    // Getters and Setters
    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "supplierId=" + supplierId +
                ", name='" + name + '\'' +
                ", contactPerson='" + contactPerson + '\'' +
                ", phone='" + phone + '\'' +
                ", active=" + active +
                '}';
    }
}
