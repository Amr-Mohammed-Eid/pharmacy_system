package com.pharmacy.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a sales report in the pharmacy system.
 * Extends the base Report class with sales-specific functionality.
 */
public class SalesReport extends Report {
    private double totalRevenue;
    private Map<Integer, Double> productSales;
    private Map<String, Double> categorySales;
    private Map<LocalDate, Double> dailySales;

    /**
     * Default constructor
     */
    public SalesReport() {
        super();
        this.setType("Sales");
        this.totalRevenue = 0.0;
        this.productSales = new HashMap<>();
        this.categorySales = new HashMap<>();
        this.dailySales = new HashMap<>();
    }

    /**
     * Parameterized constructor
     * 
     * @param reportId Report ID
     * @param startDate Start date for report period
     * @param endDate End date for report period
     * @param generatedBy User ID of generator
     */
    public SalesReport(int reportId, LocalDate startDate, LocalDate endDate, int generatedBy) {
        super(reportId, "Sales", startDate, endDate, generatedBy);
        this.totalRevenue = 0.0;
        this.productSales = new HashMap<>();
        this.categorySales = new HashMap<>();
        this.dailySales = new HashMap<>();
    }

    @Override
    public boolean generate() {
        // In a real application, this would query the database for sales data
        // For demonstration, we'll simulate some data
        
        // Simulate calculating total revenue
        this.totalRevenue = 15750.25;
        
        // Simulate product sales data
        this.productSales.put(1, 2500.50);
        this.productSales.put(2, 1800.75);
        this.productSales.put(3, 3200.00);
        this.productSales.put(4, 1250.50);
        this.productSales.put(5, 7000.50);
        
        // Simulate category sales data
        this.categorySales.put("Antibiotics", 5500.25);
        this.categorySales.put("Pain Relief", 3200.50);
        this.categorySales.put("Vitamins", 2800.75);
        this.categorySales.put("First Aid", 1500.25);
        this.categorySales.put("Skin Care", 2750.50);
        
        // Simulate daily sales data
        LocalDate currentDate = getStartDate();
        while (!currentDate.isAfter(getEndDate())) {
            // Generate random daily sales between 500 and 2000
            double dailySale = 500 + Math.random() * 1500;
            this.dailySales.put(currentDate, dailySale);
            currentDate = currentDate.plusDays(1);
        }
        
        return true;
    }

    /**
     * Calculate total revenue for the report period
     * 
     * @return Total revenue
     */
    public double calculateRevenue() {
        return this.totalRevenue;
    }

    /**
     * Analyze top selling products
     * 
     * @param limit Number of top products to return
     * @return Map of product IDs to sales amounts, sorted by sales amount
     */
    public Map<Integer, Double> analyzeTopProducts(int limit) {
        // In a real application, this would sort the product sales and return the top ones
        // For demonstration, we'll return the existing map
        return this.productSales;
    }

    /**
     * Analyze sales trends over time
     * 
     * @return Map of dates to sales amounts
     */
    public Map<LocalDate, Double> analyzeSalesTrends() {
        return this.dailySales;
    }

    // Getters and Setters
    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public Map<Integer, Double> getProductSales() {
        return new HashMap<>(productSales);
    }

    public void setProductSales(Map<Integer, Double> productSales) {
        this.productSales = new HashMap<>(productSales);
    }

    public Map<String, Double> getCategorySales() {
        return new HashMap<>(categorySales);
    }

    public void setCategorySales(Map<String, Double> categorySales) {
        this.categorySales = new HashMap<>(categorySales);
    }

    public Map<LocalDate, Double> getDailySales() {
        return new HashMap<>(dailySales);
    }

    public void setDailySales(Map<LocalDate, Double> dailySales) {
        this.dailySales = new HashMap<>(dailySales);
    }

    @Override
    public String toString() {
        return "SalesReport{" +
                "reportId=" + getReportId() +
                ", startDate=" + getStartDate() +
                ", endDate=" + getEndDate() +
                ", totalRevenue=" + totalRevenue +
                ", products=" + productSales.size() +
                '}';
    }
}
