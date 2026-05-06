package com.pharmacy.model;

import java.time.LocalDateTime;

/**
 * Base class for all users in the system.
 * Implements common user attributes and methods.
 */
public class User {
    private int userId;
    private String username;
    private String passwordHash;
    private String fullName;
    private String role;
    private String email;
    private String phone;
    private String address;
    private LocalDateTime lastLogin;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean active;

    /**
     * Default constructor
     */
    public User() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.active = true;
    }

    /**
     * Parameterized constructor
     * 
     * @param userId User ID
     * @param username Username
     * @param passwordHash Hashed password
     * @param fullName Full name
     * @param role User role
     * @param email Email address
     * @param phone Phone number
     * @param address Physical address
     */
    public User(int userId, String username, String passwordHash, String fullName, 
                String role, String email, String phone, String address) {
        this.userId = userId;
        this.username = username;
        this.passwordHash = passwordHash;
        this.fullName = fullName;
        this.role = role;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.active = true;
    }

    /**
     * Authenticate user with provided credentials
     * 
     * @param password Plain text password to verify
     * @return true if authentication is successful, false otherwise
     */
    public boolean authenticate(String password) {
        // In a real application, this would use a secure password hashing algorithm
        // For demonstration purposes, we're using a simple comparison
        String hashedInput = password; // In reality, this would be hashed
        boolean authenticated = this.passwordHash.equals(hashedInput);
        
        if (authenticated) {
            this.lastLogin = LocalDateTime.now();
        }
        
        return authenticated;
    }

    /**
     * Change user password
     * 
     * @param currentPassword Current password for verification
     * @param newPassword New password to set
     * @return true if password change is successful, false otherwise
     */
    public boolean changePassword(String currentPassword, String newPassword) {
        if (authenticate(currentPassword)) {
            // In a real application, this would use a secure password hashing algorithm
            this.passwordHash = newPassword; // In reality, this would be hashed
            this.updatedAt = LocalDateTime.now();
            return true;
        }
        return false;
    }

    /**
     * Update user profile information
     * 
     * @param fullName Full name
     * @param email Email address
     * @param phone Phone number
     * @param address Physical address
     */
    public void updateProfile(String fullName, String email, String phone, String address) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.updatedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", fullName='" + fullName + '\'' +
                ", role='" + role + '\'' +
                ", email='" + email + '\'' +
                ", active=" + active +
                '}';
    }
}
