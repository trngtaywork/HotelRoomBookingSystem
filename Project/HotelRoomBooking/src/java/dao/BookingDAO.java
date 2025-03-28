package dao;

import model.BookingRoomStatistic;
import model.BookingServiceStatistic;
import utils.DBContext;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Booking;

<<<<<<< Updated upstream
    public void Add(Booking booking) {
        String SQL = "INSERT INTO [dbo].[Booking]([ProfileID], [RoomID], [BookingDate], [TotalAmount], [Status]) "
                + "VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement st = connection.prepareStatement(SQL);
            st.setInt(1, booking.getProfileID());
            st.setInt(2, booking.getRoomID());
            st.setDate(3, booking.getBookingDate());
            st.setFloat(4, booking.getTotalAmount());
            st.setString(5, booking.getStatus());
=======
public class BookingDAO {

    private Connection conn;
>>>>>>> Stashed changes

    public BookingDAO() {
        this.conn = new DBContext().connection;
        DBContext dbContext = new DBContext();
        this.conn = dbContext.connection;
    }
<<<<<<< Updated upstream
    
    public void Update(Booking booking) {
        String SQL = "UPDATE [dbo].[Booking] SET ProfileID = ?, RoomID = ?, BookingDate = ?, TotalAmount = ?, Status = ? WHERE BookingID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(SQL);
            st.setInt(1, booking.getProfileID());
            st.setInt(2, booking.getRoomID());
            st.setDate(3, booking.getBookingDate());
            st.setFloat(4, booking.getTotalAmount());
            st.setString(5, booking.getStatus());
            st.setInt(6, booking.getBookingID());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.toString());
=======

    public List<BookingRoomStatistic> getRoomBookingStatistics(String roomNameFilter) {
        List<BookingRoomStatistic> list = new ArrayList<>();
        String sql = "SELECT p.Name AS CustomerName, r.RoomName, b.BookingDate, b.TotalAmount, b.StatusBooking "
                + "FROM Booking b "
                + "JOIN Profile p ON b.ProfileID = p.ProfileID "
                + "JOIN Room r ON b.RoomID = r.RoomID";

        if (roomNameFilter != null && !roomNameFilter.isEmpty()) {
            sql += " WHERE r.RoomName LIKE ?";
>>>>>>> Stashed changes
        }

<<<<<<< Updated upstream
    public void Delete(int bookingID) {
        String SQL = "DELETE FROM [dbo].[Booking] WHERE BookingID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(SQL);
            st.setInt(1, bookingID);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public List<Booking> GetBookingList() {
        String sql = "SELECT [BookingID], [ProfileID], [RoomID], [BookingDate], [TotalAmount], [Status] FROM [dbo].[Booking] WHERE 1 = 1";
        List<Booking> bookings = new ArrayList<>();

        try {
            ResultSet rs = getData(sql);

            if(rs == null){
                return null;
            }
            
            while (rs.next()) {
                Booking b = new Booking();
                b.setBookingID(rs.getInt("BookingID"));
                b.setProfileID(rs.getInt("ProfileID"));
                b.setRoomID(rs.getInt("RoomID"));
                b.setBookingDate(rs.getDate("BookingDate"));
                b.setTotalAmount(rs.getFloat("TotalAmount"));
                b.setStatus(rs.getString("Status"));

                bookings.add(b);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return bookings;
    }

    public List<Booking> SearchBookings(Booking target)//struct?: if var != null => + "AND X = X" in sql
    {
        String sql = "SELECT [BookingID], [ProfileID], [RoomID], [BookingDate], [TotalAmount], [Status] FROM [dbo].[Booking] WHERE 1 = 1";

        if (target.getBookingID() > 0)//??
        {
            sql += " AND [BookingID] = '" + target.getBookingID() + "'";
        }
        if (target.getProfileID() > 0)//??
        {
            sql += " AND [ProfileID] = '" + target.getProfileID() + "'";
        }
        if (target.getRoomID() > 0)//??
        {
            sql += " AND [RoomID] = '" + target.getRoomID() + "'";
        }
        if (target.getBookingDate() != null) {
            sql += " AND [BookingDate] = '" + target.getBookingDate() + "'";
        }
        if (target.getTotalAmount() > 0)//??
        {
            sql += " AND [TotalAmount] = '" + target.getTotalAmount() + "'";
        }
        if (!target.getStatus().isBlank()) {
            sql += " AND [Status] = '" + target.getStatus() + "'";
        }

        List<Booking> bookings = new ArrayList<>();

        try {
            ResultSet rs = getData(sql);

            if(rs == null){
                return null;
            }
            
            while (rs.next()) {
                Booking b = new Booking();
                b.setBookingID(rs.getInt("BookingID"));
                b.setProfileID(rs.getInt("ProfileID"));
                b.setRoomID(rs.getInt("RoomID"));
                b.setBookingDate(rs.getDate("BookingDate"));
                b.setTotalAmount(rs.getFloat("TotalAmount"));
                b.setStatus(rs.getString("Status"));

                bookings.add(b);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return bookings;
    }
    
    public List<Booking> SearchBookingsByProfileID(int profileID) {
        String sql = "SELECT [BookingID], [ProfileID], [RoomID], [BookingDate], [TotalAmount], [Status] FROM [dbo].[Booking] WHERE "
                            + "[Booking].[ProfileID] = " + profileID;

        List<Booking> bookings = new ArrayList<>();

        try {
            
            ResultSet rs = getData(sql);
            
            if(rs == null){
                return null;
            }
            
            while (rs.next()) {
                Booking b = new Booking();
                b.setBookingID(rs.getInt("BookingID"));
                b.setProfileID(rs.getInt("ProfileID"));
                b.setRoomID(rs.getInt("RoomID"));
                b.setBookingDate(rs.getDate("BookingDate"));
                b.setTotalAmount(rs.getFloat("TotalAmount"));
                b.setStatus(rs.getString("Status"));

                bookings.add(b);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return bookings;
    }

    public List<Booking> SearchBooking(String field, String target) {
        String sql;

        if (IsNullOrEmpty(target)) {
            return GetBookingList();
        }

        List<Booking> bookings = new ArrayList<>();

        try {
            switch (field) {
                case "any":
                    sql = "SELECT [BookingID], [ProfileID], [RoomID], [BookingDate], [TotalAmount], [Status] FROM [dbo].[Booking] WHERE "
                            + "[BookingID] LIKE '%" + target + "%' OR "
                            + "[ProfileID] LIKE '%" + target + "%' OR "
                            + "[RoomID] LIKE '%" + target + "%' OR "
                            + "[BookingDate] LIKE '%" + target + "%' OR "
                            + "[TotalAmount] LIKE '%" + target + "%' OR "
                            + "[Status] LIKE '%" + target + "%'";
                    break;
                case "ProfileID":
                    sql = "SELECT [BookingID], [ProfileID], [RoomID], [BookingDate], [TotalAmount], [Status] FROM [dbo].[Booking] WHERE "
                            + "[Booking].[ProfileID] LIKE '%" + target + "%'";
                    break;
                case "Name":
                    sql = "SELECT [BookingID], [Booking].[ProfileID], [Booking].[RoomID], [BookingDate], [TotalAmount], [Status] FROM [dbo].[Booking] INNER JOIN [Profile] ON [Booking].[ProfileID] = [Profile].[ProfileID] WHERE "
                            + "[Profile].[Name] LIKE '%" + target + "%'";
                    break;
                case "RoomID":
                    sql = "SELECT [BookingID], [ProfileID], [RoomID], [BookingDate], [TotalAmount], [Status] FROM [dbo].[Booking] WHERE "
                            + "[Booking].[RoomID] LIKE '%" + target + "%'";
                    break;
                case "RoomName":
                    sql = "SELECT [BookingID], [Booking].[ProfileID], [Booking].[RoomID], [BookingDate], [TotalAmount], [Status] FROM [dbo].[Booking] INNER JOIN [Room] ON [Booking].[RoomID] = [Room].[RoomID] WHERE "
                            + "[Room].[RoomName] LIKE '%" + target + "%'";
                    break;
                case "Status":
                    sql = "SELECT [BookingID], [ProfileID], [RoomID], [BookingDate], [TotalAmount], [Status] FROM [dbo].[Booking] WHERE "
                            + "[Booking].[Status] LIKE '%" + target + "%'";
                    break;
                case "TotalAmount":
                    sql = "SELECT [BookingID], [ProfileID], [RoomID], [BookingDate], [TotalAmount], [Status] FROM [dbo].[Booking] WHERE "
                            + "[Booking].[TotalAmount] LIKE '%" + target + "%'";
                    break;
                default:
                    sql = "SELECT [BookingID], [ProfileID], [RoomID], [BookingDate], [TotalAmount], [Status] FROM [dbo].[Booking] WHERE 1 = 1";
                    break;
            }

            ResultSet rs = getData(sql);
            
            if(rs == null){
                return null;
            }
            
            while (rs.next()) {
                Booking b = new Booking();
                b.setBookingID(rs.getInt("BookingID"));
                b.setProfileID(rs.getInt("ProfileID"));
                b.setRoomID(rs.getInt("RoomID"));
                b.setBookingDate(rs.getDate("BookingDate"));
                b.setTotalAmount(rs.getFloat("TotalAmount"));
                b.setStatus(rs.getString("Status"));

                bookings.add(b);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return bookings;
    }
    
    public List<Booking> SearchBooking(String field, String target, Date fromDate, Date toDate) {
        String sql;

        if (IsNullOrEmpty(target)) {
            return GetBookingList();
        }

        List<Booking> bookings = new ArrayList<>();

        try {
            switch (field) {
                case "any":
                    sql = "SELECT [BookingID], [ProfileID], [RoomID], [BookingDate], [TotalAmount], [Status] FROM [dbo].[Booking] WHERE "
                            + "[BookingID] LIKE '%" + target + "%' OR "
                            + "[ProfileID] LIKE '%" + target + "%' OR "
                            + "[RoomID] LIKE '%" + target + "%' OR "
                            + "[BookingDate] LIKE '%" + target + "%' OR "
                            + "[TotalAmount] LIKE '%" + target + "%' OR "
                            + "[Status] LIKE '%" + target + "%'";
                    break;
                case "ProfileID":
                    sql = "SELECT [BookingID], [ProfileID], [RoomID], [BookingDate], [TotalAmount], [Status] FROM [dbo].[Booking] WHERE "
                            + "[Booking].[ProfileID] LIKE '%" + target + "%'";
                    break;
                case "Name":
                    sql = "SELECT [BookingID], [Booking].[ProfileID], [Booking].[RoomID], [BookingDate], [TotalAmount], [Status] FROM [dbo].[Booking] INNER JOIN [Profile] ON [Booking].[ProfileID] = [Profile].[ProfileID] WHERE "
                            + "[Profile].[Name] LIKE '%" + target + "%'";
                    break;
                case "RoomID":
                    sql = "SELECT [BookingID], [ProfileID], [RoomID], [BookingDate], [TotalAmount], [Status] FROM [dbo].[Booking] WHERE "
                            + "[Booking].[RoomID] LIKE '%" + target + "%'";
                    break;
                case "RoomName":
                    sql = "SELECT [BookingID], [Booking].[ProfileID], [Booking].[RoomID], [BookingDate], [TotalAmount], [Status] FROM [dbo].[Booking] INNER JOIN [Room] ON [Booking].[RoomID] = [Room].[RoomID] WHERE "
                            + "[Room].[RoomName] LIKE '%" + target + "%'";
                    break;
                case "Status":
                    sql = "SELECT [BookingID], [ProfileID], [RoomID], [BookingDate], [TotalAmount], [Status] FROM [dbo].[Booking] WHERE "
                            + "[Booking].[Status] LIKE '%" + target + "%'";
                    break;
                case "TotalAmount":
                    sql = "SELECT [BookingID], [ProfileID], [RoomID], [BookingDate], [TotalAmount], [Status] FROM [dbo].[Booking] WHERE "
                            + "[Booking].[TotalAmount] LIKE '%" + target + "%'";
                    break;
                default:
                    sql = "SELECT [BookingID], [ProfileID], [RoomID], [BookingDate], [TotalAmount], [Status] FROM [dbo].[Booking] WHERE 1 = 1";
                    break;
            }

            sql += " AND [BookingDate] BETWEEN '" + fromDate + "' AND '" + toDate + "'";
            
            ResultSet rs = getData(sql);
            
            if(rs == null){
                return null;
            }
            
            while (rs.next()) {
                Booking b = new Booking();
                b.setBookingID(rs.getInt("BookingID"));
                b.setProfileID(rs.getInt("[Booking].[ProfileID]"));
                b.setRoomID(rs.getInt("[Booking].[RoomID]"));
                b.setBookingDate(rs.getDate("BookingDate"));
                b.setTotalAmount(rs.getFloat("TotalAmount"));
                b.setStatus(rs.getString("Status"));

                bookings.add(b);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return bookings;
    }

    public Booking SearchBooking(int bookingId) {
        String sql = "SELECT [BookingID], [ProfileID], [RoomID], [BookingDate], [TotalAmount], [Status] FROM [dbo].[Booking] WHERE [BookingID] = " + bookingId;

        try {
            ResultSet rs = getData(sql);

            if(rs == null){
                return null;
            }
            
            if (rs.next()) {
                Booking b = new Booking();
                b.setBookingID(rs.getInt("BookingID"));
                b.setProfileID(rs.getInt("ProfileID"));
                b.setRoomID(rs.getInt("RoomID"));
                b.setBookingDate(rs.getDate("BookingDate"));
                b.setTotalAmount(rs.getFloat("TotalAmount"));
                b.setStatus(rs.getString("Status"));

                return b;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return null;
    }
    
    public int lastBookingID() {
        String sql = "SELECT TOP 1 [BookingID]\n"
                + "FROM [dbo].[Booking]\n"
                + "ORDER BY [BookingID] DESC";
        int n = 0;
        try {
            ResultSet rs = getData(sql);
            if (rs.next()) {
                n = rs.getInt("BookingID");
=======
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
>>>>>>> Stashed changes
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
