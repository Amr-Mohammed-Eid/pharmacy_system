package com.pharmacy.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a sales transaction in the pharmacy system.
 */
public class Sale {
    private int saleId;
    private Integer patientId; // Optional, can be null for non-prescription sales
    private int cashierId;
    private LocalDate saleDate;
    private double subtotal;
    private double tax;
    private double discount;
    private double total;
    private String paymentMethod;
    private String paymentStatus;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private List<SaleItem> items;

    /**
     * Default constructor
     */
    public Sale() {
        this.saleDate = LocalDate.now();
        this.subtotal = 0.0;
        this.tax = 0.0;
        this.discount = 0.0;
        this.total = 0.0;
        this.paymentStatus = "Pending";
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
        this.items = new ArrayList<>();
    }

    /**
     * Parameterized constructor
     * 
     * @param saleId Sale ID
     * @param patientId Patient ID (optional)
     * @param cashierId Cashier ID
     * @param saleDate Sale date
     * @param paymentMethod Payment method
     */
    public Sale(int saleId, Integer patientId, int cashierId, LocalDate saleDate, String paymentMethod) {
        this.saleId = saleId;
        this.patientId = patientId;
        this.cashierId = cashierId;
        this.saleDate = saleDate;
        this.subtotal = 0.0;
        this.tax = 0.0;
        this.discount = 0.0;
        this.total = 0.0;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = "Pending";
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
        this.items = new ArrayList<>();
    }

    /**
     * Add an item to the sale
     * 
     * @param item Sale item to add
     */
    public void addItem(SaleItem item) {
        items.add(item);
        recalculateTotal();
        this.updatedAt = LocalDate.now();
    }

    /**
     * Remove an item from the sale
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
     * Apply a discount to the sale
     * 
     * @param discountAmount Discount amount
     * @param discountType Type of discount ("Amount" or "Percentage")
     */
    public void applyDiscount(double discountAmount, String discountType) {
        if ("Amount".equals(discountType)) {
            this.discount = Math.min(discountAmount, this.subtotal);
        } else if ("Percentage".equals(discountType)) {
            this.discount = this.subtotal * (discountAmount / 100.0);
        }
        recalculateTotal();
        this.updatedAt = LocalDate.now();
    }

    /**
     * Recalculate the total based on items, tax, and discount
     */
    private void recalculateTotal() {
        this.subtotal = items.stream()
                .mapToDouble(SaleItem::getSubtotal)
                .sum();
        
        // Assuming a fixed tax rate of 10% for demonstration
        this.tax = this.subtotal * 0.1;
        
        this.total = this.subtotal + this.tax - this.discount;
    }

    /**
     * Process payment for the sale
     * 
     * @param paymentMethod Payment method
     * @param amount Amount paid
     * @return true if payment was successful
     */
    public boolean processPayment(String paymentMethod, double amount) {
        if (amount >= this.total) {
            this.paymentMethod = paymentMethod;
            this.paymentStatus = "Completed";
            this.updatedAt = LocalDate.now();
            return true;
        }
        return false;
    }

    /**
     * Generate a receipt for the sale
     * 
     * @return Receipt text
     */
    public String generateReceipt() {
        StringBuilder receipt = new StringBuilder();
        receipt.append("=== PHARMACY RECEIPT ===\n");
        receipt.append("Sale ID: ").append(saleId).append("\n");
        receipt.append("Date: ").append(saleDate).append("\n");
        receipt.append("Cashier ID: ").append(cashierId).append("\n");
        if (patientId != null) {
            receipt.append("Patient ID: ").append(patientId).append("\n");
        }
        receipt.append("\nItems:\n");
        
        for (SaleItem item : items) {
            receipt.append(item.getQuantity())
                  .append(" x ")
                  .append(item.getProductName())
                  .append(" @ $")
                  .append(String.format("%.2f", item.getUnitPrice()))
                  .append(" = $")
                  .append(String.format("%.2f", item.getSubtotal()))
                  .append("\n");
        }
        
        receipt.append("\nSubtotal: $").append(String.format("%.2f", subtotal)).append("\n");
        receipt.append("Tax: $").append(String.format("%.2f", tax)).append("\n");
        if (discount > 0) {
            receipt.append("Discount: $").append(String.format("%.2f", discount)).append("\n");
        }
        receipt.append("Total: $").append(String.format("%.2f", total)).append("\n");
        receipt.append("Payment Method: ").append(paymentMethod).append("\n");
        receipt.append("Payment Status: ").append(paymentStatus).append("\n");
        receipt.append("======================\n");
        
        return receipt.toString();
    }

    // Getters and Setters
    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public int getCashierId() {
        return cashierId;
    }

    public void setCashierId(int cashierId) {
        this.cashierId = cashierId;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
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

    public List<SaleItem> getItems() {
        return new ArrayList<>(items);
    }

    public void setItems(List<SaleItem> items) {
        this.items = new ArrayList<>(items);
    }

    @Override
    public String toString() {
        return "Sale{" +
                "saleId=" + saleId +
                ", patientId=" + patientId +
                ", cashierId=" + cashierId +
                ", saleDate=" + saleDate +
                ", total=" + total +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", items=" + items.size() +
                '}';
    }
}
