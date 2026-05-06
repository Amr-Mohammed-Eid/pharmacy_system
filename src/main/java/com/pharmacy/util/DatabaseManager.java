package com.pharmacy.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Database manager utility class for the pharmacy system.
 * Handles database connections and operations.
 */
public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:pharmacy.db";
    private static Connection connection;

    /**
     * Private constructor to prevent instantiation
     */
    private DatabaseManager() {
    }

    /**
     * Get a database connection
     * 
     * @return Database connection
     * @throws SQLException If connection fails
     */
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                // Load SQLite JDBC driver
                Class.forName("org.sqlite.JDBC");
                
                // Create connection
                connection = DriverManager.getConnection(DB_URL);
                
                // Set connection properties
                connection.setAutoCommit(true);
            } catch (ClassNotFoundException e) {
                throw new SQLException("SQLite JDBC driver not found", e);
            }
        }
        return connection;
    }

    /**
     * Close the database connection
     * 
     * @throws SQLException If closing fails
     */
    public static void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    /**
     * Initialize the database with required tables
     * 
     * @throws SQLException If initialization fails
     */
    public static void initializeDatabase() throws SQLException {
        Connection conn = getConnection();
        
        // Create users table
        conn.createStatement().execute(
            "CREATE TABLE IF NOT EXISTS users (" +
            "user_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "username TEXT NOT NULL UNIQUE, " +
            "password_hash TEXT NOT NULL, " +
            "full_name TEXT NOT NULL, " +
            "role TEXT NOT NULL, " +
            "email TEXT, " +
            "phone TEXT, " +
            "address TEXT, " +
            "last_login TEXT, " +
            "created_at TEXT NOT NULL, " +
            "updated_at TEXT NOT NULL, " +
            "active INTEGER NOT NULL DEFAULT 1" +
            ")"
        );
        
        // Create products table
        conn.createStatement().execute(
            "CREATE TABLE IF NOT EXISTS products (" +
            "product_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "name TEXT NOT NULL, " +
            "description TEXT, " +
            "category TEXT, " +
            "manufacturer TEXT, " +
            "unit_price REAL NOT NULL, " +
            "stock_level INTEGER NOT NULL DEFAULT 0, " +
            "reorder_level INTEGER NOT NULL DEFAULT 10, " +
            "barcode TEXT, " +
            "prescription_required INTEGER NOT NULL DEFAULT 0, " +
            "created_at TEXT NOT NULL, " +
            "updated_at TEXT NOT NULL" +
            ")"
        );
        
        // Create medications table
        conn.createStatement().execute(
            "CREATE TABLE IF NOT EXISTS medications (" +
            "medication_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "product_id INTEGER NOT NULL, " +
            "dosage TEXT, " +
            "active_ingredients TEXT, " +
            "side_effects TEXT, " +
            "storage_instructions TEXT, " +
            "expiry_date TEXT, " +
            "FOREIGN KEY (product_id) REFERENCES products (product_id)" +
            ")"
        );
        
        // Create suppliers table
        conn.createStatement().execute(
            "CREATE TABLE IF NOT EXISTS suppliers (" +
            "supplier_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "name TEXT NOT NULL, " +
            "contact_person TEXT, " +
            "phone TEXT, " +
            "email TEXT, " +
            "address TEXT, " +
            "created_at TEXT NOT NULL, " +
            "updated_at TEXT NOT NULL, " +
            "active INTEGER NOT NULL DEFAULT 1" +
            ")"
        );
        
        // Create purchase_orders table
        conn.createStatement().execute(
            "CREATE TABLE IF NOT EXISTS purchase_orders (" +
            "order_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "supplier_id INTEGER NOT NULL, " +
            "order_date TEXT NOT NULL, " +
            "expected_delivery TEXT, " +
            "status TEXT NOT NULL, " +
            "total_amount REAL NOT NULL DEFAULT 0, " +
            "created_by INTEGER NOT NULL, " +
            "created_at TEXT NOT NULL, " +
            "updated_at TEXT NOT NULL, " +
            "FOREIGN KEY (supplier_id) REFERENCES suppliers (supplier_id), " +
            "FOREIGN KEY (created_by) REFERENCES users (user_id)" +
            ")"
        );
        
        // Create purchase_order_items table
        conn.createStatement().execute(
            "CREATE TABLE IF NOT EXISTS purchase_order_items (" +
            "item_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "order_id INTEGER NOT NULL, " +
            "product_id INTEGER NOT NULL, " +
            "quantity INTEGER NOT NULL, " +
            "unit_price REAL NOT NULL, " +
            "subtotal REAL NOT NULL, " +
            "FOREIGN KEY (order_id) REFERENCES purchase_orders (order_id), " +
            "FOREIGN KEY (product_id) REFERENCES products (product_id)" +
            ")"
        );
        
        // Create patients table
        conn.createStatement().execute(
            "CREATE TABLE IF NOT EXISTS patients (" +
            "patient_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "name TEXT NOT NULL, " +
            "date_of_birth TEXT, " +
            "gender TEXT, " +
            "address TEXT, " +
            "phone TEXT, " +
            "email TEXT, " +
            "insurance_provider TEXT, " +
            "insurance_number TEXT, " +
            "created_at TEXT NOT NULL, " +
            "updated_at TEXT NOT NULL" +
            ")"
        );
        
        // Create doctors table
        conn.createStatement().execute(
            "CREATE TABLE IF NOT EXISTS doctors (" +
            "doctor_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "name TEXT NOT NULL, " +
            "specialization TEXT, " +
            "license_number TEXT NOT NULL, " +
            "phone TEXT, " +
            "email TEXT, " +
            "address TEXT, " +
            "created_at TEXT NOT NULL, " +
            "updated_at TEXT NOT NULL" +
            ")"
        );
        
        // Create prescriptions table
        conn.createStatement().execute(
            "CREATE TABLE IF NOT EXISTS prescriptions (" +
            "prescription_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "patient_id INTEGER NOT NULL, " +
            "doctor_id INTEGER NOT NULL, " +
            "issue_date TEXT NOT NULL, " +
            "expiry_date TEXT NOT NULL, " +
            "status TEXT NOT NULL, " +
            "notes TEXT, " +
            "created_by INTEGER NOT NULL, " +
            "created_at TEXT NOT NULL, " +
            "updated_at TEXT NOT NULL, " +
            "FOREIGN KEY (patient_id) REFERENCES patients (patient_id), " +
            "FOREIGN KEY (doctor_id) REFERENCES doctors (doctor_id), " +
            "FOREIGN KEY (created_by) REFERENCES users (user_id)" +
            ")"
        );
        
        // Create prescription_items table
        conn.createStatement().execute(
            "CREATE TABLE IF NOT EXISTS prescription_items (" +
            "item_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "prescription_id INTEGER NOT NULL, " +
            "medication_id INTEGER NOT NULL, " +
            "dosage TEXT, " +
            "frequency TEXT, " +
            "duration TEXT, " +
            "instructions TEXT, " +
            "quantity INTEGER NOT NULL, " +
            "refills_allowed INTEGER NOT NULL DEFAULT 0, " +
            "refills_used INTEGER NOT NULL DEFAULT 0, " +
            "FOREIGN KEY (prescription_id) REFERENCES prescriptions (prescription_id), " +
            "FOREIGN KEY (medication_id) REFERENCES medications (medication_id)" +
            ")"
        );
        
        // Create sales table
        conn.createStatement().execute(
            "CREATE TABLE IF NOT EXISTS sales (" +
            "sale_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "patient_id INTEGER, " +
            "cashier_id INTEGER NOT NULL, " +
            "sale_date TEXT NOT NULL, " +
            "subtotal REAL NOT NULL, " +
            "tax REAL NOT NULL, " +
            "discount REAL NOT NULL DEFAULT 0, " +
            "total REAL NOT NULL, " +
            "payment_method TEXT, " +
            "payment_status TEXT NOT NULL, " +
            "created_at TEXT NOT NULL, " +
            "updated_at TEXT NOT NULL, " +
            "FOREIGN KEY (patient_id) REFERENCES patients (patient_id), " +
            "FOREIGN KEY (cashier_id) REFERENCES users (user_id)" +
            ")"
        );
        
        // Create sale_items table
        conn.createStatement().execute(
            "CREATE TABLE IF NOT EXISTS sale_items (" +
            "item_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "sale_id INTEGER NOT NULL, " +
            "product_id INTEGER NOT NULL, " +
            "quantity INTEGER NOT NULL, " +
            "unit_price REAL NOT NULL, " +
            "subtotal REAL NOT NULL, " +
            "prescription_id INTEGER, " +
            "FOREIGN KEY (sale_id) REFERENCES sales (sale_id), " +
            "FOREIGN KEY (product_id) REFERENCES products (product_id), " +
            "FOREIGN KEY (prescription_id) REFERENCES prescriptions (prescription_id)" +
            ")"
        );
        
        // Create reports table
        conn.createStatement().execute(
            "CREATE TABLE IF NOT EXISTS reports (" +
            "report_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "type TEXT NOT NULL, " +
            "start_date TEXT NOT NULL, " +
            "end_date TEXT NOT NULL, " +
            "generated_by INTEGER NOT NULL, " +
            "generated_date TEXT NOT NULL, " +
            "file_path TEXT, " +
            "FOREIGN KEY (generated_by) REFERENCES users (user_id)" +
            ")"
        );
        
        // Insert default admin user
        conn.createStatement().execute(
            "INSERT OR IGNORE INTO users (username, password_hash, full_name, role, email, created_at, updated_at) " +
            "VALUES ('admin', 'admin123', 'System Administrator', 'Admin', 'admin@pharmacy.com', datetime('now'), datetime('now'))"
        );
    }
}
