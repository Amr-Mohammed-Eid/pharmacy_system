package com.pharmacy.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

/**
 * Utility class for validation and security operations.
 */
public class ValidationUtils {
    
    // Regular expression patterns for validation
    private static final Pattern EMAIL_PATTERN = 
        Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    
    private static final Pattern PHONE_PATTERN = 
        Pattern.compile("^\\+?[0-9]{10,15}$");
    
    private static final Pattern PASSWORD_PATTERN = 
        Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
    
    /**
     * Validate email format
     * 
     * @param email Email to validate
     * @return true if email is valid
     */
    public static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }
    
    /**
     * Validate phone number format
     * 
     * @param phone Phone number to validate
     * @return true if phone number is valid
     */
    public static boolean isValidPhone(String phone) {
        if (phone == null || phone.isEmpty()) {
            return false;
        }
        return PHONE_PATTERN.matcher(phone).matches();
    }
    
    /**
     * Validate password strength
     * Password must contain at least:
     * - 8 characters
     * - 1 digit
     * - 1 lowercase letter
     * - 1 uppercase letter
     * - 1 special character
     * 
     * @param password Password to validate
     * @return true if password is strong enough
     */
    public static boolean isStrongPassword(String password) {
        if (password == null || password.isEmpty()) {
            return false;
        }
        return PASSWORD_PATTERN.matcher(password).matches();
    }
    
    /**
     * Hash a password using SHA-256
     * 
     * @param password Password to hash
     * @return Hashed password
     */
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            
            // Convert byte array to hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // Fallback to plain text if hashing fails
            // In a real application, this should throw an exception
            return password;
        }
    }
    
    /**
     * Verify if a password matches its hash
     * 
     * @param password Plain text password
     * @param hash Hashed password
     * @return true if password matches hash
     */
    public static boolean verifyPassword(String password, String hash) {
        return hashPassword(password).equals(hash);
    }
    
    /**
     * Sanitize input to prevent SQL injection
     * 
     * @param input Input to sanitize
     * @return Sanitized input
     */
    public static String sanitizeInput(String input) {
        if (input == null) {
            return "";
        }
        // Remove SQL injection characters
        return input.replaceAll("['\"\\\\;]", "");
    }
    
    /**
     * Validate numeric input
     * 
     * @param input Input to validate
     * @return true if input contains only numbers
     */
    public static boolean isNumeric(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        return input.matches("\\d+");
    }
    
    /**
     * Validate decimal input
     * 
     * @param input Input to validate
     * @return true if input is a valid decimal number
     */
    public static boolean isDecimal(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        return input.matches("\\d+(\\.\\d+)?");
    }
}
