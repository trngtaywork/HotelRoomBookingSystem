<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Booking Service Detail Admin</title>
        
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
            <jsp:include page="headerAdmin.jsp"></jsp:include>
        </header>

        <%Booking b = (Booking)request.getAttribute("booking");%>
        <%Room r = (Room)request.getAttribute("room");%>
        <%Service s = (Service)request.getAttribute("service");%>
        <%Account a = (Account)request.getAttribute("account");%>
        <%Profile p = (Profile)request.getAttribute("profile");%>
        <%BookingService bs = (BookingService)request.getAttribute("bookingService");%>

        <section class="room-details-section spad">
            <div class="container">
                <div class="row">
                    <div class="col-lg-8">
                        <div class="room-details-item">
                            <div class="rd-text">
                                <table class="table table-bordered">
                                    <tbody>
                                        <tr>
                                            <td>Customer ID</td>
                                            <td>Customer Username</td>
                                            <td>Customer Full Name</td>
                                            <td>Gender</td>
                                            <td>Email</td>
                                            <td>Phone</td>
                                            <td>Address</td>
                                        </tr>
                                        <tr>
                                            <td><%=a.getAccountID()%></td>
                                            <td><%=a.getUsername()%></td>
                                            <td><%=p.getName()%></td>
                                            <td><%=p.getGender()%></td>
                                            <td><%=a.getEmail()%></td>
                                            <td><%=p.getPhoneNumber()%></td>
                                            <td><%=p.getAddress()%></td>
                                        </tr>
                                    </tbody>
                                </table>
                                <br>
                                <table class="table table-borderless">
                                    <tbody>
                                        <tr>
                                            <td>BookingID:</td>
                                            <td><%=b.getBookingID()%></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td>Room ID:</td>
                                            <td><%=r.getRoomID()%></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td>Room Name:</td>
                                            <td><%=r.getRoomName()%></td>
                                            <td></td>
                                            <td>Booking Date:</td>
                                            <td><%=b.getBookingDate()%></td>
                                        </tr>
                                        <tr>
                                            <td>Service ID:</td>
                                            <td><%=s.getServiceID()%></td>
                                            <td></td>
                                            <td>Service Name:</td>
                                            <td><%=s.getServiceName()%></td>
                                        </tr>
                                        <tr>
                                            <td>Start Time:</td>
                                            <td><%=bs.getStartTime()%></td>
                                            <td></td>
                                            <td>End Time:</td>
                                            <td><%=bs.getEndTime()%></td>
                                        </tr>
                                        <tr>
                                            <td>Amount:</td>
                                            <td><%=bs.getAmount()%></td>
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
