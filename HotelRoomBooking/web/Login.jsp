<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Authentication</title>
    <link rel="stylesheet" href="css/stylelogin.css">
</head>
<body>

<div class="login-wrap">
    <div class="login-html">
        <input id="tab-1" type="radio" name="tab" class="sign-in" checked>
        <label for="tab-1" class="tab">Sign In</label>
        
        <input id="tab-2" type="radio" name="tab" class="sign-up">
        <label for="tab-2" class="tab">Sign Up</label>
        
        <div class="login-form">
            <div class="sign-in-htm">
                <% 
                    String message = request.getParameter("resetSuccess");
                    if ("true".equals(message)) {
                %>
                    <p style="color: green; text-align: center;">Password reset successful. Please log in.</p>
                <% } %>
                <form action="LoginServlet" method="post">
                    <div class="group">
                        <label for="user" class="label">Username</label>
                        <input id="user" name="username" type="text" class="input" required>
                    </div>
                    <div class="group">
                        <label for="pass" class="label">Password</label>
                        <input id="pass" name="password" type="password" class="input" required>
                    </div>
                    <div class="group">
                        <input type="submit" class="button" value="Sign In">
                    </div>
                    <div class="hr"></div>
                    <div class="foot-lnk">
                        <a href="ResetPassword.jsp">Forgot Password?</a>
                    </div>
                </form>
            </div>
            <div class="sign-up-htm">
                <form action="RegisterServlet" method="post">
                    <div class="group">
                        <label for="user" class="label">Username</label>
                        <input id="user" name="username" type="text" class="input" required>
                    </div>
                    <div class="group">
                        <label for="email" class="label">Email</label>
                        <input id="email" name="email" type="email" class="input" required>
                    </div>
                    <div class="group">
                        <label for="pass" class="label">Password</label>
                        <input id="pass" name="password" type="password" class="input" required>
                    </div>
                    <div class="group">
                        <label for="confirmpass" class="label">Confirm Password</label>
                        <input id="confirmpass" name="confirmpassword" type="password" class="input" required>
                    </div>
                    <div class="group">
                        <input type="submit" class="button" value="Sign Up">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
