package com.example.jobportal.servlets;

import com.example.jobportal.dao.UserDAO;
import com.example.jobportal.exceptions.UserNotFoundException;
import com.example.jobportal.model.User;
import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Controller for User Authentication.
 * Handles login for Job Seekers, Employers, and Admins.
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // 1. Retrieve login credentials from form
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        try {
            // 2. Business Logic: Validate user via DAO
            // This returns a specific subclass (Admin, Employer, or JobSeeker)
            User authenticatedUser = UserDAO.validateUser(username, password);
            
            if (authenticatedUser != null) {
                // 3. Success: Create Session and Store User Object
                HttpSession session = request.getSession(true); // Create if not exists
                session.setAttribute("user", authenticatedUser);
                
                // 4. Redirect to Dashboard (Note: relative to context root)
                response.sendRedirect("jsp/dashboard.jsp"); 
            } else {
                // Fallback if UserDAO returns null without throwing exception
                throw new UserNotFoundException("User validation returned no result.");
            }

        } catch (UserNotFoundException e) {
            // ERROR: Invalid credentials (handled by your custom exception)
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
        
        } catch (SQLException e) {
            // ERROR: JDBC / Database issues
            request.setAttribute("errorMessage", "Database error: " + e.getMessage()); 
            request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
            e.printStackTrace();
        }
    }
}