package servlet;

import dao.RoomDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class DeleteRoomServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int roomID = Integer.parseInt(request.getParameter("roomID"));
        RoomDAO roomDAO = new RoomDAO();

        boolean isDeleted = roomDAO.deleteRoom(roomID);

        if (isDeleted) {
            response.sendRedirect("roomListForAdmin.jsp");
        } else {
            response.getWriter().println("Error deleting room");
        }
    }
}
