package dao;

import entity.Status;
import entity.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.*;

public class UserDaoTests {

    private static final Long ID = 1L;

    UserDaoH2 userDao;

    @Before
    public void setUp(){
        userDao = new UserDaoH2();
        userDao.entityManager = mock(EntityManager.class);
    }

    @Test
    public void getUserById(){
        User expected = new User();

        when(userDao.entityManager.find(Matchers.anyObject(), Matchers.anyLong())).thenReturn(expected);

        User actual = userDao.get(ID);

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void getAllUsers(){
        List<User> users = new ArrayList<>();
        User user = mock(User.class);
        when(user.getId()).thenReturn(ID);
        users.add(user);

        Query mockedQuery = mock(Query.class);
        when(mockedQuery.getResultList()).thenReturn(users);
        when(userDao.entityManager.createQuery(Matchers.anyString())).thenReturn(mockedQuery);

        users = userDao.list();

        assertThat(users.size(), is(1));
        assertThat(users.get(0).getId(), is(ID));
    }

    @Test
    public void getUserWithStatus(){
        TypedQuery mockedQuery = mock(TypedQuery.class);
        when(mockedQuery.getSingleResult()).thenReturn(1L);
        when(userDao.entityManager.createQuery(Matchers.anyString(), Matchers.anyObject())).thenReturn(mockedQuery);
        when(mockedQuery.setParameter(Matchers.anyString(), Matchers.anyObject())).thenReturn(mockedQuery);

        long actualCount = userDao.countWithStatus(Status.ACTIVE);

        assertThat(actualCount, is(1L));
    }

    @Test
    public void createUser() {
        User user = new User();

        doNothing().when(userDao.entityManager).persist(Matchers.anyObject());

        userDao.create(user);

        verify(userDao.entityManager).persist(user);
    }

    @Test
    public void editUser() {
        User user = new User();

        when(userDao.entityManager.merge(Matchers.anyObject())).thenReturn(user);

        userDao.edit(user);

        verify(userDao.entityManager).merge(user);
    }

    @Test
    public void deleteUser() {
        User user = new User();

        doNothing().when(userDao.entityManager).remove(Matchers.anyObject());

        userDao.delete(user);

        verify(userDao.entityManager).remove(user);
    }

}
