<%-- 
    Document   : BookingList
    Created on : Mar 1, 2025, 9:52:37 AM
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
        <title>Booking List Admin</title>

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
    </head>
    <body>
        <header>
            <jsp:include page="headerAdmin.jsp"></jsp:include>
            </header>

        <div class="container">
            <div>
                <c:if test="${requestScope.error != null || !requestScope.error.isBlank()}">
                    <div class="container">
                        <label class="h2" style="color: red">${requestScope.error}</label>
                    </div>
                </c:if>
            </div>
            <hr>
            <form action="BookingListAdmin" method="post" class="row">
                <div class="col-md-3">
                    <label for="dateFilter">Booking Date</label>
                    <select name="dateFilter" id="DateFilter" class="form-control">
                        <option value="default">No Sorting</option>
                        <option value="asc">Low to High</option>
                        <option value="desc">High to Low</option>
                    </select>
                </div>

                <div class="col-md-3">
                    <label for="profileFilter">Profile:</label>
                    <input type="text" name="profileFilter" id="profileFilter" class="form-control" placeholder="Search by profile name">
                </div>

                <div class="col-md-3">
                    <label for="roomNameFilter">Room Name:</label>
                    <input type="text" name="roomNameFilter" id="roomNameFilter" class="form-control" placeholder="Search by room name">
                </div>

                <div class="col-md-3">
                    <input type="submit" name="Search" class="form-control">
                </div>
            </form>
        </div>
        <div>
            <c:choose>
                <c:when test="${requestScope.bookingList.size() == 0}">
                    <div class="container">
                        <label class="h2">Booking List is Empty</label>
                    </div>
                </c:when>    
                <c:otherwise>
                    <div class="container">
                        <table class="table table-bordered mt-3">
                            <thead>
                                <tr>
                                    <th style="background-color: #dfa974">BookingID</th>
                                    <th style="background-color: #dfa974">ProfileID</th>
                                    <th style="background-color: #dfa974">Profile</th>
                                    <th style="background-color: #dfa974">RoomID</th>
                                    <th style="background-color: #dfa974">Room</th>
                                    <th style="background-color: #dfa974">Booking Date</th>
                                    <th style="background-color: #dfa974">Total Amount</th>
                                    <th style="background-color: #dfa974">StatusBooking</th>
                                    <%--<th style="background-color: #dfa974"></th>--%>
                                    <%--<th style="background-color: #dfa974"></th>--%>
                                    <th style="background-color: #dfa974"></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${requestScope.bookingList}" var="b">
                                    <tr>
                                        <td>${b.getBookingID()}</td>
                                        <c:forEach items="${requestScope.profileList}" var="p">
                                            <c:if test="${b.getProfileID() == p.getProfileID()}">
                                                <td>${p.getProfileID()}</td>
                                            </c:if>
                                        </c:forEach>
                                        <c:forEach items="${requestScope.profileList}" var="p">
                                            <c:if test="${b.getProfileID() == p.getProfileID()}">
                                                <td>${p.getName()}</td>
                                            </c:if>
                                        </c:forEach>
                                        <c:forEach items="${requestScope.roomList}" var="r">
                                            <c:if test="${b.getRoomID() == r.getRoomID()}">
                                                <td>${r.getRoomID()}</td>
                                            </c:if>
                                        </c:forEach>
                                        <c:forEach items="${requestScope.roomList}" var="r">
                                            <c:if test="${b.getRoomID() == r.getRoomID()}">
                                                <td>${r.getRoomName()}</td>
                                            </c:if>
                                        </c:forEach>
                                        <td>${b.getBookingDate()}</td>
                                        <td>${b.getTotalAmount()}</td>
                                        <td>${b.getStatusBooking()}</td>
                                        <%--<td><a href="RoomDetailAdmin?roomID=${b.getRoomID()}" class="link">Room Detail</a></td>--%>
                                        <%--<td><a href="" class="link">Service Detail</a></td>--%>
                                        <td><a href="BookingDetailAdmin?bookingID=${b.getBookingID()}" class="link">More Details</a></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:otherwise>
            </c:choose>


        </div>

        <footer>
            <jsp:include page="footer.html"></jsp:include>
        </footer>
    </body>
</html>
