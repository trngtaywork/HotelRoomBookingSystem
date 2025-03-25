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
            <jsp:include page="header.html"></jsp:include>
            </header>
        <%--
        <c:forEach items="${requestScope.bookingList}" var="b">
                    <div>
                        <p>${b.getBookingID()}</p>
                        <p>${b.getProfileID()}</p>
                        <p>${b.getRoomID()}</p>
                        <p>${b.getBookingDate()}</p>
                        <p>${b.getTotalAmount()}</p>
                        <p>${b.getStatus()}</p>
                        <p><a href="BookingDetail?bookingID=${b.getBookingID()}">More Details</a></p>
                        <p></p>
                    </div>
                </c:forEach>
        --%>

        <div class="container">
            <div>
                <c:if test="${requestScope.error != null || !requestScope.error.isBlank()}">
                    <div class="container">
                        <label class="h2" style="color: red">${requestScope.error}</label>
                    </div>
                </c:if>
            </div>
            <form action="BookingListAdmin" method="post">
                <table class="table table-borderless">
                    <tr>
                        <td>From</td>
                        <td>To</td>
                        <td><%--Any Date--%></td>
                    </tr>
                    <tr>
                        <td><input type="date" name="fromDate"/></td>
                        <td><input type="date" name="toDate"/></td>
                        <td><%--<input type="checkbox" name="anyDate">--%></td>
                    </tr>
                    <tr>
                        <td>Search Field</td>
                        <td>Search Value</td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>
                            <select name="fieldList">
                                <option value="any">Any</option>
                                <option value="ProfileID">Profile ID</option>
                                <option value="Name">Customer Name</option>
                                <option value="RoomID">Room ID</option>
                                <option value="RoomName">Room Name</option>
                                <option value="TotalAmount">Total Amount</option>
                                <option value="Status">Status</option>
                            </select>
                        </td>
                        <td><input type="text" name="searchString" /></td>
                        <td><input type="submit" value="Search"></td>
                    </tr>
                </table>
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
                                    <th style="background-color: #dfa974">Status</th>
                                    <th style="background-color: #dfa974"></th>
                                    <th style="background-color: #dfa974"></th>
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
                                        <td>${b.getStatus()}</td>
                                        <td><a href="RoomDetailAdmin?roomID=${b.getRoomID()}" class="link">Room Detail</a></td>
                                        <td><a href="" class="link">Service Detail</a></td>
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
