package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Profile;
import utils.DBContext;

public class ProfileDAO extends DBContext {

    public void Add(Profile p) {
        if (Validate(p)) {
            String SQL = "INSERT INTO [dbo].[Profile]([Name], [PhoneNumber], [Gender], [Address], [AccountID]) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";

            try {
                PreparedStatement st = connection.prepareStatement(SQL);
                st.setString(1, p.getName());
                st.setString(2, p.getPhoneNumber());
                st.setString(3, p.getGender());
                st.setString(5, p.getAddress());
                st.setInt(6, p.getAccountID());

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
}
