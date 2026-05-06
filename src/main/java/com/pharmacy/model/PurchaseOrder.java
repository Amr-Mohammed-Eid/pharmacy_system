package com.pharmacy.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a purchase order in the pharmacy system.
 */
public class PurchaseOrder {
    private int orderId;
    private int supplierId;
    private LocalDate orderDate;
    private LocalDate expectedDelivery;
    private String status; // "Pending", "Approved", "Shipped", "Delivered", "Cancelled"
    private double totalAmount;
    private int createdBy;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private List<PurchaseOrderItem> items;

    /**
     * Default constructor
     */
    public PurchaseOrder() {
        this.orderDate = LocalDate.now();
        this.expectedDelivery = LocalDate.now().plusDays(7); // Default 7 days delivery
        this.status = "Pending";
        this.totalAmount = 0.0;
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
        this.items = new ArrayList<>();
    }

    /**
     * Parameterized constructor
     * 
     * @param orderId Order ID
     * @param supplierId Supplier ID
     * @param orderDate Order date
     * @param expectedDelivery Expected delivery date
     * @param status Order status
     * @param createdBy User ID of creator
     */
    public PurchaseOrder(int orderId, int supplierId, LocalDate orderDate, 
                        LocalDate expectedDelivery, String status, int createdBy) {
        this.orderId = orderId;
        this.supplierId = supplierId;
        this.orderDate = orderDate;
        this.expectedDelivery = expectedDelivery;
        this.status = status;
        this.totalAmount = 0.0;
        this.createdBy = createdBy;
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
        this.items = new ArrayList<>();
    }

    /**
     * Add an item to the purchase order
     * 
     * @param item Purchase order item to add
     */
    public void addItem(PurchaseOrderItem item) {
        items.add(item);
        recalculateTotal();
        this.updatedAt = LocalDate.now();
    }

    /**
     * Remove an item from the purchase order
     * 
     * @param itemId ID of the item to remove
     * @return true if item was removed, false if not found
     */
    public boolean removeItem(int itemId) {
        boolean removed = items.removeIf(item -> item.getItemId() == itemId);
        if (removed) {
            recalculateTotal();
            this.updatedAt = LocalDate.now();
        }
        return removed;
    }

    /**
     * Recalculate the total amount based on items
     */
    private void recalculateTotal() {
        this.totalAmount = items.stream()
                .mapToDouble(PurchaseOrderItem::getSubtotal)
                .sum();
    }

    /**
     * Update the status of the purchase order
     * 
     * @param newStatus New status
     * @return true if status was updated successfully
     */
    public boolean updateStatus(String newStatus) {
        if (isValidStatus(newStatus)) {
            this.status = newStatus;
            this.updatedAt = LocalDate.now();
            return true;
        }
        return false;
    }

    /**
     * Check if a status is valid
     * 
     * @param status Status to check
     * @return true if status is valid
     */
    private boolean isValidStatus(String status) {
        return status.equals("Pending") || 
               status.equals("Approved") || 
               status.equals("Shipped") || 
               status.equals("Delivered") || 
               status.equals("Cancelled");
    }

    // Getters and Setters
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getExpectedDelivery() {
        return expectedDelivery;
    }

    public void setExpectedDelivery(LocalDate expectedDelivery) {
        this.expectedDelivery = expectedDelivery;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (isValidStatus(status)) {
            this.status = status;
        }
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
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

    public List<PurchaseOrderItem> getItems() {
        return new ArrayList<>(items);
    }

    public void setItems(List<PurchaseOrderItem> items) {
        this.items = new ArrayList<>(items);
        recalculateTotal();
    }

    @Override
    public String toString() {
        return "PurchaseOrder{" +
                "orderId=" + orderId +
                ", supplierId=" + supplierId +
                ", orderDate=" + orderDate +
                ", status='" + status + '\'' +
                ", totalAmount=" + totalAmount +
                ", items=" + items.size() +
                '}';
    }
}
