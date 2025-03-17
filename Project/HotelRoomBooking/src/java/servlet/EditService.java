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
import dao.*;
import model.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.io.File;

/**
 *
 * @author My PC
 */
@WebServlet(name = "EditService", urlPatterns = {"/EditService"})
public class EditService extends HttpServlet {
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
        int serviceID = Integer.parseInt(request.getParameter("serviceID"));
        
        Service service = serviceDAO.SearchServiceByID(serviceID);
        
        if(request.getParameter("errorMessage") != null){
            request.setAttribute("errorMessage", request.getParameter("errorMessage"));
        }
        request.setAttribute("service", service);
        request.getRequestDispatcher("EditService.jsp").forward(request, response);
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
        String serviceIDStr = request.getParameter("serviceID");
        if (serviceIDStr == null || serviceIDStr.isEmpty()) {
            request.setAttribute("errorMessage", "Service ID is missing.");
            request.getRequestDispatcher("/EditService.jsp").forward(request, response);
            return;
        }

        int serviceID;
        try {
            serviceID = Integer.parseInt(serviceIDStr);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid Service ID.");
            request.getRequestDispatcher("/EditRoom?roomID=" + serviceIDStr).forward(request, response);
            return;
        }

        String serviceName = request.getParameter("serviceName").trim();
        String description = request.getParameter("description").trim();
        String priceStr = request.getParameter("price").trim();
        String status = request.getParameter("status");

        boolean hasError = false;

        if (serviceName.isEmpty()) {
            request.setAttribute("errorMessage", "Service Name is required.");
            hasError = true;
        } else if (serviceDAO.SearchIfServiceExistByName(serviceName)) {
            request.setAttribute("errorMessage", "Service Name already exists.");
            hasError = true;
        }

        float price = 0;
        try {
            price = Float.parseFloat(priceStr);
            if (price <= 0) {
                request.setAttribute("errorMessage", "Price must be greater than 0.");
                hasError = true;
            }
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid price value.");
            hasError = true;
        }

        if (hasError) {
            request.getRequestDispatcher("/EditService?serviceID=" + serviceID).forward(request, response);
            return;
        }

        Service existingService = serviceDAO.SearchServiceByID(serviceID);
        if (existingService == null) {
            request.setAttribute("errorMessage", "Service not found.");
            request.getRequestDispatcher("/ServiceListAdmin").forward(request, response);
            return;
        }

        Part filePart = request.getPart("serviceImage");
        String imagePath = existingService.getImage();

        if (filePart != null && filePart.getSize() > 0) {
            String fileName = extractFileName(filePart);
            String uploadDir = getServletContext().getRealPath("") + File.separator + "images";
            File uploadFolder = new File(uploadDir);
            if (!uploadFolder.exists()) {
                uploadFolder.mkdir();
            }
            filePart.write(uploadDir + File.separator + fileName);
            imagePath = "/images/" + fileName;
        }

        Service updatedService = new Service(serviceID, serviceName, description, price, imagePath, status);
        try {
            boolean isUpdated = serviceDAO.Update(updatedService);
            if (isUpdated) {
                response.sendRedirect("ServiceListAdmin");
            } else {
                request.setAttribute("errorMessage", "Failed to update service.");
                request.getRequestDispatcher("/EditService?serviceID=" + serviceID).forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Database error: " + e.getMessage());
            request.getRequestDispatcher("/EditService?serviceID=" + serviceID).forward(request, response);
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

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        for (String content : contentDisp.split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
            }
        }
        return "";
    }
}
