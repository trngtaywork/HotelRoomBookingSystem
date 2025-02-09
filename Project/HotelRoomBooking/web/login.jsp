<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="model.Account" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <link rel="stylesheet" href="css/login.css">
    </head>
    <body>
        <div class="login-wrap">
            <div class="login-html">
                <input id="tab-1" type="radio" name="tab" class="sign-in" checked>
                <label for="tab-1" class="tab">Sign In</label>
                <input id="tab-2" type="radio" name="tab" class="sign-up">
                <label for="tab-2" class="tab" onclick="window.location.href = 'Register.jsp'">Sign Up</label>

                <div class="login-form">
                    <!-- Form Login -->
                    <form action="login" method="post">
                        <div class="sign-in-htm">
                            <div class="group">
                                <label for="username" class="label">Username</label>
                                <input id="username" name="username" type="text" class="input" required>
                            </div>
                            <div class="group">
                                <label for="password" class="label">Password</label>
                                <input id="password" name="password" type="password" class="input" data-type="password" required>
                            </div>
                            <div class="group">
                                <input id="check" type="checkbox" class="check" checked>
                                <label for="check"><span class="icon"></span> Keep me Signed in</label>
                            </div>
                            <div class="group">
                                <input type="submit" class="button" value="Sign In">
                            </div>
                            <div class="hr"></div>
                            <div class="foot-lnk">
                                <a href="resetpassword.jsp">Forgot Password?</a>
                            </div>

                            <div class="error-message" style="color:red; text-align:center;">
                                <% if (request.getAttribute("error") != null) { %>
                                <%= request.getAttribute("error") %>
                                <% } %>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
