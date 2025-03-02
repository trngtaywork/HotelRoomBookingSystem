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
import java.util.Date;
import model.*;

/**
 *
 * @author My PC
 */
@WebServlet(name = "Register", urlPatterns = {"/Register"})
public class Register extends HttpServlet {

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
        request.getRequestDispatcher("Register.jsp").forward(request, response);//Chang to login
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
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Date temp = new Date();
        java.sql.Date createdDate = new java.sql.Date(temp.getTime());//get current date

        String fullName = request.getParameter("fullName");
        String phone = request.getParameter("phoneNumber");
        String gender = request.getParameter("gender");
        String role = request.getParameter("role");
        String address = request.getParameter("address");
        
        if(checkInputEmpty(username, email, password, fullName, phone, gender, role, address)){
            request.setAttribute("error", "Invalid input");
            request.getRequestDispatcher("Register.jsp").forward(request, response);
        }
        
        Account account = new Account(username, email, password, createdDate);
        //Profile profile = new Profile();

        if(accountDAO.ValidateInput(account))//validate
        {
            accountDAO.Add(account);
            Account added = accountDAO.SearchAccount(account.getUsername(), account.getEmail(), account.getPassword());
            
            profileDAO.Add(new Profile(fullName, phone, gender, role, address, added.getAccountID()));
        }
        else{
            request.getRequestDispatcher("Register.jsp").forward(request, response);
        }
        request.getRequestDispatcher("RoomList").forward(request, response);
        //account added but not profile
        /*if(!((Name.equals(null) || Name.isBlank()) &&
           (PhoneNumber.equals(null) || PhoneNumber.isBlank()) &&
           (Gender.equals(null) || Gender.isBlank()) &&
           (Role.equals(null) || Role.isBlank()) &&
           (Address.equals(null) || Address.isBlank())))// check if all is null
        if(
                !Name.isBlank() &&
                !PhoneNumber.isBlank() &&
                !Gender.isBlank() &&
                !Role.isBlank() &&
                !Address.isBlank()
            )// check if all is null
        {
            profile.setName(Name);
            profile.setPhoneNumber(PhoneNumber);
            profile.setGender(Gender);
            profile.setRole(Role);
            profile.setAddress(Address);
            profile.setAccountID(accountDAO.SearchAccount(Username, Email, Password).getAccountID());//<<<<<
            
            profileDAO.Add(profile);
        }
        else{
            request.getRequestDispatcher("Register.jsp").forward(request, response);
        }*/

        //request.getRequestDispatcher("RoomList").forward(request, response);
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

    private boolean checkInputEmpty(String username, String email, String password, String fullName, String phone, String gender, String role, String address){
        return username.isBlank() || email.isBlank() || password.isBlank() || fullName.isBlank() || phone.isBlank() || !phone.matches("[0-9]+") || gender.isBlank() || role.isBlank() || address.isBlank();
    }
}
