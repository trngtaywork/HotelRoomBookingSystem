package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
                return new Account(rs.getInt("AccountID"), rs.getString("Username"), rs.getString("Email"), rs.getDate("CreatedDate"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Account getAccountById(int accountID) {
        String sql = "SELECT * FROM Account WHERE AccountID = ?";
        try ( PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, accountID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Account(
                        rs.getInt("AccountID"),
                        rs.getString("Username"),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        rs.getString("Role"),
                        rs.getBoolean("IsActive"),
                        rs.getDate("CreatedDate")
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Profile getProfileByAccountId(int accountID) {
        String sql = "SELECT * FROM Profile WHERE AccountID = ?";
        try ( PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, accountID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Profile(
                        rs.getInt("ProfileID"),
                        rs.getString("Name"),
                        rs.getString("PhoneNumber"),
                        rs.getString("Gender"),
                        rs.getString("Address"),
                        rs.getInt("AccountID")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Account> getAllAccounts() {
        List<Account> accountList = new ArrayList<>();
        String query = "SELECT * FROM Account";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Account account = new Account(
                        rs.getInt("AccountID"),
                        rs.getString("Username"),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        rs.getString("Role"),
                        rs.getBoolean("IsActive"),
                        rs.getDate("CreatedDate")
                );
                accountList.add(account);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return accountList;
    }
    
    public List<Account> getUsersByRole(String role) {
        List<Account> accountList = new ArrayList<>();
        String query = "SELECT * FROM Account";

        // Nếu role không phải "All", lọc theo role
        if (!"All".equals(role)) {
            query += " WHERE Role = ?";
        }

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            if (!"All".equals(role)) {
                stmt.setString(1, role);
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Account account = new Account(
                    rs.getInt("AccountID"),
                    rs.getString("Username"),
                    rs.getString("Email"),
                    rs.getString("Password"),
                    rs.getString("Role"),
                    rs.getBoolean("IsActive"),
                    rs.getDate("CreatedDate")
                );
                accountList.add(account);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accountList;
    }

    public boolean updateUserStatus(int accountID, boolean isActive) {
        String query = "UPDATE Account SET IsActive = ? WHERE AccountID = ?";
        try ( PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setBoolean(1, isActive);
            stmt.setInt(2, accountID);
            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean deleteAccount(int accountID) {
        String deleteProfileQuery = "DELETE FROM Profile WHERE AccountID = ?";
        String deleteAccountQuery = "DELETE FROM Account WHERE AccountID = ?";

        // Start a transaction
        try {
            conn.setAutoCommit(false); // Disable auto-commit to begin transaction

            // Delete from Profile table
            try ( PreparedStatement profileStmt = conn.prepareStatement(deleteProfileQuery)) {
                profileStmt.setInt(1, accountID);
                profileStmt.executeUpdate();
            }

            // Delete from Account table
            try ( PreparedStatement accountStmt = conn.prepareStatement(deleteAccountQuery)) {
                accountStmt.setInt(1, accountID);
                accountStmt.executeUpdate();
            }

            // Commit transaction
            conn.commit();
            return true;
        } catch (SQLException ex) {
            try {
                // Rollback in case of error
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ex.printStackTrace();
        } finally {
            try {
                conn.setAutoCommit(true); // Restore auto-commit
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    // Method to get account and profile information by accountID
    public Account getAccountWithProfile(int accountID) {
        String sql = "SELECT a.AccountID, a.Username, a.Email, a.Password, a.Role, a.IsActive, a.CreatedDate, "
                + "p.Name, p.PhoneNumber, p.Gender, p.Address "
                + "FROM Account a "
                + "LEFT JOIN Profile p ON a.AccountID = p.AccountID "
                + "WHERE a.AccountID = ?";

        try ( PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, accountID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Account account = new Account(
                        rs.getInt("AccountID"),
                        rs.getString("Username"),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        rs.getString("Role"),
                        rs.getBoolean("IsActive"),
                        rs.getDate("CreatedDate")
                );

                // If Profile information exists, set it to Account object
                Profile profile = new Profile(
                        rs.getInt("AccountID"),
                        rs.getString("Name"),
                        rs.getString("PhoneNumber"),
                        rs.getString("Gender"),
                        rs.getString("Address"),
                        rs.getInt("AccountID")
                );

                account.setProfile(profile); // Set Profile to Account object
                return account;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    // Method to add account
    public boolean addAccount(Account account) {
        String sql = "INSERT INTO Account (Username, Email, Password, CreatedDate, Role, IsActive) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, account.getUsername());
            stmt.setString(2, account.getEmail());
            stmt.setString(3, account.getPassword());
            stmt.setDate(4, account.getCreatedDate());
            stmt.setString(5, account.getRole());
            stmt.setBoolean(6, account.getIsActive());

            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
