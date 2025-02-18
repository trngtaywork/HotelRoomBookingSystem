package servlet;

import dao.RoomDAO;
import model.Room;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.UUID;

public class AddRoomServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get form data
        String roomName = request.getParameter("roomName");
        String description = request.getParameter("description");
        String priceStr = request.getParameter("price");
        String type = request.getParameter("type");
        String status = request.getParameter("status");
        Part filePart = request.getPart("roomImage");

        // Validate and process the price
        double price = 0.0;
        try {
            price = Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid price value.");
            request.getRequestDispatcher("/addRoom.jsp").forward(request, response);
            return;
        }

        // Process the uploaded image
        String image = "";
        if (filePart != null && filePart.getSize() > 0) {
            String fileName = UUID.randomUUID().toString() + "_" + filePart.getSubmittedFileName();
            String path = getServletContext().getRealPath("/uploads");
            filePart.write(path + "/" + fileName);
            image = "/uploads/" + fileName;
        }

        // Create Room object
        int roomID = (int) (Math.random() * 10000); // Random Room ID, ideally this should come from the DB
        Room room = new Room(roomID, roomName, description, price, image, status, type);

        // Add the room to the database
        RoomDAO roomDAO = new RoomDAO();
        boolean isAdded = roomDAO.addRoom(room);

        if (isAdded) {
            request.setAttribute("successMessage", "Room added successfully.");
        } else {
            request.setAttribute("errorMessage", "Failed to add room.");
        }

        // Forward back to addRoom.jsp
        request.getRequestDispatcher("/addRoom.jsp").forward(request, response);
    }
}
