package com.example.jobportal.model;
public class JobSeeker extends User {
    // Encapsulation: Private field specific to JobSeeker
    private String resume;
    
    // Default Constructor (Required for many DAO patterns)
    public JobSeeker() {
        super();
    }

    // Parameterized Constructor
    public JobSeeker(int id, String username, String password, String email, String resume) {
        super(id, username, password, email); // Calls the User parent constructor
        this.resume = resume;
    }
    
    /**
     * Polymorphism: Specific implementation for Job Seeker.
     * @return String "jobseeker"
     */
    @Override
    public String getRole() { 
        return "jobseeker"; 
    }
    
    // Encapsulation: Getter and Setter for specialized field
    public String getResume() { return resume; }
    public void setResume(String resume) { this.resume = resume; }
}