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
public class BookingServiceDAO extends DBContext{
    public void Add(BookingService bookingService) {
        String sql = "INSERT INTO [dbo].[BookingService] ([ServiceID], [BookingID], [Amount], [StartTime], [EndTime]) VALUES (?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, bookingService.getServiceID());
            st.setInt(2, bookingService.getBookingID());
            st.setInt(3, bookingService.getAmount());
            st.setDate(4, bookingService.getStartTime());
            st.setDate(5, bookingService.getEndTime());
            
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    public void Update(BookingService bookingService) {
        String sql = "UPDATE [dbo].[BookingService] SET [ServiceID] = ?, [BookingID] = ?, [Amount] = ?, [StartTime] = ?, [EndTime] = ? WHERE [BookingServiceID] = ?";
        
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, bookingService.getServiceID());
            st.setInt(2, bookingService.getBookingID());
            st.setInt(3, bookingService.getAmount());
            st.setDate(4, bookingService.getStartTime());
            st.setDate(5, bookingService.getEndTime());
            st.setInt(6, bookingService.getBookingServiceID());
            
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    public void Delete(int bookingID, int serviceID) {
        String sql = "DELETE FROM [dbo].[BookingService] WHERE [BookingID] = ? AND [ServiceID] = ?";
        
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, bookingID);
            st.setInt(2, serviceID);
            
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    public void Delete(int bookingServiceID) {
        String sql = "DELETE FROM [dbo].[BookingService] WHERE [BookingServiceID] = ?";
        
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, bookingServiceID);
            
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    public List<BookingService> GetBookingServiceList(){
        String sql = "SELECT [BookingServiceID], [ServiceID], [BookingID], [Amount], [StartTime], [EndTime] FROM [dbo].[BookingService] WHERE 1 = 1";
        List<BookingService> bookingServices = new ArrayList<>();
        
        try {
            ResultSet rs = getData(sql);

            if(rs == null){
                return null;
            }
            
            while (rs.next()) {
                BookingService bs = new BookingService();
                bs.setBookingServiceID(rs.getInt("BookingServiceID"));
                bs.setServiceID(rs.getInt("ServiceID"));
                bs.setBookingID(rs.getInt("BookingID"));
                bs.setAmount(rs.getInt("Amount"));
                bs.setStartTime(rs.getDate("StartTime"));
                bs.setEndTime(rs.getDate("EndTime"));

                bookingServices.add(bs);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return bookingServices;
    }
    
    public List<BookingService> SearchBookingServices(int bookingID, int serviceID)
    {
        String sql = "SELECT [BookingServiceID], [ServiceID], [BookingID], [Amount], [StartTime], [EndTime] FROM [dbo].[BookingService] WHERE [BookingID] = '" + bookingID + "' AND [ServiceID] = '" + serviceID + "'";
        
        List<BookingService> bookingServices = new ArrayList<>();
        
        try {
            ResultSet rs = getData(sql);

            if(rs == null){
                return null;
            }
            
            while (rs.next()) {
                BookingService bs = new BookingService();
                bs.setBookingServiceID(rs.getInt("BookingServiceID"));
                bs.setServiceID(rs.getInt("ServiceID"));
                bs.setBookingID(rs.getInt("BookingID"));
                bs.setAmount(rs.getInt("Amount"));
                bs.setStartTime(rs.getDate("StartTime"));
                bs.setEndTime(rs.getDate("EndTime"));
                
                bookingServices.add(bs);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return bookingServices;
    }
    
    public List<BookingService> SearchBookingServices(int bookingID)
    {
        String sql = "SELECT [BookingServiceID], [ServiceID], [BookingID], [Amount], [StartTime], [EndTime] FROM [dbo].[BookingService] WHERE [BookingID] = " + bookingID;
        
        List<BookingService> bookingServices = new ArrayList<>();
        
        try {
            ResultSet rs = getData(sql);

            if(rs == null){
                return null;
            }
            
            while (rs.next()) {
                BookingService bs = new BookingService();
                bs.setBookingServiceID(rs.getInt("BookingServiceID"));
                bs.setServiceID(rs.getInt("ServiceID"));
                bs.setBookingID(rs.getInt("BookingID"));
                bs.setAmount(rs.getInt("Amount"));
                bs.setStartTime(rs.getDate("StartTime"));
                bs.setEndTime(rs.getDate("EndTime"));
                
                bookingServices.add(bs);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return bookingServices;
    }
    
    public BookingService SearchBookingService(int bookingID, int serviceID)
    {
        String sql = "SELECT [BookingServiceID], [ServiceID], [BookingID], [Amount], [StartTime], [EndTime] FROM [dbo].[BookingService] WHERE [BookingID] = '" + bookingID + "' AND [ServiceID] = '" + serviceID + "'";
        
        
        try {
            ResultSet rs = getData(sql);

            if(rs == null){
                return null;
            }
            
            if(rs.next()) {
                BookingService bs = new BookingService();
                bs.setBookingServiceID(rs.getInt("BookingServiceID"));
                bs.setServiceID(rs.getInt("ServiceID"));
                bs.setBookingID(rs.getInt("BookingID"));
                bs.setAmount(rs.getInt("Amount"));
                bs.setStartTime(rs.getDate("StartTime"));
                bs.setEndTime(rs.getDate("EndTime"));
                
                return bs;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return null;
    }
    
    public BookingService SearchBookingService(int bookingServiceID)
    {
        String sql = "SELECT [BookingServiceID], [ServiceID], [BookingID], [Amount], [StartTime], [EndTime] FROM [dbo].[BookingService] WHERE [BookingServiceID] = '" + bookingServiceID + "'";
        
        
        try {
            ResultSet rs = getData(sql);

            if(rs == null){
                return null;
            }
            
            if(rs.next()) {
                BookingService bs = new BookingService();
                bs.setBookingServiceID(rs.getInt("BookingServiceID"));
                bs.setServiceID(rs.getInt("ServiceID"));
                bs.setBookingID(rs.getInt("BookingID"));
                bs.setAmount(rs.getInt("Amount"));
                bs.setStartTime(rs.getDate("StartTime"));
                bs.setEndTime(rs.getDate("EndTime"));
                
                return bs;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return null;
    }
}
