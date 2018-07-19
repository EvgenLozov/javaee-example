package dao;

import entity.Status;
import entity.User;

import java.util.List;

public interface UserDao {
    User get(Long userId);
    List<User> list();
//    Long create(User newUser);
    User create(User newUser);
    void edit(User user);
    void delete(User user);

    long countWithStatus(Status status);
}
