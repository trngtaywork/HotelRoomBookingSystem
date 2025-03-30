<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="dao.AccountDAO" %>
<%@ page import="model.Account" %>
<%@ page import="model.Profile" %>

<%
    String accountIDStr = request.getParameter("accountID");
    if (accountIDStr == null || accountIDStr.isEmpty()) {
        response.sendRedirect("userList.jsp");
        return;
    }

    int accountID = Integer.parseInt(accountIDStr);
    AccountDAO accountDAO = new AccountDAO();
    Account account = accountDAO.getAccountById(accountID);
    Profile profile = accountDAO.getProfileByAccountId(accountID);

    if (account == null || profile == null) {
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
        </style>

        <title>User Profile</title>
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
                                        <li><a href="./index.jsp">Home</a></li>
                                        <li  ><a href="userList.jsp">User List</a></li>
                                        <li><a href="roomListForAdmin.jsp">Room List</a></li>
                                        <li><a href="ServiceListAdmin">Service List</a></li>
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

        <div class="container mt-4 mb-4">
            <h3>Profile Details</h3>
            <table class="table table-bordered mt-3">
                <tr>
                    <th>AccountID</th>
                    <td><%= account.getAccountID() %></td>
                </tr>
                <tr>
                    <th>Username</th>
                    <td><%= account.getUsername() %></td>
                </tr>
                <tr>
                    <th>Email</th>
                    <td><%= account.getEmail() %></td>
                </tr>
                <tr>
                    <th>Role</th>
                    <td><%= account.getRole() %></td>
                </tr>
                <tr>
                    <th>Status</th>
                    <td><%= account.getIsActive() ? "Active" : "Inactive" %></td>
                </tr>
                <tr>
                    <th>Created Date</th>
                    <td><%= account.getCreatedDate() %></td>
                </tr>
                <tr>
                    <th>Full Name</th>
                    <td><%= profile.getName() %></td>
                </tr>
                <tr>
                    <th>Phone Number</th>
                    <td><%= profile.getPhoneNumber() %></td>
                </tr>
                <tr>
                    <th>Gender</th>
                    <td><%= profile.getGender() %></td>
                </tr>
                <tr>
                    <th>Address</th>
                    <td><%= profile.getAddress() %></td>
                </tr>
            </table>

            <a href="userList.jsp" class="btn btn-secondary">Back to User List</a>
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
    </body>
</html>
