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
public class TypeDAO extends DBContext
{
    public List<Type> GetTypeList()
    {
        String sql = """
                     SELECT *
                     FROM [dbo].[Type] WHERE 1 = 1""";
        List<Type> Types = new ArrayList<>();
        
        try
        {
            ResultSet rs = getData(sql);
            
            while(rs.next())
            {
                Type t = new Type();
                t.setTypeID(rs.getInt("TypeID"));
                t.setTypeName(rs.getString("TypeName"));
                String desTemp = rs.getString("Description") == null ? "" : rs.getString("Description");
                t.setDescription(desTemp.isBlank() ? "" : desTemp);
                
                Types.add(t);
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        
        return Types;
    }
    
    public Type SearchTypeByID(int ID)
    {
        String sql = """
                     SELECT *
                     FROM [dbo].[Type] WHERE [TypeID] = """ + ID;
        
        try
        {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            if(rs.next())
            {
                Type t = new Type();
                
                t.setTypeID(rs.getInt("TypeID"));
                t.setTypeName(rs.getString("TypeName"));
                String desTemp = rs.getString("Description") == null ? "" : rs.getString("Description");
                t.setDescription(desTemp.isBlank() ? "" : desTemp);
                
                return t;
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
