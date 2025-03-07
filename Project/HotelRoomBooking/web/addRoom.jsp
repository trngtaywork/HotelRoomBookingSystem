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
        <link rel="stylesheet" href="css/room.css" type="text/css">

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

        <title>Add Room</title>
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
                                        <li><a href="userList.jsp">User List</a></li>
                                        <li class="active"><a href="roomListForAdmin.jsp">Room List</a></li>
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

        <div class="formbold-main-wrapper">
            <div class="formbold-form-wrapper">
                <h2>Add Room</h2>
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

                <form id="addRoomForm" action="AddRoomServlet" method="post" enctype="multipart/form-data" onsubmit="return validateForm()">
                    <div class="formbold-input-group">
                        <label class="formbold-form-label">Room Name</label>
                        <input type="text" name="roomName" id="roomName" placeholder="Enter room name" class="formbold-form-input" value="<%= request.getAttribute("roomName") != null ? request.getAttribute("roomName") : "" %>">
                        <span class="error-message" id="roomNameError"><%= request.getAttribute("roomNameError") != null ? request.getAttribute("roomNameError") : "" %></span>
                    </div>

                    <div class="formbold-input-group">
                        <label class="formbold-form-label">Description</label>
                        <textarea name="description" id="description" placeholder="Enter room description" class="formbold-form-input" rows="3"><%= request.getAttribute("description") != null ? request.getAttribute("description") : "" %></textarea>
                        <span class="error-message" id="descriptionError"><%= request.getAttribute("descriptionError") != null ? request.getAttribute("descriptionError") : "" %></span>
                    </div>

                    <div class="formbold-input-group">
                        <label class="formbold-form-label">Price</label>
                        <input type="text" name="price" id="price" placeholder="Enter price" class="formbold-form-input" value="<%= request.getAttribute("price") != null ? request.getAttribute("price") : "" %>">
                        <span class="error-message" id="priceError"><%= request.getAttribute("priceError") != null ? request.getAttribute("priceError") : "" %></span>
                    </div>

                    <div class="formbold-input-group">
                        <label class="formbold-form-label">Room Type</label>
                        <select name="type" id="type" class="formbold-form-select">
                            <option value="single" <%= "single".equals(request.getAttribute("type")) ? "selected" : "" %>>Single</option>
                            <option value="double" <%= "double".equals(request.getAttribute("type")) ? "selected" : "" %>>Double</option>
                            <option value="suite" <%= "suite".equals(request.getAttribute("type")) ? "selected" : "" %>>Suite</option>
                        </select>
                        <span class="error-message" id="typeError"><%= request.getAttribute("typeError") != null ? request.getAttribute("typeError") : "" %></span>
                    </div>

                    <div class="formbold-input-group">
                        <label class="formbold-form-label">Status</label>
                        <select name="status" id="status" class="formbold-form-select">
                            <option value="available" <%= "available".equals(request.getAttribute("status")) ? "selected" : "" %>>Available</option>
                            <option value="occupied" <%= "occupied".equals(request.getAttribute("status")) ? "selected" : "" %>>Occupied</option>
                            <option value="maintenance" <%= "maintenance".equals(request.getAttribute("status")) ? "selected" : "" %>>Maintenance</option>
                        </select>
                        <span class="error-message" id="statusError"><%= request.getAttribute("statusError") != null ? request.getAttribute("statusError") : "" %></span>
                    </div>

                    <div class="formbold-input-group">
                        <label class="formbold-form-label">Upload Image</label>
                        <input type="file" name="roomImage" class="formbold-form-input">
                        <span class="error-message" id="imageError"><%= request.getAttribute("imageError") != null ? request.getAttribute("imageError") : "" %></span>
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

    </body>
</html>
