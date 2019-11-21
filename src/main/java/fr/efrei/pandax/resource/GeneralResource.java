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
     * Tested with : curl localhost:8080/PandaX_war_exploded
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
     * Tested with : curl -X POST -H "Content-Type: application/x-www-form-urlencoded" -d 'token=<token>' localhost:8080/PandaX_war_exploded
     * @return "Pong !"
     */
    @POST
    @Secured
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response testUserToken(@FormParam("token")String token) {
        return Response.ok("Pong !").build();
    }
}
