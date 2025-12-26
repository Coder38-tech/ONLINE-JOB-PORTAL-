package com.example.jobportal.servlets;

import com.example.jobportal.dao.UserDAO;
import com.example.jobportal.model.Admin;
import com.example.jobportal.model.Employer;
import com.example.jobportal.model.JobSeeker;
import com.example.jobportal.model.User;
import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Gather common user parameters from form
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String role = request.getParameter("role");

        User newUser = null;

        // 2. Polymorphism: Instantiate the correct subclass based on role selection
        if ("employer".equals(role)) {
            String companyName = request.getParameter("companyName");
            // Constructor: id=0, username, password, email, companyName
            newUser = new Employer(0, username, password, email, companyName);
            
        } else if ("jobseeker".equals(role)) {
            String resume = request.getParameter("resume");
            // Constructor: id=0, username, password, email, resume
            newUser = new JobSeeker(0, username, password, email, resume);
            
        } else if ("admin".equals(role)) {
            // Admin role added for Review 2
            newUser = new Admin(0, username, password, email);
            
        } else {
            // Handle error if role is missing or invalid
            request.setAttribute("message", "Error: Please select a valid user role.");
            request.getRequestDispatcher("jsp/register.jsp").forward(request, response);
            return; 
        }

        // 3. Call DAO layer for Transactional Database Insertion
        try {
            // We pass the object 'newUser'. The DAO uses 'instanceof' to handle table logic.
            boolean success = UserDAO.registerUser(newUser);

            if (success) {
                // Success: Use relative path to login page
                response.sendRedirect("jsp/login.jsp?registered=true");
            } else {
                request.setAttribute("message", "Registration failed. Please try again.");
                request.getRequestDispatcher("jsp/register.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            String errorMessage;
            if (e.getMessage().contains("Duplicate entry")) {
                errorMessage = "Registration failed: Username or Email already exists.";
            } else {
                errorMessage = "A database error occurred. Error: " + e.getMessage();
                e.printStackTrace(); 
            }
            
            request.setAttribute("message", errorMessage);
            request.getRequestDispatcher("jsp/register.jsp").forward(request, response);
        }
    }
}