<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reset Password</title>
    <link rel="stylesheet" href="css/stylelogin.css">
    <style>
        .message-box {
            text-align: center;
            padding: 10px;
            margin-bottom: 10px;
            border-radius: 5px;
            font-weight: bold;
        }
        .success {
            background-color: #d4edda;
            color: #155724;
        }
        .error {
            background-color: #f8d7da;
            color: #721c24;
        }
    </style>
</head>
<body>

<div class="login-wrap">
    <div class="login-html">
        <input id="tab-1" type="radio" name="tab" class="sign-in" checked>
        <label for="tab-1" class="tab">Reset Password</label>
        
        <div class="login-form">
            <div class="reset-password-htm">
                <% 
                    String message = (String) request.getAttribute("message");
                    if (message != null) {
                        boolean isSuccess = message.contains("Change Password success");
                %>
                    <div class="message-box <%= isSuccess ? "success" : "error" %>">
                        <%= message %>
                    </div>
                <% } %>

                <form action="ResetPasswordServlet" method="post">
                    <div class="group">
                        <label for="user" class="label">Username</label>
                        <input id="user" name="username" type="text" class="input" required>
                    </div>
                    <div class="group">
                        <label for="email" class="label">Email</label>
                        <input id="email" name="email" type="email" class="input" required>
                    </div>
                    <div class="group">
                        <label for="newpass" class="label">New Password</label>
                        <input id="newpass" name="newpassword" type="password" class="input" required>
                    </div>
                    <div class="group">
                        <label for="confirmpass" class="label">Confirm New Password</label>
                        <input id="confirmpass" name="confirmpassword" type="password" class="input" required>
                    </div>
                    <div class="group">
                        <input type="submit" class="button" value="Reset Password">
                    </div>
                    <div class="hr"></div>
                    <div class="foot-lnk">
                        <a href="Login.jsp">Back to Login</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
