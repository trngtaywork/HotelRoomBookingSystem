package servlet;

import dao.AccountDAO;
import model.Account;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class AddAccountServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get form data
        String username = request.getParameter("username").trim();
        String email = request.getParameter("email").trim();
        String password = request.getParameter("password").trim();
        String role = request.getParameter("role");
        String status = request.getParameter("status");

        // Initialize error message variables
        String errorMessage = null;
        String successMessage = null;

        // Validate inputs
        if (username.isEmpty()) {
            errorMessage = "Username is required.";
        } else if (email.isEmpty() || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            errorMessage = "Please enter a valid email.";
        } else if (password.isEmpty() || password.length() < 8 || password.contains(" ")) {
            errorMessage = "Password must be at least 8 characters long and cannot contain spaces.";
        }

        // If there are validation errors, redirect back with error message
        if (errorMessage != null) {
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("addAccount.jsp").forward(request, response);
        } else {
            // If validation passes, insert into the database
            AccountDAO accountDAO = new AccountDAO();
            Account newAccount = new Account();
            newAccount.setUsername(username);
            newAccount.setEmail(email);
            newAccount.setPassword(password);
            newAccount.setRole(role);
            newAccount.setIsActive(status.equals("1")); // Convert status to boolean (Active = true)
            newAccount.setCreatedDate(new java.sql.Date(System.currentTimeMillis()));

            boolean success = accountDAO.addAccount(newAccount);

            // Set success or error message
            if (success) {
                successMessage = "Account added successfully!";
                request.setAttribute("successMessage", successMessage);
                response.sendRedirect("userList.jsp");
            } else {
                errorMessage = "An error occurred while adding the account.";
                request.setAttribute("errorMessage", errorMessage);
                request.getRequestDispatcher("addAccount.jsp").forward(request, response);
            }
        }
    }
}
