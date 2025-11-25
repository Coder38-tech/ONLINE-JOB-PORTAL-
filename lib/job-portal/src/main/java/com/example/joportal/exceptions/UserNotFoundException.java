package com.example.jobportal.exceptions;

// Custom exception for better error handling in the LoginServlet
public class UserNotFoundException extends Exception {
    private static final long serialVersionUID = 1L; 

    public UserNotFoundException(String message) {
        super(message);
    }
}