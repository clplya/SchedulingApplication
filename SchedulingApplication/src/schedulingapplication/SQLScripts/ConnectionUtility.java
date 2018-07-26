/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulingapplication.SQLScripts;

import java.sql.*;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author clply_000
 */
public class ConnectionUtility {
    //Connection conn = DriverManager.getConnection("jdbc:mysql://52.206.157.109:3306/U03xBv");
    private static String url = "jdbc:mysql://52.206.157.109:3306/U03xBv";
    private static String driver = "com.mysql.jdbc.Driver";
    private static String userName = "";
    private static String password = "";
    
//    private static void connect() throws SQLException{
//        Connection conn = DriverManager.getConnection("jdbc:mysql://52.206.157.109:3306/U03xBv");
//    }
//    
//    private static Connection connect2() throws SQLException{
//        Connection conn = DriverManager.getConnection("jdbc:mysql://52.206.157.109:3306/U03xBv");
//        return(conn);
//    }
    
    public void search() throws SQLException{
        Connection conn = DriverManager.getConnection("jdbc:mysql://52.206.157.109:3306/U03xBv");
        Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        
        Map<Integer, String> cityIdMap = new HashMap<>();
        ResultSet rs = stmt.executeQuery("select cityId from address");
        while(rs.next()) {
            int id = rs.getInt("cityId");
            String address = rs.getString("address");
            cityIdMap.put(id, address);
            
            System.out.println(cityIdMap);
            
        }
            System.out.println(cityIdMap);
    }
}
