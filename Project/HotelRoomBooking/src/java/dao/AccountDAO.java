package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Account;
import utils.DBContext;

public class AccountDAO {
    private Connection conn;

    public AccountDAO() {
        DBContext dbContext = new DBContext();
        this.conn = dbContext.connection;
    }

    public Account validateLogin(String username, String password) {
        String sql = "SELECT * FROM Account WHERE Username = ? AND Password = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Account(rs.getInt("AccountID"), rs.getString("Username"), rs.getString("Email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
