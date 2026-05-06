# Pharmacy System Management - System Design

## Architecture Overview

The Pharmacy System Management application will follow the Model-View-Controller (MVC) architecture pattern to ensure separation of concerns and maintainability.

### Architectural Layers

1. **Presentation Layer (View)**
   - JavaFX UI components
   - FXML layouts
   - CSS styling

2. **Application Layer (Controller)**
   - Event handlers
   - Business logic
   - Data validation

3. **Data Layer (Model)**
   - Entity classes
   - Data access objects (DAOs)
   - Database connection management

## Class Structure

### Model Classes

#### User Management
- `User`: Base class for system users
  - Attributes: userId, username, password, fullName, role, contactInfo
  - Methods: authenticate(), changePassword(), updateProfile()
- `Admin`: Extends User
  - Additional methods: createUser(), deleteUser(), assignRole()
- `Pharmacist`: Extends User
  - Additional methods: verifyPrescription(), checkDrugInteractions()
- `Cashier`: Extends User
  - Additional methods: processSale(), handleRefund()

#### Inventory Management
- `Product`: Base class for all pharmacy products
  - Attributes: productId, name, description, manufacturer, category, unitPrice, stockLevel, reorderLevel
  - Methods: updateStock(), checkExpiry(), generateBarcode()
- `Medication`: Extends Product
  - Additional attributes: dosage, activeIngredients, sideEffects, prescriptionRequired
  - Additional methods: checkInteractions(), verifyDosage()
- `Supplier`: Manages supplier information
  - Attributes: supplierId, name, contactPerson, phone, email, address
  - Methods: placeOrder(), updateInfo(), getOrderHistory()
- `PurchaseOrder`: Manages stock orders
  - Attributes: orderId, supplierId, orderDate, expectedDelivery, status, items
  - Methods: addItem(), removeItem(), updateStatus(), calculateTotal()

#### Prescription Management
- `Patient`: Stores patient information
  - Attributes: patientId, name, dateOfBirth, gender, address, phone, email, insuranceInfo
  - Methods: updateInfo(), getMedicalHistory(), getPrescriptionHistory()
- `Doctor`: Stores doctor information
  - Attributes: doctorId, name, specialization, licenseNumber, contactInfo
  - Methods: updateInfo(), getPrescriptionHistory()
- `Prescription`: Manages prescription details
  - Attributes: prescriptionId, patientId, doctorId, issueDate, expiryDate, status, items
  - Methods: addItem(), removeItem(), verify(), refill(), calculateTotal()
- `PrescriptionItem`: Individual medication in a prescription
  - Attributes: itemId, prescriptionId, medicationId, dosage, frequency, duration, instructions
  - Methods: updateQuantity(), calculatePrice()

#### Sales Management
- `Sale`: Manages sales transactions
  - Attributes: saleId, cashierId, patientId (optional), date, items, paymentMethod, total, tax, discount
  - Methods: addItem(), removeItem(), applyDiscount(), calculateTotal(), processPayment(), generateReceipt()
- `SaleItem`: Individual item in a sale
  - Attributes: itemId, saleId, productId, quantity, unitPrice, subtotal
  - Methods: updateQuantity(), calculateSubtotal()
- `Payment`: Handles payment processing
  - Attributes: paymentId, saleId, method, amount, status, transactionReference
  - Methods: process(), verify(), refund()
- `Receipt`: Generates sales receipts
  - Attributes: receiptId, saleId, date, items, total, paymentDetails
  - Methods: generate(), print(), emailToCustomer()

#### Reporting
- `Report`: Base class for all reports
  - Attributes: reportId, type, dateRange, generatedBy, generatedDate
  - Methods: generate(), export(), print()
- `SalesReport`: Extends Report
  - Additional methods: calculateRevenue(), analyzeTopProducts(), analyzeSalesTrends()
- `InventoryReport`: Extends Report
  - Additional methods: identifyLowStock(), analyzeStockMovement(), calculateStockValue()
- `PrescriptionReport`: Extends Report
  - Additional methods: analyzePrescriptionTrends(), identifyTopPrescribers()

### Controller Classes

- `LoginController`: Handles user authentication
- `DashboardController`: Manages the main dashboard view
- `InventoryController`: Manages inventory-related operations
- `PrescriptionController`: Handles prescription management
- `SalesController`: Processes sales transactions
- `UserManagementController`: Manages user accounts
- `ReportController`: Generates various reports
- `SettingsController`: Manages application settings

### Utility Classes

- `DatabaseManager`: Handles database connections and operations
- `ValidationUtils`: Provides input validation methods
- `SecurityUtils`: Handles encryption and security features
- `PDFGenerator`: Generates PDF documents for reports and receipts
- `NotificationManager`: Manages system notifications
- `LogManager`: Handles application logging
- `BackupManager`: Manages data backup and restoration

## Database Schema

### Tables

1. **users**
   - user_id (PK)
   - username
   - password_hash
   - full_name
   - role
   - email
   - phone
   - address
   - last_login
   - created_at
   - updated_at
   - active

2. **products**
   - product_id (PK)
   - name
   - description
   - category
   - manufacturer
   - unit_price
   - stock_level
   - reorder_level
   - barcode
   - prescription_required
   - created_at
   - updated_at

3. **medications** (extends products)
   - medication_id (PK)
   - product_id (FK)
   - dosage
   - active_ingredients
   - side_effects
   - storage_instructions
   - expiry_date

4. **suppliers**
   - supplier_id (PK)
   - name
   - contact_person
   - phone
   - email
   - address
   - created_at
   - updated_at
   - active

5. **purchase_orders**
   - order_id (PK)
   - supplier_id (FK)
   - order_date
   - expected_delivery
   - status
   - total_amount
   - created_by (FK to users)
   - created_at
   - updated_at

6. **purchase_order_items**
   - item_id (PK)
   - order_id (FK)
   - product_id (FK)
   - quantity
   - unit_price
   - subtotal

7. **patients**
   - patient_id (PK)
   - name
   - date_of_birth
   - gender
   - address
   - phone
   - email
   - insurance_provider
   - insurance_number
   - created_at
   - updated_at

8. **doctors**
   - doctor_id (PK)
   - name
   - specialization
   - license_number
   - phone
   - email
   - address
   - created_at
   - updated_at

9. **prescriptions**
   - prescription_id (PK)
   - patient_id (FK)
   - doctor_id (FK)
   - issue_date
   - expiry_date
   - status
   - notes
   - created_by (FK to users)
   - created_at
   - updated_at

10. **prescription_items**
    - item_id (PK)
    - prescription_id (FK)
    - medication_id (FK)
    - dosage
    - frequency
    - duration
    - instructions
    - quantity
    - refills_allowed
    - refills_used

11. **sales**
    - sale_id (PK)
    - patient_id (FK, optional)
    - cashier_id (FK to users)
    - sale_date
    - subtotal
    - tax
    - discount
    - total
    - payment_method
    - payment_status
    - created_at
    - updated_at

12. **sale_items**
    - item_id (PK)
    - sale_id (FK)
    - product_id (FK)
    - quantity
    - unit_price
    - subtotal
    - prescription_id (FK, optional)

13. **payments**
    - payment_id (PK)
    - sale_id (FK)
    - method
    - amount
    - status
    - transaction_reference
    - created_at
    - updated_at

14. **reports**
    - report_id (PK)
    - type
    - start_date
    - end_date
    - generated_by (FK to users)
    - generated_date
    - file_path

## UI Design

### Main Components

1. **Login Screen**
   - Username and password fields
   - Login button
   - Forgot password link

2. **Dashboard**
   - Summary widgets (sales, inventory, prescriptions)
   - Quick access buttons to main modules
   - Notifications area
   - Recent activities

3. **Inventory Management**
   - Product list with search and filter
   - Add/Edit product forms
   - Stock level indicators
   - Expiry date warnings
   - Purchase order management

4. **Prescription Management**
   - Patient search
   - Prescription form
   - Medication selection
   - Verification workflow
   - Prescription history

5. **Sales and Billing**
   - POS interface
   - Product search and barcode scanning
   - Cart management
   - Payment processing
   - Receipt generation

6. **User Management**
   - User list
   - Add/Edit user forms
   - Role assignment
   - Activity logs

7. **Reporting**
   - Report type selection
   - Date range selection
   - Report preview
   - Export options

8. **Settings**
   - Application preferences
   - Database backup/restore
   - Theme selection
   - System logs

## Data Flow

1. **Inventory Flow**
   - Supplier delivers products → Update inventory → Generate notification if stock level changes
   - Low stock detected → Create purchase order → Send to supplier → Receive products → Update inventory

2. **Prescription Flow**
   - Patient visits → Doctor writes prescription → Patient brings to pharmacy → Pharmacist verifies → Dispense medication → Update inventory → Generate bill

3. **Sales Flow**
   - Customer selects products → Cashier scans items → System checks inventory → Calculate total → Process payment → Generate receipt → Update inventory

4. **Reporting Flow**
   - User selects report type → System retrieves data → Generate report → Display/Export report

## Security Considerations

1. **Authentication and Authorization**
   - Secure login with password hashing
   - Role-based access control
   - Session management
   - Automatic logout after inactivity

2. **Data Protection**
   - Encryption of sensitive data
   - Secure database connections
   - Input validation to prevent SQL injection
   - XSS protection

3. **Audit Trail**
   - Logging of all critical operations
   - User activity tracking
   - Change history for important records

4. **Backup and Recovery**
   - Automated database backups
   - Disaster recovery procedures
   - Data integrity checks

## Implementation Considerations

1. **JavaFX Components**
   - Use of FXML for UI layout
   - CSS styling for consistent look and feel
   - Scene Builder for visual design
   - Custom controls for pharmacy-specific needs

2. **Database Integration**
   - JDBC for database connectivity
   - Connection pooling for performance
   - Prepared statements for security
   - Transaction management

3. **Performance Optimization**
   - Lazy loading for large datasets
   - Caching frequently accessed data
   - Background processing for reports
   - Efficient search algorithms

4. **Extensibility**
   - Plugin architecture for future extensions
   - Configuration-driven features
   - API design for potential integration with other systems
