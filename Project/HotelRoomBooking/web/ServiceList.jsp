<%-- 
    Document   : ServiceList
    Created on : Mar 15, 2025, 8:21:47 PM
    Author     : My PC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

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
                    <c:when test="${requestScope.serviceList.size() == 0}">
                        <label class="h2">Service List is Empty</label>
                    </c:when>    
                    <c:otherwise>
                        <div class="row">
                            <c:forEach items="${requestScope.serviceList}" var="s">
                                <div class="col-lg-4 col-md-6">
                                    <div class="room-item">
                                        <img src="${s.getImage()}" alt="">
                                        <div class="ri-text">
                                            <h4>${s.getServiceName()}</h4>
                                            <h3>${s.getPrice()}$</h3>
                                            <table>
                                                <tbody>
                                                    <tr>
                                                        <td class="r-o">Status:</td>
                                                        <td>${s.getStatus()}</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="r-o">Type:</td>
                                                        <td>${s.getType()}</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                            <a href="ServiceDetail?serviceID=${s.getServiceID()}" class="primary-btn link">More Details</a>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
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
