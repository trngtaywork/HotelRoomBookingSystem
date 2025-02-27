<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="model.Account" %>
<%@ page import="dao.RoomDAO" %>
<%@ page import="model.Room" %>

<%
    HttpSession sessionUser = request.getSession(false);
    Account user = (sessionUser != null) ? (Account) sessionUser.getAttribute("user") : null;
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    // Fetch all rooms
    RoomDAO roomDAO = new RoomDAO();
    List<Room> roomList = roomDAO.getAll();
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
            .btn-custom1 {
                height: 70px;
                width: 240px;
                border-radius: 5%;
                background-color: black;
                color: white;
                border: 1px solid black;
                padding-left: 5px;
            }
            .btn-custom {
                height: 30px;
                width: 70px;
                border-radius: 5%;
                background-color: black;
                color: white;
                border: 1px solid black;
                padding-left: 5px;
            }
        </style>

        <title>Room List For Admin</title>
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

        <!-- Room List Table -->
        <div class="container mt-4 mb-4 p-3">
            <%
    HttpSession session = request.getSession(false);
    String successMessage = (session != null) ? (String) session.getAttribute("successMessage") : null;
    if (successMessage != null) {
        session.removeAttribute("successMessage"); // Clear the message after displaying
            %>
            <div class="alert alert-success">
                <%= successMessage %>
            </div>
            <% } %>
            <h3>Room List For Admin</h3>
            <button class="btn-custom1 btn-primary" onclick="window.location.href = 'addRoom.jsp'">Add New Room</button>
            <table class="table table-bordered mt-3">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Image</th>
                        <th>Room Name</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Status Room</th>
                        <th>Type Room</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        if (roomList != null && !roomList.isEmpty()) {
                            for (Room room : roomList) { 
                    %>
                    <tr>
                        <td><%= room.getRoomID() %></td>
                        <td><img src="<%= request.getContextPath() + room.getImage() %>" width="100" height="100"></td>
                        <td><%= room.getRoomName() %></td>
                        <td><%= room.getDescription() %></td>
                        <td><%= room.getPrice() %></td>
                        <td><%= room.getStatusRoom() %></td>
                        <td><%= room.getTypeRoom() %></td>
                        <td>
                            <button class="btn-custom btn-primary" onclick="window.location.href = 'editRoom.jsp?roomID=<%= room.getRoomID() %>'">Edit</button>
                            <button class="btn-custom btn-danger" onclick="window.location.href = 'deleteRoom.jsp?roomID=<%= room.getRoomID() %>'">Delete</button>
                        </td>
                    </tr>
                    <% 
                            }
                        } else {
                    %>
                    <tr>
                        <td colspan="8" class="text-center">No rooms available</td>
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
            <script>
function deleteRoom(roomID) {
                    if (confirm("Are you sure you want to delete this room?")) {
            window.location.href = "DeleteRoomServlet?roomID=" + roomID;
            }
            ;
        };
    </script>
    </script>
</body>
</html>
