package fr.efrei.pandax.resource.converter;

import com.google.gson.Gson;
import fr.efrei.pandax.model.business.User;

import javax.ws.rs.ext.ParamConverter;

/**
 * Converts {@link User} to JSON (and vice-versa).
 */
public class UserConverter implements ParamConverter<User> {
    private Gson cypher = new Gson();

    @Override
    public User fromString(String s) {
        return cypher.fromJson(s, User.class);
    }

    @Override
    public String toString(User user) {
        return cypher.toJson(user);
    }
}
