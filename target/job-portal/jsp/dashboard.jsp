<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.jobportal.model.User" %>
<%@ page import="com.example.jobportal.model.Employer" %>
<%@ page import="com.example.jobportal.model.JobSeeker" %>
<%@ page import="com.example.jobportal.model.Admin" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Dashboard</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; line-height: 1.6; }
        .header { background: #f4f4f4; padding: 10px; border-bottom: 2px solid #ccc; }
        .role-section { margin-top: 20px; padding: 15px; border: 1px solid #ddd; border-radius: 5px; }
        .logout-btn { color: red; text-decoration: none; font-weight: bold; }
    </style>
</head>
<body>

<% 
    // 1. Session Management Check
    User user = (User) session.getAttribute("user");
    
    // Safety check: If no session, go back to login
    // Since this file is in /jsp/, login.jsp is in the same folder
    if(user == null) {
        response.sendRedirect("login.jsp");
        return; 
    }
%>

    <div class="header">
        <h1>Welcome, <%= user.getUsername() %>!</h1>
        <p>Logged in as: <strong><%= user.getRole().toUpperCase() %></strong></p>
    </div>

    <div class="role-section">
        <%
            // 2. Role-Based Display using Polymorphism & Downcasting
            
            if (user instanceof Employer) {
                Employer employer = (Employer) user; 
        %>
                <h3>Employer Panel: <%= employer.getCompanyName() %></h3>
                <ul>
                    <li><a href="postJob.jsp">Post a New Job</a></li>
                    <li><a href="../viewPostedJobs">View My Postings</a></li>
                    <li><a href="../viewApplications">Manage Applications</a></li>
                </ul>
        <% 
            } else if (user instanceof JobSeeker) {
                JobSeeker seeker = (JobSeeker) user;
        %>
                <h3>Job Seeker Panel</h3>
                <p><strong>Current Resume:</strong> <%= (seeker.getResume() != null && !seeker.getResume().isEmpty()) ? "Attached" : "No resume found" %></p>
                <ul>
                    <li><a href="../searchJobs">Browse All Jobs</a></li>
                    <li><a href="../myApplications">My Applied Jobs</a></li>
                    <li><a href="editProfile.jsp">Update Profile & Resume</a></li>
                </ul>
        <%
            } else if (user instanceof Admin) {
        %>
                <h3>Administrator Panel</h3>
                <p>System-wide management access granted.</p>
                <ul>
                    <li><a href="../admin/manageUsers">Manage All Users</a></li>
                    <li><a href="../admin/allJobs">View All Job Postings</a></li>
                    <li><a href="../admin/reports">Generate System Reports</a></li>
                </ul>
        <%
            }
        %>
    </div>

    <hr>
    <p><a href="../logout" class="logout-btn">Logout from System</a></p>

</body>
</html>