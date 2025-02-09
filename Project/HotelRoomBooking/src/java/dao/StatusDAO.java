/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

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
public class StatusDAO extends DBContext
{
    public List<Status> GetStatusList()
    {
        String sql = """
                     SELECT *
                     FROM [dbo].[Status] WHERE 1 = 1""";
        List<Status> Statuses = new ArrayList<>();
        
        try
        {
            ResultSet rs = getData(sql);
            
            while(rs.next())
            {
                Status s = new Status();
                s.setStatusID(rs.getInt("StatusID"));
                s.setStatusName(rs.getString("StatusName"));
                String desTemp = rs.getString("Description") == null ? "" : rs.getString("Description");
                s.setDescription(desTemp.isBlank() ? "" : desTemp);
                
                Statuses.add(s);
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        
        return Statuses;
    }
    
    public Status SearchStatusByID(int ID)
    {
        String sql = """
                     SELECT *
                     FROM [dbo].[Status] WHERE [StatusID] = """ + ID;
        
        try
        {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            if(rs.next())
            {
                Status s = new Status();
                
                s.setStatusID(rs.getInt("StatusID"));
                s.setStatusName(rs.getString("StatusName"));
                String desTemp = rs.getString("Description") == null ? "" : rs.getString("Description");
                s.setDescription(desTemp.isBlank() ? "" : desTemp);
                
                return s;
            }
            else
            {
                return null;
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        
        return null;
    }
}
