package servlet;

import dao.RoomDAO;
import model.Room;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class EditRoomServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy roomID từ request và kiểm tra tính hợp lệ
        String roomIDStr = request.getParameter("roomID");

        // Kiểm tra xem roomID có bị null hoặc rỗng không
        if (roomIDStr == null || roomIDStr.isEmpty()) {
            request.setAttribute("errorMessage", "Room ID is missing.");
            request.getRequestDispatcher("/editRoom.jsp").forward(request, response);
            return;
        }

        int roomID = 0;
        try {
            roomID = Integer.parseInt(roomIDStr);  // Chuyển roomID từ String sang int
        } catch (NumberFormatException e) {
            // Nếu roomID không thể chuyển thành int, thông báo lỗi
            request.setAttribute("errorMessage", "Invalid Room ID.");
            request.getRequestDispatcher("/editRoom.jsp").forward(request, response);
            return;
        }

        // Tiếp tục xử lý các trường nhập liệu khác
        String roomName = request.getParameter("roomName").trim();
        String description = request.getParameter("description").trim();
        double price = 0.0;
        String priceStr = request.getParameter("price").trim();
        String type = request.getParameter("type");
        String status = request.getParameter("status");
        Part filePart = request.getPart("roomImage");

        // Validate price
        try {
            price = Double.parseDouble(priceStr);
            if (price <= 0) {
                request.setAttribute("errorMessage", "Price must be greater than 0.");
                request.getRequestDispatcher("/editRoom.jsp?roomID=" + roomID).forward(request, response);
                return;
            }
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid price value.");
            request.getRequestDispatcher("/editRoom.jsp?roomID=" + roomID).forward(request, response);
            return;
        }

        // Kiểm tra tên phòng, ảnh, và các thông tin khác trước khi cập nhật...

        RoomDAO roomDAO = new RoomDAO();
        boolean isUpdated = roomDAO.updateRoom(new Room(roomID, roomName, description, price, "", status, type));

        if (isUpdated) {
            response.sendRedirect("roomListForAdmin.jsp");
        } else {
            request.setAttribute("errorMessage", "Failed to update room.");
            request.getRequestDispatcher("/editRoom.jsp?roomID=" + roomID).forward(request, response);
        }
    }
}
