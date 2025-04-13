<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Write Feedback</title>

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
    </head>
    <body>
        <header>
            <jsp:include page="header_loggedIn.html"></jsp:include>
            </header>

        <%Room r = (Room)request.getAttribute("room");%>
        <%Booking b = (Booking)request.getAttribute("booking");%>

        <div class="formbold-main-wrapper" style="align-self: center; align-content: center">
            <div class="formbold-form-wrapper">
                <table class="table table-borderless" >

                    <% if (request.getAttribute("errorMessage") != null) { %>
                    <div class="alert alert-danger">
                        <%= request.getAttribute("errorMessage") %>
                    </div>
                    <% } %></tr>

                    <h2>Feedback</h2>
                    <form action="WriteFeedback" method="post">
                        <input type="hidden" name="roomID" value="<%=r.getRoomID()%>">
                        <input type="hidden" name="bookingID" value="<%=b.getBookingID()%>">

                        <tbody>

                        <div class="formbold-input-group">
                            <tr>
                                <td><label class="formbold-form-label">Room Name</label></td>
                                <td><%=r.getRoomName()%></td>
                            </tr>
                        </div>


                        <div class="formbold-input-group">
                            <tr>
                                <td><label class="formbold-form-label">Image</label></td>
                                <td><img src="<%= request.getContextPath() + r.getImage() %>" alt="" width="600" height="360"></td>
                            </tr>
                        </div>

                        <div class="formbold-input-group">
                            <tr>
                                <td><label class="formbold-form-label">Comment</label></td>
                                <td><textarea name="comment" id="comment" placeholder="Enter yorur comment" class="formbold-form-input" rows="3"></textarea></td>
                            </tr>
                        </div>

                        <div class="formbold-input-group">
                            <tr>
                                <td><label class="formbold-form-label">Rating</label></td>
                                <td>
                                    <input type="radio" name="rating" value="1" class="icon_star_alt">
                                    <input type="radio" name="rating" value="2" class="icon_star_alt">
                                    <input type="radio" name="rating" value="3" class="icon_star_alt">
                                    <input type="radio" name="rating" value="4" class="icon_star_alt">
                                    <input type="radio" name="rating" value="5" class="icon_star_alt">
                                </td>
                            </tr>
                        </div>

                        <div class="formbold-input-group">
                            <tr>
                                <td></td>
                                <td><input type="submit" value="Submit" class="formbold-btn"></td>
                            </tr>
                        </div>
                        </tbody>
                    </form>
                </table>
            </div>
        </div>

        <footer>
            <jsp:include page="footer.html"></jsp:include>
        </footer>
    </body>
</html>
