package dao;

import model.Room;
import utils.DBContext;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoomDAO {
    private DBContext dbContext;

    public RoomDAO() {
        dbContext = new DBContext();
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
}
