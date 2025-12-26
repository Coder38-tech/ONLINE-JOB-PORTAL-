package com.example.jobportal.servlets;

import java.io.IOException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // Destroys the session
        }
        // Redirect back to login page
        response.sendRedirect("jsp/login.jsp?msg=Logged Out Successfully");
    }
}