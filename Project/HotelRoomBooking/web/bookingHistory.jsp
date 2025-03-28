<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Booking" %>
<%@ page import="model.BookingRoom" %>
<%@ page import="dao.BookingDAO" %>
<%@ page import="dao.BookingRoomDAO" %>
<%@ page import="dao.FeedbackDAO" %>
<%@ page import="model.Feedback" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Booking History</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h2>Your Booking History</h2>
        
        <% 
            // Get the user ID from session
            int userID = (int) session.getAttribute("userID");
            BookingDAO bookingDAO = new BookingDAO();
            List<Booking> bookings = bookingDAO.getBookingsByUserID(userID); // Get all bookings by user

            if (bookings != null && bookings.size() > 0) {
        %>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Booking ID</th>
                        <th>Room</th>
                        <th>Status</th>
                        <th>Booking Date</th>
                        <th>Total Amount</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Booking booking : bookings) { 
                        BookingRoomDAO bookingRoomDAO = new BookingRoomDAO();
                        List<BookingRoom> bookingRooms = bookingRoomDAO.getRoomsByBookingID(booking.getBookingID());
                    %>
                        <tr>
                            <td><%= booking.getBookingID() %></td>
                            <td>
                                <% 
                                    for (BookingRoom bookingRoom : bookingRooms) {
                                        out.print(bookingRoom.getRoomName() + "<br>");
                                    }
                                %>
                            </td>
                            <td><%= booking.getStatusBooking() %></td>
                            <td><%= booking.getBookingDate() %></td>
                            <td><%= booking.getTotalAmount() %></td>
                            <td>
                                <!-- Button for canceling booking -->
                                <form action="cancelBooking" method="post">
                                    <input type="hidden" name="bookingID" value="<%= booking.getBookingID() %>">
                                    <button type="submit" class="btn btn-danger">Cancel Booking</button>
                                </form>
                                <br><br>

                                <!-- Button for providing feedback -->
                                <form action="addFeedback" method="post">
                                    <input type="hidden" name="bookingID" value="<%= booking.getBookingID() %>">
                                    <textarea name="feedback" class="form-control" placeholder="Leave your feedback..." required></textarea><br>
                                    <button type="submit" class="btn btn-primary">Submit Feedback</button>
                                </form>
                            </td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        <% } else { %>
            <p>You have no bookings.</p>
        <% } %>
    </div>
</body>
</html>
