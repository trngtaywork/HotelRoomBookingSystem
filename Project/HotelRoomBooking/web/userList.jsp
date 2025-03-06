<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
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

    // Get the role filter from request parameter (default is "All")
    String roleFilter = request.getParameter("roleFilter");
    if (roleFilter == null) {
        roleFilter = "All"; // Default filter is All
    }

    // Fetch users based on role filter
    AccountDAO accountDAO = new AccountDAO();
    List<Account> accountList = accountDAO.getUsersByRole(roleFilter);
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="description" content="Sona Template">
        <meta name="keywords" content="Sona, unica, creative, html">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">

        <!-- Google Font -->
        <link href="https://fonts.googleapis.com/css?family=Lora:400,700&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Cabin:400,500,600,700&display=swap" rel="stylesheet">

        <!-- Css Styles -->
        <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
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
            .btn-custom {
                height: 40px;
                width: 120px;
                border-radius: 5%;
                background-color: black;
                color: white;
                border: 1px solid black;
                margin: 5px;
            }
            table th, table td {
                text-align: center;
                vertical-align: middle;
            }
            .btn-custom1 {
                height: 70px;
                width: 240px;
                background-color: black;
                color: white;
                border: 1px solid black;
                padding-left: 5px;
            }
            .btn-custom1:hover {
                background-color: #dfa974;
            }
        </style>

        <title>User List</title>
    </head>
    <body>
        <!-- Header -->
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

        <!-- Main Content -->
        <div class="container mt-4 mb-4 p-3">
            <h3>User List</h3>
            <br>
            <button class="btn-custom1 btn-primary" onclick="window.location.href = 'addAccount.jsp'">Add New Account</button>
            <!-- Role Filter Form -->
            <div class="container mt-4 mb-4">
                <form action="userList.jsp" method="get">
                    <label for="roleFilter">Filter by Role:</label>
                    <select name="roleFilter" id="roleFilter" class="form-control" style="width: 200px;" onchange="this.form.submit()">
                        <option value="All" <%= "All".equals(roleFilter) ? "selected" : "" %>>All</option>
                        <option value="Admin" <%= "Admin".equals(roleFilter) ? "selected" : "" %>>Admin</option>
                        <option value="Customer" <%= "Customer".equals(roleFilter) ? "selected" : "" %>>Customer</option>
                        <option value="Receptionist" <%= "Receptionist".equals(roleFilter) ? "selected" : "" %>>Receptionist</option>
                    </select>
                </form>
            </div>
            <table class="table table-bordered mt-3">
                <thead>
                    <tr>
                        <th>AccountID</th>
                        <th>Username</th>
                        <th>Email</th>
                        <th>Role</th>
                        <th>Status</th>
                        <th>Created Date</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        if (accountList != null && !accountList.isEmpty()) {
                            for (Account acc : accountList) { 
                    %>
                    <tr>
                        <td><%= acc.getAccountID() %></td>
                        <td><%= acc.getUsername() %></td>
                        <td><%= acc.getEmail() %></td>
                        <td><%= acc.getRole() %></td>
                        <td><%= acc.getIsActive() == true ? "Active" : "Inactive" %></td>
                        <td><%= acc.getCreatedDate() %></td>
                        <td>
                            <button class="btn-custom btn-primary" onclick="window.location.href = 'editUser.jsp?accountID=<%= acc.getAccountID() %>'">Edit</button>
                            <button class="btn-custom btn-danger" onclick="toggleStatus(<%= acc.getAccountID() %>, <%= acc.getIsActive() == true ? "false" : "true" %>)">
                                <%= acc.getIsActive() == true ? "Deactivate" : "Activate" %>
                            </button>
                            <button class="btn-custom btn-warning" onclick="window.location.href = 'deleteUser.jsp?accountID=<%= acc.getAccountID() %>'">Delete</button>
                            <button class="btn-custom btn-info" onclick="window.location.href = 'profileDetails.jsp?accountID=<%= acc.getAccountID() %>'">Profile</button>
                        </td>
                    </tr>
                    <% 
                            }
                        } else {
                    %>
                    <tr>
                        <td colspan="7" class="text-center">No users available</td>
                    </tr>
                    <% 
                        }
                    %>
                </tbody>
            </table>
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
                        <div class="col-lg-3 offset-lg-1">
                            <div class="ft-contact">
                                <h6>Contact Us</h6>
                                <ul>
                                    <li>(12) 345 67890</li>
                                    <li>hotelroombooking@gmail.com</li>
                                    <li>856 Cordia Extension Apt. 356, Lake, United States</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </footer>

        <script>
            // Function to toggle user status (Activate/Deactivate)
            function toggleStatus(accountID, isActive) {
                if (confirm("Are you sure you want to change the status of this user?")) {
                    window.location.href = "UpdateUserStatusServlet?accountID=" + accountID + "&isActive=" + isActive;
                }
            }
        </script>
    </body>
</html>
