<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.util.*, model.*, dao.*" pageEncoding="UTF-8"%>
<%
    DashboardDAO dashboardDAO = new DashboardDAO();
    List<Integer> availableYears = dashboardDAO.getAvailableYears();
    int selectedYear = request.getParameter("year") != null ? Integer.parseInt(request.getParameter("year")) : availableYears.get(0);
    List<MonthlyRevenue> monthlyRevenues = dashboardDAO.getRevenueByYear(selectedYear);
    List<MonthlyRevenue> annualRevenues = dashboardDAO.getAnnualRevenue();
    List<MonthlyRevenue> lastFiveYearsRevenues = dashboardDAO.getLastFiveYearsRevenue();
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="description" content="Sona Template">
        <meta name="keywords" content="Sona, unica, creative, html">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">

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

        <title>Dashboard - Revenue Report</title>
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
            .footer-section {
                position: relative;
                z-index: 1000;
                padding: 40px 20px;
                margin-left: 0;
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
            }
            table td {
                text-align: center;
            }
        </style>
    </head>
    <body>
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
            <a href="dashboard.jsp" class="btn-custom" style="background-color: #dfa974; color: white;">Report</a>
            <a href="statistics.jsp" class="btn-custom">Statistics</a>
            <a href="sendEmailAll.jsp" class="btn-custom">Send Email</a>
        </div>

        <!-- Main Content -->
        <div class="main-content">
            <h2>Revenue Report for Year <%= selectedYear %></h2>

            <form action="dashboard.jsp" method="get">
                <div class="form-group">
                    <label for="year">Select Year:</label>
                    <select name="year" class="form-control" onchange="this.form.submit()">
                        <% for (Integer year : availableYears) { %>
                        <option value="<%= year %>" <%= year == selectedYear ? "selected" : "" %>><%= year %></option>
                        <% } %>
                    </select>
                </div>
            </form>

            <h3>Monthly Revenue for <%= selectedYear %></h3>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Month</th>
                        <th>Total Revenue</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (int month = 1; month <= 12; month++) {
                        MonthlyRevenue revenue = null;
                        for (MonthlyRevenue mr : monthlyRevenues) {
                            if (mr.getMonth() == month) {
                                revenue = mr;
                                break;
                            }
                        }
                    %>
                    <tr>
                        <td><%= month %></td>
                        <td><%= revenue != null ? "$" + revenue.getTotalRevenue() : "$0.00" %></td>
                    </tr>
                    <% } %>
                </tbody>
            </table>

            <h3>Annual Revenue</h3>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Year</th>
                        <th>Total Revenue</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (MonthlyRevenue revenue : annualRevenues) { %>
                    <tr>
                        <td><%= revenue.getMonth() %></td>
                        <td><%= "$" + revenue.getTotalRevenue() %></td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>

        <footer class="footer-section">
            <div class="container">
                <div class="footer-text">
                    <div class="row">
                        <div class="col-lg-4">
                            <div class="ft-about">
                                <div class="logo">
                                    <a href="#">
                                        <img src="img/footer-logo.png" alt="">
                                    </a>
                                </div>
                                <p>We inspire and reach millions of travelers<br /> across 90 local websites</p>
                            </div>
                        </div>
                        <div class="col-lg-3 offset-lg-1">
                            <div class="ft-contact">
                                <h6>Contact Us</h6>
                                <ul>
                                    <li>(12) 345 67890</li>
                                    <li>hotelroombooking@gmail.com</li>
                                    <li>856 Cordia Extension Apt. 356, Lake, United State</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </footer>
    </body>
</html>
