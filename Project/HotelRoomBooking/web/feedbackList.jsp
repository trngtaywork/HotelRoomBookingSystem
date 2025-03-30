<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="dao.FeedbackDAO" %>
<%@ page import="model.Feedback" %>

<%
    FeedbackDAO feedbackDAO = new FeedbackDAO();
    List<Feedback> feedbackList = feedbackDAO.getAllFeedbacks(); // Lấy tất cả dữ liệu feedback

    // Các tham số phân trang
    int pageSize = 10;  // Số item mỗi trang
    int totalItems = feedbackList.size();  // Tổng số item
    int totalPages = (int) Math.ceil((double) totalItems / pageSize); // Tính tổng số trang

    // Lấy trang hiện tại từ request, nếu không có thì mặc định là trang 1
    int currentPage = 1;
    if (request.getParameter("page") != null) {
        currentPage = Integer.parseInt(request.getParameter("page"));
    }

    // Tính toán chỉ số bắt đầu và kết thúc cho phân trang
    int startIndex = (currentPage - 1) * pageSize;
    int endIndex = Math.min(startIndex + pageSize, totalItems);
    List<Feedback> paginatedFeedbackList = feedbackList.subList(startIndex, endIndex); // Phân trang dữ liệu
%>


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
        <link rel="stylesheet" href="css/room.css" type="text/css">

        <title>Feedback List</title>
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
                background-color: #dfa974;
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
                                        <li class="active"><a href="feedbackList.jsp">Feedback List</a></li>
                                        <li><a href="userList.jsp">User List</a></li>
                                        <li><a href="roomListForAdmin.jsp">Room List</a></li>
                                        <li><a href="serviceList.jsp">Service List</a></li>
                                        <li><a href="dashboard.jsp">Dashboard</a></li>
                                        <li><a href="profile.jsp">Profile</a></li>
                                    </ul>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </header>

        <br/>
        <div class="container">
            <h3>Feedback List</h3>
            <form method="get" action="FeedbackListServlet">
                <div class="row">
                    <div class="col-md-4">
                        <label for="roomName">Room Name:</label>
                        <input type="text" id="roomName" name="roomName" class="form-control" 
                               value="<%= request.getParameter("roomName") != null ? request.getParameter("roomName") : "" %>"
                               oninput="delaySubmit()">
                    </div>
                    <div class="col-md-4">
                        <label for="rating">Rating:</label>
                        <select id="rating" name="rating" class="form-control" onchange="this.form.submit()">
                            <option value="">Select Rating</option>
                            <option value="1" <%= "1".equals(request.getParameter("rating")) ? "selected" : "" %>>1</option>
                            <option value="2" <%= "2".equals(request.getParameter("rating")) ? "selected" : "" %>>2</option>
                            <option value="3" <%= "3".equals(request.getParameter("rating")) ? "selected" : "" %>>3</option>
                            <option value="4" <%= "4".equals(request.getParameter("rating")) ? "selected" : "" %>>4</option>
                            <option value="5" <%= "5".equals(request.getParameter("rating")) ? "selected" : "" %>>5</option>
                        </select>
                    </div>
                    <div class="col-md-4">
                        <label for="date">Date:</label>
                        <input type="date" id="date" name="date" class="form-control" 
                               value="<%= request.getParameter("date") != null ? request.getParameter("date") : "" %>"
                               onchange="this.form.submit()">
                    </div>
                </div>
            </form>

            <hr>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Room Name</th>
                        <th>Image</th>
                        <th>Rating</th>
                        <th>Comment</th>
                        <th>Customer Name</th>
                        <th>Date</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        if (paginatedFeedbackList != null && !paginatedFeedbackList.isEmpty()) {
                            for (Feedback feedback : paginatedFeedbackList) {
                    %>
                    <tr>
                        <td><%= feedback.getRoomName() %></td>
                        <td><img src="<%= request.getContextPath() + feedback.getImage() %>" alt="Room Image" style="width: 150px; height: 100px;"></td>
                        <td><%= feedback.getRating() %>/5</td>
                        <td><%= feedback.getComment() %></td>
                        <td><%= feedback.getCustomerName() %></td>
                        <td><%= feedback.getDate() %></td>
                    </tr>
                    <% 
                        }
                    } else {
                    %>
                    <tr>
                        <td colspan="6" class="text-center">No feedback available</td>
                    </tr>
                    <% 
                    }
                    %>
                </tbody>
            </table>

            <!-- Phân trang -->
            <div class="pagination">
                <% if (currentPage > 1) { %>
                <a href="feedbackList.jsp?page=<%= currentPage - 1 %>">Previous</a>
                <% } else { %>
                <a href="#" class="disabled">Previous</a>
                <% } %>

                <% for (int i = 1; i <= totalPages; i++) { %>
                <a href="feedbackList.jsp?page=<%= i %>" class="<%= (i == currentPage) ? "active" : "" %>"><%= i %></a>
                <% } %>

                <% if (currentPage < totalPages) { %>
                <a href="feedbackList.jsp?page=<%= currentPage + 1 %>">Next</a>
                <% } else { %>
                <a href="#" class="disabled">Next</a>
                <% } %>
            </div>

            <script>
                let timeout;
                function delaySubmit() {
                    clearTimeout(timeout);
                    timeout = setTimeout(function () {
                        document.forms[0].submit();
                    }, 1000);  // Trì hoãn 1 giây trước khi gửi form
                }
            </script>
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
