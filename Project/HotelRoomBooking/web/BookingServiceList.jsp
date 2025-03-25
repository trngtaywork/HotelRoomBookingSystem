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

            <div>

            <c:choose>
                <c:when test="${requestScope.bookingList.size() == 0}">
                    <div class="container">
                        <label class="h2">Booking Service List is Empty</label>
                    </div>
                </c:when>    
                <c:otherwise>
                    <div class="container">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Room</th>
                                    <th>Booking Date</th>
                                    <th>Service</th>
                                    <th>Amount</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${requestScope.bookingList}" var="b">
                                    <c:forEach items="${requestScope.bookingServiceList}" var="bs">
                                        <c:if test="${bs.getBookingID() == b.getBookingID()}">
                                            <tr>
                                                <c:forEach items="${requestScope.roomList}" var="r">
                                                    <c:if test="${b.getRoomID() == r.getRoomID()}">
                                                        <td>${r.getRoomName()}</td>
                                                    </c:if>
                                                </c:forEach>

                                                <td>${b.getBookingDate()}</td>

                                                <c:forEach items="${requestScope.serviceList}" var="s">
                                                    <c:if test="${bs.getServiceID() == s.getServiceID()}">
                                                        <c:when test="${s.getServiceName() == null}">
                                                            <td>Booking Service is Empty</td>
                                                        </c:when>    
                                                        <c:otherwise>
                                                            <td>${s.getServiceName()}</td>
                                                        </c:otherwise>
                                                    </c:if>
                                                </c:forEach>

                                                <c:forEach items="${requestScope.serviceList}" var="s">
                                                    <c:if test="${bs.getServiceID() == s.getServiceID()}">
                                                        <c:when test="${s.getAmount() == null}">
                                                            <td>0</td>
                                                        </c:when>    
                                                        <c:otherwise>
                                                            <td>${bs.getAmount()}</td>
                                                        </c:otherwise>
                                                    </c:if>
                                                </c:forEach>

                                                <c:forEach items="${requestScope.serviceList}" var="s">
                                                    <c:if test="${bs.getServiceID() == s.getServiceID()}">
                                                        <c:when test="${s.getBookingServiceID() == null}">
                                                            <td></td>
                                                        </c:when>    
                                                        <c:otherwise>
                                                            <td><a class="link" href="BookingServiceDetail?bookingServiceID=${bs.getBookingServiceID()}">More Details</a></td>
                                                        </c:otherwise>
                                                    </c:if>
                                                </c:forEach>

                                            </tr> 
                                        </c:if>
                                    </c:forEach>
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
