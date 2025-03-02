/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author My PC
 */
public class DBContext {

    protected Connection connection = null;

    public DBContext() {
        try {
            //String url = "jdbc:sqlserver://localhost\\NGUYENVIETCUONG:1433;databaseName=HotelRoomBooking;encrypt=true;trustServerCertificate=true";
            String url = "jdbc:sqlserver://localhost\\NGUYENVIETCUONG:1433;databaseName=HotelRoomBooking;trustServerCertificate=true";
            String username = "admin";
            String password = "admin";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public ResultSet getData(String sqlQuery) {
        ResultSet rs = null;
        Statement statement;
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = statement.executeQuery(sqlQuery);
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public static void main(String[] args) {
        new DBContext();
    }
}
