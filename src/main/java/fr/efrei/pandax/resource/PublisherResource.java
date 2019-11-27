package fr.efrei.pandax.resource;

import fr.efrei.pandax.model.business.Media;
import fr.efrei.pandax.model.business.Publisher;
import fr.efrei.pandax.model.core.MediaDAO;
import fr.efrei.pandax.model.core.PublisherDAO;
import fr.efrei.pandax.security.Role;
import fr.efrei.pandax.security.Secured;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Secured(Role.ADMIN)
@Path("publisher")
public class PublisherResource {
    @Context
    UriInfo uriInfo;

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
        return Response
                .ok(uriInfo.getBaseUriBuilder()
                        .path(PublisherResource.class)
                        .path(PublisherResource.class, "getOne")
                        .build(p.getId()).toString())
                .build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteOne(@PathParam("id")int id) {
        PublisherDAO dao = new PublisherDAO();
        dao.delete(dao.read(id));
        return Response
                .ok(uriInfo.getBaseUriBuilder()
                    .path(PublisherResource.class)
                    .path(PublisherResource.class, "getAll")
                    .build().toString())
                .build();
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
        return Response
                .ok(uriInfo.getBaseUriBuilder()
                    .path(PublisherResource.class)
                    .path(PublisherResource.class, "getOne")
                    .build(p.getId()).toString())
                .build();
    }

    /**
     * Gets all {@link Media} for a specified {@link Publisher}
     * @param idMedia of the desired {@link Publisher}
     * @return an {@link List} of all matching {@link Media}
     */
    @GET
    @Path("/{idPublisher}/media")
    public Response getMediaForType(@PathParam("idPublisher")int idMedia) {
        List<Media> media = new MediaDAO().getAllForPublisher(idMedia);
        return Response.ok(
                new GenericEntity<>(media) {})
                .build();
    }
}
