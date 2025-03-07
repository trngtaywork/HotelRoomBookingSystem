<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="model.Account" %>
<%
    HttpSession sessionUser = request.getSession(false);
    Account user = (sessionUser != null) ? (Account) sessionUser.getAttribute("user") : null;
    if (user == null) {
        response.sendRedirect("login.jsp");
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

        <!-- Google Font -->
        <link href="https://fonts.googleapis.com/css?family=Lora:400,700&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Cabin:400,500,600,700&display=swap" rel="stylesheet">

        <!-- Css Styles -->
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
        <link rel="stylesheet" href="css/profile.css" type="text/css">


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
                height: 60px;
                width: 100px;
                border-radius: 5%;
                background-color: black;
                color: white;
                border: 1px solid black;
                padding-left: 5px;
                margin: 3px;
            }
            .d-flex {
                display: flex;
                flex-direction: column;
                align-items: center;
            }

            .btn-custom {
                width: 80%;
                margin-bottom: 10px; 
            }

        </style>

        <title>Profile</title>
    </head>
    <body style="background: white">
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
                                        <li><a href="userList.jsp">User List</a></li>
                                        <li><a href="roomListForAdmin.jsp">Room List</a></li>
                                        <li><a href="serviceList.jsp">Service List</a></li>
                                        <li><a href="dashboard.jsp">Dashboard</a></li>
                                        <li class="active"><a href="profile.jsp">Profile</a></li>
                                    </ul>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </header>

        <div class="container mt-4 mb-4 p-3 d-flex justify-content-center" style="height: 90vh">
            <div class="card p-4">
                <div class="image d-flex flex-column justify-content-center align-items-center">
                    <h4 class="mt-3"><%= user.getUsername() %></h4>
                    <table class="table table-bordered mt-3">
                        <tr>
                            <th>Email</th>
                            <td><%= user.getEmail() %></td>
                        </tr>
                        <%
                            dao.AccountDAO accountDAO = new dao.AccountDAO();
                            model.Profile profile = accountDAO.getProfileByAccountId(user.getAccountID());
                            if (profile != null) {
                        %>
                        <tr>
                            <th>Full Name</th>
                            <td><%= profile.getName() %></td>
                        </tr>
                        <tr>
                            <th>Phone</th>
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
                        <% } else { %>
                        <tr>
                            <td colspan="2" class="text-danger text-center">Profile information not available.</td>
                        </tr>
                        <% } %>
                        <tr>
                            <th>Account Created Date</th>
                            <td><%= user.getCreatedDate() %></td>
                        </tr>
                    </table>
                    <div class="d-flex mt-2">
                        <button class="btn-custom btn-primary me-3 px-4 py-2" onclick="window.location.href = 'editProfile.jsp'">Edit Profile</button>
                        <button class="btn-custom btn-warning px-4 py-2" onclick="window.location.href = 'changePassword.jsp'">Change Password</button>
                        <button class="btn-custom btn-danger px-4 py-2" onclick="window.location.href = 'logout.jsp'">Sign Out</button>
                    </div>
                </div>
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
                                <p>We inspire and reach millions of travelers<br /> across 90 local websites</p>
                            </div>
                        </div>
                        <div class="col-lg-3 offset-lg-1">
                            <div class="ft-contact">
                                <h6>Contact Us</h6>
                                <ul>
                                    <li>(12) 345 67890</li>
                                    <li>hotelroombooking@gmail.com</li>
                                    <li>856 Cordia Extension Apt. 356, Lake, United State</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </footer>
    </body>
</html>
