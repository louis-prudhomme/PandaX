package fr.efrei.pandax.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.core.HttpHeaders;
import java.util.Calendar;
import java.util.Date;

import static fr.efrei.pandax.utils.Constants.*;

/**
 * This class helps every jwt token-related business.
 * It essentially generates some utilities to ease its usage.
 */
public class SecurityHelper {
    /**
     * Generates an expiration date, which is a {@link Date} pointing in the future.
     * The time skew is defined in the application properties file.
     * @return {@link Date} representing a future expiration date.
     */
    public Date generateExpirationDate() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, Integer.parseInt(getConstants().getProperty(PROP_TOKEN_VALIDITY)));
        return c.getTime();
    }

    /**
     * Generates a {@link SecretKey} which essentially is a hashed {@link String}.
     * The {@link String} to be hashed is defined through the application properties file.
     * @return a {@link SecretKey} corresponding to the application configuration.
     */
    public SecretKey generateSecretKey() {
        return new SecretKeySpec(getConstants().getProperty(PROP_SECRET).getBytes(),
                SignatureAlgorithm.HS256.getJcaName());
    }

    /**
     * Checks if the user originating the request matches the expected id
     * @param expectedId for the user
     * @return true if the user is allowed
     */
    private boolean isIncomingUserExpected(HttpHeaders headers, int expectedId) {
        return Integer.parseInt(Jwts.parser()
                .setSigningKey(generateSecretKey())
                .parseClaimsJws(headers
                    .getRequestHeader(HttpHeaders.AUTHORIZATION)
                    .get(0))
                .getBody()
                .getSubject()) == expectedId;
    }

    /**
     * Checks if the user originating the request is an admin
     * @return true if the user is an admin
     */
    private boolean isIncomingUserAdmin(HttpHeaders headers) {
        return Role.fromString(Jwts.parser()
                .setSigningKey(generateSecretKey())
                .parseClaimsJws(headers
                    .getRequestHeader(HttpHeaders.AUTHORIZATION)
                    .get(0))
                .getBody()
                .get("rol", String.class)) == Role.ADMIN;
    }

    /**
     * Checks if the user is either an admin or has the {@link fr.efrei.pandax.model.business.User#id} providied
     * @param headers {@link HttpHeaders} of the request
     * @param expectedId {@link fr.efrei.pandax.model.business.User#id} to check against
     * @return true if allowed
     */
    public boolean isIncomingUserAlien(HttpHeaders headers, int expectedId) {
        return !isIncomingUserAdmin(headers) && !isIncomingUserExpected(headers, expectedId);
    }
}
