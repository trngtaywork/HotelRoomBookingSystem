package servlet;

import dao.AccountDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EditUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int accountID = Integer.parseInt(request.getParameter("accountID"));
            String username = request.getParameter("username").trim();
            String email = request.getParameter("email").trim();
            String password = request.getParameter("password").trim();
            String role = request.getParameter("role");
            boolean isActive = request.getParameter("isActive").equals("1");

            AccountDAO accountDAO = new AccountDAO();

            // Validate input
            if (username.isEmpty()) {
                request.setAttribute("errorMessage", "Username cannot be empty.");
                request.getRequestDispatcher("editUser.jsp?accountID=" + accountID).forward(request, response);
                return;
            }

            // Kiểm tra username trùng lặp nhưng bỏ qua tài khoản hiện tại
            if (accountDAO.isUsernameExists(username, accountID)) {
                request.setAttribute("errorMessage", "Username already exists. Please choose another.");
                request.getRequestDispatcher("editUser.jsp?accountID=" + accountID).forward(request, response);
                return;
            }

            if (email.isEmpty()) {
                request.setAttribute("errorMessage", "Email cannot be empty.");
                request.getRequestDispatcher("editUser.jsp?accountID=" + accountID).forward(request, response);
                return;
            }

            if (!password.isEmpty() && password.length() < 8) {
                request.setAttribute("errorMessage", "Password must be at least 8 characters long.");
                request.getRequestDispatcher("editUser.jsp?accountID=" + accountID).forward(request, response);
                return;
            }

            // Nếu mật khẩu trống, giữ nguyên mật khẩu cũ
            if (password.isEmpty()) {
                password = accountDAO.getPasswordByAccountID(accountID);
            }

            boolean updateAccount = accountDAO.updateUser(accountID, username, email, password, role, isActive);

            if (updateAccount) {
                request.setAttribute("successMessage", "User updated successfully.");
            } else {
                request.setAttribute("errorMessage", "Failed to update user.");
            }

            request.getRequestDispatcher("userList.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred.");
            request.getRequestDispatcher("userList.jsp").forward(request, response);
        }
    }
}
