package com.pharmacy.model;

/**
 * Represents an individual item in a purchase order.
 */
public class PurchaseOrderItem {
    private int itemId;
    private int orderId;
    private int productId;
    private String productName;
    private int quantity;
    private double unitPrice;
    private double subtotal;

    /**
     * Default constructor
     */
    public PurchaseOrderItem() {
        this.quantity = 1;
        this.unitPrice = 0.0;
        this.subtotal = 0.0;
    }

    /**
     * Parameterized constructor
     * 
     * @param itemId Item ID
     * @param orderId Order ID
     * @param productId Product ID
     * @param productName Product name
     * @param quantity Quantity
     * @param unitPrice Unit price
     */
    public PurchaseOrderItem(int itemId, int orderId, int productId, String productName, 
                            int quantity, double unitPrice) {
        this.itemId = itemId;
        this.orderId = orderId;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.subtotal = quantity * unitPrice;
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

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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

    @Override
    public String toString() {
        return "PurchaseOrderItem{" +
                "itemId=" + itemId +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", subtotal=" + subtotal +
                '}';
    }
}
