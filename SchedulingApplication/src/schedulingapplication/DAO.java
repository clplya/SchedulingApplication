package schedulingapplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Mark Pilkington
 */
public interface DAO {
    
   public static final String DB_URL = "jdbc:mysql://52.206.157.109:3306/U03xBv";
   public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
   public static final String USER = "U03xBv";
   public static final String PASS = "53688111925";
   public static final String sql = "select c.customerId,c.customerName,a.address ,a.phone from customer c "
                      + "join address a on c.addressId = a.addressId";
}