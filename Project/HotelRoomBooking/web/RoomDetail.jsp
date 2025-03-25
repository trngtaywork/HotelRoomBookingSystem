<%-- 
    Document   : RoomDetail
    Created on : Jan 31, 2025, 9:53:25 PM
    Author     : My PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Room Detail</title>

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

        <%Room r = (Room)request.getAttribute("room");%>

        <section class="room-details-section spad">
            <div class="container">
                <div class="row">
                    <div class="col-lg-8">
                        <div class="room-details-item">
                            <img src="<%=r.getImage()%>" alt="">
                            <div class="rd-text">
                                <div class="rd-title">
                                    <h3><%=r.getRoomName()%></h3>
                                    <div class="rdt-right">
                                        <a href="AddBooking?roomID=<%=r.getRoomID()%>">Booking Now</a>
                                    </div>
                                </div>
                                <h2><%=r.getPrice()%>$<span>/Pernight</span></h2>
                                <table>
                                    <tbody>
                                        <tr>
                                            <td class="r-o">Type:</td>
                                            <td><%=r.getTypeRoom()%></td>
                                        </tr>
                                        <tr>
                                            <td class="r-o">Status:</td>
                                            <td><%=r.getStatusRoom()%></td>
                                        </tr>
                                    </tbody>
                                </table>
                                <p class="f-para">
                                    <%=r.getDescription()%>
                                </p>
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
