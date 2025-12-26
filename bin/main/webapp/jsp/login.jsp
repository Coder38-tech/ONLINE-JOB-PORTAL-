<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Job Portal Login</title>
    <link rel="stylesheet" href="../assets/css/style.css"> 
</head>
<body>

    <div class="login-container">
        <h2>Job Portal Login</h2>
        
        <% 
            // 1. Handle Error Messages from Servlet
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null) {
        %>
            <div style="color:red; margin-bottom: 15px; border: 1px solid red; padding: 10px; background-color: #ffe6e6;">
                <%= errorMessage %>
            </div>
        <%
            }
        %>

        <% 
            // 2. Handle Success Messages (e.g., after successful registration)
            if (request.getParameter("registered") != null) {
        %>
            <div style="color:green; margin-bottom: 15px; border: 1px solid green; padding: 10px; background-color: #e6ffed;">
                Registration successful! Please login below.
            </div>
        <%
            }
        %>
        
        <form method="post" action="../LoginServlet">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required placeholder="Enter Username" />

            <br><br>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required placeholder="Enter Password" />
            
            <br><br>
            
            <button type="submit">Login</button>
        </form>

        <p>New user? <a href="register.jsp">Register Here</a></p>
    </div>

</body>
</html>