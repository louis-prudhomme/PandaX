package fr.efrei.pandax.resource;

import fr.efrei.pandax.model.business.Comment;
import fr.efrei.pandax.model.business.Media;
import fr.efrei.pandax.model.core.CommentDAO;
import fr.efrei.pandax.model.core.MediaDAO;
import fr.efrei.pandax.security.Role;
import fr.efrei.pandax.security.Secured;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Secured
@Path("media")
public class MediaResource {
    @Context
    UriInfo uriInfo;
    /**
     * Returns all the {@link fr.efrei.pandax.model.business.Media} in database.
     * Test with : curl localhost:8080/PandaX_war_exploded/media -H 'Authorization: <token>'
     * @return list of all {@link fr.efrei.pandax.model.business.Media}
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        //todo document that shit
        List<Media> medias = new MediaDAO().getAll();
        return Response.ok(new GenericEntity<>(medias) {}).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response createOne(@FormParam("media")Media m) {
        m = new MediaDAO().create(m);
        return Response
                .ok(uriInfo.getBaseUriBuilder()
                        .path(MediaResource.class)
                        .path(MediaResource.class, "getOne")
                        .build(m.getId()).toString())
                .build();
    }

    @DELETE
    @Path("/{id}")
    @Secured(Role.ADMIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteOne(@PathParam("id")int id) {
        MediaDAO md = new MediaDAO();
        md.delete(md.read(id));
        return Response
                .ok(uriInfo.getBaseUriBuilder()
                        .path(MediaResource.class)
                        .path(MediaResource.class, "getAll")
                        .build().toString())
                .build();
    }

    @GET
    @Path("/{id}")
    @Secured
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOne(@PathParam("id")int id) {
        return Response.ok(new MediaDAO().read(id)).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateOne(@FormParam("media")Media m) {
        m = new MediaDAO().modify(m);
        return Response
                .ok(uriInfo.getBaseUriBuilder()
                        .path(MediaResource.class)
                        .path(MediaResource.class, "getOne")
                        .build(m.getId()).toString())
                .build();
    }

    @GET
    @Path("/{id}/comment")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllComment(@PathParam("id")int id) {
        List<Comment> comments = new CommentDAO().getByMedia(id);
        return Response.ok(new GenericEntity<>(comments){}).build();
    }

    @GET
    @Path("{idMedia}/user/{idUser}/comment")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMediaCommentsForUser(@PathParam("idUser")int idUser, @PathParam("idMedia")int idMedia) {
        List<Comment> comments = new CommentDAO().getByMediaAndUser(idMedia, idUser);
        return Response.ok(new GenericEntity<>(comments){}).build();
    }

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
