<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head><title>User Registration</title></head>
<body>
    <h2>Register as Job Seeker or Employer</h2>
    
    <% String message = (String) request.getAttribute("message"); %>
    <% if (message != null) { %>
        <div style="color: green; font-weight: bold;"><%= message %></div>
    <% } %>
    
    <form method="post" action="..register">
        <input type="text" name="username" required placeholder="Username" /><br/>
        <input type="email" name="email" required placeholder="Email" /><br/>
        <input type="password" name="password" required placeholder="Password" /><br/>
        
        <label for="role">Register as:</label>
        <select name="role" id="role" required onchange="toggleFields(this.value)">
            <option value="">-- Select Role --</option>
            <option value="jobseeker">Job Seeker</option>
            <option value="employer">Employer</option>
        </select><br/>
        
        <div id="seekerField" style="display:none; margin-top: 10px;">
            <input type="text" name="resume" placeholder="Resume Link (e.g., Google Drive)" />
        </div>
        
        <div id="employerField" style="display:none; margin-top: 10px;">
            <input type="text" name="companyName" placeholder="Company Name" />
        </div>
        
        <button type="submit" style="margin-top: 20px;">Register</button>
    </form>
    
    <p>Already have an account? <a href="../login.jsp">Login Here</a></p>

    <script>
        function toggleFields(role) {
            document.getElementById('seekerField').style.display = (role === 'jobseeker' ? 'block' : 'none');
            document.getElementById('employerField').style.display = (role === 'employer' ? 'block' : 'none');
        }
    </script>
</body>
</html>