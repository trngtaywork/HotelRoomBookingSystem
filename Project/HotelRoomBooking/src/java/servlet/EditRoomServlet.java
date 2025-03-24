package servlet;

import dao.RoomDAO;
import model.Room;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

@MultipartConfig
public class EditRoomServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private RoomDAO roomDAO;

    public EditRoomServlet() {
        this.roomDAO = new RoomDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String roomIDStr = request.getParameter("roomID");
        if (roomIDStr == null || roomIDStr.isEmpty()) {
            request.setAttribute("errorMessage", "Room ID is missing.");
            request.getRequestDispatcher("/editRoom.jsp").forward(request, response);
            return;
        }

        int roomID;
        try {
            roomID = Integer.parseInt(roomIDStr);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid Room ID.");
            request.getRequestDispatcher("/editRoom.jsp?roomID=" + roomIDStr).forward(request, response);
            return;
        }

        String roomName = request.getParameter("roomName").trim();
        String description = request.getParameter("description").trim();
        String priceStr = request.getParameter("price").trim();
        String type = request.getParameter("type");
        String status = request.getParameter("status");

        boolean hasError = false;

        if (roomName.isEmpty()) {
            request.setAttribute("roomNameError", "Room Name is required.");
            hasError = true;
        } else if (roomDAO.isRoomNameExists(roomName, roomID)) {
            request.setAttribute("roomNameError", "Room name already exists.");
            hasError = true;
        }

        if (description.isEmpty()) {
            request.setAttribute("descriptionError", "Description is required.");
            hasError = true;
        }

        double price = 0.0;
        try {
            price = Double.parseDouble(priceStr);
            if (price <= 0) {
                request.setAttribute("priceError", "Price must be greater than 0.");
                hasError = true;
            }
        } catch (NumberFormatException e) {
            request.setAttribute("priceError", "Invalid price value.");
            hasError = true;
        }

        if (hasError) {
            // Lưu lại dữ liệu người dùng nhập vào khi có lỗi
            request.setAttribute("roomName", roomName);
            request.setAttribute("description", description);
            request.setAttribute("price", priceStr);
            request.setAttribute("type", type);
            request.setAttribute("status", status);

            request.getRequestDispatcher("/editRoom.jsp?roomID=" + roomID).forward(request, response);
            return;
        }

        Room existingRoom = roomDAO.getRoomById(roomID);
        if (existingRoom == null) {
            request.setAttribute("errorMessage", "Room not found.");
            request.getRequestDispatcher("/roomListForAdmin.jsp").forward(request, response);
            return;
        }

        Part filePart = request.getPart("roomImage");
        String imagePath = existingRoom.getImage();

        if (filePart != null && filePart.getSize() > 0) {
            String fileName = extractFileName(filePart);
            String uploadDir = getServletContext().getRealPath("") + File.separator + "images";
            File uploadFolder = new File(uploadDir);
            if (!uploadFolder.exists()) {
                uploadFolder.mkdir();
            }
            filePart.write(uploadDir + File.separator + fileName);
            imagePath = "/images/" + fileName;
        }

        Room updatedRoom = new Room(roomID, roomName, description, price, imagePath, status, type);
        try {
            boolean isUpdated = roomDAO.updateRoom(updatedRoom);
            if (isUpdated) {
                response.sendRedirect("roomListForAdmin.jsp");
            } else {
                request.setAttribute("errorMessage", "Failed to update room.");
                request.getRequestDispatcher("/editRoom.jsp?roomID=" + roomID).forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Database error: " + e.getMessage());
            request.getRequestDispatcher("/editRoom.jsp?roomID=" + roomID).forward(request, response);
        }
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        for (String content : contentDisp.split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
            }
        }
        return "";
    }
}
