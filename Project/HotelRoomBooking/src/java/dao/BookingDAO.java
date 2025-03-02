/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.*;
import utils.DBContext;
/**
 *
 * @author My PC
 */
public class BookingDAO extends DBContext{//add merge?
    public void Add(Booking booking){
        String SQL = "INSERT INTO [dbo].[Booking]([ProfileID], [RoomID], [BookingDate], [TotalAmount], [Status]) "
                       + "VALUES (?, ?, ?, ?, ?)";
        
        try {
                PreparedStatement st = connection.prepareStatement(SQL);
                st.setInt(1, booking.getProfileID());
                st.setInt(2, booking.getRoomID());
                st.setDate(3, booking.getBookingDate());
                st.setFloat(4, booking.getTotalAmount());
                st.setString(4, booking.getStatus());

                st.executeUpdate();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
    }
    
    public List<Booking> GetBookingList(){
        String sql = "SELECT [BookingID], [ProfileID], [RoomID], [BookingDate], [TotalAmount], [Status] FROM [dbo].[Booking] WHERE 1 = 1";
        List<Booking> bookings = new ArrayList<>();
        
        try {
            ResultSet rs = getData(sql);

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
    
    public List<Booking> SearchBooking(Booking target)//struct?: if var != null => + "AND X = X" in sql
    {
        String sql = "SELECT [BookingID], [ProfileID], [RoomID], [BookingDate], [TotalAmount], [Status] FROM [dbo].[Booking] WHERE 1 = 1";
        
        if(target.getBookingID() > 0)//??
        {
            sql += " AND [BookingID] = " + target.getBookingID();
        }
        if(target.getProfileID() > 0)//??
        {
            sql += " AND [ProfileID] = " + target.getProfileID();
        }
        if(target.getRoomID() > 0)//??
        {
            sql += " AND [RoomID] = " + target.getRoomID();
        }
        if(target.getBookingDate() != null)
        {
            sql += " AND [BookingDate] = " + target.getBookingDate();
        }
        if(target.getTotalAmount() > 0)//??
        {
            sql += " AND [TotalAmount] = " + target.getTotalAmount();
        }
        if(!target.getStatus().isBlank())
        {
            sql += " AND [Status] = " + target.getStatus();
        }
        
        List<Booking> bookings = new ArrayList<>();
        
        try {
            ResultSet rs = getData(sql);

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
    
    public Booking SearchBooking(int bookingId)
    {
        String sql = "SELECT [BookingID], [ProfileID], [RoomID], [BookingDate], [TotalAmount], [Status] FROM [dbo].[Booking] WHERE [BookingID] = " + bookingId;
        
        try {
            ResultSet rs = getData(sql);

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
}
