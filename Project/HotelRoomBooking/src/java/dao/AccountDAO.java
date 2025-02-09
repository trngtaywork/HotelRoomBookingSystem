package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Account;
import model.Profile;
import utils.DBContext;

public class AccountDAO {

    private Connection conn;

    public AccountDAO() {
        DBContext dbContext = new DBContext();
        this.conn = dbContext.connection;
    }

    public Account validateLogin(String username, String password) {
        String sql = "SELECT * FROM Account WHERE Username = ? AND Password = ?";
        try ( PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Account(rs.getInt("AccountID"), rs.getString("Username"), rs.getString("Email"), rs.getDate("CreatedDate") );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Account getAccountById(int accountID) {
        String sql = "SELECT a.AccountID, a.Username, a.Email, a.Role, "
                + "p.Name, p.PhoneNumber, p.Gender, p.Address "
                + "FROM Account a "
                + "JOIN Profile p ON a.AccountID = p.AccountID "
                + "WHERE a.AccountID = ?";
        try ( PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, accountID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Account(
                        rs.getInt("AccountID"),
                        rs.getString("Username"),
                        rs.getString("Email"),
                        rs.getString("Role"),
                        rs.getString("Name"),
                        rs.getString("PhoneNumber"),
                        rs.getString("Gender"),
                        rs.getString("Address")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Profile getProfileByAccountId(int accountID) {
        String sql = "SELECT * FROM Profile WHERE AccountID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, accountID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Profile(
                    rs.getInt("ProfileID"),
                    rs.getString("Name"),
                    rs.getString("PhoneNumber"),
                    rs.getString("Gender"),
                    rs.getString("Role"),
                    rs.getString("Address"),
                    rs.getInt("AccountID")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
