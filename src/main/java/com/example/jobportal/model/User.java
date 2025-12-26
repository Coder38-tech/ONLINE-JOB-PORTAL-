package com.example.jobportal.model;
public abstract class User {
    private int id;
    private String username;
    private String password; 
    private String email;

    // Default constructor (Needed for some frameworks/DAOs)
    public User() {}

    // Parameterized Constructor
    public User(int id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // Abstract method for Polymorphism: Each subclass defines its own role
    public abstract String getRole();

    // Getters and Setters (Encapsulation)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}