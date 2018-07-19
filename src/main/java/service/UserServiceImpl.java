package service;

import dao.UserDao;
import entity.Status;
import entity.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class UserServiceImpl implements UserService {

    @Inject
    UserDao userDao;

    @Override
    public User get(Long userId) {
        return userDao.get(userId);
    }

    @Override
    public List<User> list() {
        return userDao.list();
    }

    @Override
    public User create(User newUser) {
        return userDao.create(newUser);
    }

    @Override
    public User edit(Long userId, User user) {
        userDao.edit(user);
        return user;
    }

    @Override
    public void delete(Long userId) {
        User user = userDao.get(userId);
        if (user != null)
            userDao.delete(user);
    }

    @Override
    public long countWithStatus(Status status) {
        return userDao.countWithStatus(status);
    }
}
