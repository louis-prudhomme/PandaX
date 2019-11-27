package fr.efrei.pandax.resource;

import fr.efrei.pandax.security.Secured;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Default-case rest entry, useless except for verification purposes
 * Serves as both a prof-of-concept and an example case for the rest of the project
 * //todo delete at the end of the project
 */
@Path("/")
public class GeneralResource {
    /**
     * Allows one to test if the application responds correctly.
     * It should respond with "Pong !".
     * @return "Pong !"
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response ping() {
        return Response.ok("Pong !").build();
    }

    /**
     * Allows one to test if the application reacts correctly with security.
     * Responds with "Pong !" if the provided {@link String}-represented JWT is valid.
     * @return "Pong !"
     */
    @POST
    @Secured
    @Produces(MediaType.TEXT_PLAIN)
    public Response testUserToken() {
        return Response.ok("Pong !").build();
    }
}
