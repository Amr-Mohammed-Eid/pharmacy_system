package com.pharmacy.model;

import java.time.LocalDate;

/**
 * Represents a product in the pharmacy inventory.
 * Base class for all pharmacy products.
 */
public class Product {
    private int productId;
    private String name;
    private String description;
    private String category;
    private String manufacturer;
    private double unitPrice;
    private int stockLevel;
    private int reorderLevel;
    private String barcode;
    private boolean prescriptionRequired;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    /**
     * Default constructor
     */
    public Product() {
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
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
     */
    public Product(int productId, String name, String description, String category, 
                  String manufacturer, double unitPrice, int stockLevel, 
                  int reorderLevel, String barcode, boolean prescriptionRequired) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.category = category;
        this.manufacturer = manufacturer;
        this.unitPrice = unitPrice;
        this.stockLevel = stockLevel;
        this.reorderLevel = reorderLevel;
        this.barcode = barcode;
        this.prescriptionRequired = prescriptionRequired;
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    /**
     * Update stock level
     * 
     * @param quantity Quantity to add (positive) or remove (negative)
     * @return Updated stock level
     */
    public int updateStock(int quantity) {
        this.stockLevel += quantity;
        this.updatedAt = LocalDate.now();
        return this.stockLevel;
    }

    /**
     * Check if stock is below reorder level
     * 
     * @return true if stock is below reorder level, false otherwise
     */
    public boolean isLowStock() {
        return this.stockLevel <= this.reorderLevel;
    }

    /**
     * Generate barcode for the product
     * In a real application, this would use a barcode generation algorithm
     * 
     * @return Generated barcode
     */
    public String generateBarcode() {
        // Simple barcode generation for demonstration
        String generatedBarcode = "P" + String.format("%08d", this.productId);
        this.barcode = generatedBarcode;
        return generatedBarcode;
    }

    // Getters and Setters
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(int stockLevel) {
        this.stockLevel = stockLevel;
    }

    public int getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(int reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public boolean isPrescriptionRequired() {
        return prescriptionRequired;
    }

    public void setPrescriptionRequired(boolean prescriptionRequired) {
        this.prescriptionRequired = prescriptionRequired;
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
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", unitPrice=" + unitPrice +
                ", stockLevel=" + stockLevel +
                ", prescriptionRequired=" + prescriptionRequired +
                '}';
    }
}
