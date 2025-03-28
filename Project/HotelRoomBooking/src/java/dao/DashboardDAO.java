package dao;

import model.MonthlyRevenue;
import utils.DBContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DashboardDAO {

    private Connection conn;

    public DashboardDAO() {
        this.conn = new DBContext().connection;
    }

    public List<MonthlyRevenue> getRevenueByYear(int year) {
        List<MonthlyRevenue> monthlyRevenues = new ArrayList<>();
        String sql = "SELECT YEAR(BookingDate) AS Year, MONTH(BookingDate) AS Month, SUM(TotalAmount) AS TotalRevenue "
                + "FROM Booking "
                + "WHERE YEAR(BookingDate) = ? "
                + "GROUP BY YEAR(BookingDate), MONTH(BookingDate) "
                + "ORDER BY Month";
        try ( PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, year);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                monthlyRevenues.add(new MonthlyRevenue(
                        rs.getInt("Month"),
                        rs.getDouble("TotalRevenue")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return monthlyRevenues;
    }

    public List<Integer> getAvailableYears() {
        List<Integer> years = new ArrayList<>();
        String sql = "SELECT DISTINCT YEAR(BookingDate) AS Year FROM Booking ORDER BY Year DESC";
        try ( Statement stmt = conn.createStatement();  ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                years.add(rs.getInt("Year"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return years;
    }

    public List<MonthlyRevenue> getAnnualRevenue() {
        List<MonthlyRevenue> list = new ArrayList<>();
        String sql = "SELECT YEAR(BookingDate) AS Year, SUM(TotalAmount) AS TotalRevenue "
                + "FROM Booking "
                + "GROUP BY YEAR(BookingDate) "
                + "ORDER BY YEAR(BookingDate) DESC";
        try ( Statement stmt = conn.createStatement();  ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new MonthlyRevenue(
                        rs.getInt("Year"),
                        rs.getDouble("TotalRevenue")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<MonthlyRevenue> getLastFiveYearsRevenue() {
        List<MonthlyRevenue> list = new ArrayList<>();
        int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        int startYear = currentYear - 4; // 5 năm gần nhất

        String sql = "SELECT YEAR(BookingDate) AS Year, SUM(TotalAmount) AS TotalRevenue "
                + "FROM Booking "
                + "WHERE YEAR(BookingDate) BETWEEN ? AND ? "
                + "GROUP BY YEAR(BookingDate) "
                + "ORDER BY YEAR(BookingDate) DESC";
        try ( PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, startYear);
            stmt.setInt(2, currentYear);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new MonthlyRevenue(
                        rs.getInt("Year"),
                        rs.getDouble("TotalRevenue")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
