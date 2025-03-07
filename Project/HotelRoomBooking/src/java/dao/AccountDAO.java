package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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

        if (!"All".equals(role)) {
            query += " WHERE Role = ?";
        }

        try ( PreparedStatement stmt = conn.prepareStatement(query)) {
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

        try {
            conn.setAutoCommit(false);

            try ( PreparedStatement profileStmt = conn.prepareStatement(deleteProfileQuery)) {
                profileStmt.setInt(1, accountID);
                profileStmt.executeUpdate();
            }

            try ( PreparedStatement accountStmt = conn.prepareStatement(deleteAccountQuery)) {
                accountStmt.setInt(1, accountID);
                accountStmt.executeUpdate();
            }

            conn.commit();
            return true;
        } catch (SQLException ex) {
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ex.printStackTrace();
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean addAccount(Account account) {
        String sql = "INSERT INTO Account (AccountID, Username, Email, Password, CreatedDate, Role, IsActive) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try ( PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, account.getAccountID());
            stmt.setString(2, account.getUsername());
            stmt.setString(3, account.getEmail());
            stmt.setString(4, account.getPassword());
            stmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            stmt.setString(6, account.getRole());
            stmt.setBoolean(7, account.getIsActive());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isUsernameTaken(String username) {
        String sql = "SELECT COUNT(*) FROM Account WHERE Username = ?";
        try ( PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isUsernameExists(String username, int currentAccountID) {
        String sql = "SELECT COUNT(*) FROM Account WHERE Username = ? AND AccountID <> ?";
        try ( PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setInt(2, currentAccountID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean isAccountIDExists(int accountID) {
        String sql = "SELECT COUNT(*) FROM Account WHERE AccountID = ?";
        try ( PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, accountID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateUser(int accountID, String username, String email, String password, String role, boolean isActive) {
        String sql = "UPDATE Account SET Username = ?, Email = ?, Password = ?, Role = ?, IsActive = ? WHERE AccountID = ?";
        try ( PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.setString(4, role);
            stmt.setBoolean(5, isActive);
            stmt.setInt(6, accountID);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getPasswordByAccountID(int accountID) {
        String sql = "SELECT Password FROM Account WHERE AccountID = ?";
        try ( PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, accountID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("Password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int generateRandomAccountID() {
        Random random = new Random();
        int accountID;
        boolean exists;

        do {
            accountID = random.nextInt(900000) + 100000;
            exists = isAccountIDExists(accountID);
        } while (exists);

        return accountID;
    }

    public boolean isUsernameExists(String username) {
        String sql = "SELECT COUNT(*) FROM Account WHERE Username = ?";
        try ( PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Account> getUsersFiltered(String role, String status, String username, String sortOrder) {
        List<Account> accountList = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM Account WHERE 1=1");

        if (!role.equals("All")) {
            query.append(" AND Role = ?");
        }
        if (!status.equals("All")) {
            query.append(" AND IsActive = ?");
        }
        if (!username.isEmpty()) {
            query.append(" AND Username LIKE ?");
        }

        query.append(" ORDER BY CreatedDate ").append(sortOrder.equals("asc") ? "ASC" : "DESC");

        try ( Connection conn = new DBContext().connection;  PreparedStatement stmt = conn.prepareStatement(query.toString())) {
            int paramIndex = 1;

            if (!role.equals("All")) {
                stmt.setString(paramIndex++, role);
            }
            if (!status.equals("All")) {
                stmt.setBoolean(paramIndex++, status.equals("Active"));
            }
            if (!username.isEmpty()) {
                stmt.setString(paramIndex++, "%" + username + "%");
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                accountList.add(new Account(
                        rs.getInt("AccountID"),
                        rs.getString("Username"),
                        rs.getString("Email"),
                        rs.getString("Role"),
                        rs.getBoolean("IsActive"),
                        rs.getDate("CreatedDate")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountList;
    }
    
    public boolean updateUserPassword(int accountID, String newPassword) {
    String sql = "UPDATE Account SET Password = ? WHERE AccountID = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, newPassword);
        stmt.setInt(2, accountID);
        return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}


}
