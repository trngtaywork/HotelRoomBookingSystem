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

    String roleFilter = request.getParameter("roleFilter");
    String statusFilter = request.getParameter("statusFilter");
    String usernameSearch = request.getParameter("usernameSearch");
    String sortDate = request.getParameter("sortDate");

    if (roleFilter == null) {
        roleFilter = "All";
    }
    if (statusFilter == null) {
        statusFilter = "All";
    }
    if (usernameSearch == null) {
        usernameSearch = "";
    }
    if (sortDate == null || (!sortDate.equals("asc") && !sortDate.equals("desc"))) {
        sortDate = "desc";
    }

    AccountDAO accountDAO = new AccountDAO();
    List<Account> accountList = accountDAO.getUsersFiltered(roleFilter, statusFilter, usernameSearch, sortDate);
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

        <title>User List</title>

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
            table th {
                background-color: #dfa974;
                text-align: center;
                color: white;
            }
            table td {
                text-align: center;
                vertical-align: middle;
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
                                <a href="./index.html">
                                    <img src="img/logo.png" alt="">
                                </a>
                            </div>
                        </div>
                        <div class="col-lg-10">
                            <div class="nav-menu">
                                <nav class="mainmenu">
                                    <ul>
                                        <li><a href="./index.html">Home</a></li>
                                        <li class="active"><a href="userList.jsp">User List</a></li>
                                        <li><a href="roomListForAdmin.jsp">Room List</a></li>
                                        <li><a href="serviceList.jsp">Service List</a></li>
                                        <li><a href="dashboard.jsp">Dashboard</a></li>
                                        <li><a href="profile.jsp">Profile</a></li>
                                    </ul>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </header>

        <div class="container">
            <br> <br>
            <h3>User List</h3>
            <button class="btn-custom1 btn-primary" onclick="window.location.href = 'addAccount.jsp'">Add New Account</button>
            <hr>

            <!-- Role and Status Filter -->
            <form action="userList.jsp" method="get" class="row">
                <div class="col-md-3">
                    <label for="roleFilter">Filter by Role:</label>
                    <select name="roleFilter" id="roleFilter" class="form-control" onchange="this.form.submit()">
                        <option value="All" <%= "All".equals(roleFilter) ? "selected" : "" %>>All</option>
                        <option value="Admin" <%= "Admin".equals(roleFilter) ? "selected" : "" %>>Admin</option>
                        <option value="Customer" <%= "Customer".equals(roleFilter) ? "selected" : "" %>>Customer</option>
                        <option value="Receptionist" <%= "Receptionist".equals(roleFilter) ? "selected" : "" %>>Receptionist</option>
                    </select>
                </div>

                <div class="col-md-3">
                    <label for="statusFilter">Filter by Status:</label>
                    <select name="statusFilter" id="statusFilter" class="form-control" onchange="this.form.submit()">
                        <option value="All" <%= "All".equals(statusFilter) ? "selected" : "" %>>All</option>
                        <option value="Active" <%= "Active".equals(statusFilter) ? "selected" : "" %>>Active</option>
                        <option value="Inactive" <%= "Inactive".equals(statusFilter) ? "selected" : "" %>>Inactive</option>
                    </select>
                </div>

                <div class="col-md-3">
                    <label for="sortDate">Sort by Date:</label>
                    <select name="sortDate" id="sortDate" class="form-control" onchange="this.form.submit()">
                        <option value="desc" <%= "desc".equals(sortDate) ? "selected" : "" %>>Newest to Oldest</option>
                        <option value="asc" <%= "asc".equals(sortDate) ? "selected" : "" %>>Oldest to Newest</option>
                    </select>
                </div>

                <div class="col-md-3">
                    <label for="usernameSearch">Search by Username:</label>
                    <input type="text" name="usernameSearch" id="usernameSearch" class="form-control" placeholder="Enter username..."
                           value="<%= usernameSearch %>" onkeyup="if (event.keyCode == 13)
                                       this.form.submit();">
                </div>
            </form>

        </div>

        <div class="container mt-4">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Username</th>
                        <th>Email</th>
                        <th>Role</th>
                        <th>Status</th>
                        <th>Created Date</th>
                        <th style="width: 300px">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        if (accountList != null && !accountList.isEmpty()) {
                            for (Account acc : accountList) { 
                    %>
                    <tr>
                        <td><%= acc.getUsername() %></td>
                        <td><%= acc.getEmail() %></td>
                        <td><%= acc.getRole() %></td>
                        <td><%= acc.getIsActive() == true ? "Active" : "Inactive" %></td>
                        <td><%= acc.getCreatedDate() %></td>
                        <td>
                            <button class="btn-custom btn-primary" onclick="window.location.href = 'editUser.jsp?accountID=<%= acc.getAccountID() %>'">Edit</button>
                            <button class="btn-custom btn-warning" onclick="toggleStatus(<%= acc.getAccountID() %>, <%= acc.getIsActive() == true ? "false" : "true" %>)">
                                <%= acc.getIsActive() == true ? "Deactivate" : "Activate" %>
                            </button>
                            <button class="btn-custom btn-danger" onclick="window.location.href = 'deleteUser.jsp?accountID=<%= acc.getAccountID() %>'">Delete</button>
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
                    </div>
                </div>
            </div>
        </footer>

        <script>
            function toggleStatus(accountID, isActive) {
                if (confirm("Are you sure you want to change the status of this user?")) {
                    window.location.href = "UpdateUserStatusServlet?accountID=" + accountID + "&isActive=" + isActive;
                }
            }
        </script>

    </body>
</html>
