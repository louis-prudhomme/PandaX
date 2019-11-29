package fr.efrei.pandax.resource;

import fr.efrei.pandax.model.business.Media;
import fr.efrei.pandax.model.business.MediaType;
import fr.efrei.pandax.model.core.MediaDAO;
import fr.efrei.pandax.model.core.MediaTypeDAO;
import fr.efrei.pandax.security.Role;
import fr.efrei.pandax.security.Secured;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

import static javax.ws.rs.core.MediaType.*;

@Secured(Role.ADMIN)
@Path("mediatype")
public class MediaTypeResource {
    @Context
    UriInfo uriInfo;

    /**
     * Returns all the {@link fr.efrei.pandax.model.business.MediaType} in database.
     * @return list of all {@link fr.efrei.pandax.model.business.MediaType}
     */
    @GET
    @Secured
    @Produces(APPLICATION_JSON)
    public Response getAll() {
        List<MediaType> all = new MediaTypeDAO().getAll();
        return Response.ok(new GenericEntity<>(all) { }).build();
    }

    /**
     * Adds a {@link fr.efrei.pandax.model.business.MediaType} to the database through the request /mediatype/id
     * @param type : user generated {@link fr.efrei.pandax.model.business.MediaType}
     * @return request for the {@link fr.efrei.pandax.model.business.MediaType} creation
     */
    @POST
    @Consumes(APPLICATION_FORM_URLENCODED)
    public Response createOne(@FormParam("mediatype")MediaType type) {
        type = new MediaTypeDAO().create(type);
        return Response
                .ok(uriInfo.getBaseUriBuilder()
                    .path(MediaTypeResource.class)
                    .path(MediaTypeResource.class, "getOne")
                    .build(type.getId()).toString())
                .build();
    }

    /**
     * Deletes a {@link fr.efrei.pandax.model.business.MediaType} from the database through the request /mediatype/id
     * @param id
     * @return request for the {@link fr.efrei.pandax.model.business.MediaType} deletion
     */
    @DELETE
    @Path("/{id}")
    @Produces(APPLICATION_JSON)
    public Response deleteOne(@PathParam("id")int id) {
        MediaTypeDAO dao = new MediaTypeDAO();
        dao.delete(dao.read(id));
        return Response
                .ok(uriInfo.getBaseUriBuilder()
                    .path(MediaTypeResource.class)
                    .build().toString())
                .build();
    }

    /**
     * Searches for the {@link fr.efrei.pandax.model.business.MediaType} with the corresponding id
     * @param id
     * @return Corresponding {@link fr.efrei.pandax.model.business.MediaType} item
     */
    @GET
    @Path("/{id}")
    @Secured
    @Produces(APPLICATION_JSON)
    public Response getOne(@PathParam("id")int id) {
        return Response.ok(
                new MediaTypeDAO().read(id))
                .build();
    }

    /**
     * Searches for the {@link fr.efrei.pandax.model.business.MediaType} with the corresponding id
     * @param type {@link MediaType} to update.
     * @return Corresponding {@link fr.efrei.pandax.model.business.MediaType} item
     */
    @PUT
    @Consumes(APPLICATION_FORM_URLENCODED)
    public Response updateOne(@FormParam("mediatype")MediaType type) {
        type = new MediaTypeDAO().modify(type);
        return Response
                .ok(uriInfo.getBaseUriBuilder()
                        .path(MediaTypeResource.class)
                        .path(MediaTypeResource.class, "getOne")
                        .build(type.getId()).toString())
                .build();
    }

    /**
     * Gets all {@link Media} for a specified {@link MediaType}
     * @param idMedia of the desired {@link MediaType}
     * @return an {@link List} of all matching {@link Media}
     */
    @GET
    @Secured
    @Path("/{idMediaType}/media")
    public Response getMediaForType(@PathParam("idMediaType")int idMedia) {
        List<Media> media = new MediaDAO().getAllForType(idMedia);
        return Response.ok(
                new GenericEntity<>(media) {})
                .build();
    }
}
