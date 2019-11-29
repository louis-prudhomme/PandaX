package fr.efrei.pandax.resource;

import fr.efrei.pandax.model.business.*;
import fr.efrei.pandax.model.core.CommentDAO;
import fr.efrei.pandax.model.core.PossessionDAO;
import fr.efrei.pandax.model.core.UserDAO;
import fr.efrei.pandax.security.Role;
import fr.efrei.pandax.security.Secured;
import fr.efrei.pandax.security.SecurityHelper;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.MediaType;
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

    /**
     * Returns all the {@link fr.efrei.pandax.model.business.User} in database.
     * @return list of all {@link fr.efrei.pandax.model.business.User}
     */
    @GET
    @Produces(APPLICATION_JSON)
    public Response getAll() {
        List<User> all = new UserDAO().getAll();
        return Response.ok(new GenericEntity<>(all) {}).build();
    }

    /**
     * Adds a {@link fr.efrei.pandax.model.business.User} to the database through the request /user/{id}
     * @param user : user generated {@link fr.efrei.pandax.model.business.User}
     * @return request for the {@link fr.efrei.pandax.model.business.User} creation
     */
    @POST
    @Secured(Role.ADMIN)
    @Consumes(APPLICATION_FORM_URLENCODED)
    public Response createOne(@FormParam("user")User user) {
        UserDAO dao = new UserDAO();
        user = dao.create(user);
        return Response
                .ok(uriInfo.getBaseUriBuilder()
                        .path(UserResource.class)
                        .path(UserResource.class, "getOne")
                        .build(user.getId()).toString())
                .build();
    }

    /**
     * Deletes a {@link fr.efrei.pandax.model.business.User} from the database through the request /user/{id}
     * @param id {@link User#id}
     * @return request for the {@link fr.efrei.pandax.model.business.User} deletion
     */
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

    /**
     * Searches for the {@link fr.efrei.pandax.model.business.User} with the corresponding id
     * @param id {@link User#id}
     * @return Corresponding {@link fr.efrei.pandax.model.business.User} item
     */
    @GET
    @Path("/{id}")
    @Produces(APPLICATION_JSON)
    public Response getOne(@PathParam("id")int id) {
        return Response.ok(new UserDAO().read(id)).build();
    }

    /**
     * Modifies a {@link fr.efrei.pandax.model.business.User} from the database through the request /user/{id}
     * @param user : user generated {@link fr.efrei.pandax.model.business.User}
     * @return request for the {@link fr.efrei.pandax.model.business.User} modification
     */
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

    /**
     * Obtains all the {@link Media} related to a particular {@link User}
     * @param id {@link User#id}
     * @return all the relevant {@link Media}
     */
    @GET
    @Path("/{id}/media")
    @Produces(APPLICATION_JSON)
    public Response getUserPossession(@PathParam("id")int id) {
        List<Media> allPossessions = new UserDAO().getAllPossessions(id);
        return Response.ok(new GenericEntity<>(allPossessions) {}).build();
    }

    /**
     * Obtains all the {@link Comment} related to a particular {@link User}
     * @param id {@link User#id}
     * @return all the relevant {@link Comment}
     */
    @GET
    @Path("/{id}/comment")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllComment(@PathParam("id")int id) {
        List<Comment> comments = new CommentDAO().getByUser(id);
        return Response.ok(new GenericEntity<>(comments) {}).build();
    }

    /**
     * Obtains all comments for this particuliar {@link User} and a specific {@link Media}
     * @param idMedia is the {@link Media#id}
     * @param idUser is the {@link fr.efrei.pandax.model.business.User#id}
     * @return corresponding {@link Comment}
     */
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

    /**
     * Returns the {@link fr.efrei.pandax.model.business.Possession} associated with the {@link fr.efrei.pandax.model.business.User} id and {@link fr.efrei.pandax.model.business.Media} id
     * @param idComment {@link fr.efrei.pandax.model.business.Comment} id
     * @param idUser {@link fr.efrei.pandax.model.business.User} id
     * @param idMedia {@link fr.efrei.pandax.model.business.Media} id
     * @return the corresponding {@link fr.efrei.pandax.model.business.Possession}
     */
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

    /**
     * Creates a {@link Possession} for the specified {@link User} and {@link Media}
     * @param idUser {@link User#id}
     * @param idMedia {@link Media#id}
     * @return a link to the newly created ressource
     */
    @POST
    @Path("{idUser}/media/{idMedia}")
    @Consumes(APPLICATION_FORM_URLENCODED)
    public Response createOnePossession(@PathParam("idUser")int idUser, @PathParam("idMedia") int idMedia){
        if(securityHelper.isIncomingUserAlien(headers, idUser))
            return Response.status(Response.Status.FORBIDDEN).build();

        Possession possession = new Possession();
        possession.setPossessionPK(new PossessionPK(idUser,idMedia));
        new PossessionDAO().create(possession);
        return Response
                .ok(uriInfo.getBaseUriBuilder()
                        .path(UserResource.class)
                        .path(UserResource.class, "getUserPossession")
                        .build(possession.getPossessionPK().getUser(), possession.getPossessionPK().getMedia()).toString())
                .build();
    }

    /**
     * Deletes the {@link fr.efrei.pandax.model.business.Possession} associated with the {@link fr.efrei.pandax.model.business.User} id and {@link fr.efrei.pandax.model.business.Media} id through the /possession/{id} request
     * @param idUser {@link fr.efrei.pandax.model.business.User} id
     * @param idMedia {@link fr.efrei.pandax.model.business.Media} id
     * @return the request for the corresponding {@link fr.efrei.pandax.model.business.Possession} deletion
     */
    @DELETE
    @Path("{idUser}/media/{idMedia}")
    @Produces(APPLICATION_JSON)
    public Response deleteOnePossession(@PathParam("idUser")int idUser, @PathParam("idMedia") int idMedia) {
        if(securityHelper.isIncomingUserAlien(headers, idUser))
            return Response.status(Response.Status.FORBIDDEN).build();

        PossessionDAO dao = new PossessionDAO();
        dao.deletePossession(dao.readPossession(idUser,idMedia));
        return Response
                .ok(uriInfo.getBaseUriBuilder()
                        .path(UserResource.class)
                        .build().toString())
                .build();
    }
}

