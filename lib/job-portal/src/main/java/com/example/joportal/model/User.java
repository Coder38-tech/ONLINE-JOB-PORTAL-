package com.example.jobportal.model; // Ensure this package matches your structure

public abstract class User {
    // Private fields for Encapsulation
    protected int id;
    protected String username;
    protected String password; // In a real app, hash this!
    protected String email;

    // Constructor
    public User(int id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // Abstract method for Polymorphism (must be implemented by subclasses)
    public abstract String getRole();

    // Getters (Setters are omitted here for brevity but must be included for full encapsulation score)
    public int getId() { return id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getEmail() { return email; }
    
    // Example Setter
    public void setPassword(String password) { this.password = password; }
}
