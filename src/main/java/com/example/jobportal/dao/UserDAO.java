package com.example.jobportal.dao;

import com.example.jobportal.exceptions.UserNotFoundException;
import com.example.jobportal.model.*;
import com.example.jobportal.dao.util.DBUtil;
import java.sql.*;

public class UserDAO {
    public static User validateUser(String username, String password) throws UserNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBUtil.getConnection();
            ps = con.prepareStatement("SELECT id, username, password, email, role FROM users WHERE username=? AND password=?");
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            
            if(rs.next()) {
                int id = rs.getInt("id");
                String email = rs.getString("email");
                String role = rs.getString("role");

                if("employer".equals(role)) {
                    PreparedStatement psDetails = con.prepareStatement("SELECT company_name FROM employers WHERE user_id=?");
                    psDetails.setInt(1, id);
                    ResultSet rsDetails = psDetails.executeQuery();
                    String companyName = rsDetails.next() ? rsDetails.getString("company_name") : "Unknown";
                    return new Employer(id, username, password, email, companyName);

                } else if("jobseeker".equals(role)) {
                    PreparedStatement psDetails = con.prepareStatement("SELECT resume FROM jobseekers WHERE user_id=?");
                    psDetails.setInt(1, id);
                    ResultSet rsDetails = psDetails.executeQuery();
                    String resume = rsDetails.next() ? rsDetails.getString("resume") : "";
                    return new JobSeeker(id, username, password, email, resume);

                } else if("admin".equals(role)) {
                    // Admin doesn't have a separate details table in your schema
                    return new Admin(id, username, password, email);
                }
                return null; 
            } else {
                throw new UserNotFoundException("Invalid username or password.");
            }
        } finally {
            closeResources(con, ps, rs);
        }
    }

    // --- Registration (With Transaction Handling) ---
    public static boolean registerUser(User user) throws SQLException {
        Connection con = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;

        try {
            con = DBUtil.getConnection();
            con.setAutoCommit(false); // Start Transaction

            // 1. Insert into primary USERS table
            ps1 = con.prepareStatement(
                "INSERT INTO users (username, password, email, role) VALUES (?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS 
            );
            ps1.setString(1, user.getUsername());
            ps1.setString(2, user.getPassword());
            ps1.setString(3, user.getEmail());
            ps1.setString(4, user.getRole());
            ps1.executeUpdate();
            
            ResultSet rs = ps1.getGeneratedKeys();
            int newUserId = rs.next() ? rs.getInt(1) : -1;
            
            // 2. Insert into role-specific tables (Skip for Admin)
            if (user instanceof Employer) {
                ps2 = con.prepareStatement("INSERT INTO employers (user_id, company_name) VALUES (?, ?)");
                ps2.setInt(1, newUserId);
                ps2.setString(2, ((Employer) user).getCompanyName());
                ps2.executeUpdate();
            } else if (user instanceof JobSeeker) {
                ps2 = con.prepareStatement("INSERT INTO jobseekers (user_id, resume) VALUES (?, ?)");
                ps2.setInt(1, newUserId);
                ps2.setString(2, ((JobSeeker) user).getResume());
                ps2.executeUpdate();
            }

            con.commit(); // Success
            return true;

        } catch (SQLException e) {
            if(con != null) con.rollback(); // Undo everything on error
            throw e;
        } finally {
            closeResources(con, ps1, null);
            if(ps2 != null) ps2.close();
        }
    }

    private static void closeResources(Connection con, Statement ps, ResultSet rs) {
        try { if (rs != null) rs.close(); } catch (Exception e) {}
        try { if (ps != null) ps.close(); } catch (Exception e) {}
        try { if (con != null) con.close(); } catch (Exception e) {}
    }
}