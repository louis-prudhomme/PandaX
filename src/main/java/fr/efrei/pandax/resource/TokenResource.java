package fr.efrei.pandax.resource;

import fr.efrei.pandax.model.business.Media;
import fr.efrei.pandax.model.business.User;
import fr.efrei.pandax.security.Role;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@Path("token")
public class TokenResource {
    private Properties tokenProperties;
    private SecretKeySpec key;
    private User louis;
    private Date expDate;

    private void ribouchon() throws IOException {
        tokenProperties = new Properties();
        tokenProperties.load(this.getClass().getClassLoader()
                .getResourceAsStream("META-INF/security.properties"));
        key = new SecretKeySpec(tokenProperties.getProperty("secret").getBytes(),
                SignatureAlgorithm.HS256.getJcaName());
        louis = new User(0, "Louis", "ARRETE DE CRAQUER TES PUTAIN DE DOIGTS", true);
        expDate = new Date(TimeUnit.SECONDS.toMillis(1574500000L));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    //test me with
    //curl localhost:8080/PandaX_war_exploded/token
    public Response getToken() throws IOException {
        ribouchon();

        String token = Jwts.builder().signWith(key)
                .setSubject(String.valueOf(louis.getId()))
                .setExpiration(expDate)
                .claim("role", louis.isAdmin() ? Role.ADMIN : Role.USER)
                .claim("name", louis.getUser())
                .compact();
        return Response.ok(token).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    //test me with
    //curl -X POST -H "Content-Type: application/x-www-form-urlencoded" -d 'token=eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIwIiwiZXhwIjoxNTc0NTAwMDAwLCJyb2xlIjoiQURNSU4iLCJuYW1lIjoiTG91aXMifQ.JLNDojoG_w0b8rkO3mLWYvpOPOZIb6ih3MxaJ44Yhit2xhAU2xvZK28ao-_fgBX3W8EoLYDlQhe7NjY8HrEKfg' localhost:8080/PandaX_war_exploded/token
    public Response verifyToken(@FormParam("token")String token) throws IOException {
        ribouchon();
        try {
            Jwts.parser().setSigningKey(key)
                    .requireSubject(String.valueOf(louis.getId()))
                    .requireExpiration(expDate)
                    .require("role", louis.isAdmin() ? Role.ADMIN.name() : Role.USER.name())
                    .require("name", louis.getUser())
                    .parse(token);
            return Response.ok("Pong !").build();
        } catch (Exception e) {
            return Response.ok("ntm").build();
        }
    }
}
