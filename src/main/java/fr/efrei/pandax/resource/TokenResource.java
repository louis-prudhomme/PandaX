package fr.efrei.pandax.resource;

import fr.efrei.pandax.model.business.User;
import fr.efrei.pandax.model.core.UserDAO;
import fr.efrei.pandax.security.Role;
import io.jsonwebtoken.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

@Path("token")
public class TokenResource {
    private final int TOKEN_VALIDITY_DURATION_DAYS = 5;
    private final String PANDAX_PROPERTY_FILE_LOCATION = "META-INF/security.properties";
    private final String PANDAX_APP_NAME = "PandaX REST API";

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    //test me with
    //curl localhost:8080/PandaX_war_exploded/token
    public Response getToken() throws IOException {
        User u = new UserDAO().checkCredentials("louis", "lemmy-ftw");

        String token = Jwts.builder().signWith(generateSecretKey())
                .setSubject(String.valueOf(u.getId()))
                .setExpiration(generateExpirationDate())
                .setIssuedAt(new Date())
                .setIssuer(PANDAX_APP_NAME)
                .claim("rol", u.isAdmin() ? Role.ADMIN : Role.USER)
                .claim("nam", u.getUser())
                .compact();
        return Response.ok(token).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    //test me with
    //curl -X POST -H "Content-Type: application/x-www-form-urlencoded" -d 'token=<token>' localhost:8080/PandaX_war_exploded/token
    public Response verifyToken(@FormParam("token")String token) throws IOException {
        Claims jwsMap = (Claims) Jwts.parser().setSigningKey(generateSecretKey()).parse(token).getBody();
        return Response.ok("User is named " + jwsMap.get("nam", String.class) +
                " and he is " + jwsMap.get("rol", String.class))
                .build();
    }

    private Date generateExpirationDate() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, TOKEN_VALIDITY_DURATION_DAYS);
        return c.getTime();
    }

    private SecretKey generateSecretKey() throws IOException {
        Properties tokenProperties = new Properties();
        tokenProperties.load(this.getClass().getClassLoader()
                .getResourceAsStream(PANDAX_PROPERTY_FILE_LOCATION));

        return new SecretKeySpec(tokenProperties.getProperty("secret").getBytes(),
                SignatureAlgorithm.HS256.getJcaName());
    }
}
