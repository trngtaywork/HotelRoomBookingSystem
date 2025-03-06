package dao;

import java.sql.Connection;
import model.Room;
import utils.DBContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {

    private DBContext dbContext;

    public RoomDAO() {
        dbContext = new DBContext();
    }

    // Method to check if room name already exists (excluding current room)
    public boolean isRoomNameExists(String roomName, int currentRoomID) {
        String query = "SELECT COUNT(*) FROM Room WHERE RoomName = ? AND RoomID != ?";
        try {
            PreparedStatement stmt = dbContext.connection.prepareStatement(query);
            stmt.setString(1, roomName);
            stmt.setInt(2, currentRoomID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Room name already exists
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    // Method to update room information
    public boolean updateRoom(Room room) {
    String query = "UPDATE Room SET RoomName = ?, Description = ?, Price = ?, Image = ?, StatusRoom = ?, TypeRoom = ? WHERE RoomID = ?";
    boolean isUpdated = false;

    // Sử dụng try-with-resources để tự động đóng PreparedStatement
    try (PreparedStatement stmt = dbContext.connection.prepareStatement(query)) {
        // Thiết lập các tham số cho PreparedStatement
        stmt.setObject(1, room.getRoomName());
        stmt.setObject(2, room.getDescription());
        stmt.setObject(3, room.getPrice());
        stmt.setObject(4, room.getImage());
        stmt.setObject(5, room.getStatusRoom());
        stmt.setObject(6, room.getTypeRoom());
        stmt.setObject(7, room.getRoomID());

        // Thực thi câu lệnh và kiểm tra xem có bao nhiêu bản ghi được cập nhật
        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            isUpdated = true;  // Nếu có ít nhất 1 bản ghi được cập nhật
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }

    return isUpdated;
}


    // Method to get room by ID
    public Room getRoomById(int roomID) {
        Room room = null;
        String query = "SELECT * FROM Room WHERE RoomID = ?";
        try {
            PreparedStatement stmt = dbContext.connection.prepareStatement(query);
            stmt.setInt(1, roomID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                room = new Room(
                        rs.getInt("RoomID"),
                        rs.getString("RoomName"),
                        rs.getString("Description"),
                        rs.getDouble("Price"),
                        rs.getString("Image"),
                        rs.getString("StatusRoom"),
                        rs.getString("TypeRoom")
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return room;
    }

    public boolean isRoomNameExists(String roomName) {
        String query = "SELECT COUNT(*) FROM Room WHERE RoomName = ?";
        try {
            PreparedStatement stmt = dbContext.connection.prepareStatement(query);
            stmt.setString(1, roomName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    // Method to insert room into database
    public boolean addRoom(Room room) {
        String query = "INSERT INTO Room (RoomID, RoomName, Description, Price, Image, StatusRoom, TypeRoom) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = dbContext.connection.prepareStatement(query);
            stmt.setInt(1, room.getRoomID());
            stmt.setString(2, room.getRoomName());
            stmt.setString(3, room.getDescription());
            stmt.setDouble(4, room.getPrice());
            stmt.setString(5, room.getImage());
            stmt.setString(6, room.getStatusRoom());
            stmt.setString(7, room.getTypeRoom());

            int result = stmt.executeUpdate();

            return result > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // Fetch all rooms from DB
    public List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM Room";
        ResultSet rs = dbContext.getData(sql);

        try {
            while (rs.next()) {
                int roomID = rs.getInt("roomID");
                String roomName = rs.getString("roomName");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                String image = rs.getString("image");
                String status = rs.getString("status");
                String type = rs.getString("type");

                Room room = new Room(roomID, roomName, description, price, image, status, type);
                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    public List<Room> getAll() {
        List<Room> roomList = new ArrayList<>();
        String query = "SELECT * FROM Room";
        try {
            Statement stmt = dbContext.connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int roomID = rs.getInt("RoomID");
                String roomName = rs.getString("RoomName");
                String description = rs.getString("Description");
                double price = rs.getDouble("Price");
                String image = rs.getString("Image");
                String statusRoom = rs.getString("StatusRoom");
                String typeRoom = rs.getString("TypeRoom");

                // Tạo đối tượng Room và thêm vào danh sách
                Room room = new Room(roomID, roomName, description, price, image, statusRoom, typeRoom);
                roomList.add(room);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return roomList;
    }

    // Method to delete room by ID
    public boolean deleteRoom(int roomID) {
        String query = "DELETE FROM Room WHERE RoomID = ?";
        try {
            PreparedStatement stmt = dbContext.connection.prepareStatement(query);
            stmt.setInt(1, roomID);
            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
