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
public class AccountDAO extends DBContext {

    public List<Account> GetAccountList() {
        String sql = "SELECT * FROM Account WHERE 1 = 1";
        List<Account> Accounts = new ArrayList<>();

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Account a = new Account();
                a.setAccountID(rs.getInt("AccontID"));
                a.setUsername(rs.getString("Username"));
                a.setPassword(rs.getString("Password"));
                a.setEmail(rs.getString("Email"));
                a.setCreatedDate(rs.getDate("CreatedDate"));

                Accounts.add(a);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return Accounts;
    }

    public Account SearchAccount(String Username, String Email, String Password) {
        String sql = "SELECT * FROM [Account] WHERE [Username] = " + Username + " AND [Email] = " + Email + " AND [Password] = " + Password;
        Account a = new Account();

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                a.setAccountID(rs.getInt("AccontID"));
                a.setUsername(rs.getString("Username"));
                a.setPassword(rs.getString("Password"));
                a.setEmail(rs.getString("Email"));
                a.setCreatedDate(rs.getDate("CreatedDate"));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return a;
    }

    public void Add(Account a) {
        if (ValidateEmail(a) && ValidateUsername(a)) {
            String SQL = "INSERT INTO [dbo].[Account]([Username], [Email], [Password], [CreatedDate]) "
                    + "VALUES (?, ?, ?, ?)";

            try {
                PreparedStatement st = connection.prepareStatement(SQL);
                st.setString(1, a.getUsername());
                st.setString(2, a.getEmail());
                st.setString(3, a.getPassword());
                st.setDate(4, a.getCreatedDate());

                st.executeUpdate();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }

    private boolean ValidateEmail(Account a) {
        String sql = "SELECT * FROM [dbo].[Account] WHERE Email = " + a.getEmail();

        try {
            PreparedStatement st = connection.prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            if (rs.next())//check xem neu co account dung trung email
            {
                return false;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return true;
    }

    private boolean ValidateUsername(Account a) {
        String sql = "SELECT * FROM [dbo].[Account] WHERE Username = " + a.getUsername();

        try {
            PreparedStatement st = connection.prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            if (rs.next())//check xem neu co account dung trung username
            {
                return false;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return true;
    }
}
