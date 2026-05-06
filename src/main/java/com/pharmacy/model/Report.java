package com.pharmacy.model;

import java.time.LocalDate;

/**
 * Represents a report in the pharmacy system.
 * Base class for all report types.
 */
public abstract class Report {
    private int reportId;
    private String type;
    private LocalDate startDate;
    private LocalDate endDate;
    private int generatedBy;
    private LocalDate generatedDate;
    private String filePath;

    /**
     * Default constructor
     */
    public Report() {
        this.generatedDate = LocalDate.now();
    }

    /**
     * Parameterized constructor
     * 
     * @param reportId Report ID
     * @param type Report type
     * @param startDate Start date for report period
     * @param endDate End date for report period
     * @param generatedBy User ID of generator
     */
    public Report(int reportId, String type, LocalDate startDate, LocalDate endDate, int generatedBy) {
        this.reportId = reportId;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.generatedBy = generatedBy;
        this.generatedDate = LocalDate.now();
    }

    /**
     * Generate the report
     * This is an abstract method to be implemented by subclasses
     * 
     * @return true if report generation was successful
     */
    public abstract boolean generate();

    /**
     * Export the report to a file
     * 
     * @param format Export format (e.g., "PDF", "Excel")
     * @param path File path for export
     * @return true if export was successful
     */
    public boolean export(String format, String path) {
        // Simplified export for demonstration
        this.filePath = path;
        return true;
    }

    /**
     * Print the report
     * 
     * @return true if printing was successful
     */
    public boolean print() {
        // Simplified printing for demonstration
        return true;
    }

    // Getters and Setters
    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getGeneratedBy() {
        return generatedBy;
    }

    public void setGeneratedBy(int generatedBy) {
        this.generatedBy = generatedBy;
    }

    public LocalDate getGeneratedDate() {
        return generatedDate;
    }

    public void setGeneratedDate(LocalDate generatedDate) {
        this.generatedDate = generatedDate;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "Report{" +
                "reportId=" + reportId +
                ", type='" + type + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", generatedDate=" + generatedDate +
                '}';
    }
}
