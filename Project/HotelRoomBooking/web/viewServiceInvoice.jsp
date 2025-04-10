<%@ page contentType="text/html; charset=UTF-8" language="java" import="dao.*, model.*" %>
<%
    int bookingID = Integer.parseInt(request.getParameter("bookingID"));
    BookingDAO dao = new BookingDAO();
    ServiceInvoiceDetail invoice = dao.getServiceInvoiceDetailByBookingID(bookingID);
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Service Invoice Detail</title>

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
            .main-content {
                margin-left: 270px;
                padding: 40px 20px;
            }
            .sidebar {
                position: fixed;
                left: 0;
                width: 270px;
                height: 100vh;
                background: #f8f9fa;
                padding-top: 20px;
                display: flex;
                flex-direction: column;
                align-items: center;
            }
            .sidebar .btn-custom {
                display: block;
                width: 90%;
                text-align: left;
                padding: 12px 20px;
                background-color: white;
                color: black;
                border: none;
                font-weight: bold;
                text-decoration: none;
                transition: all 0.3s ease;
            }
            .sidebar .btn-custom:hover {
                background-color: #dfa974;
                color: white;
            }
            table th {
                background-color: #dfa974;
                color: white;
                text-align: center;
                width: 500px;
            }
            table td {
                text-align: center;
            }
        </style>
    </head>
    <body>

        <!-- Header -->
        <header class="header-section">
            <div class="menu-item">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-2">
                            <div class="logo">
                                <a href="./index.html">
                                    <img src="img/logo.png" alt="">
                                </a>
                            </div>
                        </div>
                        <div class="col-lg-10">
                            <div class="nav-menu">
                                <nav class="mainmenu">
                                    <ul>
                                        <li><a href="./index.html">Home</a></li>
                                        <li><a href="userList.jsp">User List</a></li>
                                        <li><a href="roomListForAdmin.jsp">Room List</a></li>
                                        <li><a href="serviceList.jsp">Service List</a></li>
                                        <li class="active"><a href="dashboard.jsp">Dashboard</a></li>
                                        <li><a href="profile.jsp">Profile</a></li>
                                    </ul>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </header>

        <!-- Sidebar -->
        <div class="sidebar">
            <a href="dashboard.jsp" class="btn-custom">Revenue Report</a>
            <a href="statistics.jsp" class="btn-custom" style="background-color: #dfa974; color: white;">Book Room/Service List</a>
            <a href="sendEmail.jsp" class="btn-custom">Send Email</a>
        </div>

        <!-- Main Content -->
        <div class="main-content">
            <h2>Service Invoice Detail for Booking ID: <%= bookingID %></h2>
            <br/>

            <% if (invoice == null) { %>
            <div class="alert alert-danger">No invoice data found!</div>
            <% } else { %>
            <table class="table table-bordered">
                <tr><th>Customer Name</th><td><%= invoice.getCustomerName() %></td></tr>
                <tr><th>Username</th><td><%= invoice.getUsername() %></td></tr>
                <tr><th>Service Name</th><td><%= invoice.getServiceName() %></td></tr>
                <tr><th>Booking Date</th><td><%= invoice.getBookingDate() %></td></tr>
                <tr><th>Start Time</th><td><%= invoice.getStartTime() %></td></tr>
                <tr><th>End Time</th><td><%= invoice.getEndTime() %></td></tr>
                <tr><th>Total Amount</th><td>$<%= invoice.getTotalAmount() %></td></tr>
                <tr><th>Status</th><td><%= invoice.getStatus() %></td></tr>
            </table>
            <% } %>

            <a href="statistics_1.jsp" class="btn btn-secondary">Back</a>
        </div>

    </body>
</html>
