
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Account</title>
        <link rel="stylesheet" href="CreateAccountCss.css">
    </head>
   <div class="signup-container">
        <div class="signup-form">
            <h1>Create Account</h1>
            <form name="myform" action="register" method="POST" onsubmit="return showAlertAndRedirect();">
                <div class="form-group">
                    <label for="username">Username</label>
                    <input type="text" id="username" name="username" placeholder="Enter your username" required>
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" placeholder="Enter your password" required>
                </div>
                <div class="form-group">
                    <label for="confirm-password">Confirm Password</label>
                    <input type="password" id="confirm-password" name="confirm-password" placeholder="Confirm your password" required>
                </div>
                <button type="submit" class="signup-btn">Create Account</button>
                <div class="extra-options">
                    <p>Already have an account? <a href="LoginPage.jsp">Login</a></p>
                </div>
            </form>
        </div>
    </div>

   

    <script src="CreateAccountJS.js"></script>

    </body>
</html>
