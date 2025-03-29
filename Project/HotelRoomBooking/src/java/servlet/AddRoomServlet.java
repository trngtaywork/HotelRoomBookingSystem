package servlet;

import dao.RoomDAO;
import model.Room;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class AddRoomServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get form data
        String roomName = request.getParameter("roomName").trim();
        String description = request.getParameter("description").trim();
        String priceStr = request.getParameter("price").trim();
        String type = request.getParameter("type");
        String status = request.getParameter("status");
        Part filePart = request.getPart("roomImage");

        // Validate and process the price
        double price = 0.0;
        try {
            price = Double.parseDouble(priceStr);
            if (price <= 0) {
                request.setAttribute("errorMessage", "Price must be greater than 0.");
                request.getRequestDispatcher("/addRoom.jsp").forward(request, response);
                return;
            }
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid price value.");
            request.getRequestDispatcher("/addRoom.jsp").forward(request, response);
            return;
        }
        
        // Validate Room Name
        RoomDAO roomDAO = new RoomDAO();
        boolean isRoomNameExists = roomDAO.isRoomNameExists(roomName);
        if (isRoomNameExists) {
            request.setAttribute("errorMessage", "Room name already exists. Please choose a different name.");
            request.getRequestDispatcher("/addRoom.jsp").forward(request, response);
            return;
        }

        // Validate and process the uploaded image
        String image = "";
        if (filePart != null && filePart.getSize() > 0) {
            String fileName = filePart.getSubmittedFileName();
            List<String> allowedExtensions = Arrays.asList("jpg", "jpeg", "png");
            String fileExtension = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();

            if (!allowedExtensions.contains(fileExtension)) {
                request.setAttribute("errorMessage", "Only image files (jpg, jpeg, png) are allowed.");
                request.getRequestDispatcher("/addRoom.jsp").forward(request, response);
                return;
            }

            // Save the image
            String path = getServletContext().getRealPath("/uploads");
            fileName = UUID.randomUUID().toString() + "_" + fileName;
            filePart.write(path + "/" + fileName);
            image = "/uploads/" + fileName;
        }

        // Create Room object
        int roomID = (int) (Math.random() * 10000); // Random Room ID, ideally this should come from the DB
        Room room = new Room(roomID, roomName, description, price, image, status, type);

        // Add the room to the database
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
