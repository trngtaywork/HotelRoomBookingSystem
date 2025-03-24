package servlet;

import dao.AccountDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class DeleteUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int accountID = Integer.parseInt(request.getParameter("accountID"));
        AccountDAO accountDAO = new AccountDAO();

        // Call delete method in AccountDAO to delete from both Account and Profile tables
        boolean isDeleted = accountDAO.deleteAccount(accountID);

        if (isDeleted) {
            response.sendRedirect("userList.jsp");
        } else {
            response.getWriter().println("Error deleting user");
        }
    }
}
