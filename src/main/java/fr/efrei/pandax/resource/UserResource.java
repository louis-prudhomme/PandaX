package fr.efrei.pandax.resource;

import fr.efrei.pandax.model.business.Media;
import fr.efrei.pandax.model.business.Comment;
import fr.efrei.pandax.model.business.User;
import fr.efrei.pandax.model.core.CommentDAO;
import fr.efrei.pandax.model.core.UserDAO;
import fr.efrei.pandax.security.Role;
import fr.efrei.pandax.security.Secured;
import fr.efrei.pandax.security.SecurityHelper;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

import static javax.ws.rs.core.MediaType.*;

@Secured
@Path("user")
public class UserResource {
    private SecurityHelper securityHelper = new SecurityHelper();

    @Context
    private HttpHeaders headers;

    @Context
    private UriInfo uriInfo;

    @GET
    @Produces(APPLICATION_JSON)
    public Response getAll() {
        List<User> all = new UserDAO().getAll();
        return Response.ok(new GenericEntity<>(all) {}).build();
    }

    @POST
    @Secured(Role.ADMIN)
    @Consumes(APPLICATION_FORM_URLENCODED)
    public Response createOne(@FormParam("user")User user) {
        user = new UserDAO().create(user);
        return Response
                .ok(uriInfo.getBaseUriBuilder()
                        .path(UserResource.class)
                        .path(UserResource.class, "getOne")
                        .build(user.getId()).toString())
                .build();
    }

    @DELETE
    @Path("/{id}")
    @Secured(Role.ADMIN)
    @Produces(APPLICATION_JSON)
    public Response deleteOne(@PathParam("id")int id) {
        UserDAO dao = new UserDAO();
        dao.delete(dao.read(id));
        return Response
                .ok(uriInfo.getBaseUriBuilder()
                        .path(UserResource.class)
                        .build().toString())
                .build();
    }

    @GET
    @Path("/{id}")
    @Produces(APPLICATION_JSON)
    public Response getOne(@PathParam("id")int id) {
        return Response.ok(new UserDAO().read(id)).build();
    }

    @PUT
    @Consumes(APPLICATION_FORM_URLENCODED)
    public Response updateOne(@FormParam("user") User user, @Context HttpHeaders headers) {
        if(securityHelper.isIncomingUserAlien(headers, user.getId()))
            return Response.status(Response.Status.FORBIDDEN).build();

        user = new UserDAO().modify(user);
        return Response
                .ok(uriInfo.getBaseUriBuilder()
                        .path(UserResource.class)
                        .path(UserResource.class, "getOne")
                        .build(user.getId()).toString())
                .build();
    }

    @GET
    @Path("/{id}/media")
    @Produces(APPLICATION_JSON)
    public Response getUserPossession(@PathParam("id")int id) {
        List<Media> allPossessions = new UserDAO().getAllPossessions(id);
        return Response.ok(new GenericEntity<>(allPossessions) {}).build();
    }

    @GET
    @Path("/{id}/comment")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllComment(@PathParam("id")int id) {
        List<Comment> comments = new CommentDAO().getByUser(id);
        return Response.ok(new GenericEntity<>(comments) {}).build();
    }
    
    @GET
    @Path("{idUser}/media/{idMedia}/comment")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserCommentsForMedia(@PathParam("idUser")int idUser, @PathParam("idMedia")int idMedia) {
        return Response.seeOther(uriInfo
                .getBaseUriBuilder()
                    .path(MediaResource.class)
                    .path(MediaResource.class, "getMediaCommentsForUser")
                    .build(idMedia, idUser))
                .build();
    }

    @GET
    @Path("{idUser}/media/{idMedia}/comment/{idComment}")
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
