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
                                        <th>Status</th>
                                        <th style="width: 300px">Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${requestScope.serviceList}" var="s">
                                        <tr>
                                            <td>${s.getServiceID()}</td>
                                            <td>${s.getServiceName()}</td>
                                            <td>${s.getPrice()}</td>
                                            <td>${s.getStatus()}</td>
                                            <td>
                                                <button class="btn-custom btn-primary" onclick="window.location.href = 'EditService?serviceID=${s.getServiceID()}'">Edit</button>
                                                <button class="btn-custom btn-danger" onclick="window.location.href = 'DeleteService?serviceID=${s.getServiceID()}'">Delete</button>
                                                <button class="btn-custom btn-info" onclick="window.location.href = 'ServiceDetailAdmin?serviceID=${s.getServiceID()}'">Further Detail</button>
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

        <footer>
            <jsp:include page="footerAdmin.jsp"></jsp:include>
        </footer>
    </body>
</html>
