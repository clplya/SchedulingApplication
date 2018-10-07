package schedulingapplication;

import java.sql.*;

public class JDBCConnection {

    private final static String url = "jdbc:mysql://52.206.157.109:3306/U03xBv";
    private final static String jdbcDriver = "com.mysql.cj.jdbc.Driver";
    private final static String user = "U03xBv";
    private final static String password = "53688111925";
    private static Connection conn = null;

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(jdbcDriver);
            try {
                conn = DriverManager.getConnection(url, user, password);
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
