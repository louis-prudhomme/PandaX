package fr.efrei.pandax;

import fr.efrei.pandax.model.business.User;
import fr.efrei.pandax.model.core.UserDAO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Default-case rest entry, useless except for verification purposes
 * Serves as both a prof-of-concept and an exemple case for the rest of the project
 * //todo delete at the end of the project
 */
@Path("/")
public class GeneralResource {
    UserDAO us;
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response ping() {
    	us = new UserDAO();
    	
        User s = new User("Louis", "ARRETE DE CRAQUER TES PUTAIN DE DOIGTS", true);
        us.create((User)s);
        return Response.ok("Pong !").build();
    }
}
