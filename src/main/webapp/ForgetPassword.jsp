<%-- 
    Document   : ForgetPassword
    Created on : Dec 9, 2024, 7:38:41 PM
    Author     : gawes
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change The Password</title>
        <link rel="stylesheet" href="ForgetPasswordCss.css">
    </head>
    <body>
        
        <div class="forgot-password-container">
        <div class="forgot-password-form">
            <h1>Forgot Password</h1>
            <form id="forgotPasswordForm" action="forgotPasswordServlet" method="POST">
                <div class="form-group">
                    <label for="username">Enter your Username</label>
                    <input type="text" id="username" name="username" placeholder="Enter your Username" required>
                </div>
                 <div class="form-group">
                    <label for="password">Enter The New Password</label>
                    <input type="password" id="password" name="password" placeholder="Enter The New Password" required>
                </div>
                <button type="submit" class="change-btn">Change Password</button>
            </form>
        </div>
    </div>
    </body>
</html>
