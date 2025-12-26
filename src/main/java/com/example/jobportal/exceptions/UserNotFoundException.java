package com.example.jobportal.exceptions;

/**
 * Custom Exception class to handle authentication failures.
 * This demonstrates the "Exception Handling" requirement for Review 2.
 */
public class UserNotFoundException extends Exception {
    private static final long serialVersionUID = 1L; 

    // Default constructor
    public UserNotFoundException() {
        super("User not found in the system.");
    }

    // Constructor that accepts a custom message
    public UserNotFoundException(String message) {
        super(message);
    }
    
    // Constructor that accepts a message and a cause (useful for nesting exceptions)
    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}