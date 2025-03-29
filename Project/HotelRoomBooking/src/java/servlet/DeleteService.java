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
import model.*;
import dao.*;
import java.util.List;

/**
 *
 * @author My PC
 */
@WebServlet(name = "DeleteService", urlPatterns = {"/DeleteService"})
public class DeleteService extends HttpServlet {
    ServiceDAO serviceDAO = new ServiceDAO();
    BookingDAO bookingDAO = new BookingDAO();
    BookingServiceDAO bookingServiceDAO = new BookingServiceDAO();
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
        int serviceID = Integer.parseInt(request.getParameter("serviceID"));
        
        Service service = serviceDAO.SearchServiceByID(serviceID);
        
        //check current service booking
        List<Booking> bookingList1 = bookingDAO.SearchBooking("StatusBooking", "Booked");
        List<Booking> bookingList2 = bookingDAO.SearchBooking("StatusBooking", "Staying");

        if (bookingList1 != null || bookingList2 != null) {
            request.setAttribute("errorMessage", "This Service is currently in use. Are you sure you want to delete this?");
        }
        
        request.setAttribute("service", service);
        request.getRequestDispatcher("DeleteService.jsp").forward(request, response);
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
        int serviceID = Integer.parseInt(request.getParameter("serviceID"));

        boolean isDeleted = serviceDAO.Delete(serviceID);

        if (isDeleted) {
            response.sendRedirect("ServiceListAdmin");
        } else {
            response.getWriter().println("Error deleting service");
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
