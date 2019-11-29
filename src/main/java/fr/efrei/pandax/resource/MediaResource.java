package fr.efrei.pandax.resource;

import fr.efrei.pandax.model.business.Comment;
import fr.efrei.pandax.model.business.Media;
import fr.efrei.pandax.model.core.CommentDAO;
import fr.efrei.pandax.model.core.MediaDAO;
import fr.efrei.pandax.security.Secured;
import fr.efrei.pandax.security.SecurityHelper;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Secured
@Path("media")
public class MediaResource {
    private List<Media> medias;

    private SecurityHelper securityHelper = new SecurityHelper();

    @Context
    HttpHeaders headers;

    @Context
    UriInfo uriInfo;

    /**
     * Returns all the {@link Media} in database with regard to any parameter.
     * @return list of all {@link Media}
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@QueryParam("title")@DefaultValue("")String title,
                           @QueryParam("city")@DefaultValue("")String city,
                           @QueryParam("resume")@DefaultValue("")String resume) {
        medias = new MediaDAO().getAll(title, city, resume);
        return Response.ok(new GenericEntity<>(medias) {}).build();
    }

    /**
     * Adds a {@link fr.efrei.pandax.model.business.Media} to the database through the request /media/{id}
     * @param m : user generated {@link fr.efrei.pandax.model.business.Media}
     * @return request for the {@link fr.efrei.pandax.model.business.Media} creation
     */
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response createOne(@FormParam("media")Media m) {
        if(securityHelper.isIncomingUserAlien(headers, m.getUser().getId()))
            return Response.status(Response.Status.FORBIDDEN).build();

        m = new MediaDAO().create(m);
        return Response
                .ok(uriInfo.getBaseUriBuilder()
                        .path(MediaResource.class)
                        .path(MediaResource.class, "getOne")
                        .build(m.getId()).toString())
                .build();
    }

    /**
     * Deletes a {@link fr.efrei.pandax.model.business.Media} from the database through the request /media/{id}
     * @param id is the {@link Media#id}
     * @return request for the {@link fr.efrei.pandax.model.business.Media} deletion
     */
    @DELETE
    @Path("/{id}")
    @Secured
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteOne(@PathParam("id")int id) {
        MediaDAO md = new MediaDAO();
        Media m = md.read(id);

        if(securityHelper.isIncomingUserAlien(headers, m.getUser().getId()))
            return Response.status(Response.Status.FORBIDDEN).build();

        md.delete(m);
        return Response
                .ok(uriInfo.getBaseUriBuilder()
                        .path(MediaResource.class)
                        .build().toString())
                .build();
    }

    /**
     * Searches for the {@link fr.efrei.pandax.model.business.Media} with the corresponding id
     * @param id is the {@link Media#id}
     * @return Corresponding {@link fr.efrei.pandax.model.business.Media} item
     */
    @GET
    @Path("/{id}")
    @Secured
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOne(@PathParam("id")int id) {
        return Response.ok(new MediaDAO().read(id)).build();
    }

    /**
     * Modifies a {@link fr.efrei.pandax.model.business.Media} from the database through the request /media/{id}
     * @param m : user generated {@link fr.efrei.pandax.model.business.Media}
     * @return request for the {@link fr.efrei.pandax.model.business.Media} modification
     */
    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateOne(@FormParam("media")Media m) {
        if(securityHelper.isIncomingUserAlien(headers, m.getUser().getId()))
            return Response.status(Response.Status.FORBIDDEN).build();

        m = new MediaDAO().modify(m);
        return Response
                .ok(uriInfo.getBaseUriBuilder()
                        .path(MediaResource.class)
                        .path(MediaResource.class, "getOne")
                        .build(m.getId()).toString())
                .build();
    }

    /**
     * Obtains all {@link Comment} for a specific {@link Media}
     * @param id is the {@link Media#id}
     * @return a list of all relevant {@link Comment}
     */
    @GET
    @Path("/{id}/comment")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllComment(@PathParam("id")int id) {
        List<Comment> comments = new CommentDAO().getByMedia(id);
        return Response.ok(new GenericEntity<>(comments) {}).build();
    }

    /**
     * Obtains all {@link Comment} for a specific {@link Media} and a specific      * @param idMedia is the {@link fr.efrei.pandax.model.business.User#id}
     * @param idMedia is the {@link Media#id}
     * @param idUser is the {@link fr.efrei.pandax.model.business.User#id}
     * @return a list of all relevant {@link Comment}
     */
    @GET
    @Path("{idMedia}/user/{idUser}/comment")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMediaCommentsForUser(@PathParam("idUser")int idUser, @PathParam("idMedia")int idMedia) {
        List<Comment> comments = new CommentDAO().getByMediaAndUser(idMedia, idUser);
        return Response.ok(new GenericEntity<>(comments) {}).build();
    }

    /**
     * Obtains a particuliar comment for this particuliar {@link Media}
     * @param idComment is the {@link Comment#id}
     * @param idMedia is the {@link Media#id}
     * @param idUser is the {@link fr.efrei.pandax.model.business.User#id}
     * @return corresponding {@link Comment}
     */
    @GET
    @Path("{idMedia}/user/{idUser}/comment/{idComment}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getComment(@PathParam("idComment")int idComment, @PathParam("idMedia")int idMedia, @PathParam("idUser")int idUser) {
        return Response.seeOther(uriInfo
                .getBaseUriBuilder()
                    .path(CommentResource.class)
                    .path(CommentResource.class, "getOne")
                    .build(idComment, idMedia, idUser))
                .build();
    }

}
