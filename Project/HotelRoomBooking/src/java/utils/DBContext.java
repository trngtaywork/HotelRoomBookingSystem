package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBContext {
    public Connection connection = null;
    public DBContext()
    {
        try
        {

            String url = "jdbc:sqlserver://localhost:1433;databaseName=HRBS;trustServerCertificate=true";
            String username = "sa";
            String password = "12345678";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, username, password);
        }
        catch(Exception ex)
        {
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
