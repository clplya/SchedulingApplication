package schedulingapplication.Dao;

import java.util.ArrayList;
import schedulingapplication.DomainObjects.User;

public interface IUserDao {

    public void addUser(int userId, String userName, String password, int active);

    public void deleteUser(int deletedUserId);

    public ArrayList<User> getAllUsers();

    public User getUser(int userId);

    public void updateUserName(int upUserId, String upUserName);
}
