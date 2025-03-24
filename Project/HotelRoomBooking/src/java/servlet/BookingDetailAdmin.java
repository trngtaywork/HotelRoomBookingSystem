/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import dao.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.*;

/**
 *
 * @author My PC
 */
@WebServlet(name = "BookingDetailAdmin", urlPatterns = {"/BookingDetailAdmin"})
public class BookingDetailAdmin extends HttpServlet {
    BookingDAO bookingDAO = new BookingDAO();
    BookingRoomDAO bookingRoomDAO = new BookingRoomDAO();
    RoomDAO roomDAO = new RoomDAO();
    AccountDAO accountDAO = new AccountDAO();
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
        
        var bookingID = request.getParameter("bookingID");

        if (bookingID == null || bookingID.length() == 0) {
            request.getRequestDispatcher("BookingListAdmin").forward(request, response);
        } else {
            Booking booking = bookingDAO.SearchBooking(Integer.parseInt(bookingID));
            var roomId = booking.getRoomID();
            var profileId = booking.getProfileID();
            if(roomId <= 0){
                request.getRequestDispatcher("BookingListAdmin").forward(request, response);
            }
            if(profileId <= 0){
                request.getRequestDispatcher("BookingListAdmin").forward(request, response);
            }
            
            Room room = roomDAO.SearchRoomByID(roomId);
            Profile profile = profileDAO.SearchProfileById(profileId);
            Account account = accountDAO.SearchAccount(profile.getAccountID());
            BookingRoom bookingRoom = bookingRoomDAO.SearchBookingRoom(booking.getBookingID(), room.getRoomID());
            
            if(room == null || profile == null || account == null){
                request.getRequestDispatcher("BookingListAdmin").forward(request, response);
            }
            
            request.setAttribute("room", room);
            request.setAttribute("booking", booking);
            request.setAttribute("bookingRoom", bookingRoom);
            request.setAttribute("profile", profile);
            request.setAttribute("account", account);
            
            request.getRequestDispatcher("BookingDetailAdmin.jsp").forward(request, response);
        }
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
        processRequest(request, response);
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
