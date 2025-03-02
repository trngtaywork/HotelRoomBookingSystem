<%-- 
    Document   : BookingList
    Created on : Mar 1, 2025, 9:52:37 AM
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
    </head>
    <body>
        <header>
            <jsp:include page="header.html"></jsp:include>
            </header>
            
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
            
            <footer>
            <jsp:include page="footer.html"></jsp:include>
        </footer>
    </body>
</html>
