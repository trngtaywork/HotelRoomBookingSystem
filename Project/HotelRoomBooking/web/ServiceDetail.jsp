<%-- 
    Document   : ServiceDetail
    Created on : Mar 15, 2025, 8:22:24 PM
    Author     : My PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Service Detail</title>

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
        <%
            HttpSession sessionUser = request.getSession(false);
            Account user = (sessionUser != null) ? (Account) sessionUser.getAttribute("user") : null;
            if (user == null) {
        %>
        <header>
            <jsp:include page="header.html"></jsp:include>
            </header>
        <%
    }else{%><header>
            <jsp:include page="header_loggedIn.html"></jsp:include>
        </header><%}
        %>

        <%Service s = (Service)request.getAttribute("service");%>

        <section class="room-details-section spad">
            <div class="container">
                <div class="row">
                    <div class="col-lg-8">
                        <div class="room-details-item">
                            <img src="<%= request.getContextPath() + s.getImage() %>" alt="" width="800" height="480">
                            <div class="rd-text">
                                <div class="rd-title">
                                    <h3><%=s.getServiceName()%></h3>
                                    <div class="rdt-right">
                                        <a href="AddBookingService?serviceID=<%=s.getServiceID()%>">Order Now</a>
                                    </div>
                                </div>
                                <h2><%=s.getPrice()%>$</h2>
                                <table>
                                    <tbody>
                                        <tr>
                                            <td class="r-o">Status:</td>
                                            <td><%=s.getStatusService()%></td>
                                        </tr>
                                        <tr>
                                            <td class="r-o">Type:</td>
                                            <td><%=s.getTypeService()%></td>
                                        </tr>
                                    </tbody>
                                </table>
                                <p class="f-para">
                                    <%=s.getDescription()%>
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
