<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="model.Account" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="description" content="Reset Password">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Reset Password</title>

        <!-- Css Styles -->
        <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
        <link rel="stylesheet" href="css/style.css" type="text/css">
        <link rel="stylesheet" href="css/login.css">

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
                                        <li class="active"><a href="./index.html">Home</a></li>
                                        <li><a href="RoomList">Rooms</a></li>
                                        <li><a href="./about-us.html">About Us</a></li>
                                        <li><a href="./pages.html">Pages</a>
                                            <ul class="dropdown">
                                                <li><a href="./room-details.html">Room Details</a></li>
                                                <li><a href="./blog-details.html">Blog Details</a></li>
                                                <li><a href="#">Family Room</a></li>
                                                <li><a href="#">Premium Room</a></li>
                                            </ul>
                                        </li>
                                        <li><a href="./blog.html">News</a></li>
                                        <li><a href="./contact.html">Contact</a></li>
                                    </ul>
                                </nav>
                                <div class="nav-right search-switch">
                                    <i class="icon_search"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </header>

        <!-- Reset Password Section -->
        <div class="login-wrap">
            <div class="login-html">
                <h2 style="color: #fff; text-align: center;">Reset Password</h2>
                <br/>
                <!-- Form Reset Password -->
                <form action="ResetPasswordServlet" method="post">
                    <div class="login-form">
                        <div class="group">
                            <label for="email" class="label">Enter Your Email</label>
                            <input id="email" name="email" type="email" class="input" required>
                        </div>
                        <div class="group">
                            <input type="submit" class="button" value="Send New Password">
                        </div>
                    </div>
                </form>
                <br/>
                <div class="error-message" style="color:red; text-align:center;">
                <% if (request.getAttribute("message") != null) { %>
                <%= request.getAttribute("message") %>
                <% } %>
                </div>
                <div class="hr"></div>
                <div class="foot-lnk">
                    <a href="login.jsp">Back to Login</a>
                </div>
            </div>
        </div>

        <!-- Footer Section -->
        <footer class="footer-section" style="background-color: #111; color: white; padding: 20px 0; text-align: center;">
            <div class="container">
                <div class="footer-text">
                    <div class="row">
                        <div class="col-lg-4">
                            <div class="ft-about">
                                <div class="logo">
                                    <a href="#">
                                        <img src="img/footer-logo.png" alt="" width="150">
                                    </a>
                                </div>
                                <p>We inspire and reach millions of travelers across 90 local websites.</p>
                            </div>
                        </div>
                        <div class="col-lg-3 offset-lg-1">
                            <div class="ft-contact">
                                <h6>Contact Us</h6>
                                <ul>
                                    <li>(12) 345 67890</li>
                                    <li>hotelroombooking@gmail.com</li>
                                    <li>856 Cordia Extension Apt. 356, Lake, United States</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </footer>
    </body>
</html>
