package com.example.jobportal.model;

public class JobSeeker extends User {
    private String resume;
    
    public JobSeeker(int id, String username, String password, String email, String resume) {
        super(id, username, password, email); // Calls User constructor
        this.resume = resume;
    }
    
    @Override
    public String getRole() { 
        return "jobseeker"; // Polymorphic implementation
    }
    
    // JobSeeker-specific getter/setter
    public String getResume() { return resume; }
    public void setResume(String resume) { this.resume = resume; }
}