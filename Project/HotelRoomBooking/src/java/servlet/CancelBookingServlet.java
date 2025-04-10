/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import dao.BookingDAO;
 import jakarta.servlet.ServletException;
 import jakarta.servlet.annotation.WebServlet;
 import jakarta.servlet.http.HttpServlet;
 import jakarta.servlet.http.HttpServletRequest;
 import jakarta.servlet.http.HttpServletResponse;
 
 import java.io.IOException;
 
@WebServlet(name = "CancelBookingServlet", urlPatterns = {"/CancelBookingServlet"})
 public class CancelBookingServlet extends HttpServlet {
     protected void doPost(HttpServletRequest request, HttpServletResponse response)
             throws ServletException, IOException {
         int bookingID = Integer.parseInt(request.getParameter("bookingID"));
         BookingDAO dao = new BookingDAO();
         boolean success = dao.cancelBooking(bookingID);
 
         if (success) {
             response.sendRedirect("statistics.jsp"); // reload lại danh sách
         } else {
             request.setAttribute("errorMessage", "Failed to cancel booking.");
             request.getRequestDispatcher("statistics.jsp").forward(request, response);
         }
     }
 }