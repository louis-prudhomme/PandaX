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
        return Response
                .ok(uriInfo.getBaseUriBuilder()
                    .path(MediaTypeResource.class)
                    .path(MediaTypeResource.class, "getOne")
                    .build(type.getId()).toString())
                .build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(APPLICATION_JSON)
    public Response deleteOne(@PathParam("id")int id) {
        MediaTypeDAO dao = new MediaTypeDAO();
        dao.delete(dao.read(id));
        return Response
                .ok(uriInfo.getBaseUriBuilder()
                    .path(MediaTypeResource.class)
                    .path(MediaTypeResource.class, "getAll")
                    .build().toString())
                .build();
    }

    @GET
    @Path("/{id}")
    @Secured({Role.USER, Role.ADMIN})
    @Produces(APPLICATION_JSON)
    public Response getOne(@PathParam("id")int id) {
        return Response.ok(
                new MediaTypeDAO().read(id))
                .build();
    }

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
    @Path("/{idMediaType}/media")
    public Response getMediaForType(@PathParam("idMediaType")int idMedia) {
        List<Media> media = new MediaDAO().getAllForType(idMedia);
        return Response.ok(
                new GenericEntity<>(media) {})
                .build();
    }
}
