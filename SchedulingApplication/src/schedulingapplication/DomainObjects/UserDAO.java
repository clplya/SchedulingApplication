package schedulingapplication.DomainObjects;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import schedulingapplication.DAO;

/**
 *
 * @author Mark Pilkington
 */
public class UserDAO implements DAO {

    private User createUser(ResultSet rs) {
        User u = new User();
        try {
            u.setUserId(rs.getInt("userId"));
            u.setUserName(rs.getString("userName"));
            u.setPassword(rs.getString("password"));
            u.setActive(rs.getInt("active"));
        } catch (SQLException ex) {
        }
        return u;
    }

    public List getUsers() {
        String sql = "select * from users";
        List userList = new ArrayList<>();
        try {
            Class.forName(DRIVER);
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                User u = createUser(rs);
                userList.add(u);
            }
            rs.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
        }
        return userList;
    }

    public List getUserByUserId(int userId) {
        String sql = "select * from user where userId =" + userId;
        List list = new ArrayList<>();
        try {
            Class.forName(DRIVER);
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException ex) {
        }
        return list;
    }
}
