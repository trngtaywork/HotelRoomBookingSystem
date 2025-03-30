<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="model.Room" %>
<%@ page import="dao.RoomDAO" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>

<%
    String priceFilter = request.getParameter("priceFilter");
    String statusFilter = request.getParameter("statusFilter");
    String typeFilter = request.getParameter("typeFilter");
    String roomNameFilter = request.getParameter("roomNameFilter");

    if (priceFilter == null) {
        priceFilter = "All";
    }
    if (statusFilter == null) {
        statusFilter = "All";
    }
    if (typeFilter == null) {
        typeFilter = "All";
    }
    if (roomNameFilter == null) {
        roomNameFilter = "";
    }

    RoomDAO roomDAO = new RoomDAO();
    List<Room> roomList = roomDAO.getFilteredRooms(priceFilter, statusFilter, typeFilter, roomNameFilter);

    int pageSize = 10;  // Số phòng mỗi trang
    int totalItems = roomList.size();  // Tổng số phòng
    int totalPages = (int) Math.ceil((double) totalItems / pageSize); // Tính tổng số trang
    
    int currentPage = 1;
    if (request.getParameter("page") != null) {
        currentPage = Integer.parseInt(request.getParameter("page"));
    }

    int startIndex = (currentPage - 1) * pageSize;
    int endIndex = Math.min(startIndex + pageSize, totalItems);
    List<Room> paginatedRoomList = roomList.subList(startIndex, endIndex);
%>

<!DOCTYPE html>
<html lang="en">
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

        <title>Room List For Admin</title>
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

            .pagination {
                display: flex;
                justify-content: center;
                margin-top: 20px;
            }

            .pagination a {
                padding: 8px 16px;
                margin: 0 5px;
                border-radius: 5px;
                text-decoration: none;
                color: #fff;
                background-color: #000;
                transition: background-color 0.3s ease;
            }

            .pagination a:hover {
                background-color: #dfa974;
            }

            .pagination a.active {
                background-color: #dfa974;
                pointer-events: none;
            }

            .pagination a.disabled {
                background-color: #d6d6d6;
                pointer-events: none;
            }

            .pagination span {
                padding: 8px 16px;
                margin: 0 5px;
                font-size: 16px;
                color: #333;
            }

            .pagination .disabled {
                color: #ccc;
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
                                        <li><a href="userList.jsp">User List</a></li>
                                        <li ><a href="roomListForAdmin.jsp">Room List</a></li>
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

        <div class="container">
            <h3>Room List For Admin</h3>
            <button class="btn-custom1 btn-primary" onclick="window.location.href = 'addRoom.jsp'">Add New Room</button>
            <hr>
            <form action="roomListForAdmin.jsp" method="get" class="row">
                <div class="col-md-3">
                    <label for="priceFilter">Filter by Price:</label>
                    <select name="priceFilter" class="form-control" onchange="this.form.submit()">
                        <option value="All" <%= "All".equals(priceFilter) ? "selected" : "" %>>No Sorting</option>
                        <option value="asc" <%= "asc".equals(priceFilter) ? "selected" : "" %>>Low to High</option>
                        <option value="desc" <%= "desc".equals(priceFilter) ? "selected" : "" %>>High to Low</option>
                    </select>
                </div>
                <div class="col-md-3">
                    <label for="statusFilter">Filter by Status:</label>
                    <select name="statusFilter" class="form-control" onchange="this.form.submit()">
                        <option value="All" <%= "All".equals(statusFilter) ? "selected" : "" %>>All</option>
                        <option value="available" <%= "available".equals(statusFilter) ? "selected" : "" %>>Available</option>
                        <option value="occupied" <%= "occupied".equals(statusFilter) ? "selected" : "" %>>Occupied</option>
                        <option value="maintenance" <%= "maintenance".equals(statusFilter) ? "selected" : "" %>>Maintenance</option>
                    </select>
                </div>
                <div class="col-md-3">
                    <label for="typeFilter">Filter by Type:</label>
                    <select name="typeFilter" class="form-control" onchange="this.form.submit()">
                        <option value="All" <%= "All".equals(typeFilter) ? "selected" : "" %>>All</option>
                        <option value="single" <%= "single".equals(typeFilter) ? "selected" : "" %>>Single</option>
                        <option value="double" <%= "double".equals(typeFilter) ? "selected" : "" %>>Double</option>
                        <option value="suite" <%= "suite".equals(typeFilter) ? "selected" : "" %>>Suite</option>
                    </select>
                </div>
                <div class="col-md-3">
                    <label for="roomNameFilter">Search by Room Name:</label>
                    <input type="text" name="roomNameFilter" class="form-control" placeholder="Search by name" 
                           value="<%= roomNameFilter %>" onkeydown="if (event.keyCode == 13) {
                       this.form.submit();
                   }">
                </div>

            </form>
            <br/>

            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Room Name</th>
                        <th>Image</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Status</th>
                        <th>Type</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        if (paginatedRoomList != null && !paginatedRoomList.isEmpty()) {
                            for (Room room : paginatedRoomList) {
                    %>
                    <tr>
                        <td><%= room.getRoomName() %></td>
                        <td><img src="<%= request.getContextPath() + room.getImage() %>" alt="" width="300" height="120"></td>
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
                        <td colspan="6" class="text-center">No rooms available</td>
                    </tr>
                    <% 
                        }
                    %>
                </tbody>
            </table>

            <div class="pagination">
                <% if (currentPage > 1) { %>
                <a href="roomListForAdmin.jsp?page=<%= currentPage - 1 %>">Previous</a>
                <% } %>
                <span>Page <%= currentPage %> of <%= totalPages %></span>
                <% if (currentPage < totalPages) { %>
                <a href="roomListForAdmin.jsp?page=<%= currentPage + 1 %>">Next</a>
                <% } %>
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