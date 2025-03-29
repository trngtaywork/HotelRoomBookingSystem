package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Feedback;
import model.Room;
import model.Profile;
import utils.DBContext;

public class FeedbackDAO {

    private Connection conn;

    public FeedbackDAO() {
        DBContext dbContext = new DBContext();
        this.conn = dbContext.connection;
    }

    public List<Feedback> getAllFeedbacks() {
        List<Feedback> feedbackList = new ArrayList<>();
        String query = "SELECT f.FeedbackID, f.ProfileID, f.RoomID, f.Comment, f.Rating, f.Date, r.RoomName, r.Image, p.Name " +
                       "FROM Feedback f " +
                       "JOIN Room r ON f.RoomID = r.RoomID " +
                       "JOIN Profile p ON f.ProfileID = p.ProfileID";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Feedback feedback = new Feedback(
                        rs.getInt("FeedbackID"),
                        rs.getInt("ProfileID"),
                        rs.getInt("RoomID"),
                        rs.getString("Comment"),
                        rs.getInt("Rating"),
                        rs.getTimestamp("Date"),
                        rs.getString("RoomName"),
                        rs.getString("Image"),
                        rs.getString("Name")
                );
                feedbackList.add(feedback);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return feedbackList;
    }
}
