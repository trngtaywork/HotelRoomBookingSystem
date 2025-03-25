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
import utils.DBContext;
import model.*;

/**
 *
 * @author My PC
 */
public class ServiceDAO extends DBContext {

    public List<Service> GetServiceList() {
        String sql = """
                     SELECT [ServiceID]
                     ,[ServiceName]
                     ,[Description]
                     ,[Price]
                     ,[Image]
                     ,[StatusService]
                     ,[TypeService]
                     FROM [dbo].[Service] WHERE 1 = 1""";
        List<Service> Services = new ArrayList<>();

        try {
            ResultSet rs = getData(sql);

            if (rs == null) {
                return null;
            }

            while (rs.next()) {
                Service s = new Service();
                s.setServiceID(rs.getInt("ServiceID"));
                s.setServiceName(rs.getString("ServiceName"));
                String desTemp = rs.getString("Description") == null ? "" : rs.getString("Description");
                s.setDescription(desTemp.isBlank() ? "" : desTemp);
                s.setPrice(rs.getFloat("Price"));
                String imageTemp = rs.getString("Image") == null ? "" : rs.getString("Image");
                s.setImage(imageTemp.isBlank() ? "" : imageTemp);////
                s.setStatusService(rs.getString("StatusService"));
                s.setTypeService(rs.getString("TypeService"));
                
                Services.add(s);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return Services;
    }

    public Service SearchServiceByID(int ServiceID) {
        String sql = """
                     SELECT [ServiceID]
                     ,[ServiceName]
                     ,[Description]
                     ,[Price]
                     ,[Image]
                     ,[StatusService]
                     ,[TypeService]
                     FROM [dbo].[Service] WHERE [ServiceID] = '""" + ServiceID + "'";

        try {
            ResultSet rs = getData(sql);

            if (rs == null) {
                return null;
            }

            if (rs.next()) {
                Service s = new Service();
                s.setServiceID(rs.getInt("ServiceID"));
                s.setServiceName(rs.getString("ServiceName"));
                String desTemp = rs.getString("Description") == null ? "" : rs.getString("Description");
                s.setDescription(desTemp.isBlank() ? "" : desTemp);
                s.setPrice(rs.getFloat("Price"));
                String imageTemp = rs.getString("Image") == null ? "" : rs.getString("Image");
                s.setImage(imageTemp.isBlank() ? "" : imageTemp);////
                s.setStatusService(rs.getString("StatusService"));
                s.setTypeService(rs.getString("TypeService"));
                
                return s;
            } else {
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return null;
    }
    
    public List<Service> SearchServices(String ServiceName, String ServiceStatus, String sort) {
        String sql;
        List<Service> Services = new ArrayList<>();
        
        if(sort == null && ServiceName == null && ServiceStatus == null){//??
            sort = "";
            ServiceName = "";
            ServiceStatus = "";
        }
        
        switch(sort){
            case "asc":
                sql = """
                     SELECT [ServiceID]
                     ,[ServiceName]
                     ,[Description]
                     ,[Price]
                     ,[Image]
                     ,[StatusService]
                     ,[TypeService]
                     FROM [dbo].[Service] WHERE [ServiceName] LIKE '%""" + ServiceName + "%' AND [Status] LIKE '%" + ServiceStatus + "%' ORDER BY [PRICE]";
                break;
            case "desc":
                sql = """
                     SELECT [ServiceID]
                     ,[ServiceName]
                     ,[Description]
                     ,[Price]
                     ,[Image]
                     ,[StatusService]
                     ,[TypeService]
                     FROM [dbo].[Service] WHERE [ServiceName] LIKE '%""" + ServiceName + "%' AND [Status] LIKE '%" + ServiceStatus + "%' ORDER BY [PRICE] DESC";
                break;
            default:
                sql = """
                     SELECT [ServiceID]
                     ,[ServiceName]
                     ,[Description]
                     ,[Price]
                     ,[Image]
                     ,[StatusService]
                     ,[TypeService]
                     FROM [dbo].[Service] WHERE [ServiceName] LIKE '%""" + ServiceName + "%' AND [Status] LIKE '%" + ServiceStatus + "%'";
                break;
        }
        
        try {
            ResultSet rs = getData(sql);

            if (rs == null) {
                return null;
            }

            while (rs.next()) {
                Service s = new Service();
                s.setServiceID(rs.getInt("ServiceID"));
                s.setServiceName(rs.getString("ServiceName"));
                String desTemp = rs.getString("Description") == null ? "" : rs.getString("Description");
                s.setDescription(desTemp.isBlank() ? "" : desTemp);
                s.setPrice(rs.getFloat("Price"));
                String imageTemp = rs.getString("Image") == null ? "" : rs.getString("Image");
                s.setImage(imageTemp.isBlank() ? "" : imageTemp);////
                s.setStatusService(rs.getString("StatusService"));
                s.setTypeService(rs.getString("TypeService"));
                
                Services.add(s);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return Services;
    }
    
    public boolean SearchIfServiceExistByName(String ServiceName) {
        String sql = """
                     SELECT [ServiceID]
                     ,[ServiceName]
                     ,[Description]
                     ,[Price]
                     ,[Image]
                     ,[StatusService]
                     ,[TypeService]
                     FROM [dbo].[Service] WHERE [ServiceName] = '""" + ServiceName + "'";

        try {
            ResultSet rs = getData(sql);

            if (rs == null) {
                return false;
            }

            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return false;
    }
    
    public void Add(Service s) {
        String sql = "INSERT INTO [dbo].[Service]\n"
                + "           ([ServiceName]\n"
                + "           ,[Description]\n"
                + "           ,[Price]\n"
                + "           ,[Image]\n"
                + "           ,[StatusService]\n"
                + "           ,[TypeService])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, s.getServiceName());
            pre.setString(2, s.getDescription());
            pre.setFloat(3, s.getPrice());
            pre.setString(4, s.getImage());
            pre.setString(5, s.getStatusService());
            pre.setString(6, s.getTypeService());
            
            ResultSet rs = pre.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public boolean Update(Service s) {
        String sql = "UPDATE [dbo].[Service] SET [ServiceName] = ?, [Description] = ?, [Price] = ?, [Image] = ?, [StatusService] = ?, [TypeService] = ? WHERE [ServiceID] = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, s.getServiceName());
            pre.setString(2, s.getDescription());
            pre.setFloat(3, s.getPrice());
            pre.setString(4, s.getImage());
            pre.setString(5, s.getStatusService());
            pre.setString(6, s.getTypeService());
            pre.setInt(7, s.getServiceID());
            pre.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }
    
    public boolean Delete(int ServiceID) {
        String sql = "DELETE FROM [dbo].[Service] WHERE [ServiceID] = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, ServiceID);
            pre.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
}
