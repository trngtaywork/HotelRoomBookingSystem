package servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import dao.AccountDAO;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        Account sessionUser = (Account) session.getAttribute("user");
        AccountDAO accountDAO = new AccountDAO();
        Account fullAccount = accountDAO.getAccountById(sessionUser.getAccountID());

        if (fullAccount != null) {
            session.setAttribute("user", fullAccount);
        }

        request.getRequestDispatcher("profile.jsp").forward(request, response);
    }
}
