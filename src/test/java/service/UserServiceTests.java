package service;

import dao.UserDao;
import entity.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

public class UserServiceTests {

    private static final Long ID = 1L;

    UserServiceImpl userService;

    @Before
    public void setUp(){
        userService = new UserServiceImpl();
        userService.userDao = mock(UserDao.class);
    }

    @Test
    public void getUserById(){
        User expected = new User();

        when(userService.userDao.get(Matchers.anyLong())).thenReturn(expected);

        User actual = userService.get(ID);

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void getAllUsers(){
        List<User> users = new ArrayList<>();
        User user = mock(User.class);
        when(user.getId()).thenReturn(ID);
        users.add(user);

        when(userService.userDao.list()).thenReturn(users);
        users = userService.list();

        assertThat(users.size(), is(1));
        assertThat(users.get(0).getId(), is(ID));
    }

    @Test
    public void createUser() {
        User user = new User();

        doNothing().when(userService.userDao).create(Matchers.anyObject());

        userService.create(user);

        verify(userService.userDao).create(user);
    }

    @Test
    public void editUser() {
        User user = new User();

        doNothing().when(userService.userDao).edit(Matchers.anyObject());

        userService.edit(ID, user);

        verify(userService.userDao).edit(user);
    }

    @Test
    public void deleteUser() {
        User user = new User();

        doNothing().when(userService.userDao).delete(Matchers.anyObject());

        userService.delete(ID);

        verify(userService.userDao).delete(user);
    }
}
