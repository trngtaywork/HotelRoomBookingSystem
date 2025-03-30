<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="description" content="Sona Template">
    <meta name="keywords" content="Sona, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    
    <link href="https://fonts.googleapis.com/css?family=Lora:400,700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Cabin:400,500,600,700&display=swap" rel="stylesheet">
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

    <title>Send Email</title>

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
        .sidebar {
            position: fixed;
            left: 0;
            width: 270px;
            height: 70vh;
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
        .main-content {
            margin-left: 270px;
            padding: 80px 20px;
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
        <a href="dashboard.jsp" class="btn-custom">Revenue Report</a>
        <a href="statistics.jsp" class="btn-custom">Book Room/Service List</a>
        <a href="sendEmailAll.jsp" class="btn-custom" style="background-color: #dfa974; color: white;">Send Email</a>
    </div>

    <!-- Main Content -->
    <div class="main-content">
        <h2>Send Email</h2>

        <% if (request.getAttribute("successMessage") != null) { %>
        <div class="alert alert-success"><%= request.getAttribute("successMessage") %></div>
        <% } %>
        <% if (request.getAttribute("errorMessage") != null) { %>
        <div class="alert alert-danger"><%= request.getAttribute("errorMessage") %></div>
        <% } %>

        <form action="SendEmailAllServlet" method="post">
            <!-- Select Role -->
            <div class="form-group">
                <label for="role">Role:</label>
                <select name="role" class="form-control" required>
                    <option value="All" <%= "All".equals(request.getAttribute("role")) ? "selected" : "" %>>All</option>
                    <option value="Receptionist" <%= "Receptionist".equals(request.getAttribute("role")) ? "selected" : "" %>>Receptionist</option>
                    <option value="Customer" <%= "Customer".equals(request.getAttribute("role")) ? "selected" : "" %>>Customer</option>
                </select>
            </div>

            <!-- Subject -->
            <div class="form-group">
                <label for="subject">Subject:</label>
                <input type="text" name="subject" class="form-control" required/>
            </div>

            <!-- Message -->
            <div class="form-group">
                <label for="message">Message:</label>
                <textarea name="message" class="form-control" rows="5" required></textarea>
            </div>

            <button type="submit" class="btn btn-primary">Send Email</button>
        </form>
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
                            <p>We inspire and reach millions of travelers across 90 local websites</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </footer>
</body>
</html>