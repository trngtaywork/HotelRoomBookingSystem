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
import java.util.List;
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
        request.getRequestDispatcher("Register.jsp").forward(request, response);//Change to login
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
        request.setAttribute("error", null);
        
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Date temp = new Date();
        java.sql.Date createdDate = new java.sql.Date(temp.getTime());//get current date
        String role = "Customer";
        boolean isActive = true;

        String fullName = request.getParameter("fullName");
        String phone = request.getParameter("phoneNumber");
        String gender = request.getParameter("gender");
        String address = request.getParameter("address");
        
        if(checkInputEmpty(username, email, password, fullName, phone, gender, address)){
            request.setAttribute("error", "Invalid Register input");
            request.getRequestDispatcher("Register.jsp").forward(request, response);
            return;
        }
        
        
        Account account = new Account(username, email, password, createdDate, role, isActive);
        //Profile profile = new Profile();

        if(accountDAO.ValidateInput(account))//validate
        {
            accountDAO.Add(account);
            Account added = accountDAO.SearchAccount(username, email, password);
            
            if(added == null){
                request.setAttribute("error", "Failed to insert Account");
                request.getRequestDispatcher("Register.jsp").forward(request, response);
                return;
            }
            
            Profile newProfile = new Profile(fullName, phone, gender, address, added.getAccountID());
            profileDAO.Add(newProfile);
            
            //test
            newProfile.setProfileID(-1);
            List<Profile> addedProfile = profileDAO.SearchProfile(newProfile);
            if(addedProfile.isEmpty()){
                accountDAO.Delete(added.getAccountID());
                request.setAttribute("error", "Failed to insert profile || " + added.getAccountID());
                request.getRequestDispatcher("Register.jsp").forward(request, response);
                return;
            }
            //end test
        }
        else{
            request.setAttribute("error", "Failed to validate input");
            request.getRequestDispatcher("Register.jsp").forward(request, response);
            return;
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

    private boolean checkInputEmpty(String username, String email, String password, String fullName, String phone, String gender, String address){
        return username.isBlank() || email.isBlank() || password.isBlank() || fullName.isBlank() || phone.isBlank() || !phone.matches("[0-9]+") || gender.isBlank() || address.isBlank();
    }
}
