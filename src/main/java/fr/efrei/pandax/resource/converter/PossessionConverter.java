package fr.efrei.pandax.resource.converter;

import com.google.gson.Gson;
import fr.efrei.pandax.model.business.Possession;

import javax.ws.rs.ext.ParamConverter;

/**
 * Converts {@link Possession} to JSON (and vice-versa).
 */
public class PossessionConverter implements ParamConverter<Possession> {
    private Gson cypher = new Gson();

    @Override
    public Possession fromString(String s) {
        return cypher.fromJson(s, Possession.class);
    }

    @Override
    public String toString(Possession p) {
        return cypher.toJson(p);
    }
}
