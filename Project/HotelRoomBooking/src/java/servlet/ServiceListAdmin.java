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
import java.util.ArrayList;
import java.util.List;
import model.*;

/**
 *
 * @author My PC
 */
@WebServlet(name = "ServiceListAdmin", urlPatterns = {"/ServiceListAdmin"})
public class ServiceListAdmin extends HttpServlet {

    BookingDAO bookingDao = new BookingDAO();
    BookingServiceDAO bookingServiceDAO = new BookingServiceDAO();
    AccountDAO accountDao = new AccountDAO();
    ProfileDAO profileDAO = new ProfileDAO();
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

        String errorMessage = request.getParameter("errorMessage") == null || request.getParameter("errorMessage").equals("") ? "" : request.getParameter("errorMessage").trim();

        if (!errorMessage.equals("")) {
            request.setAttribute("errorMessage", errorMessage);
        }

        List<Service> serviceList = serviceDAO.GetServiceList();
        List<BookingService> bookingServiceList = bookingServiceDAO.GetBookingServiceList();

        int pageSize = 10;
        int totalItems = serviceList.size();
        int totalPages = (int) Math.ceil((double) totalItems / pageSize);

        int currentPage = 1;
        if (request.getParameter("page") != null) {
            currentPage = Integer.parseInt(request.getParameter("page"));
        }
        
        int startIndex = (currentPage - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, totalItems);
        List<Service> paginatedServiceList = serviceList.subList(startIndex, endIndex);
        
        request.setAttribute("serviceList", paginatedServiceList);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);
        
        //request.setAttribute("serviceList", serviceList);
        request.setAttribute("bookingServiceList", bookingServiceList);

        request.getRequestDispatcher("ServiceListAdmin.jsp").forward(request, response);
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
        String priceFilter = request.getParameter("priceFilter") == null ? "" : request.getParameter("priceFilter").trim();
        String statusFilter = request.getParameter("statusFilter") == null ? "" : request.getParameter("statusFilter").trim();
        String serviceNameFilter = request.getParameter("serviceNameFilter") == null ? "" : request.getParameter("serviceNameFilter").trim();

        List<Service> serviceList = new ArrayList<Service>();

        serviceList = serviceDAO.SearchServices(serviceNameFilter, statusFilter, priceFilter);

        int pageSize = 10;
        int totalItems = serviceList.size();
        int totalPages = (int) Math.ceil((double) totalItems / pageSize);

        int currentPage = 1;
        if (request.getParameter("page") != null) {
            currentPage = Integer.parseInt(request.getParameter("page"));
        }
        
        int startIndex = (currentPage - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, totalItems);
        List<Service> paginatedServiceList = serviceList.subList(startIndex, endIndex);
        
        request.setAttribute("serviceList", paginatedServiceList);
        
        //equest.setAttribute("serviceList", serviceList);

        request.getRequestDispatcher("ServiceListAdmin.jsp").forward(request, response);
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
