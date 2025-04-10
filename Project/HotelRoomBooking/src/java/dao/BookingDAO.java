package dao;

import model.BookingRoomStatistic;
import model.BookingServiceStatistic;
import utils.DBContext;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Booking;
import model.InvoiceDetail;
import model.ServiceInvoiceDetail;

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
        String sql = "SELECT b.BookingID, p.Name AS CustomerName, s.ServiceName, b.BookingDate, b.TotalAmount, b.StatusBooking "
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
                            rs.getInt("BookingID"),
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

    public List<BookingRoomStatistic> getRoomBookingStatisticsWithID(String roomNameFilter) {
        List<BookingRoomStatistic> list = new ArrayList<>();
        String sql = "SELECT b.BookingID, p.Name AS CustomerName, r.RoomName, b.BookingDate, b.TotalAmount, b.StatusBooking "
                + "FROM Booking b "
                + "JOIN Profile p ON b.ProfileID = p.ProfileID "
                + "JOIN Room r ON b.RoomID = r.RoomID";

        if (roomNameFilter != null && !roomNameFilter.isEmpty()) {
            sql += " WHERE r.RoomName LIKE ?";
        }

        try ( PreparedStatement stmt = conn.prepareStatement(sql)) {
            if (roomNameFilter != null && !roomNameFilter.isEmpty()) {
                stmt.setString(1, "%" + roomNameFilter + "%");
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new BookingRoomStatistic(
                        rs.getInt("BookingID"),
                        rs.getString("CustomerName"),
                        rs.getString("RoomName"),
                        rs.getTimestamp("BookingDate"),
                        rs.getDouble("TotalAmount"),
                        rs.getString("StatusBooking")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public InvoiceDetail getInvoiceDetailByBookingID(int bookingID) {
        String sql = "SELECT p.Name AS CustomerName, a.Username, b.BookingDate, br.StartTime, br.EndTime, b.TotalAmount, b.StatusBooking FROM Booking b "
                + "JOIN Profile p ON b.ProfileID = p.ProfileID JOIN Account a ON p.AccountID = a.AccountID JOIN BookingRoom br ON b.BookingID = br.BookingID "
                + "WHERE b.BookingID = ?";

        try ( PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bookingID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new InvoiceDetail(
                        rs.getString("CustomerName"),
                        rs.getString("Username"),
                        rs.getTimestamp("BookingDate"),
                        rs.getTimestamp("StartTime"),
                        rs.getTimestamp("EndTime"),
                        rs.getDouble("TotalAmount"),
                        rs.getString("StatusBooking")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean cancelBooking(int bookingID) {
        String sql = "UPDATE Booking SET StatusBooking = 'Cancelled' WHERE BookingID = ?";
        try ( PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bookingID);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean confirmBooking(int bookingID) {
        String sql = "UPDATE Booking SET StatusBooking = 'Confirmed' WHERE BookingID = ?";
        try ( PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bookingID);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void updateBookingStatus(int bookingID, String newStatus) {
        String sql = "UPDATE Booking SET StatusBooking = ? WHERE BookingID = ?";
        try ( PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newStatus);
            stmt.setInt(2, bookingID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ServiceInvoiceDetail getServiceInvoiceDetailByBookingID(int bookingID) {
        String sql = "SELECT b.BookingID, p.Name AS CustomerName, a.Username, b.BookingDate, "
                + "bs.StartTime, bs.EndTime, b.TotalAmount, b.StatusBooking, s.ServiceName "
                + "FROM Booking b "
                + "JOIN Profile p ON b.ProfileID = p.ProfileID "
                + "JOIN Account a ON p.AccountID = a.AccountID "
                + "JOIN BookingService bs ON bs.BookingID = b.BookingID "
                + "JOIN Service s ON bs.ServiceID = s.ServiceID "
                + "WHERE b.BookingID = ?";

        try ( PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bookingID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new ServiceInvoiceDetail(
                        rs.getInt("BookingID"),
                        rs.getString("CustomerName"),
                        rs.getString("Username"),
                        rs.getTimestamp("BookingDate"),
                        rs.getTimestamp("StartTime"),
                        rs.getTimestamp("EndTime"),
                        rs.getDouble("TotalAmount"),
                        rs.getString("StatusBooking"),
                        rs.getString("ServiceName")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
