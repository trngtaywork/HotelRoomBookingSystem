package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.*;
import utils.DBContext;
import java.sql.Timestamp;

public class FeedbackDAO extends DBContext{

    private Connection conn;

    public FeedbackDAO() {
        DBContext dbContext = new DBContext();
        this.conn = dbContext.connection;
    }

    public List<Feedback> getAllFeedbacks() {
        List<Feedback> feedbackList = new ArrayList<>();
        String query = "SELECT f.FeedbackID, f.ProfileID, f.RoomID, f.Comment, f.Rating, f.Date, r.RoomName, r.Image, p.Name "
                + "FROM Feedback f "
                + "JOIN Room r ON f.RoomID = r.RoomID "
                + "JOIN Profile p ON f.ProfileID = p.ProfileID";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
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

    public List<Feedback> getFeedbacksWithFilters(String roomName, Integer rating, String dateStr) {
        List<Feedback> feedbackList = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT f.FeedbackID, f.ProfileID, f.RoomID, f.Comment, f.Rating, f.Date, r.RoomName, r.Image, p.Name "
                + "FROM Feedback f "
                + "JOIN Room r ON f.RoomID = r.RoomID "
                + "JOIN Profile p ON f.ProfileID = p.ProfileID WHERE 1=1");

        // Thêm điều kiện lọc nếu có
        if (roomName != null && !roomName.isEmpty()) {
            // Dùng dấu '%' trước và sau để tìm kiếm trong RoomName
            query.append(" AND r.RoomName LIKE '%").append(roomName).append("%'");
        }
        if (rating != null) {
            query.append(" AND f.Rating = ").append(rating);
        }
        if (dateStr != null && !dateStr.isEmpty()) {
            query.append(" AND CONVERT(date, f.Date) = '").append(dateStr).append("'");
        }

        // Thực hiện truy vấn
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query.toString())) {
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
    
    public int lastFeedbackID() {
        String sql = "SELECT TOP 1 [FeedbackID]\n"
                + "FROM [dbo].[Feedback]\n"
                + "ORDER BY [FeedbackID] DESC";
        int n = 0;
        try {
            ResultSet rs = getData(sql);
            if (rs.next()) {
                n = rs.getInt("FeedbackID");
            }
        } catch (SQLException e) {
        }
        return n;
    }
    
    public void Add(Feedback feedback) {
        String SQL = "INSERT INTO [dbo].[Feedback]([FeedbackID], [ProfileID], [RoomID], [Comment], [Rating], [Date]) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement st = connection.prepareStatement(SQL);
            st.setInt(1, lastFeedbackID() + 1);
            st.setInt(2, feedback.getProfileID());
            st.setInt(3, feedback.getRoomID());
            st.setString(4, feedback.getComment());
            st.setInt(5, feedback.getRating());
            st.setDate(6, new Date(feedback.getDate().getTime()));

            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    public List<Feedback> GetFeedbackList() {
        String sql = "SELECT * FROM [dbo].[Feedback] WHERE 1 = 1";
        List<Feedback> feedbacks = new ArrayList<>();

        try {
            ResultSet rs = getData(sql);

            if (rs == null) {
                return null;
            }

            while (rs.next()) {
                Feedback f = new Feedback();
                f.setFeedbackID(rs.getInt("FeedbackID"));
                f.setProfileID(rs.getInt("ProfileID"));
                f.setRoomID(rs.getInt("RoomID"));
                f.setComment(rs.getString("Comment"));
                f.setRating(rs.getInt("Rating"));
                f.setDate(new Timestamp(rs.getDate("Date").getTime()));

                feedbacks.add(f);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return feedbacks;
    }
    
    public List<Feedback> GetFeedbackListByProfileID(int profileID) {
        String sql = "SELECT * FROM [dbo].[Feedback] WHERE [Feedback].[ProfileID] = '" + profileID + "'";
        List<Feedback> feedbacks = new ArrayList<>();

        try {
            ResultSet rs = getData(sql);

            if (rs == null) {
                return null;
            }

            while (rs.next()) {
                Feedback f = new Feedback();
                f.setFeedbackID(rs.getInt("FeedbackID"));
                f.setProfileID(rs.getInt("ProfileID"));
                f.setRoomID(rs.getInt("RoomID"));
                f.setComment(rs.getString("Comment"));
                f.setRating(rs.getInt("Rating"));
                f.setDate(new Timestamp(rs.getDate("Date").getTime()));

                feedbacks.add(f);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return feedbacks;
    }
}
