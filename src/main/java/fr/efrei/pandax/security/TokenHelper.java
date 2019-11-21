package fr.efrei.pandax.security;

import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Calendar;
import java.util.Date;

import static fr.efrei.pandax.utils.Constants.*;

/**
 * This class helps every jwt token-related business.
 * It essentially generates some utilities to ease its usage.
 */
public class TokenHelper {
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
}
