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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author My PC
 */
@WebServlet(name = "AddBooking", urlPatterns = {"/AddBooking"})
public class AddBooking extends HttpServlet {

    BookingDAO bookingDAO = new BookingDAO();
    RoomDAO roomDAO = new RoomDAO();
    BookingRoomDAO bookingRoomDAO = new BookingRoomDAO();
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

        int roomID = Integer.parseInt(request.getParameter("roomID").trim());
        Room room = roomDAO.SearchRoomByID(roomID);
        if (room == null) {
            response.sendRedirect("RoomList");
            return;
        }

        String error = request.getParameter("error");
        request.setAttribute("error", error);
        
        request.setAttribute("room", room);
        request.getRequestDispatcher("BookingRoomOrder.jsp").forward(request, response);
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

            int roomID = Integer.parseInt(request.getParameter("roomID"));
            Room room = roomDAO.SearchRoomByID(roomID);
            if (room == null) {
                //response.sendRedirect("nullRoom");
                response.sendRedirect("RoomList");
                return;
            }

            LocalDate temp = LocalDate.now();
            java.sql.Date currentDate = java.sql.Date.valueOf(temp);//get current date

            String quantityStr = request.getParameter("quantity").trim();
            String startTimeStr = request.getParameter("startTime").trim();
            String endTimeStr = request.getParameter("endTime").trim();
            //String totalAmountStr = request.getParameter("totalAmount");

            if (isNullOrEmpty(quantityStr) || isNullOrEmpty(startTimeStr) || isNullOrEmpty(endTimeStr)) {
                request.setAttribute("error", "Input Error: Empty Input");
                request.getRequestDispatcher("RoomList").forward(request, response);
                return;
            }

            int quantity = Integer.parseInt(quantityStr);

            //float totalAmount = Float.parseFloat(totalAmountStr);
            float totalAmount;

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            java.util.Date startTime = format.parse(startTimeStr);
            java.util.Date endTime = format.parse(endTimeStr);

            java.sql.Date startTimeSQL = new java.sql.Date(startTime.getTime());
            java.sql.Date endTimeSQL = new java.sql.Date(endTime.getTime());

            //if (startTimeSQL.before(currentDate) || endTimeSQL.before(startTimeSQL) || endTimeSQL.before(startTimeSQL))
            if (endTimeSQL.before(startTimeSQL)) {
                request.setAttribute("error", "Date Error: End Date is before Start Date");
                request.getRequestDispatcher("RoomList").forward(request, response);
                return;
            }
            
            
            if (startTimeSQL.before(currentDate)) {
                request.setAttribute("error", "Date Error: Start Date is before Current Date");
                request.getRequestDispatcher("RoomList").forward(request, response);
                return;
            }

            //
            long diffInMillies = Math.abs(endTimeSQL.getTime() - startTimeSQL.getTime());
            long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

            if(diff <= 1){
                diff = 1;
            }
            
            totalAmount = quantity * diff * (float)room.getPrice();
            //

            Booking booking = new Booking(profile.getProfileID(), roomID, currentDate, totalAmount, "Booked");

            bookingDAO.Add(booking);

            BookingRoom bookingRoom = new BookingRoom(bookingDAO.lastBookingID(), roomID, quantity, startTimeSQL, endTimeSQL);

            bookingRoomDAO.Add(bookingRoom);
            
            request.setAttribute("message", "Room booked successfully");
            request.getRequestDispatcher("BookingList").forward(request, response);
        } catch (Exception ex) {
            System.out.println(ex.toString());
            //response.sendRedirect("exception");//exception error
            
            int roomID = Integer.parseInt(request.getParameter("roomID"));
            request.setAttribute("error", ex.toString());
            request.getRequestDispatcher("RoomList").forward(request, response);
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
