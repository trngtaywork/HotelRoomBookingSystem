/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import java.sql.Timestamp;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.*;
import dao.*;

/**
 *
 * @author My PC
 */
@WebServlet(name = "WriteFeedback", urlPatterns = {"/WriteFeedback"})
public class WriteFeedback extends HttpServlet {

    BookingDAO bookingDAO = new BookingDAO();
    FeedbackDAO feedbackDAO = new FeedbackDAO();
    RoomDAO roomDAO = new RoomDAO();
    ProfileDAO profileDAO = new ProfileDAO();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessionUser = request.getSession(false);
        Account user = (sessionUser != null) ? (Account) sessionUser.getAttribute("user") : null;
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int bookingID = Integer.parseInt(request.getParameter("bookingID").trim());
        Booking booking = bookingDAO.SearchBooking(bookingID);
        if (booking == null) {
            response.sendRedirect("BookingList");
            return;
        }

        int roomID = booking.getRoomID();
        Room room = roomDAO.SearchRoomByID(roomID);
        if (room == null) {
            //response.sendRedirect("nullRoom");
            response.sendRedirect("BookingList");
            return;
        }

        request.setAttribute("booking", booking);
        request.setAttribute("room", room);
        request.getRequestDispatcher("WriteFeedback.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession sessionUser = request.getSession(false);
            Account user = (sessionUser != null) ? (Account) sessionUser.getAttribute("user") : null;
            if (user == null) {
                //response.sendRedirect("NullUser");
                response.sendRedirect("login.jsp");
                return;
            }

            Profile profile = profileDAO.SearchProfileByAccountId(user.getAccountID());
            if (profile == null) {
                //response.sendRedirect("nullProfile");
                response.sendRedirect("login.jsp");
                return;
            }

            int bookingID = Integer.parseInt(request.getParameter("bookingID").trim());
            Booking booking = bookingDAO.SearchBooking(bookingID);
            if (booking == null) {
                response.sendRedirect("BookingList");
                return;
            }

            int roomID = booking.getRoomID();
            Room room = roomDAO.SearchRoomByID(roomID);
            if (room == null) {
                //response.sendRedirect("nullRoom");
                response.sendRedirect("BookingList");
                return;
            }

            java.util.Date temp = new java.util.Date();
            java.sql.Date currentDate = new java.sql.Date(temp.getTime());//get current date

            String comment = request.getParameter("comment").trim();
            int rating = Integer.parseInt(request.getParameter("rating").trim());

            Feedback feedback = new Feedback(profile.getProfileID(), roomID, comment, rating, new Timestamp(currentDate.getTime()));
            feedbackDAO.Add(feedback);

            request.setAttribute("message", "Feedback submited successfully");
            request.getRequestDispatcher("BookingList").forward(request, response);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
