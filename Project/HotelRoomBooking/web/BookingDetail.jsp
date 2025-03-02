<%-- 
    Document   : BookingDetail
    Created on : Mar 1, 2025, 9:54:02 AM
    Author     : My PC
--%>

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
            
            <%Booking b = (Booking)request.getAttribute("booking");%>
            
            BookingID: <P><%=b.getBookingID()%></P><br>
            RoomID <P><%=b.getRoomID()%></P><br>
            BookingDate <P><%=b.getBookingDate()%></P><br>
            TotalAmount <P><%=b.getTotalAmount()%></P><br>
            Status: <P><%=b.getStatus()%></P><br>
        <br>
        <a href="BookingList">Return</a><br>
        <br>
            
            <footer>
            <jsp:include page="footer.html"></jsp:include>
        </footer>
    </body>
</html>
