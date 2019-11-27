package fr.efrei.pandax.resource;

import fr.efrei.pandax.model.business.User;
import fr.efrei.pandax.model.core.UserDAO;
import fr.efrei.pandax.security.Role;
import fr.efrei.pandax.security.SecurityHelper;
import io.jsonwebtoken.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

import static fr.efrei.pandax.utils.Constants.*;

/**
 * Entry point for JWT-related business.
 * Will essentially allow any user to verify its identity.
 */
@Path("token")
public class TokenResource {
    /**
     * Provides a JWT if the provided {@param login} and {@param password} match a {@link User} in database.
     * @param login claimed login of the {@link User}
     * @param password claimed password of the {@link User}
     * Test with : curl localhost:8080/PandaX_war_exploded/token -X POST -H "Content-Type: application/x-www-form-urlencoded" -d 'login=<login>&password=<pass>' localhost:8080/PandaX_war_exploded/token
     * @return a {@link String}-represented base64-encoded JWT
     */
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response getToken(@FormParam("login")String login, @FormParam("password")String password) {
        //todo handle wrong credentials
        User u = new UserDAO().checkCredentials(login, password);

        //todo add more claims ?
        String token = Jwts.builder().signWith(new SecurityHelper().generateSecretKey())
                .setSubject(String.valueOf(u.getId()))
                .setExpiration(new SecurityHelper().generateExpirationDate())
                .setIssuedAt(new Date())
                .setIssuer(getConstants().getProperty(PROP_APP_NAME))
                .claim("rol", u.isAdmin() ? Role.ADMIN : Role.USER)
                .claim("nam", u.getPseudo())
                .compact();

        return Response.ok(token).build();
    }
}
