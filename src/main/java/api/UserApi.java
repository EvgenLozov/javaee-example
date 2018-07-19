package api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import entity.Status;
import entity.User;
import service.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("users")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class UserApi {

    @Inject
    UserService userService;

    private ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

    @GET
    public Response list() throws JsonProcessingException {
        List<User> users = userService.list();
        return Response.ok(objectWriter.writeValueAsString(users)).build();
    }

    @POST
    public Response create(User user) throws JsonProcessingException {
        User created = userService.create(user);
        return Response.ok(objectWriter.writeValueAsString(created)).build();
    }

    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") Long id) throws JsonProcessingException {
        User user = userService.get(id);
        return Response.ok(objectWriter.writeValueAsString(user)).build();
    }

    @PUT
    @Path("/{id}")
    public Response edit(@PathParam("id") Long id, User user){
        return Response.ok(userService.edit(id, user)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
        userService.delete(id);
        return Response.ok().build();
    }

    @POST
    @Path("/{id}/changeStatus")
    public  Response changeStatus(@PathParam("id") Long userId, @QueryParam("status") String status){
        Status statusEnum = Status.valueOf(status.toUpperCase());
        User user = userService.get(userId);
        if (user == null)
            return Response.notModified("User with id: " + userId + " does not exist").build();

        user.setStatus(statusEnum);

        userService.edit(userId, user);

        return Response.ok().build();
    }

    @GET
    @Path("/count/{status}")
    public Response countStatus(@PathParam("status") String status){
        Status statusEnum = Status.valueOf(status.toUpperCase());
        long count = userService.countWithStatus(statusEnum);
        return Response.ok(count).build();
    }
}
