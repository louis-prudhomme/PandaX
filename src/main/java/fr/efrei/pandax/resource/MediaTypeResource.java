package fr.efrei.pandax.resource;

import fr.efrei.pandax.model.business.MediaType;
import fr.efrei.pandax.model.core.MediaTypeDAO;
import fr.efrei.pandax.security.Role;
import fr.efrei.pandax.security.Secured;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.MediaType.*;

@Secured(Role.ADMIN)
@Path("mediatype")
public class MediaTypeResource {
    @GET
    @Secured({Role.USER, Role.ADMIN})
    @Produces(APPLICATION_JSON)
    public Response getAll() {
        List<MediaType> all = new MediaTypeDAO().getAll();
        return Response.ok(new GenericEntity<>(all) {}).build();
    }

    @POST
    @Consumes(APPLICATION_FORM_URLENCODED)
    public Response createOne(@FormParam("mediatype") MediaType type) {
        type = new MediaTypeDAO().create(type);
        return Response.ok("/mediatype/" + type.getId()).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(APPLICATION_JSON)
    public Response deleteOne(@PathParam("id")int id) {
        MediaTypeDAO dao = new MediaTypeDAO();
        dao.delete(dao.read(id));
        return Response.ok("/mediatype/").build();
    }

    @GET
    @Path("/{id}")
    @Secured({Role.USER, Role.ADMIN})
    @Produces(APPLICATION_JSON)
    public Response getOne(@PathParam("id")int id) {
        return Response.ok(new MediaTypeDAO().read(id)).build();
    }

    @PUT
    @Consumes(APPLICATION_FORM_URLENCODED)
    public Response updateOne(@FormParam("mediatype")MediaType type) {
        type = new MediaTypeDAO().modify(type);
        return Response.ok("/mediatype/" + type.getId()).build();
    }
}
