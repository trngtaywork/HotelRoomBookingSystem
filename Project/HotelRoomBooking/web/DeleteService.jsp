<%-- 
    Document   : DeleteService
    Created on : Mar 15, 2025, 8:24:52 PM
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
            <jsp:include page="headerAdmin.jsp"></jsp:include>
        </header>
        
        <%Service s = (Service)request.getAttribute("service");%>
        
        <div class="container mt-4 mb-4">
            <h3>Are you sure you want to delete this service?</h3>
            <table class="table table-bordered mt-3">
                <tr>
                    <th>ID</th>
                    <td><%=s.getServiceID()%></td>
                </tr>
                <tr>
                    <th>Image</th>
                    <td>
                        <img src="<%= s.getImage() %>" alt="Service Image" width="200" height="150">
                    </td>
                </tr>
                <tr>
                    <th>Service Name</th>
                    <td><%=s.getServiceName()%></td>
                </tr>
                <tr>
                    <th>Description</th>
                    <td><%=s.getDescription()%></td>
                </tr>
                <tr>
                    <th>Price</th>
                    <td><%=s.getPrice()%></td>
                </tr>
                <tr>
                    <th>Status</th>
                    <td><%=s.getStatus()%></td>
                </tr>
                <tr>
                    <th>Type</th>
                    <td><%=s.getType()%></td>
                </tr>
            </table>

            <form action="DeleteService" method="post">
                <input type="hidden" name="serviceID" value="<%=s.getServiceID()%>">
                <button type="submit" class="btn btn-danger">Confirm Delete</button>
                <a href="ServiceListAdmin" class="btn btn-secondary">Cancel</a>
            </form>
        </div>
        
        <footer>
            <jsp:include page="footerAdmin.jsp"></jsp:include>
        </footer>
    </body>
</html>
