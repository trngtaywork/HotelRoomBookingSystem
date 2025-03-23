/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import dao.AccountDAO;
import dao.BookingDAO;
import dao.BookingRoomDAO;
import dao.BookingServiceDAO;
import dao.ProfileDAO;
import dao.RoomDAO;
import dao.ServiceDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Account;
import model.Booking;
import model.BookingRoom;
import model.BookingService;
import model.Profile;
import model.Room;
import model.Service;

/**
 *
 * @author My PC
 */
@WebServlet(name = "BookingServiceListAdmin", urlPatterns = {"/BookingServiceListAdmin"})
public class BookingServiceListAdmin extends HttpServlet {
BookingDAO bookingDao = new BookingDAO();
    BookingRoomDAO bookingRoomDao = new BookingRoomDAO();
    AccountDAO accountDao = new AccountDAO();
    ProfileDAO profileDAO = new ProfileDAO();
    RoomDAO roomDAO = new RoomDAO();
    BookingServiceDAO bookingServiceDAO = new BookingServiceDAO();
    ServiceDAO serviceDAO = new ServiceDAO();
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
        
        List<Booking> bookingList = bookingDao.GetBookingList();
        List<Profile> profileList = profileDAO.GetProfileList();
        List<Account> accountList = accountDao.GetAccountList();
        List<Room> roomList = roomDAO.GetRoomList();
        List<BookingRoom> bookingRoomList = bookingRoomDao.GetBookingRoomList();
        List<BookingService> bookingServiceList = bookingServiceDAO.GetBookingServiceList();
        List<Service> serviceList = serviceDAO.GetServiceList();
        
        request.setAttribute("bookingRoomList", bookingRoomList);
        request.setAttribute("bookingList", bookingList);
        request.setAttribute("profileList", profileList);
        request.setAttribute("accountList", accountList);
        request.setAttribute("roomList", roomList);
        request.setAttribute("bookingServiceList", bookingServiceList);
        request.setAttribute("serviceList", serviceList);

        request.getRequestDispatcher("BookingServiceListAdmin.jsp").forward(request, response);
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
