<%-- 
    Document   : BookingDetail
    Created on : Mar 1, 2025, 9:54:02 AM
    Author     : My PC
--%>

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
        <title>Booking Detail</title>

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
            <jsp:include page="header_loggedIn.html"></jsp:include>
            </header>

        <%Booking b = (Booking)request.getAttribute("booking");%>
        <%Room r = (Room)request.getAttribute("room");%>
        <%BookingRoom br = (BookingRoom)request.getAttribute("bookingRoom");%>

        <section class="room-details-section spad">
            <div class="container">
                <div class="row">
                    <div class="col-lg-8">
                        <div class="room-details-item">
                            <div class="rd-text">
                                <table class="table table-borderless">
                                    <tbody>
                                        <tr>
                                            <td>Room Name:</td>
                                            <td><%=r.getRoomName()%></td>
                                            <td></td>
                                            <td>Booking Date:</td>
                                            <td><%=b.getBookingDate()%></td>
                                        </tr>
                                        <tr>
                                            <td>Start Time:</td>
                                            <td><%=br.getStartTime()%></td>
                                            <td></td>
                                            <td>End Time:</td>
                                            <td><%=br.getEndTime()%></td>
                                        </tr>
                                        <tr>
                                            <td>Quantity:</td>
                                            <td><%=br.getQuantity()%></td>
                                            <td></td>
                                            <td>Total Amount:</td>
                                            <td><%=b.getTotalAmount()%></td>
                                        </tr>
                                        <tr>
                                            <td>Status:</td>
                                            <td><%=b.getStatusBooking()%></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <footer>
            <jsp:include page="footer.html"></jsp:include>
        </footer>
    </body>
</html>
