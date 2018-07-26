/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulingapplication.SQLScripts;
import java.sql.*;
import schedulingapplication.JDBCConnection;
/**
 *
 * @author clply_000
 */
public class AddDefaultUser {
    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet rs = null;
    
    public static void addFirstUser() throws SQLException{
        connection = JDBCConnection.getConnection();
        statement = connection.createStatement();
        statement.executeUpdate("insert into user (1,2) value (1,admin) on duplicate key update 1=1;");
    }
}
