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
public class BookingServiceRequestDAO extends DBContext{
    public List<BookingServiceRequest> GetBookingServiceRequestList() {
        String sql = """
                     SELECT *
                     FROM [dbo].[BookingServiceRequest] WHERE 1 = 1""";
        List<BookingServiceRequest> BookingServiceRequests = new ArrayList<>();

        try {
            ResultSet rs = getData(sql);

            if (rs == null) {
                return null;
            }

            while (rs.next()) {
                BookingServiceRequest brq = new BookingServiceRequest();
                
                brq.setBookingServiceRequestID(rs.getInt("BookingServiceRequestID"));
                brq.setBookingServiceID(rs.getInt("BookingServiceID"));
                brq.setTypeBookingServiceRequest(rs.getString("TypeBookingServiceRequest"));
                
                BookingServiceRequests.add(brq);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return BookingServiceRequests;
    }

    public BookingServiceRequest SearchBookingServiceRequestByID(int BookingServiceRequestID) {
        String sql = """
                     SELECT *
                     FROM [dbo].[BookingServiceRequest] WHERE [BookingServiceRequestID] = '""" + BookingServiceRequestID + "'";

        try {
            ResultSet rs = getData(sql);

            if (rs == null) {
                return null;
            }

            if (rs.next()) {
                BookingServiceRequest brq = new BookingServiceRequest();
                
                brq.setBookingServiceRequestID(rs.getInt("BookingServiceRequestID"));
                brq.setBookingServiceID(rs.getInt("BookingServiceID"));
                brq.setTypeBookingServiceRequest(rs.getString("TypeBookingServiceRequest"));
                
                return brq;
            } else {
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return null;
    }
    
    public boolean Delete(int BookingServiceRequestID) {
        String sql = "DELETE FROM [dbo].[BookingServiceRequest] WHERE [BookingServiceRequestID] = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, BookingServiceRequestID);
            pre.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
    
    public boolean DeleteAll() {
        String sql = "DELETE FROM [dbo].[BookingServiceRequest]";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
    
    public int lastBookingServiceRequestID() {
        String sql = "SELECT TOP 1 [BookingServiceRequestID]\n"
                + "FROM [dbo].[BookingServiceRequest]\n"
                + "ORDER BY [BookingServiceRequestID] DESC";
        int n = 0;
        try {
            ResultSet rs = getData(sql);
            if (rs.next()) {
                n = rs.getInt("BookingServiceRequestID");
            }
        } catch (SQLException e) {
        }
        return n;
    }
}
