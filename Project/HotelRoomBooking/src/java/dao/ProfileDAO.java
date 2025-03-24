package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Profile;
import utils.DBContext;
import java.util.ArrayList;
import java.util.List;
import model.*;

public class ProfileDAO extends DBContext {
    public List<Profile> GetProfileList() {
        String sql = "SELECT * FROM [dbo].[Profile] WHERE 1 = 1";
        List<Profile> profiles = new ArrayList<>();

        try {
            ResultSet rs = getData(sql);

            if(rs == null){
                return null;
            }
            
            while (rs.next()) {
                Profile p = new Profile();
                p.setProfileID(rs.getInt("ProfileID"));
                p.setName(rs.getString("Name"));
                p.setPhoneNumber(rs.getString("PhoneNumber"));
                p.setGender(rs.getString("Gender"));
                p.setAddress(rs.getString("Address"));
                p.setAccountID(rs.getInt("AccountID"));

                profiles.add(p);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return profiles;
    }

    public List<Profile> SearchProfile(Profile target) {
        String sql = "SELECT * FROM [dbo].[Profile] WHERE 1 = 1";
        
        if(target.getProfileID() > 0)//??
        {
            sql += " AND [ProfileID] = '" + target.getProfileID() + "'";
        }
        if(!target.getName().isBlank())//??
        {
            sql += " AND [Name] = '" + target.getName() + "'";
        }
        if(!target.getPhoneNumber().isBlank())//??
        {
            sql += " AND [PhoneNumber] = '" + target.getPhoneNumber() + "'";
        }
        if(!target.getGender().isBlank())
        {
            sql += " AND [Gender] = '" + target.getGender() + "'";
        }
        if(!target.getAddress().isBlank())
        {
            sql += " AND [Address] = '" + target.getAddress() + "'";
        }
        if(target.getAccountID() > 0)
        {
            sql += " AND [AccountID] = '" + target.getAccountID() + "'";
        }
        
        List<Profile> profiles = new ArrayList<>();
        
        try {
            ResultSet rs = getData(sql);

            if(rs == null){
                return null;
            }
            
            while (rs.next()) {
                Profile p = new Profile();
                p.setProfileID(rs.getInt("ProfileID"));
                p.setName(rs.getString("Name"));
                p.setPhoneNumber(rs.getString("PhoneNumber"));
                p.setGender(rs.getString("Gender"));
                p.setAddress(rs.getString("Address"));
                p.setAccountID(rs.getInt("AccountID"));

                profiles.add(p);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return profiles;
    }
    
    public Profile SearchProfileById(int profileId) {
        String sql = "SELECT * FROM [dbo].[Profile] WHERE 1 = 1 AND [ProfileID] = '" + profileId + "'";
        
        try {
            ResultSet rs = getData(sql);

            if(rs == null){
                return null;
            }
            
            if (rs.next()) {
                Profile p = new Profile();
                p.setProfileID(rs.getInt("ProfileID"));
                p.setName(rs.getString("Name"));
                p.setPhoneNumber(rs.getString("PhoneNumber"));
                p.setGender(rs.getString("Gender"));
                p.setAddress(rs.getString("Address"));
                p.setAccountID(rs.getInt("AccountID"));

                return p;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return null;
    }
    
    public Profile SearchProfileByAccountId(int accountID) {
        String sql = "SELECT * FROM [dbo].[Profile] WHERE 1 = 1 AND [AccountID] = '" + accountID + "'";
        
        try {
            ResultSet rs = getData(sql);

            if(rs == null){
                return null;
            }
            
            if (rs.next()) {
                Profile p = new Profile();
                p.setProfileID(rs.getInt("ProfileID"));
                p.setName(rs.getString("Name"));
                p.setPhoneNumber(rs.getString("PhoneNumber"));
                p.setGender(rs.getString("Gender"));
                p.setAddress(rs.getString("Address"));
                p.setAccountID(rs.getInt("AccountID"));

                return p;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return null;
    }
    
    public void Add(Profile p) {
        if (/*Validate(p)*/ true) {//????????????
            String SQL = "INSERT INTO [dbo].[Profile]([Name], [PhoneNumber], [Gender], [Address], [AccountID]) "
                    + "VALUES (?, ?, ?, ?, ?)";

            try {
                PreparedStatement st = connection.prepareStatement(SQL);
                st.setString(1, p.getName());
                st.setString(2, p.getPhoneNumber());
                st.setString(3, p.getGender());
                st.setString(4, p.getAddress());
                st.setInt(5, p.getAccountID());
                st.executeUpdate();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }
    private boolean Validate(Profile p) {
        String sql1 = "SELECT * FROM [dbo].[Profile] WHERE AccountID = '" + p.getAccountID() + "'";
        String sql2 = "SELECT * FROM [dbo].[Account] WHERE AccountID = '" + p.getAccountID() + "'";

        try {
            ResultSet rs1 = getData(sql1);
            ResultSet rs2 = getData(sql2);

            //if(rs1 != null || rs2 == null)
                //return false;
            
            int count = 0;
            while (rs1.next())//check xem neu co profile dung trung account
            {
                count++;
            }
            if(count >= 1)
                return false;
            
            count = 0;
            while (rs2.next())//check account exsist
            {
                count++;
            }
            if(count != 1)
                return false;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return true;
    }

    public Profile getProfileByAccountId(int accountId) {
        Profile profile = null;
        String sql = "SELECT * FROM [dbo].[Profile] WHERE AccountID = " + accountId;

        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {
                profile = new Profile(rs.getInt("ProfileID"),
                        rs.getString("Name"),
                        rs.getString("PhoneNumber"),
                        rs.getString("Gender"),
                        rs.getString("Address"),
                        rs.getInt("AccountID"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return profile;
    }
    
    public boolean updateProfile(Profile profile) {
    String sql = "UPDATE Profile SET Name = ?, PhoneNumber = ?, Gender = ?, Address = ? WHERE AccountID = ?";
    try (Connection connection = new DBContext().connection;  PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setString(1, profile.getName());
        stmt.setString(2, profile.getPhoneNumber());
        stmt.setString(3, profile.getGender());
        stmt.setString(4, profile.getAddress());
        stmt.setInt(5, profile.getAccountID());

        return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}

    
    public boolean addProfile(Profile profile) {
        String sql = "INSERT INTO Profile (Name, PhoneNumber, Gender, Address, AccountID) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = new DBContext().connection;
             PreparedStatement stmt = connection.prepareStatement(sql)) {
             
            stmt.setString(1, profile.getName().trim());
            stmt.setString(2, profile.getPhoneNumber().trim());
            stmt.setString(3, profile.getGender());
            stmt.setString(4, profile.getAddress().trim());
            stmt.setInt(5, profile.getAccountID());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
