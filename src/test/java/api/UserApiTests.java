package api;

import com.fasterxml.jackson.core.JsonProcessingException;
import dao.UserDao;
import entity.Status;
import entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import service.UserService;
import service.UserServiceImpl;

import javax.ws.rs.core.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserApiTests {

    UserApi userApi;

    @Before
    public void setUp(){
        userApi = new UserApi();
        userApi.userService = mock(UserService.class);
    }

    @Test
    public void getUserById() throws JsonProcessingException {
        User expected = new User();
        expected.setId(1L);
        expected.setStatus(Status.ACTIVE);
        expected.setFirstName("Jeka");

        when(userApi.userService.get(Matchers.anyLong())).thenReturn(expected);

        Response actualResponse = userApi.get(1L);
    }
}
