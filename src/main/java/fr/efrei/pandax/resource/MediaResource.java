package fr.efrei.pandax.resource;

import fr.efrei.pandax.model.business.Media;
import fr.efrei.pandax.model.core.MediaDAO;
import fr.efrei.pandax.security.Role;
import fr.efrei.pandax.security.Secured;

import javax.ws.rs.*;
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
        return Response.ok(new GenericEntity<>(medias) {}).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response createMedia(@FormParam("media")Media m) {
        m = new MediaDAO().create(m);
        return Response.ok("/media/" + m.getId()).build();
    }

    @DELETE
    @Path("/{id}")
    @Secured(Role.ADMIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteOne(@PathParam("id")int id) {
        MediaDAO md = new MediaDAO();
        md.delete(md.read(id));
        return Response.ok("/media/").build();
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
    public Response updateMedia(@FormParam("media")Media m) {
        m = new MediaDAO().modify(m);
        return Response.ok("/media/" + m.getId()).build();
    }
}
