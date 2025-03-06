<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="description" content="Sona Template">
        <meta name="keywords" content="Sona, unica, creative, html">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">

        <link href="https://fonts.googleapis.com/css?family=Lora:400,700&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Cabin:400,500,600,700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
        <link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
        <link rel="stylesheet" href="css/flaticon.css" type="text/css">
        <link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
        <link rel="stylesheet" href="css/nice-select.css" type="text/css">
        <link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css">
        <link rel="stylesheet" href="css/magnific-popup.css" type="text/css">
        <link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
        <link rel="stylesheet" href="css/style.css" type="text/css">

        <style>
            .header-section {
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                z-index: 999;
                background-color: #fff;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            }
            body {
                padding-top: 80px;
            }

            .formbold-main-wrapper {
                width: 100%;
                display: flex;
                justify-content: center;
            }

            .formbold-form-wrapper {
                background-color: #fff;
                padding: 20px;
                width: 100%;
                max-width: 600px;
                border-radius: 8px;
                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            }

            .formbold-form-wrapper h2 {
                text-align: center;
                margin-bottom: 20px;
            }

            .formbold-input-group {
                margin-bottom: 20px;
            }

            .formbold-form-label {
                display: block;
                font-weight: bold;
                margin-bottom: 8px;
            }

            .formbold-form-input,
            .formbold-form-select,
            .formbold-btn {
                width: 100%;
                padding: 10px;
                border-radius: 5px;
                border: 1px solid #ddd;
                font-size: 16px;
                box-sizing: border-box;
            }

            .formbold-btn {
                background-color: #28a745;
                color: #fff;
                border: none;
                cursor: pointer;
                font-weight: bold;
            }

            .formbold-btn:hover {
                background-color: #218838;
            }

            .error-message {
                color: red;
                font-size: 14px;
            }

            .alert {
                margin-top: 10px;
            }

            /* Additional style for consistency */
            .formbold-form-input {
                padding-left: 15px;
            }

            .password-container {
                position: relative;
            }

            .password-toggle {
                position: absolute;
                right: 10px;
                top: 50%;
                transform: translateY(-50%);
                cursor: pointer;
            }

        </style>

        <title>Add Account</title>
    </head>
    <body>
        <header class="header-section">
            <div class="menu-item">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-2">
                            <div class="logo">
                                <a href="./index.html">
                                    <img src="img/logo.png" alt="">
                                </a>
                            </div>
                        </div>
                        <div class="col-lg-10">
                            <div class="nav-menu">
                                <nav class="mainmenu">
                                    <ul>
                                        <li class="active"><a href="./index.html">Home</a></li>
                                        <li><a href="userList.jsp">Users</a></li>
                                        <li><a href="./about-us.html">About Us</a></li>
                                        <li><a href="./contact.html">Contact</a></li>
                                    </ul>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </header>

        <div class="formbold-main-wrapper">
            <div class="formbold-form-wrapper">
                <h2>Add Account</h2>
                <% if (request.getAttribute("successMessage") != null) { %>
                <div class="alert alert-success">
                    <%= request.getAttribute("successMessage") %>
                </div>
                <% } %>
                <% if (request.getAttribute("errorMessage") != null) { %>
                <div class="alert alert-danger">
                    <%= request.getAttribute("errorMessage") %>
                </div>
                <% } %>

                <form id="addAccountForm" action="AddAccountServlet" method="post" onsubmit="return validateForm()">
                    <div class="formbold-input-group">
                        <label class="formbold-form-label">Username</label>
                        <input type="text" name="username" id="username" placeholder="Enter username" class="formbold-form-input" value="<%= request.getAttribute("username") != null ? request.getAttribute("username") : "" %>">
                        <span class="error-message" id="usernameError"><%= request.getAttribute("usernameError") != null ? request.getAttribute("usernameError") : "" %></span>
                    </div>

                    <div class="formbold-input-group">
                        <label class="formbold-form-label">Email</label>
                        <input type="email" name="email" id="email" placeholder="Enter email" class="formbold-form-input" value="<%= request.getAttribute("email") != null ? request.getAttribute("email") : "" %>">
                        <span class="error-message" id="emailError"><%= request.getAttribute("emailError") != null ? request.getAttribute("emailError") : "" %></span>
                    </div>

                    <div class="formbold-input-group password-container">
                        <label class="formbold-form-label">Password</label>
                        <input type="password" name="password" id="password" placeholder="Enter password" class="formbold-form-input">
                        <span class="password-toggle" onclick="togglePassword()">
                            <i class="fa fa-eye"></i>
                        </span>
                        <span class="error-message" id="passwordError"><%= request.getAttribute("passwordError") != null ? request.getAttribute("passwordError") : "" %></span>
                    </div>

                    <div class="formbold-input-group">
                        <label class="formbold-form-label">Role</label>
                        <select name="role" id="role" class="formbold-form-select">
                            <option value="Admin" <%= "Admin".equals(request.getAttribute("role")) ? "selected" : "" %>>Admin</option>
                            <option value="Customer" <%= "Customer".equals(request.getAttribute("role")) ? "selected" : "" %>>Customer</option>
                            <option value="Receptionist" <%= "Receptionist".equals(request.getAttribute("role")) ? "selected" : "" %>>Receptionist</option>
                        </select>
                        <span class="error-message" id="roleError"><%= request.getAttribute("roleError") != null ? request.getAttribute("roleError") : "" %></span>
                    </div>

                    <div class="formbold-input-group">
                        <label class="formbold-form-label">Status</label>
                        <select name="status" id="status" class="formbold-form-select">
                            <option value="1" <%= "1".equals(request.getAttribute("status")) ? "selected" : "" %>>Active</option>
                            <option value="0" <%= "0".equals(request.getAttribute("status")) ? "selected" : "" %>>Inactive</option>
                        </select>
                        <span class="error-message" id="statusError"><%= request.getAttribute("statusError") != null ? request.getAttribute("statusError") : "" %></span>
                    </div>

                    <button class="formbold-btn">Submit</button>
                </form>
            </div>
        </div>

        <footer class="footer-section">
            <div class="container">
                <div class="footer-text">
                    <div class="row">
                        <div class="col-lg-4">
                            <div class="ft-about">
                                <div class="logo">
                                    <a href="#">
                                        <img src="img/footer-logo.png" alt="">
                                    </a>
                                </div>
                                <p>We inspire and reach millions of travelers across 90 local websites</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </footer>

        <script>
            function validateForm() {
                let isValid = true;

                function showError(id, message) {
                    document.getElementById(id).innerText = message;
                    isValid = false;
                }

                document.querySelectorAll(".error-message").forEach(e => e.innerText = "");

                let username = document.getElementById("username").value.trim();
                let email = document.getElementById("email").value.trim();
                let password = document.getElementById("password").value.trim();
                let role = document.getElementById("role").value;
                let status = document.getElementById("status").value;

                if (username === "")
                    showError("usernameError", "Username is required.");
                if (email === "")
                    showError("emailError", "Email is required.");
                if (password === "" || password.length < 8 || password.includes(" "))
                    showError("passwordError", "Password must be at least 8 characters long and cannot contain spaces.");
                if (role === "")
                    showError("roleError", "Role is required.");
                if (status === "")
                    showError("statusError", "Status is required.");

                return isValid;
            }

            // Function to toggle the password visibility
            function togglePassword() {
                const passwordInput = document.getElementById("password");
                const passwordToggleIcon = document.querySelector(".password-toggle i");

                if (passwordInput.type === "password") {
                    passwordInput.type = "text";
                    passwordToggleIcon.classList.remove("fa-eye");
                    passwordToggleIcon.classList.add("fa-eye-slash");
                } else {
                    passwordInput.type = "password";
                    passwordToggleIcon.classList.remove("fa-eye-slash");
                    passwordToggleIcon.classList.add("fa-eye");
                }
            }
        </script>
    </body>
</html>
