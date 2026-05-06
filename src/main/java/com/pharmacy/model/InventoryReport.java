package com.pharmacy.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents an inventory report in the pharmacy system.
 * Extends the base Report class with inventory-specific functionality.
 */
public class InventoryReport extends Report {
    private int totalProducts;
    private int lowStockProducts;
    private int expiringSoonProducts;
    private double totalInventoryValue;
    private Map<Integer, Integer> productStock;
    private Map<String, Integer> categoryStock;

    /**
     * Default constructor
     */
    public InventoryReport() {
        super();
        this.setType("Inventory");
        this.totalProducts = 0;
        this.lowStockProducts = 0;
        this.expiringSoonProducts = 0;
        this.totalInventoryValue = 0.0;
        this.productStock = new HashMap<>();
        this.categoryStock = new HashMap<>();
    }

    /**
     * Parameterized constructor
     * 
     * @param reportId Report ID
     * @param startDate Start date for report period
     * @param endDate End date for report period
     * @param generatedBy User ID of generator
     */
    public InventoryReport(int reportId, LocalDate startDate, LocalDate endDate, int generatedBy) {
        super(reportId, "Inventory", startDate, endDate, generatedBy);
        this.totalProducts = 0;
        this.lowStockProducts = 0;
        this.expiringSoonProducts = 0;
        this.totalInventoryValue = 0.0;
        this.productStock = new HashMap<>();
        this.categoryStock = new HashMap<>();
    }

    @Override
    public boolean generate() {
        // In a real application, this would query the database for inventory data
        // For demonstration, we'll simulate some data
        
        // Simulate inventory statistics
        this.totalProducts = 250;
        this.lowStockProducts = 15;
        this.expiringSoonProducts = 8;
        this.totalInventoryValue = 125000.75;
        
        // Simulate product stock data
        this.productStock.put(1, 120);
        this.productStock.put(2, 85);
        this.productStock.put(3, 5);  // Low stock
        this.productStock.put(4, 200);
        this.productStock.put(5, 10); // Low stock
        
        // Simulate category stock data
        this.categoryStock.put("Antibiotics", 150);
        this.categoryStock.put("Pain Relief", 200);
        this.categoryStock.put("Vitamins", 180);
        this.categoryStock.put("First Aid", 90);
        this.categoryStock.put("Skin Care", 120);
        
        return true;
    }

    /**
     * Identify products with low stock levels
     * 
     * @return Map of product IDs to stock levels for low stock products
     */
    public Map<Integer, Integer> identifyLowStock() {
        Map<Integer, Integer> lowStock = new HashMap<>();
        
        // In a real application, this would filter products below reorder level
        // For demonstration, we'll add the products we know are low stock
        lowStock.put(3, 5);
        lowStock.put(5, 10);
        
        return lowStock;
    }

    /**
     * Analyze stock movement over time
     * 
     * @return Map of product IDs to stock change (positive for increase, negative for decrease)
     */
    public Map<Integer, Integer> analyzeStockMovement() {
        Map<Integer, Integer> stockMovement = new HashMap<>();
        
        // In a real application, this would calculate stock changes over the report period
        // For demonstration, we'll simulate some data
        stockMovement.put(1, -30);  // Decreased by 30
        stockMovement.put(2, 15);   // Increased by 15
        stockMovement.put(3, -10);  // Decreased by 10
        stockMovement.put(4, 50);   // Increased by 50
        stockMovement.put(5, -5);   // Decreased by 5
        
        return stockMovement;
    }

    /**
     * Calculate the total value of current inventory
     * 
     * @return Total inventory value
     */
    public double calculateStockValue() {
        return this.totalInventoryValue;
    }

    // Getters and Setters
    public int getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(int totalProducts) {
        this.totalProducts = totalProducts;
    }

    public int getLowStockProducts() {
        return lowStockProducts;
    }

    public void setLowStockProducts(int lowStockProducts) {
        this.lowStockProducts = lowStockProducts;
    }

    public int getExpiringSoonProducts() {
        return expiringSoonProducts;
    }

    public void setExpiringSoonProducts(int expiringSoonProducts) {
        this.expiringSoonProducts = expiringSoonProducts;
    }

    public double getTotalInventoryValue() {
        return totalInventoryValue;
    }

    public void setTotalInventoryValue(double totalInventoryValue) {
        this.totalInventoryValue = totalInventoryValue;
    }

    public Map<Integer, Integer> getProductStock() {
        return new HashMap<>(productStock);
    }

    public void setProductStock(Map<Integer, Integer> productStock) {
        this.productStock = new HashMap<>(productStock);
    }

    public Map<String, Integer> getCategoryStock() {
        return new HashMap<>(categoryStock);
    }

    public void setCategoryStock(Map<String, Integer> categoryStock) {
        this.categoryStock = new HashMap<>(categoryStock);
    }

    @Override
    public String toString() {
        return "InventoryReport{" +
                "reportId=" + getReportId() +
                ", totalProducts=" + totalProducts +
                ", lowStockProducts=" + lowStockProducts +
                ", expiringSoonProducts=" + expiringSoonProducts +
                ", totalInventoryValue=" + totalInventoryValue +
                '}';
    }
}
