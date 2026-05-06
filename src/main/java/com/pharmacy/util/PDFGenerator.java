package com.pharmacy.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Utility class for generating PDF documents.
 */
public class PDFGenerator {
    
    private static final Font TITLE_FONT = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
    private static final Font SUBTITLE_FONT = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
    private static final Font NORMAL_FONT = FontFactory.getFont(FontFactory.HELVETICA, 12);
    private static final Font SMALL_FONT = FontFactory.getFont(FontFactory.HELVETICA, 10);
    
    /**
     * Generate a receipt PDF
     * 
     * @param saleId Sale ID
     * @param cashierName Cashier name
     * @param patientName Patient name (optional)
     * @param saleDate Sale date
     * @param items Array of item names
     * @param quantities Array of quantities
     * @param prices Array of prices
     * @param subtotal Subtotal amount
     * @param tax Tax amount
     * @param discount Discount amount
     * @param total Total amount
     * @param paymentMethod Payment method
     * @param outputPath Output file path
     * @return true if PDF generation was successful
     */
    public static boolean generateReceipt(int saleId, String cashierName, String patientName,
                                         LocalDateTime saleDate, String[] items, int[] quantities,
                                         double[] prices, double subtotal, double tax,
                                         double discount, double total, String paymentMethod,
                                         String outputPath) {
        Document document = new Document();
        
        try {
            PdfWriter.getInstance(document, new FileOutputStream(outputPath));
            document.open();
            
            // Add pharmacy header
            Paragraph header = new Paragraph("PHARMACY MANAGEMENT SYSTEM", TITLE_FONT);
            header.setAlignment(Element.ALIGN_CENTER);
            document.add(header);
            
            Paragraph subheader = new Paragraph("RECEIPT", SUBTITLE_FONT);
            subheader.setAlignment(Element.ALIGN_CENTER);
            document.add(subheader);
            
            document.add(new Paragraph(" ")); // Empty line
            
            // Add receipt details
            document.add(new Paragraph("Receipt No: " + saleId, NORMAL_FONT));
            document.add(new Paragraph("Date: " + saleDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), NORMAL_FONT));
            document.add(new Paragraph("Cashier: " + cashierName, NORMAL_FONT));
            if (patientName != null && !patientName.isEmpty()) {
                document.add(new Paragraph("Patient: " + patientName, NORMAL_FONT));
            }
            
            document.add(new Paragraph(" ")); // Empty line
            
            // Add items table
            PdfPTable table = new PdfPTable(4); // 4 columns
            table.setWidthPercentage(100);
            
            // Add table headers
            PdfPCell cell = new PdfPCell(new Phrase("Item", SUBTITLE_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Qty", SUBTITLE_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Price", SUBTITLE_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Amount", SUBTITLE_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            
            // Add table rows
            for (int i = 0; i < items.length; i++) {
                table.addCell(new Phrase(items[i], NORMAL_FONT));
                
                cell = new PdfPCell(new Phrase(String.valueOf(quantities[i]), NORMAL_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase(String.format("$%.2f", prices[i]), NORMAL_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase(String.format("$%.2f", quantities[i] * prices[i]), NORMAL_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);
            }
            
            document.add(table);
            
            document.add(new Paragraph(" ")); // Empty line
            
            // Add totals
            Paragraph totals = new Paragraph();
            totals.add(new Phrase("Subtotal: ", NORMAL_FONT));
            totals.add(new Phrase(String.format("$%.2f", subtotal), NORMAL_FONT));
            totals.setAlignment(Element.ALIGN_RIGHT);
            document.add(totals);
            
            totals = new Paragraph();
            totals.add(new Phrase("Tax: ", NORMAL_FONT));
            totals.add(new Phrase(String.format("$%.2f", tax), NORMAL_FONT));
            totals.setAlignment(Element.ALIGN_RIGHT);
            document.add(totals);
            
            if (discount > 0) {
                totals = new Paragraph();
                totals.add(new Phrase("Discount: ", NORMAL_FONT));
                totals.add(new Phrase(String.format("$%.2f", discount), NORMAL_FONT));
                totals.setAlignment(Element.ALIGN_RIGHT);
                document.add(totals);
            }
            
            totals = new Paragraph();
            totals.add(new Phrase("Total: ", SUBTITLE_FONT));
            totals.add(new Phrase(String.format("$%.2f", total), SUBTITLE_FONT));
            totals.setAlignment(Element.ALIGN_RIGHT);
            document.add(totals);
            
            document.add(new Paragraph(" ")); // Empty line
            
            // Add payment method
            Paragraph payment = new Paragraph("Payment Method: " + paymentMethod, NORMAL_FONT);
            document.add(payment);
            
            document.add(new Paragraph(" ")); // Empty line
            
            // Add footer
            Paragraph footer = new Paragraph("Thank you for your purchase!", NORMAL_FONT);
            footer.setAlignment(Element.ALIGN_CENTER);
            document.add(footer);
            
            document.close();
            return true;
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Generate a sales report PDF
     * 
     * @param reportId Report ID
     * @param startDate Start date
     * @param endDate End date
     * @param totalSales Total sales amount
     * @param totalItems Total items sold
     * @param topProducts Array of top product names
     * @param topProductSales Array of top product sales amounts
     * @param generatedBy User who generated the report
     * @param outputPath Output file path
     * @return true if PDF generation was successful
     */
    public static boolean generateSalesReport(int reportId, LocalDate startDate, LocalDate endDate,
                                             double totalSales, int totalItems, String[] topProducts,
                                             double[] topProductSales, String generatedBy,
                                             String outputPath) {
        Document document = new Document();
        
        try {
            PdfWriter.getInstance(document, new FileOutputStream(outputPath));
            document.open();
            
            // Add pharmacy header
            Paragraph header = new Paragraph("PHARMACY MANAGEMENT SYSTEM", TITLE_FONT);
            header.setAlignment(Element.ALIGN_CENTER);
            document.add(header);
            
            Paragraph subheader = new Paragraph("SALES REPORT", SUBTITLE_FONT);
            subheader.setAlignment(Element.ALIGN_CENTER);
            document.add(subheader);
            
            document.add(new Paragraph(" ")); // Empty line
            
            // Add report details
            document.add(new Paragraph("Report ID: " + reportId, NORMAL_FONT));
            document.add(new Paragraph("Period: " + startDate + " to " + endDate, NORMAL_FONT));
            document.add(new Paragraph("Generated By: " + generatedBy, NORMAL_FONT));
            document.add(new Paragraph("Generated On: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), NORMAL_FONT));
            
            document.add(new Paragraph(" ")); // Empty line
            
            // Add summary
            Paragraph summaryHeader = new Paragraph("SUMMARY", SUBTITLE_FONT);
            document.add(summaryHeader);
            
            document.add(new Paragraph("Total Sales: $" + String.format("%.2f", totalSales), NORMAL_FONT));
            document.add(new Paragraph("Total Items Sold: " + totalItems, NORMAL_FONT));
            
            document.add(new Paragraph(" ")); // Empty line
            
            // Add top products
            Paragraph topProductsHeader = new Paragraph("TOP SELLING PRODUCTS", SUBTITLE_FONT);
            document.add(topProductsHeader);
            
            PdfPTable table = new PdfPTable(3); // 3 columns
            table.setWidthPercentage(100);
            
            // Add table headers
            PdfPCell cell = new PdfPCell(new Phrase("Rank", SUBTITLE_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Product", SUBTITLE_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Sales", SUBTITLE_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            
            // Add table rows
            for (int i = 0; i < topProducts.length; i++) {
                cell = new PdfPCell(new Phrase(String.valueOf(i + 1), NORMAL_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                
                table.addCell(new Phrase(topProducts[i], NORMAL_FONT));
                
                cell = new PdfPCell(new Phrase(String.format("$%.2f", topProductSales[i]), NORMAL_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);
            }
            
            document.add(table);
            
            document.add(new Paragraph(" ")); // Empty line
            
            // Add footer
            Paragraph footer = new Paragraph("End of Report", SMALL_FONT);
            footer.setAlignment(Element.ALIGN_CENTER);
            document.add(footer);
            
            document.close();
            return true;
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Generate an inventory report PDF
     * 
     * @param reportId Report ID
     * @param totalProducts Total number of products
     * @param lowStockProducts Number of low stock products
     * @param expiringSoonProducts Number of expiring soon products
     * @param totalValue Total inventory value
     * @param productNames Array of product names
     * @param stockLevels Array of stock levels
     * @param reorderLevels Array of reorder levels
     * @param generatedBy User who generated the report
     * @param outputPath Output file path
     * @return true if PDF generation was successful
     */
    public static boolean generateInventoryReport(int reportId, int totalProducts,
                                                int lowStockProducts, int expiringSoonProducts,
                                                double totalValue, String[] productNames,
                                                int[] stockLevels, int[] reorderLevels,
                                                String generatedBy, String outputPath) {
        Document document = new Document();
        
        try {
            PdfWriter.getInstance(document, new FileOutputStream(outputPath));
            document.open();
            
            // Add pharmacy header
            Paragraph header = new Paragraph("PHARMACY MANAGEMENT SYSTEM", TITLE_FONT);
            header.setAlignment(Element.ALIGN_CENTER);
            document.add(header);
            
            Paragraph subheader = new Paragraph("INVENTORY REPORT", SUBTITLE_FONT);
            subheader.setAlignment(Element.ALIGN_CENTER);
            document.add(subheader);
            
            document.add(new Paragraph(" ")); // Empty line
            
            // Add report details
            document.add(new Paragraph("Report ID: " + reportId, NORMAL_FONT));
            document.add(new Paragraph("Generated By: " + generatedBy, NORMAL_FONT));
            document.add(new Paragraph("Generated On: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), NORMAL_FONT));
            
            document.add(new Paragraph(" ")); // Empty line
            
            // Add summary
            Paragraph summaryHeader = new Paragraph("SUMMARY", SUBTITLE_FONT);
            document.add(summaryHeader);
            
            document.add(new Paragraph("Total Products: " + totalProducts, NORMAL_FONT));
            document.add(new Paragraph("Low Stock Products: " + lowStockProducts, NORMAL_FONT));
            document.add(new Paragraph("Expiring Soon Products: " + expiringSoonProducts, NORMAL_FONT));
            document.add(new Paragraph("Total Inventory Value: $" + String.format("%.2f", totalValue), NORMAL_FONT));
            
            document.add(new Paragraph(" ")); // Empty line
            
            // Add inventory table
            Paragraph inventoryHeader = new Paragraph("INVENTORY DETAILS", SUBTITLE_FONT);
            document.add(inventoryHeader);
            
            PdfPTable table = new PdfPTable(4); // 4 columns
            table.setWidthPercentage(100);
            
            // Add table headers
            PdfPCell cell = new PdfPCell(new Phrase("Product", SUBTITLE_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Stock Level", SUBTITLE_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Reorder Level", SUBTITLE_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Status", SUBTITLE_FONT));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            
            // Add table rows
            for (int i = 0; i < productNames.length; i++) {
                table.addCell(new Phrase(productNames[i], NORMAL_FONT));
                
                cell = new PdfPCell(new Phrase(String.valueOf(stockLevels[i]), NORMAL_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase(String.valueOf(reorderLevels[i]), NORMAL_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                
                String status = stockLevels[i] <= reorderLevels[i] ? "LOW STOCK" : "OK";
                cell = new PdfPCell(new Phrase(status, NORMAL_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }
            
            document.add(table);
            
            document.add(new Paragraph(" ")); // Empty line
            
            // Add footer
            Paragraph footer = new Paragraph("End of Report", SMALL_FONT);
            footer.setAlignment(Element.ALIGN_CENTER);
            document.add(footer);
            
            document.close();
            return true;
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Check if output directory exists, create if not
     * 
     * @param outputPath Output file path
     */
    public static void ensureOutputDirectoryExists(String outputPath) {
        File file = new File(outputPath);
        File directory = file.getParentFile();
        
        if (directory != null && !directory.exists()) {
            directory.mkdirs();
        }
    }
}
