//package dao;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import model.Account;
//import util.DBContext;
//
//public class AccountDAO {
//
//    public Account getAccountByUsernameAndEmail(String username, String email) {
//        Account account = null;
//        String sql = "SELECT * FROM Account WHERE Username = ? AND Email = ?";
//
//        try (Connection conn = DBContext.getConnection();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//
//            ps.setString(1, username);
//            ps.setString(2, email);
//            ResultSet rs = ps.executeQuery();
//
//            if (rs.next()) {
//                if (rs.next()) {
//    account = new Account(
//        rs.getInt("AccountID"),
//        rs.getString("Username"),
//        rs.getString("Email"),
//        rs.getString("Password"),
//        rs.getString("CreatedDate") 
//    );
//}
//
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return account;
//    }
//
//    public boolean updatePassword(String username, String newPassword) {
//        String sql = "UPDATE Account SET Password = ? WHERE Username = ?";
//        
//        try (Connection conn = DBContext.getConnection();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//
//            ps.setString(1, newPassword); 
//            ps.setString(2, username);
//            
//            int rowsUpdated = ps.executeUpdate();
//            return rowsUpdated > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//    
//   
//public boolean checkCurrentPassword(String username, String currentPassword) {
//    String sql = "SELECT * FROM Account WHERE Username = ? AND Password = ?";
//
//    try (Connection conn = DBContext.getConnection();
//         PreparedStatement ps = conn.prepareStatement(sql)) {
//
//        ps.setString(1, username);
//        ps.setString(2, currentPassword); 
//        ResultSet rs = ps.executeQuery();
//
//        return rs.next();
//    } catch (SQLException e) {
//        e.printStackTrace();
//    }
//    return false;
//}
//
//}
