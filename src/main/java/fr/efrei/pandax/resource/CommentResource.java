package fr.efrei.pandax.resource;

import fr.efrei.pandax.model.business.Comment;
import fr.efrei.pandax.model.core.CommentDAO;
import fr.efrei.pandax.security.Secured;
import fr.efrei.pandax.security.SecurityHelper;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import java.util.List;

@Secured
@Path("comment")
public class CommentResource {
    @Context
    UriInfo uriInfo;

    @Context
    private HttpHeaders headers;

    SecurityHelper securityHelper = new SecurityHelper();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Comment> comments = new CommentDAO().getAll();
        return Response.ok(new GenericEntity<>(comments) {}).build();
    }

    @GET
    @Path("{idComment}/media/{idMedia}/user/{idUser}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOne(@PathParam("idComment")int idComment, @PathParam("idMedia")int idMedia, @PathParam("idUser")int idUser) {
        Comment c = new CommentDAO().getByPk(idComment, idMedia, idUser);
        return Response.ok(c).build();
    }

    @DELETE
    @Path("{idComment}/media/{idMedia}/user/{idUser}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteOne(@PathParam("idComment")int idComment, @PathParam("idMedia")int idMedia, @PathParam("idUser")int idUser) {
        CommentDAO dao = new CommentDAO();
        Comment c = dao.getByPk(idComment, idMedia, idUser);

        if(securityHelper.isIncomingUserAlien(headers, c.getUser().getId()))
            return Response.status(Response.Status.FORBIDDEN).build();

        dao.delete(c);
        return Response
                .ok(uriInfo.getBaseUriBuilder()
                        .path(CommentResource.class)
                        .build()
                        .toString())
                .build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateOne(@FormParam("comment")Comment c) {
        if(securityHelper.isIncomingUserAlien(headers, c.getUser().getId()))
            return Response.status(Response.Status.FORBIDDEN).build();

        CommentDAO dao = new CommentDAO();
        dao.modify(c);
        return Response
                .ok(uriInfo.getBaseUriBuilder()
                        .path(CommentResource.class)
                        .path(CommentResource.class, "getOne")
                        .build(c.getId(),
                                c.getMedia().getId(),
                                c.getUser().getId())
                        .toString())
                .build();
    }

    /**
     *
     * @param c
     * @return
     */
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response createOne(@FormParam("comment")Comment c) {
        if(securityHelper.isIncomingUserAlien(headers, c.getUser().getId()))
            return Response.status(Response.Status.FORBIDDEN).build();

        CommentDAO dao = new CommentDAO();
        c = dao.create(c);
        return Response
                .ok(uriInfo.getBaseUriBuilder()
                        .path(CommentResource.class)
                        .path(CommentResource.class, "getOne")
                        .build(c.getId(),
                                c.getMedia().getId(),
                                c.getUser().getId())
                        .toString())
                .build();
    }
}
