package servlet;

import dao.RoomDAO;
import model.Room;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class RoomListForAdminServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RoomDAO roomDAO = new RoomDAO();
        List<Room> rooms = roomDAO.getAllRooms();  

        request.setAttribute("rooms", rooms);
        
        request.getRequestDispatcher("/roomListForAdmin.jsp").forward(request, response);
    }
}