package dao;

import model.User;
import java.util.List;

//TODO same as TransportDao
public interface UserDao {

    //create
    void add(User user);

    //read
    User getBy(Long id);

    User getBy(String login);

    User getBy(String login, String password);

    List<User> getAll();

    //update
    void update(User user);

    //delete
    void delete(User user);
}
