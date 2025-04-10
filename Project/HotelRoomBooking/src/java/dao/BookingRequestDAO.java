/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.*;
import utils.DBContext;

/**
 *
 * @author My PC
 */
public class BookingRequestDAO extends DBContext{
    public List<BookingRequest> GetBookingRequestList() {
        String sql = """
                     SELECT *
                     FROM [dbo].[BookingRequest] WHERE 1 = 1""";
        List<BookingRequest> BookingRequests = new ArrayList<>();

        try {
            ResultSet rs = getData(sql);

            if (rs == null) {
                return null;
            }

            while (rs.next()) {
                BookingRequest brq = new BookingRequest();
                
                brq.setBookingRequestID(rs.getInt("BookingRequestID"));
                brq.setBookingID(rs.getInt("BookingID"));
                brq.setTypeBookingRequest(rs.getString("TypeBookingRequest"));
                
                BookingRequests.add(brq);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return BookingRequests;
    }

    public BookingRequest SearchBookingRequestByID(int BookingRequestID) {
        String sql = """
                     SELECT *
                     FROM [dbo].[BookingRequest] WHERE [BookingRequestID] = '""" + BookingRequestID + "'";

        try {
            ResultSet rs = getData(sql);

            if (rs == null) {
                return null;
            }

            if (rs.next()) {
                BookingRequest brq = new BookingRequest();
                
                brq.setBookingRequestID(rs.getInt("BookingRequestID"));
                brq.setBookingID(rs.getInt("BookingID"));
                brq.setTypeBookingRequest(rs.getString("TypeBookingRequest"));
                
                return brq;
            } else {
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return null;
    }
    
    public boolean Delete(int BookingRequestID) {
        String sql = "DELETE FROM [dbo].[BookingRequest] WHERE [BookingRequestID] = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, BookingRequestID);
            pre.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
    
    public boolean DeleteAll() {
        String sql = "DELETE FROM [dbo].[BookingRequest]";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
    
    public int lastBookingRequestID() {
        String sql = "SELECT TOP 1 [BookingRequestID]\n"
                + "FROM [dbo].[BookingRequest]\n"
                + "ORDER BY [BookingRequestID] DESC";
        int n = 0;
        try {
            ResultSet rs = getData(sql);
            if (rs.next()) {
                n = rs.getInt("BookingRequestID");
            }
        } catch (SQLException e) {
        }
        return n;
    }
}
