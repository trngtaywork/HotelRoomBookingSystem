<%-- 
    Document   : AddService
    Created on : Mar 15, 2025, 8:24:26 PM
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
        
        <div class="formbold-main-wrapper" style="align-self: center">
            <div class="formbold-form-wrapper">
                <h2>Add Service</h2>
                <% if (request.getAttribute("successMessage") != null) { %>
                <div class="alert alert-success">
                    <%= request.getAttribute("successMessage") %>
                </div>
                <% } %>
                <% if (request.getAttribute("errorMessage") != null) { %>
                <div class="alert alert-danger">
                    <%= request.getAttribute("errorMessage") %>
                </div>
                <% } %>

                <form id="addServiceForm" action="AddService" method="post" enctype="multipart/form-data">
                    <div class="formbold-input-group">
                        <label class="formbold-form-label">Service Name</label>
                        <input type="text" name="serviceName" id="serviceName" placeholder="Enter service name">
                    </div>

                    <div class="formbold-input-group">
                        <label class="formbold-form-label">Description</label>
                        <textarea name="description" id="description" placeholder="Enter service description" class="formbold-form-input" rows="3"></textarea>
                    </div>

                    <div class="formbold-input-group">
                        <label class="formbold-form-label">Price</label>
                        <input type="text" name="price" id="price" placeholder="Enter price" class="formbold-form-input">
                    </div>

                    <div class="formbold-input-group">
                        <label class="formbold-form-label">Status</label>
                        <select name="statusService" id="statusService" class="formbold-form-select">
                            <option value="available" >Available</option>
                            <option value="unavailable" >Unavailable</option>
                        </select>
                    </div>
                    
                    <div class="formbold-input-group">
                        <label class="formbold-form-label">Type</label>
                        <input type="text" name="typeService" id="typeService" placeholder="Enter service type" class="formbold-form-input">
                    </div>

                    <div class="formbold-input-group">
                        <label class="formbold-form-label">Upload Image</label>
                        <input type="file" name="serviceImage" class="formbold-form-input">
                    </div>

                    <button class="formbold-btn">Submit</button>
                </form>
            </div>
        </div>
        
        <footer>
            <jsp:include page="footerAdmin.jsp"></jsp:include>
        </footer>
    </body>
</html>
