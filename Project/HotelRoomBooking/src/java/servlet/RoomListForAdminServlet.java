package servlet;

import dao.RoomDAO;
import model.Room;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class RoomListForAdminServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String priceFilter = request.getParameter("priceFilter");
        String statusFilter = request.getParameter("statusFilter");
        String typeFilter = request.getParameter("typeFilter");
        String roomNameFilter = request.getParameter("roomNameFilter");

        if (priceFilter == null) {
            priceFilter = "All";
        }
        if (statusFilter == null) {
            statusFilter = "All";
        }
        if (typeFilter == null) {
            typeFilter = "All";
        }
        if (roomNameFilter == null) {
            roomNameFilter = "";
        }

        RoomDAO roomDAO = new RoomDAO();
        List<Room> roomList = roomDAO.getFilteredRooms(priceFilter, statusFilter, typeFilter, roomNameFilter);

        int pageSize = 10;
        int totalItems = roomList.size();
        int totalPages = (int) Math.ceil((double) totalItems / pageSize);

        int currentPage = 1;
        if (request.getParameter("page") != null) {
            currentPage = Integer.parseInt(request.getParameter("page"));
        }

        int startIndex = (currentPage - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, totalItems);
        List<Room> paginatedRoomList = roomList.subList(startIndex, endIndex);

        request.setAttribute("roomList", paginatedRoomList);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);

        request.getRequestDispatcher("/roomListForAdmin.jsp").forward(request, response);
    }

}
