/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
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
public class ProfileDAO extends DBContext {

    public List<Profile> GetProfileList() {
        String sql = "SELECT * FROM [dbo].[Profile] WHERE 1 = 1";
        List<Profile> profiles = new ArrayList<>();

        try {
            ResultSet rs = getData(sql);

            if (rs == null) {
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

        if (target.getProfileID() > 0)//??
        {
            sql += " AND [ProfileID] = '" + target.getProfileID() + "'";
        }
        if (!target.getName().isBlank())//??
        {
            sql += " AND [Name] = '" + target.getName() + "'";
        }
        if (!target.getPhoneNumber().isBlank())//??
        {
            sql += " AND [PhoneNumber] = '" + target.getPhoneNumber() + "'";
        }
        if (!target.getGender().isBlank()) {
            sql += " AND [Gender] = '" + target.getGender() + "'";
        }
        if (!target.getAddress().isBlank()) {
            sql += " AND [Address] = '" + target.getAddress() + "'";
        }
        if (target.getAccountID() > 0) {
            sql += " AND [AccountID] = '" + target.getAccountID() + "'";
        }

        List<Profile> profiles = new ArrayList<>();

        try {
            ResultSet rs = getData(sql);

            if (rs == null) {
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

            if (rs == null) {
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

            if (rs == null) {
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
        if (/*Validate(p)*/true) {//????????????
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
            if (count >= 1) {
                return false;
            }

            count = 0;
            while (rs2.next())//check account exsist
            {
                count++;
            }
            if (count != 1) {
                return false;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return true;
    }

    /*
    public void Add(Profile p) {
        if (Validate(p)) {
            String SQL = "INSERT INTO [dbo].[Profile]([Name], [PhoneNumber], [Gender], [Address], [AccountID]) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";

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

    // Phương thức kiểm tra tính hợp lệ của profile
    private boolean Validate(Profile p) {
        String sql1 = "SELECT * FROM [dbo].[Profile] WHERE AccountID = " + p.getAccountID();
        String sql2 = "SELECT * FROM [dbo].[Account] WHERE AccountID = " + p.getAccountID();

        try {
            PreparedStatement st1 = connection.prepareStatement(sql1);
            PreparedStatement st2 = connection.prepareStatement(sql2);

            ResultSet rs1 = st1.executeQuery();
            ResultSet rs2 = st2.executeQuery();

            if (rs1.next()) {
                return false;
            }

            if (!rs2.next()) {
                return false;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return true;
    }
     */
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
        try (Connection connection = new DBContext().connection; PreparedStatement stmt = connection.prepareStatement(sql)) {
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

        try (Connection connection = new DBContext().connection; PreparedStatement stmt = connection.prepareStatement(sql)) {

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

    public List<Account> GetAccountList() {
        String sql = "SELECT [AccountID], [Username], [Email], [Password], [CreatedDate], [Role], [IsActive] FROM Account WHERE 1 = 1";
        List<Account> Accounts = new ArrayList<>();

        try {
            ResultSet rs = getData(sql);

            if (rs == null) {
                return null;
            }

            while (rs.next()) {
                Account a = new Account();
                a.setAccountID(rs.getInt("AccountID"));
                a.setUsername(rs.getString("Username"));
                a.setPassword(rs.getString("Password"));
                a.setEmail(rs.getString("Email"));
                a.setCreatedDate(rs.getDate("CreatedDate"));
                a.setRole(rs.getString("Role"));
                a.setIsActive(rs.getBoolean("IsActive"));

                Accounts.add(a);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return Accounts;
    }
}
