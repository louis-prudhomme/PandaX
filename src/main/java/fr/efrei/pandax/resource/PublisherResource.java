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

    /**
     * Returns all the {@link fr.efrei.pandax.model.business.Publisher} in database.
     * @return list of all {@link fr.efrei.pandax.model.business.Publisher}
     */
    @GET
    @Secured
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Publisher> all = new PublisherDAO().getAll();
        return Response.ok(new GenericEntity<>(all) { }).build();
    }

    /**
     * Adds a {@link fr.efrei.pandax.model.business.Publisher} to the database through the request /publisher/{id}
     * @param p : user generated {@link fr.efrei.pandax.model.business.Publisher}
     * @return request for the {@link fr.efrei.pandax.model.business.Publisher} creation
     */
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

    /**
     * Deletes a {@link fr.efrei.pandax.model.business.Publisher} from the database through the request /publisher/{id}
     * @param id
     * @return request for the {@link fr.efrei.pandax.model.business.Publisher} deletion
     */
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteOne(@PathParam("id")int id) {
        PublisherDAO dao = new PublisherDAO();
        dao.delete(dao.read(id));
        return Response
                .ok(uriInfo.getBaseUriBuilder()
                    .path(PublisherResource.class)
                    .build().toString())
                .build();
    }

    /**
     * Searches for the {@link fr.efrei.pandax.model.business.Publisher} with the corresponding id
     * @param id
     * @return Corresponding {@link fr.efrei.pandax.model.business.Publisher} item
     */
    @GET
    @Path("/{id}")
    @Secured
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOne(@PathParam("id")int id) {
        return Response.ok(new PublisherDAO().read(id)).build();
    }

    /**
     * Modifies a {@link fr.efrei.pandax.model.business.Publisher} from the database through the request /publisher/{id}
     * @param p : user generated {@link fr.efrei.pandax.model.business.Publisher}
     * @return request for the {@link fr.efrei.pandax.model.business.Publisher} modification
     */
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
    @Secured
    @Path("/{idPublisher}/media")
    public Response getMediaForType(@PathParam("idPublisher")int idMedia) {
        List<Media> media = new MediaDAO().getAllForPublisher(idMedia);
        return Response.ok(
                new GenericEntity<>(media) { })
                .build();
    }
}
