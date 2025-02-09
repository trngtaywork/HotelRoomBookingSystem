/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.*;
import utils.DBContext;

/**
 *
 * @author My PC
 */
public class ProfileDAO extends DBContext
{
    public void Add(Profile p)
    {
        if(Validate(p))
        {
            String SQL = "INSERT INTO [dbo].[Profile]([Name], [PhoneNumber], [Gender], [Role], [Address], [AccountID]) "
                       + "VALUES (?, ?, ?, ?, ?, ?)";
            
            try
            {
                PreparedStatement st = connection.prepareStatement(SQL);
                st.setString(1, p.getName());
                st.setString(2, p.getPhoneNumber());
                st.setString(3, p.getGender());
                st.setString(4, p.getRole());
                st.setString(5, p.getAddress());
                st.setInt(6, p.getAccountID());
                
                st.executeUpdate();
            }
            catch (Exception e)
            {
                System.out.println(e.toString());
            }
        }
    }
    
    private boolean Validate(Profile p)
    {
        String sql1 = "SELECT * FROM [dbo].[Profile] WHERE AccountID = " + p.getAccountID();
        String sql2 = "SELECT * FROM [dbo].[Account] WHERE AccountID = " + p.getAccountID();
        
        try
        {
            PreparedStatement st1 = connection.prepareStatement(sql1);
            PreparedStatement st2 = connection.prepareStatement(sql2);
            
            ResultSet rs1 = st1.executeQuery();
            ResultSet rs2 = st2.executeQuery();
            
            if(rs1.next())//check xem neu co profile dung trung account
            {
                return false;
            }
            
            if(!rs2.next())//check account exsist
            {
                return false;
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        
        return true;
    }
}
