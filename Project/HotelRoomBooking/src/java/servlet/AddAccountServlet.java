package servlet;

import java.io.IOException;
import java.util.UUID;
import java.util.regex.Pattern;
import java.sql.Timestamp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.AccountDAO;
import java.sql.Date;
import model.Account;

public class AddAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AccountDAO accountDAO;

    public AddAccountServlet() {
        super();
        this.accountDAO = new AccountDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username").trim();
        String email = request.getParameter("email").trim();
        String password = request.getParameter("password").trim();
        String role = request.getParameter("role");
        String status = request.getParameter("status");

        boolean hasError = false;

        if (username.isEmpty()) {
            request.setAttribute("usernameError", "Username is required.");
            hasError = true;
        } else if (accountDAO.isUsernameTaken(username)) { 
            request.setAttribute("usernameError", "Username is already taken.");
            hasError = true;
        }

        if (email.isEmpty()) {
            request.setAttribute("emailError", "Email is required.");
            hasError = true;
        } else if (!isValidEmail(email)) {
            request.setAttribute("emailError", "Invalid email format.");
            hasError = true;
        }

        if (password.isEmpty()) {
            request.setAttribute("passwordError", "Password is required.");
            hasError = true;
        } else if (password.length() < 8) {
            request.setAttribute("passwordError", "Password must be at least 8 characters long.");
            hasError = true;
        }

        if (role == null || role.isEmpty()) {
            request.setAttribute("roleError", "Role is required.");
            hasError = true;
        }

        if (status == null || status.isEmpty()) {
            request.setAttribute("statusError", "Status is required.");
            hasError = true;
        }

        if (hasError) {
            request.getRequestDispatcher("addAccount.jsp").forward(request, response);
            return;
        }

        int accountID = generateRandomID();
        boolean isActive = Integer.parseInt(status) == 1;

        Account newAccount = new Account(accountID, username, email, password, new Date(System.currentTimeMillis()), role, isActive);
        boolean success = accountDAO.addAccount(newAccount); 

        if (success) {
            request.setAttribute("successMessage", "Account added successfully.");
        } else {
            request.setAttribute("errorMessage", "Error adding account.");
        }

        request.getRequestDispatcher("addAccount.jsp").forward(request, response);
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return Pattern.matches(emailRegex, email);
    }

    private int generateRandomID() {
        return Math.abs(UUID.randomUUID().hashCode());
    }
}
