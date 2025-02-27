package servlet;

import dao.RoomDAO;
import model.Room;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.Arrays;

public class EditRoomServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int roomID = Integer.parseInt(request.getParameter("roomID"));
        String roomName = request.getParameter("roomName").trim(); // Remove leading/trailing spaces
        String description = request.getParameter("description").trim(); // Remove leading/trailing spaces
        String priceStr = request.getParameter("price").trim(); // Remove leading/trailing spaces
        String type = request.getParameter("type");
        String status = request.getParameter("status");
        Part filePart = request.getPart("roomImage");

        double price = 0.0;
        try {
            price = Double.parseDouble(priceStr);
            if (price <= 0) {
                request.setAttribute("errorMessage", "Price must be greater than 0.");
                request.getRequestDispatcher("editRoom.jsp?roomID=" + roomID).forward(request, response);
                return;
            }
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid price value.");
            request.getRequestDispatcher("editRoom.jsp?roomID=" + roomID).forward(request, response);
            return;
        }

        // Validate Room Name (Check if room name already exists)
        RoomDAO roomDAO = new RoomDAO();
        boolean isRoomNameExists = roomDAO.isRoomNameExists(roomName, roomID);  // Ensure room name does not exist for other rooms
        if (isRoomNameExists) {
            request.setAttribute("errorMessage", "Room name already exists. Please choose a different name.");
            request.getRequestDispatcher("editRoom.jsp?roomID=" + roomID).forward(request, response);
            return;
        }

        // Validate Room Image (Only image files)
        String image = "";
        if (filePart != null && filePart.getSize() > 0) {
            String fileName = filePart.getSubmittedFileName();
            List<String> allowedExtensions = Arrays.asList("jpg", "jpeg", "png", "gif");
            String fileExtension = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();

            if (!allowedExtensions.contains(fileExtension)) {
                request.setAttribute("errorMessage", "Only image files (jpg, jpeg, png, gif) are allowed.");
                request.getRequestDispatcher("editRoom.jsp?roomID=" + roomID).forward(request, response);
                return;
            }

            // Save the image
            String path = getServletContext().getRealPath("/uploads");
            fileName = roomID + "_" + fileName;
            filePart.write(path + "/" + fileName);
            image = "/uploads/" + fileName;
        }

        // Update Room object and update the room in database
        Room room = new Room(roomID, roomName, description, price, image, status, type);
        boolean isUpdated = roomDAO.updateRoom(room);

        if (isUpdated) {
            // Set success message
            HttpSession session = request.getSession();
            session.setAttribute("successMessage", "Room updated successfully.");
            response.sendRedirect("RoomListForAdmin.jsp"); // Redirect to the list page
        } else {
            request.setAttribute("errorMessage", "Failed to update room.");
            request.getRequestDispatcher("editRoom.jsp?roomID=" + roomID).forward(request, response);
        }
    }
}
