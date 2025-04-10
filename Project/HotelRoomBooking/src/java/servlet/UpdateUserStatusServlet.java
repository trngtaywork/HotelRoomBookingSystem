package servlet;

import dao.AccountDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "UpdateUserStatusServlet", urlPatterns = {"/UpdateUserStatusServlet"})
public class UpdateUserStatusServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int accountID = Integer.parseInt(request.getParameter("accountID"));
        boolean isActive = Boolean.parseBoolean(request.getParameter("isActive"));

        AccountDAO accountDAO = new AccountDAO();
        boolean isUpdated = accountDAO.updateUserStatus(accountID, isActive);

        if (isUpdated) {
            response.sendRedirect("userList.jsp");
        } else {
            response.getWriter().println("Error updating status");
        }
    }
}