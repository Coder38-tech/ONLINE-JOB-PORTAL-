<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Job Portal Login</title>
    <link rel="stylesheet" href="assets/css/style.css"> 
    </head>
<body>

    <div class="login-container">
        <h2>Job Portal Login</h2>
        
        <% 
            // Check if the Servlet set an error message in the Request scope
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null) {
        %>
            <div style="color:red; margin-bottom: 15px; border: 1px solid red; padding: 10px;">
                <%= errorMessage %>
            </div>
        <%
            }
        %>
        
        <form method="post" action="login">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required placeholder="Enter Username" />

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required placeholder="Enter Password" />
            
            <button type="submit">Login</button>
        </form>

        <p>New user? <a href="jsp/register.jsp">Register Here</a></p>
    </div>

</body>
</html>