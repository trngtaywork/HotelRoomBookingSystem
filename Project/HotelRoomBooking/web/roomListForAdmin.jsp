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
                background-color: black;
                color: white;
                border: 1px solid black;
                padding-left: 5px;
            }
            .btn-custom {
                height: 35px;
                width: 70px;
                border-radius: 5%;
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

        <!-- Room List Table -->
        <div class="container mt-4 mb-4 p-3">
            <h3>Room List For Admin</h3>

            <button class="btn-custom1 btn-primary" onclick="window.location.href = 'addRoom.jsp'">Add New Room</button>
            <hr>
            <form action="RoomListForAdminServlet" method="get" class="row">
                <div class="col-md-3">
                    <label for="priceFilter">Price:</label>
                    <select name="priceFilter" id="priceFilter" class="form-control">
                        <option value="asc">Low to High</option>
                        <option value="desc">High to Low</option>
                    </select>
                </div>

                <div class="col-md-3">
                    <label for="statusFilter">Status:</label>
                    <select name="statusFilter" id="statusFilter" class="form-control">
                        <option value="all">All</option>
                        <option value="available">Available</option>
                        <option value="occupied">Occupied</option>
                        <option value="maintenance">Maintenance</option>
                    </select>
                </div>

                <div class="col-md-3">
                    <label for="typeFilter">Type:</label>
                    <select name="typeFilter" id="typeFilter" class="form-control">
                        <option value="all">All</option>
                        <option value="single">Single</option>
                        <option value="double">Double</option>
                        <option value="suite">Suite</option>
                    </select>
                </div>

                <div class="col-md-3">
                    <label for="roomNameFilter">Room Name:</label>
                    <input type="text" name="roomNameFilter" id="roomNameFilter" class="form-control" placeholder="Search by name">
                </div>
            </form>
            <table class="table table-bordered mt-3">
                <thead>
                    <tr>
                        <th style="background-color: #dfa974">Room Name</th>
                        <th style="background-color: #dfa974">Image</th>
                        <th style="background-color: #dfa974">Description</th>
                        <th style="background-color: #dfa974">Price</th>
                        <th style="background-color: #dfa974">Status Room</th>
                        <th style="background-color: #dfa974">Type Room</th>
                        <th style="background-color: #dfa974; width: 170px">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        if (roomList != null && !roomList.isEmpty()) {
                            for (Room room : roomList) { 
                    %>
                    <tr>
                        <td><%= room.getRoomName() %></td>
                        <td><img src="<%= request.getContextPath() + room.getImage() %>" width="150" height="100"></td>                       
                        <td><%= room.getDescription() %></td>
                        <td><%= room.getPrice() %></td>
                        <td><%= room.getStatusRoom() %></td>
                        <td><%= room.getTypeRoom() %></td>
                        <td>
                            <button class="btn-custom btn-info" onclick="window.location.href = 'editRoom.jsp?roomID=<%= room.getRoomID() %>'">Edit</button>
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
            function deleteRoom(roomID) {
                if (confirm("Are you sure you want to delete this room?")) {
                    window.location.href = "DeleteRoomServlet?roomID=" + roomID;
                }
                ;
            }
            ;
        </script>
    </body>
</html>