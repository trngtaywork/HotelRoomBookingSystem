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
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.*;
import java.io.File;

/**
 *
 * @author My PC
 */
@MultipartConfig
@WebServlet(name = "AddService", urlPatterns = {"/AddService"})
public class AddService extends HttpServlet {

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
        request.getRequestDispatcher("AddService.jsp").forward(request, response);
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
        String serviceName = request.getParameter("serviceName");//is null -> why???????
        String description = request.getParameter("description");//is null -> why???????
        String priceStr = request.getParameter("price");//is null -> why???????
        String statusService = request.getParameter("statusService");
        String typeService = request.getParameter("typeService");
        Part filePart = request.getPart("serviceImage");

        serviceName = serviceName.trim();
        description = description.trim();
        priceStr = priceStr.trim();

        // Validate and process the price
        float price = 0;
        try {
            price = Float.parseFloat(priceStr);
            if (price <= 0) {
                request.setAttribute("errorMessage", "Price must be greater than 0.");
                request.getRequestDispatcher("AddService.jsp").forward(request, response);
                return;
            }
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid price value.");
            request.getRequestDispatcher("AddService.jsp").forward(request, response);
            return;
        }

        // Validate Service Name
        boolean isServiceNameExists = serviceDAO.SearchIfServiceExistByName(serviceName);
        if (isServiceNameExists) {
            request.setAttribute("errorMessage", "Service name already exists. Please choose a different name.");
            request.getRequestDispatcher("AddService.jsp").forward(request, response);
            return;
        }

        // Validate and process the uploaded image
        String image = "";
        if (filePart != null && filePart.getSize() > 0) {
            String fileName = filePart.getSubmittedFileName();
            List<String> allowedExtensions = Arrays.asList("jpg", "jpeg", "png");
            String fileExtension = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();

            if (!allowedExtensions.contains(fileExtension)) {
                request.setAttribute("errorMessage", "Only image files (jpg, jpeg, png) are allowed.");
                request.getRequestDispatcher("AddService.jsp").forward(request, response);
                return;
            }

            // Save the image
            String path = getServletContext().getRealPath("/") + "../web/img/service";
            File directory = new File(path);
            if (!directory.exists()) {
                directory.mkdirs(); // Ensure the directory exists
            }
            filePart.write(path + "/" + fileName);
            image = "/img/service/" + fileName;
        }

        // Create Service object
        Service service = new Service(serviceName, description, price, image, statusService, typeService);

        // Add the service to the database
        serviceDAO.Add(service);
        //boolean isAdded = serviceDAO.;

        /*
        if (isAdded) {
            request.setAttribute("successMessage", "Room added successfully.");
        } else {
            request.setAttribute("errorMessage", "Failed to add room.");
        }
         */
        // Forward back to ServiceListAdmin
        request.getRequestDispatcher("ServiceListAdmin").forward(request, response);
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
