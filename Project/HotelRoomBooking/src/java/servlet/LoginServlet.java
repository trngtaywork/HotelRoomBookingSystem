package servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.AccountDAO;
import model.Account;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Tạo đối tượng AccountDAO để xác thực tài khoản
        AccountDAO accountDAO = new AccountDAO();
        Account user = accountDAO.validateLogin(username, password);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            String role = user.getRole();
            if ("Admin".equals(role)) {
                response.sendRedirect("profile.jsp");
            } else if ("Customer".equals(role)) {
                response.sendRedirect("profileCustomer.jsp");
            } else if ("Receptionist".equals(role)) {
                response.sendRedirect("profileReceptionist.jsp");
            }
        } else {
            request.setAttribute("error", "Invalid username or password!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
