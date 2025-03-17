<%-- 
    Document   : RoomList
    Created on : Jan 31, 2025, 9:53:16 PM
    Author     : My PC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.*" %>

<%--
<%
    HttpSession sessionUser = request.getSession(false);
    Account user = (sessionUser != null) ? (Account) sessionUser.getAttribute("user") : null;
    if (user == null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>
--%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Room List Admin</title>

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
    </head>
    <body>
        <header>
            <jsp:include page="header.html"></jsp:include>
            </header>

            <section class="rooms-section spad">
                <div class="container">
                <c:choose>
                    <c:when test="${requestScope.roomList.size() == 0}">
                        <label class="h2">Room List is Empty</label>
                    </c:when>    
                    <c:otherwise>
                        <div class="container mt-4">
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th>Room ID</th>
                                        <th>Room Name</th>
                                        <th>Price</th>
                                        <th>Type</th>
                                        <th>Status</th>
                                        <th style="width: 300px">Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${requestScope.roomList}" var="r">
                                        <tr>
                                            <td>${r.getRoomID()}</td>
                                            <td>${r.getRoomName()}</td>
                                            <td>${r.getPrice()}</td>
                                            <td>${r.getType()}</td>
                                            <td>${r.getStatus()}</td>
                                            <td>
                                                <button class="btn-custom btn-primary" onclick="window.location.href = 'editUser.jsp?accountID=<%= acc.getAccountID() %>'">Edit</button>
                                                <button class="btn-custom btn-danger" onclick="window.location.href = 'deleteUser.jsp?accountID=<%= acc.getAccountID() %>'">Delete</button>
                                                <button class="btn-custom btn-info" onclick="window.location.href = 'profileDetails.jsp?accountID=<%= acc.getAccountID() %>'">Profile</button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>

                            </table>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </section>

        <footer>
            <jsp:include page="footer.html"></jsp:include>
        </footer>
    </body>
</html>
