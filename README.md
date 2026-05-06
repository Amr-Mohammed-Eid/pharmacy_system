# Pharmacy System

This is a Java-based pharmacy management system developed as an Object-Oriented Programming (OOP) course assignment. The application provides a comprehensive solution for managing inventory, prescriptions, sales, and user interactions in a pharmacy setting.

## Features

- **User Authentication**: Secure login system for pharmacy staff.
- **Inventory Management**: Track medications, products, and suppliers.
- **Prescription Management**: Handle patient prescriptions and related reports.
- **Sales Processing**: Manage sales transactions and generate reports.
- **Dashboard**: Overview of key metrics and quick access to main functions.
- **Reporting**: Generate various reports including inventory, sales, and prescription reports.
- **PDF Generation**: Export reports to PDF format.

## Technologies Used

- **Java**: Core programming language.
- **JavaFX**: For the graphical user interface.
- **MySQL**: Database for data persistence (assumed based on DatabaseManager).
- **Maven/Gradle**: Build tools (project structure suggests Maven).
- **CSS**: Styling for the UI.

## Project Structure

- `src/main/java/com/pharmacy/`: Contains all Java source files.
  - `controller/`: Controllers for handling UI logic.
  - `model/`: Data models representing entities.
  - `util/`: Utility classes for database, PDF generation, and validation.
  - `view/`: (Currently empty, but intended for views if not using FXML).
- `src/main/resources/`: Resources like FXML files, CSS, and images.

## How to Run

1. Ensure you have Java JDK installed (version 11 or higher recommended).
2. Set up a MySQL database and configure the connection in `DatabaseManager.java`.
3. Build the project using your preferred build tool (e.g., Maven: `mvn clean install`).
4. Run the application: `java -cp target/classes com.pharmacy.PharmacyApplication`.

## Requirements

See `requirements.md` for detailed functional and non-functional requirements.

## Design

Refer to `system_design.md` for the system architecture and design decisions.

## Validation

Check `validation_report.md` for testing and validation results.

## Implementation Summary

See `implementation_summary.md` for a summary of the implementation.

## TODO

Refer to `todo.md` for pending tasks and improvements.

## License

This project is developed for educational purposes as part of an OOP course assignment.
