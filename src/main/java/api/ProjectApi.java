package api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import entity.Project;
import service.ProjectService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

@Path("projects")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class ProjectApi {

    @Inject
    private ProjectService projectService;

    private ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

    @GET
    public Response list() throws JsonProcessingException {
        List<Project> projects = projectService.list();
        return Response.ok(objectWriter.writeValueAsString(projects)).build();
    }

    @POST
    public Response create(Project project) throws JsonProcessingException {
        Project created = projectService.create(project);
        return Response.ok(objectWriter.writeValueAsString(created)).build();
    }

    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") Long id) throws JsonProcessingException {
        Project project = projectService.get(id);
        return Response.ok(objectWriter.writeValueAsString(project)).build();
    }

    @PUT
    @Path("/{id}")
    public Response edit(@PathParam("id") Long id, Project project){
        return Response.ok(projectService.edit(id, project)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
        projectService.delete(id);
        return Response.ok().build();
    }

    @POST
    @Path("/{id}/finish")
    public Response finish(@PathParam("id") Long id){
        Project project = projectService.finish(id, new Date());
        return Response.ok(project).build();
    }

    @GET
    @Path("/countActive")
    public Response countActive(){
        return Response.ok(projectService.countActive()).build();
    }


    @GET
    @Path("/countFinished")
    public Response countFinished(){
        return Response.ok(projectService.countFinished()).build();
    }
}
