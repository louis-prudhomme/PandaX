package fr.efrei.pandax.resource;

import fr.efrei.pandax.model.business.Media;
import fr.efrei.pandax.model.core.MediaDAO;
import fr.efrei.pandax.security.Secured;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Secured
@Path("media")
public class MediaResource {
    /**
     * Returns all the {@link fr.efrei.pandax.model.business.Media} in database.
     * Test with : curl localhost:8080/PandaX_war_exploded/media -H 'Authorization: <token>'
     * @return list of all {@link fr.efrei.pandax.model.business.Media}
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllMedia() {
        //todo document that shit
        List<Media> medias = new MediaDAO().getAll();
        return Response.ok(new GenericEntity<List<Media>>(medias) {}).build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response putMedia(@FormParam("media")Media m) {
        new MediaDAO().create(m);
        return Response.ok().build();
    }
    
}
