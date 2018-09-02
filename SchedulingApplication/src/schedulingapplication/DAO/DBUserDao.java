package schedulingapplication.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import schedulingapplication.DomainObjects.User;

public class DBUserDao implements IUserDao {

    private User user;
    private final ArrayList<User> userList;

    public DBUserDao() {
        userList = new ArrayList<>();
        user = null;
    }

    @Override
    public void addUser(int userId, String userName, String password, int active) {
        Statement stmt = null;

        try {
            Connection conn = DataSource.getConnection();

            stmt = conn.createStatement();
            String sql = "insert into user(userId,userName,password,active,createBy,createDate,lastUpdatedBy) values (" + userId + ",'" + userName + "','" + password + "'," + active + ",1,now(),1)";
            int result = stmt.executeUpdate(sql);
            System.out.println("Inserting number of records: " + result);

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteUser(int deletedUserId) {
        Statement stmt = null;

        try {
            Connection conn = DataSource.getConnection();

            stmt = conn.createStatement();
            String sql = "delete from user where userId=" + deletedUserId;
            int result = stmt.executeUpdate(sql);
            System.out.println("Deleting number of records: " + result);

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public ArrayList<User> getAllUsers() {
        Statement stmt = null;

        try {
            Connection conn = DataSource.getConnection();

            stmt = conn.createStatement();
            String sql = "select userId,userName,password,active from user";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int userId = rs.getInt(1);
                String userName = rs.getString(2);
                String password = rs.getString(3);
                int active = rs.getInt(4);

                user = new User(userId, userName, password, active);
                userList.add(user);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }

        }
        return userList;
    }

    @Override
    public User getUser(int userId) {
        Statement stmt = null;

        try {
            Connection conn = DataSource.getConnection();
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("select userId,userName,password,active from user where userId =" + userId);

            while (rs.next()) {
                int userID = rs.getInt(1);
                String userName = rs.getString(2);
                String password = rs.getString(3);
                int active = rs.getInt(4);

                user = new User(userID, userName, password, active);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return user;
    }

    @Override
    public void updateUserName(int upUserId, String upUserName) {
        Statement stmt = null;

        try {
            Connection conn = DataSource.getConnection();
            stmt = conn.createStatement();

            String updateSql = null;
            updateSql = "update user set user.userName =" + upUserName + " where user.userId =" + upUserId;
            stmt.executeUpdate(updateSql);

            String selectSql = "select userId,userName,password,active from user where user.userId=" + upUserId;
            ResultSet result = stmt.executeQuery(selectSql);

            while (result.next()) {
                int userId = result.getInt(1);
                String userName = result.getString(2);
                String password = result.getString(3);
                int active = result.getInt(4);

                user = new User(userId, userName, password, active);
                System.out.println("Updated User Name: " + user.getUserName());
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

}
/* Use below to compare password entry to DB values
import java.io.*;
import java.util.Arrays;

public class PasswordCompareSample {
   public static void main(String[] args) throws NumberFormatException, IOException {
      Console console = System.console();
      if(console == null) {
         throw new RuntimeException("Console not available");
      } else {
         char[] password = console.readPassword("Enter your password: ");
         console.format("Enter your password again:  ");
         console.flush();
         char[] verify = console.readPassword();
         boolean match = Arrays.equals(password,verify);

         // Immediately clear passwords from memory
         for(int i=0; i<password.length; i++) {
            password[i]='x';
         }
         for(int i=0; i<verify.length; i++) {
            verify[i]='x';
         }

         console.format("Your password was "+(match ? "correct": "incorrect"));
      }
   }
}*/
