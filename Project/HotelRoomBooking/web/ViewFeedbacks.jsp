<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View FeedBack</title>

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
            <jsp:include page="header_loggedIn.html"></jsp:include>
            </header>

        <% if (request.getAttribute("message") != null) { %>
        <div class="alert alert-success">
            <%= request.getAttribute("message") %>
        </div>
        <% } %>

        <div>
            <c:choose>
                <c:when test="${requestScope.feedbackList.size() == 0}">
                    <div class="container">
                        <label class="h2">Feedback List is Empty</label>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="container">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Room</th>
                                    <th>Comment</th>
                                    <th>Rating</th>
                                    <th>Date</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${requestScope.feedbackList}" var="f">
                                    <tr>
                                        <c:forEach items="${requestScope.roomList}" var="r">
                                            <c:if test="${f.getRoomID() == r.getRoomID()}">
                                                <td>${r.getRoomName()}</td>
                                            </c:if>
                                        </c:forEach>
                                        <td>${f.getComment()}</td>
                                        <td>${f.getRating()}</td>
                                        <td>${f.getDate()}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>

        <footer>
            <jsp:include page="footer.html"></jsp:include>
        </footer>
    </body>
</html>
