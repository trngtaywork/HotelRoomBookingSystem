<%-- 
    Document   : ServiceListAdmin
    Created on : Mar 15, 2025, 8:22:07 PM
    Author     : My PC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Service List Admin</title>

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
            .header-section {
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                z-index: 999;
                background-color: #fff;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            }
            body {
                padding-top: 80px;
            }
            .btn-custom1 {
                height: 70px;
                width: 240px;
                background-color: black;
                color: white;
                border: 1px solid black;
                padding-left: 5px;
            }
            .btn-custom {
                height: 35px;
                width: 70px;
                border-radius: 5%;
                background-color: black;
                color: white;
                border: 1px solid black;
                padding-left: 5px;
            }
            .btn-custom1:hover {
                background-color: #000;
            }
            table th {
                background-color: #dfa974;
                text-align: center;
                color: white;
            }
            table td {
                text-align: center;
                vertical-align: middle;
            }
        </style>
    </head>
    <body>
        <header>
            <jsp:include page="headerAdmin.jsp"></jsp:include>
            </header>
        <%----------------------------------------------------%>
        <div class="container mt-4 mb-4 p-3">
            <h3>Service List For Admin</h3>

            <button style="background-color: #000" class="btn-custom1 btn-primary" onclick="window.location.href = 'AddService.jsp'">Add New Service</button>
            <hr>
            <form action="ServiceListAdmin" method="post" class="row">
                <div class="col-md-3">
                    <label for="priceFilter">Price:</label>
                    <select name="priceFilter" id="priceFilter" class="form-control">
                        <option value="default">No Sorting</option>
                        <option value="asc">Low to High</option>
                        <option value="desc">High to Low</option>
                    </select>
                </div>

                <div class="col-md-3">
                    <label for="statusFilter">Status:</label>
                    <input type="text" name="statusFilter" id="statusFilter" class="form-control" placeholder="Search by status">
                </div>

                <div class="col-md-3">
                    <label for="serviceNameFilter">Service Name:</label>
                    <input type="text" name="serviceNameFilter" id="serviceNameFilter" class="form-control" placeholder="Search by name">
                </div>

                <div class="col-md-3">
                    <input type="submit" name="Search" class="form-control">
                </div>
            </form>

            <% if (request.getAttribute("errorMessage") != null) { %>
            <span class="text-danger"><%= request.getAttribute("errorMessage") %></span>
            <% } %>

            <c:choose>
                <c:when test="${requestScope.serviceList.size() == 0}">
                    <label class="h2 centered">Service List is Empty</label>
                </c:when>    
                <c:otherwise>
                    <table class="table table-bordered mt-3">
                        <thead>
                            <tr style="background-color: #dfa974">
                                <th>Service ID</th>
                                <th>Service Name</th>
                                <th>Price</th>
                                <th>StatusService</th>
                                <th>TypeService</th>
                                <th style="width: 300px">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                        <tbody>
                            <c:forEach items="${requestScope.serviceList}" var="s">
                                <tr>
                                    <td>${s.getServiceID()}</td>
                                    <td>${s.getServiceName()}</td>
                                    <td>${s.getPrice()}</td>
                                    <td>${s.getStatusService()}</td>
                                    <td>${s.getTypeService()}</td>
                                    <td>
                                        <button style="background-color: #000" class="btn-custom btn-info" onclick="window.location.href = 'EditService?serviceID=${s.getServiceID()}'">Edit</button>
                                        <button style="background-color: #000" class="btn-custom btn-info" onclick="window.location.href = 'DeleteService?serviceID=${s.getServiceID()}'">Delete</button>
                                        <button style="background-color: #000" class="btn-custom btn-info" onclick="window.location.href = 'ServiceDetailAdmin?serviceID=${s.getServiceID()}'">Details</button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:otherwise>
            </c:choose>
        </div>
        <%--------------------------------------------------
        <button class="btn-custom1 btn-primary" onclick="window.location.href = 'AddService.jsp'">Add New Service</button>
        <hr>

            <form action="ServiceListAdmin" method="post" class="row">
                <div class="col-md-3">
                    <label for="priceFilter">Price:</label>
                    <select name="priceFilter" id="priceFilter" class="form-control">
                        <option value="default">No Sorting</option>
                        <option value="asc">Low to High</option>
                        <option value="desc">High to Low</option>
                    </select>
                </div>

                <div class="col-md-3">
                    <label for="statusFilter">Status:</label>
                    <input type="text" name="statusFilter" id="statusFilter" class="form-control" placeholder="Search by status">
                </div>

                <div class="col-md-3">
                    <label for="serviceNameFilter">Service Name:</label>
                    <input type="text" name="serviceNameFilter" id="serviceNameFilter" class="form-control" placeholder="Search by name">
                </div>

                <div class="col-md-3">
                    <input type="submit" name="Search" class="form-control">
                </div>
            </form>

            <section class="rooms-section spad">
                <div class="container">

                    <% if (request.getAttribute("errorMessage") != null) { %>
                    <span class="text-danger"><%= request.getAttribute("errorMessage") %></span>
                    <% } %>

                    <c:choose>
                        <c:when test="${requestScope.serviceList.size() == 0}">
                            <label class="h2 centered">Service List is Empty</label>
                        </c:when>    
                        <c:otherwise>
                            <div class="container mt-4">
                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th>Service ID</th>
                                            <th>Service Name</th>
                                            <th>Price</th>
                                            <th>StatusService</th>
                                            <th>TypeService</th>
                                            <th style="width: 300px">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${requestScope.serviceList}" var="s">
                                            <tr>
                                                <td>${s.getServiceID()}</td>
                                                <td>${s.getServiceName()}</td>
                                                <td>${s.getPrice()}</td>
                                                <td>${s.getStatusService()}</td>
                                                <td>${s.getTypeService()}</td>
                                                <td>
                                                    <a class="btn-custom btn-primary" href = "EditService?serviceID=${s.getServiceID()}" >Edit</a>
                                                    <a class="btn-custom btn-danger" href="DeleteService?serviceID=${s.getServiceID()}">Delete</a>
                                                    <a class="btn-custom btn-info" href="ServiceDetailAdmin?serviceID=${s.getServiceID()}">Further Detail</a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>

                                </table>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </section>
        --%>
        <footer>
            <jsp:include page="footerAdmin.jsp"></jsp:include>
        </footer>
    </body>
</html>
