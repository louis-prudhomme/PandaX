package fr.efrei.pandax.resource;

import fr.efrei.pandax.model.business.User;
import fr.efrei.pandax.model.core.UserDAO;
import fr.efrei.pandax.security.Role;
import fr.efrei.pandax.security.Secured;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.MediaType.*;

@Secured(Role.ADMIN)
@Path("user")
public class UserResource {
    @GET
    @Produces(APPLICATION_JSON)
    public Response getAll() {
        List<User> all = new UserDAO().getAll();
        return Response.ok(new GenericEntity<>(all) {}).build();
    }

    @POST
    @Consumes(APPLICATION_FORM_URLENCODED)
    public Response createOne(@FormParam("user") User user) {
        user = new UserDAO().create(user);
        return Response.ok("/user/" + user.getId()).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(APPLICATION_JSON)
    public Response deleteOne(@PathParam("id")int id) {
        UserDAO dao = new UserDAO();
        dao.delete(dao.read(id));
        return Response.ok("/mediatype/").build();
    }

    @GET
    @Path("/{id}")
    @Produces(APPLICATION_JSON)
    public Response getOne(@PathParam("id")int id) {
        return Response.ok(new UserDAO().read(id)).build();
    }

    @PUT
    @Consumes(APPLICATION_FORM_URLENCODED)
    public Response updateOne(@FormParam("user") User user) {
        user = new UserDAO().modify(user);
        return Response.ok("/user/" + user.getId()).build();
    }
}
