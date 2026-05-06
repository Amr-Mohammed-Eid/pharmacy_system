package com.pharmacy.model;

/**
 * Represents an individual item in a sale transaction.
 */
public class SaleItem {
    private int itemId;
    private int saleId;
    private int productId;
    private String productName;
    private int quantity;
    private double unitPrice;
    private double subtotal;
    private Integer prescriptionId; // Optional, can be null for non-prescription items

    /**
     * Default constructor
     */
    public SaleItem() {
        this.quantity = 1;
        this.unitPrice = 0.0;
        this.subtotal = 0.0;
    }

    /**
     * Parameterized constructor
     * 
     * @param itemId Item ID
     * @param saleId Sale ID
     * @param productId Product ID
     * @param productName Product name
     * @param quantity Quantity
     * @param unitPrice Unit price
     * @param prescriptionId Prescription ID (optional)
     */
    public SaleItem(int itemId, int saleId, int productId, String productName, 
                   int quantity, double unitPrice, Integer prescriptionId) {
        this.itemId = itemId;
        this.saleId = saleId;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.subtotal = quantity * unitPrice;
        this.prescriptionId = prescriptionId;
    }

    /**
     * Update the quantity of the item
     * 
     * @param newQuantity New quantity
     */
    public void updateQuantity(int newQuantity) {
        if (newQuantity > 0) {
            this.quantity = newQuantity;
            calculateSubtotal();
        }
    }

    /**
     * Calculate the subtotal based on quantity and unit price
     * 
     * @return Calculated subtotal
     */
    public double calculateSubtotal() {
        this.subtotal = this.quantity * this.unitPrice;
        return this.subtotal;
    }

    // Getters and Setters
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        calculateSubtotal();
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
        calculateSubtotal();
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public Integer getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(Integer prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    @Override
    public String toString() {
        return "SaleItem{" +
                "itemId=" + itemId +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", subtotal=" + subtotal +
                '}';
    }
}
