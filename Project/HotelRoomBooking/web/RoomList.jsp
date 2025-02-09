<%-- 
    Document   : RoomList
    Created on : Jan 31, 2025, 9:53:16 PM
    Author     : My PC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Room List</title>

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
        <%--<%var roomList = request.getAttribute("roomList");%>--%>
        <header>
            <jsp:include page="header.html"></jsp:include>
            </header>
            <!--
        <c:forEach items="${requestScope.roomList}" var="r">
            Room Name: <p>${r.getRoomName()}</p><br>
            Description: <P>${r.getDescription()}</P><br>
            Price <P>${r.getPrice()}</P><br>
            Image: <P>${r.getImage()}</P><br>
            Type: <P>${r.getTypeID()}</P><br>
            Status: <P>${r.getStatusID()}</P><br>
            <a href="RoomDetail?roomID=${r.getRoomID()}">Details</a><br>
            <br>
        </c:forEach>
        -->

        <section class="rooms-section spad">
            <div class="container">
                <div class="row">
                    <c:forEach items="${requestScope.roomList}" var="r">
                        <div class="col-lg-4 col-md-6">
                            <div class="room-item">
                                <img src="${r.getImage()}" alt="">
                                <div class="ri-text">
                                    <h4>${r.getRoomName()}</h4>
                                    <h3>${r.getPrice()}$<span>/Pernight</span></h3>
                                    <table>
                                        <tbody>
                                            <tr>
                                                <td class="r-o">Type:</td>
                                                <c:forEach items="${requestScope.typeList}" var="t">
                                                    <c:if test="${r.getTypeID() == t.getTypeID()}">
                                                        <td>${t.getTypeName()}</td>
                                                    </c:if>
                                                </c:forEach>
                                                <!--<td>${r.getTypeID()}</td>-->
                                            </tr>
                                            <tr>
                                                <td class="r-o">Status:</td>
                                                <c:forEach items="${requestScope.statusList}" var="s">
                                                    <c:if test="${r.getStatusID() == s.getStatusID()}">
                                                        <td>${s.getStatusName()}</td>
                                                    </c:if>
                                                </c:forEach>
                                                <!--<td>${r.getStatusID()}</td>-->
                                            </tr>
                                        </tbody>
                                    </table>
                                    <a href="RoomDetail?roomID=${r.getRoomID()}" class="primary-btn">More Details</a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </section>
        <footer>
            <jsp:include page="footer.html"></jsp:include>
        </footer>
    </body>
</html>
