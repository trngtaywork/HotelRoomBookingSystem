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
import utils.EncryptKey;

/**
 *
 * @author My PC
 */
public class AccountDAO extends DBContext {

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

    public Account SearchAccount(String Username, String Email, String Password) {
        String sql = "SELECT [AccountID], [Username], [Email], [Password], [CreatedDate], [Role], [IsActive] FROM [dbo].[Account] WHERE [Username] = '" + Username + "' AND [Email] = '" + Email + "' AND [Password] = '" + Password + "'";
        Account a = new Account();

        try {
            ResultSet rs = getData(sql);

            if (rs == null) {
                return null;
            }

            if (rs.next()) {
                a.setAccountID(rs.getInt("AccountID"));
                a.setUsername(rs.getString("Username"));
                a.setPassword(rs.getString("Password"));
                a.setEmail(rs.getString("Email"));
                a.setCreatedDate(rs.getDate("CreatedDate"));
                a.setRole(rs.getString("Role"));
                a.setIsActive(rs.getBoolean("IsActive"));
            }

            return a;
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return null;
    }

    public List<Account> SearchAccounts(String Username, String Email, String Password) {
        String sql = "SELECT [AccountID], [Username], [Email], [Password], [CreatedDate], [Role], [IsActive] FROM [dbo].[Account] WHERE [Username] = '" + Username + "' AND [Email] = '" + Email + "' AND [Password] = '" + Password + "'";
        List<Account> list = new ArrayList<Account>();

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

                list.add(a);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return list;
    }

    public Account SearchAccount(int accountId) {
        String sql = "SELECT [AccountID], [Username], [Email], [Password], [CreatedDate], [Role], [IsActive] FROM [dbo].[Account] WHERE [Account].[AccountID] = " + accountId;
        Account a = new Account();

        try {
            ResultSet rs = getData(sql);

            if (rs == null) {
                return null;
            }

            if (rs.next()) {
                a.setAccountID(rs.getInt("AccountID"));
                a.setUsername(rs.getString("Username"));
                a.setPassword(rs.getString("Password"));
                a.setEmail(rs.getString("Email"));
                a.setCreatedDate(rs.getDate("CreatedDate"));
                a.setRole(rs.getString("Role"));
                a.setIsActive(rs.getBoolean("IsActive"));
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }

        return a;
    }

    public void Add(Account a) {
        if (ValidateInput(a)) {
            String SQL = "INSERT INTO [dbo].[Account]([Username], [Email], [Password], [CreatedDate], [Role], [IsActive]) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";

            try {
                PreparedStatement st = connection.prepareStatement(SQL);
                st.setString(1, a.getUsername());
                st.setString(2, a.getEmail());
                st.setString(3, a.getPassword());
                st.setDate(4, a.getCreatedDate());
                st.setString(5, a.getRole());
                st.setBoolean(6, a.getIsActive());

                st.executeUpdate();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }

    public void Delete(int accountId) {
        if (SearchAccount(accountId) != null) {
            String SQL = "REMOVE FROM [dbo].[Account] WHERE [AccountID] = '" + accountId + "'";

            try {
                PreparedStatement st = connection.prepareStatement(SQL);
                st.executeUpdate();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }

    public boolean ValidateInput(Account a) {
        List<Account> temp = SearchAccounts(a.getUsername(), a.getEmail(), a.getPassword());
        if (IsNullOrEmpty(a.getUsername()) || IsNullOrEmpty(a.getEmail()) || IsNullOrEmpty(a.getPassword())) {
            return false;
        } else if (temp != null) {
            return false;
        }
        /*
        else if(!temp.isEmpty()){
            return false;
        }
        /*
        else {
            return a.getIsActive() ? (ValidateEmail(a) && ValidateUsername(a)) : false;
        }
         */
        return true;
    }

    private boolean ValidateEmail(Account a) {
        String sql = "SELECT * FROM [dbo].[Account] WHERE Email = '" + a.getEmail() + "'";

        try {
            PreparedStatement st = connection.prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            int count = 0;
            while (rs.next())//check xem neu co account dung trung email
            {
                count++;
            }

            if (count > 1) {
                return false;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return true;
    }

    private boolean ValidateUsername(Account a) {
        String sql = "SELECT * FROM [dbo].[Account] WHERE Username = '" + a.getUsername() + "'";

        try {
            PreparedStatement st = connection.prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            int count = 0;
            while (rs.next())//check xem neu co account dung trung username
            {
                count++;
            }

            if (count > 1) {
                return false;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return true;
    }

    private boolean IsNullOrEmpty(String s) {
        return s.trim().length() == 0 || s.equals(null) || s.equals("");
    }
}
