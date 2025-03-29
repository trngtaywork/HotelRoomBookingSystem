/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.awt.Image;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.DBContext;
import model.*;

/**
 *
 * @author My PC
 */
public class RoomDAO extends DBContext {
    private DBContext dbContext;

    public RoomDAO() {
        dbContext = new DBContext();
    }

    public boolean updateRoom(Room room) throws SQLException {
        String query = "UPDATE Room SET RoomName = ?, Description = ?, Price = ?, Image = ?, StatusRoom = ?, TypeRoom = ? WHERE RoomID = ?";

        try ( PreparedStatement stmt = dbContext.connection.prepareStatement(query)) {
            stmt.setString(1, room.getRoomName());
            stmt.setString(2, room.getDescription());
            stmt.setDouble(3, room.getPrice());
            stmt.setString(4, room.getImage());
            stmt.setString(5, room.getStatusRoom());
            stmt.setString(6, room.getTypeRoom());
            stmt.setInt(7, room.getRoomID());

            return stmt.executeUpdate() > 0;
        }
    }

    public Room getRoomById(int roomID) {
        String query = "SELECT * FROM Room WHERE RoomID = ?";
        try ( PreparedStatement stmt = dbContext.connection.prepareStatement(query)) {
            stmt.setInt(1, roomID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Room(
                        rs.getInt("RoomID"),
                        rs.getString("RoomName"),
                        rs.getString("Description"),
                        rs.getDouble("Price"),
                        rs.getString("Image"),
                        rs.getString("StatusRoom"),
                        rs.getString("TypeRoom")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isRoomNameExists(String roomName, int currentRoomID) {
        String query = "SELECT COUNT(*) FROM Room WHERE RoomName = ? AND RoomID != ?";
        try ( PreparedStatement stmt = dbContext.connection.prepareStatement(query)) {
            stmt.setString(1, roomName);
            stmt.setInt(2, currentRoomID);
            ResultSet rs = stmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
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
    
    public List<Room> GetRoomList() {
        String sql = """
                     SELECT [RoomID]
                     ,[RoomName]
                     ,[Description]
                     ,[Price]
                     ,[Image]
                     ,[StatusRoom]
                     ,[TypeRoom]
                     FROM [dbo].[Room] WHERE 1 = 1""";
        List<Room> Rooms = new ArrayList<>();

        try {
            ResultSet rs = getData(sql);

            if(rs == null){
                return null;
            }
            
            while (rs.next()) {
                Room r = new Room();
                r.setRoomID(rs.getInt("RoomID"));
                r.setRoomName(rs.getString("RoomName"));
                String desTemp = rs.getString("Description") == null ? "" : rs.getString("Description");
                r.setDescription(desTemp.isBlank() ? "" : desTemp);
                r.setPrice(rs.getDouble("Price"));
                String imageTemp = rs.getString("Image") == null ? "" : rs.getString("Image");
                r.setImage(imageTemp.isBlank() ? "" : imageTemp);////
                r.setStatusRoom(rs.getString("StatusRoom"));
                r.setTypeRoom(rs.getString("TypeRoom"));

                Rooms.add(r);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return Rooms;
    }

    public Room SearchRoomByID(int RoomID) {
        String sql = """
                     SELECT [RoomID]
                     ,[RoomName]
                     ,[Description]
                     ,[Price]
                     ,[Image]
                     ,[StatusRoom]
                     ,[TypeRoom]
                     FROM [dbo].[Room] WHERE [RoomID] = '""" + RoomID + "'";

        try {
            ResultSet rs = getData(sql);

            if(rs == null){
                return null;
            }
            
            if (rs.next()) {
                Room r = new Room();

                r.setRoomID(rs.getInt("RoomID"));
                r.setRoomName(rs.getString("RoomName"));
                String desTemp = rs.getString("Description") == null ? "" : rs.getString("Description");
                r.setDescription(desTemp.isBlank() ? "" : desTemp);
                r.setPrice(rs.getDouble("Price"));
                String imageTemp = rs.getString("Image") == null ? "" : rs.getString("Image");
                r.setImage(imageTemp.isBlank() ? "" : imageTemp);////
                r.setStatusRoom(rs.getString("StatusRoom"));
                r.setTypeRoom(rs.getString("TypeRoom"));

                return r;
            } else {
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return null;
    }
}
