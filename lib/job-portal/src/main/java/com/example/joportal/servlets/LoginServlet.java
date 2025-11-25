package com.example.jobportal.servlets;

import com.example.jobportal.dao.UserDAO;
import com.example.jobportal.exceptions.UserNotFoundException;
import com.example.jobportal.model.User;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        
        try {
            User user = UserDAO.validateUser(username, password);
            
            // SUCCESS: Session Management
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("jsp/dashboard.jsp"); 

        } catch (UserNotFoundException e) {
            // ERROR: Invalid credentials (handled by custom exception)
            // Request Dispatcher: forwards request back to login.jsp
            req.setAttribute("errorMessage", "Invalid username or password.");
            req.getRequestDispatcher("jsp/login.jsp").forward(req, resp);
        
        } catch (SQLException e) {
            // ERROR: Database connection or query issue
            req.setAttribute("errorMessage", "A critical database error occurred. Try again later."); 
            req.getRequestDispatcher("jsp/login.jsp").forward(req, resp);
            e.printStackTrace();
        }
    }
}