<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="dao.RoomDAO" %>
<%@ page import="model.Room" %>

<%
    String roomIDStr = request.getParameter("roomID");
    if (roomIDStr == null || roomIDStr.isEmpty()) {
        response.sendRedirect("roomListForAdmin.jsp");
        return;
    }

    int roomID = Integer.parseInt(roomIDStr);
    RoomDAO roomDAO = new RoomDAO();
    Room room = roomDAO.getRoomById(roomID);

    if (room == null) {
        response.sendRedirect("roomListForAdmin.jsp");
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
            .btn-custom {
                height: 70px;
                width: 140px;
                border-radius: 5%;
                background-color: black;
                color: white;
                border: 1px solid black;
                padding-left: 5px;
            }
        </style>
        <title>Edit User</title>
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

        <div class="container mt-4 mb-4">
            <h3>Edit Room</h3>

            <form action="EditRoomServlet" method="post" enctype="multipart/form-data">
                <input type="hidden" name="roomID" value="<%= room.getRoomID() %>">

                <div class="form-group">
                    <label for="roomName">Room Name</label>
                    <input type="text" name="roomName" id="roomName" class="form-control" 
                           value="<%= request.getAttribute("roomName") != null ? request.getAttribute("roomName") : room.getRoomName() %>" required>
                    <% if (request.getAttribute("roomNameError") != null) { %>
                    <span class="text-danger"><%= request.getAttribute("roomNameError") %></span>
                    <% } %>
                </div>

                <div class="form-group">
                    <label for="description">Description</label>
                    <textarea name="description" id="description" class="form-control" rows="3" required><%= room.getDescription() %></textarea>
                    <% if (request.getAttribute("descriptionError") != null) { %>
                    <span class="text-danger"><%= request.getAttribute("descriptionError") %></span>
                    <% } %>
                </div>

                <div class="form-group">
                    <label for="price">Price</label>
                    <input type="text" name="price" id="price" class="form-control" 
                           value="<%= request.getAttribute("price") != null ? request.getAttribute("price") : room.getPrice() %>" required>
                    <% if (request.getAttribute("priceError") != null) { %>
                    <span class="text-danger"><%= request.getAttribute("priceError") %></span>
                    <% } %>
                </div>

                <div class="form-group">
                    <label for="type">Room Type</label>
                    <select name="type" id="type" class="form-control" required>
                        <option value="Single" <%= "Single".equals(room.getTypeRoom()) ? "selected" : "" %>>Single</option>
                        <option value="Double" <%= "Double".equals(room.getTypeRoom()) ? "selected" : "" %>>Double</option>
                        <option value="Suite" <%= "Suite".equals(room.getTypeRoom()) ? "selected" : "" %>>Suite</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="status">Status</label>
                    <select name="status" id="status" class="form-control" required>
                        <option value="Available" <%= "Available".equals(room.getStatusRoom()) ? "selected" : "" %>>Available</option>
                        <option value="Occupied" <%= "Occupied".equals(room.getStatusRoom()) ? "selected" : "" %>>Occupied</option>
                        <option value="Maintenance" <%= "Maintenance".equals(room.getStatusRoom()) ? "selected" : "" %>>Maintenance</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="roomImage">Room Image (Optional)</label>
                    <input type="file" name="roomImage" class="form-control">
                    <% if (request.getAttribute("imageError") != null) { %>
                    <span class="text-danger"><%= request.getAttribute("imageError") %></span>
                    <% } %>
                </div>

                <div class="form-group">
                    <label for="roomImage">Current Image</label>
                    <br>
                    <img src="<%= request.getContextPath() + room.getImage() %>" alt="Room Image" width="200" height="150">
                </div>

                <button type="submit" class="btn btn-primary">Update Room</button>
                <button type="button" class="btn btn-danger" onclick="window.location.href = 'roomListForAdmin.jsp'">Cancel</button>
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
