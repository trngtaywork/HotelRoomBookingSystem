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
public class BookingRoomDAO extends DBContext{
    public void Add(BookingRoom bookingRoom){
        String SQL = "INSERT INTO [dbo].[BookingRoom]([BookingID], [RoomID], [Quantity], [StartTime], [EndTime]) "
                       + "VALUES (?, ?, ?, ?, ?)";
        
        try {
                PreparedStatement st = connection.prepareStatement(SQL);
                st.setInt(1, bookingRoom.getBookingID());
                st.setInt(2, bookingRoom.getRoomID());
                st.setInt(3, bookingRoom.getQuantity());
                st.setDate(4, bookingRoom.getStartTime());
                st.setDate(5, bookingRoom.getEndTime());

                st.executeUpdate();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
    }
    
    public void Update(BookingRoom bookingRoom) {
        String SQL = "UPDATE [dbo].[BookingRoom] SET [Quantity] = ?, [StartTime] = ?, [EndTime] = ? "
                + "WHERE [BookingID] = ? AND [RoomID] = ?";
        
        try {
            PreparedStatement st = connection.prepareStatement(SQL);
            st.setInt(1, bookingRoom.getQuantity());
            st.setDate(2, bookingRoom.getStartTime());
            st.setDate(3, bookingRoom.getEndTime());
            st.setInt(4, bookingRoom.getBookingID());
            st.setInt(5, bookingRoom.getRoomID());
            
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    public void Delete(int bookingID, int roomID) {
        String SQL = "DELETE FROM [dbo].[BookingRoom] WHERE [BookingID] = ? AND [RoomID] = ?";
        
        try {
            PreparedStatement st = connection.prepareStatement(SQL);
            st.setInt(1, bookingID);
            st.setInt(2, roomID);
            
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    public void Delete(int bookingRoomID) {
        String SQL = "DELETE FROM [dbo].[BookingRoom] WHERE [BookingRoomID = ?";
        
        try {
            PreparedStatement st = connection.prepareStatement(SQL);
            st.setInt(1, bookingRoomID);
            
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    public List<BookingRoom> GetBookingRoomList(){
        String sql = "SELECT [BookingRoomID], [BookingID], [RoomID], [Quantity], [StartTime], [EndTime] FROM [dbo].[BookingRoom] WHERE 1 = 1";
        List<BookingRoom> bookingRooms = new ArrayList<>();
        
        try {
            ResultSet rs = getData(sql);

            if(rs == null){
                return null;
            }
            
            while (rs.next()) {
                BookingRoom br = new BookingRoom();
                br.setBookingRoomID(rs.getInt("BookingRoomID"));
                br.setBookingID(rs.getInt("BookingID"));
                br.setRoomID(rs.getInt("RoomID"));
                br.setQuantity(rs.getInt("Quantity"));
                br.setStartTime(rs.getDate("StartTime"));
                br.setEndTime(rs.getDate("EndTime"));

                bookingRooms.add(br);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return bookingRooms;
    }
    
    public List<BookingRoom> SearchBookingRooms(int bookingID, int roomID)
    {
        String sql = "SELECT [BookingRoomID], [BookingID], [RoomID], [Quantity], [StartTime], [EndTime] FROM [dbo].[BookingRoom] WHERE [BookingID] = '" + bookingID + "' AND [RoomID] = '" + roomID + "'";
        
        List<BookingRoom> bookingRooms = new ArrayList<>();
        
        try {
            ResultSet rs = getData(sql);

            if(rs == null){
                return null;
            }
            
            while (rs.next()) {
                BookingRoom br = new BookingRoom();
                br.setBookingRoomID(rs.getInt("BookingRoomID"));
                br.setBookingID(rs.getInt("BookingID"));
                br.setRoomID(rs.getInt("RoomID"));
                br.setQuantity(rs.getInt("Quantity"));
                br.setStartTime(rs.getDate("StartTime"));
                br.setEndTime(rs.getDate("EndTime"));
                
                bookingRooms.add(br);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return bookingRooms;
    }
    
    public BookingRoom SearchBookingRoom(int bookingID, int roomID)
    {
        String sql = "SELECT [BookingRoomID], [BookingID], [RoomID], [Quantity], [StartTime], [EndTime] FROM [dbo].[BookingRoom] WHERE [BookingID] = '" + bookingID + "' AND [RoomID] = '" + roomID + "'";
        
        try {
            ResultSet rs = getData(sql);

            if(rs == null){
                return null;
            }
            
            if (rs.next()) {
                BookingRoom br = new BookingRoom();
                br.setBookingRoomID(rs.getInt("BookingRoomID"));
                br.setBookingID(rs.getInt("BookingID"));
                br.setRoomID(rs.getInt("RoomID"));
                br.setQuantity(rs.getInt("Quantity"));
                br.setStartTime(rs.getDate("StartTime"));
                br.setEndTime(rs.getDate("EndTime"));
                
                return br;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return null;
    }
}
