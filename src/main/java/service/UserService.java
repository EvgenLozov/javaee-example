package service;

import entity.Status;
import entity.User;

import java.util.List;

public interface UserService {
    User get(Long userId);
    List<User> list();
    User create(User newUser);
    User edit(Long userId, User user);
    void delete(Long userId);
    long countWithStatus(Status status);
}
