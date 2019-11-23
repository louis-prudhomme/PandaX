package fr.efrei.pandax.resource;

import fr.efrei.pandax.model.business.Publisher;
import fr.efrei.pandax.model.core.PublisherDAO;
import fr.efrei.pandax.security.Role;
import fr.efrei.pandax.security.Secured;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Secured(Role.ADMIN)
@Path("publisher")
public class PublisherResource {
    @GET
    @Secured({Role.USER, Role.ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Publisher> all = new PublisherDAO().getAll();
        return Response.ok(new GenericEntity<>(all) {}).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response createOne(@FormParam("publisher")Publisher p) {
        p = new PublisherDAO().create(p);
        return Response.ok("/publisher/" + p.getId()).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteOne(@PathParam("id")int id) {
        PublisherDAO dao = new PublisherDAO();
        dao.delete(dao.read(id));
        return Response.ok("/publisher/").build();
    }

    @GET
    @Path("/{id}")
    @Secured({Role.USER, Role.ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOne(@PathParam("id")int id) {
        return Response.ok(new PublisherDAO().read(id)).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response updateOne(@FormParam("publisher")Publisher p) {
        p = new PublisherDAO().modify(p);
        return Response.ok("/publisher/" + p.getId()).build();
    }


}
