package com.example.jobportal.model;

public class Employer extends User {
    private String companyName;
    
    public Employer(int id, String username, String password, String email, String companyName) {
        super(id, username, password, email); // Calls User constructor
        this.companyName = companyName;
    }
    
    @Override
    public String getRole() { 
        return "employer"; // Polymorphic implementation
    }
    
    // Employer-specific getter/setter
    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
}
