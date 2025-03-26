package servlet;

import dao.AccountDAO;
import model.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class ChangePasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AccountDAO accountDAO;

    public ChangePasswordServlet() {
        this.accountDAO = new AccountDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        Account user = (Account) session.getAttribute("user");
        int accountID = user.getAccountID();

        String oldPassword = request.getParameter("oldPassword").trim();
        String newPassword = request.getParameter("newPassword").trim();
        String confirmPassword = request.getParameter("confirmPassword").trim();

        boolean hasError = false;

        String currentPassword = accountDAO.getPasswordByAccountID(accountID);
        if (!currentPassword.equals(oldPassword)) {
            request.setAttribute("oldPasswordError", "Current password is incorrect.");
            hasError = true;
        }

        if (newPassword.length() < 8) {
            request.setAttribute("newPasswordError", "New password must be at least 8 characters.");
            hasError = true;
        }

        if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("confirmPasswordError", "Passwords do not match.");
            hasError = true;
        }

        if (hasError) {
            request.getRequestDispatcher("changePassword.jsp").forward(request, response);
            return;
        }

        boolean isUpdated = accountDAO.updateUserPassword(accountID, newPassword);
        if (isUpdated) {
            request.setAttribute("successMessage", "Password changed successfully.");
            request.getRequestDispatcher("changePassword.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Failed to change password.");
            request.getRequestDispatcher("changePassword.jsp").forward(request, response);
        }
    }
}
