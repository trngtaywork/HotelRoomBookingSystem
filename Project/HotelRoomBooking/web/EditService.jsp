<%-- 
    Document   : EditService
    Created on : Mar 15, 2025, 8:24:36 PM
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
            <h3>Edit Service</h3>

            <% if (request.getAttribute("errorMessage") != null) { %>
                    <span class="text-danger"><%= request.getAttribute("errorMessage") %></span>
            <% } %>
            
            <form action="EditRoom" method="post" enctype="multipart/form-data">
                <input type="hidden" name="serviceID" value="<%=s.getServiceID()%>">

                <div class="form-group">
                    <label for="serviceName">Service Name</label>
                    <input type="text" name="serviceName" id="serviceName" class="form-control" 
                           value="<%= s.getServiceName() %>" required>
                </div>

                <div class="form-group">
                    <label for="description">Description</label>
                    <textarea name="description" id="description" class="form-control" rows="3" required><%= s.getDescription() %></textarea>
                </div>

                <div class="form-group">
                    <label for="price">Price</label>
                    <input type="text" name="price" id="price" class="form-control" 
                           value="<%= s.getPrice() %>" required>
                </div>

                <div class="form-group">
                    <label for="status">Status</label>
                    <select name="status" id="status" class="form-control" required>
                        <option value="available" >Available</option>
                        <option value="notAvailable" >Occupied</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="serviceImage">Service Image (Optional)</label>
                    <input type="file" name="ServiceImage" class="form-control">
                </div>

                <div class="form-group">
                    <label for="serviceImage">Current Image</label>
                    <br>
                    <img src="<%= request.getContextPath() + s.getImage() %>" alt="" width="200" height="150">
                </div>

                <button type="submit" class="btn btn-primary">Update Service</button>
                <button type="button" class="btn btn-danger" onclick="window.location.href = 'ServiceListAdmin'">Cancel</button>
            </form>
        </div>

        
        <footer>
            <jsp:include page="footerAdmin.jsp"></jsp:include>
        </footer>
    </body>
</html>
