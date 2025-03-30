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
        <title>Delete Service</title>

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

        <style>
            body {
                font-family: 'Cabin', sans-serif;
                background-color: #f9f9f9;
                margin: 0;
                padding: 0;
            }

            h1 {
                font-size: 2rem;
                text-align: center;
                margin-top: 50px;
                color: #333;
            }

            table {
                width: 80%;
                margin: 30px auto;
                border-collapse: collapse;
                background-color: #fff;
                box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
            }

            th, td {
                padding: 12px 20px;
                text-align: left;
                font-size: 1rem;
                color: #555;
            }

            th {
                background-color: #007bff;
                color: white;
            }

            tr:nth-child(even) {
                background-color: #f2f2f2;
            }

            td a {
                text-decoration: none;
                color: #d9534f;
                font-weight: bold;
                cursor: pointer;
            }

            td a:hover {
                color: #c9302c;
            }

            button {
                background-color: #007bff;
                color: white;
                padding: 8px 16px;
                border: none;
                cursor: pointer;
                font-size: 1rem;
                border-radius: 4px;
                transition: background-color 0.3s ease;
            }

            button:hover {
                background-color: #0056b3;
            }

            /* Success and Error Messages */
            .success-message, .error-message {
                text-align: center;
                font-size: 1.2rem;
                margin: 20px auto;
                padding: 10px;
                border-radius: 5px;
            }

            .success-message {
                background-color: #d4edda;
                color: #155724;
            }

            .error-message {
                background-color: #f8d7da;
                color: #721c24;
            }

            /* Modal Confirmation */
            #confirmDeleteModal {
                display: none;
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background-color: rgba(0, 0, 0, 0.5);
                justify-content: center;
                align-items: center;
                z-index: 999;
            }

            .modal-content {
                background-color: white;
                padding: 20px;
                border-radius: 5px;
                text-align: center;
                width: 300px;
            }

            .modal-content button {
                width: 100px;
                margin: 10px;
            }

            /* Footer Styles */
            .footer-section {
                background-color: #f8f9fa;
                padding: 50px 0;
                text-align: center;
            }

            .footer-section .logo img {
                width: 150px;
                margin-bottom: 10px;
            }

            .footer-section .ft-contact ul {
                list-style: none;
                padding: 0;
            }

            .footer-section .ft-contact li {
                margin-bottom: 10px;
                font-size: 1rem;
                color: #555;
            }

            .footer-section .ft-contact h6 {
                font-size: 1.2rem;
                font-weight: bold;
                color: #007bff;
            }
        </style>
    </head>
    <body>
        <header>
            <jsp:include page="headerAdmin.jsp"></jsp:include>
            </header>

        <% if (request.getAttribute("errorMessage") != null) { %>
        <div class="alert alert-danger">
            <%= request.getAttribute("errorMessage") %>
        </div>
        <% } %>

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
                        <img src="<%= request.getContextPath() + r.getImage() %>" alt="Service Image" width="200" height="150">
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
                    <td><%=s.getStatusService()%></td>
                </tr>
                <tr>
                    <th>Type</th>
                    <td><%=s.getTypeService()%></td>
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
