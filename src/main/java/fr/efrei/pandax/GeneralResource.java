package fr.efrei.pandax;

<<<<<<< Updated upstream
=======
import fr.efrei.pandax.model.business.User;
import fr.efrei.pandax.model.core.UserDAO;
import javax.ejb.EJB;
>>>>>>> Stashed changes
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Default-case rest entry, useless except for verification purposes
 */
@Path("/")
public class GeneralResource {
<<<<<<< Updated upstream
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response ping() {
=======
	
    UserDAO us;
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response ping() {
    	us = new UserDAO();
    	
        User s = new User("Louis", "ARRETE DE CRAQUER TES PUTAIN DE DOIGTS","coucou");
        us.create((User)s);
>>>>>>> Stashed changes
        return Response.ok("Pong !").build();
    }
}
