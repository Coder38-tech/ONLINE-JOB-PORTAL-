<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.jobportal.model.User" %>
<%@ page import="com.example.jobportal.model.Employer" %>
<%@ page import="com.example.jobportal.model.JobSeeker" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Dashboard</title>
</head>
<body>

<% 
    // 1. Session Management Check
    User user = (User) session.getAttribute("user");
    
    // If the user object is null, redirect them back to the login page (Security check)
    if(user == null) {
        response.sendRedirect("jsp/login.jsp");
        return; // Stop execution
    }
%>

    <h1>Welcome, <%= user.getUsername() %>!</h1>
    
    <h2>Your Role: <%= user.getRole().toUpperCase() %></h2>
    <hr>

    <%
        // 2. Polymorphism and Role-Based Display
        
        // Check if the user is an Employer
        if("employer".equals(user.getRole())) {
            // Downcasting to access Employer-specific methods
            Employer employer = (Employer) user; 
    %>
            <h3>Employer Dashboard (<%= employer.getCompanyName() %>)</h3>
            <ul>
                <li><a href="jsp/postJob.jsp">Post a New Job</a></li>
                <li><a href="viewPostedJobs">View & Manage Posted Jobs</a></li>
                <li><a href="viewApplications">View Job Applications Received</a></li>
            </ul>
    <% 
        // Check if the user is a Job Seeker
        } else if("jobseeker".equals(user.getRole())) {
            JobSeeker seeker = (JobSeeker) user;
    %>
            <h3>Job Seeker Dashboard</h3>
            <ul>
                <li><a href="searchJobs">Search Available Jobs</a></li>
                <li><a href="viewApplications">View Application Status</a></li>
                <li><a href="editProfile">Update Resume (<%= seeker.getResume() != null ? "View" : "Upload" %>)</a></li>
            </ul>
    <%
        }
    %>
    
    <hr>
    <p><a href="logout">Logout</a></p>

</body>
</html>