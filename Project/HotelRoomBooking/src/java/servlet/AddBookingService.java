/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

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
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author My PC
 */
@WebServlet(name = "AddBookingService", urlPatterns = {"/AddBookingService"})
public class AddBookingService extends HttpServlet {

    ServiceDAO serviceDAO = new ServiceDAO();
    RoomDAO roomDAO = new RoomDAO();
    BookingDAO bookingDAO = new BookingDAO();
    BookingServiceDAO bookingServiceDAO = new BookingServiceDAO();
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
        
        int serviceID = Integer.parseInt(request.getParameter("serviceID"));
        Service service = serviceDAO.SearchServiceByID(serviceID);
        if (service == null) {
            response.sendRedirect("ServiceList");
            return;
        }

        Profile profile = profileDAO.SearchProfileByAccountId(user.getAccountID());

        List<Booking> bookingList = bookingDAO.SearchBooking("ProfileID", String.valueOf(profile.getProfileID()));
        List<Room> roomList = roomDAO.GetRoomList();

        request.setAttribute("service", service);
        request.setAttribute("bookingList", bookingList);
        request.setAttribute("roomList", roomList);
        request.getRequestDispatcher("BookingServiceOrder.jsp").forward(request, response);
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
            response.setContentType("text/html;charset=UTF-8");
            HttpSession sessionUser = request.getSession(false);
            Account user = (sessionUser != null) ? (Account) sessionUser.getAttribute("user") : null;
            if (user == null) {
                response.sendRedirect("ServiceList");
                return;
            }

            int serviceID = Integer.parseInt(request.getParameter("serviceID"));

            Service service = serviceDAO.SearchServiceByID(serviceID);
            if (service == null) {
                response.sendRedirect("ServiceList");
                return;
            }

            int bookingID = Integer.parseInt(request.getParameter("forBooking"));
            Booking booking = bookingDAO.SearchBooking(bookingID);
            if (booking == null) {
                response.sendRedirect("ServiceList");
                return;
            }

            String amountStr = request.getParameter("amount");
            String startTimeStr = request.getParameter("startTime");
            String endTimeStr = request.getParameter("endTime");
            String totalAmountStr = request.getParameter("totalAmount");

            if (isNullOrEmpty(amountStr) || isNullOrEmpty(startTimeStr) || isNullOrEmpty(endTimeStr)) {
                response.sendRedirect("ServiceList");
                return;
            }

            int amount = Integer.parseInt(amountStr);
            float totalAmount = Float.parseFloat(totalAmountStr);

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            java.util.Date startTime = format.parse(startTimeStr);
            java.util.Date endTime = format.parse(endTimeStr);

            java.sql.Date startTimeSQL = new java.sql.Date(startTime.getTime());
            java.sql.Date endTimeSQL = new java.sql.Date(endTime.getTime());
            
            if (endTimeSQL.before(startTimeSQL))
            {
                //response.sendRedirect("datetimeError");
                response.sendRedirect("ServiceList");
                return;
            }
            
            BookingService bookingService = new BookingService(serviceID, booking.getBookingID(), amount, startTimeSQL, endTimeSQL);

            bookingServiceDAO.Add(bookingService);

            Booking bookingToUpdate = bookingDAO.SearchBooking(bookingID);
            bookingToUpdate.setTotalAmount(bookingToUpdate.getTotalAmount() + totalAmount);

            bookingDAO.Update(bookingToUpdate);

            request.getRequestDispatcher("BookingList").forward(request, response);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    private boolean isNullOrEmpty(String s) {
        s = s.trim();
        return s.equals("") || s.equals(null) || s == null;
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
