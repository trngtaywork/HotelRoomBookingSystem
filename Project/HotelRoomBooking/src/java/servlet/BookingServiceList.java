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
import java.util.List;
import model.*;

/**
 *
 * @author My PC
 */
@WebServlet(name = "BookingServiceList", urlPatterns = {"/BookingServiceList"})
public class BookingServiceList extends HttpServlet {

    BookingDAO bookingDao = new BookingDAO();
    BookingServiceDAO bookingServiceDAO = new BookingServiceDAO();
    AccountDAO accountDao = new AccountDAO();
    ProfileDAO profileDAO = new ProfileDAO();
    ServiceDAO serviceDAO = new ServiceDAO();
    RoomDAO roomDAO = new RoomDAO();

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

        Profile profile = profileDAO.SearchProfileByAccountId(user.getAccountID());

        //temp implement
        List<Booking> bookingList = bookingDao.SearchBookingsByProfileID(profile.getProfileID());
        List<Profile> profileList = profileDAO.GetProfileList();
        List<Account> accountList = accountDao.GetAccountList();
        List<Service> serviceList = serviceDAO.GetServiceList();
        List<Room> roomList = roomDAO.GetRoomList();
        List<BookingService> bookingServiceList = bookingServiceDAO.GetBookingServiceList();

        request.setAttribute("bookingServiceList", bookingServiceList);
        request.setAttribute("bookingList", bookingList);
        request.setAttribute("profileList", profileList);
        request.setAttribute("accountList", accountList);
        request.setAttribute("serviceList", serviceList);
        request.setAttribute("roomList", roomList);

        request.getRequestDispatcher("BookingServiceList.jsp").forward(request, response);
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
