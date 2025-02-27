<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="dao.RoomDAO" %>
<%@ page import="model.Room" %>

<%
    String roomIDStr = request.getParameter("roomID");
    if (roomIDStr == null || roomIDStr.isEmpty()) {
        response.sendRedirect("RoomListForAdmin.jsp");
        return;
    }

    int roomID = Integer.parseInt(roomIDStr);
    RoomDAO roomDAO = new RoomDAO();
    Room room = roomDAO.getRoomById(roomID);

    if (room == null) {
        response.sendRedirect("RoomListForAdmin.jsp");
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

        <title>Edit Room</title>
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
                                        <li><a href="RoomList">Rooms</a></li>
                                        <li><a href="./about-us.html">About Us</a></li>
                                        <li><a href="./pages.html">Pages</a></li>
                                        <li><a href="./blog.html">News</a></li>
                                        <li><a href="./contact.html">Contact</a></li>
                                    </ul>
                                </nav>
                                <div class="nav-right search-switch">
                                    <i class="icon_search"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </header>

        <div class="container mt-4 mb-4">
            <h3>Edit Room Information</h3>
            <form action="EditRoomServlet" method="post" enctype="multipart/form-data">
                <input type="hidden" name="roomID" value="<%= room.getRoomID() %>">

                <div class="form-group">
                    <label for="roomName">Room Name</label>
                    <input type="text" name="roomName" class="form-control" id="roomName" value="<%= room.getRoomName() %>" required>
                </div>

                <div class="form-group">
                    <label for="description">Description</label>
                    <textarea name="description" class="form-control" id="description" rows="3" required><%= room.getDescription() %></textarea>
                </div>

                <div class="form-group">
                    <label for="price">Price</label>
                    <input type="text" name="price" class="form-control" id="price" value="<%= room.getPrice() %>" required>
                </div>

                <div class="form-group">
                    <label for="type">Room Type</label>
                    <select name="type" class="form-control" id="type">
                        <option value="single" <%= room.getTypeRoom().equals("single") ? "selected" : "" %>>Single</option>
                        <option value="double" <%= room.getTypeRoom().equals("double") ? "selected" : "" %>>Double</option>
                        <option value="suite" <%= room.getTypeRoom().equals("suite") ? "selected" : "" %>>Suite</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="status">Room Status</label>
                    <select name="status" class="form-control" id="status">
                        <option value="available" <%= room.getStatusRoom().equals("available") ? "selected" : "" %>>Available</option>
                        <option value="occupied" <%= room.getStatusRoom().equals("occupied") ? "selected" : "" %>>Occupied</option>
                        <option value="maintenance" <%= room.getStatusRoom().equals("maintenance") ? "selected" : "" %>>Maintenance</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="roomImage">Upload Image (Optional)</label>
                    <input type="file" name="roomImage" class="form-control-file" id="roomImage">
                    <p>Current Image:</p>
                    <img src="<%= request.getContextPath() + room.getImage() %>" alt="Room Image" width="200" height="150">
                </div>

                <button type="submit" class="btn btn-primary">Update Room</button>
                <a href="RoomListForAdmin.jsp" class="btn btn-secondary">Cancel</a>
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
    </body>
</html>
