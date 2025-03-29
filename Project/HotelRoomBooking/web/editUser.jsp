<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="model.Account" %>
<%@ page import="dao.AccountDAO" %>

<%
    HttpSession sessionUser = request.getSession(false);
    Account user = (sessionUser != null) ? (Account) sessionUser.getAttribute("user") : null;
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    String accountIDParam = request.getParameter("accountID");
    int accountID = (accountIDParam != null) ? Integer.parseInt(accountIDParam) : -1;

    AccountDAO accountDAO = new AccountDAO();
    Account editAccount = accountDAO.getAccountById(accountID);

    if (editAccount == null) {
        response.sendRedirect("userList.jsp");
        return;
    }
%>

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

        <title>Edit User</title>

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
            .form-group {
                margin-bottom: 20px;
            }
            .btn-custom {
                width: 100%;
                padding: 10px;
                background-color: black;
                color: white;
                border: 1px solid black;
                cursor: pointer;
            }
            .btn-custom:hover {
                background-color: #dfa974;
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
                font-size: 18px;
                color: #666;
            }
        </style>
    </head>
    <body>
        <header class="header-section">
            <div class="menu-item">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-2">
                            <div class="logo">
                                <a href="./index.jsp">
                                    <img src="img/logo.png" alt="">
                                </a>
                            </div>
                        </div>
                        <div class="col-lg-10">
                            <div class="nav-menu">
                                <nav class="mainmenu">
                                    <ul>
                                        <li><a href="feedbackList.jsp">Feedback List</a></li>
                                        <li><a href="userList.jsp">User List</a></li>
                                        <li><a href="roomListForAdmin.jsp">Room List</a></li>
                                        <li><a href="ServiceListAdmin">Service List</a></li>
                                        <li><a href="dashboard.jsp">Dashboard</a></li>
                                        <li  ><a href="profile.jsp">Profile</a></li>
                                    </ul>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </header>

        <div class="container mt-4 mb-4">
            <h2>Edit User</h2>

            <% if (request.getAttribute("successMessage") != null) { %>
            <div class="alert alert-success"><%= request.getAttribute("successMessage") %></div>
            <% } %>

            <% if (request.getAttribute("errorMessage") != null) { %>
            <div class="alert alert-danger"><%= request.getAttribute("errorMessage") %></div>
            <% } %>

            <form action="EditUserServlet" method="post">
                <input type="hidden" name="accountID" value="<%= editAccount.getAccountID() %>">

                <div class="form-group">
                    <label>Username</label>
                    <input type="text" name="username" class="form-control" value="<%= editAccount.getUsername() %>" required>
                </div>

                <div class="form-group">
                    <label>Email</label>
                    <input type="email" name="email" class="form-control" value="<%= editAccount.getEmail() %>" required>
                </div>

                <div class="form-group password-container">
                    <label>Password</label>
                    <input type="password" name="password" id="password" class="form-control" value="<%= editAccount.getPassword() %>" required>
                    <i class="fa fa-eye password-toggle" onclick="togglePassword()"></i>
                </div>

                <div class="form-group">
                    <label>Role</label>
                    <select name="role" class="form-control">
                        <option value="Admin" <%= "Admin".equals(editAccount.getRole()) ? "selected" : "" %>>Admin</option>
                        <option value="Customer" <%= "Customer".equals(editAccount.getRole()) ? "selected" : "" %>>Customer</option>
                        <option value="Receptionist" <%= "Receptionist".equals(editAccount.getRole()) ? "selected" : "" %>>Receptionist</option>
                    </select>
                </div>

                <div class="form-group">
                    <label>Status</label>
                    <select name="isActive" class="form-control">
                        <option value="1" <%= editAccount.getIsActive() ? "selected" : "" %>>Active</option>
                        <option value="0" <%= !editAccount.getIsActive() ? "selected" : "" %>>Inactive</option>
                    </select>
                </div>

                <button type="submit" class="btn btn-primary">Save Changes</button>
                <button type="button" class="btn btn-danger" onclick="window.location.href = 'userList.jsp'">Cancel</button>
            </form>
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
            function togglePassword() {
                var passwordInput = document.getElementById("password");
                var passwordToggleIcon = document.querySelector(".password-toggle");

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
