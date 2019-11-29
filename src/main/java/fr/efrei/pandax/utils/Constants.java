package fr.efrei.pandax.utils;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

/**
 * Singleton representing the constants of the application.
 * Those constants are defined in the application properties file.
 * They should be matched by a public static final {@link String} here, too.
 */
public class Constants {
    private static final String PROP_FILE_LOCATION = "META-INF/app.properties";
    public static final String PROP_APP_NAME = "appName";
    public static final String PROP_SECRET = "secret";
    public static final String PROP_TOKEN_VALIDITY = "tokenDaysValid";

    /**
     * Singleton instance.
     */
    private static Constants instance;

    /**
     * Instance’s properties set.
     */
    private Properties properties;

    /**
     * Obtains the only possible instance of {@link Constants}.
     * @return {@link Constants} instance.
     */
    public static Constants getConstants() {
        if (instance == null)
            instance = new Constants();
        return instance;
    }

    /**
     * Constructor ; is private for Singleton reasons
     */
    private Constants() {
        properties = new Properties();
        try {
            properties.load(Objects.requireNonNull(this.getClass().getClassLoader()
                    .getResourceAsStream(PROP_FILE_LOCATION)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtains an application property through its name
     * @param propertyName {@link String} representing the name of the property
     * @return property as a {@link String}
     */
    public String getProperty(String propertyName) {
        return this.properties.getProperty(propertyName);
    }
}
