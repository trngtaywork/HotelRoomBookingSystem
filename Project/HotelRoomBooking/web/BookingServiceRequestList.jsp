<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            }
            table td {
                text-align: center;
            }
            .footer-section {
                position: relative;
                z-index: 1000;
                padding: 40px 20px;
                margin-left: 0;
            }
            .statistics-list ul {
                list-style-type: none;
                padding: 0;
            }
            .statistics-list ol {
                margin: 10px 0;
            }
            .statistics-list a {
                display: inline-block;
                width: 100%;
                padding: 12px 20px;
                background-color: #dfa974;
                color: white;
                text-align: center;
                border: none;
                border-radius: 5px;
                font-weight: bold;
                text-decoration: none;
                transition: background-color 0.3s ease, transform 0.2s ease;
            }
            .statistics-list a:hover {
                background-color: #f8b33e;
                transform: scale(1.05);
            }
            .statistics-list a:active {
                background-color: #e09b29;
            }
            .pagination {
                display: flex;
                justify-content: center;
                margin-top: 20px;
            }

            .pagination a {
                padding: 8px 16px;
                margin: 0 5px;
                border-radius: 5px;
                text-decoration: none;
                color: #fff;
                background-color: #000;
                transition: background-color 0.3s ease;
            }

            .pagination a:hover {
                background-color: #dfa974;
            }

            .pagination a.active {
                background-color: #dfa974;
                pointer-events: none;
            }

            .pagination a.disabled {
                background-color: #d6d6d6;
                pointer-events: none;
            }

            .pagination span {
                padding: 8px 16px;
                margin: 0 5px;
                font-size: 16px;
                color: #333;
            }

            .pagination .disabled {
                color: #ccc;
            }
        </style>
    </head>
    <body>
        <header>
            <jsp:include page="headerAdmin.jsp"></jsp:include>
        </header>

        <!-- Sidebar -->
        <div class="sidebar">
            <a href="dashboard.jsp" class="btn-custom">Revenue Report</a>
            <a href="statistics.jsp" class="btn-custom" style="background-color: #dfa974; color: white;">Book Room/Service List</a>
            <a href="sendEmail.jsp" class="btn-custom">Send Email</a>
        </div>

        <div class="main-content">
            
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
