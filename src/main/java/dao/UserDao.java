package dao;

import model.User;
import java.util.List;

public interface UserDao {

    //create
    boolean insert(User user);

    //read
    User getBy(String login);

    User getBy(String login, String password);

    List<User> getAll();

    //update
    boolean update(User user);

    //delete
    boolean delete(User user);
}
