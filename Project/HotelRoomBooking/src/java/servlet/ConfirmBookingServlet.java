package servlet;

import dao.BookingDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ConfirmBookingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int bookingID = Integer.parseInt(request.getParameter("bookingID"));
        BookingDAO dao = new BookingDAO();
        boolean success = dao.confirmBooking(bookingID);

        if (success) {
            response.sendRedirect("statistics.jsp");
        } else {
            request.setAttribute("errorMessage", "Failed to confirm booking.");
            request.getRequestDispatcher("statistics.jsp").forward(request, response);
        }
    }
}
