<%-- 
    Document   : BookingServiceOrder
    Created on : Mar 19, 2025, 10:38:09 AM
    Author     : My PC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Service</title>

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
    </head>
    <body>
        <header>
            <jsp:include page="header_loggedIn.html"></jsp:include>
            </header>

        <%Service s = (Service)request.getAttribute("service");%>

        <div class="formbold-main-wrapper" style="align-self: center; align-content: center">
            <div class="formbold-form-wrapper">
                <table class="table table-borderless">
                    <h2>Please confirm your service order</h2>
                    <form action="AddBookingService" method="post">
                        <input type="hidden" name="serviceID" value="<%=s.getServiceID()%>">

                        <tbody>
                            <tr>
                        <div class="formbold-input-group">
                            <td><label class="formbold-form-label">Service Name</label></td>
                            <td><%=s.getServiceName()%></td>
                        </div>
                        </tr>

                        <div class="formbold-input-group">
                            <tr>
                                <td><label class="formbold-form-label">Service Price</label></td>
                                <td><%=s.getPrice()%></td>
                            </tr>
                        </div>

                        <div class="formbold-input-group">
                            <tr>
                                <td><label class="formbold-form-label">For Booking</label></td>

                                <c:choose>
                                    <c:when test="${requestScope.bookingList.size() == 0}">
                                        <td class="container">
                                            <label class="formbold-form-label">Your Booking List is Empty</label>
                                        </td>
                                    </c:when>    
                                    <c:otherwise>
                                        <td>
                                            <select name="forBooking" id="forBooking" class="formbold-form-select">
                                                <c:forEach items="${requestScope.bookingList}" var="b">
                                                    <option value="${b.getBookingID()}"><c:forEach items="${requestScope.roomList}" var="r"><c:if test="${b.getRoomID() == r.getRoomID()}">Room Name: ${r.getRoomName()} | Booking Date: ${b.getBookingDate()}</c:if></c:forEach></option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                        </div>

                        <div class="formbold-input-group">
                            <tr>
                                <td><label class="formbold-form-label">Amount</label></td>
                                <td><input class="formbold-form-input" type="number" name="amount" id="amount" value="1" oninput="calculateTotal()"></td>
                            </tr>
                        </div>

                        <div class="formbold-input-group">
                            <tr>
                                <td><label class="formbold-form-label">Start Time</label></td>
                                <td><input class="formbold-form-input" type="date" name="startTime" id="startTime"></td>
                            </tr>
                        </div>

                        <div class="formbold-input-group">
                            <tr>
                                <td><label class="formbold-form-label">End Time</label></td>
                                <td><input class="formbold-form-input" type="date" name="endTime" id="endTime"></td>
                            </tr>
                        </div>

                        <div class="formbold-input-group">
                            <tr>
                                <td></td>
                                <td></td>
                            </tr>
                        </div>

                        <div class="formbold-input-group">
                            <tr>
                                <td><label class="formbold-form-label">Total</label></td>
                                <td id="totalPrice"><></td>
                            </tr>
                        </div>

                        <div class="formbold-input-group">


                            <tr>
                                <td></td>

                                <c:choose>
                                    <c:when test="${requestScope.bookingList.size() == 0}">
                                        <td>
                                            <input disabled="true" type="submit" value="Submit" class="formbold-btn">
                                        </td>
                                    </c:when>    
                                    <c:otherwise>
                                        <td>
                                            <input type="submit" value="Submit" class="formbold-btn">
                                        </td>
                                    </c:otherwise>
                                </c:choose>


                            </tr>
                        </div>
                        </tbody>

                        <input type="hidden" name="totalAmount" id="totalAmount" value="">
                    </form>
                </table>
            </div>
        </div>

        <footer>
            <jsp:include page="footer.html"></jsp:include>
            </footer>

            <script>
                function calculateTotal() {
                    let amount = document.getElementById("amount").value;
                    let startDate = document.getElementById("startTime").value;
                    let endDate = document.getElementById("endTime").value;
                    let roomPrice = <%= s.getPrice() %>; // Get price per day from JSP

                    let total;

                    // Check if both dates are selected
                    if (startDate && endDate) {
                        let start = new Date(startDate);
                        let end = new Date(endDate);

                        // Calculate the difference in days
                        let timeDifference = end - start;
                        let days = timeDifference / (1000 * 60 * 60 * 24); // Convert milliseconds to days

                        // Ensure minimum booking of 1 day
                        days = days >= 1 ? days : 1;

                        // Calculate total with days
                        total = amount * days * roomPrice;
                    } else {
                        // If dates are empty, only multiply price × quantity
                        total = amount * roomPrice;
                    }

                    return total.toFixed(2); // Return total as a string with 2 decimal places
                }

                // Event listeners to update total dynamically
                document.getElementById("amount").addEventListener("input", updateTotal);
                document.getElementById("startTime").addEventListener("input", updateTotal);
                document.getElementById("endTime").addEventListener("input", updateTotal);

                function updateTotal() {
                    document.getElementById("totalPrice").innerText = calculateTotal();
                }

                // Call function on page load to set the initial total
                document.addEventListener("DOMContentLoaded", updateTotal);

                document.getElementById('totalAmount').value = calculateTotal();
        </script>
    </body>
</html>
