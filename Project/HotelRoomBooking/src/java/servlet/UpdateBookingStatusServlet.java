/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package servlet;

import dao.BookingDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UpdateBookingStatusServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int bookingID = Integer.parseInt(request.getParameter("bookingID"));
        String action = request.getParameter("action");
        String newStatus = "";

        if ("cancel".equals(action)) {
            newStatus = "Cancelled";
        } else if ("confirm".equals(action)) {
            newStatus = "Confirmed";
        }

        BookingDAO dao = new BookingDAO();
        dao.updateBookingStatus(bookingID, newStatus);
        response.sendRedirect("statistics_1.jsp");
    }
}
