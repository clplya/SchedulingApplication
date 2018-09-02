package schedulingapplication.Dao;

import java.sql.*;
import java.sql.Connection;

public class DataSource {

    private final static String URL = "jdbc:mysql://52.206.157.109:3306/U03xBv";
    private final static String DATASOURCEDRIVER = "com.mysql.cj.jdbc.Driver";
    private final static String USER = "U03xBv";
    private final static String PASSWORD = "53688111925";
    private static Connection conn = null;

    public static String getUrl() {
        return URL;
    }

    public static String getDataSourceDriver() {
        return DATASOURCEDRIVER;
    }

    public static String getUsername() {
        return USER;
    }

    public static String getPassword() {
        return PASSWORD;
    }

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(DATASOURCEDRIVER);
            try {
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException ex) {
                System.out.println("Failed to create the DB connection.");
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver not found");
        }
        return conn;
    }

    public static Connection getConnection(String input1, String input2) throws SQLException {
        try {
            Class.forName(DATASOURCEDRIVER);
            try {
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException ex) {
                System.out.println("Failed to create the DB connection.");
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver not found");
        }
        return conn;
    }

    public static ResultSet dbQuery(String stmtQuery) {
        Statement stmt;
        ResultSet rs = null;
        try {
            getConnection();

            stmt = conn.createStatement();

            rs = stmt.executeQuery(stmtQuery);
        } catch (SQLException ex) {
        }
        return rs;
    }
}
