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
        <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
        <title>Edit Room</title>
        <style>
            .form-group {
                margin-bottom: 20px;
            }
        </style>
    </head>
    <body>
        <div class="container mt-4 mb-4">
            <h3>Edit Room</h3>

            <!-- Form for editing room -->
            <form action="EditRoomServlet" method="post" enctype="multipart/form-data">
                <input type="hidden" name="roomID" value="<%= room.getRoomID() %>">

                <div class="form-group">
                    <label for="roomName">Room Name</label>
                    <input type="text" name="roomName" id="roomName" class="form-control" value="<%= room.getRoomName() %>" required>
                </div>

                <div class="form-group">
                    <label for="description">Description</label>
                    <textarea name="description" id="description" class="form-control" rows="3" required><%= room.getDescription() %></textarea>
                </div>

                <div class="form-group">
                    <label for="price">Price</label>
                    <input type="text" name="price" id="price" class="form-control" value="<%= room.getPrice() %>" required>
                </div>

                <div class="form-group">
                    <label for="type">Room Type</label>
                    <select name="type" id="type" class="form-control" required>
                        <option value="single" <%= "single".equals(room.getTypeRoom()) ? "selected" : "" %>>Single</option>
                        <option value="double" <%= "double".equals(room.getTypeRoom()) ? "selected" : "" %>>Double</option>
                        <option value="suite" <%= "suite".equals(room.getTypeRoom()) ? "selected" : "" %>>Suite</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="status">Status</label>
                    <select name="status" id="status" class="form-control" required>
                        <option value="available" <%= "available".equals(room.getStatusRoom()) ? "selected" : "" %>>Available</option>
                        <option value="occupied" <%= "occupied".equals(room.getStatusRoom()) ? "selected" : "" %>>Occupied</option>
                        <option value="maintenance" <%= "maintenance".equals(room.getStatusRoom()) ? "selected" : "" %>>Maintenance</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="roomImage">Room Image (Optional)</label>
                    <input type="file" name="roomImage" class="form-control">
                    <p>Current Image: <img src="<%= room.getImage() %>" width="100" height="100"></p>
                </div>

                <button type="submit" class="btn btn-primary">Update Room</button>
            </form>
        </div>
    </body>
</html>
