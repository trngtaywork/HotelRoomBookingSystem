/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.awt.Image;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.DBContext;
import model.*;

/**
 *
 * @author My PC
 */
public class RoomDAO extends DBContext {

    public List<Room> GetRoomList() {
        String sql = """
                     SELECT [RoomID]
                     ,[RoomName]
                     ,[Description]
                     ,[Price]
                     ,[Image]
                     ,[StatusRoom]
                     ,[TypeRoom]
                     FROM [dbo].[Room] WHERE 1 = 1""";
        List<Room> Rooms = new ArrayList<>();

        try {
            ResultSet rs = getData(sql);

            if(rs == null){
                return null;
            }
            
            while (rs.next()) {
                Room r = new Room();
                r.setRoomID(rs.getInt("RoomID"));
                r.setRoomName(rs.getString("RoomName"));
                String desTemp = rs.getString("Description") == null ? "" : rs.getString("Description");
                r.setDescription(desTemp.isBlank() ? "" : desTemp);
                r.setPrice(rs.getDouble("Price"));
                String imageTemp = rs.getString("Image") == null ? "" : rs.getString("Image");
                r.setImage(imageTemp.isBlank() ? "" : imageTemp);////
                r.setStatusRoom(rs.getString("StatusRoom"));
                r.setTypeRoom(rs.getString("TypeRoom"));

                Rooms.add(r);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return Rooms;
    }

    public Room SearchRoomByID(int RoomID) {
        String sql = """
                     SELECT [RoomID]
                     ,[RoomName]
                     ,[Description]
                     ,[Price]
                     ,[Image]
                     ,[StatusRoom]
                     ,[TypeRoom]
                     FROM [dbo].[Room] WHERE [RoomID] = '""" + RoomID + "'";

        try {
            ResultSet rs = getData(sql);

            if(rs == null){
                return null;
            }
            
            if (rs.next()) {
                Room r = new Room();

                r.setRoomID(rs.getInt("RoomID"));
                r.setRoomName(rs.getString("RoomName"));
                String desTemp = rs.getString("Description") == null ? "" : rs.getString("Description");
                r.setDescription(desTemp.isBlank() ? "" : desTemp);
                r.setPrice(rs.getDouble("Price"));
                String imageTemp = rs.getString("Image") == null ? "" : rs.getString("Image");
                r.setImage(imageTemp.isBlank() ? "" : imageTemp);////
                r.setStatusRoom(rs.getString("StatusRoom"));
                r.setTypeRoom(rs.getString("TypeRoom"));

                return r;
            } else {
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return null;
    }
}
