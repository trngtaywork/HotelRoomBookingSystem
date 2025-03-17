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

        <%Service s = (Service)request.getAttribute("service");%>

        <section class="room-details-section spad">
            <div class="container">
                <div class="row">
                    <div class="col-lg-8">
                        <div class="room-details-item">
                            <img src="<%=s.getImage()%>" alt="">
                            <div class="rd-text">
                                <div class="rd-title">
                                    <h3><%=s.getServiceName()%></h3>
                                    <div class="rdt-right">
                                        <a href="#">Order Now</a>
                                    </div>
                                </div>
                                <h2><%=s.getPrice()%>$</h2>
                                <table>
                                    <tbody>
                                        <tr>
                                            <td class="r-o">Status:</td>
                                            <td><%=s.getStatus()%></td>
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
