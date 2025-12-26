<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Register</title>
    <script>
        function toggleFields(role) {
            document.getElementById('empField').style.display = (role === 'employer') ? 'block' : 'none';
            document.getElementById('jsField').style.display = (role === 'jobseeker') ? 'block' : 'none';
        }
    </script>
</head>
<body>
    <h2>Register</h2>
     <form method="post" action="${pageContext.request.contextPath}/RegisterServlet">
    <input type="text" name="username" placeholder="Username" required><br>
    <input type="email" name="email" placeholder="Email" required><br>
    <input type="password" name="password" placeholder="Password" required><br>
    
    <select name="role" onchange="toggleFields(this.value)" required>
        <option value="">Select Role</option>
        <option value="jobseeker">Job Seeker</option>
        <option value="employer">Employer</option>
        <option value="admin">Admin</option>
    </select><br>

    <div id="companyField" style="display:none;">
        <input type="text" name="companyName" placeholder="Company Name">
    </div>
    
    <div id="resumeField" style="display:none;">
        <textarea name="resume" placeholder="Enter Resume Details"></textarea>
    </div>

    <button type="submit">Register</button>
    </form>

    <script>
    function toggleFields(role) {
        document.getElementById('companyField').style.display = (role === 'employer') ? 'block' : 'none';
        document.getElementById('resumeField').style.display = (role === 'jobseeker') ? 'block' : 'none';
    }
    </script>
</body>
</html>