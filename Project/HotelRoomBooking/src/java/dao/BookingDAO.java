package dao;

import model.BookingRoomStatistic;
import model.BookingServiceStatistic;
import utils.DBContext;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Booking;

public class BookingDAO {

    private Connection conn;

    public BookingDAO() {
        this.conn = new DBContext().connection;
        DBContext dbContext = new DBContext();
        this.conn = dbContext.connection;
    }

    public List<BookingRoomStatistic> getRoomBookingStatistics(String roomNameFilter) {
        List<BookingRoomStatistic> list = new ArrayList<>();
        String sql = "SELECT p.Name AS CustomerName, r.RoomName, b.BookingDate, b.TotalAmount, b.StatusBooking "
                + "FROM Booking b "
                + "JOIN Profile p ON b.ProfileID = p.ProfileID "
                + "JOIN Room r ON b.RoomID = r.RoomID";

        if (roomNameFilter != null && !roomNameFilter.isEmpty()) {
            sql += " WHERE r.RoomName LIKE ?";
        }

        try ( PreparedStatement stmt = conn.prepareStatement(sql)) {
            if (roomNameFilter != null && !roomNameFilter.isEmpty()) {
                stmt.setString(1, "%" + roomNameFilter + "%");  // Tìm kiếm tương đối (LIKE)
            }

            try ( ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(new BookingRoomStatistic(
                            rs.getString("CustomerName"),
                            rs.getString("RoomName"),
                            rs.getTimestamp("BookingDate"),
                            rs.getDouble("TotalAmount"),
                            rs.getString("StatusBooking")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<BookingServiceStatistic> getServiceBookingStatistics(String serviceNameFilter) {
        List<BookingServiceStatistic> list = new ArrayList<>();
        String sql = "SELECT p.Name AS CustomerName, s.ServiceName, b.BookingDate, b.TotalAmount, b.StatusBooking "
                + "FROM BookingService bs "
                + "JOIN Booking b ON bs.BookingID = b.BookingID "
                + "JOIN Profile p ON b.ProfileID = p.ProfileID "
                + "JOIN Service s ON bs.ServiceID = s.ServiceID";

        // Thêm điều kiện lọc theo tên dịch vụ nếu có
        if (serviceNameFilter != null && !serviceNameFilter.isEmpty()) {
            sql += " WHERE s.ServiceName LIKE ?";
        }

        try ( PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Nếu có điều kiện lọc tên dịch vụ, gán tham số vào PreparedStatement
            if (serviceNameFilter != null && !serviceNameFilter.isEmpty()) {
                stmt.setString(1, "%" + serviceNameFilter + "%");  // Tìm kiếm tương đối (LIKE)
            }

            try ( ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(new BookingServiceStatistic(
                            rs.getString("CustomerName"),
                            rs.getString("ServiceName"),
                            rs.getTimestamp("BookingDate"),
                            rs.getDouble("TotalAmount"),
                            rs.getString("StatusBooking")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
